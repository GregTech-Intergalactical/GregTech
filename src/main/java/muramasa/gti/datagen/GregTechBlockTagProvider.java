package muramasa.gti.datagen;

import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.util.Utils;
import muramasa.gti.data.Data;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;

public class GregTechBlockTagProvider extends AntimatterBlockTagProvider {

    public GregTechBlockTagProvider(String providerDomain, String providerName, boolean replace, DataGenerator gen) {
        super(providerDomain, providerName, replace, gen);
    }

    @Override
    protected void registerTags() {
        super.registerTags();

        this.getBuilder(Utils.getBlockTag(new ResourceLocation("logs"))).add(Data.RUBBER_LOG);
        this.getBuilder(Utils.getBlockTag(new ResourceLocation("leaves"))).add(Data.RUBBER_LEAVES);
        this.getBuilder(Utils.getBlockTag(new ResourceLocation("saplings"))).add(Data.RUBBER_SAPLING);
    }
}
