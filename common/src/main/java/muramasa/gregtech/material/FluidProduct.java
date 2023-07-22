package muramasa.gregtech.material;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;

import java.util.Objects;

public record FluidProduct(Material mat, int amount){
    public FluidHolder convert(){
        return mat.has(AntimatterMaterialTypes.LIQUID) ? mat.getLiquid(amount) : mat.getGas(amount);
    }
}
