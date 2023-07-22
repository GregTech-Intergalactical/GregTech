package muramasa.gregtech.loader.machines;


import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.CHEMICAL_REACTING;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

public class ChemicalReactorLoader {

    public static void init() {
        addChemicalRecipesSimple();
        //addChemicalRecipesComplicated();
        //nuclearProcessing();
    }



    private static void addChemicalRecipesSimple() {
        //TITANIUM
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Rutile), 1),of(DUST.getMaterialTag(Carbon), 2)).fi(Chlorine.getGas(4000)).fo(Titaniumtetrachloride.getLiquid(1000),CarbonMonoxide.getGas(2000)).add("titanium_tetrachloride",500, 480);
        //SULFURIC ACID
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Sulfur),1), INT_CIRCUITS.get(1).setNoConsume()).fi(Water.getLiquid(2000)).fo(SulfuricAcid.getLiquid(3000)).add("sulfuric_acid",1150, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Sulfur),1), INT_CIRCUITS.get(2).setNoConsume()).fi(Water.getLiquid(3000), Oxygen.getGas(3000)).fo(SulfuricAcid.getLiquid(7000)).add("sulfuric_acid_2",480, 30);
        CHEMICAL_REACTING.RB().fi(Water.getLiquid(1000), HydrogenSulfide.getGas(1000)).fo(SulfuricAcid.getLiquid(1500)).add("sulfuric_acid_3",320, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(4000), HydrogenSulfide.getGas(3000)).ii(INT_CIRCUITS.get(2).setNoConsume()).fo(SulfuricAcid.getLiquid(7000)).add("sulfuric_acid_4",240, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(1000), HydrogenSulfide.getGas(750)).ii(INT_CIRCUITS.get(1).setNoConsume()).fo(SulfuricAcid.getLiquid(1750)).add("sulfuric_acid_5",60, 30);
        //HYDROGEN SULFIDE
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Sulfur),1)).fi(Hydrogen.getGas(2000)).fo(HydrogenSulfide.getGas(3000)).add("hydrogen_sulfide",60, 8);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(125), NaturalGas.getGas(2000)).fo(HydrogenSulfide.getGas(125), RefineryGas.getGas(2000)).add("hydrogen_sulfide_2",20, 30);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(125), SulfuricHeavyFuel.getLiquid(1000)).fo(HydrogenSulfide.getGas(125), HeavyFuel.getLiquid(1000)).add("hydrogen_sulfide_3",20, 30);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(125), SulfuricGas.getGas(1000)).fo(HydrogenSulfide.getGas(125), RefineryGas.getGas(1000)).add("hydrogen_sulfide_4",20, 30);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(250), SulfuricLightFuel.getLiquid(3000)).fo(HydrogenSulfide.getGas(250), LightFuel.getLiquid(3000)).add("hydrogen_sulfide_5",40, 30);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(250), SulfuricNaphtha.getLiquid(3000)).fo(HydrogenSulfide.getGas(250), Naphtha.getLiquid(3000)).add("hydrogen_sulfide_6",40, 30);
        //RUBBER
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Sulfur), 1), of(DUST.get(RawRubber), 9)).fo(Rubber.getLiquid(AntimatterPlatformUtils.isFabric() ? 1000 : 1296)).add("rubber",200, 8);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(14000),Isoprene.getLiquid(2000)).ii(INT_CIRCUITS.get(2).setNoConsume()).io(new ItemStack(DUST.get(RawRubber),21)).add("raw_rubber_dust",2240, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(2000),Isoprene.getLiquid(288)).ii(INT_CIRCUITS.get(1).setNoConsume()).io(new ItemStack(DUST.get(RawRubber),3)).add("raw_rubber_dust_2",320, 30);
        //POLYETHYLENE
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(2000),Naphtha.getLiquid(144)).fo(Polyethylene.getLiquid(72)).add("ethylene_to_polyethylene",320, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(1000),Ethylene.getGas(144)).fo(Polyethylene.getLiquid(216)).add("ethylene_to_polyethylene_1",160, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(2000),Ethylene.getGas(288)).ii(INT_CIRCUITS.get(1).setNoConsume()).fo(Polyethylene.getLiquid(288)).add("polyethylene_2",320, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(16000),Naphtha.getLiquid(1296)).ii(of(DUST_TINY.getMaterialTag(Titanium),1)).fo(Polyethylene.getLiquid(1296)).add("ethylene_to_polyethylene_3",640, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(7000),Ethylene.getGas(1000)).ii(INT_CIRCUITS.get(2).setNoConsume()).fo(Polyethylene.getLiquid(1500)).add("ethylene_to_polyethylene_4",1120, 30);
        //POLYVINYLCHLORIDE
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(1000),VinylChloride.getGas(144)).fo(PolyvinylChloride.getLiquid(216)).add("vinylchloride_to_polyvinylchloride",160, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(7000),VinylChloride.getGas(1000)).fo(PolyvinylChloride.getLiquid(1500)).add("vinylchloride_to_polyvinylchloride_1",1120, 30);
        //TETRAFLUOROETHYLENE
        CHEMICAL_REACTING.RB().fi(Fluorine.getGas(1000),Ethylene.getGas(500)).ii(INT_CIRCUITS.get(1).setNoConsume()).fo(Tetrafluoroethylene.getGas(1500)).add("ethylene_to_tetrafluoroethylene",90, 180);
        CHEMICAL_REACTING.RB().fi(Fluorine.getGas(2000),Ethylene.getGas(1000)).ii(INT_CIRCUITS.get(2).setNoConsume()).fo(Tetrafluoroethylene.getGas(3000)).add("ethylene_to_tetrafluoroethylene_1",180, 180);
        //POLYTETRAFLUOROETHYLENE
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(1000),Tetrafluoroethylene.getGas(144)).ii(INT_CIRCUITS.get(1).setNoConsume()).fo(Polytetrafluoroethylene.getLiquid(216)).add("tetrafluoroethylene_to_polytetrafluoroethylene",160, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(7000),Tetrafluoroethylene.getGas(1000)).ii(INT_CIRCUITS.get(2).setNoConsume()).fo(Polytetrafluoroethylene.getLiquid(1500)).add("tetrafluoroethylene_to_polytetrafluoroethylene_1",1120, 30);
        CHEMICAL_REACTING.RB().fi(Naphtha.getLiquid(3000),Fluorine.getGas(1000),Epichlorohydrin.getLiquid(432)).fo(Polytetrafluoroethylene.getLiquid(432)).add("tetrafluoroethylene_to_polytetrafluoroethylene_2",240, 256);
        //STYRENE
        CHEMICAL_REACTING.RB().fi(Benzene.getLiquid(1000),Ethylene.getGas(500)).ii(INT_CIRCUITS.get(1).setNoConsume()).fo(Styrene.getLiquid(1500)).add("ethylene_to_styrene",60, 30);
        CHEMICAL_REACTING.RB().fi(Benzene.getLiquid(2000),Ethylene.getGas(1000)).ii(INT_CIRCUITS.get(2).setNoConsume()).fo(Styrene.getLiquid(3000)).add("ethylene_to_styrene_1",120, 30);
        //POLYSTYRENE
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(1000),Styrene.getLiquid(144)).ii(INT_CIRCUITS.get(1).setNoConsume()).fo(Polystyrene.getLiquid(216)).add("styrene_to_polystyrene",160, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(7000),Styrene.getLiquid(1000)).ii(INT_CIRCUITS.get(2).setNoConsume()).fo(Polystyrene.getLiquid(1500)).add("styrene_to_polystyrene_1",1120, 30);
        //METHANE
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Carbon, 1)).fi(Hydrogen.getGas(4000)).fo(Methane.getGas(5000)).add("methane",14000, 30);
        //ISOPROPENE
        CHEMICAL_REACTING.RB().fi(Propene.getGas(1500),Ethylene.getGas(1000)).ii(INT_CIRCUITS.get(1).setNoConsume()).fo(Isoprene.getLiquid(1500)).add("isopropene",60, 30);
        CHEMICAL_REACTING.RB().fi(Propene.getGas(3000),Ethylene.getGas(2000)).ii(INT_CIRCUITS.get(2).setNoConsume()).fo(Isoprene.getLiquid(5000)).add("isopropene_1",120, 30);
        //POLYPHENYLENESULFIDE
        CHEMICAL_REACTING.RB().fi(Benzene.getLiquid(1000)).ii(of(DUST.getMaterialTag(Sulfur),1)).fo(PolyphenyleneSulfide.getLiquid(2000)).add("polyphenylene_sulfide",320, 30);
        //EPICHLOROHYDRIN
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Carbon),1)).fi(LPG.getGas(432),Chlorine.getGas(1000)).fo(Epichlorohydrin.getLiquid(432)).add("epichlorohydrin",480, 30);
        //METHANOL
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(1000),CarbonDioxide.getGas(500)).ii(INT_CIRCUITS.get(1).setNoConsume()).fo(Methanol.getLiquid(1000)).add("methanol",100, 120);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(2000),CarbonDioxide.getGas(1000)).ii(INT_CIRCUITS.get(2).setNoConsume()).fo(Methanol.getLiquid(2000),Water.getLiquid(1000)).add("methanol_1",200, 120);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(2000),CarbonDioxide.getGas(1000)).ii(INT_CIRCUITS.get(3).setNoConsume()).fo(Methanol.getLiquid(2000)).add("methanol_2",200, 120);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(1000),CarbonMonoxide.getGas(500)).ii(INT_CIRCUITS.get(1).setNoConsume()).fo(Methanol.getLiquid(1500)).add("methanol_3",150, 120);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(2000),CarbonMonoxide.getGas(1000)).ii(INT_CIRCUITS.get(2).setNoConsume()).fo(Methanol.getLiquid(3000)).add("methanol_4",150, 120);
        //CARBON DIOXIDE
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Charcoal), 1), INT_CIRCUITS.get(2).setNoConsume()).fi(Oxygen.getGas(2000)).fo(CarbonDioxide.getGas(3000)).io(DUST_TINY.get(Ash, 1)).add("carbon_dioxide",40, 8);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Carbon), 1), INT_CIRCUITS.get(2).setNoConsume()).fi(Oxygen.getGas(2000)).fo(CarbonDioxide.getGas(3000)).add("carbon_dioxide_1",40, 8);
        CHEMICAL_REACTING.RB().ii(of(GEM.getMaterialTag(Charcoal), 1), INT_CIRCUITS.get(2).setNoConsume()).fi(Oxygen.getGas(2000)).fo(CarbonDioxide.getGas(3000)).io(DUST_TINY.get(Ash, 1)).add("carbon_dioxide_2",40, 8);
        CHEMICAL_REACTING.RB().ii(of(GEM.getMaterialTag(Coal), 1), INT_CIRCUITS.get(2).setNoConsume()).fi(Oxygen.getGas(2000)).fo(CarbonDioxide.getGas(3000)).io(DUST_TINY.get(Ash, 1)).add("carbon_dioxide_3",40, 8);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Coal), 1), INT_CIRCUITS.get(2).setNoConsume()).fi(Oxygen.getGas(2000)).fo(CarbonDioxide.getGas(3000)).io(DUST_TINY.get(Ash, 1)).add("carbon_dioxide_4",40, 8);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Calcite), 5), INT_CIRCUITS.get(1).setNoConsume()).fo(CarbonDioxide.getGas(3000)).io(DUST.get(Quicklime, 2)).add("carbon_dioxide_5",240, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Magnesia), 5), INT_CIRCUITS.get(1).setNoConsume()).fo(CarbonDioxide.getGas(3000)).io(DUST.get(Magnesia, 2)).add("carbon_dioxide_6",240, 30);
        CHEMICAL_REACTING.RB().fi(Water.getLiquid(3000), Methane.getGas(2000)).fo(CarbonDioxide.getGas(1500), Hydrogen.getGas(4000)).add("carbon_dioxide_7",20, 240);
        //ETHYLENE
        CHEMICAL_REACTING.RB().fi(Butene.getGas(1000)).ii(INT_CIRCUITS.get(12).setNoConsume()).fo(Ethylene.getGas(1000)).add("ethylene",720, 120);
        CHEMICAL_REACTING.RB().fi(Propene.getGas(1000)).ii(INT_CIRCUITS.get(2).setNoConsume()).fo(Ethylene.getGas(1000)).add("ethylene_1",720, 120);
        CHEMICAL_REACTING.RB().fi(SulfuricAcid.getLiquid(2000), Ethanol.getLiquid(3000)).fo(Ethylene.getGas(2000), DilutedSulfuricAcid.getLiquid(3000)).add("ethylene_2",400, 120);
        CHEMICAL_REACTING.RB().fi(SulfuricAcid.getLiquid(2000), Ethanol.getLiquid(3000)).ii(INT_CIRCUITS.get(11).setNoConsume()).fo(Ethylene.getGas(2000), DilutedSulfuricAcid.getLiquid(3000)).add("ethylene_3",400, 120);
        CHEMICAL_REACTING.RB().fi(Ethane.getGas(4000)).ii(INT_CIRCUITS.get(8).setNoConsume()).fo(Ethylene.getGas(3000), Hydrogen.getGas(1000)).add("ethylene_4",320, 120);
        //ACETIC ACID
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(9).setNoConsume()).fi(Oxygen.getGas(1000), Ethylene.getGas(3000)).fo(AceticAcid.getLiquid(4000)).add("acetic_acid",50, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(CarbonMonoxide.getGas(1000), Methanol.getLiquid(3000)).fo(AceticAcid.getLiquid(4000)).add("acetic_acid_1",150, 30);
        //BIODIESEL
        CHEMICAL_REACTING.RB().fi(SeedOil.getLiquid(6000),Methanol.getLiquid(1000)).ii(of(DUST_TINY.getMaterialTag(SodiumHydroxide), 1)).fo(BioDiesel.getLiquid(6000),Glyceryl.getLiquid(1000)).add("biodiesel_and_glyceryl",600, 30);
        CHEMICAL_REACTING.RB().fi(FishOil.getLiquid(6000),Methanol.getLiquid(1000)).ii(of(DUST_TINY.getMaterialTag(SodiumHydroxide), 1)).fo(BioDiesel.getLiquid(6000),Glyceryl.getLiquid(1000)).add("biodiesel_and_glyceryl_1",600, 30);
        CHEMICAL_REACTING.RB().fi(SeedOil.getLiquid(6000),Ethanol.getLiquid(1000)).ii(of(DUST_TINY.getMaterialTag(SodiumHydroxide), 1)).fo(BioDiesel.getLiquid(6000),Glyceryl.getLiquid(1000)).add("biodiesel_and_glyceryl_2",600, 30);
        CHEMICAL_REACTING.RB().fi(FishOil.getLiquid(6000),Methanol.getLiquid(1000)).ii(of(DUST_TINY.getMaterialTag(SodiumHydroxide), 1)).fo(BioDiesel.getLiquid(6000),Glyceryl.getLiquid(1000)).add("biodiesel_and_glyceryl_3",600, 30);
        //GELLED TOLUENE
        CHEMICAL_REACTING.RB().ii(of(Items.SUGAR),of(DUST_TINY.get(Polyethylene,1))).fi(Toluene.getLiquid(133)).io(DUST.get(GelledToluene,2)).add("gelled_toluene",140, 192);
        //NITROGEN DIOXIDE
        CHEMICAL_REACTING.RB().fi(Nitrogen.getGas(500),Oxygen.getGas(1000)).ii(INT_CIRCUITS.get(1).setNoConsume()).fo(NitrogenDioxide.getGas(1500)).add("nitrogen_dioxide",625, 30);
        CHEMICAL_REACTING.RB().fi(Nitrogen.getGas(1000),Oxygen.getGas(2000)).ii(INT_CIRCUITS.get(2).setNoConsume()).fo(NitrogenDioxide.getGas(3000)).add("nitrogen_dioxide_2",1250, 30);
        //CARBON MONOXIDE
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Charcoal), 1), INT_CIRCUITS.get(1).setNoConsume()).fi(Oxygen.getGas(1000)).fo(CarbonMonoxide.getGas(2000)).io(DUST_TINY.get(Ash, 1)).add("carbon_monoxide",40, 8);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Carbon), 1), INT_CIRCUITS.get(1).setNoConsume()).fi(Oxygen.getGas(1000)).fo(CarbonMonoxide.getGas(2000)).add("carbon_monoxide_1",40, 8);
        CHEMICAL_REACTING.RB().ii(of(GEM.getMaterialTag(Charcoal), 1), INT_CIRCUITS.get(1).setNoConsume()).fi(Oxygen.getGas(1000)).fo(CarbonMonoxide.getGas(2000)).io(DUST_TINY.get(Ash, 1)).add("carbon_monoxide_2",40, 8);
        CHEMICAL_REACTING.RB().ii(of(GEM.getMaterialTag(Coal), 1), INT_CIRCUITS.get(1).setNoConsume()).fi(Oxygen.getGas(1000)).fo(CarbonMonoxide.getGas(2000)).io(DUST_TINY.get(Ash, 1)).add("carbon_monoxide_3",40, 8);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Coal), 1), INT_CIRCUITS.get(1).setNoConsume()).fi(Oxygen.getGas(1000)).fo(CarbonMonoxide.getGas(2000)).io(DUST_TINY.get(Ash, 1)).add("carbon_monoxide_4",40, 8);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Carbon), 1)).fi(CarbonDioxide.getGas(3000)).fo(CarbonMonoxide.getGas(4000)).add("carbon_monoxide_5",800, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Rutile), 1), of(DUST.getMaterialTag(Carbon), 2), INT_CIRCUITS.get(1).setNoConsume()).fi(Chlorine.getGas(4000)).fo(CarbonMonoxide.getGas(2000), Titaniumtetrachloride.getLiquid(1000)).add("carbon_monoxide_6",500, 480);
        //AMMONIA
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(3000),Nitrogen.getGas(1000)).ii(INT_CIRCUITS.get(1).setNoConsume()).fo(Ammonia.getGas(1000)).add("ammonia",320, 384);
        //NITRIC ACID
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(11).setNoConsume()).fi(Oxygen.getGas(1000),Ammonia.getGas(1000)).fo(NitricAcid.getLiquid(1000), Water.getLiquid(1000)).add("nitric_acid",160, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(12).setNoConsume()).fi(Oxygen.getGas(1000),Ammonia.getGas(1000)).fo(NitricAcid.getLiquid(1000)).add("nitric_acid_2",160, 30);
        //CALCITE
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Carbon),1),of(DUST.getMaterialTag(Calcium),1)).fi(Oxygen.getGas(3000)).io(DUST.get(Calcite,5)).add("calcite",500, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Quicklime),2)).fi(CarbonDioxide.getGas(3000)).io(DUST.get(Calcite,5)).add("calcite_1",80, 30);
        //MAGNESITE
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Magnesia),2)).fi(CarbonDioxide.getGas(3000)).io(DUST.get(Magnesite,5)).add("magnesite",80, 30);
        //GLYCERYL TRINITRATE
        CHEMICAL_REACTING.RB().fi(Glyceryl.getLiquid(1000),NitrationMixture.getLiquid(3000)).fo(GlycerylTrinitrate.getLiquid(1000),DilutedSulfuricAcid.getLiquid(3000)).add("glyceryl_trinitrate",180,30);
        CHEMICAL_REACTING.RB().fi(Nitrogen.getGas(1000),Water.getLiquid(2000)).ii(of(DUST.getMaterialTag(Carbon), 1)).fo(GlycerylTrinitrate.getLiquid(4000)).add("glyceryl_trinitrate_1",2700,30);
        //SALTPETER
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Potassium),1)).fi(Nitrogen.getGas(1000), Oxygen.getGas(3000)).io(DUST.get(Saltpeter,5)).add("saltpeter",180,30);
        //SODIUM PERSULFATE
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Sodium),1), of(DUST.get(Sulfur, 1))).fi(Oxygen.getGas(4000)).fo(SodiumPersulfate.getLiquid(6000)).add("sodium_persulfate",8000, 30);
        //CETANE-BOOSTED DIESEL
        CHEMICAL_REACTING.RB().fi(Diesel.getLiquid(1000),NitricAcid.getLiquid(125)).fo(CetaneBoostedDiesel.getLiquid(500)).add("cetaneboosteddiesel",40,8);
        CHEMICAL_REACTING.RB().fi(BioDiesel.getLiquid(1000),NitricAcid.getLiquid(250)).fo(CetaneBoostedDiesel.getLiquid(750)).add("cetaneboosteddiesel_1",80,8);
        CHEMICAL_REACTING.RB().fi(LightFuel.getLiquid(1000),NitricAcid.getLiquid(250)).fo(CetaneBoostedDiesel.getLiquid(1000)).add("cetaneboosteddiesel_2",80,8);
        CHEMICAL_REACTING.RB().fi(LightFuel.getLiquid(1000),GlycerylTrinitrate.getLiquid(250)).fo(CetaneBoostedDiesel.getLiquid(1250)).add("cetaneboosteddiesel_3",250,30);
        //RADON
        CHEMICAL_REACTING.RB().ii(of(INGOT.getMaterialTag(Plutonium239), 3)).fo(Radon.getGas(50)).io(DUST.get(Plutonium239, 3)).add("radon",6000,8);
        //TNT
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(GelledToluene), 4)).fi(SulfuricAcid.getLiquid(250)).io(Items.TNT).add("tnt",200,24);
        //WATER
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(1000), Oxygen.getGas(500)).fo(Water.getLiquid(1500)).add("water",5,30);
        //GLISTERING MELON SLICE
        CHEMICAL_REACTING.RB().ii(of(NUGGET.getMaterialTag(Gold), 8), RecipeIngredient.of(Items.MELON_SLICE)).io(Items.GLISTERING_MELON_SLICE).add("glistering_melon_slice",50,30);
        //GOLDEN APPLE
        CHEMICAL_REACTING.RB().ii(of(INGOT.getMaterialTag(Gold), 8), RecipeIngredient.of(Items.APPLE)).io(Items.GOLDEN_APPLE).add("golden_apple",50,30);
        //MAGMA CREAM
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(Items.SLIME_BALL), RecipeIngredient.of(Items.BLAZE_POWDER)).io(Items.MAGMA_CREAM).add("magma_cream",50,30);
        //EYE OF ENDER
        CHEMICAL_REACTING.RB().ii(RecipeIngredient.of(Items.ENDER_PEARL), RecipeIngredient.of(Items.BLAZE_POWDER)).io(Items.ENDER_EYE).add("ender_eye",50,30);
        //Golden CARROT
        CHEMICAL_REACTING.RB().ii(of(NUGGET.getMaterialTag(Gold), 8), RecipeIngredient.of(Items.CARROT)).io(Items.GOLDEN_CARROT).add("golden_carrot",50,30);
        //TINY PILE OF PLATINUM GROUP SLUDGE DUST
        CHEMICAL_REACTING.RB().ii(of(CRUSHED_PURIFIED.getMaterialTag(Chalcopyrite), 1)).fi(NitricAcid.getLiquid(1000)).io(DUST_TINY.get(PlatinumGroupSludge, 1)).fo(BlueVitriol.getLiquid(9000)).add("tiny_pile_of_platinum_group_sludge",50,30);
        CHEMICAL_REACTING.RB().ii(of(CRUSHED_PURIFIED.getMaterialTag(Pentlandite), 1)).fi(NitricAcid.getLiquid(1000)).io(DUST_TINY.get(PlatinumGroupSludge, 1)).fo(NickelSulfate.getLiquid(9000)).add("tiny_pile_of_platinum_group_sludge_1",50,30);
        //NITROUS OXIDE
        // CHEMICAL_REACTING.RB().fi(Nitrogen.getGas(1000),Oxygen.getGas(500)).ii(INT_CIRCUITS.get(3).setNoConsume()).fo(NitrousOxide.getGas(1500)).add("nitrous_oxide",100, 30);
        //MOLTEN EPOXY RESIN
        CHEMICAL_REACTING.RB().fi(Naphtha.getLiquid(3000), NitrogenDioxide.getGas(1000), Epichlorohydrin.getLiquid(144)).fo(EpoxyResin.getLiquid(288)).add("epoxy_resin",240,30);
        CHEMICAL_REACTING.RB().fi(Propene.getGas(1000), Benzene.getLiquid(1000), Chlorine.getGas(500)).fo(EpoxyResin.getLiquid(288)).add("epoxy_resin_1",120,240);
        //GLUE
        CHEMICAL_REACTING.RB().fi(Ethylene.getGas(1000), AceticAcid.getLiquid(2000), Methanol.getLiquid(1000)).fo(Glue.getLiquid(4000)).add("glue",240,30);
        //NETHER QUARTZ
        CHEMICAL_REACTING.RB().fi(Water.getLiquid(1000)).ii(of(DUST.getMaterialTag(Quartz), 3), of(DUST.getMaterialTag(Sodium), 1)).io(GEM.get(Quartz, 3)).add("quartz",500,30);
        //QUARTZITE
        CHEMICAL_REACTING.RB().fi(Water.getLiquid(1000)).ii(of(DUST.get(Quartzite, 3), DUST.get(Sodium, 1))).io(GEM.get(Quartzite, 3)).add("quartzite",500,30);
        //SILICONE RUBBER
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Polydimethylsiloxane), 9), of(DUST.getMaterialTag(Sulfur), 1)).fo(SiliconeRubber.getLiquid(1296)).add("silcone_rubber",600,30);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Silicon), 1)).fi(Epichlorohydrin.getLiquid(144)).fo(SiliconeRubber.getLiquid(144)).add("silcone_rubber_1",240,96);
        //STYRENE-BUTADIENE RUBBER
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(RawStyreneButadieneRubber), 9), of(DUST.get(Sulfur), 1)).fo(StyreneButadieneRubber.getLiquid(1296)).add("styrenebutadiene_rubber",600,30);
        //POLYCAPROLACTAM
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Saltpeter), 1)).fi(Naphtha.getLiquid(576)).fo(Polycaprolactam.getLiquid(1296)).add("polycaprolactam",640,30);
        //URANIUM 238 DUST
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Uraninite), 1), of(DUST.getMaterialTag(Magnesium), 1)).io(DUST.get(Uranium, 1)).add("uranium238",1000,30);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Uraninite), 1), of(DUST.getMaterialTag(Aluminium), 1)).io(DUST.get(Uranium, 1)).add("uranium238_1",1000,30);
        //CHEMICAL DYES
        // CHEMICAL_REACTING.RB().ii(of(DUST.get(Salt, 2)), RecipeIngredient.of(Items.GRAY_DYE)).fi(SulfuricAcid.getLiquid(432)).fo(ChemicalGrayDye.getLiquid(288)).add("chemical_gray_dye",600,48);
        //BUTADIENE
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(4).setNoConsume()).fi(Butene.getGas(1200)).fo(Hydrogen.getGas(500), Butadiene.getGas(1000)).add("butadiene",64,120);
        //BUTENE
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(4).setNoConsume()).fi(Ethylene.getGas(25)).fo(Butene.getGas(25)).add("butane_to_butene",18,120);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(5).setNoConsume()).fi(Butane.getGas(3500)).fo(Hydrogen.getGas(500), Butene.getGas(3000)).add("butane_to_butene_1",160,120);
        //POLYDIMETHYLSILOXANE pulp
        CHEMICAL_REACTING.RB().fi(Methane.getGas(2000), Chlorine.getGas(1000)).io(DUST.get(Polydimethylsiloxane, 3)).add("polydimethylsiloxane_pulp",240,120);
        //DILUTED SULFURIC ACID
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Ethanol.getLiquid(1500), SulfuricAcid.getLiquid(1000)).fo(DilutedSulfuricAcid.getLiquid(1500)).add("diluted_sulfuric_acid",200,120);
        //PROPENE
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Ethylene.getGas(25)).fo(Propene.getGas(25)).add("propane_to_propene",18,120);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(8).setNoConsume()).fi(Propane.getGas(5500)).fo(Propene.getGas(4500), Hydrogen.getGas(500)).add("propane_to_propene_1",320,120);
        //INDIUM CONCENTRATE
        CHEMICAL_REACTING.RB().ii(of(CRUSHED_PURIFIED.getMaterialTag(Galena), 3), of(CRUSHED_PURIFIED.getMaterialTag(Sphalerite), 1)).fi(SulfuricAcid.getLiquid(4000)).fo(IndiumConcentrate.getLiquid(8000)).add("indiumconcentrate",60,150);
        //HYDROGEN
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(12).setNoConsume()).fi(Methane.getGas(2500), Water.getLiquid(3000)).fo(Hydrogen.getGas(4000)).add("hydrogen",20,240);
        //TINY PILE OF INDIUM DUST
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Aluminium), 4)).fi(IndiumConcentrate.getLiquid(8000)).fo(LeadZincSolution.getLiquid(8000)).io(DUST_TINY.get(Indium, 1)).add("tiny_pile_of_indium_dust",50,600);
        //TITANIUMTETRACHLORIDE
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Rutile), 1), of(DUST.getMaterialTag(Carbon), 2), INT_CIRCUITS.get(2).setNoConsume()).fi(Chlorine.getGas(4000)).fo(Titaniumtetrachloride.getLiquid(1000)).add("titaniumtetrachloride",500, 480);
        //ETHYL TERT-BUTYL ETHER
        CHEMICAL_REACTING.RB().fi(Ethanol.getLiquid(1000), Butene.getGas(1000)).fo(EthylTertButylEther.getLiquid(2000)).add("ethyltertbutylether",400, 480);
        //ROCKET FUEL
        CHEMICAL_REACTING.RB().fi(NitrogenDioxide.getGas(1000), Hydrogen.getGas(3000)).fo(RocketFuel.getLiquid(3000), Water.getLiquid(4000)).add("rocketfuel",1000,388);
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(1000), Ammonia.getGas(3000), Methanol.getLiquid(4000)).fo(RocketFuel.getLiquid(7000)).add("rocketfuel_1",3600,480);
        //CHLORINE
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Sodium), 1), of(DUST.getMaterialTag(MagnesiumChloride), 2)).fo(Chlorine.getGas(3000)).io(DUST_SMALL.get(Magnesium, 6)).add("chlorine",300,240);
        //RAW STYRENE-BUDADIENE RUBBER PULP
        CHEMICAL_REACTING.RB().fi(Butadiene.getGas(3000), Oxygen.getGas(4000), Styrene.getLiquid(1000)).io(DUST.get(RawStyreneButadieneRubber, 6)).add("raw_styrenebutadiene_rubber_pulp",160,240);






    }
    private static void nuclearProcessing() {

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

/*
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
*/
    /*
    private static void alkanolsToAlkenes(){
        alkanolToAlkene(Ethanol,Ethylene,1,1);
        alkanolToAlkene(Propanol,Propene,1,1);
        alkanolToAlkene(Butane,Butene,1,1);
        alkanolToAlkene(Butanediol,Butadiene,1,2);
    }
*/
    /*
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
*/
    /*
    private static void alkanolToAlkene(Material alkanol, Material alkene, int mol_alk, int mol_sulf){
        FluidStack fi = alkanol.has(LIQUID) ? alkanol.getLiquid(mol_alk * 1000) : alkanol.has(GAS) ? alkanol.getGas(mol_alk * 1000) : FluidStack.EMPTY;
        FluidStack fo = alkene.has(LIQUID) ? alkene.getLiquid(mol_alk * 1000) : alkene.has(GAS) ? alkene.getGas(mol_alk * 1000) : FluidStack.EMPTY;
        if (fi.isEmpty() || fo.isEmpty()) return;
        CHEMICAL_REACTING.RB().fi(fi,SulfuricAcid.getLiquid(mol_sulf*1000)).fo(fo,DilutedSulfuricAcid.getLiquid(mol_sulf*1000)).add(alkanol.getId() + "_to_" + alkene.getId(),200,50);
    }
*/
    /*
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
   */
/*
 private static void addChemicalRecipesComplicated() {
     CHEMICAL_REACTING.RB().fi(Oxygen.getGas(2000)).ii(of(DUST.get(Carbon, 1)), INT_CIRCUITS.get(2).setNoConsume()).fo(CarbonDioxide.getGas(3000)).add("carbon_dioxide",40, 8);
     CHEMICAL_REACTING.RB().fi(Oxygen.getGas(2000)).ii(of(DUST.get(Charcoal, 1)), INT_CIRCUITS.get(2).setNoConsume()).fo(CarbonDioxide.getGas(3000)).io(DUST_TINY.get(Ash, 1)).add("carbon_dioxide_1",40, 8);
     CHEMICAL_REACTING.RB().fi(Oxygen.getGas(2000)).ii(of(DUST.get(Coal, 1)), INT_CIRCUITS.get(2).setNoConsume()).fo(CarbonDioxide.getGas(3000)).io(DUST_TINY.get(Ash, 1)).add("carbon_dioxide_2",40, 8);



 }
*/
}

