package muramasa.gti.tree;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.blocks.BlockBasic;
import muramasa.antimatter.texture.Texture;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;

public class BlockRubberLog extends BlockBasic {

    final static EnumProperty<ResinState> RESIN_STATE = EnumProperty.create("resin_state", ResinState.class);
    final static DirectionProperty RESIN_FACING = BlockStateProperties.HORIZONTAL_FACING;
    final static EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

    public BlockRubberLog(String domain) {
        super(domain, "rubber_log", Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD), new Texture(domain, "block/tree/rubber_log"));
        setDefaultState(getDefaultState().with(RESIN_STATE, ResinState.NONE).with(RESIN_FACING, Direction.NORTH).with(AXIS, Direction.Axis.Y));
        AntimatterAPI.register(BlockRubberLog.class, this);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(RESIN_STATE, RESIN_FACING, AXIS);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        //return this.getDefaultState().with(AXIS, context.getFace().getAxis());
        // place filled block for debugging
        return this.getDefaultState().with(AXIS, context.getFace().getAxis()).with(RESIN_FACING, context.getPlacementHorizontalFacing().getOpposite()).with(RESIN_STATE, ResinState.FILLED);
    }
}
