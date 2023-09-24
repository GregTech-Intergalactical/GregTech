package muramasa.gregtech.loader.machines;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialItem;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.data.GregTechTags;
import muramasa.gregtech.data.Machines;
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
import static muramasa.gregtech.data.Machines.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.Materials.Rubber;
import static muramasa.gregtech.data.RecipeMaps.*;
import static muramasa.gregtech.data.TierMaps.*;

public class ArcFurnace {
    public static void init() {
        ARC_SMELTING.RB().ii(INGOT.getMaterialIngredient(AntimatterMaterials.Iron, 1)).fi(Materials.Oxygen.getGas(56)).io(INGOT.get(Materials.WroughtIron, 1)).add("wrought_iron_ingot",56, 30, 0, 3);
        ARC_SMELTING.RB().ii(DUST.getMaterialIngredient(AntimatterMaterials.Iron, 1)).fi(Materials.Oxygen.getGas(56)).io(INGOT.get(Materials.WroughtIron, 1)).add("wrought_iron_ingot_2",56, 30, 0, 3);
        addRecyclingRecipe(MotorLV, of(Copper, 2f, Tin, 1f, Steel, 1f, Iron, 0.5f), 328, 377, 1, 20);
        addRecyclingRecipe(MotorMV, of(Copper, 5f, Aluminium, 1f, Steel, 0.5f), 369, 418, 1, 23);
        addRecyclingRecipe(MotorHV, of(Copper, 8f, Gold, 1f, StainlessSteel, 1f, Steel, 0.5f), 783, 832, 1, 48);
        addRecyclingRecipe(MotorEV, of(Copper, 16f, Aluminium,1f, Titanium, 1f, Neodymium, 0.5f), 1154, 1203, 1, 72);
        addRecyclingRecipe(MotorIV, of(Copper, 32f, Tungsten,1f, TungstenSteel, 1f, Neodymium, 0.5f), 2390, 2439, 3, 149);
        addRecyclingRecipe(COVER_PUMP.getItem(LV).getItem(), of(Tin, 5.78f, Bronze, 3f, Copper, 2f, Iron, 1.5f), 1129, 1252, 1, 70);
        addRecyclingRecipe(COVER_PUMP.getItem(MV).getItem(), of(Copper, 5.5f, Bronze, 4.34f, Steel, 3.5f, Aluminium, 1f), 899, 975, 1, 56);
        addRecyclingRecipe(COVER_PUMP.getItem(HV).getItem(), of(Copper, 8f, Steel, 4.78f, StainlessSteel, 4f, Gold, 1.5f), 1290, 1366, 2, 80);
        addRecyclingRecipe(COVER_PUMP.getItem(EV).getItem(), of(Copper, 16f, StainlessSteel, 4.78f, Titanium, 4f, Aluminium, 1.5f), 1550, 1626, 2, 96);
        addRecyclingRecipe(COVER_PUMP.getItem(IV).getItem(), of(Copper, 32f, TungstenSteel, 3.67f, Tungsten, 1.5f, Neodymium, 0.5f), 3357, 3433, 5, 209);
        addRecyclingRecipe(COVER_CONVEYOR.getItem(LV).getItem(), of(Rubber, 6f, Copper, 4f, Iron, 3f, Tin, 2.5f), 788, 867, 1, 49);
        addRecyclingRecipe(COVER_CONVEYOR.getItem(MV).getItem(), of(Rubber, 6f, Copper, 10.5f, Aluminium, 2f, Steel, 1f), 843, 920, 1, 52);
        addRecyclingRecipe(COVER_CONVEYOR.getItem(HV).getItem(), of(Copper, 16f, Gold, 2.5f, StainlessSteel, 2f, Steel, 1f), 1664, 1816, 2, 104);
        addRecyclingRecipe(COVER_CONVEYOR.getItem(EV).getItem(), of(Copper, 32f, Aluminium, 2.5f, Titanium, 2f, Neodymium, 1f), 2321, 2473, 3, 145);
        addRecyclingRecipe(COVER_CONVEYOR.getItem(IV).getItem(), of(Copper, 64f, Tungsten, 2.5f, TungstenSteel, 2f, Neodymium, 1f), 4871, 5024, 7, 304);
        addRecyclingRecipe(PistonLV, of(Steel, 5f, Tin, 2f, Copper, 2f, Iron, 1.5f), 726, 824, 1, 45);
        addRecyclingRecipe(PistonMV, of(Aluminium, 6f, Copper, 6f, Steel, 0.5f), 562, 660, 1, 35);
        addRecyclingRecipe(PistonHV, of(Copper, 8f, StainlessSteel, 6f, Gold, 2f, Steel, 0.5f), 1254, 1352, 1, 78);
        addRecyclingRecipe(PistonEV, of(Copper, 16f, Titanium, 6f, Aluminium, 2f, Neodymium, 0.5f), 1420, 1518, 2, 88);
        addRecyclingRecipe(PistonIV, of(Copper, 32f, TungstenSteel, 6f, Tungsten, 2f, Neodymium, 0.5f), 3168, 3266, 4, 198);
        addRecyclingRecipe(COAL_BOILER.getItem(BRONZE), of(Stone, 8f, Bronze, 5f, Brick, 2f), 380, 1024, 1, 23);
        addRecyclingRecipe(COAL_BOILER.getItem(STEEL), of(Stone, 8f, Steel, 5f, Brick, 2f), 280, 1104, 1, 17);
        addRecyclingRecipe(LAVA_BOILER.getItem(STEEL), of(Steel, 10f, Glass, 3f, Brick, 3f), 560, 680, 1, 35);
        addRecyclingRecipe(STEAM_FURNACE.getItem(BRONZE), of(Bronze, 12f, Stone, 8f, Brick, 3f), 912, 1756, 1, 57);
        addRecyclingRecipe(STEAM_FURNACE.getItem(STEEL), of(Steel, 12f, Stone, 8f, Brick, 3f), 672, 1516, 1, 42);
        addRecyclingRecipe(STEAM_MACERATOR.getItem(BRONZE), of(Bronze, 12f, Stone, 8f, Wood, 6f, Diamond, 2f), 1010, 3286, 1, 63);
        addRecyclingRecipe(STEAM_MACERATOR.getItem(STEEL), of(Steel, 12f, Stone, 8f, Wood, 6f, Diamond, 2f), 770, 3046, 1, 48);
    }

