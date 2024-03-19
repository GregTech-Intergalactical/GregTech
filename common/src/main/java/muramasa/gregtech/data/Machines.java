package muramasa.gregtech.data;

import com.google.common.collect.ImmutableMap;
import io.github.gregtechintergalactical.gtcore.data.GTCoreBlocks;
import io.github.gregtechintergalactical.gtcore.machine.DrumMachine;
import muramasa.antimatter.blockentity.single.BlockEntityBatteryBuffer;
import muramasa.antimatter.blockentity.single.BlockEntityDigitalTransformer;
import muramasa.antimatter.blockentity.single.BlockEntityTransformer;
import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.*;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.block.BlockNuclearReactorCore;
import muramasa.gregtech.blockentity.miniportals.BlockEntityMiniEndPortal;
import muramasa.gregtech.blockentity.miniportals.BlockEntityMiniNetherPortal;
import muramasa.gregtech.blockentity.miniportals.BlockEntityMiniPortal;
import muramasa.gregtech.blockentity.miniportals.BlockEntityMiniTwilightPortal;
import muramasa.gregtech.blockentity.multi.*;
import muramasa.gregtech.blockentity.single.*;
import muramasa.gregtech.machine.MiniPortalMachine;
import muramasa.gregtech.machine.MultiblockTankMachine;
import muramasa.gregtech.machine.SteamMachine;
import net.minecraft.ChatFormatting;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.Shapes;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.machine.MachineFlag.*;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.gregtech.data.GregTechCovers.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.*;

