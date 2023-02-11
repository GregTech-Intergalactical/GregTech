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
import muramasa.antimatter.gui.SlotData;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.integration.rei.category.RecipeMapDisplay;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.FluidIngredient;
import muramasa.gregtech.Ref;
import muramasa.gregtech.data.GregTechMaterialTags;
import muramasa.gregtech.data.Machines;
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

import static muramasa.antimatter.data.AntimatterMaterialTypes.INGOT;
import static muramasa.antimatter.data.AntimatterMaterialTypes.ORE;
import static muramasa.antimatter.material.MaterialTags.SMELTING_MULTI;
import static muramasa.antimatter.material.MaterialTags.SMELT_INTO;

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
            if (!SMELT_INTO.getMapping(display.ore).has(INGOT)) return;
            if (display.ore.has(MaterialTags.NEEDS_BLAST_FURNACE)) return;
            drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/ore_byproducts/smelt.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
        }));
        widgets.addAll(setupSlots(display, bounds));
        return widgets;
    }

    private List<Widget> setupSlots(OreProcessingDisplay display, Rectangle bounds){
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createSlot(xy(4, 4, bounds)).entries(EntryIngredients.ofIngredient(ORE.getMaterialIngredient(display.ore, 1))).markInput().disableBackground());
        widgets.addAll(setupBaseMachineSlots(display, bounds));
        widgets.addAll(setupFurnaceSlot(display, bounds));
        return widgets;
    }

    private List<Widget> setupBaseMachineSlots(OreProcessingDisplay display, Rectangle bounds){
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createSlot(xy(4, 26, bounds)).entries(ofMachine(Machines.MACERATOR)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(25, 26, bounds)).entries(ofMachine(Machines.ORE_WASHER)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(25, 4, bounds)).entries(
                EntryIngredient.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(Items.FURNACE)),
                        EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(Items.BLAST_FURNACE))))
                .markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(25, 72, bounds)).entries(ofMachine(Machines.MACERATOR)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(4, 124, bounds)).entries(ofMachine(Machines.ORE_WASHER, Items.CAULDRON)).markInput().disableBackground());
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

    private List<Widget> setupFurnaceSlot(OreProcessingDisplay display, Rectangle bounds){
        if (!SMELT_INTO.getMapping(display.ore).has(INGOT)) return List.of();
        if (display.ore.has(MaterialTags.NEEDS_BLAST_FURNACE)) return List.of();
        Item ingot = INGOT.get(SMELT_INTO.getMapping(display.ore));
        return List.of(Widgets.createSlot(xy(47, 4, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(ingot, SMELTING_MULTI.getInt(display.ore))))).markOutput());
    }

    private static void drawTexture(PoseStack stack, ResourceLocation loc, int left, int top, int x, int y, int sizeX, int sizeY) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, loc);
        //AbstractGui.blit(stack, left, top, x, y, sizeX, sizeY);
        GuiComponent.blit(stack, left, top, 0, x, y, sizeX, sizeY, 176, 166);
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
        return 176;
    }
}
