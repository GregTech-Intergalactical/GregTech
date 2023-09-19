package muramasa.gregtech.cover.base;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.gui.ButtonOverlay;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.blockentity.pipe.BlockEntityPipe;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.cover.ICoverRedstoneSensitive;
import muramasa.gregtech.cover.ImportExportMode;
import muramasa.gregtech.cover.RedstoneMode;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;


import javax.annotation.Nullable;

import static muramasa.gregtech.cover.ImportExportMode.EXPORT;
import static muramasa.gregtech.cover.ImportExportMode.IMPORT;

public abstract class CoverBasicTransport extends CoverBasicRedstone implements ICoverRedstoneSensitive {

    protected ImportExportMode exportMode;
    int coverModeInt;

    public CoverBasicTransport(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
        this.exportMode = source.getTile() instanceof BlockEntityPipe<?> ? IMPORT : EXPORT;
        redstoneMode = RedstoneMode.NO_WORK;
        coverModeInt = exportMode.ordinal();
        addGuiCallback(t -> {
            t.addCycleButton(70, 34, 16, 16, h -> ((CoverBasicRedstone)h).redstoneMode.ordinal(), i -> "tooltip.gti.redstone_mode." + i, ButtonOverlay.TORCH_OFF, ButtonOverlay.TORCH_ON, ButtonOverlay.REDSTONE);
            t.addCycleButton(88, 34, 16, 16, h -> ((CoverBasicTransport)h).exportMode.ordinal(), i -> "tooltip.gti.export_mode." + i, ButtonOverlay.EXPORT, ButtonOverlay.IMPORT, ButtonOverlay.EXPORT_IMPORT, ButtonOverlay.IMPORT_EXPORT);
        });
    }

    @Override
    public String getDomain() {
        return GTIRef.ID;
    }

    @Override
    public void onPlace() {
        super.onPlace();
        if (handler.getTile() instanceof BlockEntityPipe<?> pipe){
            pipe.setConnection(this.side);
        }
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        super.onGuiEvent(event, playerEntity);
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){

            GuiEvents.GuiEvent ev = (GuiEvents.GuiEvent) event;
            if (ev.data[1] == 1){
                exportMode = ev.data[0] == 0 ? exportMode.next() : exportMode.previous();
                if (handler.getTile() instanceof BlockEntityPipe<?> pipe) pipe.onBlockUpdate(pipe.getBlockPos());
                if (handler.getTile() instanceof BlockEntityMachine<?> machine) machine.onBlockUpdate(machine.getBlockPos());
            }
        }
    }

    @Override
    public CompoundTag serialize() {
        CompoundTag nbt =  super.serialize();
        nbt.putInt("coverMode", exportMode.ordinal());
        return nbt;
    }

    @Override
    public void deserialize(CompoundTag nbt) {
        super.deserialize(nbt);
        if (nbt.contains("coverMode")) {
            coverModeInt = nbt.getInt("coverMode");
            if (coverModeInt > 3) coverModeInt = 2;
            exportMode = ImportExportMode.values()[coverModeInt];
        }
    }
}
