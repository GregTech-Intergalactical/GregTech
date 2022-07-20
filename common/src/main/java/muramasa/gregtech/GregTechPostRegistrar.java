package muramasa.gregtech;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.event.MaterialEvent;
import muramasa.antimatter.registration.IAntimatterRegistrar;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.data.GregTechMaterialEvents;

public class GregTechPostRegistrar implements IAntimatterRegistrar {
    public GregTechPostRegistrar(){
        if (AntimatterPlatformUtils.isForge()){
            onRegistrarInit();
        }
    }

    @Override
    public String getId() {
        return Ref.ID + "_post";
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {

    }

    @Override
    public void onMaterialEvent(MaterialEvent event) {
        GregTechMaterialEvents.onMaterialEvent(event);
    }

    @Override
    public void onRegistrarInit() {
        AntimatterAPI.addRegistrar(this);
    }

    @Override
    public int getPriority() {
        return 600;
    }
}
