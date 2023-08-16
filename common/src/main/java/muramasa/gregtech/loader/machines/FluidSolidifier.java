package muramasa.gregtech.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.world.item.Items;

import static muramasa.antimatter.Ref.L;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.FLUID_SOLIDIFYING;

public class FluidSolidifier {
    public static void init() {
        AntimatterAPI.all(Material.class, mat -> {
            if (!mat.has(AntimatterMaterialTypes.LIQUID) || mat == Glass) return;
            if (mat.has(PLATE)) {
                FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldPlate, 1).setNoConsume())
                .fi(mat.getFluidTag(L)).io(PLATE.get(mat,1)).add(mat.getId() + "_plate",32, 8);
            }
            if (mat.has(ITEM_CASING)) {
                FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldCasing, 1).setNoConsume())
                        .fi(mat.getFluidTag(L / 2)).io(ITEM_CASING.get(mat,1)).add(mat.getId() + "_casing",16, 8);
            }
            if (mat.has(INGOT)) {
                FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldIngot, 1).setNoConsume())
                        .fi(mat.getFluidTag(L)).io(INGOT.get(mat,1)).add(mat.getId() + "_ingot",32, 8);
            }
            if (mat.has(GEAR)) {
                FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldGear, 1).setNoConsume())
                .fi(mat.getFluidTag(L * 4)).io(GEAR.get(mat,1)).add(mat.getId() + "_gear",128, 8);
            }
            if (mat.has(GEAR_SMALL)) {
                FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldGearSmall, 1).setNoConsume())
                .fi(mat.getFluidTag(L)).io(GEAR_SMALL.get(mat,1)).add(mat.getId() + "_gear_small",16, 8);
            }
            if (mat.has(NUGGET)) {
                FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldNugget, 1).setNoConsume())
                .fi(mat.getFluidTag(L / 9)).io(NUGGET.get(mat,1)).add(mat.getId() + "_nugget",16, 4);
            }
            if (mat.has(BLOCK)) {
                FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldBlock, 1).setNoConsume())
                .fi(mat.getFluidTag(L * 9)).io(BLOCK.get().get(mat).asStack(1)).add(mat.getId() + "_block",288, 8);
            }
        });
        FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldAnvil, 1).setNoConsume()).fi(Iron.getFluidTag(L * 31)).io(Items.ANVIL).add("anvil", 128, 16);
        FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldPlate, 1).setNoConsume()).fi(Glass.getFluidTag(L)).io(PLATE.get(Glass)).add("glass_plate",12, 4);
        FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldBlock, 1).setNoConsume()).fi(Glass.getFluidTag(L)).io(Items.GLASS).add("glass_block",12, 4);
        FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldBottle, 1).setNoConsume()).fi(Glass.getFluidTag(L)).io(Items.GLASS_BOTTLE).add("glass_bottle",12, 4);
        FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldBlock, 1).setNoConsume()).fi(Glowstone.getFluidTag(L * 4)).io(Items.GLOWSTONE).add("glowstone_block", 12, 4);
        FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldBlock, 1).setNoConsume()).fi(Water.getLiquid(1000)).io(Items.SNOW_BLOCK).add("snow_block", 512, 4);
        FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldBlock, 1).setNoConsume()).fi(DistilledWater.getFluidTag(1000)).io(Items.SNOW_BLOCK).add("snow_block_2", 512, 4);
        FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldBall, 1).setNoConsume()).fi(Water.getLiquid(250)).io(Items.SNOWBALL).add("snow_ball", 128, 4);
        FLUID_SOLIDIFYING.RB().ii(RecipeIngredient.of(GregTechData.MoldBall, 1).setNoConsume()).fi(DistilledWater.getFluidTag(250)).io(Items.SNOWBALL).add("snow_ball_2", 128, 4);
    }
}
