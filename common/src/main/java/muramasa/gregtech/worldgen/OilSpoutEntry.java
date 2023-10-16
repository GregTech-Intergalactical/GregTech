package muramasa.gregtech.worldgen;

import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;


public class OilSpoutEntry {
    final OilSpoutFluid fluid;
    int currentYield;
    int level = 1000;
    public OilSpoutEntry(OilSpoutFluid fluid, int currentYield){
        this.fluid = fluid;
        this.currentYield = currentYield;
    }

    public void decreaseLevel(){
        if (fluid == null) return;
        if (currentYield == fluid.getMinYield()){
            return;
        }
        if (level == 0){
            if (currentYield > fluid.getMinYield()){
                currentYield--;
            }
            level = 1000;
        } else {
            level -= Math.min(level, fluid.getLevelDecrese());
        }
    }

    public CompoundTag writeToNBT() {
        var tag = new CompoundTag();
        if (fluid != null) {
            tag.putString("fluid", fluid.getId());
        }
        tag.putInt("currentYield", currentYield);
        tag.putInt("level", level);

        return tag;
    }

    @NotNull
    public static OilSpoutEntry readFromNBT(@NotNull CompoundTag tag) {
        OilSpoutEntry info = new OilSpoutEntry(tag.contains("fluid") ? OilSpoutSavedData.getOilSpoutFluid(tag.getString("fluid")) : null, tag.getInt("currentYield"));
        info.level = tag.getInt("level");
        return info;
    }

    public OilSpoutFluid getFluid() {
        return fluid;
    }

    public int getCurrentYield() {
        return currentYield;
    }

    public int getLevel() {
        return level;
    }
}
