package muramasa.gregtech.loader.multi;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.gregtech.GregTechConfig;
import muramasa.gregtech.data.GregTechMaterialTags;
import muramasa.gregtech.material.FluidProduct;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST_SMALL;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.*;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

public class DistillationTower {
    public static void init() {
        if (GregTechConfig.GT5U_OIL.get()){
            init5U();
        } else {
            init6U();
        }

        addDistillingRecipe(Creosote, 24, 16, 96, ItemStack.EMPTY, new FluidProduct(Lubricant, 12));
        addDistillingRecipe(FishOil, 24, 16, 96, ItemStack.EMPTY, new FluidProduct(Lubricant, 12));
        addDistillingRecipe(SeedOil, 32, 16, 96, ItemStack.EMPTY, new FluidProduct(Lubricant, 12));
        addDistillationRecipe(Water, 576, 16, 120, new FluidProduct(DistilledWater,520));
        addDistillationDistillingRecipe(DilutedSulfuricAcid, 2000, 600, 120,
                new FluidProduct(SulfuricAcid, 1000),
                new FluidProduct(Water, 1000));

        addDistillationDistillingRecipe(FermentedBiomass, 1000, 75, 180, 8, 1500, ItemStack.EMPTY,
                new FluidProduct(AceticAcid, 25),
                new FluidProduct(Water, 375),
                new FluidProduct(Ethanol, 150),
                new FluidProduct(Methanol, 150),
                new FluidProduct(Ammonia, 100),
                new FluidProduct(CarbonDioxide, 400),
                new FluidProduct(Methane, 600));
        addDistillationDistillingRecipe(CharcoalByproducts, 1000, 40, 256, 64, 80, DUST_SMALL.get(Charcoal,1),
                new FluidProduct(WoodTar, 250),
                new FluidProduct(WoodVinegar, 500),
                new FluidProduct(WoodGas, 250));
        addDistillationDistillingRecipe(WoodGas, 1000, 40, 256, 64, 16, ItemStack.EMPTY,
                new FluidProduct(CarbonDioxide, 490),
                new FluidProduct(Ethylene, 20),
                new FluidProduct(Methane, 130),
                new FluidProduct(CarbonMonoxide, 340),
                new FluidProduct(Hydrogen, 20));
        addDistillationDistillingRecipe(Biomass, 600, 16, 400, 24, 16, DUST_SMALL.get(Wood, 1),
                new FluidProduct(Ethanol, 240),
                new FluidProduct(Water,240));
        addDistillationDistillingRecipe(RefineryGas, 1000, 240, 120,
                new FluidProduct(Butane, 60),
                new FluidProduct(Propane, 70),
                new FluidProduct(Ethane, 100),
                new FluidProduct(Methane, 750),
                new FluidProduct(Helium, 20));
        addDistillationRecipe(SteamCrackedButane, 1000, 120, 120, DUST_SMALL.get(Carbon, 1),
                new FluidProduct(Butadiene,500), new FluidProduct(Propene,1000),
                new FluidProduct(Ethylene,1000),new FluidProduct(Methane,500));
        addDistillationRecipe(SteamCrackedNaphtha, 1000, 120, 120, DUST_SMALL.get(Carbon, 1),
                new FluidProduct(HeavyFuel,100), new FluidProduct(LightFuel,100),
                new FluidProduct(Toluene,200),new FluidProduct(Benzene,400),
                new FluidProduct(Butadiene,400),new FluidProduct(Propene,600),
                new FluidProduct(Ethylene,600), new FluidProduct(Methane,600));
        addDistillationRecipe(SteamCrackedRefineryGas, 1000, 120, 120, DUST_SMALL.get(Carbon, 1),
                new FluidProduct(Butadiene,60),new FluidProduct(Propene,70),
                new FluidProduct(Ethylene,100), new FluidProduct(Methane,750),
                new FluidProduct(Helium, 20));
        addDistillationRecipe(SteamCrackedEthane, 1000, 120, 120, DUST_SMALL.get(Carbon, 1),
                new FluidProduct(Ethylene,1000), new FluidProduct(Methane,500));
        addDistillationRecipe(SteamCrackedPropane, 1000, 120, 120, DUST_SMALL.get(Carbon, 1),
                new FluidProduct(Propene,500), new FluidProduct(Ethylene,1000),
                new FluidProduct(Methane,500));
        addDistillationRecipe(HydroCrackedEthane, 1000, 120, 120, new FluidProduct(Methane,2000));
        addDistillationRecipe(HydroCrackedRefineryGas, 1000, 120, 120,
                new FluidProduct(Methane,1500), new FluidProduct(Helium, 20));
        addDistillationRecipe(HydroCrackedPropane, 1000, 120, 120,
                new FluidProduct(Ethane, 1000), new FluidProduct(Methane, 1000));
        addDistillationRecipe(HydroCrackedButane, 1000, 120, 120,
                new FluidProduct(Propane, 1000), new FluidProduct(Ethane, 1000),
                new FluidProduct(Methane, 1000));

        addDistillationRecipe(HydroCrackedNaphtha, 1000, 120, 120,
                new FluidProduct(Butane, 750), new FluidProduct(Propane, 750),
                new FluidProduct(Ethane, 750), new FluidProduct(Methane, 750));

        if (GregTechConfig.MORE_COMPLICATED_CHEMICAL_RECIPES){
            initComplicated();
        } else {
            initSimple();
        }
        CRYO_DISTILLATION.RB().fi(Air.getGas(200)).fo(Helium.getGas(1), Neon.getGas(1), Argon.getGas(1), Nitrogen.getGas(143), Oxygen.getGas(50), CarbonDioxide.getGas(10)).add("air_distillation", 64, 64);
    }

