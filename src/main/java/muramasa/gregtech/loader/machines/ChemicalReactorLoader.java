package muramasa.gregtech.loader.machines;


import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.item.ItemBattery;
import muramasa.antimatter.item.ItemFluidCell;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.GregTechData;

import static muramasa.antimatter.Data.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.CHEMICAL_REACTING;

public class ChemicalReactorLoader {
    public static void init() {
        rubber();
        plastics_and_oil();
        batteries();
        /* TITANIUM */
        CHEMICAL_REACTING.RB().ii(
                        RecipeIngredient.of(DUST.get(Rutile, 1)),
                        RecipeIngredient.of(DUST.get(Carbon, 2))
                ).fi(Chlorine.getGas(4000))
                .fo(Titaniumtetrachloride.getLiquid(1000))
                .add(25 * 20, 480);
        CHEMICAL_REACTING.RB().ii(
                        RecipeIngredient.of(DUST.get(MagnesiumChloride, 2)),
                        RecipeIngredient.of(DUST.get(Carbon, 1))
                ).io(DUST.get(Magnesium, 1))
                .fo(Chlorine.getGas(3000))
                .add(20 * 15, 256);

        sulfuric();
    }

    private static void sulfuric() {
        CHEMICAL_REACTING.RB().fi(SulfuricTrioxide.getGas(1000), Water.getLiquid(1000)).fo(SulfuricAcid.getLiquid(1000)).add(320, 7);
        CHEMICAL_REACTING.RB().fi(SulfuricDioxide.getGas(1000), Oxygen.getGas(1000)).fo(SulfuricTrioxide.getGas(1000)).add(200, 30);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Sulfur, 1)).fi(Oxygen.getGas(2000)).fo(SulfuricDioxide.getGas(1000)).add(60, 7);
        CHEMICAL_REACTING.RB().fi(HydricSulfide.getGas(1000), Oxygen.getGas(3000)).fo(SulfuricDioxide.getGas(1000), Water.getLiquid(1000)).add(120, 30);
    }

    private static void batteries() {
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(GregTechData.BatteryHullSmall, 1)).fi(SulfuricAcid.getLiquid(1000)).io(ItemBattery.getFilledBattery(GregTechData.BatterySmallAcid)).add(40, 2);
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(GregTechData.BatteryHullMedium, 1)).fi(SulfuricAcid.getLiquid(4000)).io(ItemBattery.getFilledBattery(GregTechData.BatteryMediumAcid)).add(40, 2);
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(GregTechData.BatteryHullLarge, 1)).fi(SulfuricAcid.getLiquid(16000)).io(ItemBattery.getFilledBattery(GregTechData.BatteryLargeAcid)).add(40, 2);
    }

    private static void rubber() {
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(DUST.get(Sulfur), 1), RecipeIngredient.of(DUST.get(RawRubber), 9)).fo(Rubber.getLiquid(144 * 9)).add(200, 8);
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(DUST.get(Sulfur), 1), RecipeIngredient.of(DUST.get(Polydimethylsiloxane), 9)).fo(SiliconRubber.getLiquid(144 * 9)).add(200, 32);
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(DUST.get(Sulfur), 1), RecipeIngredient.of(DUST.get(RawStyreneButadieneRubber), 9)).fo(StyreneButadieneRubber.getLiquid(144 * 9)).add(200, 128);
    }


    private static void plastics_and_oil() {
        AntimatterAPI.all(ItemFluidCell.class, cell -> CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(cell.fill(Oxygen.getGas()))).fi(Ethylene.getGas(144)).fo(Polyethylene.getLiquid(144)).add(8 * 20, 30));
        CHEMICAL_REACTING.RB().fi(Chloroform.getLiquid(1000), HydrofluoricAcid.getLiquid(2000)).fo(Tetrafluoroethylene.getGas(500), DilutedHydrochloricAcid.getLiquid(3000)).add(240, 240);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(1000), Fluorine.getGas(1000)).fo(HydrofluoricAcid.getLiquid(1000)).add(60, 7);
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(6000), Methane.getGas(1000)).fo(Chloroform.getLiquid(1000), HydrofluoricAcid.getLiquid(3000)).add(80, 30);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Carbon, 1)).fi(Hydrogen.getGas(4000)).fo(Methane.getGas(1000)).add(200, 30);
    }
}
