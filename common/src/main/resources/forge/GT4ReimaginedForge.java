package trinsdar.gt4r.forge;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import trinsdar.gt4r.GT4RConfig;
import trinsdar.gt4r.GT4Reimagined;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.proxy.ClientHandler;
import trinsdar.gt4r.proxy.CommonHandler;
import trinsdar.gt4r.proxy.ServerHandler;

@Mod(Ref.ID)
public class GT4ReimaginedForge {
    public GT4ReimaginedForge(){
        new GT4Reimagined();
        GT4Reimagined.PROXY = DistExecutor.runForDist(() -> ClientHandler::new, () -> ServerHandler::new); // todo: scheduled to change in new Forge
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverSetup);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GT4RConfig.COMMON_SPEC);
    }

    private void clientSetup(final FMLClientSetupEvent e) {
        ClientHandler.setup();
    }

    private void setup(final FMLCommonSetupEvent e) {
        CommonHandler.setup();
    }

    private void serverSetup(final FMLDedicatedServerSetupEvent event){
    }
}
