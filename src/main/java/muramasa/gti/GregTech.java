package muramasa.gti;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.datagen.ExistingFileHelperOverride;
import muramasa.antimatter.datagen.providers.*;
import muramasa.antimatter.recipe.loader.IRecipeRegistrate;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.gti.data.*;
import muramasa.gti.datagen.GregTechBlockTagProvider;
import muramasa.gti.datagen.GregTechRecipes;
import muramasa.gti.datagen.ProgressionAdvancements;
import muramasa.gti.loader.WorldGenLoader;
import muramasa.gti.loader.machines.*;
import muramasa.gti.loader.machines.generator.Fuels;
import muramasa.gti.loader.multi.Blasting;
import muramasa.gti.loader.multi.VacFreezer;
import muramasa.gti.proxy.ClientHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(Ref.ID)
public class GregTech extends AntimatterMod {

    public static GregTech INSTANCE;
    public static Logger LOGGER = LogManager.getLogger(Ref.ID);

    public GregTech() {
        super();
        INSTANCE = this;
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        final AntimatterBlockTagProvider[] p = new AntimatterBlockTagProvider[1];

        AntimatterAPI.addProvider(Ref.ID, g -> new AntimatterBlockStateProvider(Ref.ID, Ref.NAME + " BlockStates", g));
        AntimatterAPI.addProvider(Ref.ID, g -> new AntimatterItemModelProvider(Ref.ID, Ref.NAME + " Item Models", g));
        AntimatterAPI.addProvider(Ref.ID, g -> {
            p[0] = new GregTechBlockTagProvider(Ref.ID, Ref.NAME.concat(" Block Tags"), false, g, new ExistingFileHelperOverride());
            return p[0];
        });
        AntimatterAPI.addProvider(Ref.ID, g ->
                          new AntimatterItemTagProvider(Ref.ID,Ref.NAME.concat(" Item Tags"), false, g, p[0], new ExistingFileHelperOverride()));
        AntimatterAPI.addProvider(Ref.ID, g -> new AntimatterFluidTagProvider(Ref.ID, Ref.NAME.concat(" Fluid Tags"), false, g, new ExistingFileHelperOverride()));
        AntimatterAPI.addProvider(Ref.ID, g -> new GregTechRecipes(Ref.ID, Ref.NAME.concat(" Recipes"), g));
        AntimatterAPI.addProvider(Ref.ID, g -> new AntimatterAdvancementProvider(Ref.ID, Ref.NAME.concat(" Advancements"), g, new ProgressionAdvancements()));
        AntimatterAPI.addProvider(Ref.ID, GregTechLocalizations.en_US::new);
        AntimatterAPI.addProvider(Ref.ID, g -> new AntimatterBlockLootProvider(Ref.ID, Ref.NAME.concat( " Loot generator"),g));
        registerRecipeLoaders();
    }

    private void registerRecipeLoaders() {
        IRecipeRegistrate loader = AntimatterAPI.getRecipeRegistrate();
        loader.add(WiremillLoader::init);
        loader.add(WasherLoader::init);
        loader.add(Blasting::init);
        loader.add(BendingLoader::init);
        loader.add(AssemblyLoader::init);
        loader.add(ChemicalReactorLoader::init);
        loader.add(Fuels::init);
        loader.add(FluidExtractor::init);
        loader.add(AlloyLoader::init);

        loader.add(ElectrolyzerLoader::init);
        loader.add(FluidCanningLoader::init);
        loader.add(CentrifugingLoader::init);
        loader.add(ExtractorLoader::init);
        loader.add(CompressorLoader::init);
        loader.add(VacFreezer::init);
        loader.add(OreByproducts::init);
        loader.add(PulverizerLoader::init);
        loader.add(SiftingLoader::init);
        loader.add(ThermalCentrifuge::init);
    }

    private void clientSetup(final FMLClientSetupEvent e) {
        ClientHandler.setup(e);
    }

    private void setup(final FMLCommonSetupEvent e) {
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Dist side) {
        switch (event) {
            case DATA_INIT:
                Materials.init();
                GregTechData.init();
                Machines.init();
                Guis.init(side);
                Models.init();
                break;
                //TODO: This runs before AM.DATA_READY.
            case DATA_READY:
                GregTechData.buildTierMaps();
                Structures.init();
                //GregTechAPI.registerFluidCell(Data.CellTin.get(1));
                //GregTechAPI.registerFluidCell(Data.CellSteel.get(1));
                //GregTechAPI.registerFluidCell(Data.CellTungstensteel.get(1));

//                AntimatterAPI.registerCover(Data.COVER_PLATE);
//                AntimatterAPI.registerCover(Data.COVER_CONVEYOR);
//                AntimatterAPI.registerCover(Data.COVER_PUMP);

//                AntimatterAPI.registerCoverStack(Data.ConveyorLV.get(1), new CoverConveyor(Tier.LV));
//                AntimatterAPI.registerCoverStack(Data.ConveyorMV.get(1), new CoverConveyor(Tier.MV));
//                AntimatterAPI.registerCoverStack(Data.ConveyorHV.get(1), new CoverConveyor(Tier.HV));
//                AntimatterAPI.registerCoverStack(Data.ConveyorEV.get(1), new CoverConveyor(Tier.EV));
//                AntimatterAPI.registerCoverStack(Data.ConveyorIV.get(1), new CoverConveyor(Tier.IV));
//                AntimatterAPI.registerCoverStack(Data.PumpLV.get(1), new CoverPump(Tier.LV));
//                AntimatterAPI.registerCoverStack(Data.PumpMV.get(1), new CoverPump(Tier.MV));
//                AntimatterAPI.registerCoverStack(Data.PumpHV.get(1), new CoverPump(Tier.HV));
//                AntimatterAPI.registerCoverStack(Data.PumpEV.get(1), new CoverPump(Tier.EV));
//                AntimatterAPI.registerCoverStack(Data.PumpIV.get(1), new CoverPump(Tier.IV));
//                MaterialType.PLATE.all().forEach(m -> AntimatterAPI.registerCoverStack(MaterialType.PLATE.get(m, 1), Data.COVER_PLATE));
                break;
            case WORLDGEN_INIT:
                WorldGenLoader.init();
                break;
        }
    }
}
