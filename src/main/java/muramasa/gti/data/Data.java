package muramasa.gti.data;

import muramasa.antimatter.Configs;
import muramasa.antimatter.block.BlockCasing;
import muramasa.antimatter.block.BlockCasingMachine;
import muramasa.antimatter.block.BlockCoil;
import muramasa.antimatter.cover.Cover;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.pipe.BlockCable.BlockCableBuilder;
import muramasa.antimatter.pipe.BlockFluidPipe.BlockFluidPipeBuilder;
import muramasa.antimatter.pipe.BlockItemPipe.BlockItemPipeBuilder;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.texture.Texture;
import muramasa.gti.Ref;
import muramasa.gti.block.BlockFusionCasing;
import muramasa.gti.block.BlockTurbineCasing;
import muramasa.gti.cover.CoverConveyor;
import muramasa.gti.cover.CoverPlate;
import muramasa.gti.cover.CoverPump;
import muramasa.gti.tree.BlockRubberLeaves;
import muramasa.gti.tree.BlockRubberLog;
import muramasa.gti.tree.BlockRubberSapling;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static muramasa.gti.data.Materials.*;

public class Data {

    private static boolean HC = Configs.GAMEPLAY.HARDCORE_CABLES;
    
    static DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Ref.ID);
    static DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Ref.ID);

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
        BLOCKS.register(bus);
    }
    
    public static void init() {

    }

    public static final Cover COVER_PLATE = new CoverPlate();
    public static final Cover COVER_CONVEYOR = new CoverConveyor(Tier.LV);
    public static final Cover COVER_PUMP = new CoverPump(Tier.LV);

    public static final RegistryObject<Item> StickyResin = ITEMS.register("sticky_resin", ItemBasic::new);
    public static final RegistryObject<Item> ComputerMonitor = ITEMS.register("computer_monitor", () -> new ItemBasic("Can be placed on machines as a cover"));

    //public static ItemFluidCell CellTin = new ItemFluidCell(Tin, 1000));
    //public static ItemFluidCell CellSteel = new ItemFluidCell(Steel, 16000));
    //public static ItemFluidCell CellTungstensteel = new ItemFluidCell(TungstenSteel, 64000));

    public static final RegistryObject<Item> ItemFilter = ITEMS.register("item_filter", ItemBasic::new);
    public static final RegistryObject<Item> DiamondSawBlade = ITEMS.register("diamond_saw_blade", ItemBasic::new);
    public static final RegistryObject<Item> DiamondGrindHead = ITEMS.register("diamond_grind_head", ItemBasic::new);
    public static final RegistryObject<Item> TungstenGrindHead = ITEMS.register("tungsten_grind_head", ItemBasic::new);
    public static final RegistryObject<Item> IridiumAlloyIngot = ITEMS.register("iridium_alloy_ingot", () -> new ItemBasic("Used to make Iridium Plates"));
    public static final RegistryObject<Item> IridiumReinforcedPlate = ITEMS.register("iridium_reinforced_plate", () -> new ItemBasic("GT2s Most Expensive Component"));
    public static final RegistryObject<Item> IridiumNeutronReflector = ITEMS.register("iridium_neutron_reflector", () -> new ItemBasic("Indestructible"));
    public static final RegistryObject<Item> QuantumEye = ITEMS.register("quantum_eye", () -> new ItemBasic("Improved Ender Eye"));
    public static final RegistryObject<Item> QuantumStat = ITEMS.register("quantum_star", () -> new ItemBasic("Improved Nether Star"));
    public static final RegistryObject<Item> GraviStar = ITEMS.register("gravi_star", () -> new ItemBasic("Ultimate Nether Star"));

    public static final RegistryObject<Item> MotorLV = ITEMS.register("motor_lv", ItemBasic::new);
    public static final RegistryObject<Item> MotorMV = ITEMS.register("motor_mv", ItemBasic::new);
    public static final RegistryObject<Item> MotorHV = ITEMS.register("motor_hv", ItemBasic::new);
    public static final RegistryObject<Item> MotorEV = ITEMS.register("motor_ev", ItemBasic::new);
    public static final RegistryObject<Item> MotorIV = ITEMS.register("motor_iv", ItemBasic::new);
    public static final RegistryObject<Item> PumpLV = ITEMS.register("pump_lv", () -> new ItemBasic("640 L/s (as Cover)"));
    public static final RegistryObject<Item> PumpMV = ITEMS.register("pump_mv", () -> new ItemBasic("2,560 L/s (as Cover)"));
    public static final RegistryObject<Item> PumpHV = ITEMS.register("pump_hv", () -> new ItemBasic("10,240 L/s (as Cover)"));
    public static final RegistryObject<Item> PumpEV = ITEMS.register("pump_ev", () -> new ItemBasic("40,960 L/s (as Cover)"));
    public static final RegistryObject<Item> PumpIV = ITEMS.register("pump_iv", () -> new ItemBasic("163,840 L/s (as Cover)"));
    public static final RegistryObject<Item> FluidRegulatorLV = ITEMS.register("fluid_regulator_lv", () -> new ItemBasic("Configurable up to 640 L/s (as Cover)"));
    public static final RegistryObject<Item> FluidRegulatorMV = ITEMS.register("fluid_regulator_mv", () -> new ItemBasic("Configurable up to 2,560 L/s (as Cover)"));
    public static final RegistryObject<Item> FluidRegulatorHV = ITEMS.register("fluid_regulator_hv", () -> new ItemBasic("Configurable up to 10,240 L/s (as Cover)"));
    public static final RegistryObject<Item> FluidRegulatorEV = ITEMS.register("fluid_regulator_ev", () -> new ItemBasic("Configurable up to 40,960 L/s (as Cover)"));
    public static final RegistryObject<Item> FluidRegulatorIV = ITEMS.register("fluid_regulator_iv", () -> new ItemBasic("Configurable up to 163,840 L/s (as Cover)"));
    public static final RegistryObject<Item> ConveyorLV = ITEMS.register("conveyor_lv", () -> new ItemBasic("1 Stack every 20s (as Cover)"));
    public static final RegistryObject<Item> ConveyorMV = ITEMS.register("conveyor_mv", () -> new ItemBasic("1 Stack every 5s (as Cover)"));
    public static final RegistryObject<Item> ConveyorHV = ITEMS.register("conveyor_hv", () -> new ItemBasic("1 Stack every 1s (as Cover)"));
    public static final RegistryObject<Item> ConveyorEV = ITEMS.register("conveyor_ev", () -> new ItemBasic("1 Stack every 0.5s (as Cover)"));
    public static final RegistryObject<Item> ConveyorIV = ITEMS.register("conveyor_iv", () -> new ItemBasic("1 Stack every 0.05s (as Cover)"));
    public static final RegistryObject<Item> PistonLV = ITEMS.register("piston_lv", ItemBasic::new);
    public static final RegistryObject<Item> PistonMV = ITEMS.register("piston_mv", ItemBasic::new);
    public static final RegistryObject<Item> PistonHV = ITEMS.register("piston_hv", ItemBasic::new);
    public static final RegistryObject<Item> PistonEV = ITEMS.register("piston_ev", ItemBasic::new);
    public static final RegistryObject<Item> PistonIV = ITEMS.register("piston_iv", ItemBasic::new);
    public static final RegistryObject<Item> RobotArmLV = ITEMS.register("robot_arm_lv", () -> new ItemBasic("Insets into specific Slots (as Cover)"));
    public static final RegistryObject<Item> RobotArmMV = ITEMS.register("robot_arm_mv", () -> new ItemBasic("Insets into specific Slots (as Cover)"));
    public static final RegistryObject<Item> RobotArmHV = ITEMS.register("robot_arm_hv", () -> new ItemBasic("Insets into specific Slots (as Cover)"));
    public static final RegistryObject<Item> RobotArmEV = ITEMS.register("robot_arm_ev", () -> new ItemBasic("Insets into specific Slots (as Cover)"));
    public static final RegistryObject<Item> RobotArmIV = ITEMS.register("robot_arm_iv", () -> new ItemBasic("Insets into specific Slots (as Cover)"));
    public static final RegistryObject<Item> FieldGenLV = ITEMS.register("field_gen_lv", ItemBasic::new);
    public static final RegistryObject<Item> FieldGenMV = ITEMS.register("field_gen_mv", ItemBasic::new);
    public static final RegistryObject<Item> FieldGenHV = ITEMS.register("field_gen_hv", ItemBasic::new);
    public static final RegistryObject<Item> FieldGenEV = ITEMS.register("field_gen_ev", ItemBasic::new);
    public static final RegistryObject<Item> FieldGenIV = ITEMS.register("field_gen_iv", ItemBasic::new);
    public static final RegistryObject<Item> EmitterLV = ITEMS.register("emitter_lv", ItemBasic::new);
    public static final RegistryObject<Item> EmitterMV = ITEMS.register("emitter_mv", ItemBasic::new);
    public static final RegistryObject<Item> EmitterHV = ITEMS.register("emitter_hv", ItemBasic::new);
    public static final RegistryObject<Item> EmitterEV = ITEMS.register("emitter_ev", ItemBasic::new);
    public static final RegistryObject<Item> EmitterIV = ITEMS.register("emitter_iv", ItemBasic::new);
    public static final RegistryObject<Item> SensorLV = ITEMS.register("sensor_lv", ItemBasic::new);
    public static final RegistryObject<Item> SensorMV = ITEMS.register("sensor_mv", ItemBasic::new);
    public static final RegistryObject<Item> SensorHV = ITEMS.register("sensor_hv", ItemBasic::new);
    public static final RegistryObject<Item> SensorEV = ITEMS.register("sensor_ev", ItemBasic::new);
    public static final RegistryObject<Item> SensorIV = ITEMS.register("sensor_iv", ItemBasic::new);

    public static final RegistryObject<Item> NandChip = ITEMS.register("nand_chip", () -> new ItemBasic("A very simple circuit"));
    public static final RegistryObject<Item> AdvCircuitParts = ITEMS.register("adv_circuit_parts", () -> new ItemBasic("Used for making Advanced Circuits"));
    public static final RegistryObject<Item> EtchedWiringMV = ITEMS.register("etched_wiring_mv", () -> new ItemBasic("Circuit board parts"));
    public static final RegistryObject<Item> EtchedWiringHV = ITEMS.register("etched_wiring_hv", () -> new ItemBasic("Circuit board parts"));
    public static final RegistryObject<Item> EtchedWiringEV = ITEMS.register("etched_wiring_ev", () -> new ItemBasic("Circuit board parts"));
    public static final RegistryObject<Item> EngravedCrystalChip = ITEMS.register("engraved_crystal_chip", () -> new ItemBasic("Needed for Circuits"));
    public static final RegistryObject<Item> EngravedLapotronChip = ITEMS.register("engraved_lapotron_chip", () -> new ItemBasic("Needed for Circuits"));
    public static final RegistryObject<Item> CircuitBoardEmpty = ITEMS.register("circuit_board_empty", () -> new ItemBasic("A board Part"));
    public static final RegistryObject<Item> CircuitBoardBasic = ITEMS.register("circuit_board_basic", () -> new ItemBasic("A basic Board"));
    public static final RegistryObject<Item> CircuitBoardAdv = ITEMS.register("circuit_board_adv", () -> new ItemBasic("An advanced Board"));
    public static final RegistryObject<Item> CircuitBoardProcessorEmpty = ITEMS.register("circuit_board_processor_empty", () -> new ItemBasic("A Processor Board Part"));
    public static final RegistryObject<Item> CircuitBoardProcessor = ITEMS.register("circuit_board_processor", () -> new ItemBasic("A Processor Board"));
    public static final RegistryObject<Item> CircuitBasic = ITEMS.register("circuit_basic", () -> new ItemBasic("A basic Circuit"));
    public static final RegistryObject<Item> CircuitGood = ITEMS.register("circuit_good", () -> new ItemBasic("A good Circuit"));
    public static final RegistryObject<Item> CircuitAdv = ITEMS.register("circuit_adv", () -> new ItemBasic("An advanced Circuit"));
    public static final RegistryObject<Item> CircuitDataStorage = ITEMS.register("circuit_data_storage", () -> new ItemBasic("A Data Storage Chip"));
    public static final RegistryObject<Item> CircuitDataControl = ITEMS.register("circuit_data_control", () -> new ItemBasic("A Data Control Processor"));
    public static final RegistryObject<Item> CircuitEnergyFlow = ITEMS.register("circuit_energy_flow", () -> new ItemBasic("A High Voltage Processor"));
    public static final RegistryObject<Item> CircuitDataOrb = ITEMS.register("circuit_data_orb", () -> new ItemBasic("A High Capacity Data Storage"));
    public static final RegistryObject<Item> DataStick = ITEMS.register("data_stick", () -> new ItemBasic("A Low Capacity Data Storage"));

    public static final RegistryObject<Item> BatteryTantalum = ITEMS.register("battery_tantalum", () -> new ItemBasic("Reusable"));
    public static final RegistryObject<Item> BatteryHullSmall = ITEMS.register("battery_hull_small", () -> new ItemBasic("An empty LV Battery Hull"));
    public static final RegistryObject<Item> BatteryHullMedium = ITEMS.register("battery_hull_medium", () -> new ItemBasic("An empty MV Battery Hull"));
    public static final RegistryObject<Item> BatteryHullLarge = ITEMS.register("battery_hull_large", () -> new ItemBasic("An empty HV Battery Hull"));
    public static final RegistryObject<Item> BatterySmallAcid = ITEMS.register("battery_small_acid", () -> new ItemBasic("Single Use"));
    public static final RegistryObject<Item> BatterySmallMercury = ITEMS.register("battery_small_mercury", () -> new ItemBasic("Single Use"));
    public static final RegistryObject<Item> BatterySmallCadmium = ITEMS.register("battery_small_cadmium", () -> new ItemBasic("Reusable"));
    public static final RegistryObject<Item> BatterySmallLithium = ITEMS.register("battery_small_lithium", () -> new ItemBasic("Reusable"));
    public static final RegistryObject<Item> BatterySmallSodium = ITEMS.register("battery_small_sodium", () -> new ItemBasic("Reusable"));
    public static final RegistryObject<Item> BatteryMediumAcid = ITEMS.register("battery_medium_acid", () -> new ItemBasic("Single Use"));
    public static final RegistryObject<Item> BatteryMediumMercury = ITEMS.register("battery_medium_mercury", () -> new ItemBasic("Single Use"));
    public static final RegistryObject<Item> BatteryMediumCadmium = ITEMS.register("battery_medium_cadmium", () -> new ItemBasic("Reusable"));
    public static final RegistryObject<Item> BatteryMediumLithium = ITEMS.register("battery_medium_lithium", () -> new ItemBasic("Reusable"));
    public static final RegistryObject<Item> BatteryMediumSodium = ITEMS.register("battery_medium_sodium", () -> new ItemBasic("Reusable"));
    public static final RegistryObject<Item> BatteryLargeAcid = ITEMS.register("battery_large_acid", () -> new ItemBasic("Single Use"));
    public static final RegistryObject<Item> BatteryLargeMercury = ITEMS.register("battery_large_mercury", () -> new ItemBasic("Single Use"));
    public static final RegistryObject<Item> BatteryLargeCadmium = ITEMS.register("battery_large_cadmium", () -> new ItemBasic("Reusable"));
    public static final RegistryObject<Item> BatteryLargeLithium = ITEMS.register("battery_large_lithium", () -> new ItemBasic("Reusable"));
    public static final RegistryObject<Item> BatteryLargeSodium = ITEMS.register("battery_large_sodium", () -> new ItemBasic("Reusable"));
    public static final RegistryObject<Item> BatteryEnergyOrb = ITEMS.register("battery_energy_orb", ItemBasic::new);
    public static final RegistryObject<Item> BatteryEnergyOrbCluster = ITEMS.register("battery_energy_orb_cluster", ItemBasic::new);

    public static final RegistryObject<Item> EmptyShape = ITEMS.register("empty_shape_plate", () -> new ItemBasic("Raw plate to make Molds and Extruder Shapes"));
    public static final RegistryObject<Item> MoldPlate = ITEMS.register("mold_plate", () -> new ItemBasic("Mold for making Plates"));
    public static final RegistryObject<Item> MoldGear = ITEMS.register("mold_gear", () -> new ItemBasic("Mold for making Gears"));
    public static final RegistryObject<Item> MoldGearSmall = ITEMS.register("mold_small_gear", () -> new ItemBasic("Mold for making Small Gears"));
    public static final RegistryObject<Item> MoldCoinage = ITEMS.register("mold_coinage", () -> new ItemBasic("Secure Mold for making Coins (Don't lose it!)"));
    public static final RegistryObject<Item> MoldBottle = ITEMS.register("mold_bottle", () -> new ItemBasic("Mold for making Bottles"));
    public static final RegistryObject<Item> MoldIngot = ITEMS.register("mold_ingot", () -> new ItemBasic("Mold for making Ingots"));
    public static final RegistryObject<Item> MoldBall = ITEMS.register("mold_ball", () -> new ItemBasic("Mold for making Balls"));
    public static final RegistryObject<Item> MoldBlock = ITEMS.register("mold_block", () -> new ItemBasic("Mold for making Blocks"));
    public static final RegistryObject<Item> MoldNugget = ITEMS.register("mold_nugget", () -> new ItemBasic("Mold for making Nuggets"));
    public static final RegistryObject<Item> MoldAnvil = ITEMS.register("mold_anvil", () -> new ItemBasic("Mold for making Anvils"));
    public static final RegistryObject<Item> ShapePlate = ITEMS.register("shape_plate", () -> new ItemBasic("Shape for making Plates"));
    public static final RegistryObject<Item> ShapeRod = ITEMS.register("shape_rod", () -> new ItemBasic("Shape for making Rods"));
    public static final RegistryObject<Item> ShapeBolt = ITEMS.register("shape_bolt", () -> new ItemBasic("Shape for making Bolts"));
    public static final RegistryObject<Item> ShapeRing = ITEMS.register("shape_ring", () -> new ItemBasic("Shape for making Rings"));
    public static final RegistryObject<Item> ShapeCell = ITEMS.register("shape_cell", () -> new ItemBasic("Shape for making Cells"));
    public static final RegistryObject<Item> ShapeIngot = ITEMS.register("shape_ingot", () -> new ItemBasic("Shape for making Ingots"));
    public static final RegistryObject<Item> ShapeWire = ITEMS.register("shape_wire", () -> new ItemBasic("Shape for making Wires"));
    public static final RegistryObject<Item> ShapePipeTiny = ITEMS.register("shape_pipe_tiny", () -> new ItemBasic("Shape for making Tiny Pipes"));
    public static final RegistryObject<Item> ShapePipeSmall = ITEMS.register("shape_pipe_small", () -> new ItemBasic("Shape for making Small Pipes"));
    public static final RegistryObject<Item> ShapePipeNormal = ITEMS.register("shape_pipe_normal", () -> new ItemBasic("Shape for making Normal Pipes"));
    public static final RegistryObject<Item> ShapePipeLarge = ITEMS.register("shape_pipe_large", () -> new ItemBasic("Shape for making Large Pipes"));
    public static final RegistryObject<Item> ShapePipeHuge = ITEMS.register("shape_pipe_huge", () -> new ItemBasic("Shape for making Huge Pipes"));
    public static final RegistryObject<Item> ShapeBlock = ITEMS.register("shape_block", () -> new ItemBasic("Shape for making Blocks"));
    public static final RegistryObject<Item> ShapeHeadSword = ITEMS.register("shape_head_sword", () -> new ItemBasic("Shape for making Sword Blades"));
    public static final RegistryObject<Item> ShapeHeadPickaxe = ITEMS.register("shape_head_pickaxe", () -> new ItemBasic("Shape for making Pickaxe Heads"));
    public static final RegistryObject<Item> ShapeHeadShovel = ITEMS.register("shape_head_shovel", () -> new ItemBasic("Shape for making Shovel Heads"));
    public static final RegistryObject<Item> ShapeHeadAxe = ITEMS.register("shape_head_axe", () -> new ItemBasic("Shape for making Axe Heads"));
    public static final RegistryObject<Item> ShapeHeadHoe = ITEMS.register("shape_head_hoe", () -> new ItemBasic("Shape for making Hoe Heads"));
    public static final RegistryObject<Item> ShapeHeadHammer = ITEMS.register("shape_head_hammer", () -> new ItemBasic("Shape for making Hammer Heads"));
    public static final RegistryObject<Item> ShapeHeadFile = ITEMS.register("shape_head_file", () -> new ItemBasic("Shape for making File Heads"));
    public static final RegistryObject<Item> ShapeHeadSaw = ITEMS.register("shape_head_saw", () -> new ItemBasic("Shape for making Saw Heads"));
    public static final RegistryObject<Item> ShapeGear = ITEMS.register("shape_gear", () -> new ItemBasic("Shape for making Gears"));
    public static final RegistryObject<Item> ShapeGearSmall = ITEMS.register("shape_gear_small", () -> new ItemBasic("Shape for making Small Gears"));
    public static final RegistryObject<Item> ShapeBottle = ITEMS.register("shape_bottle", () -> new ItemBasic("Shape for making Bottles")); //TODO needed?
