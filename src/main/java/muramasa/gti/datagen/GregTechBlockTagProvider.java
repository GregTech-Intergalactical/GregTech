package muramasa.gti.datagen;

import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.util.Utils;
import muramasa.gti.data.Data;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;

public class GregTechBlockTagProvider extends AntimatterBlockTagProvider {

    public GregTechBlockTagProvider(String providerDomain, String providerName, boolean replace, DataGenerator gen) {
        super(providerDomain, providerName, replace, gen);
    }

    @Override
    public void registerTags() {
        this.getBuilder(BlockTags.LOGS).add(Data.RUBBER_LOG);
        this.getBuilder(BlockTags.LEAVES).add(Data.RUBBER_LEAVES);
        this.getBuilder(BlockTags.SAPLINGS).add(Data.RUBBER_SAPLING);
    }
}
