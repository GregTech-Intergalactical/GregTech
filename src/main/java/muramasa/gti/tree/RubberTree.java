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
        BlockStateProperties.HORIZONTAL_FACING.getAllowedValues().forEach(d -> {
            TRUNK_BLOCKS.addWeightedBlockstate(GregTechData.RUBBER_LOG.getDefaultState()
                    .with(BlockRubberLog.RESIN_STATE, ResinState.FILLED)
                    .with(BlockRubberLog.RESIN_FACING, d), 1);
            TRUNK_BLOCKS.addWeightedBlockstate(GregTechData.RUBBER_LOG.getDefaultState()
                    .with(BlockRubberLog.RESIN_STATE, ResinState.EMPTY)
                    .with(BlockRubberLog.RESIN_FACING, d), 1);
        });
        TRUNK_BLOCKS.addWeightedBlockstate(GregTechData.RUBBER_LOG.getDefaultState()
                .with(BlockRubberLog.RESIN_STATE, ResinState.NONE), 20);
    }

    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random rand, boolean flowers) {
        return TREE_FEATURE.withConfiguration(RubberTreeWorldGen.RUBBER_TREE_CONFIG_NORMAL);
    }

    @Override
    public boolean attemptGrowTree(ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random rand) {
        ConfiguredFeature<BaseTreeFeatureConfig, ?> configuredFeature = TREE_FEATURE
                .withConfiguration(RubberTreeWorldGen.getTreeConfig(world.getBiome(pos).getCategory()));
        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
        configuredFeature.config.forcePlacement();
        if (!configuredFeature.generate(world, chunkGenerator, rand, pos)) {
            world.setBlockState(pos, state, 4);
            return false;
        } else
            return true;
    }
}
