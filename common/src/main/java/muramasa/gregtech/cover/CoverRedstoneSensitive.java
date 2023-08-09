package muramasa.gregtech.cover;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.util.AntimatterCapUtils;
import muramasa.gregtech.cover.redstone.CoverRedstoneMachineController;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CoverRedstoneSensitive extends BaseCover {
    public CoverRedstoneSensitive(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    protected boolean isPowered(Direction side){
        return AntimatterCapUtils.getCoverHandler(handler.getTile(), side).map(h -> {
            List<CoverRedstoneMachineController> list = new ArrayList<>();
            for (Direction dir : Direction.values()){
                if (h.get(dir) instanceof CoverRedstoneMachineController machineController){
                    list.add(machineController);
                }
            }
            int i = 0;
            int j = 0;
            for (CoverRedstoneMachineController coverStack : list){
                j++;
                if (coverStack.isPowered()){
                    i++;
                }
            }
            return i > 0 && i == j;
        }).orElse(false);
    }
}
