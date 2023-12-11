package muramasa.gregtech.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.block.BlockBasic;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.item.*;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.*;
import muramasa.antimatter.registration.Side;
import muramasa.antimatter.texture.Texture;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.block.*;
import muramasa.gregtech.block.BlockCoil.CoilData;
import muramasa.gregtech.cover.*;
import muramasa.gregtech.cover.redstone.CoverRedstoneMachineController;
import muramasa.gregtech.items.ItemDataStick;
import muramasa.gregtech.items.ItemPrintedPages;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.HashSet;
import java.util.Set;

import static muramasa.gregtech.data.Materials.*;

public class GregTechData {


    private static final String CAPE_PATH = "textures/capes/";
    public static final ResourceLocation[] CAPE_LOCATIONS = new ResourceLocation[] {new ResourceLocation(GTIRef.ID,  CAPE_PATH + "braintech.png"), new ResourceLocation(GTIRef.ID, CAPE_PATH + "silver.png"), new ResourceLocation(GTIRef.ID, CAPE_PATH + "mrbrain.png"), new ResourceLocation(GTIRef.ID, CAPE_PATH + "dev.png"), new ResourceLocation(GTIRef.ID, CAPE_PATH + "gold.png"), new ResourceLocation(GTIRef.ID, CAPE_PATH + "crazy.png"), new ResourceLocation(GTIRef.ID, CAPE_PATH + "fake.png")};

    public static final Set<String> SupporterListSilver = new HashSet<>(), SupporterListGold = new HashSet<>();

    public static void init(Side side) {
        if (side == Side.CLIENT)
            RecipeMaps.clientMaps();
        /*AntimatterAPI.all(MaterialType.class, t -> {
            if (t instanceof MaterialTypeFluid) return;
            if (t.getClass() == MaterialType.class) return;
            //TODO: add better check
            if (t == AntimatterMaterialTypes.ORE_STONE) return;
            CoverFactory.builder((a,b,c,d) -> new CoverTypeFilter(a,b,c,d,t)).addTextures(Material.NULL.getSet().getTextures(t)).item((a, b) -> {
            return new ItemCover(a.getDomain(), a.getId()).tip("Filters for " + t.getId()).texture(Material.NULL.getSet().getTextures(t));}).build(GTIRef.ID, "cover_type_" + t.getId());
        });*/
    }

    public static final CoverFactory COVER_CONVEYOR = CoverFactory.builder(CoverConveyor::new).gui().item((a,b) ->
            new ItemCover(a.getDomain(), a.getId(), b).tip(String.format("1 Stack every %dt(%ss) (as Cover)", CoverConveyor.speeds.get(b), (float)CoverConveyor.speeds.get(b) / 20))
    ).addTextures(new Texture(GTIRef.ID, "block/cover/conveyor")).setTiers(Tier.getStandard()).build(GTIRef.ID, "conveyor");

    public static final CoverFactory COVER_ITEM_REGULATOR = CoverFactory.builder(CoverItemRegulator::new).gui().item((a,b) ->
            new ItemCover(a.getDomain(), a.getId(), b).tip(String.format("1 Stack every %dt(%ss), with configurable stack size limits (as Cover)", CoverConveyor.speeds.get(b), (float)CoverConveyor.speeds.get(b) / 20))
    ).addTextures(new Texture(GTIRef.ID, "block/cover/conveyor")).setTiers(Tier.getStandard()).build(GTIRef.ID, "item_regulator");
    public static final CoverFactory COVER_PUMP = CoverFactory.builder(CoverPump::new).gui().item((a,b) ->
            new ItemCover(a.getDomain(), a.getId(), b).tip(String.format("%d L/t (as Cover)", CoverPump.speeds.get(b))))
            .addTextures(new Texture(GTIRef.ID, "block/cover/pump")).setTiers(Tier.getStandard()).build(GTIRef.ID, "pump");
    public static final CoverFactory COVER_FLUID_REGULATOR = CoverFactory.builder(CoverFluidRegulator::new).gui().item((a,b) ->
                    new ItemCover(a.getDomain(), a.getId(), b).tip(String.format("Configurable up to %d L/t (as Cover)", CoverPump.speeds.get(b))))
            .addTextures(new Texture(GTIRef.ID, "block/cover/pump")).setTiers(Tier.getStandard()).build(GTIRef.ID, "fluid_regulator");
    public static final CoverFactory COVER_ROBOT_ARM = CoverFactory.builder(CoverRobotArm::new).gui().item((a,b) ->
                    new ItemCover(a.getDomain(), a.getId(), b).tip(String.format("1 Stack every %dt(%ss) (as Cover)", CoverConveyor.speeds.get(b), (float)CoverConveyor.speeds.get(b) / 20)))
            .addTextures(new Texture(GTIRef.ID, "block/cover/conveyor")).setTiers(Tier.getStandard()).build(GTIRef.ID, "robot_arm");
    public static final CoverFactory COVER_DRAIN = CoverFactory.builder(CoverDrain::new).item((a, b) ->
            new ItemCover(GTIRef.ID, "drain").tip("Can be placed on machines/pipes as a cover")).addTextures(new Texture(GTIRef.ID, "block/cover/drain")).build(GTIRef.ID, "drain");
    public static final CoverFactory COVER_AIR_VENT = CoverFactory.builder(CoverAirVent::new).item((a, b) ->
            new ItemCover(GTIRef.ID, "air_vent").tip("Can be placed on machines/pipes as a cover")).addTextures(new Texture(GTIRef.ID, "block/cover/air_vent")).build(GTIRef.ID, "air_vent");

