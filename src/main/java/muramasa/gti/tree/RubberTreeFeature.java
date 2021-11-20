package muramasa.gti.tree;

import muramasa.antimatter.worldgen.AntimatterWorldGenerator;
import muramasa.gti.Ref;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;

public class RubberTreeFeature extends TreeFeature {

    public RubberTreeFeature() {
        super(BaseTreeFeatureConfig.CODEC);
    }
    
    public void init() {
        AntimatterWorldGenerator.register(RubberTreeWorldGen::onEvent, "rubber_tree", Ref.ID, RubberTreeWorldGen.getValidBiomesStatic());
    }

    
    /*public boolean doPlace(ISeedReader reader, ChunkGenerator generator, Random random, BlockPos pos, BaseTreeFeatureConfig config) {
        int baseHeight = config.baseHeight + random.nextInt(config.heightRandA + 1) + random.nextInt(config.heightRandB + 1);
        int trunkHeight = config.trunkHeight >= 0 ? config.trunkHeight + random.nextInt(config.trunkHeightRandom + 1) : baseHeight - (config.foliageHeight + random.nextInt(config.foliageHeightRandom + 1));

        if (!isSoil(world, pos.down(), config.getSapling()))
            return false;

        // check if at least bare trunk may be placed
        for (int i = 1; i < trunkHeight; ++i)
            if (!func_214587_a(world, pos.add(0, i, 0)))
                return false;

         // fill upper trunk
        for (int y = trunkHeight; y < baseHeight; ++y)
            set.add(pos.add(0, y, 0));

        // fill branches
        for (int y = trunkHeight; y < baseHeight; ++y) {
            BlockPos p = pos.add(0,y,0);
            for(int d = 0; d < 4; ++d) {
                if (random.nextFloat() < 0.7) {
                    int len = Math.min(random.nextInt(12), baseHeight - y + 2);
                    Direction dir = Direction.byHorizontalIndex(d);
                    makeBranch(random, set, baseHeight, p.offset(dir,1), dir, len);
                }
            }
        }
        // check if the branches may be placed
        for (BlockPos bp : set)
            if (!func_214587_a(world, bp))
                return false;

        setDirtAt(world, pos.down(), pos);

        // set crown logs to default
        for (BlockPos bp : set) {
            setBlockState(world, bp, RUBBER_LOG.getDefaultState());
        }
        // place leaves
        for (BlockPos bp : set) {
            for(int d = 0; d < 6; ++d) {
                Direction dir = Direction.byIndex(d);
                BlockPos p = bp.offset(dir,1);
                if (!set.contains(p) && isAirOrLeaves(world, p) && !set1.contains(p)) {
                    setBlockState(world, p, RUBBER_LEAVES.getDefaultState());
                    set1.add(p);
                }
            }
        }
        makeBareTrunk(world, random, pos, set, config, trunkHeight);
        updateBoundingBox(pos, SetUtils.union(set, set1), boundingBox);
        return true;
    }

    private void updateBoundingBox(BlockPos pos, Set<BlockPos> set, MutableBoundingBox boundingBox) {
        int[] coords = {pos.getX(), pos.getY(), pos.getZ(), pos.getX(), pos.getY(), pos.getZ()};
        for (BlockPos bp : set) {
            coords[0] = Math.min(coords[0], bp.getX());
            coords[1] = Math.min(coords[1], bp.getY());
            coords[2] = Math.min(coords[2], bp.getZ());
            coords[3] = Math.max(coords[3], bp.getX());
            coords[4] = Math.max(coords[4], bp.getY());
            coords[5] = Math.max(coords[5], bp.getZ());
        }
        boundingBox.expandTo(new MutableBoundingBox(coords));
    }

    private void makeBranch(Random random, Set<BlockPos> set, int baseHeight, BlockPos branchStart, Direction dir, int len) {
        if (set.contains(branchStart.down())||set.contains(branchStart.down(2)))
            return;
        if (!tryContinueBranch(set, branchStart, dir))
            return;
        if (len > 1) {
            if (!tryContinueBranch(set, branchStart.up().offset(dir,1), dir))
                return;
        }
        if (len > 2) {
            if (!tryContinueBranch(set, branchStart.up(2).offset(dir,1), dir))
                return;
        }
        if (len > 3) {
            // roll for fork
            if (random.nextInt(6) == 0) {
                Direction newDir = dir.rotateYCCW();
                makeBranch(random, set, baseHeight, branchStart.offset(newDir,1).up(), newDir, len - 3);
                newDir = dir.rotateY();
                makeBranch(random, set, baseHeight, branchStart.offset(newDir,1).up(), newDir, len - 3);
            }
            // roll for turn left
            else if (random.nextInt(8) == 0) {
                Direction newDir = dir.rotateYCCW();
                makeBranch(random, set, baseHeight, branchStart.offset(newDir,1).up(), newDir, len - 3);
            }
            // roll for turn right
            else if (random.nextInt(8) == 0) {
                Direction newDir = dir.rotateY();
                makeBranch(random, set, baseHeight, branchStart.offset(newDir,1).up(), newDir, len - 3);
            }
            // continue straight
            else
                makeBranch(random, set, baseHeight, branchStart.offset(dir,1).up(), dir, len - 3);
        }
    }

    private boolean tryContinueBranch(Set<BlockPos> set, BlockPos p, Direction dir) {
        if (set.contains(p.offset(dir,1))||set.contains(p.offset(dir.rotateY(),1))|| set.contains(p.offset(dir.rotateYCCW(),1)))
            return false;
        set.add(p);
        return true;
    }

    private void makeBareTrunk(IWorldGenerationReader world, Random random, BlockPos pos, Set<BlockPos> set, TreeFeatureConfig config, int trunkHeight) {
        for (int i = 0; i < trunkHeight; ++i) {
            BlockPos p = pos.add(0, i, 0);
            set.add(p);
            this.setBlockState(world, p, config.trunkProvider.getBlockState(random, pos));
        }
    }*/

    /*@Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, BaseTreeFeatureConfig config) {
        return false;
    }*/
}