public class Machines {
    /**
     ** Steam Singleblock Machines
     **/
    public static SteamMachine COAL_BOILER = new SteamMachine(GTIRef.ID, "coal_boiler").setTiers(BRONZE, STEEL).setMap(COAL_BOILERS).addFlags(GUI, STEAM, ITEM, FLUID, CELL).baseTexture(Textures.BOILER_HANDLER).setTile(BlockEntityCoalBoiler::new).noCovers();
    public static SteamMachine LAVA_BOILER = new SteamMachine(GTIRef.ID, "lava_boiler").setTiers(STEEL).addFlags(GUI, STEAM, ITEM, FLUID).setTile(BlockEntityLavaBoiler::new).noCovers();
    public static SteamMachine SOLAR_BOILER = new SteamMachine(GTIRef.ID, "solar_boiler").setTiers(BRONZE).addFlags(GUI, STEAM, ITEM, FLUID).setTile(BlockEntitySolarBoiler::new).allowFrontIO().noCovers();
    public static SteamMachine STEAM_ALLOY_SMELTER = new SteamMachine(GTIRef.ID, "steam_alloy_smelter").setTiers(BRONZE, STEEL).setMap(RecipeMaps.STEAM_ALLOY_SMELTER).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.FURNACE,  0.6f).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_COMPRESSOR = new SteamMachine(GTIRef.ID, "steam_compressor").setTiers(BRONZE, STEEL).setMap(RecipeMaps.STEAM_COMPRESSOR).addFlags(GUI, ITEM, FLUID).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_EXTRACTOR = new SteamMachine(GTIRef.ID, "steam_extractor").setTiers(BRONZE, STEEL).setMap(RecipeMaps.STEAM_EXTRACTOR).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.EXTRACTOR,  0.6f).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_FORGE_HAMMER = new SteamMachine(GTIRef.ID, "steam_forge_hammer").setTiers(BRONZE, STEEL).setMap(RecipeMaps.STEAM_FORGE_HAMMER).addFlags(GUI, ITEM, FLUID).covers(COVER_STEAM_VENT).setSound(SoundEvents.ANVIL_PLACE, 0.6f);
    public static SteamMachine STEAM_FURNACE = new SteamMachine(GTIRef.ID, "steam_furnace").setTiers(BRONZE, STEEL).setMap(RecipeMaps.STEAM_FURNACE).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.FURNACE,  0.6f).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_MACERATOR = new SteamMachine(GTIRef.ID, "steam_macerator").setTiers(BRONZE, STEEL).setMap(RecipeMaps.STEAM_MACERATOR).addFlags(GUI, ITEM, FLUID).covers(COVER_STEAM_VENT).setSound(GregTechSounds.MACERATOR,  0.6f);
    public static SteamMachine STEAM_SIFTER = new SteamMachine(GTIRef.ID, "steam_sifter").setTiers(BRONZE, STEEL).setMap(RecipeMaps.STEAM_SIFTER).addFlags(GUI, ITEM, FLUID).covers(COVER_STEAM_VENT);
    /**
     ** Hatchless Multiblock Machines (Steam Age)
     **/
    public static BasicMachine CHARCOAL_PIT = new BasicMachine(GTIRef.ID, "charcoal_pit").setTiers(NONE).baseTexture(new Texture(GTIRef.ID, "block/machine/base/charcoal_pit")).setTile(BlockEntityCharcoalPit::new).noCovers().setAmbientTicking();
    public static BasicMultiMachine<?> COKE_OVEN = new BasicMultiMachine<>(GTIRef.ID, "coke_oven").setTiers(NONE).setMap(RecipeMaps.COKE_OVEN).addFlags(GUI, ITEM, FLUID).setTile(BlockEntityCokeOven::new);
    public static BasicMultiMachine<?> PRIMITIVE_BLAST_FURNACE = new BasicMultiMachine<>(GTIRef.ID, "primitive_blast_furnace").setTiers(NONE).setMap(RecipeMaps.PRIMITIVE_BLAST_FURNACE).addFlags(GUI, ITEM).setTile(BlockEntityPrimitiveBlastFurnace::new);
    /**
     ** Electric Singleblock Machines
     **/
    /**
     * Processors
     **/
    public static BasicMachine ALLOY_SMELTER = new BasicMachine(GTIRef.ID, "alloy_smelter").setMap(RecipeMaps.ALLOY_SMELTER).addFlags(GUI, ITEM).setSound(GregTechSounds.FURNACE,  0.6f);
    public static BasicMachine AMP_FABRICATOR = new BasicMachine(GTIRef.ID, "amp_fabricator").setMap(RecipeMaps.AMP_FABRICATOR).addFlags(GUI, ITEM);
    public static BasicMachine ARC_FURNACE = new BasicMachine(GTIRef.ID, "arc_furnace").setMap(RecipeMaps.ARC_FURNACE).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.FURNACE,  0.6f).amps(3);
    public static BasicMachine ASSEMBLER = new BasicMachine(GTIRef.ID, "assembler").setMap(RecipeMaps.ASSEMBLER).setTile(BlockEntityAssembler::new).addFlags(GUI, ITEM, FLUID).setVerticalFacingAllowed(true).custom();
    public static BasicMachine AUTOCLAVE = new BasicMachine(GTIRef.ID, "autoclave").setMap(RecipeMaps.AUTOCLAVE).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine BENDER = new BasicMachine(GTIRef.ID, "bender").setMap(RecipeMaps.BENDER).addFlags(GUI, ITEM);
    public static BasicMachine CANNER = new BasicMachine(GTIRef.ID, "canner").setMap(RecipeMaps.CANNER).addFlags(GUI, ITEM);
    public static BasicMachine CENTRIFUGE = new BasicMachine(GTIRef.ID, "centrifuge").setMap(RecipeMaps.CENTRIFUGE).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine BATH = new BasicMachine(GTIRef.ID, "bath").setTiers(HV).setMap(RecipeMaps.BATH).addFlags(GUI, ITEM, FLUID).baseTexture(new Texture(GTIRef.ID, "block/machine/base/hv"));
    public static BasicMachine DEHYDRATOR = new BasicMachine(GTIRef.ID, "dehydrator").setMap(RecipeMaps.DEHYDRATOR).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine CHEMICAL_REACTOR = new BasicMachine(GTIRef.ID, "chemical_reactor").setMap(RecipeMaps.CHEMICAL_REACTOR).addFlags(GUI, ITEM, FLUID).renderContainedLiquids().custom();
    public static BasicMachine CIRCUIT_ASSEMBLER = new BasicMachine(GTIRef.ID, "circuit_assembler").setMap(RecipeMaps.CIRCUIT_ASSEMBLER).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine COMPRESSOR = new BasicMachine(GTIRef.ID, "compressor").setMap(RecipeMaps.COMPRESSOR).addFlags(GUI, ITEM);
    public static BasicMachine CUTTER = new BasicMachine(GTIRef.ID, "cutter").setMap(RecipeMaps.CUTTER).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine DISASSEMBLER = new BasicMachine(GTIRef.ID, "disassembler").setMap(RecipeMaps.DISASSEMBLER).addFlags(GUI, ITEM).custom();
    public static BasicMachine DISTILLERY = new BasicMachine(GTIRef.ID, "distillery").setMap(RecipeMaps.DISTILLERY).addFlags(GUI, ITEM, FLUID).custom().renderContainedLiquids().setVerticalFacingAllowed(true).setSound(GregTechSounds.EXTRACTOR,  0.6f);
    public static BasicMachine ELECTRIC_OVEN = new BasicMachine(GTIRef.ID, "electric_oven").setMap(RecipeMaps.ELECTRIC_OVEN).addFlags(GUI, ITEM).setSound(GregTechSounds.FURNACE, 0.6f);
    public static BasicMachine ELECTROLYZER = new BasicMachine(GTIRef.ID, "electrolyzer").setMap(RecipeMaps.ELECTROLYZER).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.MAGNETIZER, 0.6f);
    public static BasicMachine ELECTROMAGNETIC_SEPARATOR = new BasicMachine(GTIRef.ID, "electromagnetic_separator").setMap(RecipeMaps.ELECTROMAGNETIC_SEPARATOR).addFlags(GUI, ITEM);
    public static BasicMachine EXTRACTOR = new BasicMachine(GTIRef.ID, "extractor").setMap(RecipeMaps.EXTRACTOR).addFlags(GUI, ITEM).setSound(GregTechSounds.EXTRACTOR,  0.6f);
    public static BasicMachine EXTRUDER = new BasicMachine(GTIRef.ID, "extruder").setMap(RecipeMaps.EXTRUDER).addFlags(GUI, ITEM).custom();
    public static BasicMachine FERMENTER = new BasicMachine(GTIRef.ID, "fermenter").setMap(RecipeMaps.FERMENTER).addFlags(GUI, ITEM, FLUID).custom().renderContainedLiquids();
    public static BasicMachine FLUID_CANNER = new BasicMachine(GTIRef.ID, "fluid_canner").setMap(RecipeMaps.FLUID_CANNER).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.EXTRACTOR,  0.6f);
    public static BasicMachine FLUID_PRESS = new BasicMachine(GTIRef.ID, "fluid_press").setMap(RecipeMaps.FLUID_PRESS).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FLUID_HEATER = new BasicMachine(GTIRef.ID, "fluid_heater").setMap(RecipeMaps.FLUID_HEATER).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FLUID_SOLIDIFIER = new BasicMachine(GTIRef.ID, "fluid_solidifier").setMap(FLUID_SOLIDIFYER).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.EXTRACTOR,  0.6f);
    public static BasicMachine FORGE_HAMMER = new BasicMachine(GTIRef.ID, "forge_hammer").setMap(RecipeMaps.FORGE_HAMMER).addFlags(GUI, ITEM).setSound(SoundEvents.ANVIL_PLACE, 0.6f);
    public static BasicMachine FORMING_PRESS = new BasicMachine(GTIRef.ID, "forming_press").setMap(RecipeMaps.FORMING_PRESS).addFlags(GUI, ITEM);
    public static BasicMachine FURNACE = new BasicMachine(GTIRef.ID, "furnace").setMap(ELECTRIC_FURNACE).addFlags(GUI, ITEM).setSound(GregTechSounds.FURNACE,  0.6f);
    public static BasicMachine LASER_ENGRAVER = new BasicMachine(GTIRef.ID, "laser_engraver").setMap(RecipeMaps.LASER_ENGRAVER).addFlags(GUI, ITEM).setSound(GregTechSounds.MAGNETIZER,  0.6f);
    public static BasicMachine LATHE = new BasicMachine(GTIRef.ID, "lathe").setMap(RecipeMaps.LATHE).addFlags(GUI, ITEM);
    public static BasicMachine MACERATOR = new BasicMachine(GTIRef.ID, "macerator").setMap(RecipeMaps.MACERATOR).custom().addFlags(GUI, ITEM).setGuiTiers(ImmutableMap.<Tier, Tier>builder().put(HV, HV).put(EV, EV).put(IV, IV)).setSound(GregTechSounds.MACERATOR,  0.6f);
    public static BasicMachine MASS_FABRICATOR = new BasicMachine(GTIRef.ID, "mass_fabricator").setMap(RecipeMaps.MASS_FABRICATOR).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine MIXER = new BasicMachine(GTIRef.ID, "mixer").setMap(RecipeMaps.MIXER).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine ORE_WASHER = new BasicMachine(GTIRef.ID, "ore_washer").setMap(RecipeMaps.ORE_WASHER).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine PACKAGER = new BasicMachine(GTIRef.ID, "packager").setMap(RecipeMaps.PACKAGER).addFlags(GUI, ITEM);
    public static BasicMachine POLARIZER = new BasicMachine(GTIRef.ID, "polarizer").setMap(RecipeMaps.POLARIZER).addFlags(GUI, ITEM);
    public static BasicMachine PRINTER = new BasicMachine(GTIRef.ID, "printer").setTiers(Tier.LV).setMap(PRINTING).addFlags(GUI, ITEM, FLUID).setTile(BlockEntityPrinter::new);
    public static BasicMachine ROASTER = new BasicMachine(GTIRef.ID, "roaster").setMap(RecipeMaps.ROASTER).addFlags(GUI, ITEM, FLUID).amps(3);
    public static BasicMachine RECYCLER = new BasicMachine(GTIRef.ID, "recycler").setMap(RecipeMaps.RECYCLER).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine REPLICATOR = new BasicMachine(GTIRef.ID, "replicator").setMap(RecipeMaps.REPLICATOR).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine SCANNER = new BasicMachine(GTIRef.ID, "scanner").setMap(RecipeMaps.SCANNER).addFlags(GUI, ITEM, FLUID).setTile(BlockEntityScanner::new).setSound(GregTechSounds.MAGNETIZER,  0.6f);
    public static BasicMachine SEISMIC_PROSPECTOR = new BasicMachine(GTIRef.ID, "seismic_prospector").setTiers(LV, EV).setTile(BlockEntitySeismicProspector::new).setOutputCover(ICover.emptyFactory);
    public static BasicMachine SIFTER = new BasicMachine(GTIRef.ID, "sifter").setMap(RecipeMaps.SIFTER).addFlags(GUI, ITEM);
    public static BasicMachine SMELTER = new BasicMachine(GTIRef.ID, "smelter").setMap(RecipeMaps.SMELTER).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine THERMAL_CENTRIFUGE = new BasicMachine(GTIRef.ID, "thermal_centrifuge").setMap(RecipeMaps.THERMAL_CENTRIFUGE).addFlags(GUI,ITEM).amps(2);
    public static BasicMachine UNPACKAGER = new BasicMachine(GTIRef.ID, "unpackager").setMap(RecipeMaps.UNPACKAGER).addFlags(GUI, ITEM);
    public static BasicMachine WIRE_MILL = new BasicMachine(GTIRef.ID, "wire_mill").setMap(RecipeMaps.WIRE_MILL).addFlags(GUI, ITEM).custom();
    /**
     * Battery Buffers
     **/
    public static BasicMachine BATTERY_BUFFER_SIXTEEN = new BasicMachine(GTIRef.ID, "16x_battery_buffer").addFlags(GUI, EU, ITEM).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).noCovers().setTile(BlockEntityBatteryBuffer::new).setVerticalFacingAllowed(true).allowFrontIO();
    public static BasicMachine BATTERY_BUFFER_EIGHT = new BasicMachine(GTIRef.ID, "8x_battery_buffer").addFlags(GUI, EU, ITEM).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).noCovers().setTile(BlockEntityBatteryBuffer::new).setVerticalFacingAllowed(true).allowFrontIO();
    public static BasicMachine BATTERY_BUFFER_FOUR = new BasicMachine(GTIRef.ID, "4x_battery_buffer").addFlags(GUI, EU, ITEM).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).noCovers().setTile(BlockEntityBatteryBuffer::new).setVerticalFacingAllowed(true).allowFrontIO();
    public static BasicMachine BATTERY_BUFFER_ONE = new BasicMachine(GTIRef.ID, "1x_battery_buffer").addFlags(GUI, EU, ITEM).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).noCovers().setTile(BlockEntityBatteryBuffer::new).setVerticalFacingAllowed(true).allowFrontIO();
    /**
     * Filters
     **/
    public static BasicMachine ELECTRIC_ITEM_FILTER = new BasicMachine(GTIRef.ID, "electric_item_filter").addFlags(GUI, EU, ITEM).setTile(BlockEntityItemFilter::new).noCovers().frontCovers().allowFrontIO().setVerticalFacingAllowed(true).overlayTexture(Textures.LEFT_RIGHT_HANDLER);
    public static BasicMachine ELECTRIC_TYPE_FILTER = new BasicMachine(GTIRef.ID, "electric_type_filter").addFlags(GUI, EU, ITEM).setTile(BlockEntityTypeFilter::new).noCovers().frontCovers().allowFrontIO().setVerticalFacingAllowed(true).overlayTexture(Textures.LEFT_RIGHT_HANDLER);
    public static BasicMachine SUPER_BUFFER =new BasicMachine(GTIRef.ID, "super_buffer").addFlags(GUI, EU, ITEM).setTile(BlockEntityBuffer::new).setVerticalFacingAllowed(true).allowFrontIO().noCovers().frontCovers().overlayTexture(Textures.LEFT_RIGHT_HANDLER);
    public static BasicMachine CHEST_BUFFER =new BasicMachine(GTIRef.ID, "chest_buffer").addFlags(GUI, EU, ITEM).setTile(BlockEntityBuffer::new).setVerticalFacingAllowed(true).allowFrontIO().noCovers().frontCovers().overlayTexture(Textures.LEFT_RIGHT_HANDLER);
    /**
     * Drums
     */
    public static DrumMachine BRONZE_DRUM = GTCoreBlocks.createDrum(Materials.Bronze, 16000);
    public static DrumMachine STEEL_DRUM = GTCoreBlocks.createDrum(Steel, 48000);
    public static DrumMachine INVAR_DRUM = GTCoreBlocks.createDrum(Materials.Invar, 32000);
    public static DrumMachine STAINLESS_DRUM = GTCoreBlocks.createDrum(Materials.StainlessSteel, 64000).acidProof();
    public static DrumMachine TITANIUM_DRUM = GTCoreBlocks.createDrum(Materials.Titanium, 128000);
    public static DrumMachine NETHERRITE_DRUM = GTCoreBlocks.createDrum(AntimatterMaterials.Netherite, 128000).acidProof();
    public static DrumMachine TUNGSTENSTEEL_DRUM = GTCoreBlocks.createDrum(Materials.TungstenSteel, 256000);
    public static DrumMachine TUNGSTEN_DRUM = GTCoreBlocks.createDrum(Materials.Tungsten, 256000);

    public static MultiblockTankMachine WOOD_TANK = new MultiblockTankMachine(GTIRef.ID, Wood, true, 432000);
    public static MultiblockTankMachine[] STEEL_TANKS = createTankMachine(Steel, 3);
    public static MultiblockTankMachine[] INVAR_TANKS = createTankMachine(Invar, 2);
    public static MultiblockTankMachine[] STAINLESS_STEEL_TANKS = createTankMachine(StainlessSteel, 4);
    public static MultiblockTankMachine[] TITANIUM_TANKS = createTankMachine(Titanium, 8);
    public static MultiblockTankMachine[] NETHERITE_TANKS = createTankMachine(Netherite, 8);
    public static MultiblockTankMachine[] TUNGSTENSTEEL_TANKS = createTankMachine(TungstenSteel, 16);
    public static MultiblockTankMachine[] TUNGSTEN_TANKS = createTankMachine(Tungsten, 16);

    /**
     * Transformers
     **/
    public static BasicMachine TRANSFORMER = new BasicMachine(GTIRef.ID, "transformer").addFlags(EU).setTiers(ULV, LV, MV, HV, EV, IV, LUV, ZPM, UV).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).setTile((v, pos, state) -> new BlockEntityTransformer<>(v, pos, state, 1)).noCovers().allowFrontIO().setVerticalFacingAllowed(true).addTooltipInfo((machine, stack, world, tooltip, flag) -> {
        tooltip.remove(tooltip.size() - 1);
        tooltip.remove(tooltip.size() - 1);
        Tier upper = Tier.getTier(machine.getTier().getVoltage() * 4);
        tooltip.add(Utils.translatable("machine.transformer.voltage_info", Utils.literal(upper.getId().toUpperCase()), Utils.literal(machine.getTier().getId().toUpperCase())));
        tooltip.add(Utils.translatable("machine.voltage.in").append(": ").append(Utils.literal(upper.getVoltage() + " (" + upper.getId().toUpperCase() + ")")).withStyle(ChatFormatting.GREEN));
        tooltip.add(Utils.translatable("machine.voltage.out").append(": ").append(Utils.literal(machine.getTier().getVoltage() + " (" + machine.getTier().getId().toUpperCase() + ")")).withStyle(ChatFormatting.GREEN));
        tooltip.add(Utils.translatable("generic.amp").append(": ").append(Utils.literal(String.valueOf(4)).withStyle(ChatFormatting.YELLOW)));
        tooltip.add(Utils.translatable("machine.power.capacity").append(": ").append(Utils.literal(String.valueOf(512L + machine.getTier().getVoltage() * 8L))).withStyle(ChatFormatting.BLUE));
    });
    public static BasicMachine ADJUSTABLE_TRANSFORMER = new BasicMachine(GTIRef.ID, "adjustable_transformer").setTiers(EV, IV).addFlags(GUI, EU).setTile(BlockEntityDigitalTransformer::new).noCovers().allowFrontIO();
    /**
     ** Generators
     **/
    public static GeneratorMachine COMBUSTION_GENERATOR = new GeneratorMachine(GTIRef.ID, "combustion_generator").setTiers(LV, MV, HV).setMap(COMBUSTION_FUELS).addFlags(GUI, ITEM, FLUID, CELL).allowFrontIO();
    public static GeneratorMachine SEMIFLUID_GENERATOR = new GeneratorMachine(GTIRef.ID, "semifluid_generator").setTiers(LV, MV, HV).setMap(SEMI_FUELS).addFlags(GUI, ITEM, FLUID, CELL).allowFrontIO();
    public static GeneratorMachine GAS_GENERATOR = new GeneratorMachine(GTIRef.ID, "gas_turbine").setTiers(LV, MV, HV).setMap(GAS_FUELS).addFlags(GUI, ITEM, FLUID, CELL).allowFrontIO().custom();
    public static GeneratorMachine NAQUADAH_GENERATOR = new GeneratorMachine(GTIRef.ID, "naquadah_reactor").setTiers(EV, IV, LUV).setMap(NAQUADAH_FUELS).addFlags(GUI, ITEM, FLUID, CELL).allowFrontIO();
    public static GeneratorMachine STEAM_GENERATOR = new GeneratorMachine(GTIRef.ID, "steam_turbine").setTiers(LV, MV, HV).setMap(STEAM_FUELS).addFlags(GUI, ITEM, FLUID, CELL).setTile(BlockEntitySteamTurbine::new).efficiency(t -> {
        return t.getIntegerId() + 6;
    }).allowFrontIO().custom();
    public static GeneratorMachine SOLAR_PANEL = new GeneratorMachine(GTIRef.ID, "solar_panel").setTiers(NONE, ULV, LV).addFlags(GUI).removeFlags(COVERABLE).customShape(Shapes.box(0,0,0, 1, 0.5, 1)).itemModelParent(new ResourceLocation(GTIRef.ID, "block/preset/solar_panel")).setVerticalFacingAllowed(false).setTile(BlockEntitySolarPanel::new).custom().addTooltipInfo((machine, stack, world, tooltip, flag) -> {
        if (machine.getTier() == NONE){
            tooltip.add(Utils.translatable("machine.voltage.out").append(": ").append(Utils.literal(1 + "")).withStyle(ChatFormatting.GREEN));
            tooltip.add(Utils.translatable("machine.power.capacity").append(": ").append(Utils.literal("" + 80).withStyle(ChatFormatting.BLUE)));
        }
    });
    public static BasicMachine NUCLEAR_REACTOR_CORE = new BasicMachine(GTIRef.ID, "nuclear_reactor_core").setTiers(NONE).addFlags(GUI, ITEM, FLUID).custom().overlayTexture(Textures.REACTOR_CORE_OVERLAY_HANDLER).baseTexture(new Texture(GTIRef.ID, "block/machine/base/nuclear_reactor_core")).setTile(BlockEntityNuclearReactorCore::new).setBlock(BlockNuclearReactorCore::new).setItemBlockClass(() -> BlockNuclearReactorCore.class).frontCovers().allowFrontIO().setNoTextureRotation(true).setOutputCover(GregTechCovers.COVER_REACTOR_OUTPUT).covers(ICover.emptyFactory, ICover.emptyFactory, GregTechCovers.COVER_REACTOR_OUTPUT, GregTechCovers.COVER_REACTOR_OUTPUT_SECONDARY, ICover.emptyFactory, ICover.emptyFactory);
    /**
     ** Multiblock Hatch Machines (Electrical Age)
     **/
    public static MultiMachine ADVANCED_MINER = new MultiMachine(GTIRef.ID, "advanced_miner").setTiers(LV).addFlags(GUI, ITEM, EU).setTile(BlockEntityAdvancedMiner::new).setTextureBlock(GregTechBlocks.CASING_SOLID_STEEL);
    public static MultiMachine BLAST_FURNACE = new MultiMachine(GTIRef.ID, "electric_blast_furnace").setTiers(LV).setMap(E_BLAST_FURNACE).addFlags(GUI, ITEM, FLUID, EU).setTile(BlockEntityElectricBlastFurnace::new).custom().setTextureBlock(GregTechBlocks.CASING_HEAT_PROOF);
    public static MultiMachine COMBUSTION_ENGINE = new MultiMachine(GTIRef.ID, "combustion_engine").setTiers(EV).setMap(COMBUSTION_FUELS).addFlags(GUI, FLUID, EU).setTile(BlockEntityCombustionEngine::new).custom().setTextureBlock(GregTechBlocks.CASING_TITANIUM);
    public static MultiMachine CRACKING_UNIT = new MultiMachine(GTIRef.ID, "cracking_unit").setTiers(HV).setMap(CRACKING).addFlags(GUI, ITEM, FLUID, EU).setTile(BlockEntityOilCrackingUnit::new).custom().setTextureBlock(GregTechBlocks.CASING_STAINLESS_STEEL);
    public static MultiMachine DISTLLATION_TOWER = new MultiMachine(GTIRef.ID, "distillation_tower").setTiers(HV).setMap(DISTILLATION).addFlags(GUI, ITEM, FLUID, EU).setTile(BlockEntityDistillationTower::new).custom().setTextureBlock(GregTechBlocks.CASING_STAINLESS_STEEL);
    public static MultiMachine CRYO_DISTLLATION_TOWER = new MultiMachine(GTIRef.ID, "cryo_distillation_tower").setTiers(HV).setMap(CRYO_DISTILLATION).addFlags(GUI, ITEM, FLUID, EU).setTile(BlockEntityDistillationTower::new).custom().setTextureBlock(GregTechBlocks.CASING_FROST_PROOF);
    public static MultiMachine FUSION_REACTOR = new MultiMachine(GTIRef.ID, "fusion_control_computer").setTiers(LUV).setMap(FUSION).addFlags(GUI, FLUID, EU).setTile(BlockEntityFusionReactor::new).setTextureBlock(GregTechBlocks.CASING_FUSION);
    public static MultiMachine HEAT_EXCHANGER = new MultiMachine(GTIRef.ID, "heat_exchanger").setTiers(EV).setMap(RecipeMaps.HEAT_EXCHANGER).addFlags(GUI, FLUID, EU).setTile(BlockEntityHeatExchanger::new).custom().setTextureBlock(GregTechBlocks.CASING_TITANIUM);
    public static MultiMachine IMPLOSION_COMPRESSOR = new MultiMachine(GTIRef.ID, "implosion_compressor").setTiers(HV).setMap(RecipeMaps.IMPLOSION_COMPRESSOR).addFlags(GUI, ITEM, EU).setTile(BlockEntityImplosionCompressor::new).setTextureBlock(GregTechBlocks.CASING_SOLID_STEEL);
    public static MultiMachine LARGE_BOILER = new MultiMachine(GTIRef.ID, "large_boiler").setTiers(LV, MV, HV, EV).addFlags(GUI, ITEM, FLUID).setMap(LARGE_BOILERS).setTile(BlockEntityLargeBoiler::new).custom().setTierSpecificLang().addTooltipInfo((machine, stack, world, tooltip, flag) -> {
        double total = machine.getTier() == LV ? 32000 : machine.getTier() == MV ? 36000 : machine.getTier() == HV ? 41600 : 48000;
        double production = machine.getTier() == LV ? 16000 : machine.getTier() == MV ? 24000 : machine.getTier() == HV ? 32000 : 40000;
        tooltip.add(Utils.translatable("machine.gti.large_boiler.production", total, production));
        tooltip.add(Utils.translatable("machine.gti.large_boiler.circuit"));
    });
    public static MultiMachine LARGE_CENTRIFUGE = new MultiMachine(GTIRef.ID, "large_centrifuge").setTiers(HV).setMap(RecipeMaps.CENTRIFUGE).addFlags(GUI, ITEM, FLUID, EU).setTile(BlockEntityLargeCentrifuge::new).setTextureBlock(GregTechBlocks.CASING_TUNGSTENSTEEL);
    public static MultiMachine LARGE_CHEMICAL_REACTOR = new MultiMachine(GTIRef.ID, "large_chemical_reactor").setTiers(HV).setMap(RecipeMaps.CHEMICAL_REACTOR).addFlags(GUI, ITEM, FLUID, EU).setTile(BlockEntityLargeChemicalReactor::new).custom().setTextureBlock(GregTechBlocks.CASING_CHEMICALLY_INERT);
    public static MultiMachine LARGE_ELECTROLYZER = new MultiMachine(GTIRef.ID, "large_electrolyzer").setTiers(HV).setMap(RecipeMaps.ELECTROLYZER).addFlags(GUI, ITEM, FLUID, EU).setTile(BlockEntityLargeElectrolyzer::new).setTextureBlock(GregTechBlocks.CASING_STAINLESS_STEEL);
    public static MultiMachine LARGE_MACERATOR = new MultiMachine(GTIRef.ID, "large_macerator").setTiers(HV).setMap(RecipeMaps.MACERATOR).addFlags(GUI, ITEM, EU).setTile(BlockEntityLargeMacerator::new).setTextureBlock(GregTechBlocks.CASING_TUNGSTENSTEEL);
    public static MultiMachine LARGE_TURBINE = new MultiMachine(GTIRef.ID, "large_turbine").setTiers(HV, EV, IV).setMap(STEAM_FUELS, HV).setMap(HP_STEAM_FUELS, IV).setMap(GAS_FUELS, EV).addFlags(GUI, ITEM, FLUID, EU, GENERATOR).setTile(BlockEntityLargeTurbine::new).custom(Textures.TURBINE).setTierSpecificLang();
    public static MultiMachine MULTI_SMELTER = new MultiMachine(GTIRef.ID, "multi_smelter").setTiers(HV).setMap(ELECTRIC_FURNACE).addFlags(GUI, ITEM, EU).setTile(BlockEntityMultiSmelter::new).custom().setTextureBlock(GregTechBlocks.CASING_HEAT_PROOF);
    public static MultiMachine OIL_DRILLING_RIG = new MultiMachine(GTIRef.ID, "oil_drilling_rig").setTiers(MV).addFlags(GUI, ITEM, FLUID, EU).setTile(BlockEntityOilDrillingRig::new).custom().setTextureBlock(GregTechBlocks.CASING_SOLID_STEEL);
    public static MultiMachine PROCESSING_ARRAY = new MultiMachine(GTIRef.ID, "processing_array").setTiers(EV).addFlags(GUI, ITEM, FLUID, EU, RECIPE).setTile(BlockEntityProcessingArray::new).custom().setTextureBlock(GregTechBlocks.CASING_TUNGSTENSTEEL);
    public static MultiMachine PYROLYSIS_OVEN = new MultiMachine(GTIRef.ID, "pyrolysis_oven").setTiers(MV).setMap(RecipeMaps.PYROLYSIS_OVEN).addFlags(GUI, ITEM, FLUID, EU).setTile(BlockEntityPyrolysisOven::new).custom().setTextureBlock(GregTechBlocks.CASING_ULV);
    public static MultiMachine TREE_GROWTH_SIMULATOR = new MultiMachine(GTIRef.ID, "tree_growth_simulator").setTiers(LV).setMap(RecipeMaps.TREE_GROWTH_SIMULATOR).addFlags(GUI, ITEM, FLUID, EU).setTile(BlockEntityTreeGrowthSimulator::new).setTextureBlock(GregTechBlocks.CASING_PLASTIC);
    public static MultiMachine VACUUM_FREEZER = new MultiMachine(GTIRef.ID, "vacuum_freezer").setTiers(HV).setMap(RecipeMaps.VACUUM_FREEZER).addFlags(GUI, ITEM, FLUID, EU).setTile(BlockEntityVacuumFreezer::new).setTextureBlock(GregTechBlocks.CASING_FROST_PROOF);
    /**
     * Long distance pipelines
     */
    public static BasicMultiMachine<?> LONG_DISTANCE_FLUID_ENDPOINT = new BasicMultiMachine<>(GTIRef.ID,"long_distance_fluid_endpoint").allowFrontIO().setTiers(NONE).addFlags(FLUID).setTile(BlockEntityLongDistancePipeEndpoint::new);
    public static BasicMultiMachine<?> LONG_DISTANCE_ITEM_ENDPOINT = new BasicMultiMachine<>(GTIRef.ID,"long_distance_item_endpoint").allowFrontIO().setTiers(NONE).addFlags(ITEM).setTile(BlockEntityLongDistancePipeEndpoint::new);
    public static BasicMultiMachine<?> LONG_DISTANCE_TRANSFORMER_ENDPOINT = new BasicMultiMachine<>(GTIRef.ID,"long_distance_transformer_endpoint").allowFrontIO().setTiers(EV, IV, LUV, ZPM, UV).addFlags(EU).setTile(BlockEntityLongDistancePipeEndpoint::new).overlayTexture(Textures.STATE_IGNORANT_TIER_SPECIFIC_OVERLAY_HANDLER).baseTexture((m, tier, state) -> new Texture[]{tier.getBaseTexture(m.getDomain())});

    /**
     ** Hatches
     **/
    public static HatchMachine HATCH_DYNAMO = new HatchMachine(GTIRef.ID, "dynamo_hatch", COVER_DYNAMO_COLORED).addFlags(EU).overlayTexture(Textures.HATCH_OVERLAY_HANDLER);
    public static HatchMachine HATCH_ENERGY = new HatchMachine(GTIRef.ID, "energy_hatch", COVER_ENERGY_COLORED).addFlags(EU).overlayTexture(Textures.HATCH_OVERLAY_HANDLER);
    public static HatchMachine HATCH_FLUID_I = new HatchMachine(GTIRef.ID, "fluid_input_hatch", COVERINPUT).addFlags(GUI, FLUID, CELL);
    public static HatchMachine HATCH_FLUID_O = new HatchMachine(GTIRef.ID, "fluid_output_hatch", COVEROUTPUT).addFlags(GUI, FLUID, CELL);
    //public static final HeatHatch HATCH_HEAT_COPPER = new HeatHatch(GTIRef.ID, "copper_heat", Copper, 386);
    public static HatchMachine HATCH_ITEM_I = new HatchMachine(GTIRef.ID, "item_input_hatch", COVERINPUT).addFlags(GUI, ITEM);
    public static HatchMachine HATCH_MUFFLER = new HatchMachine(GTIRef.ID, "muffler_hatch", COVERMUFFLER).addFlags(GUI, ITEM).setClientTicking();
    public static HatchMachine HATCH_ITEM_O = new HatchMachine(GTIRef.ID, "item_output_hatch", COVEROUTPUT).addFlags(GUI, ITEM);
    /**
     ** Tanks
     **/
    public static TankMachine QUANTUM_TANK = new TankMachine(GTIRef.ID, "quantum_tank", t -> (int) (1602000 * Math.pow(6,  (t.getIntegerId() - 1)))).addFlags(BASIC, GUI, CELL).frontCovers();

    public static BasicMachine PUMP = new BasicMachine(GTIRef.ID, "electric_pump").addFlags(FLUID).setVerticalFacingAllowed(true).setTile(BlockEntityPump::new).noCovers();
    public static BasicMachine CROP_HARVESTER = new BasicMachine(GTIRef.ID, "crop_harvester").setTiers(LV).addFlags(GUI, ITEM).setTile(BlockEntityCropHarvester::new);
    public static BasicMachine MINIATURE_NETHER_PORTAL = new MiniPortalMachine(GTIRef.ID, "miniature_nether_portal").baseTexture(new Texture("block/obsidian")).overlayTexture(Textures.MINI_NETHER_PORTAL).setBlock((machine, tier) -> new BlockMachine(machine, tier, BlockBehaviour.Properties.of(WRENCH_MATERIAL).strength(1.0f, 10.0f).sound(SoundType.STONE).requiresCorrectToolForDrops().noOcclusion())).setTile(BlockEntityMiniNetherPortal::new);
    public static BasicMachine MINIATURE_END_PORTAL = new MiniPortalMachine(GTIRef.ID, "miniature_end_portal").baseTexture(new Texture("block/end_portal_frame_top")).overlayTexture(Textures.MINI_END_PORTAL).setBlock((machine, tier) -> new BlockMachine(machine, tier, BlockBehaviour.Properties.of(WRENCH_MATERIAL).strength(3.0f, 9.0f).sound(SoundType.STONE).requiresCorrectToolForDrops().noOcclusion())).setTile(BlockEntityMiniEndPortal::new);
    public static BasicMachine MINIATURE_TWILIGHT_PORTAL = new MiniPortalMachine(GTIRef.ID, "miniature_twilight_portal").baseTexture(new Texture("block/grass_block_top")).overlayTexture(Textures.MINI_TWILIGHT_PORTAL).setBlock((machine, tier) -> new BlockMachine(machine, tier, BlockBehaviour.Properties.of(WRENCH_MATERIAL).strength(1.0f, 10.0f).sound(SoundType.STONE).requiresCorrectToolForDrops().noOcclusion())).setTile(BlockEntityMiniTwilightPortal::new).blockColorHandler((state, world, pos, machine, i) -> {
        if (machine != null && i == 1){
            if (machine.getMachineState() != MachineState.ACTIVE){
                Biome biome = machine.getLevel().getBiome(pos).value();
                return biome.getWaterColor();//0x3f76e4;
            }
        }
        return i == 0 ? 0x00FF00 : -1;
    }).itemColorHandler((stack, block, i) -> i == 0 ? 0x00FF00 : -1);

    /**
     ** Creative Machines
     **/
    public static TankMachine INFINITE_STEAM = new TankMachine(GTIRef.ID, "infinite_steam").addFlags(FLUID, CELL, GUI).setTile(BlockEntityInfiniteFluid::new).setTiers(LV);

    private static MultiblockTankMachine[] createTankMachine(Material material, int multiplier){
        MultiblockTankMachine[] multiblockTankMachines = {
                new MultiblockTankMachine(GTIRef.ID, material, true, 432 * multiplier * 1000),
                new MultiblockTankMachine(GTIRef.ID, material, false, 2000 * multiplier * 1000)
        };
        if (material == StainlessSteel || material == Netherite){
            multiblockTankMachines[0].acidProof();
            multiblockTankMachines[1].acidProof();
        }
        return multiblockTankMachines;
    }

    public static void init() {
        BATH.removeFlags(EU);
        NUCLEAR_REACTOR_CORE.removeFlags(EU);
    }
}
