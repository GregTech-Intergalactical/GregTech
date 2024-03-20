package muramasa.gregtech.blockentity.multi;

import com.mojang.blaze3d.vertex.PoseStack;
import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongList;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.capability.IFilterableHandler;
import muramasa.antimatter.capability.machine.MultiMachineEnergyHandler;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.widget.InfoRenderWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.integration.jeirei.renderer.IInfoRenderer;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.event.MachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.util.int3;
import muramasa.gregtech.data.GregTechBlocks;
import muramasa.gregtech.worldgen.OilSpoutEntry;
import muramasa.gregtech.worldgen.OilSpoutSavedData;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractGraphWrappers;

import java.util.List;

import static muramasa.antimatter.gui.ICanSyncData.SyncDirection.SERVER_TO_CLIENT;
import static muramasa.gregtech.data.GregTechBlocks.MINING_PIPE;
import static muramasa.gregtech.data.GregTechBlocks.MINING_PIPE_THIN;

public class BlockEntityOilDrillingRig extends BlockEntityMultiMachine<BlockEntityOilDrillingRig> implements IFilterableHandler, IMiningPipeTile {
    boolean foundBottom = false;
    boolean stopped = false;
    int euPerTick;
    int cycle = 160;
    int progress = 0;
    BlockPos miningPos, centerPos;
    OilSpoutEntry oilEntry = null;

    public BlockEntityOilDrillingRig(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        miningPos = new int3(pos, this.getFacing(state)).back(1).immutable();
        centerPos = miningPos.below();
    }

    @Override
    public void onFirstTick() {
        super.onFirstTick();
        if (foundBottom){
            LongList positions = new LongArrayList();
            for (int y = miningPos.getY(); y < this.getBlockPos().getY(); y++) {
                positions.add(BlockPos.asLong(miningPos.getX(), y, miningPos.getZ()));
            }
            MiningPipeStructureCache.add(this.level, this.getBlockPos(), positions);
        }
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        boolean wasStopped = false;
        if (stopped && level.getGameTime() % 200 == 0){
            wasStopped = true;
            stopped = false;
        }
        if (!validStructure || stopped) return;
        ItemStack stack = itemHandler.map(i -> i.getHandler(SlotType.STORAGE).getStackInSlot(0)).orElse(ItemStack.EMPTY);
        if ((stack.getItem() == GregTechBlocks.MINING_PIPE_THIN.asItem() || foundBottom) && energyHandler.map(e -> e.getEnergy() >= euPerTick).orElse(false)){
            if (!foundBottom){

                if (getMachineState() == MachineState.IDLE) setMachineState(MachineState.ACTIVE);
                energyHandler.ifPresent(e -> e.extractEu(euPerTick, false));

                if (level.getGameTime() % 20 != 0) return;
                if (!wasStopped) {
                    miningPos = miningPos.below();
                }

                BlockState block = level.getBlockState(miningPos);
                MineResult breakResult = destroyBlock(level, miningPos, true, null, Items.NETHERITE_PICKAXE.getDefaultInstance());

                if (breakResult == MineResult.FOUND_BOTTOM){
                    foundBottom = true;
                    LongList positions = new LongArrayList();
                    for (int y = miningPos.getY(); y < this.getBlockPos().getY(); y++) {
                        positions.add(BlockPos.asLong(miningPos.getX(), y, miningPos.getZ()));
                    }
                    MiningPipeStructureCache.add(this.level, this.getBlockPos(), positions);
                    return;
                }

                if (breakResult == MineResult.FOUND_OBSTRUCTION){
                    stopped = true;
                    return;
                }
                if (breakResult == MineResult.FOUND_MINEABLE) {
                    stack.shrink(1);
                }
            } else {
                if (oilEntry == null){
                    oilEntry = OilSpoutSavedData.getOrCreate((ServerLevel) level).getFluidVeinWorldEntry(SectionPos.blockToSectionCoord(this.miningPos.getX()), SectionPos.blockToSectionCoord(this.miningPos.getZ()));
                }
                if (oilEntry.getFluid() == null) return;
                energyHandler.ifPresent(e -> e.extractEu(euPerTick, false));
                if (++progress == cycle){
                    progress = 0;
                    FluidHolder fluidHolder = FluidPlatformUtils.createFluidStack(oilEntry.getFluid().fluid(), oilEntry.getCurrentYield() * TesseractGraphWrappers.dropletMultiplier);
                    if (fluidHandler.map(f -> f.fillOutput(fluidHolder, true) == oilEntry.getCurrentYield() * TesseractGraphWrappers.dropletMultiplier).orElse(false)){
                        fluidHandler.ifPresent(f -> f.fillOutput(fluidHolder, false));
                        onMachineEvent(MachineEvent.FLUIDS_OUTPUTTED);
                        oilEntry.decreaseLevel();
                    }
                }
            }
        } else {
            if (getMachineState() == MachineState.ACTIVE) setMachineState(MachineState.IDLE);
        }
    }

