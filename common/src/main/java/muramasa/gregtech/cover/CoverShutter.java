package muramasa.gregtech.cover;

import muramasa.antimatter.blockentity.pipe.BlockEntityPipe;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CoverShutter extends BaseCover implements ICoverRedstoneSensitive {
    Mode mode = Mode.OPEN_REDSTONE;
    boolean isPowered = false;
    public CoverShutter(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    public boolean canPlace() {
        return handler.getTile() instanceof BlockEntityPipe<?>;
    }

    @Override
    public void onPlace() {
        super.onPlace();
        checkPipeConnection();
    }

    @Override
    public <T> boolean blocksCapability(Class<T> cap, @Nullable Direction side) {
        if (side == null) return false;
        return (mode == Mode.OPEN_NO_REDSTONE && isPowered) || (mode == Mode.OPEN_REDSTONE && !isPowered);
    }

    @Override
    public <T> boolean blocksInput(Class<T> cap, @Nullable Direction side) {
        return mode == Mode.OUTPUT_ONLY;
    }

    @Override
    public <T> boolean blocksOutput(Class<T> cap, @Nullable Direction side) {
        return mode == Mode.INPUT_ONLY;
    }

    @Override
    public InteractionResult onInteract(Player player, InteractionHand hand, Direction side, @Nullable AntimatterToolType type) {
        if (type != null && type.getTag() == AntimatterDefaultTools.SCREWDRIVER.getTag()){
            mode = player.isShiftKeyDown() ? mode.cycleBackward() : mode.cycleForward();
            this.handler.getTile().setChanged();
            switch (mode){
                case OPEN_NO_REDSTONE -> {
                    if (handler.getTile() instanceof BlockEntityPipe<?> pipe){
                        if (isPowered){
                            pipe.clearConnection(this.side);
                        } else {
                            pipe.setConnection(this.side);
                        }
                    }
                    player.sendMessage(Utils.literal("Open if work disabled"), player.getUUID());
                }
                case OUTPUT_ONLY -> {
                    if (handler.getTile() instanceof BlockEntityPipe<?> pipe){
                        pipe.setConnection(this.side);
                    }
                    player.sendMessage(Utils.literal("Output only"), player.getUUID());
                }
                case INPUT_ONLY -> {
                    if (handler.getTile() instanceof BlockEntityPipe<?> pipe){
                        pipe.setConnection(this.side);
                    }
                    player.sendMessage(Utils.literal("Input only"), player.getUUID());
                }
                case OPEN_REDSTONE -> {
                    if (handler.getTile() instanceof BlockEntityPipe<?> pipe){
                        if (isPowered){
                            pipe.setConnection(this.side);
                        } else {
                            pipe.clearConnection(this.side);

                        }
                    }
                    player.sendMessage(Utils.literal("Open if work enabled"), player.getUUID());
                }
            }
            return InteractionResult.SUCCESS;
        }
        return super.onInteract(player, hand, side, type);
    }

    @Override
    public void onBlockUpdate() {
        checkPipeConnection();
    }

    private void checkPipeConnection(){
        BlockEntityPipe<?> pipe = (BlockEntityPipe<?>) handler.getTile();
        isPowered = isPowered(this.side);
        if (mode == Mode.OPEN_NO_REDSTONE || mode == Mode.OPEN_REDSTONE){
            boolean remove = (mode == Mode.OPEN_NO_REDSTONE && isPowered) || (mode == Mode.OPEN_REDSTONE && !isPowered);
            if (remove) pipe.clearConnection(this.side);
            else pipe.setConnection(this.side);
        }
    }

    @Override
    protected String getRenderId() {
        return "shutter";
    }

    @Override
    public String getId() {
        return "shutter";
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }

    enum Mode {
        INPUT_ONLY, OUTPUT_ONLY, OPEN_REDSTONE, OPEN_NO_REDSTONE;

        Mode cycleForward(){
            return switch (this){
                case OPEN_REDSTONE -> Mode.OPEN_NO_REDSTONE;
                case OPEN_NO_REDSTONE -> Mode.OUTPUT_ONLY;
                case OUTPUT_ONLY -> Mode.INPUT_ONLY;
                case INPUT_ONLY -> Mode.OPEN_REDSTONE;
            };
        }

        Mode cycleBackward(){
            return switch (this){
                case OPEN_REDSTONE -> Mode.INPUT_ONLY;
                case OPEN_NO_REDSTONE -> Mode.OPEN_REDSTONE;
                case OUTPUT_ONLY -> Mode.OPEN_NO_REDSTONE;
                case INPUT_ONLY -> Mode.OUTPUT_ONLY;
            };
        }
    }

    @Override
    public CompoundTag serialize() {
        CompoundTag nbt = super.serialize();
        nbt.putInt("mode", mode.ordinal());
        return nbt;
    }

    @Override
    public void deserialize(CompoundTag nbt) {
        super.deserialize(nbt);
        mode = Mode.values()[nbt.getInt("mode")];
    }
}