    private static void addRecyclingRecipe(ItemLike input, ImmutableMap<Material, Float> outputs, int arcDuration, int macerateDuration, int plasma, int plasmaDuration){
        RecipeBuilder arc = ARC_SMELTING.RB();
        RecipeBuilder mac = MACERATING.RB();
        RecipeBuilder plas = PLASMA_ARC_SMELTING.RB();
        arc.ii(input);
        mac.ii(input);
        plas.ii(input);
        outputs.forEach((material, aFloat) -> {
            Material arcOutput = material == Iron ? WroughtIron : material == Copper ? AnnealedCopper : material.has(MaterialTags.RUBBERTOOLS) || material == Wood ? Ash : material;
            int i = aFloat.intValue();
            float arcFloat = aFloat;
            if (material.has(MaterialTags.RUBBERTOOLS) || material == Wood) arcFloat = aFloat / 9;
            int j = (int)arcFloat;
            float leftover = aFloat - i;
            float arcLeftover = arcFloat - j;
            if (leftover > 0){
                float mExtraF = leftover * 4;
                int mExtra = (int) (mExtraF);
                float mLeftover = mExtraF - mExtra;
                int aExtra = (int) (leftover * 9);
                if (mLeftover > 0){
                    mac.io(DUST_TINY.get(material, (i * 9) + aExtra));
                } else {
                    mac.io(DUST_SMALL.get(material, (i * 4) + mExtra));
                }
            } else {
                mac.io(DUST.get(material, i));
            }
            if (arcOutput == Ash || arcOutput.has(INGOT)){
                if (arcLeftover > 0){
                    int aExtra = (int) (arcLeftover * 9);

                    MaterialTypeItem<?> arcType = arcOutput == Ash ? DUST_TINY : NUGGET;
                    arc.io(arcType.get(arcOutput, (j * 9) + aExtra));
                    plas.io(arcType.get(arcOutput, (j * 9) + aExtra));
                } else {
                    MaterialTypeItem<?> arcType = arcOutput == Ash ? DUST : INGOT;
                    arc.io(arcType.get(arcOutput, j));
                    plas.io(arcType.get(arcOutput, j));
                }
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
