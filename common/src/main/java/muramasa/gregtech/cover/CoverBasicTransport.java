package muramasa.gregtech.cover;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.Ref;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.capability.pipe.PipeCoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.cover.ICoverMode;
import muramasa.antimatter.cover.ICoverModeHandler;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tile.pipe.TileEntityPipe;
import muramasa.gregtech.GTIRef;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;


import javax.annotation.Nullable;

import static muramasa.gregtech.cover.ImportExportMode.EXPORT;
import static muramasa.gregtech.cover.ImportExportMode.IMPORT;

public abstract class CoverBasicTransport extends BaseCover implements ICoverModeHandler {

    protected ImportExportMode coverMode;
    int coverModeInt;

    public CoverBasicTransport(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
        this.coverMode = source.getTile() instanceof TileEntityPipe<?> ? IMPORT : EXPORT;
        coverModeInt = coverMode.ordinal();
    }

    @Override
    public String getDomain() {
        return GTIRef.ID;
    }

    @Override
    public ICoverMode getCoverMode() {
        return coverMode;
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){
            GuiEvents.GuiEvent ev = (GuiEvents.GuiEvent) event;
            coverMode = getCoverModeFromButton(ev.data[1]);
            coverModeInt = coverMode.ordinal();
        }
    }

    public ImportExportMode getCoverModeFromButton(int buttonID){
        return switch (buttonID) {
            case 0 -> IMPORT;
            case 1 -> ImportExportMode.IMPORT_EXPORT;
            case 2 -> ImportExportMode.IMPORT_CONDITIONAL;
            case 3 -> ImportExportMode.IMPORT_EXPORT_CONDITIONAL;
            case 4 -> ImportExportMode.IMPORT_INVERT_COND;
            case 5 -> ImportExportMode.IMPORT_EXPORT_INVERT_COND;
            case 7 -> ImportExportMode.EXPORT_IMPORT;
            case 8 -> ImportExportMode.EXPORT_CONDITIONAL;
            case 9 -> ImportExportMode.EXPORT_IMPORT_CONDITIONAL;
            case 10 -> ImportExportMode.EXPORT_INVERT_COND;
            case 11 -> ImportExportMode.EXPORT_IMPORT_INVERT_COND;
            default -> EXPORT;
        };
    }

    @Override
    public int coverModeToInt() {
        return coverMode.ordinal();
    }

    public ICoverMode getCoverMode(int index) {
        return ImportExportMode.values()[index];
    }

    @Override
    public CompoundTag serialize() {
        CompoundTag nbt =  super.serialize();
        nbt.putInt("coverMode", coverModeInt);
        return nbt;
    }

    @Override
    public void deserialize(CompoundTag nbt) {
        super.deserialize(nbt);
        if (nbt.contains("coverMode")) {
            coverModeInt = nbt.getInt("coverMode");
            coverMode = (ImportExportMode) getCoverMode(coverModeInt);
        }
    }
}
