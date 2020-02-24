package muramasa.gti.tree;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.treedecorator.LeaveVineTreeDecorator;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static muramasa.gti.common.Data.RUBBER_LEAVES;
import static muramasa.gti.common.Data.RUBBER_SAPLING;

public class RubberTreeWorldGen { //  extends WorldGenBase<RubberTreeWorldGen> {
    final static TreeFeatureConfig RUBBER_TREE_CONFIG_SWAMP =
            (new TreeFeatureConfig.Builder(RubberTree.TRUNK_BLOCKS, new SimpleBlockStateProvider(RUBBER_LEAVES.getDefaultState()),
                    new BlobFoliagePlacer(2, 0))).baseHeight(11).heightRandA(2) // total height
                    .trunkHeight(4).trunkHeightRandom(1) // bare trunk height
                    .trunkTopOffset(2) // depresses trunk top within leaves
                    .setSapling(RUBBER_SAPLING)
                    .maxWaterDepth(1).decorators(ImmutableList.of(new LeaveVineTreeDecorator())).build();

    final static TreeFeatureConfig RUBBER_TREE_CONFIG_JUNGLE =
            (new TreeFeatureConfig.Builder(RubberTree.TRUNK_BLOCKS, new SimpleBlockStateProvider(RUBBER_LEAVES.getDefaultState()),
                    new BlobFoliagePlacer(2, 0))).baseHeight(13).heightRandA(2) // total height
                    .trunkHeight(4).trunkHeightRandom(1) // bare trunk height
                    .trunkTopOffset(2) // depresses trunk top within leaves
                    .setSapling(RUBBER_SAPLING)
                    .decorators(ImmutableList.of(new LeaveVineTreeDecorator())).build();

    final static TreeFeatureConfig RUBBER_TREE_CONFIG_NORMAL =
            (new TreeFeatureConfig.Builder(RubberTree.TRUNK_BLOCKS, new SimpleBlockStateProvider(RUBBER_LEAVES.getDefaultState()),
                    new BlobFoliagePlacer(2, 0))).baseHeight(12).heightRandA(2) // total height
                    .trunkHeight(4).trunkHeightRandom(1) // bare trunk height
                    .trunkTopOffset(2) // depresses trunk top within leaves
                    .setSapling(RUBBER_SAPLING).build();

//    public RubberTreeWorldGen(){
//        super("rubber_tree", RubberTreeWorldGen.class, OVERWORLD);
//    }

    //@Override
    static Predicate<Biome> getValidBiomes() {
        final Set<Biome.Category> blacklist = new HashSet<>();
        blacklist.add(Biome.Category.DESERT);
        blacklist.add(Biome.Category.TAIGA);
        blacklist.add(Biome.Category.EXTREME_HILLS);
        blacklist.add(Biome.Category.ICY);
        blacklist.add(Biome.Category.THEEND);
        blacklist.add(Biome.Category.OCEAN);
        blacklist.add(Biome.Category.NETHER);
        return b -> !blacklist.contains(b.getCategory());
    }

    static TreeFeatureConfig getTreeConfig(Biome biome){
        TreeFeatureConfig config = RUBBER_TREE_CONFIG_NORMAL;
        if (biome.getCategory() == Biome.Category.SWAMP)
            config = RUBBER_TREE_CONFIG_SWAMP;
        else if (biome.getCategory() == Biome.Category.JUNGLE)
            config = RUBBER_TREE_CONFIG_JUNGLE;
        return config;
    }
    public static class RubberTreePlacement extends Placement<AtSurfaceWithExtraConfig> {
        public RubberTreePlacement() {
            super(AtSurfaceWithExtraConfig::deserialize);
        }
        @Override
        public Stream<BlockPos> getPositions(IWorld world, ChunkGenerator<? extends GenerationSettings> chunkGenerator, Random random, AtSurfaceWithExtraConfig config, BlockPos pos) {
            int i = config.count;
            if (random.nextFloat() < config.extraChance) {
                i = random.nextInt(config.extraCount) + config.extraCount;
            }
            return IntStream.range(0, i).mapToObj((ix) -> {
                int j = random.nextInt(16) + pos.getX();
                int k = random.nextInt(16) + pos.getZ();
                return new BlockPos(j, world.getHeight(Heightmap.Type.MOTION_BLOCKING, j, k), k);
            });
        }
    }

    public static void build(){
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (!getValidBiomes().test(biome) || biome.getCategory() == Biome.Category.PLAINS)
                continue;
            float p = 0.05F;
            if (biome.isHighHumidity()) {
                p = 0.2F;
                if (biome.getTempCategory() == Biome.TempCategory.WARM)
                    p += 0.1F;
            }
            biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RubberTree.TREE_FEATURE.withConfiguration(getTreeConfig(biome))
                    .withPlacement(new RubberTreePlacement().configure(new AtSurfaceWithExtraConfig(0, p, 3))));
        }
    }
}
