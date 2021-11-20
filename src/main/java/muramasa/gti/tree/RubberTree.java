package muramasa.gti.tree;

import muramasa.gti.data.GregTechData;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class RubberTree extends Tree {

    public static final RubberTreeFeature TREE_FEATURE = new RubberTreeFeature();
    public static final WeightedBlockStateProvider TRUNK_BLOCKS = new WeightedBlockStateProvider();

    public RubberTree() {
        BlockStateProperties.HORIZONTAL_FACING.getPossibleValues().forEach(d -> {
            TRUNK_BLOCKS.add(GregTechData.RUBBER_LOG.defaultBlockState()
                    .setValue(BlockRubberLog.RESIN_STATE, ResinState.FILLED)
                    .setValue(BlockRubberLog.RESIN_FACING, d), 1);
            TRUNK_BLOCKS.add(GregTechData.RUBBER_LOG.defaultBlockState()
                    .setValue(BlockRubberLog.RESIN_STATE, ResinState.EMPTY)
                    .setValue(BlockRubberLog.RESIN_FACING, d), 1);
        });
        TRUNK_BLOCKS.add(GregTechData.RUBBER_LOG.defaultBlockState()
                .setValue(BlockRubberLog.RESIN_STATE, ResinState.NONE), 20);
    }

    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random rand, boolean flowers) {
        return TREE_FEATURE.configured(RubberTreeWorldGen.RUBBER_TREE_CONFIG_NORMAL);
    }

    @Override
    public boolean growTree(ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random rand) {
        ConfiguredFeature<BaseTreeFeatureConfig, ?> configuredFeature = TREE_FEATURE
                .configured(RubberTreeWorldGen.getTreeConfig(world.getBiome(pos).getBiomeCategory()));
        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 4);
        configuredFeature.config.setFromSapling();
        if (!configuredFeature.place(world, chunkGenerator, rand, pos)) {
            world.setBlock(pos, state, 4);
            return false;
        } else
            return true;
    }
}
