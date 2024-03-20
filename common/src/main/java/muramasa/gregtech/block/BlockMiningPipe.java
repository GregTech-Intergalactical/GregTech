package muramasa.gregtech.block;

import muramasa.antimatter.Ref;
import muramasa.antimatter.block.AntimatterItemBlock;
import muramasa.antimatter.block.BlockBasic;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.registration.IColorHandler;
import muramasa.antimatter.registration.IItemBlockProvider;
import muramasa.antimatter.texture.Texture;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.data.GregTechBlocks;
import muramasa.gregtech.data.Materials;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BlockMiningPipe extends BlockBasic implements IItemBlockProvider, IColorHandler {
    VoxelShape shape = null;
    public BlockMiningPipe(String domain, String id, Properties properties) {
        super(domain, id, properties);
        if (id.equals("mining_pipe_thin")){
            shape = Shapes.create(0.375, 0.0, 0.375, 0.625, 1.0, 0.625);
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState above = level.getBlockState(pos.above());
        return above.isFaceSturdy(level, pos.above(), Direction.DOWN) || above.getBlock() == GregTechBlocks.MINING_PIPE_THIN || above.getBlock() == GregTechBlocks.MINING_PIPE;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (this.id.equals("mining_pipe_thin")){
            return shape;
        }
        return super.getShape(state, level, pos, context);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide) {
            level.scheduleTick(pos, this, 1);
        }

    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (!level.isClientSide()) {
            level.scheduleTick(currentPos, this, 1);
        }

        return state;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        if (!canSurvive(state, level, pos)){
            level.destroyBlock(pos, true);
        }

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

    /*@Override
    public BlockItem getItemBlock() {
        return new AntimatterItemBlock(this){
            @Override
            protected boolean placeBlock(BlockPlaceContext context, BlockState state) {
                return context.getPlayer().isCreative();
            }
        };
    }*/
}
