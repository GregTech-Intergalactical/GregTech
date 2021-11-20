package muramasa.gti.tree;

import muramasa.antimatter.block.BlockBasic;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.util.Utils;
import muramasa.gti.Ref;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;

import java.util.Random;

import static muramasa.antimatter.Data.DUST;
import static muramasa.antimatter.Data.HAMMER;
import static muramasa.gti.data.Materials.RawRubber;

public class BlockRubberLog extends BlockBasic {

    final static EnumProperty<ResinState> RESIN_STATE = EnumProperty.create("resin_state", ResinState.class);
    final static DirectionProperty RESIN_FACING = BlockStateProperties.HORIZONTAL_FACING;
    final static EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

    final static double CHANCE_FILL = 0.3;

    public BlockRubberLog(String domain, String id) {
        super(domain, id, Block.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD).randomTicks());
        registerDefaultState(defaultBlockState().setValue(RESIN_STATE, ResinState.NONE).setValue(RESIN_FACING, Direction.NORTH).setValue(AXIS, Direction.Axis.Y));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(RESIN_STATE, RESIN_FACING, AXIS);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        super.randomTick(state, worldIn, pos, random);

        if (state.getValue(RESIN_STATE) != ResinState.EMPTY) {
            return;
        }
        if (random.nextDouble() < CHANCE_FILL) {
            worldIn.setBlock(pos, state.setValue(RESIN_STATE, ResinState.FILLED), 3);
        }
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isClientSide || state.getValue(RESIN_STATE) != ResinState.FILLED) {
            return ActionResultType.PASS;
        }
        if (Utils.isPlayerHolding(player, handIn, HAMMER)) {
            worldIn.setBlock(pos, state.setValue(RESIN_STATE, ResinState.EMPTY), 3);
            Direction dir = state.getValue(RESIN_FACING);
            BlockPos spawnPos = pos.offset(dir.getStepX(), dir.getStepY(), dir.getStepZ());
            InventoryHelper.dropItemStack(worldIn, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), DUST.get(RawRubber, 1));
            if (worldIn.random.nextDouble() > 0.5) {
                InventoryHelper.dropItemStack(worldIn, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), DUST.get(RawRubber, 1));
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.CONSUME;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(AXIS, context.getClickedFace().getAxis()).setValue(RESIN_FACING, context.getHorizontalDirection().getOpposite()).setValue(RESIN_STATE, ResinState.NONE);
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(getRegistryName().getNamespace(), "block/tree/rubber_log")};
    }

    @Override
    public void onBlockModelBuild(Block block, AntimatterBlockStateProvider prov) {
        ModelFile rubberLog = prov.existing(Ref.ID, "block/rubber_log");
        ModelFile rubberLogEmpty = prov.existing(Ref.ID, "block/rubber_log_empty");
        ModelFile rubberLogFilled = prov.existing(Ref.ID, "block/rubber_log_filled");
        prov.getVariantBuilder(block).forAllStates(s ->
                ConfiguredModel.builder().modelFile(s.getValue(RESIN_STATE) == ResinState.NONE ? rubberLog : s.getValue(RESIN_STATE) == ResinState.EMPTY ? rubberLogEmpty : rubberLogFilled)
                        .rotationY((int) s.getValue(RESIN_FACING).getOpposite().toYRot())
                        .rotationX(s.getValue(AXIS) == Direction.Axis.Y ? 0 : 90).build()
        );
    }
}