    public static final CoverFactory COVER_ITEM_FILTER = CoverFactory.builder(CoverItemFilter::new).item((a, b) ->
            new ItemCover(GTIRef.ID, "item_filter").tip("Can be placed as cover")).addTextures(new Texture(GTIRef.ID, "block/cover/item_filter")).gui().build(GTIRef.ID, "item_filter");
    public static final CoverFactory COVER_FLUID_FILTER = CoverFactory.builder(CoverFluidFilter::new).item((a, b) ->
            new ItemCover(GTIRef.ID, "fluid_filter").tip("Can be placed as cover")).addTextures(new Texture(GTIRef.ID, "block/cover/fluid_filter")).gui().build(GTIRef.ID, "fluid_filter");

    public static CoverFactory COVER_SHUTTER = CoverFactory.builder(CoverShutter::new).item((a, b) ->
            new ItemCover(GTIRef.ID, "shutter").tip("can be placed as a pipe cover")).addTextures(new Texture(GTIRef.ID, "block/cover/shutter")).build(GTIRef.ID, "shutter");
    public static final CoverFactory COVER_REDSTONE_MACHINE_CONTROLLER = CoverFactory.builder(CoverRedstoneMachineController::new).gui().item((a, b) -> {
        return new ItemCover(GTIRef.ID, "redstone_machine_controller");
    }).addTextures(new Texture(GTIRef.ID, "block/cover/redstone_machine_controller")).build(GTIRef.ID, "redstone_machine_controller");

    public static final CoverFactory COVER_ENERGY_DETECTOR = CoverFactory.builder(CoverEnergyDetector::new).gui().item((a, b) -> {
        return new ItemCover(GTIRef.ID, "energy_detector");
    }).addTextures(new Texture(GTIRef.ID, "block/cover/energy_detector")).build(GTIRef.ID, "energy_detector");

    public static final CoverFactory COVER_FLUID_DETECTOR = CoverFactory.builder(CoverFluidDetector::new).gui().item((a, b) -> {
        return new ItemCover(GTIRef.ID, "fluid_detector");
    }).addTextures(new Texture(GTIRef.ID, "block/cover/fluid_detector")).build(GTIRef.ID, "fluid_detector");

    public static final CoverFactory COVER_ITEM_DETECTOR = CoverFactory.builder(CoverItemDetector::new).gui().item((a, b) -> {
        return new ItemCover(GTIRef.ID, "item_detector");
    }).addTextures(new Texture(GTIRef.ID, "block/cover/item_detector")).build(GTIRef.ID, "item_detector");

    public static final CoverFactory COVER_STEAM_VENT = CoverFactory.builder(CoverSteamVent::new)
            .addTextures(new Texture(GTIRef.ID, "block/cover/output")).build(GTIRef.ID, "steam_vent");
    public static ItemBasic<?> ComputerMonitor = new ItemBasic<>(GTIRef.ID, "computer_monitor").tip("Can be placed on machines as a cover");

    public static ItemFluidCell CellTin = new ItemFluidCell(GTIRef.ID, Tin, 1000);
    public static ItemFluidCell CellSteel = new ItemFluidCell(GTIRef.ID, Steel, 16000);
    public static ItemFluidCell CellTungstensteel = new ItemFluidCell(GTIRef.ID, TungstenSteel, 64000);
    public static ItemBasic<?> Scrap = new ItemBasic<>(GTIRef.ID, "scrap");
    public static ItemBasic<?> WoodPellet = new ItemBasic<>(GTIRef.ID, "wood_pellet");
    public static ItemBasic<?> PrintedPages = new ItemPrintedPages(GTIRef.ID, "printed_pages").tip("Used to make written Books");
    public static ItemBasic<?> DataStick = new ItemDataStick(GTIRef.ID, "data_stick").tip("A Low Capacity Data Storage");
    public static ItemBasic<?> QuantumEye = new ItemBasic<>(GTIRef.ID, "quantum_eye").tip("Improved Ender Eye");
    public static ItemBasic<?> QuantumStar = new ItemBasic<>(GTIRef.ID, "quantum_star").tip("Improved Nether Star");
    public static ItemBasic<?> GraviStar = new ItemBasic<>(GTIRef.ID, "gravi_star").tip("Ultimate Nether Star");
    public static ItemBasic<?> SuperFuelBinder = new ItemBasic<>(GTIRef.ID, "super_fuel_binder");
    public static ItemBasic<?> PistonLV = new ItemBasic<>(GTIRef.ID, "piston_lv");
    public static ItemBasic<?> PistonMV = new ItemBasic<>(GTIRef.ID, "piston_mv");
    public static ItemBasic<?> PistonHV = new ItemBasic<>(GTIRef.ID, "piston_hv");
    public static ItemBasic<?> PistonEV = new ItemBasic<>(GTIRef.ID, "piston_ev");
    public static ItemBasic<?> PistonIV = new ItemBasic<>(GTIRef.ID, "piston_iv");
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

