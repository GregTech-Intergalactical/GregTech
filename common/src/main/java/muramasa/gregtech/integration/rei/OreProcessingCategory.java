package muramasa.gregtech.integration.rei;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Slot;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import muramasa.antimatter.Data;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.gui.SlotData;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.integration.rei.category.RecipeMapDisplay;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.FluidIngredient;
import muramasa.gregtech.Ref;
import muramasa.gregtech.data.GregTechMaterialTags;
import muramasa.gregtech.data.Machines;
import muramasa.gregtech.data.Materials;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.integration.rei.REIUtils.toREIFLuidStack;
import static muramasa.antimatter.material.MaterialTags.*;

public class OreProcessingCategory implements DisplayCategory<OreProcessingDisplay> {
    protected static Renderer icon = EntryStacks.of(Items.IRON_ORE);
    private static Component title = new TranslatableComponent(Ref.ID + ".rei.tooltip.ore.byproducts");
    static CategoryIdentifier<? extends OreProcessingDisplay> id = CategoryIdentifier.of(Ref.ID, "ore_byproducts");

    @Override
    public CategoryIdentifier<? extends OreProcessingDisplay> getCategoryIdentifier() {
        return id;
    }

    @Override
    public List<Widget> setupDisplay(OreProcessingDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createDrawableWidget((helper, matrices, mouseX, mouseY, delta) -> {
            drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/ore_byproducts/background.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/ore_byproducts/base.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            if (display.bathingMode != OreProcessingDisplay.BathingMode.NONE){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/ore_byproducts/chem.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if (display.ore.has(AntimatterMaterialTypes.GEM)){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/ore_byproducts/sift.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if (display.sepMode != OreProcessingDisplay.SepMode.NONE){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/ore_byproducts/sep.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if (DIRECT_SMELT_INTO.getMapping(display.ore) != null){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/ore_byproducts/smelt1.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if (display.ore.has(DUST) && display.ore.has(INGOT)){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/ore_byproducts/smelt2.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if (display.ore.has(MaterialTags.NEEDS_BLAST_FURNACE) && display.ore.has(INGOT_HOT)){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/ore_byproducts/vac.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
        }));
        widgets.addAll(setupSlots(display, bounds));
        return widgets;
    }

    private List<Widget> setupSlots(OreProcessingDisplay display, Rectangle bounds){
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createSlot(xy(4, 4, bounds)).entries(EntryIngredients.ofIngredient(ORE.getMaterialIngredient(display.ore, 1))).markInput().disableBackground());
        widgets.addAll(setupBaseMachineSlots(display, bounds));
        if(DIRECT_SMELT_INTO.getMapping(display.ore) != null){
            widgets.addAll(setupPrimaryFurnaceSlot(display, bounds));
        }
        widgets.addAll(setupSecondaryFurnaceSlots(display, bounds));
        if (display.bathingMode != OreProcessingDisplay.BathingMode.NONE){
            widgets.addAll(setupChemMachineSlots(display, bounds));
        }
        if (display.ore.has(AntimatterMaterialTypes.GEM)){
            widgets.addAll(setupSiftMachineSlots(bounds));
        }
        if (display.sepMode != OreProcessingDisplay.SepMode.NONE){
            widgets.addAll(setupSepMachineSlots(bounds));
        }
        if (display.ore.has(INGOT_HOT)){
            widgets.addAll(setupVacMachineSlots(bounds));
        }
        return widgets;
    }

    private List<Widget> setupPrimaryFurnaceSlot(OreProcessingDisplay display, Rectangle bounds){
        Item ingot, gem, dust;
        if (!SMELT_INTO.getMapping(display.ore).has(INGOT) && !SMELT_INTO.getMapping(display.ore).has(GEM) && !SMELT_INTO.getMapping(display.ore).has(DUST)) return List.of();
        if (display.ore.has(MaterialTags.NEEDS_BLAST_FURNACE)) return List.of();
        if(SMELT_INTO.getMapping(display.ore).has(INGOT) && !SMELT_INTO.getMapping(display.ore).has(GEM)){
            ingot = INGOT.get(SMELT_INTO.getMapping(display.ore));
            return List.of(Widgets.createSlot(xy(50, 4, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(ingot, SMELTING_MULTI.getInt(display.ore))))).markOutput().disableBackground());
        }else if(!SMELT_INTO.getMapping(display.ore).has(INGOT) && SMELT_INTO.getMapping(display.ore).has(GEM)){
            gem = GEM.get(SMELT_INTO.getMapping(display.ore));
            return List.of(Widgets.createSlot(xy(50, 4, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(gem, SMELTING_MULTI.getInt(display.ore))))).markOutput().disableBackground());
        }else if(!SMELT_INTO.getMapping(display.ore).has(INGOT) && !SMELT_INTO.getMapping(display.ore).has(GEM) && SMELT_INTO.getMapping(display.ore).has(DUST)){
            dust = DUST.get(SMELT_INTO.getMapping(display.ore));
            return List.of(Widgets.createSlot(xy(50, 4, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(dust, SMELTING_MULTI.getInt(display.ore))))).markOutput().disableBackground());
        }
        return List.of();
    }

    private List<Widget> setupSecondaryFurnaceSlots(OreProcessingDisplay display, Rectangle bounds){
        List<Widget> widgets = new ArrayList<>();
        if (display.ore.has(MaterialTags.NEEDS_BLAST_FURNACE)){
            widgets.add(Widgets.createSlot(xy(122, 111, bounds)).entries(ofMachine(Machines.BLAST_FURNACE)).markOutput().disableBackground());
            widgets.add(Widgets.createSlot(xy(72, 146, bounds)).entries(ofMachine(Machines.BLAST_FURNACE)).markOutput().disableBackground());
        }else{
            widgets.add(Widgets.createSlot(xy(122, 111, bounds)).entries(
                            EntryIngredient.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(Items.FURNACE)),
                                    EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(Items.BLAST_FURNACE))))
                    .markInput().disableBackground());
            widgets.add(Widgets.createSlot(xy(72, 146, bounds)).entries(
                            EntryIngredient.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(Items.FURNACE)),
                                    EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(Items.BLAST_FURNACE))))
                    .markInput().disableBackground());
        }
        return widgets;
    }

    private List<Widget> setupBaseMachineSlots(OreProcessingDisplay display, Rectangle bounds){
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createSlot(xy(4, 26, bounds)).entries(ofMachine(Machines.MACERATOR)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(4, 48, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, CRUSHED.get(display.ore, ORE_MULTI.getInt(display.ore))))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(4, 66, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST.get(display.byProduct1),1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(25, 72, bounds)).entries(ofMachine(Machines.MACERATOR)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(25, 93, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, DUST_IMPURE.get(display.ore, 1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(25, 111, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST.get(display.byProduct1),1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(29, 26, bounds)).entries(ofMachine(Machines.ORE_WASHER)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(50, 26, bounds)).entries(ofFluid(AntimatterMaterials.Water, 1000)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(72, 26, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, CRUSHED_PURIFIED.get(display.ore, 1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(90, 26, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST_TINY.get(display.byProduct1),3)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(120, 48, bounds)).entries(ofMachine(Machines.MACERATOR)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(148, 48, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, DUST_PURE.get(display.ore, 1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(166, 48, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST.get(display.byProduct2),1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(97, 72, bounds)).entries(ofMachine(Machines.THERMAL_CENTRIFUGE)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(97, 93, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, CRUSHED_CENTRIFUGED.get(display.ore, 1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(97, 111, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST_TINY.get(display.byProduct2),3)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(148, 72, bounds)).entries(ofMachine(Machines.CENTRIFUGE)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(148, 93, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, DUST.get(display.ore, 1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(148, 111, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST_TINY.get(display.byProduct2),1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(50, 81, bounds)).entries(ofMachine(Machines.CENTRIFUGE)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(50, 102, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, DUST_TINY.get(display.ore, 1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(50, 120, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST_TINY.get(display.byProduct1),1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(72, 81, bounds)).entries(ofMachine(Machines.MACERATOR)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(50, 102, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, DUST.get(display.ore, 1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(50, 120, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST.get(display.byProduct3),1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(4, 128, bounds)).entries(ofMachine(Machines.ORE_WASHER, Items.CAULDRON)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(25, 146, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, DUST.get(display.ore, 1)))).markOutput().disableBackground());
        return widgets;
    }

    private List<Widget> setupChemMachineSlots(OreProcessingDisplay display, Rectangle bounds){
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createSlot(xy(29, 48, bounds)).entries(ofMachine(Machines.CHEMICAL_BATH)).markInput().disableBackground());
        if(display.bathingMode == OreProcessingDisplay.BathingMode.MERCURY){
            widgets.add(Widgets.createSlot(xy(50, 48, bounds)).entries(ofFluid(Materials.Mercury,1000)).markInput().disableBackground());
            widgets.add(Widgets.createSlot(xy(90, 48, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST.get(CHEMBATH_MERCURY.getMapping(display.ore)),1)))).markOutput().disableBackground());
        }else{
            widgets.add(Widgets.createSlot(xy(50, 48, bounds)).entries(ofFluid(Materials.SodiumPersulfate,1000)).markInput().disableBackground());
            widgets.add(Widgets.createSlot(xy(90, 48, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST.get(CHEMBATH_PERSULFATE.getMapping(display.ore)),1)))).markOutput().disableBackground());
        }
        widgets.add(Widgets.createSlot(xy(72, 48, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(CRUSHED_PURIFIED.get(display.ore),1)))).markOutput().disableBackground());
        return widgets;
    }

    private List<Widget> setupSiftMachineSlots(Rectangle bounds){
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createSlot(xy(110, 25, bounds)).entries(ofMachine(Machines.SIFTER)).markInput().disableBackground());
        return widgets;
    }

    private List<Widget> setupSepMachineSlots(Rectangle bounds){
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createSlot(xy(166, 72, bounds)).entries(ofMachine(Machines.ELECTROMAGNETIC_SEPARATOR)).markInput().disableBackground());
        return widgets;
    }

    private List<Widget> setupVacMachineSlots(Rectangle bounds){
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createSlot(xy(116, 146, bounds)).entries(ofMachine(Machines.VACUUM_FREEZER)).markInput().disableBackground());
        return widgets;
    }

    private Point xy(int x, int y, Rectangle bounds){
        int offsetX = 0, offsetY = 0;
        return new Point(offsetX + x + bounds.x, offsetY + y + bounds.y);
    }

    private EntryIngredient ofMachine(Machine<?> machine, Item... extra){
        List<EntryStack<?>> stacks = new ArrayList<>();
        machine.getTiers().forEach(t -> {
            stacks.add(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(machine.getItem(t))));
        });
        for (Item item : extra) {
            stacks.add(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(item)));
        }
        return EntryIngredient.of(stacks);
    }

    private EntryIngredient ofFluid(Material fluid, int amount){
        List<EntryStack<?>> stacks = new ArrayList<>();
        FluidStack stack;
        if(fluid.has(LIQUID)){
           stack = fluid.getLiquid(amount);
        }else{
            stack = fluid.getGas(amount);
        }
        stacks.add(EntryStack.of(VanillaEntryTypes.FLUID, toREIFLuidStack(stack)));
        return EntryIngredient.of(stacks);
    }

    private static void drawTexture(PoseStack stack, ResourceLocation loc, int left, int top, int x, int y, int sizeX, int sizeY) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, loc);
        //AbstractGui.blit(stack, left, top, x, y, sizeX, sizeY);
        GuiComponent.blit(stack, left, top, 0, x, y, sizeX, sizeY, 186, 166);
    }

    @Override
    public Component getTitle() {
        return title;
    }

    @Override
    public Renderer getIcon() {
        return icon;
    }


    @Override
    public int getDisplayHeight() {
        return 166;
    }

    @Override
    public int getDisplayWidth(OreProcessingDisplay display) {
        return 186;
    }
}
