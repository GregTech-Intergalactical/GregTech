package muramasa.gregtech.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterStoneTypes;
import muramasa.antimatter.datagen.providers.AntimatterBlockLootProvider;
import muramasa.antimatter.ore.CobbleStoneType;
import muramasa.gregtech.block.BlockCasing;
import muramasa.gregtech.block.BlockCoil;
import muramasa.gregtech.block.BlockColoredWall;
import muramasa.gregtech.block.BlockFakeCasing;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.Materials;
import muramasa.gregtech.integration.AppliedEnergisticsRegistrar;
import muramasa.gregtech.integration.SpaceModRegistrar;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import static muramasa.antimatter.data.AntimatterMaterialTypes.RAW_ORE;
import static muramasa.antimatter.data.AntimatterMaterials.*;

public class GregtechBlockLootProvider extends AntimatterBlockLootProvider {
    public GregtechBlockLootProvider(String providerDomain, String providerName) {
        super(providerDomain, providerName);
    }

    @Override
    protected void loot() {
        super.loot();
        AntimatterAPI.all(BlockCasing.class,providerDomain, this::add);
        AntimatterAPI.all(BlockColoredWall.class,providerDomain, this::add);
        AntimatterAPI.all(BlockCoil.class,providerDomain, this::add);
        AntimatterAPI.all(BlockFakeCasing.class, providerDomain, this::add);
        this.add(GregTechData.MINING_PIPE_THIN);
        this.add(GregTechData.SOLID_SUPER_FUEL);
        this.add(GregTechData.MINING_PIPE, b -> this.build(GregTechData.MINING_PIPE_THIN));
        tables.put(Blocks.LAPIS_ORE, b -> createOreDrop(b, RAW_ORE.get(Lapis)));
        tables.put(Blocks.DEEPSLATE_LAPIS_ORE, b -> createOreDrop(b, RAW_ORE.get(Lapis)));
        tables.put(Blocks.REDSTONE_ORE, b -> createOreDrop(b, RAW_ORE.get(Redstone)));
        tables.put(Blocks.DEEPSLATE_REDSTONE_ORE, b -> createOreDrop(b, RAW_ORE.get(Redstone)));
        tables.put(Blocks.DIAMOND_ORE, b -> createOreDrop(b, RAW_ORE.get(Diamond)));
        tables.put(Blocks.DEEPSLATE_DIAMOND_ORE, b -> createOreDrop(b, RAW_ORE.get(Diamond)));
        tables.put(Blocks.EMERALD_ORE, b -> createOreDrop(b, RAW_ORE.get(Emerald)));
        tables.put(Blocks.DEEPSLATE_EMERALD_ORE, b -> createOreDrop(b, RAW_ORE.get(Emerald)));
        tables.put(Blocks.COPPER_ORE, b -> createOreDrop(b, RAW_ORE.get(Copper)));
        tables.put(Blocks.DEEPSLATE_COPPER_ORE, b -> createOreDrop(b, RAW_ORE.get(Copper)));
        tables.put(Blocks.COAL_ORE, b -> createOreDrop(b, RAW_ORE.get(Coal)));
        tables.put(Blocks.DEEPSLATE_COAL_ORE, b -> createOreDrop(b, RAW_ORE.get(Coal)));
        tables.put(GregTechData.BRITTLE_CHARCOAL, b -> createSingleItemTable(Items.CHARCOAL, UniformGenerator.between(1.0f, 2.0f)));
        tables.put(Blocks.ANCIENT_DEBRIS, b -> createOreDrop(b, RAW_ORE.get(NetheriteScrap)));
        tables.put(Blocks.ANDESITE, b -> createSingleItemTableWithSilkTouch(Blocks.ANDESITE, ((CobbleStoneType)AntimatterStoneTypes.ANDESITE).getBlock("cobble")));
        tables.put(Blocks.DIORITE, b -> createSingleItemTableWithSilkTouch(Blocks.DIORITE, ((CobbleStoneType)AntimatterStoneTypes.DIORITE).getBlock("cobble")));
        tables.put(Blocks.GRANITE, b -> createSingleItemTableWithSilkTouch(Blocks.GRANITE, ((CobbleStoneType)AntimatterStoneTypes.GRANITE).getBlock("cobble")));
        tables.put(Blocks.BASALT, b -> createSingleItemTableWithSilkTouch(Blocks.BASALT, ((CobbleStoneType)AntimatterStoneTypes.BASALT).getBlock("cobble")));
        if (AntimatterAPI.isModLoaded(Ref.MOD_AE)){
            tables.put(AppliedEnergisticsRegistrar.getAe2Block("quartz_ore"), b -> createOreDrop(b, RAW_ORE.get(Materials.CertusQuartz)));
            tables.put(AppliedEnergisticsRegistrar.getAe2Block("deepslate_quartz_ore"), b -> createOreDrop(b, RAW_ORE.get(Materials.CertusQuartz)));
        }
        if (AntimatterAPI.isModLoaded("ad_astra")){
            tables.put(SpaceModRegistrar.getSpaceBlock("mars_diamond_ore"), b -> createOreDrop(b, RAW_ORE.get(Diamond)));
        }
    }
}
