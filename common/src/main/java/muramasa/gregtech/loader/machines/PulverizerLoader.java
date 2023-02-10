package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.data.Materials;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static muramasa.gregtech.data.Materials.WoodPulp;
import static muramasa.gregtech.data.RecipeMaps.PULVERIZING;

public class PulverizerLoader {
    public static void init() {
        AntimatterMaterialTypes.CRUSHED.all().forEach(m -> {
            if (!m.has(AntimatterMaterialTypes.ORE)) return;
            if (!m.has(AntimatterMaterialTypes.CRUSHED)) return;
            int multiplier = 1;
            RecipeIngredient ore = RecipeIngredient.of(AntimatterMaterialTypes.ORE.getMaterialTag(m),1), crushed = AntimatterMaterialTypes.CRUSHED.getIngredient(m, 1);
            ItemStack crushedStack = AntimatterMaterialTypes.CRUSHED.get(m,1);
            ItemStack stoneDust = AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Stone, 1);
            ItemStack dustStack = AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Stone, 1);

            //TODO better way to do this
            Material aOreByProduct1 = m.getByProducts().size() >= 1 ? m.getByProducts().get(0) : MaterialTags.MACERATE_INTO.getMapping(m);
            Material aOreByProduct2 = m.getByProducts().size() >= 2 ? m.getByProducts().get(1) : aOreByProduct1;
            PULVERIZING.RB().ii(ore).io(Utils.ca((MaterialTags.ORE_MULTI.getInt(m) * multiplier) * 2, crushedStack), m.getByProducts().size() > 0 ? AntimatterMaterialTypes.DUST.get(m.getByProducts().get(0), 1) : dustStack, stoneDust).chances(1.0, 0.1 * multiplier * MaterialTags.BY_PRODUCT_MULTI.getInt(m), 0.5).add("ore_" + m.getId(),400, 2);
            PULVERIZING.RB().ii(crushed).io(AntimatterMaterialTypes.DUST_IMPURE.get(MaterialTags.MACERATE_INTO.getMapping(m), 1), AntimatterMaterialTypes.DUST.get(aOreByProduct1, 1)).chances(1.0, 0.1).add("crushed_" + m.getId(),400, 2);

            if (m.has(AntimatterMaterialTypes.CRUSHED_CENTRIFUGED)) {
                PULVERIZING.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.CRUSHED_CENTRIFUGED.get(m,1))).io(AntimatterMaterialTypes.DUST.get(MaterialTags.MACERATE_INTO.getMapping(m), 1), AntimatterMaterialTypes.DUST.get(aOreByProduct2, 1)).chances(1.0, 0.1).add("refined_" + m.getId(),400, 2);
            }
            if (m.has(AntimatterMaterialTypes.CRUSHED_PURIFIED) && m.has(AntimatterMaterialTypes.DUST_PURE)) {
                PULVERIZING.RB().ii(AntimatterMaterialTypes.CRUSHED_PURIFIED.getIngredient(m, 1)).io(AntimatterMaterialTypes.DUST_PURE.get(MaterialTags.MACERATE_INTO.getMapping(m), 1), AntimatterMaterialTypes.DUST.get(aOreByProduct1, 1)).chances(1.0, 0.1).add("purified_" + m.getId(),400, 2);
            }
            if (m.has(AntimatterMaterialTypes.RAW_ORE)){
                PULVERIZING.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.RAW_ORE.getMaterialTag(m), 1)).io(Utils.ca((MaterialTags.ORE_MULTI.getInt(m) * multiplier) * 2, crushedStack), AntimatterMaterialTypes.DUST.get(aOreByProduct1, 1)).chances(1.0, 0.1 * multiplier * MaterialTags.BY_PRODUCT_MULTI.getInt(m)).add("raw_" + m.getId(),400, 2);
            }
        });
        AntimatterMaterialTypes.GEM_BRITTLE.all().forEach(m -> {
            if (!m.has(AntimatterMaterialTypes.DUST)) return;
            PULVERIZING.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.GEM_BRITTLE.get(m),1)).io(AntimatterMaterialTypes.DUST_SMALL.get(m,2)).add("gem_brittle_" + m.getId(),40,8);
        });
        AntimatterMaterialTypes.GEM_POLISHED.all().forEach(m -> {
            if (!m.has(AntimatterMaterialTypes.DUST)) return;
            PULVERIZING.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.GEM_POLISHED.get(m),1)).io(AntimatterMaterialTypes.DUST.get(m,2)).add("gem_polished_" + m.getId(),60,16);
        });
        PULVERIZING.RB().ii(RecipeIngredient.of(Items.STONE,1)).io(new ItemStack(Items.GRAVEL,1)).add("gravel",100,2);
        PULVERIZING.RB().ii(RecipeIngredient.of(Items.COBBLESTONE,1)).io(new ItemStack(Items.SAND,1)).add("sand",100,2);
        PULVERIZING.RB().ii(RecipeIngredient.of(Items.SAND,1)).io(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Sand, 1)).add("sand_dust",50,4);
        PULVERIZING.RB().ii(RecipeIngredient.of(Items.BRICK,1)).io(AntimatterMaterialTypes.DUST.get(Materials.Brick, 1)).add("brick_dust",50,4);
        PULVERIZING.RB().ii(RecipeIngredient.of(Items.COAL,1)).io(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Coal, 1)).add("coal_dust",50,4);
        PULVERIZING.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 1)).io(AntimatterMaterialTypes.DUST.get(WoodPulp, 2)).add("wood_dust",40, 2);


        //INGOT -> DUST
        AntimatterMaterialTypes.INGOT.all().forEach(t -> {
            if (!t.has(AntimatterMaterialTypes.DUST)) return;
            PULVERIZING.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.INGOT.getMaterialTag(t),1)).io(AntimatterMaterialTypes.DUST.get(t,1)).add("dust_" + t.getId(),40,2);
        });
    }
}
