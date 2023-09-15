package muramasa.gregtech.cover;

import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.util.AntimatterCapUtils;
import muramasa.gregtech.cover.redstone.CoverRedstoneMachineController;
import net.minecraft.core.Direction;

import java.util.ArrayList;
import java.util.List;

public interface ICoverRedstoneSensitive extends ICover {
    default boolean isPowered(Direction side){
        return AntimatterCapUtils.getCoverHandler(source().getTile(), side).map(h -> {
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