    public static ItemBasic<?> MicroProcessor = new ItemBasic<>(GTIRef.ID, "microprocessor", "circuits/").tip("A Basic Circuit");
    public static ItemBasic<?> IntegratedProcessor = new ItemBasic<>(GTIRef.ID, "integrated_processor", "circuits/").tip("A Good Circuit");
    public static ItemBasic<?> ProcessorAssembly = new ItemBasic<>(GTIRef.ID, "processor_assembly", "circuits/").tip("An advanced Circuit");
    public static ItemBasic<?> NanoProcessor = new ItemBasic<>(GTIRef.ID, "nanoprocessor", "circuits/").tip("An advanced Circuit");
    public static ItemBasic<?> Workstation = new ItemBasic<>(GTIRef.ID, "workstation", "circuits/").tip("An extreme Circuit");
    public static ItemBasic<?> QuantumProcessor = new ItemBasic<>(GTIRef.ID, "quantumprocessor", "circuits/").tip("An extreme Circuit");
    public static ItemBasic<?> NanoprocessorAssembly = new ItemBasic<>(GTIRef.ID, "nanoprocessor_assembly", "circuits/").tip("An extreme Circuit");
    public static ItemBasic<?> Mainframe = new ItemBasic<>(GTIRef.ID, "mainframe", "circuits/").tip("An elite circuit");
    public static ItemBasic<?> QuantumProcessorAssembly = new ItemBasic<>(GTIRef.ID, "quantumprocessor_assembly", "circuits/").tip("An elite circuit");
    public static ItemBasic<?> CrystalProcessor = new ItemBasic<>(GTIRef.ID, "crystal_processor", "circuits/").tip("An elite Circuit");

    public static ItemBasic<?> CircuitWetware = new ItemBasic<>(GTIRef.ID, "wetware_circuit", "circuits/").tip("You feel like it's watching you");
    public static ItemBasic<?> SmallCoil = new ItemBasic<>(GTIRef.ID, "small_coil").tip("A part for circuits");
    public static ItemBasic<?> PetriDish = new ItemBasic<>(GTIRef.ID, "petri_dish");

    public static ItemBasic<?> SiliconBoule = new ItemBasic<>(GTIRef.ID, "monocrystalline_silicon_boule", "silicon/");
    public static ItemBasic<?> GlowstoneDopedSiliconBoule = new ItemBasic<>(GTIRef.ID, "glowstone_doped_monocrystalline_silicon_boule", "silicon/");
    public static ItemBasic<?> NaquadahDopedSiliconBoule = new ItemBasic<>(GTIRef.ID, "naquadah_doped_monocrystalline_silicon_boule", "silicon/");

