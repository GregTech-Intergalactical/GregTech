package muramasa.gti.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterBlockLootProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.ore.BlockOre;
import muramasa.gti.block.BlockCasing;
import muramasa.gti.data.GregTechData;
import net.minecraft.data.DataGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.SetCount;

import static muramasa.antimatter.Data.*;

public class GregtechBlockLootProvider extends AntimatterBlockLootProvider {
    public GregtechBlockLootProvider(String providerDomain, String providerName, DataGenerator gen) {
        super(providerDomain, providerName, gen);
    }

    @Override
    protected void loot() {
        super.loot();
        tables.put(GregTechData.RUBBER_LEAVES, b -> createLeavesDrops(GregTechData.RUBBER_LEAVES, GregTechData.RUBBER_SAPLING, 0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F));
        this.add(GregTechData.RUBBER_LOG);
        this.add(GregTechData.RUBBER_SAPLING);
        AntimatterAPI.all(BlockCasing.class,providerDomain, this::add);
        AntimatterAPI.all(BlockOre.class, o -> {
            if (o.getOreType() != ORE) return;
            Material mat = o.getMaterial();
            if (mat.has(GEM)){
                Item item = GEM.get(mat);
                if (mat == Lapis /*|| mat == Sodalite || mat == Lazurite */) {
                    tables.put(o, b -> createSilkTouchDispatchTable(b, applyExplosionDecay(b, ItemLootEntry.lootTableItem(item).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))).apply(ApplyBonus.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));
                } else {
                    tables.put(o, b -> createOreDrop(b, item));
                }
            }
        });
    }
}
