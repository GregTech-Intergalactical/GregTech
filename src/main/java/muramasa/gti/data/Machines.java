package muramasa.gti.data;

import muramasa.antimatter.machine.types.*;
import muramasa.gti.tile.TileEntitySteamMachine;
import muramasa.gti.Ref;
import muramasa.gti.block.BlockBatteryBufferCreative;
import muramasa.gti.tile.multi.*;

import static muramasa.antimatter.machine.MachineFlag.*;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.gti.data.RecipeMaps.*;

public class Machines {

    public static BasicMachine ALLOY_SMELTER = new BasicMachine(Ref.ID, "alloy_smelter", ALLOY_SMELTING, ITEM);
    public static BasicMachine ASSEMBLER = new BasicMachine(Ref.ID, "assembler", ASSEMBLING, ITEM);
    public static BasicMachine BENDER = new BasicMachine(Ref.ID, "bender", BENDING, ITEM);
    public static BasicMachine CANNER = new BasicMachine(Ref.ID, "canner", CANNING, ITEM);
    public static BasicMachine COMPRESSOR = new BasicMachine(Ref.ID, "compressor", COMPRESSING, ITEM);
    public static BasicMachine CUTTER = new BasicMachine(Ref.ID, "cutter", CUTTING, ITEM);
    public static BasicMachine FURNACE = new BasicMachine(Ref.ID, "furnace", SMELTING, ITEM);
    public static BasicMachine EXTRACTOR = new BasicMachine(Ref.ID, "extractor", EXTRACTING, ITEM);
    public static BasicMachine EXTRUDER = new BasicMachine(Ref.ID, "extruder", EXTRUDING, ITEM);
    public static BasicMachine LATHE = new BasicMachine(Ref.ID, "lathe", LATHING, ITEM);
    public static BasicMachine PULVERIZER = new BasicMachine(Ref.ID, "pulverizer", PULVERIZING, ITEM);
    public static BasicMachine RECYCLER = new BasicMachine(Ref.ID, "recycler", RECYCLING, ITEM);
    public static BasicMachine SCANNER = new BasicMachine(Ref.ID, "scanner", SCANNING, ITEM, FLUID);
    public static BasicMachine WIRE_MILL = new BasicMachine(Ref.ID, "wire_mill", WIRE_MILLING, ITEM);
    public static BasicMachine CENTRIFUGE = new BasicMachine(Ref.ID, "centrifuge", CENTRIFUGING, ITEM, FLUID);
    public static BasicMachine ELECTROLYZER = new BasicMachine(Ref.ID, "electrolyzer", ELECTROLYZING, ITEM, FLUID);
    public static BasicMachine THERMAL_CENTRIFUGE = new BasicMachine(Ref.ID, "thermal_centrifuge", THERMAL_CENTRIFUGING, ITEM);
    public static BasicMachine ORE_WASHER = new BasicMachine(Ref.ID, "ore_washer", ORE_WASHING, ITEM, FLUID);
    public static BasicMachine CHEMICAL_REACTOR = new BasicMachine(Ref.ID, "chemical_reactor", CHEMICAL_REACTING, ITEM, FLUID);
    public static BasicMachine FLUID_CANNER = new BasicMachine(Ref.ID, "fluid_canner", FLUID_CANNING, ITEM, FLUID);
    public static BasicMachine DISASSEMBLER = new BasicMachine(Ref.ID, "disassembler", DISASSEMBLING, ITEM);
    public static BasicMachine MASS_FABRICATOR = new BasicMachine(Ref.ID, "mass_fabricator", MASS_FABRICATING, ITEM, FLUID);
    public static BasicMachine AMP_FABRICATOR = new BasicMachine(Ref.ID, "amp_fabricator", AMP_FABRICATING, ITEM);
    public static BasicMachine REPLICATOR = new BasicMachine(Ref.ID, "replicator", REPLICATING, ITEM, FLUID);
    public static BasicMachine FERMENTER = new BasicMachine(Ref.ID, "fermenter", FERMENTING, ITEM, FLUID);
    public static BasicMachine FLUID_EXTRACTOR = new BasicMachine(Ref.ID, "fluid_extractor", FLUID_EXTRACTING, ITEM, FLUID);
    public static BasicMachine FLUID_SOLIDIFIER = new BasicMachine(Ref.ID, "fluid_solidifier", FLUID_SOLIDIFYING, ITEM, FLUID);
    public static BasicMachine DISTILLERY = new BasicMachine(Ref.ID, "distillery", DISTILLING, ITEM, FLUID);
    public static BasicMachine CHEMICAL_BATH = new BasicMachine(Ref.ID, "chemical_bath", CHEMICAL_BATHING, ITEM, FLUID);
    public static BasicMachine AUTOCLAVE = new BasicMachine(Ref.ID, "autoclave", AUTOCLAVING, ITEM, FLUID);
    public static BasicMachine MIXER = new BasicMachine(Ref.ID, "mixer", MIXING, ITEM, FLUID);
    public static BasicMachine LASER_ENGRAVER = new BasicMachine(Ref.ID, "laser_engraver", LASER_ENGRAVING, ITEM);
    public static BasicMachine FORMING_PRESS = new BasicMachine(Ref.ID, "forming_press", PRESSING, ITEM);
    public static BasicMachine FORGE_HAMMER = new BasicMachine(Ref.ID, "forge_hammer", HAMMERING, ITEM);
    public static BasicMachine SIFTER = new BasicMachine(Ref.ID, "sifter", SIFTING, ITEM);
    public static BasicMachine ARC_FURNACE = new BasicMachine(Ref.ID, "arc_furnace", ARC_SMELTING, ITEM, FLUID);
    public static BasicMachine PLASMA_ARC_FURNACE = new BasicMachine(Ref.ID, "plasma_arc_furnace", PLASMA_ARC_SMELTING, ITEM, FLUID);

