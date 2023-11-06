package muramasa.gregtech.loader.machines;


import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.GregTechConfig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static muramasa.antimatter.Ref.L;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.CHEMICAL_REACTING;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

public class ChemicalReactorLoader {

    public static void init() {
        addShared();
        if (GregTechConfig.GAMEPLAY.MORE_COMPLICATED_CHEMICAL_RECIPES) {
            addComplicated();
        } else {
            addSimple();
        }
        nuclearProcessing();
    }



    private static void addSimple() {
        //POLYETHYLENE
        CHEMICAL_REACTING.RB().fi(Air.getGas(4),Naphtha.getLiquid(L)).fo(Polyethylene.getLiquid(L / 2)).add("naphtha_to_polyethylene",320, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(1),Ethylene.getGas(L)).fo(Polyethylene.getLiquid((long) (L * 1.5))).add("ethylene_to_polyethylene",160, 30);
        CHEMICAL_REACTING.RB().fi(Air.getGas(2),Ethylene.getGas(L * 2)).fo(Polyethylene.getLiquid(L * 2)).add("ethylene_to_polyethylene_2",320, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(16),Naphtha.getLiquid(L * 9)).ii(DUST_TINY.getMaterialIngredient(Titanium, 1).setNoConsume()).fo(Polyethylene.getLiquid(L * 9)).add("naphtha_to_polyethylene_2",640, 30);
        //POLYVINYLCHLORIDE
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(1000),VinylChloride.getGas(144)).fo(PolyvinylChloride.getLiquid(216)).add("vinylchloride_to_polyvinylchloride",160, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(7000),VinylChloride.getGas(1000)).fo(PolyvinylChloride.getLiquid(1500)).add("vinylchloride_to_polyvinylchloride_1",1120, 30);
        //TETRAFLUOROETHYLENE
        CHEMICAL_REACTING.RB().fi(Fluorine.getGas(2000),Ethylene.getGas(1000)).fo(Tetrafluoroethylene.getGas(3000)).add("ethylene_to_tetrafluoroethylene",180, 180);
        //POLYTETRAFLUOROETHYLENE
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(1000),Tetrafluoroethylene.getGas(144)).ii(INT_CIRCUITS.get(1).setNoConsume()).fo(Polytetrafluoroethylene.getLiquid(216)).add("tetrafluoroethylene_to_polytetrafluoroethylene",160, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(7000),Tetrafluoroethylene.getGas(1000)).ii(INT_CIRCUITS.get(2).setNoConsume()).fo(Polytetrafluoroethylene.getLiquid(1500)).add("tetrafluoroethylene_to_polytetrafluoroethylene_1",1120, 30);
        CHEMICAL_REACTING.RB().fi(Naphtha.getLiquid(3000),Fluorine.getGas(1000),Epichlorohydrin.getLiquid(432)).fo(Polytetrafluoroethylene.getLiquid(432)).add("tetrafluoroethylene_to_polytetrafluoroethylene_2",240, 256);
        //POLYSTYRENE
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(1000),Styrene.getLiquid(144)).ii(INT_CIRCUITS.get(1).setNoConsume()).fo(Polystyrene.getLiquid(216)).add("styrene_to_polystyrene",160, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(7000),Styrene.getLiquid(1000)).ii(INT_CIRCUITS.get(2).setNoConsume()).fo(Polystyrene.getLiquid(1500)).add("styrene_to_polystyrene_1",1120, 30);
        //METHANE
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Carbon, 1)).fi(Hydrogen.getGas(4000)).fo(Methane.getGas(5000)).add("methane",14000, 30);
        //POLYPHENYLENESULFIDE
        CHEMICAL_REACTING.RB().fi(Benzene.getLiquid(1000)).ii(of(DUST.getMaterialTag(Sulfur),1)).fo(PolyphenyleneSulfide.getLiquid(2000)).add("polyphenylene_sulfide",320, 30);
        //EPICHLOROHYDRIN
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Carbon),1)).fi(LPG.getGas(432),Chlorine.getGas(1000)).fo(Epichlorohydrin.getLiquid(432)).add("epichlorohydrin",480, 30);
        //GELLED TOLUENE
        CHEMICAL_REACTING.RB().ii(of(Items.SUGAR),of(DUST_TINY.get(Polyethylene,1))).fi(Toluene.getLiquid(133)).io(DUST.get(GelledToluene,2)).add("gelled_toluene",140, 192);
        //NITROGEN DIOXIDE
        CHEMICAL_REACTING.RB().fi(Nitrogen.getGas(1000),Oxygen.getGas(2000)).fo(NitrogenDioxide.getGas(3000)).add("nitrogen_dioxide",1250, 30);
        //NITRIC ACID
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(1000),Ammonia.getGas(1000)).fo(NitricAcid.getLiquid(1000), Water.getLiquid(1000)).add("nitric_acid",160, 30);
        //CALCITE
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Carbon),1),of(DUST.getMaterialTag(Calcium),1)).fi(Oxygen.getGas(3000)).io(DUST.get(Calcite,5)).add("calcite",500, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Quicklime),2)).fi(CarbonDioxide.getGas(3000)).io(DUST.get(Calcite,5)).add("calcite_1",80, 30);
        //MAGNESITE
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Magnesia),2)).fi(CarbonDioxide.getGas(3000)).io(DUST.get(Magnesite,5)).add("magnesite",80, 30);
        //SODIUM PERSULFATE
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Sodium),1), of(DUST.get(Sulfur, 1))).fi(Oxygen.getGas(4000)).fo(SodiumPersulfate.getLiquid(6000)).add("sodium_persulfate",8000, 30);
        //CETANE-BOOSTED DIESEL
        CHEMICAL_REACTING.RB().fi(Diesel.getLiquid(1000),NitricAcid.getLiquid(125)).fo(CetaneBoostedDiesel.getLiquid(500)).add("cetaneboosteddiesel",40,8);
        CHEMICAL_REACTING.RB().fi(BioDiesel.getLiquid(1000),NitricAcid.getLiquid(250)).fo(CetaneBoostedDiesel.getLiquid(750)).add("cetaneboosteddiesel_1",80,8);
        CHEMICAL_REACTING.RB().fi(LightFuel.getLiquid(1000),NitricAcid.getLiquid(250)).fo(CetaneBoostedDiesel.getLiquid(1000)).add("cetaneboosteddiesel_2",80,8);
        CHEMICAL_REACTING.RB().fi(LightFuel.getLiquid(1000),GlycerylTrinitrate.getLiquid(250)).fo(CetaneBoostedDiesel.getLiquid(1250)).add("cetaneboosteddiesel_3",250,30);
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
        CHEMICAL_REACTING.RB().fi(Nitrogen.getGas(1000),Oxygen.getGas(500)).ii(INT_CIRCUITS.get(3).setNoConsume()).fo(NitrousOxide.getGas(1500)).add("nitrous_oxide",100, 30);
        //MOLTEN EPOXY RESIN
        CHEMICAL_REACTING.RB().fi(Naphtha.getLiquid(3000), NitrogenDioxide.getGas(1000), Epichlorohydrin.getLiquid(L)).fo(EpoxyResin.getLiquid(L *2)).add("epoxy_resin",240,30);
        CHEMICAL_REACTING.RB().fi(Propene.getGas(1000), Benzene.getLiquid(1000), Chlorine.getGas(500)).fo(EpoxyResin.getLiquid(L *2)).add("epoxy_resin_1",120,240);
        //GLUE
        CHEMICAL_REACTING.RB().fi(Ethylene.getGas(1000), AceticAcid.getLiquid(2000), Methanol.getLiquid(1000)).fo(Glue.getLiquid(4000)).add("glue",240,30);
        //SILICONE RUBBER
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Polydimethylsiloxane), 9), of(DUST.getMaterialTag(Sulfur), 1)).fo(SiliconeRubber.getLiquid(L * 9)).add("silcone_rubber",600,30);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Silicon), 1)).fi(Epichlorohydrin.getLiquid(144)).fo(SiliconeRubber.getLiquid(L)).add("silcone_rubber_1",240,96);
        //POLYCAPROLACTAM
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Saltpeter), 1)).fi(Naphtha.getLiquid(576)).fo(Polycaprolactam.getLiquid(L * 9)).add("polycaprolactam",640,30);
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
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Ethanol.getLiquid(3000), SulfuricAcid.getLiquid(2000)).fo(DilutedSulfuricAcid.getLiquid(3000)).add("diluted_sulfuric_acid",400,120);
        //PROPENE
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Ethylene.getGas(25)).fo(Propene.getGas(25)).add("ethylene_to_propene",18,120);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(8).setNoConsume()).fi(Propane.getGas(5500)).fo(Propene.getGas(4500), Hydrogen.getGas(500)).add("propane_to_propene",320,120);
        //INDIUM CONCENTRATE
        CHEMICAL_REACTING.RB().ii(of(CRUSHED_PURIFIED.getMaterialTag(Galena), 3), of(CRUSHED_PURIFIED.getMaterialTag(Sphalerite), 1)).fi(SulfuricAcid.getLiquid(4000)).fo(IndiumConcentrate.getLiquid(8000)).add("indiumconcentrate",60,150);
        //HYDROGEN
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(12).setNoConsume()).fi(Methane.getGas(5000), Water.getLiquid(6000)).fo(Hydrogen.getGas(8000)).add("hydrogen",40,240);
        //TINY PILE OF INDIUM DUST
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Aluminium), 4)).fi(IndiumConcentrate.getLiquid(8000)).fo(LeadZincSolution.getLiquid(8000)).io(DUST_TINY.get(Indium, 1)).add("tiny_pile_of_indium_dust",50,600);
        //ETHYL TERT-BUTYL ETHER
        CHEMICAL_REACTING.RB().fi(Ethanol.getLiquid(1000), Butene.getGas(1000)).fo(EthylTertButylEther.getLiquid(2000)).add("ethyltertbutylether",400, 480);
        //ROCKET FUEL
        CHEMICAL_REACTING.RB().fi(NitrogenDioxide.getGas(1000), Hydrogen.getGas(3000)).fo(RocketFuel.getLiquid(3000), Water.getLiquid(4000)).add("rocketfuel",1000,388);
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(1000), Ammonia.getGas(3000), Methanol.getLiquid(4000)).fo(RocketFuel.getLiquid(7000)).add("rocketfuel_1",3600,480);

    }

    private static void addShared(){
        //CARBON DIOXIDE
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Charcoal), 1), INT_CIRCUITS.get(2).setNoConsume()).fi(Oxygen.getGas(2000)).fo(CarbonDioxide.getGas(3000)).io(DUST_TINY.get(Ash, 1)).add("carbon_dioxide",40, 8);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Carbon), 1), INT_CIRCUITS.get(2).setNoConsume()).fi(Oxygen.getGas(2000)).fo(CarbonDioxide.getGas(3000)).add("carbon_dioxide_1",40, 8);
        CHEMICAL_REACTING.RB().ii(of(GEM.getMaterialTag(Charcoal), 1), INT_CIRCUITS.get(2).setNoConsume()).fi(Oxygen.getGas(2000)).fo(CarbonDioxide.getGas(3000)).io(DUST_TINY.get(Ash, 1)).add("carbon_dioxide_2",40, 8);
        CHEMICAL_REACTING.RB().ii(of(GEM.getMaterialTag(Coal), 1), INT_CIRCUITS.get(2).setNoConsume()).fi(Oxygen.getGas(2000)).fo(CarbonDioxide.getGas(3000)).io(DUST_TINY.get(Ash, 1)).add("carbon_dioxide_3",40, 8);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Coal), 1), INT_CIRCUITS.get(2).setNoConsume()).fi(Oxygen.getGas(2000)).fo(CarbonDioxide.getGas(3000)).io(DUST_TINY.get(Ash, 1)).add("carbon_dioxide_4",40, 8);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Calcite), 5), INT_CIRCUITS.get(1).setNoConsume()).fo(CarbonDioxide.getGas(3000)).io(DUST.get(Quicklime, 2)).add("carbon_dioxide_5",240, 30);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Magnesite), 5), INT_CIRCUITS.get(1).setNoConsume()).fo(CarbonDioxide.getGas(3000)).io(DUST.get(Magnesia, 2)).add("carbon_dioxide_6",240, 30);
        CHEMICAL_REACTING.RB().fi(Water.getLiquid(3000), Methane.getGas(2000)).fo(CarbonDioxide.getGas(1500), Hydrogen.getGas(4000)).add("carbon_dioxide_7",20, 240);
        //GLYCERYL TRINITRATE
        CHEMICAL_REACTING.RB().fi(Glycerol.getLiquid(1000),NitrationMixture.getLiquid(3000)).fo(GlycerylTrinitrate.getLiquid(1000),DilutedSulfuricAcid.getLiquid(3000)).add("glyceryl_trinitrate",180,30);
        CHEMICAL_REACTING.RB().fi(Nitrogen.getGas(1000),Water.getLiquid(2000)).ii(of(DUST.getMaterialTag(Carbon), 1)).fo(GlycerylTrinitrate.getLiquid(4000)).add("glyceryl_trinitrate_1",2700,30);
        //BIODIESEL
        CHEMICAL_REACTING.RB().fi(SeedOil.getLiquid(6000),Methanol.getLiquid(1000)).ii(of(DUST_TINY.getMaterialTag(SodiumHydroxide), 1)).fo(BioDiesel.getLiquid(6000), Glycerol.getLiquid(1000)).add("biodiesel_and_glyceryl",600, 30);
        CHEMICAL_REACTING.RB().fi(FishOil.getLiquid(6000),Methanol.getLiquid(1000)).ii(of(DUST_TINY.getMaterialTag(SodiumHydroxide), 1)).fo(BioDiesel.getLiquid(6000), Glycerol.getLiquid(1000)).add("biodiesel_and_glyceryl_1",600, 30);
        CHEMICAL_REACTING.RB().fi(SeedOil.getLiquid(6000),Ethanol.getLiquid(1000)).ii(of(DUST_TINY.getMaterialTag(SodiumHydroxide), 1)).fo(BioDiesel.getLiquid(6000), Glycerol.getLiquid(1000)).add("biodiesel_and_glyceryl_2",600, 30);
        CHEMICAL_REACTING.RB().fi(FishOil.getLiquid(6000),Methanol.getLiquid(1000)).ii(of(DUST_TINY.getMaterialTag(SodiumHydroxide), 1)).fo(BioDiesel.getLiquid(6000), Glycerol.getLiquid(1000)).add("biodiesel_and_glyceryl_3",600, 30);
        //STYRENE
        CHEMICAL_REACTING.RB().fi(Benzene.getLiquid(1000),Ethylene.getGas(500)).ii(INT_CIRCUITS.get(1).setNoConsume()).fo(Styrene.getLiquid(1500)).add("ethylene_to_styrene",60, 30);
        CHEMICAL_REACTING.RB().fi(Benzene.getLiquid(2000),Ethylene.getGas(1000)).ii(INT_CIRCUITS.get(2).setNoConsume()).fo(Styrene.getLiquid(3000)).add("ethylene_to_styrene_1",120, 30);
        //RUBBER
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Sulfur), 1), of(DUST.get(RawRubber), 9)).fo(Rubber.getLiquid(L * 9)).add("rubber",200, 8);
        CHEMICAL_REACTING.RB().fi(Air.getGas(14000),Isoprene.getLiquid(2000)).ii(INT_CIRCUITS.get(2).setNoConsume()).io(new ItemStack(DUST.get(RawRubber),21)).add("raw_rubber_dust",2240, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(2000),Isoprene.getLiquid(L * 2)).ii(INT_CIRCUITS.get(1).setNoConsume()).io(new ItemStack(DUST.get(RawRubber),3)).add("raw_rubber_dust_2",320, 30);
        //RAW STYRENE-BUDADIENE RUBBER PULP
        CHEMICAL_REACTING.RB().fi(Butadiene.getGas(3000), Oxygen.getGas(4000), Styrene.getLiquid(1000)).io(DUST.get(RawStyreneButadieneRubber, 6)).add("raw_styrene_butadiene_rubber_pulp",160,240);
        //STYRENE-BUTADIENE RUBBER
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(RawStyreneButadieneRubber), 9), of(DUST.get(Sulfur), 1)).fo(StyreneButadieneRubber.getLiquid(L * 9)).add("styrenebutadiene_rubber",600,30);
        //ISOPROPENE
        CHEMICAL_REACTING.RB().fi(Propene.getGas(3000),Ethylene.getGas(2000)).ii(INT_CIRCUITS.get(2).setNoConsume()).fo(Isoprene.getLiquid(5000)).add("isopropene",120, 30);
        //HYDROGEN SULFIDE
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Sulfur),1)).fi(Hydrogen.getGas(2000)).fo(HydrogenSulfide.getGas(3000)).add("hydrogen_sulfide",60, 8);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(125), NaturalGas.getGas(2000)).fo(HydrogenSulfide.getGas(125), RefineryGas.getGas(2000)).add("hydrogen_sulfide_2",20, 30);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(125), SulfuricHeavyFuel.getLiquid(1000)).fo(HydrogenSulfide.getGas(125), HeavyFuel.getLiquid(1000)).add("hydrogen_sulfide_3",20, 30);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(125), SulfuricGas.getGas(1000)).fo(HydrogenSulfide.getGas(125), RefineryGas.getGas(1000)).add("hydrogen_sulfide_4",20, 30);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(250), SulfuricLightFuel.getLiquid(3000)).fo(HydrogenSulfide.getGas(250), LightFuel.getLiquid(3000)).add("hydrogen_sulfide_5",40, 30);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(250), SulfuricNaphtha.getLiquid(3000)).fo(HydrogenSulfide.getGas(250), Naphtha.getLiquid(3000)).add("hydrogen_sulfide_6",40, 30);
        //AMMONIA
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(3000),Nitrogen.getGas(1000)).ii(INT_CIRCUITS.get(1).setNoConsume()).fo(Ammonia.getGas(1000)).add("ammonia",320, 384);
        //ETHYLENE
        CHEMICAL_REACTING.RB().fi(Butene.getGas(1000)).ii(INT_CIRCUITS.get(12).setNoConsume()).fo(Ethylene.getGas(1000)).add("ethylene",720, 120);
        CHEMICAL_REACTING.RB().fi(Propene.getGas(1000)).ii(INT_CIRCUITS.get(2).setNoConsume()).fo(Ethylene.getGas(1000)).add("ethylene_1",720, 120);
        CHEMICAL_REACTING.RB().fi(SulfuricAcid.getLiquid(2000), Ethanol.getLiquid(3000)).fo(Ethylene.getGas(2000), DilutedSulfuricAcid.getLiquid(3000)).add("ethylene_2",400, 120);
        CHEMICAL_REACTING.RB().fi(SulfuricAcid.getLiquid(2000), Ethanol.getLiquid(3000)).ii(INT_CIRCUITS.get(11).setNoConsume()).fo(Ethylene.getGas(2000), DilutedSulfuricAcid.getLiquid(3000)).add("ethylene_3",400, 120);
        CHEMICAL_REACTING.RB().fi(Ethane.getGas(4000)).ii(INT_CIRCUITS.get(8).setNoConsume()).fo(Ethylene.getGas(3000), Hydrogen.getGas(1000)).add("ethylene_4",320, 120);
        //ACETIC ACID
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(9).setNoConsume()).fi(Oxygen.getGas(1000), Ethylene.getGas(3000)).fo(AceticAcid.getLiquid(4000)).add("acetic_acid",50, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(CarbonMonoxide.getGas(1000), Methanol.getLiquid(3000)).fo(AceticAcid.getLiquid(4000)).add("acetic_acid_1",150, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(24).setNoConsume()).fi(CarbonMonoxide.getGas(1000), Hydrogen.getGas(1000)).fo(AceticAcid.getLiquid(2000)).add("acetic_acid_2",80, 30);
        //METHANOL
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(2000),CarbonDioxide.getGas(1000)).ii(INT_CIRCUITS.get(1).setNoConsume()).fo(Methanol.getLiquid(1000)).add("methanol",100, 120);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(2000),CarbonDioxide.getGas(1000)).ii(INT_CIRCUITS.get(2).setNoConsume()).fo(Methanol.getLiquid(2000),Water.getLiquid(1000)).add("methanol_1",200, 120);
        //CARBON MONOXIDE
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Charcoal), 1), INT_CIRCUITS.get(1).setNoConsume()).fi(Oxygen.getGas(1000)).fo(CarbonMonoxide.getGas(2000)).io(DUST_TINY.get(Ash, 1)).add("carbon_monoxide",40, 8);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Carbon), 1), INT_CIRCUITS.get(1).setNoConsume()).fi(Oxygen.getGas(1000)).fo(CarbonMonoxide.getGas(2000)).add("carbon_monoxide_1",40, 8);
        CHEMICAL_REACTING.RB().ii(of(GEM.getMaterialTag(Charcoal), 1), INT_CIRCUITS.get(1).setNoConsume()).fi(Oxygen.getGas(1000)).fo(CarbonMonoxide.getGas(2000)).io(DUST_TINY.get(Ash, 1)).add("carbon_monoxide_2",40, 8);
        CHEMICAL_REACTING.RB().ii(of(GEM.getMaterialTag(Coal), 1), INT_CIRCUITS.get(1).setNoConsume()).fi(Oxygen.getGas(1000)).fo(CarbonMonoxide.getGas(2000)).io(DUST_TINY.get(Ash, 1)).add("carbon_monoxide_3",40, 8);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Coal), 1), INT_CIRCUITS.get(1).setNoConsume()).fi(Oxygen.getGas(1000)).fo(CarbonMonoxide.getGas(2000)).io(DUST_TINY.get(Ash, 1)).add("carbon_monoxide_4",40, 8);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Carbon), 1)).fi(CarbonDioxide.getGas(3000)).fo(CarbonMonoxide.getGas(4000)).add("carbon_monoxide_5",800, 30);
        //SALTPETER
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Potassium),1)).fi(Nitrogen.getGas(1000), Oxygen.getGas(3000)).io(DUST.get(Saltpeter,5)).add("saltpeter",180,30);
        //SULFURIC ACID chain
        CHEMICAL_REACTING.RB().fi(Water.getLiquid(3000), SulfurTrioxide.getGas(4000)).fo(SulfuricAcid.getLiquid(7000)).add("sulfuric_acid_",320, 8);
        CHEMICAL_REACTING.RB().fi(Water.getLiquid(1000), HydrogenSulfide.getGas(1000)).fo(SulfuricAcid.getLiquid(1500)).add("sulfuric_acid_1",320, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(1000), SulfurDioxide.getGas(3000)).fo(SulfurTrioxide.getGas(4000)).add("sulfur_trioxide", 200, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(2000)).ii(DUST.getMaterialIngredient(Sulfur, 1)).fo(SulfurDioxide.getGas(3000)).add("sulfur_dioxide", 60, 8);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(1000), HydrogenSulfide.getGas(1000)).fo(SulfurDioxide.getGas(1000), Water.getLiquid(1000)).add("sulfur_dioxide_1", 40, 30);
        //HYDROCHLORIC ACID
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(1000), Chlorine.getGas(1000)).fo(HydrochloricAcid.getLiquid(2000)).add("hydrochloric_acid", 60, 8);
        //TITANIUMTETRACHLORIDE
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Rutile), 1), of(DUST.getMaterialTag(Carbon), 1)).fi(Chlorine.getGas(4000), Calcite.getLiquid(L)).fo(TitaniumTetrachloride.getLiquid(5000),CarbonDioxide.getGas(3000)).add("titanium_tetrachloride",500, 480);
        CHEMICAL_REACTING.RB().ii(of(DUST.getMaterialTag(Ilmenite), 5), of(DUST.getMaterialTag(Carbon), 3)).fi(Chlorine.getGas(7000), Calcite.getLiquid(L)).io(DUST.get(FerricChloride, 4)).fo(TitaniumTetrachloride.getLiquid(5000),CarbonMonoxide.getGas(6000)).add("titanium_tetrachloride_2",500, 480);
        // Magnesium Chloride
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(MagnesiumCarbonate, 5)).fi(HydrochloricAcid.getLiquid(4000)).io(DUST.get(MagnesiumChloride, 3)).fo(Water.getLiquid(3000), CarbonDioxide.getGas(3000)).add("magnesium_chloride", 144, 16);
        //Alumina chain
        CHEMICAL_REACTING.RB().fi(SulfuricAcid.getLiquid(7000)).ii(DUST.getMaterialIngredient(Fluorite, 3)).fo(HydrogenFluoride.getGas(4000)).io(DUST.get(CalciumSulfate, 6)).add("hydrogen_fluoride", 160, 16);
        CHEMICAL_REACTING.RB().fi(Fluorine.getGas(1000), Hydrogen.getGas(1000)).fo(HydrogenFluoride.getGas(2000)).add("hydrogen_fluoride_1", 16, 16);
        CHEMICAL_REACTING.RB().fi(Fluorine.getGas(2000)).ii(DUST.getMaterialIngredient(Calcium, 1)).io(DUST.get(Fluorite, 3)).add("fluorite", 48, 16);
        CHEMICAL_REACTING.RB().fi(HydrogenFluoride.getGas(4000)).ii(DUST.getMaterialIngredient(SiliconDioxide, 1)).fo(Water.getLiquid(2000), HexafluorosilicicAcid.getLiquid(3000)).add("hexafluorosilicic_acid", 80, 16);
        CHEMICAL_REACTING.RB().fi(HydrogenFluoride.getGas(4000)).ii(DUST.getMaterialIngredient(Quartzite, 1)).fo(Water.getLiquid(2000), HexafluorosilicicAcid.getLiquid(3000)).add("hexafluorosilicic_acid_1", 80, 16);
        CHEMICAL_REACTING.RB().fi(HydrogenFluoride.getGas(4000)).ii(DUST.getMaterialIngredient(Quartz, 1)).fo(Water.getLiquid(2000), HexafluorosilicicAcid.getLiquid(3000)).add("hexafluorosilicic_acid_2", 80, 16);
        CHEMICAL_REACTING.RB().fi(HydrogenFluoride.getGas(4000)).ii(DUST.getMaterialIngredient(Flint, 1)).fo(Water.getLiquid(2000), HexafluorosilicicAcid.getLiquid(3000)).add("hexafluorosilicic_acid_3", 80, 16);
        CHEMICAL_REACTING.RB().fi(HydrogenFluoride.getGas(4000)).ii(DUST.getMaterialIngredient(Glass, 1)).fo(Water.getLiquid(2000), HexafluorosilicicAcid.getLiquid(3000)).add("hexafluorosilicic_acid_4", 80, 16);
        CHEMICAL_REACTING.RB().fi(HydrogenFluoride.getGas(4000)).ii(DUST.getMaterialIngredient(MilkyQuartz, 1)).fo(Water.getLiquid(2000), HexafluorosilicicAcid.getLiquid(3000)).add("hexafluorosilicic_acid_5", 80, 16);
        if (AntimatterAPI.isModLoaded(Ref.MOD_AE)){
            CHEMICAL_REACTING.RB().fi(HydrogenFluoride.getGas(4000)).ii(DUST.getMaterialIngredient(CertusQuartz, 1)).fo(Water.getLiquid(2000), HexafluorosilicicAcid.getLiquid(3000)).add("hexafluorosilicic_acid_6", 80, 16);
        }
        if (GregTechConfig.GAMEPLAY.HARDER_ALUMINIUM_PROCESSING){
            CHEMICAL_REACTING.RB().fi(HexafluorosilicicAcid.getLiquid(9000)).ii(DUST.getMaterialIngredient(Alumina, 5)).io(DUST.get(SiliconDioxide, 3)).fo(AluminiumFluoride.getLiquid(L * 8), Water.getLiquid(3000)).add("aluminium_fluoride", 224, 16);
            CHEMICAL_REACTING.RB().fi(Fluorine.getGas(3000)).ii(DUST.getMaterialIngredient(Aluminium, 1)).fo(AluminiumFluoride.getLiquid(L * 4)).add("aluminium_fluoride_pure", 64, 16);
            CHEMICAL_REACTING.RB().fi(HydrogenFluoride.getGas(24000)).ii(DUST.getMaterialIngredient(SodiumHydroxide, 18), DUST.getMaterialIngredient(Alumina, 5)).fo(Cryolite.getLiquid(L * 20), Water.getLiquid(27000)).add("cryolite", 752, 16);
        }
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(6000)).ii(DUST.getMaterialIngredient(TungstenTrioxide, 4)).io(DUST.get(Tungsten)).fo(Water.getLiquid(9000)).add("tungsten", 160, 16);

    }

    private static void addComplicated(){
        //ACETONE
        CHEMICAL_REACTING.RB().ii(DUST_SMALL.getMaterialIngredient(Calcite, 5)).fi(AceticAcid.getLiquid(8000)).fo(Acetone.getLiquid(5000), CarbonDioxide.getGas(3000)).add("acetone", 400, 480);
        CHEMICAL_REACTING.RB().ii(DUST_SMALL.getMaterialIngredient(Quicklime, 5)).fi(AceticAcid.getLiquid(8000)).fo(Acetone.getLiquid(5000), CarbonDioxide.getGas(3000)).add("acetone_1", 400, 480);
        //METHYL ACETATE
        CHEMICAL_REACTING.RB().fi(Methanol.getLiquid(6000), AceticAcid.getLiquid(8000)).fo(MethylAcetate.getLiquid(11000), Water.getLiquid(3000)).add("methyl_acetate", 240, 30);
        //VINYL ACETATE
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(500), Ethylene.getGas(3000), AceticAcid.getLiquid(4000)).fo(VinylAcetate.getLiquid(6000)).add("vinyl_acetate", 90, 30);
        addPolymerRecipe(VinylAcetate, PolyvinylAcetate);
        //HYDROCHLORIC ACID
        CHEMICAL_REACTING.RB().fi(Methane.getGas(6000), Chlorine.getGas(5000)).ii(INT_CIRCUITS.get(13)).fo(HydrochloricAcid.getLiquid(6000), Chloroform.getLiquid(5000)).add("hydrochloric_acid_1", 16, 30);
        //SODIUM BISULFATE
        CHEMICAL_REACTING.RB().fi(SulfuricAcid.getLiquid(7000)).ii(DUST.getMaterialIngredient(Salt, 2)).io(DUST.get(SodiumBisulfate, 7)).fo(HydrochloricAcid.getLiquid(2000)).add("sodium_bisulfate", 60, 30);
        //VINYL CHLORIDE
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(1000), Ethylene.getGas(3000)).fo(HydrochloricAcid.getLiquid(1000), VinylChloride.getGas(3000)).add("vinyl_chloride3", 80, 30);
        //CHLOROBENZENE
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(2000), Benzene.getLiquid(6000)).ii(INT_CIRCUITS.get(4)).fo(HydrochloricAcid.getLiquid(1000), Chlorobenzene.getLiquid(6000)).add("chlorobenzene", 120, 30);
        //HYPOCHLOROUS ACID
        CHEMICAL_REACTING.RB().fi(Mercury.getLiquid(200), Chlorine.getGas(1000), Water.getLiquid(3000)).fo(HypochlorousAcid.getLiquid(3000)).add("hypochlorous_acid", 120, 8);
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(1000), Water.getLiquid(1500)).ii(INT_CIRCUITS.get(1)).fo(DilutedHydrochloricAcid.getLiquid(1000), HypochlorousAcid.getLiquid(1500)).add("hypochlorous_acid_1", 60, 30);
        //EPICHLOROHYDRIN
        CHEMICAL_REACTING.RB().fi(AllylChloride.getLiquid(3000), HypochlorousAcid.getLiquid(1000)).ii(DUST.getMaterialIngredient(SodiumHydroxide, 1)).fo(Epichlorohydrin.getLiquid(4000),SaltWater.getLiquid(1000)).add("epichlorohydrin", 160, 30);
        CHEMICAL_REACTING.RB().fi(Glycerol.getLiquid(7000), HydrochloricAcid.getLiquid(1000)).fo(Water.getLiquid(3000), Epichlorohydrin.getLiquid(5000)).add("epichlorohydrin_1", 240, 30);
        CHEMICAL_REACTING.RB().fi(LPG.getGas(432), Chlorine.getGas(1000)).ii(DUST.getMaterialIngredient(Carbon, 1)).fo(Epichlorohydrin.getLiquid(432)).add("epichlorohydrin_2", 480, 30);
        //PHOSPHORIC ACID
        CHEMICAL_REACTING.RB().fi(Water.getLiquid(9000)).ii(DUST.getMaterialIngredient(PhosphorousPentoxide, 7)).fo(PhosphoricAcid.getLiquid(16000)).add("phosphoric_acid", 120, 30);
        CHEMICAL_REACTING.RB().fi(Water.getLiquid(4000), Oxygen.getGas(2500)).ii(DUST.getMaterialIngredient(Phosphorus, 1)).fo(PhosphoricAcid.getLiquid(8000)).add("phosphoric_acid_1", 320, 30);
        //CHEMICAL_REACTING.RB().fi(Water.getLiquid(10000), SulfuricAcid.getLiquid(35000)).ii(DUST.getMaterialIngredient(Apatite, 21)).fo(PhosphoricAcid.getLiquid(24000), HydrochloricAcid.getLiquid(2000)).io(DUST.get(Gypsum, 40)).add("phosphoric_acid_2", 320, 30);
        //CUMENE
        CHEMICAL_REACTING.RB().fi(Benzene.getLiquid(8000), Propene.getGas(6000), PhosphoricAcid.getLiquid(1000)).fo(Cumene.getLiquid(14000)).add("cumene", 360, 30);
        //PHENOL
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(2000), Cumene.getLiquid(11000)).fo(Phenol.getLiquid(6000), Acetone.getLiquid(6000)).add("phenol", 160, 30);
        CHEMICAL_REACTING.RB().fi(Water.getLiquid(3000), Chlorine.getGas(2000), Benzene.getLiquid(12000)).fo(Phenol.getLiquid(13000), HydrochloricAcid.getLiquid(2000), DilutedHydrochloricAcid.getLiquid(2000)).add("phenol_1", 560, 30);
        CHEMICAL_REACTING.RB().fi(Chlorobenzene.getLiquid(12000), Water.getLiquid(3000)).fo(Phenol.getLiquid(13000), DilutedHydrochloricAcid.getLiquid(2000)).add("phenol_2", 240, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(2000), PhosphoricAcid.getLiquid(1000), Benzene.getLiquid(12000), Propene.getGas(9000)).fo(Phenol.getLiquid(13000), Acetone.getLiquid(10000)).add("phenol_3", 480, 30);
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(2000), Benzene.getLiquid(12000)).ii(DUST.getMaterialIngredient(SodiumHydroxide, 1)).fo(Phenol.getLiquid(13000), HydrochloricAcid.getLiquid(2000)).add("phenol_4", 560, 30);
        CHEMICAL_REACTING.RB().fi(Chlorobenzene.getLiquid(4000)).ii(DUST.getMaterialIngredient(SodiumHydroxide, 1)).fo(Phenol.getLiquid(4000)).add("phenol_5", 960, 30);
        //BISPHENOL A
        CHEMICAL_REACTING.RB().fi(Acetone.getLiquid(1000), Phenol.getLiquid(2000)).fo(BisphenolA.getLiquid(3000)).add("bisphenol_a", 160, 30);
        //EPOXY RESIN
        CHEMICAL_REACTING.RB().fi(BisphenolA.getLiquid(12000), Epichlorohydrin.getLiquid(4000)).ii(DUST.getMaterialIngredient(SodiumHydroxide, 1)).fo(EpoxyResin.getLiquid(16000), SaltWater.getLiquid(1000)).add("epoxy_resin", 200, 30);
        //CHLOROMETHANE
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(2000), Methane.getGas(5000)).ii(INT_CIRCUITS.get(1)).fo(Chloromethane.getGas(5000), HydrochloricAcid.getLiquid(2000)).add("chloromethane", 80, 30);
        CHEMICAL_REACTING.RB().fi(Methanol.getLiquid(6000), HydrochloricAcid.getLiquid(2000)).fo(Chloromethane.getGas(5000), Water.getLiquid(3000)).add("chloromethane_1",160, 30);
        //ALLYL CHLORIDE
        CHEMICAL_REACTING.RB().fi(Propene.getGas(9000), Chlorine.getGas(2000)).fo(AllylChloride.getLiquid(9000), HydrochloricAcid.getLiquid(2000)).add("hydrochloric_acid_5", 160, 30);
        CHEMICAL_REACTING.RB().fi(HydrogenFluoride.getGas(1000), Water.getLiquid(1000)).fo(HydrofluoricAcid.getLiquid(2000)).add("hydrofluoric_acid", 60, 8);
        //TETRAFLUORETHYLENE
        CHEMICAL_REACTING.RB().fi(Chloroform.getLiquid(5000), HydrofluoricAcid.getLiquid(4000)).fo(Tetrafluoroethylene.getGas(3000), DilutedHydrochloricAcid.getLiquid(6000)).add("tetrafluoroethylene", 480, 240);
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(6000), Methane.getGas(5000), HydrofluoricAcid.getLiquid(4000)).fo(Tetrafluoroethylene.getGas(6000), HydrochloricAcid.getLiquid(6000), DilutedHydrochloricAcid.getLiquid(6000)).add("tetrafluoroethylene_1", 540, 240);
        CHEMICAL_REACTING.RB().fi(Chloromethane.getGas(10000)).ii(DUST.getMaterialIngredient(Silicon, 1)).fo(Dimethyldichlorosilane.getLiquid(11000)).add("dimethyldichlorosilane", 240, 96);
        CHEMICAL_REACTING.RB().fi(Dimethyldichlorosilane.getLiquid(11000), Water.getLiquid(3000)).fo(DilutedHydrochloricAcid.getLiquid(4000)).io(DUST.get(Polydimethylsiloxane, 10)).add("polydimethylsiloxane", 240, 96);
        CHEMICAL_REACTING.RB().fi(HydrochloricAcid.getLiquid(4000), Methanol.getLiquid(12000)).ii(DUST.getMaterialIngredient(Silicon, 1)).io(DUST.get(Polydimethylsiloxane, 10)).fo(DilutedHydrochloricAcid.getLiquid(4000)).add("polydimethylsiloxane_2", 480, 96);
        CHEMICAL_REACTING.RB().fi(Ammonia.getGas(2000), Methanol.getLiquid(6000)).fo(Dimethylamine.getGas(5000), Water.getLiquid(3000)).add("dimethylamine", 240, 120);
        CHEMICAL_REACTING.RB().fi(Ammonia.getGas(4000), HypochlorousAcid.getLiquid(3000)).fo(Chloramine.getLiquid(4000), Water.getLiquid(3000)).add("chloramine", 160, 30);
        CHEMICAL_REACTING.RB().fi(Dimethylamine.getGas(5000), Chloramine.getLiquid(2000)).fo(Dimethylhydrazine.getLiquid(6000), DilutedHydrochloricAcid.getLiquid(1000)).add("dimethylhydrazine", 960, 480);
        CHEMICAL_REACTING.RB().fi(Methanol.getLiquid(12000), Ammonia.getGas(8000), HypochlorousAcid.getLiquid(3000)).fo(Dimethylhydrazine.getLiquid(12000), DilutedHydrochloricAcid.getLiquid(2000), Water.getLiquid(9000)).add("dimethylhydrazine_2", 1040, 480);
        CHEMICAL_REACTING.RB().fi(NitrogenDioxide.getGas(1000)).ii(INT_CIRCUITS.get(2)).fo(DinitrogenTetroxide.getGas(1000)).add("dinitrogen_tetroxide", 640, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(7000), Ammonia.getGas(8000)).fo(DinitrogenTetroxide.getGas(6000), Water.getLiquid(9000)).add("dinitrogen_tetroxide_1", 480, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(7000), Hydrogen.getGas(6000), Nitrogen.getGas(2000)).fo(DinitrogenTetroxide.getGas(6000), Water.getLiquid(9000)).add("dinitrogen_tetroxide_2", 1100, 480);
        CHEMICAL_REACTING.RB().fi(Ammonia.getGas(8000), Oxygen.getGas(5000)).fo(NitricOxide.getGas(4000), Water.getLiquid(9000)).add("nitric_oxide", 160, 30);
        CHEMICAL_REACTING.RB().fi(NitricOxide.getGas(2000), Oxygen.getGas(1000)).fo(NitrogenDioxide.getGas(3000)).add("nitrogen_dioxide", 160, 30);
        CHEMICAL_REACTING.RB().fi(NitrogenDioxide.getGas(9000), Water.getLiquid(3000)).fo(NitricAcid.getLiquid(10000), NitricOxide.getGas(2000)).add("nitric_acid", 240, 30);
        CHEMICAL_REACTING.RB().fi(NitrogenDioxide.getGas(6000), Water.getLiquid(3000), Oxygen.getGas(1000)).fo(NitricAcid.getLiquid(10000)).add("nitric_acid_2", 240, 30);
        CHEMICAL_REACTING.RB().fi(Ethylene.getGas(3000), Chlorine.getGas(1000)).fo(VinylChloride.getGas(3000), HydrochloricAcid.getLiquid(1000)).add("vinyl_chloride", 80, 30);
        CHEMICAL_REACTING.RB().fi(Ethylene.getGas(6000), Oxygen.getGas(1000), HydrochloricAcid.getLiquid(4000)).fo(VinylChloride.getGas(6000)).add("vinyl_chloride_1", 80, 30);
        CHEMICAL_REACTING.RB().fi(AceticAcid.getLiquid(8000), SulfuricAcid.getLiquid(7000)).fo(DilutedSulfuricAcid.getLiquid(10000), Ethenone.getGas(5000)).add("diluted_sulfuric_acid", 160, 120);
        CHEMICAL_REACTING.RB().fi(NitricAcid.getLiquid(8000), Ethenone.getGas(1000)).fo(Tetranitromethane.getLiquid(9000)).add("tetranitromethane", 480, 16);
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(1000), Benzene.getLiquid(3000)).fo(Dichlorobenzene.getLiquid(3000), HydrochloricAcid.getLiquid(1000)).add("dichlorobenzene", 240, 30);
        CHEMICAL_REACTING.RB().fi(Dichlorobenzene.getLiquid(1000), Oxygen.getGas(8000)).ii(DUST.getMaterialIngredient(SodiumSulfide, 1)).fo(PolyphenyleneSulfide.getLiquid(1000)).io(DUST.get(Salt)).add("polyphenol_sulfide", 240, 360);
        CHEMICAL_REACTING.RB().fi(SulfuricAcid.getLiquid(7000)).ii(DUST.getMaterialIngredient(Salt, 2)).io(DUST.get(SodiumBisulfate, 7)).fo(HydrochloricAcid.getLiquid(2000)).add("sodium_bisulfate_1", 60, 30);
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(1000), Benzene.getLiquid(6000)).fo(HydrochloricAcid.getLiquid(1000), Chlorobenzene.getLiquid(6000)).add("chlorobenzene_1", 240, 30);
    }

    private static void addPolymerRecipe(Material in, Material out){
        CHEMICAL_REACTING.RB().fi(in.getLiquid(144), Oxygen.getGas(1000)).fo(out.getLiquid(216)).add(in.getId() + "_polymer_to_" + out.getId(), 160, 30);
    }
    private static void nuclearProcessing() {

        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Uraninite, 1)).fi(HydrogenFluoride.getGas(8000)).io(DUST.get(UraniumTetrafluoride, 5)).fo(Water.getLiquid(6000)).add("uranium_tetrafluoride", 186, 16);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(UraniumTetrafluoride, 1)).fi(Fluorine.getGas(400)).fo(UraniumHexafluoride.getGas(1400)).add("uranium_hexafluoride", 22, 16);
        CHEMICAL_REACTING.RB().fi(Uranium238Hexafluoride.getGas(1400), Hydrogen.getGas(400)).io(DUST.get(Uranium238Tetrafluoride)).fo(HydrogenFluoride.getGas(800)).add("uranium_238_tetrafluoride", 28, 16);
        CHEMICAL_REACTING.RB().fi(Uranium235Hexafluoride.getGas(1400), Hydrogen.getGas(400)).io(DUST.get(Uranium235Tetrafluoride)).fo(HydrogenFluoride.getGas(800)).add("uranium_235_tetrafluoride", 28, 16);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Uranium235Tetrafluoride, 5), DUST.getMaterialIngredient(Calcium, 2)).io(DUST.get(Uranium235, 1), DUST.get(Fluorite, 6)).add("uranium_235_tetrafluoride_to_uranium_235", 108, 16);
        CHEMICAL_REACTING.RB().ii(DUST.getMaterialIngredient(Uranium238Tetrafluoride, 5), DUST.getMaterialIngredient(Calcium, 2)).io(DUST.get(Uranium, 1), DUST.get(Fluorite, 6)).add("uranium_238_tetrafluoride_to_uranium_238", 108, 16);

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

