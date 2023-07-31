package muramasa.gregtech.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.Ref;
import muramasa.antimatter.block.BlockBasic;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.item.*;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialType;
import muramasa.antimatter.material.MaterialTypeFluid;
import muramasa.antimatter.ore.CobbleStoneType;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.*;
import muramasa.antimatter.registration.Side;
import muramasa.antimatter.texture.Texture;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.block.*;
import muramasa.gregtech.block.BlockCoil.CoilData;
import muramasa.gregtech.cover.*;
import muramasa.gregtech.cover.redstone.CoverRedstoneMachineController;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MaterialColor;

import static muramasa.gregtech.data.Materials.*;

public class GregTechData {

    private static final boolean HC = AntimatterConfig.GAMEPLAY.HARDCORE_CABLES;

    public static void init(Side side) {
        if (side == Side.CLIENT)
            RecipeMaps.clientMaps();
        AntimatterAPI.all(MaterialType.class, t -> {
            if (t instanceof MaterialTypeFluid) return;
            if (t.getClass() == MaterialType.class) return;
            //TODO: add better check
            if (t == AntimatterMaterialTypes.ORE_STONE) return;
            CoverFactory.builder((a,b,c,d) -> new CoverTypeFilter(a,b,c,d,t)).addTextures(Material.NULL.getSet().getTextures(t)).item((a, b) -> {
            return new ItemCover(a.getDomain(), a.getId()).tip("Filters for " + t.getId()).texture(Material.NULL.getSet().getTextures(t));}).build(GTIRef.ID, "cover_type_" + t.getId());
        });
    }

    public static final CoverFactory COVER_CONVEYOR = CoverFactory.builder(CoverConveyor::new).gui().item((a,b) ->
            new ItemCover(a.getDomain(), a.getId(), b).tip(String.format("1 Stack every %ds (as Cover)", CoverConveyor.speeds.get(b)))
    ).addTextures(new Texture(GTIRef.ID, "block/cover/conveyor")).setTiers(Tier.getStandard()).build(GTIRef.ID, "conveyor");
    public static final CoverFactory COVER_PUMP = CoverFactory.builder(CoverPump::new).gui().item((a,b) ->
            new ItemCover(a.getDomain(), a.getId(), b).tip(String.format("%d L/s (as Cover)", CoverPump.speeds.get(b))))
            .addTextures(new Texture(GTIRef.ID, "block/cover/pump")).setTiers(Tier.getStandard()).build(GTIRef.ID, "pump");
    public static final CoverFactory COVER_DRAIN = CoverFactory.builder(CoverDrain::new).item((a, b) ->
            new ItemCover(GTIRef.ID, "drain").tip("Can be placed on machines as a cover")).addTextures(new Texture(GTIRef.ID, "block/cover/drain")).build(GTIRef.ID, "drain");

    public static final CoverFactory COVER_REDSTONE_MACHINE_CONTROLLER = CoverFactory.builder(CoverRedstoneMachineController::new).gui().item((a, b) -> {
        return new ItemCover(GTIRef.ID, "redstone_machine_controller");
    }).addTextures(new Texture(GTIRef.ID, "block/cover/redstone_machine_controller")).build(GTIRef.ID, "redstone_machine_controller");

    public static final CoverFactory COVER_STEAM_VENT = CoverFactory.builder(CoverSteamVent::new)
            .addTextures(new Texture(GTIRef.ID, "block/cover/output")).build(Ref.ID, "steam_vent");
    public static ItemBasic<?> ComputerMonitor = new ItemBasic<>(GTIRef.ID, "computer_monitor").tip("Can be placed on machines as a cover");

    public static ItemFluidCell CellTin = new ItemFluidCell(GTIRef.ID, Tin, 1000);
    public static ItemFluidCell CellSteel = new ItemFluidCell(GTIRef.ID, Steel, 16000);
    public static ItemFluidCell CellTungstensteel = new ItemFluidCell(GTIRef.ID, TungstenSteel, 64000);

    public static ItemBasic<?> Biochaff = new ItemBasic<>(GTIRef.ID, "biochaff");
    public static ItemBasic<?> CarbonFibre = new ItemBasic<>(GTIRef.ID, "raw_carbon_fibre");
    public static ItemBasic<?> CarbonMesh = new ItemBasic<>(GTIRef.ID, "carbon_mesh");
    public static ItemBasic<?> CoalBall = new ItemBasic<>(GTIRef.ID, "coal_ball");
    public static ItemBasic<?> CompressedCoalBall = new ItemBasic<>(GTIRef.ID, "compressed_coal_ball");
    public static ItemBasic<?> CoalChunk = new ItemBasic<>(GTIRef.ID, "coal_chunk");

    public static ItemBasic<?> ItemFilter = new ItemBasic<>(GTIRef.ID, "item_filter");
    public static ItemBasic<?> DiamondSawBlade = new ItemBasic<>(GTIRef.ID, "diamond_saw_blade");
    public static ItemBasic<?> DiamondGrindHead = new ItemBasic<>(GTIRef.ID, "diamond_grind_head");
    public static ItemBasic<?> TungstenGrindHead = new ItemBasic<>(GTIRef.ID, "tungsten_grind_head");
    public static ItemBasic<?> IridiumAlloyIngot = new ItemBasic<>(GTIRef.ID, "iridium_alloy_ingot").tip("Used to make Iridium Plates");
    public static ItemBasic<?> IridiumReinforcedPlate = new ItemBasic<>(GTIRef.ID, "iridium_reinforced_plate").tip("GT2s Most Expensive Component");
    public static ItemBasic<?> IridiumNeutronReflector = new ItemBasic<>(GTIRef.ID, "iridium_neutron_reflector").tip("Indestructible");
    public static ItemBasic<?> QuantumEye = new ItemBasic<>(GTIRef.ID, "quantum_eye").tip("Improved Ender Eye");
    public static ItemBasic<?> QuantumStat = new ItemBasic<>(GTIRef.ID, "quantum_star").tip("Improved Nether Star");
    public static ItemBasic<?> GraviStar = new ItemBasic<>(GTIRef.ID, "gravi_star").tip("Ultimate Nether Star");
    public static ItemBasic<?> CompressedFireClay = new ItemBasic<>(GTIRef.ID, "compressed_fire_clay").tip("Brick Shaped");
    public static ItemBasic<?> FireBrick = new ItemBasic<>(GTIRef.ID, "fire_brick").tip("Heat Resistant");

