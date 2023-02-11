package muramasa.gregtech.data;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.event.MaterialEvent;
import muramasa.antimatter.material.FluidProduct;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.material.SubTag;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.data.AntimatterMaterials.Charcoal;
import static muramasa.antimatter.data.AntimatterMaterials.Wood;
import static muramasa.antimatter.material.MaterialTags.CHEMBATH_MERCURY;
import static muramasa.antimatter.material.MaterialTags.CHEMBATH_PERSULFATE;
import static muramasa.gregtech.data.Materials.*;
import static net.minecraft.data.loot.BlockLoot.applyExplosionDecay;
import static net.minecraft.data.loot.BlockLoot.createSilkTouchDispatchTable;

public class GregTechMaterialEvents {


    public static void onMaterialEvent(MaterialEvent event){
        flags(event);
        antimatterMaterials(event);
        byproducts(event);
        MaterialTags.CUSTOM_ORE_DROPS.add(AntimatterMaterials.Lapis, b -> createSilkTouchDispatchTable(b, applyExplosionDecay(b, LootItem.lootTableItem(Items.LAPIS_LAZULI).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 9.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));

        event.setMaterial(Aluminium).asMetal(933, 1700, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.SCREW, AntimatterMaterialTypes.BOLT, AntimatterMaterialTypes.RING, AntimatterMaterialTypes.GEAR, AntimatterMaterialTypes.FRAME, AntimatterMaterialTypes.GEAR_SMALL, AntimatterMaterialTypes.FOIL).asOre()
                .addTools(1.5F, 10.0F, 140, 2);
        event.setMaterial(Beryllium).asOre().addTools(2.0F, 14.0F, 64, 2);
        event.setMaterial(Bismuth).asOre();
        event.setMaterial(Carbon).asSolid(); // TODO: Tools,
        // Carbon
        // Fluid? Removed Tool;
        event.setMaterial(Chrome).asMetal(2180, 1700, AntimatterMaterialTypes.SCREW, AntimatterMaterialTypes.BOLT, AntimatterMaterialTypes.RING, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROTOR).addTools(2.5F, 11.0F, 256, 3);
        event.setMaterial(Cobalt).asMetal(1768, 0).asOre()
                .addTools(3.0F, 8.0F, 512, 3);
        event.setMaterial(Iridium).asMetal(2719, 2719, AntimatterMaterialTypes.FRAME, AntimatterMaterialTypes.PLATE)
                .asOre().addTools(5.0F, 8.0F, 2560, 4);
        event.setMaterial(Lanthanum).asSolid(1193, 1193);
        event.setMaterial(Lead).asMetal(600, 0, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.PLATE_DENSE, AntimatterMaterialTypes.FOIL, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.FRAME, AntimatterMaterialTypes.BOLT).asOre();
        event.setMaterial(Manganese).asMetal(1519, 0).asOre();
        event.setMaterial(Molybdenum).asMetal(2896, 0).asOre()
                .addTools(2.0F, 7.0F, 512, 2);
        event.setMaterial(Neodymium).asMetal(1297, 1297, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD).asOre(); // TODO: Bastnasite or Monazite for Ore For;
        event.setMaterial(Neutronium).asMetal(10000, 10000, AntimatterMaterialTypes.SCREW, AntimatterMaterialTypes.BOLT, AntimatterMaterialTypes.RING, AntimatterMaterialTypes.GEAR, AntimatterMaterialTypes.FRAME).addTools(9.0F, 24.0F, 655360, 6); // TODO Vibraniu;
        event.setMaterial(Nickel).asMetal(1728, 0, AntimatterMaterialTypes.PLATE)
                .asOre().asPlasma();
        event.setMaterial(Osmium).asMetal(3306, 3306, AntimatterMaterialTypes.SCREW, AntimatterMaterialTypes.BOLT, AntimatterMaterialTypes.RING, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.FOIL, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.WIRE_FINE).addTools(4.0F, 16.0F, 1080, 4);
        event.setMaterial(Palladium).asMetal(1828, 1828)
                .asOre().addTools(3.0F, 10.0F, 420, 2);
        event.setMaterial(Platinum).asMetal(2041, 0, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.FOIL, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.WIRE_FINE).asOre().addTools(4.5F, 18.0F, 48, 2);
        event.setMaterial(Plutonium).asMetal(912, 0)
                .addTools(2.5F, 6.0F, 280, 3, of(Enchantments.FIRE_ASPECT, 2)); // TODO: Enchantment: Radioactivit;
        event.setMaterial(Plutonium241).asMetal(912, 0)
                .addTools(2.5F, 6.0F, 280, 3);
        event.setMaterial(Silver).asMetal(1234, 0, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.SCREW)
                .asOre();
        event.setMaterial(Thorium).asMetal(2115, 0, AntimatterMaterialTypes.PLATE_DENSE).asOre()
                .addTools(1.5F, 6.0F, 512, 2);
        event.setMaterial(Titanium).asMetal(1941, 1940, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.SCREW, AntimatterMaterialTypes.BOLT, AntimatterMaterialTypes.RING, AntimatterMaterialTypes.GEAR, AntimatterMaterialTypes.FRAME, AntimatterMaterialTypes.GEAR_SMALL, AntimatterMaterialTypes.ROTOR).asOre()
                .addTools(2.5F, 7.0F, 1600, 3);
        event.setMaterial(Tungsten).asMetal(3695, 3000, AntimatterMaterialTypes.FOIL)
                .addTools(2.0F, 6.0F, 512, 3); // Tungstensteel would be the one with tool;
        event.setMaterial(Uranium).asMetal(1405, 0)
                .asOre();
        event.setMaterial(Uranium235).asMetal(1405, 0)
                .addTools(3.0F, 6.0F, 512, 3);
        event.setMaterial(Graphite).asDust().asOre();
        event.setMaterial(Americium).asMetal(1149, 0); // TODO:
        // When
        // we're
        // thinking
        // about
        // fusio;
        event.setMaterial(Antimony).asMetal(1449, 0);
        event.setMaterial(Argon).asGas();
        event.setMaterial(Arsenic).asSolid();
        event.setMaterial(Barium).asDust(1000);
        event.setMaterial(Boron).asDust(2349);
        event.setMaterial(Caesium).asMetal(2349, 0);
        event.setMaterial(Calcium).asDust(1115);
        event.setMaterial(Cadmium).asDust(594);
        event.setMaterial(Cerium).asSolid(1068, 1068);
        event.setMaterial(Chlorine).asGas();
        event.setMaterial(Deuterium).asGas();
        event.setMaterial(Dysprosium).asMetal(1680, 1680);
        event.setMaterial(Europium).asMetal(1099, 1099);
        event.setMaterial(Fluorine).asGas();
        event.setMaterial(Gallium).asMetal(302, 0);
        event.setMaterial(Hydrogen).asGas();
        event.setMaterial(Helium).asPlasma();
        event.setMaterial(Helium3).asGas();
        event.setMaterial(Indium).asSolid(429, 0);
        event.setMaterial(Lithium).asSolid(454, 0).asOre();
        event.setMaterial(Lutetium).asMetal(1925, 1925);
        event.setMaterial(Magnesium).asMetal(923, 0);
        event.setMaterial(Mercury).asFluid();
        event.setMaterial(Neon).asPlasma();
        event.setMaterial(Niobium).asMetal(2750, 2750, AntimatterMaterialTypes.GEAR);
        event.setMaterial(Nitrogen).asPlasma();
        event.setMaterial(Oxygen).asPlasma();
        event.setMaterial(Phosphor).asDust(317);
        event.setMaterial(Potassium).asSolid(336, 0);
        event.setMaterial(Radon).asGas();
        event.setMaterial(Silicon).asMetal(1687, 1687, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.FOIL);
        event.setMaterial(Sodium).asDust(370);
        event.setMaterial(Sulfur).asDust(388).asOre().asPlasma();
        event.setMaterial(Tantalum).asSolid(3290, 0);
        event.setMaterial(Tin).asMetal(505, 505, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.SCREW, AntimatterMaterialTypes.BOLT, AntimatterMaterialTypes.RING, AntimatterMaterialTypes.GEAR, AntimatterMaterialTypes.FOIL, AntimatterMaterialTypes.WIRE_FINE, AntimatterMaterialTypes.FRAME, AntimatterMaterialTypes.ROTOR).asOre();
        event.setMaterial(Tritium).asGas();
        event.setMaterial(Vanadium).asMetal(2183, 2183);
        event.setMaterial(Xenon).asPlasma();
        event.setMaterial(Yttrium).asMetal(1799, 1799);
        event.setMaterial(Zinc).asMetal(692, 0, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.FOIL)
                .asOre();

        // TODO: We can be more lenient about what fluids we have in, its not as bad as
        // solids above, and we can stop them from showing in JEI (I think...)

        /**
         * Gases
         **/
        event.setMaterial(WoodGas)
                .asGas(24,0,true,new FluidProduct[]{
                        new FluidProduct(CarbonDioxide,"gas",720),
                        new FluidProduct(CarbonMonoxide,"gas",360),
                        new FluidProduct(Ethylene,"gas",90),
                        new FluidProduct(Methane,"gas",180),
                        new FluidProduct(Hydrogen,"gas",90)
                }, 1440).mats(of(CarbonDioxide,8,CarbonMonoxide,4,Methane,2,Ethylene,1,Hydrogen,1));
        event.setMaterial(Methane).asGas(104)
                .mats(of(Carbon, 1, Hydrogen, 4));
        event.setMaterial(CarbonDioxide).asGas()
                .mats(of(Carbon, 1, Oxygen, 2));
        event.setMaterial(Air).asGas().mats(of(Nitrogen, 40, Oxygen, 11, Argon, 1, NobleGases, 1 ));
        event.setMaterial(NitrogenDioxide).asGas()
                .mats(of(Nitrogen, 1, Oxygen, 2));
        event.setMaterial(NaturalGas).asGas(15)
                .mats(of(Methane,4,Ethane,2,Propane,2,Butane,1));
        event.setMaterial(SulfuricGas).asGas(20)
                .mats(of(NaturalGas,1,Sulfur,1));
        event.setMaterial(RefineryGas).asGas(128);
        event.setMaterial(LPG).asGas(256);
        event.setMaterial(Ethane).asGas(168)
                .mats(of(Carbon, 2, Hydrogen, 6));
        event.setMaterial(Propane).asGas(232)
                .mats(of(Carbon, 2, Hydrogen, 6));
        event.setMaterial(Butane).asGas(296)
                .mats(of(Carbon, 4, Hydrogen, 10));
        event.setMaterial(Butene).asGas(256)
                .mats(of(Carbon, 4, Hydrogen, 8));
        event.setMaterial(Butadiene).asGas(206)
                .mats(of(Carbon, 4, Hydrogen, 6));
        event.setMaterial(VinylChloride).asGas()
                .mats(of(Carbon, 2, Hydrogen, 3, Chlorine, 1));
        event.setMaterial(SulfurDioxide).asGas()
                .mats(of(Sulfur, 1, Oxygen, 2));
        event.setMaterial(SulfurTrioxide).asGas(0,344)
                .mats(of(Sulfur, 1, Oxygen, 3));
        event.setMaterial(Dimethylamine).asGas()
                .mats(of(Carbon, 2, Hydrogen, 7, Nitrogen, 1));
        event.setMaterial(DinitrogenTetroxide).asGas()
                .mats(of(Nitrogen, 2, Oxygen, 4));
        event.setMaterial(NitricOxide).asGas()
                .mats(of(Nitrogen, 1, Oxygen, 1));
        event.setMaterial(Ammonia).asGas()
                .mats(of(Nitrogen, 1, Hydrogen, 3));
        event.setMaterial(Chloromethane).asGas()
                .mats(of(Carbon, 1, Hydrogen, 3, Chlorine, 1));
        event.setMaterial(Tetrafluoroethylene).asGas()
                .mats(of(Carbon, 2, Fluorine, 4));
        event.setMaterial(CarbonMonoxide).asGas(24)
                .mats(of(Carbon, 1, Oxygen, 1));
        event.setMaterial(Ethylene).asGas(128)
                .mats(of(Carbon, 2, Hydrogen, 4));
        event.setMaterial(Propene).asGas(192)
                .mats(of(Carbon, 3, Hydrogen, 6));
        event.setMaterial(Ethenone).asGas()
                .mats(of(Carbon, 2, Hydrogen, 2, Oxygen, 1));
        event.setMaterial(HydrogenSulfide).asGas()
                .mats(of(Hydrogen, 2, Sulfur, 1));
        event.setMaterial(NobleGases).asGas(0,790)
                .mats(of(Helium, 10, Neon, 10, Argon, 10, Xenon, 10));

        /**
         * Fluids
         **/
        event.setMaterial(Benzoylchloride).asFluid().mats(of(Carbon,7,Hydrogen,5,Chlorine,1,Oxygen,1));
        event.setMaterial(Benzaldehyde).asFluid().mats(of(Carbon,7,Hydrogen,6,Oxygen,1));
        event.setMaterial(HydrogenPeroxide).asFluid().mats(of(Hydrogen,2,Oxygen,2));
        event.setMaterial(Dichloroethane).asFluid().mats(of(Carbon,2,Hydrogen,4,Chlorine,2));
        event.setMaterial(PeroxydisulfuricAcid).asFluid().mats(of(Sulfur,2,Hydrogen,2,Oxygen,8));
        event.setMaterial(Steam).asGas(1, 395)
                .mats(of(AntimatterMaterials.Water,1));
        event.setMaterial(SaltWater).asFluid()
                .mats(of(AntimatterMaterials.Water,1,Sodium,1,Chlorine,1));;
        event.setMaterial(UUAmplifier).asFluid();
        event.setMaterial(UUMatter).asFluid();
        event.setMaterial(Antimatter).asFluid();
        event.setMaterial(CharcoalByproducts).asFluid(0,0,true,new FluidProduct[]{
                new FluidProduct(WoodTar,"fluid",360),
                new FluidProduct(WoodVinegar,"fluid",720),
                new FluidProduct(WoodGas,"gas",360)
        },1440).mats(of(WoodTar,1,WoodGas,1,WoodVinegar,2));
        event.setMaterial(Glue).asFluid();
        event.setMaterial(Honey).asFluid(); // TODO: Only when Forestry's present;
        event.setMaterial(Lubricant).asFluid();
        event.setMaterial(WoodTar)
                .asFluid(0,0,true,new FluidProduct[]{
                        new FluidProduct(Creosote,"fluid",720),
                        new FluidProduct(Phenol,"fluid",180),
                        new FluidProduct(Benzene,"fluid",360),
                        new FluidProduct(Toluene,"fluid",180)
                },1440).mats(of(Creosote,4,Phenol,1,Benzene,2,Toluene,1));
        event.setMaterial(WoodVinegar).asFluid(0,0,true,new FluidProduct[]{
                new FluidProduct(AceticAcid,"fluid",180),
                new FluidProduct(AntimatterMaterials.Water,"fluid",720),
                new FluidProduct(Ethanol,"fluid",90),
                new FluidProduct(Methanol,"fluid",360),
                new FluidProduct(Acetone,"fluid",45),
                new FluidProduct(MethylAcetate,"fluid",45)
        },1440).mats(of(MethylAcetate,1,Acetone,1,Ethanol,2,AceticAcid,4,Methanol,8, AntimatterMaterials.Water,16));
        event.setMaterial(LiquidAir).asFluid(0, 79)
                .mats(of(Nitrogen, 40, Oxygen, 11, Argon, 1, NobleGases, 1 )); // TODO Rrename to
        // liquid
        // oxygen <- Nope, add
        // fluid to Oxyge;
        event.setMaterial(DistilledWater).asFluid()
                .mats(of(AntimatterMaterials.Water,1));
        event.setMaterial(GlycerylTrinitrate).asFluid()
                .mats(of(Carbon, 3, Hydrogen, 5, Nitrogen, 3, Oxygen, 9));
        event.setMaterial(Titaniumtetrachloride).asFluid()
                .mats(of(Titanium, 1, Chlorine, 4));
        event.setMaterial(SodiumPersulfate).asFluid()
                .mats(of(Sodium, 2, Sulfur, 2, Oxygen, 8));
        event.setMaterial(DilutedHydrochloricAcid)
                .asFluid(0,0,true,new FluidProduct[]{
                        new FluidProduct(HydrochloricAcid,"fluid",144),
                        new FluidProduct(AntimatterMaterials.Water,"fluid",144)
                },288)
                .mats(of(Hydrogen, 1, Chlorine, 1));
        event.setMaterial(SulfuricAcid).asFluid()
                .mats(of(Hydrogen, 2, Sulfur, 1, Oxygen, 4));
        event.setMaterial(NitricAcid).asFluid()
                .mats(of(Hydrogen, 1, Nitrogen, 1, Oxygen, 3));
        event.setMaterial(NitrationMixture).asFluid()
                .mats(of(SulfuricAcid,1,NitricAcid,1));
        event.setMaterial(Dichlorobenzene).asFluid()
                .mats(of(Carbon, 6, Hydrogen, 4, Chlorine, 2));
        event.setMaterial(Styrene).asFluid()
                .mats(of(Carbon, 8, Hydrogen, 8));
        event.setMaterial(Isoprene).asFluid()
                .mats(of(Carbon, 8, Hydrogen, 8));
        event.setMaterial(Tetranitromethane).asFluid()
                .mats(of(Carbon, 1, Nitrogen, 4, Oxygen, 8));
        event.setMaterial(Epichlorohydrin).asFluid()
                .mats(of(Carbon, 3, Hydrogen, 5, Chlorine, 1, Oxygen, 1));
        event.setMaterial(Dimethylhydrazine).asFluid()
                .mats(of(Carbon, 2, Hydrogen, 8, Nitrogen, 2));
        event.setMaterial(Chloramine).asFluid()
                .mats(of(Nitrogen, 1, Hydrogen, 2, Chlorine, 1));
        event.setMaterial(Dimethyldichlorosilane).asFluid()
                .mats(of(Carbon, 2, Hydrogen, 6, Chlorine, 2, Silicon, 1));
        event.setMaterial(HydrofluoricAcid).asFluid()
                .mats(of(Hydrogen, 1, Fluorine, 1));
        event.setMaterial(Chloroform).asFluid()
                .mats(of(Carbon, 1, Hydrogen, 1, Chlorine, 3));
        event.setMaterial(BisphenolA).asFluid()
                .mats(of(Carbon, 15, Hydrogen, 16, Oxygen, 2));
        event.setMaterial(AceticAcid).asFluid()
                .mats(of(Carbon, 2, Hydrogen, 4, Oxygen, 2));
        // event.setMaterial(CalciumAcetateSolution).asFluid().addComposition(of(Calcium, 1, Carbon, 2, Oxygen, 4,
        // Hydrogen, 6);
        event.setMaterial(Acetone)
                .asFluid(0,0,true,new FluidProduct[]{
                        new FluidProduct(Ethenone,"gas",144),
                        new FluidProduct(Methane,"gas",144)
                },288)
                .mats(of(Carbon, 3, Hydrogen, 6, Oxygen, 1));
        event.setMaterial(Methanol).asFluid(84)
                .mats(of(Carbon, 1, Hydrogen, 4, Oxygen, 1));
        event.setMaterial(VinylAcetate).asFluid()
                .mats(of(Carbon, 4, Hydrogen, 6, Oxygen, 2));
        event.setMaterial(PolyvinylAcetate).asFluid()
                .mats(of(Carbon, 4, Hydrogen, 6, Oxygen, 2));
        event.setMaterial(MethylAcetate).asFluid()
                .mats(of(Carbon, 3, Hydrogen, 6, Oxygen, 2));
        event.setMaterial(AllylChloride).asFluid()
                .mats(of(Carbon, 3, Hydrogen, 5, Chlorine, 1));
        event.setMaterial(HydrochloricAcid).asFluid()
                .mats(of(Hydrogen, 1, Chlorine, 1));
        event.setMaterial(HypochlorousAcid).asFluid()
                .mats(of(Hydrogen, 1, Chlorine, 1, Oxygen, 1));
        event.setMaterial(Cumene).asFluid()
                .mats(of(Carbon, 9, Hydrogen, 12));
        event.setMaterial(PhosphoricAcid).asFluid()
                .mats(of(Hydrogen, 3, Phosphor, 1, Oxygen, 4));
        event.setMaterial(SulfuricTrioxide).asGas()
                .mats(of(Sulfur, 1, Oxygen, 3));
        event.setMaterial(SulfuricDioxide).asGas()
                .mats(of(Sulfur, 1, Oxygen, 2));
        event.setMaterial(DilutedSulfuricAcid).asFluid()
                .asFluid(0,0,true, new FluidProduct[]{
                        new FluidProduct(SulfuricAcid,"fluid",144),
                        new FluidProduct(AntimatterMaterials.Water,"fluid",144)
                },288)
                .mats(of(SulfuricAcid, 1));
        event.setMaterial(Benzene).asFluid(288)
                .mats(of(Carbon, 6, Hydrogen, 6));
        event.setMaterial(Phenol).asFluid(288)
                .mats(of(Carbon, 6, Hydrogen, 6, Oxygen, 1));
        event.setMaterial(Toluene).asFluid(328)
                .mats(of(Carbon, 7, Hydrogen, 8));
        event.setMaterial(SulfuricNaphtha).asFluid(32)
                .mats(of(SulfuricAcid, 1, AntimatterMaterials.Water,1));
        event.setMaterial(Naphtha).asFluid(256)
                .mats(of(Carbon,8,Hydrogen,16));
        event.setMaterial(DrillingFluid).asFluid(); // TODO:
        // Perhaps for
        // a bedrock drill;
        event.setMaterial(BlueVitriol).asFluid();
        event.setMaterial(IndiumConcentrate).asFluid();
        event.setMaterial(NickelSulfate).asFluid()
                .mats(of(Nickel,1,Sulfur,1,Oxygen,4));
        event.setMaterial(RocketFuel).asFluid();
        event.setMaterial(LeadZincSolution).asFluid();

        /**
         * Fuels
         **/
        event.setMaterial(Diesel).asFluid(128);
        event.setMaterial(NitroFuel).asFluid(512);
        event.setMaterial(BioDiesel).asFluid(192);
        event.setMaterial(Biomass).asFluid(8);
        event.setMaterial(FermentedBiomass).asFluid(16,0,true,new FluidProduct[]{
                new FluidProduct(AceticAcid,"fluid",25),
                new FluidProduct(AntimatterMaterials.Water,"fluid",395),
                new FluidProduct(Ethanol,"fluid",250),
                new FluidProduct(Methanol,"fluid",250),
                new FluidProduct(Ammonia,"gas",100),
                new FluidProduct(Methane,"gas",600),
                new FluidProduct(CarbonDioxide,"gas",400)
        },2000);
        event.setMaterial(Ethanol).asFluid(148)
                .mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 1));
        event.setMaterial(Ethanediol).asFluid(216)
                .mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 2));
        event.setMaterial(Propanol).asFluid(196)
                .mats(of(Carbon, 3, Hydrogen, 8, Oxygen, 1));
        event.setMaterial(Ethenol).asFluid(120)
                .mats(of(Carbon, 2, Hydrogen, 4, Oxygen, 1));
        event.setMaterial(Propanediol).asFluid(256)
                .mats(of(Carbon, 3, Hydrogen, 8, Oxygen, 2));
        event.setMaterial(Propenol).asFluid(196)
                .mats(of(Carbon, 3, Hydrogen, 6, Oxygen, 1));
        event.setMaterial(Butanol).asFluid(166)
                .mats(of(Carbon, 4, Hydrogen, 10, Oxygen, 1));
        event.setMaterial(Butenol).asFluid(186)
                .mats(of(Carbon, 4, Hydrogen, 8, Oxygen, 1));
        event.setMaterial(Butanediol).asFluid(286)
                .mats(of(Carbon, 4, Hydrogen, 10, Oxygen, 2));
        event.setMaterial(Heptanol).asFluid(226)
                .mats(of(Carbon, 7, Hydrogen, 16, Oxygen, 1));
        event.setMaterial(Creosote)
                .asFluid(8,0,true,new FluidProduct[]{new FluidProduct(Lubricant,"fluid",144)},288);
        event.setMaterial(FishOil).asFluid(2);
        event.setMaterial(Oil).asFluid(16,0,true,
                new FluidProduct[]{
                        new FluidProduct(SulfuricLightFuel,"fluid",50),
                        new FluidProduct(SulfuricHeavyFuel,"fluid",15),
                        new FluidProduct(Naphtha,"fluid",20),
                        new FluidProduct(SulfuricGas,"gas",60)
                },290);
        event.setMaterial(SeedOil).asFluid(2,0,true,new FluidProduct[]{
                new FluidProduct(Lubricant,"fluid",10)
        },20);
        // event.setMaterial(SeedOilHemp).asSemi(2;
        // event.setMaterial(SeedOilLin).asSemi(2;
        // event.setMaterial(OilExtraHeavy).asFluid(40);
        event.setMaterial(OilHeavy).asFluid(32,0,true,new FluidProduct[]{
                new FluidProduct(SulfuricLightFuel,"fluid",250),
                new FluidProduct(SulfuricHeavyFuel,"fluid",45),
                new FluidProduct(Naphtha,"fluid",15),
                new FluidProduct(SulfuricGas,"gas",600)
        },910);
        event.setMaterial(OilMedium).asFluid(24);
        event.setMaterial(OilLight).asFluid(16,0,true,new FluidProduct[]{
                new FluidProduct(SulfuricLightFuel,"fluid",10),
                new FluidProduct(SulfuricHeavyFuel,"fluid",20),
                new FluidProduct(Naphtha,"fluid",30),
                new FluidProduct(SulfuricGas,"gas",240)
        },300);
        event.setMaterial(SulfuricLightFuel).asFluid(32)
                .mats(of(LightFuel,1,Sulfur,1));
        event.setMaterial(SulfuricHeavyFuel).asFluid(32)
                .mats(of(HeavyFuel,1,Sulfur,1));
        event.setMaterial(LightFuel).asFluid(48);
        event.setMaterial(HeavyFuel).asFluid(48);
        event.setMaterial(Glyceryl).asFluid(164)
                .mats(of(Carbon, 3, Hydrogen, 8, Oxygen, 3));

        /**
         * Dusts
         **/
        event.setMaterial(Aluminiumtrichloride).asDust().mats(of(Aluminium,1,Chlorine,3));
        event.setMaterial(Dialuminiumtrioxide).asDust().mats(of(Aluminium,2,Oxygen,3));
        event.setMaterial(Dibenzene).asDust().mats(of(Carbon,12,Hydrogen,10));
        event.setMaterial(Dibenzoylperoxide).asDust().mats(of(Carbon,14,Hydrogen,10,Oxygen,4));
        event.setMaterial(ReactionCatalyst).asDust().mats(of(AntimatterMaterials.Copper,1,Zinc,1,Aluminium,2,Oxygen,4));
        event.setMaterial(WoodPulp).asDust();
        event.setMaterial(SodiumSulfide).asDust().mats(of(Sodium, 2, Sulfur, 1));
        event.setMaterial(TinAlloy).asDust().mats(of(Tin, 1, AntimatterMaterials.Iron, 1));
        event.setMaterial(Energium).asDust().mats(of(AntimatterMaterials.Redstone,1,Ruby,1));
        event.setMaterial(BorosilicateGlass).asDust().mats(of(Boron, 1, Silicon, 7, Oxygen,14));
        event.setMaterial(IridiumSodiumOxide).asDust().mats(of(Iridium,1,Sodium,1,Oxygen,2));
        event.setMaterial(IndiumGalliumPhosphide).asDust().mats(of(Indium, 1, Gallium, 1, Phosphor, 1));
        event.setMaterial(PlatinumGroupSludge).asDust();
        event.setMaterial(Graphene).asDust(AntimatterMaterialTypes.PLATE);
        event.setMaterial(Oilsands).asOre(true);
        event.setMaterial(RareEarth).asDust();
        event.setMaterial(Almandine).asOre()
                .mats(of(Aluminium, 2, AntimatterMaterials.Iron, 3, Silicon, 3, Oxygen, 12));
        event.setMaterial(Andradite).asOre()
                .mats(of(Calcium, 3, AntimatterMaterials.Iron, 2, Silicon, 3, Oxygen, 12));
        event.setMaterial(Ash).asDust();
        event.setMaterial(BandedIron).asOre(true)
                .mats(of(AntimatterMaterials.Iron, 2, Oxygen, 3));
        event.setMaterial(BrownLimonite).asOre(true)
                .mats(of(AntimatterMaterials.Iron, 1, Hydrogen, 1, Oxygen, 2));
        event.setMaterial(Calcite).asOre(true)
                .mats(of(Calcium, 1, Carbon, 1, Oxygen, 3));
        event.setMaterial(Cassiterite).asOre()
                .mats(of(Tin, 1, Oxygen, 2));
        event.setMaterial(Chalcopyrite).asOre()
                .mats(of(AntimatterMaterials.Copper, 1, AntimatterMaterials.Iron, 1, Sulfur, 2));
        event.setMaterial(Clay).asDust().mats(of(Sodium, 2, Lithium, 1, Aluminium, 2, Silicon, 2, AntimatterMaterials.Water, 6));
        event.setMaterial(Cobaltite).asOre(true)
                .mats(of(Cobalt, 1, Arsenic, 1, Sulfur, 1));
        event.setMaterial(Cooperite).asOre()
                .mats(of(Platinum, 3, Nickel, 1, Sulfur, 1, Palladium, 1));
        event.setMaterial(DarkAsh).asDust();
        event.setMaterial(Galena).asOre()
                .mats(of(Lead, 3, Silver, 3, Sulfur, 2));
        event.setMaterial(Garnierite).asOre()
                .mats(of(Nickel, 1, Oxygen, 1));
        event.setMaterial(Grossular).asOre()
                .mats(of(Calcium, 3, Aluminium, 2, Silicon, 3, Oxygen, 12));
        event.setMaterial(Ilmenite).asOre()
                .mats(of(AntimatterMaterials.Iron, 1, Titanium, 1, Oxygen, 3));
        event.setMaterial(Rutile).asOre()
                .mats(of(Titanium, 1, Oxygen, 2));
        event.setMaterial(MagnesiumChloride).asDust()
                .mats(of(Magnesium, 1, Chlorine, 2));
        event.setMaterial(Magnesite).asOre(true)
                .mats(of(Magnesium, 1, Carbon, 1, Oxygen, 3));
        event.setMaterial(Magnetite).asOre()
                .mats(of(AntimatterMaterials.Iron, 3, Oxygen, 4));
        event.setMaterial(Molybdenite).asOre(true)
                .mats(of(Molybdenum, 1, Sulfur, 2));
        event.setMaterial(Obsidian).asDust()
                .addHandleStat(222, -0.5F, of(Enchantments.UNBREAKING, 2))
                .mats(of(Magnesium, 1, AntimatterMaterials.Iron, 1, Silicon, 2, Oxygen, 8));
        event.setMaterial(Phosphate).asOre(true)
                .mats(of(Phosphor, 1, Oxygen, 4));
        event.setMaterial(GelledToluene).asDust();
        event.setMaterial(Polydimethylsiloxane).asDust()
                .mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 1, Silicon, 1));
        // event.setMaterial(Powellite).asDust(ORE).addComposition(of(Calcium, 1, Molybdenum, 1, Oxygen, 4));
        event.setMaterial(Pyrite).asOre()
                .mats(of(AntimatterMaterials.Iron, 1, Sulfur, 2));
        event.setMaterial(Pyrolusite).asOre()
                .mats(of(Manganese, 1, Oxygen, 2));
        event.setMaterial(Pyrope).asOre()
                .mats(of(Aluminium, 2, Magnesium, 3, Silicon, 3, Oxygen, 12));
        event.setMaterial(RawRubber).asDust()
                .mats(of(Carbon, 5, Hydrogen, 8));
        event.setMaterial(Saltpeter).asOre()
                .mats(of(Potassium, 1, Nitrogen, 1, Oxygen, 3));
        event.setMaterial(Scheelite).asDust(2500).asOre(true)
                .mats(of(Tungsten, 1, Calcium, 2, Oxygen, 4));
        event.setMaterial(SiliconDioxide).asDust()
                .mats(of(Silicon, 1, Oxygen, 2));
        // event.setMaterial(Pyrochlore).asDust(ORE).addComposition(of(Calcium, 2, Niobium, 2, Oxygen, 7));
        event.setMaterial(FerriteMixture).asDust()
                .mats(of(Nickel, 1, Zinc, 1, AntimatterMaterials.Iron, 4));
        event.setMaterial(Massicot).asDust()
                .mats(of(Lead, 1, Oxygen, 1));
        event.setMaterial(ArsenicTrioxide).asDust()
                .mats(of(Arsenic, 2, Oxygen, 3));
        event.setMaterial(CobaltOxide).asDust()
                .mats(of(Cobalt, 1, Oxygen, 1));
        event.setMaterial(Magnesia).asDust()
                .mats(of(Magnesium, 1, Oxygen, 1));
        event.setMaterial(Quicklime).asDust()
                .mats(of(Calcium, 1, Oxygen, 1));
        event.setMaterial(Potash).asDust()
                .mats(of(Potassium, 2, Oxygen, 1));
        event.setMaterial(SodaAsh).asDust()
                .mats(of(Sodium, 2, Carbon, 1, Oxygen, 3));
        event.setMaterial(Brick).asDust()
                .mats(of(Aluminium, 4, Silicon, 3, Oxygen, 12));
        event.setMaterial(Fireclay).asDust(AntimatterMaterialTypes.INGOT, AntimatterMaterialTypes.PLATE).mats(of(Brick, 1));
        event.setMaterial(SodiumBisulfate).asDust()
                .mats(of(Sodium, 1, Hydrogen, 1, Sulfur, 1, Oxygen, 4));
        event.setMaterial(SodiumSulfate).asDust()
                .mats(of(Sodium, 2, Sulfur, 1, Oxygen, 4));
        event.setMaterial(RawStyreneButadieneRubber).asDust()
                .mats(of(Styrene, 1, Butadiene, 3));
        event.setMaterial(PhosphorousPentoxide).asDust()
                .mats(of(Phosphor, 4, Oxygen, 10));
        event.setMaterial(SodiumHydroxide).asDust()
                .mats(of(Sodium, 1, Oxygen, 1, Hydrogen, 1));
        event.setMaterial(Spessartine).asOre()
                .mats(of(Aluminium, 2, Manganese, 3, Silicon, 3, Oxygen, 12));
        event.setMaterial(Sphalerite).asOre()
                .mats(of(Zinc, 1, Sulfur, 1));
        event.setMaterial(Stibnite).asOre()
                .mats(of(Antimony, 2, Sulfur, 3));
        event.setMaterial(Tetrahedrite).asOre(true)
                .mats(of(AntimatterMaterials.Copper, 3, Antimony, 1, Sulfur, 3, AntimatterMaterials.Iron, 1));
        event.setMaterial(Tungstate).asOre(true)
                .mats(of(Tungsten, 1, Lithium, 2, Oxygen, 4));
        event.setMaterial(Uraninite).asOre(true)
                .mats(of(Uranium, 1, Oxygen, 2));
        event.setMaterial(Uvarovite).asOre()
                .mats(of(Calcium, 3, Chrome, 2, Silicon, 3, Oxygen, 12));
        event.setMaterial(Wood).asDust(AntimatterMaterialTypes.GEAR).addHandleStat(12, 0.0F).mats(of(Carbon, 1, Oxygen, 1, Hydrogen, 1));
        event.setMaterial(Wulfenite).asOre(true)
                .mats(of(Lead, 1, Molybdenum, 1, Oxygen, 4));
        event.setMaterial(YellowLimonite).asOre(true)
                .mats(of(AntimatterMaterials.Iron, 1, Hydrogen, 1, Oxygen, 2));
        // event.setMaterial(SealedWood).asDust().addTools(3.0F,
        // 24, 0).addComposition(of(Wood, 1; TODO: Perhaps with IE integration or when
        // we have some utility stuf;
        event.setMaterial(PotassiumFeldspar).asDust()
                .mats(of(Potassium, 1, Aluminium, 1, Silicon, 3, Oxygen, 8));
        event.setMaterial(Biotite).asDust()
                .mats(b -> b.put(Potassium, 1).put(Magnesium, 3).put(Aluminium, 3).put(Fluorine, 2).put(Silicon, 3)
                        .put(Oxygen, 10));
        event.setMaterial(VanadiumMagnetite).asOre(true)
                .mats(of(Magnetite, 1, Vanadium, 1));
        event.setMaterial(Bastnasite).asOre(true)
                .mats(of(Cerium, 1, Carbon, 1, Fluorine, 1, Oxygen, 3));
        event.setMaterial(Pentlandite).asOre()
                .mats(of(Nickel, 9, Sulfur, 8));
        event.setMaterial(Spodumene).asOre(true)
                .mats(of(Lithium, 1, Aluminium, 1, Silicon, 2, Oxygen, 6));
        event.setMaterial(Tantalite).asOre(true)
                .mats(of(Manganese, 1, Tantalum, 2, Oxygen, 6));
        event.setMaterial(Lepidolite).asOre(true)
                .mats(of(Potassium, 1, Lithium, 3, Aluminium, 4, Fluorine, 2, Oxygen, 10)); // TODO: Ore Ge;
        event.setMaterial(Glauconite).asOre(true)
                .mats(of(Potassium, 1, Magnesium, 2, Aluminium, 4, Hydrogen, 2, Oxygen, 12)); // TODO: Ore Ge;
        event.setMaterial(Bentonite).asOre(true).mats(b -> b
                .put(Sodium, 1).put(Magnesium, 6).put(Silicon, 12).put(Hydrogen, 6).put(AntimatterMaterials.Water, 5).put(Oxygen, 36)); // TODO:
        // Ore Gen
        event.setMaterial(Pitchblende).asOre(true)
                .mats(of(Uraninite, 3, Thorium, 1, Lead, 1));
        event.setMaterial(Malachite).asOre(true)
                .mats(of(AntimatterMaterials.Copper, 2, Carbon, 1, Hydrogen, 2, Oxygen, 5));
        event.setMaterial(Barite).asOre(true)
                .mats(of(Barium, 1, Sulfur, 1, Oxygen, 4));
        event.setMaterial(Talc).asOre(true).mats(of(Magnesium, 3, Silicon, 4, Hydrogen, 2, Oxygen, 12));
        event.setMaterial(Soapstone).asOre(true)
                .mats(of(Magnesium, 3, Silicon, 4, Hydrogen, 2, Oxygen, 12)); // TODO: Ore Ge;
        event.setMaterial(Concrete).asDust(300).mats(of(AntimatterMaterials.Stone, 1)).asFluid();
        event.setMaterial(AntimonyTrioxide).asDust()
                .mats(of(Antimony, 2, Oxygen, 3));
        event.setMaterial(CupricOxide).asDust()
                .mats(of(AntimatterMaterials.Copper, 1, Oxygen, 1));
        event.setMaterial(Ferrosilite).asDust()
                .mats(of(AntimatterMaterials.Iron, 1, Silicon, 1, Oxygen, 3));

        /**
         * Gems
         **/
        // event.setMaterial(CertusQuartz).asGemBasic(false,
        // PLATE, ORE).addTools(5.0F, 32, 1; TODO: Only when AE2 is loade;
        event.setMaterial(Dilithium).asGemBasic(true);
        event.setMaterial(NetherStar).asGemBasic(false)
                .addTools(3.5F, 6.0F, 3620, 4, of(Enchantments.SILK_TOUCH, 1)); // Made Nether Stars usabl;

        // Brittle Gems
        event.setMaterial(BlueTopaz).asGem(true).asOre(3, 7, true)
                .addTools(2.5F, 7.0F, 256, 3).mats(of(Aluminium, 2, Silicon, 1, Fluorine, 2, Hydrogen, 2, Oxygen, 6));
        event.setMaterial(Charcoal).asGemBasic(false)
                .mats(of(Carbon, 1));
        event.setMaterial(CoalCoke).asGemBasic(false);
        event.setMaterial(LigniteCoke).asGemBasic(false);

        event.setMaterial(GreenSapphire).asGem(true)
                .asOre(3, 7, true).addTools(2.0F, 7.0F, 256, 2).mats(of(Aluminium, 2, Oxygen, 3));
        event.setMaterial(Lazurite).asGemBasic(false)
                .asOre(2, 5, true).mats(of(Aluminium, 6, Silicon, 6, Calcium, 8, Sodium, 8)); // TODO I think this is
        // needed;
        event.setMaterial(Ruby).asGem(true).asOre(3, 7, true).addTools(2.0F, 7.0F, 256, 2).mats(of(Chrome, 1, Aluminium, 2, Oxygen, 3));
        event.setMaterial(BlueSapphire).asGem(true)
                .asOre(3, 7, true).addTools(2.0F, 7.0F, 256, 2).mats(of(Aluminium, 2, Oxygen, 3));
        event.setMaterial(Sodalite).asGemBasic(false)
                .asOre(2, 5, true).mats(of(Aluminium, 3, Silicon, 3, Sodium, 4, Chlorine, 1)); // TODO I think this is
        // needed;
        event.setMaterial(Tanzanite).asGem(true).asOre(3, 7, true)
                .addTools(2.0F, 7.0F, 256, 2).mats(of(Calcium, 2, Aluminium, 3, Silicon, 3, Hydrogen, 1, Oxygen, 13));
        event.setMaterial(Topaz).asGem(true).asOre(3, 7, true)
                .addTools(2.0F, 7.0F, 256, 2).mats(of(Aluminium, 2, Silicon, 1, Fluorine, 2, Hydrogen, 2, Oxygen, 6));
        event.setMaterial(Glass).asDust(AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.LENS)
                .mats(of(SiliconDioxide, 1));
        event.setMaterial(Olivine).asGem(true).asOre(3, 7, true)
                .addTools(2.0F, 7.0F, 256, 2, of(Enchantments.SILK_TOUCH, 1))
                .mats(of(Magnesium, 2, AntimatterMaterials.Iron, 1, SiliconDioxide, 2));
        event.setMaterial(Opal).asGem(true).asOre(3, 7, true).addTools(2.0F, 7.0F, 256, 2).mats(of(SiliconDioxide, 1));
        event.setMaterial(Amethyst).asGem(true).asOre(3, 7, true)
                .addTools(2.0F, 7.0F, 256, 3).mats(of(SiliconDioxide, 4, AntimatterMaterials.Iron, 1));
        event.setMaterial(Phosphorus).asGemBasic(false)
                .asOre(3, 7, true).mats(of(Calcium, 3, Phosphate, 2));
        event.setMaterial(RedGarnet).asGemBasic(true)
                .asOre(3, 7, true).mats(of(Pyrope, 3, Almandine, 5, Spessartine, 8));
        event.setMaterial(YellowGarnet).asGemBasic(true)
                .asOre(3, 7, true).mats(of(Andradite, 5, Grossular, 8, Uvarovite, 3));
        // event.setMaterial(Monazite).asGemBasic(false,
        // ORE).addComposition(of(RareEarth, 1, Phosphate, 1));

        /**
         *
         **/
        event.setMaterial(Cinnabar).asOre(true)
                .mats(of(Mercury, 1, Sulfur, 1));

        /**
         * Metals
         **/
        event.setMaterial(AnnealedCopper).asMetal(1357, 0, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.FOIL, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.WIRE_FINE, AntimatterMaterialTypes.SCREW).mats(of(AntimatterMaterials.Copper, 1));
        event.setMaterial(BatteryAlloy).asMetal(295, 0, AntimatterMaterialTypes.PLATE)
                .mats(of(Lead, 4, Antimony, 1));
        event.setMaterial(Brass).asMetal(1170, 0, AntimatterMaterialTypes.FRAME, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.PLATE)
                .mats(of(Zinc, 1, AntimatterMaterials.Copper, 3));
        event.setMaterial(Bronze).asMetal(1125, 0, AntimatterMaterialTypes.GEAR, AntimatterMaterialTypes.FRAME, AntimatterMaterialTypes.ROTOR).addTools(1.5F, 6.5F, 182, 2, of(Enchantments.UNBREAKING, 1))
                .mats(of(Tin, 1, AntimatterMaterials.Copper, 3));
        event.setMaterial(Cupronickel).asMetal(1728, 0, AntimatterMaterialTypes.PLATE)
                .mats(of(AntimatterMaterials.Copper, 1, Nickel, 1));
        event.setMaterial(Electrum).asMetal(1330, 0, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.FOIL, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.WIRE_FINE).addTools(1.0F, 13.0F, 48, 2, of(Enchantments.UNBREAKING, 3))
                .mats(of(Silver, 1, AntimatterMaterials.Gold, 1));
        event.setMaterial(Invar).asMetal(1700, 0, AntimatterMaterialTypes.FRAME)
                .addTools(2.5F, 7.0F, 320, 2, of(Enchantments.BANE_OF_ARTHROPODS, 2)).mats(of(AntimatterMaterials.Iron, 2, Nickel, 1));
        event.setMaterial(Kanthal).asMetal(1800, 1800)
                .addTools(2.5F, 6.0F, 64, 2, of(Enchantments.BANE_OF_ARTHROPODS, 1))
                .mats(of(AntimatterMaterials.Iron, 1, Aluminium, 1, Chrome, 1));
        event.setMaterial(Magnalium).asMetal(870, 0, AntimatterMaterialTypes.PLATE)
                .mats(of(Magnesium, 1, Aluminium, 2));
        event.setMaterial(Nichrome).asMetal(2700, 2700)
                .addTools(2.0F, 6.0F, 81, 2, of(Enchantments.BANE_OF_ARTHROPODS, 3)).mats(of(Nickel, 4, Chrome, 1));
        event.setMaterial(NiobiumTitanium).asMetal(4500, 4500, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.FOIL, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.WIRE_FINE, AntimatterMaterialTypes.GEAR).mats(of(Nickel, 4, Chrome, 1));
        event.setMaterial(SolderingAlloy).asMetal(400, 400, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.FOIL, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.WIRE_FINE).mats(of(Tin, 9, Antimony, 1));
        event.setMaterial(Steel).asMetal(1811, 1000, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.SCREW, AntimatterMaterialTypes.BOLT, AntimatterMaterialTypes.RING, AntimatterMaterialTypes.GEAR, AntimatterMaterialTypes.FRAME, AntimatterMaterialTypes.ROTOR, AntimatterMaterialTypes.GEAR_SMALL).addTools(AntimatterMaterials.Iron)
                .mats(of(AntimatterMaterials.Iron, 50, Carbon, 1));
        event.setMaterial(StainlessSteel).asMetal(1700, 1700, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.SCREW, AntimatterMaterialTypes.BOLT, AntimatterMaterialTypes.RING, AntimatterMaterialTypes.GEAR, AntimatterMaterialTypes.FRAME, AntimatterMaterialTypes.ROTOR, AntimatterMaterialTypes.GEAR_SMALL).addTools(Steel)
                .mats(of(AntimatterMaterials.Iron, 6, Chrome, 1, Manganese, 1, Nickel, 1));
        event.setMaterial(Ultimet).asMetal(2700, 2700, AntimatterMaterialTypes.PLATE)
                .mats(of(Cobalt, 5, Chrome, 2, Nickel, 1, Molybdenum, 1));
        event.setMaterial(VanadiumGallium).asMetal(4500, 4500, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.PLATE).mats(of(Vanadium, 3, Gallium, 1));
        event.setMaterial(WroughtIron).asMetal(1811, 0, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.SCREW, AntimatterMaterialTypes.BOLT, AntimatterMaterialTypes.RING, AntimatterMaterialTypes.GEAR, AntimatterMaterialTypes.FRAME, AntimatterMaterialTypes.ROTOR, AntimatterMaterialTypes.GEAR_SMALL).addTools(AntimatterMaterials.Iron)
                .mats(of(AntimatterMaterials.Iron, 1)).asOre();
        event.setMaterial(YttriumBariumCuprate).asMetal(4500, 4500, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.FOIL, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.WIRE_FINE).mats(of(Yttrium, 1, Barium, 2, AntimatterMaterials.Copper, 3, Oxygen, 7));
        event.setMaterial(SterlingSilver).asMetal(1700, 1700)
                .addTools(3.0F, 10.5F, 96, 2, of(Enchantments.BLOCK_EFFICIENCY, 2)).mats(of(AntimatterMaterials.Copper, 1, Silver, 4));
        event.setMaterial(RoseGold).asMetal(1600, 1600, AntimatterMaterialTypes.WIRE_FINE)
                .addTools(AntimatterMaterials.Gold, of(Enchantments.BLOCK_FORTUNE, 3, Enchantments.SMITE, 3)).mats(of(AntimatterMaterials.Copper, 1, AntimatterMaterials.Gold, 4));
        event.setMaterial(BlackBronze).asMetal(2000, 2000)
                .addTools(Bronze, of(Enchantments.SWEEPING_EDGE, 1)).mats(of(AntimatterMaterials.Gold, 1, Silver, 1, AntimatterMaterials.Copper, 3));
        event.setMaterial(BismuthBronze).asMetal(1100, 900, AntimatterMaterialTypes.PLATE)
                .addTools(2.5F, MaterialTags.TOOLS.getToolData(Bronze).toolSpeed() + 2.0F, 350, 2, of(Enchantments.BANE_OF_ARTHROPODS, 4))
                .mats(of(Bismuth, 1, Zinc, 1, AntimatterMaterials.Copper, 3));
        event.setMaterial(BlackSteel).asMetal(1200, 1200, AntimatterMaterialTypes.FRAME, AntimatterMaterialTypes.PLATE).addTools(3.5F, 6.5F, 768, 2)
                .mats(of(Nickel, 1, BlackBronze, 1, Steel, 3));
        event.setMaterial(RedSteel).asMetal(1300, 1300)
                .addTools(3.5F, 7.0F, 896, 2).mats(of(SterlingSilver, 1, BismuthBronze, 1, Steel, 2, BlackSteel, 4));
        event.setMaterial(BlueSteel).asMetal(1400, 1400, AntimatterMaterialTypes.FRAME)
                .addTools(3.5F, 7.5F, 1024, 2).mats(of(RoseGold, 1, Brass, 1, Steel, 2, BlackSteel, 4));
        // event.setMaterial(DamascusSteel).asMetal(2500,
        // 1500).addTools(8.0F, 1280, 2).addComposition(of(Steel, 1); //TODO: Sorta a
        // fantasy metals;
        event.setMaterial(TungstenSteel).asMetal(3000, 3000, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.SCREW, AntimatterMaterialTypes.BOLT, AntimatterMaterialTypes.RING, AntimatterMaterialTypes.GEAR, AntimatterMaterialTypes.FRAME, AntimatterMaterialTypes.ROTOR, AntimatterMaterialTypes.GEAR_SMALL).asOre()
                .addTools(4.0F, 8.0F, 2560, 4).mats(of(Steel, 1, Tungsten, 1));
        event.setMaterial(RedAlloy).asMetal(295, 0, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.FOIL, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.WIRE_FINE).mats(of(AntimatterMaterials.Copper, 1, AntimatterMaterials.Redstone, 4));
        event.setMaterial(CobaltBrass).asMetal(1500, 0, AntimatterMaterialTypes.GEAR)
                .addTools(2.5F, 8.0F, 256, 2).mats(of(Brass, 7, Aluminium, 1, Cobalt, 1));
        event.setMaterial(IronMagnetic).asMetal(1811, 0, AntimatterMaterialTypes.ROD)
                .addTools(AntimatterMaterials.Iron).mats(of(AntimatterMaterials.Iron, 1));
        event.setMaterial(SteelMagnetic).asMetal(1000, 1000, AntimatterMaterialTypes.ROD).addTools(Steel).mats(of(Steel, 1));
        event.setMaterial(NeodymiumMagnetic).asMetal(1297, 1297, AntimatterMaterialTypes.ROD).mats(of(Neodymium, 1));
        event.setMaterial(NickelZincFerrite).asMetal(1500, 1500)
                .addTools(0.0F, 3.0F, 32, 1).mats(of(Nickel, 1, Zinc, 1, AntimatterMaterials.Iron, 4, Oxygen, 8));
        event.setMaterial(TungstenCarbide).asMetal(2460, 2460)
                .addTools(5.0F, 14.0F, 1280, 4).mats(of(Tungsten, 1, Carbon, 1));
        event.setMaterial(VanadiumSteel).asMetal(1453, 1453)
                .addTools(3.0F, 5.0F, 1920, 3).mats(of(Vanadium, 1, Chrome, 1, Steel, 7));
        event.setMaterial(HSSG).asMetal(4500, 4500, AntimatterMaterialTypes.GEAR, AntimatterMaterialTypes.FRAME)
                .addTools(3.8F, 10.0F, 4000, 3).mats(of(TungstenSteel, 5, Chrome, 1, Molybdenum, 2, Vanadium, 1));
        event.setMaterial(HSSE).asMetal(5400, 5400, AntimatterMaterialTypes.GEAR, AntimatterMaterialTypes.FRAME)
                .addTools(4.2F, 10.0F, 5120, 4).mats(of(HSSG, 6, Cobalt, 1, Manganese, 1, Silicon, 1));
        event.setMaterial(HSSS).asMetal(5400, 5400)
                .addTools(5.0F, 14.0F, 3000, 4).mats(of(HSSG, 6, Iridium, 2, Osmium, 1));
        event.setMaterial(Osmiridium).asMetal(3333, 2500, AntimatterMaterialTypes.FRAME)
                .addTools(6.0F, 15.0F, 1940, 5).mats(of(Iridium, 3, Osmium, 1));
        event.setMaterial(Duranium).asMetal(295, 0)
                .addHandleStat(620, -1.0F, of(Enchantments.SILK_TOUCH, 1)).addTools(6.5F, 16.0F, 5120, 5);
        event.setMaterial(Naquadah).asMetal(5400, 5400).asOre()
                .addHandleStat(102, 0.5F, of(Enchantments.BLOCK_EFFICIENCY, 2)).addTools(4.0F, 6.0F, 890, 4);
        event.setMaterial(NaquadahAlloy).asMetal(7200, 7200)
                .addTools(4.5F, 8.0F, 5120, 5);
        event.setMaterial(EnrichedNaquadah).asMetal(4500, 4500)
                .asOre().addTools(5.0F, 6.0F, 1280, 4);
        event.setMaterial(Naquadria).asMetal(9000, 9000);
        event.setMaterial(Tritanium).asMetal(295, 0, AntimatterMaterialTypes.FRAME)
                .addTools(9.0F, 15.0F, 9400, 6);
        event.setMaterial(Vibranium).asMetal(295, 0, AntimatterMaterialTypes.FRAME)
                .addTools(10.0F, 20.0F, 12240, 6);

        /**
         * Solids (Plastic Related Stuff)
         **/
        event.setMaterial(Polyethylene).asSolid(295, 0, AntimatterMaterialTypes.PLATE)
                .asFluid()
                .addHandleStat(66, 0.5F).mats(of(Carbon, 1, Hydrogen, 2));
        event.setMaterial(SiliconRubber).asFluid()
                .mats(of(Sulfur, 1, Polydimethylsiloxane, 9));
        event.setMaterial(Epoxid).asSolid(400, 0, AntimatterMaterialTypes.PLATE)
                .addHandleStat(70, 1.5F).mats(of(Carbon, 2, Hydrogen, 4, Oxygen, 1));
        event.setMaterial(Silicone).asSolid(900, 0, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.FOIL)
                .addHandleStat(-40, 2.0F).mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 1, Silicon, 1));
        event.setMaterial(Polycaprolactam).asSolid(500, 0)
                .mats(of(Carbon, 6, Hydrogen, 11, Nitrogen, 1, Oxygen, 1));
        event.setMaterial(Polytetrafluoroethylene).asSolid(1400, 0, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.FRAME).mats(of(Carbon, 2, Fluorine, 4));
        event.setMaterial(Rubber).asSolid(295, 0, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.RING)
                .addHandleStat(11, 0.4F).mats(of(Carbon, 5, Hydrogen, 8));
        event.setMaterial(PolyphenyleneSulfide).asSolid(295, 0, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.FOIL).mats(of(Carbon, 6, Hydrogen, 4, Sulfur, 1));
        event.setMaterial(Polystyrene).asSolid(295, 0)
                .addHandleStat(3, 1.0F).mats(of(Carbon, 8, Hydrogen, 8));
        event.setMaterial(StyreneButadieneRubber).asSolid(295, 0, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.RING).addHandleStat(66, 1.2F).mats(of(Styrene, 1, Butadiene, 3));
        event.setMaterial(PolyvinylChloride).asSolid(295, 0, AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.FOIL).addHandleStat(210, 0.5F).mats(of(Carbon, 2, Hydrogen, 3, Chlorine, 1));
        event.setMaterial(GalliumArsenide).asSolid(295, 1200)
                .mats(of(Arsenic, 1, Gallium, 1));
        event.setMaterial(EpoxidFiberReinforced).asSolid(400, 0, AntimatterMaterialTypes.PLATE).mats(of(Epoxid, 1));

        event.setMaterial(RedGranite).asDust(AntimatterMaterialTypes.ROCK)
                .addHandleStat(74, 1.0F, of(Enchantments.UNBREAKING, 1))
                .mats(of(Aluminium, 2, PotassiumFeldspar, 1, Oxygen, 3));
        event.setMaterial(BlackGranite).asDust(AntimatterMaterialTypes.ROCK)
                .addHandleStat(74, 1.0F, of(Enchantments.UNBREAKING, 1)).mats(of(SiliconDioxide, 4, Biotite, 1));
        event.setMaterial(Marble).asDust(AntimatterMaterialTypes.ROCK)
                .mats(of(Magnesium, 1, Calcite, 7));
        event.setMaterial(Komatiite).asDust(AntimatterMaterialTypes.ROCK)
                .mats(of(Olivine, 1, /* MgCO3, 2, */AntimatterMaterials.Flint, 6, DarkAsh, 3));
        event.setMaterial(Limestone).asDust(AntimatterMaterialTypes.ROCK)
                .mats(of(Calcite, 1));
        event.setMaterial(GreenSchist).asDust(AntimatterMaterialTypes.ROCK);
        event.setMaterial(BlueSchist).asDust(AntimatterMaterialTypes.ROCK);
        event.setMaterial(Kimberlite).asDust(AntimatterMaterialTypes.ROCK);
        event.setMaterial(Quartzite).asGemBasic(false, AntimatterMaterialTypes.ROCK, AntimatterMaterialTypes.ROD)
                .asOre(2, 5, true).mats(of(Silicon, 1, Oxygen, 2));

        /**
         * Ore Stones
         **/
        event.setMaterial(Lignite).asGemBasic(false)
                .asOreStone(0, 2, AntimatterMaterialTypes.ORE_SMALL).mats(of(Carbon, 3, AntimatterMaterials.Water, 1));
        event.setMaterial(Salt).asOreStone(AntimatterMaterialTypes.ORE_SMALL).mats(of(Sodium, 1, Chlorine, 1));
        event.setMaterial(RockSalt).asOreStone(AntimatterMaterialTypes.ORE_SMALL)
                .mats(of(Potassium, 1, Chlorine, 1));
        event.setMaterial(Bauxite).asOreStone(AntimatterMaterialTypes.ORE_SMALL)
                .mats(of(Rutile, 2, Aluminium, 16, Hydrogen, 10, Oxygen, 11));
        event.setMaterial(OilShale).asOreStone(AntimatterMaterialTypes.ORE_SMALL);

        /**
         * Reference Materials
         **/
        event.setMaterial(Superconductor).asSolid(AntimatterMaterialTypes.PLATE);

        /**
         * Cracked stuff
         */
        event.setMaterial(SteamCrackedEthane)
                .asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",288)},144)
                .mats(of(Ethane,1,Steam,1));
        event.setMaterial(SteamCrackedEthylene)
                .asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",288)},144)
                .mats(of(Ethylene,1,Steam,1));
        event.setMaterial(SteamCrackedPropane)
                .asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",3*144),new FluidProduct(Ethylene,"gas",144)},288)
                .mats(of(Propane,1,Steam,1));
        event.setMaterial(SteamCrackedPropene)
                .asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",3*144)},144)
                .mats(of(Propene,1,Steam,1));
        event.setMaterial(SteamCrackedButane)
                .asGas(0,0,true, new FluidProduct[]{
                    new FluidProduct(Methane,"gas",4*144),new FluidProduct(Ethane,"gas",36),
                    new FluidProduct(Ethylene,"gas",36),new FluidProduct(Propane,"gas",36)
                },288)
                .mats(of(Butane,1,Steam,1));
        event.setMaterial(SteamCrackedButene)
                .asGas(0,0,true, new FluidProduct[]{
                    new FluidProduct(Methane,"gas",3*144),
                    new FluidProduct(Ethylene,"gas",90),
                    new FluidProduct(Propene,"gas",36)
                },288)
                .mats(of(Butene,1,Steam,1));
        event.setMaterial(SteamCrackedButadiene)
                .asGas(0,0,true,new FluidProduct[]{
                    new FluidProduct(Methane,"gas",324),
                    new FluidProduct(Ethylene,"gas",54),
                    new FluidProduct(Propene,"gas",36)
                },288)
                .mats(of(Butadiene,1,Steam,1));
        event.setMaterial(SteamCrackedLightFuel)
                .asFluid(0,0,true,new FluidProduct[]{
                    new FluidProduct(Methane,"gas",36),new FluidProduct(Ethane,"gas",9),
                    new FluidProduct(Ethylene,"gas",36),new FluidProduct(Propane,"gas",9),
                    new FluidProduct(Propene,"gas",36),new FluidProduct(Butene,"gas",18),
                    new FluidProduct(Butadiene,"gas",9),new FluidProduct(Benzene,"fluid",36),
                    new FluidProduct(Toluene,"fluid",18),new FluidProduct(Naphtha,"fluid",9),
                    new FluidProduct(HeavyFuel,"fluid",9)
                },144)
                .mats(of(LightFuel,1,Steam,1));
        event.setMaterial(SteamCrackedHeavyFuel)
                .asFluid(0,0,true,new FluidProduct[]{
                        new FluidProduct(Methane,"gas",18),new FluidProduct(Ethane,"gas",9),
                        new FluidProduct(Ethylene,"gas",18),new FluidProduct(Propane,"gas",9),
                        new FluidProduct(Propene,"gas",18),new FluidProduct(Butene,"gas",9),
                        new FluidProduct(Butadiene,"gas",9),new FluidProduct(Benzene,"fluid",72),
                        new FluidProduct(Toluene,"fluid",12),new FluidProduct(Naphtha,"fluid",36),
                        new FluidProduct(SulfuricHeavyFuel,"liquid",9)
                },144)
                .mats(of(HeavyFuel,1,Steam,1));
        event.setMaterial(SteamCrackedNaphtha)
                .asFluid(0,0,true,new FluidProduct[]{
                        new FluidProduct(Methane,"gas",72),new FluidProduct(Ethane,"gas",36),
                        new FluidProduct(Ethylene,"gas",72),new FluidProduct(Propane,"gas",9),
                        new FluidProduct(Propene,"gas",18),new FluidProduct(Butene,"gas",9),
                        new FluidProduct(Butadiene,"gas",9),new FluidProduct(Benzene,"fluid",18),
                        new FluidProduct(Toluene,"fluid",9),new FluidProduct(LightFuel,"fluid",9),
                        new FluidProduct(HeavyFuel,"fluid",9)
                },144)
                .mats(of(Naphtha,1,Steam,1));
        event.setMaterial(SteamCrackedRefineryGas)
                .asGas(0,0,true,new FluidProduct[]{
                        new FluidProduct(Methane,"gas",144),new FluidProduct(Ethane,"gas",9),
                        new FluidProduct(Ethylene,"gas",18), new FluidProduct(Propene,"gas",9)
                },144)
                .mats(of(RefineryGas,1,Steam,1));
        event.setMaterial(HydroCrackedEthane)
                .asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",288)},144)
                .mats(of(Ethane,1,Hydrogen,2));
        event.setMaterial(HydroCrackedEthylene)
                .asGas(0,0,true,new FluidProduct[]{new FluidProduct(Ethane,"gas",144)},144)
                .mats(of(Ethylene,1,Hydrogen,2));
        event.setMaterial(HydroCrackedPropane)
                .asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",144),new FluidProduct(Ethane,"gas",144)},144)
                .mats(of(Propane,1,Hydrogen,2));
        event.setMaterial(HydroCrackedPropene)
                .asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",144),
                        new FluidProduct(Ethylene,"gas",144),new FluidProduct(Propane,"gas",144)},288)
                .mats(of(Propene,1,Hydrogen,2));
        event.setMaterial(HydroCrackedButane)
                .asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",144),new FluidProduct(Ethane,"gas",288),
                        new FluidProduct(Propane,"gas",144)},288)
                .mats(of(Butane,1,Hydrogen,2));
        event.setMaterial(HydroCrackedButene)
                .asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",144),new FluidProduct(Ethane,"gas",288),
                        new FluidProduct(Propene,"gas",144),new FluidProduct(Butane,"gas",144)},3*144)
                .mats(of(Butene,1,Hydrogen,2));
        event.setMaterial(HydroCrackedButadiene)
                .asGas(0,0,true,new FluidProduct[]{new FluidProduct(Ethylene,"gas",288),new FluidProduct(Butene,"gas",144)},288)
                .mats(of(Butadiene,1,Hydrogen,2));
        event.setMaterial(HydroCrackedLightFuel)
                .asFluid(0,0,true,new FluidProduct[]{new FluidProduct(Naphtha,"fluid",144),
                        new FluidProduct(Butane,"gas",288),new FluidProduct(Propane,"gas",288),
                        new FluidProduct(Ethane,"gas",4*144),new FluidProduct(Methane,"gas",288)},4*144)
                .mats(of(LightFuel,1,Hydrogen,2));
        event.setMaterial(HydroCrackedHeavyFuel)
                .asFluid(0,0,true,new FluidProduct[]{new FluidProduct(LightFuel,"fluid",144),new FluidProduct(Naphtha,"fluid",144),
                        new FluidProduct(Butane,"gas",288),new FluidProduct(Propane,"gas",288),
                        new FluidProduct(Ethane,"gas",4*144),new FluidProduct(Methane,"gas",288)},5*144)
                .mats(of(HeavyFuel,1,Hydrogen,2));
        event.setMaterial(HydroCrackedNaphtha)
                .asFluid(0,0,true,new FluidProduct[]{
                        new FluidProduct(Methane,"gas",288),new FluidProduct(Ethane,"gas",4*144),
                        new FluidProduct(Propane,"gas",288),new FluidProduct(Butane,"gas",288)},3*144)
                .mats(of(Naphtha,1,Hydrogen,2));
        event.setMaterial(HydroCrackedRefineryGas)
                .asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",144),new FluidProduct(Hydrogen,"gas",144)},144)
                .mats(of(RefineryGas,1,Hydrogen,2));

    }

    public static void byproducts(MaterialEvent event){
        event.setMaterial(Chalcopyrite).addByProduct(Pyrite, Cobalt, Cadmium, AntimatterMaterials.Gold);
        event.setMaterial(Sphalerite).addByProduct(YellowGarnet, Cadmium, Gallium, Zinc);
        event.setMaterial(Glauconite).addByProduct(Sodium, Aluminium, AntimatterMaterials.Iron);
        event.setMaterial(Bentonite).addByProduct(Aluminium, Calcium, Magnesium);
        event.setMaterial(Uraninite).addByProduct(Uranium, Thorium, Uranium235);
        event.setMaterial(Pitchblende).addByProduct(Thorium, Uranium, Lead);
        event.setMaterial(Galena).addByProduct(Sulfur, Silver, Lead);
        event.setMaterial(AntimatterMaterials.Lapis).addByProduct(Calcite, Pyrite);
        event.setMaterial(Pyrite).addByProduct(Sulfur, Phosphorus, AntimatterMaterials.Iron);
        event.setMaterial(AntimatterMaterials.Copper).addByProduct(Cobalt, AntimatterMaterials.Gold, Nickel);
        event.setMaterial(Nickel).addByProduct(Cobalt, Platinum, AntimatterMaterials.Iron);
        event.setMaterial(RedGarnet).addByProduct(Spessartine, Pyrope, Almandine);
        event.setMaterial(YellowGarnet).addByProduct(Andradite, Grossular, Uvarovite);
        event.setMaterial(Cooperite).addByProduct(Palladium, Nickel, Iridium);
        event.setMaterial(Cinnabar).addByProduct(AntimatterMaterials.Redstone, Sulfur, AntimatterMaterials.Glowstone);
        event.setMaterial(Tantalite).addByProduct(Manganese, Niobium, Tantalum);
        event.setMaterial(Pentlandite).addByProduct(AntimatterMaterials.Iron, Sulfur, Cobalt);
        event.setMaterial(Uranium).addByProduct(Lead, Uranium235, Thorium);
        event.setMaterial(Scheelite).addByProduct(Manganese, Molybdenum, Calcium);
        event.setMaterial(Tungstate).addByProduct(Manganese, Silver, Lithium);
        event.setMaterial(Bauxite).addByProduct(Grossular, Rutile, Gallium);
        event.setMaterial(AntimatterMaterials.Redstone).addByProduct(Cinnabar, RareEarth, AntimatterMaterials.Glowstone);
        event.setMaterial(Malachite).addByProduct(AntimatterMaterials.Copper, BrownLimonite, Calcite);
        event.setMaterial(YellowLimonite).addByProduct(Nickel, BrownLimonite, Cobalt);
        event.setMaterial(Andradite).addByProduct(YellowGarnet, AntimatterMaterials.Iron, Boron);
        event.setMaterial(Quartzite).addByProduct(Barite);
        event.setMaterial(BrownLimonite).addByProduct(Malachite, YellowLimonite);
        event.setMaterial(Neodymium).addByProduct(RareEarth);
        event.setMaterial(Bastnasite).addByProduct(Neodymium, RareEarth);
        event.setMaterial(AntimatterMaterials.Glowstone).addByProduct(AntimatterMaterials.Redstone, AntimatterMaterials.Gold);
        event.setMaterial(Zinc).addByProduct(Tin, Gallium);
        event.setMaterial(Tungsten).addByProduct(Manganese, Molybdenum);
        event.setMaterial(AntimatterMaterials.Iron).addByProduct(Nickel, Tin);
        event.setMaterial(AntimatterMaterials.Gold).addByProduct(AntimatterMaterials.Copper, Nickel);
        event.setMaterial(Tin).addByProduct(AntimatterMaterials.Iron, Zinc);
        event.setMaterial(Antimony).addByProduct(Zinc, AntimatterMaterials.Iron);
        event.setMaterial(Silver).addByProduct(Lead, Sulfur);
        event.setMaterial(Lead).addByProduct(Silver, Sulfur);
        event.setMaterial(Thorium).addByProduct(Uranium, Lead);
        event.setMaterial(Plutonium).addByProduct(Uranium, Lead);
        event.setMaterial(Electrum).addByProduct(AntimatterMaterials.Gold, Silver);
        event.setMaterial(Bronze).addByProduct(AntimatterMaterials.Copper, Tin);
        event.setMaterial(Brass).addByProduct(AntimatterMaterials.Copper, Zinc);
        event.setMaterial(AntimatterMaterials.Coal).addByProduct(Lignite, Thorium);
        event.setMaterial(Ilmenite).addByProduct(AntimatterMaterials.Iron, Rutile);
        event.setMaterial(Manganese).addByProduct(Chrome, AntimatterMaterials.Iron);
        event.setMaterial(BlueSapphire).addByProduct(Aluminium);
        event.setMaterial(Platinum).addByProduct(Nickel, Iridium);
        event.setMaterial(AntimatterMaterials.Emerald).addByProduct(Beryllium, Aluminium);
        event.setMaterial(Olivine).addByProduct(Pyrope, Magnesium);
        event.setMaterial(Chrome).addByProduct(AntimatterMaterials.Iron, Magnesium);
        event.setMaterial(Tetrahedrite).addByProduct(Antimony, Zinc);
        event.setMaterial(Magnetite).addByProduct(AntimatterMaterials.Iron, AntimatterMaterials.Gold);
        event.setMaterial(AntimatterMaterials.Basalt).addByProduct(Olivine, DarkAsh);
        event.setMaterial(VanadiumMagnetite).addByProduct(Magnetite, Vanadium);
        event.setMaterial(Spodumene).addByProduct(Aluminium, Lithium);
        event.setMaterial(Ruby).addByProduct(Chrome, RedGarnet);
        event.setMaterial(Phosphorus).addByProduct(Phosphate);
        event.setMaterial(Iridium).addByProduct(Platinum, Osmium);
        event.setMaterial(Pyrope).addByProduct(RedGarnet, Magnesium);
        event.setMaterial(Almandine).addByProduct(RedGarnet, Aluminium);
        event.setMaterial(Spessartine).addByProduct(RedGarnet, Manganese);
        event.setMaterial(Grossular).addByProduct(YellowGarnet, Calcium);
        event.setMaterial(Uvarovite).addByProduct(YellowGarnet, Chrome);
        event.setMaterial(Calcite).addByProduct(Andradite, Malachite);
        event.setMaterial(EnrichedNaquadah).addByProduct(Naquadah, Naquadria);
        event.setMaterial(Naquadah).addByProduct(EnrichedNaquadah);
        event.setMaterial(Pyrolusite).addByProduct(Manganese);
        event.setMaterial(Molybdenite).addByProduct(Molybdenum);
        event.setMaterial(Stibnite).addByProduct(Antimony);
        event.setMaterial(Garnierite).addByProduct(Nickel);
        event.setMaterial(Lignite).addByProduct(AntimatterMaterials.Coal);
        event.setMaterial(AntimatterMaterials.Diamond).addByProduct(Graphite);
        event.setMaterial(Beryllium).addByProduct(AntimatterMaterials.Emerald);
        event.setMaterial(Magnesite).addByProduct(Magnesium);
        event.setMaterial(AntimatterMaterials.Quartz).addByProduct(AntimatterMaterials.Netherrack);
        event.setMaterial(Steel).addByProduct(AntimatterMaterials.Iron);
        event.setMaterial(Graphite).addByProduct(Carbon);
        event.setMaterial(AntimatterMaterials.Netherrack).addByProduct(Sulfur);
        event.setMaterial(AntimatterMaterials.Flint).addByProduct(Obsidian);
        event.setMaterial(Cobaltite).addByProduct(Cobalt);
        event.setMaterial(Cobalt).addByProduct(Cobaltite);
        event.setMaterial(Sulfur).addByProduct(Sulfur);
        event.setMaterial(Saltpeter).addByProduct(Saltpeter);
        event.setMaterial(AntimatterMaterials.Endstone).addByProduct(Helium3);
        event.setMaterial(Osmium).addByProduct(Iridium);
        event.setMaterial(Magnesium).addByProduct(Olivine);
        event.setMaterial(Aluminium).addByProduct(Bauxite);
        event.setMaterial(Titanium).addByProduct(Almandine);
        event.setMaterial(Obsidian).addByProduct(Olivine);
        event.setMaterial(Ash).addByProduct(Carbon);
        event.setMaterial(DarkAsh).addByProduct(Carbon);
        event.setMaterial(Marble).addByProduct(Calcite);
        event.setMaterial(Clay).addByProduct(Clay);
        event.setMaterial(Cassiterite).addByProduct(Tin);
        event.setMaterial(BlackGranite).addByProduct(Biotite);
        event.setMaterial(RedGranite).addByProduct(PotassiumFeldspar);
        event.setMaterial(Phosphate).addByProduct(Phosphor);
        event.setMaterial(Phosphor).addByProduct(Phosphate);
        event.setMaterial(Tanzanite).addByProduct(Opal);
        event.setMaterial(Opal).addByProduct(Tanzanite);
        event.setMaterial(Amethyst).addByProduct(Amethyst);
        // event.setMaterial(Amber).addByProduct(Amber);
        event.setMaterial(Neutronium).addByProduct(Neutronium);
        event.setMaterial(Lithium).addByProduct(Lithium);
        event.setMaterial(Silicon).addByProduct(SiliconDioxide);
        event.setMaterial(Salt).addByProduct(RockSalt);
        event.setMaterial(RockSalt).addByProduct(Salt);
    }

    private static void flags(MaterialEvent event){
        CHEMBATH_MERCURY.add(Chalcopyrite, AntimatterMaterials.Gold);
        CHEMBATH_MERCURY.add(AntimatterMaterials.Gold,Nickel);
        CHEMBATH_MERCURY.add(Silver,Sulfur);
        //CHEMBATH_MERCURY.add(Osmium,Iridium);
        CHEMBATH_MERCURY.add(Platinum,Iridium);
        CHEMBATH_PERSULFATE.add(Nickel, AntimatterMaterials.Iron);
        CHEMBATH_PERSULFATE.add(Cobalt,Cobaltite);
        CHEMBATH_PERSULFATE.add(Cobaltite,Cobalt);
        CHEMBATH_PERSULFATE.add(Sphalerite,Zinc);
        CHEMBATH_PERSULFATE.add(Tetrahedrite,Zinc);
        CHEMBATH_PERSULFATE.add(Zinc,Gallium);
        CHEMBATH_PERSULFATE.add(AntimatterMaterials.Copper,Nickel);
        GregTechMaterialTags.ELECTROMAGNETIC_SEPARATOR_IRON.add(YellowLimonite, BrownLimonite, Tin/*, Chrome*/, Ilmenite, BandedIron, Pyrite, Glauconite, Nickel, /*Chromite, */Pentlandite, Manganese);
        GregTechMaterialTags.ELECTROMAGNETIC_SEPARATOR_GOLD.add(VanadiumMagnetite, Magnetite);
        GregTechMaterialTags.ELECTROMAGNETIC_SEPARATOR_NEODYMIUM.add(/*Monazite, */Bastnasite);
        //MaterialTags.ELECSEPN.add(YellowLimonite, BrownLimonite, Pyrite, BandedIron, Nickel, Glauconite, Pentlandite, Tin, Antimony,
        //        Ilmenite, Manganese, Chrome, Andradite);
        MaterialTags.ELEC.add(Methane, CarbonDioxide, NitrogenDioxide, Toluene, VinylChloride, SulfurDioxide, SulfurTrioxide,
                Dimethylamine, DinitrogenTetroxide, NitricOxide, Ammonia, Chloromethane, Tetrafluoroethylene,
                CarbonMonoxide, Ethylene, Propane, Ethenone, Ethanol, SodiumPersulfate, Dichlorobenzene,
                Styrene, Isoprene, Tetranitromethane, Epichlorohydrin, NitricAcid, Dimethylhydrazine, Chloramine,
                Dimethyldichlorosilane, HydrofluoricAcid, Chloroform, BisphenolA, AceticAcid, Acetone, Methanol,
                VinylAcetate, MethylAcetate, AllylChloride, HypochlorousAcid, Cumene, PhosphoricAcid, SulfuricAcid,
                Benzene, Phenol, Glyceryl, SodiumSulfide, Almandine, Andradite, BandedIron, Calcite, Cassiterite,
                Chalcopyrite, Cobaltite, Galena, Garnierite, Grossular, Bauxite, Magnesite, Magnetite, Molybdenite,
                Obsidian, Phosphate, Polydimethylsiloxane, Pyrite, Pyrolusite, Pyrope, RockSalt, Saltpeter,
                SiliconDioxide, Massicot, ArsenicTrioxide, CobaltOxide, Magnesia, Quicklime, Potash, SodaAsh,
                PhosphorousPentoxide, SodiumHydroxide, Spessartine, Sphalerite, Uvarovite, PotassiumFeldspar, Biotite,
                RedGranite, Bastnasite, Pentlandite, Spodumene, Glauconite, Bentonite, Malachite, Barite, Talc,
                AntimonyTrioxide, CupricOxide, Ferrosilite, Quartzite, Charcoal, AntimatterMaterials.Coal, Lignite, AntimatterMaterials.Diamond, AntimatterMaterials.Emerald, Ruby,
                BlueSapphire, Tanzanite, Topaz, Olivine, Opal, Amethyst, AntimatterMaterials.EnderPearl, StainlessSteel, Steel, Ultimet,
                IronMagnetic, SteelMagnetic, NeodymiumMagnetic, Osmiridium, Sodalite);
        MaterialTags.CENT.add(/* NobleGases, */Air, BrownLimonite, Cinnabar, Clay, Cooperite/* , Powellite */, Stibnite,
                Tetrahedrite, Uraninite, Wulfenite, YellowLimonite, AntimatterMaterials.Blaze, AntimatterMaterials.Flint, Marble, BlackGranite,
                VanadiumMagnetite, Pitchblende, Glass, AntimatterMaterials.Lapis, AntimatterMaterials.EnderEye, Phosphorus, AntimatterMaterials.Redstone, AntimatterMaterials.Basalt, AnnealedCopper,
                BatteryAlloy, Brass, Bronze, Cupronickel, Electrum, Invar, Kanthal, Magnalium, Nichrome,
                NiobiumTitanium, SolderingAlloy, VanadiumGallium, WroughtIron, SterlingSilver, RoseGold, BismuthBronze,
                TungstenSteel, RedAlloy, CobaltBrass, TungstenCarbide, VanadiumSteel, HSSG, HSSE, HSSS,
                GalliumArsenide/* , IndiumGalliumPhosphide, BorosilicateGlass */);
        MaterialTags.CRACK.add(RefineryGas, Naphtha, Ethane, Propane, Butane, Butene, Ethylene, Propene, LightFuel, HeavyFuel);
        MaterialTags.CALCITE2X.add(Pyrite, BrownLimonite, YellowLimonite, Magnetite);
        MaterialTags.CALCITE3X.add(AntimatterMaterials.Iron, WroughtIron);
        MaterialTags.NOSMELT.add(Wood/* , WoodSealed */, Sulfur, Saltpeter, Graphite, /* Paper, */AntimatterMaterials.Coal, Charcoal, Lignite,
                NitroFuel, AntimatterMaterials.Emerald, Amethyst, Tanzanite, Topaz, /* Amber, */ BlueSapphire, Ruby, Opal, Olivine,
                AntimatterMaterials.Lapis/* , Sodalite, Lazurite, Monazite */, Quartzite, AntimatterMaterials.Quartz, Phosphorus, Phosphate, NetherStar,
                AntimatterMaterials.EnderPearl, AntimatterMaterials.EnderEye, AntimatterMaterials.Blaze);
        MaterialTags.NOSMASH.add(Wood/* WoodSealed */, Sulfur, Saltpeter, Graphite, /* Paper, */AntimatterMaterials.Coal, Charcoal, Lignite, Rubber,
                StyreneButadieneRubber, Polyethylene, PolyvinylChloride, Polystyrene, Silicone, NitroFuel,
                Concrete, AntimatterMaterials.Redstone, AntimatterMaterials.Glowstone, AntimatterMaterials.Netherrack, AntimatterMaterials.Stone, Brick, AntimatterMaterials.Endstone, Marble, AntimatterMaterials.Basalt, Obsidian, AntimatterMaterials.Flint,
                RedGranite, BlackGranite, Salt, RockSalt, Glass, AntimatterMaterials.Diamond, AntimatterMaterials.Emerald, Amethyst, Tanzanite, Topaz,
                /* Amber, */ BlueSapphire, Ruby, Opal, Olivine, AntimatterMaterials.Lapis, Quartzite, AntimatterMaterials.Quartz, Phosphorus, Phosphate,
                NetherStar, AntimatterMaterials.EnderPearl, AntimatterMaterials.EnderEye);
        MaterialTags.GRINDABLE.add(/* Paper, */AntimatterMaterials.Coal, Charcoal, Lignite, Lead, Tin, SolderingAlloy, AntimatterMaterials.Flint, AntimatterMaterials.Gold, Silver, AntimatterMaterials.Iron,
                IronMagnetic, Steel, SteelMagnetic, Zinc, Antimony, AntimatterMaterials.Copper, AnnealedCopper, Bronze, Nickel, Invar,
                Brass, WroughtIron, Electrum, Clay, AntimatterMaterials.Blaze);
        MaterialTags.SMELTF.add(Concrete, AntimatterMaterials.Redstone, AntimatterMaterials.Glowstone, Glass, AntimatterMaterials.Blaze);

        MaterialTags.NOBBF.add(Tetrahedrite, Chalcopyrite, Cooperite, Pyrolusite, Magnesite, Molybdenite, Galena);
        MaterialTags.CRYSTALLIZE.add(AntimatterMaterials.Lapis, Quartzite, AntimatterMaterials.Quartz);
        MaterialTags.BRITTLEG.add(AntimatterMaterials.Coal, Charcoal, Lignite);
        MaterialTags.RUBBERTOOLS.add(Rubber, StyreneButadieneRubber, Polyethylene, PolyvinylChloride, Polystyrene, Silicone);

        MaterialTags.SOLDER.subTag(SubTag.BAD_SOLDER, Lead, Tin);
        MaterialTags.SOLDER.subTag(SubTag.GOOD_SOLDER, SolderingAlloy, Tin);
        MaterialTags.SOLDER.add(Lead, Tin, SolderingAlloy);
        MaterialTags.WIRE.subTag(SubTag.COPPER_WIRE, AnnealedCopper);
        MaterialTags.WIRE.subTag(SubTag.COPPER_WIRE, AntimatterMaterials.Copper);



        event.setMaterial(BlueTopaz).remove(AntimatterMaterialTypes.ORE);

        event.setMaterial(NeodymiumMagnetic).setSmeltInto(Neodymium).setMacerateInto(Neodymium).setArcSmeltInto(Neodymium);
        event.setMaterial(SteelMagnetic).setSmeltInto(Steel).setMacerateInto(Steel).setArcSmeltInto(Steel);
        event.setMaterial(AntimatterMaterials.Iron).setSmeltInto(AntimatterMaterials.Iron).setMacerateInto(AntimatterMaterials.Iron).setArcSmeltInto(WroughtIron);
        event.setMaterial(WroughtIron).setSmeltInto(AntimatterMaterials.Iron).setMacerateInto(AntimatterMaterials.Iron).setArcSmeltInto(WroughtIron);
        event.setMaterial(IronMagnetic).setSmeltInto(AntimatterMaterials.Iron).setMacerateInto(AntimatterMaterials.Iron).setArcSmeltInto(WroughtIron);
        event.setMaterial(AntimatterMaterials.Copper).setSmeltInto(AntimatterMaterials.Copper).setMacerateInto(AntimatterMaterials.Copper).setArcSmeltInto(AnnealedCopper);
        event.setMaterial(AnnealedCopper).setSmeltInto(AntimatterMaterials.Copper).setMacerateInto(AntimatterMaterials.Copper).setArcSmeltInto(AnnealedCopper);

        event.setMaterial(Cinnabar).setDirectSmeltInto(Mercury);
        event.setMaterial(Tetrahedrite).setDirectSmeltInto(AntimatterMaterials.Copper);
        event.setMaterial(Chalcopyrite).setDirectSmeltInto(AntimatterMaterials.Copper);
        event.setMaterial(Malachite).setDirectSmeltInto(AntimatterMaterials.Copper);
        event.setMaterial(Pentlandite).setDirectSmeltInto(Nickel);
        event.setMaterial(Sphalerite).setDirectSmeltInto(Zinc);
        event.setMaterial(Pyrite).setDirectSmeltInto(AntimatterMaterials.Iron);
        event.setMaterial(YellowLimonite).setDirectSmeltInto(AntimatterMaterials.Iron);
        event.setMaterial(BrownLimonite).setDirectSmeltInto(AntimatterMaterials.Iron);
        event.setMaterial(BandedIron).setDirectSmeltInto(AntimatterMaterials.Iron);
        event.setMaterial(Magnetite).setDirectSmeltInto(AntimatterMaterials.Iron);
        event.setMaterial(Cassiterite).setDirectSmeltInto(Tin);
        event.setMaterial(Garnierite).setDirectSmeltInto(Nickel);
        event.setMaterial(Cobaltite).setDirectSmeltInto(Cobalt);
        event.setMaterial(Stibnite).setDirectSmeltInto(Antimony);
        event.setMaterial(Cooperite).setDirectSmeltInto(Platinum);
        event.setMaterial(Pyrolusite).setDirectSmeltInto(Manganese);
        event.setMaterial(Magnesite).setDirectSmeltInto(Magnesium);
        event.setMaterial(Molybdenite).setDirectSmeltInto(Molybdenum);
        event.setMaterial(Galena).setDirectSmeltInto(Lead);
        event.setMaterial(Salt).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(RockSalt).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Scheelite).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Tungstate).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Cassiterite).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(AntimatterMaterials.Quartz).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Phosphorus).setOreMulti(3).setSmeltingMulti(3);
        event.setMaterial(Saltpeter).setOreMulti(4).setSmeltingMulti(4);
        event.setMaterial(AntimatterMaterials.Redstone).setOreMulti(5).setSmeltingMulti(5);
        event.setMaterial(AntimatterMaterials.Glowstone).setOreMulti(5).setSmeltingMulti(5);
        event.setMaterial(AntimatterMaterials.Lapis).setOreMulti(6).setSmeltingMulti(6).setByProductMulti(4);
        // Plastic.setEnchantmentForTools(Enchantment.knockback, 1);
        // PolyvinylChloride.setEnchantmentForTools(Enchantment.knockback, 1);
        // Polystyrene.setEnchantmentForTools(Enchantment.knockback, 1);
        // Rubber.setEnchantmentForTools(Enchantment.knockback, 2);
        // StyreneButadieneRubber.setEnchantmentForTools(Enchantment.knockback, 2);
        // Flint.setEnchantmentForTools(Enchantment.fireAspect, 1);
        // Blaze.setEnchantmentForTools(Enchantment.fireAspect, 3);
        // EnderPearl.setEnchantmentForTools(Enchantment.silkTouch, 1);
        // NetherStar.setEnchantmentForTools(Enchantment.silkTouch, 1);
        // BlackBronze.setEnchantmentForTools(Enchantment.smite, 2);
        // Gold.setEnchantmentForTools(Enchantment.smite, 3);
        // RoseGold.setEnchantmentForTools(Enchantment.smite, 4);
        // Platinum.setEnchantmentForTools(Enchantment.smite, 5);
        // Lead.setEnchantmentForTools(Enchantment.baneOfArthropods, 2);
        // Nickel.setEnchantmentForTools(Enchantment.baneOfArthropods, 2);
        // Invar.setEnchantmentForTools(Enchantment.baneOfArthropods, 3);
        // Antimony.setEnchantmentForTools(Enchantment.baneOfArthropods, 3);
        // BatteryAlloy.setEnchantmentForTools(Enchantment.baneOfArthropods, 4);
        // Bismuth.setEnchantmentForTools(Enchantment.baneOfArthropods, 4);
        // BismuthBronze.setEnchantmentForTools(Enchantment.baneOfArthropods, 5);
        // Iron.setEnchantmentForTools(Enchantment.sharpness, 1);
        // Bronze.setEnchantmentForTools(Enchantment.sharpness, 1);
        // Brass.setEnchantmentForTools(Enchantment.sharpness, 2);
        // Steel.setEnchantmentForTools(Enchantment.sharpness, 2);
        // WroughtIron.setEnchantmentForTools(Enchantment.sharpness, 2);
        // StainlessSteel.setEnchantmentForTools(Enchantment.sharpness, 3);
        // BlackSteel.setEnchantmentForTools(Enchantment.sharpness, 4);
        // RedSteel.setEnchantmentForTools(Enchantment.sharpness, 4);
        // BlueSteel.setEnchantmentForTools(Enchantment.sharpness, 5);
        // DamascusSteel.setEnchantmentForTools(Enchantment.sharpness, 5);
        // TungstenCarbide.setEnchantmentForTools(Enchantment.sharpness, 5);
        // HSSE.setEnchantmentForTools(Enchantment.sharpness, 5);
        // HSSG.setEnchantmentForTools(Enchantment.sharpness, 4);
        // HSSS.setEnchantmentForTools(Enchantment.sharpness, 5);
        // Lava.setTemperatureDamage(3.0F);


        // Glue.mChemicalFormula = "No Horses were harmed for the Production";
        // UUAmplifier.mChemicalFormula = "Accelerates the Mass Fabricator";
        // WoodSealed.mChemicalFormula = "";
        // Wood.mChemicalFormula = "";

        // Naquadah.mMoltenRGBa[0] = 0;
        // Naquadah.mMoltenRGBa[1] = 255;
        // Naquadah.mMoltenRGBa[2] = 0;
        // Naquadah.mMoltenRGBa[3] = 0;
        // NaquadahEnriched.mMoltenRGBa[0] = 64;
        // NaquadahEnriched.mMoltenRGBa[1] = 255;
        // NaquadahEnriched.mMoltenRGBa[2] = 64;
        // NaquadahEnriched.mMoltenRGBa[3] = 0;
        // Naquadria.mMoltenRGBa[0] = 128;
        // Naquadria.mMoltenRGBa[1] = 255;
        // Naquadria.mMoltenRGBa[2] = 128;
        // Naquadria.mMoltenRGBa[3] = 0;

        // NaquadahEnriched.mChemicalFormula = "Nq+";
        // Naquadah.mChemicalFormula = "Nq";
        // Naquadria.mChemicalFormula = "NqX";
    }

    private static void antimatterMaterials(MaterialEvent event){
        event.setMaterial(AntimatterMaterials.Redstone).mats(of(Silicon, 1, Pyrite, 5, Ruby, 1, Mercury, 3)).asFluid(0, MaterialTags.MELTING_POINT.getInt(AntimatterMaterials.Redstone));//.setOreMulti(4);
        event.setMaterial(AntimatterMaterials.Prismarine).mats(of(Potassium, 2, Oxygen, 8, Manganese, 1, Silicon, 5));
        event.setMaterial(AntimatterMaterials.Basalt).mats(of(Olivine, 1, Calcite, 3, AntimatterMaterials.Flint, 8, DarkAsh, 4));
        event.setMaterial(AntimatterMaterials.Lapis).mats(of(Lazurite, 12, Sodalite, 2, Pyrite, 1, Calcite, 1));//.setOreMulti(6);
        event.setMaterial(AntimatterMaterials.EnderEye).asGemBasic(true, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.PLATE).mats(of(AntimatterMaterials.EnderPearl, 1, AntimatterMaterials.Blaze, 1));
        event.setMaterial(AntimatterMaterials.EnderPearl).mats(of(Beryllium, 1, Potassium, 4, Nitrogen, 5, Chlorine, 6));
        event.setMaterial(AntimatterMaterials.Diamond).asGem(true).mats(of(Carbon, 64));
        event.setMaterial(AntimatterMaterials.Emerald).asGem(true).mats(of(Beryllium, 3, Aluminium, 2, Silicon, 3, Oxygen, 18));//.addTools(3.0F, 9.0F, 590, 3);
        event.setMaterial(AntimatterMaterials.Coal).flags(AntimatterMaterialTypes.ORE_STONE).mats(of(Carbon, 2));
        event.setMaterial(AntimatterMaterials.Iron).flags(AntimatterMaterialTypes.RING, AntimatterMaterialTypes.GEAR, AntimatterMaterialTypes.FRAME);
        event.setMaterial(AntimatterMaterials.Gold).flags(AntimatterMaterialTypes.FOIL, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.WIRE_FINE, AntimatterMaterialTypes.GEAR);
        event.setMaterial(AntimatterMaterials.Copper).flags(AntimatterMaterialTypes.PLATE, AntimatterMaterialTypes.ROD, AntimatterMaterialTypes.FOIL, AntimatterMaterialTypes.WIRE_FINE, AntimatterMaterialTypes.GEAR, AntimatterMaterialTypes.BOLT);
        event.setMaterial(AntimatterMaterials.Quartz).asOre();
        event.setMaterial(AntimatterMaterials.Water).mats(of(Hydrogen, 2, Oxygen, 1));
        event.setMaterial(AntimatterMaterials.Blaze)
                .mats(of(Sulfur, 1, DarkAsh, 1/* , Magic, 1 */));
        event.setMaterial(AntimatterMaterials.Flint).flags(AntimatterMaterialTypes.ROCK);
    }
}