    public static ItemBasic<?> Wafer = new ItemBasic<>(GTIRef.ID, "wafer", "silicon/");
    public static ItemBasic<?> SiliconChip = new ItemBasic<>(GTIRef.ID, "silicon_chip", "silicon/");
    public static ItemBasic<?> GlowstoneDopedWafer = new ItemBasic<>(GTIRef.ID, "glowstone_doped_wafer", "silicon/");
    public static ItemBasic<?> NaquadahDopedWafer = new ItemBasic<>(GTIRef.ID, "naquadah_doped_wafer", "silicon/");
    public static ItemBasic<?> ASoCWafer = new ItemBasic<>(GTIRef.ID, "asoc_wafer", "silicon/");
    public static ItemBasic<?> ASoC = new ItemBasic<>(GTIRef.ID, "asoc", "silicon/");
    public static ItemBasic<?> CentralProcessingUnitWafer = new ItemBasic<>(GTIRef.ID, "central_processing_unit_wafer", "silicon/");
    public static ItemBasic<?> CentralProcessingUnit = new ItemBasic<>(GTIRef.ID, "central_processing_unit", "silicon/");
    public static ItemBasic<?> HPICWafer = new ItemBasic<>(GTIRef.ID, "hpic_wafer", "silicon/");
    public static ItemBasic<?> HighPowerIC = new ItemBasic<>(GTIRef.ID, "high_power_ic", "silicon/");
    public static ItemBasic<?> IntegratedLogicCircuitWafer = new ItemBasic<>(GTIRef.ID, "integrated_logic_circuit_wafer", "silicon/");
    public static ItemBasic<?> IntegratedLogicCircuit = new ItemBasic<>(GTIRef.ID, "integrated_logic_circuit", "silicon/");
    public static ItemBasic<?> NANDMemoryChipWafer = new ItemBasic<>(GTIRef.ID, "nand_memory_chip_wafer", "silicon/");
    public static ItemBasic<?> NANDMemoryChip = new ItemBasic<>(GTIRef.ID, "nand_memory_chip", "silicon/");
    public static ItemBasic<?> NanoCpuWafer = new ItemBasic<>(GTIRef.ID, "nano_cpu_wafer", "silicon/");
    public static ItemBasic<?> NanoCpu = new ItemBasic<>(GTIRef.ID, "nanocomponent_central_processing_unit", "silicon/");
    public static ItemBasic<?> NorMemoryChipWafer = new ItemBasic<>(GTIRef.ID, "nor_memory_chip_wafer", "silicon/");
    public static ItemBasic<?> NorMemoryChip = new ItemBasic<>(GTIRef.ID, "nor_memory_chip", "silicon/");
    public static ItemBasic<?> PICWafer = new ItemBasic<>(GTIRef.ID, "pic_wafer", "silicon/");
    public static ItemBasic<?> PowerIC = new ItemBasic<>(GTIRef.ID, "power_ic", "silicon/");
    public static ItemBasic<?> QBitWafer = new ItemBasic<>(GTIRef.ID, "qbit_wafer", "silicon/");
    public static ItemBasic<?> QBitProcessingUnit = new ItemBasic<>(GTIRef.ID, "qbit_processing_unit", "silicon/");
    public static ItemBasic<?> RandomAccessMemoryChipWafer = new ItemBasic<>(GTIRef.ID, "random_access_memory_chip_wafer", "silicon/");
    public static ItemBasic<?> RandomAccessMemoryChip = new ItemBasic<>(GTIRef.ID, "random_access_memory_chip", "silicon/");
    public static ItemBasic<?> SOCWafer = new ItemBasic<>(GTIRef.ID, "soc_wafer", "silicon/");
    public static ItemBasic<?> SOC = new ItemBasic<>(GTIRef.ID, "soc", "silicon/");

    public static ItemBasic<?> BatteryTantalum = new ItemBattery(GTIRef.ID, "tantalum_capacitor", Tier.ULV, 10000, true).tip("Reusable");
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

    public static final BlockBasic SOLID_SUPER_FUEL = new BlockBasic(GTIRef.ID, "solid_super_fuel", BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(5.0F, 6.0F)){
        @Override
        public Texture[] getTextures() {
            return new Texture[]{new Texture("minecraft:block/coal_block")};
        }
    };

    public static final BlockBasic MINING_PIPE_THIN = new BlockMiningPipe(GTIRef.ID, "mining_pipe_thin", BlockBehaviour.Properties.of(net.minecraft.world.level.material.Material.STONE, MaterialColor.COLOR_GRAY));
    public static final BlockBasic MINING_PIPE = new BlockMiningPipe(GTIRef.ID, "mining_pipe", BlockBehaviour.Properties.of(net.minecraft.world.level.material.Material.STONE, MaterialColor.COLOR_GRAY));
    public static final BlockCasing CASING_ULV = new BlockCasing(GTIRef.ID, "casing_ulv");
    public static final BlockCasing CASING_LV = new BlockCasing(GTIRef.ID, "casing_lv");
    public static final BlockCasing CASING_MV = new BlockCasing(GTIRef.ID, "casing_mv");
    public static final BlockCasing CASING_HV = new BlockCasing(GTIRef.ID, "casing_hv");
    public static final BlockCasing CASING_EV = new BlockCasing(GTIRef.ID, "casing_ev");
    public static final BlockCasing CASING_IV = new BlockCasing(GTIRef.ID, "casing_iv");
    public static final BlockCasing CASING_LUV = new BlockCasing(GTIRef.ID, "casing_luv");
    public static final BlockCasing CASING_ZPM = new BlockCasing(GTIRef.ID, "casing_zpm");
    public static final BlockCasing CASING_UV = new BlockCasing(GTIRef.ID, "casing_uv");
    public static final BlockCasing CASING_UHV = new BlockCasing(GTIRef.ID, "casing_uhv");

    public static final BlockCasing HULL_ULV = new BlockCasing(GTIRef.ID, "hull_ulv");
    public static final BlockCasing HULL_LV = new BlockCasing(GTIRef.ID, "hull_lv");
    public static final BlockCasing HULL_MV = new BlockCasing(GTIRef.ID, "hull_mv");
    public static final BlockCasing HULL_HV = new BlockCasing(GTIRef.ID, "hull_hv");
    public static final BlockCasing HULL_EV = new BlockCasing(GTIRef.ID, "hull_ev");
    public static final BlockCasing HULL_IV = new BlockCasing(GTIRef.ID, "hull_iv");
    public static final BlockCasing HULL_LUV = new BlockCasing(GTIRef.ID, "hull_luv");
    public static final BlockCasing HULL_ZPM = new BlockCasing(GTIRef.ID, "hull_zpm");
    public static final BlockCasing HULL_UV = new BlockCasing(GTIRef.ID, "hull_uv");
    public static final BlockCasing HULL_UHV = new BlockCasing(GTIRef.ID, "hull_uhv");

