package muramasa.gregtech.data;

import io.github.gregtechintergalactical.gtcore.data.GTCoreBlocks;
import muramasa.antimatter.Data;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.material.SubTag;
import muramasa.gregtech.material.GregTechMaterialEvent;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.List;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.data.AntimatterMaterials.Charcoal;
import static muramasa.antimatter.material.MaterialTags.*;
import static muramasa.gregtech.data.GregTechMaterialTags.*;
import static muramasa.gregtech.data.Materials.*;

public class GregTechMaterialEvents {
    public static void onMaterialEvent(GregTechMaterialEvent event){
        flags(event);
        antimatterMaterials(event);
        byproducts(event);
        /**
         *** Periodic Table of Elements (No Isotopes)
         **/
        event.setMaterial(Hydrogen).asGas(20);
        event.setMaterial(Helium).asPlasma();
        event.setMaterial(Lithium).asSolid(454, 0).asOre().harvestLevel(2);
        event.setMaterial(Beryllium).asOre(INGOT, NUGGET);
        event.setMaterial(Boron).asDust(2349);
        event.setMaterial(Carbon).asDust(PLATE);
        event.setMaterial(Nitrogen).asPlasma();
        event.setMaterial(Oxygen).asPlasma();
        event.setMaterial(Fluorine).asGas();
        event.setMaterial(Neon).asPlasma();
        event.setMaterial(Sodium).asDust(370, MOLTEN).asFluid();
        event.setMaterial(Magnesium).asMetal(1383, 0/*1383*/);
        event.setMaterial(Aluminium).asMetal(933, 0, PLATE, ROD_LONG, SCREW, BOLT, RING, GEAR, FRAME, GEAR_SMALL, FOIL, HAS_CUSTOM_SMELTING);
        event.setMaterial(Silicon).asMetal(1683, 1683, PLATE, FOIL);
        event.setMaterial(Phosphor).asDust(933);
        event.setMaterial(Sulfur).asDust(388).asOre().harvestLevel(2).asPlasma();
        event.setMaterial(Chlorine).asGas();
        event.setMaterial(Argon).asPlasma();
        event.setMaterial(Potassium).asSolid(336, 0);
        event.setMaterial(Calcium).asDust(1115);
        //Scandium, not added
        event.setMaterial(Titanium).asMetal(1941, 1940, PLATE, ROD_LONG, SCREW, BOLT, RING, GEAR, FRAME, GEAR_SMALL, ROTOR, SPRING);
        event.setMaterial(Vanadium).asMetal(2183, 2183);
        event.setMaterial(Chromium).asMetal(2180, 1700, SCREW, BOLT, RING, PLATE, ROTOR); //aka Chromium
        event.setMaterial(Manganese).asMetal(1519, FOIL).asOre();
        //Iron, added by vanilla
        event.setMaterial(Cobalt).asMetal(1768, 0);
        event.setMaterial(Nickel).asMetal(1728, 0, PLATE).asOre().asPlasma();
        //Copper, by vanilla
        event.setMaterial(Zinc).asMetal(692, 0, PLATE, FOIL).asOre();
        event.setMaterial(Gallium).asMetal(302);
        event.setMaterial(Germanium).asMetal(1211);
        event.setMaterial(Arsenic).asMetal(1090, 1090);
        event.setMaterial(Selenium).asMetal(494, 0);
        //Bromine, not added
        event.setMaterial(Krypton).asGas();
        event.setMaterial(Rubidium).asMetal(312,0);
        event.setMaterial(Strontium).asMetal(1050, 1050);
        event.setMaterial(Yttrium).asMetal(1799, 1799);
        event.setMaterial(Zirconium).asMetal(2130,2130);
        event.setMaterial(Niobium).asMetal(2750, 2750, GEAR);
        event.setMaterial(Molybdenum).asMetal(2896, 2896).asOre();
        event.setMaterial(Technetium).asMetal(2430,2430);
        event.setMaterial(Ruthenium).asMetal(2607,2607);
        event.setMaterial(Rhodium).asMetal(2237, 2237);
        event.setMaterial(Palladium).asMetal(1828, 1828).asOre();
        event.setMaterial(Silver).asMetal(1234, 0, PLATE, SCREW).asOre().harvestLevel(2);
        event.setMaterial(Cadmium).asDust(594);
        event.setMaterial(Indium).asSolid(429, 0);
        event.setMaterial(Tin).asMetal(505, 505, PLATE, ROD_LONG, SCREW, BOLT, RING, GEAR, FOIL, WIRE_FINE, FRAME, ROTOR).asOre().harvestLevel(1);
        event.setMaterial(Antimony).asMetal(1449);
        event.setMaterial(Tellurium).asMetal(723,0);
        event.setMaterial(Iodine).asSolid(387,0);
        event.setMaterial(Xenon).asPlasma();
        event.setMaterial(Caesium).asMetal(2349, 2349);
        event.setMaterial(Barium).asDust(1000);
        event.setMaterial(Lanthanum).asMetal(1193, 1193);
        event.setMaterial(Cerium).asMetal(1068, 1068);
        event.setMaterial(Praseodymium).asMetal(1208,1208);
        event.setMaterial(Neodymium).asMetal(1297, 1297, PLATE, ROD_LONG).asOre().harvestLevel(2); // TODO: Bastnasite or Monazite for Ore For;
        event.setMaterial(Promethium).asMetal(1353,1353);
        event.setMaterial(Samarium).asMetal(1345,1345);
        event.setMaterial(Europium).asMetal(1099, 1099, PLATE);
        event.setMaterial(Gadolinium).asMetal(1585,1585);
        event.setMaterial(Terbium).asMetal(1696,1696);
        event.setMaterial(Dysprosium).asMetal(1680, 1680);
        event.setMaterial(Holmium).asMetal(1734,1734);
        event.setMaterial(Erbium).asMetal(1802,1802);
        event.setMaterial(Thulium).asMetal(1818,1818);
        event.setMaterial(Ytterbium).asMetal(1097,1097);
        event.setMaterial(Lutetium).asMetal(1925, 1925);
        event.setMaterial(Hafnium).asMetal(2506);
        event.setMaterial(Tantalum).asSolid(3290, 0);
        event.setMaterial(Tungsten).asMetal(3695, 3000, FOIL, RING, FRAME);
        event.setMaterial(Rhenium).asMetal(3459,3459);
        event.setMaterial(Osmium).asOre().asMetal(3306, 3306, SCREW, BOLT, RING, PLATE, FOIL, ROD_LONG, WIRE_FINE);
        event.setMaterial(Iridium).asMetal(2719, 2719, FRAME, PLATE).asOre();
        event.setMaterial(Platinum).asMetal(2041, 0, PLATE, FOIL, ROD_LONG, WIRE_FINE, FRAME).asOre();
        //Gold, added by vanilla
        event.setMaterial(Mercury).asFluid();
        event.setMaterial(Thallium).asMetal(577, 0);
        event.setMaterial(Lead).asMetal(600, 0, PLATE, PLATE_DENSE, FOIL, ROD_LONG, FRAME, BOLT, RING).asOre().harvestLevel(1);
        event.setMaterial(Bismuth).asOre();
        event.setMaterial(Polonium).asMetal(527, 0);
        event.setMaterial(Astatine).asMetal(575, 0);
        event.setMaterial(Radon).asGas();
        event.setMaterial(Francium).asMetal(298, 0);
        event.setMaterial(Radium).asMetal(973, 0);
        event.setMaterial(Actinium).asMetal(1323, 0);
        event.setMaterial(Thorium).asMetal(2115, PLATE_DENSE).asOre();
        event.setMaterial(Protactinium).asMetal(1841, 0);
        event.setMaterial(Uranium).asMetal(1405).harvestLevel(2);
        event.setMaterial(Neptunium).asMetal(912, 0);
        event.setMaterial(Plutonium).asMetal(912, 0);
        event.setMaterial(Americium).asMetal(1149, 0, ROD_LONG);
        event.setMaterial(Curium).asMetal(1613, 0);
        event.setMaterial(Berkelium).asMetal(1259, 0);
        event.setMaterial(Californium).asMetal(1173, 0);
        event.setMaterial(Einsteinium).asMetal(1133, 0);
        event.setMaterial(Fermium).asMetal(1125, 0);
        event.setMaterial(Mendelevium).asMetal(1111, 0);
        //Elements 101 - 118 not added

        /**
         ***  Solids
         **/
        /**
         **  Metals
         **/
        event.setMaterial(AnnealedCopper).asMetal(1357, PLATE, FOIL, ROD_LONG, WIRE_FINE, SCREW);
        event.setMaterial(BatteryAlloy).asMetal(295, PLATE);
        event.setMaterial(BismuthBronze).asMetal(1100, 900, PLATE);
        event.setMaterial(BlackBronze).asMetal(2000);
        event.setMaterial(BlackSteel).asMetal(1200, 1200, FRAME, PLATE);
        event.setMaterial(BlueSteel).asMetal(1400, 1400, FRAME);
        event.setMaterial(Brass).asMetal(1170, FRAME, ROD_LONG, PLATE);
        event.setMaterial(Bronze).asMetal(1125, GEAR, FRAME, ROTOR);
        event.setMaterial(CobaltBrass).asMetal(1500, GEAR);
        event.setMaterial(Cupronickel).asMetal(1728, PLATE);
        event.setMaterial(Duranium).asMetal(295);
        event.setMaterial(Electrum).asMetal(1330, PLATE, FOIL, ROD_LONG, WIRE_FINE);
        event.setMaterial(EnrichedNaquadah).asMetal(4500, 4500);
        event.setMaterial(HSSE).asMetal(5400, 5400, GEAR, FRAME);
        event.setMaterial(HSSG).asMetal(4500, 4500, GEAR, FRAME);
        event.setMaterial(HSSS).asMetal(5400, 5400);
        event.setMaterial(Invar).asMetal(1700, FRAME, RING);
        event.setMaterial(IronMagnetic).asMetal(1811, ROD_LONG);
        event.setMaterial(Kanthal).asMetal(1800, 1800);
        event.setMaterial(Magnalium).asMetal(870, 0, PLATE, ROD_LONG);
        event.setMaterial(NeodymiumMagnetic).asMetal(1297, 1297, ROD_LONG);
        event.setMaterial(Neutronium).asMetal(10000, 10000, SCREW, BOLT, RING, GEAR, FRAME);
        event.setMaterial(Naquadah).asMetal(5400, 5400).asOre();
        event.setMaterial(NaquadahAlloy).asMetal(7200, 7200);
        event.setMaterial(Naquadria).asMetal(9000, 9000);
        event.setMaterial(Nichrome).asMetal(2700, 2700);
        event.setMaterial(NickelZincFerrite).asMetal(1500, 1500);
        event.setMaterial(NiobiumTitanium).asMetal(4500, 4500, PLATE, FOIL, ROD_LONG, WIRE_FINE, GEAR);
        event.setMaterial(Osmiridium).asMetal(3333, 2500, FRAME);
        event.setMaterial(RedAlloy).asMetal(295, PLATE, FOIL, ROD_LONG, WIRE_FINE);
        event.setMaterial(RedSteel).asMetal(1300, 1300);
        event.setMaterial(RoseGold).asMetal(1600, WIRE_FINE);
        event.setMaterial(SolderingAlloy).asMetal(400, 400, PLATE, FOIL, ROD_LONG, WIRE_FINE);
        event.setMaterial(Steel).asMetal(1811, 1000, PLATE, ROD_LONG, SCREW, BOLT, RING, GEAR, FRAME, ROTOR, GEAR_SMALL, ITEM_CASING, WIRE_FINE);
        event.setMaterial(SteelMagnetic).asMetal(1000, 1000, ROD_LONG);
        event.setMaterial(SterlingSilver).asMetal(1700);
        event.setMaterial(StainlessSteel).asMetal(1700, 1700, PLATE, ROD_LONG, SCREW, BOLT, RING, GEAR, FRAME, ROTOR, GEAR_SMALL, ITEM_CASING);
        event.setMaterial(TinAlloy).asMetal(1158);
        event.setMaterial(Tritanium).asMetal(295, FRAME);
        event.setMaterial(TungstenCarbide).asMetal(2460, 2460);
        event.setMaterial(TungstenSteel).asMetal(3000, 3000, PLATE, ROD_LONG, SCREW, BOLT, RING, GEAR, FRAME, ROTOR, GEAR_SMALL);
        event.setMaterial(Ultimet).asMetal(2700, 2700, PLATE);
        event.setMaterial(VanadiumGallium).asMetal(4500, 4500, ROD_LONG, PLATE);
        event.setMaterial(VanadiumSteel).asMetal(1453, 1453);
        event.setMaterial(Vibranium).asMetal(295, FRAME);
        event.setMaterial(WroughtIron).asMetal(1811, PLATE, ROD_LONG, SCREW, BOLT, RING, GEAR, FRAME, ROTOR, GEAR_SMALL);
        event.setMaterial(YttriumBariumCuprate).asMetal(4500, 4500, PLATE, FOIL, ROD_LONG, WIRE_FINE);
        /**
         **  Dusts
         **/
        /**
         *  Organic
         **/
        event.setMaterial(Ash).asDust();
        event.setMaterial(AntimonyTrioxide).asDust();
        event.setMaterial(Clay).asDust();
        event.setMaterial(CupricOxide).asDust();
        event.setMaterial(DarkAsh).asDust();
        event.setMaterial(Dibenzene).asDust();
        event.setMaterial(DibenzoylPeroxide).asDust();
        event.setMaterial(GelledToluene).asDust();
        event.setMaterial(Graphene).asDust(PLATE);
        event.setMaterial(Polydimethylsiloxane).asDust();
        event.setMaterial(RareEarth).asDust();
        event.setMaterial(RawRubber).asDust(RUBBERTOOLS);
        event.setMaterial(RawStyreneButadieneRubber).asDust(RUBBERTOOLS);
        event.setMaterial(SodaAsh).asDust();
        event.setMaterial(Wood).asDust(GEAR);
        /**
         *  Inorganic
         **/
        event.setMaterial(AluminiumHydroxide).asDust();
        event.setMaterial(AluminiumTrichloride).asDust();
        event.setMaterial(Aluminosilicate).asDust();
        event.setMaterial(AmmoniumChloride).asDust();
        event.setMaterial(ArsenicTrioxide).asDust();
        event.setMaterial(Biotite).asDust();
        event.setMaterial(Bitumen).asDust().asFluid(0, 1000).flags(MOLTEN);
        event.setMaterial(BlackGranite).asDust(ROCK);
        event.setMaterial(BlueSchist).asDust(ROCK);
        event.setMaterial(BorosilicateGlass).asDust();
        event.setMaterial(Brick).asDust();
        event.setMaterial(CalciumChloride).asDust();
        event.setMaterial(CalciumSulfate).asDust();
        event.setMaterial(CobaltOxide).asDust();
        event.setMaterial(Concrete).asDust(300);
        event.setMaterial(DialuminiumTrioxide).asDust();
        event.setMaterial(Energium).asDust();
        event.setMaterial(FerricChloride).asDust();
        event.setMaterial(FerriteMixture).asDust();
        event.setMaterial(Ferrosilite).asDust();
        event.setMaterial(Fireclay).asDust();
        event.setMaterial(Fluorite).asDust();
        event.setMaterial(GreenSchist).asDust(ROCK);
        event.setMaterial(IndiumGalliumPhosphide).asDust();
        event.setMaterial(IridiumSodiumOxide).asDust();
        event.setMaterial(Kimberlite).asDust(ROCK);
        event.setMaterial(Komatiite).asDust(ROCK);
        event.setMaterial(Limestone).asDust(ROCK);
        event.setMaterial(LithiumChloride).asDust(880, MOLTEN).asFluid();
        event.setMaterial(Magnesia).asDust();
        event.setMaterial(MagnesiumCarbonate).asDust();
        event.setMaterial(MagnesiumChloride).asDust();
        event.setMaterial(Marble).asDust(ROCK);
        event.setMaterial(Massicot).asDust();
        event.setMaterial(Obsidian).asDust(ROD_LONG, PLATE);
        event.setMaterial(PlatinumGroupSludge).asDust();
        event.setMaterial(PhosphorousPentoxide).asDust();
        event.setMaterial(Potash).asDust();
        event.setMaterial(PotassiumFeldspar).asDust();
        event.setMaterial(Powellite).asOre(1, 5, true).harvestLevel(2);
        //event.setMaterial(Pyrochlore).asDust(ORE).addComposition(of(Calcium, 2, Niobium, 2, Oxygen, 7));
        event.setMaterial(Quartzite).asDust(ROCK);
        event.setMaterial(Quicklime).asDust();
        event.setMaterial(ReactionCatalyst).asDust();
        event.setMaterial(RedGranite).asDust(ROCK);
        event.setMaterial(SiliconDioxide).asDust();
        event.setMaterial(Shale).asDust(ROCK);
        event.setMaterial(Slate).asDust(ROCK);
        event.setMaterial(SodiumAluminate).asDust();
        event.setMaterial(SodiumBisulfate).asDust();
        event.setMaterial(SodiumHydroxide).asDust();
        event.setMaterial(SodiumSulfate).asDust();
        event.setMaterial(SodiumSulfide).asDust();
        event.setMaterial(TungsticAcid).asDust();
        event.setMaterial(TungstenTrioxide).asDust();
        //Nuclear

        /**
         **  Ores
         **/
        event.setMaterial(Almandine).asOre().harvestLevel(1);
        event.setMaterial(Alumina).asOre();
        event.setMaterial(Andradite).asOre();
        event.setMaterial(Hematite).asOre().harvestLevel(2);
        event.setMaterial(Bastnasite).asOre().harvestLevel(2);
        event.setMaterial(Barite).asOre().harvestLevel(2);
        event.setMaterial(Bentonite).asOre(); // TODO: Ore Gen
        event.setMaterial(BrownLimonite).asOre().harvestLevel(1);
        event.setMaterial(Calcite).asOre(MOLTEN).asFluid(0, 1612).harvestLevel(1);
        event.setMaterial(Cassiterite).asOre().harvestLevel(1);
        event.setMaterial(Chalcopyrite).asOre().harvestLevel(1);
        event.setMaterial(Cinnabar).asOre().harvestLevel(1);
        event.setMaterial(Chromite).asOre().harvestLevel(2);
        event.setMaterial(Cobaltite).asOre().harvestLevel(2);
        event.setMaterial(Sheldonite).asOre().harvestLevel(1);
        event.setMaterial(Galena).asOre().harvestLevel(3);
        event.setMaterial(Garnierite).asOre().harvestLevel(3);
        event.setMaterial(Glauconite).asOre().harvestLevel(2); // TODO: Ore Gen;
        event.setMaterial(Graphite).asDust().asOre().harvestLevel(2);
        event.setMaterial(Grossular).asOre().harvestLevel(1);
        event.setMaterial(Ilmenite).asOre().harvestLevel(3);
        event.setMaterial(Lepidolite).asOre().harvestLevel(2); // TODO: Ore Gen;
        event.setMaterial(Rutile).asOre();
        event.setMaterial(Magnesite).asOre().harvestLevel(2);
        event.setMaterial(Magnetite).asOre().harvestLevel(2);
        event.setMaterial(Malachite).asOre().harvestLevel(2);
        event.setMaterial(Molybdenite).asOre().harvestLevel(2);
        event.setMaterial(Pentlandite).asOre().harvestLevel(2);
        event.setMaterial(Phosphate).asOre().harvestLevel(1);
        event.setMaterial(Pitchblende).asOre();
        event.setMaterial(Pyrite).asOre().harvestLevel(1);
        event.setMaterial(Pyrolusite).asOre().harvestLevel(2);
        event.setMaterial(Pyrope).asOre().harvestLevel(2);
        event.setMaterial(Saltpeter).asOre();
        event.setMaterial(Scheelite).asDust(2500).asOre().harvestLevel(3);
        event.setMaterial(Soapstone).asOre().harvestLevel(1); // TODO: Ore Gen;
        event.setMaterial(Spodumene).asOre().harvestLevel(2);
        event.setMaterial(Spessartine).asOre().harvestLevel(2);
        event.setMaterial(Sphalerite).asOre().harvestLevel(1);
        event.setMaterial(Stibnite).asOre().harvestLevel(2);
        event.setMaterial(Tantalite).asOre().harvestLevel(3);
        event.setMaterial(Talc).asOre();
        event.setMaterial(Tetrahedrite).asOre().harvestLevel(2);
        event.setMaterial(Tungstate).asOre().harvestLevel(3);
        event.setMaterial(Uraninite).asOre().harvestLevel(2);
        event.setMaterial(Uvarovite).asOre();
        event.setMaterial(VanadiumMagnetite).asOre().harvestLevel(2);
        event.setMaterial(Wulfenite).asOre().harvestLevel(3);
        event.setMaterial(YellowLimonite).asOre().harvestLevel(2);
        event.setMaterial(Zircon).asDust();
        /**
         **  Ore Stones
         **/
        event.setMaterial(Bauxite).asOreStone(ORE_SMALL).harvestLevel(1);
        event.setMaterial(Lignite).asGemBasic(false).asOreStone(0, 2, ORE_SMALL);
        event.setMaterial(OilShale).asDust(ORE, ORE_STONE, ORE_SMALL, RAW_ORE, RAW_ORE_BLOCK, ROCK).setExpRange(1,5).harvestLevel(1);
        event.setMaterial(RockSalt).asOreStone(ORE_SMALL).harvestLevel(1);
        event.setMaterial(Salt).asOreStone(ORE_SMALL).harvestLevel(1);
        /**
         **  Gems
         **/
        /**
         *  Regular
         **/
        event.setMaterial(Amber).asGem(false).asOre(3, 7, true);
        event.setMaterial(Amethyst).asGem(false).replaceItem(GEM_FLAWED, Items.AMETHYST_SHARD).asOre(3, 7, true);
        event.setMaterial(Sapphire).asGem(true).asOre(3, 7, true);
        event.setMaterial(BlueTopaz).asGem(true).asOre(3, 7, true);
        event.setMaterial(Glass).asFluid(0, 1500).asDust(PLATE, LENS, MOLTEN);
        event.setMaterial(GreenSapphire).asGem(true).asOre(3, 7, true);
        event.setMaterial(Jade).asGem(false).asOre(3, 7, true);
        event.setMaterial(Lazurite).asGemBasic(false, PLATE).asOre(2, 5, true).harvestLevel(1);
        event.setMaterial(LigniteCoke).asGemBasic(false);
        event.setMaterial(PetroleumCoke).asGemBasic(false);
        event.setMaterial(Olivine).asGem(false, PLATE).asOre(3, 7, true);
        event.setMaterial(Opal).asGem(true).asOre(3, 7, true);
        event.setMaterial(TricalciumPhosphate).asGemBasic(false).asOre(3, 7, true).harvestLevel(2);
        event.setMaterial(MilkyQuartz).asGemBasic(false, ROCK, ROD_LONG, QUARTZ_LIKE_BLOCKS).asOre(2, 5, true).harvestLevel(1);
        event.setMaterial(RedGarnet).asGem(true).asOre(3, 7, true);
        event.setMaterial(Ruby).asGem(true).asOre(3, 7, true);
        event.setMaterial(Sodalite).asGemBasic(false).asOre(2, 5, true).harvestLevel(1);
        event.setMaterial(Tanzanite).asGem(false).asOre(3, 7, true);
        event.setMaterial(Topaz).asGem(false).asOre(3, 7, true);
        event.setMaterial(YellowGarnet).asGem(true).asOre(3, 7, true);
        /**
         *  Basic
         **/
        event.setMaterial(CertusQuartz).asGemBasic(false,PLATE, QUARTZ_LIKE_BLOCKS).asOre(1, 5, true);
        event.setMaterial(Fluix).asGemBasic(false, QUARTZ_LIKE_BLOCKS);
        event.setMaterial(Charcoal).asGemBasic(false);
        event.setMaterial(CoalCoke).asGemBasic(false);
        event.setMaterial(Dilithium).asGemBasic(true);
        event.setMaterial(Apatite).asGemBasic(false, PLATE).asOre(2, 5, true).harvestLevel(1);
        event.setMaterial(Monazite).asGemBasic(false, PLATE).asOre(3, 7, true).harvestLevel(1);
        event.setMaterial(NetherStar).replaceItem(GEM, Items.NETHER_STAR).asGemBasic(false);
        /**
         **  Plastic Related
         **/
        event.setMaterial(EpoxyResin).asSolid(400, 0, PLATE, MOLTEN);
        event.setMaterial(FiberReinforcedEpoxyResin).asSolid(400, 0, PLATE);
        event.setMaterial(Polycaprolactam).asSolid(500, 0);
        event.setMaterial(Plastic).asSolid(295, 0, PLATE, FOIL).asFluid();
        event.setMaterial(PolyphenyleneSulfide).asSolid(295, 0, PLATE, FOIL);
        event.setMaterial(Polystyrene).asSolid(295, 0);
        event.setMaterial(Polytetrafluoroethylene).asSolid(1400, 0, PLATE, FRAME, ROD_LONG);
        event.setMaterial(PolyvinylChloride).asSolid(295, 0, PLATE, FOIL);
        event.setMaterial(Rubber).asSolid(295, 0, PLATE, RING, MOLTEN);
        event.setMaterial(Silicone).asSolid(900, 0, PLATE, FOIL);
        event.setMaterial(StyreneButadieneRubber).asSolid(295, 0, PLATE, RING);
        /**
         **  Misc
         **/
        event.setMaterial(GalliumArsenide).asSolid(295, 1200);
        event.setMaterial(Superconductor).asSolid(PLATE);
        /**
         ***  Fluids
         **/
        /**
         *  Organic
         **/
        event.setMaterial(Acetone).asFluid();
        event.setMaterial(CharcoalByproducts).asGas();
        event.setMaterial(EthylTertButylEther).asFluid();
        event.setMaterial(FermentedBiomass).asFluid(16);
        event.setMaterial(SeedOil).asFluid(2).flags(SEMIFUELS);
        event.setMaterial(WoodTar).asFluid();
        event.setMaterial(WoodVinegar).asFluid();
        //Alkanoles
        event.setMaterial(Methanol).asFluid(84);
        event.setMaterial(Ethanol).asFluid(148);
        event.setMaterial(Propanol).asFluid(196);
        event.setMaterial(Butanol).asFluid(166);
        event.setMaterial(Heptanol).asFluid(226);
        //Alkenoles
        event.setMaterial(Ethenol).asFluid(120);
        event.setMaterial(Propenol).asFluid(196);
        event.setMaterial(Butenol).asFluid(186);
        //Alkanedioles
        event.setMaterial(Ethanediol).asFluid(216);
        event.setMaterial(Propanediol).asFluid(256);
        event.setMaterial(Butanediol).asFluid(286);
        //Plastic Related
        event.setMaterial(SiliconeRubber).asFluid();
        //Misc
        event.setMaterial(AceticAcid).asFluid();
        event.setMaterial(AllylChloride).asFluid();
        event.setMaterial(Benzaldehyde).asFluid();
        event.setMaterial(Benzene).asFluid(288);
        event.setMaterial(BenzoylChloride).asFluid();
        event.setMaterial(Biomass).asFluid(8).flags(SEMIFUELS);
        event.setMaterial(BisphenolA).asFluid();
        event.setMaterial(Chloramine).asFluid();
        event.setMaterial(Chloroform).asFluid();
        event.setMaterial(Cumene).asFluid();
        event.setMaterial(Chlorobenzene).asFluid();
        event.setMaterial(Dichlorobenzene).asFluid();
        event.setMaterial(Dichloroethane).asFluid();
        event.setMaterial(Dimethyldichlorosilane).asFluid();
        event.setMaterial(Dimethylhydrazine).asFluid();
        event.setMaterial(Epichlorohydrin).asFluid();
        event.setMaterial(Glue).asFluid();
        event.setMaterial(Glycerol).asFluid(164);
        event.setMaterial(GlycerylTrinitrate).asFluid();
        event.setMaterial(Honey).asFluid(); // TODO: Only when Forestry's present;
        event.setMaterial(Isoprene).asFluid();
        event.setMaterial(MethylAcetate).asFluid();
        event.setMaterial(Naphtha).asFluid(256);
        event.setMaterial(Phenol).asFluid(288);
        event.setMaterial(PolyvinylAcetate).asFluid();
        event.setMaterial(SquidInk).asFluid();
        event.setMaterial(Styrene).asFluid();
        event.setMaterial(SulfuricNaphtha).asFluid(32);
        event.setMaterial(Tetranitromethane).asFluid();
        event.setMaterial(Toluene).asFluid(328);
        event.setMaterial(VinylAcetate).asFluid();
        /**
         *  Inorganic
         **/
        event.setMaterial(AluminiumFluoride).asFluid(0,1560).flags(MOLTEN, ACID);
        event.setMaterial(Antimatter).asFluid();
        event.setMaterial(BlueVitriol).asFluid();
        event.setMaterial(GreenVitriol).asFluid();
        event.setMaterial(RedVitriol).asFluid();
        event.setMaterial(PinkVitriol).asFluid();
        event.setMaterial(CyanVitriol).asFluid();
        event.setMaterial(WhiteVitriol).asFluid();
        event.setMaterial(GrayVitriol).asFluid();
        event.setMaterial(MartianVitriol).asFluid();
        event.setMaterial(VitriolOfClay).asFluid();
        event.setMaterial(AquaRegia).asFluid().flags(ACID);
        event.setMaterial(CalciumAcetateSolution).asFluid();
        event.setMaterial(ChloroplatinicAcid).asFluid().flags(ACID);
        event.setMaterial(Coolant).asFluid();
        event.setMaterial(Cryolite).asFluid(0, 1285).flags(MOLTEN, ACID);
        event.setMaterial(DistilledWater).asFluid();
        event.setMaterial(DilutedHydrochloricAcid).asFluid().flags(ACID);
        event.setMaterial(DilutedSulfuricAcid).asFluid().flags(ACID);
        event.setMaterial(DrillingFluid).asFluid(); // TODO: Perhaps for a bedrock drill;
        event.setMaterial(HexafluorosilicicAcid).asFluid().flags(ACID);
        event.setMaterial(HotCoolant).asFluid(0,500);
        event.setMaterial(HeavyWater).asFluid();
        event.setMaterial(SemiheavyWater).asFluid();
        event.setMaterial(TritiatedWater).asFluid();
        event.setMaterial(HotHeavyWater).asFluid(0, 600);
        event.setMaterial(HotSemiheavyWater).asFluid(0, 550);
        event.setMaterial(HotTritiatedWater).asFluid(0, 650);
        event.setMaterial(HotMoltenSodium).asFluid(0, 1100).flags(MOLTEN);
        event.setMaterial(HotMoltenTin).asFluid(0, 2800).flags(MOLTEN);
        event.setMaterial(HotMoltenLithiumChloride).asFluid(0, 1600).flags(MOLTEN);
        event.setMaterial(HydrochloricAcid).asFluid().flags(ACID);
        event.setMaterial(HydrofluoricAcid).asFluid().flags(ACID);
        event.setMaterial(HydrogenFluoride).asGas().flags(ACID);
        event.setMaterial(HydrogenPeroxide).asFluid();
        event.setMaterial(HypochlorousAcid).asFluid().flags(ACID);
        event.setMaterial(IndiumConcentrate).asFluid();

        event.setMaterial(LeadZincSolution).asFluid();
        event.setMaterial(LiquidAir).asFluid(0, 79);
        event.setMaterial(Lubricant).asFluid();
        event.setMaterial(NickelSulfate).asFluid();
        event.setMaterial(NitrationMixture).asFluid();
        event.setMaterial(NitricAcid).asFluid().flags(ACID);
        event.setMaterial(PeroxydisulfuricAcid).asFluid().flags(ACID);
        event.setMaterial(PhosphoricAcid).asFluid().flags(ACID);
        event.setMaterial(SaltWater).asFluid();
        event.setMaterial(SodiumPersulfate).asFluid();
        event.setMaterial(SodiumBicarbonateSolution).asFluid();
        event.setMaterial(SodiumCarbonateSolution).asFluid();
        event.setMaterial(Steam).asGas(1, 395);
        event.setMaterial(SuperheatedSteam).asGas(2, 600);
        event.setMaterial(SulfuricAcid).asFluid().flags(ACID);
        event.setMaterial(SulfurTrioxide).asGas();
        event.setMaterial(SulfurDioxide).asGas();
        event.setMaterial(Tar).asFluid();
        event.setMaterial(TitaniumTetrachloride).asFluid().flags(ACID);
        event.setMaterial(UUAmplifier).asFluid();
        event.setMaterial(UUMatter).asFluid();
        //Nuclear
        /**
         ***  Gases/Plasmas
         **/
        /**
         *  Organic
         **/
        //Alkanes
        event.setMaterial(Methane).asGas(104);
        event.setMaterial(Ethane).asGas(168);
        event.setMaterial(Propane).asGas(232);
        event.setMaterial(Butane).asGas(296);
        //Alkenes
        event.setMaterial(Ethylene).asGas(128);
        event.setMaterial(Propene).asGas(192);
        event.setMaterial(Butene).asGas(256);
        event.setMaterial(Butadiene).asGas(206);
        //Ketones
        event.setMaterial(Ethenone).asGas();
        //Misc
        event.setMaterial(HotCarbonDioxide).asGas();
        event.setMaterial(CarbonDioxide).asGas();
        event.setMaterial(CarbonMonoxide).asGas(24);
        event.setMaterial(Chloromethane).asGas();
        event.setMaterial(Dimethylamine).asGas();
        event.setMaterial(LPG).asFluid(256);
        event.setMaterial(NaturalGas).asGas(15);
        event.setMaterial(RefineryGas).asGas(128);
        event.setMaterial(SulfuricGas).asGas(20);
        event.setMaterial(Tetrafluoroethylene).asGas();
        event.setMaterial(VinylChloride).asGas();
        event.setMaterial(WoodGas).asGas(24);
        /**
         *  Inorganic
         **/
        event.setMaterial(Air).asGas();
        event.setMaterial(Ammonia).asGas().flags(ACID);
        event.setMaterial(DinitrogenTetroxide).asGas();
        event.setMaterial(HydrogenSulfide).asGas();
        event.setMaterial(NitricOxide).asGas();
        event.setMaterial(NitrousOxide).asGas();
        event.setMaterial(NitrogenDioxide).asGas();
        event.setMaterial(NobleGases).asGas(0,790);
        event.setMaterial(SulfurDioxide).asGas();
        event.setMaterial(SulfurTrioxide).asGas(0,344);
        /**
         ** Fuels
         **/
        event.setMaterial(Creosote).asFluid(8).flags(SEMIFUELS);
        event.setMaterial(Oil).asFluid(16).flags(SEMIFUELS);
        event.setMaterial(OilHeavy).asFluid(32).flags(SEMIFUELS);
        event.setMaterial(OilLight).asFluid(16).flags(SEMIFUELS);
        event.setMaterial(BioDiesel).asFluid(192);
        event.setMaterial(Diesel).asFluid(128);
        event.setMaterial(FishOil).asFluid(2).flags(SEMIFUELS);
        event.setMaterial(HeavyFuel).asFluid(48);
        event.setMaterial(LightFuel).asFluid(48);
        event.setMaterial(CetaneBoostedDiesel).asFluid(512);
        event.setMaterial(FuelOil).asFluid(448);
        event.setMaterial(Gasoline).asFluid(384);
        event.setMaterial(Kerosene).asFluid(384);
        event.setMaterial(HighOctaneGasoline).asFluid(768);
        event.setMaterial(SulfuricLightFuel).asFluid(32);
        event.setMaterial(SulfuricHeavyFuel).asFluid(32).flags(SEMIFUELS);
        /**
         ** Cracked Stuff
         */
        event.setMaterial(SteamCrackedEthane).asGas();
        event.setMaterial(SteamCrackedPropane).asGas();
        event.setMaterial(SteamCrackedButane).asGas();
        event.setMaterial(SteamCrackedLightFuel).asFluid();
        event.setMaterial(SteamCrackedHeavyFuel).asFluid();
        event.setMaterial(SteamCrackedNaphtha).asFluid();
        event.setMaterial(SteamCrackedRefineryGas).asGas();
        event.setMaterial(HydroCrackedEthane).asGas();
        event.setMaterial(HydroCrackedPropane).asGas();
        event.setMaterial(HydroCrackedButane).asGas();
        event.setMaterial(HydroCrackedLightFuel).asFluid();
        event.setMaterial(HydroCrackedHeavyFuel).asFluid();
        event.setMaterial(HydroCrackedNaphtha).asFluid();
        event.setMaterial(HydroCrackedRefineryGas).asGas();
        processInto(event);
        nuclearIsotopes(event);
        toolsAndArmor(event);
        workbenches(event);
    }

