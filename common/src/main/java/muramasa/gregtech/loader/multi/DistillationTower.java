package muramasa.gregtech.loader.multi;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.gregtech.data.GregTechMaterialTags;
import muramasa.gregtech.material.FluidProduct;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidAttributes;

import static muramasa.antimatter.data.AntimatterMaterials.Water;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.DISTILLATION;
import static muramasa.gregtech.data.RecipeMaps.DISTILLING;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

public class DistillationTower {
    public static void init() {
        /*DISTILL_INTO.getAll().forEach((material, fluidProducts) -> {
           RecipeBuilder builder = DISTILLATION.RB()
                   .fi(material.has(AntimatterMaterialTypes.LIQUID) ? material.getLiquid(DISTILLATION_FLUID_INPUT_AMOUNT.getInt(material)) : material.getGas(DISTILLATION_FLUID_INPUT_AMOUNT.getInt(material)));

           for(int i=0;i<fluidProducts.size();i++){
               Material fo = fluidProducts.get(i).mat();
               if (fo.has(AntimatterMaterialTypes.LIQUID)){
                   builder.fo(fluidProducts.get(i).mat().getLiquid(fluidProducts.get(i).amount()));
               } else if (fo.has(AntimatterMaterialTypes.GAS)){
                   builder.fo(fluidProducts.get(i).mat().getGas(fluidProducts.get(i).amount()));
               }

           }

           builder.add(material.getId(), material.getMass(), 20);
        });*/
        addDistillationDistillingRecipe(Creosote, 24, 16, 96, new FluidProduct(Lubricant, 12));
        addDistillationDistillingRecipe(FishOil, 24, 16, 96, new FluidProduct(Lubricant, 12));
        addDistillationDistillingRecipe(SeedOil, 32, 16, 96, new FluidProduct(Lubricant, 12));
        addDistillationDistillingRecipe(OilLight, 150, 20, 96,
                new FluidProduct(SulfuricHeavyFuel,10),
                new FluidProduct(SulfuricLightFuel,20),
                new FluidProduct(SulfuricNaphtha,30),
                new FluidProduct(SulfuricGas,240));
        addDistillationDistillingRecipe(OilMedium, 100, 20, 96,
                new FluidProduct(SulfuricHeavyFuel,15),
                new FluidProduct(SulfuricLightFuel,50),
                new FluidProduct(SulfuricNaphtha,20),
                new FluidProduct(SulfuricGas,60));
        addDistillationDistillingRecipe(Oil, 50, 20, 96,
                new FluidProduct(SulfuricHeavyFuel,15),
                new FluidProduct(SulfuricLightFuel,50),
                new FluidProduct(SulfuricNaphtha,20),
                new FluidProduct(SulfuricGas,60));
        addDistillationDistillingRecipe(OilHeavy, 100, 20, 96,
                new FluidProduct(SulfuricHeavyFuel,250),
                new FluidProduct(SulfuricLightFuel,45),
                new FluidProduct(SulfuricNaphtha,15),
                new FluidProduct(SulfuricGas,60));
        addDistillationDistillingRecipe(RefineryGas, 1000, 240, 120,
                new FluidProduct(Butane, 60),
                new FluidProduct(Propane, 70),
                new FluidProduct(Ethane, 100),
                new FluidProduct(Methane, 750),
                new FluidProduct(Helium, 20));
        addDistillationDistillingRecipe(DilutedSulfuricAcid, 2000, 600, 120,
                new FluidProduct(SulfuricAcid, 1000),
                new FluidProduct(Water, 1000));
        addDistillationDistillingRecipe(FermentedBiomass, 1000, 75, 180, 8, 7500);
        addDistillationRecipe(Water, 576, 16, 120, new FluidProduct(DistilledWater,520));
    }

    private static void addDistillationDistillingRecipe(Material input, int amount, int ticks, int euPerTick, int distilleryPerTick, int distilleryTicks, FluidProduct... outputs){
        addDistillationRecipe(input, amount, ticks, euPerTick, outputs);
        for (int i = 0; i < outputs.length; i++){
            DISTILLING.RB()
                    .ii(INT_CIRCUITS.get(i + 1).setNoConsume())
                    .fi(input.has(AntimatterMaterialTypes.LIQUID) ? input.getLiquid(amount) : input.getGas(amount))
                    .fo(outputs[i].convert())
                    .add(input.getId() + "_" + outputs[i].mat().getId(), distilleryTicks, distilleryPerTick);
        }
    }

    private static void addDistillationDistillingRecipe(Material input, int amount, int ticks, int euPerTick, FluidProduct... outputs){
        addDistillationDistillingRecipe(input, amount, ticks, euPerTick, euPerTick / 4, ticks, outputs);
    }

    private static void addDistillationRecipe(Material input, int amount, int ticks, int euPerTick, ItemStack itemOutput, FluidProduct... outputs){
        RecipeBuilder builder = DISTILLATION.RB().fi(input.has(AntimatterMaterialTypes.LIQUID) ? input.getLiquid(amount) : input.getGas(amount));
        for(int i=0;i<outputs.length;i++){
            Material fo = outputs[i].mat();
            if (fo.has(AntimatterMaterialTypes.LIQUID)){
                builder.fo(outputs[i].mat().getLiquid(outputs[i].amount()));
            } else if (fo.has(AntimatterMaterialTypes.GAS)){
                builder.fo(outputs[i].mat().getGas(outputs[i].amount()));
            }

        }

        builder.io(itemOutput).add(input.getId(), ticks, euPerTick);
    }

    private static void addDistillationRecipe(Material input, int amount, int ticks, int euPerTick, FluidProduct... outputs){
        RecipeBuilder builder = DISTILLATION.RB().fi(input.has(AntimatterMaterialTypes.LIQUID) ? input.getLiquid(amount) : input.getGas(amount));
        for(int i=0;i<outputs.length;i++){
            Material fo = outputs[i].mat();
            if (fo.has(AntimatterMaterialTypes.LIQUID)){
                builder.fo(outputs[i].mat().getLiquid(outputs[i].amount()));
            } else if (fo.has(AntimatterMaterialTypes.GAS)){
                builder.fo(outputs[i].mat().getGas(outputs[i].amount()));
            }

        }

        builder.add(input.getId(), ticks, euPerTick);
    }
}
