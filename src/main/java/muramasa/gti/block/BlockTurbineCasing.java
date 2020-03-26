package muramasa.gti.block;

import muramasa.antimatter.Ref;
import muramasa.antimatter.block.BlockCasing;
import muramasa.antimatter.client.ModelConfig;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.gti.tile.multi.TileEntityLargeTurbine;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockTurbineCasing extends BlockCasing {

    public BlockTurbineCasing() {
        super();
        //setDefaultModel(true);
    }

    @Override
    public ModelConfig getConfig(BlockState state, IBlockReader world, BlockPos.Mutable mut, BlockPos pos) {
        int[] ct = new int[6];
        TileEntity tile;
        for (int s = 0; s < 6; s++) {
            if ((tile = world.getTileEntity(pos.offset(Ref.DIRECTIONS[s]))) instanceof TileEntityLargeTurbine) {
                ct[s] = (1 << s) + (((TileEntityMachine) tile).getFacing().getIndex() * 100) /*+ ((TileEntityLargeTurbine) tile).getClientProgress() > 0 ? 1000 : 0*/;
            } else if ((tile = world.getTileEntity(pos.offset(Ref.DIRECTIONS[s]).down())) instanceof TileEntityLargeTurbine) {
                ct[s] = (1 << s) + (1 << Direction.DOWN.getIndex()) + (((TileEntityLargeTurbine) tile).getFacing().getIndex() * 100) /*+ ((TileEntityLargeTurbine) tile).getClientProgress() > 0 ? 1000 : 0*/;
            } else if ((tile = world.getTileEntity(pos.offset(Ref.DIRECTIONS[s]).up())) instanceof TileEntityLargeTurbine) {
                ct[s] = (1 << s) + (1 << Direction.UP.getIndex()) + (((TileEntityLargeTurbine) tile).getFacing().getIndex() * 100) /*+ ((TileEntityLargeTurbine) tile).getClientProgress() > 0 ? 1000 : 0*/;
            }
        }
        return config.set(ct);
    }

//    @Override
//    public void onConfigBuild() {
//        add(216, () -> ModelUtils.bakeQuad(LARGE_TURBINE[3], 2)); //North
//        add(232, () -> ModelUtils.bakeQuad(LARGE_TURBINE[5], 2));
//        add(201, () -> ModelUtils.bakeQuad(LARGE_TURBINE[1], 2));
//        add(202, () -> ModelUtils.bakeQuad(LARGE_TURBINE[7], 2));
//
//        add(332, () -> ModelUtils.bakeQuad(LARGE_TURBINE[3], 3)); //South
//        add(316, () -> ModelUtils.bakeQuad(LARGE_TURBINE[5], 3));
//        add(301, () -> ModelUtils.bakeQuad(LARGE_TURBINE[1], 3));
//        add(302, () -> ModelUtils.bakeQuad(LARGE_TURBINE[7], 3));
//
//        add(408, () -> ModelUtils.bakeQuad(LARGE_TURBINE[3], 4)); //West
//        add(404, () -> ModelUtils.bakeQuad(LARGE_TURBINE[5], 4));
//        add(401, () -> ModelUtils.bakeQuad(LARGE_TURBINE[1], 4));
//        add(402, () -> ModelUtils.bakeQuad(LARGE_TURBINE[7], 4));
//
//        add(504, () -> ModelUtils.bakeQuad(LARGE_TURBINE[3], 5)); //East
//        add(508, () -> ModelUtils.bakeQuad(LARGE_TURBINE[5], 5));
//        add(501, () -> ModelUtils.bakeQuad(LARGE_TURBINE[1], 5));
//        add(502, () -> ModelUtils.bakeQuad(LARGE_TURBINE[7], 5));
//
//        add(217, () -> ModelUtils.bakeQuad(LARGE_TURBINE[0], 2)); //North Corners
//        add(233, () -> ModelUtils.bakeQuad(LARGE_TURBINE[2], 2));
//        add(218, () -> ModelUtils.bakeQuad(LARGE_TURBINE[6], 2));
//        add(234, () -> ModelUtils.bakeQuad(LARGE_TURBINE[8], 2));
//
//        add(333, () -> ModelUtils.bakeQuad(LARGE_TURBINE[0], 3)); //South Corners
//        add(317, () -> ModelUtils.bakeQuad(LARGE_TURBINE[2], 3));
//        add(334, () -> ModelUtils.bakeQuad(LARGE_TURBINE[6], 3));
//        add(318, () -> ModelUtils.bakeQuad(LARGE_TURBINE[8], 3));
//
//        add(409, () -> ModelUtils.bakeQuad(LARGE_TURBINE[0], 4)); //West Corners
//        add(405, () -> ModelUtils.bakeQuad(LARGE_TURBINE[2], 4));
//        add(410, () -> ModelUtils.bakeQuad(LARGE_TURBINE[6], 4));
//        add(406, () -> ModelUtils.bakeQuad(LARGE_TURBINE[8], 4));
//
//        add(505, () -> ModelUtils.bakeQuad(LARGE_TURBINE[0], 5)); //East Corners
//        add(509, () -> ModelUtils.bakeQuad(LARGE_TURBINE[2], 5));
//        add(506, () -> ModelUtils.bakeQuad(LARGE_TURBINE[6], 5));
//        add(510, () -> ModelUtils.bakeQuad(LARGE_TURBINE[8], 5));
//
//        add(1216, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[3], 2)); //North
//        add(1232, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[5], 2));
//        add(1201, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[1], 2));
//        add(1202, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[7], 2));
//
//        add(1332, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[3], 3)); //South
//        add(1316, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[5], 3));
//        add(1301, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[1], 3));
//        add(1302, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[7], 3));
//
//        add(1408, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[3], 4)); //West
//        add(1404, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[5], 4));
//        add(1401, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[1], 4));
//        add(1402, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[7], 4));
//
//        add(1504, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[3], 5)); //East
//        add(1508, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[5], 5));
//        add(1501, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[1], 5));
//        add(1502, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[7], 5));
//
//        add(1217, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[0], 2)); //North Corners
//        add(1233, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[2], 2));
//        add(1218, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[6], 2));
//        add(1234, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[8], 2));
//
//        add(1333, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[0], 3)); //South Corners
//        add(1317, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[2], 3));
//        add(1334, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[6], 3));
//        add(1318, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[8], 3));
//
//        add(1409, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[0], 4)); //West Corners
//        add(1405, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[2], 4));
//        add(1410, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[6], 4));
//        add(1406, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[8], 4));
//
//        add(1505, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[0], 5)); //East Corners
//        add(1509, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[2], 5));
//        add(1506, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[6], 5));
//        add(1510, () -> ModelUtils.bakeQuad(LARGE_TURBINE_ACTIVE[8], 5));
//    }

//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public void getTextures(Set<ResourceLocation> textures) {
//        super.getTextures(textures);
//        textures.addAll(Arrays.asList(LARGE_TURBINE));
//        textures.addAll(Arrays.asList(LARGE_TURBINE_ACTIVE));
//    }
}
