package muramasa.gregtech.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.Ref;
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
import muramasa.gregtech.cover.*;
import net.minecraft.world.level.block.SoundType;

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

    public static final CoverFactory COVER_STEAM_VENT = CoverFactory.builder(CoverSteamVent::new)
            .addTextures(new Texture(GTIRef.ID, "block/cover/output")).build(Ref.ID, "steam_vent");;
    public static ItemBasic<?> ComputerMonitor = new ItemBasic<>(GTIRef.ID, "computer_monitor").tip("Can be placed on machines as a cover");

    public static ItemFluidCell CellTin = new ItemFluidCell(GTIRef.ID, Tin, 1000);
    public static ItemFluidCell CellSteel = new ItemFluidCell(GTIRef.ID, Steel, 16000);
    public static ItemFluidCell CellTungstensteel = new ItemFluidCell(GTIRef.ID, TungstenSteel, 64000);

    public static ItemBasic<?> Biochaff = new ItemBasic<>(GTIRef.ID, "biochaff");

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
    public static ItemBasic<?> AdvCircuitParts = new ItemBasic<>(GTIRef.ID, "adv_circuit_parts").tip("Used for making Advanced Circuits");
    public static ItemBasic<?> EtchedWiringMV = new ItemBasic<>(GTIRef.ID, "etched_wiring_mv").tip("Circuit board parts");
    public static ItemBasic<?> EtchedWiringHV = new ItemBasic<>(GTIRef.ID, "etched_wiring_hv").tip("Circuit board parts");
    public static ItemBasic<?> EtchedWiringEV = new ItemBasic<>(GTIRef.ID, "etched_wiring_ev").tip("Circuit board parts");
    public static ItemBasic<?> EngravedCrystalChip = new ItemBasic<>(GTIRef.ID, "engraved_crystal_chip").tip("Needed for Circuits");
    public static ItemBasic<?> EngravedLapotronChip = new ItemBasic<>(GTIRef.ID, "engraved_lapotron_chip").tip("Needed for Circuits");

    public static ItemBasic<?> CircuitBoardCoated = new ItemBasic<>(GTIRef.ID, "board_coated").tip("The most basic Board");
    public static ItemBasic<?> CircuitBoardPhenolic = new ItemBasic<>(GTIRef.ID, "board_phenolic").tip("A basic Board");
    public static ItemBasic<?> CircuitBoardPlastic = new ItemBasic<>(GTIRef.ID, "board_plastic").tip("An advanced Board");
    public static ItemBasic<?> CircuitBoardEpoxy = new ItemBasic<>(GTIRef.ID, "board_epoxy").tip("4th Tier Board");
    public static ItemBasic<?> CircuitBoardFiber = new ItemBasic<>(GTIRef.ID, "board_fiber_reinforced").tip("5th Tier Board");
    public static ItemBasic<?> CircuitBoardMultiFiber = new ItemBasic<>(GTIRef.ID, "board_multilayer_fiber_reinforced").tip("6th Tier Board");
    public static ItemBasic<?> CircuitBoardWetware = new ItemBasic<>(GTIRef.ID, "board_wetware").tip("7th Tier Board");

    public static ItemBasic<?> CircuitBasic = new ItemBasic<>(GTIRef.ID, "circuit_basic").tip("The breakthrough to the electrical age");
    public static ItemBasic<?> CircuitBasicElectronic = new ItemBasic<>(GTIRef.ID, "circuit_basic_electronic").tip("A basic Circuit");
    public static ItemBasic<?> CircuitGood = new ItemBasic<>(GTIRef.ID, "circuit_good").tip("A good Circuit");
    public static ItemBasic<?> CircuitAdv = new ItemBasic<>(GTIRef.ID, "circuit_adv").tip("An advanced Circuit");
    public static ItemBasic<?> CircuitNanoProcessor = new ItemBasic<>(GTIRef.ID, "circuit_nanoprocessor").tip("A Nanoprocessor");
    public static ItemBasic<?> CircuitQuantumProcessor = new ItemBasic<>(GTIRef.ID, "circuit_quantumprocessor").tip("A Quantumprocessor");
    public static ItemBasic<?> CircuitEnergyFlow = new ItemBasic<>(GTIRef.ID, "circuit_energy_flow").tip("A High Voltage Processor");
    public static ItemBasic<?> CircuitWetware = new ItemBasic<>(GTIRef.ID, "circuit_wetware").tip("You feel like it's watching you");

    public static ItemBasic<?> Transistor = new ItemBasic<>(GTIRef.ID, "transistor").tip("An integral part to Circuitry");
    public static ItemBasic<?> Resistor = new ItemBasic<>(GTIRef.ID, "resistor").tip("An integral part to Circuitry");
    public static ItemBasic<?> Capacitor = new ItemBasic<>(GTIRef.ID, "capacitor").tip("An integral part to Circuitry");
    public static ItemBasic<?> VacuumTube = new ItemBasic<>(GTIRef.ID, "vacuumtube").tip("A part for the Basic Circuit");

    public static ItemBasic<?> BatteryTantalum = new ItemBattery(GTIRef.ID, "battery_tantalum", Tier.ULV, 10000, true).tip("Reusable");
    public static ItemBasic<?> BatteryHullSmall = new ItemBasic<>(GTIRef.ID, "battery_hull_small").tip("An empty LV Battery Hull");
    public static ItemBasic<?> BatteryHullMedium = new ItemBasic<>(GTIRef.ID, "battery_hull_medium").tip("An empty MV Battery Hull");
    public static ItemBasic<?> BatteryHullLarge = new ItemBasic<>(GTIRef.ID, "battery_hull_large").tip("An empty HV Battery Hull");
    public static ItemBasic<?> BatterySmallAcid = new ItemMultiTextureBattery(GTIRef.ID, "battery_small_acid", Tier.LV, 50000, false).tip("Single Use");
    public static ItemBasic<?> BatterySmallMercury = new ItemMultiTextureBattery(GTIRef.ID, "battery_small_mercury", Tier.LV, 100000, false).tip("Single Use");
    public static ItemBasic<?> BatterySmallCadmium = new ItemMultiTextureBattery(GTIRef.ID, "battery_small_cadmium", Tier.LV,75000, true).tip("Reusable");
    public static ItemBasic<?> BatterySmallLithium = new ItemMultiTextureBattery(GTIRef.ID, "battery_small_lithium", Tier.LV, 100000, true).tip("Reusable");
    public static ItemBasic<?> BatterySmallSodium = new ItemMultiTextureBattery(GTIRef.ID, "battery_small_sodium", Tier.LV, 50000, true).tip("Reusable");
    public static ItemBasic<?> BatteryMediumAcid = new ItemMultiTextureBattery(GTIRef.ID, "battery_medium_acid", Tier.MV, 200000, false).tip("Single Use");
    public static ItemBasic<?> BatteryMediumMercury = new ItemMultiTextureBattery(GTIRef.ID, "battery_medium_mercury", Tier.MV, 400000, false).tip("Single Use");
    public static ItemBasic<?> BatteryMediumCadmium = new ItemMultiTextureBattery(GTIRef.ID, "battery_medium_cadmium", Tier.MV, 300000, true).tip("Reusable");
    public static ItemBasic<?> BatteryMediumLithium = new ItemMultiTextureBattery(GTIRef.ID, "battery_medium_lithium", Tier.MV, 400000, true).tip("Reusable");
    public static ItemBasic<?> BatteryMediumSodium = new ItemMultiTextureBattery(GTIRef.ID, "battery_medium_sodium", Tier.MV,200000, true).tip("Reusable");
    public static ItemBasic<?> BatteryLargeAcid = new ItemMultiTextureBattery(GTIRef.ID, "battery_large_acid", Tier.HV, 800000, false).tip("Single Use");
    public static ItemBasic<?> BatteryLargeMercury = new ItemMultiTextureBattery(GTIRef.ID, "battery_large_mercury", Tier.HV, 1600000, false).tip("Single Use");
    public static ItemBasic<?> BatteryLargeCadmium = new ItemMultiTextureBattery(GTIRef.ID, "battery_large_cadmium", Tier.HV, 1200000, true).tip("Reusable");
    public static ItemBasic<?> BatteryLargeLithium = new ItemMultiTextureBattery(GTIRef.ID, "battery_large_lithium", Tier.HV, 1600000, true).tip("Reusable");
    public static ItemBasic<?> BatteryLargeSodium = new ItemMultiTextureBattery(GTIRef.ID, "battery_large_sodium", Tier.HV, 800000, true).tip("Reusable");
    public static ItemBasic<?> BatteryEnergyOrb = new ItemBasic<>(GTIRef.ID, "battery_energy_orb");
    public static ItemBasic<?> BatteryEnergyOrbCluster = new ItemBasic<>(GTIRef.ID, "battery_energy_orb_cluster");

    public static ItemBasic<?> EmptyShape = new ItemBasic<>(GTIRef.ID, "empty_shape_plate").tip("Raw plate to make Molds and Extruder Shapes");
    public static ItemBasic<?> MoldPlate = new ItemBasic<>(GTIRef.ID, "mold_plate").tip("Mold for making Plates");
    public static ItemBasic<?> MoldGear = new ItemBasic<>(GTIRef.ID, "mold_gear").tip("Mold for making Gears");
    public static ItemBasic<?> MoldGearSmall = new ItemBasic<>(GTIRef.ID, "mold_small_gear").tip("Mold for making Small Gears");
    public static ItemBasic<?> MoldCoinage = new ItemBasic<>(GTIRef.ID, "mold_coinage").tip("Secure Mold for making Coins (Don't lose it!)");
    public static ItemBasic<?> MoldBottle = new ItemBasic<>(GTIRef.ID, "mold_bottle").tip("Mold for making Bottles");
    public static ItemBasic<?> MoldIngot = new ItemBasic<>(GTIRef.ID, "mold_ingot").tip("Mold for making Ingots");
    public static ItemBasic<?> MoldBall = new ItemBasic<>(GTIRef.ID, "mold_ball").tip("Mold for making Balls");
    public static ItemBasic<?> MoldBlock = new ItemBasic<>(GTIRef.ID, "mold_block").tip("Mold for making Blocks");
    public static ItemBasic<?> MoldNugget = new ItemBasic<>(GTIRef.ID, "mold_nugget").tip("Mold for making Nuggets");
    public static ItemBasic<?> MoldAnvil = new ItemBasic<>(GTIRef.ID, "mold_anvil").tip("Mold for making Anvils");
    public static ItemBasic<?> ShapePlate = new ItemBasic<>(GTIRef.ID, "shape_plate").tip("Shape for making Plates");
    public static ItemBasic<?> ShapeRod = new ItemBasic<>(GTIRef.ID, "shape_rod").tip("Shape for making Rods");
    public static ItemBasic<?> ShapeBolt = new ItemBasic<>(GTIRef.ID, "shape_bolt").tip("Shape for making Bolts");
    public static ItemBasic<?> ShapeRing = new ItemBasic<>(GTIRef.ID, "shape_ring").tip("Shape for making Rings");
    public static ItemBasic<?> ShapeCell = new ItemBasic<>(GTIRef.ID, "shape_cell").tip("Shape for making Cells");
    public static ItemBasic<?> ShapeIngot = new ItemBasic<>(GTIRef.ID, "shape_ingot").tip("Shape for making Ingots");
    public static ItemBasic<?> ShapeWire = new ItemBasic<>(GTIRef.ID, "shape_wire").tip("Shape for making Wires");
    public static ItemBasic<?> ShapePipeTiny = new ItemBasic<>(GTIRef.ID, "shape_pipe_tiny").tip("Shape for making Tiny Pipes");
    public static ItemBasic<?> ShapePipeSmall = new ItemBasic<>(GTIRef.ID, "shape_pipe_small").tip("Shape for making Small Pipes");
    public static ItemBasic<?> ShapePipeNormal = new ItemBasic<>(GTIRef.ID, "shape_pipe_normal").tip("Shape for making Normal Pipes");
    public static ItemBasic<?> ShapePipeLarge = new ItemBasic<>(GTIRef.ID, "shape_pipe_large").tip("Shape for making Large Pipes");
    public static ItemBasic<?> ShapePipeHuge = new ItemBasic<>(GTIRef.ID, "shape_pipe_huge").tip("Shape for making Huge Pipes");
    public static ItemBasic<?> ShapeBlock = new ItemBasic<>(GTIRef.ID, "shape_block").tip("Shape for making Blocks");
    public static ItemBasic<?> ShapeHeadSword = new ItemBasic<>(GTIRef.ID, "shape_head_sword").tip("Shape for making Sword Blades");
    public static ItemBasic<?> ShapeHeadPickaxe = new ItemBasic<>(GTIRef.ID, "shape_head_pickaxe").tip("Shape for making Pickaxe Heads");
    public static ItemBasic<?> ShapeHeadShovel = new ItemBasic<>(GTIRef.ID, "shape_head_shovel").tip("Shape for making Shovel Heads");
    public static ItemBasic<?> ShapeHeadAxe = new ItemBasic<>(GTIRef.ID, "shape_head_axe").tip("Shape for making Axe Heads");
    public static ItemBasic<?> ShapeHeadHoe = new ItemBasic<>(GTIRef.ID, "shape_head_hoe").tip("Shape for making Hoe Heads");
    public static ItemBasic<?> ShapeHeadHammer = new ItemBasic<>(GTIRef.ID, "shape_head_hammer").tip("Shape for making Hammer Heads");
    public static ItemBasic<?> ShapeHeadFile = new ItemBasic<>(GTIRef.ID, "shape_head_file").tip("Shape for making File Heads");
    public static ItemBasic<?> ShapeHeadSaw = new ItemBasic<>(GTIRef.ID, "shape_head_saw").tip("Shape for making Saw Heads");
    public static ItemBasic<?> ShapeGear = new ItemBasic<>(GTIRef.ID, "shape_gear").tip("Shape for making Gears");
    public static ItemBasic<?> ShapeGearSmall = new ItemBasic<>(GTIRef.ID, "shape_gear_small").tip("Shape for making Small Gears");
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
    //
    //    //TODO optional items (register anyway, but don't show in JEI?)
    //    //TODO move to IC2+IC2C Registrar
    //    public static final RegistryObject<Item> DropTin = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_tin", "Source of Tin")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropLead = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_lead", "Source of Lead")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropSilver = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_silver", "Source of Silver")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropIron = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_iron", "Source of Iron")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropGold = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_gold", "Source of Gold")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropAluminium = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_aluminium", "Source of Aluminium")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropTitanium = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_titanium", "Source of Titanium")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropUranium = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_uranium", "Source of Uranium")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropUranite = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_uranite", "Source of Uranite")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropThorium = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_thorium", "Source of Thorium")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropNickel = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_nickel", "Source of Nickel")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropZinc = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_zinc", "Source of Zinc")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropManganese = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_manganese", "Source of Manganese")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropCopper = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_copper", "Source of Copper")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropTungsten = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_tungsten", "Source of Tungsten")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropPlatinum = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_platinum", "Source of Platinum")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropIridium = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_iridium", "Source of Iridium")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropOsmium = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_osmium", "Source of Osmium")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropNaquadah = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_naquadah", "Source of Naquadah")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropEmerald = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_emerald", "Source of Emeralds")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropOil = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_oil", "Source of Oil")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropUUM = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_uum", "Source of UU Matter")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //    public static final RegistryObject<Item> DropUUA = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "drop_uua", "Source of UU Amplifier")/*.optional(GTIRef.MOD_IC2, GTIRef.MOD_IC2C)*/;
    //
    //    //TODO move to Forestry Registrar
    //    public static final RegistryObject<Item> CombLignite = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_lignite", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombCoal = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_coal", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombResin = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_resin", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombOil = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_oil", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombStone = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_stone", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombCertus = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_certus", "")/*.required(GTIRef.MOD_FR, GTIRef.MOD_AE)*/;
    //    public static final RegistryObject<Item> CombRedstone = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_redstone", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombLapis = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_lapis", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombRuby = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_ruby", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombSapphire = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_sapphire", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombDiamond = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_diamond", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombOlivine = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_olivine", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombEmerald = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_emerald", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombSlag = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_slag", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombCopper = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_copper", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombTin = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_tin", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombLead = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_lead", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombIron = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_iron", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombSteel = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_steel", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombNickel = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_nickel", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombZinc = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_zinc", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombSilver = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_silver", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombGold = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_gold", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombAluminium = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_aluminium", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombManganese = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_manganese", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombTitanium = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_titanium", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombChrome = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_chrome", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombTungsten = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_tungsten", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombPlatinum = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_platinum", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombIridium = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_iridium", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombUranium = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_uranium", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombPlutonium = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_plutonium", "")/*.optional(GTIRef.MOD_FR)*/;
    //    public static final RegistryObject<Item> CombNaquadah = new ItemBasic<>(GTIRef.ID, "").tip(GTIRef.ID, "comb_naquadah", "")/*.optional(GTIRef.MOD_FR)*/;


    public static StoneType GRANITE_RED = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GTIRef.ID, "red_granite", Materials.RedGranite, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(4.5F, 60.0F).setHarvestLevel(3);
    public static StoneType GRANITE_BLACK = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GTIRef.ID, "black_granite", Materials.BlackGranite, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(4.5F, 60.0F).setHarvestLevel(3);
    public static StoneType MARBLE = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GTIRef.ID, "marble", Materials.Marble, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(0.75F,7.5F);

    public static StoneType KOMATIITE = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GTIRef.ID, "komatiite", Materials.Komatiite, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(3.0F, 30.0F).setHarvestLevel(2);
    public static StoneType LIMESTONE = AntimatterAPI.register(StoneType.class,  new CobbleStoneType(GTIRef.ID, "limestone", Limestone, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(0.75F,7.5F);
    public static StoneType GREEN_SCHIST = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GTIRef.ID, "green_schist", GreenSchist, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(0.75F,7.5F);
    public static StoneType BLUE_SCHIST = AntimatterAPI.register(StoneType.class,  new CobbleStoneType(GTIRef.ID, "blue_schist", BlueSchist, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(0.75F,7.5F);
    public static StoneType KIMBERLITE = AntimatterAPI.register(StoneType.class,  new CobbleStoneType(GTIRef.ID, "kimberlite", Kimberlite, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(3.0F, 30.0F).setHarvestLevel(2);
    public static StoneType QUARTZITE = AntimatterAPI.register(StoneType.class, new CobbleStoneType(GTIRef.ID, "quartzite", Quartzite, "block/stone/", SoundType.STONE, true)).setHardnessAndResistance(0.75F,7.5F);

    //public static BlockBasic ANTHRACITE_COAL = new BlockBasic(GTIRef.ID, "anthracite_coal", new Texture(GTIRef.ID, "block/basic/anthracite_coal");
    //public static BlockBasic ANTHRACITE_COAL = new BlockBasic(GTIRef.ID, "anthracite_coal", new Texture(GTIRef.ID, "block/basic/anthracite_coal");
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

    public static final BlockCasing CASING_FIRE_BRICK = new BlockCasing(GTIRef.ID, "fire_bricks");
    public static final BlockCasing CASING_BRONZE = new BlockCasing(GTIRef.ID, "casing_bronze");
    public static final BlockCasing CASING_BRICKED_BRONZE = new BlockSidedCasing(GTIRef.ID, "casing_bricked_bronze", "brick", "bronze");
    public static final BlockCasing CASING_BRONZE_PLATED_BRICK = new BlockCasing(GTIRef.ID, "casing_bronze_plated_brick");
    public static final BlockCasing CASING_STEEL = new BlockCasing(GTIRef.ID, "casing_steel");
    public static final BlockCasing CASING_BRICKED_STEEL = new BlockSidedCasing(GTIRef.ID, "casing_bricked_steel", "brick", "steel");
    public static final BlockCasing CASING_SOLID_STEEL = new BlockCasing(GTIRef.ID, "casing_solid_steel");
    public static final BlockCasing CASING_STAINLESS_STEEL = new BlockCasing(GTIRef.ID, "casing_stainless_steel");
    public static final BlockCasing CASING_TITANIUM = new BlockCasing(GTIRef.ID, "casing_titanium");
    public static final BlockCasing CASING_TUNGSTENSTEEL = new BlockCasing(GTIRef.ID, "casing_tungstensteel");
    public static final BlockCasing CASING_HEAT_PROOF = new BlockCasing(GTIRef.ID, "casing_heat_proof");
    public static final BlockCasing CASING_FROST_PROOF = new BlockCasing(GTIRef.ID, "casing_frost_proof");
    public static final BlockCasing CASING_RADIATION_PROOF = new BlockCasing(GTIRef.ID, "casing_radiation_proof");
    public static final BlockCasing CASING_FIREBOX_BRONZE = new BlockCasing(GTIRef.ID, "casing_firebox_bronze");
    public static final BlockCasing CASING_FIREBOX_STEEL = new BlockCasing(GTIRef.ID, "casing_firebox_steel");
    public static final BlockCasing CASING_FIREBOX_TITANIUM = new BlockCasing(GTIRef.ID, "casing_firebox_titanium");
    public static final BlockCasing CASING_FIREBOX_TUNGSTENSTEEL = new BlockCasing(GTIRef.ID, "casing_firebox_tungstensteel");
    public static final BlockCasing CASING_GEARBOX_BRONZE = new BlockCasing(GTIRef.ID, "casing_gearbox_bronze");
    public static final BlockCasing CASING_GEARBOX_STEEL = new BlockCasing(GTIRef.ID, "casing_gearbox_steel");
    public static final BlockCasing CASING_GEARBOX_TITANIUM = new BlockCasing(GTIRef.ID, "casing_gearbox_titanium");
    public static final BlockCasing CASING_GEARBOX_TUNGSTENSTEEL = new BlockCasing(GTIRef.ID, "casing_gearbox_tungstensteel");
    public static final BlockCasing CASING_PIPE_BRONZE = new BlockCasing(GTIRef.ID, "casing_pipe_bronze");
    public static final BlockCasing CASING_PIPE_STEEL = new BlockCasing(GTIRef.ID, "casing_pipe_steel");
    public static final BlockCasing CASING_PIPE_TITANIUM = new BlockCasing(GTIRef.ID, "casing_pipe_titanium");
    public static final BlockCasing CASING_PIPE_TUNGSTENSTEEL = new BlockCasing(GTIRef.ID, "casing_pipe_tungstensteel");
    public static final BlockCasing CASING_ENGINE_INTAKE = new BlockCasing(GTIRef.ID, "casing_engine_intake");

    public static final BlockCasing CASING_FUSION_1 = new BlockCasing(GTIRef.ID, "fusion_1");
    public static final BlockCasing CASING_FUSION_2 = new BlockCasing(GTIRef.ID, "fusion_2");
    //public static final BlockCasing CASING_FUSION_3 = new BlockCasing(GTIRef.ID, "fusion_3");

    public static final BlockCasing CASING_TURBINE_1 = new BlockCasing(GTIRef.ID, "casing_turbine_1");
    public static final BlockCasing CASING_TURBINE_2 = new BlockCasing(GTIRef.ID, "casing_turbine_2");
    public static final BlockCasing CASING_TURBINE_3 = new BlockCasing(GTIRef.ID, "casing_turbine_3");
    public static final BlockCasing CASING_TURBINE_4 = new BlockCasing(GTIRef.ID, "casing_turbine_4");

    public static final BlockCoil COIL_CUPRONICKEL = new BlockCoil(GTIRef.ID, "coil_cupronickel", 113); //1808
    public static final BlockCoil COIL_KANTHAL = new BlockCoil(GTIRef.ID, "coil_kanthal", 169); //2704
    public static final BlockCoil COIL_NICHROME = new BlockCoil(GTIRef.ID, "coil_nichrome", 225); //3600
    public static final BlockCoil COIL_TUNGSTENSTEEL = new BlockCoil(GTIRef.ID, "coil_tungstensteel", 282); //4512
    public static final BlockCoil COIL_HSSG = new BlockCoil(GTIRef.ID, "coil_hssg", 338); //5408
    public static final BlockCoil COIL_NAQUADAH = new BlockCoil(GTIRef.ID, "coil_naquadah", 450); //7200
    public static final BlockCoil COIL_NAQUADAH_ALLOY = new BlockCoil(GTIRef.ID, "coil_naquadah_alloy", 563); //9008
    public static final BlockCoil COIL_FUSION = new BlockCoil(GTIRef.ID, "coil_fusion", 563); //9008
    public static final BlockCoil COIL_SUPERCONDUCTOR = new BlockCoil(GTIRef.ID, "coil_superconductor", 563); //9008

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
