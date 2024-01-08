package muramasa.gregtech.loader.machines;

import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.world.item.Items;

import static muramasa.antimatter.Ref.L;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.FLUID_SOLIDIFYER;

public class FluidSolidifier {
    public static void init() {
        AntimatterAPI.all(Material.class, mat -> {
            if (!mat.has(AntimatterMaterialTypes.LIQUID) || mat == Glass) return;
            if (mat.has(PLATE)) {
                FLUID_SOLIDIFYER.RB().ii(RecipeIngredient.of(GTCoreItems.MoldPlate, 1).setNoConsume())
                .fi(mat.getFluidIngredient(L)).io(PLATE.get(mat,1)).add(mat.getId() + "_plate",32, 8);
            }
            if (mat.has(ITEM_CASING)) {
                FLUID_SOLIDIFYER.RB().ii(RecipeIngredient.of(GTCoreItems.MoldCasing, 1).setNoConsume())
                        .fi(mat.getFluidIngredient(L / 2)).io(ITEM_CASING.get(mat,1)).add(mat.getId() + "_casing",16, 8);
            }
            if (mat.has(INGOT)) {
                FLUID_SOLIDIFYER.RB().ii(RecipeIngredient.of(GTCoreItems.MoldIngot, 1).setNoConsume())
                        .fi(mat.getFluidIngredient(L)).io(INGOT.get(mat,1)).add(mat.getId() + "_ingot",32, 8);
            }
            if (mat.has(GEAR)) {
                FLUID_SOLIDIFYER.RB().ii(RecipeIngredient.of(GTCoreItems.MoldGear, 1).setNoConsume())
                .fi(mat.getFluidIngredient(L * 4)).io(GEAR.get(mat,1)).add(mat.getId() + "_gear",128, 8);
            }
            if (mat.has(GEAR_SMALL)) {
                FLUID_SOLIDIFYER.RB().ii(RecipeIngredient.of(GTCoreItems.MoldGearSmall, 1).setNoConsume())
                .fi(mat.getFluidIngredient(L)).io(GEAR_SMALL.get(mat,1)).add(mat.getId() + "_gear_small",16, 8);
            }
            if (mat.has(NUGGET)) {
                FLUID_SOLIDIFYER.RB().ii(RecipeIngredient.of(GTCoreItems.MoldNugget, 1).setNoConsume())
                .fi(mat.getFluidIngredient(L / 9)).io(NUGGET.get(mat,1)).add(mat.getId() + "_nugget",16, 4);
            }
            if (mat.has(ROD_LONG)) {
                FLUID_SOLIDIFYER.RB().ii(RecipeIngredient.of(GTCoreItems.MoldLongRod, 1).setNoConsume())
                        .fi(mat.getFluidIngredient(L)).io(ROD_LONG.get(mat,1)).add(mat.getId() + "_long_rod",16, 8);
            }
            if (mat.has(BLOCK)) {
                FLUID_SOLIDIFYER.RB().ii(RecipeIngredient.of(GTCoreItems.MoldBlock, 1).setNoConsume())
                .fi(mat.getFluidIngredient(L * 9)).io(BLOCK.get().get(mat).asStack(1)).add(mat.getId() + "_block",288, 8);
            }
        });
        FLUID_SOLIDIFYER.RB().ii(RecipeIngredient.of(GTCoreItems.MoldLongRod, 1).setNoConsume()).fi(Lava.getLiquid(AntimatterPlatformUtils.isFabric() ? L : 111)).io(ROD_LONG.get(Obsidian)).add("long_obsidian_rod", 16, 8);
        FLUID_SOLIDIFYER.RB().ii(RecipeIngredient.of(GTCoreItems.MoldPlate, 1).setNoConsume()).fi(Lava.getLiquid(AntimatterPlatformUtils.isFabric() ? L : 111)).io(PLATE.get(Obsidian)).add("obsidian_plate", 16, 8);
        FLUID_SOLIDIFYER.RB().ii(RecipeIngredient.of(GTCoreItems.MoldAnvil, 1).setNoConsume()).fi(Iron.getFluidIngredient(L * 31)).io(Items.ANVIL).add("anvil", 128, 16);
        FLUID_SOLIDIFYER.RB().ii(RecipeIngredient.of(GTCoreItems.MoldPlate, 1).setNoConsume()).fi(Glass.getFluidIngredient(L)).io(PLATE.get(Glass)).add("glass_plate",12, 4);
        FLUID_SOLIDIFYER.RB().ii(RecipeIngredient.of(GTCoreItems.MoldBlock, 1).setNoConsume()).fi(Glass.getFluidIngredient(L)).io(Items.GLASS).add("glass_block",12, 4);
        FLUID_SOLIDIFYER.RB().ii(RecipeIngredient.of(GTCoreItems.MoldBottle, 1).setNoConsume()).fi(Glass.getFluidIngredient(L)).io(Items.GLASS_BOTTLE).add("glass_bottle",12, 4);
        FLUID_SOLIDIFYER.RB().ii(RecipeIngredient.of(GTCoreItems.MoldBlock, 1).setNoConsume()).fi(Glowstone.getFluidIngredient(L * 4)).io(Items.GLOWSTONE).add("glowstone_block", 12, 4);
        FLUID_SOLIDIFYER.RB().ii(RecipeIngredient.of(GTCoreItems.MoldBlock, 1).setNoConsume()).fi(Water.getLiquid(1000)).io(Items.SNOW_BLOCK).add("snow_block", 512, 4);
        FLUID_SOLIDIFYER.RB().ii(RecipeIngredient.of(GTCoreItems.MoldBlock, 1).setNoConsume()).fi(DistilledWater.getFluidIngredient(1000)).io(Items.SNOW_BLOCK).add("snow_block_2", 512, 4);
        FLUID_SOLIDIFYER.RB().ii(RecipeIngredient.of(GTCoreItems.MoldBall, 1).setNoConsume()).fi(Water.getLiquid(250)).io(Items.SNOWBALL).add("snow_ball", 128, 4);
        FLUID_SOLIDIFYER.RB().ii(RecipeIngredient.of(GTCoreItems.MoldBall, 1).setNoConsume()).fi(DistilledWater.getFluidIngredient(250)).io(Items.SNOWBALL).add("snow_ball_2", 128, 4);
    }
}
