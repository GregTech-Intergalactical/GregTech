package muramasa.gti.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterBlockLootProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.mixin.BlockLootTablesAccessor;
import muramasa.antimatter.ore.BlockOre;
import muramasa.gti.block.BlockCasing;
import muramasa.gti.data.GregTechData;
import net.minecraft.data.DataGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.SetCount;

import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.GEM;
import static muramasa.antimatter.Data.ORE;
import static muramasa.gti.data.Materials.Lapis;

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
        AntimatterAPI.all(BlockOre.class, o -> {
            if (o.getOreType() != ORE) return;
            Material mat = o.getMaterial();
            if (mat.has(GEM)){
                Item item = GEM.get(mat);
                if (mat == Lapis /*|| mat == Sodalite || mat == Lazurite */) {
                    tables.put(o, b -> droppingWithSilkTouch(b, withExplosionDecay(b, ItemLootEntry.builder(item).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))).acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE)))));
                } else {
                    tables.put(o, b -> droppingItemWithFortune(b, item));
                }
            }
        });
    }
}
