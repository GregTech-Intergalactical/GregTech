package muramasa.gregtech.material;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import net.minecraftforge.fluids.FluidStack;

import java.util.Objects;

public record FluidProduct(Material mat, int amount){
    public FluidStack convert(){
        return mat.has(AntimatterMaterialTypes.LIQUID) ? mat.getLiquid(amount) : mat.getGas(amount);
    }
}
