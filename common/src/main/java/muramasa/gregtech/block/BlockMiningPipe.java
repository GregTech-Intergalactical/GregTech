package muramasa.gregtech.block;

import muramasa.antimatter.Ref;
import muramasa.antimatter.block.BlockBasic;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.registration.IColorHandler;
import muramasa.antimatter.registration.IItemBlockProvider;
import muramasa.antimatter.texture.Texture;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.data.Materials;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BlockMiningPipe extends BlockBasic implements IItemBlockProvider, IColorHandler {
    VoxelShape shape = null;
    public BlockMiningPipe(String domain, String id, Properties properties) {
        super(domain, id, properties);
        if (id.equals("mining_pipe_thin")){
            shape = Shapes.create(0.375, 0.0, 0.375, 0.625, 1.0, 0.625);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (this.id.equals("mining_pipe_thin")){
            return shape;
        }
        return super.getShape(state, level, pos, context);
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(GTIRef.ID, "block/pipe/mining_pipe")};
    }

    @Override
    public void onBlockModelBuild(Block block, AntimatterBlockStateProvider prov) {
        if (getId().equals("mining_pipe_thin")){
            prov.state(block, () -> prov.existing(GTIRef.ID,"block/mining_pipe_thin"));
            return;
        }
        super.onBlockModelBuild(block, prov);
    }

    @Override
    public boolean generateItemBlock() {
        return !getId().equals("mining_pipe");
    }

    @Override
    public int getBlockColor(BlockState state, @Nullable BlockGetter world, @Nullable BlockPos pos, int i) {
        return Materials.Steel.getRGB();
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        return Materials.Steel.getRGB();
    }
}
