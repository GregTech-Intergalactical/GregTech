package muramasa.gregtech.loader.machines;
import muramasa.antimatter.material.Material;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.material.MaterialTags.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.antimatter.material.Material.get;
import static muramasa.gregtech.data.RecipeMaps.DECAYING;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

public class DecayChamber {
    public static void init() {
        DECAYABLE.all().forEach(d -> {
            int decaytime;
            long hls = d.getElement().halfLifeSeconds;
            if (hls < 60){
                decaytime = 20;
            }else if (hls < 3600){
                decaytime = 20*10;
            }else if (hls < 3600*24){
                decaytime = 20*30;
            }else if (hls < 3600*24*7){
                decaytime = 20*60;
            }else if (hls < 3600*24*7*30){
                decaytime = 20*120;
            }else if (hls < 3600*24*365){
                decaytime = 20*300;
            }else{
                decaytime = 20*600;
            }
            if(d.has(INGOT)){
                Item ingot = INGOT.get(d);
                if(d.getElement().decayIntoA != null){
                    Material alphaproduct = get(d.getElement().decayIntoA);
                    if(alphaproduct.has(INGOT)){
                        DECAYING.RB().ii(of(ingot,1), INT_CIRCUITS.get(0).setNoConsume()).io(new ItemStack(INGOT.get(alphaproduct))).add("alpha_decaying_"+ d.getId() +"_ingot_to_ingot",decaytime, 30);
                    }else if(alphaproduct.has(LIQUID)){
                        DECAYING.RB().ii(of(ingot,1), INT_CIRCUITS.get(0).setNoConsume()).fo(alphaproduct.getLiquid(1000)).add("alpha_decaying_"+ d.getId() +"_ingot_to_fluid",decaytime, 30);
                    }else if(alphaproduct.has(GAS)){
                        DECAYING.RB().ii(of(ingot,1), INT_CIRCUITS.get(0).setNoConsume()).fo(alphaproduct.getGas(1000)).add("alpha_decaying_"+ d.getId() +"_ingot_to_gas",decaytime, 30);
                    }
                }
                if(d.getElement().decayIntoBP != null){
                    Material betaplusproduct = get(d.getElement().decayIntoBP);
                    if(betaplusproduct.has(INGOT)){
                        DECAYING.RB().ii(of(ingot,1), INT_CIRCUITS.get(1).setNoConsume()).io(new ItemStack(INGOT.get(betaplusproduct))).add("beta_plus_decaying_"+ d.getId() +"_fluid_to_ingot",decaytime, 30);
                    }else if(betaplusproduct.has(LIQUID)){
                        DECAYING.RB().ii(of(ingot,1), INT_CIRCUITS.get(1).setNoConsume()).fo(betaplusproduct.getLiquid(1000)).add("beta_aplus_decaying_"+ d.getId() +"_fluid_to_fluid",decaytime, 30);
                    }else if(betaplusproduct.has(GAS)){
                        DECAYING.RB().ii(of(ingot,1), INT_CIRCUITS.get(1).setNoConsume()).fo(betaplusproduct.getGas(1000)).add("beta_plus_decaying_"+ d.getId() +"_fluid_to_gas",decaytime, 30);
                    }
                }
                if(d.getElement().decayIntoBM != null){
                    Material betaminusproduct = get(d.getElement().decayIntoBM);
                    if(betaminusproduct.has(INGOT)){
                        DECAYING.RB().ii(of(ingot,1), INT_CIRCUITS.get(2).setNoConsume()).io(new ItemStack(INGOT.get(betaminusproduct))).add("beta_minus_decaying_"+ d.getId() +"_gas_to_ingot",decaytime, 30);
                    }else if(betaminusproduct.has(LIQUID)){
                        DECAYING.RB().ii(of(ingot,1), INT_CIRCUITS.get(2).setNoConsume()).fo(betaminusproduct.getLiquid(1000)).add("beta_minus_decaying_"+ d.getId() +"_gas_to_fluid",decaytime, 30);
                    }else if(betaminusproduct.has(GAS)){
                        DECAYING.RB().ii(of(ingot,1), INT_CIRCUITS.get(2).setNoConsume()).fo(betaminusproduct.getGas(1000)).add("beta_minus_decaying_"+ d.getId() +"_gas_to_gas",decaytime, 30);
                    }
                }
            }else if(d.has(LIQUID)){
                FluidStack stack = d.getLiquid(1000);
                if(d.getElement().decayIntoA != null){
                    Material alphaproduct = get(d.getElement().decayIntoA);
                    if(alphaproduct.has(INGOT)){
                        DECAYING.RB().ii(INT_CIRCUITS.get(0).setNoConsume()).fi(stack).io(new ItemStack(INGOT.get(alphaproduct))).add("alpha_decaying_"+ d.getId() +"_fluid_to_ingot",decaytime, 30);
                    }else if(alphaproduct.has(LIQUID)){
                        DECAYING.RB().ii(INT_CIRCUITS.get(0).setNoConsume()).fi(stack).fo(alphaproduct.getLiquid(1000)).add("alpha_decaying_"+ d.getId() +"_fluid_to_fluid",decaytime, 30);
                    }else if(alphaproduct.has(GAS)){
                        DECAYING.RB().ii(INT_CIRCUITS.get(0).setNoConsume()).fi(stack).fo(alphaproduct.getGas(1000)).add("alpha_decaying_"+ d.getId() +"_fluid_to_gas",decaytime, 30);
                    }
                }
                if(d.getElement().decayIntoBP != null){
                    Material betaplusproduct = get(d.getElement().decayIntoBP);
                    if(betaplusproduct.has(INGOT)){
                        DECAYING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(stack).io(new ItemStack(INGOT.get(betaplusproduct))).add("beta_plus_decaying_"+ d.getId() +"_fluid_to_ingot",decaytime, 30);
                    }else if(betaplusproduct.has(LIQUID)){
                        DECAYING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(stack).fo(betaplusproduct.getLiquid(1000)).add("beta_plus_decaying_"+ d.getId() +"_fluid_to_fluid",decaytime, 30);
                    }else if(betaplusproduct.has(GAS)){
                        DECAYING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(stack).fo(betaplusproduct.getGas(1000)).add("beta_plus_decaying_"+ d.getId() +"_fluid_to_gas",decaytime, 30);
                    }
                }
                if(d.getElement().decayIntoBM != null){
                    Material betaminusproduct = get(d.getElement().decayIntoBM);
                    if(betaminusproduct.has(INGOT)){
                        DECAYING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(stack).io(new ItemStack(INGOT.get(betaminusproduct))).add("beta_minus_decaying_"+ d.getId() +"_fluid_to_ingot",decaytime, 30);
                    }else if(betaminusproduct.has(LIQUID)){
                        DECAYING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(stack).fo(betaminusproduct.getLiquid(1000)).add("beta_minus_decaying_"+ d.getId() +"_fluid_to_fluid",decaytime, 30);
                    }else if(betaminusproduct.has(GAS)){
                        DECAYING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(stack).fo(betaminusproduct.getGas(1000)).add("beta_minus_decaying_"+ d.getId() +"_fluid_to_gas",decaytime, 30);
                    }
                }
            }else{
                FluidStack stack = d.getGas(1000);
                if(d.getElement().decayIntoA != null){
                    Material alphaproduct = get(d.getElement().decayIntoA);
                    if(alphaproduct.has(INGOT)){
                        DECAYING.RB().ii(INT_CIRCUITS.get(0).setNoConsume()).fi(stack).io(new ItemStack(INGOT.get(alphaproduct))).add("alpha_decaying_"+ d.getId() +"_gas_to_ingot",decaytime, 30);
                    }else if(alphaproduct.has(LIQUID)){
                        DECAYING.RB().ii(INT_CIRCUITS.get(0).setNoConsume()).fi(stack).fo(alphaproduct.getLiquid(1000)).add("alpha_decaying_"+ d.getId() +"_gas_to_fluid",decaytime, 30);
                    }else if(alphaproduct.has(GAS)){
                        DECAYING.RB().ii(INT_CIRCUITS.get(0).setNoConsume()).fi(stack).fo(alphaproduct.getGas(1000)).add("alpha_decaying_"+ d.getId() +"_gas_to_gas",decaytime, 30);
                    }
                }
                if(d.getElement().decayIntoBP != null){
                    Material betaplusproduct = get(d.getElement().decayIntoBP);
                    if(betaplusproduct.has(INGOT)){
                        DECAYING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(stack).io(new ItemStack(INGOT.get(betaplusproduct))).add("beta_plus_decaying_"+ d.getId() +"_fluid_to_ingot",decaytime, 30);
                    }else if(betaplusproduct.has(LIQUID)){
                        DECAYING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(stack).fo(betaplusproduct.getLiquid(1000)).add("beta_aplus_decaying_"+ d.getId() +"_fluid_to_fluid",decaytime, 30);
                    }else if(betaplusproduct.has(GAS)){
                        DECAYING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(stack).fo(betaplusproduct.getGas(1000)).add("beta_plus_decaying_"+ d.getId() +"_fluid_to_gas",decaytime, 30);
                    }
                }
                if(d.getElement().decayIntoBM != null){
                    Material betaminusproduct = get(d.getElement().decayIntoBM);
                    if(betaminusproduct.has(INGOT)){
                        DECAYING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(stack).io(new ItemStack(INGOT.get(betaminusproduct))).add("beta_minus_decaying_"+ d.getId() +"_gas_to_ingot",decaytime, 30);
                    }else if(betaminusproduct.has(LIQUID)){
                        DECAYING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(stack).fo(betaminusproduct.getLiquid(1000)).add("beta_minus_decaying_"+ d.getId() +"_gas_to_fluid",decaytime, 30);
                    }else if(betaminusproduct.has(GAS)){
                        DECAYING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(stack).fo(betaminusproduct.getGas(1000)).add("beta_minus_decaying_"+ d.getId() +"_gas_to_gas",decaytime, 30);
                    }
                }
            }
        });
    }
}