    public static ItemBasic<?> MotorLV = new ItemBasic<>(GTIRef.ID, "motor_lv");
    public static ItemBasic<?> MotorMV = new ItemBasic<>(GTIRef.ID, "motor_mv");
    public static ItemBasic<?> MotorHV = new ItemBasic<>(GTIRef.ID, "motor_hv");
    public static ItemBasic<?> MotorEV = new ItemBasic<>(GTIRef.ID, "motor_ev");
    public static ItemBasic<?> MotorIV = new ItemBasic<>(GTIRef.ID, "motor_iv");
    public static ItemBasic<?> FluidRegulatorLV = new ItemBasic<>(GTIRef.ID, "fluid_regulator_lv").tip("Configurable up to 640 L/s (as Cover)");
    public static ItemBasic<?> FluidRegulatorMV = new ItemBasic<>(GTIRef.ID, "fluid_regulator_mv").tip("Configurable up to 2,560 L/s (as Cover)");
    public static ItemBasic<?> FluidRegulatorHV = new ItemBasic<>(GTIRef.ID, "fluid_regulator_hv").tip("Configurable up to 10,240 L/s (as Cover)");
    public static ItemBasic<?> FluidRegulatorEV = new ItemBasic<>(GTIRef.ID, "fluid_regulator_ev").tip("Configurable up to 40,960 L/s (as Cover)");
    public static ItemBasic<?> FluidRegulatorIV = new ItemBasic<>(GTIRef.ID, "fluid_regulator_iv").tip("Configurable up to 163,840 L/s (as Cover)");
    public static ItemBasic<?> PistonLV = new ItemBasic<>(GTIRef.ID, "piston_lv");
    public static ItemBasic<?> PistonMV = new ItemBasic<>(GTIRef.ID, "piston_mv");
    public static ItemBasic<?> PistonHV = new ItemBasic<>(GTIRef.ID, "piston_hv");
    public static ItemBasic<?> PistonEV = new ItemBasic<>(GTIRef.ID, "piston_ev");
    public static ItemBasic<?> PistonIV = new ItemBasic<>(GTIRef.ID, "piston_iv");
    public static ItemBasic<?> RobotArmLV = new ItemBasic<>(GTIRef.ID, "robot_arm_lv").tip("Insets into specific Slots (as Cover)");
    public static ItemBasic<?> RobotArmMV = new ItemBasic<>(GTIRef.ID, "robot_arm_mv").tip("Insets into specific Slots (as Cover)");
    public static ItemBasic<?> RobotArmHV = new ItemBasic<>(GTIRef.ID, "robot_arm_hv").tip("Insets into specific Slots (as Cover)");
    public static ItemBasic<?> RobotArmEV = new ItemBasic<>(GTIRef.ID, "robot_arm_ev").tip("Insets into specific Slots (as Cover)");
    public static ItemBasic<?> RobotArmIV = new ItemBasic<>(GTIRef.ID, "robot_arm_iv").tip("Insets into specific Slots (as Cover)");
    public static ItemBasic<?> FieldGenLV = new ItemBasic<>(GTIRef.ID, "field_gen_lv");
    public static ItemBasic<?> FieldGenMV = new ItemBasic<>(GTIRef.ID, "field_gen_mv");
    public static ItemBasic<?> FieldGenHV = new ItemBasic<>(GTIRef.ID, "field_gen_hv");
    public static ItemBasic<?> FieldGenEV = new ItemBasic<>(GTIRef.ID, "field_gen_ev");
    public static ItemBasic<?> FieldGenIV = new ItemBasic<>(GTIRef.ID, "field_gen_iv");
    public static ItemBasic<?> EmitterLV = new ItemBasic<>(GTIRef.ID, "emitter_lv");
    public static ItemBasic<?> EmitterMV = new ItemBasic<>(GTIRef.ID, "emitter_mv");
    public static ItemBasic<?> EmitterHV = new ItemBasic<>(GTIRef.ID, "emitter_hv");
    public static ItemBasic<?> EmitterEV = new ItemBasic<>(GTIRef.ID, "emitter_ev");
    public static ItemBasic<?> EmitterIV = new ItemBasic<>(GTIRef.ID, "emitter_iv");
    public static ItemBasic<?> SensorLV = new ItemBasic<>(GTIRef.ID, "sensor_lv");
    public static ItemBasic<?> SensorMV = new ItemBasic<>(GTIRef.ID, "sensor_mv");
    public static ItemBasic<?> SensorHV = new ItemBasic<>(GTIRef.ID, "sensor_hv");
    public static ItemBasic<?> SensorEV = new ItemBasic<>(GTIRef.ID, "sensor_ev");
    public static ItemBasic<?> SensorIV = new ItemBasic<>(GTIRef.ID, "sensor_iv");

    /** CIRCUIT ITEMS **/

    public static ItemBasic<?> NandChip = new ItemBasic<>(GTIRef.ID, "nand_chip").tip("A very simple circuit");
    public static ItemBasic<?> AdvCircuitParts = new ItemBasic<>(GTIRef.ID, "advanced_circuit_parts").tip("Used for making Advanced Circuits");
    public static ItemBasic<?> EtchedWiringMV = new ItemBasic<>(GTIRef.ID, "etched_wiring_mv").tip("Circuit board parts");
    public static ItemBasic<?> EtchedWiringHV = new ItemBasic<>(GTIRef.ID, "etched_wiring_hv").tip("Circuit board parts");
    public static ItemBasic<?> EtchedWiringEV = new ItemBasic<>(GTIRef.ID, "etched_wiring_ev").tip("Circuit board parts");
    public static ItemBasic<?> EngravedCrystalChip = new ItemBasic<>(GTIRef.ID, "engraved_crystal_chip").tip("Needed for Circuits");
    public static ItemBasic<?> EngravedLapotronChip = new ItemBasic<>(GTIRef.ID, "engraved_lapotron_chip").tip("Needed for Circuits");

    public static ItemBasic<?> CircuitBoardBasic = new ItemBasic<>(GTIRef.ID, "basic_circuit_board");
    public static ItemBasic<?> CircuitBoardAdvanced = new ItemBasic<>(GTIRef.ID, "advanced_circuit_board");
    public static ItemBasic<?> CircuitBoardEmpty = new ItemBasic<>(GTIRef.ID, "empty_circuit_board");
    public static ItemBasic<?> CircuitBoardProcessor = new ItemBasic<>(GTIRef.ID, "processor_circuit_board");
    public static ItemBasic<?> CircuitBoardProcessorEmpty = new ItemBasic<>(GTIRef.ID, "empty_processor_circuit_board");

    public static ItemBasic<?> CircuitBoardCoated = new ItemBasic<>(GTIRef.ID, "board_coated").tip("The most basic Board");
    public static ItemBasic<?> CircuitBoardPhenolic = new ItemBasic<>(GTIRef.ID, "board_phenolic").tip("A basic Board");
    public static ItemBasic<?> CircuitBoardPlastic = new ItemBasic<>(GTIRef.ID, "board_plastic").tip("An advanced Board");
    public static ItemBasic<?> CircuitBoardEpoxy = new ItemBasic<>(GTIRef.ID, "board_epoxy").tip("4th Tier Board");
    public static ItemBasic<?> CircuitBoardFiber = new ItemBasic<>(GTIRef.ID, "board_fiber_reinforced").tip("5th Tier Board");
    public static ItemBasic<?> CircuitBoardMultiFiber = new ItemBasic<>(GTIRef.ID, "board_multilayer_fiber_reinforced").tip("6th Tier Board");
    public static ItemBasic<?> CircuitBoardWetware = new ItemBasic<>(GTIRef.ID, "board_wetware").tip("7th Tier Board");