    public static BasicMachine COAL_BOILER = new BasicMachine(Ref.ID, "coal_boiler", SMALL_BOILERS, BRONZE, STEEL, STEAM, ITEM, FLUID, Textures.BOILER_HANDLER);
    public static BasicMachine LAVA_BOILER = new BasicMachine(Ref.ID, "lava_boiler", SMALL_BOILERS, STEEL, STEAM, ITEM, FLUID); //TODO
    public static BasicMachine SOLAR_BOILER = new BasicMachine(Ref.ID, "solar_boiler", SMALL_BOILERS, BRONZE, STEAM, ITEM, FLUID); //TODO
    public static BasicMachine STEAM_FURNACE = new BasicMachine(Ref.ID, "steam_furnace", TileEntitySteamMachine.class, BRONZE, STEEL, STEAM, ITEM, FLUID, STEAM_SMELTING);
    public static BasicMachine STEAM_PULVERIZER = new BasicMachine(Ref.ID, "steam_pulverizer", TileEntitySteamMachine.class, BRONZE, STEEL, STEAM, ITEM, FLUID, STEAM_PULVERIZING);
    public static BasicMachine STEAM_EXTRACTOR = new BasicMachine(Ref.ID, "steam_extractor", TileEntitySteamMachine.class, BRONZE, STEEL, STEAM, ITEM, FLUID, STEAM_EXTRACTING);
    public static BasicMachine STEAM_FORGE_HAMMER = new BasicMachine(Ref.ID, "steam_forge_hammer", TileEntitySteamMachine.class, BRONZE, STEEL, STEAM, ITEM, FLUID, STEAM_HAMMERING);
    public static BasicMachine STEAM_COMPRESSOR = new BasicMachine(Ref.ID, "steam_compressor", TileEntitySteamMachine.class, BRONZE, STEEL, STEAM, ITEM, FLUID, STEAM_COMPRESSING);
    public static BasicMachine STEAM_ALLOY_SMELTER = new BasicMachine(Ref.ID, "steam_alloy_smelter", TileEntitySteamMachine.class, BRONZE, STEEL, STEAM, ITEM, FLUID, STEAM_ALLOY_SMELTING);

