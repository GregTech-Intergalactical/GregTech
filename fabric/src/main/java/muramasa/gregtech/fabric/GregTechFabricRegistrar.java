package muramasa.gregtech.fabric;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;
import muramasa.gregtech.Ref;
import muramasa.gregtech.data.GregTechData;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

public class GregTechFabricRegistrar extends AntimatterMod {

    @Override
    public String getId() {
        return Ref.ID + "_fabric";
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {
        if (event == RegistrationEvent.DATA_READY){
            FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();
            flammableRegistry.add(GregTechData.RUBBER_LOG, 5, 5);
            flammableRegistry.add(GregTechData.RUBBER_LEAVES, 60, 30);
        }
    }

    @Override
    public int getPriority() {
        return 850;
    }
}