    public static ItemBasic<?> CircuitBasicIntegrated = new ItemBasic<>(GTIRef.ID, "basic_integrated_circuit").tip("The breakthrough to the electrical age");
    public static ItemBasic<?> CircuitBasicElectronic = new ItemBasic<>(GTIRef.ID, "basic_electronic_circuit").tip("A basic Circuit");
    public static ItemBasic<?> CircuitGoodElectronic = new ItemBasic<>(GTIRef.ID, "good_electronic_circuit").tip("A good Circuit");
    public static ItemBasic<?> CircuitGoodIntegrated = new ItemBasic<>(GTIRef.ID, "good_integrated_circuit").tip("Good Circuit");
    public static ItemBasic<?> CircuitAdv = new ItemBasic<>(GTIRef.ID, "advanced_circuit").tip("An advanced Circuit");
    public static ItemBasic<?> CircuitDataStorage = new ItemBasic<>(GTIRef.ID, "data_storage_circuit");
    public static ItemBasic<?> CircuitDataControl = new ItemBasic<>(GTIRef.ID, "data_control_circuit");
    public static ItemBasic<?> MicroProcessor = new ItemBasic<>(GTIRef.ID, "microprocessor").tip("A Basic Circuit");
    public static ItemBasic<?> IntegratedProcessor = new ItemBasic<>(GTIRef.ID, "integrated_processor").tip("A Good Circuit");
    public static ItemBasic<?> NanoProcessor = new ItemBasic<>(GTIRef.ID, "nanoprocessor").tip("A Nanoprocessor");
    public static ItemBasic<?> QuantumProcessor = new ItemBasic<>(GTIRef.ID, "quantumprocessor").tip("A Quantumprocessor");
    public static ItemBasic<?> CircuitEnergyFlow = new ItemBasic<>(GTIRef.ID, "energy_flow_circuit").tip("A High Voltage Processor");
    public static ItemBasic<?> CircuitWetware = new ItemBasic<>(GTIRef.ID, "wetware_circuit").tip("You feel like it's watching you");
    public static ItemBasic<?> DataOrb = new ItemBasic<>(GTIRef.ID, "data_orb");

    public static ItemBasic<?> Transistor = new ItemBasic<>(GTIRef.ID, "transistor").tip("An integral part to Circuitry");
    public static ItemBasic<?> Resistor = new ItemBasic<>(GTIRef.ID, "resistor").tip("An integral part to Circuitry");
    public static ItemBasic<?> Capacitor = new ItemBasic<>(GTIRef.ID, "capacitor").tip("An integral part to Circuitry");
    public static ItemBasic<?> VacuumTube = new ItemBasic<>(GTIRef.ID, "vacuum_tube").tip("A part for the Basic Circuit");
    public static ItemBasic<?> GlassTube = new ItemBasic<>(GTIRef.ID, "glass_tube").tip("A part for circuits");

    public static ItemBasic<?> SiliconBoule = new ItemBasic<>(GTIRef.ID, "monocrystalline_silicon_boule", "silicon/");
    public static ItemBasic<?> GlowstoneDopedSiliconBoule = new ItemBasic<>(GTIRef.ID, "glowstone_doped_monocrystalline_silicon_boule", "silicon/");
    public static ItemBasic<?> NaquadahDopedSiliconBoule = new ItemBasic<>(GTIRef.ID, "naquadah_doped_monocrystalline_silicon_boule", "silicon/");

    public static ItemBasic<?> Wafer = new ItemBasic<>(GTIRef.ID, "wafer", "silicon/");
    public static ItemBasic<?> GlowstoneDopedWafer = new ItemBasic<>(GTIRef.ID, "glowstone_doped_wafer", "silicon/");
    public static ItemBasic<?> NaquadahDopedWafer = new ItemBasic<>(GTIRef.ID, "naquadah_doped_wafer", "silicon/");

    public static ItemBasic<?> BatteryTantalum = new ItemBattery(GTIRef.ID, "tantalum_capacitor", Tier.ULV, 10000, true).tip("Reusable");
    public static ItemBasic<?> BatteryHullSmall = new ItemBasic<>(GTIRef.ID, "small_battery_hull").tip("An empty LV Battery Hull");
    public static ItemBasic<?> BatteryHullMedium = new ItemBasic<>(GTIRef.ID, "medium_battery_hull").tip("An empty MV Battery Hull");
    public static ItemBasic<?> BatteryHullLarge = new ItemBasic<>(GTIRef.ID, "large_battery_hull").tip("An empty HV Battery Hull");
    public static ItemBasic<?> BatterySmallAcid = new ItemMultiTextureBattery(GTIRef.ID, "small_acid_battery", Tier.LV, 18000, false).tip("Single Use");
    public static ItemBasic<?> BatterySmallMercury = new ItemMultiTextureBattery(GTIRef.ID, "small_mercury_battery", Tier.LV, 32000, false).tip("Single Use");
    public static ItemBasic<?> BatterySmallCadmium = new ItemMultiTextureBattery(GTIRef.ID, "small_cadmium_battery", Tier.LV,75000, true).tip("Reusable");
    public static ItemBasic<?> BatterySmallLithium = new ItemMultiTextureBattery(GTIRef.ID, "small_lithium_battery", Tier.LV, 100000, true).tip("Reusable");
    public static ItemBasic<?> BatterySmallSodium = new ItemMultiTextureBattery(GTIRef.ID, "small_sodium_battery", Tier.LV, 50000, true).tip("Reusable");
    public static ItemBasic<?> BatteryMediumAcid = new ItemMultiTextureBattery(GTIRef.ID, "medium_acid_battery", Tier.MV, 72000, false).tip("Single Use");
    public static ItemBasic<?> BatteryMediumMercury = new ItemMultiTextureBattery(GTIRef.ID, "medium_mercury_battery", Tier.MV, 128000, false).tip("Single Use");
    public static ItemBasic<?> BatteryMediumCadmium = new ItemMultiTextureBattery(GTIRef.ID, "medium_cadmium_battery", Tier.MV, 300000, true).tip("Reusable");
    public static ItemBasic<?> BatteryMediumLithium = new ItemMultiTextureBattery(GTIRef.ID, "medium_lithium_battery", Tier.MV, 400000, true).tip("Reusable");
    public static ItemBasic<?> BatteryMediumSodium = new ItemMultiTextureBattery(GTIRef.ID, "medium_sodium_battery", Tier.MV,200000, true).tip("Reusable");
    public static ItemBasic<?> BatteryLargeAcid = new ItemMultiTextureBattery(GTIRef.ID, "large_acid_battery", Tier.HV, 288000, false).tip("Single Use");
    public static ItemBasic<?> BatteryLargeMercury = new ItemMultiTextureBattery(GTIRef.ID, "large_mercury_battery", Tier.HV, 512000, false).tip("Single Use");
    public static ItemBasic<?> BatteryLargeCadmium = new ItemMultiTextureBattery(GTIRef.ID, "large_cadmium_battery", Tier.HV, 1200000, true).tip("Reusable");
    public static ItemBasic<?> BatteryLargeLithium = new ItemMultiTextureBattery(GTIRef.ID, "large_lithium_battery", Tier.HV, 1600000, true).tip("Reusable");
    public static ItemBasic<?> BatteryLargeSodium = new ItemMultiTextureBattery(GTIRef.ID, "large_sodium_battery", Tier.HV, 800000, true).tip("Reusable");
    public static ItemBasic<?> LapotronCrystal = new ItemMultiTextureBattery(GTIRef.ID, "lapotron_crystal", Tier.EV, 10000000, true);
    public static ItemBasic<?> EnergyCrystal = new ItemMultiTextureBattery(GTIRef.ID, "energy_crystal", Tier.HV, 1000000, true);
    public static ItemBasic<?> BatteryEnergyOrb = new ItemBasic<>(GTIRef.ID, "battery_energy_orb");
    public static ItemBasic<?> BatteryEnergyOrbCluster = new ItemBasic<>(GTIRef.ID, "battery_energy_orb_cluster");

