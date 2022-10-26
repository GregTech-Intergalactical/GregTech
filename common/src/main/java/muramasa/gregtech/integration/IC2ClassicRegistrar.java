package muramasa.gregtech.integration;

import muramasa.antimatter.Ref;
import muramasa.antimatter.registration.IAntimatterRegistrar;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;

public class IC2ClassicRegistrar implements IAntimatterRegistrar {

    @Override
    public String getId() {
        return Ref.MOD_IC2C;
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {
        //TODO
    }

    @Override
    public void onRegistrarInit() {

    }
}