    public MineResult destroyBlock(Level level, BlockPos pos, boolean dropBlock, @Nullable Entity entity, ItemStack item) {
        BlockState blockstate = level.getBlockState(pos);
        if (blockstate.getBlock() == Blocks.BEDROCK || blockstate.getBlock() == Blocks.VOID_AIR){
            return MineResult.FOUND_BOTTOM;
        }
        if (blockstate.getDestroySpeed(level, pos) < 0) {
            return MineResult.FOUND_OBSTRUCTION;
        } else {
            FluidState fluidstate = level.getFluidState(pos);
            if (!(blockstate.getBlock() instanceof BaseFireBlock)) {
                level.levelEvent(2001, pos, Block.getId(blockstate));
            }
            boolean miningPipe = blockstate.getBlock() == MINING_PIPE || blockstate.getBlock() == MINING_PIPE_THIN;

            CompoundTag tag = item.getTag();
            BlockEntity blockentity = blockstate.hasBlockEntity() ? level.getBlockEntity(pos) : null;
            //BlockEve event = new BlockEvent.BreakEvent(level, pos, blockstate, entity instanceof Player player ? player : null);
            //MinecraftForge.EVENT_BUS.post(event);
            /*if (event.isCanceled()){
                return false;
            }*/
            if (dropBlock && !miningPipe) {
                if (level instanceof ServerLevel serverLevel) {
                    List<ItemStack> drops = Block.getDrops(blockstate, serverLevel, pos, blockentity, null, item);
                    if (itemHandler.map(i -> i.canOutputsFit(drops.toArray(ItemStack[]::new))).orElse(false)){
                        itemHandler.ifPresent(i -> i.addOutputs(drops.toArray(ItemStack[]::new)));
                    } else {
                        drops.forEach(i -> Block.popResource(level, pos, i));
                    }
                    blockstate.spawnAfterBreak(serverLevel, pos, ItemStack.EMPTY);
                }
            }

            boolean flag = blockstate.getBlock() == MINING_PIPE || level.setBlock(pos, MINING_PIPE.defaultBlockState(), 3, 512);
            if (flag && pos.getY() + 1 < this.getBlockPos().getY()) {
                level.setBlock(pos.above(), MINING_PIPE_THIN.defaultBlockState(), 11);
                //level.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(entity, blockstate));
            }

            return miningPipe ? MineResult.FOUND_MINING_PIPE : MineResult.FOUND_MINEABLE;
        }
    }

    @Override
    public void afterStructureFormed() {
        super.afterStructureFormed();
        this.energyHandler.ifPresent(e -> {
            int tier = ((MultiMachineEnergyHandler<?>) e).getAccumulatedPower().getIntegerId();
            this.euPerTick = 3 * (1 << (tier << 1));
            this.cycle = (int) (160 * (tier == 0 ? 2 : Math.pow(0.5, tier - 1)));
        });
    }