    public static MultiMachine COKE_OVEN = new MultiMachine(Ref.ID, "coke_oven", COKING, LV, ITEM).setTile(m -> () -> new TileEntityCokeOven(m));
    public static MultiMachine PRIMITIVE_BLAST_FURNACE = new MultiMachine(Ref.ID, "primitive_blast_furnace", BASIC_BLASTING, LV, ITEM).setTile(m -> () -> new TileEntityPrimitiveBlastFurnace(m));
    public static MultiMachine BRONZE_BLAST_FURNACE = new MultiMachine(Ref.ID, "bronze_blast_furnace", BASIC_BLASTING, LV, ITEM).setTile(m -> () -> new TileEntityBronzeBlastFurnace(m));
    public static MultiMachine CHARCOAL_PIT = new MultiMachine(Ref.ID, "charcoal_pit", LV).setTile(m -> () -> new TileEntityCharcoalPit(m));
    public static MultiMachine BLAST_FURNACE = new MultiMachine(Ref.ID, "electric_blast_furnace", BLASTING, LV, ITEM, FLUID).setTile(m -> () -> new TileEntityElectricBlastFurnace(m));
    public static MultiMachine IMPLOSION_COMPRESSOR = new MultiMachine(Ref.ID, "implosion_compressor", IMPLOSION_COMPRESSING, HV, ITEM).setTile(m -> () -> new TileEntityImplosionCompressor(m));
    public static MultiMachine VACUUM_FREEZER = new MultiMachine(Ref.ID, "vacuum_freezer", VACUUM_FREEZING, HV, ITEM, FLUID).setTile(m -> () -> new TileEntityVacuumFreezer(m));
    public static MultiMachine MULTI_SMELTER = new MultiMachine(Ref.ID, "multi_smelter", HV, ITEM).setTile(m -> () -> new TileEntityMultiSmelter(m));
    public static MultiMachine LARGE_BOILER = new MultiMachine(Ref.ID, "large_boiler", LV, MV, HV, EV, ITEM, FLUID).setTile(m -> () -> new TileEntityLargeBoiler(m));
    public static MultiMachine LARGE_TURBINE = new MultiMachine(Ref.ID, "large_turbine", HV, EV, IV, UV, FLUID).setTile(m -> () -> new TileEntityLargeTurbine(m));
    public static MultiMachine HEAT_EXCHANGER = new MultiMachine(Ref.ID, "heat_exchanger", EV, FLUID).setTile(m -> () -> new TileEntityHeatExchanger(m));
    public static MultiMachine OIL_DRILLING_RIG = new MultiMachine(Ref.ID, "oil_drilling_rig", EV, IV, LUV, ZPM, FLUID).setTile(m -> () -> new TileEntityOilDrillingRig(m));
    public static MultiMachine OIL_CRACKING_UNIT = new MultiMachine(Ref.ID, "oil_cracking_unit", HV, FLUID).setTile(m -> () -> new TileEntityOilCrackingUnit(m));
    public static MultiMachine ADVANCED_MINER = new MultiMachine(Ref.ID, "advanced_miner", LV, ITEM).setTile(m -> () -> new TileEntityAdvancedMiner(m));
    public static MultiMachine PYROLYSIS_OVEN = new MultiMachine(Ref.ID, "pyrolysis_oven", MV, ITEM, FLUID).setTile(m -> () -> new TileEntityPyrolysisOven(m));
    public static MultiMachine COMBUSTION_ENGINE = new MultiMachine(Ref.ID, "combustion_engine", COMBUSTION_FUELS, EV, FLUID).setTile(m -> () -> new TileEntityCombustionEngine(m));
    public static MultiMachine FUSION_REACTOR = new MultiMachine(Ref.ID, "fusion_reactor", FUSION, LUV, ZPM, UV, FLUID).setTile(m -> () -> new TileEntityFusionReactor(m));

    public static HatchMachine HATCH_ITEM_I = new HatchMachine(Ref.ID, "hatch_item_input", GUI, ITEM);
    public static HatchMachine HATCH_ITEM_O = new HatchMachine(Ref.ID, "hatch_item_output", GUI, ITEM);
    public static HatchMachine HATCH_FLUID_I = new HatchMachine(Ref.ID, "hatch_fluid_input", GUI, FLUID);
    public static HatchMachine HATCH_FLUID_O = new HatchMachine(Ref.ID, "hatch_fluid_output", GUI, FLUID);
    public static HatchMachine HATCH_MUFFLER = new HatchMachine(Ref.ID, "hatch_muffler", GUI, ITEM);
    public static HatchMachine HATCH_DYNAMO = new HatchMachine(Ref.ID, "hatch_dynamo", ENERGY);
    public static HatchMachine HATCH_ENERGY = new HatchMachine(Ref.ID, "hatch_energy", ENERGY);

    public static TankMachine QUANTUM_TANK = new TankMachine(Ref.ID, "quantum_tank");

    public static BasicMachine STEAM_GENERATOR = new BasicMachine(Ref.ID, "steam_generator", STEAM_FUELS, LV, MV, HV, ITEM, FLUID);
    public static BasicMachine GAS_GENERATOR = new BasicMachine(Ref.ID, "gas_generator", GAS_FUELS, LV, MV, HV, ITEM, FLUID);
    public static BasicMachine COMBUSTION_GENERATOR = new BasicMachine(Ref.ID, "combustion_generator", COMBUSTION_FUELS, LV, MV, HV, ITEM, FLUID);
    public static BasicMachine NAQUADAH_GENERATOR = new BasicMachine(Ref.ID, "naquadah_generator", NAQUADAH_FUELS, EV, IV, LUV, ITEM, FLUID);
    public static BasicMachine PLASMA_GENERATOR = new BasicMachine(Ref.ID, "plasma_generator", PLASMA_FUELS, IV, LUV, ZPM, ITEM, FLUID);

    public static BlockBatteryBufferCreative CREATIVE_ENERGY_BUFFER = new BlockBatteryBufferCreative(Ref.ID, "creative_energy_buffer", ENERGY);

    public static void init() {

    }
}
