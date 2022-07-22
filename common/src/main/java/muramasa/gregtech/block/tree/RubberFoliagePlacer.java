package muramasa.gregtech.block.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class RubberFoliagePlacer extends FoliagePlacer {
    public static final Codec<RubberFoliagePlacer> CODEC = RecordCodecBuilder.create((p_242834_0_) -> {
        return foliagePlacerParts(p_242834_0_).apply(p_242834_0_, RubberFoliagePlacer::new);
    });
    public static final FoliagePlacerType<RubberFoliagePlacer> RUBBER = new FoliagePlacerType<>(CODEC);
    protected RubberFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    public RubberFoliagePlacer() {
        super(ConstantInt.of(2), ConstantInt.of(-1));
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return RUBBER;
    }

    @Override
    protected void createFoliage(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, Random pRandom, TreeConfiguration pConfig, int pMaxFreeTreeHeight, FoliageAttachment pAttachment, int pFoliageHeight, int pFoliageRadius, int pOffset) {
        generate(pLevel, pBlockSetter, pRandom, pConfig, pMaxFreeTreeHeight, pAttachment, pFoliageHeight, pFoliageRadius, pOffset);
    }

    protected void generate(LevelSimulatedReader world,  BiConsumer<BlockPos, BlockState> pBlockSetter, Random random, TreeConfiguration config, int trunkHeight, FoliageAttachment treeNode, int foliageHeight, int radius, int offset) {
        BlockPos center = treeNode.pos();
        BlockPos.MutableBlockPos pos = center.mutable();

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        double treeRadius = 2.5;
        for(int i = offset; i >= offset - foliageHeight; --i) {
            if (i == offset){
                this.placeLeavesRow(world, pBlockSetter, random, config, center, 1, i, treeNode.doubleTrunk());
                continue;
            }
            pos.set(x, y + i, z);
            circle(pos.mutable(), treeRadius, position -> {
                if (TreeFeature.isAirOrLeaves(world, position)) {
                    pBlockSetter.accept(position, config.foliageProvider.getState(random, position));
                }
            });
        }

        int spikeHeight = 2 + random.nextInt(3);
        for (int i = 0; i < spikeHeight; i++){
            BlockPos leaf = center.above(i);
            if (TreeFeature.isAirOrLeaves(world, leaf)) {
                pBlockSetter.accept(leaf, config.foliageProvider.getState(random, leaf));
            }
        }

    }

    /**
     * Iterates over the positions contained with in a circle defined by origin and radius. The circle is two dimensional,
     * perpendicular to the Y axis.
     *
     * @param origin The center block of the circle; this function clobbers the variable, and it must be reset afterwards
     * @param radius The radius of the circle
     * @param consumer The target of the positions; it passes the same BlockPos.Mutable object each time
     */
    private static void circle(BlockPos.MutableBlockPos origin, double radius, Consumer<BlockPos.MutableBlockPos> consumer) {
        int x = origin.getX();
        int z = origin.getZ();

        double radiusSq = radius * radius;
        int radiusCeil = (int) Math.ceil(radius);

        for (int dz = -radiusCeil; dz <= radiusCeil; dz++) {
            int dzSq = dz * dz;

            for (int dx = -radiusCeil; dx <= radiusCeil; dx++) {
                int dxSq = dx * dx;

                if (dzSq + dxSq <= radiusSq) {
                    origin.set(x + dx, origin.getY(), z + dz);
                    consumer.accept(origin);
                }
            }
        }
    }

    @Override
    public int foliageHeight(Random p_230374_1_, int p_230374_2_, TreeConfiguration p_230374_3_) {
        return Math.max(2, p_230374_2_ - (3 + p_230374_1_.nextInt(2)));
    }

    @Override
    protected boolean shouldSkipLocation(Random p_230373_1_, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
        return false;
    }
}
