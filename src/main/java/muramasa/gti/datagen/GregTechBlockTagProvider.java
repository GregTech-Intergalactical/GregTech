package muramasa.gti.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Data;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.gti.Ref;
import muramasa.gti.block.BlockCasing;
import muramasa.gti.block.BlockCoil;
import muramasa.gti.data.GregTechData;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

public class GregTechBlockTagProvider extends AntimatterBlockTagProvider {

    public GregTechBlockTagProvider(String providerDomain, String providerName, boolean replace, DataGenerator gen) {
        super(providerDomain, providerName, replace, gen);
    }

    @Override
    public void addTags() {
        super.addTags();
        this.tag(BlockTags.LOGS).add(GregTechData.RUBBER_LOG);
        this.tag(BlockTags.LEAVES).add(GregTechData.RUBBER_LEAVES);
        this.tag(BlockTags.SAPLINGS).add(GregTechData.RUBBER_SAPLING);
        AntimatterAPI.all(BlockCasing.class, Ref.ID, cas -> {
            this.tag(Data.WRENCH.getToolType()).add(cas);
        });
        AntimatterAPI.all(BlockCoil.class, Ref.ID, cas -> {
            this.tag(Data.WRENCH.getToolType()).add(cas);
        });
    }
}
