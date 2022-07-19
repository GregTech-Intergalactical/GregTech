package muramasa.gregtech.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.item.ItemBattery;
import muramasa.antimatter.item.ItemFluidCell;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;

import static muramasa.gregtech.data.Materials.Mercury;
import static muramasa.gregtech.data.RecipeMaps.FLUID_CANNING;

public class FluidCanningLoader {
    public static void init() {
        FLUID_CANNING.RB().ii(RecipeIngredient.of(GregTechData.BatteryHullSmall, 1)).fi(Mercury.getLiquid(500)).io(ItemBattery.getFilledBattery(GregTechData.BatterySmallMercury)).add(20, 2);
        FLUID_CANNING.RB().ii(RecipeIngredient.of(GregTechData.BatteryHullMedium, 1)).fi(Mercury.getLiquid(2000)).io(ItemBattery.getFilledBattery(GregTechData.BatteryMediumMercury)).add(20, 2);
        FLUID_CANNING.RB().ii(RecipeIngredient.of(GregTechData.BatteryHullLarge, 1)).fi(Mercury.getLiquid(8000)).io(ItemBattery.getFilledBattery(GregTechData.BatteryLargeMercury)).add(20, 2);
        AntimatterPlatformUtils.getAllFluids().forEach(fluid -> {
            Item bucket = fluid.getBucket();
            if (bucket == Items.AIR) return;
            //Only the source, so we don't get duplicates.
            if (!fluid.isSource(fluid.defaultFluidState())) return;
            FLUID_CANNING.RB().ii(RecipeIngredient.of(bucket, 1)).fo(new FluidStack(fluid, 1000)).io(Items.BUCKET.getDefaultInstance()).add(20, 8);
            FLUID_CANNING.RB().ii(RecipeIngredient.of(Items.BUCKET, 1)).fi(new FluidStack(fluid, 1000)).io(new ItemStack(bucket, 1)).add(20, 8);

            AntimatterAPI.all(ItemFluidCell.class, emptyCell -> {
                int size = emptyCell.getCapacity();
                ItemStack filled = emptyCell.fill(fluid, size);
                FLUID_CANNING.RB().ii(RecipeIngredient.of(filled)).fo(new FluidStack(fluid, size)).io(emptyCell.getDefaultInstance()).add(20, 8);
                FLUID_CANNING.RB().ii(RecipeIngredient.of(emptyCell, 1)).fi(new FluidStack(fluid, size)).io(filled).add(20, 8);
            });
        });
    }
}
