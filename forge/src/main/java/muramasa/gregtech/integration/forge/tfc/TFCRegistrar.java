package muramasa.gregtech.integration.forge.tfc;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterFluidTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemTagProvider;
import muramasa.antimatter.event.forge.AntimatterProvidersEvent;
import muramasa.antimatter.registration.IAntimatterRegistrar;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.GregTech;
import net.dries007.tfc.common.fluids.TFCFluids;
import net.dries007.tfc.util.Metal;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static muramasa.antimatter.data.AntimatterDefaultTools.SAW;

public class TFCRegistrar implements IAntimatterRegistrar {
    public TFCRegistrar(){
        onRegistrarInit();
    }
    @Override
    public String getId() {
        return "tfc";
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {

    }

    @Override
    public void onRegistrarInit() {
        AntimatterAPI.addRegistrar(this);
        if (isEnabled()) {
            FMLJavaModLoadingContext.get().getModEventBus().register(this);
        }
    }

    @SubscribeEvent
    public void onProviders(AntimatterProvidersEvent ev) {
        ev.event.addProvider("tfc", () -> new AntimatterFluidTagProvider("tfc", "TFC Fluid Tags", false){
            @Override
            protected void processTags(String domain) {
                super.processTags(domain);
                this.tag(TagUtils.getForgelikeFluidTag("salt_water")).add(TFCFluids.SALT_WATER.getSource());
            }
        });
        AntimatterBlockTagProvider[] blockTagProviders = new AntimatterBlockTagProvider[1];
        blockTagProviders[0] = new AntimatterBlockTagProvider("tfc", "TFC Block Tags", false);
        ev.event.addProvider("tfc", () -> new AntimatterItemTagProvider("tfc", "TFC Item Tags", false,  blockTagProviders[0]){
            @Override
            protected void processTags(String domain) {
                super.processTags(domain);
                this.tag(TagUtils.getItemTag(new ResourceLocation("tfc", "saws"))).addTag(SAW.getTag());
            }
        });

    }

    @Override
    public boolean isEnabled() {
        return AntimatterAPI.isModLoaded("tfc");
    }
}