    public static ItemBasic<?> EmptyShape = new ItemBasic<>(GTIRef.ID, "empty_shape_plate", "molds/").tip("Raw plate to make Molds and Extruder Shapes");
    public static ItemBasic<?> MoldPlate = new ItemBasic<>(GTIRef.ID, "mold_plate", "molds/").tip("Mold for making Plates");
    public static ItemBasic<?> MoldCasing = new ItemBasic<>(GTIRef.ID, "mold_casing", "molds/").tip("Mold for making Item Casings");
    public static ItemBasic<?> MoldGear = new ItemBasic<>(GTIRef.ID, "mold_gear", "molds/").tip("Mold for making Gears");
    public static ItemBasic<?> MoldGearSmall = new ItemBasic<>(GTIRef.ID, "mold_small_gear", "molds/").tip("Mold for making Small Gears");
    public static ItemBasic<?> MoldCoinage = new ItemBasic<>(GTIRef.ID, "mold_coinage", "molds/").tip("Secure Mold for making Coins (Don't lose it!)");
    public static ItemBasic<?> MoldBottle = new ItemBasic<>(GTIRef.ID, "mold_bottle", "molds/").tip("Mold for making Bottles");
    public static ItemBasic<?> MoldIngot = new ItemBasic<>(GTIRef.ID, "mold_ingot", "molds/").tip("Mold for making Ingots");
    public static ItemBasic<?> MoldBall = new ItemBasic<>(GTIRef.ID, "mold_ball", "molds/").tip("Mold for making Balls");
    public static ItemBasic<?> MoldBlock = new ItemBasic<>(GTIRef.ID, "mold_block", "molds/").tip("Mold for making Blocks");
    public static ItemBasic<?> MoldNugget = new ItemBasic<>(GTIRef.ID, "mold_nugget", "molds/").tip("Mold for making Nuggets");
    public static ItemBasic<?> MoldAnvil = new ItemBasic<>(GTIRef.ID, "mold_anvil", "molds/").tip("Mold for making Anvils");
    public static ItemBasic<?> ShapePlate = new ItemBasic<>(GTIRef.ID, "shape_plate", "molds/").tip("Shape for making Plates");
    public static ItemBasic<?> ShapeCasing = new ItemBasic<>(GTIRef.ID, "shape_casing", "molds/").tip("Shape for making Item Casings");
    public static ItemBasic<?> ShapeRod = new ItemBasic<>(GTIRef.ID, "shape_rod", "molds/").tip("Shape for making Rods");
    public static ItemBasic<?> ShapeBolt = new ItemBasic<>(GTIRef.ID, "shape_bolt", "molds/").tip("Shape for making Bolts");
    public static ItemBasic<?> ShapeRing = new ItemBasic<>(GTIRef.ID, "shape_ring", "molds/").tip("Shape for making Rings");
    public static ItemBasic<?> ShapeCell = new ItemBasic<>(GTIRef.ID, "shape_cell", "molds/").tip("Shape for making Cells");
    public static ItemBasic<?> ShapeIngot = new ItemBasic<>(GTIRef.ID, "shape_ingot", "molds/").tip("Shape for making Ingots");
    public static ItemBasic<?> ShapeWire = new ItemBasic<>(GTIRef.ID, "shape_wire", "molds/").tip("Shape for making Wires");
    public static ItemBasic<?> ShapePipeTiny = new ItemBasic<>(GTIRef.ID, "shape_pipe_tiny", "molds/").tip("Shape for making Tiny Pipes");
    public static ItemBasic<?> ShapePipeSmall = new ItemBasic<>(GTIRef.ID, "shape_pipe_small", "molds/").tip("Shape for making Small Pipes");
    public static ItemBasic<?> ShapePipeNormal = new ItemBasic<>(GTIRef.ID, "shape_pipe_normal", "molds/").tip("Shape for making Normal Pipes");
    public static ItemBasic<?> ShapePipeLarge = new ItemBasic<>(GTIRef.ID, "shape_pipe_large", "molds/").tip("Shape for making Large Pipes");
    public static ItemBasic<?> ShapePipeHuge = new ItemBasic<>(GTIRef.ID, "shape_pipe_huge", "molds/").tip("Shape for making Huge Pipes");
    public static ItemBasic<?> ShapeBlock = new ItemBasic<>(GTIRef.ID, "shape_block", "molds/").tip("Shape for making Blocks");
    public static ItemBasic<?> ShapeHeadSword = new ItemBasic<>(GTIRef.ID, "shape_head_sword", "molds/").tip("Shape for making Sword Blades");
    public static ItemBasic<?> ShapeHeadPickaxe = new ItemBasic<>(GTIRef.ID, "shape_head_pickaxe", "molds/").tip("Shape for making Pickaxe Heads");
    public static ItemBasic<?> ShapeHeadShovel = new ItemBasic<>(GTIRef.ID, "shape_head_shovel", "molds/").tip("Shape for making Shovel Heads");
    public static ItemBasic<?> ShapeHeadAxe = new ItemBasic<>(GTIRef.ID, "shape_head_axe", "molds/").tip("Shape for making Axe Heads");
    public static ItemBasic<?> ShapeHeadHoe = new ItemBasic<>(GTIRef.ID, "shape_head_hoe", "molds/").tip("Shape for making Hoe Heads");
    public static ItemBasic<?> ShapeHeadHammer = new ItemBasic<>(GTIRef.ID, "shape_head_hammer", "molds/").tip("Shape for making Hammer Heads");
    public static ItemBasic<?> ShapeHeadFile = new ItemBasic<>(GTIRef.ID, "shape_head_file", "molds/").tip("Shape for making File Heads");
    public static ItemBasic<?> ShapeHeadSaw = new ItemBasic<>(GTIRef.ID, "shape_head_saw", "molds/").tip("Shape for making Saw Heads");
    public static ItemBasic<?> ShapeGear = new ItemBasic<>(GTIRef.ID, "shape_gear", "molds/").tip("Shape for making Gears");
    public static ItemBasic<?> ShapeGearSmall = new ItemBasic<>(GTIRef.ID, "shape_gear_small", "molds/").tip("Shape for making Small Gears");
    public static ItemBasic<?> NuclearWaste = new ItemBasic<>(GTIRef.ID, "nuclear_waste").tip("Common Waste extracted from depleted Fuel");
    public static ItemBasic<?> AlkalineWaste = new ItemBasic<>(GTIRef.ID, "alkaline_waste").tip("Waste containing alkaline metals");
    public static ItemBasic<?> NonmetalWaste = new ItemBasic<>(GTIRef.ID, "nonmetal_waste").tip("Waste containing non-metal substances");
    public static ItemBasic<?> MetaloidWaste = new ItemBasic<>(GTIRef.ID, "metaloid_waste").tip("Waste containing metal substances");
    public static ItemBasic<?> HeavyMetalWaste = new ItemBasic<>(GTIRef.ID, "heavy_metal_waste").tip("Waste containing heavy metal substances");
    public static ItemBasic<?> MetalGroupAWaste = new ItemBasic<>(GTIRef.ID, "metal_group_a_waste").tip("Waste containing rare metals");
    public static ItemBasic<?> MetalGroupBWaste = new ItemBasic<>(GTIRef.ID, "metal_group_b_waste").tip("Waste semi-rare metals");
    public static ItemBasic<?> MetalGroupCWaste = new ItemBasic<>(GTIRef.ID, "metal_group_c_waste").tip("Waste containing common metals");
    public static ItemBasic<?> LanthanideGroupAWaste = new ItemBasic<>(GTIRef.ID, "lanthanide_group_a_waste").tip("Waste containing rare Lanthanides");
    public static ItemBasic<?> LanthanideGroupBWaste = new ItemBasic<>(GTIRef.ID, "lanthanide_group_b_waste").tip("Waste containing semi-rare Lanthanides");
    public static ItemBasic<?> ThoriumWaste = new ItemBasic<>(GTIRef.ID, "thorium_waste").tip("Waste extracted from depleted Thorium Fuel");
    public static ItemBasic<?> ProtactiniumWaste = new ItemBasic<>(GTIRef.ID, "protactinium_waste").tip("Waste extracted from depleted Protactinium Fuel");
    public static ItemBasic<?> UraniumWaste = new ItemBasic<>(GTIRef.ID, "uranium_waste").tip("Waste extracted from depleted Uranium Fuel");
    public static ItemBasic<?> NeptuniumWaste = new ItemBasic<>(GTIRef.ID, "neptunium_waste").tip("Waste extracted from depleted Neptunium Fuel");
    public static ItemBasic<?> PlutoniumWaste = new ItemBasic<>(GTIRef.ID, "plutonium_waste").tip("Waste extracted from depleted Plutonium Fuel");
    public static ItemBasic<?> AmericiumWaste = new ItemBasic<>(GTIRef.ID, "americium_waste").tip("Waste extracted from depleted Americium Fuel");
    public static ItemBasic<?> CuriumWaste = new ItemBasic<>(GTIRef.ID, "curium_waste").tip("Waste extracted from depleted Curium Fuel");
    public static ItemBasic<?> BerkeliumWaste = new ItemBasic<>(GTIRef.ID, "berkelium_waste").tip("Waste extracted from depleted Berkelium Fuel");
    public static ItemBasic<?> CaliforniumWaste = new ItemBasic<>(GTIRef.ID, "californium_waste").tip("Waste extracted from depleted Californium Fuel");
    public static ItemBasic<?> EinsteiniumWaste = new ItemBasic<>(GTIRef.ID, "einsteinium_waste").tip("Waste extracted from depleted Einsteinium Fuel");
    public static ItemBasic<?> FermiumWaste = new ItemBasic<>(GTIRef.ID, "fermium_waste").tip("Waste extracted from depleted Fermium Fuel");
    public static ItemBasic<?> MendeleviumWaste = new ItemBasic<>(GTIRef.ID, "mendelevium_waste").tip("Waste extracted from depleted Mendelevium Fuel");


