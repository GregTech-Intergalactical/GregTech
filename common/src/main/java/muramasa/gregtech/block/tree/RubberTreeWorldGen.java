package muramasa.gregtech.block.tree;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import muramasa.antimatter.worldgen.AntimatterWorldGenerator;
import muramasa.antimatter.worldgen.object.WorldGenBase;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.Set;
import java.util.function.Predicate;

public class RubberTreeWorldGen extends WorldGenBase<RubberTreeWorldGen> {

    public static final RubberTreeWorldGen INSTANCE = new RubberTreeWorldGen();

    /*@Override
    public Predicate<Biome> getValidBiomes() {
        return getValidBiomesStatic();
    }*/

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
            (new TreeConfiguration.TreeConfigurationBuilder(RubberTree.TRUNK_BLOCKS, new StraightTrunkPlacer(5, 2, 2), BlockStateProvider.simple(GregTechData.RUBBER_LEAVES.defaultBlockState()),
                    new RubberFoliagePlacer(),  new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build();

    final static TreeConfiguration RUBBER_TREE_CONFIG_JUNGLE =
            (new TreeConfiguration.TreeConfigurationBuilder(RubberTree.TRUNK_BLOCKS, new StraightTrunkPlacer(7, 2, 2), BlockStateProvider.simple(GregTechData.RUBBER_LEAVES.defaultBlockState()),
                    new RubberFoliagePlacer(),  new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build();

    final static TreeConfiguration RUBBER_TREE_CONFIG_NORMAL =
            (new TreeConfiguration.TreeConfigurationBuilder(RubberTree.TRUNK_BLOCKS, new StraightTrunkPlacer(5, 2, 2),BlockStateProvider.simple(GregTechData.RUBBER_LEAVES.defaultBlockState()),
                    new RubberFoliagePlacer(),  new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build();

    public RubberTreeWorldGen(){
        super("rubber_tree", RubberTreeWorldGen.class, Level.OVERWORLD);
        AntimatterWorldGenerator.register(this.toRegister, this);
    }


    public static void onEvent(ResourceLocation name, Biome.ClimateSettings climate, Biome.BiomeCategory category, BiomeSpecialEffects effects, BiomeGenerationSettings.Builder gen, MobSpawnSettings.Builder spawns) {
        if (!getValidBiomesStatic().test(category) || category == Biome.BiomeCategory.PLAINS) return;
        float p = 0.05F;
        if (climate.temperature > 0.8f) {
            p = 0.04F;
            if (climate.precipitation == Biome.Precipitation.RAIN)
                p += 0.04F;
        }
        float finalp = p;
        if (name != null) {
            if (name.equals(Biomes.JUNGLE.location())) {
                gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RubberTree.TREE_JUNGLE);
                return;
            }
            if (name.equals(Biomes.SWAMP.location())) {
                gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RubberTree.TREE_SWAMP);
                return;
            }
        }
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RubberTree.TREE);
    }

  /*
    public static class RubberTreePlacement extends PlacementModifier {
        public RubberTreePlacement() {
        }

        @Override
        public Stream<BlockPos> getPositions(PlacementContext ctxt, Random random, BlockPos pos) {
            int i = config.count;
            TreeConfiguration config = ( TreeConfiguration) ctxt.topFeature().get().feature().value().config();

            double next = random.nextDouble();
            if (next < config.extraChance) {
                i = random.nextInt(config.extraCount) + config.extraCount;
            }
            return IntStream.range(0, i).mapToObj((ix) -> {
                int j = random.nextInt(16) + pos.getX();
                int k = random.nextInt(16) + pos.getZ();
                return new BlockPos(j, ctxt.getHeight(Heightmap.Types.MOTION_BLOCKING, j, k), k);
            });
        }

        @Override
        public PlacementModifierType<?> type() {
            return PlacementModifierType.COUNT;
        }

}*/
}
