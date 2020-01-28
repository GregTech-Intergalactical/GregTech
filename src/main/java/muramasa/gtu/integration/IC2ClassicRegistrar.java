package muramasa.gtu.integration;

import muramasa.antimatter.Ref;
import muramasa.antimatter.registration.IAntimatterRegistrar;
import muramasa.antimatter.registration.RegistrationEvent;

public class IC2ClassicRegistrar implements IAntimatterRegistrar {

    @Override
    public String getId() {
        return Ref.MOD_IC2C;
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event) {
        //TODO
    }
}
