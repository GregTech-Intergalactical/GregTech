package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.gregtech.data.GregTechData.CompressedFireClay;
import static muramasa.gregtech.data.Materials.Fireclay;
import static muramasa.gregtech.data.Materials.WoodPulp;
import static muramasa.gregtech.data.RecipeMaps.COMPRESSING;

public class CompressorLoader {
    public static void init() {
        AntimatterMaterialTypes.INGOT.all().forEach(ingot -> {
            if (ingot.has(AntimatterMaterialTypes.BLOCK)) {
                int count = ingot.has(MaterialTags.QUARTZ_LIKE_BLOCKS) ? 4 : 9;
                COMPRESSING.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.INGOT.get(ingot), count)).io(AntimatterMaterialTypes.BLOCK.get().get(ingot).asStack(1))
                        .add("block_" + ingot.getId(),Math.max(80, ingot.getMass() * 2), 16);
            }
        });
        AntimatterMaterialTypes.GEM.all().forEach(ingot -> {
            if (ingot.has(AntimatterMaterialTypes.BLOCK)) {
                int count = ingot.has(MaterialTags.QUARTZ_LIKE_BLOCKS) ? 4 : 9;
                COMPRESSING.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.GEM.get(ingot), count)).io(AntimatterMaterialTypes.BLOCK.get().get(ingot).asStack(1))
                        .add("gem_" + ingot.getId(),Math.max(80, ingot.getMass() * 2), 16);
            }
        });
        COMPRESSING.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.DUST.get(WoodPulp), 1)).io(AntimatterMaterialTypes.PLATE.get(AntimatterMaterials.Wood, 1)).add("wood_plate",60, 4);
        COMPRESSING.RB().ii(DUST.getMaterialIngredient(Fireclay, 1)).io(new ItemStack(CompressedFireClay)).add("compressed_fireclay",200, 2);
    }
}