    private static void init6U(){
        addDistillationDistillingRecipe(OilLight, 25, 64, 64,
                new FluidProduct(Diesel,15),
                new FluidProduct(Naphtha, 10),
                new FluidProduct(Kerosene, 10),
                new FluidProduct(Gasoline, 10),
                new FluidProduct(RefineryGas, 25),
                new FluidProduct(Lubricant, 15),
                new FluidProduct(Tar, 15));
        addDistillationDistillingRecipe(Oil, 25, 64, 64,
                new FluidProduct(Diesel,25),
                new FluidProduct(Naphtha, 15),
                new FluidProduct(Kerosene, 15),
                new FluidProduct(Gasoline, 15),
                new FluidProduct(RefineryGas, 15),
                new FluidProduct(Lubricant, 25),
                new FluidProduct(Tar, 15));
        addDistillationDistillingRecipe(OilHeavy, 25, 64, 64,
                new FluidProduct(Diesel,30),
                new FluidProduct(Naphtha, 20),
                new FluidProduct(Kerosene, 20),
                new FluidProduct(Gasoline, 15),
                new FluidProduct(RefineryGas, 10),
                new FluidProduct(Lubricant, 40),
                new FluidProduct(Tar, 15));
    }

    private static void init5U(){
        addDistillationDistillingRecipe(OilLight, 150, 20, 96,
                new FluidProduct(SulfuricHeavyFuel,10),
                new FluidProduct(SulfuricLightFuel,20),
                new FluidProduct(SulfuricNaphtha,30),
                new FluidProduct(SulfuricGas,240));
        addDistillationDistillingRecipe(Oil, 100, 20, 96,
                new FluidProduct(SulfuricHeavyFuel,15),
                new FluidProduct(SulfuricLightFuel,50),
                new FluidProduct(SulfuricNaphtha,20),
                new FluidProduct(SulfuricGas,60));
        addDistillationDistillingRecipe(OilHeavy, 100, 20, 96,
                new FluidProduct(SulfuricHeavyFuel,250),
                new FluidProduct(SulfuricLightFuel,45),
                new FluidProduct(SulfuricNaphtha,15),
                new FluidProduct(SulfuricGas,60));
        addDistillationRecipe(SteamCrackedLightFuel, 1000, 120, 120, DUST_SMALL.get(Carbon, 1),
                new FluidProduct(LightFuel,100), new FluidProduct(Naphtha,100),
                new FluidProduct(Toluene,100),new FluidProduct(Benzene,200),
                new FluidProduct(Butadiene,200),new FluidProduct(Propene,300),
                new FluidProduct(Ethylene,300), new FluidProduct(Methane,300));
        addDistillationRecipe(SteamCrackedHeavyFuel, 1000, 120, 120, DUST_SMALL.get(Carbon, 1),
                new FluidProduct(HeavyFuel,100), new FluidProduct(Naphtha,100),
                new FluidProduct(Toluene,150),new FluidProduct(Benzene,300),
                new FluidProduct(Butadiene,300),new FluidProduct(Propene,450),
                new FluidProduct(Ethylene,450), new FluidProduct(Methane,450));
        addDistillationRecipe(HydroCrackedLightFuel, 1000, 120, 120,
                new FluidProduct(Naphtha,800), new FluidProduct(Butane,400),
                new FluidProduct(Propane,400),new FluidProduct(Ethane,200),
                new FluidProduct(Methane, 200));
        addDistillationRecipe(HydroCrackedHeavyFuel, 1000, 120, 120,
                new FluidProduct(LightFuel,800), new FluidProduct(Naphtha,400),
                new FluidProduct(Butane,100),new FluidProduct(Propane,100),
                new FluidProduct(Ethane,75),new FluidProduct(Methane,75));
    }

