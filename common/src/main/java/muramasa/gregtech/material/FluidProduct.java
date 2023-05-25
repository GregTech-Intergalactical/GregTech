package muramasa.gregtech.material;

import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import net.minecraftforge.fluids.FluidStack;

import java.util.Objects;

public record FluidProduct(Material mat, String mattype, int amount){
    public FluidStack convert(){
        if(Objects.equals(mattype, "fluid")){
            return mat.getLiquid(amount);
        }else if(Objects.equals(mattype, "gas")){
            return mat.getGas(amount);
        }else if(Objects.equals(mattype, "plasma")){
            return mat.getPlasma(amount);
        }else{
            return AntimatterMaterials.Water.getLiquid(1);
        }
    }
}
