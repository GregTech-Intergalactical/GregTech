package muramasa.gregtech.loader.machines.generator;

import com.google.common.collect.ImmutableList;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.data.RecipeMaps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.List;

import static muramasa.gregtech.data.GregTechMaterialTags.SEMIFUELS;

public class LargeBoilerLoader {
    public static void init(){
        AntimatterPlatformUtils.getAllBurnables().forEach((i, b) -> {
            ResourceLocation id = AntimatterPlatformUtils.getIdFromItem(i);
            RecipeMaps.LARGE_BOILERS.RB().ii(RecipeIngredient.of(i)).add(id.getNamespace() + "." + id.getPath(), b / 80, b % 80);
        });
        AntimatterMaterialTypes.LIQUID.all().forEach(mat -> {
            if (!mat.has(MaterialTags.FUEL_POWER) || MaterialTags.FUEL_POWER.getInt(mat) <= 0) return;
            int special = mat.has(SEMIFUELS) ? -1 : 4;
            int ticks = mat.has(SEMIFUELS) ? MaterialTags.FUEL_POWER.getInt(mat) * 2 : MaterialTags.FUEL_POWER.getInt(mat) / 2;
            RecipeMaps.LARGE_BOILERS.RB().fi(mat.getLiquid(1000)).add(mat.getId(), ticks, 0, special);
        });
    }
}