    public static StoneType GRANITE_RED = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GTIRef.ID, "red_granite", Materials.RedGranite, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(4.5F, 60.0F).setHarvestLevel(3);
    public static StoneType GRANITE_BLACK = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GTIRef.ID, "black_granite", Materials.BlackGranite, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(4.5F, 60.0F).setHarvestLevel(3);
    public static StoneType MARBLE = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GTIRef.ID, "marble", Materials.Marble, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(0.75F,7.5F);

    public static StoneType KOMATIITE = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GTIRef.ID, "komatiite", Materials.Komatiite, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(3.0F, 30.0F).setHarvestLevel(2);
    public static StoneType LIMESTONE = AntimatterAPI.register(StoneType.class,  new CobbleStoneType(GTIRef.ID, "limestone", Limestone, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(0.75F,7.5F);
    public static StoneType GREEN_SCHIST = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GTIRef.ID, "green_schist", GreenSchist, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(0.75F,7.5F);
    public static StoneType BLUE_SCHIST = AntimatterAPI.register(StoneType.class,  new CobbleStoneType(GTIRef.ID, "blue_schist", BlueSchist, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(0.75F,7.5F);
    public static StoneType KIMBERLITE = AntimatterAPI.register(StoneType.class,  new CobbleStoneType(GTIRef.ID, "kimberlite", Kimberlite, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(3.0F, 30.0F).setHarvestLevel(2);
    public static StoneType QUARTZITE = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GTIRef.ID, "quartzite", Quartzite, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(0.75F,7.5F);

    public static final LiquidBlock LAVA = AntimatterAPI.register(Block.class, "lava", GTIRef.ID, new LiquidBlock(Fluids.LAVA, BlockBehaviour.Properties.of(net.minecraft.world.level.material.Material.LAVA).noCollission().randomTicks().strength(100.0F).lightLevel((blockStatex) -> 15).noDrops()){
        @Override
        public ItemStack pickupBlock(LevelAccessor level, BlockPos pos, BlockState state) {
            return ItemStack.EMPTY;
        }
    });

    //public static BlockBasic ANTHRACITE_COAL = new BlockBasic(GTIRef.ID, "anthracite_coal", new Texture(GTIRef.ID, "block/basic/anthracite_coal");
    //public static BlockBasic ANTHRACITE_COAL = new BlockBasic(GTIRef.ID, "anthracite_coal", new Texture(GTIRef.ID, "block/basic/anthracite_coal");

    public static final BlockBasic BRITTLE_CHARCOAL = new BlockBasic(GTIRef.ID, "brittle_charcoal", BlockBehaviour.Properties.of(net.minecraft.world.level.material.Material.WOOD, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(5.0F, 6.0F)){
        @Override
        public Texture[] getTextures() {
            return new Texture[]{new Texture("minecraft:block/coal_block")};
        }
    };

    public static final BlockBasic MINING_PIPE = new BlockBasic(GTIRef.ID, "mining_pipe", BlockBehaviour.Properties.of(net.minecraft.world.level.material.Material.STONE, MaterialColor.COLOR_GRAY));
    public static final BlockCasing CASING_ULV = new BlockCasing(GTIRef.ID, "casing_ulv");
    public static final BlockCasing CASING_LV = new BlockCasing(GTIRef.ID, "casing_lv");
    public static final BlockCasing CASING_MV = new BlockCasing(GTIRef.ID, "casing_mv");
    public static final BlockCasing CASING_HV = new BlockCasing(GTIRef.ID, "casing_hv");
    public static final BlockCasing CASING_EV = new BlockCasing(GTIRef.ID, "casing_ev");
    public static final BlockCasing CASING_IV = new BlockCasing(GTIRef.ID, "casing_iv");
    public static final BlockCasing CASING_LUV = new BlockCasing(GTIRef.ID, "casing_luv");
    public static final BlockCasing CASING_ZPM = new BlockCasing(GTIRef.ID, "casing_zpm");
    public static final BlockCasing CASING_UV = new BlockCasing(GTIRef.ID, "casing_uv");
    public static final BlockCasing CASING_MAX = new BlockCasing(GTIRef.ID, "casing_max");

    public static final BlockCasing HULL_ULV = new BlockCasing(GTIRef.ID, "hull_ulv");
    public static final BlockCasing HULL_LV = new BlockCasing(GTIRef.ID, "hull_lv");
    public static final BlockCasing HULL_MV = new BlockCasing(GTIRef.ID, "hull_mv");
    public static final BlockCasing HULL_HV = new BlockCasing(GTIRef.ID, "hull_hv");
    public static final BlockCasing HULL_EV = new BlockCasing(GTIRef.ID, "hull_ev");
    public static final BlockCasing HULL_IV = new BlockCasing(GTIRef.ID, "hull_iv");
    public static final BlockCasing HULL_LUV = new BlockCasing(GTIRef.ID, "hull_luv");
    public static final BlockCasing HULL_ZPM = new BlockCasing(GTIRef.ID, "hull_zpm");
    public static final BlockCasing HULL_UV = new BlockCasing(GTIRef.ID, "hull_uv");
    public static final BlockCasing HULL_MAX = new BlockCasing(GTIRef.ID, "hull_max");

    public static final BlockFakeCasing CASING_FIRE_BRICK = new BlockFakeCasing(GTIRef.ID, "fire_bricks", BlockBehaviour.Properties.of(net.minecraft.world.level.material.Material.STONE, MaterialColor.DIRT).strength(1.0f, 10.0f).sound(SoundType.STONE));
    public static final BlockCasing CASING_BRONZE = new BlockCasing(GTIRef.ID, "bronze_casing");
    public static final BlockCasing CASING_BRICKED_BRONZE = new BlockSidedCasing(GTIRef.ID, "bricked_bronze_casing", "brick", "bronze");
    public static final BlockCasing CASING_BRONZE_PLATED_BRICK = new BlockCasing(GTIRef.ID, "bronze_plated_brick_casing");
    public static final BlockCasing CASING_STEEL = new BlockCasing(GTIRef.ID, "steel_casing");
    public static final BlockCasing CASING_BRICKED_STEEL = new BlockSidedCasing(GTIRef.ID, "bricked_steel_casing", "brick", "steel");
    public static final BlockCasing CASING_SOLID_STEEL = new BlockCasing(GTIRef.ID, "solid_steel_casing");
    public static final BlockCasing CASING_STAINLESS_STEEL = new BlockCasing(GTIRef.ID, "stainless_steel_casing");
    public static final BlockCasing CASING_TITANIUM = new BlockCasing(GTIRef.ID, "titanium_casing");
    public static final BlockCasing CASING_TUNGSTENSTEEL = new BlockCasing(GTIRef.ID, "tungstensteel_casing");
    public static final BlockCasing CASING_HEAT_PROOF = new BlockCasing(GTIRef.ID, "heat_proof_casing");
    public static final BlockCasing CASING_FROST_PROOF = new BlockCasing(GTIRef.ID, "frost_proof_casing");
    public static final BlockCasing CASING_RADIATION_PROOF = new BlockCasing(GTIRef.ID, "radiation_proof_casing");
    public static final BlockCasing CASING_FIREBOX_BRONZE = new BlockCasing(GTIRef.ID, "bronze_firebox_casing");
    public static final BlockCasing CASING_FIREBOX_STEEL = new BlockCasing(GTIRef.ID, "steel_firebox_casing");
    public static final BlockCasing CASING_FIREBOX_TITANIUM = new BlockCasing(GTIRef.ID, "titanium_firebox_casing");
    public static final BlockCasing CASING_FIREBOX_TUNGSTENSTEEL = new BlockCasing(GTIRef.ID, "tungstensteel_firebox_casing");
    public static final BlockCasing CASING_GEARBOX_BRONZE = new BlockCasing(GTIRef.ID, "bronze_gearbox_casing");
    public static final BlockCasing CASING_GEARBOX_STEEL = new BlockCasing(GTIRef.ID, "steel_gearbox_casing");
    public static final BlockCasing CASING_GEARBOX_TITANIUM = new BlockCasing(GTIRef.ID, "titanium_gearbox_casing");
    public static final BlockCasing CASING_GEARBOX_TUNGSTENSTEEL = new BlockCasing(GTIRef.ID, "tungstensteel_gearbox_casing");
    public static final BlockCasing CASING_PIPE_BRONZE = new BlockCasing(GTIRef.ID, "bronze_pipe_casing");
    public static final BlockCasing CASING_PIPE_STEEL = new BlockCasing(GTIRef.ID, "steel_pipe_casing");
    public static final BlockCasing CASING_PIPE_TITANIUM = new BlockCasing(GTIRef.ID, "titanium_pipe_casing");
    public static final BlockCasing CASING_PIPE_TUNGSTENSTEEL = new BlockCasing(GTIRef.ID, "tungstensteel_pipe_casing");
    public static final BlockCasing CASING_ENGINE_INTAKE = new BlockCasing(GTIRef.ID, "engine_intake_casing");

    public static final BlockCasing CASING_FUSION_1 = new BlockCasing(GTIRef.ID, "fusion_1");
    public static final BlockCasing CASING_FUSION_2 = new BlockCasing(GTIRef.ID, "fusion_2");

    public static final BlockCasing CASING_TURBINE_STEEL = new BlockCasing(GTIRef.ID, "steel_turbine_casing");
    public static final BlockCasing CASING_TURBINE_STAINLESS = new BlockCasing(GTIRef.ID, "stainless_steel_turbine_casing");
    public static final BlockCasing CASING_TURBINE_TITANIUM = new BlockCasing(GTIRef.ID, "titanium_turbine_casing");
    public static final BlockCasing CASING_TURBINE_TUNGSTENSTEEL = new BlockCasing(GTIRef.ID, "tungstensteel_turbine_casing");

    public static final BlockCoil COIL_CUPRONICKEL = new BlockCoil(GTIRef.ID, "cupronickel_coil", new CoilData(1800, 0.5f));
    public static final BlockCoil COIL_KANTHAL = new BlockCoil(GTIRef.ID, "kanthal_coil", new CoilData(2700, 1.0f));
    public static final BlockCoil COIL_NICHROME = new BlockCoil(GTIRef.ID, "nichrome_coil", new CoilData(3600, 1.5f));
    public static final BlockCoil COIL_TUNGSTENSTEEL = new BlockCoil(GTIRef.ID, "tungstensteel_coil", new CoilData(4500, 2.0f));
    public static final BlockCoil COIL_HSSG = new BlockCoil(GTIRef.ID, "hssg_coil", new CoilData(5400, 2.5f));
    public static final BlockCoil COIL_NAQUADAH = new BlockCoil(GTIRef.ID, "naquadah_coil", new CoilData(7200, 3.0f));
    public static final BlockCoil COIL_NAQUADAH_ALLOY = new BlockCoil(GTIRef.ID, "naquadah_alloy_coil", new CoilData(9001, 3.5f));
    public static final BlockCoil COIL_FUSION = new BlockCoil(GTIRef.ID, "fusion_coil", new CoilData(9001, 4.0f));
    public static final BlockCoil COIL_SUPERCONDUCTOR = new BlockCoil(GTIRef.ID, "superconductor_coil", new CoilData(9001, 4.0f));

    public static final Cable<?> CABLE_RED_ALLOY = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, RedAlloy, 0, Tier.ULV).amps(1));
    public static final Cable<?> CABLE_COBALT = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Cobalt, 2, Tier.LV).amps(2)); //L);
    public static final Cable<?> CABLE_LEAD = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Lead, 2, Tier.LV).amps(2));
    public static final Cable<?> CABLE_TIN = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Tin, 1, Tier.LV).amps(1));
    public static final Cable<?> CABLE_ZINC = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Zinc, 1, Tier.LV).amps(1));
    public static final Cable<?> CABLE_SOLDERING_ALLOY = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, SolderingAlloy, 1, Tier.LV).amps(1));
    public static final Cable<?> CABLE_IRON = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, AntimatterMaterials.Iron, HC ? 3 : 4, Tier.MV).amps(2)); //M);
    public static final Cable<?> CABLE_NICKEL = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Nickel, HC ? 3 : 5, Tier.MV).amps(3));
    public static final Cable<?> CABLE_CUPRONICKEL = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Cupronickel, HC ? 3 : 4, Tier.MV).amps(2));
    public static final Cable<?> CABLE_COPPER = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, AntimatterMaterials.Copper, HC ? 2 : 3, Tier.MV).amps(1));
    public static final Cable<?> CABLE_ANNEALED_COPPER = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, AnnealedCopper, HC ? 1 : 2, Tier.MV).amps(1));
    public static final Cable<?> CABLE_KANTHAL = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Kanthal, HC ? 3 : 8, Tier.HV).amps(4)); //H);
    public static final Cable<?> CABLE_GOLD = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, AntimatterMaterials.Gold, HC ? 2 : 6, Tier.HV).amps(3));
    public static final Cable<?> CABLE_ELECTRUM = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Electrum, HC ? 2 : 5, Tier.HV).amps(2));
    public static final Cable<?> CABLE_SILVER = AntimatterAPI.register(Cable.class,new Cable<>(GTIRef.ID, Silver, HC ? 1 : 4, Tier.HV).amps(1));
    public static final Cable<?> CABLE_NICHROME = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Nichrome, HC ? 4 : 32, Tier.EV).amps(3)); //E);
    public static final Cable<?> CABLE_STEEL = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Steel, HC ? 2 : 16, Tier.EV).amps(2));
    public static final Cable<?> CABLE_TITANIUM = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Titanium, HC ? 2 : 12, Tier.EV).amps(4));
    public static final Cable<?> CABLE_ALUMINIUM = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Aluminium, HC ? 1 : 8, Tier.EV).amps(1));
    public static final Cable<?> CABLE_GRAPHENE = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Graphene, HC ? 1 : 16, Tier.IV).amps(1)); //I);
    public static final Cable<?> CABLE_OSMIUM = AntimatterAPI.register(Cable.class,new Cable<>(GTIRef.ID, Osmium, HC ? 2 : 32, Tier.IV).amps(4));
    public static final Cable<?> CABLE_PLATINUM = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Platinum, HC ? 1 : 16, Tier.IV).amps(2));
    public static final Cable<?> CABLE_TUNGSTEN_STEEL = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, TungstenSteel, HC ? 1 : 14, Tier.IV).amps(3));
    public static final Cable<?> CABLE_TUNGSTEN = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Tungsten, HC ? 2 : 12, Tier.IV).amps(1));
    public static final Cable<?> CABLE_HSSG = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, HSSG, HC ? 2 : 128, Tier.LUV).amps(4)); //LU);
    public static final Cable<?> CABLE_NIOBIUM_TITANIUM = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, NiobiumTitanium, HC ? 2 : 128, Tier.LUV).amps(4));
    public static final Cable<?> CABLE_VANADIUM_GALLIUM = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, VanadiumGallium, HC ? 2 : 128, Tier.LUV).amps(4));
    public static final Cable<?> CABLE_YTTRIUM_BARIUM_CUPRATE = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, YttriumBariumCuprate, HC ? 4 : 256, Tier.LUV).amps(4));
    public static final Cable<?> CABLE_NAQUADAH = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Naquadah, HC ? 2 : 64, Tier.ZPM).amps(2)); //ZP);
    public static final Cable<?> CABLE_NAQUADAH_ALLOY = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, NaquadahAlloy, HC ? 4 : 64, Tier.ZPM).amps(2));
    public static final Cable<?> CABLE_DURANIUM = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Duranium, HC ? 8 : 64, Tier.ZPM).amps(1));
    public static final Cable<?> CABLE_SUPERCONDUCTOR = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Superconductor, 0, Tier.MAX).amps(4)); //MA);


    public static final Wire<?> WIRE_RED_ALLOY = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, RedAlloy, 1, Tier.ULV).amps(1));
    public static final Wire<?> WIRE_COBALT = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Cobalt, 4, Tier.LV).amps(2)); //L);
    public static final Wire<?> WIRE_LEAD = AntimatterAPI.register(Wire.class,  new Wire<>(GTIRef.ID, Lead, 4, Tier.LV).amps(2));
    public static final Wire<?> WIRE_TIN = AntimatterAPI.register(Wire.class,  new Wire<>(GTIRef.ID, Tin, 2, Tier.LV).amps(1));
    public static final Wire<?> WIRE_ZINC = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Zinc, 2, Tier.LV).amps(1));
    public static final Wire<?> WIRE_SOLDERING_ALLOY = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, SolderingAlloy, 2, Tier.LV).amps(1));
    public static final Wire<?> WIRE_IRON = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, AntimatterMaterials.Iron, HC ? 6 : 8, Tier.MV).amps(2)); //M);
    public static final Wire<?> WIRE_NICKEL = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Nickel, HC ? 6 : 10, Tier.MV).amps(3));
    public static final Wire<?> WIRE_CUPRONICKEL = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Cupronickel, HC ? 6 : 8, Tier.MV).amps(2));
    public static final Wire<?> WIRE_COPPER = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, AntimatterMaterials.Copper, HC ? 4 : 6, Tier.MV).amps(1));
    public static final Wire<?> WIRE_ANNEALED_COPPER = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, AnnealedCopper, HC ? 2 : 4, Tier.MV).amps(1));
    public static final Wire<?> WIRE_KANTHAL = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Kanthal, HC ? 6 : 16, Tier.HV).amps(4)); //H);
    public static final Wire<?> WIRE_GOLD = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, AntimatterMaterials.Gold, HC ? 4 : 12, Tier.HV).amps(3));
    public static final Wire<?> WIRE_ELECTRUM = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Electrum, HC ? 4 : 10, Tier.HV).amps(2));
    public static final Wire<?> WIRE_SILVER = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Silver, HC ? 2 : 8, Tier.HV).amps(1));
    public static final Wire<?> WIRE_NICHROME = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Nichrome, HC ? 8 : 64, Tier.EV).amps(3)); //E);
    public static final Wire<?> WIRE_STEEL = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Steel, HC ? 4 : 32, Tier.EV).amps(2));
    public static final Wire<?> WIRE_TITANIUM = AntimatterAPI.register(Wire.class,  new Wire<>(GTIRef.ID, Titanium, HC ? 4 : 24, Tier.EV).amps(4));
    public static final Wire<?> WIRE_ALUMINIUM = AntimatterAPI.register(Wire.class,new Wire<>(GTIRef.ID, Aluminium, HC ? 2 : 16, Tier.EV).amps(1));
    public static final Wire<?> WIRE_GRAPHENE = AntimatterAPI.register(Wire.class,  new Wire<>(GTIRef.ID, Graphene, HC ? 2 : 32, Tier.IV).amps(1)); //I);
    public static final Wire<?> WIRE_OSMIUM = AntimatterAPI.register(Wire.class,new Wire<>(GTIRef.ID, Osmium, HC ? 4 : 64, Tier.IV).amps(4));
    public static final Wire<?> WIRE_PLATINUM = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Platinum, HC ? 2 : 32, Tier.IV).amps(2));
    public static final Wire<?> WIRE_TUNGSTEN_STEEL = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, TungstenSteel, HC ? 2 : 28, Tier.IV).amps(3));
    public static final Wire<?> WIRE_TUNGSTEN = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Tungsten, HC ? 2 : 12, Tier.IV).amps(1));
    public static final Wire<?> WIRE_HSSG = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, HSSG, HC ? 4 : 256, Tier.LUV).amps(4)); //LU);
    public static final Wire<?> WIRE_NIOBIUM_TITANIUM = AntimatterAPI.register(Wire.class,new Wire<>(GTIRef.ID, NiobiumTitanium, HC ? 4 : 256, Tier.LUV).amps(4));
    public static final Wire<?> WIRE_VANADIUM_GALLIUM = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, VanadiumGallium, HC ? 4 : 256, Tier.LUV).amps(4));
    public static final Wire<?> WIRE_YTTRIUM_BARIUM_CUPRATE = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, YttriumBariumCuprate, HC ? 8 : 512, Tier.LUV).amps(4));
    public static final Wire<?> WIRE_NAQUADAH = AntimatterAPI.register(Wire.class,new Wire<>(GTIRef.ID, Naquadah, HC ? 4 : 128, Tier.ZPM).amps(2)); //ZP);
    public static final Wire<?> WIRE_NAQUADAH_ALLOY = AntimatterAPI.register(Wire.class,new Wire<>(GTIRef.ID, NaquadahAlloy, HC ? 8 : 128, Tier.ZPM).amps(2));
    public static final Wire<?> WIRE_DURANIUM = AntimatterAPI.register(Wire.class,new Wire<>(GTIRef.ID, Duranium, HC ? 16 : 128, Tier.ZPM).amps(1));
    public static final Wire<?> WIRE_SUPERCONDUCTOR = AntimatterAPI.register(Wire.class,new Wire<>(GTIRef.ID, Superconductor, 1, Tier.MAX).amps(4)); //MA);

    public static final FluidPipe<?> FLUID_PIPE_WOOD = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GTIRef.ID, AntimatterMaterials.Wood, 350, false).caps(1).pressures(getPressures(150)));
    public static final FluidPipe<?> FLUID_PIPE_COPPER = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GTIRef.ID, AntimatterMaterials.Copper, 1696, true).caps(1).pressures(getPressures(300)));
    public static final FluidPipe<?> FLUID_PIPE_BRONZE = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GTIRef.ID, Bronze, 1696, true).caps(1).pressures(getPressures(450)));
    // it's not in gt6, not sure whether to keep it or not
    //public static final FluidPipe<?> FLUID_PIPE_BISMUTH_BRONZE = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GTIRef.ID, BismuthBronze, 950, true).caps(1).pressures(800));
    //public static final FluidPipe<?> FLUID_PIPE_BLACK_STEEL = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GTIRef.ID, BlackSteel, 1200, true).caps(1).pressures(900));
    public static final FluidPipe<?> FLUID_PIPE_INVAR = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GTIRef.ID, Invar, 2395, true).caps(1).pressures(getPressures(600)));
    public static final FluidPipe<?> FLUID_PIPE_STEEL = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GTIRef.ID, Steel, 2557, true).caps(1).pressures(getPressures(600)));
    public static final FluidPipe<?> FLUID_PIPE_STAINLESS_STEEL = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GTIRef.ID, StainlessSteel, 2428, true).caps(1).pressures(getPressures(750)));
    public static final FluidPipe<?> FLUID_PIPE_NETHERRITE = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GTIRef.ID, AntimatterMaterials.Netherite, 2807, true).caps(1).pressures(getPressures(900)));
    public static final FluidPipe<?> FLUID_PIPE_TUNGSTEN = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GTIRef.ID, Tungsten, 4618, true).caps(1).pressures(getPressures(1050)));
    public static final FluidPipe<?> FLUID_PIPE_TITANIUM = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GTIRef.ID, Titanium, 1668, true).caps(1).pressures(getPressures(900)));
    public static final FluidPipe<?> FLUID_PIPE_TUNGSTEN_STEEL = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GTIRef.ID, TungstenSteel, 3587, true).caps(1).pressures(getPressures(1200)));
    public static final FluidPipe<?> FLUID_PIPE_TUNGSTEN_CARBIDE = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GTIRef.ID, TungstenCarbide, 3837, true).caps(1).pressures(getPressures(1350)));
    public static final FluidPipe<?> FLUID_PIPE_PLASTIC = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GTIRef.ID, Polyethylene, 370, true).caps(1).pressures(getPressures(300)));
    public static final FluidPipe<?> FLUID_PIPE_POLY = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GTIRef.ID, Polytetrafluoroethylene, 327, true).caps(1).pressures(getPressures(150)));
    public static final FluidPipe<?> FLUID_PIPE_HP = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GTIRef.ID, HighPressure, 3422, true).sizes(PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE).caps(1).pressures(10000));
    public static final FluidPipe<?> FLUID_PIPE_PLASMA = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GTIRef.ID, PlasmaContainment, 100000, true).sizes(PipeSize.NORMAL).caps(1).pressures(100000));

    public static final ItemPipe<?> ITEM_PIPE_BRASS = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, Brass).caps(0, 0, 0, 1, 2, 4));
    public static final ItemPipe<?> ITEM_PIPE_CUPRONICKEL = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, Cupronickel).caps(0, 0, 0, 1, 2, 4));
    public static final ItemPipe<?> ITEM_PIPE_COBALT = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, Cobalt).caps(0, 0, 0, 1, 2, 4));
    public static final ItemPipe<?> ITEM_PIPE_BLACK_BRONZE = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, BlackBronze).caps(0, 0, 0, 2, 4, 8));
    public static final ItemPipe<?> ITEM_PIPE_STERLING_SILVER = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, SterlingSilver).caps(0,0,0,2,4,8));
    public static final ItemPipe<?> ITEM_PIPE_ROSE_GOLD = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, RoseGold).caps(0,0,0,2,4,8));
    public static final ItemPipe<?> ITEM_PIPE_ELECTRUM = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, Electrum).caps(0, 0, 0, 2, 4, 8));
    public static final ItemPipe<?> ITEM_PIPE_MAGNALIUM = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, Magnalium).caps(0, 0, 0, 2, 4, 8));
    public static final ItemPipe<?> ITEM_PIPE_PLATINUM = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, Platinum).caps(0, 0, 0, 4, 8, 16));
    public static final ItemPipe<?> ITEM_PIPE_OSMIUM = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, Osmium).caps(0, 0, 0, 8, 16, 32));
    public static final ItemPipe<?> ITEM_PIPE_ULTIMET = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, Ultimet).caps(0, 0, 0, 16, 32, 64));
    public static final ItemPipe<?> ITEM_PIPE_HC = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, HighCapacity).caps(64));
    public static final ItemPipe<?> ITEM_PIPE_OSMIRIDIUM = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, Osmiridium).caps(0, 0, 0, 32, 64, 128));

    //Miscellaneous
    public static ItemBasic<?> PlantBall = new ItemBasic<>(GTIRef.ID, "plant_ball");

    //public static final HeatPipe<?> HEAT_PIPE_COPPER = AntimatterAPI.register(HeatPipe.class, new HeatPipe<>(GTIRef.ID, Copper, 386).sizes(PipeSize.SMALL));
    private static int[] getPressures(int basePressure){
        basePressure *= 20;
        return new int[]{basePressure / 6, basePressure / 6, basePressure / 3, basePressure, basePressure * 2, basePressure * 4};
    }
}
