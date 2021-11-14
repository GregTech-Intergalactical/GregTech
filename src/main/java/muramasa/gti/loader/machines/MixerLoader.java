package muramasa.gti.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;

import java.util.List;
import java.util.stream.Collectors;

import static muramasa.antimatter.Data.*;
import static muramasa.gti.data.TierMaps.INT_CIRCUITS;
import static muramasa.gti.data.Materials.*;
import static muramasa.gti.data.RecipeMaps.MIXING;



public class MixerLoader {

    public static void init() {
        addDust(StainlessSteel, 7, 45*20);
        addDust(Nichrome, 7, 25*20);
        addDust(Invar, 7, 15*20);
        addDust(Bronze, 7, 10*20);
        addDust(FerriteMixture, 7, 30*20);
        addDust(IndiumGalliumPhosphide, 7, 20*20);
        addDust(Energium, 7, 10*20);
        addDust(GalliumArsenide, 7, 15*20);
        addDust(VanadiumSteel, 7, 50*20);
        addDust(CobaltBrass, 7, 45*20);
        addDust(BlueSteel, 7, 40*20);
        addDust(RedSteel, 7, 40*20);
        addDust(BlackSteel, 7, 25*20);
        addDust(SterlingSilver, 7, 25*20);
        addDust(RoseGold, 7, 25*20);
        addDust(BismuthBronze, 7, 25*20);
        addDust(Osmiridium, 7, 50*20);
        addDust(YttriumBariumCuprate, 7, 40*20);
        addDust(VanadiumGallium, 7, 20*20);
        addDust(Ultimet, 7, 45*20);
        addDust(TinAlloy, 7, 10*20);
        addDust(SodiumSulfide, 7, 3*20);
        addDust(Magnalium, 7, 20*20);
        addDust(Kanthal, 7, 15*20);
        addDust(Electrum, 7, 10*20);
        addDust(Brass, 7, 20*20);
        addDust(BatteryAlloy, 7, 25*20);
    }

    private static void addDust(Material mat, int eut, int duration) {
        for (MaterialTypeItem<?> type : new MaterialTypeItem[]{DUST, DUST_SMALL, DUST_TINY}) {
            List<RecipeIngredient> ings = mat.getProcessInto().stream().map(t -> type.getMaterialIngredient(t.m, t.s)).collect(Collectors.toList());
            if (ings.size() == 0) return;
            int count = mat.getProcessInto().stream().mapToInt(t -> t.s).sum();
            ings.add(INT_CIRCUITS.get(4));
            MIXING.RB().ii(ings).io(type.get(mat,count)).add(duration,eut);
        }
    }
}
