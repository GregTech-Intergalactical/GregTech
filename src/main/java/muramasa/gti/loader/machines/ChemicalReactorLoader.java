package muramasa.gti.loader.machines;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.block.Block;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Items;

import java.util.function.Consumer;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.Data.SCREW;
import static muramasa.gti.data.Materials.*;
import static muramasa.gti.data.RecipeMaps.CHEMICAL_REACTING;
import static muramasa.gti.data.TierMaps.TIER_CIRCUITS;

public class ChemicalReactorLoader {
    public static void init() {
        rubber();
        plastics();
        /* TITANIUM */
        CHEMICAL_REACTING.RB().ii(
                RecipeIngredient.of(DUST.get(Rutile,1)),
                RecipeIngredient.of(DUST.get(Carbon,2))
        ).fi(Chlorine.getGas(4000))
            .fo(Titaniumtetrachloride.getLiquid(1000))
                .add(25*20, 480);
        CHEMICAL_REACTING.RB().ii(
                RecipeIngredient.of(DUST.get(MagnesiumChloride,2)),
                RecipeIngredient.of(DUST.get(Carbon,1))
        ).io(DUST.get(Magnesium,1))
                .fo(Chlorine.getGas(3000))
                .add(20*15, 256);


    }

    private static void rubber() {
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(DUST.get(Sulfur),1),RecipeIngredient.of(DUST.get(RawRubber),9)).fo(Rubber.getLiquid(144*9)).add(200,8);
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(DUST.get(Sulfur),1),RecipeIngredient.of(DUST.get(Polydimethylsiloxane),9)).fo(SiliconRubber.getLiquid(144*9)).add(200,32);
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(DUST.get(Sulfur),1),RecipeIngredient.of(DUST.get(RawStyreneButadieneRubber),9)).fo(StyreneButadieneRubber.getLiquid(144*9)).add(200,128);
    }

    private static void plastics() {
        EMPTY_CELLS.forEach(cell -> CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(cell.fill(Oxygen.getGas()))).fi(Ethylene.getGas(144)).fo(Polyethylene.getLiquid(144)).add(8*20, 30));}
}
