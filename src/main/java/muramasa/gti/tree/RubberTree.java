package muramasa.gti.tree;

import muramasa.gti.common.Data;
import muramasa.gti.loaders.WorldGenLoader;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public class RubberTree extends Tree {

    public static final WeightedBlockStateProvider trunkBlocks = new WeightedBlockStateProvider();

    public RubberTree() {
        trunkBlocks.func_227407_a_(Data.RUBBER_LOG.getDefaultState().with(BlockRubberLog.RESIN_STATE, ResinState.FILLED).
                with(BlockRubberLog.RESIN_FACING, Direction.NORTH), 1);
        trunkBlocks.func_227407_a_(Data.RUBBER_LOG.getDefaultState().with(BlockRubberLog.RESIN_STATE, ResinState.FILLED).
                with(BlockRubberLog.RESIN_FACING, Direction.WEST), 1);
        trunkBlocks.func_227407_a_(Data.RUBBER_LOG.getDefaultState().with(BlockRubberLog.RESIN_STATE, ResinState.FILLED).
                with(BlockRubberLog.RESIN_FACING, Direction.SOUTH), 1);
        trunkBlocks.func_227407_a_(Data.RUBBER_LOG.getDefaultState().with(BlockRubberLog.RESIN_STATE, ResinState.FILLED).
                with(BlockRubberLog.RESIN_FACING, Direction.EAST), 1);
        trunkBlocks.func_227407_a_(Data.RUBBER_LOG.getDefaultState().with(BlockRubberLog.RESIN_STATE, ResinState.NONE), 10);
    }

    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> func_225546_b_(Random rand, boolean flowers) {
        return Feature.NORMAL_TREE.withConfiguration(WorldGenLoader.RUBBER_TREE_CONFIG_BLOB);
    }

    @Override
    public boolean func_225545_a_(IWorld world, ChunkGenerator<?> chunkGenerator, BlockPos pos, BlockState state, Random random) {
        ConfiguredFeature<TreeFeatureConfig, ?> configuredfeature = Feature.NORMAL_TREE.withConfiguration(
                world.getBiome(pos).getDefaultTemperature() <= 0.25 ? WorldGenLoader.RUBBER_TREE_CONFIG_SPRUCE : WorldGenLoader.RUBBER_TREE_CONFIG_BLOB);
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
            configuredfeature.config.forcePlacement();
            if (configuredfeature.place(world, chunkGenerator, random, pos)) {
                return true;
            } else {
                world.setBlockState(pos, state, 4);
                return false;
            }
    }
}
