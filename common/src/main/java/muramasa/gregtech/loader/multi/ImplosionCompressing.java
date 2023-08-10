package muramasa.gregtech.loader.multi;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.GregTechMaterialTags;
import muramasa.gregtech.data.Materials;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Diamond;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.IMPLOSION_COMPRESSING;

public class ImplosionCompressing {
    public static void init(){
        GEM.all().stream().filter(m -> !m.has(GregTechMaterialTags.CRYSTALLIZE)).forEach(m -> {
            int tnt = m == Materials.Monazite || m == RedGarnet || m == YellowGarnet || m == Amber | m == Ruby ? 4 : m == Diamond ? 8: 6;
            IMPLOSION_COMPRESSING.RB().ii(DUST.getMaterialIngredient(m, 4), RecipeIngredient.of(Items.TNT, tnt * 2)).io(GEM.get(m, 3), DUST_TINY.get(DarkAsh, tnt * 2)).add(m.getId() + "_from_tnt", 20, 30);
        });
    }
}
