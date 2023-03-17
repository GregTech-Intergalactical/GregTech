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
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.antimatter.pipe.types.ItemPipe;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.gregtech.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.integration.rei.REIUtils.toREIFLuidStack;
import static muramasa.antimatter.material.MaterialTags.*;

public class MaterialTreeCategory implements DisplayCategory<MaterialTreeDisplay> {
    protected static Renderer icon = EntryStacks.of(DUST.get(AntimatterMaterials.Iron));
    private static final Component title = new TranslatableComponent(Ref.ID + ".rei.tooltip.material_tree");
    static CategoryIdentifier<? extends MaterialTreeDisplay> id = CategoryIdentifier.of(Ref.ID, "material_tree");

    @Override
    public CategoryIdentifier<? extends MaterialTreeDisplay> getCategoryIdentifier() {
        return id;
    }

    @Override
    public List<Widget> setupDisplay(MaterialTreeDisplay display, Rectangle bounds) {
        Material mat = display.mat;
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createDrawableWidget((helper, matrices, mouseX, mouseY, delta) -> {
            drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/background.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/base.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            if(mat.has(INGOT) && !mat.has(INGOT_HOT)){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/ingot.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if(mat.has(INGOT_HOT)){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/ingot_hot.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if(mat.has(NUGGET)){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/nugget.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if(mat.has(LIQUID)){
                if(mat.has(INGOT)){
                    drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/fluid_from_ingot.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
                }else{
                    drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/fluid_from_dust.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
                }
            }
            if(mat.has(BLOCK)){
                if(mat.has(INGOT)){
                    drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/block.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
                }

                else{
                    drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/block_no_ingot.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
                }
            }
            if(mat.has(PLATE)){
                if(mat.has(INGOT) && mat.has(BLOCK)){
                    drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/plate_from_ingot_and_block.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
                }else if(mat.has(INGOT)){
                    drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/plate_from_ingot.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
                }else if(mat.has(BLOCK)){
                    drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/plate_no_ingot_from_dust_and_block.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
                }else{
                    drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/plate_no_ingot_from_dust.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
                }
            }
            if(mat.has(PLATE_DENSE)){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/plate_dense.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if(mat.has(FOIL)){
              drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/foil.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if(mat.has(FRAME)){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/frame.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if(mat.has(FLUIDPIPE) || mat.has(ITEMPIPE)){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/pipe.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if(mat.has(GEAR)){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/gear.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if(mat.has(GEAR_SMALL)){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/gear_small.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if(mat.has(ROD)){
                if(mat.has(INGOT)){
                    drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/rod.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
                }
                else{
                    drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/rod_no_ingot.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
                }
            }
            if(mat.has(ROD_LONG)){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/rod_long.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if(mat.has(RING)){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/ring.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if(mat.has(BOLT)){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/bolt.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if(mat.has(SCREW)){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/screw.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if(mat.has(SPRING)){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/spring.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if(mat.has(WIRE)){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/wire.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if(mat.has(CABLE)){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/cable.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if(mat.has(WIRE_FINE)){
                drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/material_tree/wire_fine.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            setupInfo(display, bounds, matrices);
        }));
        widgets.addAll(setupSlots(display, bounds));
        return widgets;
    }

    private List<Widget> setupSlots(MaterialTreeDisplay display, Rectangle bounds){
        List<Widget> widgets = new ArrayList<>();
        Material mat = display.mat;
        widgets.add(Widgets.createSlot(xy(4, 92, bounds)).entries(EntryIngredients.ofIngredient(DUST.getMaterialIngredient(mat, 1))).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(30, 50, bounds)).entries(EntryIngredients.ofIngredient(DUST_TINY.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(4, 50, bounds)).entries(EntryIngredients.ofIngredient(DUST_SMALL.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        if(mat.has(INGOT) && !mat.has(INGOT_HOT)){
            widgets.add(Widgets.createSlot(xy(30, 110, bounds)).entries(EntryIngredients.ofIngredient(INGOT.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(INGOT_HOT)){
            widgets.add(Widgets.createSlot(xy(30, 74, bounds)).entries(EntryIngredients.ofIngredient(INGOT_HOT.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
            widgets.add(Widgets.createSlot(xy(30, 110, bounds)).entries(EntryIngredients.ofIngredient(INGOT.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(NUGGET)){
            widgets.add(Widgets.createSlot(xy(60, 110, bounds)).entries(EntryIngredients.ofIngredient(NUGGET.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(LIQUID)){
            widgets.add(Widgets.createSlot(xy(5, 128, bounds)).entries(ofFluid(mat,144)).markInput().disableBackground());
        }
        if(mat.has(BLOCK)){
            widgets.add(Widgets.createSlot(xy(30, 146, bounds)).entries(EntryIngredients.ofIngredient(BLOCK.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(PLATE)){
            widgets.add(Widgets.createSlot(xy(60, 146, bounds)).entries(EntryIngredients.ofIngredient(PLATE.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(PLATE_DENSE)){
            widgets.add(Widgets.createSlot(xy(90, 146, bounds)).entries(EntryIngredients.ofIngredient(PLATE_DENSE.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(FOIL)){
          widgets.add(Widgets.createSlot(xy(150, 146, bounds)).entries(EntryIngredients.ofIngredient(FOIL.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(FRAME)){
            widgets.add(Widgets.createSlot(xy(90, 74, bounds)).entries(EntryIngredients.ofIngredient(FRAME.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(FLUIDPIPE) || mat.has(ITEMPIPE)){
            if(mat.has(FLUIDPIPE) && !mat.has(ITEMPIPE)){
                Item fPipeItemT = AntimatterAPI.get(FluidPipe.class,"fluid_"+mat.getId()).getBlockItem(PipeSize.TINY);
                Item fPipeItemS = AntimatterAPI.get(FluidPipe.class,"fluid_"+mat.getId()).getBlockItem(PipeSize.SMALL);
                Item fPipeItemN = AntimatterAPI.get(FluidPipe.class,"fluid_"+mat.getId()).getBlockItem(PipeSize.NORMAL);
                Item fPipeItemL = AntimatterAPI.get(FluidPipe.class,"fluid_"+mat.getId()).getBlockItem(PipeSize.LARGE);
                Item fPipeItemH = AntimatterAPI.get(FluidPipe.class,"fluid_"+mat.getId()).getBlockItem(PipeSize.HUGE);
                widgets.add(Widgets.createSlot(xy(90, 110, bounds)).entries(ofItems(fPipeItemT,fPipeItemS,fPipeItemN,fPipeItemL,fPipeItemH
                        )).markOutput().disableBackground());
            }else if(mat.has(ITEMPIPE) && !mat.has(FLUIDPIPE)){
                Item iPipeItemN = AntimatterAPI.get(ItemPipe.class,"item_"+mat.getId()).getBlockItem(PipeSize.NORMAL);
                Item iPipeItemL = AntimatterAPI.get(ItemPipe.class,"item_"+mat.getId()).getBlockItem(PipeSize.LARGE);
                Item iPipeItemH = AntimatterAPI.get(ItemPipe.class,"item_"+mat.getId()).getBlockItem(PipeSize.HUGE);
                widgets.add(Widgets.createSlot(xy(90, 110, bounds)).entries(ofItems(iPipeItemN,iPipeItemL,iPipeItemH
                        )).markOutput().disableBackground());
            }else{
                Item fPipeItemT = AntimatterAPI.get(FluidPipe.class,"fluid_"+mat.getId()).getBlockItem(PipeSize.TINY);
                Item fPipeItemS = AntimatterAPI.get(FluidPipe.class,"fluid_"+mat.getId()).getBlockItem(PipeSize.SMALL);
                Item fPipeItemN = AntimatterAPI.get(FluidPipe.class,"fluid_"+mat.getId()).getBlockItem(PipeSize.NORMAL);
                Item fPipeItemL = AntimatterAPI.get(FluidPipe.class,"fluid_"+mat.getId()).getBlockItem(PipeSize.LARGE);
                Item fPipeItemH = AntimatterAPI.get(FluidPipe.class,"fluid_"+mat.getId()).getBlockItem(PipeSize.HUGE);
                Item iPipeItemN = AntimatterAPI.get(ItemPipe.class,"item_"+mat.getId()).getBlockItem(PipeSize.NORMAL);
                Item iPipeItemL = AntimatterAPI.get(ItemPipe.class,"item_"+mat.getId()).getBlockItem(PipeSize.LARGE);
                Item iPipeItemH = AntimatterAPI.get(ItemPipe.class,"item_"+mat.getId()).getBlockItem(PipeSize.HUGE);
                widgets.add(Widgets.createSlot(xy(90, 110, bounds)).entries(ofItems(fPipeItemT,fPipeItemS,fPipeItemN,fPipeItemL,fPipeItemH,
                        iPipeItemN,iPipeItemL,iPipeItemH)).markOutput().disableBackground());
            }
        }
        if(mat.has(GEAR)){
            widgets.add(Widgets.createSlot(xy(120, 110, bounds)).entries(EntryIngredients.ofIngredient(GEAR.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(GEAR_SMALL)){
            widgets.add(Widgets.createSlot(xy(150, 110, bounds)).entries(EntryIngredients.ofIngredient(GEAR_SMALL.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(ROD)){
            widgets.add(Widgets.createSlot(xy(60, 74, bounds)).entries(EntryIngredients.ofIngredient(ROD.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(ROD_LONG)){
            widgets.add(Widgets.createSlot(xy(180, 110, bounds)).entries(EntryIngredients.ofIngredient(ROD_LONG.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(RING)){
            widgets.add(Widgets.createSlot(xy(180, 146, bounds)).entries(EntryIngredients.ofIngredient(RING.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(BOLT)){
            widgets.add(Widgets.createSlot(xy(120, 74, bounds)).entries(EntryIngredients.ofIngredient(BOLT.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(SCREW)){
            widgets.add(Widgets.createSlot(xy(150, 74, bounds)).entries(EntryIngredients.ofIngredient(SCREW.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(SPRING)){
            widgets.add(Widgets.createSlot(xy(180, 74, bounds)).entries(EntryIngredients.ofIngredient(SPRING.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        if(mat.has(WIRE)){
            Item wireItem = AntimatterAPI.get(Wire.class,"wire_"+mat.getId()).getBlockItem(PipeSize.VTINY);
            widgets.add(Widgets.createSlot(xy(90, 50, bounds)).entries(EntryIngredients.of(wireItem)).markOutput().disableBackground());
        }
        if(mat.has(CABLE)){
            Item wireItem = AntimatterAPI.get(Cable.class,"cable_"+mat.getId()).getBlockItem(PipeSize.VTINY);
            widgets.add(Widgets.createSlot(xy(60, 50, bounds)).entries(EntryIngredients.of(wireItem)).markOutput().disableBackground());
        }
        if(mat.has(WIRE_FINE)){
            widgets.add(Widgets.createSlot(xy(120, 50, bounds)).entries(EntryIngredients.ofIngredient(WIRE_FINE.getMaterialIngredient(mat, 1))).markOutput().disableBackground());
        }
        return widgets;
    }

    private void setupInfo(MaterialTreeDisplay display, Rectangle bounds, PoseStack stack){
        Material mat = display.mat;
        renderString(stack, mat.getDisplayName().getString(), Minecraft.getInstance().font, 12, 10, 0xFFFFFF, bounds.x, bounds.y);
        renderString(stack, "Mass: "+mat.getMass(), Minecraft.getInstance().font, 12, 20, 0xFFFFFF, bounds.x, bounds.y);
        renderString(stack, "Formula: "+mat.getChemicalFormula(), Minecraft.getInstance().font, 12, 30, 0xFFFFFF, bounds.x, bounds.y);
        renderString(stack, "Density: "+mat.getDensity(), Minecraft.getInstance().font, 120, 10, 0xFFFFFF, bounds.x, bounds.y);
        renderString(stack, "Hardness: "+mat.getHardness(), Minecraft.getInstance().font, 120, 20, 0xFFFFFF, bounds.x, bounds.y);
    }

    private void renderString(PoseStack stack, String string, Font render, float x, float y, int color, int guiOffsetX, int guiOffsetY) {
        render.drawShadow(stack, string, (guiOffsetX + x), guiOffsetY + y, color);
    }

    private Point xy(int x, int y, Rectangle bounds){
        int offsetX = 0, offsetY = 0;
        return new Point(offsetX + x + bounds.x, offsetY + y + bounds.y);
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

    private EntryIngredient ofItems(Item item, Item... extras){
        List<EntryStack<?>> stacks = new ArrayList<>();
        stacks.add(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(item)));
        for (Item extra : extras) {
            stacks.add(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(extra)));
        }
        return EntryIngredient.of(stacks);
    }

    private static void drawTexture(PoseStack stack, ResourceLocation loc, int left, int top, int x, int y, int sizeX, int sizeY) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, loc);
        //AbstractGui.blit(stack, left, top, x, y, sizeX, sizeY);
        GuiComponent.blit(stack, left, top, 0, x, y, sizeX, sizeY, 200, 166);
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
        return 200;
    }
}
