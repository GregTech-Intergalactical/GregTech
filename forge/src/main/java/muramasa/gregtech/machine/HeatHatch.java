package muramasa.gregtech.machine;

import muramasa.antimatter.Data;
import muramasa.antimatter.machine.types.HatchMachine;
import muramasa.antimatter.material.Material;

public class HeatHatch extends HatchMachine {

    public final Material mat;
    public final int heatCoefficient;

    public HeatHatch(String domain, String id, Material material, int coeff) {
        super(domain, id, Data.COVERHEAT);
        setTile(BlockEntityHatchHeat::new);
        this.mat = material;
        this.heatCoefficient = coeff;
    }
}
