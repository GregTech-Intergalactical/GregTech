package muramasa.gti.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterBlockLootProvider;
import muramasa.gti.block.BlockCasing;
import muramasa.gti.data.GregTechData;
import net.minecraft.data.DataGenerator;

public class GregtechBlockLootProvider extends AntimatterBlockLootProvider {
    public GregtechBlockLootProvider(String providerDomain, String providerName, DataGenerator gen) {
        super(providerDomain, providerName, gen);
    }

    @Override
    protected void loot() {
        super.loot();
        tables.put(GregTechData.RUBBER_LEAVES,b -> droppingWithChancesAndSticks(GregTechData.RUBBER_LEAVES, GregTechData.RUBBER_SAPLING, 0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F));
        this.add(GregTechData.RUBBER_LOG);
        this.add(GregTechData.RUBBER_SAPLING);
        AntimatterAPI.all(BlockCasing.class,providerDomain, this::add);
    }
}
