package muramasa.gregtech;

import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.Ref;
import muramasa.antimatter.common.event.CommonEvents;
import muramasa.antimatter.datagen.AntimatterDynamics;
import muramasa.antimatter.datagen.providers.*;
import muramasa.antimatter.event.CraftingEvent;
import muramasa.antimatter.event.ProvidersEvent;
import muramasa.antimatter.integration.jeirei.AntimatterJEIREIPlugin;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.mixin.LivingEntityAccessor;
import muramasa.antimatter.recipe.loader.IRecipeRegistrate;
import muramasa.antimatter.registration.IAntimatterRegistrar;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.block.BlockAsphalt;
import muramasa.gregtech.data.Machines;
import muramasa.gregtech.data.*;
import muramasa.gregtech.datagen.*;
import muramasa.gregtech.integration.AppliedEnergisticsRegistrar;
import muramasa.gregtech.integration.SpaceModRegistrar;
import muramasa.gregtech.integration.rei.REIRegistrar;
import muramasa.gregtech.loader.crafting.*;
import muramasa.gregtech.loader.items.Circuitry;
import muramasa.gregtech.loader.machines.*;
import muramasa.gregtech.loader.machines.generator.CoalBoilerLoader;
import muramasa.gregtech.loader.machines.generator.Fuels;
import muramasa.gregtech.loader.machines.generator.LargeBoilerLoader;
import muramasa.gregtech.loader.multi.*;
import muramasa.gregtech.proxy.CommonHandler;
import muramasa.gregtech.proxy.ServerHandler;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class GregTech extends AntimatterMod {

    public static GregTech INSTANCE;
    public static Logger LOGGER = LogManager.getLogger(GTIRef.ID);

    public GregTech() {
        super();
    }

    @Override
    public void onRegistrarInit() {
        super.onRegistrarInit();
        new AppliedEnergisticsRegistrar();
        new SpaceModRegistrar();
        LOGGER.info("Loading GregTech");
        INSTANCE = this;
        ServerHandler.setup();


        AntimatterDynamics.clientProvider(GTIRef.ID,
                () -> new AntimatterBlockStateProvider(GTIRef.ID, GTIRef.NAME + " BlockStates"));
        AntimatterDynamics.clientProvider(GTIRef.ID,
                () -> new AntimatterItemModelProvider(GTIRef.ID, GTIRef.NAME + " Item Models"));
        AntimatterDynamics.clientProvider(GTIRef.ID, GregTechLocalizations.en_US::new);
        GregTechConfig.createConfig();
    }

    public static void onProviders(ProvidersEvent ev) {
        final AntimatterBlockTagProvider[] p = new AntimatterBlockTagProvider[1];
        ev.addProvider(GTIRef.ID, () -> {
            p[0] = new GregTechBlockTagProvider(GTIRef.ID, GTIRef.NAME.concat(" Block Tags"), false);
            return p[0];
        });
        ev.addProvider(GTIRef.ID, () -> new GregTechItemTagProvider(GTIRef.ID, GTIRef.NAME.concat(" Item Tags"),
                false, p[0]));
        ev.addProvider(GTIRef.ID, () -> new AntimatterFluidTagProvider(GTIRef.ID,
                GTIRef.NAME.concat(" Fluid Tags"), false));
        ev.addProvider(GTIRef.ID, () -> new AntimatterAdvancementProvider(GTIRef.ID,
                GTIRef.NAME.concat(" Advancements"), new ProgressionAdvancements()));
        ev.addProvider(GTIRef.ID,
                () -> new GregtechBlockLootProvider(GTIRef.ID, GTIRef.NAME.concat(" Loot generator")));
    }
    
    public static void registerCraftingLoaders(CraftingEvent event) {
        event.addLoader(Miscellaneous::loadRecipes);
        event.addLoader(Smelting::loadRecipes);
        event.addLoader(WireCablesPlates::loadRecipes);
        event.addLoader(VanillaExtensions::loadRecipes);
        event.addLoader(muramasa.gregtech.loader.crafting.Machines::loadRecipes);
        event.addLoader(SteamMachines::loadRecipes);
        event.addLoader(BlockParts::loadRecipes);
        event.addLoader(Parts::loadRecipes);
        event.addLoader(Circuitry::loadCraftingRecipes);
        event.addLoader(ElectricToolRecipes::loadRecipes);
        event.addLoader(MaterialCrafting::loadRecipes);
        event.addLoader(WoodCrafting::loadRecipes);
        if (AntimatterAPI.isModLoaded(Ref.MOD_AE)){
            event.addLoader(AppliedEnergisticsRegistrar::craftingRecipes);
        }
    }

    public static void registerRecipeLoaders(IAntimatterRegistrar registrar, IRecipeRegistrate reg) {
        BiConsumer<String, IRecipeRegistrate.IRecipeLoader> loader = (a, b) -> reg.add(GTIRef.ID, a, b);
        loader.accept("alloy_smelter", AlloySmelterLoader::init);
        loader.accept("arc_furnace", ArcFurnaceLoader::init);
        loader.accept("assembler", AssemblerLoader::init);
        loader.accept("autoclave", Autoclave::init);
        loader.accept("bath", BathLoader::init);
        loader.accept("bender", BenderLoader::init);
        loader.accept("canner", CannerLoader::init);
        loader.accept("centrifuge", CentrifugeLoader::init);
        loader.accept("chemical_reactor", ChemicalReactorLoader::init);
        loader.accept("circuitry", Circuitry::init);
        loader.accept("coal_boiler", CoalBoilerLoader::init);
        loader.accept("compressor", CompressorLoader::init);
        loader.accept("cutter", CutterLoader::init);
        loader.accept("dehydrator", Dehydrator::init);
        loader.accept("distillery", DistilleryLoader::init);
        loader.accept("electrolyzer", ElectrolyzerLoader::init);
        loader.accept("electromagnetic_separator", ElectromagneticSeparatorLoader::init);
        loader.accept("extractor", ExtractorLoader::init);
        loader.accept("extruder", ExtruderLoader::init);
        loader.accept("fermenter", FermenterLoader::init);
        loader.accept("fluid_canner", FluidCannerLoader::init);
        loader.accept("fluid_heater", FluidHeaterLoader::init);
        loader.accept("fluid_press", FluidPressLoader::init);
        loader.accept("fluid_solidifier", FluidSolidifierLoader::init);
        loader.accept("forge_hammer", ForgeHammerLoader::init);
        loader.accept("forming_press", FormingPressLoader::init);
        loader.accept("fuels", Fuels::init);
        loader.accept("laser_engraver", LaserEngraverLoader::init);
        loader.accept("lathe", LatheLoader::init);
        loader.accept("macerator_auto", MaceratorLoader::initAuto);
        loader.accept("macerator", MaceratorLoader::init);
        loader.accept("mixer", MixerLoader::init);
        loader.accept("ore_byproducts", OreByproducts::init);
        loader.accept("ore_washer", OreWasherLoader::init);
        loader.accept("packager", PackagerLoader::init);
        loader.accept("polarizer", PolarizerLoader::init);
        loader.accept("printer", PrinterLoader::init);
        loader.accept("roaster", RoasterLoader::init);
        loader.accept("scanner", ScannerLoader::init);
        loader.accept("sifter", SifterLoader::init);
        loader.accept("smelter", SmelterLoader::init);
        loader.accept("thermal_centrifuge", ThermalCentrifugeLoader::init);
        loader.accept("uu_matter", UUMatterLoader::init);
        loader.accept("unpackager", UnpackagerLoader::init);
        loader.accept("wiremill", WiremillLoader::init);
        //multiblocks
        loader.accept("blast_furnace", BlastFurnaceLoader::init);
        loader.accept("coke_oven", CokeOvenLoader::init);
        loader.accept("cracking_unit", CrackingUnitLoader::init);
        loader.accept("distillation_tower", DistillationTowerLoader::init);
        loader.accept("fusion_reactor", FusionReactorLoader::init);
        loader.accept("heat_exchanger", HeatExchangerLoader::init);
        loader.accept("implosion_compressor", ImplosionCompressorLoader::init);
        loader.accept("large_boiler", LargeBoilerLoader::init);
        loader.accept("pyrolysis_oven", PyrolysisOvenLoader::init);
        loader.accept("tree_growth_simulator", TreeGrowthSimulatorLoader::init);
        loader.accept("vacuum_freezer", VacuumFreezerLoader::init);
        if (AntimatterAPI.isModLoaded(Ref.MOD_AE)){
            loader.accept("ae2", AppliedEnergisticsRegistrar::machineRecipes);
        }
    }

    public static <T> T get(Class<? extends T> clazz, String id) {
        return AntimatterAPI.get(clazz, id, GTIRef.ID);
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {
        switch (event) {
            case DATA_INIT -> {
                ToolTypes.init();
                Materials.init();
                TierMaps.init();
                GregTechData.init(side);
                GregTechCovers.init();
                GregTechItems.init();
                GregTechBlocks.init();
                Machines.init();
                MenuHandlers.init();
                Guis.init(side);
                Models.init();
                GregTechSounds.init();
                if (AntimatterAPI.isModLoaded(Ref.MOD_REI) && side.isClient()){
                    REIRegistrar.init();
                }
                CommonEvents.addPlayerTickCallback((end, logicalServer, player) -> {
                    if (!end && logicalServer && (((LivingEntityAccessor)player).getLastPos() == null || !((LivingEntityAccessor)player).getLastPos().equals(player.blockPosition()))){
                        BlockState state = player.getLevel().getBlockState(player.getOnPos());
                        AttributeInstance attributeinstance = player.getAttribute(Attributes.MOVEMENT_SPEED);
                        if (state.isAir()) state = player.getLevel().getBlockState(player.getOnPos().below());
                        if (attributeinstance == null) {
                            return;
                        }
                        if (state.is(GregTechTags.ASPHALT)){
                            if (attributeinstance.getModifier(BlockAsphalt.SPEED_MODIFIER) == null){
                                attributeinstance.addTransientModifier(new AttributeModifier(BlockAsphalt.SPEED_MODIFIER, "Asphalt speed modification", GregTechConfig.ASPHALT_MULTIPLIER.get(), AttributeModifier.Operation.MULTIPLY_BASE));
                            } 
                        } else {
                            if (attributeinstance.getModifier(BlockAsphalt.SPEED_MODIFIER) != null){
                                attributeinstance.removeModifier(BlockAsphalt.SPEED_MODIFIER);
                            }
                        }
                    }
                });
            }
            case DATA_READY -> {
                Structures.init();
                GregTechTwilightStalctites.init();
                GTRemapping.init();
                AntimatterJEIREIPlugin.addItemsToHide(GregTechBlocks.LAVA);
                AntimatterJEIREIPlugin.addItemsToHide(l -> {
                    IAntimatterTool screwdriver_mv = AntimatterAPI.get(IAntimatterTool.class, "electric_screwdriver_mv");
                    IAntimatterTool screwdriver_hv = AntimatterAPI.get(IAntimatterTool.class, "electric_screwdriver_hv");
                    l.addAll(Arrays.asList(screwdriver_mv.getItem(), screwdriver_hv.getItem()));
                    if (!GregTechConfig.HARDER_CIRCUITS){
                        l.addAll(Arrays.asList(GTCoreItems.CircuitBoardPhenolic));
                        l.addAll(Arrays.asList(GregTechItems.CircuitWetware, GregTechItems.MicroProcessor, GregTechItems.IntegratedProcessor, GregTechItems.NanoProcessor, GregTechItems.QuantumProcessor));
                    }
                });
                AntimatterPlatformUtils.setBurnTime(GregTechBlocks.SOLID_SUPER_FUEL.asItem(), 100000);
                AntimatterPlatformUtils.setBurnTime(GregTechItems.WoodPellet, 200);
                AntimatterPlatformUtils.setFlammability(GregTechBlocks.WOOD_WALL, 5, 20);
                AntimatterPlatformUtils.setFlammability(Machines.WOOD_TANK.getBlockState(Tier.NONE), 5, 20);
                CommonHandler.setup();
              //  if (side == Dist.CLIENT) StructureInfo.init();
                TierMaps.providerInit();
            }
            case CLIENT_DATA_INIT -> ClientData.init();
        }
    }

    @Override
    public String getId() {
        return GTIRef.ID;
    }
}
