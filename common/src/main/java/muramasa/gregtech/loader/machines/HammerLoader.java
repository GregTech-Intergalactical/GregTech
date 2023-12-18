package muramasa.gregtech.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.data.ForgeCTags;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.ore.CobbleStoneType;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.TagUtils;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.data.GregTechMaterialTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.ToLongFunction;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.material.MaterialTags.MACERATE_INTO;
import static muramasa.antimatter.material.MaterialTags.ORE_MULTI;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.FORGE_HAMMER;

public class HammerLoader {
    public static void init() {
        AntimatterMaterialTypes.CRUSHED.all().forEach(m -> {
            if (!m.has(AntimatterMaterialTypes.ORE) && m != AntimatterMaterials.Gold && m != AntimatterMaterials.Iron && m != AntimatterMaterials.Diamond && m != AntimatterMaterials.Emerald && m != AntimatterMaterials.Lapis && m != AntimatterMaterials.Redstone) return;
            int multiplier = 1;
            RecipeIngredient ore = RecipeIngredient.of(TagUtils.getForgelikeItemTag("ores/" + m.getId()),1), crushed = AntimatterMaterialTypes.CRUSHED.getIngredient(m, 1);
            ItemStack crushedStack = AntimatterMaterialTypes.CRUSHED.get(m,1);

            FORGE_HAMMER.RB().ii(ore).io(Utils.ca(ORE_MULTI.getInt(m) * multiplier, crushedStack)).add(m.getId() + "_ore",16, 10);
            FORGE_HAMMER.RB().ii(crushed).io(DUST_IMPURE.get(MACERATE_INTO.getMapping(m), 1)).add(m.getId() + "_crushed_ore",16, 10);
            FORGE_HAMMER.RB().ii(RecipeIngredient.of(CRUSHED_PURIFIED.get(m,1))).io(DUST_PURE.get(MACERATE_INTO.getMapping(m), 1)).add(m.getId() + "_purified_ore",16, 10);
            if (m.has(CRUSHED_REFINED)) {
                FORGE_HAMMER.RB().ii(RecipeIngredient.of(CRUSHED_REFINED.get(m,1))).io(DUST.get(MACERATE_INTO.getMapping(m), 1)).add(m.getId() + "_centrifuged_ore",16, 10);
            }
            if (m.has(RAW_ORE)){
                FORGE_HAMMER.RB().ii(RecipeIngredient.of(RAW_ORE.getMaterialTag(m), 1)).io(Utils.ca((ORE_MULTI.getInt(m) * multiplier), crushedStack)).add(m.getId() + "_raw_ore",16, 10);
            }
        });
        ToLongFunction<Material> baseDuration = m -> {
            if (m.has(GregTechMaterialTags.RECIPE_MASS)) return GregTechMaterialTags.RECIPE_MASS.get(m);
            return m.getMass();
        };
        PLATE.all().forEach(plate -> {
            if (!plate.has(INGOT) || plate.has(MaterialTags.NOSMASH)) return;
            FORGE_HAMMER.RB().ii(INGOT.getMaterialIngredient(plate, 3)).io(PLATE.get(plate, 2)).add("plate_" + plate.getId(),baseDuration.applyAsLong(plate) * 2, 16);
        });
        ROD_LONG.all().stream().filter(m -> !m.has(MaterialTags.NOSMASH)).forEach(rod -> {
            FORGE_HAMMER.RB().ii(ROD.getMaterialIngredient(rod, 2)).io(ROD_LONG.get(rod, 1)).add("rod_long_" + rod.getId(), baseDuration.applyAsLong(rod) * 2, 16);
        });
        GEM_EXQUISITE.all().forEach(m -> {
            FORGE_HAMMER.RB().ii(GEM_EXQUISITE.getMaterialIngredient(m, 1)).io(GEM_FLAWLESS.get(m, 2)).add(m.getId() + "_exquisite", 64, 16);
            FORGE_HAMMER.RB().ii(GEM_FLAWLESS.getMaterialIngredient(m, 1)).io(GEM.get(m, 2)).add(m.getId() + "_flawless", 64, 16);
            FORGE_HAMMER.RB().ii(GEM.getMaterialIngredient(m, 1)).io(GEM_FLAWED.get(m, 2)).add(m.getId() + "_flawed", 64, 16);
            FORGE_HAMMER.RB().ii(GEM_FLAWED.getMaterialIngredient(m, 1)).io(GEM_CHIPPED.get(m, 2)).add(m.getId() + "_chipped", 64, 16);
        });
        AntimatterAPI.all(StoneType.class, GTIRef.ID, s -> {
            if (!(s instanceof CobbleStoneType)) return;
            FORGE_HAMMER.RB().ii(RecipeIngredient.of(((CobbleStoneType)s).getBlock(""), 1)).io(new ItemStack(((CobbleStoneType)s).getBlock("cobble"))).add(s.getId() + "_to_cobble",10, 16);
            FORGE_HAMMER.RB().ii(RecipeIngredient.of(((CobbleStoneType)s).getBlock("cobble").asItem(), 1)).io(DUST.get(s.getMaterial(), 1)).add("cobbled_" + s.getMaterial().getId() + "_dust",10, 16);
        });
        FORGE_HAMMER.RB().ii(RecipeIngredient.of(Items.COBBLESTONE, 1)).io(new ItemStack(Items.GRAVEL)).add("gravel",10, 16);
        FORGE_HAMMER.RB().ii(RecipeIngredient.of(Items.STONE, 1)).io(new ItemStack(Items.COBBLESTONE)).add("cobblestone",10, 16);
        FORGE_HAMMER.RB().ii(RecipeIngredient.of(ForgeCTags.GRAVEL, 1)).io(new ItemStack(Items.SAND)).add("sand",10, 16);
        FORGE_HAMMER.RB().ii(RecipeIngredient.of(ForgeCTags.GLASS, 1)).io(DUST.get(Glass)).add("glass_dust",10, 16);
        //Wrought Iron and Annealed Copper 2 to 1 (pre Arc Furnace)
        FORGE_HAMMER.RB().ii(RecipeIngredient.of(Items.BRICK, 1)).io(DUST_SMALL.get(Brick, 2)).add("brick_dust_small",10, 16);
        FORGE_HAMMER.RB().ii(RecipeIngredient.of(Items.BRICKS, 1)).io(DUST.get(Brick, 2)).add("brick_dust",40, 16);
    }
}
