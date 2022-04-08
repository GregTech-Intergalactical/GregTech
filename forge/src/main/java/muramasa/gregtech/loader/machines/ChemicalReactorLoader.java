package muramasa.gregtech.loader.machines;


import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.capability.energy.ItemEnergyHandler;
import muramasa.antimatter.item.ItemBattery;
import muramasa.antimatter.item.ItemFluidCell;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.GregTechData;
import tesseract.api.capability.TesseractGTCapability;

import static muramasa.antimatter.Data.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.CHEMICAL_REACTING;

public class ChemicalReactorLoader {
    public static void init() {
        rubber();
        plastics();
        batteries();
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



    private static void batteries() {
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(GregTechData.BatteryHullSmall, 1)).fi(SulfuricAcid.getLiquid(1000)).io(ItemBattery.getFilledBattery(GregTechData.BatterySmallAcid)).add(40,2);
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(GregTechData.BatteryHullMedium, 1)).fi(SulfuricAcid.getLiquid(4000)).io(ItemBattery.getFilledBattery(GregTechData.BatteryMediumAcid)).add(40,2);
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(GregTechData.BatteryHullLarge, 1)).fi(SulfuricAcid.getLiquid(16000)).io(ItemBattery.getFilledBattery(GregTechData.BatteryLargeAcid)).add(40,2);
    }

    private static void rubber() {
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(DUST.get(Sulfur),1),RecipeIngredient.of(DUST.get(RawRubber),9)).fo(Rubber.getLiquid(144*9)).add(200,8);
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(DUST.get(Sulfur),1),RecipeIngredient.of(DUST.get(Polydimethylsiloxane),9)).fo(SiliconRubber.getLiquid(144*9)).add(200,32);
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(DUST.get(Sulfur),1),RecipeIngredient.of(DUST.get(RawStyreneButadieneRubber),9)).fo(StyreneButadieneRubber.getLiquid(144*9)).add(200,128);
    }

    private static void plastics() {
        AntimatterAPI.all(ItemFluidCell.class, cell -> CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(cell.fill(Oxygen.getGas()))).fi(Ethylene.getGas(144)).fo(Polyethylene.getLiquid(144)).add(8*20, 30));}
}
