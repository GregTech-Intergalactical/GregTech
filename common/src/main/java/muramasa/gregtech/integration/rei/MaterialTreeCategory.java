package muramasa.gregtech.integration.rei;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.gregtech.Ref;
import muramasa.gregtech.data.Machines;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.SPRING;
import static muramasa.antimatter.integration.rei.REIUtils.toREIFLuidStack;
import static muramasa.antimatter.material.MaterialTags.*;

public class MaterialTreeCategory implements DisplayCategory<MaterialTreeDisplay> {
    protected static Renderer icon = EntryStacks.of(Items.IRON_INGOT);
    private static final Component title = new TranslatableComponent(Ref.ID + ".rei.tooltip.material_tree");
    static CategoryIdentifier<? extends MaterialTreeDisplay> id = CategoryIdentifier.of(Ref.ID, "material_tree");

    @Override
    public CategoryIdentifier<? extends MaterialTreeDisplay> getCategoryIdentifier() {
        return id;
    }

    @Override
    public List<Widget> setupDisplay(MaterialTreeDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createDrawableWidget((helper, matrices, mouseX, mouseY, delta) -> {
            drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/ore_byproducts/smelt.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
        }));
        widgets.addAll(setupSlots(display, bounds));
        return widgets;
    }

    private List<Widget> setupSlots(MaterialTreeDisplay display, Rectangle bounds){
        List<Widget> widgets = new ArrayList<>();
        Material mat = display.mat;
        widgets.add(Widgets.createSlot(xy(4, 37, bounds)).entries(EntryIngredients.ofIngredient(DUST.getMaterialIngredient(mat, 1))).markInput().disableBackground());
        if(mat.has(DUST_SMALL)){
            widgets.add(Widgets.createSlot(xy(4, 4, bounds)).entries(EntryIngredients.ofIngredient(DUST_SMALL.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(DUST_TINY)){
            widgets.add(Widgets.createSlot(xy(4, 70, bounds)).entries(EntryIngredients.ofIngredient(DUST_TINY.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(INGOT)){
            widgets.add(Widgets.createSlot(xy(45, 37, bounds)).entries(EntryIngredients.ofIngredient(INGOT.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(LIQUID)){
            widgets.add(Widgets.createSlot(xy(139, 37, bounds)).entries(ofFluid(mat,144)).markInput().disableBackground());
        }
        if(mat.has(BLOCK)){
            widgets.add(Widgets.createSlot(xy(4, 4, bounds)).entries(EntryIngredients.ofIngredient(BLOCK.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(PLATE)){
            widgets.add(Widgets.createSlot(xy(86, 76, bounds)).entries(EntryIngredients.ofIngredient(PLATE.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(PLATE_DENSE)){
            widgets.add(Widgets.createSlot(xy(45, 115, bounds)).entries(EntryIngredients.ofIngredient(PLATE_DENSE.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(GEAR)){
            widgets.add(Widgets.createSlot(xy(96, 115, bounds)).entries(EntryIngredients.ofIngredient(GEAR.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(GEAR_SMALL)){
            widgets.add(Widgets.createSlot(xy(96, 133, bounds)).entries(EntryIngredients.ofIngredient(GEAR_SMALL.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(ROD)){
            widgets.add(Widgets.createSlot(xy(106, 76, bounds)).entries(EntryIngredients.ofIngredient(ROD.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        //if(mat.has(ROD_LONG)){
            //widgets.add(Widgets.createSlot(xy(4, 4, bounds)).entries(EntryIngredients.ofIngredient(ROD_LONG.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        //}
        //if(mat.has(RING)){
            //this.output.add(EntryIngredient.of(EntryStack.of(VanillaEntryTypes.ITEM,new ItemStack(RING.get(mat),1))));
        //}
        if(mat.has(BOLT)){
            widgets.add(Widgets.createSlot(xy(130, 63, bounds)).entries(EntryIngredients.ofIngredient(BOLT.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(SCREW)){
            widgets.add(Widgets.createSlot(xy(156, 83, bounds)).entries(EntryIngredients.ofIngredient(SCREW.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(WIRE)){
            Item wireItem = AntimatterAPI.get(Wire.class,"wire_"+mat.getId()).getBlockItem(PipeSize.VTINY);
            widgets.add(Widgets.createSlot(xy(86, 4, bounds)).entries(EntryIngredients.of(wireItem)).markOutput().disableBackground());
        }
        if(mat.has(MaterialTags.CABLE)){
            Item wireItem = AntimatterAPI.get(Cable.class,"cable_"+mat.getId()).getBlockItem(PipeSize.VTINY);
            widgets.add(Widgets.createSlot(xy(127, 4, bounds)).entries(EntryIngredients.of(wireItem)).markOutput().disableBackground());
        }
        if(mat.has(WIRE_FINE)){
            widgets.add(Widgets.createSlot(xy(45, 4, bounds)).entries(EntryIngredients.ofIngredient(WIRE_FINE.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        return widgets;
    }

    private List<Widget> setupMachines(MaterialTreeDisplay display, Rectangle bounds){
        List<Widget> widgets = new ArrayList<>();
        Material mat = display.mat;
        if(mat.has(INGOT)){
            widgets.add(Widgets.createSlot(xy(23, 28, bounds)).entries(ofMachine(Machines.FURNACE)).markInput().disableBackground());
            widgets.add(Widgets.createSlot(xy(26, 46, bounds)).entries(ofMachine(Machines.MACERATOR)).markInput().disableBackground());
        }
        if(mat.has(BLOCK)){
            widgets.add(Widgets.createSlot(xy(45, 55, bounds)).entries(ofMachine(Machines.COMPRESSOR)).markInput().disableBackground());
        }
        if(mat.has(LIQUID)){
            widgets.add(Widgets.createSlot(xy(113, 37, bounds)).entries(ofMachine(Machines.FLUID_EXTRACTOR)).markInput().disableBackground());
        }
        if(mat.has(PLATE)){
            widgets.add(Widgets.createSlot(xy(64, 75, bounds)).entries(ofMachine(Machines.CUTTER)).markInput().disableBackground());
            widgets.add(Widgets.createSlot(xy(86, 55, bounds)).entries(ofMachine(Machines.BENDER)).markInput().disableBackground());
        }
        if(mat.has(PLATE_DENSE)){
            widgets.add(Widgets.createSlot(xy(64, 96, bounds)).entries(ofMachine(Machines.COMPRESSOR)).markInput().disableBackground());
        }
        if(mat.has(ROD)){
            widgets.add(Widgets.createSlot(xy(106, 50, bounds)).entries(ofMachine(Machines.LATHE)).markInput().disableBackground());
        }
        //if(mat.has(ROD_LONG)){
        //widgets.add(Widgets.createSlot(xy(4, 4, bounds)).entries(EntryIngredients.ofIngredient(ROD_LONG.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        //}
        //if(mat.has(RING)){
        //this.output.add(EntryIngredient.of(EntryStack.of(VanillaEntryTypes.ITEM,new ItemStack(RING.get(mat),1))));
        //}
        if(mat.has(BOLT)){
            widgets.add(Widgets.createSlot(xy(130, 85, bounds)).entries(ofMachine(Machines.CUTTER)).markInput().disableBackground());
        }
        if(mat.has(SCREW)){
            widgets.add(Widgets.createSlot(xy(156, 83, bounds)).entries(EntryIngredients.ofIngredient(SCREW.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(WIRE)){
            widgets.add(Widgets.createSlot(xy(86, 27, bounds)).entries(ofMachine(Machines.WIRE_MILL)).markInput().disableBackground());
        }
        if(mat.has(MaterialTags.CABLE)){
            widgets.add(Widgets.createSlot(xy(105, 3, bounds)).entries(ofMachine(Machines.ASSEMBLER)).markInput().disableBackground());
        }
        if(mat.has(WIRE_FINE)){
            widgets.add(Widgets.createSlot(xy(67, 3, bounds)).entries(ofMachine(Machines.WIRE_MILL)).markInput().disableBackground());
        }
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
    public int getDisplayWidth(MaterialTreeDisplay display) {
        return 176;
    }
}
