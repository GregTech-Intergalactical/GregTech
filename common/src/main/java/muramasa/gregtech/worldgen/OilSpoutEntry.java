package muramasa.gregtech.worldgen;

import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.worldgen.AntimatterWorldGenerator;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.material.Fluid;

import javax.annotation.Nonnull;

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

    @Nonnull
    public static OilSpoutEntry readFromNBT(@Nonnull CompoundTag tag) {
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
