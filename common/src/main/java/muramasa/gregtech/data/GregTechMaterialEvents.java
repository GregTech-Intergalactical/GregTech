package muramasa.gregtech.data;

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
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.data.AntimatterMaterials.Charcoal;
import static muramasa.antimatter.material.MaterialTags.*;
import static muramasa.gregtech.data.GregTechMaterialTags.*;
import static muramasa.gregtech.data.Materials.*;
import static net.minecraft.data.loot.BlockLoot.applyExplosionDecay;
import static net.minecraft.data.loot.BlockLoot.createSilkTouchDispatchTable;

public class GregTechMaterialEvents {
    public static void onMaterialEvent(MaterialEvent event){
        flags(event);
        antimatterMaterials(event);
        byproducts(event);
        MaterialTags.CUSTOM_ORE_DROPS.add(Lapis, b -> createSilkTouchDispatchTable(b, applyExplosionDecay(b, LootItem.lootTableItem(Items.LAPIS_LAZULI).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 9.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));
        /**
         *** PSE (Solids, no Isotopes)
         **/
        event.setMaterial(Lithium).asSolid(454, 0).asOre();
        event.setMaterial(Beryllium).asOre().addTools(2.0F, 14.0F, 64, 2);
        event.setMaterial(Boron).asDust(2349);
        event.setMaterial(Carbon).asSolid();
        event.setMaterial(Sodium).asDust(370);
        event.setMaterial(Magnesium).asMetal(1383, 1383);
        event.setMaterial(Aluminium).asMetal(933, 1700, PLATE, ROD, SCREW, BOLT, RING, GEAR, FRAME, GEAR_SMALL, FOIL).asOre().addTools(1.5F, 10.0F, 140, 2);
        event.setMaterial(Silicon).asMetal(1683, 1683, PLATE, FOIL);
        event.setMaterial(Phosphor).asDust(933);
        event.setMaterial(Sulfur).asDust(388).asOre().asPlasma();
        event.setMaterial(Potassium).asSolid(336, 0);
        event.setMaterial(Calcium).asDust(1115);
        event.setMaterial(Titanium).asMetal(1941, 1940, PLATE, ROD, SCREW, BOLT, RING, GEAR, FRAME, GEAR_SMALL, ROTOR).asOre().addTools(2.5F, 7.0F, 1600, 3);
        event.setMaterial(Vanadium).asMetal(2183, 2183);
        event.setMaterial(Chrome).asMetal(2180, 1700, SCREW, BOLT, RING, PLATE, ROTOR).addTools(2.5F, 11.0F, 256, 3);
        event.setMaterial(Manganese).asMetal(1519, 0).asOre();
        event.setMaterial(Cobalt).asMetal(1768, 0).asOre().addTools(3.0F, 8.0F, 512, 3);
        event.setMaterial(Nickel).asMetal(1728, 0, PLATE).asOre().asPlasma();
        event.setMaterial(Zinc).asMetal(692, 0, PLATE, FOIL).asOre();
        event.setMaterial(Gallium).asMetal(302, 0);
        event.setMaterial(Germanium).asMetal(1211,1211);
        event.setMaterial(Arsenic).asMetal(1090, 1090);
        event.setMaterial(Selenium).asMetal(494, 0);
        event.setMaterial(Krypton).asGas();
        event.setMaterial(Rubidium).asMetal(312,0);
        event.setMaterial(Strontium).asMetal(1050, 1050);
        event.setMaterial(Yttrium).asMetal(1799, 1799);
        event.setMaterial(Zirconium).asMetal(2130,2130);
        event.setMaterial(Niobium).asMetal(2750, 2750, GEAR);
        event.setMaterial(Molybdenum).asMetal(2896, 2896).asOre().addTools(2.0F, 7.0F, 512, 2);
        event.setMaterial(Technetium).asMetal(2430,2430);
        event.setMaterial(Ruthenium).asMetal(2607,2607);
        event.setMaterial(Rhodium).asMetal(2237, 2237);
        event.setMaterial(Palladium).asMetal(1828, 1828).asOre().addTools(3.0F, 10.0F, 420, 2);
        event.setMaterial(Silver).asMetal(1234, 0, PLATE, SCREW).asOre();
        event.setMaterial(Cadmium).asDust(594);
        event.setMaterial(Indium).asSolid(429, 0);
        event.setMaterial(Tin).asMetal(505, 505, PLATE, ROD, SCREW, BOLT, RING, GEAR, FOIL, WIRE_FINE, FRAME, ROTOR).asOre();
        event.setMaterial(Antimony).asMetal(1449, 1449);
        event.setMaterial(Tellurium).asMetal(723,0);
        event.setMaterial(Iodine).asSolid(387,0);
        event.setMaterial(Xenon).asGas();
        event.setMaterial(Caesium).asMetal(2349, 2349);
        event.setMaterial(Barium).asDust(1000);
        event.setMaterial(Lanthanum).asMetal(1193, 1193);
        event.setMaterial(Cerium).asMetal(1068, 1068);
        event.setMaterial(Praseodymium).asMetal(1208,1208);
        event.setMaterial(Neodymium).asMetal(1297, 1297, PLATE, ROD).asOre(); // TODO: Bastnasite or Monazite for Ore For;
        event.setMaterial(Promethium).asMetal(1353,1353);
        event.setMaterial(Samarium).asMetal(1345,1345);
        event.setMaterial(Europium).asMetal(1099, 1099);
        event.setMaterial(Gadolinium).asMetal(1585,1585);
        event.setMaterial(Terbium).asMetal(1696,1696);
        event.setMaterial(Dysprosium).asMetal(1680, 1680);
        event.setMaterial(Holmium).asMetal(1734,1734);
        event.setMaterial(Erbium).asMetal(1802,1802);
        event.setMaterial(Thulium).asMetal(1818,1818);
        event.setMaterial(Ytterbium).asMetal(1097,1097);
        event.setMaterial(Lutetium).asMetal(1925, 1925);
        event.setMaterial(Hafnium).asMetal(2506,2506);
        event.setMaterial(Tantalum).asSolid(3290, 0);
        event.setMaterial(Tungsten).asMetal(3695, 3000, FOIL).addTools(2.0F, 6.0F, 512, 3); // Tungstensteel would be the one with tool;
        event.setMaterial(Rhenium).asMetal(3459,3459);
        event.setMaterial(Osmium).asMetal(3306, 3306, SCREW, BOLT, RING, PLATE, FOIL, ROD, WIRE_FINE).addTools(4.0F, 16.0F, 1080, 4);
        event.setMaterial(Iridium).asMetal(2719, 2719, FRAME, PLATE).asOre().addTools(5.0F, 8.0F, 2560, 4);
        event.setMaterial(Platinum).asMetal(2041, 0, PLATE, FOIL, ROD, WIRE_FINE).asOre().addTools(4.5F, 18.0F, 48, 2);
        event.setMaterial(Thallium).asMetal(577, 0);
        event.setMaterial(Lead).asMetal(600, 0, PLATE, PLATE_DENSE, FOIL, ROD, FRAME, BOLT).asOre();
        event.setMaterial(Bismuth).asOre();
        event.setMaterial(Polonium).asMetal(527, 0);
        event.setMaterial(Astatine).asMetal(575, 0);
        event.setMaterial(Francium).asMetal(298, 0);
        event.setMaterial(Radium).asMetal(973, 0);
        event.setMaterial(Actinium).asMetal(1323, 0);
        event.setMaterial(Thorium).asMetal(2115, 0, PLATE_DENSE).asOre().addTools(1.5F, 6.0F, 512, 2);
        event.setMaterial(Protactinium).asMetal(1841, 0);
        event.setMaterial(Uranium).asMetal(1405, 0).asOre();
        event.setMaterial(Neptunium).asMetal(912, 0);
        event.setMaterial(Plutonium).asMetal(912, 0).addTools(2.5F, 6.0F, 280, 3, of(Enchantments.FIRE_ASPECT, 2)); // TODO: Enchantment: Radioactivit;
        event.setMaterial(Americium).asMetal(1149, 0);
        event.setMaterial(Curium).asMetal(1613, 0);
        event.setMaterial(Berkelium).asMetal(1259, 0);
        event.setMaterial(Californium).asMetal(1173, 0);
        event.setMaterial(Einsteinium).asMetal(1133, 0);
        event.setMaterial(Fermium).asMetal(1125, 0);
        event.setMaterial(Mendelevium).asMetal(1111, 0);
        /**
         *** PSE (Fluids, no Isotopes)
         **/
        event.setMaterial(Mercury).asFluid();
        /**
         *** PSE (Gases, no Isotopes)
         **/
        event.setMaterial(Hydrogen).asGas();
        event.setMaterial(Helium).asPlasma();
        event.setMaterial(Nitrogen).asPlasma();
        event.setMaterial(Oxygen).asPlasma();
        event.setMaterial(Fluorine).asGas();
        event.setMaterial(Neon).asPlasma();
        event.setMaterial(Chlorine).asGas();
        event.setMaterial(Argon).asGas();
        event.setMaterial(Xenon).asPlasma();
        event.setMaterial(Radon).asGas();
        /**
         *** Isotopes (Solids)
         **/
        event.setMaterial(Thallium207).asMetal(577, 0).mats(of(Thallium, 1));
        event.setMaterial(Thallium209).asMetal(577, 0).mats(of(Thallium, 1));
        event.setMaterial(Thallium210).asMetal(577, 0).mats(of(Thallium, 1));
        event.setMaterial(Bismuth211).asMetal(544, 0).mats(of(Bismuth, 1));
        event.setMaterial(Bismuth213).asMetal(544, 0).mats(of(Bismuth, 1));
        event.setMaterial(Bismuth214).asMetal(544, 0).mats(of(Bismuth, 1));
        event.setMaterial(Bismuth215).asMetal(544, 0).mats(of(Bismuth, 1));
        event.setMaterial(Polonium211).asMetal(527, 0).mats(of(Polonium, 1));
        event.setMaterial(Polonium213).asMetal(527, 0).mats(of(Polonium, 1));
        event.setMaterial(Polonium214).asMetal(527, 0).mats(of(Polonium, 1));
        event.setMaterial(Polonium215).asMetal(527, 0).mats(of(Polonium, 1));
        event.setMaterial(Polonium216).asMetal(527, 0).mats(of(Polonium, 1));
        event.setMaterial(Polonium218).asMetal(527, 0).mats(of(Polonium, 1));
        event.setMaterial(Astatine215).asMetal(575, 0).mats(of(Astatine, 1));
        event.setMaterial(Astatine217).asMetal(575, 0).mats(of(Astatine, 1));
        event.setMaterial(Astatine218).asMetal(575, 0).mats(of(Astatine, 1));
        event.setMaterial(Astatine219).asMetal(575, 0).mats(of(Astatine, 1));
        event.setMaterial(Francium221).asMetal(298, 0).mats(of(Francium, 1));
        event.setMaterial(Francium223).asMetal(298, 0).mats(of(Francium, 1));
        event.setMaterial(Radium221).asMetal(973, 0).mats(of(Radium, 1));
        event.setMaterial(Radium223).asMetal(973, 0).mats(of(Radium, 1));
        event.setMaterial(Radium224).asMetal(973, 0).mats(of(Radium, 1));
        event.setMaterial(Radium225).asMetal(973, 0).mats(of(Radium, 1));
        event.setMaterial(Radium226).asMetal(973, 0).mats(of(Radium, 1));
        event.setMaterial(Radium228).asMetal(973, 0).mats(of(Radium, 1));
        event.setMaterial(Actinium225).asMetal(1323, 1323).mats(of(Actinium, 1));
        event.setMaterial(Actinium227).asMetal(1323, 1323).mats(of(Actinium, 1));
        event.setMaterial(Actinium228).asMetal(1323, 1323).mats(of(Actinium, 1));
        event.setMaterial(Thorium227).asMetal(2028, 0).mats(of(Thorium, 1));
        event.setMaterial(Thorium228).asMetal(2028, 0).mats(of(Thorium, 1));
        event.setMaterial(Thorium229).asMetal(2028, 0).mats(of(Thorium, 1));
        event.setMaterial(Thorium230).asMetal(2028, 0).mats(of(Thorium, 1));
        event.setMaterial(Thorium231).asMetal(2028, 0).mats(of(Thorium, 1));
        event.setMaterial(Thorium232).asMetal(2028, 0).mats(of(Thorium, 1));
        event.setMaterial(Thorium233).asMetal(2028, 0).mats(of(Thorium, 1));
        event.setMaterial(Thorium234).asMetal(2028, 0).mats(of(Thorium, 1));
        event.setMaterial(Protactinium231).asMetal(1841, 0).mats(of(Protactinium, 1));
        event.setMaterial(Protactinium232).asMetal(1841, 0).mats(of(Protactinium, 1));
        event.setMaterial(Protactinium233).asMetal(1841, 0).mats(of(Protactinium, 1));
        event.setMaterial(Protactinium234).asMetal(1841, 0).mats(of(Protactinium, 1));
        event.setMaterial(Uranium232).asMetal(1406, 0).mats(of(Uranium, 1));
        event.setMaterial(Uranium233).asMetal(1406, 0).mats(of(Uranium, 1));
        event.setMaterial(Uranium234).asMetal(1406, 0).mats(of(Uranium, 1));
        event.setMaterial(Uranium235).asMetal(1406, 0).mats(of(Uranium, 1));
        event.setMaterial(Uranium236).asMetal(1406, 0).mats(of(Uranium, 1));
        event.setMaterial(Uranium237).asMetal(1406, 0).mats(of(Uranium, 1));
        event.setMaterial(Uranium238).asMetal(1406, 0).mats(of(Uranium, 1));
        event.setMaterial(Uranium239).asMetal(1406, 0).mats(of(Uranium, 1));
        event.setMaterial(Uranium240).asMetal(1406, 0).mats(of(Uranium, 1));
        event.setMaterial(Neptunium236).asMetal(912, 0).mats(of(Neptunium, 1));
        event.setMaterial(Neptunium237).asMetal(912, 0).mats(of(Neptunium, 1));
        event.setMaterial(Neptunium238).asMetal(912, 0).mats(of(Neptunium, 1));
        event.setMaterial(Neptunium239).asMetal(912, 0).mats(of(Neptunium, 1));
        event.setMaterial(Neptunium240).asMetal(912, 0).mats(of(Neptunium, 1));
        event.setMaterial(Plutonium236).asMetal(1406, 0).mats(of(Plutonium, 1));
        event.setMaterial(Plutonium238).asMetal(1406, 0).mats(of(Plutonium, 1));
        event.setMaterial(Plutonium239).asMetal(912, 0).mats(of(Plutonium, 1));
        event.setMaterial(Plutonium240).asMetal(912, 0).mats(of(Plutonium, 1));
        event.setMaterial(Plutonium241).asMetal(912, 0).mats(of(Plutonium, 1));
        event.setMaterial(Plutonium242).asMetal(912, 0).mats(of(Plutonium, 1));
        event.setMaterial(Plutonium243).asMetal(912, 0).mats(of(Plutonium, 1));
        event.setMaterial(Plutonium244).asMetal(912, 0).mats(of(Plutonium, 1));
        event.setMaterial(Plutonium246).asMetal(912, 0).mats(of(Plutonium, 1));
        event.setMaterial(Americium241).asMetal(1449, 0).mats(of(Americium, 1));
        event.setMaterial(Americium242).asMetal(1449, 0).mats(of(Americium, 1));
        event.setMaterial(Americium244).asMetal(1449, 0).mats(of(Americium, 1));
        event.setMaterial(Americium245).asMetal(1449, 0).mats(of(Americium, 1));
        event.setMaterial(Americium246).asMetal(1449, 0).mats(of(Americium, 1));
        event.setMaterial(Curium242).asMetal(1613, 0).mats(of(Curium, 1));
        event.setMaterial(Curium244).asMetal(1613, 0).mats(of(Curium, 1));
        event.setMaterial(Curium245).asMetal(1613, 0).mats(of(Curium, 1));
        event.setMaterial(Curium246).asMetal(1613, 0).mats(of(Curium, 1));
        event.setMaterial(Curium247).asMetal(1613, 0).mats(of(Curium, 1));
        event.setMaterial(Curium248).asMetal(1613, 0).mats(of(Curium, 1));
        event.setMaterial(Curium249).asMetal(1613, 0).mats(of(Curium, 1));
        event.setMaterial(Curium250).asMetal(1613, 0).mats(of(Curium, 1));
        event.setMaterial(Berkelium248).asMetal(1259, 0).mats(of(Berkelium, 1));
        event.setMaterial(Berkelium249).asMetal(1259, 0).mats(of(Berkelium, 1));
        event.setMaterial(Berkelium250).asMetal(1259, 0).mats(of(Berkelium, 1));
        event.setMaterial(Berkelium251).asMetal(1259, 0).mats(of(Berkelium, 1));
        event.setMaterial(Californium248).asMetal(1173, 0).mats(of(Californium, 1));
        event.setMaterial(Californium249).asMetal(1173, 0).mats(of(Californium, 1));
        event.setMaterial(Californium250).asMetal(1173, 0).mats(of(Californium, 1));
        event.setMaterial(Californium251).asMetal(1173, 0).mats(of(Californium, 1));
        event.setMaterial(Californium252).asMetal(1173, 0).mats(of(Californium, 1));
        event.setMaterial(Californium253).asMetal(1173, 0).mats(of(Californium, 1));
        event.setMaterial(Californium254).asMetal(1173, 0).mats(of(Californium, 1));
        event.setMaterial(Californium255).asMetal(1173, 0).mats(of(Californium, 1));
        event.setMaterial(Einsteinium253).asMetal(1133, 0).mats(of(Einsteinium, 1));
        event.setMaterial(Einsteinium254).asMetal(1133, 0).mats(of(Einsteinium, 1));
        event.setMaterial(Einsteinium255).asMetal(1133, 0).mats(of(Einsteinium, 1));
        event.setMaterial(Einsteinium256).asMetal(1133, 0).mats(of(Einsteinium, 1));
        event.setMaterial(Fermium255).asMetal(1125, 0).mats(of(Fermium, 1));
        event.setMaterial(Fermium256).asMetal(1125, 0).mats(of(Fermium, 1));
        event.setMaterial(Fermium257).asMetal(1125, 0).mats(of(Fermium, 1));
        event.setMaterial(Fermium258).asMetal(1125, 0).mats(of(Fermium, 1));
        event.setMaterial(Fermium259).asMetal(1125, 0).mats(of(Fermium, 1));
        event.setMaterial(Fermium260).asMetal(1125, 0).mats(of(Fermium, 1));
        event.setMaterial(Mendelevium259).asMetal(1111, 0).mats(of(Mendelevium, 1));
        event.setMaterial(Mendelevium260).asMetal(1111, 0).mats(of(Mendelevium, 1));
        /**
         *** Isotopes (Fluids)
         **/
        /**
         *** Isotopes (Gases/Plasmas)
         **/
        event.setMaterial(Deuterium).asGas();
        event.setMaterial(Tritium).asGas();
        event.setMaterial(Helium3).asGas();
        event.setMaterial(Radon217).asGas().mats(of(Radon, 1));
        event.setMaterial(Radon218).asGas().mats(of(Radon, 1));
        event.setMaterial(Radon219).asGas().mats(of(Radon, 1));
        event.setMaterial(Radon220).asGas().mats(of(Radon, 1));
        event.setMaterial(Radon222).asGas().mats(of(Radon, 1));
        /**
         ***  Solids
         **/
        /**
         **  Metals
         **/
        event.setMaterial(AnnealedCopper).asMetal(1357, 0, PLATE, FOIL, ROD, WIRE_FINE, SCREW).mats(of(Copper, 1));
        event.setMaterial(BatteryAlloy).asMetal(295, 0, PLATE).mats(of(Lead, 4, Antimony, 1));
        event.setMaterial(BismuthBronze).asMetal(1100, 900, PLATE).addTools(2.5F, 8.5F, 350, 2, of(Enchantments.BANE_OF_ARTHROPODS, 4)).mats(of(Bismuth, 1, Zinc, 1, Copper, 3));
        event.setMaterial(BlackBronze).asMetal(2000, 2000).addTools(1.5F, 6.5F, 182, 2, of(Enchantments.SWEEPING_EDGE, 1)).mats(of(Gold, 1, Silver, 1, Copper, 3));
        event.setMaterial(BlackSteel).asMetal(1200, 1200, FRAME, PLATE).addTools(3.5F, 6.5F, 768, 2).mats(of(Nickel, 1, BlackBronze, 1, Steel, 3));
        event.setMaterial(BlueSteel).asMetal(1400, 1400, FRAME).addTools(3.5F, 7.5F, 1024, 2).mats(of(RoseGold, 1, Brass, 1, Steel, 2, BlackSteel, 4));
        event.setMaterial(Brass).asMetal(1170, 0, FRAME, ROD, PLATE).mats(of(Zinc, 1, Copper, 3));
        event.setMaterial(Bronze).asMetal(1125, 0, GEAR, FRAME, ROTOR).addTools(1.5F, 6.5F, 182, 2, of(Enchantments.UNBREAKING, 1)).mats(of(Tin, 1, Copper, 3));
        event.setMaterial(CobaltBrass).asMetal(1500, 0, GEAR).addTools(2.5F, 8.0F, 256, 2).mats(of(Brass, 7, Aluminium, 1, Cobalt, 1));
        event.setMaterial(Cupronickel).asMetal(1728, 0, PLATE).mats(of(Copper, 1, Nickel, 1));
        event.setMaterial(Duranium).asMetal(295, 0).addHandleStat(620, -1.0F, of(Enchantments.SILK_TOUCH, 1)).addTools(6.5F, 16.0F, 5120, 5);
        event.setMaterial(Electrum).asMetal(1330, 0, PLATE, FOIL, ROD, WIRE_FINE).addTools(1.0F, 13.0F, 48, 2, of(Enchantments.UNBREAKING, 3)).mats(of(Silver, 1, Gold, 1));
        event.setMaterial(EnrichedNaquadah).asMetal(4500, 4500).asOre().addTools(5.0F, 6.0F, 1280, 4);
        event.setMaterial(HSSE).asMetal(5400, 5400, GEAR, FRAME).addTools(4.2F, 10.0F, 5120, 4).mats(of(HSSG, 6, Cobalt, 1, Manganese, 1, Silicon, 1));
        event.setMaterial(HSSG).asMetal(4500, 4500, GEAR, FRAME).addTools(3.8F, 10.0F, 4000, 3).mats(of(TungstenSteel, 5, Chrome, 1, Molybdenum, 2, Vanadium, 1));
        event.setMaterial(HSSS).asMetal(5400, 5400).addTools(5.0F, 14.0F, 3000, 4).mats(of(HSSG, 6, Iridium, 2, Osmium, 1));
        event.setMaterial(Invar).asMetal(1700, 0, FRAME).addTools(2.5F, 7.0F, 320, 2, of(Enchantments.BANE_OF_ARTHROPODS, 2)).mats(of(Iron, 2, Nickel, 1));
        event.setMaterial(IronMagnetic).asMetal(1811, 0, ROD).addTools(Iron).mats(of(Iron, 1));
        event.setMaterial(Kanthal).asMetal(1800, 1800).addTools(2.5F, 6.0F, 64, 2, of(Enchantments.BANE_OF_ARTHROPODS, 1)).mats(of(Iron, 1, Aluminium, 1, Chrome, 1));
        event.setMaterial(Magnalium).asMetal(870, 0, PLATE).mats(of(Magnesium, 1, Aluminium, 2));
        event.setMaterial(NeodymiumMagnetic).asMetal(1297, 1297, ROD).mats(of(Neodymium, 1));
        event.setMaterial(Neutronium).asMetal(10000, 10000, SCREW, BOLT, RING, GEAR, FRAME).addTools(9.0F, 24.0F, 655360, 6);
        event.setMaterial(Naquadah).asMetal(5400, 5400).asOre().addHandleStat(102, 0.5F, of(Enchantments.BLOCK_EFFICIENCY, 2)).addTools(4.0F, 6.0F, 890, 4);
        event.setMaterial(NaquadahAlloy).asMetal(7200, 7200).addTools(4.5F, 8.0F, 5120, 5);
        event.setMaterial(Naquadria).asMetal(9000, 9000);
        event.setMaterial(Nichrome).asMetal(2700, 2700).addTools(2.0F, 6.0F, 81, 2, of(Enchantments.BANE_OF_ARTHROPODS, 3)).mats(of(Nickel, 4, Chrome, 1));
        event.setMaterial(NickelZincFerrite).asMetal(1500, 1500).addTools(0.0F, 3.0F, 32, 1).mats(of(Nickel, 1, Zinc, 1, Iron, 4, Oxygen, 8));
        event.setMaterial(NiobiumTitanium).asMetal(4500, 4500, PLATE, FOIL, ROD, WIRE_FINE, GEAR).mats(of(Nickel, 4, Chrome, 1));
        event.setMaterial(Osmiridium).asMetal(3333, 2500, FRAME).addTools(6.0F, 15.0F, 1940, 5).mats(of(Iridium, 3, Osmium, 1));
        event.setMaterial(RedAlloy).asMetal(295, 0, PLATE, FOIL, ROD, WIRE_FINE).mats(of(Copper, 1, Redstone, 4));
        event.setMaterial(RedSteel).asMetal(1300, 1300).addTools(3.5F, 7.0F, 896, 2).mats(of(SterlingSilver, 1, BismuthBronze, 1, Steel, 2, BlackSteel, 4));
        event.setMaterial(RoseGold).asMetal(1600, 1600, WIRE_FINE).addTools(Gold, of(Enchantments.BLOCK_FORTUNE, 3, Enchantments.SMITE, 3)).mats(of(Copper, 1, Gold, 4));
        event.setMaterial(SolderingAlloy).asMetal(400, 400, PLATE, FOIL, ROD, WIRE_FINE).mats(of(Tin, 9, Antimony, 1));
        event.setMaterial(Steel).asMetal(1811, 1000, PLATE, ROD, SCREW, BOLT, RING, GEAR, FRAME, ROTOR, GEAR_SMALL).addTools(Iron).mats(of(Iron, 50, Carbon, 1));
        event.setMaterial(SteelMagnetic).asMetal(1000, 1000, ROD).addTools(Steel).mats(of(Steel, 1));
        event.setMaterial(SterlingSilver).asMetal(1700, 1700).addTools(3.0F, 10.5F, 96, 2, of(Enchantments.BLOCK_EFFICIENCY, 2)).mats(of(Copper, 1, Silver, 4));
        event.setMaterial(StainlessSteel).asMetal(1700, 1700, PLATE, ROD, SCREW, BOLT, RING, GEAR, FRAME, ROTOR, GEAR_SMALL).addTools(Steel).mats(of(Iron, 6, Chrome, 1, Manganese, 1, Nickel, 1));
        event.setMaterial(TinAlloy).asMetal(1158,1158).mats(of(Tin, 1, Iron, 1));
        event.setMaterial(Tritanium).asMetal(295, 0, FRAME).addTools(9.0F, 15.0F, 9400, 6);
        event.setMaterial(TungstenCarbide).asMetal(2460, 2460).addTools(5.0F, 14.0F, 1280, 4).mats(of(Tungsten, 1, Carbon, 1));
        event.setMaterial(TungstenSteel).asMetal(3000, 3000, PLATE, ROD, SCREW, BOLT, RING, GEAR, FRAME, ROTOR, GEAR_SMALL).asOre().addTools(4.0F, 8.0F, 2560, 4).mats(of(Steel, 1, Tungsten, 1));
        event.setMaterial(Ultimet).asMetal(2700, 2700, PLATE).mats(of(Cobalt, 5, Chrome, 2, Nickel, 1, Molybdenum, 1));
        event.setMaterial(VanadiumGallium).asMetal(4500, 4500, ROD, PLATE).mats(of(Vanadium, 3, Gallium, 1));
        event.setMaterial(VanadiumSteel).asMetal(1453, 1453).addTools(3.0F, 5.0F, 1920, 3).mats(of(Vanadium, 1, Chrome, 1, Steel, 7));
        event.setMaterial(Vibranium).asMetal(295, 0, FRAME).addTools(10.0F, 20.0F, 12240, 6);
        event.setMaterial(WroughtIron).asMetal(1811, 0, PLATE, ROD, SCREW, BOLT, RING, GEAR, FRAME, ROTOR, GEAR_SMALL).addTools(Iron).mats(of(Iron, 1)).asOre();
        event.setMaterial(YttriumBariumCuprate).asMetal(4500, 4500, PLATE, FOIL, ROD, WIRE_FINE).mats(of(Yttrium, 1, Barium, 2, Copper, 3, Oxygen, 7));
        /**
         **  Dusts
         **/
        /**
         *  Organic
         **/
        event.setMaterial(Ash).asDust();
        event.setMaterial(AntimonyTrioxide).asDust().mats(of(Antimony, 2, Oxygen, 3));
        event.setMaterial(Clay).asDust().mats(of(Sodium, 2, Lithium, 1, Aluminium, 2, Silicon, 2, Water, 6));
        event.setMaterial(CupricOxide).asDust().mats(of(Copper, 1, Oxygen, 1));
        event.setMaterial(DarkAsh).asDust();
        event.setMaterial(Dibenzene).asDust().mats(of(Carbon,12,Hydrogen,10));
        event.setMaterial(DibenzoylPeroxide).asDust().mats(of(Carbon,14,Hydrogen,10,Oxygen,4));
        event.setMaterial(GelledToluene).asDust();
        event.setMaterial(Graphene).asDust(PLATE);
        event.setMaterial(Polydimethylsiloxane).asDust().mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 1, Silicon, 1));
        event.setMaterial(RareEarth).asDust();
        event.setMaterial(RawRubber).asDust().mats(of(Carbon, 5, Hydrogen, 8));
        event.setMaterial(RawStyreneButadieneRubber).asDust().mats(of(Styrene, 1, Butadiene, 3));
        event.setMaterial(SodaAsh).asDust().mats(of(Sodium, 2, Carbon, 1, Oxygen, 3));
        event.setMaterial(Wood).asDust(GEAR).addHandleStat(12, 0.0F).mats(of(Carbon, 1, Oxygen, 1, Hydrogen, 1));
        event.setMaterial(WoodPulp).asDust();
        /**
         *  Inorganic
         **/
        event.setMaterial(AluminiumTrichloride).asDust().mats(of(Aluminium,1,Chlorine,3));
        event.setMaterial(AmmoniumChloride).asDust().mats(of(Nitrogen,1, Hydrogen,4,Chlorine,1));
        event.setMaterial(ArsenicTrioxide).asDust().mats(of(Arsenic, 2, Oxygen, 3));
        event.setMaterial(Biotite).asDust().mats(b -> b.put(Potassium, 1).put(Magnesium, 3).put(Aluminium, 3).put(Fluorine, 2).put(Silicon, 3).put(Oxygen, 10));
        event.setMaterial(BlackGranite).asDust(ROCK).addHandleStat(74, 1.0F, of(Enchantments.UNBREAKING, 1)).mats(of(SiliconDioxide, 4, Biotite, 1));
        event.setMaterial(BlueSchist).asDust(ROCK);
        event.setMaterial(BorosilicateGlass).asDust().mats(of(Boron, 1, Silicon, 7, Oxygen,14));
        event.setMaterial(Brick).asDust().mats(of(Aluminium, 4, Silicon, 3, Oxygen, 12));
        event.setMaterial(CobaltOxide).asDust().mats(of(Cobalt, 1, Oxygen, 1));
        event.setMaterial(Concrete).asDust(300).mats(of(Stone, 1)).asFluid();
        event.setMaterial(DialuminiumTrioxide).asDust().mats(of(Aluminium,2,Oxygen,3));
        event.setMaterial(Energium).asDust().mats(of(Redstone,1,Ruby,1));
        event.setMaterial(FerriteMixture).asDust().mats(of(Nickel, 1, Zinc, 1, Iron, 4));
        event.setMaterial(Ferrosilite).asDust().mats(of(Iron, 1, Silicon, 1, Oxygen, 3));
        event.setMaterial(Fireclay).asDust(INGOT, PLATE).mats(of(Brick, 1));
        event.setMaterial(GreenSchist).asDust(ROCK);
        event.setMaterial(IndiumGalliumPhosphide).asDust().mats(of(Indium, 1, Gallium, 1, Phosphor, 1));
        event.setMaterial(IridiumSodiumOxide).asDust().mats(of(Iridium,1,Sodium,1,Oxygen,2));
        event.setMaterial(Kimberlite).asDust(ROCK);
        event.setMaterial(Komatiite).asDust(ROCK).mats(of(Olivine, 1, /* MgCO3, 2, */Flint, 6, DarkAsh, 3));
        event.setMaterial(Limestone).asDust(ROCK).mats(of(Calcite, 1));
        event.setMaterial(Magnesia).asDust().mats(of(Magnesium, 1, Oxygen, 1));
        event.setMaterial(MagnesiumChloride).asDust().mats(of(Magnesium, 1, Chlorine, 2));
        event.setMaterial(Marble).asDust(ROCK).mats(of(Magnesium, 1, Calcite, 7));
        event.setMaterial(Massicot).asDust().mats(of(Lead, 1, Oxygen, 1));
        event.setMaterial(Obsidian).asDust().addHandleStat(222, -0.5F, of(Enchantments.UNBREAKING, 2)).mats(of(Magnesium, 1, Iron, 1, Silicon, 2, Oxygen, 8));
        event.setMaterial(PlatinumGroupSludge).asDust();
        event.setMaterial(PhosphorousPentoxide).asDust().mats(of(Phosphor, 4, Oxygen, 10));
        event.setMaterial(Potash).asDust().mats(of(Potassium, 2, Oxygen, 1));
        event.setMaterial(PotassiumFeldspar).asDust().mats(of(Potassium, 1, Aluminium, 1, Silicon, 3, Oxygen, 8));
        //event.setMaterial(Powellite).asDust(ORE).mats(of(Calcium, 1, Molybdenum, 1, Oxygen, 4));
        //event.setMaterial(Pyrochlore).asDust(ORE).addComposition(of(Calcium, 2, Niobium, 2, Oxygen, 7));
        event.setMaterial(Quicklime).asDust().mats(of(Calcium, 1, Oxygen, 1));
        event.setMaterial(ReactionCatalyst).asDust().mats(of(Copper,1,Zinc,1,Aluminium,2,Oxygen,4));
        event.setMaterial(RedGranite).asDust(ROCK).addHandleStat(74, 1.0F, of(Enchantments.UNBREAKING, 1)).mats(of(Aluminium, 2, PotassiumFeldspar, 1, Oxygen, 3));
        event.setMaterial(SiliconDioxide).asDust().mats(of(Silicon, 1, Oxygen, 2));
        event.setMaterial(SodiumBisulfate).asDust().mats(of(Sodium, 1, Hydrogen, 1, Sulfur, 1, Oxygen, 4));
        event.setMaterial(SodiumHydroxide).asDust().mats(of(Sodium, 1, Oxygen, 1, Hydrogen, 1));
        event.setMaterial(SodiumSulfate).asDust().mats(of(Sodium, 2, Sulfur, 1, Oxygen, 4));
        event.setMaterial(SodiumSulfide).asDust().mats(of(Sodium, 2, Sulfur, 1));
        //Nuclear
        event.setMaterial(ThoriumCake).asDust().mats(of(ThoriumDioxide,1,TrithoriumOctoxide,4));
        event.setMaterial(UraniumCake).asDust().mats(of(UraniumDioxide,1,TriuraniumOctoxide,4));
        event.setMaterial(ThoriumDioxide).asDust().mats(of(Thorium,1,Oxygen,2));
        event.setMaterial(Thorium227Dioxide).asDust().mats(of(Thorium227,1,Oxygen,2));
        event.setMaterial(Thorium228Dioxide).asDust().mats(of(Thorium228,1,Oxygen,2));
        event.setMaterial(Thorium229Dioxide).asDust().mats(of(Thorium229,1,Oxygen,2));
        event.setMaterial(Thorium230Dioxide).asDust().mats(of(Thorium230,1,Oxygen,2));
        event.setMaterial(Thorium231Dioxide).asDust().mats(of(Thorium231,1,Oxygen,2));
        event.setMaterial(Thorium233Dioxide).asDust().mats(of(Thorium233,1,Oxygen,2));
        event.setMaterial(Thorium234Dioxide).asDust().mats(of(Thorium234,1,Oxygen,2));
        event.setMaterial(UraniumDioxide).asDust().mats(of(Uranium,1,Oxygen,2));
        event.setMaterial(Uranium232Dioxide).asDust().mats(of(Uranium232,1,Oxygen,2));
        event.setMaterial(Uranium233Dioxide).asDust().mats(of(Uranium233,1,Oxygen,2));
        event.setMaterial(Uranium234Dioxide).asDust().mats(of(Uranium234,1,Oxygen,2));
        event.setMaterial(Uranium235Dioxide).asDust().mats(of(Uranium235,1,Oxygen,2));
        event.setMaterial(Uranium236Dioxide).asDust().mats(of(Uranium236,1,Oxygen,2));
        event.setMaterial(Uranium237Dioxide).asDust().mats(of(Uranium237,1,Oxygen,2));
        event.setMaterial(Uranium238Dioxide).asDust().mats(of(Uranium238,1,Oxygen,2));
        event.setMaterial(Uranium239Dioxide).asDust().mats(of(Uranium239,1,Oxygen,2));
        event.setMaterial(Uranium240Dioxide).asDust().mats(of(Uranium240,1,Oxygen,2));
        event.setMaterial(TrithoriumOctoxide).asDust().mats(of(Thorium,3,Oxygen,8));
        event.setMaterial(TriuraniumOctoxide).asDust().mats(of(Uranium,3,Oxygen,8));
        /**
         **  Ores
         **/
        event.setMaterial(Almandine).asOre().mats(of(Aluminium, 2, Iron, 3, Silicon, 3, Oxygen, 12));
        event.setMaterial(Andradite).asOre().mats(of(Calcium, 3, Iron, 2, Silicon, 3, Oxygen, 12));
        event.setMaterial(BandedIron).asOre(true).mats(of(Iron, 2, Oxygen, 3));
        event.setMaterial(Bastnasite).asOre(true).mats(of(Cerium, 1, Carbon, 1, Fluorine, 1, Oxygen, 3));
        event.setMaterial(Barite).asOre(true).mats(of(Barium, 1, Sulfur, 1, Oxygen, 4));
        event.setMaterial(Bentonite).asOre(true).mats(b -> b.put(Sodium, 1).put(Magnesium, 6).put(Silicon, 12).put(Hydrogen, 6).put(Water, 5).put(Oxygen, 36)); // TODO: Ore Gen
        event.setMaterial(BrownLimonite).asOre(true).mats(of(Iron, 1, Hydrogen, 1, Oxygen, 2));
        event.setMaterial(Calcite).asOre(true).mats(of(Calcium, 1, Carbon, 1, Oxygen, 3));
        event.setMaterial(Cassiterite).asOre().mats(of(Tin, 1, Oxygen, 2));
        event.setMaterial(Chalcopyrite).asOre().mats(of(Copper, 1, Iron, 1, Sulfur, 2));
        event.setMaterial(Cinnabar).asOre(true).mats(of(Mercury, 1, Sulfur, 1));
        event.setMaterial(Cobaltite).asOre(true).mats(of(Cobalt, 1, Arsenic, 1, Sulfur, 1));
        event.setMaterial(Cooperite).asOre().mats(of(Platinum, 3, Nickel, 1, Sulfur, 1, Palladium, 1));
        event.setMaterial(Galena).asOre().mats(of(Lead, 3, Silver, 3, Sulfur, 2));
        event.setMaterial(Garnierite).asOre().mats(of(Nickel, 1, Oxygen, 1));
        event.setMaterial(Glauconite).asOre(true).mats(of(Potassium, 1, Magnesium, 2, Aluminium, 4, Hydrogen, 2, Oxygen, 12)); // TODO: Ore Gen;
        event.setMaterial(Graphite).asDust().asOre();
        event.setMaterial(Grossular).asOre().mats(of(Calcium, 3, Aluminium, 2, Silicon, 3, Oxygen, 12));
        event.setMaterial(Ilmenite).asOre().mats(of(Iron, 1, Titanium, 1, Oxygen, 3));
        event.setMaterial(Lepidolite).asOre(true).mats(of(Potassium, 1, Lithium, 3, Aluminium, 4, Fluorine, 2, Oxygen, 10)); // TODO: Ore Gen;
        event.setMaterial(Rutile).asOre().mats(of(Titanium, 1, Oxygen, 2)); event.setMaterial(Cobaltite).asOre(true).mats(of(Cobalt, 1, Arsenic, 1, Sulfur, 1));
        event.setMaterial(Magnesite).asOre(true).mats(of(Magnesium, 1, Carbon, 1, Oxygen, 3));
        event.setMaterial(Magnetite).asOre().mats(of(Iron, 3, Oxygen, 4));
        event.setMaterial(Malachite).asOre(true).mats(of(Copper, 2, Carbon, 1, Hydrogen, 2, Oxygen, 5));
        event.setMaterial(Molybdenite).asOre(true).mats(of(Molybdenum, 1, Sulfur, 2));
        event.setMaterial(Oilsands).asOre(true);
        event.setMaterial(Pentlandite).asOre().mats(of(Nickel, 9, Sulfur, 8));
        event.setMaterial(Phosphate).asOre(true).mats(of(Phosphor, 1, Oxygen, 4));
        event.setMaterial(Pitchblende).asOre(true).mats(of(Uraninite, 3, Thorium, 1, Lead, 1));
        event.setMaterial(Pyrite).asOre().mats(of(Iron, 1, Sulfur, 2));
        event.setMaterial(Pyrolusite).asOre().mats(of(Manganese, 1, Oxygen, 2));
        event.setMaterial(Pyrope).asOre().mats(of(Aluminium, 2, Magnesium, 3, Silicon, 3, Oxygen, 12));
        event.setMaterial(Saltpeter).asOre().mats(of(Potassium, 1, Nitrogen, 1, Oxygen, 3));
        event.setMaterial(Scheelite).asDust(2500).asOre(true).mats(of(Tungsten, 1, Calcium, 2, Oxygen, 4));
        event.setMaterial(Soapstone).asOre(true).mats(of(Magnesium, 3, Silicon, 4, Hydrogen, 2, Oxygen, 12)); // TODO: Ore Gen;
        event.setMaterial(Spodumene).asOre(true).mats(of(Lithium, 1, Aluminium, 1, Silicon, 2, Oxygen, 6));
        event.setMaterial(Spessartine).asOre().mats(of(Aluminium, 2, Manganese, 3, Silicon, 3, Oxygen, 12));
        event.setMaterial(Sphalerite).asOre().mats(of(Zinc, 1, Sulfur, 1));
        event.setMaterial(Stibnite).asOre().mats(of(Antimony, 2, Sulfur, 3));
        event.setMaterial(Tantalite).asOre(true).mats(of(Manganese, 1, Tantalum, 2, Oxygen, 6));
        event.setMaterial(Talc).asOre(true).mats(of(Magnesium, 3, Silicon, 4, Hydrogen, 2, Oxygen, 12));
        event.setMaterial(Tetrahedrite).asOre(true).mats(of(Copper, 3, Antimony, 1, Sulfur, 3, Iron, 1));
        event.setMaterial(Tungstate).asOre(true).mats(of(Tungsten, 1, Lithium, 2, Oxygen, 4));
        event.setMaterial(Uraninite).asOre(true).mats(of(Uranium, 1, Oxygen, 2));
        event.setMaterial(Uvarovite).asOre().mats(of(Calcium, 3, Chrome, 2, Silicon, 3, Oxygen, 12));
        event.setMaterial(VanadiumMagnetite).asOre(true).mats(of(Magnetite, 1, Vanadium, 1));
        event.setMaterial(Wulfenite).asOre(true).mats(of(Lead, 1, Molybdenum, 1, Oxygen, 4));
        event.setMaterial(YellowLimonite).asOre(true).mats(of(Iron, 1, Hydrogen, 1, Oxygen, 2));
        /**
         **  Ore Stones
         **/
        event.setMaterial(Bauxite).asOreStone(ORE_SMALL).mats(of(Rutile, 2, Aluminium, 16, Hydrogen, 10, Oxygen, 11));
        event.setMaterial(Lignite).asGemBasic(false).asOreStone(0, 2, ORE_SMALL).mats(of(Carbon, 3, Water, 1));
        event.setMaterial(OilShale).asOreStone(ORE_SMALL);
        event.setMaterial(RockSalt).asOreStone(ORE_SMALL).mats(of(Potassium, 1, Chlorine, 1));
        event.setMaterial(Salt).asOreStone(ORE_SMALL).mats(of(Sodium, 1, Chlorine, 1));
        /**
         **  Gems
         **/
        /**
         *  Regular
         **/
        event.setMaterial(Amethyst).asGem(true).asOre(3, 7, true).addTools(2.0F, 7.0F, 256, 3).mats(of(SiliconDioxide, 4, Iron, 1));
        event.setMaterial(BlueSapphire).asGem(true).asOre(3, 7, true).addTools(2.0F, 7.0F, 256, 2).mats(of(Aluminium, 2, Oxygen, 3));
        event.setMaterial(BlueTopaz).asGem(true).asOre(3, 7, true).addTools(2.5F, 7.0F, 256, 3).mats(of(Aluminium, 2, Silicon, 1, Fluorine, 2, Hydrogen, 2, Oxygen, 6));
        event.setMaterial(Glass).asDust(PLATE, LENS).mats(of(SiliconDioxide, 1));
        event.setMaterial(GreenSapphire).asGem(true).asOre(3, 7, true).addTools(2.0F, 7.0F, 256, 2).mats(of(Aluminium, 2, Oxygen, 3));
        event.setMaterial(Olivine).asGem(true).asOre(3, 7, true).addTools(2.0F, 7.0F, 256, 2, of(Enchantments.SILK_TOUCH, 1)).mats(of(Magnesium, 2, Iron, 1, SiliconDioxide, 2));
        event.setMaterial(Opal).asGem(true).asOre(3, 7, true).addTools(2.0F, 7.0F, 256, 2).mats(of(SiliconDioxide, 1));
        event.setMaterial(Ruby).asGem(true).asOre(3, 7, true).addTools(2.0F, 7.0F, 256, 2).mats(of(Chrome, 1, Aluminium, 2, Oxygen, 3));
        event.setMaterial(Tanzanite).asGem(true).asOre(3, 7, true).addTools(2.0F, 7.0F, 256, 2).mats(of(Calcium, 2, Aluminium, 3, Silicon, 3, Hydrogen, 1, Oxygen, 13));
        event.setMaterial(Topaz).asGem(true).asOre(3, 7, true).addTools(2.0F, 7.0F, 256, 2).mats(of(Aluminium, 2, Silicon, 1, Fluorine, 2, Hydrogen, 2, Oxygen, 6));
        /**
         *  Basic
         **/
        //event.setMaterial(CertusQuartz).asGemBasic(false,PLATE, ORE).addTools(5.0F, 32, 1; TODO: Only when AE2 is loaded;
        event.setMaterial(Charcoal).asGemBasic(false).mats(of(Carbon, 1));
        event.setMaterial(CoalCoke).asGemBasic(false);
        event.setMaterial(Dilithium).asGemBasic(true);
        event.setMaterial(Lazurite).asGemBasic(false).asOre(2, 5, true).mats(of(Aluminium, 6, Silicon, 6, Calcium, 8, Sodium, 8));
        event.setMaterial(LigniteCoke).asGemBasic(false);
        //event.setMaterial(Monazite).asGemBasic(false,ORE).addComposition(of(RareEarth, 1, Phosphate, 1)); TODO: Add Monazite;
        event.setMaterial(NetherStar).asGemBasic(false).addTools(3.5F, 6.0F, 3620, 4, of(Enchantments.SILK_TOUCH, 1));
        event.setMaterial(Phosphorus).asGemBasic(false).asOre(3, 7, true).mats(of(Calcium, 3, Phosphate, 2));
        event.setMaterial(Quartzite).asGemBasic(false, ROCK, ROD).asOre(2, 5, true).mats(of(Silicon, 1, Oxygen, 2));
        event.setMaterial(RedGarnet).asGemBasic(true).asOre(3, 7, true).mats(of(Pyrope, 3, Almandine, 5, Spessartine, 8));
        event.setMaterial(Sodalite).asGemBasic(false).asOre(2, 5, true).mats(of(Aluminium, 3, Silicon, 3, Sodium, 4, Chlorine, 1));
        event.setMaterial(YellowGarnet).asGemBasic(true).asOre(3, 7, true).mats(of(Andradite, 5, Grossular, 8, Uvarovite, 3));
        /**
         **  Plastic Related
         **/
        event.setMaterial(Epoxid).asSolid(400, 0, PLATE).addHandleStat(70, 1.5F).mats(of(Carbon, 2, Hydrogen, 4, Oxygen, 1));
        event.setMaterial(EpoxidFiberReinforced).asSolid(400, 0, PLATE).mats(of(Epoxid, 1));
        event.setMaterial(Polycaprolactam).asSolid(500, 0).mats(of(Carbon, 6, Hydrogen, 11, Nitrogen, 1, Oxygen, 1));
        event.setMaterial(Polyethylene).asSolid(295, 0, PLATE).asFluid().addHandleStat(66, 0.5F).mats(of(Carbon, 1, Hydrogen, 2));
        event.setMaterial(PolyphenyleneSulfide).asSolid(295, 0, PLATE, FOIL).mats(of(Carbon, 6, Hydrogen, 4, Sulfur, 1));
        event.setMaterial(Polystyrene).asSolid(295, 0).addHandleStat(3, 1.0F).mats(of(Carbon, 8, Hydrogen, 8));
        event.setMaterial(Polytetrafluoroethylene).asSolid(1400, 0, PLATE, FRAME).mats(of(Carbon, 2, Fluorine, 4));
        event.setMaterial(PolyvinylChloride).asSolid(295, 0, PLATE, FOIL).addHandleStat(210, 0.5F).mats(of(Carbon, 2, Hydrogen, 3, Chlorine, 1));
        event.setMaterial(Rubber).asSolid(295, 0, PLATE, RING).addHandleStat(11, 0.4F).mats(of(Carbon, 5, Hydrogen, 8));
        event.setMaterial(Silicone).asSolid(900, 0, PLATE, FOIL).addHandleStat(-40, 2.0F).mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 1, Silicon, 1));
        event.setMaterial(StyreneButadieneRubber).asSolid(295, 0, PLATE, RING).addHandleStat(66, 1.2F).mats(of(Styrene, 1, Butadiene, 3));
        /**
         **  Misc
         **/
        event.setMaterial(GalliumArsenide).asSolid(295, 1200).mats(of(Arsenic, 1, Gallium, 1));
        event.setMaterial(Superconductor).asSolid(PLATE);
        /**
         ***  Fluids
         **/
        /**
         **  With Distillation Products
         **/
        /**
         *  Organic
         **/
        event.setMaterial(Acetone).asFluid(0,0,true,new FluidProduct[]{
                new FluidProduct(Ethenone,"gas",144),
                new FluidProduct(Methane,"gas",144)
        },288).mats(of(Carbon, 3, Hydrogen, 6, Oxygen, 1));
        event.setMaterial(CharcoalByproducts).asFluid(0,0,true,new FluidProduct[]{
                new FluidProduct(WoodTar,"fluid",360),
                new FluidProduct(WoodVinegar,"fluid",720),
                new FluidProduct(WoodGas,"gas",360)
        },1440).mats(of(WoodTar,1,WoodGas,1,WoodVinegar,2));
        event.setMaterial(FermentedBiomass).asFluid(16,0,true,new FluidProduct[]{
                new FluidProduct(AceticAcid,"fluid",25),
                new FluidProduct(Water,"fluid",395),
                new FluidProduct(Ethanol,"fluid",250),
                new FluidProduct(Methanol,"fluid",250),
                new FluidProduct(Ammonia,"gas",100),
                new FluidProduct(Methane,"gas",600),
                new FluidProduct(CarbonDioxide,"gas",400)
        },2000);
        event.setMaterial(SeedOil).asFluid(2,0,true,new FluidProduct[]{new FluidProduct(Lubricant,"fluid",10)
        },20);
        event.setMaterial(WoodTar).asFluid(0,0,true,new FluidProduct[]{
                new FluidProduct(Creosote,"fluid",720),
                new FluidProduct(Phenol,"fluid",180),
                new FluidProduct(Benzene,"fluid",360),
                new FluidProduct(Toluene,"fluid",180)
        },1440).mats(of(Creosote,4,Phenol,1,Benzene,2,Toluene,1));
        event.setMaterial(WoodVinegar).asFluid(0,0,true,new FluidProduct[]{
                new FluidProduct(AceticAcid,"fluid",180),
                new FluidProduct(Water,"fluid",720),
                new FluidProduct(Ethanol,"fluid",90),
                new FluidProduct(Methanol,"fluid",360),
                new FluidProduct(Acetone,"fluid",45),
                new FluidProduct(MethylAcetate,"fluid",45)
        },1440).mats(of(MethylAcetate,1,Acetone,1,Ethanol,2,AceticAcid,4,Methanol,8, Water,16));
        /**
         *  Inorganic
         **/
        event.setMaterial(DilutedHydrochloricAcid).asFluid(0,0,true,new FluidProduct[]{
                new FluidProduct(HydrochloricAcid,"fluid",1000),
                new FluidProduct(Water,"fluid",1000)
        },2000).mats(of(Hydrogen, 1, Chlorine, 1));
        event.setMaterial(DilutedSulfuricAcid).asFluid().asFluid(0,0,true, new FluidProduct[]{
                new FluidProduct(SulfuricAcid,"fluid",1000),
                new FluidProduct(Water,"fluid",1000)
        },2000).mats(of(Hydrogen,2,Sulfur,1,Oxygen,4));
        /**
         **  Without Distillation Products
         **/
        /**
         *  Organic
         **/
        //Alkanoles
        event.setMaterial(Methanol).asFluid(84).mats(of(Carbon, 1, Hydrogen, 4, Oxygen, 1));
        event.setMaterial(Ethanol).asFluid(148).mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 1));
        event.setMaterial(Propanol).asFluid(196).mats(of(Carbon, 3, Hydrogen, 8, Oxygen, 1));
        event.setMaterial(Butanol).asFluid(166).mats(of(Carbon, 4, Hydrogen, 10, Oxygen, 1));
        event.setMaterial(Heptanol).asFluid(226).mats(of(Carbon, 7, Hydrogen, 16, Oxygen, 1));
        //Alkenoles
        event.setMaterial(Ethenol).asFluid(120).mats(of(Carbon, 2, Hydrogen, 4, Oxygen, 1));
        event.setMaterial(Propenol).asFluid(196).mats(of(Carbon, 3, Hydrogen, 6, Oxygen, 1));
        event.setMaterial(Butenol).asFluid(186).mats(of(Carbon, 4, Hydrogen, 8, Oxygen, 1));
        //Alkanedioles
        event.setMaterial(Ethanediol).asFluid(216).mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 2));
        event.setMaterial(Propanediol).asFluid(256).mats(of(Carbon, 3, Hydrogen, 8, Oxygen, 2));
        event.setMaterial(Butanediol).asFluid(286).mats(of(Carbon, 4, Hydrogen, 10, Oxygen, 2));
        //Plastic Related
        event.setMaterial(SiliconRubber).asFluid().mats(of(Sulfur, 1, Polydimethylsiloxane, 9));
        //Misc
        event.setMaterial(AceticAcid).asFluid().mats(of(Carbon, 2, Hydrogen, 4, Oxygen, 2));
        event.setMaterial(AllylChloride).asFluid().mats(of(Carbon, 3, Hydrogen, 5, Chlorine, 1));
        event.setMaterial(Benzaldehyde).asFluid().mats(of(Carbon,7,Hydrogen,6,Oxygen,1));
        event.setMaterial(Benzene).asFluid(288).mats(of(Carbon, 6, Hydrogen, 6));
        event.setMaterial(BenzoylChloride).asFluid().mats(of(Carbon,7,Hydrogen,5,Chlorine,1,Oxygen,1));
        event.setMaterial(Biomass).asFluid(8);
        event.setMaterial(BisphenolA).asFluid().mats(of(Carbon, 15, Hydrogen, 16, Oxygen, 2));
        event.setMaterial(Chloramine).asFluid().mats(of(Nitrogen, 1, Hydrogen, 2, Chlorine, 1));
        event.setMaterial(Chloroform).asFluid().mats(of(Carbon, 1, Hydrogen, 1, Chlorine, 3));
        event.setMaterial(Cumene).asFluid().mats(of(Carbon, 9, Hydrogen, 12));
        event.setMaterial(Dichlorobenzene).asFluid().mats(of(Carbon, 6, Hydrogen, 4, Chlorine, 2));
        event.setMaterial(Dichloroethane).asFluid().mats(of(Carbon,2,Hydrogen,4,Chlorine,2));
        event.setMaterial(Dimethyldichlorosilane).asFluid().mats(of(Carbon, 2, Hydrogen, 6, Chlorine, 2, Silicon, 1));
        event.setMaterial(Dimethylhydrazine).asFluid().mats(of(Carbon, 2, Hydrogen, 8, Nitrogen, 2));
        event.setMaterial(Epichlorohydrin).asFluid().mats(of(Carbon, 3, Hydrogen, 5, Chlorine, 1, Oxygen, 1));
        event.setMaterial(Glue).asFluid();
        event.setMaterial(Glyceryl).asFluid(164).mats(of(Carbon, 3, Hydrogen, 8, Oxygen, 3));
        event.setMaterial(GlycerylTrinitrate).asFluid().mats(of(Carbon, 3, Hydrogen, 5, Nitrogen, 3, Oxygen, 9));
        event.setMaterial(Honey).asFluid(); // TODO: Only when Forestry's present;
        event.setMaterial(Isoprene).asFluid().mats(of(Carbon, 8, Hydrogen, 8));
        event.setMaterial(MethylAcetate).asFluid().mats(of(Carbon, 3, Hydrogen, 6, Oxygen, 2));
        event.setMaterial(Naphtha).asFluid(256).mats(of(Carbon,8,Hydrogen,16));
        event.setMaterial(Phenol).asFluid(288).mats(of(Carbon, 6, Hydrogen, 6, Oxygen, 1));
        event.setMaterial(PolyvinylAcetate).asFluid().mats(of(Carbon, 4, Hydrogen, 6, Oxygen, 2));
        event.setMaterial(Styrene).asFluid().mats(of(Carbon, 8, Hydrogen, 8));
        event.setMaterial(SulfuricNaphtha).asFluid(32).mats(of(Naphtha, 1, HydrogenSulfide,1));
        event.setMaterial(Tetranitromethane).asFluid().mats(of(Carbon, 1, Nitrogen, 4, Oxygen, 8));
        event.setMaterial(Toluene).asFluid(328).mats(of(Carbon, 7, Hydrogen, 8));
        event.setMaterial(VinylAcetate).asFluid().mats(of(Carbon, 4, Hydrogen, 6, Oxygen, 2));
        /**
         *  Inorganic
         **/
        event.setMaterial(Antimatter).asFluid();
        event.setMaterial(BlueVitriol).asFluid().mats(of(Copper,1,Sulfur,1,Oxygen,4,Water,5));
        //event.setMaterial(CalciumAcetateSolution).asFluid().mats(of(Calcium, 1, Carbon, 2, Oxygen, 4, Hydrogen, 6);
        event.setMaterial(DistilledWater).asFluid().mats(of(Water,1));
        event.setMaterial(DrillingFluid).asFluid(); // TODO: Perhaps for a bedrock drill;
        event.setMaterial(HydrochloricAcid).asFluid().mats(of(Hydrogen, 1, Chlorine, 1));
        event.setMaterial(HydrofluoricAcid).asFluid().mats(of(Hydrogen, 1, Fluorine, 1));
        event.setMaterial(HydrogenPeroxide).asFluid().mats(of(Hydrogen,2,Oxygen,2));
        event.setMaterial(HypochlorousAcid).asFluid().mats(of(Hydrogen, 1, Chlorine, 1, Oxygen, 1));
        event.setMaterial(IndiumConcentrate).asFluid();

        event.setMaterial(LeadZincSolution).asFluid();
        event.setMaterial(LiquidAir).asFluid(0, 79).mats(of(Nitrogen, 40, Oxygen, 11, Argon, 1, NobleGases, 1 ));
        event.setMaterial(Lubricant).asFluid();
        event.setMaterial(NickelSulfate).asFluid().mats(of(Nickel,1,Sulfur,1,Oxygen,4));
        event.setMaterial(NitrationMixture).asFluid().mats(of(SulfuricAcid,1,NitricAcid,1));
        event.setMaterial(NitricAcid).asFluid().mats(of(Hydrogen, 1, Nitrogen, 1, Oxygen, 3));
        event.setMaterial(PeroxydisulfuricAcid).asFluid().mats(of(Sulfur,2,Hydrogen,2,Oxygen,8));
        event.setMaterial(PhosphoricAcid).asFluid().mats(of(Hydrogen, 3, Phosphor, 1, Oxygen, 4));
        event.setMaterial(SaltWater).asFluid().mats(of(Water,1,Sodium,1,Chlorine,1));
        event.setMaterial(SodiumPersulfate).asFluid().mats(of(Sodium, 2, Sulfur, 2, Oxygen, 8));
        event.setMaterial(SodiumBicarbonateSolution).asFluid().mats(of(Sodium, 1, Hydrogen,1 ,Carbon, 1, Oxygen, 3, Water, 1));
        event.setMaterial(SodiumCarbonateSolution).asFluid().mats(of(Sodium, 2, Carbon, 1, Oxygen, 3, Water, 1));
        event.setMaterial(Steam).asGas(1, 395).mats(of(Water,1));
        event.setMaterial(SulfuricAcid).asFluid().mats(of(Hydrogen, 2, Sulfur, 1, Oxygen, 4));
        event.setMaterial(SulfurTrioxide).asGas().mats(of(Sulfur, 1, Oxygen, 3));
        event.setMaterial(SulfurDioxide).asGas().mats(of(Sulfur, 1, Oxygen, 2));
        event.setMaterial(Titaniumtetrachloride).asFluid().mats(of(Titanium, 1, Chlorine, 4));
        event.setMaterial(UUAmplifier).asFluid();
        event.setMaterial(UUMatter).asFluid();
        //Nuclear
        event.setMaterial(LeachingSolution).asFluid().mats(of(SodiumBicarbonateSolution,1,SodiumCarbonateSolution,1));
        event.setMaterial(LeachedThorium).asFluid().mats(of(LeachingSolution,1,Thorium,1));
        event.setMaterial(LeachedUranium).asFluid().mats(of(LeachingSolution,1,Uranium,1));
        event.setMaterial(Thoriumdioxidedinitrate).asFluid().mats(of(ThoriumDioxide,1,Nitrogen,2,Oxygen,6));
        event.setMaterial(Uraniumdioxidedinitrate).asFluid().mats(of(UraniumDioxide,1,Nitrogen,2,Oxygen,6));
        /**
         ***  Gases/Plasmas
         **/
        /**
         **  With Distillation Products
         **/
        /**
         *  Organic
         **/
        event.setMaterial(WoodGas).asGas(24,0,true,new FluidProduct[]{
                        new FluidProduct(CarbonDioxide,"gas",720),
                        new FluidProduct(CarbonMonoxide,"gas",360),
                        new FluidProduct(Ethylene,"gas",90),
                        new FluidProduct(Methane,"gas",180),
                        new FluidProduct(Hydrogen,"gas",90)
        }, 1440).mats(of(CarbonDioxide,8,CarbonMonoxide,4,Methane,2,Ethylene,1,Hydrogen,1));
        /**
         *  Inorganic
         **/
        /**
         **  Without Distillation Products
         **/
        /**
         *  Organic
         **/
        //Alkanes
        event.setMaterial(Methane).asGas(104).mats(of(Carbon, 1, Hydrogen, 4));
        event.setMaterial(Ethane).asGas(168).mats(of(Carbon, 2, Hydrogen, 6));
        event.setMaterial(Propane).asGas(232).mats(of(Carbon, 2, Hydrogen, 6));
        event.setMaterial(Butane).asGas(296).mats(of(Carbon, 4, Hydrogen, 10));
        //Alkenes
        event.setMaterial(Ethylene).asGas(128).mats(of(Carbon, 2, Hydrogen, 4));
        event.setMaterial(Propene).asGas(192).mats(of(Carbon, 3, Hydrogen, 6));
        event.setMaterial(Butene).asGas(256).mats(of(Carbon, 4, Hydrogen, 8));
        event.setMaterial(Butadiene).asGas(206).mats(of(Carbon, 4, Hydrogen, 6));
        //Ketones
        event.setMaterial(Ethenone).asGas().mats(of(Carbon, 2, Hydrogen, 2, Oxygen, 1));
        //Misc
        event.setMaterial(CarbonDioxide).asGas().mats(of(Carbon, 1, Oxygen, 2));
        event.setMaterial(CarbonMonoxide).asGas(24).mats(of(Carbon, 1, Oxygen, 1));
        event.setMaterial(Chloromethane).asGas().mats(of(Carbon, 1, Hydrogen, 3, Chlorine, 1));
        event.setMaterial(Dimethylamine).asGas().mats(of(Carbon, 2, Hydrogen, 7, Nitrogen, 1));
        event.setMaterial(LPG).asGas(256);
        event.setMaterial(NaturalGas).asGas(15).mats(of(Methane,4,Ethane,2,Propane,2,Butane,1));
        event.setMaterial(RefineryGas).asGas(128);
        event.setMaterial(SulfuricGas).asGas(20).mats(of(NaturalGas,1,Sulfur,1));
        event.setMaterial(Tetrafluoroethylene).asGas().mats(of(Carbon, 2, Fluorine, 4));
        event.setMaterial(VinylChloride).asGas().mats(of(Carbon, 2, Hydrogen, 3, Chlorine, 1));
        /**
         *  Inorganic
         **/
        event.setMaterial(Air).asGas().mats(of(Nitrogen, 40, Oxygen, 11, Argon, 1, NobleGases, 1 ));
        event.setMaterial(Ammonia).asGas().mats(of(Nitrogen, 1, Hydrogen, 3));
        event.setMaterial(DinitrogenTetroxide).asGas().mats(of(Nitrogen, 2, Oxygen, 4));
        event.setMaterial(HydrogenSulfide).asGas().mats(of(Hydrogen, 2, Sulfur, 1));
        event.setMaterial(NitricOxide).asGas().mats(of(Nitrogen, 1, Oxygen, 1));
        event.setMaterial(NitrogenDioxide).asGas().mats(of(Nitrogen, 1, Oxygen, 2));
        event.setMaterial(NobleGases).asGas(0,790).mats(of(Helium, 10, Neon, 10, Argon, 10, Xenon, 10));
        event.setMaterial(SulfurDioxide).asGas().mats(of(Sulfur, 1, Oxygen, 2));
        event.setMaterial(SulfurTrioxide).asGas(0,344).mats(of(Sulfur, 1, Oxygen, 3));
        /**
         ** Fuels
         **/
        /**
         *  With Distillation Products
         **/
        event.setMaterial(Creosote).asFluid(8,0,true,new FluidProduct[]{new FluidProduct(Lubricant,"fluid",144)},288);
        event.setMaterial(Oil).asFluid(16,0,true,
                new FluidProduct[]{
                        new FluidProduct(SulfuricLightFuel,"fluid",50),
                        new FluidProduct(SulfuricHeavyFuel,"fluid",15),
                        new FluidProduct(Naphtha,"fluid",20),
                        new FluidProduct(SulfuricGas,"gas",60)
                },290);
        event.setMaterial(OilHeavy).asFluid(32,0,true,new FluidProduct[]{
                new FluidProduct(SulfuricLightFuel,"fluid",250),
                new FluidProduct(SulfuricHeavyFuel,"fluid",45),
                new FluidProduct(Naphtha,"fluid",15),
                new FluidProduct(SulfuricGas,"gas",600)
        },910);
        event.setMaterial(OilLight).asFluid(16,0,true,new FluidProduct[]{
                new FluidProduct(SulfuricLightFuel,"fluid",10),
                new FluidProduct(SulfuricHeavyFuel,"fluid",20),
                new FluidProduct(Naphtha,"fluid",30),
                new FluidProduct(SulfuricGas,"gas",240)
        },300);
        /**
         *  Without Distillation Products
         **/
        event.setMaterial(BioDiesel).asFluid(192);
        event.setMaterial(Diesel).asFluid(128);
        event.setMaterial(FishOil).asFluid(2);
        event.setMaterial(HeavyFuel).asFluid(48);
        event.setMaterial(LightFuel).asFluid(48);
        event.setMaterial(NitroFuel).asFluid(512);
        event.setMaterial(OilMedium).asFluid(24);
        event.setMaterial(RocketFuel).asFluid();
        event.setMaterial(SulfuricLightFuel).asFluid(32).mats(of(LightFuel,1,HydrogenSulfide,1));
        event.setMaterial(SulfuricHeavyFuel).asFluid(32).mats(of(HeavyFuel,1,HydrogenSulfide,1));
        /**
         ** Cracked Stuff
         */
        event.setMaterial(SteamCrackedEthane).asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",1000)},1000).mats(of(Ethane,1,Steam,1));
        event.setMaterial(SteamCrackedEthylene).asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",1000)},1000).mats(of(Ethylene,1,Steam,1));
        event.setMaterial(SteamCrackedPropane).asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",1000),new FluidProduct(Ethylene,"gas",1000)},2000).mats(of(Propane,1,Steam,1));
        event.setMaterial(SteamCrackedPropene).asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",1000)},1000).mats(of(Propene,1,Steam,1));
        event.setMaterial(SteamCrackedButane).asGas(0,0,true, new FluidProduct[]{
                    new FluidProduct(Methane,"gas",1000),new FluidProduct(Ethane,"gas",1000),
                    new FluidProduct(Ethylene,"gas",2000),new FluidProduct(Propane,"gas",500)
        },1000).mats(of(Butane,1,Steam,1));
        event.setMaterial(SteamCrackedButene).asGas(0,0,true, new FluidProduct[]{
                    new FluidProduct(Methane,"gas",1000),
                    new FluidProduct(Ethylene,"gas",2000),
                    new FluidProduct(Propene,"gas",1500)
        },1000).mats(of(Butene,1,Steam,1));
        event.setMaterial(SteamCrackedButadiene).asGas(0,0,true,new FluidProduct[]{
                    new FluidProduct(Methane,"gas",1000),
                    new FluidProduct(Ethylene,"gas",2000),
                    new FluidProduct(Propene,"gas",1500)
        },1000).mats(of(Butadiene,1,Steam,1));
        event.setMaterial(SteamCrackedLightFuel).asFluid(0,0,true,new FluidProduct[]{
                    new FluidProduct(Methane,"gas",100),new FluidProduct(Ethane,"gas",100),
                    new FluidProduct(Ethylene,"gas",400),new FluidProduct(Propane,"gas",100),
                    new FluidProduct(Propene,"gas",200),new FluidProduct(Butene,"gas",200),
                    new FluidProduct(Butadiene,"gas",100),new FluidProduct(Benzene,"fluid",100),
                    new FluidProduct(Toluene,"fluid",100),new FluidProduct(Naphtha,"fluid",100),
                    new FluidProduct(HeavyFuel,"fluid",100)
        },1000).mats(of(LightFuel,1,Steam,1));
        event.setMaterial(SteamCrackedHeavyFuel).asFluid(0,0,true,new FluidProduct[]{
                        new FluidProduct(Methane,"gas",100),new FluidProduct(Ethane,"gas",100),
                        new FluidProduct(Ethylene,"gas",200),new FluidProduct(Propane,"gas",100),
                        new FluidProduct(Propene,"gas",400),new FluidProduct(Butene,"gas",200),
                        new FluidProduct(Butadiene,"gas",200),new FluidProduct(Benzene,"fluid",400),
                        new FluidProduct(Toluene,"fluid",200),new FluidProduct(Naphtha,"fluid",200),
                        new FluidProduct(SulfuricHeavyFuel,"liquid",9)
        },1000).mats(of(HeavyFuel,1,Steam,1));
        event.setMaterial(SteamCrackedNaphtha).asFluid(0,0,true,new FluidProduct[]{
                        new FluidProduct(Methane,"gas",200),new FluidProduct(Ethane,"gas",200),
                        new FluidProduct(Ethylene,"gas",400),new FluidProduct(Propane,"gas",200),
                        new FluidProduct(Propene,"gas",600),new FluidProduct(Butene,"gas",100),
                        new FluidProduct(Butadiene,"gas",400),new FluidProduct(Benzene,"fluid",200),
                        new FluidProduct(Toluene,"fluid",400),new FluidProduct(LightFuel,"fluid",200),
                        new FluidProduct(HeavyFuel,"fluid",200)
        },1000).mats(of(Naphtha,1,Steam,1));
        event.setMaterial(SteamCrackedRefineryGas).asGas(0,0,true,new FluidProduct[]{
                        new FluidProduct(Methane,"gas",200),new FluidProduct(Ethane,"gas",200),
                        new FluidProduct(Ethylene,"gas",800), new FluidProduct(Propene,"gas",400)
        },1000).mats(of(RefineryGas,1,Steam,1));
        event.setMaterial(HydroCrackedEthane).asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",1000)},1000).mats(of(Ethane,1,Hydrogen,1));
        event.setMaterial(HydroCrackedEthylene).asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",1000),new FluidProduct(Ethane,"gas",1000)},2000).mats(of(Ethylene,1,Hydrogen,1));
        event.setMaterial(HydroCrackedPropane).asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",1000),new FluidProduct(Ethane,"gas",1000)},2000).mats(of(Propane,1,Hydrogen,1));
        event.setMaterial(HydroCrackedPropene).asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",1000), new FluidProduct(Ethylene,"gas",1000),new FluidProduct(Propane,"gas",1000)},3000).mats(of(Propene,1,Hydrogen,2));
        event.setMaterial(HydroCrackedButane).asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",1000),new FluidProduct(Ethane,"gas",1000), new FluidProduct(Propane,"gas",1000)},3000).mats(of(Butane,1,Hydrogen,2));
        event.setMaterial(HydroCrackedButene).asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",1000),new FluidProduct(Ethane,"gas",1000), new FluidProduct(Propene,"gas",1000),new FluidProduct(Propene,"gas",1000), new FluidProduct(Butane,"gas",1000)},6000).mats(of(Butene,1,Hydrogen,2));
        event.setMaterial(HydroCrackedButadiene).asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",1000),new FluidProduct(Ethane,"gas",1000),new FluidProduct(Ethylene,"gas",1000),new FluidProduct(Butane,"gas",1000),new FluidProduct(Butene,"gas",1000)},7000).mats(of(Butadiene,1,Hydrogen,2));
        event.setMaterial(HydroCrackedLightFuel).asFluid(0,0,true,new FluidProduct[]{new FluidProduct(Naphtha,"fluid",1000), new FluidProduct(Butane,"gas",1000),new FluidProduct(Propane,"gas",1000), new FluidProduct(Ethane,"gas",2000),new FluidProduct(Methane,"gas",2000)},6000).mats(of(LightFuel,1,Hydrogen,2));
        event.setMaterial(HydroCrackedHeavyFuel).asFluid(0,0,true,new FluidProduct[]{new FluidProduct(LightFuel,"fluid",144),new FluidProduct(Naphtha,"fluid",2000), new FluidProduct(Butane,"gas",2000),new FluidProduct(Propane,"gas",1000), new FluidProduct(Ethane,"gas",2000),new FluidProduct(Methane,"gas",1000)},8000).mats(of(HeavyFuel,1,Hydrogen,2));
        event.setMaterial(HydroCrackedNaphtha).asFluid(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",1000),new FluidProduct(Ethane,"gas",2000), new FluidProduct(Propane,"gas",1000),new FluidProduct(Butane,"gas",1000)},5000).mats(of(Naphtha,1,Hydrogen,2));
        event.setMaterial(HydroCrackedRefineryGas).asGas(0,0,true,new FluidProduct[]{new FluidProduct(Methane,"gas",2000),new FluidProduct(Hydrogen,"gas",1000)},3000).mats(of(RefineryGas,1,Hydrogen,2));
        /**
         ** Nuclear Processing
         */
        event.setMaterial(Ammoniumdithoranate).asDust().mats(of(Nitrogen,2,Hydrogen,8,Thorium,2,Oxygen,7));
        event.setMaterial(Ammoniumdiuranate).asDust().mats(of(Nitrogen,2,Hydrogen,8,Uranium,2,Oxygen,7));
        /**
         * Tetrafluorides
         */
        event.setMaterial(ThoriumTetrafluoride).asGas().mats(of(Thorium,1,Fluorine,4));
        event.setMaterial(UraniumTetrafluoride).asGas().mats(of(Uranium,1,Fluorine,4));
        /**
         * Hexafluorides
         */
        event.setMaterial(ThoriumHexafluoride).asGas().mats(of(Thorium,1,Fluorine,6));
        event.setMaterial(Thorium227Hexafluoride).asGas().mats(of(Thorium227,1,Fluorine,6));
        event.setMaterial(Thorium228Hexafluoride).asGas().mats(of(Thorium228,1,Fluorine,6));
        event.setMaterial(Thorium229Hexafluoride).asGas().mats(of(Thorium229,1,Fluorine,6));
        event.setMaterial(Thorium230Hexafluoride).asGas().mats(of(Thorium230,1,Fluorine,6));
        event.setMaterial(Thorium231Hexafluoride).asGas().mats(of(Thorium231,1,Fluorine,6));
        event.setMaterial(Thorium233Hexafluoride).asGas().mats(of(Thorium233,1,Fluorine,6));
        event.setMaterial(Thorium234Hexafluoride).asGas().mats(of(Thorium234,1,Fluorine,6));
        event.setMaterial(UraniumHexafluoride).asGas().mats(of(Uranium,1,Fluorine,6));
        event.setMaterial(Uranium232Hexafluoride).asGas().mats(of(Uranium232,1,Fluorine,6));
        event.setMaterial(Uranium233Hexafluoride).asGas().mats(of(Uranium233,1,Fluorine,6));
        event.setMaterial(Uranium234Hexafluoride).asGas().mats(of(Uranium234,1,Fluorine,6));
        event.setMaterial(Uranium235Hexafluoride).asGas().mats(of(Uranium235,1,Fluorine,6));
        event.setMaterial(Uranium236Hexafluoride).asGas().mats(of(Uranium236,1,Fluorine,6));
        event.setMaterial(Uranium237Hexafluoride).asGas().mats(of(Uranium237,1,Fluorine,6));
        event.setMaterial(Uranium238Hexafluoride).asGas().mats(of(Uranium238,1,Fluorine,6));
        event.setMaterial(Uranium239Hexafluoride).asGas().mats(of(Uranium239,1,Fluorine,6));
        event.setMaterial(Uranium240Hexafluoride).asGas().mats(of(Uranium240,1,Fluorine,6));
        /**
         * Fissile Fuels
         */
        event.setMaterial(Thorium227).flags(INGOT_HOT,FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Thorium228).flags(INGOT_HOT,FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Thorium229).flags(INGOT_HOT,FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Thorium230).flags(INGOT_HOT,FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Thorium231).flags(INGOT_HOT,FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Thorium232).flags(INGOT_HOT,FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Thorium233).flags(INGOT_HOT,FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Thorium234).flags(INGOT_HOT,FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Uranium232).flags(INGOT_HOT,FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Uranium233).flags(INGOT_HOT,FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Uranium234).flags(INGOT_HOT,FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Uranium235).flags(INGOT_HOT,FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Uranium236).flags(INGOT_HOT,FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Uranium237).flags(INGOT_HOT,FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Uranium238).flags(INGOT_HOT,FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Uranium239).flags(INGOT_HOT,FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Uranium240).flags(INGOT_HOT,FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Neptunium236).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Neptunium237).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Neptunium238).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Neptunium239).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Neptunium240).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Plutonium236).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Plutonium238).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Plutonium239).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Plutonium240).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Plutonium241).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Plutonium242).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Plutonium243).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Plutonium244).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Plutonium246).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Americium241).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Americium242).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Americium244).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Americium245).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Americium246).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Curium242).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Curium244).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Curium245).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Curium246).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Curium247).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Curium248).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Curium249).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Berkelium248).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Berkelium249).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Berkelium250).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Berkelium251).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Californium248).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Californium249).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Californium250).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Californium251).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Californium252).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Californium253).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Californium254).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Californium255).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Einsteinium253).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Einsteinium254).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Einsteinium255).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Einsteinium256).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Fermium255).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Fermium256).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Fermium257).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Fermium258).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Fermium259).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Fermium260).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Mendelevium259).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        event.setMaterial(Mendelevium260).flags(FISSILE_FUEL,DEPLETED_FISSILE_FUEL);
        /**
         * Breeder Fuels
         */
    }

    public static void byproducts(MaterialEvent event){
        event.setMaterial(Almandine).addByProduct(RedGarnet, Aluminium);
        event.setMaterial(Aluminium).addByProduct(Bauxite);
        //event.setMaterial(Amber).addByProduct(Amber); TODO: Add Amber
        event.setMaterial(Amethyst).addByProduct(Amethyst);
        event.setMaterial(Andradite).addByProduct(YellowGarnet, Iron, Boron);
        event.setMaterial(Antimony).addByProduct(Zinc, Iron);
        event.setMaterial(Ash).addByProduct(Carbon);
        event.setMaterial(Basalt).addByProduct(Olivine, DarkAsh);
        event.setMaterial(Bastnasite).addByProduct(Neodymium, RareEarth);
        event.setMaterial(Bauxite).addByProduct(Grossular, Rutile, Gallium);
        event.setMaterial(Bentonite).addByProduct(Aluminium, Calcium, Magnesium);
        event.setMaterial(Beryllium).addByProduct(Emerald);
        event.setMaterial(BlackGranite).addByProduct(Biotite);
        event.setMaterial(BlueSapphire).addByProduct(Aluminium);
        event.setMaterial(Brass).addByProduct(Copper, Tin);
        event.setMaterial(Bronze).addByProduct(Copper, Zinc);
        event.setMaterial(BrownLimonite).addByProduct(Malachite, YellowLimonite);
        event.setMaterial(Calcite).addByProduct(Andradite, Malachite);
        event.setMaterial(Cassiterite).addByProduct(Tin);
        event.setMaterial(Chalcopyrite).addByProduct(Pyrite, Cobalt, Cadmium, Gold);
        event.setMaterial(Chrome).addByProduct(Iron, Magnesium);
        event.setMaterial(Cinnabar).addByProduct(Redstone, Sulfur, Glowstone);
        event.setMaterial(Clay).addByProduct(Clay);
        event.setMaterial(Coal).addByProduct(Lignite, Thorium);
        event.setMaterial(Cobalt).addByProduct(Cobaltite);
        event.setMaterial(Cobaltite).addByProduct(Cobalt);
        event.setMaterial(Cooperite).addByProduct(Palladium, Nickel, Iridium);
        event.setMaterial(Copper).addByProduct(Cobalt, Gold, Nickel);
        event.setMaterial(DarkAsh).addByProduct(Carbon);
        event.setMaterial(Diamond).addByProduct(Graphite);
        event.setMaterial(Electrum).addByProduct(Gold, Silver);
        event.setMaterial(Emerald).addByProduct(Beryllium, Aluminium);
        event.setMaterial(Endstone).addByProduct(Helium3);
        event.setMaterial(EnrichedNaquadah).addByProduct(Naquadah, Naquadria);
        event.setMaterial(Flint).addByProduct(Obsidian);
        event.setMaterial(Galena).addByProduct(Sulfur, Silver, Lead);
        event.setMaterial(Garnierite).addByProduct(Nickel);
        event.setMaterial(Glauconite).addByProduct(Sodium, Aluminium, Iron);
        event.setMaterial(Glowstone).addByProduct(Redstone, Gold);
        event.setMaterial(Gold).addByProduct(Copper, Nickel);
        event.setMaterial(Graphite).addByProduct(Carbon);
        event.setMaterial(Grossular).addByProduct(YellowGarnet, Calcium);
        event.setMaterial(Ilmenite).addByProduct(Iron, Rutile);
        event.setMaterial(Iridium).addByProduct(Platinum, Osmium);
        event.setMaterial(Iron).addByProduct(Nickel, Tin);
        event.setMaterial(Lapis).addByProduct(Calcite, Pyrite);
        event.setMaterial(Lead).addByProduct(Silver, Sulfur);
        event.setMaterial(Lignite).addByProduct(Coal);
        event.setMaterial(Lithium).addByProduct(Dilithium);
        event.setMaterial(Magnesite).addByProduct(Magnesium);
        event.setMaterial(Magnesium).addByProduct(Olivine);
        event.setMaterial(Magnetite).addByProduct(Iron, Gold);
        event.setMaterial(Malachite).addByProduct(Copper, BrownLimonite, Calcite);
        event.setMaterial(Manganese).addByProduct(Chrome, Iron);
        event.setMaterial(Marble).addByProduct(Calcite);
        event.setMaterial(Molybdenite).addByProduct(Molybdenum);
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
        event.setMaterial(Phosphorus).addByProduct(Phosphate);
        event.setMaterial(Pitchblende).addByProduct(Thorium, Uranium, Lead);
        event.setMaterial(Platinum).addByProduct(Nickel, Iridium);
        event.setMaterial(Plutonium).addByProduct(Uranium, Lead);
        event.setMaterial(Pyrite).addByProduct(Sulfur, Phosphorus, Iron);
        event.setMaterial(Pyrolusite).addByProduct(Manganese);
        event.setMaterial(Pyrope).addByProduct(RedGarnet, Magnesium);
        event.setMaterial(Quartz).addByProduct(Netherrack);
        event.setMaterial(Quartzite).addByProduct(Barite);
        event.setMaterial(RedGarnet).addByProduct(Spessartine, Pyrope, Almandine);
        event.setMaterial(Redstone).addByProduct(Cinnabar, RareEarth, Glowstone);
        event.setMaterial(RedGranite).addByProduct(PotassiumFeldspar);
        event.setMaterial(RockSalt).addByProduct(Salt);
        event.setMaterial(Ruby).addByProduct(Chrome, RedGarnet);
        event.setMaterial(Salt).addByProduct(RockSalt);
        event.setMaterial(Saltpeter).addByProduct(Saltpeter);
        event.setMaterial(Scheelite).addByProduct(Manganese, Molybdenum, Calcium);
        event.setMaterial(Silicon).addByProduct(SiliconDioxide);
        event.setMaterial(Silver).addByProduct(Lead, Sulfur);
        event.setMaterial(Spessartine).addByProduct(RedGarnet, Manganese);
        event.setMaterial(Sphalerite).addByProduct(YellowGarnet, Cadmium, Gallium, Zinc);
        event.setMaterial(Spodumene).addByProduct(Aluminium, Lithium);
        event.setMaterial(Steel).addByProduct(Iron);
        event.setMaterial(Stibnite).addByProduct(Antimony);
        event.setMaterial(Sulfur).addByProduct(Sulfur);
        event.setMaterial(Tantalite).addByProduct(Manganese, Niobium, Tantalum);
        event.setMaterial(Tanzanite).addByProduct(Opal);
        event.setMaterial(Tetrahedrite).addByProduct(Antimony, Zinc);
        event.setMaterial(Thorium).addByProduct(Uranium, Lead);
        event.setMaterial(Tin).addByProduct(Iron, Zinc);
        event.setMaterial(Titanium).addByProduct(Almandine);
        event.setMaterial(Tungstate).addByProduct(Manganese, Silver, Lithium);
        event.setMaterial(Tungsten).addByProduct(Manganese, Molybdenum);
        event.setMaterial(Uraninite).addByProduct(Uranium, Thorium, Plutonium);
        event.setMaterial(Uranium).addByProduct(Lead, Thorium, Plutonium);
        event.setMaterial(Uvarovite).addByProduct(YellowGarnet, Chrome);
        event.setMaterial(VanadiumMagnetite).addByProduct(Magnetite, Vanadium);
        event.setMaterial(YellowGarnet).addByProduct(Andradite, Grossular, Uvarovite);
        event.setMaterial(YellowLimonite).addByProduct(Nickel, BrownLimonite, Cobalt);
        event.setMaterial(Zinc).addByProduct(Tin, Gallium);
    }

    private static void flags(MaterialEvent event){
        BRITTLEG.add(Coal, Charcoal, Lignite);
        CALCITE2X.add(Pyrite, BrownLimonite, YellowLimonite, Magnetite);
        CALCITE3X.add(Iron, WroughtIron);
        CENT.add(NobleGases, Air, BrownLimonite, Cinnabar, Clay, Cooperite, Stibnite,
                Tetrahedrite, Uraninite, Wulfenite, YellowLimonite, Blaze, Flint, Marble, BlackGranite,
                VanadiumMagnetite, Pitchblende, Glass, Lapis, EnderEye, Phosphorus, Redstone, Basalt, AnnealedCopper,
                BatteryAlloy, Brass, Bronze, Cupronickel, Electrum, Invar, Kanthal, Magnalium, Nichrome,
                NiobiumTitanium, SolderingAlloy, VanadiumGallium, WroughtIron, SterlingSilver, RoseGold, BismuthBronze,
                TungstenSteel, RedAlloy, CobaltBrass, TungstenCarbide, VanadiumSteel, HSSG, HSSE, HSSS,
                GalliumArsenide, IndiumGalliumPhosphide, BorosilicateGlass);
        CHEMBATH_MERCURY.add(Chalcopyrite, Gold);
        CHEMBATH_MERCURY.add(Gold,Nickel);
        CHEMBATH_MERCURY.add(Osmium,Iridium);
        CHEMBATH_MERCURY.add(Platinum,Iridium);
        CHEMBATH_MERCURY.add(Silver,Sulfur);
        CHEMBATH_PERSULFATE.add(Cobalt,Cobaltite);
        CHEMBATH_PERSULFATE.add(Cobaltite,Cobalt);
        CHEMBATH_PERSULFATE.add(Copper,Nickel);
        CHEMBATH_PERSULFATE.add(Nickel, Iron);
        CHEMBATH_PERSULFATE.add(Sphalerite,Zinc);
        CHEMBATH_PERSULFATE.add(Tetrahedrite,Zinc);
        CHEMBATH_PERSULFATE.add(Zinc,Gallium);
        CRACK.add(RefineryGas, Naphtha, Ethane, Ethylene, Propane, Propene, Butane, Butene, Butadiene, LightFuel, HeavyFuel);
        CRYSTALLIZE.add(Lapis, Quartzite, Quartz);
        DECAYABLE.add(Thallium209,Thallium210,Bismuth213,Bismuth214,Polonium213,Polonium214,Polonium215,Polonium216,Polonium218,Astatine217,Astatine218,
                Radon219,Radon220,Radon222,Francium221,Francium223,Radium223,Radium224,Radium225,Radium226,Radium228,Actinium225,Actinium227,Actinium228,
                Thorium227,Thorium228,Thorium229,Thorium230,Thorium231,Thorium233,Thorium234,Protactinium231,Protactinium232,Protactinium233,Protactinium234,
                Uranium232,Uranium233,Uranium234,Uranium235,Uranium237,Uranium238,Uranium239,Uranium240,Neptunium236,Neptunium237,Neptunium238,Neptunium239,
                Neptunium240,Plutonium236,Plutonium238,Plutonium239,Plutonium240,Plutonium241,Plutonium242,Plutonium243,Plutonium244,Plutonium246,
                Americium241,Americium242,Americium244,Americium246,Curium242,Curium244,Curium245,Curium246,Curium247,Curium248,Curium249,Curium250,
                Berkelium248,Berkelium249,Berkelium250,Berkelium251,Californium248,Californium249,Californium250,Californium251,Californium252,Californium253,
                Californium254,Californium255,Einsteinium253,Einsteinium254,Einsteinium255,Einsteinium256,Fermium255,Fermium256,Fermium257,Fermium258,Fermium259,
                Fermium260,Mendelevium259,Mendelevium260
        );
        ELEC.add(Methane, CarbonDioxide, NitrogenDioxide, Toluene, VinylChloride, SulfurDioxide, SulfurTrioxide,
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
                AntimonyTrioxide, CupricOxide, Ferrosilite, Quartzite, Charcoal, Coal, Lignite, Diamond, Emerald, Ruby,
                BlueSapphire, Tanzanite, Topaz, Olivine, Opal, Amethyst, EnderPearl, StainlessSteel, Steel, Ultimet,
                IronMagnetic, SteelMagnetic, NeodymiumMagnetic, Osmiridium, Sodalite);
        ELECTROMAGNETIC_SEPARATOR_GOLD.add(VanadiumMagnetite, Magnetite);
        ELECTROMAGNETIC_SEPARATOR_IRON.add(YellowLimonite, BrownLimonite, Tin, Chrome, Ilmenite, BandedIron, Pyrite, Glauconite, Nickel, /*Chromite, */Pentlandite, Manganese);
        ELECTROMAGNETIC_SEPARATOR_NEODYMIUM.add(/*Monazite, */Bastnasite);
        ELECSEPN.add(YellowLimonite, BrownLimonite, Pyrite, BandedIron, Nickel, Glauconite, Pentlandite, Tin, Antimony, Ilmenite, Manganese, Chrome, Andradite);
        GRINDABLE.add(/* Paper, */Coal, Charcoal, Lignite, Lead, Tin, SolderingAlloy, Flint, Gold, Silver, Iron,
                IronMagnetic, Steel, SteelMagnetic, Zinc, Antimony, Copper, AnnealedCopper, Bronze, Nickel, Invar,
                Brass, WroughtIron, Electrum, Clay, Blaze);
        NOBBF.add(Tetrahedrite, Chalcopyrite, Cooperite, Pyrolusite, Magnesite, Molybdenite, Galena);
        NOSMASH.add(Wood/* WoodSealed */, Sulfur, Saltpeter, Graphite, /* Paper, */Coal, Charcoal, Lignite, Rubber,
                StyreneButadieneRubber, Polyethylene, PolyvinylChloride, Polystyrene, Silicone, NitroFuel,
                Concrete, Redstone, Glowstone, Netherrack, Stone, Brick, Endstone, Marble, Basalt, Obsidian, Flint,
                RedGranite, BlackGranite, Salt, RockSalt, Glass, Diamond, Emerald, Amethyst, Tanzanite, Topaz,
                /* Amber, */ BlueSapphire, Ruby, Opal, Olivine, Lapis, Quartzite, Quartz, Phosphorus, Phosphate,
                NetherStar, EnderPearl, EnderEye);
        NOSMELT.add(Wood/* , WoodSealed */, Sulfur, Saltpeter, Graphite, /* Paper, */Coal, Charcoal, Lignite,
                NitroFuel, Emerald, Amethyst, Tanzanite, Topaz, /* Amber, */ BlueSapphire, Ruby, Opal, Olivine,
                Lapis, Sodalite, Lazurite, /*Monazite ,*/ Quartzite, Quartz, Phosphorus, Phosphate, NetherStar,
                EnderPearl, EnderEye, Blaze);
        RUBBERTOOLS.add(Rubber, StyreneButadieneRubber, Polyethylene, PolyvinylChloride, Polystyrene, Silicone);
        SMELTF.add(Concrete, Redstone, Glowstone, Glass, Blaze);
        SOLDER.add(Lead, Tin, SolderingAlloy);
        SOLDER.subTag(SubTag.BAD_SOLDER, Lead, Tin);
        SOLDER.subTag(SubTag.GOOD_SOLDER, SolderingAlloy, Tin);
        WIRE.subTag(SubTag.COPPER_WIRE, AnnealedCopper);
        WIRE.subTag(SubTag.COPPER_WIRE, Copper);

        event.setMaterial(AnnealedCopper).setSmeltInto(Copper).setMacerateInto(Copper).setArcSmeltInto(AnnealedCopper);
        event.setMaterial(Copper).setSmeltInto(Copper).setMacerateInto(Copper).setArcSmeltInto(AnnealedCopper);
        event.setMaterial(Iron).setSmeltInto(Iron).setMacerateInto(Iron).setArcSmeltInto(WroughtIron);
        event.setMaterial(IronMagnetic).setSmeltInto(Iron).setMacerateInto(Iron).setArcSmeltInto(WroughtIron);
        event.setMaterial(NeodymiumMagnetic).setSmeltInto(Neodymium).setMacerateInto(Neodymium).setArcSmeltInto(Neodymium);
        event.setMaterial(SteelMagnetic).setSmeltInto(Steel).setMacerateInto(Steel).setArcSmeltInto(Steel);
        event.setMaterial(WroughtIron).setSmeltInto(Iron).setMacerateInto(Iron).setArcSmeltInto(WroughtIron);

        event.setMaterial(BandedIron).setDirectSmeltInto(Iron);
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
        event.setMaterial(YellowLimonite).setDirectSmeltInto(Iron);

        event.setMaterial(Cassiterite).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Glowstone).setOreMulti(5).setSmeltingMulti(5);
        event.setMaterial(Lapis).setOreMulti(6).setSmeltingMulti(6).setByProductMulti(4);
        event.setMaterial(Phosphorus).setOreMulti(3).setSmeltingMulti(3);
        event.setMaterial(Quartz).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Redstone).setOreMulti(5).setSmeltingMulti(5);
        event.setMaterial(RockSalt).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Salt).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Saltpeter).setOreMulti(4).setSmeltingMulti(4);
        event.setMaterial(Scheelite).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Tungstate).setOreMulti(2).setSmeltingMulti(2);

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
        event.setMaterial(Basalt).mats(of(Olivine, 1, Calcite, 3, Flint, 8, DarkAsh, 4));
        event.setMaterial(Blaze).mats(of(Sulfur, 1, DarkAsh, 1));
        event.setMaterial(Coal).flags(ORE_STONE).mats(of(Carbon, 2));
        event.setMaterial(Copper).flags(PLATE, ROD, FOIL, WIRE_FINE, GEAR, BOLT);
        event.setMaterial(Diamond).asGem(true).mats(of(Carbon, 64));
        event.setMaterial(Emerald).asGem(true).mats(of(Beryllium, 3, Aluminium, 2, Silicon, 3, Oxygen, 18)).addTools(3.0F, 9.0F, 590, 3);
        event.setMaterial(EnderEye).asGemBasic(true, ROD, PLATE).mats(of(EnderPearl, 1, Blaze, 1));
        event.setMaterial(EnderPearl).mats(of(Beryllium, 1, Potassium, 4, Nitrogen, 5, Chlorine, 6));
        event.setMaterial(Flint).flags(ROCK);
        event.setMaterial(Gold).flags(FOIL, ROD, WIRE_FINE, GEAR);
        event.setMaterial(Iron).flags(RING, GEAR, FRAME);
        event.setMaterial(Lapis).mats(of(Lazurite, 12, Sodalite, 2, Pyrite, 1, Calcite, 1));
        event.setMaterial(Prismarine).mats(of(Potassium, 2, Oxygen, 8, Manganese, 1, Silicon, 5));
        event.setMaterial(Quartz).asOre();
        event.setMaterial(Redstone).mats(of(Silicon, 1, Pyrite, 5, Ruby, 1, Mercury, 3)).asFluid(0, MaterialTags.MELTING_POINT.getInt(Redstone));//.setOreMulti(4);
        event.setMaterial(Water).mats(of(Hydrogen, 2, Oxygen, 1));
    }
}