    private static void initSimple(){
        addDistillationDistillingRecipe(WoodVinegar, 1000, 40, 256,
                new FluidProduct(AceticAcid, 150),
                new FluidProduct(Water, 500),
                new FluidProduct(Ethanol, 20),
                new FluidProduct(Methanol, 300));
        addDistillationDistillingRecipe(WoodTar, 1000, 40, 256,
                new FluidProduct(Creosote, 500),
                new FluidProduct(Benzene, 425),
                new FluidProduct(Toluene, 75));
    }
    private static void initComplicated(){
        addDistillationDistillingRecipe(DilutedHydrochloricAcid, 2000, 300, 64,
                new FluidProduct(HydrochloricAcid, 1000),
                new FluidProduct(Water, 1000));
        addDistillationDistillingRecipe(WoodVinegar, 1000, 40, 256,
                new FluidProduct(AceticAcid, 100),
                new FluidProduct(Water, 500),
                new FluidProduct(Ethanol, 10),
                new FluidProduct(Methanol, 300),
                new FluidProduct(Acetone, 50),
                new FluidProduct(MethylAcetate, 10));
        addDistillationDistillingRecipe(WoodTar, 1000, 40, 256,
                new FluidProduct(Creosote, 500),
                new FluidProduct(Phenol, 75),
                new FluidProduct(Benzene, 350),
                new FluidProduct(Toluene, 75));
        addDistillationDistillingRecipe(Acetone, 1000, 50, 640,
                new FluidProduct(Ethenone, 500),
                new FluidProduct(Methane, 500));
        addDistillationDistillingRecipe(CalciumAcetateSolution, 4500, 100, 240, 60, 20, DUST_SMALL.get(Quicklime, 1),
                new FluidProduct(Acetone, 2500),
                new FluidProduct(Water, 750),
                new FluidProduct(CarbonDioxide, 750));
    }

    private static void addDistillationDistillingRecipe(Material input, int amount, int ticks, int euPerTick, int distilleryPerTick, int distilleryTicks, ItemStack itemStack, FluidProduct... outputs){
        addDistillationRecipe(input, amount, ticks, euPerTick, itemStack, outputs);
        for (int i = 0; i < outputs.length; i++){
            RecipeBuilder b = DISTILLING.RB()
                    .ii(INT_CIRCUITS.get(i + 1).setNoConsume())
                    .fi(input.has(AntimatterMaterialTypes.LIQUID) ? input.getLiquid(amount) : input.getGas(amount))
                    .fo(outputs[i].convert());
            if (!itemStack.isEmpty()) b.io(itemStack);
            b.add(input.getId() + "_" + outputs[i].mat().getId(), distilleryTicks, distilleryPerTick);
        }
    }

    private static void addDistillationDistillingRecipe(Material input, int amount, int ticks, int euPerTick, FluidProduct... outputs){
        addDistillationDistillingRecipe(input, amount, ticks, euPerTick, euPerTick / 4, ticks, ItemStack.EMPTY, outputs);
    }

    private static void addDistillingRecipe(Material input, int amount, int ticks, int euPerTick, ItemStack itemStack, FluidProduct... outputs){
        for (int i = 0; i < outputs.length; i++){
            RecipeBuilder b = DISTILLING.RB()
                    .ii(INT_CIRCUITS.get(i + 1).setNoConsume())
                    .fi(input.has(AntimatterMaterialTypes.LIQUID) ? input.getLiquid(amount) : input.getGas(amount))
                    .fo(outputs[i].convert());
            if (!itemStack.isEmpty()) b.io(itemStack);
            b.add(input.getId() + "_" + outputs[i].mat().getId(), ticks, euPerTick);
        }
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

        if (!itemOutput.isEmpty()) builder.io(itemOutput);
        builder.add(input.getId(), ticks, euPerTick);
    }

    private static void addDistillationRecipe(Material input, int amount, int ticks, int euPerTick, FluidProduct... outputs){
        addDistillationRecipe(input, amount, ticks, euPerTick, ItemStack.EMPTY, outputs);
    }
}
