package muramasa.gregtech.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.antimatter.pipe.types.HeatPipe;
import muramasa.antimatter.pipe.types.ItemPipe;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;

import static muramasa.gregtech.data.RecipeMaps.EXTRUDING;

public class ExtruderLoader {
    public static void init() {
        AntimatterMaterialTypes.RING.all().forEach(r -> {
            if (!r.has(AntimatterMaterialTypes.INGOT)) return;
            long duration = r.getElement() == null ? Math.max(r.getMass(), 1) : r.getElement().getHardness();
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(r),1),of(GregTechData.ShapeRing,1).setNoConsume()).io(AntimatterMaterialTypes.RING.get(r,4)).add("ring_" + r.getId(),duration,30);
        });

        AntimatterMaterialTypes.GEAR.all().forEach(g -> {
            if (!g.has(AntimatterMaterialTypes.INGOT)) return;
            long duration = g.getElement() == null ? Math.max(g.getMass(), 1) : g.getElement().getHardness();
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(g),4),of(GregTechData.ShapeGear,1).setNoConsume()).io(AntimatterMaterialTypes.GEAR.get(g,1)).add("gear_" + g.getId(),duration*4,30);
        });

        AntimatterMaterialTypes.BOLT.all().forEach(b -> {
            if (!b.has(AntimatterMaterialTypes.INGOT)) return;
            long duration = b.getElement() == null ? Math.max(b.getMass(), 1) : b.getElement().getHardness();
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(b),1),of(GregTechData.ShapeBolt,1).setNoConsume()).io(AntimatterMaterialTypes.BOLT.get(b,8)).add("bolt_" + b.getId(),duration,30);
        });

        AntimatterMaterialTypes.GEAR_SMALL.all().forEach(g -> {
            if (!g.has(AntimatterMaterialTypes.INGOT)) return;
            long duration = g.getElement() == null ? Math.max(g.getMass(), 1) : g.getElement().getHardness();
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(g),1),of(GregTechData.ShapeGearSmall,1).setNoConsume()).io(AntimatterMaterialTypes.GEAR_SMALL.get(g,1)).add("gear_small_" + g.getId(),duration,30);
        });

        AntimatterMaterialTypes.PLATE.all().forEach(p -> {
            if (!p.has(AntimatterMaterialTypes.INGOT)) return;
            long duration = p.getElement() == null ? Math.max(p.getMass(), 1) : p.getElement().getHardness();
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(p),1),of(GregTechData.ShapePlate,1).setNoConsume()).io(AntimatterMaterialTypes.PLATE.get(p,1)).add("plate_" + p.getId(),duration,30);
        });

        AntimatterMaterialTypes.ROD.all().forEach(r -> {
            if (!r.has(AntimatterMaterialTypes.INGOT)) return;
            long duration = r.getElement() == null ? Math.max(r.getMass(), 1) : r.getElement().getHardness();
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(r),1),of(GregTechData.ShapeRod,1).setNoConsume()).io(AntimatterMaterialTypes.ROD.get(r,2)).add("rod_" + r.getId(),duration,30);
        });

        AntimatterAPI.all(Wire.class).forEach(t -> {
            Item wireItem = t.getBlockItem(PipeSize.VTINY);
            ItemStack stack = new ItemStack(wireItem,2);
            long duration = t.getMaterial().getElement() == null ? Math.max(t.getMaterial().getMass(), 1) : t.getMaterial().getElement().getHardness();
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(t.getMaterial()),1),of(GregTechData.ShapeWire,1).setNoConsume()).io(stack).add("wire_" + t.getMaterial().getId(),duration,30);
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
            long duration = t.getMaterial().getElement() == null ? Math.max(t.getMaterial().getMass(), 1) : t.getMaterial().getElement().getHardness();
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(t.getMaterial()),3),of(GregTechData.ShapePipeTiny,1).setNoConsume()).io(stackT).add("tiny_fluid_pipe_" + t.getMaterial().getId(),duration*3,30);
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(t.getMaterial()),3),of(GregTechData.ShapePipeSmall,1).setNoConsume()).io(stackS).add("small_fluid_pipe_" + t.getMaterial().getId(),duration*3,30);
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(t.getMaterial()),3),of(GregTechData.ShapePipeNormal,1).setNoConsume()).io(stackM).add("normal_fluid_pipe_" + t.getMaterial().getId(),duration*3,30);
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(t.getMaterial()),6),of(GregTechData.ShapePipeLarge,1).setNoConsume()).io(stackL).add("large_fluid_pipe_" + t.getMaterial().getId(),duration*6,30);
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(t.getMaterial()),6),of(GregTechData.ShapePipeHuge,1).setNoConsume()).io(stackH).add("huge_fluid_pipe_" + t.getMaterial().getId(),duration*6,30);
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
            long duration = t.getMaterial().getElement() == null ? Math.max(t.getMaterial().getMass(), 1) : t.getMaterial().getElement().getHardness();
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(t.getMaterial()),3),of(GregTechData.ShapePipeTiny,1).setNoConsume()).io(stackT).add("tiny_item_pipe_" + t.getMaterial().getId(),duration*3,30);
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(t.getMaterial()),3),of(GregTechData.ShapePipeSmall,1).setNoConsume()).io(stackS).add("small_item_pipe_" + t.getMaterial().getId(),duration*3,30);
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(t.getMaterial()),3),of(GregTechData.ShapePipeNormal,1).setNoConsume()).io(stackM).add("normal_item_pipe_" + t.getMaterial().getId(),duration*3,30);
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(t.getMaterial()),6),of(GregTechData.ShapePipeLarge,1).setNoConsume()).io(stackL).add("large_item_pipe_" + t.getMaterial().getId(),duration*6,30);
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(t.getMaterial()),6),of(GregTechData.ShapePipeHuge,1).setNoConsume()).io(stackH).add("huge_item_pipe_" + t.getMaterial().getId(),duration*6,30);
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
            long duration = t.getMaterial().getElement() == null ? Math.max(t.getMaterial().getMass(), 1) : t.getMaterial().getElement().getHardness();
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(t.getMaterial()),3),of(GregTechData.ShapePipeTiny,1).setNoConsume()).io(stackT).add("tiny_heat_pipe_" + t.getMaterial().getId(),duration*10,30);
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(t.getMaterial()),3),of(GregTechData.ShapePipeSmall,1).setNoConsume()).io(stackS).add("small_heat_pipe_" + t.getMaterial().getId(),duration*20,30);
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(t.getMaterial()),3),of(GregTechData.ShapePipeNormal,1).setNoConsume()).io(stackM).add("normal_heat_pipe_" + t.getMaterial().getId(),duration*30,30);
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(t.getMaterial()),6),of(GregTechData.ShapePipeLarge,1).setNoConsume()).io(stackL).add("large_heat_pipe_" + t.getMaterial().getId(),duration*40,30);
            EXTRUDING.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(t.getMaterial()),6),of(GregTechData.ShapePipeHuge,1).setNoConsume()).io(stackH).add("huge_heat_pipe_" + t.getMaterial().getId(),duration*50,30);
        });
    }
}