    public static final BlockColoredWall WOOD_WALL = new BlockColoredWall(GTIRef.ID, AntimatterMaterials.Wood, BlockBehaviour.Properties.of(net.minecraft.world.level.material.Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final BlockColoredWall STEEL_WALL = new BlockColoredWall(GTIRef.ID, Steel);
    public static final BlockColoredWall INVAR_WALL = new BlockColoredWall(GTIRef.ID, Invar);
    public static final BlockColoredWall STAINLESS_STEEL_WALL = new BlockColoredWall(GTIRef.ID, StainlessSteel);
    public static final BlockColoredWall TITANIUM_WALL = new BlockColoredWall(GTIRef.ID, Titanium);
    public static final BlockColoredWall NETHERITE_WALL = new BlockColoredWall(GTIRef.ID, AntimatterMaterials.Netherite);
    public static final BlockColoredWall TUNGSTENSTEEL_WALL = new BlockColoredWall(GTIRef.ID, TungstenSteel);
    public static final BlockColoredWall TUNGSTEN_WALL = new BlockColoredWall(GTIRef.ID, Tungsten);
    public static final BlockCasing LONG_DIST_ITEM_PIPE = new BlockCasing(GTIRef.ID, "long_distance_item_pipe");
    public static final BlockCasing LONG_DIST_FLUID_PIPE = new BlockCasing(GTIRef.ID, "long_distance_fluid_pipe");
    public static final BlockCasing LONG_DIST_WIRE_EV = new BlockCasing(GTIRef.ID, "long_distance_wire_ev", Block.Properties.of(net.minecraft.world.level.material.Material.WOOL).strength(1.0f, 10.0f).sound(SoundType.WOOL));
    public static final BlockCasing LONG_DIST_WIRE_IV = new BlockCasing(GTIRef.ID, "long_distance_wire_iv", Block.Properties.of(net.minecraft.world.level.material.Material.WOOL).strength(1.0f, 10.0f).sound(SoundType.WOOL));
    public static final BlockCasing LONG_DIST_WIRE_LUV = new BlockCasing(GTIRef.ID, "long_distance_wire_luv", Block.Properties.of(net.minecraft.world.level.material.Material.WOOL).strength(1.0f, 10.0f).sound(SoundType.WOOL));
    public static final BlockCasing LONG_DIST_WIRE_ZPM = new BlockCasing(GTIRef.ID, "long_distance_wire_zpm", Block.Properties.of(net.minecraft.world.level.material.Material.WOOL).strength(1.0f, 10.0f).sound(SoundType.WOOL));
    public static final BlockCasing LONG_DIST_WIRE_UV = new BlockCasing(GTIRef.ID, "long_distance_wire_uv", Block.Properties.of(net.minecraft.world.level.material.Material.WOOL).strength(1.0f, 10.0f).sound(SoundType.WOOL));
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
    public static final BlockCasing CASING_TUNGSTEN = new BlockCasing(GTIRef.ID, "tungsten_casing");
    public static final BlockCasing CASING_PLATINUM = new BlockCasing(GTIRef.ID, "platinum_casing");
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

    public static final BlockCasing CASING_FUSION = new BlockCasing(GTIRef.ID, "fusion_casing");

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

    public static final Cable<?> CABLE_RED_ALLOY = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, RedAlloy, 0, Tier.ULV).amps(1)).loss(0.02);
    public static final Cable<?> CABLE_COBALT = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Cobalt, 2, Tier.LV).amps(2)); //L);
    public static final Cable<?> CABLE_LEAD = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Lead, 2, Tier.LV).amps(2));
    public static final Cable<?> CABLE_TIN = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Tin, 1, Tier.LV).amps(1));
    public static final Cable<?> CABLE_ZINC = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Zinc, 1, Tier.LV).amps(1));
    public static final Cable<?> CABLE_SOLDERING_ALLOY = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, SolderingAlloy, 1, Tier.LV).amps(1));
    public static final Cable<?> CABLE_IRON = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, AntimatterMaterials.Iron, 3, Tier.MV).amps(2)); //M);
    public static final Cable<?> CABLE_NICKEL = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Nickel, 3, Tier.MV).amps(3));
    public static final Cable<?> CABLE_CUPRONICKEL = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Cupronickel, 3, Tier.MV).amps(2));
    public static final Cable<?> CABLE_COPPER = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, AntimatterMaterials.Copper, 2, Tier.MV).amps(1));
    public static final Cable<?> CABLE_ANNEALED_COPPER = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, AnnealedCopper, 1, Tier.MV).amps(1));
    public static final Cable<?> CABLE_KANTHAL = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Kanthal, 3, Tier.HV).amps(4)); //H);
    public static final Cable<?> CABLE_GOLD = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, AntimatterMaterials.Gold, 2, Tier.HV).amps(3));
    public static final Cable<?> CABLE_ELECTRUM = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Electrum, 2, Tier.HV).amps(2));
    public static final Cable<?> CABLE_SILVER = AntimatterAPI.register(Cable.class,new Cable<>(GTIRef.ID, Silver, 1, Tier.HV).amps(1));
    public static final Cable<?> CABLE_NICHROME = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Nichrome, 4, Tier.EV).amps(3)); //E);
    public static final Cable<?> CABLE_STEEL = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Steel, 2, Tier.EV).amps(2));
    public static final Cable<?> CABLE_TITANIUM = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Titanium, 2, Tier.EV).amps(4));
    public static final Cable<?> CABLE_ALUMINIUM = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Aluminium, 1, Tier.EV).amps(1));
    public static final Cable<?> CABLE_GRAPHENE = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Graphene, 1, Tier.IV).amps(1)); //I);
    public static final Cable<?> CABLE_OSMIUM = AntimatterAPI.register(Cable.class,new Cable<>(GTIRef.ID, Osmium, 2, Tier.IV).amps(4));
    public static final Cable<?> CABLE_PLATINUM = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Platinum, 1, Tier.IV).amps(2));
    public static final Cable<?> CABLE_TUNGSTEN_STEEL = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, TungstenSteel, 2, Tier.IV).amps(3));
    public static final Cable<?> CABLE_TUNGSTEN = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Tungsten, 2, Tier.IV).amps(1));
    public static final Cable<?> CABLE_HSSG = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, HSSG, 2, Tier.LUV).amps(4)); //LU);
    public static final Cable<?> CABLE_NIOBIUM_TITANIUM = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, NiobiumTitanium, 2, Tier.LUV).amps(4));
    public static final Cable<?> CABLE_VANADIUM_GALLIUM = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, VanadiumGallium, 2, Tier.LUV).amps(4));
    public static final Cable<?> CABLE_YTTRIUM_BARIUM_CUPRATE = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, YttriumBariumCuprate, 4, Tier.LUV).amps(4));
    public static final Cable<?> CABLE_NAQUADAH = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Naquadah, 2, Tier.ZPM).amps(2)); //ZP);
    public static final Cable<?> CABLE_NAQUADAH_ALLOY = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, NaquadahAlloy, 4, Tier.ZPM).amps(2));
    public static final Cable<?> CABLE_DURANIUM = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Duranium, 8, Tier.ZPM).amps(1));
    public static final Cable<?> CABLE_SUPERCONDUCTOR = AntimatterAPI.register(Cable.class, new Cable<>(GTIRef.ID, Superconductor, 0.1, Tier.UHV).amps(4)).loss(0.1); //MA);


    public static final Wire<?> WIRE_RED_ALLOY = (Wire<?>) AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, RedAlloy, 1, Tier.ULV).amps(1)).loss(0.04);
    public static final Wire<?> WIRE_COBALT = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Cobalt, 4, Tier.LV).amps(2)); //L);
    public static final Wire<?> WIRE_LEAD = AntimatterAPI.register(Wire.class,  new Wire<>(GTIRef.ID, Lead, 4, Tier.LV).amps(2));
    public static final Wire<?> WIRE_TIN = AntimatterAPI.register(Wire.class,  new Wire<>(GTIRef.ID, Tin, 2, Tier.LV).amps(1));
    public static final Wire<?> WIRE_ZINC = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Zinc, 2, Tier.LV).amps(1));
    public static final Wire<?> WIRE_SOLDERING_ALLOY = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, SolderingAlloy, 2, Tier.LV).amps(1));
    public static final Wire<?> WIRE_IRON = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, AntimatterMaterials.Iron, 6, Tier.MV).amps(2)); //M);
    public static final Wire<?> WIRE_NICKEL = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Nickel, 6, Tier.MV).amps(3));
    public static final Wire<?> WIRE_CUPRONICKEL = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Cupronickel, 6, Tier.MV).amps(2));
    public static final Wire<?> WIRE_COPPER = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, AntimatterMaterials.Copper, 4, Tier.MV).amps(1));
    public static final Wire<?> WIRE_ANNEALED_COPPER = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, AnnealedCopper, 2, Tier.MV).amps(1));
    public static final Wire<?> WIRE_KANTHAL = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Kanthal, 6, Tier.HV).amps(4)); //H);
    public static final Wire<?> WIRE_GOLD = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, AntimatterMaterials.Gold, 4, Tier.HV).amps(3));
    public static final Wire<?> WIRE_ELECTRUM = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Electrum, 4, Tier.HV).amps(2));
    public static final Wire<?> WIRE_SILVER = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Silver, 2, Tier.HV).amps(1));
    public static final Wire<?> WIRE_NICHROME = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Nichrome, 8, Tier.EV).amps(3)); //E);
    public static final Wire<?> WIRE_STEEL = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Steel, 4, Tier.EV).amps(2));
    public static final Wire<?> WIRE_TITANIUM = AntimatterAPI.register(Wire.class,  new Wire<>(GTIRef.ID, Titanium, 4, Tier.EV).amps(4));
    public static final Wire<?> WIRE_ALUMINIUM = AntimatterAPI.register(Wire.class,new Wire<>(GTIRef.ID, Aluminium, 2, Tier.EV).amps(1));
    public static final Wire<?> WIRE_GRAPHENE = AntimatterAPI.register(Wire.class,  new Wire<>(GTIRef.ID, Graphene, 2, Tier.IV).amps(1)); //I);
    public static final Wire<?> WIRE_OSMIUM = AntimatterAPI.register(Wire.class,new Wire<>(GTIRef.ID, Osmium, 4, Tier.IV).amps(4));
    public static final Wire<?> WIRE_PLATINUM = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Platinum, 2, Tier.IV).amps(2));
    public static final Wire<?> WIRE_TUNGSTEN_STEEL = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, TungstenSteel, 2, Tier.IV).amps(3));
    public static final Wire<?> WIRE_TUNGSTEN = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, Tungsten, 2, Tier.IV).amps(1));
    public static final Wire<?> WIRE_HSSG = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, HSSG, 4, Tier.LUV).amps(4)); //LU);
    public static final Wire<?> WIRE_NIOBIUM_TITANIUM = AntimatterAPI.register(Wire.class,new Wire<>(GTIRef.ID, NiobiumTitanium, 4, Tier.LUV).amps(4));
    public static final Wire<?> WIRE_VANADIUM_GALLIUM = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, VanadiumGallium, 4, Tier.LUV).amps(4));
    public static final Wire<?> WIRE_YTTRIUM_BARIUM_CUPRATE = AntimatterAPI.register(Wire.class, new Wire<>(GTIRef.ID, YttriumBariumCuprate, 8, Tier.LUV).amps(4));
    public static final Wire<?> WIRE_NAQUADAH = AntimatterAPI.register(Wire.class,new Wire<>(GTIRef.ID, Naquadah, 4, Tier.ZPM).amps(2)); //ZP);
    public static final Wire<?> WIRE_NAQUADAH_ALLOY = AntimatterAPI.register(Wire.class,new Wire<>(GTIRef.ID, NaquadahAlloy, 8, Tier.ZPM).amps(2));
    public static final Wire<?> WIRE_DURANIUM = AntimatterAPI.register(Wire.class,new Wire<>(GTIRef.ID, Duranium, 16, Tier.ZPM).amps(1));
    public static final Wire<?> WIRE_SUPERCONDUCTOR = (Wire<?>) AntimatterAPI.register(Wire.class,new Wire<>(GTIRef.ID, Superconductor, 1, Tier.UHV).amps(4)).loss(0.2); //MA);

    public static final FluidPipe<?> FLUID_PIPE_WOOD = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GTIRef.ID, AntimatterMaterials.Wood, 350, false).caps(1).pressures(getPressures(150)));
    public static final FluidPipe<?> FLUID_PIPE_COPPER = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GTIRef.ID, AntimatterMaterials.Copper, 1696, true).caps(1).pressures(getPressures(300)));
    public static final FluidPipe<?> FLUID_PIPE_BRONZE = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GTIRef.ID, Bronze, 1696, true).caps(1).pressures(getPressures(450)));
    // it's not in gt6, not sure whether to keep it or not
    //public static final FluidPipe<?> FLUID_PIPE_BISMUTH_BRONZE = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GTIRef.ID, BismuthBronze, 950, true).caps(1).pressures(800));
    //public static final FluidPipe<?> FLUID_PIPE_BLACK_STEEL = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GTIRef.ID, BlackSteel, 1200, true).caps(1).pressures(900));
    public static final FluidPipe<?> FLUID_PIPE_GOLD = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GTIRef.ID, AntimatterMaterials.Gold, 1671, true).acidProof(true).pressures(getPressures(300)));
    public static final FluidPipe<?> FLUID_PIPE_INVAR = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GTIRef.ID, Invar, 2395, true).caps(1).pressures(getPressures(600)));
    public static final FluidPipe<?> FLUID_PIPE_STEEL = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GTIRef.ID, Steel, 2557, true).caps(1).pressures(getPressures(600)));
    public static final FluidPipe<?> FLUID_PIPE_CHROMIUM = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GTIRef.ID, Chromium, 2725, true).acidProof(true).pressures(getPressures(600)));
    public static final FluidPipe<?> FLUID_PIPE_STAINLESS_STEEL = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GTIRef.ID, StainlessSteel, 2428, true).acidProof(true).caps(1).pressures(getPressures(750)));
    public static final FluidPipe<?> FLUID_PIPE_NETHERRITE = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GTIRef.ID, AntimatterMaterials.Netherite, 2807, true).acidProof(true).caps(1).pressures(getPressures(900)));
    public static final FluidPipe<?> FLUID_PIPE_TUNGSTEN = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GTIRef.ID, Tungsten, 4618, true).acidProof(true).caps(1).pressures(getPressures(1050)));
    public static final FluidPipe<?> FLUID_PIPE_TITANIUM = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GTIRef.ID, Titanium, 1668, true).caps(1).pressures(getPressures(900)));
    public static final FluidPipe<?> FLUID_PIPE_TUNGSTEN_STEEL = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GTIRef.ID, TungstenSteel, 3587, true).caps(1).pressures(getPressures(1200)));
    public static final FluidPipe<?> FLUID_PIPE_TUNGSTEN_CARBIDE = AntimatterAPI.register(FluidPipe.class,new FluidPipe<>(GTIRef.ID, TungstenCarbide, 3837, true).caps(1).pressures(getPressures(1350)));
    public static final FluidPipe<?> FLUID_PIPE_VANADIUM_STEEL = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GTIRef.ID, VanadiumSteel, 2591, true).acidProof(true).pressures(getPressures(1200)));
    public static final FluidPipe<?> FLUID_PIPE_RUBBER = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GTIRef.ID, Rubber, 350, true).caps(1).pressures(getPressures(300)));
    public static final FluidPipe<?> FLUID_PIPE_PLASTIC = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GTIRef.ID, Plastic, 370, true).caps(1).pressures(getPressures(300)));
    public static final FluidPipe<?> FLUID_PIPE_POLY = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GTIRef.ID, Polytetrafluoroethylene, 327, true).caps(1).pressures(getPressures(150)));
    public static final FluidPipe<?> FLUID_PIPE_HP = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GTIRef.ID, HighPressure, 3422, true).sizes(PipeSize.SMALL, PipeSize.NORMAL, PipeSize.LARGE).caps(1).pressures(10000));
    public static final FluidPipe<?> FLUID_PIPE_PLASMA = AntimatterAPI.register(FluidPipe.class, new FluidPipe<>(GTIRef.ID, PlasmaContainment, 100000, true).sizes(PipeSize.NORMAL).caps(1).pressures(100000));

    public static final ItemPipe<?> ITEM_PIPE_BRASS = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, Brass).stepsize(32768).caps(0, 0, 0, 1, 2, 4));
    public static final ItemPipe<?> ITEM_PIPE_CUPRONICKEL = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, Cupronickel).stepsize(32768).caps(0, 0, 0, 1, 2, 4));
    public static final ItemPipe<?> ITEM_PIPE_BLACK_BRONZE = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, BlackBronze).stepsize(16384).caps(0, 0, 0, 2, 4, 8));
    public static final ItemPipe<?> ITEM_PIPE_STERLING_SILVER = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, SterlingSilver).stepsize(16384).caps(0,0,0,2,4,8));
    public static final ItemPipe<?> ITEM_PIPE_ROSE_GOLD = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, RoseGold).stepsize(16384).caps(0,0,0,2,4,8));
    public static final ItemPipe<?> ITEM_PIPE_ELECTRUM = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, Electrum).stepsize(16384).caps(0, 0, 0, 2, 4, 8));
    public static final ItemPipe<?> ITEM_PIPE_MAGNALIUM = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, Magnalium).stepsize(16384).caps(0, 0, 0, 2, 4, 8));
    public static final ItemPipe<?> ITEM_PIPE_PLATINUM = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, Platinum).stepsize(8192).caps(0, 0, 0, 4, 8, 16));
    public static final ItemPipe<?> ITEM_PIPE_OSMIUM = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, Osmium).stepsize(4096).caps(0, 0, 0, 8, 16, 32));
    public static final ItemPipe<?> ITEM_PIPE_ULTIMET = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, Ultimet).stepsize(2048).caps(0, 0, 0, 16, 32, 64));
    //public static final ItemPipe<?> ITEM_PIPE_HC = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, HighCapacity).stepsize(4096).caps(64));
    public static final ItemPipe<?> ITEM_PIPE_OSMIRIDIUM = AntimatterAPI.register(ItemPipe.class, new ItemPipe<>(GTIRef.ID, Osmiridium).stepsize(1024).caps(0, 0, 0, 32, 64, 128));

    //public static final HeatPipe<?> HEAT_PIPE_COPPER = AntimatterAPI.register(HeatPipe.class, new HeatPipe<>(GTIRef.ID, Copper, 386).sizes(PipeSize.SMALL));
    private static int[] getPressures(int basePressure){
        basePressure *= 20;
        return new int[]{basePressure / 6, basePressure / 6, basePressure / 3, basePressure, basePressure * 2, basePressure * 4};
    }
}
