package muramasa.gregtech.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.*;
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
            addPipeRecipe(t.getMaterial(), 1, 2, PipeSize.TINY, t, 2);
            addPipeRecipe(t.getMaterial(), 1, 1, PipeSize.SMALL, t, 1);
            addPipeRecipe(t.getMaterial(), 3, 1, PipeSize.NORMAL, t, 3);
            addPipeRecipe(t.getMaterial(), 6, 1, PipeSize.LARGE, t, 6);
            addPipeRecipe(t.getMaterial(), 12, 1, PipeSize.HUGE, t, 12);
        });

        AntimatterAPI.all(ItemPipe.class).forEach(t -> {
            addPipeRecipe(t.getMaterial(), 1, 2, PipeSize.TINY, t, 2);
            addPipeRecipe(t.getMaterial(), 1, 1, PipeSize.SMALL, t, 1);
            addPipeRecipe(t.getMaterial(), 3, 1, PipeSize.NORMAL, t, 3);
            addPipeRecipe(t.getMaterial(), 6, 1, PipeSize.LARGE, t, 6);
            addPipeRecipe(t.getMaterial(), 12, 1, PipeSize.HUGE, t, 12);
        });

        AntimatterAPI.all(HeatPipe.class).forEach(t -> {
            addPipeRecipe(t.getMaterial(), 1, 2, PipeSize.TINY, t, 10);
            addPipeRecipe(t.getMaterial(), 1, 1, PipeSize.SMALL, t, 20);
            addPipeRecipe(t.getMaterial(), 3, 1, PipeSize.NORMAL, t, 30);
            addPipeRecipe(t.getMaterial(), 6, 1, PipeSize.LARGE, t, 40);
            addPipeRecipe(t.getMaterial(), 12, 1, PipeSize.HUGE, t, 50);
        });
    }

    private static void addPipeRecipe(Material material, int countIn, int countOut, PipeSize size, PipeType<?> pipe, int durationMultiplier){
        if (pipe.getSizes().contains(size)) {
            long duration = material.getElement() == null ? Math.max(material.getMass(), 1) : material.getElement().getHardness();
            EXTRUDING.RB().ii(AntimatterMaterialTypes.INGOT.getMaterialIngredient(material, countIn), of(getPipeMold(size), 1).setNoConsume()).io(new ItemStack(pipe.getBlockItem(size), countOut)).add(size.getId() + "_" + pipe.getType() + "_pipe_" + material.getId(), duration * durationMultiplier, 30);
        }
    }

    private static Item getPipeMold(PipeSize size){
        return switch (size) {
            case HUGE -> GregTechData.ShapePipeHuge;
            case LARGE -> GregTechData.ShapePipeLarge;
            case NORMAL -> GregTechData.ShapePipeNormal;
            case SMALL -> GregTechData.ShapePipeSmall;
            case TINY, VTINY -> GregTechData.ShapePipeTiny;
        };
    }
}