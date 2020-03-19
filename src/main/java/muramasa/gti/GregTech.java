package muramasa.gti;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.client.AntimatterModelManager;
import muramasa.antimatter.datagen.providers.AntimatterAdvancementProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemTagProvider;
import muramasa.antimatter.machines.Tier;
import muramasa.antimatter.materials.MaterialType;
import muramasa.antimatter.registration.IAntimatterRegistrar;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.RegistrationHelper;
import muramasa.gti.client.Models;
import muramasa.gti.common.Data;
import muramasa.gti.cover.CoverConveyor;
import muramasa.gti.cover.CoverPump;
import muramasa.gti.data.*;
import muramasa.gti.data.advancement.ProgressionAdvancements;
import muramasa.gti.datagen.GregTechBlockTagProvider;
import muramasa.gti.loaders.MachineRecipeLoader;
import muramasa.gti.loaders.MaterialRecipeLoader;
import muramasa.gti.loaders.WorldGenLoader;
import muramasa.gti.proxy.ClientHandler;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Ref.ID)
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class GregTech implements IAntimatterRegistrar {

    public static GregTech INSTANCE;
    public static Logger LOGGER = LogManager.getLogger(Ref.ID);

    public GregTech() {
        INSTANCE = this;
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientHandler::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        AntimatterAPI.addRegistrar(INSTANCE);
        //GregTechAPI.addRegistrar(new ForestryRegistrar());
        //GregTechAPI.addRegistrar(new GalacticraftRegistrar());
        //if (ModList.get().isLoaded(Ref.MOD_UB)) GregTechAPI.addRegistrar(new UndergroundBiomesRegistrar());
    }

    private void setup(final FMLCommonSetupEvent e) {

    }

    @SubscribeEvent
    public static void onDataGather(GatherDataEvent e) {
        DataGenerator gen = e.getGenerator();
        if (e.includeClient()) {
            AntimatterModelManager.onProviderInit(Ref.ID, e.getGenerator());
        }
        if (e.includeServer()) {
            gen.addProvider(new GregTechBlockTagProvider(Ref.ID, Ref.NAME.concat(" Block Tags"), false, gen));
            gen.addProvider(new AntimatterItemTagProvider(Ref.ID, Ref.NAME.concat(" Item Tags"), false, gen));
            gen.addProvider(new Recipes(gen));
            gen.addProvider(new AntimatterAdvancementProvider(Ref.ID, Ref.NAME.concat(" Advancements"), gen, new ProgressionAdvancements()));
            gen.addProvider(new Localizations.en_US(gen));
        }
    }

    @Override
    public String getId() {
        return Ref.ID;
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event) {
        switch (event) {
            case DATA_INIT:
                Materials.init();
                Data.init();
                Machines.init();
                Structures.init();
                Guis.init();
                Models.init();
                break;
            case DATA_BUILD:
                RegistrationHelper.buildDefaultMaterialDerivedObjects(Ref.ID);
                break;
            case DATA_READY:
                //GregTechAPI.registerFluidCell(Data.CellTin.get(1));
                //GregTechAPI.registerFluidCell(Data.CellSteel.get(1));
                //GregTechAPI.registerFluidCell(Data.CellTungstensteel.get(1));

                AntimatterAPI.registerCover(Data.COVER_PLATE);
                AntimatterAPI.registerCover(Data.COVER_CONVEYOR);
                AntimatterAPI.registerCover(Data.COVER_PUMP);

                AntimatterAPI.registerCoverStack(Data.ConveyorLV.get(1), new CoverConveyor(Tier.LV));
                AntimatterAPI.registerCoverStack(Data.ConveyorMV.get(1), new CoverConveyor(Tier.MV));
                AntimatterAPI.registerCoverStack(Data.ConveyorHV.get(1), new CoverConveyor(Tier.HV));
                AntimatterAPI.registerCoverStack(Data.ConveyorEV.get(1), new CoverConveyor(Tier.EV));
                AntimatterAPI.registerCoverStack(Data.ConveyorIV.get(1), new CoverConveyor(Tier.IV));
                AntimatterAPI.registerCoverStack(Data.PumpLV.get(1), new CoverPump(Tier.LV));
                AntimatterAPI.registerCoverStack(Data.PumpMV.get(1), new CoverPump(Tier.MV));
                AntimatterAPI.registerCoverStack(Data.PumpHV.get(1), new CoverPump(Tier.HV));
                AntimatterAPI.registerCoverStack(Data.PumpEV.get(1), new CoverPump(Tier.EV));
                AntimatterAPI.registerCoverStack(Data.PumpIV.get(1), new CoverPump(Tier.IV));
                MaterialType.PLATE.all().forEach(m -> AntimatterAPI.registerCoverStack(MaterialType.PLATE.get(m, 1), Data.COVER_PLATE));
                break;
            case RECIPE:
                // OreDictLoader.init();
                //CraftingRecipeLoader.init();
                MaterialRecipeLoader.init();
                MachineRecipeLoader.init();
                break;
            case WORLDGEN_INIT:
                WorldGenLoader.init();
                break;
        }
    }
}
