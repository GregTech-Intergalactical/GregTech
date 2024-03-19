package muramasa.gregtech.loader.machines;

import io.github.gregtechintergalactical.gtcore.data.GTCoreFluids;
import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.item.ItemBattery;
import muramasa.antimatter.item.ItemFluidCell;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.data.GregTechItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractGraphWrappers;

import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.FLUID_CANNER;

public class FluidCannerLoader {
    public static void init() {
        FLUID_CANNER.RB().ii(RecipeIngredient.of(GTCoreItems.BatteryHullSmall, 1)).fi(Mercury.getLiquid(1000)).io(ItemBattery.getFilledBattery(GTCoreItems.BatterySmallMercury)).add("battery_small_mercury",16, 1);
        FLUID_CANNER.RB().ii(RecipeIngredient.of(GTCoreItems.BatteryHullMedium, 1)).fi(Mercury.getLiquid(4000)).io(ItemBattery.getFilledBattery(GTCoreItems.BatteryMediumMercury)).add("battery_medium_mercury",64, 1);
        FLUID_CANNER.RB().ii(RecipeIngredient.of(GTCoreItems.BatteryHullLarge, 1)).fi(Mercury.getLiquid(16000)).io(ItemBattery.getFilledBattery(GTCoreItems.BatteryLargeMercury)).add("battery_large_mercury",258, 1);
        FLUID_CANNER.RB().ii(RecipeIngredient.of(GTCoreItems.BatteryHullSmall, 1)).fi(SulfuricAcid.getLiquid(1000)).io(ItemBattery.getFilledBattery(GTCoreItems.BatterySmallAcid)).add("battery_small_acid",16, 1);
        FLUID_CANNER.RB().ii(RecipeIngredient.of(GTCoreItems.BatteryHullMedium, 1)).fi(SulfuricAcid.getLiquid(4000)).io(ItemBattery.getFilledBattery(GTCoreItems.BatteryMediumAcid)).add("battery_medium_acid",64, 1);
        FLUID_CANNER.RB().ii(RecipeIngredient.of(GTCoreItems.BatteryHullLarge, 1)).fi(SulfuricAcid.getLiquid(16000)).io(ItemBattery.getFilledBattery(GTCoreItems.BatteryLargeAcid)).add("battery_large_acid",258, 1);
        FLUID_CANNER.RB().ii(RecipeIngredient.of(GTCoreItems.LighterEmpty)).fi(Butane.getGas(100)).io(GTCoreItems.Lighter).add("lighter", 1, 1);
        FLUID_CANNER.RB().ii(RecipeIngredient.of(GregTechItems.TritiumEnrichedRod)).io(GregTechItems.EmptyNuclearFuelRod).fo(Tritium.getGas(500)).add("tritium_enriched_rod", 16, 16);
        FLUID_CANNER.RB().ii(GregTechItems.EmptyGeigerCounter).fi(Argon.getGas(1000)).io(GregTechItems.GeigerCounter).add("geiger_counter_argon", 64, 16);
        FLUID_CANNER.RB().ii(GregTechItems.EmptyGeigerCounter).fi(Helium.getGas(1000)).io(GregTechItems.GeigerCounter).add("geiger_counter_helium", 64, 16);
        FLUID_CANNER.RB().ii(GregTechItems.EmptyGeigerCounter).fi(Neon.getGas(1000)).io(GregTechItems.GeigerCounter).add("geiger_counter_neon", 64, 16);
        AntimatterPlatformUtils.getAllFluids().forEach(fluid -> {
            Item bucket = fluid.getBucket();
            if (bucket == Items.AIR) return;
            //Only the source, so we don't get duplicates.
            if (!fluid.isSource(fluid.defaultFluidState())) return;
            ResourceLocation fluidId = AntimatterPlatformUtils.getIdFromFluid(fluid);
            FLUID_CANNER.RB().ii(RecipeIngredient.of(bucket, 1)).fo(FluidPlatformUtils.createFluidStack(fluid, 1000 * TesseractGraphWrappers.dropletMultiplier)).io(Items.BUCKET.getDefaultInstance()).add(fluidId.getNamespace() + "_" + fluidId.getPath() + "_bucket",20, 8);
            FLUID_CANNER.RB().ii(RecipeIngredient.of(Items.BUCKET, 1)).fi(FluidPlatformUtils.createFluidStack(fluid, 1000 * TesseractGraphWrappers.dropletMultiplier)).io(new ItemStack(bucket, 1)).add("bucket_from_" + fluidId.getNamespace() + "_" + fluidId.getPath(),20, 8);

            AntimatterAPI.all(ItemFluidCell.class, emptyCell -> {
                if (!emptyCell.getFilter().test(0, FluidPlatformUtils.createFluidStack(fluid, 1))) return;
                int size = emptyCell.getCapacity();
                ItemStack filled = emptyCell.fill(fluid, size);
                FLUID_CANNER.RB().ii(RecipeIngredient.of(filled)).fo(FluidPlatformUtils.createFluidStack(fluid, size * TesseractGraphWrappers.dropletMultiplier)).io(emptyCell.getDefaultInstance()).add(emptyCell.getId() + "_from_" + AntimatterPlatformUtils.getIdFromFluid(fluid).getPath(),20, 8);
                FLUID_CANNER.RB().ii(RecipeIngredient.of(emptyCell, 1)).fi(FluidPlatformUtils.createFluidStack(fluid, size * TesseractGraphWrappers.dropletMultiplier)).io(filled).add(AntimatterPlatformUtils.getIdFromFluid(fluid).getPath() + "_" + emptyCell.getId(),20, 8);
            });
        });
        if (AntimatterAPI.isModLoaded(Ref.MOD_TWILIGHT)){
            FLUID_CANNER.RB().ii(RecipeIngredient.of(AntimatterPlatformUtils.getItemFromID(Ref.MOD_TWILIGHT, "fiery_blood"))).io(Items.GLASS_BOTTLE).fo(FluidPlatformUtils.createFluidStack(GTCoreFluids.FIERY_BLOOD.getFluid(), 250 * TesseractGraphWrappers.dropletMultiplier)).add("fiery_blood_from_fiery_blood_bottle", 20, 8);
            FLUID_CANNER.RB().ii(RecipeIngredient.of(AntimatterPlatformUtils.getItemFromID(Ref.MOD_TWILIGHT, "fiery_tears"))).io(Items.GLASS_BOTTLE).fo(FluidPlatformUtils.createFluidStack(GTCoreFluids.FIERY_TEARS.getFluid(), 250 * TesseractGraphWrappers.dropletMultiplier)).add("fiery_tears_from_fiery_tears_bottle", 20, 8);
        }
    }
}
