package muramasa.gregtech.loader.machines;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.*;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.antimatter.pipe.types.ItemPipe;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.block.BlockCasing;
import muramasa.gregtech.data.GregTechBlocks;
import muramasa.gregtech.data.GregTechCovers;
import muramasa.gregtech.data.GregTechItems;
import muramasa.gregtech.data.RecipeMaps;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import tesseract.TesseractGraphWrappers;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Ref.L;
import static muramasa.antimatter.Ref.U;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.gregtech.data.Machines.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.Materials.Rubber;
import static muramasa.gregtech.data.RecipeMaps.ASSEMBLER;
import static muramasa.gregtech.data.TierMaps.*;

public class ArcFurnace {
    public static void init() {
        DUST.all().forEach(m -> {
            if (!m.has(INGOT) || m.has(MaterialTags.HAS_CUSTOM_SMELTING)) return;
            Material output = m == Iron ? WroughtIron : m == Copper ? AnnealedCopper : m;
            RecipeMaps.ARC_FURNACE.RB().ii(DUST.getMaterialIngredient(m, 1)).fi(Oxygen.getGas((int)m.getMass())).io(INGOT.get(output)).add(m.getId() + "_dust_to_" + output.getId() + "_ingot", m.getMass(), 30, 0, 3);
            if (m == Iron || m == Copper){
                RecipeMaps.ARC_FURNACE.RB().ii(INGOT.getMaterialIngredient(m, 1)).fi(Oxygen.getGas((int)m.getMass())).io(INGOT.get(output)).add(m.getId() + "_ingot_to_" + output.getId() + "_ingot", m.getMass(), 30, 0, 3);
            }
        });
        for (MaterialType<?> t : AntimatterAPI.all(MaterialType.class)) {
            if (t.getUnitValue() <= 0 || t == DUST || t == DUST_TINY || t == DUST_SMALL || t == INGOT || t == NUGGET ||
                    t == INGOT_HOT || t == GEM || t == GEM_CHIPPED || t == GEM_FLAWED || t == GEM_FLAWLESS || t == GEM_EXQUISITE) continue;
            double amount = (double) t.getUnitValue() / U;
            t.all().forEach(m -> {
                if (m.has(MaterialTags.RUBBERTOOLS) || m == Bone || m == Carbon || m == Blaze) return;
                addRecyclingRecipe(t.getMaterialIngredient(m, 1), of(m, (float) amount), m.getId() + "_" + t.getId() + "_recycling");
            });
        }
        AntimatterAPI.all(FluidPipe.class).stream().filter(t -> t.getMaterial() != AntimatterMaterials.Wood).forEach(t -> {
            if (t.getSizes().contains(PipeSize.TINY)){
                addRecyclingRecipe(t.getBlockItem(PipeSize.TINY), of(t.getMaterial(), 0.5f));
            }
            if (t.getSizes().contains(PipeSize.SMALL)){
                addRecyclingRecipe(t.getBlockItem(PipeSize.SMALL), of(t.getMaterial(), 1f));
            }
            if (t.getSizes().contains(PipeSize.NORMAL)){
                addRecyclingRecipe(t.getBlockItem(PipeSize.NORMAL), of(t.getMaterial(), 3f));
            }
            if (t.getSizes().contains(PipeSize.LARGE)){
                addRecyclingRecipe(t.getBlockItem(PipeSize.LARGE), of(t.getMaterial(), 6f));
            }
            if (t.getSizes().contains(PipeSize.HUGE)){
                addRecyclingRecipe(t.getBlockItem(PipeSize.HUGE), of(t.getMaterial(), 12f));
            }
        });
        AntimatterAPI.all(ItemPipe.class).stream().filter(t -> t.getMaterial() != AntimatterMaterials.Wood).forEach(t -> {
            if (t.getSizes().contains(PipeSize.TINY)){
                addRecyclingRecipe(t.getBlockItem(PipeSize.TINY), of(t.getMaterial(), 0.5f));
            }
            if (t.getSizes().contains(PipeSize.SMALL)){
                addRecyclingRecipe(t.getBlockItem(PipeSize.SMALL), of(t.getMaterial(), 1f));
            }
            if (t.getSizes().contains(PipeSize.NORMAL)){
                addRecyclingRecipe(t.getBlockItem(PipeSize.NORMAL), of(t.getMaterial(), 3f));
            }
            if (t.getSizes().contains(PipeSize.LARGE)){
                addRecyclingRecipe(t.getBlockItem(PipeSize.LARGE), of(t.getMaterial(), 6f));
            }
            if (t.getSizes().contains(PipeSize.HUGE)){
                addRecyclingRecipe(t.getBlockItem(PipeSize.HUGE), of(t.getMaterial(), 12f));
            }
        });
        AntimatterAPI.all(Wire.class, w -> {
            ImmutableSet<PipeSize> sizes = w.getSizes();
            sizes.forEach(size -> {
                Item cableItem = w.getBlockItem(size);
                int ct = size.getCableThickness();
                float amount = ct == 1 ? 0.5f : ct == 2 ? 1 : ct == 4 ? 2 : ct == 8 ? 4 : ct == 12 ? 8 : 16;
                addRecyclingRecipe(cableItem, of(w.getMaterial(), amount));
            });
        });
        AntimatterAPI.all(Cable.class, c -> {
            ImmutableSet<PipeSize> sizes = c.getSizes();
            sizes.forEach(size -> {
                Item cableItem = c.getBlockItem(size);
                int ct = size.getCableThickness();
                float multiplier = ct == 16 ?  5 : ct == 12 ? 4 : ct == 8 ? 3 : ct == 4 ? 2 : 1;
                float amount = ct == 1 ? 0.5f : ct == 2 ? 1 : ct == 4 ? 2 : ct == 8 ? 4 : ct == 12 ? 8 : 16;
                addRecyclingRecipe(cableItem, of(c.getMaterial(), amount, Rubber, multiplier));
            });
        });
        addRecyclingRecipe(GTCoreItems.MotorLV, of(Copper, 2f, Tin, 1f, Steel, 1f, Iron, 0.5f));
        addRecyclingRecipe(GTCoreItems.MotorMV, of(Copper, 5f, Aluminium, 1f, Steel, 0.5f));
        addRecyclingRecipe(GTCoreItems.MotorHV, of(Copper, 8f, Gold, 1f, StainlessSteel, 1f, Steel, 0.5f));
        addRecyclingRecipe(GTCoreItems.MotorEV, of(Copper, 16f, Aluminium,1f, Titanium, 1f, Neodymium, 0.5f));
        addRecyclingRecipe(GTCoreItems.MotorIV, of(Copper, 32f, Tungsten,1f, TungstenSteel, 1f, Neodymium, 0.5f));
        addRecyclingRecipe(GregTechCovers.COVER_PUMP.getItem(LV).getItem(), of(Tin, 5.78f, Bronze, 3f, Copper, 2f, Iron, 1.5f));
        addRecyclingRecipe(GregTechCovers.COVER_PUMP.getItem(MV).getItem(), of(Copper, 5.5f, Bronze, 4.34f, Steel, 3.5f, Aluminium, 1f));
        addRecyclingRecipe(GregTechCovers.COVER_PUMP.getItem(HV).getItem(), of(Copper, 8f, Steel, 4.78f, StainlessSteel, 4f, Gold, 1.5f));
        addRecyclingRecipe(GregTechCovers.COVER_PUMP.getItem(EV).getItem(), of(Copper, 16f, StainlessSteel, 4.78f, Titanium, 4f, Aluminium, 1.5f));
        addRecyclingRecipe(GregTechCovers.COVER_PUMP.getItem(IV).getItem(), of(Copper, 32f, TungstenSteel, 3.67f, Tungsten, 1.5f, Neodymium, 0.5f));
        addRecyclingRecipe(GregTechCovers.COVER_CONVEYOR.getItem(LV).getItem(), of(Rubber, 6f, Copper, 4f, Iron, 3f, Tin, 2.5f));
        addRecyclingRecipe(GregTechCovers.COVER_CONVEYOR.getItem(MV).getItem(), of(Rubber, 6f, Copper, 10.5f, Aluminium, 2f, Steel, 1f));
        addRecyclingRecipe(GregTechCovers.COVER_CONVEYOR.getItem(HV).getItem(), of(Copper, 16f, Gold, 2.5f, StainlessSteel, 2f, Steel, 1f));
        addRecyclingRecipe(GregTechCovers.COVER_CONVEYOR.getItem(EV).getItem(), of(Copper, 32f, Aluminium, 2.5f, Titanium, 2f, Neodymium, 1f));
        addRecyclingRecipe(GregTechCovers.COVER_CONVEYOR.getItem(IV).getItem(), of(Copper, 64f, Tungsten, 2.5f, TungstenSteel, 2f, Neodymium, 1f));
        addRecyclingRecipe(GregTechItems.PistonLV, of(Steel, 5f, Tin, 2f, Copper, 2f, Iron, 1.5f));
        addRecyclingRecipe(GregTechItems.PistonMV, of(Aluminium, 6f, Copper, 6f, Steel, 0.5f));
        addRecyclingRecipe(GregTechItems.PistonHV, of(Copper, 8f, StainlessSteel, 6f, Gold, 2f, Steel, 0.5f));
        addRecyclingRecipe(GregTechItems.PistonEV, of(Copper, 16f, Titanium, 6f, Aluminium, 2f, Neodymium, 0.5f));
        addRecyclingRecipe(GregTechItems.PistonIV, of(Copper, 32f, TungstenSteel, 6f, Tungsten, 2f, Neodymium, 0.5f));
        addRecyclingRecipe(COAL_BOILER.getItem(BRONZE), of(Stone, 8f, Bronze, 5f, Brick, 2f));
        addRecyclingRecipe(COAL_BOILER.getItem(STEEL), of(Stone, 8f, Steel, 5f, Brick, 2f));
        addRecyclingRecipe(LAVA_BOILER.getItem(STEEL), of(Steel, 10f, Glass, 3f, Brick, 3f));
        addRecyclingRecipe(SOLAR_BOILER.getItem(BRONZE), of(Bronze, 7f, Glass, 3f, Silver, 3f, Brick, 3f));
        addRecyclingRecipe(STEAM_FURNACE.getItem(BRONZE), of(Bronze, 12f, Stone, 8f, Brick, 3f));
        addRecyclingRecipe(STEAM_FURNACE.getItem(STEEL), of(Steel, 12f, Stone, 8f, Brick, 3f));
        addRecyclingRecipe(STEAM_COMPRESSOR.getItem(BRONZE), of(Bronze, 14f, Stone, 8f, Wood, 6f));
        addRecyclingRecipe(STEAM_COMPRESSOR.getItem(STEEL), of(Steel, 14f, Stone, 8f, Wood, 6f));
        addRecyclingRecipe(STEAM_EXTRACTOR.getItem(BRONZE), of(Bronze, 14f, Stone, 4f, Wood, 3f));
        addRecyclingRecipe(STEAM_EXTRACTOR.getItem(STEEL), of(Steel, 14f, Stone, 4f, Wood, 3f));
        addRecyclingRecipe(STEAM_FORGE_HAMMER.getItem(BRONZE), of(Bronze, 14f, Iron, 10f, Stone, 4f, Wood, 3f));
        addRecyclingRecipe(STEAM_FORGE_HAMMER.getItem(STEEL), of(Steel, 14f, Iron, 10f, Stone, 4f, Wood, 3f));
        addRecyclingRecipe(STEAM_ALLOY_SMELTER.getItem(BRONZE), of(Bronze, 11f, Stone, 16f, Wood, 3f));
        addRecyclingRecipe(STEAM_ALLOY_SMELTER.getItem(STEEL), of(Steel, 11f, Stone, 16f, Wood, 3f));
        addRecyclingRecipe(STEAM_SIFTER.getItem(BRONZE), of(Bronze, 11f, Steel, 3f, Stone, 8f, Wood, 6f));
        addRecyclingRecipe(STEAM_SIFTER.getItem(STEEL), of(Steel, 14f, Stone, 8f, Wood, 6f));
        addRecyclingRecipe(STEAM_MACERATOR.getItem(BRONZE), of(Bronze, 12f, Stone, 8f, Wood, 6f, Diamond, 2f));
        addRecyclingRecipe(STEAM_MACERATOR.getItem(STEEL), of(Steel, 12f, Stone, 8f, Wood, 6f, Diamond, 2f));
        for (Tier tier : getAllElectric()) {
            addRecyclingRecipe(GregTech.get(BlockCasing.class,"casing_" + tier.getId()), of(TIER_MATERIALS.get(tier), 8f));
        }
        addRecyclingRecipe(GregTechBlocks.CASING_BRONZE, of(Bronze, 8f));
        addRecyclingRecipe(GregTechBlocks.CASING_BRICKED_BRONZE, of(Bronze, 5f, Brick, 3f));
        addRecyclingRecipe(GregTechBlocks.CASING_STEEL, of(Steel, 8f));
        addRecyclingRecipe(GregTechBlocks.CASING_BRICKED_STEEL, of(Steel, 5f, Brick, 3f));
        addRecyclingRecipe(GregTechBlocks.CASING_SOLID_STEEL, of(Steel, 8f));
        addRecyclingRecipe(GregTechBlocks.CASING_STAINLESS_STEEL, of(StainlessSteel, 8f));
        addRecyclingRecipe(GregTechBlocks.CASING_TITANIUM, of(Titanium, 8f));
        addRecyclingRecipe(GregTechBlocks.CASING_TUNGSTENSTEEL, of(TungstenSteel, 8f));
        addRecyclingRecipe(GregTechBlocks.CASING_TUNGSTEN, of(Tungsten, 8f));
        addRecyclingRecipe(GregTechBlocks.CASING_PLATINUM, of(Platinum, 8f));
        addRecyclingRecipe(GregTechBlocks.CASING_BLACK_BRONZE, of(BlackBronze, 8f));
        addRecyclingRecipe(GregTechBlocks.CASING_HEAT_PROOF, of(Invar, 8f));
        addRecyclingRecipe(GregTechBlocks.CASING_FROST_PROOF, of(Aluminium, 8f));
        addRecyclingRecipe(GregTechBlocks.CASING_RADIATION_PROOF, of(Lead, 8f));
        addRecyclingRecipe(GregTechBlocks.CASING_DENSE_LEAD, of(Lead, 56f));
        addRecyclingRecipe(GregTechBlocks.CASING_FIREBOX_BRONZE, of(Bronze, 8f));
        addRecyclingRecipe(GregTechBlocks.CASING_FIREBOX_STEEL, of(Steel, 8f));
        addRecyclingRecipe(GregTechBlocks.CASING_FIREBOX_TITANIUM, of(Titanium, 8f));
        addRecyclingRecipe(GregTechBlocks.CASING_FIREBOX_TUNGSTENSTEEL, of(TungstenSteel, 8f));
        addRecyclingRecipe(GregTechBlocks.CASING_GEARBOX_BRONZE, of(Bronze, 14f));
        addRecyclingRecipe(GregTechBlocks.CASING_GEARBOX_STEEL, of(Steel, 14f));
        addRecyclingRecipe(GregTechBlocks.CASING_GEARBOX_TITANIUM, of(Titanium, 14f));
        addRecyclingRecipe(GregTechBlocks.CASING_GEARBOX_TUNGSTENSTEEL, of(TungstenSteel, 14f));
        addRecyclingRecipe(GregTechBlocks.CASING_PIPE_BRONZE, of(Bronze, 18f));
        addRecyclingRecipe(GregTechBlocks.CASING_PIPE_STEEL, of(Steel, 18f));
        addRecyclingRecipe(GregTechBlocks.CASING_PIPE_TITANIUM, of(Titanium, 18f));
        addRecyclingRecipe(GregTechBlocks.CASING_PIPE_TUNGSTENSTEEL, of(TungstenSteel, 18f));
        addRecyclingRecipe(GregTechBlocks.CASING_TURBINE_STEEL, of(Steel, 9f));
        addRecyclingRecipe(GregTechBlocks.CASING_TURBINE_STAINLESS, of(StainlessSteel, 9f));
        addRecyclingRecipe(GregTechBlocks.CASING_TURBINE_TITANIUM, of(Titanium, 9f));
        addRecyclingRecipe(GregTechBlocks.CASING_TURBINE_TUNGSTENSTEEL, of(TungstenSteel, 9f));;
        addRecyclingRecipe(GregTechBlocks.COIL_CUPRONICKEL, of(Cupronickel, 8f));
        addRecyclingRecipe(GregTechBlocks.COIL_KANTHAL, of(Kanthal, 8f));
        addRecyclingRecipe(GregTechBlocks.COIL_NICHROME, of(Nichrome, 8f));
        addRecyclingRecipe(GregTechBlocks.COIL_TUNGSTENSTEEL, of(TungstenSteel, 8f));
        addRecyclingRecipe(GregTechBlocks.COIL_HSSG, of(HSSG, 8f));
        addRecyclingRecipe(GregTechBlocks.COIL_NAQUADAH, of(Naquadah, 8f));
        addRecyclingRecipe(GregTechBlocks.COIL_NAQUADAH_ALLOY, of(NaquadahAlloy, 8f));
    }