//
//    //TODO optional items (register anyway, but don't show in JEI?)
//    //TODO move to IC2+IC2C Registrar
//    public static final RegistryObject<Item> DropTin = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_tin", "Source of Tin")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropLead = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_lead", "Source of Lead")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropSilver = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_silver", "Source of Silver")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropIron = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_iron", "Source of Iron")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropGold = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_gold", "Source of Gold")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropAluminium = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_aluminium", "Source of Aluminium")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropTitanium = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_titanium", "Source of Titanium")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropUranium = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_uranium", "Source of Uranium")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropUranite = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_uranite", "Source of Uranite")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropThorium = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_thorium", "Source of Thorium")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropNickel = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_nickel", "Source of Nickel")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropZinc = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_zinc", "Source of Zinc")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropManganese = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_manganese", "Source of Manganese")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropCopper = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_copper", "Source of Copper")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropTungsten = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_tungsten", "Source of Tungsten")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropPlatinum = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_platinum", "Source of Platinum")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropIridium = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_iridium", "Source of Iridium")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropOsmium = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_osmium", "Source of Osmium")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropNaquadah = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_naquadah", "Source of Naquadah")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropEmerald = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_emerald", "Source of Emeralds")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropOil = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_oil", "Source of Oil")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropUUM = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_uum", "Source of UU Matter")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//    public static final RegistryObject<Item> DropUUA = ITEMS.register("", () -> new ItemBasic(Ref.ID, "drop_uua", "Source of UU Amplifier")/*.optional(Ref.MOD_IC2, Ref.MOD_IC2C)*/;
//
//    //TODO move to Forestry Registrar
//    public static final RegistryObject<Item> CombLignite = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_lignite", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombCoal = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_coal", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombResin = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_resin", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombOil = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_oil", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombStone = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_stone", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombCertus = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_certus", "")/*.required(Ref.MOD_FR, Ref.MOD_AE)*/;
//    public static final RegistryObject<Item> CombRedstone = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_redstone", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombLapis = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_lapis", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombRuby = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_ruby", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombSapphire = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_sapphire", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombDiamond = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_diamond", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombOlivine = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_olivine", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombEmerald = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_emerald", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombSlag = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_slag", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombCopper = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_copper", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombTin = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_tin", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombLead = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_lead", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombIron = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_iron", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombSteel = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_steel", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombNickel = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_nickel", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombZinc = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_zinc", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombSilver = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_silver", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombGold = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_gold", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombAluminium = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_aluminium", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombManganese = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_manganese", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombTitanium = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_titanium", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombChrome = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_chrome", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombTungsten = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_tungsten", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombPlatinum = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_platinum", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombIridium = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_iridium", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombUranium = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_uranium", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombPlutonium = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_plutonium", "")/*.optional(Ref.MOD_FR)*/;
//    public static final RegistryObject<Item> CombNaquadah = ITEMS.register("", () -> new ItemBasic(Ref.ID, "comb_naquadah", "")/*.optional(Ref.MOD_FR)*/;

    //TODO
    //public static BlockRubberSapling RUBBER_SAPLING = new BlockRubberSapling();
    //public static BlockRubberLog RUBBER_LOG = new BlockRubberLog();
    //public static BlockLeavesBase RUBBER_LEAVES = new BlockLeavesBase("rubber_leaves", RUBBER_SAPLING);

    //STONE should be the only non-removable StoneType. It serves as the foundation. It is also used natively by BlockRock
    //TODO move vanilla stone types to Antimatter
    public static StoneType STONE = new StoneType(Ref.ID, "stone", Materials.Stone, new Texture("minecraft", "block/stone"), SoundType.STONE, false).setState(Blocks.STONE);

    public static StoneType GRANITE = new StoneType(Ref.ID, "granite", Granite, new Texture("minecraft", "block/granite"), SoundType.STONE, !muramasa.antimatter.Configs.WORLD.DISABLE_VANILLA_STONE_GEN || muramasa.antimatter.Ref.debugStones).setState(Blocks.GRANITE);
    public static StoneType DIORITE = new StoneType(Ref.ID, "diorite", Diorite, new Texture("minecraft", "block/diorite"), SoundType.STONE, !muramasa.antimatter.Configs.WORLD.DISABLE_VANILLA_STONE_GEN || muramasa.antimatter.Ref.debugStones).setState(Blocks.DIORITE);
    public static StoneType ANDESITE = new StoneType(Ref.ID, "andesite", Andesite, new Texture("minecraft", "block/andesite"),  SoundType.STONE, !muramasa.antimatter.Configs.WORLD.DISABLE_VANILLA_STONE_GEN || muramasa.antimatter.Ref.debugStones).setState(Blocks.ANDESITE);

    public static StoneType GRAVEL = new StoneType(Ref.ID, "gravel", Gravel, new Texture("minecraft", "block/gravel"), SoundType.GROUND, false).setState(Blocks.GRAVEL);
    public static StoneType SAND = new StoneType(Ref.ID, "sand", Sand, new Texture("minecraft", "block/sand"), SoundType.SAND, false).setState(Blocks.SAND);
    public static StoneType SAND_RED = new StoneType(Ref.ID, "sand_red", RedSand, new Texture("minecraft", "block/red_sand"), SoundType.SAND, false).setState(Blocks.RED_SAND);
    public static StoneType SANDSTONE = new StoneType(Ref.ID, "sandstone", Sandstone, new Texture("minecraft", "block/sandstone"), SoundType.STONE, false).setState(Blocks.SANDSTONE);

    public static StoneType NETHERRACK = new StoneType(Ref.ID, "netherrack", Materials.Netherrack, new Texture("minecraft", "block/netherrack"), SoundType.STONE, false).setState(Blocks.NETHERRACK);
    public static StoneType ENDSTONE = new StoneType(Ref.ID, "endstone", Materials.Endstone, new Texture("minecraft", "block/end_stone"), SoundType.STONE, false).setState(Blocks.END_STONE);

    public static StoneType GRANITE_RED = new StoneType(Ref.ID, "granite_red", Materials.RedGranite, new Texture(Ref.ID, "block/stone/granite_red"), SoundType.STONE, true);
    public static StoneType GRANITE_BLACK = new StoneType(Ref.ID, "granite_black", Materials.BlackGranite, new Texture(Ref.ID, "block/stone/granite_black"), SoundType.STONE, true);
    public static StoneType MARBLE = new StoneType(Ref.ID, "marble", Materials.Marble, new Texture(Ref.ID, "block/stone/marble"), SoundType.STONE, true);
    public static StoneType BASALT = new StoneType(Ref.ID, "basalt", Materials.Basalt, new Texture(Ref.ID, "block/stone/basalt"), SoundType.STONE, true);

    public static StoneType KOMATIITE = new StoneType(Ref.ID, "komatiite", Materials.Komatiite, new Texture(Ref.ID, "block/stone/komatiite"), SoundType.STONE, true);
    public static StoneType LIMESTONE = new StoneType(Ref.ID, "limestone", Limestone, new Texture(Ref.ID, "block/stone/limestone"), SoundType.STONE, true);
    public static StoneType GREEN_SCHIST = new StoneType(Ref.ID, "green_schist", GreenSchist, new Texture(Ref.ID, "block/stone/green_schist"), SoundType.STONE, true);
    public static StoneType BLUE_SCHIST = new StoneType(Ref.ID, "blue_schist", BlueSchist, new Texture(Ref.ID, "block/stone/blue_schist"), SoundType.STONE, true);
    public static StoneType KIMBERLITE = new StoneType(Ref.ID, "kimberlite", Kimberlite, new Texture(Ref.ID, "block/stone/kimberlite"), SoundType.STONE, true);
    public static StoneType QUARTZITE = new StoneType(Ref.ID, "quartzite", Quartzite, new Texture(Ref.ID, "block/stone/quartzite"), SoundType.STONE, true);

    //public static BlockBasic ANTHRACITE_COAL = new BlockBasic(Ref.ID, "anthracite_coal", new Texture(Ref.ID, "block/basic/anthracite_coal"));
    //public static BlockBasic ANTHRACITE_COAL = new BlockBasic(Ref.ID, "anthracite_coal", new Texture(Ref.ID, "block/basic/anthracite_coal"));

