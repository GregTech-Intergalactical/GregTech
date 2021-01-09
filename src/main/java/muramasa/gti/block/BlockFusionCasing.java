package muramasa.gti.block;

import muramasa.antimatter.datagen.builder.AntimatterBlockModelBuilder;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.dynamic.ModelConfig;
import muramasa.antimatter.texture.Texture;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

import static muramasa.antimatter.client.AntimatterModelManager.LOADER_DYNAMIC;

public class BlockFusionCasing extends BlockTurbineCasing {

    @Override
    public ModelConfig getConfig(BlockState state, IBlockReader world, BlockPos.Mutable mut, BlockPos pos) {
        ModelConfig conf = super.getConfig(state, world, mut, pos);
        //This is what you call a "hack". This ensures that if there is no turbine
        //It returns a config of size 1 instead, otherwise it will incorrectly render the textures.
        if (conf.getConfig()[0] == -1) {
            conf.set(new int[]{conf.getConfig()[1]});
        }
        return conf;
    }

    @Override
    protected String getTextureID() {
        return "fusion";
    }

    public BlockFusionCasing(String domain, String id) {
        super(domain, id);
    }

    @Override
    public boolean canConnect(IBlockReader world, BlockState state, @Nullable TileEntity tile, BlockPos pos) {
        return state.getBlock() instanceof BlockFusionCasing;
    }

    //Default code for connected textures.
    @Override
    public AntimatterBlockModelBuilder buildBlock(Block block, AntimatterBlockStateProvider prov) {
        AntimatterBlockModelBuilder builder = super.buildBlock(block, prov);
        return builder.loader(LOADER_DYNAMIC).basicConfig(block, getConnectedTextures());
    }
}
