package muramasa.gregtech.loader.machines;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import io.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Wood;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.GregTechMaterialTags.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.CENTRIFUGING;
import static net.minecraft.world.item.Items.*;
import static net.minecraft.world.item.Items.GOLD_NUGGET;

public class CentrifugingLoader {
    public static void init() {
        DUST_IMPURE.all().forEach(dust -> {
            if (dust.hasByProducts()) CENTRIFUGING.RB().ii(of(DUST_IMPURE.get(dust),1)).io(new ItemStack(DUST.get(dust), 1), DUST_TINY.get(dust.getByProducts().get(0), 1)).add("dust_impure_" + dust.getId(), 400, 2);
            else CENTRIFUGING.RB().ii(of(DUST_IMPURE.get(dust),1)).io(new ItemStack(DUST.get(dust), 1)).chances(1.0, 0.1).add("dust_impure_" + dust.getId(),dust.getMass(), 2);
        });
        DUST_PURE.all().forEach(dust -> {
            if (dust.hasByProducts())  {
                int index = dust.getByProducts().size() > 1 ? 1 : 0;
                CENTRIFUGING.RB().ii(of(DUST_PURE.get(dust),1)).io(new ItemStack(DUST.get(dust), 1), DUST_TINY.get(dust.getByProducts().get(index), 1)).add("dust_pure_" + dust.getId(),dust.getMass(), 2);
            }
            else CENTRIFUGING.RB().ii(of(DUST_IMPURE.get(dust),1)).io(new ItemStack(DUST.get(dust), 1)).chances(1.0, 0.1).add("dust_pure_" + dust.getId(),dust.getMass(), 2);
        });
        CENT.all().forEach(t -> {
            if (!t.has(DUST) && !t.has(LIQUID) && !t.has(GAS)) return;
            FluidHolder[] fluids = t.getProcessInto().stream().filter(mat -> ((mat.m.has(GAS) || mat.m.has(LIQUID)) && !mat.m.has(DUST))).map(mat -> mat.m.has(GAS) ? mat.m.getGas(mat.s*1000) : mat.m.getLiquid(mat.s*1000)).toArray(FluidHolder[]::new);
            if (fluids.length > 6) return;
            for (FluidHolder fluid : fluids) {
                if (fluid.isEmpty())
                    return;
            }
            ItemStack[] items = t.getProcessInto().stream().filter(mat -> mat.m.has(DUST)).map(mat -> DUST.get(mat.m, mat.s)).toArray(ItemStack[]::new);
            int inputAmount = MaterialTags.PROCESS_INTO.get(t).getRight() > 0 ? MaterialTags.PROCESS_INTO.get(t).getRight() : t.getProcessInto().stream().mapToInt(mat -> mat.s).sum();
            RecipeBuilder b = CENTRIFUGING.RB();
            if (t.has(DUST)){
                b.ii(DUST.getMaterialIngredient(t, inputAmount));
            } else {
                b.fi(t.has(LIQUID) ? t.getLiquid(inputAmount * 1000) : t.getGas(inputAmount * 1000));
            }
            int euPerTick = t.has(CENT5) ? 5 : t.has(CENT10) ? 10 : t.has(CENT15) ? 15 : t.has(CENT20) ? 20 : 16;
            b.io(items).fo(fluids).add("dust_" + t.getId(),t.getMass()*4, euPerTick);
        });
        //some stone dust recipe from gtnh without metal mixture
        //CENTRIFUGING.RB().ii(of(DUST.get(Stone, 32))).io(DUST.get(Quartz, 9), DUST.get(PotassiumFeldspar, 9), DUST.get(Marble, 8), DUST.get(Biotite, 4),
        //        DUST.get(Sodalite, 4)).add("stone_dust",7680, 30);

        CENTRIFUGING.RB().ii(of(MAGMA_CREAM, 1)).io(BLAZE_POWDER, SLIME_BALL).add("magma_cream", 500, 5);
        CENTRIFUGING.RB().fi(Propane.getGas(320)).fo(Lubricant.getLiquid(290)).add("propane", 20, 5);
        CENTRIFUGING.RB().fi(Butane.getGas(320)).fo(Lubricant.getLiquid(370)).add("butane", 20, 5);
        CENTRIFUGING.RB().fi(RefineryGas.getGas(800)).fo(LPG.getGas(400)).add("refinery_gas", 20, 5);
        //Cake Centrifuging
        CENTRIFUGING.RB().ii(of(DUST.get(ThoriumCake, 5))).io(DUST.get(ThoriumDioxide, 1), DUST.get(TrithoriumOctoxide, 4)).add("thorium_cake_centrifuging",400, 500);
        CENTRIFUGING.RB().ii(of(DUST.get(UraniumCake, 5))).io(DUST.get(UraniumDioxide, 1), DUST.get(TriuraniumOctoxide, 4)).add("uranium_cake_centrifuging",400, 500);
        CENTRIFUGING.RB().ii(of(GTRubberData.RUBBER_LOGS)).io(new ItemStack(GTRubberData.StickyResin), new ItemStack(GregTechData.PlantBall), DUST.get(Carbon, 1), DUST.get(Wood, 1)).fo(Methane.getGas(60)).chances(0.5, 0.375, 0.25, 0.25).add("rubber_logs", 200, 20);

        //Methane
        addMethaneRecipe(MELON, 9, 72);
        addMethaneRecipe(BREAD, 9, 72);
        addMethaneRecipe(COOKIE, 9, 72);
        addMethaneRecipe(BROWN_MUSHROOM, 18, 144);
        addMethaneRecipe(RED_MUSHROOM, 18, 144);
        addMethaneRecipe(APPLE, 18, 144);
        addMethaneRecipe(NETHER_WART, 18, 144);
        addMethaneRecipe(SPIDER_EYE, 18, 144);
        addMethaneRecipe(BAKED_POTATO, 24, 192);
        addMethaneRecipe(PUMPKIN, 36, 288);
        addMethaneRecipe(COOKED_BEEF, 36, 288);
        addMethaneRecipe(POTATO, 36, 288);
        addMethaneRecipe(COOKED_PORKCHOP, 36, 288);
        addMethaneRecipe(ROTTEN_FLESH, 36, 288);
        addMethaneRecipe(CARROT, 36, 288);
        addMethaneRecipe(BEETROOT, 36, 288);
        addMethaneRecipe(COOKED_SALMON, 36, 288);
        addMethaneRecipe(COOKED_COD, 36, 288);
        addMethaneRecipe(COOKED_CHICKEN, 36, 288);
        addMethaneRecipe(COOKED_MUTTON, 36, 288);
        addMethaneRecipe(COOKED_RABBIT, 36, 288);
        addMethaneRecipe(MUSHROOM_STEW, 36, 288, new ItemStack(BOWL));
        addMethaneRecipe(BEEF, 48, 384);
        addMethaneRecipe(CHICKEN, 48, 384);
        addMethaneRecipe(PORKCHOP, 48, 384);
        addMethaneRecipe(MUTTON, 48, 384);
        addMethaneRecipe(RABBIT, 48, 384);
        addMethaneRecipe(COD, 48, 384);
        addMethaneRecipe(SALMON, 48, 384);
        addMethaneRecipe(PUFFERFISH, 48, 384);
        addMethaneRecipe(TROPICAL_FISH, 48, 384);
        addMethaneRecipe(RED_MUSHROOM_BLOCK, 48, 384);
        addMethaneRecipe(BROWN_MUSHROOM_BLOCK, 48, 384);
        addMethaneRecipe(POISONOUS_POTATO, 48, 384);
        addMethaneRecipe(CAKE, 72, 576);
        //addMethaneRecipe(TerraWart, 36, 288);
        addMethaneRecipe(GOLDEN_APPLE, 576, 9216, new ItemStack(GOLD_INGOT, 7));
        addMethaneRecipe(ENCHANTED_GOLDEN_APPLE, 4608, 9216, new ItemStack(GOLD_INGOT, 64));
        addMethaneRecipe(GOLDEN_CARROT, 576, 9216, new ItemStack(GOLD_NUGGET, 6));
        addMethaneRecipe(GLISTERING_MELON_SLICE, 576, 9216, new ItemStack(GOLD_NUGGET, 6));
    }

    private static void addMethaneRecipe(Item input, int methane, int ticks){
        CENTRIFUGING.RB().ii(of(input, 1)).fo(Methane.getGas(methane)).add(AntimatterPlatformUtils.getIdFromItem(input).getPath() + "_into_methane", ticks, 5);
    }

    private static void addMethaneRecipe(Item input, int methane, int ticks, ItemStack extra){
        CENTRIFUGING.RB().ii(of(input, 1)).io(extra).fo(Methane.getGas(methane)).add(AntimatterPlatformUtils.getIdFromItem(input).getPath() + "_into_methane", ticks, 5);
    }
}