    private static void processInto(GregTechMaterialEvent event){
        /**
         ***  Solids
         **/
        /**
         **  Metals
         **/
        event.setMaterial(AnnealedCopper).mats(of(Copper, 1));
        event.setMaterial(BatteryAlloy).mats(of(Lead, 4, Antimony, 1));
        event.setMaterial(BismuthBronze).mats(of(Bismuth, 1, Zinc, 1, Copper, 3));
        event.setMaterial(BlackBronze).mats(of(Gold, 1, Silver, 1, Copper, 3));
        event.setMaterial(BlackSteel).mats(of(Nickel, 1, BlackBronze, 1, Steel, 3));
        event.setMaterial(BlueSteel).mats(of(RoseGold, 1, Brass, 1, Steel, 2, BlackSteel, 4));
        event.setMaterial(Brass).mats(of(Zinc, 1, Copper, 3));
        event.setMaterial(Bronze).mats(of(Tin, 1, Copper, 3));
        event.setMaterial(CobaltBrass).mats(of(Brass, 7, Aluminium, 1, Cobalt, 1));
        event.setMaterial(Cupronickel).mats(of(Copper, 1, Nickel, 1));
        event.setMaterial(Electrum).mats(of(Silver, 1, Gold, 1));
        event.setMaterial(HSSE).mats(of(HSSG, 6, Cobalt, 1, Manganese, 1, Silicon, 1));
        event.setMaterial(HSSG).mats(of(TungstenSteel, 5, Chromium, 1, Molybdenum, 2, Vanadium, 1));
        event.setMaterial(HSSS).mats(of(HSSG, 6, Iridium, 2, Osmium, 1));
        event.setMaterial(Invar).mats(of(Iron, 2, Nickel, 1));
        event.setMaterial(IronMagnetic).mats(of(Iron, 1)).elecTicks(52);
        event.setMaterial(Kanthal).mats(of(Iron, 1, Aluminium, 1, Chromium, 1));
        event.setMaterial(Magnalium).mats(of(Magnesium, 1, Aluminium, 2));
        event.setMaterial(NeodymiumMagnetic).mats(of(Neodymium, 1)).elecTicks(122);
        event.setMaterial(Nichrome).mats(of(Nickel, 4, Chromium, 1));
        event.setMaterial(NickelZincFerrite).mats(of(Nickel, 1, Zinc, 1, Iron, 4, Oxygen, 8));
        event.setMaterial(NiobiumTitanium).mats(of(Nickel, 4, Chromium, 1));
        event.setMaterial(Osmiridium).mats(of(Iridium, 3, Osmium, 1)).elecTicks(608);
        event.setMaterial(RedAlloy).mats(of(Copper, 1, Redstone, 4));
        event.setMaterial(RedSteel).mats(of(SterlingSilver, 1, BismuthBronze, 1, Steel, 2, BlackSteel, 4));
        event.setMaterial(RoseGold).mats(of(Copper, 1, Gold, 4));
        event.setMaterial(SolderingAlloy).mats(of(Tin, 9, Antimony, 1));
        event.setMaterial(Steel).mats(of(Iron, 50, Carbon, 1), 50).elecTicks(2600);
        event.setMaterial(SteelMagnetic).mats(of(Steel, 1)).elecTicks(52);
        event.setMaterial(SterlingSilver).mats(of(Copper, 1, Silver, 4));
        event.setMaterial(StainlessSteel).mats(of(Iron, 6, Chromium, 1, Manganese, 1, Nickel, 1)).elecTicks(450);
        event.setMaterial(TinAlloy).mats(of(Tin, 1, Iron, 1));
        event.setMaterial(TungstenCarbide).mats(of(Tungsten, 1, Carbon, 1));
        event.setMaterial(TungstenSteel).mats(of(Steel, 1, Tungsten, 1));
        event.setMaterial(Ultimet).mats(of(Cobalt, 5, Chromium, 2, Nickel, 1, Molybdenum, 1)).elecTicks(504);
        event.setMaterial(VanadiumGallium).mats(of(Vanadium, 3, Gallium, 1));
        event.setMaterial(VanadiumSteel).mats(of(Vanadium, 1, Chromium, 1, Steel, 7));
        event.setMaterial(WroughtIron).mats(of(Iron, 1));
        event.setMaterial(YttriumBariumCuprate).mats(of(Yttrium, 1, Barium, 2, Copper, 3, Oxygen, 7));
        /**
         **  Dusts
         **/
        /**
         *  Organic
         **/
        event.setMaterial(AntimonyTrioxide).mats(of(Antimony, 2, Oxygen, 3)).elecTicks(250);
        event.setMaterial(Clay).mats(of(Alumina, 5, SiliconDioxide, 12, Water, 1, Sodium, 1, Lithium, 1), 18).elecTicks(182);
        event.setMaterial(CupricOxide).mats(of(Copper, 1, Oxygen, 1)).elecTicks(72);
        event.setMaterial(DarkAsh).mats(of(Carbon, 1, Ash, 1), 1);
        event.setMaterial(Dibenzene).mats(of(Carbon,12,Hydrogen,10));
        event.setMaterial(DibenzoylPeroxide).mats(of(Carbon,14,Hydrogen,10,Oxygen,4));
        event.setMaterial(Polydimethylsiloxane).mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 1, Silicon, 1)).elecTicks(80);
        event.setMaterial(RawRubber).mats(of(Carbon, 5, Hydrogen, 8));
        event.setMaterial(RawStyreneButadieneRubber).mats(of(Styrene, 1, Butadiene, 3));
        event.setMaterial(SodaAsh).mats(of(Sodium, 2, Carbon, 1, Oxygen, 3)).elecTicks(96);
        event.setMaterial(Wood).mats(of(Carbon, 1, Oxygen, 1, Hydrogen, 1));
        /**
         *  Inorganic
         **/
        event.setMaterial(AluminiumHydroxide).mats(of(Aluminium, 1, Oxygen, 3, Hydrogen, 3));
        event.setMaterial(AluminiumTrichloride).mats(of(Aluminium,1,Chlorine,3));
        event.setMaterial(Aluminosilicate).mats(of(Aluminium, 2, Silicon, 1, Oxygen, 5));
        event.setMaterial(AmmoniumChloride).mats(of(Nitrogen,1, Hydrogen,4,Chlorine,1));
        event.setMaterial(ArsenicTrioxide).mats(of(Arsenic, 2, Oxygen, 3)).elecTicks(180);
        event.setMaterial(Biotite).mats(b -> b.put(Potassium, 2).put(Magnesium, 6).put(Alumina, 15).put(SiliconDioxide, 18).put(Fluorine, 4)).elecTicks(440);
        event.setMaterial(BlackGranite).mats(of(Biotite, 1, PotassiumFeldspar, 1, Flint, 1));
        event.setMaterial(BorosilicateGlass).mats(of(Boron, 1, Silicon, 7, Oxygen,14));
        event.setMaterial(Brick).mats(of(Aluminium, 4, Silicon, 3, Oxygen, 12));
        event.setMaterial(CalciumChloride).mats(of(Calcium, 1, Chlorine, 2)).elecTicks(1024);
        event.setMaterial(CalciumSulfate).mats(of(Calcium, 1, SiliconDioxide, 1, Oxygen, 2)).elecTicks(1460);
        event.setMaterial(CobaltOxide).mats(of(Cobalt, 1, Oxygen, 1)).elecTicks(68);
        event.setMaterial(Concrete).mats(of(Stone, 1)).asFluid();
        event.setMaterial(DialuminiumTrioxide).mats(of(Aluminium,2,Oxygen,3));
        event.setMaterial(Energium).mats(of(Redstone,5,Ruby,4));
        event.setMaterial(FerricChloride).mats(of(Iron, 1, Chlorine, 3)).elecTicks(876);
        event.setMaterial(FerriteMixture).mats(of(Nickel, 1, Zinc, 1, Iron, 4));
        event.setMaterial(Ferrosilite).mats(of(Iron, 1, Silicon, 1, Oxygen, 3)).elecTicks(120);
        event.setMaterial(Fireclay).mats(of(Brick, 1));
        event.setMaterial(Fluorite).mats(of(Calcium, 1, Fluorine, 2));
        event.setMaterial(IndiumGalliumPhosphide).mats(of(Indium, 1, Gallium, 1, Phosphor, 1));
        event.setMaterial(IridiumSodiumOxide).mats(of(Iridium,1,Sodium,1,Oxygen,2));
        event.setMaterial(Komatiite).mats(of(Olivine, 1, MagnesiumCarbonate, 2, Flint, 6, DarkAsh, 3));
        event.setMaterial(Limestone).mats(of(Calcite, 1));
        event.setMaterial(LithiumChloride).mats(of(Lithium, 1, Chlorine, 1)).elecTicks(64);
        event.setMaterial(Magnesia).mats(of(Magnesium, 1, Oxygen, 1)).elecTicks(40);
        event.setMaterial(MagnesiumChloride).mats(of(Magnesium, 1, Chlorine, 2)).elecTicks(1024);
        event.setMaterial(Marble).mats(of(Magnesium, 1, Calcite, 7));
        event.setMaterial(Massicot).mats(of(Lead, 1, Oxygen, 1)).elecTicks(180);
        event.setMaterial(Obsidian).mats(of(Magnesium, 1, Iron, 1, Silicon, 2, Oxygen, 8)).elecTicks(240);
        event.setMaterial(PhosphorousPentoxide).mats(of(Phosphor, 4, Oxygen, 10)).elecTicks(560);
        event.setMaterial(Potash).mats(of(Potassium, 2, Oxygen, 1)).elecTicks(90);
        event.setMaterial(PotassiumFeldspar).mats(of(Potassium, 2, Alumina, 5, SiliconDioxide, 18, Oxygen, 1)).elecTicks(260);
        event.setMaterial(Powellite).mats(of(Calcium, 1, Molybdenum, 1, Oxygen, 4));
        //event.setMaterial(Pyrochlore).asDust(ORE).addComposition(of(Calcium, 2, Niobium, 2, Oxygen, 7));
        event.setMaterial(Quartzite).mats(of(Silicon, 1, Oxygen, 2)).elecTicks(60);
        event.setMaterial(Quicklime).mats(of(Calcium, 1, Oxygen, 1)).elecTicks(56);
        event.setMaterial(ReactionCatalyst).mats(of(Copper,1,Zinc,1,Aluminium,2,Oxygen,4));
        event.setMaterial(RedGranite).mats(of(Biotite, 1, PotassiumFeldspar, 1, Flint, 1)).elecTicks(120);
        event.setMaterial(SiliconDioxide).mats(of(Silicon, 1, Oxygen, 2)).elecTicks(240);
        event.setMaterial(Shale).mats(of(Calcite, 2, MilkyQuartz, 1, Clay, 1));
        //event.setMaterial(Slate).mats(of())
        event.setMaterial(SodiumAluminate).mats(of(Sodium, 1, Aluminium, 1, Oxygen, 2));
        event.setMaterial(SodiumBisulfate).mats(of(Sodium, 1, Hydrogen, 1, Sulfur, 1, Oxygen, 4)).elecTicks(600);
        event.setMaterial(SodiumHydroxide).mats(of(Sodium, 1, Oxygen, 1, Hydrogen, 1)).elecTicks(36);
        event.setMaterial(SodiumSulfate).mats(of(Sodium, 2, Sulfur, 1, Oxygen, 4));
        event.setMaterial(SodiumSulfide).mats(of(Sodium, 2, Sulfur, 1)).elecTicks(72);
        event.setMaterial(TungsticAcid).mats(of(Hydrogen, 2, Tungsten, 1, Oxygen, 4));
        event.setMaterial(TungstenTrioxide).mats(of(Tungsten, 1, Oxygen, 3));
        //Nuclear

        /**
         **  Ores
         **/
        event.setMaterial(Almandine).mats(of(Alumina, 5, Iron, 3, SiliconDioxide, 9, Oxygen, 3)).elecTicks(480);
        event.setMaterial(Alumina).mats(of(Aluminium, 2, Oxygen, 3)).elecTicks(480);
        event.setMaterial(Andradite).mats(of(Calcium, 3, Iron, 2, Silicon, 3, Oxygen, 12)).elecTicks(480);
        event.setMaterial(Hematite).mats(of(Iron, 2, Oxygen, 3)).elecTicks(150);
        event.setMaterial(Bastnasite).mats(of(Cerium, 1, Carbon, 1, Fluorine, 1, Oxygen, 3)).elecTicks(192);
        event.setMaterial(Barite).mats(of(Barium, 1, Sulfur, 1, Oxygen, 4)).elecTicks(204);
        event.setMaterial(Bentonite).mats(b -> b.put(Sodium, 1).put(Magnesium, 6).put(Silicon, 12).put(Hydrogen, 6).put(Water, 5).put(Oxygen, 36)).elecTicks(480); // TODO: Ore Gen
        event.setMaterial(BrownLimonite).mats(of(Iron, 1, Hydrogen, 1, Oxygen, 2));
        event.setMaterial(Calcite).mats(of(Calcium, 1, Carbon, 1, Oxygen, 3)).elecTicks(100);
        event.setMaterial(Cassiterite).mats(of(Tin, 1, Oxygen, 2), 1).elecTicks(132);
        event.setMaterial(Chalcopyrite).mats(of(Copper, 1, Iron, 1, Sulfur, 2)).elecTicks(168);
        event.setMaterial(Cinnabar).mats(of(Mercury, 1, Sulfur, 1));
        event.setMaterial(Chromite).mats(of(Iron, 1, Chromium, 2, Oxygen, 4)).elecTicks(210);
        event.setMaterial(Cobaltite).mats(of(Cobalt, 1, Arsenic, 1, Sulfur, 1)).elecTicks(150);
        event.setMaterial(Sheldonite).mats(of(Platinum, 3, Nickel, 1, Sulfur, 1, Palladium, 1));
        event.setMaterial(Galena).mats(of(Lead, 3, Silver, 3, Sulfur, 2)).elecTicks(832);
        event.setMaterial(Garnierite).mats(of(Nickel, 1, Oxygen, 1), 1).elecTicks(72);
        event.setMaterial(Graphite).mats(of(Carbon, 4), 1).elecTicks(100);
        event.setMaterial(Glauconite).mats(of(Alumina, 10, Potassium, 1, Magnesium, 2, Water, 3, Oxygen, 7)).elecTicks(378); // TODO: Ore Gen;
        event.setMaterial(Grossular).mats(of(Alumina, 5, Calcium, 3, SiliconDioxide, 9, Oxygen, 3)).elecTicks(440);
        event.setMaterial(Ilmenite).mats(of(Iron, 1, Titanium, 1, Oxygen, 3));
        event.setMaterial(Lepidolite).mats(of(Alumina, 10, Potassium, 1, Lithium, 3, Fluorine, 2, Oxygen, 6)).elecTicks(320); // TODO: Ore Gen;
        event.setMaterial(Rutile).mats(of(Cobalt, 1, Arsenic, 1, Sulfur, 1));
        event.setMaterial(Magnesite).mats(of(Magnesium, 1, Carbon, 1, Oxygen, 3)).elecTicks(80);
        event.setMaterial(Magnetite).mats(of(Iron, 3, Oxygen, 4)).elecTicks(210);
        event.setMaterial(Malachite).mats(of(Copper, 2, Carbon, 1, Hydrogen, 2, Oxygen, 5)).elecTicks(200);
        event.setMaterial(Molybdenite).mats(of(Molybdenum, 1, Sulfur, 2)).elecTicks(144);
        event.setMaterial(Pentlandite).mats(of(Nickel, 9, Sulfur, 8)).elecTicks(748);
        event.setMaterial(Phosphate).mats(of(Phosphor, 1, Oxygen, 4)).elecTicks(360);
        event.setMaterial(Pitchblende).mats(of(Uraninite, 3, Thorium, 1, Lead, 1));
        event.setMaterial(Pyrite).mats(of(Iron, 1, Sulfur, 2)).elecTicks(114);
        event.setMaterial(Pyrolusite).mats(of(Manganese, 1, Oxygen, 2)).elecTicks(78);
        event.setMaterial(Pyrope).mats(of(Alumina, 5, Magnesium, 3, SiliconDioxide, 9, Oxygen, 3)).elecTicks(400);
        event.setMaterial(Saltpeter).mats(of(Potassium, 1, Nitrogen, 1, Oxygen, 3)).elecTicks(100);
        event.setMaterial(Scheelite).mats(of(Tungsten, 1, Calcium, 2, Oxygen, 4)).elecTicks(120);
        event.setMaterial(Soapstone).mats(of(Magnesium, 3, Silicon, 4, Hydrogen, 2, Oxygen, 12)).elecTicks(378); // TODO: Ore Gen;
        event.setMaterial(Spodumene).mats(of(Lithium, 2, Alumina, 5, SiliconDioxide, 12, Oxygen, 1)).elecTicks(180);
        event.setMaterial(Spessartine).mats(of(Alumina, 5, Manganese, 3, SiliconDioxide, 9, Oxygen, 3)).elecTicks(440);
        event.setMaterial(Sphalerite).mats(of(Zinc, 1, Sulfur, 1)).elecTicks(92);
        event.setMaterial(Stibnite).mats(of(Antimony, 2, Sulfur, 3));
        event.setMaterial(Tantalite).mats(of(Manganese, 1, Tantalum, 2, Oxygen, 6)).elecTicks(432);
        event.setMaterial(Talc).mats(of(Magnesium, 3, Silicon, 4, Hydrogen, 2, Oxygen, 12)).elecTicks(378);
        event.setMaterial(Tetrahedrite).mats(of(Copper, 3, Antimony, 1, Sulfur, 3, Iron, 1));
        event.setMaterial(Tungstate).mats(of(Tungsten, 1, Lithium, 2, Oxygen, 4)).elecTicks(120);
        event.setMaterial(Uraninite).mats(of(Uranium, 1, Oxygen, 2));
        event.setMaterial(Uvarovite).mats(of(Calcium, 3, Chromium, 2, Silicon, 3, Oxygen, 12)).elecTicks(480);
        event.setMaterial(VanadiumMagnetite).mats(of(Magnetite, 1, Vanadium, 1));
        event.setMaterial(Wulfenite).mats(of(Lead, 1, Molybdenum, 1, Oxygen, 4));
        event.setMaterial(YellowLimonite).mats(of(Iron, 1, Hydrogen, 1, Oxygen, 2));
        event.setMaterial(Zircon).mats(of(Zirconium, 1, SiliconDioxide, 3, Oxygen, 2)).elecTicks(1740);
        /**
         **  Ore Stones
         **/
        event.setMaterial(Bauxite).mats(of(Rutile, 2, Aluminium, 16, Hydrogen, 10, Oxygen, 11));
        event.setMaterial(Lignite).mats(of(Carbon, 3, Water, 1)).elecTicks(40);
        event.setMaterial(RockSalt).mats(of(Potassium, 1, Chlorine, 1)).elecTicks(72);
        event.setMaterial(Salt).mats(of(Sodium, 1, Chlorine, 1)).elecTicks(320);
        /**
         **  Gems
         **/
        /**
         *  Regular
         **/
        event.setMaterial(Amethyst).mats(of(SiliconDioxide, 4, Iron, 1)).elecTicks(130);
        event.setMaterial(Sapphire).mats(of(Alumina, 5, Iron, 1)).elecTicks(100);
        event.setMaterial(BlueTopaz).mats(of(Alumina, 5, SiliconDioxide, 3, Fluorine, 2, Water, 3)).elecTicks(208);
        event.setMaterial(Glass).mats(of(SiliconDioxide, 1));
        event.setMaterial(GreenSapphire).mats(of(Alumina, 5, Magnesium, 1)).elecTicks(100);
        event.setMaterial(Lazurite).mats(of(Alumina, 3, SiliconDioxide, 3, Calcium, 4, Sodium, 4)).elecTicks(392);
        event.setMaterial(Olivine).mats(of(Magnesium, 2, Iron, 1, SiliconDioxide, 2)).elecTicks(140);
        event.setMaterial(Opal).mats(of(SiliconDioxide, 1)).elecTicks(20);
        event.setMaterial(TricalciumPhosphate).mats(of(Calcium, 3, Phosphate, 2));
        event.setMaterial(MilkyQuartz).mats(of(Silicon, 1, Oxygen, 2)).elecTicks(60);
        event.setMaterial(RedGarnet).mats(of(Pyrope, 3, Almandine, 5, Spessartine, 8));
        event.setMaterial(Ruby).mats(of(Chromium, 1, Alumina, 5)).elecTicks(144);
        event.setMaterial(Sodalite).mats(of(Alumina, 3, SiliconDioxide, 3, Sodium, 4, Chlorine, 1)).elecTicks(264);
        event.setMaterial(Tanzanite).mats(of(Alumina, 15, Calcium, 4, SiliconDioxide, 18, Water, 3, Oxygen, 4)).elecTicks(440);
        event.setMaterial(Topaz).mats(of(Alumina, 5, SiliconDioxide, 3, Fluorine, 2, Water, 3)).elecTicks(208);
        event.setMaterial(YellowGarnet).mats(of(Andradite, 5, Grossular, 8, Uvarovite, 3));
        /**
         *  Basic
         **/
        event.setMaterial(Charcoal).mats(of(Carbon, 1)).elecTicks(12);
        event.setMaterial(Apatite).mats(of(Calcium, 5, Phosphate, 3, Chlorine, 1)).elecTicks(288);
        event.setMaterial(Monazite).mats(of(RareEarth, 1, Phosphate, 1)).elecTicks(104);
        /**
         **  Plastic Related
         **/
        event.setMaterial(EpoxyResin).mats(of(Carbon, 2, Hydrogen, 4, Oxygen, 1));
        event.setMaterial(FiberReinforcedEpoxyResin).mats(of(EpoxyResin, 1));
        event.setMaterial(Polycaprolactam).mats(of(Carbon, 6, Hydrogen, 11, Nitrogen, 1, Oxygen, 1));
        event.setMaterial(Plastic).mats(of(Carbon, 1, Hydrogen, 2));
        event.setMaterial(PolyphenyleneSulfide).mats(of(Carbon, 6, Hydrogen, 4, Sulfur, 1));
        event.setMaterial(Polystyrene).mats(of(Carbon, 8, Hydrogen, 8));
        event.setMaterial(Polytetrafluoroethylene).mats(of(Carbon, 2, Fluorine, 4));
        event.setMaterial(PolyvinylChloride).mats(of(Carbon, 2, Hydrogen, 3, Chlorine, 1));
        event.setMaterial(Rubber).mats(of(Carbon, 5, Hydrogen, 8));
        event.setMaterial(Silicone).mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 1, Silicon, 1));
        event.setMaterial(StyreneButadieneRubber).mats(of(Styrene, 1, Butadiene, 3));
        /**
         **  Misc
         **/
        event.setMaterial(GalliumArsenide).mats(of(Arsenic, 1, Gallium, 1));
        /**
         ***  Fluids
         **/
        /**
         *  Organic
         **/
        event.setMaterial(Acetone).mats(of(Carbon, 3, Hydrogen, 6, Oxygen, 1)).elecTicks(240);
        event.setMaterial(CharcoalByproducts).mats(of(WoodTar,1,WoodGas,1,WoodVinegar,2));
        event.setMaterial(WoodTar).mats(of(Creosote,4,Phenol,1,Benzene,2,Toluene,1));
        //Alkanoles
        event.setMaterial(Methanol).mats(of(Carbon, 1, Hydrogen, 4, Oxygen, 1)).elecTicks(144);
        event.setMaterial(Ethanol).mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 1)).elecTicks(144);
        event.setMaterial(Propanol).mats(of(Carbon, 3, Hydrogen, 8, Oxygen, 1));
        event.setMaterial(Butanol).mats(of(Carbon, 4, Hydrogen, 10, Oxygen, 1));
        event.setMaterial(Heptanol).mats(of(Carbon, 7, Hydrogen, 16, Oxygen, 1));
        //Alkenoles
        event.setMaterial(Ethenol).mats(of(Carbon, 2, Hydrogen, 4, Oxygen, 1));
        event.setMaterial(Propenol).mats(of(Carbon, 3, Hydrogen, 6, Oxygen, 1));
        event.setMaterial(Butenol).mats(of(Carbon, 4, Hydrogen, 8, Oxygen, 1));
        //Alkanedioles
        event.setMaterial(Ethanediol).mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 2));
        event.setMaterial(Propanediol).mats(of(Carbon, 3, Hydrogen, 8, Oxygen, 2));
        event.setMaterial(Butanediol).mats(of(Carbon, 4, Hydrogen, 10, Oxygen, 2));
        //Plastic Related
        event.setMaterial(SiliconeRubber).mats(of(Sulfur, 1, Polydimethylsiloxane, 9));
        //Misc
        event.setMaterial(AceticAcid).mats(of(Carbon, 2, Hydrogen, 4, Oxygen, 2)).elecTicks(128);
        event.setMaterial(AllylChloride).mats(of(Carbon, 3, Hydrogen, 5, Chlorine, 1)).elecTicks(288);
        event.setMaterial(Benzaldehyde).mats(of(Carbon,7,Hydrogen,6,Oxygen,1));
        event.setMaterial(Benzene).mats(of(Carbon, 6, Hydrogen, 6)).elecTicks(48);
        event.setMaterial(BenzoylChloride).mats(of(Carbon,7,Hydrogen,5,Chlorine,1,Oxygen,1));
        event.setMaterial(BisphenolA).mats(of(Carbon, 15, Hydrogen, 16, Oxygen, 2));
        event.setMaterial(Chloramine).mats(of(Nitrogen, 1, Hydrogen, 2, Chlorine, 1)).elecTicks(192);
        event.setMaterial(Chloroform).mats(of(Carbon, 1, Hydrogen, 1, Chlorine, 3)).elecTicks(440);
        event.setMaterial(Cumene).mats(of(Carbon, 9, Hydrogen, 12));
        event.setMaterial(Chlorobenzene).mats(of(Carbon, 6, Hydrogen, 5, Chlorine, 1)).elecTicks(384);
        event.setMaterial(Dichlorobenzene).mats(of(Carbon, 6, Hydrogen, 4, Chlorine, 2)).elecTicks(288);
        event.setMaterial(Dichloroethane).mats(of(Carbon,2,Hydrogen,4,Chlorine,2));
        event.setMaterial(Dimethyldichlorosilane).mats(of(Carbon, 2, Hydrogen, 6, Chlorine, 2, Silicon, 1)).elecTicks(528);
        event.setMaterial(Dimethylhydrazine).mats(of(Carbon, 2, Hydrogen, 8, Nitrogen, 2));
        event.setMaterial(Epichlorohydrin).mats(of(Carbon, 3, Hydrogen, 5, Chlorine, 1, Oxygen, 1)).elecTicks(320);
        event.setMaterial(Glycerol).mats(of(Carbon, 3, Hydrogen, 8, Oxygen, 3)).elecTicks(336);
        event.setMaterial(GlycerylTrinitrate).mats(of(Carbon, 3, Hydrogen, 5, Nitrogen, 3, Oxygen, 9)).elecTicks(800);
        event.setMaterial(Isoprene).mats(of(Carbon, 8, Hydrogen, 8)).elecTicks(208);
        event.setMaterial(MethylAcetate).mats(of(Carbon, 3, Hydrogen, 6, Oxygen, 2)).elecTicks(264);
        event.setMaterial(Naphtha).mats(of(Carbon,8,Hydrogen,16));
        event.setMaterial(Phenol).mats(of(Carbon, 6, Hydrogen, 6, Oxygen, 1)).elecTicks(312);
        event.setMaterial(PolyvinylAcetate).mats(of(Carbon, 4, Hydrogen, 6, Oxygen, 2));
        event.setMaterial(Styrene).mats(of(Carbon, 8, Hydrogen, 8)).elecTicks(48);
        event.setMaterial(SulfuricNaphtha).mats(of(Naphtha, 1, HydrogenSulfide,1));
        event.setMaterial(Tetranitromethane).mats(of(Carbon, 1, Nitrogen, 4, Oxygen, 8)).elecTicks(728);
        event.setMaterial(Toluene).mats(of(Carbon, 7, Hydrogen, 8)).elecTicks(360);
        event.setMaterial(VinylAcetate).mats(of(Carbon, 4, Hydrogen, 6, Oxygen, 2)).elecTicks(144);
        /**
         *  Inorganic
         **/
        event.setMaterial(LeadZincSolution).mats(of(Water, 2, Lead, 1, Silver, 1, Zinc, 1, Sulfur, 3)).elecTicks(300);//TODO 192 eu/tick
        event.setMaterial(BlueVitriol).mats(of(Copper,1,Sulfur,1,Oxygen,4));
        event.setMaterial(GreenVitriol).mats(of(Iron,1,Sulfur,1,Oxygen,4));
        event.setMaterial(RedVitriol).mats(of(Cobalt,1,Sulfur,1,Oxygen,4));
        event.setMaterial(PinkVitriol).mats(of(Magnesium,1,Sulfur,1,Oxygen,4));
        event.setMaterial(CyanVitriol).mats(of(Nickel,1,Sulfur,1,Oxygen,4));
        event.setMaterial(WhiteVitriol).mats(of(Zinc,1,Sulfur,1,Oxygen,4));
        event.setMaterial(GrayVitriol).mats(of(Manganese,1,Sulfur,1,Oxygen,4));
        event.setMaterial(VitriolOfClay).mats(of(Alumina, 1, Sulfur, 3, Oxygen, 8));
        event.setMaterial(CalciumAcetateSolution).mats(of(Calcium, 1, Carbon, 2, Oxygen, 4, Hydrogen, 6)).elecTicks(520);
        event.setMaterial(DistilledWater).mats(of(Hydrogen,2, Oxygen, 1)).elecTicks(2000);
        event.setMaterial(DilutedHydrochloricAcid).mats(of(Hydrogen, 1, Chlorine, 1));
        event.setMaterial(DilutedSulfuricAcid).mats(of(Hydrogen,2,Sulfur,1,Oxygen,4));
        event.setMaterial(HeavyWater).mats(of(Deuterium, 2, Oxygen, 1)).elecTicks(2000);
        event.setMaterial(SemiheavyWater).mats(of(Hydrogen, 1, Deuterium, 1, Oxygen, 1)).elecTicks(2000);
        event.setMaterial(TritiatedWater).mats(of(Tritanium, 2, Oxygen, 1)).elecTicks(2000);
        event.setMaterial(HotHeavyWater).mats(of(Deuterium, 2, Oxygen, 1));
        event.setMaterial(HotSemiheavyWater).mats(of(Hydrogen, 1, Deuterium, 1, Oxygen, 1));
        event.setMaterial(HotTritiatedWater).mats(of(Tritanium, 2, Oxygen, 1));
        event.setMaterial(HotMoltenLithiumChloride).mats(of(LithiumChloride, 1));
        event.setMaterial(HydrochloricAcid).mats(of(Hydrogen, 1, Chlorine, 1)).elecTicks(720);
        event.setMaterial(HydrofluoricAcid).mats(of(Hydrogen, 1, Fluorine, 1)).elecTicks(80);
        event.setMaterial(HydrogenFluoride).mats(of(Hydrogen, 1, Fluorine, 1)).elecTicks(80);
        event.setMaterial(HydrogenPeroxide).mats(of(Hydrogen,2,Oxygen,2));
        event.setMaterial(HypochlorousAcid).mats(of(Hydrogen, 1, Chlorine, 1, Oxygen, 1)).elecTicks(192);

        event.setMaterial(LiquidAir).mats(of(Nitrogen, 40, Oxygen, 11, Argon, 1, NobleGases, 1 ));
        event.setMaterial(NickelSulfate).mats(of(Nickel,1,Sulfur,1,Oxygen,4)).elecTicks(900);
        event.setMaterial(NitrationMixture).mats(of(SulfuricAcid,1,NitricAcid,1));
        event.setMaterial(NitricAcid).mats(of(Hydrogen, 1, Nitrogen, 1, Oxygen, 3)).elecTicks(240);
        event.setMaterial(PeroxydisulfuricAcid).mats(of(Sulfur,2,Hydrogen,2,Oxygen,8));
        event.setMaterial(PhosphoricAcid).mats(of(Hydrogen, 3, Phosphor, 1, Oxygen, 4)).elecTicks(392);
        event.setMaterial(SaltWater).mats(of(Hydrogen,1,SodiumHydroxide,3,Chlorine,1), 8).elecTicks(720);
        event.setMaterial(SodiumPersulfate).mats(of(Sodium, 2, Sulfur, 2, Oxygen, 8)).elecTicks(432);
        event.setMaterial(SodiumBicarbonateSolution).mats(of(Sodium, 1, Hydrogen,1 ,Carbon, 1, Oxygen, 3, Water, 1));
        event.setMaterial(SodiumCarbonateSolution).mats(of(Sodium, 2, Carbon, 1, Oxygen, 3, Water, 1));
        event.setMaterial(Steam).mats(of(Water,1));
        event.setMaterial(SuperheatedSteam).mats(of(Steam, 1));
        event.setMaterial(SulfuricAcid).mats(of(Hydrogen, 2, Sulfur, 1, Oxygen, 4)).elecTicks(392);
        event.setMaterial(SulfurTrioxide).mats(of(Sulfur, 1, Oxygen, 3)).elecTicks(320);
        event.setMaterial(SulfurDioxide).mats(of(Sulfur, 1, Oxygen, 2)).elecTicks(300);
        event.setMaterial(TitaniumTetrachloride).mats(of(Titanium, 1, Chlorine, 4));
        /**
         ***  Gases/Plasmas
         **/
        /**
         *  Organic
         **/
        //Alkanes
        event.setMaterial(Methane).mats(of(Carbon, 1, Hydrogen, 4)).elecTicks(80);
        event.setMaterial(Ethane).mats(of(Carbon, 2, Hydrogen, 6)).elecTicks(64);
        event.setMaterial(Propane).mats(of(Carbon, 2, Hydrogen, 6)).elecTicks(196);
        event.setMaterial(Butane).mats(of(Carbon, 4, Hydrogen, 10)).elecTicks(112);
        //Alkenes
        event.setMaterial(Ethylene).mats(of(Carbon, 2, Hydrogen, 4)).elecTicks(48);
        event.setMaterial(Propene).mats(of(Carbon, 3, Hydrogen, 6)).elecTicks(48);
        event.setMaterial(Butene).mats(of(Carbon, 4, Hydrogen, 8)).elecTicks(48);
        event.setMaterial(Butadiene).mats(of(Carbon, 4, Hydrogen, 6)).elecTicks(120);
        //Ketones
        event.setMaterial(Ethenone).mats(of(Carbon, 2, Hydrogen, 2, Oxygen, 1)).elecTicks(160);
        //Misc
        event.setMaterial(CarbonDioxide).mats(of(Carbon, 1, Oxygen, 2)).elecTicks(300);
        event.setMaterial(CarbonMonoxide).mats(of(Carbon, 1, Oxygen, 1)).elecTicks(112);
        event.setMaterial(Chloromethane).mats(of(Carbon, 1, Hydrogen, 3, Chlorine, 1)).elecTicks(200);
        event.setMaterial(Dimethylamine).mats(of(Carbon, 2, Hydrogen, 7, Nitrogen, 1)).elecTicks(160);
        event.setMaterial(NaturalGas).mats(of(Methane,4,Ethane,2,Propane,2,Butane,1));
        event.setMaterial(RefineryGas).asGas(128);
        event.setMaterial(SulfuricGas).mats(of(NaturalGas,1,Sulfur,1));
        event.setMaterial(Tetrafluoroethylene).mats(of(Carbon, 2, Fluorine, 4));
        event.setMaterial(VinylChloride).mats(of(Carbon, 2, Hydrogen, 3, Chlorine, 1)).elecTicks(240);
        event.setMaterial(WoodGas).mats(of(CarbonDioxide,8,CarbonMonoxide,4,Methane,2,Ethylene,1,Hydrogen,1));
        /**
         *  Inorganic
         **/
        event.setMaterial(Air).mats(of(Nitrogen, 40, Oxygen, 11, Argon, 1, NobleGases, 1 ));
        event.setMaterial(Ammonia).mats(of(Nitrogen, 1, Hydrogen, 3)).elecTicks(64);
        event.setMaterial(DinitrogenTetroxide).mats(of(Nitrogen, 2, Oxygen, 4)).elecTicks(168);
        event.setMaterial(HydrogenSulfide).mats(of(Hydrogen, 2, Sulfur, 1));
        event.setMaterial(NitricOxide).mats(of(Nitrogen, 1, Oxygen, 1)).elecTicks(112);
        event.setMaterial(NitrousOxide).mats(of(Nitrogen, 2, Oxygen, 1)).elecTicks(168);
        event.setMaterial(NitrogenDioxide).mats(of(Nitrogen, 1, Oxygen, 2)).elecTicks(168);
        event.setMaterial(NobleGases).mats(of(Helium, 10, Neon, 10, Argon, 10, Xenon, 10));
        event.setMaterial(SulfurDioxide).mats(of(Sulfur, 1, Oxygen, 2));
        event.setMaterial(SulfurTrioxide).mats(of(Sulfur, 1, Oxygen, 3));
        /**
         ** Fuels
         **/
        event.setMaterial(SulfuricLightFuel).mats(of(LightFuel,1,HydrogenSulfide,1));
        event.setMaterial(SulfuricHeavyFuel).mats(of(HeavyFuel,1,HydrogenSulfide,1));
        /**
         ** Cracked Stuff
         */
        event.setMaterial(SteamCrackedEthane).mats(of(Ethane,1,Steam,1));
        event.setMaterial(SteamCrackedPropane).mats(of(Propane,1,Steam,1));
        event.setMaterial(SteamCrackedButane).mats(of(Butane,1,Steam,1));
        event.setMaterial(SteamCrackedLightFuel).mats(of(LightFuel,1,Steam,1));
        event.setMaterial(SteamCrackedHeavyFuel).mats(of(HeavyFuel,1,Steam,1));
        event.setMaterial(SteamCrackedNaphtha).mats(of(Naphtha,1,Steam,1));
        event.setMaterial(SteamCrackedRefineryGas).mats(of(RefineryGas,1,Steam,1));
        event.setMaterial(HydroCrackedEthane).mats(of(Ethane,1,Hydrogen,1));
        event.setMaterial(HydroCrackedPropane).mats(of(Propane,1,Hydrogen,1));
        event.setMaterial(HydroCrackedButane).mats(of(Butane,1,Hydrogen,2));
        event.setMaterial(HydroCrackedLightFuel).mats(of(LightFuel,1,Hydrogen,2));
        event.setMaterial(HydroCrackedHeavyFuel).mats(of(HeavyFuel,1,Hydrogen,2));
        event.setMaterial(HydroCrackedNaphtha).mats(of(Naphtha,1,Hydrogen,2));
        event.setMaterial(HydroCrackedRefineryGas).mats(of(RefineryGas,1,Hydrogen,2));
    }

    private static void toolsAndArmor(GregTechMaterialEvent event){
        /**
         ** Periodic Elements
         */
        event.setMaterial(Beryllium).tool().toolDamage(2).toolSpeed(14).toolDurability(64).toolQuality(2).build();
        event.setMaterial(Titanium).tool().toolDamage(3).toolSpeed(8).toolDurability(2560).toolQuality(3).toolEnchantments(of(Enchantments.SHARPNESS, 3)).build();
        event.setMaterial(Chromium).tool().toolDamage(3).toolSpeed(11).toolDurability(256).toolQuality(3).build();
        event.setMaterial(Manganese).tool().toolDamage(2).toolSpeed(7).toolDurability(256).toolQuality(2).build();
        event.setMaterial(Cobalt).tool().toolDamage(3).toolSpeed(5).toolDurability(256).toolQuality(3).build();
        event.setMaterial(Nickel).tool().toolDamage(2).toolSpeed(6).toolDurability(64).toolQuality(2).toolEnchantments(of(Enchantments.BANE_OF_ARTHROPODS, 2)).build().harvestLevel(2);
        event.setMaterial(Zirconium).tool().toolDamage(3).toolSpeed(6).toolDurability(512).toolQuality(3).build();
        event.setMaterial(Molybdenum).tool().toolDamage(2).toolSpeed(7).toolDurability(512).toolQuality(2).build().harvestLevel(2);
        event.setMaterial(Technetium).tool().toolDamage(1).toolSpeed(10).toolDurability(1280).toolQuality(1).build();
        event.setMaterial(Palladium).tool().toolDamage(2).toolSpeed(8).toolDurability(512).toolQuality(2).build().harvestLevel(2);
        event.setMaterial(Neodymium).tool().toolDamage(3).toolSpeed(6).toolDurability(512).toolQuality(3).build();
        event.setMaterial(Tungsten).tool().toolDamage(3).toolSpeed(8).toolDurability(5120).toolQuality(3).build(); // TODO: stats like non burnable
        event.setMaterial(Osmium).tool().toolDamage(4).toolSpeed(16).toolDurability(1280).toolQuality(4).build();
        event.setMaterial(Iridium).tool().toolDamage(4).toolSpeed(6).toolDurability(5120).toolQuality(4).build();
        event.setMaterial(Platinum).tool().toolDamage(2).toolSpeed(15).toolDurability(64).toolQuality(2).toolEnchantments(of(Enchantments.SMITE, 5)).build().harvestLevel(2);
        event.setMaterial(Thorium).tool().toolDamage(2).toolSpeed(6).toolDurability(512).toolQuality(2).build().harvestLevel(2);
        event.setMaterial(Uranium).tool().toolDamage(3).toolSpeed(6).toolDurability(512).toolQuality(3).build();
        event.setMaterial(Plutonium).tool().toolDamage(3).toolSpeed(6).toolDurability(512).toolQuality(3).build(); // TODO: Enchantment: Radioactivity;

        /**
         **  Metals
         **/
        event.setMaterial(BismuthBronze).tool().toolDamage(2).toolSpeed(8).toolDurability(512).toolQuality(2).toolEnchantments(of(Enchantments.BANE_OF_ARTHROPODS, 4)).build()
                .addArmor(new int[]{2, 6, 5, 2}, 0.0f, 0.0f, 16);
        event.setMaterial(BlackBronze).tool().toolDamage(2).toolSpeed(12).toolDurability(512).toolQuality(2).toolEnchantments(of(Enchantments.SMITE, 2)).build()
                .addArmor(new int[]{2, 6, 5, 2}, 0.0f, 0.0f, 16);
        event.setMaterial(BlackSteel).tool().toolDamage(2).toolSpeed(6.5f).toolDurability(768).toolQuality(2).toolEnchantments(of(Enchantments.SHARPNESS, 3)).build()
                .addArmor(new int[]{2, 7, 6, 2}, 1.0F, 0.0F, 32, of(Enchantments.ALL_DAMAGE_PROTECTION, 2));
        event.setMaterial(BlueSteel).tool().toolDamage(2).toolSpeed(7).toolDurability(896).toolQuality(2).toolEnchantments(of(Enchantments.SHARPNESS, 3)).build()
                .addArmor(new int[]{2, 7, 6, 2}, 1.0F, 0.0F, 37, of(Enchantments.ALL_DAMAGE_PROTECTION, 3));
        event.setMaterial(Bronze).tool().toolDamage(2).toolSpeed(6).toolDurability(448).toolQuality(2).toolEnchantments(of(Enchantments.SHARPNESS, 1)).build()
                .addArmor(new int[]{2, 6, 5, 2}, 0.0F, 0.0F, 12);
        event.setMaterial(CobaltBrass).tool().toolDamage(2).toolSpeed(8).toolDurability(256).toolQuality(2).toolEnchantments(of(Enchantments.SHARPNESS, 2)).build();
        //event.setMaterial(Duranium).addHandleStat(620, -1.0F, of(Enchantments.SILK_TOUCH, 1))
                //.addTools(6.5F, 16.0F, 5120, 5);
        event.setMaterial(Electrum).tool().toolDamage(2).toolSpeed(12).toolDurability(64).toolQuality(2).toolEnchantments(of(Enchantments.SMITE, 3)).build();
        event.setMaterial(EnrichedNaquadah).tool().toolDamage(4).toolSpeed(6).toolDurability(1280).toolQuality(4).build();
        event.setMaterial(HSSE).tool().toolDamage(4).toolSpeed(10).toolDurability(5120).toolQuality(4).toolEnchantments(of(Enchantments.SHARPNESS, 4)).build();
        event.setMaterial(HSSG).tool().toolDamage(3).toolSpeed(10).toolDurability(4000).toolQuality(3).toolEnchantments(of(Enchantments.SHARPNESS, 4)).build();
        event.setMaterial(HSSS).tool().toolDamage(4).toolSpeed(14).toolDurability(3000).toolQuality(4).toolEnchantments(of(Enchantments.SHARPNESS, 4)).build();
        event.setMaterial(Invar).tool().toolDamage(2).toolSpeed(6).toolDurability(256).toolQuality(3).toolEnchantments(of(Enchantments.BANE_OF_ARTHROPODS, 3)).build()
                .addArmor(new int[]{2, 6, 5, 2}, 0.0F, 0.0F, 15, of(Enchantments.FIRE_PROTECTION, 1));
        event.setMaterial(IronMagnetic).tool(Iron).toolEnchantments(of(Enchantments.SHARPNESS, 1)).build();
        event.setMaterial(Kanthal).tool().toolDamage(2).toolSpeed(6).toolDurability(64).toolQuality(2).build();
        event.setMaterial(Neutronium).tool().toolDamage(9).toolSpeed(24).toolDurability(655360).toolQuality(6).build();
        event.setMaterial(Naquadah).tool().toolDamage(4).toolSpeed(6).toolDurability(1280).toolQuality(4).build();
        event.setMaterial(NaquadahAlloy).tool().toolDamage(4.5f).toolSpeed(8).toolDurability(5120).toolQuality(4).build();
        event.setMaterial(Nichrome).tool().toolDamage(2).toolSpeed(6).toolDurability(64).toolQuality(2).toolEnchantments(of(Enchantments.BANE_OF_ARTHROPODS, 2)).build();
        event.setMaterial(NickelZincFerrite).tool().toolDamage(0).toolSpeed(3).toolDurability(32).toolQuality(1).build();
        event.setMaterial(Osmiridium).tool().toolDamage(4).toolSpeed(11).toolDurability(3840).toolQuality(4).build();
        event.setMaterial(RedSteel).tool().toolDamage(2).toolSpeed(7.5f).toolDurability(1024).toolQuality(2).toolEnchantments(of(Enchantments.SHARPNESS, 3)).build()
                .addArmor(new int[]{2, 7, 6, 2}, 1.0F, 0.0F, 42, of(Enchantments.ALL_DAMAGE_PROTECTION, 3));
        event.setMaterial(RoseGold).tool().toolDamage(2).toolSpeed(14).toolDurability(128).toolQuality(2).toolEnchantments(of(Enchantments.SMITE, 4)).build();
        event.setMaterial(Steel).tool(Iron).toolDurability(512).toolEnchantments(of(Enchantments.SHARPNESS, 2)).build()
                .addArmor(new int[]{2, 7, 6, 2}, 1.0F, 0.0F, 21, of(Enchantments.ALL_DAMAGE_PROTECTION, 1));
        event.setMaterial(SteelMagnetic).tool(Steel).toolEnchantments(of(Enchantments.SHARPNESS, 2)).build();
        event.setMaterial(SterlingSilver).tool().toolDamage(2).toolSpeed(13).toolDurability(128).toolQuality(2).build();
        event.setMaterial(StainlessSteel).tool().toolDamage(2).toolSpeed(7).toolDurability(480).toolQuality(2).toolEnchantments(of(Enchantments.SHARPNESS, 3)).build();
        event.setMaterial(Tritanium).tool().toolDamage(9).toolSpeed(15).toolDurability(9400).toolQuality(6).build();
        event.setMaterial(TungstenCarbide).tool().toolDamage(4).toolSpeed(10).toolDurability(5120).toolQuality(4).build();
        event.setMaterial(TungstenSteel).tool(TungstenCarbide).toolEnchantments(of(Enchantments.SHARPNESS, 4)).build();
        event.setMaterial(VanadiumSteel).tool().toolDamage(3).toolSpeed(7).toolDurability(512).toolQuality(3).toolEnchantments(of(Enchantments.SHARPNESS, 3)).build();
        event.setMaterial(Vibranium).tool().toolDamage(15).toolSpeed(1000).toolDurability(512).toolQuality(15).toolEnchantments(of(Enchantments.BLOCK_FORTUNE, 5, Enchantments.MOB_LOOTING, 10)).build();
        event.setMaterial(WroughtIron).tool(Iron).toolDurability(384).build()
                .addArmor(new int[]{2, 6, 5, 2}, 1.0F, 0.0F, 17, of(Enchantments.ALL_DAMAGE_PROTECTION, 1));
        /**
         **  Gems
         **/
        /**
         *  Regular
         **/
        event.setMaterial(Amber).tool().toolDamage(3).toolSpeed(4).toolDurability(256).toolQuality(3).toolEnchantments(of(Enchantments.SILK_TOUCH, 1)).build();
        event.setMaterial(Amethyst).tool().toolDamage(3).toolSpeed(7).toolDurability(256).toolQuality(3).toolEnchantments(of(Data.IMPLOSION, 3)).build()
                .addArmor(new int[]{3, 7, 7, 3}, 1.0F, 0.0F, 30);
        event.setMaterial(Sapphire).tool().toolDamage(3).toolSpeed(7).toolDurability(512).toolQuality(3).toolEnchantments(of(Data.IMPLOSION, 3)).build()
                .addArmor(new int[]{3, 7, 7, 3}, 1.0F, 0.0F, 30).harvestLevel(2);
        event.setMaterial(BlueTopaz).tool().toolDamage(3).toolSpeed(7).toolDurability(256).toolQuality(3).toolEnchantments(of(Data.IMPLOSION, 5)).build();
        event.setMaterial(GreenSapphire).tool(Sapphire).toolEnchantments(of(Data.IMPLOSION, 3)).build()
                .addArmor(new int[]{3, 7, 7, 3}, 1.0F, 0.0F, 30)
                .harvestLevel(2);
        event.setMaterial(Jade).tool().toolDamage(2).toolSpeed(8).toolDurability(512).toolQuality(2).toolEnchantments(of(Enchantments.BLOCK_FORTUNE, 3, Enchantments.MOB_LOOTING, 6, Data.IMPLOSION, 3)).build();
        event.setMaterial(Olivine).tool().toolDamage(2).toolSpeed(7).toolDurability(256).toolQuality(2).toolEnchantments(of(Data.IMPLOSION, 2)).build().harvestLevel(2);
        event.setMaterial(Opal).tool(Olivine).toolEnchantments(of(Data.IMPLOSION, 4)).build();
        event.setMaterial(Ruby).tool(Sapphire).toolEnchantments(of(Data.IMPLOSION, 3)).build()
                .addArmor(new int[]{3, 7, 7, 3}, 1.0F, 0.0F, 30);
        event.setMaterial(Tanzanite).tool(Olivine).toolEnchantments(of(Data.IMPLOSION, 4)).build();
        event.setMaterial(Topaz).tool(BlueTopaz).toolEnchantments(of(Data.IMPLOSION, 5)).build();
        /**
         *  Basic
         **/
        event.setMaterial(CertusQuartz).tool(Iron).toolDurability(256).build();
        event.setMaterial(Fluix).tool(Iron).toolDamage(2.4f).toolDurability(768).toolEnchantments(of(Enchantments.MOB_LOOTING, 1, Enchantments.BLOCK_FORTUNE, 1)).build();
        /**
         **  Plastic Related
         **/
        event.setMaterial(Rubber).tool().toolDamage(-1).toolSpeed(0.15f).toolDurability(256).toolEnchantments(of(Enchantments.KNOCKBACK, 2)).allowedToolTypes(List.of(AntimatterDefaultTools.SOFT_HAMMER)).build();
        event.setMaterial(EpoxyResin).tool().toolSpeed(2.25f).toolDurability(32).toolQuality(1).allowedToolTypes(List.of(AntimatterDefaultTools.SOFT_HAMMER)).build();
        event.setMaterial(FiberReinforcedEpoxyResin).tool().toolSpeed(2.25f).toolDurability(32).toolQuality(1).allowedToolTypes(List.of(AntimatterDefaultTools.SOFT_HAMMER)).build();
        event.setMaterial(Plastic).tool().toolSpeed(0.3f).toolDurability(256).toolQuality(1).toolEnchantments(of(Enchantments.KNOCKBACK, 1)).allowedToolTypes(List.of(AntimatterDefaultTools.SOFT_HAMMER)).build();
        event.setMaterial(Polystyrene).tool().toolSpeed(0.3f).toolDurability(256).toolQuality(1).toolEnchantments(of(Enchantments.KNOCKBACK, 1)).allowedToolTypes(List.of(AntimatterDefaultTools.SOFT_HAMMER)).build();
        event.setMaterial(PolyvinylChloride).tool().toolSpeed(0.3f).toolDurability(256).toolQuality(1).toolEnchantments(of(Enchantments.KNOCKBACK, 1)).allowedToolTypes(List.of(AntimatterDefaultTools.SOFT_HAMMER)).build();
        event.setMaterial(Silicone).tool().toolSpeed(0.3f).toolDurability(1024).toolQuality(1).toolEnchantments(of(Enchantments.KNOCKBACK, 2)).allowedToolTypes(List.of(AntimatterDefaultTools.SOFT_HAMMER)).build();
        event.setMaterial(StyreneButadieneRubber).tool().toolSpeed(0.3f).toolDurability(1024).toolQuality(1).toolEnchantments(of(Enchantments.KNOCKBACK, 2)).allowedToolTypes(List.of(AntimatterDefaultTools.SOFT_HAMMER)).build();
    }


    private static void nuclearIsotopes(GregTechMaterialEvent event){
        /**
         *** Isotopes (Solids)
         **/
        event.setMaterial(Cobalt60).asMetal(1768);
        event.setMaterial(Thorium230).asMetal(2028);
        event.setMaterial(Uranium233).asMetal(1406);
        event.setMaterial(Uranium235).asMetal(1406);
        event.setMaterial(Plutonium239).asMetal(912);
        event.setMaterial(Plutonium241).asMetal(912);
        event.setMaterial(Plutonium243).asMetal(912);
        event.setMaterial(Americium241).asMetal(1449);
        event.setMaterial(Americium242).asMetal(1449);
        /**
         *** Isotopes (Fluids)
         **/
        /**
         *** Isotopes (Gases/Plasmas)
         **/
        event.setMaterial(HotHelium).asGas(0, 1150);
        event.setMaterial(Deuterium).asGas();
        event.setMaterial(Tritium).asGas();
        event.setMaterial(Helium3).asGas();
        /**
         * Tetrafluorides
         */
        event.setMaterial(UraniumTetrafluoride).asDust().mats(of(Uranium,1,Fluorine,4));
        event.setMaterial(Uranium235Tetrafluoride).asDust().mats(of(Uranium235, 1, Fluorine, 4));
        event.setMaterial(Uranium238Tetrafluoride).asDust().mats(of(Uranium, 1, Fluorine, 4));
        /**
         * Hexafluorides
         */
        event.setMaterial(UraniumHexafluoride).asGas();
        event.setMaterial(Uranium238Hexafluoride).asGas().mats(of(Uranium,1,Fluorine,6));
        event.setMaterial(Uranium235Hexafluoride).asGas().mats(of(Uranium235,1,Fluorine,6));
    }


    private static void workbenches(GregTechMaterialEvent event){
        METAL.all().forEach(m -> {
            if ((m.getElement() == null || !m.getElement().isIsotope) && m.has(PLATE) && m.has(ROD)){

                GTCoreBlocks.createWorkbench(m, false);
                GTCoreBlocks.createWorkbench(m, true);
                GTCoreBlocks.createLocker(m, false);
                GTCoreBlocks.createLocker(m, true);
                GTCoreBlocks.createMassStorage(m, 1000000);
                GTCoreBlocks.createChest(m, true);
                m.flags(RING, SCREW);
                GTCoreBlocks.createBarrel(m, true);
            }
        });
    }

    public static void byproducts(GregTechMaterialEvent event){
        event.setMaterial(Almandine).addByProduct(RedGarnet, Alumina);
        event.setMaterial(Alumina).addByProduct(Bauxite);
        //event.setMaterial(Amber).addByProduct(Amber); TODO: Add Amber
        event.setMaterial(Amethyst).addByProduct(Amethyst);
        event.setMaterial(Monazite).addByProduct(Thorium, Neodymium, RareEarth);
        event.setMaterial(Apatite).addByProduct(TricalciumPhosphate);
        event.setMaterial(Andradite).addByProduct(YellowGarnet, Iron, Boron);
        event.setMaterial(Antimony).addByProduct(Zinc, Iron);
        event.setMaterial(Ash).addByProduct(Carbon);
        event.setMaterial(Basalt).addByProduct(Olivine, DarkAsh);
        event.setMaterial(Bastnasite).addByProduct(Neodymium, RareEarth);
        event.setMaterial(Bauxite).addByProduct(Grossular, Rutile, Gallium);
        event.setMaterial(Bentonite).addByProduct(Alumina, Calcium, Magnesium).elecTicks(480);
        event.setMaterial(Beryllium).addByProduct(Emerald);
        event.setMaterial(BlackGranite).addByProduct(Biotite);
        event.setMaterial(Sapphire).addByProduct(Alumina);
        event.setMaterial(GreenSapphire).addByProduct(Alumina);
        event.setMaterial(Brass).addByProduct(Copper, Tin);
        event.setMaterial(Bronze).addByProduct(Copper, Zinc);
        event.setMaterial(BrownLimonite).addByProduct(Malachite, YellowLimonite);
        event.setMaterial(Calcite).addByProduct(Andradite, Malachite);
        event.setMaterial(Cassiterite).addByProduct(Tin);
        event.setMaterial(Chalcopyrite).addByProduct(Pyrite, Cobalt, Cadmium);
        event.setMaterial(Chromium).addByProduct(Iron, Magnesium);
        event.setMaterial(Chromite).addByProduct(Iron, Magnesium);
        event.setMaterial(Cinnabar).addByProduct(Redstone, Sulfur, Glowstone);
        event.setMaterial(Clay).addByProduct(Clay);
        event.setMaterial(Coal).addByProduct(Lignite, Thorium);
        event.setMaterial(Cobalt).addByProduct(Cobaltite);
        event.setMaterial(Cobaltite).addByProduct(Cobalt, Cobalt, Cobalt60);
        event.setMaterial(Sheldonite).addByProduct(Palladium, Nickel, Iridium);
        event.setMaterial(Copper).addByProduct(Cobalt, Gold, Nickel);
        event.setMaterial(DarkAsh).addByProduct(Carbon);
        event.setMaterial(Diamond).addByProduct(Graphite);
        event.setMaterial(Electrum).addByProduct(Gold, Silver);
        event.setMaterial(Emerald).addByProduct(Beryllium, Alumina);
        event.setMaterial(Endstone).addByProduct(Helium3);
        event.setMaterial(EnrichedNaquadah).addByProduct(Naquadah, Naquadria);
        event.setMaterial(Flint).addByProduct(Obsidian);
        event.setMaterial(Galena).addByProduct(Sulfur, Silver, Lead);
        event.setMaterial(Garnierite).addByProduct(Nickel);
        event.setMaterial(Glauconite).addByProduct(Sodium, Alumina, Iron);
        event.setMaterial(Glowstone).addByProduct(Redstone, Gold);
        event.setMaterial(Gold).addByProduct(Copper, Nickel);
        event.setMaterial(Graphite).addByProduct(Carbon);
        event.setMaterial(Grossular).addByProduct(YellowGarnet, Calcium);
        event.setMaterial(Ilmenite).addByProduct(Iron, Rutile);
        event.setMaterial(Iridium).addByProduct(Platinum, Osmium);
        event.setMaterial(Iron).addByProduct(Nickel, Tin);
        event.setMaterial(Lapis).addByProduct(Calcite, Pyrite);
        event.setMaterial(Lead).addByProduct(Silver, Sulfur);
        event.setMaterial(Lepidolite).addByProduct(Lithium, Caesium, Boron);
        event.setMaterial(Lignite).addByProduct(Coal);
        event.setMaterial(Lithium).addByProduct(Dilithium);
        event.setMaterial(Magnesite).addByProduct(Magnesium);
        event.setMaterial(Magnesium).addByProduct(Olivine);
        event.setMaterial(Magnetite).addByProduct(Iron, Gold);
        event.setMaterial(Malachite).addByProduct(Copper, BrownLimonite, Calcite);
        event.setMaterial(Manganese).addByProduct(Chromium, Iron);
        event.setMaterial(Marble).addByProduct(Calcite);
        event.setMaterial(Molybdenite).addByProduct(Powellite, Scheelite, Wulfenite, Osmium);
        event.setMaterial(Molybdenum).addByProduct(Powellite, Scheelite, Wulfenite, Osmium);
        event.setMaterial(Naquadah).addByProduct(EnrichedNaquadah);
        event.setMaterial(Neodymium).addByProduct(RareEarth);
        event.setMaterial(Netherrack).addByProduct(Sulfur);
        event.setMaterial(Neutronium).addByProduct(Neutronium);
        event.setMaterial(Nickel).addByProduct(Cobalt, Platinum, Iron);
        event.setMaterial(Obsidian).addByProduct(Olivine);
        event.setMaterial(Olivine).addByProduct(Pyrope, Magnesium);
        event.setMaterial(Opal).addByProduct(Tanzanite);
        event.setMaterial(Osmium).addByProduct(Iridium);
        event.setMaterial(Pentlandite).addByProduct(Iron, Sulfur, Cobalt);
        event.setMaterial(Phosphate).addByProduct(Phosphor);
        event.setMaterial(Phosphor).addByProduct(Phosphate);
        event.setMaterial(TricalciumPhosphate).addByProduct(Phosphate);
        event.setMaterial(Pitchblende).addByProduct(Thorium, Uranium, Lead);
        event.setMaterial(Platinum).addByProduct(Nickel, Iridium);
        event.setMaterial(Plutonium).addByProduct(Uranium, Lead);
        event.setMaterial(Powellite).addByProduct(Molybdenite, Scheelite);
        event.setMaterial(Pyrite).addByProduct(Sulfur, TricalciumPhosphate, Iron);
        event.setMaterial(Pyrolusite).addByProduct(Manganese);
        event.setMaterial(Pyrope).addByProduct(RedGarnet, Magnesium);
        event.setMaterial(Rutile).addByProduct(Hematite, Zircon);
        event.setMaterial(Quartz).addByProduct(Netherrack);
        event.setMaterial(MilkyQuartz).addByProduct(Barite);
        event.setMaterial(RedGarnet).addByProduct(Spessartine, Pyrope, Almandine);
        event.setMaterial(Redstone).addByProduct(Cinnabar, RareEarth, Glowstone);
        event.setMaterial(RedGranite).addByProduct(PotassiumFeldspar);
        event.setMaterial(RockSalt).addByProduct(Salt);
        event.setMaterial(Ruby).addByProduct(Chromium, RedGarnet);
        event.setMaterial(Salt).addByProduct(RockSalt);
        event.setMaterial(Saltpeter).addByProduct(Saltpeter);
        event.setMaterial(Scheelite).addByProduct(Manganese, Molybdenum, Calcium).elecTicks(120); //1920 eu/tick
        event.setMaterial(Silicon).addByProduct(SiliconDioxide);
        event.setMaterial(Silver).addByProduct(Lead, Sulfur);
        event.setMaterial(Spessartine).addByProduct(RedGarnet, Manganese);
        event.setMaterial(Sphalerite).addByProduct(YellowGarnet, Cadmium, Gallium, Zinc);
        event.setMaterial(Spodumene).addByProduct(Alumina, Lithium);
        event.setMaterial(Steel).addByProduct(Iron);
        event.setMaterial(Stibnite).addByProduct(Antimony);
        event.setMaterial(Sulfur).addByProduct(Sulfur);
        event.setMaterial(Tantalite).addByProduct(Manganese, Niobium, Tantalum);
        event.setMaterial(Tanzanite).addByProduct(Opal);
        event.setMaterial(Tetrahedrite).addByProduct(Antimony, Zinc);
        event.setMaterial(Thorium).addByProduct(Lead, Thorium, Indium);
        event.setMaterial(Tin).addByProduct(Iron, Zinc);
        event.setMaterial(Titanium).addByProduct(Almandine);
        event.setMaterial(Tungstate).addByProduct(Manganese, Silver, Lithium);
        event.setMaterial(Tungsten).addByProduct(Manganese, Molybdenum);
        event.setMaterial(Uraninite).addByProduct(Uranium, Thorium, Plutonium);
        //event.setMaterial(Uranium).addByProduct(Lead, Thorium, Plutonium);
        event.setMaterial(Uvarovite).addByProduct(YellowGarnet, Chromium);
        event.setMaterial(VanadiumMagnetite).addByProduct(Magnetite, Vanadium);
        event.setMaterial(Wulfenite).addByProduct(Powellite/*, Sheelite*/, Molybdenite, Galena);
        event.setMaterial(YellowGarnet).addByProduct(Andradite, Grossular, Uvarovite);
        event.setMaterial(YellowLimonite).addByProduct(Nickel, BrownLimonite, Cobalt);
        event.setMaterial(Zinc).addByProduct(Tin, Gallium);
    }

    private static void flags(GregTechMaterialEvent event){
        BRITTLEG.add(Coal, Charcoal, Lignite);
        CALCITE2X.add(Pyrite, YellowLimonite);
        CALCITE3X.add(Iron, BrownLimonite);
        CENT5.add(FiberReinforcedEpoxyResin, /*Chrysolite*/ Flint, /*Niter*/ Glass, /*Perlite*/ WroughtIron, DarkAsh, AnnealedCopper, NobleGases,
                Cinnabar);
        CENT10.add(Magnalium, VanadiumMagnetite, BrownLimonite, YellowLimonite, BlackGranite, Cupronickel, NiobiumTitanium, BorosilicateGlass,
                GalliumArsenide, Marble, Limestone, Invar, TinAlloy, TungstenCarbide, EnderEye, Powellite, VanadiumGallium, Blaze,
                TungstenSteel, Brass, Nichrome, Electrum, Bronze, Stibnite, Wulfenite, RedAlloy, SterlingSilver, RoseGold, BatteryAlloy, SolderingAlloy, TricalciumPhosphate);
        CENT15.add(Kanthal, IndiumGalliumPhosphide, BlackSteel, RedGarnet, YellowGarnet, BismuthBronze, BlackBronze, VanadiumSteel, CobaltBrass,
                Pitchblende, Redstone, HSSS);
        CENT20.add(Lapis, Tetrahedrite, RedSteel, BlueSteel, Basalt, HSSE, Sheldonite, HSSG, Komatiite);
        CENT.add(CENT5.all().toArray(new Material[0]));
        CENT.add(CENT10.all().toArray(new Material[0]));
        CENT.add(CENT15.all().toArray(new Material[0]));
        CENT.add(CENT20.all().toArray(new Material[0]));
        RECIPE_MASS.add(Lead, 64);
        CHEMBATH_MERCURY.add(Chalcopyrite, Gold);
        CHEMBATH_MERCURY.add(Copper, Gold);
        CHEMBATH_MERCURY.add(Gold,Gold);
        CHEMBATH_MERCURY.add(Sheldonite, Sheldonite);
        CHEMBATH_MERCURY.add(Lead, Silver);
        CHEMBATH_MERCURY.add(Galena, Silver);
        CHEMBATH_MERCURY.add(Magnetite, Gold);
        CHEMBATH_MERCURY.add(Nickel, Platinum);
        CHEMBATH_MERCURY.add(Osmium, Osmium);
        CHEMBATH_MERCURY.add(Platinum, Platinum);
        CHEMBATH_MERCURY.add(Iridium, Platinum);
        CHEMBATH_MERCURY.add(Silver,Silver);
        CHEMBATH_MERCURY.add(Tungstate, Silver);
        CHEMBATH_PERSULFATE.add(Cobaltite,Cobaltite);
        CHEMBATH_PERSULFATE.add(Pentlandite,Cobalt);
        CHEMBATH_PERSULFATE.add(Copper, Copper);
        CHEMBATH_PERSULFATE.add(Gold, Copper);
        CHEMBATH_PERSULFATE.add(Chalcopyrite,Cobalt);
        CHEMBATH_PERSULFATE.add(Nickel, Nickel);
        CHEMBATH_PERSULFATE.add(Garnierite, Nickel);
        CHEMBATH_PERSULFATE.add(YellowLimonite, Nickel);
        CHEMBATH_PERSULFATE.add(Sphalerite,Zinc);
        CHEMBATH_PERSULFATE.add(Tin,Zinc);
        CHEMBATH_PERSULFATE.add(Malachite, Copper);
        CHEMBATH_PERSULFATE.add(Tetrahedrite,Tetrahedrite);
        CHEMBATH_PERSULFATE.add(Zinc, Zinc);
        CHEMBATH_PERSULFATE.add(Platinum, Nickel);
        CHEMBATH_PERSULFATE.add(Iron, Nickel);
        CHEMBATH_PERSULFATE.add(Sheldonite, Nickel);
        FURNACE_FUELS.add(CoalCoke, GEM, 3200);
        FURNACE_FUELS.add(CoalCoke, DUST, 3200);
        FURNACE_FUELS.add(CoalCoke, BLOCK, 32000);
        FURNACE_FUELS.add(Lignite, GEM, 800);
        FURNACE_FUELS.add(Lignite, DUST, 800);
        FURNACE_FUELS.add(Lignite, RAW_ORE, 800);
        FURNACE_FUELS.add(Lignite, CRUSHED, 900);
        FURNACE_FUELS.add(Lignite, CRUSHED_PURIFIED, 1000);
        FURNACE_FUELS.add(Lignite, DUST_IMPURE, 800);
        FURNACE_FUELS.add(Lignite, DUST_PURE, 800);
        FURNACE_FUELS.add(Lignite, CRUSHED_REFINED, 1100);
        FURNACE_FUELS.add(Lignite, BLOCK, 8000);
        FURNACE_FUELS.add(LigniteCoke, GEM, 1600);
        FURNACE_FUELS.add(LigniteCoke, DUST, 1600);
        FURNACE_FUELS.add(LigniteCoke, BLOCK, 16000);
        FURNACE_FUELS.add(PetroleumCoke, GEM, 6400);
        FURNACE_FUELS.add(PetroleumCoke, DUST, 6400);
        FURNACE_FUELS.add(PetroleumCoke, BLOCK, 64000);
        FURNACE_FUELS.add(Coal, DUST, 1600);
        FURNACE_FUELS.add(Coal, RAW_ORE, 1600);
        FURNACE_FUELS.add(Coal, CRUSHED, 1800);
        FURNACE_FUELS.add(Coal, CRUSHED_PURIFIED, 2000);
        FURNACE_FUELS.add(Coal, DUST_IMPURE, 1600);
        FURNACE_FUELS.add(Coal, DUST_PURE, 1600);
        FURNACE_FUELS.add(Coal, CRUSHED_REFINED, 2200);
        FURNACE_FUELS.add(Charcoal, DUST, 1600);
        FURNACE_FUELS.add(Charcoal, BLOCK, 16000);
        FURNACE_FUELS.add(OilShale, DUST, 400);
        FURNACE_FUELS.add(OilShale, RAW_ORE, 400);
        CRACK.add(RefineryGas, Naphtha, Ethane, Ethylene, Propane, Propene, Butane, Butene, Butadiene, LightFuel, HeavyFuel);
        CRYSTALLIZE.add(Lapis, Lazurite, Sodalite, MilkyQuartz, Quartz, CertusQuartz, Fluix, Jade, Amber, Apatite, Dilithium, Monazite, Coal, CoalCoke, Charcoal, Lignite, LigniteCoke);
        ELEC30.add(Charcoal, Opal, Coal, Lignite, Magnesia, SteelMagnetic, IronMagnetic, Quicklime, Quartzite,
                SiliconDioxide, CalciumSulfate, CobaltOxide, Garnierite, CupricOxide, RockSalt, /*Zincite,*/Pyrolusite, /*ChromiumDioxide,*/
                Phosphate, Potash, /*NiobiumNitride,*/ GreenSapphire, Sapphire, NeodymiumMagnetic, Cassiterite,
                PhosphorousPentoxide, Hematite, Massicot, ArsenicTrioxide, Sugar, Magnetite, AntimonyTrioxide,
                Salt, SodiumBisulfate, HydrochloricAcid, SaltWater, HydrochloricAcid, Diamond,
                NickelSulfate, Water, DistilledWater, HeavyWater, SemiheavyWater, TritiatedWater, MilkyQuartz, FerricChloride);
        ELEC60.add(CalciumChloride, SodiumHydroxide, Propene, Ethylene, Butene, Benzene, Styrene, Ethane, Ammonia, SodiumSulfide, Methane,
                Magnesite, HydrofluoricAcid, HydrogenFluoride, Sphalerite, /*NitroCarbon,*/ SodaAsh, Calcite, Saltpeter, Monazite,
                /*Wollastonite,*/ NitricOxide, Butane, CarbonMonoxide, Pyrite, RedGranite, Ferrosilite, Butadiene, Amethyst,
                Molybdenite, Ruby, /*Kyanite,*/ NitrogenDioxide, NitrousOxide, DinitrogenTetroxide, Propane, Barite, Isoprene,
                Chromite, EnderPearl, SiliconDioxide, Apatite, SulfurTrioxide, /*Pyrochlore, */ Toluene, Phosphate,
                Tantalite, PhosphorousPentoxide, Osmiridium, Pentlandite, Steel, Graphite, MagnesiumChloride);
        ELEC90.add(Polydimethylsiloxane, AceticAcid, Olivine, Ethanol, Methanol, VinylAcetate, /*Gypsum,*/ Cobaltite, Ethenone,
                /*Dymethylamine,*/ Chalcopyrite, /*Mirabilite,*/ Spodumene, /*Dolomite,*/ HypochlorousAcid, Chloramine, Bastnasite,
                Chloromethane, Malachite, /*Borax, */ /*Kaolinite,*/ Obsidian, NitricAcid, VinylChloride, Acetone, /*Asbestos,*/ PotassiumFeldspar,
                MethylAcetate, Sodalite, AllylChloride, Dichlorobenzene, Phenol, Glycerol, Talc, Soapstone, PhosphoricAcid,
                Chlorobenzene, SulfuricAcid, Pyrope, SodiumPersulfate, Chloroform, Grossular, Spessartine, Almandine, Uvarovite, Andradite,
                Emerald, Tetranitromethane, Galena, Zircon);
        ELEC120.add(Clay, /*Trona,*/ BlueTopaz, Topaz, /*Pollucite,*/ CarbonDioxide, SulfurDioxide, Epichlorohydrin, Lepidolite, /*FullersEarth, Alunite,*/ Glauconite,
                /*Mica,*/ Lazurite, Tanzanite, Biotite, StainlessSteel, Bentonite, Ultimet, CalciumAcetateSolution, Dimethyldichlorosilane, /*Vermiculate, Zeolite,*/ GlycerylTrinitrate,
                LeadZincSolution);
        ELEC.add(ELEC30.all().toArray(new Material[0]));
        ELEC.add(ELEC60.all().toArray(new Material[0]));
        ELEC.add(ELEC90.all().toArray(new Material[0]));
        ELEC.add(ELEC120.all().toArray(new Material[0]));
        ELECSEPG.add(VanadiumMagnetite, Magnetite);
        ELECSEPI.add(YellowLimonite, BrownLimonite, Tin, Chromium, Ilmenite, Hematite, Pyrite, Glauconite, Nickel, Chromite, Pentlandite, Manganese);
        ELECSEPN.add(Monazite, Bastnasite);
        GRINDABLE.add(/* Paper, */Coal, Charcoal, Lignite, Lead, Tin, SolderingAlloy, Flint, Gold, Silver, Iron,
                IronMagnetic, Steel, SteelMagnetic, Zinc, Antimony, Copper, AnnealedCopper, Bronze, Nickel, Invar,
                Brass, WroughtIron, Electrum, Clay, Blaze);
        NOBBF.add(Tetrahedrite, Chalcopyrite, Sheldonite, Pyrolusite, Magnesite, Molybdenite, Galena);
        NOSMASH.add(Wood/* WoodSealed */, Sulfur, Saltpeter, Graphite, /* Paper, */Coal, Charcoal, Lignite, Rubber,
                StyreneButadieneRubber, Plastic, PolyvinylChloride, Polystyrene, Silicone, CetaneBoostedDiesel,
                Concrete, Redstone, Glowstone, Netherrack, Stone, Brick, Endstone, Marble, Basalt, Obsidian, Flint,
                RedGranite, BlackGranite, Salt, RockSalt, Glass, Diamond, Emerald, Amethyst, Tanzanite, Topaz,
                /* Amber, */ Sapphire, Ruby, Opal, Olivine, Lapis, MilkyQuartz, Quartz, TricalciumPhosphate, Phosphate,
                NetherStar, EnderPearl, EnderEye, Silicon);
        NOSMELT.add(Wood/* , WoodSealed */, Sulfur, Saltpeter, Graphite, /* Paper, */Coal, Charcoal, Lignite,
                CetaneBoostedDiesel, Emerald, Amethyst, Tanzanite, Topaz, /* Amber, */ Sapphire, Ruby, Opal, Olivine,
                Lapis, Sodalite, Lazurite, Monazite , MilkyQuartz, Quartz, TricalciumPhosphate, Phosphate, NetherStar,
                EnderPearl, EnderEye, Blaze);
        RUBBERTOOLS.add(Rubber, StyreneButadieneRubber, Plastic, PolyvinylChloride, Polystyrene, Silicone, EpoxyResin, FiberReinforcedEpoxyResin);
        SMELTF.add(Concrete, Redstone, Glowstone, Glass, Blaze);
        SOLDER.add(Lead, Tin, SolderingAlloy);
        SOLDER.subTag(SubTag.BAD_SOLDER, Lead, Tin);
        SOLDER.subTag(SubTag.GOOD_SOLDER, SolderingAlloy, Tin);
        WIRE.subTag(SubTag.COPPER_WIRE, AnnealedCopper);
        WIRE.subTag(SubTag.COPPER_WIRE, Copper);
        CABLE.subTag(SubTag.COPPER_CABLE, AnnealedCopper);
        CABLE.subTag(SubTag.COPPER_CABLE, Copper);

        event.setMaterial(AnnealedCopper).setDirectSmeltInto(Copper).setMacerateInto(Copper).setArcSmeltInto(AnnealedCopper);
        event.setMaterial(Copper).setArcSmeltInto(AnnealedCopper);
        event.setMaterial(Iron).setArcSmeltInto(WroughtIron);
        event.setMaterial(IronMagnetic).setDirectSmeltInto(Iron).setMacerateInto(Iron).setArcSmeltInto(WroughtIron);
        event.setMaterial(NeodymiumMagnetic).setDirectSmeltInto(Neodymium).setMacerateInto(Neodymium).setArcSmeltInto(Neodymium);
        event.setMaterial(SteelMagnetic).setDirectSmeltInto(Steel).setMacerateInto(Steel).setArcSmeltInto(Steel);
        event.setMaterial(WroughtIron).setDirectSmeltInto(Iron).setMacerateInto(Iron).setArcSmeltInto(WroughtIron);

        /*event.setMaterial(BandedIron).setDirectSmeltInto(Iron);
        event.setMaterial(BrownLimonite).setDirectSmeltInto(Iron);
        event.setMaterial(Cassiterite).setDirectSmeltInto(Tin);
        event.setMaterial(Chalcopyrite).setDirectSmeltInto(Copper);
        event.setMaterial(Cinnabar).setDirectSmeltInto(Mercury);
        event.setMaterial(Cobaltite).setDirectSmeltInto(Cobalt);
        event.setMaterial(Cooperite).setDirectSmeltInto(Platinum);
        event.setMaterial(Galena).setDirectSmeltInto(Lead);
        event.setMaterial(Garnierite).setDirectSmeltInto(Nickel);
        event.setMaterial(Magnesite).setDirectSmeltInto(Magnesium);
        event.setMaterial(Magnetite).setDirectSmeltInto(Iron);
        event.setMaterial(Malachite).setDirectSmeltInto(Copper);
        event.setMaterial(Molybdenite).setDirectSmeltInto(Molybdenum);
        event.setMaterial(Pentlandite).setDirectSmeltInto(Nickel);
        event.setMaterial(Pyrite).setDirectSmeltInto(Iron);
        event.setMaterial(Pyrolusite).setDirectSmeltInto(Manganese);
        event.setMaterial(Sphalerite).setDirectSmeltInto(Zinc);
        event.setMaterial(Stibnite).setDirectSmeltInto(Antimony);
        event.setMaterial(Tetrahedrite).setDirectSmeltInto(Copper);
        event.setMaterial(YellowLimonite).setDirectSmeltInto(Iron);*/

        event.setMaterial(Cassiterite).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Glowstone).setOreMulti(5).setSmeltingMulti(5);
        event.setMaterial(Lapis).setOreMulti(6).setSmeltingMulti(6).setByProductMulti(4);
        event.setMaterial(TricalciumPhosphate).setOreMulti(3).setSmeltingMulti(3);
        event.setMaterial(Quartz).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Redstone).setOreMulti(5).setSmeltingMulti(5);
        event.setMaterial(RockSalt).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Salt).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Saltpeter).setOreMulti(4).setSmeltingMulti(4);
        event.setMaterial(Scheelite).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Tungstate).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Monazite).setOreMulti(8);
        event.setMaterial(Apatite).setOreMulti(4);
        event.setMaterial(CertusQuartz).setOreMulti(2);
        event.setMaterial(Lazurite).setOreMulti(6);
        event.setMaterial(Sodalite).setOreMulti(6);
        event.setMaterial(TricalciumPhosphate).setOreMulti(3);

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

    private static void antimatterMaterials(GregTechMaterialEvent event){
        event.setMaterial(Basalt).mats(of(Olivine, 1, Calcite, 3, Flint, 8, DarkAsh, 4));
        event.setMaterial(Blaze).mats(of(Sulfur, 1, DarkAsh, 1));
        event.setMaterial(Coal).asGemBasic(false).flags(ORE_STONE).mats(of(Carbon, 2));
        event.setMaterial(Copper).flags(PLATE, ROD_LONG, FOIL, WIRE_FINE, GEAR, BOLT);
        event.setMaterial(Diamond).asGem(true).mats(of(Carbon, 64), 1).elecTicks(768);
        event.setMaterial(Emerald).asGem(true).mats(of(Alumina, 5, Beryllium, 3, SiliconDioxide, 18, Oxygen, 3)).elecTicks(540);
                //.tool().toolDamage(3).toolSpeed(9).toolDurability(590).toolQuality(3).toolEnchantments(of(Data.IMPLOSION, 5)).build();
        event.setMaterial(EnderEye).asGemBasic(false, ROD_LONG, PLATE).mats(of(EnderPearl, 1, Blaze, 1));
        event.setMaterial(EnderPearl).mats(of(Beryllium, 1, Potassium, 4, Nitrogen, 5, Chlorine, 6)).elecTicks(220);
        event.setMaterial(Flint).flags(ROCK);
        event.setMaterial(Gold).flags(FOIL, ROD_LONG, WIRE_FINE, GEAR);
        event.setMaterial(Iron).flags(RING, ROD_LONG, GEAR, FRAME);
        event.setMaterial(Wood).flags(FRAME);
        event.setMaterial(AntimatterMaterials.Netherite).asMetal(2246, 1300, RING);
        event.setMaterial(Lapis).asGemBasic(false, PLATE).mats(of(Lazurite, 12, Sodalite, 2, Pyrite, 1, Calcite, 1));
        event.setMaterial(Prismarine).mats(of(Potassium, 2, Oxygen, 8, Manganese, 1, Silicon, 5));
        event.setMaterial(Redstone).mats(of(Silicon, 1, Pyrite, 5, Ruby, 1, Mercury, 3)).asFluid(0, MaterialTags.MELTING_POINT.getInt(Redstone));//.setOreMulti(4);
        event.setMaterial(Water).mats(of(Hydrogen, 2, Oxygen, 1)).elecTicks(2000);
        event.setMaterial(Sugar).mats(of(Water, 11, Carbon, 12)).elecTicks(184);
        event.setMaterial(Glowstone).asFluid(0, 1000).flags(MOLTEN);
    }
}