//    public static BlockStone STONE_GRANITE_RED = new BlockStone(Ref.ID, GRANITE_RED);
//    public static BlockStone STONE_GRANITE_BLACK = new BlockStone(Ref.ID, GRANITE_BLACK);
//    public static BlockStone STONE_MARBLE = new BlockStone(Ref.ID, MARBLE);
//    public static BlockStone STONE_BASALT = new BlockStone(Ref.ID, BASALT);
//
//    public static BlockStone STONE_KOMATIITE = new BlockStone(Ref.ID, KOMATIITE);
//    public static BlockStone STONE_LIMESTONE = new BlockStone(Ref.ID, LIMESTONE);
//    public static BlockStone STONE_GREEN_SCHIST = new BlockStone(Ref.ID, GREEN_SCHIST);
//    public static BlockStone STONE_BLUE_SCHIST = new BlockStone(Ref.ID, BLUE_SCHIST);
//    public static BlockStone STONE_KIMBERLITE = new BlockStone(Ref.ID, KIMBERLITE);
//    public static BlockStone STONE_QUARTZITE = new BlockStone(Ref.ID, QUARTZITE);

    public static final RegistryObject<Block> CASING_ULV = BLOCKS.register("casing_ulv", BlockCasingMachine::new);
    public static final RegistryObject<Block> CASING_LV = BLOCKS.register("casing_lv", BlockCasingMachine::new);
    public static final RegistryObject<Block> CASING_MV = BLOCKS.register("casing_mv", BlockCasingMachine::new);
    public static final RegistryObject<Block> CASING_HV = BLOCKS.register("casing_hv", BlockCasingMachine::new);
    public static final RegistryObject<Block> CASING_EV = BLOCKS.register("casing_ev", BlockCasingMachine::new);
    public static final RegistryObject<Block> CASING_IV = BLOCKS.register("casing_iv", BlockCasingMachine::new);
    public static final RegistryObject<Block> CASING_LUV = BLOCKS.register("casing_luv", BlockCasingMachine::new);
    public static final RegistryObject<Block> CASING_ZPM = BLOCKS.register("casing_zpm", BlockCasingMachine::new);
    public static final RegistryObject<Block> CASING_UV = BLOCKS.register("casing_uv", BlockCasingMachine::new);
    public static final RegistryObject<Block> CASING_MAX = BLOCKS.register("casing_max", BlockCasingMachine::new);

    public static final RegistryObject<Block> CASING_FIRE_BRICK = BLOCKS.register("casing_fire_brick", BlockCasing::new);
    public static final RegistryObject<Block> CASING_BRONZE = BLOCKS.register("casing_bronze", BlockCasing::new);
    public static final RegistryObject<Block> CASING_BRICKED_BRONZE = BLOCKS.register("casing_bricked_bronze", BlockCasing::new);
    public static final RegistryObject<Block> CASING_BRONZE_PLATED_BRICK = BLOCKS.register("casing_bronze_plated_brick", BlockCasing::new);
    public static final RegistryObject<Block> CASING_STEEL = BLOCKS.register("casing_steel", BlockCasing::new);
    public static final RegistryObject<Block> CASING_BRICKED_STEEL = BLOCKS.register("casing_bricked_steel", BlockCasing::new);
    public static final RegistryObject<Block> CASING_SOLID_STEEL = BLOCKS.register("casing_solid_steel", BlockCasing::new);
    public static final RegistryObject<Block> CASING_STAINLESS_STEEL = BLOCKS.register("casing_stainless_steel", BlockCasing::new);
    public static final RegistryObject<Block> CASING_TITANIUM = BLOCKS.register("casing_titanium", BlockCasing::new);
    public static final RegistryObject<Block> CASING_TUNGSTENSTEEL = BLOCKS.register("casing_tungstensteel", BlockCasing::new);
    public static final RegistryObject<Block> CASING_HEAT_PROOF = BLOCKS.register("casing_heat_proof", BlockCasing::new);
    public static final RegistryObject<Block> CASING_FROST_PROOF = BLOCKS.register("casing_frost_proof", BlockCasing::new);
    public static final RegistryObject<Block> CASING_RADIATION_PROOF = BLOCKS.register("casing_radiation_proof", BlockCasing::new);
    public static final RegistryObject<Block> CASING_FIREBOX_BRONZE = BLOCKS.register("casing_firebox_bronze", BlockCasing::new);
    public static final RegistryObject<Block> CASING_FIREBOX_STEEL = BLOCKS.register("casing_firebox_steel", BlockCasing::new);
    public static final RegistryObject<Block> CASING_FIREBOX_TITANIUM = BLOCKS.register("casing_firebox_titanium", BlockCasing::new);
    public static final RegistryObject<Block> CASING_FIREBOX_TUNGSTENSTEEL = BLOCKS.register("casing_firebox_tungstensteel", BlockCasing::new);
    public static final RegistryObject<Block> CASING_GEARBOX_BRONZE = BLOCKS.register("casing_gearbox_bronze", BlockCasing::new);
    public static final RegistryObject<Block> CASING_GEARBOX_STEEL = BLOCKS.register("casing_gearbox_steel", BlockCasing::new);
    public static final RegistryObject<Block> CASING_GEARBOX_TITANIUM = BLOCKS.register("casing_gearbox_titanium", BlockCasing::new);
    public static final RegistryObject<Block> CASING_GEARBOX_TUNGSTENSTEEL = BLOCKS.register("casing_gearbox_tungstensteel", BlockCasing::new);
    public static final RegistryObject<Block> CASING_PIPE_BRONZE = BLOCKS.register("casing_pipe_bronze", BlockCasing::new);
    public static final RegistryObject<Block> CASING_PIPE_STEEL = BLOCKS.register("casing_pipe_steel", BlockCasing::new);
    public static final RegistryObject<Block> CASING_PIPE_TITANIUM = BLOCKS.register("casing_pipe_titanium", BlockCasing::new);
    public static final RegistryObject<Block> CASING_PIPE_TUNGSTENSTEEL = BLOCKS.register("casing_pipe_tungstensteel", BlockCasing::new);
    public static final RegistryObject<Block> CASING_ENGINE_INTAKE = BLOCKS.register("casing_engine_intake", BlockCasing::new);

    public static final RegistryObject<Block> CASING_FUSION_1 = BLOCKS.register("casing_fusion_1", BlockFusionCasing::new);
    public static final RegistryObject<Block> CASING_FUSION_2 = BLOCKS.register("casing_fusion_2", BlockFusionCasing::new);
    public static final RegistryObject<Block> CASING_FUSION_3 = BLOCKS.register("casing_fusion_3", BlockFusionCasing::new);

    public static final RegistryObject<Block> CASING_TURBINE_1 = BLOCKS.register("casing_turbine_1", BlockTurbineCasing::new);
    public static final RegistryObject<Block> CASING_TURBINE_2 = BLOCKS.register("casing_turbine_2", BlockTurbineCasing::new);
    public static final RegistryObject<Block> CASING_TURBINE_3 = BLOCKS.register("casing_turbine_3", BlockTurbineCasing::new);
    public static final RegistryObject<Block> CASING_TURBINE_4 = BLOCKS.register("casing_turbine_4", BlockTurbineCasing::new);

   public static final RegistryObject<Block> COIL_CUPRONICKEL = BLOCKS.register("coil_cupronickel", () -> new BlockCoil(113)); //1808
   public static final RegistryObject<Block> COIL_KANTHAL = BLOCKS.register("coil_kanthal", () -> new BlockCoil(169)); //2704
   public static final RegistryObject<Block> COIL_NICHROME = BLOCKS.register("coil_nichrome", () -> new BlockCoil(225)); //3600
   public static final RegistryObject<Block> COIL_TUNGSTENSTEEL = BLOCKS.register("coil_tungstensteel", () -> new BlockCoil(282)); //4512
   public static final RegistryObject<Block> COIL_HSSG = BLOCKS.register("coil_hssg", () -> new BlockCoil(338)); //5408
   public static final RegistryObject<Block> COIL_NAQUADAH = BLOCKS.register("coil_naquadah", () -> new BlockCoil(450)); //7200
   public static final RegistryObject<Block> COIL_NAQUADAH_ALLOY = BLOCKS.register("coil_naquadah_alloy", () -> new BlockCoil(563)); //9008
   public static final RegistryObject<Block> COIL_FUSION = BLOCKS.register("coil_fusion", () -> new BlockCoil(563)); //9008
   public static final RegistryObject<Block> COIL_SUPERCONDUCTOR = BLOCKS.register("coil_superconductor", () -> new BlockCoil(563)); //9008

    static {
        new BlockCableBuilder(RedAlloy, 0, 1, Tier.ULV).amps(1).build(BLOCKS); //ULV
        new BlockCableBuilder(Cobalt, 2, 4, Tier.LV).amps(2).build(BLOCKS); //LV
        new BlockCableBuilder(Lead, 2, 4, Tier.LV).amps(2).build(BLOCKS);
        new BlockCableBuilder(Tin, 1, 2, Tier.LV).amps(1).build(BLOCKS);
        new BlockCableBuilder(Zinc, 1, 2, Tier.LV).amps(1).build(BLOCKS);
        new BlockCableBuilder(SolderingAlloy, 1, 2, Tier.LV).amps(1).build(BLOCKS);
        new BlockCableBuilder(Iron, HC ? 3 : 4, HC ? 6 : 8, Tier.MV).amps(2).build(BLOCKS); //MV
        new BlockCableBuilder(Nickel, HC ? 3 : 5, HC ? 6 : 10, Tier.MV).amps(3).build(BLOCKS);
        new BlockCableBuilder(Cupronickel, HC ? 3 : 4, HC ? 6 : 8, Tier.MV).amps(2).build(BLOCKS);
        new BlockCableBuilder(Copper, HC ? 2 : 3, HC ? 4 : 6, Tier.MV).amps(1).build(BLOCKS);
        new BlockCableBuilder(AnnealedCopper, HC ? 1 : 2, HC ? 2 : 4, Tier.MV).amps(1).build(BLOCKS);
        new BlockCableBuilder(Kanthal, HC ? 3 : 8, HC ? 6 : 16, Tier.HV).amps(4).build(BLOCKS); //HV
        new BlockCableBuilder(Gold, HC ? 2 : 6, HC ? 4 : 12, Tier.HV).amps(3).build(BLOCKS);
        new BlockCableBuilder(Electrum, HC ? 2 : 5, HC ? 4 : 10, Tier.HV).amps(2).build(BLOCKS);
        new BlockCableBuilder(Silver, HC ? 1 : 4, HC ? 2 : 8, Tier.HV).amps(1).build(BLOCKS);
        new BlockCableBuilder(Nichrome, HC ? 4 : 32, HC ? 8 : 64, Tier.EV).amps(3).build(BLOCKS); //EV
        new BlockCableBuilder(Steel, HC ? 2 : 16, HC ? 4 : 32, Tier.EV).amps(2).build(BLOCKS);
        new BlockCableBuilder(Titanium, HC ? 2 : 12, HC ? 4 : 24, Tier.EV).amps(4).build(BLOCKS);
        new BlockCableBuilder(Aluminium, HC ? 1 : 8, HC ? 2 : 16, Tier.EV).amps(1).build(BLOCKS);
        new BlockCableBuilder(Graphene, HC ? 1 : 16, HC ? 2 : 32, Tier.IV).amps(1).build(BLOCKS); //IV
        new BlockCableBuilder(Osmium, HC ? 2 : 32, HC ? 4 : 64, Tier.IV).amps(4).build(BLOCKS);
        new BlockCableBuilder(Platinum, HC ? 1 : 16, HC ? 2 : 32, Tier.IV).amps(2).build(BLOCKS);
        new BlockCableBuilder(TungstenSteel, HC ? 1 : 14, HC ? 4 : 28, Tier.IV).amps(3).build(BLOCKS);
        new BlockCableBuilder(Tungsten, HC ? 2 : 12, HC ? 4 : 24, Tier.IV).amps(1).build(BLOCKS);
        new BlockCableBuilder(HSSG, HC ? 2 : 128, HC ? 4 : 256, Tier.LUV).amps(4).build(BLOCKS); //LUV
        new BlockCableBuilder(NiobiumTitanium, HC ? 2 : 128, HC ? 4 : 256, Tier.LUV).amps(4).build(BLOCKS);
        new BlockCableBuilder(VanadiumGallium, HC ? 2 : 128, HC ? 4 : 256, Tier.LUV).amps(4).build(BLOCKS);
        new BlockCableBuilder(YttriumBariumCuprate, HC ? 4 : 256, HC ? 8 : 512, Tier.LUV).amps(4).build(BLOCKS);
        new BlockCableBuilder(Naquadah, HC ? 2 : 64, HC ? 4 : 128, Tier.ZPM).amps(2).build(BLOCKS); //ZPM
        new BlockCableBuilder(NaquadahAlloy, HC ? 4 : 64, HC ? 8 : 128, Tier.ZPM).amps(2).build(BLOCKS);
        new BlockCableBuilder(Duranium, HC ? 8 : 64, HC ? 16 : 128, Tier.ZPM).amps(1).build(BLOCKS);
        new BlockCableBuilder(Superconductor, 1, 1, Tier.MAX).amps(4).build(BLOCKS); //MAX

        new BlockFluidPipeBuilder(Wood, 30, false, PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE).caps(10, 10, 30, 60, 60, 60).build(BLOCKS);
        new BlockFluidPipeBuilder(Copper, 1000, true).caps(10).build(BLOCKS);
        new BlockFluidPipeBuilder(Bronze, 2000, true).caps(20).build(BLOCKS);
        new BlockFluidPipeBuilder(Steel, 2500, true).caps(40).build(BLOCKS);
        new BlockFluidPipeBuilder(StainlessSteel, 3000, true).caps(60).build(BLOCKS);
        new BlockFluidPipeBuilder(Titanium, 5000, true).caps(80).build(BLOCKS);
        new BlockFluidPipeBuilder(TungstenSteel, 7500, true).caps(100).build(BLOCKS);
        new BlockFluidPipeBuilder(Plastic, 250, true).caps(60).build(BLOCKS);
        new BlockFluidPipeBuilder(Polytetrafluoroethylene, 600, true).caps(480).build(BLOCKS);
        new BlockFluidPipeBuilder(HighPressure, 1500, true, PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE).caps(4800, 4800, 4800, 7200, 9600, 9600).build(BLOCKS);
        new BlockFluidPipeBuilder(PlasmaContainment, 100000, true, PipeSize.NORMAL).caps(240, 240, 240, 240, 240, 240).build(BLOCKS);

        new BlockItemPipeBuilder(Cupronickel).slots(1).steps(1).build(BLOCKS);
        new BlockItemPipeBuilder(CobaltBrass).slots(1).steps(1).build(BLOCKS);
        new BlockItemPipeBuilder(Brass).slots(1).steps(1).build(BLOCKS);
        new BlockItemPipeBuilder(Electrum).slots(2).steps(2).build(BLOCKS);
        new BlockItemPipeBuilder(RoseGold).slots(2).steps(2).build(BLOCKS);
        new BlockItemPipeBuilder(SterlingSilver).slots(2).steps(2).build(BLOCKS);
        new BlockItemPipeBuilder(Platinum).slots(4).steps(4).build(BLOCKS);
        new BlockItemPipeBuilder(Ultimet).slots(4).steps(4).build(BLOCKS);
        new BlockItemPipeBuilder(PolyvinylChloride).slots(4).steps(4).build(BLOCKS);
        new BlockItemPipeBuilder(Osmium).slots(8).steps(8).build(BLOCKS);
    }

    // Rubber Tree
    public static final RegistryObject<Block> RUBBER_LEAVES = BLOCKS.register("rubber_leaves", BlockRubberLeaves::new);
    public static final RegistryObject<Block> RUBBER_LOG = BLOCKS.register("rubber_log", BlockRubberLog::new);
    public static final RegistryObject<Block> RUBBER_SAPLING = BLOCKS.register("rubber_sapling", BlockRubberSapling::new);
}
