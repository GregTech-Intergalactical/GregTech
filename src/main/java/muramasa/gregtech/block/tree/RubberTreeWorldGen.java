package muramasa.gregtech.block.tree;

import muramasa.antimatter.worldgen.object.WorldGenBase;

public class RubberTreeWorldGen extends WorldGenBase<RubberTreeWorldGen> {

    /*@Override
    public Predicate<Biome> getValidBiomes() {
        return getValidBiomesStatic();
    }*/
/*
    public static Predicate<Biome.BiomeCategory> getValidBiomesStatic() {
        final Set<Biome.BiomeCategory> blacklist = new ObjectOpenHashSet<>();
        blacklist.add(Biome.BiomeCategory.DESERT);
        blacklist.add(Biome.BiomeCategory.TAIGA);
        blacklist.add(Biome.BiomeCategory.EXTREME_HILLS);
        blacklist.add(Biome.BiomeCategory.ICY);
        blacklist.add(Biome.BiomeCategory.THEEND);
        blacklist.add(Biome.BiomeCategory.OCEAN);
        blacklist.add(Biome.BiomeCategory.NETHER);
        blacklist.add(Biome.BiomeCategory.PLAINS);
        return b -> !blacklist.contains(b);
    }

    
    final static TreeConfiguration RUBBER_TREE_CONFIG_SWAMP =
            (new TreeConfiguration.TreeConfigurationBuilder(RubberTree.TRUNK_BLOCKS,  BlockStateProvider.simple(GregTechData.RUBBER_LEAVES.defaultBlockState()),
                    new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), new StraightTrunkPlacer(5, 2, 2), new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().maxWaterDepth(1).build();

    final static TreeConfiguration RUBBER_TREE_CONFIG_JUNGLE =
            (new TreeConfiguration.TreeConfigurationBuilder(RubberTree.TRUNK_BLOCKS,  BlockStateProvider.simple(GregTechData.RUBBER_LEAVES.defaultBlockState()),
                    new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), new StraightTrunkPlacer(7, 2, 2), new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build();
                 ///   .trunkHeight(4).trunkHeightRandom(1) // bare trunk height
               //     .trunkTopOffset(2) // depresses trunk top within leaves
               //     .setSapling(RUBBER_SAPLING)
          //          .decorators(ImmutableList.of(new LeaveVineTreeDecorator())).build();

    final static TreeConfiguration RUBBER_TREE_CONFIG_NORMAL =
            (new TreeConfiguration.TreeConfigurationBuilder(RubberTree.TRUNK_BLOCKS, BlockStateProvider.simple(GregTechData.RUBBER_LEAVES.defaultBlockState()),
                    new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), new StraightTrunkPlacer(5, 2, 2), new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build();

    public RubberTreeWorldGen(){
        super("rubber_tree", RubberTreeWorldGen.class, Level.OVERWORLD);
    }
  
    public static void onEvent(BiomeLoadingEvent builder) {
        Biome.BiomeCategory biomeCategory = builder.getCategory();
        if (!getValidBiomesStatic().test(biomeCategory) || biomeCategory == Biome.BiomeCategory.PLAINS) return;
        float p = 0.05F;
        if (builder.getClimate().temperature > 0.8f) {
            p = 0.04F;
            if (builder.getClimate().precipitation == Biome.Precipitation.RAIN)
                p += 0.04F;
        }
        float finalp = p;
        builder.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> RubberTree.TREE_FEATURE.configured(getTreeConfig(biomeCategory))
                .place(new RubberTreePlacement().configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, finalp, 1))));
    }

    static TreeConfiguration getTreeConfig(Biome.BiomeCategory biome){
        TreeConfiguration config = RUBBER_TREE_CONFIG_NORMAL;
        if (biome == Biome.BiomeCategory.SWAMP)
            config = RUBBER_TREE_CONFIG_SWAMP;
        else if (biome == Biome.BiomeCategory.JUNGLE)
            config = RUBBER_TREE_CONFIG_JUNGLE;
        return config;
    }
  
    public static class RubberTreePlacement extends PlacementModifier {
        public RubberTreePlacement() {
        }

        @Override
        public Stream<BlockPos> getPositions(PlacementContext p_191845_, Random p_191846_, BlockPos p_191847_) {
            int i = config.count;
            double next = random.nextDouble();
            if (next < config.extraChance) {
                i = random.nextInt(config.extraCount) + config.extraCount;
            }
            return IntStream.range(0, i).mapToObj((ix) -> {
                int j = random.nextInt(16) + pos.getX();
                int k = random.nextInt(16) + pos.getZ();
                return new BlockPos(j, helper.getHeight(Heightmap.Types.MOTION_BLOCKING, j, k), k);
            });
        }

        @Override
        public PlacementModifierType<?> type() {
            return null;
        }


        @Nonnull
        @Override
        public Stream<BlockPos> getPositions(@Nonnull DecorationContext helper, Random random, FrequencyWithExtraChanceDecoratorConfiguration config, @Nonnull BlockPos pos) {
            int i = config.count;
            double next = random.nextDouble();
            if (next < config.extraChance) {
                i = random.nextInt(config.extraCount) + config.extraCount;
            }
            return IntStream.range(0, i).mapToObj((ix) -> {
                int j = random.nextInt(16) + pos.getX();
                int k = random.nextInt(16) + pos.getZ();
                return new BlockPos(j, helper.getHeight(Heightmap.Types.MOTION_BLOCKING, j, k), k);
            });
        }


}*/
}
