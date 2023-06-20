package muramasa.gregtech.loader.multi;

import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import static muramasa.antimatter.data.AntimatterMaterialTypes.BLOCK;
import static muramasa.antimatter.data.AntimatterMaterialTypes.GEM;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.COKING;

public class Coking {
    public static void init() {
        COKING.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 1)).io(GEM.get(AntimatterMaterials.Charcoal, 1)).fo(Creosote.getLiquid(100)).add("charcoal",600, 0);
        COKING.RB().ii(RecipeIngredient.of(Items.COAL, 1)).io(GEM.get(CoalCoke, 1)).fo(Creosote.getLiquid(200)).add("coal_coke",600, 0);
        COKING.RB().ii(RecipeIngredient.of(GEM.getMaterialTag(Lignite), 1)).io(GEM.get(LigniteCoke, 1)).fo(Creosote.getLiquid(200)).add("lignite_coal_coke",600, 0);
        COKING.RB().ii(RecipeIngredient.of(Blocks.COAL_BLOCK, 1)).io(BLOCK.get().get(CoalCoke).asStack()).fo(Creosote.getLiquid(1800)).add("coal_coke_block",5400, 0);
        COKING.RB().ii(RecipeIngredient.of(BLOCK.getMaterialTag(Lignite), 1)).io(BLOCK.get().get(LigniteCoke).asStack()).fo(Creosote.getLiquid(1800)).add("lignite_coal_coke_block",5400, 0);
    }
}
