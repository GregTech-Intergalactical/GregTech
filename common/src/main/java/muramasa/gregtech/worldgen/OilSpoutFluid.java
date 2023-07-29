package muramasa.gregtech.worldgen;

import net.minecraft.world.level.material.Fluid;

import java.util.Objects;

public final class OilSpoutFluid {
    private static int totalWeight = 0;
    private final String id;
    private final Fluid fluid;
    private final int maxYield, minYield, levelDecrese;
    private final int chance;

    public OilSpoutFluid(String id, Fluid fluid, int chance, int maxYield, int minYield, int levelDecrease) {
        this.id = id;
        this.fluid = fluid;
        this.chance = chance;
        this.maxYield = maxYield;
        this.minYield = minYield;
        this.levelDecrese = levelDecrease;
        OilSpoutSavedData.registerOilSpoutFluid(id, this);
        totalWeight += chance;
    }

    public Fluid fluid() {
        return fluid;
    }

    public int chance() {
        return chance;
    }

    public int getMaxYield() {
        return maxYield;
    }

    public int getMinYield() {
        return minYield;
    }

    public int getLevelDecrese() {
        return levelDecrese;
    }

    public String getId() {
        return id;
    }

    public static void resetTotalWeight(){
        totalWeight = 0;
    }

    public static int getTotalWeight() {
        return totalWeight;
    }
}
