package muramasa.gregtech.loader.machines;

import muramasa.antimatter.item.ItemBattery;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.gregtech.data.GregTechData;
import muramasa.antimatter.material.Material;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
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
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Rutile, 1)),of(DUST.get(Carbon, 2))).fi(Chlorine.getGas(4000)).fo(Titaniumtetrachloride.getLiquid(1000),CarbonMonoxide.getGas(2000)).add("titanium_tetrachloride",500, 480);
    }

    private static void sulfuric() {
        CHEMICAL_REACTING.RB().fi(SulfurTrioxide.getGas(1000), Water.getLiquid(1000)).fo(SulfuricAcid.getLiquid(1000)).add("sulfuric_acid",320, 7);
        CHEMICAL_REACTING.RB().fi(SulfurDioxide.getGas(1000), Oxygen.getGas(1000)).fo(SulfurTrioxide.getGas(1000)).add("sulfuric_trioxide",200, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Sulfur, 1))).fi(Oxygen.getGas(2000)).fo(SulfurDioxide.getGas(1000)).add("sulfuric_dioxide",60, 7);
        CHEMICAL_REACTING.RB().fi(HydrogenSulfide.getGas(1000), Oxygen.getGas(2000)).fo(SulfurDioxide.getGas(1000), Water.getLiquid(2000)).add("sulfuric_dioxide_2",120, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Sulfur,1))).fi(Hydrogen.getGas(2000)).fo(HydrogenSulfide.getGas(1000)).add("hydrogen_sulfide",60, 8);
    }

    private static void batteries() {
        CHEMICAL_REACTING.RB().ii(of(GregTechData.BatteryHullSmall, 1)).fi(SulfuricAcid.getLiquid(1000)).io(ItemBattery.getFilledBattery(GregTechData.BatterySmallAcid)).add("battery_small_acid",40, 2);
        CHEMICAL_REACTING.RB().ii(of(GregTechData.BatteryHullMedium, 1)).fi(SulfuricAcid.getLiquid(4000)).io(ItemBattery.getFilledBattery(GregTechData.BatteryMediumAcid)).add("battery_medium_acid",40, 2);
        CHEMICAL_REACTING.RB().ii(of(GregTechData.BatteryHullLarge, 1)).fi(SulfuricAcid.getLiquid(16000)).io(ItemBattery.getFilledBattery(GregTechData.BatteryLargeAcid)).add("battery_large_acid",40, 2);
    }

    private static void rubber() {
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Sulfur), 1), of(DUST.get(RawRubber), 9)).fo(Rubber.getLiquid(9000)).add("rubber",200, 8);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Sulfur), 1), of(DUST.get(Polydimethylsiloxane), 9)).fo(SiliconRubber.getLiquid(9000)).add("silicon_rubber",200, 32);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Sulfur), 1), of(DUST.get(RawStyreneButadieneRubber), 9)).fo(StyreneButadieneRubber.getLiquid(9000)).add("styrene_rubber",200, 128);
        CHEMICAL_REACTING.RB().fi(Air.getGas(2000),Isoprene.getLiquid(100)).io(new ItemStack(DUST.get(RawRubber),1)).add("raw_rubber_dust",160, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(2000),Isoprene.getLiquid(100)).io(new ItemStack(DUST.get(RawRubber),3)).add("raw_rubber_dust_2",160, 30);
        CHEMICAL_REACTING.RB().fi(Air.getGas(2000),Styrene.getLiquid(100),Butadiene.getGas(1000)).io(new ItemStack(DUST.get(RawStyreneButadieneRubber),1)).add("raw_styrene_rubber_dust",160, 240);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(2000),Styrene.getLiquid(100),Butadiene.getGas(1000)).io(new ItemStack(DUST.get(RawStyreneButadieneRubber),3)).add("raw_styrene_rubber_dust_2",160, 240);
    }


    private static void plastics() {
        plasticPolymerization(Ethylene,Polyethylene,30,180);
        plasticPolymerization(VinylChloride,PolyvinylChloride,80,360);
        plasticPolymerization(Tetrafluoroethylene,Polytetrafluoroethylene,240,420);
        plasticPolymerization(Styrene,Polystyrene,400,600);
    }

    private static void miscCarbohydrates() {
        CHEMICAL_REACTING.RB().fi(Chloroform.getLiquid(2000), HydrofluoricAcid.getLiquid(4000)).fo(Tetrafluoroethylene.getGas(5000), HydrochloricAcid.getLiquid(6000)).add("tetrafluoroethylene",240, 240);
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(6000), Methane.getGas(1000)).fo(Chloroform.getLiquid(1000), HydrofluoricAcid.getLiquid(3000)).add("chloroform",80, 30);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Carbon, 1)).fi(Hydrogen.getGas(1000)).fo(Methane.getGas(1000)).add("methane",200, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Propene.getGas(2000)).fo(Methane.getGas(1000),Isoprene.getLiquid(1000)).add("methane_2",120, 30);
        CHEMICAL_REACTING.RB().fi(Propene.getGas(1000),Ethylene.getGas(1000)).fo(Hydrogen.getGas(2000),Isoprene.getLiquid(1000)).add("hydrogen",120, 30);
        CHEMICAL_REACTING.RB().fi(Phenol.getLiquid(2000),Acetone.getLiquid(1000),HydrochloricAcid.getLiquid(1000)).fo(BisphenolA.getLiquid(1000),DilutedHydrochloricAcid.getLiquid(1000)).add("bisphenol_a",160, 30);
        CHEMICAL_REACTING.RB().fi(Dichlorobenzene.getLiquid(1000),Epichlorohydrin.getLiquid(1000)).ii(of(DUST.get(SodiumSulfide,1))).fo(PolyphenyleneSulfide.getLiquid(1000)).io(DUST.get(Salt,5)).add("polyphenylene_sulfide",160, 30);
        CHEMICAL_REACTING.RB().fi(Dimethyldichlorosilane.getLiquid(1000), Water.getLiquid(2000)).fo(HydrochloricAcid.getLiquid(2000)).io(DUST.get(Polydimethylsiloxane,1)).add("hydrochloric_acid",240, 96);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Methane.getGas(1000),Chlorine.getGas(3000)).fo(HydrochloricAcid.getLiquid(3000),Chloroform.getLiquid(1000)).add("hydrochloric_acid_2",80, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Methane.getGas(1000),Chlorine.getGas(1000)).fo(HydrochloricAcid.getLiquid(1000),Chloromethane.getGas(1000)).add("hydrochloric_acid_3",80, 30);
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(2000),Benzene.getLiquid(1000)).fo(Dichlorobenzene.getLiquid(1000),HydrochloricAcid.getLiquid(2000)).add("dichlorobenzene",120, 30);
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(1000),Propene.getGas(1000)).fo(AllylChloride.getLiquid(1000),HydrochloricAcid.getLiquid(1000)).add("allyl_chloride",160, 30);
        CHEMICAL_REACTING.RB().fi(AceticAcid.getLiquid(1000),SulfuricAcid.getLiquid(1000)).fo(Ethenone.getGas(1000),DilutedSulfuricAcid.getLiquid(1000)).add("ethenone",160, 120);
        CHEMICAL_REACTING.RB().fi(AceticAcid.getLiquid(1000),Methanol.getLiquid(1000)).fo(MethylAcetate.getLiquid(1000), Water.getLiquid(1000)).add("methyl_acetate",240, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(SodiumHydroxide,1))).fi(Glyceryl.getLiquid(1000),HydrochloricAcid.getLiquid(1000)).io(DUST.get(Salt,1)).fo(Epichlorohydrin.getLiquid(1000), Water.getLiquid(2000)).add("epichlorohydrin",480, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(SodiumHydroxide),1)).fi(HypochlorousAcid.getLiquid(1000),AllylChloride.getLiquid(1000)).fo(Epichlorohydrin.getLiquid(1000),SaltWater.getLiquid(1000)).add("epichlorohydrin_2",480, 30);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(3000),CarbonDioxide.getGas(1000)).fo(Methanol.getLiquid(1000), Water.getLiquid(1000)).add("methanol",360, 96);
        CHEMICAL_REACTING.RB().fi(Chloramine.getLiquid(1000),Dimethylamine.getGas(1000)).fo(Dimethylhydrazine.getLiquid(1000),HydrochloricAcid.getLiquid(1000)).add("dimethylhydrazine",960, 480);
        CHEMICAL_REACTING.RB().fi(Ethylene.getGas(1000),Benzene.getLiquid(1000)).fo(Styrene.getLiquid(1000),Hydrogen.getGas(1000)).add("styrene",120, 30);
        CHEMICAL_REACTING.RB().fi(Propene.getGas(1000),Benzene.getLiquid(1000)).fo(Cumene.getLiquid(1000)).add("cumene",600, 96);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Silicon,1))).fi(Chloromethane.getGas(2000)).fo(Dimethyldichlorosilane.getLiquid(1000)).add("dimethyldichlorosilane",240, 96);
        CHEMICAL_REACTING.RB().fi(Ethylene.getGas(2000),Oxygen.getGas(1000),AceticAcid.getLiquid(2000)).fo(VinylAcetate.getLiquid(2000), Water.getLiquid(2000)).add("vinyl_acetate",180, 30);
        CHEMICAL_REACTING.RB().fi(Methanol.getLiquid(1000),HydrochloricAcid.getLiquid(1000)).fo(Chloromethane.getGas(1000), Water.getLiquid(1000)).add("chloromethane",160, 30);
        CHEMICAL_REACTING.RB().fi(Methane.getGas(1000),Steam.getGas(2000)).fo(CarbonDioxide.getGas(1000),Hydrogen.getGas(4000)).add("carbon_dioxide",140, 480);
        CHEMICAL_REACTING.RB().fi(Benzene.getLiquid(2000),Oxygen.getGas(1000)).fo(Phenol.getLiquid(2000)).add("phenol",240, 2000);
        CHEMICAL_REACTING.RB().fi(Glyceryl.getLiquid(2000),CarbonDioxide.getGas(2000)).fo(Ethylene.getGas(4000),Oxygen.getGas(5000)).add("ethylene",240, 200);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(SodiumHydroxide),1).setNoConsume()).fi(MethylAcetate.getLiquid(1000), Water.getLiquid(1000)).fo(AceticAcid.getLiquid(1000),Methanol.getLiquid(1000)).add("acetic_acid",260, 60);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(AluminiumTrichloride),1).setNoConsume()).fi(Benzene.getLiquid(1000), Chloromethane.getGas(1000)).fo(Toluene.getLiquid(1000),HydrochloricAcid.getLiquid(1000)).add("toluene",140, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(1000), Toluene.getLiquid(1000)).fo(Benzaldehyde.getLiquid(1000)).add("benzaldehyde",120, 30);
        CHEMICAL_REACTING.RB().fi(Benzaldehyde.getLiquid(1000), Chlorine.getGas(2000)).fo(BenzoylChloride.getLiquid(1000),HydrochloricAcid.getLiquid(1000)).add("benzoylchloride",120, 30);
        CHEMICAL_REACTING.RB().fi(BenzoylChloride.getLiquid(2000), HydrogenPeroxide.getLiquid(2000)).io(DUST.get(DibenzoylPeroxide,1)).fo(HydrochloricAcid.getLiquid(2000)).add("dibenzoylperoxide",120, 30);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Carbon, 1)).fi(Hydrogen.getGas(4000)).fo(Methane.getGas(1000)).add("methane_3",200, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(SodiumHydroxide,4))).fi(Dichlorobenzene.getLiquid(2000)).io(DUST.get(Salt,4)).fo(Phenol.getLiquid(2000),Oxygen.getGas(1000)).add("phenol_2",120, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(SodiumHydroxide,1))).fi(BisphenolA.getLiquid(1000),Epichlorohydrin.getLiquid(1000)).io(DUST.get(Epoxid,1)).fo(SaltWater.getLiquid(1000)).add("epoxid",120, 30);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(AluminiumTrichloride,1).setNoConsume()).fi(Benzene.getLiquid(1000),Chlorine.getGas(2000)).fo(Dichlorobenzene.getLiquid(1000),HydrochloricAcid.getLiquid(2000)).add("dichlorobenzene_2",120, 30);
        CHEMICAL_REACTING.RB().fi(Diesel.getLiquid(1000),GlycerylTrinitrate.getLiquid(1000)).fo(NitroFuel.getLiquid(1000)).add("nitrofuel",120, 30);
        CHEMICAL_REACTING.RB().fi(Diesel.getLiquid(1000),Tetranitromethane.getLiquid(1000)).fo(NitroFuel.getLiquid(1000)).add("nitrofuel_2",120, 30);
        CHEMICAL_REACTING.RB().fi(Glyceryl.getLiquid(1000),HydrochloricAcid.getLiquid(1000)).fo(Epichlorohydrin.getLiquid(1000),Water.getLiquid(2000)).add("epichlorohydrin_3",240, 30);
        CHEMICAL_REACTING.RB().fi(Methanol.getLiquid(1000),CarbonMonoxide.getGas(1000)).fo(AceticAcid.getLiquid(1000)).add("acetic_acid_2",300, 30);
        CHEMICAL_REACTING.RB().fi(SeedOil.getLiquid(2000),Methanol.getLiquid(1000)).fo(BioDiesel.getLiquid(1000),Glyceryl.getLiquid(1000)).add("biodiesel",600, 30);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(ReactionCatalyst,1).setNoConsume()).fi(CarbonMonoxide.getGas(1000),Hydrogen.getGas(4000)).fo(Methanol.getLiquid(1000)).add("methanol_2",60, 96);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(ReactionCatalyst,1).setNoConsume()).fi(CarbonDioxide.getGas(1000),Hydrogen.getGas(6000)).fo(Methanol.getLiquid(1000),Water.getLiquid(1000)).add("methanol_3",60, 96);
        CHEMICAL_REACTING.RB().fi(Dimethyldichlorosilane.getLiquid(2000),Water.getLiquid(1000)).io(DUST.get(Polydimethylsiloxane,1)).fo(DilutedHydrochloricAcid.getLiquid(1000)).add("diluted_hydrochloric_acid",60, 96);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(DialuminiumTrioxide,1).setNoConsume()).fi(Methanol.getLiquid(2000),Ammonia.getGas(1000)).fo(Dimethylamine.getGas(2000),Water.getLiquid(1000)).add("dimethylamine",60, 96);
        CHEMICAL_REACTING.RB().fi(SulfuricAcid.getLiquid(1000),AceticAcid.getLiquid(1000)).fo(Ethenone.getGas(1000),DilutedSulfuricAcid.getLiquid(1000)).add("ethenone_2",160, 120);
        CHEMICAL_REACTING.RB().ii(of(Items.SUGAR),of(DUST.get(Polyethylene,1))).fi(Toluene.getLiquid(1000)).io(DUST.get(GelledToluene,1)).add("gelled_toluene",140, 192);
        CHEMICAL_REACTING.RB().fi(HydrofluoricAcid.getLiquid(4000),Chloroform.getLiquid(2000)).io(DUST.get(Tetrafluoroethylene,1)).fo(HydrochloricAcid.getLiquid(6000)).add("tetrafluoroethylene",240, 256);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Salt,1)),of(DUST.get(DibenzoylPeroxide,1))).fi(Dichlorobenzene.getLiquid(2000),Water.getLiquid(1000)).io(DUST.get(PolyphenyleneSulfide,1),DUST.get(Dibenzene,1)).fo(SaltWater.getLiquid(1000),CarbonDioxide.getGas(1000)).add("polyphenylene_sulfide_2",240, 360);
        CHEMICAL_REACTING.RB().fi(Dimethylamine.getGas(1000),Chloramine.getLiquid(1000)).fo(Dimethylhydrazine.getLiquid(1000),DilutedHydrochloricAcid.getLiquid(1000)).add("dimethylhydrazine_2",240, 360);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Silicon,1))).fi(Chloromethane.getGas(2000),Water.getLiquid(1000)).fo(Dimethyldichlorosilane.getLiquid(1000)).add("dimethyldichlorosilane_2",240, 96);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(AluminiumTrichloride,1).setNoConsume()).fi(Propene.getGas(1000)).fo(Cumene.getLiquid(1000)).add("cumene_2",240, 120);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Dibenzene,1)).fi(Hydrogen.getGas(2000)).fo(Benzene.getLiquid(2000)).add("diebzene_separation",200, 80);
    }

    private static void inorganicChemistry(){
        batteries();
        titanium();
        sulfuric();
        nuclear_processing();
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(1000),Hydrogen.getGas(1000)).fo(HydrochloricAcid.getLiquid(1000)).add("hydrochloric_acid_4",60, 8);
        CHEMICAL_REACTING.RB().fi(SulfuricAcid.getLiquid(1000)).ii(of(DUST.get(Salt,2))).fo(HydrochloricAcid.getLiquid(2000)).io(DUST.get(SodiumSulfate,1)).add("hydrochloric_acid_5",60, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Sodium,1))).fi(Water.getLiquid(1000)).fo(Hydrogen.getGas(1000)).io(DUST.get(SodiumHydroxide,1)).add("sodium_hydroxide",40, 8);
        CHEMICAL_REACTING.RB().fi(Nitrogen.getGas(1000),Oxygen.getGas(2000)).fo(NitrogenDioxide.getGas(1000)).add("nitrogen_dioxide",1200, 30);
        CHEMICAL_REACTING.RB().fi(Fluorine.getGas(1000),Hydrogen.getGas(1000)).fo(HydrofluoricAcid.getLiquid(1000)).add("hydroflouric_acid",60, 8);
        CHEMICAL_REACTING.RB().fi(NitrogenDioxide.getGas(3000), Water.getLiquid(1000)).fo(NitricOxide.getGas(1000),NitricAcid.getLiquid(2000)).add("nitric_oxide",40, 240);
        CHEMICAL_REACTING.RB().fi(Ammonia.getGas(4000),Oxygen.getGas(5000)).fo(NitricOxide.getGas(4000), Water.getLiquid(6000)).add("nitric_oxide_2",320, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Carbon,1))).fi(CarbonDioxide.getGas(1000)).fo(CarbonMonoxide.getGas(2000)).add("carbon_monoxide",800, 8);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(3000),Nitrogen.getGas(1000)).fo(Ammonia.getGas(1000)).add("ammonia",320, 384);
        CHEMICAL_REACTING.RB().fi(HypochlorousAcid.getLiquid(1000),Ammonia.getGas(1000)).fo(Chloramine.getLiquid(1000), Water.getLiquid(1000)).add("chloramine",160, 30);
        CHEMICAL_REACTING.RB().fi(Methanol.getLiquid(2000),Ammonia.getGas(1000)).fo(Dimethylamine.getGas(1000), Water.getLiquid(2000)).add("dimethylamine_2",240, 120);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Phosphorus, 4))).fi(Oxygen.getGas(5000)).io(DUST.get(PhosphorousPentoxide, 1)).add("phosphorous_pentoxide",40, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Carbon,1)),INT_CIRCUITS.get(1).setNoConsume()).fi(Oxygen.getGas(1000)).fo(CarbonMonoxide.getGas(1000)).add("carbon_monoxide_2",80, 8);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Carbon,1)),INT_CIRCUITS.get(2).setNoConsume()).fi(Oxygen.getGas(2000)).fo(CarbonDioxide.getGas(1000)).add("carbon_dioxide_2",80, 8);
        CHEMICAL_REACTING.RB().fi(NitrationMixture.getLiquid(4000),Methane.getGas(1000)).fo(Tetranitromethane.getLiquid(1000), DilutedSulfuricAcid.getLiquid(4000)).add("tetranitromethane",480, 120);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Oxygen.getGas(7000),Ammonia.getGas(4000)).fo(DinitrogenTetroxide.getGas(2000), Water.getLiquid(6000)).add("dinitrogen_tetroxide",480, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Oxygen.getGas(2000),Ammonia.getGas(1000)).fo(NitricAcid.getLiquid(1000), Water.getLiquid(1000)).add("nitric_acid",480, 30);
        CHEMICAL_REACTING.RB().fi(Methanol.getLiquid(1000),SeedOil.getLiquid(6000)).fo(Glyceryl.getLiquid(1000),BioDiesel.getLiquid(6000)).add("glyceryl",600, 30);
        CHEMICAL_REACTING.RB().fi(Ethanol.getLiquid(1000),SeedOil.getLiquid(6000)).fo(Glyceryl.getLiquid(1000),BioDiesel.getLiquid(6000)).add("glyceryl_2",600, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Carbon,1)),of(DUST.get(Calcium,1))).fi(Oxygen.getGas(3000)).io(DUST.get(Calcite,1)).add("calcite",500, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Quicklime,1))).fi(CarbonDioxide.getGas(1000)).io(DUST.get(Calcite,1)).add("calcite_2",80, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Magnesia,1))).fi(CarbonDioxide.getGas(1000)).io(DUST.get(Magnesite,1)).add("magnesite",80, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(DialuminiumTrioxide,1),DUST.get(Carbon,3))).fi(Chlorine.getGas(6000)).io(DUST.get(AluminiumTrichloride,2)).fo(CarbonMonoxide.getGas(3000)).add("aluminium_trichloride",100, 40);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(1000), Fluorine.getGas(1000)).fo(HydrofluoricAcid.getLiquid(1000)).add("hydroflouric_acid_2",60, 7);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Sodium,1))).fi(Water.getLiquid(1000)).io(DUST.get(SodiumHydroxide,1)).fo(Hydrogen.getGas(1000)).add("sodium_hydroxide_2",30,120);
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(2000),Water.getLiquid(1000)).fo(HydrochloricAcid.getLiquid(1000),HypochlorousAcid.getLiquid(1000)).add("hydrochloric_acid_6",30,120);
        CHEMICAL_REACTING.RB().fi(NitrogenDioxide.getGas(2000)).fo(DinitrogenTetroxide.getGas(1000)).add("dinitrogen_tetroxide_2",30,120);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(PhosphorousPentoxide,1))).fi(Water.getLiquid(6000)).fo(PhosphoricAcid.getLiquid(4000)).add("phosphoric_acid",30,120);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Salt,1))).fi(SulfuricAcid.getLiquid(1000)).io(DUST.get(SodiumBisulfate,1)).fo(HydrochloricAcid.getLiquid(1000)).add("sodium_bisulfate",30,120);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Magnesia,1))).fi(CarbonDioxide.getGas(1000)).io(DUST.get(Magnesite,1)).add("magnesite_2",30,120);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Quicklime,1))).fi(CarbonDioxide.getGas(1000)).io(DUST.get(Calcite,1)).fo().add("calcite_3",30,120);
        CHEMICAL_REACTING.RB().fi(Propene.getGas(2000)).fo(Isoprene.getLiquid(1000),Methane.getGas(1000)).add("isoprene",30,120);
        CHEMICAL_REACTING.RB().fi(HydrogenSulfide.getGas(2000),SulfurDioxide.getGas(1000)).io(DUST.get(Sulfur,3)).fo(Water.getLiquid(2000)).add("sulfur",30,120);
        CHEMICAL_REACTING.RB().fi(HydrogenSulfide.getGas(1000),Oxygen.getGas(3000)).fo(SulfurDioxide.getGas(1000),Water.getLiquid(1000)).add("sulfuric_dioxide_3",30,120);
        CHEMICAL_REACTING.RB().fi(Benzene.getLiquid(1000),Ethylene.getGas(1000)).fo(Styrene.getLiquid(1000),Hydrogen.getGas(2000)).add("styrene_2",30,120);
        CHEMICAL_REACTING.RB().fi(Propene.getGas(1000),Ethylene.getGas(1000)).fo(Isoprene.getLiquid(1000),Hydrogen.getGas(2000)).add("isoprene_2",30,120);
        CHEMICAL_REACTING.RB().fi(HydrochloricAcid.getLiquid(1000),Ethylene.getGas(1000),Oxygen.getGas(1000)).fo(Dichloroethane.getLiquid(1000),Water.getLiquid(1000)).add("dichloroethane",30,160);
        CHEMICAL_REACTING.RB().fi(Dichloroethane.getLiquid(1000)).fo(VinylChloride.getGas(1000),HydrochloricAcid.getLiquid(1000)).add("vinyl_chloride",30,120);
        CHEMICAL_REACTING.RB().fi(Cumene.getLiquid(1000),Oxygen.getGas(1000),Water.getLiquid(1000)).fo(Phenol.getLiquid(1000),Acetone.getLiquid(1000)).add("phenol_3",30,120);
        CHEMICAL_REACTING.RB().fi(Phenol.getLiquid(1000),Acetone.getLiquid(1000),HydrochloricAcid.getLiquid(1000)).fo(BisphenolA.getLiquid(1000),DilutedHydrochloricAcid.getLiquid(1000)).add("bisphenola_1",30,120);
        CHEMICAL_REACTING.RB().fi(Ammonia.getGas(1000),HypochlorousAcid.getLiquid(1000)).fo(Chloramine.getLiquid(1000),Water.getLiquid(1000)).add("chloramine_2",30,120);
        CHEMICAL_REACTING.RB().fi(Propene.getGas(1000),Chlorine.getGas(1000)).fo(AllylChloride.getLiquid(1000),HydrochloricAcid.getLiquid(1000)).add("allyl_chloride_2",30,120);
        CHEMICAL_REACTING.RB().fi(Ammonia.getGas(2000),Oxygen.getGas(5000)).fo(NitricOxide.getGas(2000),Water.getLiquid(3000)).add("nitric_oxide_3",30,120);
        CHEMICAL_REACTING.RB().fi(Glyceryl.getLiquid(1000),NitrationMixture.getLiquid(3000)).fo(GlycerylTrinitrate.getLiquid(1000),DilutedSulfuricAcid.getLiquid(3000)).add("glyceryl_trinitrate",30,120);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Potassium,1))).fi(NitricAcid.getLiquid(2000)).io(DUST.get(Saltpeter,1)).fo(Hydrogen.getGas(1000)).add("saltpeter",30,180);
        CHEMICAL_REACTING.RB().fi(AceticAcid.getLiquid(1000),Ethylene.getGas(1000),Oxygen.getGas(1000)).fo(VinylAcetate.getLiquid(1000),Water.getLiquid(1000)).add("vinyl_acetate_2",30,180);
        CHEMICAL_REACTING.RB().fi(NitricOxide.getGas(2000),Oxygen.getGas(1000),Water.getLiquid(1000)).fo(NitricAcid.getLiquid(2000)).add("nitric_acid_2",120, 30);
        CHEMICAL_REACTING.RB().fi(NitrogenDioxide.getGas(3000),Water.getLiquid(1000)).fo(NitricAcid.getLiquid(2000),NitricOxide.getGas(1000)).add("nitric_acid_3",120, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(SodiumHydroxide,2))).fi(PeroxydisulfuricAcid.getLiquid(1000)).fo(SodiumPersulfate.getLiquid(1000),Water.getLiquid(2000)).add("sodium_persulfate",300, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(SulfuricAcid.getLiquid(2000)).fo(PeroxydisulfuricAcid.getLiquid(1000),Hydrogen.getGas(2000)).add("peroxydisulfuric_acid",240, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Aluminium,4)),of(DUST.get(SiliconDioxide, 5))).io(DUST.get(Aluminosilicate, 2), DUST.get(Silicon, 3)).add("aluminosilicate_dust",70, 18);
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
        FluidStack fi = alkane.has(LIQUID) ? alkane.getLiquid(mol_alk * 1000) : alkane.has(GAS) ? alkane.getGas(mol_alk * 1000) : FluidStack.EMPTY;
        FluidStack fo = alkanol.has(LIQUID) ? alkanol.getLiquid(mol_alk * 1000) : alkanol.has(GAS) ? alkanol.getGas(mol_alk * 1000) : FluidStack.EMPTY;
        if (fi.isEmpty() || fo.isEmpty()) return;
        RecipeBuilder rb = CHEMICAL_REACTING.RB();
        if (circuit != null){
            rb.ii(circuit);
        }
        rb.fi(fi,Oxygen.getGas(mol_ox*1000)).fo(fo).add(alkane.getId() + "_to_" + alkanol.getId(),200,50);
    }

    private static void alkanolToAlkene(Material alkanol, Material alkene, int mol_alk, int mol_sulf){
        FluidStack fi = alkanol.has(LIQUID) ? alkanol.getLiquid(mol_alk * 1000) : alkanol.has(GAS) ? alkanol.getGas(mol_alk * 1000) : FluidStack.EMPTY;
        FluidStack fo = alkene.has(LIQUID) ? alkene.getLiquid(mol_alk * 1000) : alkene.has(GAS) ? alkene.getGas(mol_alk * 1000) : FluidStack.EMPTY;
        if (fi.isEmpty() || fo.isEmpty()) return;
        CHEMICAL_REACTING.RB().fi(fi,SulfuricAcid.getLiquid(mol_sulf*1000)).fo(fo,DilutedSulfuricAcid.getLiquid(mol_sulf*1000)).add(alkanol.getId() + "_to_" + alkene.getId(),200,50);
    }

    private static void plasticPolymerization(Material monomer, Material polymer, int volt, int dur) {
        FluidStack fi = monomer.has(LIQUID) ? monomer.getLiquid(2000) : monomer.has(GAS) ? monomer.getGas(2000) : FluidStack.EMPTY;
        FluidStack fo = polymer.has(LIQUID) ? polymer.getLiquid(1000) : polymer.has(GAS) ? polymer.getGas(1000) : FluidStack.EMPTY;
        if (fi.isEmpty()) return;
        RecipeBuilder rb = CHEMICAL_REACTING.RB().ii(of(DUST.get(DibenzoylPeroxide,1))).fi(fi).io(DUST.get(Dibenzene,1)).fo(CarbonDioxide.getGas(2000));
        if (fo.isEmpty()){
            if (!polymer.has(DUST)) return;
            rb.io(DUST.get(polymer, 1));
        } else {
            rb.fo(fo);
        }
        rb.add(monomer.getId() +"_to_" + polymer.getId(),dur,volt);
    }

    private static void nuclear_processing() {
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Salt,4))).fi(CarbonDioxide.getGas(2000),Ammonia.getGas(2000),Water.getLiquid(2000)).io(new ItemStack(DUST.get(AmmoniumChloride),12)).fo(SodiumBicarbonateSolution.getLiquid(1000)).add("sodium_bicarbonate_solution",80,100);
        CHEMICAL_REACTING.RB().fi(SodiumBicarbonateSolution.getLiquid(1000)).fo(SodiumCarbonateSolution.getLiquid(1000),CarbonDioxide.getGas(1000),Water.getLiquid(1000)).add("sodium_carbonate_solution",100,200);

        CHEMICAL_REACTING.RB().ii(of(DUST.get(Thorium,1))).fi(LeachingSolution.getLiquid(1000)).fo(LeachedThorium.getLiquid(1000)).add("thorium_leaching",120,1000);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Uranium,1))).fi(LeachingSolution.getLiquid(1000)).fo(LeachedUranium.getLiquid(1000)).add("uranium_leaching",120,1000);

        CHEMICAL_REACTING.RB().ii(of(DUST.get(TrithoriumOctoxide,11))).fi(NitricAcid.getLiquid(3000)).fo(Thoriumdioxidedinitrate.getLiquid(3000),Water.getLiquid(4000),NitrogenDioxide.getGas(2000)).add("thoriumdioxidedinatrate",120,1000);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(TriuraniumOctoxide,11))).fi(NitricAcid.getLiquid(3000)).fo(Uraniumdioxidedinitrate.getLiquid(3000),Water.getLiquid(4000),NitrogenDioxide.getGas(2000)).add("uraniumdioxidedinatrate",120,1000);

        CHEMICAL_REACTING.RB().fi(Thoriumdioxidedinitrate.getLiquid(10000),Ammonia.getGas(22000)).io(DUST.get(Ammoniumdithoranate,5)).fo(Water.getLiquid(13000),NitricOxide.getGas(32000)).add("ammoniumdithoranate",120,1000);
        CHEMICAL_REACTING.RB().fi(Uraniumdioxidedinitrate.getLiquid(10000),Ammonia.getGas(22000)).io(DUST.get(Ammoniumdiuranate,5)).fo(Water.getLiquid(13000),NitricOxide.getGas(32000)).add("ammoniumdiuranate",120,1000);

        CHEMICAL_REACTING.RB().ii(of(DUST.get(Ammoniumdithoranate))).fi(Hydrogen.getGas(4000)).io(DUST.get(ThoriumDioxide,2)).fo(Ammonia.getGas(2000),Water.getLiquid(3000)).add("secondary_thorium_dioxide",120,1000);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(Ammoniumdiuranate))).fi(Hydrogen.getGas(4000)).io(DUST.get(UraniumDioxide,2)).fo(Ammonia.getGas(2000),Water.getLiquid(3000)).add("secondary_uranium_dioxide",120,1000);

        CHEMICAL_REACTING.RB().ii(of(DUST.get(ThoriumDioxide))).fi(HydrofluoricAcid.getLiquid(4000)).fo(ThoriumTetrafluoride.getGas(1000),Water.getLiquid(2000)).add("thoriumtetrafluoride",120,1000);
        CHEMICAL_REACTING.RB().ii(of(DUST.get(UraniumDioxide))).fi(HydrofluoricAcid.getLiquid(4000)).fo(UraniumTetrafluoride.getGas(1000),Water.getLiquid(2000)).add("uraniumtetrafluoride",120,1000);

        CHEMICAL_REACTING.RB().fi(ThoriumTetrafluoride.getGas(1000),Fluorine.getGas(2000)).fo(ThoriumHexafluoride.getGas(1000)).add("thoriumhexafluoride",120,1000);
        CHEMICAL_REACTING.RB().fi(UraniumTetrafluoride.getGas(1000),Fluorine.getGas(2000)).fo(UraniumHexafluoride.getGas(1000)).add("uraniumhexafluoride",120,1000);

        CHEMICAL_REACTING.RB().fi(Thorium227Hexafluoride.getGas(1000),Water.getLiquid(2000),Hydrogen.getGas(2000)).io(DUST.get(Thorium227Dioxide)).fo(HydrofluoricAcid.getLiquid(6000)).add("thorium227_hexafluoride_reduction",120,1000);
        CHEMICAL_REACTING.RB().fi(Thorium228Hexafluoride.getGas(1000),Water.getLiquid(2000),Hydrogen.getGas(2000)).io(DUST.get(Thorium228Dioxide)).fo(HydrofluoricAcid.getLiquid(6000)).add("thorium228_hexafluoride_reduction",120,1000);
        CHEMICAL_REACTING.RB().fi(Thorium229Hexafluoride.getGas(1000),Water.getLiquid(2000),Hydrogen.getGas(2000)).io(DUST.get(Thorium229Dioxide)).fo(HydrofluoricAcid.getLiquid(6000)).add("thorium229_hexafluoride_reduction",120,1000);
        CHEMICAL_REACTING.RB().fi(Thorium230Hexafluoride.getGas(1000),Water.getLiquid(2000),Hydrogen.getGas(2000)).io(DUST.get(Thorium230Dioxide)).fo(HydrofluoricAcid.getLiquid(6000)).add("thorium230_hexafluoride_reduction",120,1000);
        CHEMICAL_REACTING.RB().fi(Thorium231Hexafluoride.getGas(1000),Water.getLiquid(2000),Hydrogen.getGas(2000)).io(DUST.get(Thorium231Dioxide)).fo(HydrofluoricAcid.getLiquid(6000)).add("thorium231_hexafluoride_reduction",120,1000);
        CHEMICAL_REACTING.RB().fi(Thorium233Hexafluoride.getGas(1000),Water.getLiquid(2000),Hydrogen.getGas(2000)).io(DUST.get(Thorium233Dioxide)).fo(HydrofluoricAcid.getLiquid(6000)).add("thorium233_hexafluoride_reduction",120,1000);
        CHEMICAL_REACTING.RB().fi(Thorium234Hexafluoride.getGas(1000),Water.getLiquid(2000),Hydrogen.getGas(2000)).io(DUST.get(Thorium234Dioxide)).fo(HydrofluoricAcid.getLiquid(6000)).add("thorium234_hexafluoride_reduction",120,1000);

        CHEMICAL_REACTING.RB().fi(Uranium232Hexafluoride.getGas(1000),Water.getLiquid(2000),Hydrogen.getGas(2000)).io(DUST.get(Uranium232Dioxide)).fo(HydrofluoricAcid.getLiquid(6000)).add("uranium232_hexafluoride_reduction",120,1000);
        CHEMICAL_REACTING.RB().fi(Uranium233Hexafluoride.getGas(1000),Water.getLiquid(2000),Hydrogen.getGas(2000)).io(DUST.get(Uranium233Dioxide)).fo(HydrofluoricAcid.getLiquid(6000)).add("uranium233_hexafluoride_reduction",120,1000);
        CHEMICAL_REACTING.RB().fi(Uranium234Hexafluoride.getGas(1000),Water.getLiquid(2000),Hydrogen.getGas(2000)).io(DUST.get(Uranium234Dioxide)).fo(HydrofluoricAcid.getLiquid(6000)).add("uranium234_hexafluoride_reduction",120,1000);
        CHEMICAL_REACTING.RB().fi(Uranium235Hexafluoride.getGas(1000),Water.getLiquid(2000),Hydrogen.getGas(2000)).io(DUST.get(Uranium235Dioxide)).fo(HydrofluoricAcid.getLiquid(6000)).add("uranium235_hexafluoride_reduction",120,1000);
        CHEMICAL_REACTING.RB().fi(Uranium236Hexafluoride.getGas(1000),Water.getLiquid(2000),Hydrogen.getGas(2000)).io(DUST.get(Uranium236Dioxide)).fo(HydrofluoricAcid.getLiquid(6000)).add("uranium236_hexafluoride_reduction",120,1000);
        CHEMICAL_REACTING.RB().fi(Uranium237Hexafluoride.getGas(1000),Water.getLiquid(2000),Hydrogen.getGas(2000)).io(DUST.get(Uranium237Dioxide)).fo(HydrofluoricAcid.getLiquid(6000)).add("uranium237_hexafluoride_reduction",120,1000);
        CHEMICAL_REACTING.RB().fi(Uranium238Hexafluoride.getGas(1000),Water.getLiquid(2000),Hydrogen.getGas(2000)).io(DUST.get(Uranium238Dioxide)).fo(HydrofluoricAcid.getLiquid(6000)).add("uranium238_hexafluoride_reduction",120,1000);
        CHEMICAL_REACTING.RB().fi(Uranium239Hexafluoride.getGas(1000),Water.getLiquid(2000),Hydrogen.getGas(2000)).io(DUST.get(Uranium239Dioxide)).fo(HydrofluoricAcid.getLiquid(6000)).add("uranium239_hexafluoride_reduction",120,1000);
        CHEMICAL_REACTING.RB().fi(Uranium240Hexafluoride.getGas(1000),Water.getLiquid(2000),Hydrogen.getGas(2000)).io(DUST.get(Uranium240Dioxide)).fo(HydrofluoricAcid.getLiquid(6000)).add("uranium240_hexafluoride_reduction",120,1000);

    }
}