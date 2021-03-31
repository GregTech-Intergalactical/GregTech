package muramasa.gti.tree;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.block.BlockBasic;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.util.Utils;
import muramasa.gti.Ref;
import muramasa.gti.data.GregTechData;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
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
        super(domain, id, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD).tickRandomly());
        setDefaultState(getDefaultState().with(RESIN_STATE, ResinState.NONE).with(RESIN_FACING, Direction.NORTH).with(AXIS, Direction.Axis.Y));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(RESIN_STATE, RESIN_FACING, AXIS);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        super.randomTick(state, worldIn, pos, random);

        if (state.get(RESIN_STATE) != ResinState.EMPTY) {
            return;
        }
        if (random.nextDouble() < CHANCE_FILL) {
            worldIn.setBlockState(pos, state.with(RESIN_STATE, ResinState.FILLED), 3);
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        if (state.get(RESIN_STATE) != ResinState.FILLED)
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);

        if (Utils.getToolType(player) == HAMMER) {
            worldIn.setBlockState(pos, state.with(RESIN_STATE, ResinState.EMPTY), 3);
            Direction dir = state.get(RESIN_FACING);
            pos = pos.add(dir.getXOffset(), dir.getYOffset(), dir.getZOffset());
            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(DUST.get(RawRubber), 1));
            if (worldIn.rand.nextDouble() > 0.5) {
                InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(DUST.get(RawRubber), 1));
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.CONSUME;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(AXIS, context.getFace().getAxis()).with(RESIN_FACING, context.getPlacementHorizontalFacing().getOpposite()).with(RESIN_STATE, ResinState.NONE);
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
            ConfiguredModel.builder().modelFile(s.get(RESIN_STATE) == ResinState.NONE ? rubberLog : s.get(RESIN_STATE) == ResinState.EMPTY ? rubberLogEmpty : rubberLogFilled)
                .rotationY((int) s.get(RESIN_FACING).getOpposite().getHorizontalAngle())
                .rotationX(s.get(AXIS) == Direction.Axis.Y ? 0 : 90).build()
        );
    }
}
