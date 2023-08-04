package muramasa.gregtech.block;

import muramasa.antimatter.block.BlockBasic;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.registration.IItemBlockProvider;
import muramasa.gregtech.GTIRef;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockMiningPipe extends BlockBasic implements IItemBlockProvider {
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
    public void onBlockModelBuild(Block block, AntimatterBlockStateProvider prov) {
        if (getId().equals("mining_pipe_thin")){
            prov.state(block, prov.getBuilder(block).parent(new ResourceLocation("block/fence_post")).texture("texture", new ResourceLocation(GTIRef.ID, "block/casing/brick")));
            return;
        }
        super.onBlockModelBuild(block, prov);
    }

    @Override
    public boolean generateItemBlock() {
        return !getId().equals("mining_pipe");
    }
}
