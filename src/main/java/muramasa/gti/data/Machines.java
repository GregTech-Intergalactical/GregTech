package muramasa.gti.data;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.*;
import muramasa.antimatter.tile.single.TileEntityBatteryBuffer;
import muramasa.antimatter.tile.single.TileEntityDigitalTransformer;
import muramasa.antimatter.tile.single.TileEntityInfiniteStorage;
import muramasa.antimatter.tile.single.TileEntityTransformer;
import muramasa.gti.Ref;
import muramasa.gti.machine.SteamMachine;
import muramasa.gti.tile.multi.*;
import muramasa.gti.tile.single.TileEntityCoalBoiler;
import muramasa.gti.tile.single.TileEntityInfiniteFluid;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.machine.MachineFlag.*;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.gti.data.RecipeMaps.*;

public class Machines {

    public static BasicMachine ALLOY_SMELTER = new BasicMachine(Ref.ID, "alloy_smelter").setMap(ALLOY_SMELTING).addFlags(GUI, ITEM);
    public static BasicMachine ASSEMBLER = new BasicMachine(Ref.ID, "assembler").setMap(ASSEMBLING).addFlags(GUI, ITEM, FLUID).setAllowVerticalFacing(true);
    public static BasicMachine BENDER = new BasicMachine(Ref.ID, "bender").setMap(BENDING).addFlags(GUI, ITEM);
    public static BasicMachine CANNER = new BasicMachine(Ref.ID, "canner").setMap(CANNING).addFlags(GUI, ITEM);
    public static BasicMachine COMPRESSOR = new BasicMachine(Ref.ID, "compressor").setMap(COMPRESSING).addFlags(GUI, ITEM);
    public static BasicMachine CUTTER = new BasicMachine(Ref.ID, "cutter").setMap(CUTTING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FURNACE = new BasicMachine(Ref.ID, "furnace").setMap(SMELTING).addFlags(GUI, ITEM);
    public static BasicMachine EXTRACTOR = new BasicMachine(Ref.ID, "extractor").setMap(EXTRACTING).addFlags(GUI, ITEM);
    public static BasicMachine EXTRUDER = new BasicMachine(Ref.ID, "extruder").setMap(EXTRUDING).addFlags(GUI, ITEM);
    public static BasicMachine LATHE = new BasicMachine(Ref.ID, "lathe").setMap(LATHING).addFlags(GUI, ITEM);
    public static BasicMachine PULVERIZER = new BasicMachine(Ref.ID, "pulverizer").setMap(PULVERIZING).addFlags(GUI, ITEM).setGuiTiers(ImmutableMap.<Tier, Tier>builder().put(HV, HV).put(EV, EV).put(IV, IV));
    public static BasicMachine RECYCLER = new BasicMachine(Ref.ID, "recycler").setMap(RECYCLING).addFlags(GUI, ITEM);
    public static BasicMachine SCANNER = new BasicMachine(Ref.ID, "scanner").setMap(SCANNING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine WIRE_MILL = new BasicMachine(Ref.ID, "wire_mill").setMap(WIRE_MILLING).addFlags(GUI, ITEM);
    public static BasicMachine CENTRIFUGE = new BasicMachine(Ref.ID, "centrifuge").setMap(CENTRIFUGING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine ELECTROLYZER = new BasicMachine(Ref.ID, "electrolyzer").setMap(ELECTROLYZING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine THERMAL_CENTRIFUGE = new BasicMachine(Ref.ID, "thermal_centrifuge").setMap(THERMAL_CENTRIFUGING).addFlags(GUI,ITEM).amps(2);
    public static BasicMachine ORE_WASHER = new BasicMachine(Ref.ID, "ore_washer").setMap(ORE_WASHING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine CHEMICAL_REACTOR = new BasicMachine(Ref.ID, "chemical_reactor").setMap(CHEMICAL_REACTING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FLUID_CANNER = new BasicMachine(Ref.ID, "fluid_canner").setMap(FLUID_CANNING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine DISASSEMBLER = new BasicMachine(Ref.ID, "disassembler").setMap(DISASSEMBLING).addFlags(GUI, ITEM);
    public static BasicMachine MASS_FABRICATOR = new BasicMachine(Ref.ID, "mass_fabricator").setMap(MASS_FABRICATING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine AMP_FABRICATOR = new BasicMachine(Ref.ID, "amp_fabricator").setMap(AMP_FABRICATING).addFlags(GUI, ITEM);
    public static BasicMachine REPLICATOR = new BasicMachine(Ref.ID, "replicator").setMap(REPLICATING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FERMENTER = new BasicMachine(Ref.ID, "fermenter").setMap(FERMENTING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FLUID_EXTRACTOR = new BasicMachine(Ref.ID, "fluid_extractor").setMap(FLUID_EXTRACTING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FLUID_SOLIDIFIER = new BasicMachine(Ref.ID, "fluid_solidifier").setMap(FLUID_SOLIDIFYING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine DISTILLERY = new BasicMachine(Ref.ID, "distillery").setMap(DISTILLING).addFlags(GUI, ITEM, FLUID).renderContainedLiquids();
    public static BasicMachine CHEMICAL_BATH = new BasicMachine(Ref.ID, "chemical_bath").setMap(CHEMICAL_BATHING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine AUTOCLAVE = new BasicMachine(Ref.ID, "autoclave").setMap(AUTOCLAVING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine MIXER = new BasicMachine(Ref.ID, "mixer").setMap(MIXING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine LASER_ENGRAVER = new BasicMachine(Ref.ID, "laser_engraver").setMap(LASER_ENGRAVING).addFlags(GUI, ITEM);
    public static BasicMachine FORMING_PRESS = new BasicMachine(Ref.ID, "forming_press").setMap(PRESSING).addFlags(GUI, ITEM);
    public static BasicMachine FORGE_HAMMER = new BasicMachine(Ref.ID, "forge_hammer").setMap(HAMMERING).addFlags(GUI, ITEM);
    public static BasicMachine SIFTER = new BasicMachine(Ref.ID, "sifter").setMap(SIFTING).addFlags(GUI, ITEM);
    public static BasicMachine ARC_FURNACE = new BasicMachine(Ref.ID, "arc_furnace").setMap(ARC_SMELTING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine PLASMA_ARC_FURNACE = new BasicMachine(Ref.ID, "plasma_arc_furnace").setMap(PLASMA_ARC_SMELTING).addFlags(GUI, ITEM, FLUID);

    public static SteamMachine COAL_BOILER = new SteamMachine(Ref.ID, "coal_boiler").setMap(COAL_BOILERS).setTiers(BRONZE, STEEL).addFlags(GUI, STEAM, ITEM, FLUID, CELL).baseTexture(Textures.BOILER_HANDLER).setTile(m -> () -> new TileEntityCoalBoiler(m));
    public static SteamMachine LAVA_BOILER = new SteamMachine(Ref.ID, "lava_boiler").setMap(SMALL_BOILERS).setTiers(STEEL).addFlags(GUI, STEAM, ITEM, FLUID);
    public static BasicMachine SOLAR_BOILER = new BasicMachine(Ref.ID, "solar_boiler").setMap(SMALL_BOILERS).setTiers(BRONZE).addFlags(GUI, STEAM, ITEM, FLUID);
    public static SteamMachine STEAM_FURNACE = new SteamMachine(Ref.ID, "steam_furnace").setMap(STEAM_SMELTING).setTiers(BRONZE, STEEL).addFlags(GUI, ITEM, FLUID);
    public static SteamMachine STEAM_PULVERIZER = new SteamMachine(Ref.ID, "steam_pulverizer").setMap(STEAM_PULVERIZING).setTiers(BRONZE, STEEL).addFlags(GUI, ITEM, FLUID);
    public static SteamMachine STEAM_EXTRACTOR = new SteamMachine(Ref.ID, "steam_extractor").setMap(STEAM_EXTRACTING).setTiers(BRONZE, STEEL).addFlags(GUI, ITEM, FLUID);
    public static SteamMachine STEAM_FORGE_HAMMER = new SteamMachine(Ref.ID, "steam_forge_hammer").setMap(STEAM_HAMMERING).setTiers(BRONZE, STEEL).addFlags(GUI, ITEM, FLUID);
    public static SteamMachine STEAM_COMPRESSOR = new SteamMachine(Ref.ID, "steam_compressor").setMap(STEAM_COMPRESSING).setTiers(BRONZE, STEEL).addFlags(GUI, ITEM, FLUID);
    public static SteamMachine STEAM_ALLOY_SMELTER = new SteamMachine(Ref.ID, "steam_alloy_smelter").setMap(STEAM_ALLOY_SMELTING).setTiers(BRONZE, STEEL).addFlags(GUI, ITEM, FLUID);

    public static BasicMultiMachine<?> COKE_OVEN = new BasicMultiMachine<>(Ref.ID, "coke_oven").setMap(COKING).setTiers(BRONZE).addFlags(GUI, ITEM).setTile(m -> () -> new TileEntityCokeOven(m));
    public static BasicMultiMachine<?> PRIMITIVE_BLAST_FURNACE = new BasicMultiMachine<>(Ref.ID, "primitive_blast_furnace").setMap(BASIC_BLASTING).setTiers(BRONZE).addFlags(GUI, ITEM).setTile(m -> () -> new TileEntityPrimitiveBlastFurnace(m));
    public static BasicMultiMachine<?> BRONZE_BLAST_FURNACE = new BasicMultiMachine<>(Ref.ID, "bronze_blast_furnace").setMap(BASIC_BLASTING).setTiers(BRONZE).addFlags(GUI, ITEM).setTile(m -> () -> new TileEntityBronzeBlastFurnace(m));
    public static BasicMultiMachine<?> CHARCOAL_PIT = new BasicMultiMachine<>(Ref.ID, "charcoal_pit").setTiers(BRONZE).setTile(m -> () -> new TileEntityCharcoalPit(m));
    public static MultiMachine BLAST_FURNACE = new MultiMachine(Ref.ID, "electric_blast_furnace").setMap(BLASTING).setTiers(LV).addFlags(GUI, ITEM, FLUID, ENERGY).setTile(m -> () -> new TileEntityElectricBlastFurnace(m));
    public static MultiMachine IMPLOSION_COMPRESSOR = new MultiMachine(Ref.ID, "implosion_compressor").setMap(IMPLOSION_COMPRESSING).setTiers(HV).addFlags(GUI, ITEM, ENERGY).setTile(m -> () -> new TileEntityImplosionCompressor(m));
    public static MultiMachine VACUUM_FREEZER = new MultiMachine(Ref.ID, "vacuum_freezer").setMap(VACUUM_FREEZING).setTiers(HV).addFlags(GUI, ITEM, FLUID, ENERGY).setTile(m -> () -> new TileEntityVacuumFreezer(m));
    public static MultiMachine MULTI_SMELTER = new MultiMachine(Ref.ID, "multi_smelter").setMap(SMELTING).setTiers(HV).addFlags(GUI, ITEM, ENERGY).setTile(m -> () -> new TileEntityMultiSmelter(m));
    public static MultiMachine LARGE_BOILER = new MultiMachine(Ref.ID, "large_boiler").setTiers(LV, MV, HV, EV).addFlags(GUI, ITEM, FLUID).setTile(m -> () -> new TileEntityLargeBoiler(m));
    public static MultiMachine LARGE_TURBINE = new MultiMachine(Ref.ID, "large_turbine").setMap(STEAM_FUELS).setTiers(HV, EV, IV, UV).addFlags(GUI, FLUID, ENERGY, GENERATOR).setTile(m -> () -> new TileEntityLargeTurbine(m));
    public static MultiMachine HEAT_EXCHANGER = new MultiMachine(Ref.ID, "heat_exchanger").setTiers(EV).addFlags(GUI, FLUID, ENERGY).setTile(m -> () -> new TileEntityHeatExchanger(m));
    public static MultiMachine OIL_DRILLING_RIG = new MultiMachine(Ref.ID, "oil_drilling_rig").setTiers(EV, IV, LUV, ZPM).addFlags(GUI, FLUID, ENERGY).setTile(m -> () -> new TileEntityOilDrillingRig(m));
    public static MultiMachine OIL_CRACKING_UNIT = new MultiMachine(Ref.ID, "oil_cracking_unit").setTiers(HV).addFlags(GUI, FLUID, ENERGY).setTile(m -> () -> new TileEntityOilCrackingUnit(m));
    public static MultiMachine ADVANCED_MINER = new MultiMachine(Ref.ID, "advanced_miner").setTiers(LV).addFlags(GUI, ITEM, ENERGY).setTile(m -> () -> new TileEntityAdvancedMiner(m));
    public static MultiMachine PYROLYSIS_OVEN = new MultiMachine(Ref.ID, "pyrolysis_oven").setTiers(MV).addFlags(GUI, ITEM, FLUID, ENERGY).setTile(m -> () -> new TileEntityPyrolysisOven(m));
    public static MultiMachine COMBUSTION_ENGINE = new MultiMachine(Ref.ID, "combustion_engine").setMap(COMBUSTION_FUELS).setTiers(EV).addFlags(GUI, FLUID, ENERGY).setTile(m -> () -> new TileEntityCombustionEngine(m));
    public static MultiMachine FUSION_REACTOR = new MultiMachine(Ref.ID, "fusion_reactor").setMap(FUSION).setTiers(LUV, ZPM, UV).addFlags(GUI, FLUID,ENERGY).setTile(m -> () -> new TileEntityFusionReactor(m));
    public static MultiMachine DISTLLATION_TOWER = new MultiMachine(Ref.ID, "distillation_tower").setMap(DISTILLATION).setTiers(HV).addFlags(GUI, ITEM, FLUID,ENERGY).setTile(m -> () -> new TileEntityDistillationTower(m));

    public static HatchMachine HATCH_ITEM_I = new HatchMachine(Ref.ID, "hatch_item_input", COVERINPUT).addFlags(GUI, ITEM);
    public static HatchMachine HATCH_ITEM_O = new HatchMachine(Ref.ID, "hatch_item_output", COVEROUTPUT).addFlags(GUI, ITEM);
    public static HatchMachine HATCH_FLUID_I = new HatchMachine(Ref.ID, "hatch_fluid_input", COVERINPUT).addFlags(GUI, FLUID, CELL);
    public static HatchMachine HATCH_FLUID_O = new HatchMachine(Ref.ID, "hatch_fluid_output", COVEROUTPUT).addFlags(GUI, FLUID, CELL);
    public static HatchMachine HATCH_MUFFLER = new HatchMachine(Ref.ID, "hatch_muffler", COVERMUFFLER).addFlags(GUI, ITEM);
    public static HatchMachine HATCH_DYNAMO = new HatchMachine(Ref.ID, "hatch_dynamo", COVERDYNAMO).addFlags(ENERGY);
    public static HatchMachine HATCH_ENERGY = new HatchMachine(Ref.ID, "hatch_energy", COVERENERGY).addFlags(ENERGY);

    public static TankMachine QUANTUM_TANK = new TankMachine(Ref.ID, "quantum_tank").addFlags(BASIC, GUI, CELL).frontCovers();

    public static GeneratorMachine STEAM_GENERATOR = new GeneratorMachine(Ref.ID, "steam_generator").setMap(STEAM_FUELS).setTiers(LV, MV, HV).addFlags(GUI, ITEM, FLUID, CELL).allowFrontIO();
    public static GeneratorMachine GAS_GENERATOR = new GeneratorMachine(Ref.ID, "gas_generator").setMap(GAS_FUELS).setTiers(LV, MV, HV).addFlags(GUI, ITEM, FLUID, CELL).allowFrontIO();
    public static GeneratorMachine COMBUSTION_GENERATOR = new GeneratorMachine(Ref.ID, "combustion_generator").setMap(COMBUSTION_FUELS).setTiers(LV, MV, HV).addFlags(GUI, ITEM, FLUID, CELL).allowFrontIO();
    public static GeneratorMachine NAQUADAH_GENERATOR = new GeneratorMachine(Ref.ID, "naquadah_generator").setMap(NAQUADAH_FUELS).setTiers(EV, IV, LUV).addFlags(GUI, ITEM, FLUID, CELL).allowFrontIO();
    public static GeneratorMachine PLASMA_GENERATOR = new GeneratorMachine(Ref.ID, "plasma_generator").setMap(PLASMA_FUELS).setTiers(IV, LUV, ZPM).addFlags(GUI, ITEM, FLUID, CELL).allowFrontIO();

    public static BasicMachine INFINITE_STORAGE = new BasicMachine(Ref.ID, "infinite_storage").setTiers(ULV, LV, MV, HV, EV, IV, LUV, ZPM, UV, MAX).addFlags(ENERGY).setTile(m -> () -> new TileEntityInfiniteStorage<>(m, 16)).noCovers().setAllowVerticalFacing(true).allowFrontIO();
    public static TankMachine INFINITE_STEAM = new TankMachine(Ref.ID, "infinite_steam").addFlags(FLUID, CELL, GUI).setTile(m -> () -> new TileEntityInfiniteFluid(m)).setTiers(LV);
    public static BasicMachine BATTERY_BUFFER_FOUR = new BasicMachine(Ref.ID, "battery_buffer_four").addFlags(GUI, ENERGY, ITEM).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).noCovers().setTile(m -> () -> new TileEntityBatteryBuffer<>(m)).setAllowVerticalFacing(true).allowFrontIO();
    public static BasicMachine BATTERY_BUFFER_ONE = new BasicMachine(Ref.ID, "battery_buffer_one").addFlags(GUI, ENERGY, ITEM).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).noCovers().setTile(m -> () -> new TileEntityBatteryBuffer<>(m)).setAllowVerticalFacing(true).allowFrontIO();
    public static BasicMachine BATTERY_BUFFER_NINE = new BasicMachine(Ref.ID, "battery_buffer_nine").addFlags(GUI, ENERGY, ITEM).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).noCovers().setTile(m -> () -> new TileEntityBatteryBuffer<>(m)).setAllowVerticalFacing(true).allowFrontIO();
    public static BasicMachine TRANSFORMER = new BasicMachine(Ref.ID, "transformer").addFlags(ENERGY).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).setTile(m -> () -> new TileEntityTransformer<>(m, 1)).noCovers().allowFrontIO();
    public static BasicMachine TRANSFORMER_HIAMP = new BasicMachine(Ref.ID, "transformer_hiamp").addFlags(ENERGY).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).setTile(m -> () -> new TileEntityTransformer<>(m, 4)).noCovers().allowFrontIO();
    public static BasicMachine TRANSFORMER_ULTRA = new BasicMachine(Ref.ID, "transformer_ultra").addFlags(ENERGY).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).setTile(m -> () -> new TileEntityTransformer<>(m, 16)).noCovers().allowFrontIO();
    public static BasicMachine TRANSFORMER_DIGITAL = new BasicMachine(Ref.ID, "transformer_digital").setTiers(EV, IV).addFlags(GUI, ENERGY).setTile(m -> () -> new TileEntityDigitalTransformer<>(m)).noCovers().allowFrontIO();

    public static void init() {

    }
}
