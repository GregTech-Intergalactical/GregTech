package muramasa.gregtech.integration.tfc;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.registration.IAntimatterRegistrar;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;

public class TFCRegistrar implements IAntimatterRegistrar {
    @Override
    public String getId() {
        return "tfc";
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {

    }

    @Override
    public void onRegistrarInit() {

    }

    @Override
    public boolean isEnabled() {
        return AntimatterAPI.isModLoaded("tfc");
    }
}
