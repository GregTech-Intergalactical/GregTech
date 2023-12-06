package muramasa.gregtech.loader.machines;

import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.LongFunction;
import java.util.function.ToLongFunction;

import static muramasa.antimatter.material.MaterialTags.RUBBERTOOLS;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;

import static muramasa.gregtech.data.RecipeMaps.EXTRUDER;

public class ExtruderLoader {
    public static void init() {
        ToLongFunction<Material> energyPerTick = m -> m.has(RUBBERTOOLS) ? 16 : 128;
        AntimatterMaterialTypes.RING.all().forEach(r -> {
            long duration = r.getElement() == null ? Math.max(r.getMass(), 1) : r.getElement().getHardness();
            if (r.has(AntimatterMaterialTypes.INGOT)) {
                EXTRUDER.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(r), 1), of(GTCoreItems.ShapeRing, 1).setNoConsume()).io(AntimatterMaterialTypes.RING.get(r, 4)).add("ring_" + r.getId(), duration, energyPerTick.applyAsLong(r));
            }
            if (r.has(AntimatterMaterialTypes.DUST) && r.has(RUBBERTOOLS)) {
                EXTRUDER.RB().ii(of(AntimatterMaterialTypes.DUST.getMaterialTag(r), 1), of(GTCoreItems.ShapeRing, 1).setNoConsume()).io(AntimatterMaterialTypes.RING.get(r, 4)).add("ring_" + r.getId(), duration, energyPerTick.applyAsLong(r));
            }
        });

        AntimatterMaterialTypes.GEAR.all().forEach(g -> {
            long duration = g.getElement() == null ? Math.max(g.getMass(), 1) : g.getElement().getHardness();
            if (g.has(AntimatterMaterialTypes.INGOT)) {
                EXTRUDER.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(g), 4), of(GTCoreItems.ShapeGear, 1).setNoConsume()).io(AntimatterMaterialTypes.GEAR.get(g, 1)).add("gear_" + g.getId(), duration * 4, energyPerTick.applyAsLong(g));
            }
            if (g.has(AntimatterMaterialTypes.DUST) && g.has(RUBBERTOOLS)) {
                EXTRUDER.RB().ii(of(AntimatterMaterialTypes.DUST.getMaterialTag(g), 4), of(GTCoreItems.ShapeGear, 1).setNoConsume()).io(AntimatterMaterialTypes.GEAR.get(g, 1)).add("gear_" + g.getId(), duration * 4, energyPerTick.applyAsLong(g));
            }
        });

        AntimatterMaterialTypes.BOLT.all().forEach(b -> {
            long duration = b.getElement() == null ? Math.max(b.getMass(), 1) : b.getElement().getHardness();
            if (b.has(AntimatterMaterialTypes.INGOT)) {
                EXTRUDER.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(b), 1), of(GTCoreItems.ShapeBolt, 1).setNoConsume()).io(AntimatterMaterialTypes.BOLT.get(b, 8)).add("bolt_" + b.getId(), duration, energyPerTick.applyAsLong(b));
            }
            if (b.has(AntimatterMaterialTypes.DUST) && b.has(RUBBERTOOLS)) {
                EXTRUDER.RB().ii(of(AntimatterMaterialTypes.DUST.getMaterialTag(b), 1), of(GTCoreItems.ShapeBolt, 1).setNoConsume()).io(AntimatterMaterialTypes.BOLT.get(b, 8)).add("bolt_" + b.getId(), duration, energyPerTick.applyAsLong(b));
            }
        });

        AntimatterMaterialTypes.GEAR_SMALL.all().forEach(g -> {
            long duration = g.getElement() == null ? Math.max(g.getMass(), 1) : g.getElement().getHardness();
            if (g.has(AntimatterMaterialTypes.INGOT)) {
                EXTRUDER.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(g), 1), of(GTCoreItems.ShapeGearSmall, 1).setNoConsume()).io(AntimatterMaterialTypes.GEAR_SMALL.get(g, 1)).add("gear_small_" + g.getId(), duration, energyPerTick.applyAsLong(g));
            }
            if (g.has(AntimatterMaterialTypes.DUST) && g.has(RUBBERTOOLS)) {
                EXTRUDER.RB().ii(of(AntimatterMaterialTypes.DUST.getMaterialTag(g), 1), of(GTCoreItems.ShapeGearSmall, 1).setNoConsume()).io(AntimatterMaterialTypes.GEAR_SMALL.get(g, 1)).add("gear_small_" + g.getId(), duration, energyPerTick.applyAsLong(g));
            }
        });

        AntimatterMaterialTypes.PLATE.all().forEach(p -> {
            long duration = p.getElement() == null ? Math.max(p.getMass(), 1) : p.getElement().getHardness();
            if (p.has(AntimatterMaterialTypes.INGOT)) {
                EXTRUDER.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(p), 1), of(GTCoreItems.ShapePlate, 1).setNoConsume()).io(AntimatterMaterialTypes.PLATE.get(p, 1)).add("plate_" + p.getId(), duration, energyPerTick.applyAsLong(p));
            }
            if (p.has(AntimatterMaterialTypes.DUST) && p.has(RUBBERTOOLS)) {
                EXTRUDER.RB().ii(of(AntimatterMaterialTypes.DUST.getMaterialTag(p), 1), of(GTCoreItems.ShapePlate, 1).setNoConsume()).io(AntimatterMaterialTypes.PLATE.get(p, 1)).add("plate_" + p.getId(), duration, energyPerTick.applyAsLong(p));
            }
        });

        AntimatterMaterialTypes.ROD.all().forEach(r -> {
            long duration = r.getElement() == null ? Math.max(r.getMass(), 1) : r.getElement().getHardness();
            if (r.has(AntimatterMaterialTypes.INGOT)) {
                EXTRUDER.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(r), 1), of(GTCoreItems.ShapeRod, 1).setNoConsume()).io(AntimatterMaterialTypes.ROD.get(r, 2)).add("rod_" + r.getId(), duration, energyPerTick.applyAsLong(r));
            }
            if (r.has(AntimatterMaterialTypes.DUST) && r.has(RUBBERTOOLS)) {
                EXTRUDER.RB().ii(of(AntimatterMaterialTypes.DUST.getMaterialTag(r), 1), of(GTCoreItems.ShapeRod, 1).setNoConsume()).io(AntimatterMaterialTypes.ROD.get(r, 2)).add("rod_" + r.getId(), duration, energyPerTick.applyAsLong(r));
            }
        });

        AntimatterAPI.all(Wire.class).forEach(t -> {
            Item wireItem = t.getBlockItem(PipeSize.VTINY);
            ItemStack stack = new ItemStack(wireItem,2);
            long duration = t.getMaterial().getElement() == null ? Math.max(t.getMaterial().getMass(), 1) : t.getMaterial().getElement().getHardness();
            if (t.getMaterial().has(AntimatterMaterialTypes.INGOT)) {
                EXTRUDER.RB().ii(of(AntimatterMaterialTypes.INGOT.getMaterialTag(t.getMaterial()),1),of(GTCoreItems.ShapeWire,1).setNoConsume()).io(stack).add("wire_" + t.getMaterial().getId(),duration,energyPerTick.applyAsLong(t.getMaterial()));
            }
            if (t.getMaterial().has(AntimatterMaterialTypes.DUST) && t.getMaterial().has(RUBBERTOOLS)) {
                EXTRUDER.RB().ii(of(AntimatterMaterialTypes.DUST.getMaterialTag(t.getMaterial()),1),of(GTCoreItems.ShapeWire,1).setNoConsume()).io(stack).add("wire_" + t.getMaterial().getId(),duration,energyPerTick.applyAsLong(t.getMaterial()));
            }
        });

        AntimatterAPI.all(FluidPipe.class).stream().filter(t -> t.getMaterial() != AntimatterMaterials.Wood).forEach(t -> {
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
            EXTRUDER.RB().ii(AntimatterMaterialTypes.INGOT.getMaterialIngredient(material, countIn), of(getPipeMold(size), 1).setNoConsume()).io(new ItemStack(pipe.getBlockItem(size), countOut)).add(size.getId() + "_" + pipe.getType() + "_" + material.getId(), duration * durationMultiplier, 30);
        }
    }

    private static Item getPipeMold(PipeSize size){
        return switch (size) {
            case HUGE -> GTCoreItems.ShapePipeHuge;
            case LARGE -> GTCoreItems.ShapePipeLarge;
            case NORMAL -> GTCoreItems.ShapePipeNormal;
            case SMALL -> GTCoreItems.ShapePipeSmall;
            case TINY, VTINY -> GTCoreItems.ShapePipeTiny;
            default -> Items.AIR;
        };
    }
}