    private static void addRecyclingRecipe(ItemLike input, ImmutableMap<Material, Float> outputs){
        addRecyclingRecipe(RecipeIngredient.of(input), outputs, AntimatterPlatformUtils.getIdFromItem(input.asItem()).getPath());
    }

    private static void addRecyclingRecipe(Ingredient input, ImmutableMap<Material, Float> outputs, String id){
        RecipeBuilder arc = RecipeMaps.ARC_FURNACE.RB();
        RecipeBuilder mac = RecipeMaps.MACERATOR.RB();
        arc.ii(input);
        mac.ii(input);
        long[] totalMassArc = new long[]{0};
        long[] totalMassMac = new long[]{0};
        outputs.forEach((material, aFloat) -> {
            Material arcOutput = material == Iron ? WroughtIron : material == Copper ? AnnealedCopper : material.has(MaterialTags.RUBBERTOOLS) || material == Wood ? Ash : material;
            int i = aFloat.intValue();
            float arcFloat = aFloat;
            if (material.has(MaterialTags.RUBBERTOOLS) || material == Wood) arcFloat = aFloat / 9;
            int j = (int)arcFloat;
            float leftover = aFloat - i;
            float arcLeftover = arcFloat - j;
            totalMassMac[0] += (long) (material.getMass() * aFloat);
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
                totalMassArc[0] += (long) (arcOutput.getMass() * arcFloat);
                if (arcLeftover > 0){
                    int aExtra = (int) (arcLeftover * 9);

                    MaterialTypeItem<?> arcType = arcOutput == Ash ? DUST_TINY : NUGGET;
                    arc.io(arcType.get(arcOutput, (j * 9) + aExtra));
                } else {
                    MaterialTypeItem<?> arcType = arcOutput == Ash ? DUST : INGOT;
                    arc.io(arcType.get(arcOutput, j));
                }
            }

        });
        if (totalMassArc[0] > 0){
            arc.fi(Oxygen.getGas(totalMassArc[0] * TesseractGraphWrappers.dropletMultiplier)).add(id, totalMassArc[0], 32);
        }
        if (totalMassMac[0] > 0) {
            mac.add(id, totalMassMac[0] * 2, 4);
        }

    }

    static int fromTier(Tier tier){
        if (tier == LV) return 1;
        if (tier == MV) return 2;
        if (tier == HV) return 4;
        if (tier == IV) return 16;
        return 8;
    }
}
