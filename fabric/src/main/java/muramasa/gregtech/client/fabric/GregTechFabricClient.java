package muramasa.gregtech.client.fabric;

import muramasa.gregtech.proxy.ClientHandler;
import net.fabricmc.api.ClientModInitializer;

public class GregTechFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientHandler.setup();
    }
}
