package muramasa.gregtech.client.fabric;

import muramasa.antimatter.client.fabric.IAntimatterClientInitializer;
import muramasa.gregtech.proxy.ClientHandler;
import net.fabricmc.api.ClientModInitializer;

public class GregTechFabricClient implements IAntimatterClientInitializer {
    @Override
    public void onInitializeClient() {
        ClientHandler.setup();
    }
}
