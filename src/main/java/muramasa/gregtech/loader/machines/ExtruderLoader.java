package muramasa.gregtech.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.antimatter.pipe.types.HeatPipe;
import muramasa.antimatter.pipe.types.ItemPipe;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.Materials;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static muramasa.antimatter.material.MaterialTag.WIRE;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;

import static muramasa.antimatter.Data.*;
import static muramasa.gregtech.data.GregTechData.WIRE_LEAD;
import static muramasa.gregtech.data.RecipeMaps.EXTRUDING;
import static muramasa.gregtech.data.RecipeMaps.WIRE_MILLING;

public class ExtruderLoader {
    public static void init() {
        RING.all().forEach(r -> {
            if (!r.has(INGOT)) return;
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(r),1),of(GregTechData.ShapeRing,1).setNoConsume()).io(RING.get(r,4)).add(r.getHardness(),30);
        });

        GEAR.all().forEach(g -> {
            if (!g.has(INGOT)) return;
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(g),4),of(GregTechData.ShapeGear,1).setNoConsume()).io(GEAR.get(g,1)).add(g.getHardness()*4,30);
        });

        BOLT.all().forEach(b -> {
            if (!b.has(INGOT)) return;
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(b),1),of(GregTechData.ShapeBolt,1).setNoConsume()).io(BOLT.get(b,8)).add(b.getHardness(),30);
        });

        GEAR_SMALL.all().forEach(g -> {
            if (!g.has(INGOT)) return;
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(g),1),of(GregTechData.ShapeGearSmall,1).setNoConsume()).io(GEAR_SMALL.get(g,1)).add(g.getHardness(),30);
        });

        PLATE.all().forEach(p -> {
            if (!p.has(INGOT)) return;
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(p),1),of(GregTechData.ShapePlate,1).setNoConsume()).io(PLATE.get(p,1)).add(p.getHardness(),30);
        });

        ROD.all().forEach(r -> {
            if (!r.has(INGOT)) return;
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(r),1),of(GregTechData.ShapeRod,1).setNoConsume()).io(ROD.get(r,2)).add(r.getHardness(),30);
        });

        AntimatterAPI.all(Wire.class).forEach(t -> {
            Item wireItem = t.getBlockItem(PipeSize.VTINY);
            ItemStack stack = new ItemStack(wireItem,2);
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(t.getMaterial()),1),of(GregTechData.ShapeWire,1).setNoConsume()).io(stack).add(t.getMaterial().getHardness(),30);
        });

        AntimatterAPI.all(FluidPipe.class).forEach(t -> {
            Item pipeT = t.getBlockItem(PipeSize.TINY);
            Item pipeS = t.getBlockItem(PipeSize.SMALL);
            Item pipeM = t.getBlockItem(PipeSize.NORMAL);
            Item pipeL = t.getBlockItem(PipeSize.LARGE);
            Item pipeH = t.getBlockItem(PipeSize.HUGE);
            ItemStack stackT = new ItemStack(pipeT,4);
            ItemStack stackS = new ItemStack(pipeS,2);
            ItemStack stackM = new ItemStack(pipeM,1);
            ItemStack stackL = new ItemStack(pipeL,2);
            ItemStack stackH = new ItemStack(pipeH,1);
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(t.getMaterial()),3),of(GregTechData.ShapePipeTiny,1).setNoConsume()).io(stackT).add(t.getMaterial().getHardness()*3,30);
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(t.getMaterial()),3),of(GregTechData.ShapePipeSmall,1).setNoConsume()).io(stackS).add(t.getMaterial().getHardness()*3,30);
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(t.getMaterial()),3),of(GregTechData.ShapePipeNormal,1).setNoConsume()).io(stackM).add(t.getMaterial().getHardness()*3,30);
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(t.getMaterial()),6),of(GregTechData.ShapePipeLarge,1).setNoConsume()).io(stackL).add(t.getMaterial().getHardness()*6,30);
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(t.getMaterial()),6),of(GregTechData.ShapePipeHuge,1).setNoConsume()).io(stackH).add(t.getMaterial().getHardness()*6,30);
        });

        AntimatterAPI.all(ItemPipe.class).forEach(t -> {
            Item pipeT = t.getBlockItem(PipeSize.TINY);
            Item pipeS = t.getBlockItem(PipeSize.SMALL);
            Item pipeM = t.getBlockItem(PipeSize.NORMAL);
            Item pipeL = t.getBlockItem(PipeSize.LARGE);
            Item pipeH = t.getBlockItem(PipeSize.HUGE);
            ItemStack stackT = new ItemStack(pipeT,4);
            ItemStack stackS = new ItemStack(pipeS,2);
            ItemStack stackM = new ItemStack(pipeM,1);
            ItemStack stackL = new ItemStack(pipeL,2);
            ItemStack stackH = new ItemStack(pipeH,1);
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(t.getMaterial()),3),of(GregTechData.ShapePipeTiny,1).setNoConsume()).io(stackT).add(t.getMaterial().getHardness()*3,30);
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(t.getMaterial()),3),of(GregTechData.ShapePipeSmall,1).setNoConsume()).io(stackS).add(t.getMaterial().getHardness()*3,30);
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(t.getMaterial()),3),of(GregTechData.ShapePipeNormal,1).setNoConsume()).io(stackM).add(t.getMaterial().getHardness()*3,30);
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(t.getMaterial()),6),of(GregTechData.ShapePipeLarge,1).setNoConsume()).io(stackL).add(t.getMaterial().getHardness()*6,30);
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(t.getMaterial()),6),of(GregTechData.ShapePipeHuge,1).setNoConsume()).io(stackH).add(t.getMaterial().getHardness()*6,30);
        });

        AntimatterAPI.all(HeatPipe.class).forEach(t -> {
            Item pipeT = t.getBlockItem(PipeSize.TINY);
            Item pipeS = t.getBlockItem(PipeSize.SMALL);
            Item pipeM = t.getBlockItem(PipeSize.NORMAL);
            Item pipeL = t.getBlockItem(PipeSize.LARGE);
            Item pipeH = t.getBlockItem(PipeSize.HUGE);
            ItemStack stackT = new ItemStack(pipeT,4);
            ItemStack stackS = new ItemStack(pipeS,2);
            ItemStack stackM = new ItemStack(pipeM,1);
            ItemStack stackL = new ItemStack(pipeL,2);
            ItemStack stackH = new ItemStack(pipeH,1);
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(t.getMaterial()),3),of(GregTechData.ShapePipeTiny,1).setNoConsume()).io(stackT).add(t.getMaterial().getMass()*10,30);
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(t.getMaterial()),3),of(GregTechData.ShapePipeSmall,1).setNoConsume()).io(stackS).add(t.getMaterial().getMass()*20,30);
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(t.getMaterial()),3),of(GregTechData.ShapePipeNormal,1).setNoConsume()).io(stackM).add(t.getMaterial().getMass()*30,30);
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(t.getMaterial()),6),of(GregTechData.ShapePipeLarge,1).setNoConsume()).io(stackL).add(t.getMaterial().getMass()*40,30);
            EXTRUDING.RB().ii(of(INGOT.getMaterialTag(t.getMaterial()),6),of(GregTechData.ShapePipeHuge,1).setNoConsume()).io(stackH).add(t.getMaterial().getMass()*50,30);
        });
    }
}
