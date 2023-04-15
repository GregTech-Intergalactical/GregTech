package muramasa.gregtech.loader.multi;

import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.GTIRef;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.data.AntimatterStoneTypes.STONE;
import static muramasa.antimatter.material.MaterialTags.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.BASIC_BLASTING;
import static muramasa.gregtech.data.RecipeMaps.BLASTING;

public class Blasting {
    public static int mixedOreYield = GTIRef.mixedOreYieldsTwoThirdsPureOre ? 2 : 3;

    public static void init() {
        final int multiplier = 1;
        CRUSHED.all().forEach(m -> {
            boolean needsBF = m.has(NEEDS_BLAST_FURNACE) || DIRECT_SMELT_INTO.getMapping(m).has(NEEDS_BLAST_FURNACE);
            if (!m.has(ORE) || !m.has(INGOT)) return;
            Item crushed = CRUSHED.get(m);
            Item dust = DUST.get(m);
            RecipeIngredient ore = ORE.get().get(m, STONE).asIngredient();
            ItemStack ingot = m != DIRECT_SMELT_INTO.getMapping(m) ? INGOT.get(DIRECT_SMELT_INTO.getMapping(m), 1) : INGOT.get(m, 1);
            ItemStack aIngotSmeltInto = m == SMELT_INTO.getMapping(m) ? ingot : INGOT.get(SMELT_INTO.getMapping(m), 1);
            if (needsBF) {
                long aBlastDuration = Math.max(m.getMass() / 6, 1) * BLAST_FURNACE_TEMP.getInt(m);

                ItemStack blastOut = BLAST_FURNACE_TEMP.getInt(m) > 1750 && SMELT_INTO.getMapping(m).has(INGOT_HOT) ? INGOT_HOT.get(MaterialTags.SMELT_INTO.getMapping(m), 1) : aIngotSmeltInto;

                BLASTING.RB().ii(of(crushed,1), INT_CIRCUITS.get(1)).io(blastOut).add("crushed_" + m.getId(), aBlastDuration, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
                BLASTING.RB().ii(of(dust,1), INT_CIRCUITS.get(1)).io(blastOut).add("dust_" + m.getId(),aBlastDuration/4, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
                BLASTING.RB().ii(CRUSHED_PURIFIED.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add("purified_" + m.getId(),aBlastDuration, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
                BLASTING.RB().ii(CRUSHED_REFINED.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add("refined_" + m.getId(),aBlastDuration, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
                BLASTING.RB().ii(DUST_PURE.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add("dust_pure_" + m.getId(),aBlastDuration, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
                BLASTING.RB().ii(DUST_IMPURE.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add("dust_impure_" + m.getId(),aBlastDuration, 120, MaterialTags.BLAST_FURNACE_TEMP.getInt(m));
            }

            if (m.has(CALCITE3X)) {
                ItemStack ingotMulti = Utils.mul(multiplier * 3 * MaterialTags.SMELTING_MULTI.getInt(m), ingot);
                ItemStack darkAsh = DUST_SMALL.get(DarkAsh, 1);
                BLASTING.RB().ii(ore, DUST.getIngredient(Calcite, multiplier)).io(ingotMulti, darkAsh).add("ore_" + m.getId(),ingot.getCount() * 500L, 120, 1500);
                BLASTING.RB().ii(ore, DUST.getIngredient(Quicklime, multiplier)).io(ingotMulti, darkAsh).add("ore_" + m.getId() +"_2",ingot.getCount() * 500L, 120, 1500);
            } else if (m.has(CALCITE2X)) {
                ItemStack darkAsh = DUST_SMALL.get(DarkAsh, 1);
                BLASTING.RB().ii(ore, DUST.getIngredient(Calcite, multiplier)).io(Utils.mul(multiplier * mixedOreYield * MaterialTags.SMELTING_MULTI.getInt(m), ingot), darkAsh).add("ore_" + m.getId(),ingot.getCount() * 500L, 120, 1500);
                BLASTING.RB().ii(ore, DUST_TINY.getIngredient(Quicklime, multiplier * 3)).io(Utils.mul(multiplier * 3 * MaterialTags.SMELTING_MULTI.getInt(m), ingot), darkAsh).add("ore_" + m.getId() + "_2",ingot.getCount() * 500L, 120, 1500);
            }
        });

        /* PRIMITIVE */
        BASIC_BLASTING.RB().ii(INGOT.getMaterialIngredient(AntimatterMaterials.Iron,1)).io(INGOT.get(Steel, 1), DUST_SMALL.get(DarkAsh,8)).chances(1.0, 0.5).add("steel_ingot",1200, 0);
        INGOT.all().forEach(m -> {
            if (m.has(NEEDS_BLAST_FURNACE) && m.has(BLAST_FURNACE_TEMP)){
                ItemStack ingot = m.has(INGOT_HOT) ? INGOT_HOT.get(m, 1) : INGOT.get(m, 1);
                int heat = BLAST_FURNACE_TEMP.getInt(m);
                BLASTING.RB().ii(DUST.getMaterialIngredient(m, 1)).io(ingot).add(m.getId() + "ingot_from_dust", Math.max(m.getMass() / 40L, 1L) * heat, 120, heat);
            }
        });

        BLASTING.RB().ii(INGOT.getMaterialIngredient(AntimatterMaterials.Iron, 1)).fi(Oxygen.getGas(1000)).io(INGOT.get(Steel), DUST_SMALL.get(DarkAsh)).add("steel_ingot", 500, 120, 1000);
        /* TITANIUM */
        BLASTING.RB().temperature(1700).ii(RecipeIngredient.of(DUST.get(Magnesium,2)), INT_CIRCUITS.get(1).setNoConsume())
                .fi(Titaniumtetrachloride.getLiquid(1000))
                .io(INGOT_HOT.get(Titanium,1), DUST.get(MagnesiumChloride,2))
                .add("titanium_ingot",40*20, 480);

        //Nuclear Processing
        BLASTING.RB().temperature(3000).ii(of(DUST.get(Thorium227Dioxide,1)), of(DUST.get(Carbon,1))).io(INGOT_HOT.get(Thorium227,1)).fo(CarbonDioxide.getGas(1000)).add("hot_thorium227_ingot",40*20, 480);
        BLASTING.RB().temperature(3000).ii(of(DUST.get(Thorium228Dioxide,1)), of(DUST.get(Carbon,1))).io(INGOT_HOT.get(Thorium228,1)).fo(CarbonDioxide.getGas(1000)).add("hot_thorium228_ingot",40*20, 480);
        BLASTING.RB().temperature(3000).ii(of(DUST.get(Thorium229Dioxide,1)), of(DUST.get(Carbon,1))).io(INGOT_HOT.get(Thorium229,1)).fo(CarbonDioxide.getGas(1000)).add("hot_thorium229_ingot",40*20, 480);
        BLASTING.RB().temperature(3000).ii(of(DUST.get(Thorium230Dioxide,1)), of(DUST.get(Carbon,1))).io(INGOT_HOT.get(Thorium230,1)).fo(CarbonDioxide.getGas(1000)).add("hot_thorium230_ingot",40*20, 480);
        BLASTING.RB().temperature(3000).ii(of(DUST.get(Thorium231Dioxide,1)), of(DUST.get(Carbon,1))).io(INGOT_HOT.get(Thorium231,1)).fo(CarbonDioxide.getGas(1000)).add("hot_thorium231_ingot",40*20, 480);
        BLASTING.RB().temperature(3000).ii(of(DUST.get(Thorium233Dioxide,1)), of(DUST.get(Carbon,1))).io(INGOT_HOT.get(Thorium233,1)).fo(CarbonDioxide.getGas(1000)).add("hot_thorium233_ingot",40*20, 480);
        BLASTING.RB().temperature(3000).ii(of(DUST.get(Thorium234Dioxide,1)), of(DUST.get(Carbon,1))).io(INGOT_HOT.get(Thorium234,1)).fo(CarbonDioxide.getGas(1000)).add("hot_thorium234_ingot",40*20, 480);

        BLASTING.RB().temperature(3000).ii(of(DUST.get(Uranium232Dioxide,1)), of(DUST.get(Carbon,1))).io(INGOT_HOT.get(Uranium232,1)).fo(CarbonDioxide.getGas(1000)).add("hot_uranium232_ingot",40*20, 480);
        BLASTING.RB().temperature(3000).ii(of(DUST.get(Uranium233Dioxide,1)), of(DUST.get(Carbon,1))).io(INGOT_HOT.get(Uranium233,1)).fo(CarbonDioxide.getGas(1000)).add("hot_uranium233_ingot",40*20, 480);
        BLASTING.RB().temperature(3000).ii(of(DUST.get(Uranium234Dioxide,1)), of(DUST.get(Carbon,1))).io(INGOT_HOT.get(Uranium234,1)).fo(CarbonDioxide.getGas(1000)).add("hot_uranium234_ingot",40*20, 480);
        BLASTING.RB().temperature(3000).ii(of(DUST.get(Uranium235Dioxide,1)), of(DUST.get(Carbon,1))).io(INGOT_HOT.get(Uranium235,1)).fo(CarbonDioxide.getGas(1000)).add("hot_uranium235_ingot",40*20, 480);
        BLASTING.RB().temperature(3000).ii(of(DUST.get(Uranium236Dioxide,1)), of(DUST.get(Carbon,1))).io(INGOT_HOT.get(Uranium236,1)).fo(CarbonDioxide.getGas(1000)).add("hot_uranium236_ingot",40*20, 480);
        BLASTING.RB().temperature(3000).ii(of(DUST.get(Uranium237Dioxide,1)), of(DUST.get(Carbon,1))).io(INGOT_HOT.get(Uranium237,1)).fo(CarbonDioxide.getGas(1000)).add("hot_uranium237_ingot",40*20, 480);
        BLASTING.RB().temperature(3000).ii(of(DUST.get(Uranium238Dioxide,1)), of(DUST.get(Carbon,1))).io(INGOT_HOT.get(Uranium238,1)).fo(CarbonDioxide.getGas(1000)).add("hot_uranium238_ingot",40*20, 480);
        BLASTING.RB().temperature(3000).ii(of(DUST.get(Uranium239Dioxide,1)), of(DUST.get(Carbon,1))).io(INGOT_HOT.get(Uranium239,1)).fo(CarbonDioxide.getGas(1000)).add("hot_uranium239_ingot",40*20, 480);
        BLASTING.RB().temperature(3000).ii(of(DUST.get(Uranium240Dioxide,1)), of(DUST.get(Carbon,1))).io(INGOT_HOT.get(Uranium240,1)).fo(CarbonDioxide.getGas(1000)).add("hot_uranium240_ingot",40*20, 480);


    }
}
