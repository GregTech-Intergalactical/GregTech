package muramasa.gregtech.loader.machines;


import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.item.ItemBattery;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.GregTechData;
import muramasa.antimatter.material.Material;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.CHEMICAL_REACTING;

public class ChemicalReactorLoader {
    public static void init() {
        organicChemistry();
        inorganicChemistry();
    }

    private static void organicChemistry() {
        rubber();
        plastics();
        miscCarbohydrates();
        alkaloidsToAlkanols();
        alkanolsToAlkenes();
    }

    private static void titanium() {
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Rutile, 1)),of(DUST.get(Carbon, 2))).fi(Chlorine.getGas(4000)).fo(Titaniumtetrachloride.getLiquid(1000),CarbonMonoxide.getGas(2000)).add(500, 480);
    }

    private static void sulfuric() {
        CHEMICAL_REACTING.RB().fi(SulfuricTrioxide.getGas(1000), AntimatterMaterials.Water.getLiquid(1000)).fo(SulfuricAcid.getLiquid(1000)).add(320, 7);
        CHEMICAL_REACTING.RB().fi(SulfuricDioxide.getGas(1000), Oxygen.getGas(1000)).fo(SulfuricTrioxide.getGas(1000)).add(200, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Sulfur, 1))).fi(Oxygen.getGas(2000)).fo(SulfuricDioxide.getGas(1000)).add(60, 7);
        CHEMICAL_REACTING.RB().fi(HydrogenSulfide.getGas(1000), Oxygen.getGas(2000)).fo(SulfuricDioxide.getGas(1000), AntimatterMaterials.Water.getLiquid(2000)).add(120, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Sulfur,1))).fi(Hydrogen.getGas(2000)).fo(HydrogenSulfide.getGas(1000)).add(60, 8);
    }

    private static void batteries() {
        CHEMICAL_REACTING.RB().ii(of(GregTechData.BatteryHullSmall, 1)).fi(SulfuricAcid.getLiquid(1000)).io(ItemBattery.getFilledBattery(GregTechData.BatterySmallAcid)).add(40, 2);
        CHEMICAL_REACTING.RB().ii(of(GregTechData.BatteryHullMedium, 1)).fi(SulfuricAcid.getLiquid(4000)).io(ItemBattery.getFilledBattery(GregTechData.BatteryMediumAcid)).add(40, 2);
        CHEMICAL_REACTING.RB().ii(of(GregTechData.BatteryHullLarge, 1)).fi(SulfuricAcid.getLiquid(16000)).io(ItemBattery.getFilledBattery(GregTechData.BatteryLargeAcid)).add(40, 2);
    }

    private static void rubber() {
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Sulfur), 1), of(DUST.get(RawRubber), 9)).fo(Rubber.getLiquid(9000)).add(200, 8);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Sulfur), 1), of(DUST.get(Polydimethylsiloxane), 9)).fo(SiliconRubber.getLiquid(9000)).add(200, 32);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Sulfur), 1), of(DUST.get(RawStyreneButadieneRubber), 9)).fo(StyreneButadieneRubber.getLiquid(9000)).add(200, 128);
        CHEMICAL_REACTING.RB().fi(Air.getGas(2000),Isoprene.getLiquid(100)).io(new ItemStack(DUST.get(RawRubber),1)).add(160, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(2000),Isoprene.getLiquid(100)).io(new ItemStack(DUST.get(RawRubber),3)).add(160, 30);
        CHEMICAL_REACTING.RB().fi(Air.getGas(2000),Styrene.getLiquid(100),Butadiene.getGas(1000)).io(new ItemStack(DUST.get(RawStyreneButadieneRubber),1)).add(160, 240);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(2000),Styrene.getLiquid(100),Butadiene.getGas(1000)).io(new ItemStack(DUST.get(RawStyreneButadieneRubber),3)).add(160, 240);
    }


    private static void plastics() {
        plasticPolymerization(Ethylene,Polyethylene,30,180);
        plasticPolymerization(VinylChloride,PolyvinylChloride,80,360);
        plasticPolymerization(Tetrafluoroethylene,Polytetrafluoroethylene,240,420);
        plasticPolymerization(Styrene,Polystyrene,400,600);
    }

    private static void miscCarbohydrates() {
        CHEMICAL_REACTING.RB().fi(Chloroform.getLiquid(2000), HydrofluoricAcid.getLiquid(4000)).fo(Tetrafluoroethylene.getGas(5000), HydrochloricAcid.getLiquid(6000)).add(240, 240);
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(6000), Methane.getGas(1000)).fo(Chloroform.getLiquid(1000), HydrofluoricAcid.getLiquid(3000)).add(80, 30);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Carbon, 1)).fi(Hydrogen.getGas(1000)).fo(Methane.getGas(1000)).add(200, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Propene.getGas(2000)).fo(Methane.getGas(1000),Isoprene.getLiquid(1000)).add(120, 30);
        CHEMICAL_REACTING.RB().fi(Propene.getGas(1000),Ethylene.getGas(1000)).fo(Hydrogen.getGas(2000),Isoprene.getLiquid(1000)).add(120, 30);
        CHEMICAL_REACTING.RB().fi(Phenol.getLiquid(2000),Acetone.getLiquid(1000),HydrochloricAcid.getLiquid(1000)).fo(BisphenolA.getLiquid(1000),DilutedHydrochloricAcid.getLiquid(1000)).add(160, 30);
        CHEMICAL_REACTING.RB().fi(Dichlorobenzene.getLiquid(1000),Epichlorohydrin.getLiquid(1000)).ii(of(DUST.get(SodiumSulfide,1))).fo(PolyphenyleneSulfide.getLiquid(1000)).io(DUST.get(Salt,5)).add(160, 30);
        CHEMICAL_REACTING.RB().fi(Dimethyldichlorosilane.getLiquid(1000), AntimatterMaterials.Water.getLiquid(2000)).fo(HydrochloricAcid.getLiquid(2000)).io(DUST.get(Polydimethylsiloxane,1)).add(240, 96);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Methane.getGas(1000),Chlorine.getGas(3000)).fo(HydrochloricAcid.getLiquid(3000),Chloroform.getLiquid(1000)).add(80, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Methane.getGas(1000),Chlorine.getGas(1000)).fo(HydrochloricAcid.getLiquid(1000),Chloromethane.getGas(1000)).add(80, 30);
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(2000),Benzene.getLiquid(1000)).fo(Dichlorobenzene.getLiquid(1000),HydrochloricAcid.getLiquid(2000)).add(120, 30);
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(1000),Propene.getGas(1000)).fo(AllylChloride.getLiquid(1000),HydrochloricAcid.getLiquid(1000)).add(160, 30);
        CHEMICAL_REACTING.RB().fi(AceticAcid.getLiquid(1000),SulfuricAcid.getLiquid(1000)).fo(Ethenone.getGas(1000),DilutedSulfuricAcid.getLiquid(1000)).add(160, 120);
        CHEMICAL_REACTING.RB().fi(AceticAcid.getLiquid(1000),Methanol.getLiquid(1000)).fo(MethylAcetate.getLiquid(1000), AntimatterMaterials.Water.getLiquid(1000)).add(240, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(SodiumHydroxide,1))).fi(Glyceryl.getLiquid(1000),HydrochloricAcid.getLiquid(1000)).io(DUST.get(Salt,1)).fo(Epichlorohydrin.getLiquid(1000), AntimatterMaterials.Water.getLiquid(2000)).add(480, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(SodiumHydroxide),1)).fi(HypochlorousAcid.getLiquid(1000),AllylChloride.getLiquid(1000)).fo(Epichlorohydrin.getLiquid(1000),SaltWater.getLiquid(1000)).add(480, 30);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(3000),CarbonDioxide.getGas(1000)).fo(Methanol.getLiquid(1000), AntimatterMaterials.Water.getLiquid(1000)).add(360, 96);
        CHEMICAL_REACTING.RB().fi(Chloramine.getLiquid(1000),Dimethylamine.getLiquid(1000)).fo(Dimethylhydrazine.getLiquid(1000),HydrochloricAcid.getLiquid(1000)).add(960, 480);
        CHEMICAL_REACTING.RB().fi(Ethylene.getGas(1000),Benzene.getLiquid(1000)).fo(Styrene.getLiquid(1000),Hydrogen.getGas(1000)).add(120, 30);
        CHEMICAL_REACTING.RB().fi(Propene.getGas(1000),Benzene.getLiquid(1000)).fo(Cumene.getLiquid(1000)).add(600, 96);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Silicon,1))).fi(Chloromethane.getLiquid(2000)).fo(Dimethyldichlorosilane.getLiquid(1000)).add(240, 96);
        CHEMICAL_REACTING.RB().fi(Ethylene.getGas(2000),Oxygen.getGas(1000),AceticAcid.getLiquid(2000)).fo(VinylAcetate.getLiquid(2000), AntimatterMaterials.Water.getLiquid(2000)).add(180, 30);
        CHEMICAL_REACTING.RB().fi(Methanol.getLiquid(1000),HydrochloricAcid.getLiquid(1000)).fo(Chloromethane.getGas(1000), AntimatterMaterials.Water.getLiquid(1000)).add(160, 30);
        CHEMICAL_REACTING.RB().fi(Methane.getGas(1000),Steam.getGas(2000)).fo(CarbonDioxide.getGas(1000),Hydrogen.getGas(4000)).add(140, 480);
        CHEMICAL_REACTING.RB().fi(Benzene.getLiquid(2000),Oxygen.getGas(1000)).fo(Phenol.getLiquid(2000)).add(240, 2000);
        CHEMICAL_REACTING.RB().fi(Glyceryl.getLiquid(2000),CarbonDioxide.getGas(2000)).fo(Ethylene.getLiquid(4000),Oxygen.getGas(5000)).add(240, 200);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(SodiumHydroxide),1).setNoConsume()).fi(MethylAcetate.getLiquid(1000), AntimatterMaterials.Water.getLiquid(1000)).fo(AceticAcid.getLiquid(1000),Methanol.getLiquid(1000)).add(260, 60);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Aluminiumtrichloride),1).setNoConsume()).fi(Benzene.getLiquid(1000), Chloromethane.getGas(1000)).fo(Toluene.getLiquid(1000),HydrochloricAcid.getLiquid(1000)).add(140, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(1000), Toluene.getLiquid(1000)).fo(Benzaldehyde.getLiquid(1000)).add(120, 30);
        CHEMICAL_REACTING.RB().fi(Benzaldehyde.getLiquid(1000), Chlorine.getGas(2000)).fo(Benzoylchloride.getLiquid(1000),HydrochloricAcid.getLiquid(1000)).add(120, 30);
        CHEMICAL_REACTING.RB().fi(Benzoylchloride.getLiquid(2000), HydrogenPeroxide.getLiquid(2000)).io(DUST.get(Dibenzoylperoxide,1)).fo(HydrochloricAcid.getLiquid(2000)).add(120, 30);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Carbon, 1)).fi(Hydrogen.getGas(4000)).fo(Methane.getGas(1000)).add(200, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(SodiumHydroxide,4))).fi(Dichlorobenzene.getLiquid(2000)).io(DUST.get(Salt,4)).fo(Phenol.getLiquid(2000),Oxygen.getGas(1000)).add(120, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(SodiumHydroxide,1))).fi(BisphenolA.getLiquid(1000),Epichlorohydrin.getLiquid(1000)).io(DUST.get(Epoxid,1)).fo(SaltWater.getLiquid(1000)).add(120, 30);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Aluminiumtrichloride,1).setNoConsume()).fi(Benzene.getLiquid(1000),Chlorine.getGas(2000)).fo(Dichlorobenzene.getLiquid(1000),HydrochloricAcid.getLiquid(2000)).add(120, 30);
        CHEMICAL_REACTING.RB().fi(Diesel.getLiquid(1000),GlycerylTrinitrate.getLiquid(1000)).fo(NitroFuel.getLiquid(1000)).add(120, 30);
        CHEMICAL_REACTING.RB().fi(Diesel.getLiquid(1000),Tetranitromethane.getLiquid(1000)).fo(NitroFuel.getLiquid(1000)).add(120, 30);
        CHEMICAL_REACTING.RB().fi(Glyceryl.getLiquid(1000),HydrochloricAcid.getLiquid(1000)).fo(Epichlorohydrin.getLiquid(1000),AntimatterMaterials.Water.getLiquid(2000)).add(240, 30);
        CHEMICAL_REACTING.RB().fi(Methanol.getLiquid(1000),CarbonMonoxide.getGas(1000)).fo(AceticAcid.getLiquid(1000)).add(300, 30);
        CHEMICAL_REACTING.RB().fi(SeedOil.getLiquid(2000),Methanol.getLiquid(1000)).fo(BioDiesel.getLiquid(1000),Glyceryl.getLiquid(1000)).add(600, 30);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(ReactionCatalyst,1).setNoConsume()).fi(CarbonMonoxide.getGas(1000),Hydrogen.getGas(4000)).fo(Methanol.getLiquid(1000)).add(60, 96);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(ReactionCatalyst,1).setNoConsume()).fi(CarbonDioxide.getGas(1000),Hydrogen.getGas(6000)).fo(Methanol.getLiquid(1000),AntimatterMaterials.Water.getLiquid(1000)).add(60, 96);
        CHEMICAL_REACTING.RB().fi(Dimethyldichlorosilane.getLiquid(2000),AntimatterMaterials.Water.getLiquid(1000)).io(DUST.get(Polydimethylsiloxane,1)).fo(DilutedHydrochloricAcid.getLiquid(1000)).add(60, 96);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Dialuminiumtrioxide,1).setNoConsume()).fi(Methanol.getLiquid(2000),Ammonia.getGas(1000)).fo(Dimethylamine.getLiquid(2000),AntimatterMaterials.Water.getLiquid(1000)).add(60, 96);
        CHEMICAL_REACTING.RB().fi(SulfuricAcid.getLiquid(1000),AceticAcid.getLiquid(1000)).fo(Ethenone.getLiquid(1000),DilutedSulfuricAcid.getLiquid(1000)).add(160, 120);
        CHEMICAL_REACTING.RB().ii(of(Items.SUGAR),of(DUST.get(Polyethylene,1))).fi(Toluene.getLiquid(1000)).io(DUST.get(GelledToluene,1)).add(140, 192);
        CHEMICAL_REACTING.RB().fi(HydrofluoricAcid.getLiquid(4000),Chloroform.getGas(2000)).io(DUST.get(Tetrafluoroethylene,1)).fo(HydrochloricAcid.getLiquid(6000)).add(240, 256);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Salt,1)),of(DUST.get(Dibenzoylperoxide,1))).fi(Dichlorobenzene.getLiquid(2000),AntimatterMaterials.Water.getLiquid(1000)).io(DUST.get(PolyphenyleneSulfide,1),DUST.get(Dibenzene,1)).fo(SaltWater.getLiquid(1000),CarbonDioxide.getGas(1000)).add(240, 360);
        CHEMICAL_REACTING.RB().fi(Dimethylamine.getLiquid(1000),Chloramine.getLiquid(1000)).fo(Dimethylhydrazine.getLiquid(1000),DilutedHydrochloricAcid.getLiquid(1000)).add(240, 360);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Silicon,1))).fi(Chloromethane.getGas(2000),AntimatterMaterials.Water.getLiquid(1000)).fo(Dimethyldichlorosilane.getLiquid(1000)).add(240, 96);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Aluminiumtrichloride,1).setNoConsume()).fi(Propene.getGas(1000)).fo(Cumene.getLiquid(1000)).add(240, 120);

    }

    private static void inorganicChemistry(){
        batteries();
        titanium();
        sulfuric();
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(1000),Hydrogen.getGas(1000)).fo(HydrochloricAcid.getLiquid(1000)).add(60, 8);
        CHEMICAL_REACTING.RB().fi(SulfuricAcid.getLiquid(1000)).ii(of(DUST.get(Salt,2))).fo(HydrochloricAcid.getLiquid(2000)).io(DUST.get(SodiumSulfate,1)).add(60, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Sodium,1))).fi(AntimatterMaterials.Water.getLiquid(1000)).fo(Hydrogen.getGas(1000)).io(DUST.get(SodiumHydroxide,1)).add(40, 8);
        CHEMICAL_REACTING.RB().fi(Nitrogen.getGas(1000),Oxygen.getGas(2000)).fo(NitrogenDioxide.getGas(1000)).add(1200, 30);
        CHEMICAL_REACTING.RB().fi(Fluorine.getGas(1000),Hydrogen.getGas(1000)).fo(HydrofluoricAcid.getLiquid(1000)).add(60, 8);
        CHEMICAL_REACTING.RB().fi(NitrogenDioxide.getGas(3000), AntimatterMaterials.Water.getLiquid(1000)).fo(NitricOxide.getGas(1000),NitricAcid.getLiquid(2000)).add(40, 240);
        CHEMICAL_REACTING.RB().fi(Ammonia.getGas(4000),Oxygen.getGas(5000)).fo(NitricOxide.getGas(4000), AntimatterMaterials.Water.getLiquid(6000)).add(320, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Carbon,1))).fi(CarbonDioxide.getGas(1000)).fo(CarbonMonoxide.getGas(2000)).add(800, 8);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(3000),Nitrogen.getGas(1000)).fo(Ammonia.getGas(1000)).add(320, 384);
        CHEMICAL_REACTING.RB().fi(HypochlorousAcid.getLiquid(1000),Ammonia.getGas(1000)).fo(Chloramine.getLiquid(1000), AntimatterMaterials.Water.getLiquid(1000)).add(160, 30);
        CHEMICAL_REACTING.RB().fi(Methanol.getLiquid(2000),Ammonia.getGas(1000)).fo(Dimethylamine.getLiquid(1000), AntimatterMaterials.Water.getLiquid(2000)).add(240, 120);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Phosphorus, 4))).fi(Oxygen.getGas(5000)).io(DUST.get(PhosphorousPentoxide, 1)).add(40, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Carbon,1)),INT_CIRCUITS.get(1).setNoConsume()).fi(Oxygen.getGas(1000)).fo(CarbonMonoxide.getGas(1000)).add(80, 8);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Carbon,1)),INT_CIRCUITS.get(2).setNoConsume()).fi(Oxygen.getGas(2000)).fo(CarbonDioxide.getGas(1000)).add(80, 8);
        CHEMICAL_REACTING.RB().fi(NitrationMixture.getLiquid(4000),Methane.getGas(1000)).fo(Tetranitromethane.getLiquid(1000), DilutedSulfuricAcid.getLiquid(4000)).add(480, 120);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Oxygen.getGas(7000),Ammonia.getGas(4000)).fo(DinitrogenTetroxide.getGas(2000), AntimatterMaterials.Water.getLiquid(6000)).add(480, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Oxygen.getGas(2000),Ammonia.getGas(1000)).fo(NitricAcid.getLiquid(1000), AntimatterMaterials.Water.getLiquid(1000)).add(480, 30);
        CHEMICAL_REACTING.RB().fi(Methanol.getLiquid(1000),SeedOil.getLiquid(6000)).fo(Glyceryl.getLiquid(1000),BioDiesel.getLiquid(6000)).add(600, 30);
        CHEMICAL_REACTING.RB().fi(Ethanol.getLiquid(1000),SeedOil.getLiquid(6000)).fo(Glyceryl.getLiquid(1000),BioDiesel.getLiquid(6000)).add(600, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Carbon,1)),of(DUST.get(Calcium,1))).fi(Oxygen.getGas(3000)).io(DUST.get(Calcite,1)).add(500, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Quicklime,1))).fi(CarbonDioxide.getGas(1000)).io(DUST.get(Calcite,1)).add(80, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Magnesia,1))).fi(CarbonDioxide.getGas(1000)).io(DUST.get(Magnesite,1)).add(80, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Dialuminiumtrioxide,1),DUST.get(Carbon,3))).fi(Chlorine.getGas(6000)).io(DUST.get(Aluminiumtrichloride,2)).fo(CarbonMonoxide.getGas(3000)).add(100, 40);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(1000), Fluorine.getGas(1000)).fo(HydrofluoricAcid.getLiquid(1000)).add(60, 7);
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(DUST.get(Sodium,1))).fi(AntimatterMaterials.Water.getLiquid(1000)).io(DUST.get(SodiumHydroxide,1)).fo(Hydrogen.getGas(1000)).add(30,120);
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(2000),AntimatterMaterials.Water.getLiquid(1000)).fo(HydrochloricAcid.getLiquid(1000),HypochlorousAcid.getLiquid(1000)).add(30,120);
        CHEMICAL_REACTING.RB().fi(NitrogenDioxide.getGas(2000)).fo(DinitrogenTetroxide.getGas(1000)).add(30,120);
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(DUST.get(PhosphorousPentoxide,1))).fi(AntimatterMaterials.Water.getLiquid(6000)).fo(PhosphoricAcid.getLiquid(4000)).add(30,120);
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(DUST.get(Salt,1))).fi(SulfuricAcid.getLiquid(1000)).io(DUST.get(SodiumBisulfate,1)).fo(HydrochloricAcid.getLiquid(1000)).add(30,120);
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(DUST.get(Magnesia,1))).fi(CarbonDioxide.getGas(1000)).io(DUST.get(Magnesite,1)).add(30,120);
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(DUST.get(Quicklime,1))).fi(CarbonDioxide.getGas(1000)).io(DUST.get(Calcite,1)).fo().add(30,120);
        CHEMICAL_REACTING.RB().fi(Propene.getGas(2000)).fo(Isoprene.getGas(1000),Methane.getGas(1000)).add(30,120);
        CHEMICAL_REACTING.RB().fi(HydrogenSulfide.getGas(2000),SulfurDioxide.getGas(1000)).io(DUST.get(Sulfur,3)).fo(AntimatterMaterials.Water.getLiquid(2000)).add(30,120);
        CHEMICAL_REACTING.RB().fi(HydrogenSulfide.getGas(1000),Oxygen.getGas(3000)).fo(SulfuricDioxide.getGas(1000),AntimatterMaterials.Water.getLiquid(1000)).add(30,120);
        CHEMICAL_REACTING.RB().fi(Benzene.getLiquid(1000),Ethylene.getGas(1000)).fo(Styrene.getGas(1000),Hydrogen.getGas(2000)).add(30,120);
        CHEMICAL_REACTING.RB().fi(Propene.getGas(1000),Ethylene.getGas(1000)).fo(Isoprene.getGas(1000),Hydrogen.getGas(2000)).add(30,120);
        CHEMICAL_REACTING.RB().fi(HydrochloricAcid.getLiquid(1000),Ethylene.getGas(1000),Oxygen.getGas(1000)).fo(Dichloroethane.getLiquid(1000),AntimatterMaterials.Water.getLiquid(1000)).add(30,160);
        CHEMICAL_REACTING.RB().fi(Dichloroethane.getLiquid(1000)).fo(VinylChloride.getGas(1000),HydrochloricAcid.getLiquid(1000)).add(30,120);
        CHEMICAL_REACTING.RB().fi(Cumene.getLiquid(1000),Oxygen.getGas(1000),AntimatterMaterials.Water.getLiquid(1000)).fo(Phenol.getLiquid(1000),Acetone.getLiquid(1000)).add(30,120);
        CHEMICAL_REACTING.RB().fi(Phenol.getLiquid(1000),Acetone.getLiquid(1000),HydrochloricAcid.getLiquid(1000)).fo(BisphenolA.getLiquid(1000),DilutedHydrochloricAcid.getLiquid(1000)).add(30,120);
        CHEMICAL_REACTING.RB().fi(Ammonia.getGas(1000),HypochlorousAcid.getLiquid(1000)).fo(Chloramine.getLiquid(1000),AntimatterMaterials.Water.getLiquid(1000)).add(30,120);
        CHEMICAL_REACTING.RB().fi(Propene.getGas(1000),Chlorine.getGas(1000)).fo(AllylChloride.getGas(1000),HydrochloricAcid.getLiquid(1000)).add(30,120);
        CHEMICAL_REACTING.RB().fi(Ammonia.getGas(2000),Oxygen.getGas(5000)).fo(NitricOxide.getGas(2000),AntimatterMaterials.Water.getLiquid(3000)).add(30,120);
        CHEMICAL_REACTING.RB().fi(Glyceryl.getLiquid(1000),NitrationMixture.getLiquid(3000)).fo(GlycerylTrinitrate.getLiquid(1000),DilutedSulfuricAcid.getLiquid(3000)).add(30,120);
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(DUST.get(Potassium,1))).fi(NitricAcid.getLiquid(2000)).io(DUST.get(Saltpeter,1)).fo(Hydrogen.getGas(1000)).add(30,180);
        CHEMICAL_REACTING.RB().fi(AceticAcid.getLiquid(1000),Ethylene.getGas(1000),Oxygen.getGas(1000)).fo(VinylAcetate.getLiquid(1000),AntimatterMaterials.Water.getLiquid(1000)).add(30,180);
        CHEMICAL_REACTING.RB().fi(NitricOxide.getGas(2000),Oxygen.getGas(1000),AntimatterMaterials.Water.getLiquid(1000)).fo(NitricAcid.getLiquid(2000)).add(120, 30);
        CHEMICAL_REACTING.RB().fi(NitrogenDioxide.getGas(3000),AntimatterMaterials.Water.getLiquid(1000)).fo(NitricAcid.getLiquid(2000),NitricOxide.getGas(1000)).add(120, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(SodiumHydroxide,2))).fi(PeroxydisulfuricAcid.getLiquid(1000)).fo(SodiumPersulfate.getLiquid(1000),AntimatterMaterials.Water.getLiquid(2000)).add(300, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(2)).fi(SulfuricAcid.getLiquid(2000)).fo(PeroxydisulfuricAcid.getLiquid(1000),Hydrogen.getGas(2000)).add(240, 30);
    }

    private static void alkaloidsToAlkanols(){
        alkaneToAlkanol(Methane,Methanol,1,1);
        alkaneToAlkanol(Ethane,Ethanol,1,1,INT_CIRCUITS.get(1).setNoConsume());
        alkaneToAlkanol(Ethane,Ethanediol,1,2,INT_CIRCUITS.get(2).setNoConsume());
        alkaneToAlkanol(Propane,Propanol,1,1,INT_CIRCUITS.get(1).setNoConsume());
        alkaneToAlkanol(Propane,Propanediol,1,2,INT_CIRCUITS.get(2).setNoConsume());
        alkaneToAlkanol(Propene,Propenol,1,1);
        alkaneToAlkanol(Butane,Butanol,1,1,INT_CIRCUITS.get(1).setNoConsume());
        alkaneToAlkanol(Butane,Butanediol,1,2,INT_CIRCUITS.get(2).setNoConsume());
        alkaneToAlkanol(Butene,Butenol,1,1);
    }

    private static void alkanolsToAlkenes(){
        alkanolToAlkene(Ethanol,Ethylene,1,1);
        alkanolToAlkene(Propanol,Propene,1,1);
        alkanolToAlkene(Butane,Butene,1,1);
        alkanolToAlkene(Butanediol,Butadiene,1,2);
    }

    private static void alkaneToAlkanol(Material alkane, Material alkanol, int mol_alk, int mol_ox, RecipeIngredient... circuit){
        if(circuit!=null){
            if(alkane.has(AntimatterMaterialTypes.LIQUID)){
                CHEMICAL_REACTING.RB().ii(circuit).fi(alkane.getLiquid(mol_alk*1000),Oxygen.getGas(mol_ox*1000)).fo(alkanol.getLiquid(mol_alk*1000)).add(200,50);
            }else if(alkane.has(AntimatterMaterialTypes.GAS)){
                CHEMICAL_REACTING.RB().ii(circuit).fi(alkane.getGas(mol_alk*1000),Oxygen.getGas(mol_ox*1000)).fo(alkanol.getLiquid(mol_alk*1000)).add(200,50);
            }
        }else{
            if(alkane.has(AntimatterMaterialTypes.LIQUID)){
                CHEMICAL_REACTING.RB().fi(alkane.getLiquid(mol_alk*1000),Oxygen.getGas(mol_ox*1000)).fo(alkanol.getLiquid(mol_alk*1000)).add(200,50);
            }else if(alkane.has(AntimatterMaterialTypes.GAS)){
                CHEMICAL_REACTING.RB().fi(alkane.getGas(mol_alk*1000),Oxygen.getGas(mol_ox*1000)).fo(alkanol.getLiquid(mol_alk*1000)).add(200,50);
            }
        }
    }

    private static void alkanolToAlkene(Material alkanol, Material alkene, int mol_alk, int mol_sulf){
        if(alkene.has(AntimatterMaterialTypes.LIQUID)){
            CHEMICAL_REACTING.RB().fi(alkanol.getLiquid(mol_alk*1000),SulfuricAcid.getLiquid(mol_sulf*1000)).fo(alkene.getLiquid(mol_alk*1000),DilutedSulfuricAcid.getLiquid(mol_sulf*1000)).add(200,50);
        }else if(alkene.has(AntimatterMaterialTypes.GAS)){
            CHEMICAL_REACTING.RB().fi(alkanol.getLiquid(mol_alk*1000),SulfuricAcid.getGas(mol_sulf*1000)).fo(alkene.getLiquid(mol_alk*1000),DilutedSulfuricAcid.getLiquid(mol_sulf*1000)).add(200,50);
        }
    }

    private static void plasticPolymerization(Material monomer, Material polymer, int volt, int dur) {
        if(monomer.has(AntimatterMaterialTypes.LIQUID)){
            if(polymer.has(AntimatterMaterialTypes.LIQUID)){
                CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(DUST.get(Dibenzoylperoxide,1))).fi(monomer.getLiquid(2000)).io(DUST.get(Dibenzene,1)).fo(polymer.getLiquid(1000),CarbonDioxide.getGas(2000)).add(dur,volt);
            }else if(polymer.has(DUST)){
                CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(DUST.get(Dibenzoylperoxide,1))).fi(monomer.getLiquid(2000)).io(DUST.get(Dibenzene,1),DUST.get(polymer,1)).fo(CarbonDioxide.getGas(2000)).add(dur,volt);
            }
        }else if(monomer.has(AntimatterMaterialTypes.GAS)){
            if(polymer.has(AntimatterMaterialTypes.LIQUID)){
                CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(DUST.get(Dibenzoylperoxide,1))).fi(monomer.getGas(2000)).io(DUST.get(Dibenzene,1)).fo(polymer.getLiquid(1000),CarbonDioxide.getGas(2000)).add(dur,volt);
            }else if(polymer.has(DUST)){
                CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(DUST.get(Dibenzoylperoxide,1))).fi(monomer.getGas(2000)).io(DUST.get(Dibenzene,1),DUST.get(polymer,1)).fo(CarbonDioxide.getGas(2000)).add(dur,volt);
            }
        }

    }
}