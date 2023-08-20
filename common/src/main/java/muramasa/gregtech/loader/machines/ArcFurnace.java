package muramasa.gregtech.loader.machines;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialItem;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.data.GregTechTags;
import muramasa.gregtech.data.Materials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.Arrays;
import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.data.AntimatterDefaultTools.SCREWDRIVER;
import static muramasa.antimatter.data.AntimatterDefaultTools.WRENCH;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.GregTechData.WIRE_OSMIUM;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.Materials.Rubber;
import static muramasa.gregtech.data.RecipeMaps.*;
import static muramasa.gregtech.data.TierMaps.*;

public class ArcFurnace {
    public static void init() {
        ARC_SMELTING.RB().ii(INGOT.getMaterialIngredient(AntimatterMaterials.Iron, 1)).fi(Materials.Oxygen.getGas(56)).io(INGOT.get(Materials.WroughtIron, 1)).add("wrought_iron_ingot",56, 30, 0, 3);
        addRecyclingRecipe(MotorLV, of(Copper, 2f, Tin, 1f, Iron, 0.5f, Steel, 1f), 328, 377, 1, 20);
        addRecyclingRecipe(MotorMV, of(Copper, 5f, Aluminium, 1f, Steel, 0.5f), 369, 418, 1, 23);
        addRecyclingRecipe(MotorHV, of(Copper, 8f, Gold, 1f, StainlessSteel, 1f, Steel, 0.5f), 783, 832, 1, 48);
        addRecyclingRecipe(MotorEV, of(Copper, 16f, Aluminium,1f, Titanium, 1f, Neodymium, 0.5f), 1154, 1203, 1, 72);
        addRecyclingRecipe(MotorIV, of(Copper, 32f, Tungsten,1f, TungstenSteel, 1f, Neodymium, 0.5f), 2390, 2439, 3, 149);

    }

    private static void addRecyclingRecipe(ItemLike input, ImmutableMap<Material, Float> outputs, int arcDuration, int macerateDuration, int plasma, int plasmaDuration){
        RecipeBuilder arc = ARC_SMELTING.RB();
        RecipeBuilder mac = MACERATING.RB();
        RecipeBuilder plas = PLASMA_ARC_SMELTING.RB();
        arc.ii(input);
        mac.ii(input);
        plas.ii(input);
        outputs.forEach((material, aFloat) -> {
            Material arcOutput = material == Iron ? WroughtIron : material == Copper ? AnnealedCopper : material;
            int i = aFloat.intValue();
            float leftover = aFloat - i;
            if (leftover > 0){
                int aExtra = (int) (leftover * 9);
                int mExtra = (int) (leftover * 4);
                arc.io(NUGGET.get(arcOutput, (i * 9) + aExtra));
                plas.io(NUGGET.get(arcOutput, (i * 9) + aExtra));
                mac.io(DUST_SMALL.get(material, (i * 4) + mExtra));
            } else {
                arc.io(INGOT.get(arcOutput, i));
                plas.io(INGOT.get(arcOutput, i));
                mac.io(DUST.get(material, i));
            }
        });
        arc.fi(Oxygen.getGas(arcDuration)).add(AntimatterPlatformUtils.getIdFromItem(input.asItem()).getPath(), arcDuration, 32);
        mac.add(AntimatterPlatformUtils.getIdFromItem(input.asItem()).getPath(), macerateDuration, 4);
        plas.fi(Argon.getPlasma(plasma)).fo(Argon.getGas(plasma)).add(AntimatterPlatformUtils.getIdFromItem(input.asItem()).getPath() + "_argon", plasmaDuration, 32);
        plas.clearFluidInputs().clearFluidOutputs().fi(Nitrogen.getPlasma(plasma)).fo(Nitrogen.getGas(plasma)).add(AntimatterPlatformUtils.getIdFromItem(input.asItem()).getPath() + "_nitrogen", plasmaDuration, 32);
    }

    static int fromTier(Tier tier){
        if (tier == LV) return 1;
        if (tier == MV) return 2;
        if (tier == HV) return 4;
        if (tier == IV) return 16;
        return 8;
    }
}