    @Override
    public void onRemove() {
        super.onRemove();
        MiningPipeStructureCache.remove(this.level, this.getBlockPos());
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putBoolean("foundBottom", foundBottom);
        tag.putLong("miningPos", miningPos.asLong());
        tag.putInt("progress", progress);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.foundBottom = nbt.getBoolean("foundBottom");
        this.miningPos = BlockPos.of(nbt.getLong("miningPos"));
        this.progress = nbt.getInt("progress");
    }

    @Override
    public WidgetSupplier getInfoWidget() {
        return OilInfoWidget.build().setPos(10, 10);
    }

    @Override
    public int drawInfo(InfoRenderWidget.MultiRenderWidget instance, PoseStack stack, Font renderer, int left, int top) {
        OilInfoWidget oilInfoWidget = (OilInfoWidget) instance;
        renderer.draw(stack, this.getDisplayName().getString(), left, top, 16448255);
        if (getMachineState() != MachineState.ACTIVE) {
            renderer.draw(stack, "Inactive.", left, top + 8, 16448255);
            return 16;
        } else if (instance.drawActiveInfo()) {
            if (oilInfoWidget.foundBottom){
                renderer.draw(stack, "Progress: " + instance.currentProgress + "/" + instance.maxProgress, left, top + 8, 16448255);
                return 16;
            } else if (oilInfoWidget.stopped){
                renderer.draw(stack, "Can't mine at: " + oilInfoWidget.currentPos.toString(), left, top + 8, 16448255);
                return 16;
            }
        }
        return 8;
    }

    @Override
    public boolean test(SlotType<?> slotType, int slot, ItemStack stack) {
        return slotType != SlotType.STORAGE || stack.getItem() == MINING_PIPE_THIN.asItem();
    }

    @Override
    public void onMiningPipeUpdate(BlockPos miningPipePos) {
        BlockState pipe = level.getBlockState(miningPipePos);
        if (pipe.getBlock() != MINING_PIPE && pipe.getBlock() != MINING_PIPE_THIN){
            foundBottom = false;
            BlockPos centerPos = miningPos.atY(this.getBlockPos().getY() - 1);
            while (true){
                BlockState state = level.getBlockState(centerPos);
                if (state.getBlock() == MINING_PIPE || state.getBlock() == MINING_PIPE_THIN){
                    centerPos = centerPos.below();
                    continue;
                }
                break;
            }
            miningPos = centerPos;
            MiningPipeStructureCache.remove(level, this.getBlockPos());
        }
    }

    enum MineResult {
        FOUND_BOTTOM, FOUND_OBSTRUCTION, FOUND_MINING_PIPE, FOUND_MINEABLE
    }

    public static class OilInfoWidget extends InfoRenderWidget.MultiRenderWidget {
        BlockPos currentPos;
        boolean stopped;
        boolean foundBottom;


        protected OilInfoWidget(GuiInstance gui, IGuiElement parent, IInfoRenderer<MultiRenderWidget> renderer) {
            super(gui, parent, renderer);
        }

        @Override
        public void init() {
            BlockEntityOilDrillingRig m = (BlockEntityOilDrillingRig) gui.handler;
            gui.syncLong(() -> m.miningPos.asLong(), l -> currentPos = BlockPos.of(l), SERVER_TO_CLIENT);
            gui.syncBoolean(() -> m.stopped, s -> stopped = s, SERVER_TO_CLIENT);
            gui.syncBoolean(() -> m.foundBottom, b -> foundBottom = b, SERVER_TO_CLIENT);
            gui.syncInt(() -> m.progress, i -> currentProgress = i, SERVER_TO_CLIENT);
            gui.syncInt(() -> m.cycle, i -> maxProgress = i, SERVER_TO_CLIENT);
        }

        public static WidgetSupplier build() {
            return builder((a, b) -> new OilInfoWidget(a, b, (IInfoRenderer) a.handler));
        }
    }
}
