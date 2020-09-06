package muramasa.gti;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.*;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.AntimatterMod;
import muramasa.gti.data.*;
import muramasa.gti.datagen.GregTechBlockTagProvider;
import muramasa.gti.datagen.GregTechRecipes;
import muramasa.gti.datagen.ProgressionAdvancements;
import muramasa.gti.loader.MachineRecipeLoader;
import muramasa.gti.loader.MaterialRecipeLoader;
import muramasa.gti.loader.WorldGenLoader;
import muramasa.gti.proxy.ClientHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(Ref.ID)
public class GregTech extends AntimatterMod {

    public static class WorldLoadListener {
        public static boolean loaded = false;
        @SubscribeEvent
        public static void TagsUpdatedEvent(TagsUpdatedEvent event)
        {
            if (!loaded) {
                MaterialRecipeLoader.init();
                MachineRecipeLoader.init();
                loaded = true;
            }
        }
    }
    public static GregTech INSTANCE;
    public static Logger LOGGER = LogManager.getLogger(Ref.ID);

    public GregTech() {
        super();
        INSTANCE = this;
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(WorldLoadListener.class);
        //GregTechAPI.addRegistrar(new ForestryRegistrar());
        //GregTechAPI.addRegistrar(new GalacticraftRegistrar());
        //if (ModList.get().isLoaded(Ref.MOD_UB)) GregTechAPI.addRegistrar(new UndergroundBiomesRegistrar());

        AntimatterAPI.addProvider(Ref.ID, g -> new AntimatterBlockStateProvider(Ref.ID, Ref.NAME + " BlockStates", g));
        AntimatterAPI.addProvider(Ref.ID, g -> new AntimatterItemModelProvider(Ref.ID, Ref.NAME + " Item Models", g));
        AntimatterAPI.addProvider(Ref.ID, g -> new GregTechBlockTagProvider(Ref.ID, Ref.NAME.concat(" Block Tags"), false, g));
        AntimatterAPI.addProvider(Ref.ID, g -> new AntimatterItemTagProvider(Ref.ID, Ref.NAME.concat(" Item Tags"), false, g));
        AntimatterAPI.addProvider(Ref.ID, g -> new AntimatterFluidTagProvider(Ref.ID, Ref.NAME.concat(" Fluid Tags"), false, g));
        AntimatterAPI.addProvider(Ref.ID, g -> new GregTechRecipes(Ref.ID, Ref.NAME.concat(" Recipes"), g));
        AntimatterAPI.addProvider(Ref.ID, g -> new AntimatterAdvancementProvider(Ref.ID, Ref.NAME.concat(" Advancements"), g, new ProgressionAdvancements()));
        AntimatterAPI.addProvider(Ref.ID, GregTechLocalizations.en_US::new);
    }

    private void clientSetup(final FMLClientSetupEvent e) {
        ClientHandler.setup(e);
    }

    private void setup(final FMLCommonSetupEvent e) {

    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event) {
        switch (event) {
            case DATA_INIT:
                Materials.init();
                GregTechData.init();
                Machines.init();
                Guis.init();
                Models.init();
                break;
            case DATA_READY:
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
