package muramasa.gti.datagen;

import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.gti.data.GregTechData;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;

public class GregTechBlockTagProvider extends AntimatterBlockTagProvider {

    public GregTechBlockTagProvider(String providerDomain, String providerName, boolean replace, DataGenerator gen) {
        super(providerDomain, providerName, replace, gen);
    }

    @Override
    public void registerTags() {
        super.registerTags();
        this.getBuilder(BlockTags.LOGS).add(GregTechData.RUBBER_LOG);
        this.getBuilder(BlockTags.LEAVES).add(GregTechData.RUBBER_LEAVES);
        this.getBuilder(BlockTags.SAPLINGS).add(GregTechData.RUBBER_SAPLING);
    }
}
