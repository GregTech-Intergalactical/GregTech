package muramasa.gti.tree;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.blocks.BlockBasic;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.texture.Texture;
import muramasa.gti.Ref;
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
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;

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
        return this.getDefaultState().with(AXIS, context.getFace().getAxis()).with(RESIN_FACING, context.getPlacementHorizontalFacing().getOpposite()).with(RESIN_STATE, ResinState.NONE);
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
