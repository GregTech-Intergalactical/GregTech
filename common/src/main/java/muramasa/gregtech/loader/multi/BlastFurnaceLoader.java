package muramasa.gregtech.loader.multi;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.GregTechConfig;
import muramasa.gregtech.data.GregTechMaterialTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static muramasa.antimatter.data.AntimatterMaterials.Copper;
import static muramasa.antimatter.data.AntimatterMaterials.Iron;
import static muramasa.antimatter.material.MaterialTags.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.PRIMITIVE_BLAST_FURNACE;
import static muramasa.gregtech.data.RecipeMaps.E_BLAST_FURNACE;

public class BlastFurnaceLoader {
    public static int mixedOreYield = GTIRef.mixedOreYieldsTwoThirdsPureOre ? 2 : 3;

    public static void init() {
        final int multiplier = 1;
        /*CRUSHED.all().forEach(m -> {
            boolean needsBF = m.has(GregTechMaterialTags.NEEDS_BLAST_FURNACE) || DIRECT_SMELT_INTO.getMapping(m).has(GregTechMaterialTags.NEEDS_BLAST_FURNACE);
            if (!m.has(ORE) || !m.has(INGOT)) return;
            Item crushed = CRUSHED.get(m);
            Item dust = DUST.get(m);
            ItemStack ingot = m != DIRECT_SMELT_INTO.getMapping(m) ? INGOT.get(DIRECT_SMELT_INTO.getMapping(m), 1) : INGOT.get(m, 1);
            ItemStack aIngotSmeltInto = m == SMELT_INTO.getMapping(m) ? ingot : INGOT.get(SMELT_INTO.getMapping(m), 1);
            if (needsBF) {
                long aBlastDuration = Math.max(m.getMass() / 6, 1) * GregTechMaterialTags.BLAST_FURNACE_TEMP.getInt(m);

                ItemStack blastOut = GregTechMaterialTags.BLAST_FURNACE_TEMP.getInt(m) > 1750 && SMELT_INTO.getMapping(m).has(INGOT_HOT) ? INGOT_HOT.get(MaterialTags.SMELT_INTO.getMapping(m), 1) : aIngotSmeltInto;

                BLASTING.RB().ii(of(crushed,1), INT_CIRCUITS.get(1)).io(blastOut).add("crushed_" + m.getId(), aBlastDuration, 120, GregTechMaterialTags.BLAST_FURNACE_TEMP.getInt(m));
                BLASTING.RB().ii(of(dust,1), INT_CIRCUITS.get(1)).io(blastOut).add("dust_" + m.getId(),aBlastDuration/4, 120, GregTechMaterialTags.BLAST_FURNACE_TEMP.getInt(m));
                BLASTING.RB().ii(CRUSHED_PURIFIED.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add("purified_" + m.getId(),aBlastDuration, 120, GregTechMaterialTags.BLAST_FURNACE_TEMP.getInt(m));
                BLASTING.RB().ii(CRUSHED_REFINED.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add("refined_" + m.getId(),aBlastDuration, 120, GregTechMaterialTags.BLAST_FURNACE_TEMP.getInt(m));
                BLASTING.RB().ii(DUST_PURE.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add("dust_pure_" + m.getId(),aBlastDuration, 120, GregTechMaterialTags.BLAST_FURNACE_TEMP.getInt(m));
                BLASTING.RB().ii(DUST_IMPURE.getIngredient(m, 1),INT_CIRCUITS.get(1)).io(blastOut).add("dust_impure_" + m.getId(),aBlastDuration, 120, GregTechMaterialTags.BLAST_FURNACE_TEMP.getInt(m));
            }


        });*/

        /*CRUSHED.all().forEach(m -> {
            if (!m.has(ORE)) return;
            ItemStack ingot = new ItemStack(Items.IRON_INGOT);
            RecipeIngredient ore = ORE.getMaterialIngredient(m, 1);
            if (m.has(CALCITE3X)) {
                ItemStack ingotMulti = Utils.mul(multiplier * 3, ingot);
                ItemStack darkAsh = DUST_SMALL.get(DarkAsh, 1);
                BLASTING.RB().ii(ore, DUST.getIngredient(Calcite, multiplier)).io(ingotMulti, darkAsh).add("ore_" + m.getId(),ingot.getCount() * 500L, 120, 1500);
                BLASTING.RB().ii(ore, DUST.getIngredient(Quicklime, multiplier)).io(ingotMulti, darkAsh).add("ore_" + m.getId() +"_2",ingot.getCount() * 500L, 120, 1500);
            } else if (m.has(CALCITE2X)) {
                ItemStack darkAsh = DUST_SMALL.get(DarkAsh, 1);
                BLASTING.RB().ii(ore, DUST.getIngredient(Calcite, multiplier)).io(Utils.mul(multiplier * 2, ingot), darkAsh).add("ore_" + m.getId(),ingot.getCount() * 500L, 120, 1500);
                BLASTING.RB().ii(ore, DUST.getIngredient(Quicklime, multiplier)).io(Utils.mul(multiplier * 2, ingot), darkAsh).add("ore_" + m.getId() + "_2",ingot.getCount() * 500L, 120, 1500);
            }
        });*/

        /* PRIMITIVE */
        PRIMITIVE_BLAST_FURNACE.RB().ii(INGOT.getMaterialIngredient(AntimatterMaterials.Iron,1)).io(INGOT.get(Steel, 1), DUST_SMALL.get(DarkAsh,8)).chances(1.0, 0.5).add("steel_ingot",7200, 0);
        DUST.all().forEach(m -> {
            if (m.has(GregTechMaterialTags.NEEDS_BLAST_FURNACE) && m.has(GregTechMaterialTags.BLAST_FURNACE_TEMP)){
                ItemStack ingot = DIRECT_SMELT_INTO.getMapping(m).has(INGOT_HOT) ? INGOT_HOT.get(DIRECT_SMELT_INTO.getMapping(m), 1) : INGOT.get(DIRECT_SMELT_INTO.getMapping(m), 1);
                int heat = GregTechMaterialTags.BLAST_FURNACE_TEMP.getInt(m);
                E_BLAST_FURNACE.RB().temperature(heat).ii(DUST.getMaterialIngredient(m, 1), INT_CIRCUITS.get(1)).io(ingot).add(DIRECT_SMELT_INTO.getMapping(m).getId() + "_ingot_from_" + m.getId() + "_dust", Math.max(m.getMass() / 40L, 1L) * heat, 120);
            }
        });

        PRIMITIVE_BLAST_FURNACE.RB().ii(DUST.getMaterialIngredient(BlackSteel, 1)).io(INGOT.get(BlackSteel)).add("black_steel", 10800);
        PRIMITIVE_BLAST_FURNACE.RB().ii(DUST.getMaterialIngredient(BlueSteel, 1)).io(INGOT.get(BlueSteel)).add("blue_steel", 14400);
        PRIMITIVE_BLAST_FURNACE.RB().ii(DUST.getMaterialIngredient(RedSteel, 1)).io(INGOT.get(RedSteel)).add("red_steel", 14400);

        addCalciteRecipe(Hematite, 4, new ItemStack(Items.IRON_INGOT, 1));
        addCalciteRecipe(YellowLimonite, 6, new ItemStack(Items.IRON_INGOT, 1));
        addCalciteRecipe(BrownLimonite, 6, new ItemStack(Items.IRON_INGOT, 1));
        addCalciteRecipe(Magnetite, 11, new ItemStack(Items.IRON_INGOT, 3));
        addCalciteRecipe(Chalcopyrite, 6, new ItemStack(Items.COPPER_INGOT), new ItemStack(Items.IRON_INGOT));
        addCalciteRecipe(Galena, 4, INGOT.get(Lead, 1), INGOT.get(Silver, 1));
        addCalciteRecipe(Garnierite, 1, INGOT.get(Nickel, 1));
        addCalciteRecipe(Cassiterite, 1, INGOT.get(Tin, 1));
        addCalciteRecipe(Cobaltite, 5, INGOT.get(Cobalt, 1));
        addCalciteRecipe(Pyrite, 4, new ItemStack(Items.IRON_INGOT));
        addCalciteRecipe(Stibnite, 4, INGOT.get(Antimony, 1));
        addCalciteRecipe(Tetrahedrite, 12, INGOT.get(Copper, 3), INGOT.get(Antimony, 1), INGOT.get(Iron, 1));
        addCalciteRecipe(Malachite, 15, INGOT.get(Copper, 2));
        /* Annealed Copper*/
        E_BLAST_FURNACE.RB().temperature(1200).ii(DUST.getMaterialIngredient(Copper, 1))
                .fi(Oxygen.getGas(1000))
                .io(INGOT.get(AnnealedCopper))
                .add("annealed_copper_ingot", 25 * 20, 120);
        /* Steel */
        E_BLAST_FURNACE.RB().temperature(1000).ii(INGOT.getMaterialIngredient(AntimatterMaterials.Iron, 1))
                .fi(Oxygen.getGas(1000))
                .io(INGOT.get(Steel), DUST_SMALL.get(DarkAsh))
                .add("steel_ingot", 500, 120);
        E_BLAST_FURNACE.RB().temperature(1000).ii(INGOT.getMaterialIngredient(WroughtIron, 1))
                .fi(Oxygen.getGas(1000))
                .io(INGOT.get(Steel), DUST_SMALL.get(DarkAsh))
                .add("steel_ingot_2", 100, 120);
        if (!GregTechConfig.HARDER_ALUMINIUM_PROCESSING.get()){
            /* Aluminium*/
            E_BLAST_FURNACE.RB().temperature(1200).ii(DUST.getMaterialIngredient(Ruby, 1))
                    .io(NUGGET.get(Aluminium, 3), DUST_TINY.get(DarkAsh, 1))
                    .add("aluminium_ingot_from_ruby", 400, 100);
            E_BLAST_FURNACE.RB().temperature(1200).ii(DUST.getMaterialIngredient(Sapphire, 1))
                    .io(NUGGET.get(Aluminium, 3))
                    .add("aluminium_ingot_from_blue_sapphire", 400, 100);
            E_BLAST_FURNACE.RB().temperature(1200).ii(DUST.getMaterialIngredient(GreenSapphire, 1))
                    .io(NUGGET.get(Aluminium, 3), DUST_TINY.get(DarkAsh, 1))
                    .add("aluminium_ingot_from_green_sapphire", 400, 100);
            int heat = GregTechMaterialTags.BLAST_FURNACE_TEMP.getInt(Aluminium);
            E_BLAST_FURNACE.RB().temperature(1700).ii(DUST.getMaterialIngredient(Aluminium, 1), INT_CIRCUITS.get(1)).io(INGOT.get(Aluminium)).add( "aluminium_ingot_from_aluminium_dust", Math.max(Aluminium.getMass() / 40L, 1L) * heat, 120);
            E_BLAST_FURNACE.RB().ii(DUST.getMaterialIngredient(Alumina, 4), DUST.getMaterialIngredient(Calcite, 1)).io(INGOT.get(Aluminium)).add("alumina_calcite", 4 * 100, 120);
            E_BLAST_FURNACE.RB().ii(DUST.getMaterialIngredient(Alumina, 4), DUST.getMaterialIngredient(Limestone, 1)).io(INGOT.get(Aluminium)).add("alumina_limestone", 4 * 100, 120);
            E_BLAST_FURNACE.RB().ii(DUST.getMaterialIngredient(Alumina, 4), DUST.getMaterialIngredient(Marble, 1)).io(INGOT.get(Aluminium)).add("alumina_marble", 4 * 100, 120);
        }

        /* Stainless Steel*/
        addBlastAlloyRecipes(StainlessSteel, 9, 1000, 120, ImmutableMap.of(Iron, 6, Nickel, 1, Manganese, 1, Chromium, 1));
        /* Tungsten Steel/Carbide*/
        addBlastAlloyRecipes(TungstenSteel, 2, 3000, 480, ImmutableMap.of(Steel, 1, Tungsten, 1));
        addBlastAlloyRecipes(TungstenCarbide, 1, 246 * 20, 480, ImmutableMap.of(Carbon, 1, Tungsten, 1));
        /* Niobium Titanium*/
        addBlastAlloyRecipes(NiobiumTitanium, 2, 225 * 20, 480, ImmutableMap.of(Niobium, 1, Titanium, 1));
        /* Vanadium Gallium*/
        addBlastAlloyRecipes(VanadiumGallium, 4, 225 * 20, 480, ImmutableMap.of(Vanadium, 3, Gallium, 1));
        /* Vanadium Steel*/
        addBlastAlloyRecipes(VanadiumSteel, 9, 225 * 20, 120, ImmutableMap.of(Vanadium, 1, Chromium, 1, Steel, 7));
        /* Ultimet */
        addBlastAlloyRecipes(Ultimet, 9, 2700, 120, ImmutableMap.of(Cobalt, 5, Chromium, 2, Nickel, 1, Molybdenum, 1));
        /* Kanthal*/
        addBlastAlloyRecipes(Kanthal, 3, 1800, 120, ImmutableMap.of(Iron, 1, Aluminium, 1, Chromium, 1));
        /* Nichrome*/
        E_BLAST_FURNACE.RB().temperature(2700).ii(of(4, DUST.getMaterialTag(Nickel), INGOT.getMaterialTag(Nickel)), of(1, DUST.getMaterialTag(Chromium), INGOT.getMaterialTag(Chromium)), INT_CIRCUITS.get(2))
                .io(INGOT_HOT.get(Nichrome, 5))
                .add("nichrome_ingot", 135 * 20, 480);
        /* Osmiridium*/
        E_BLAST_FURNACE.RB().temperature(2900).ii(of(3, DUST.getMaterialTag(Iridium), INGOT.getMaterialTag(Iridium)), of(1, DUST.getMaterialTag(Osmium), INGOT.getMaterialTag(Osmium)))
                .fi(Helium.getGas(1000))
                .io(INGOT_HOT.get(Osmiridium, 4))
                .add("osmiridium_ingot", 25 * 20, 1920);
        /* Naquadah Alloy*/
        E_BLAST_FURNACE.RB().temperature(7200).ii(of(1, DUST.getMaterialTag(Naquadah), INGOT.getMaterialTag(Naquadah)), of(1, INGOT.getMaterialTag(Osmiridium), DUST.getMaterialTag(Osmiridium), INGOT_HOT.getMaterialTag(Osmiridium)))
                .fi(Argon.getGas(1000))
                .io(INGOT_HOT.get(NaquadahAlloy, 2))
                .add("naquadah_alloy_ingot",25 * 20, 30720);
        /* TFC stuff and hss */
        addBlastAlloyRecipes(BlackSteel, 5, 1200, 120, ImmutableMap.of(Nickel, 1, BlackBronze, 1, Steel, 3));
        addBlastAlloyRecipes(BlueSteel, 8, 1400, 120, ImmutableMap.of(SterlingSilver, 1, BismuthBronze, 1, Steel, 2, BlackSteel, 4));
        addBlastAlloyRecipes(RedSteel, 8, 1300, 120, ImmutableMap.of(RoseGold, 1, Brass, 1, Steel, 2, BlackSteel, 4));
        addBlastAlloyRecipes(HSSG, 9, 9000, 120, ImmutableMap.of(TungstenSteel, 5, Chromium, 1, Molybdenum, 2, Vanadium, 1));
        addBlastAlloyRecipes(HSSE, 9, 10800, 120, ImmutableMap.of(HSSG, 6, Cobalt, 1, Manganese, 1, Silicon, 1));
        addBlastAlloyRecipes(HSSS, 9, 810 * 20, 120, ImmutableMap.of(HSSG, 6, Cobalt, 2, Iridium, 1));
        //TODO figure out proper nuclear

        //Nuclear Processing
        /*BLASTING.RB().temperature(3000).ii(of(DUST.get(Thorium227Dioxide,1)), of(DUST.get(Carbon,1))).io(INGOT_HOT.get(Thorium227,1)).fo(CarbonDioxide.getGas(1000)).add("hot_thorium227_ingot",40*20, 480);
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
        BLASTING.RB().temperature(3000).ii(of(DUST.get(Uranium240Dioxide,1)), of(DUST.get(Carbon,1))).io(INGOT_HOT.get(Uranium240,1)).fo(CarbonDioxide.getGas(1000)).add("hot_uranium240_ingot",40*20, 480);*/


    }

    private static void addBlastAlloyRecipes(Material output, int amount, int duration, int power, ImmutableMap<Material, Integer> inputs){
        RecipeBuilder b = E_BLAST_FURNACE.RB().temperature(GregTechMaterialTags.BLAST_FURNACE_TEMP.getInt(output));
        b.io((output.has(INGOT_HOT) ?  INGOT_HOT : INGOT).get(output, amount));
        inputs.forEach((m, i) -> {
            if (m.has(INGOT_HOT)){
                b.ii(of(i, DUST.getMaterialTag(m), INGOT.getMaterialTag(m), INGOT_HOT.getMaterialTag(m)));
            } else if (m.has(INGOT)){
                b.ii(of(i, DUST.getMaterialTag(m), INGOT.getMaterialTag(m)));
            } else {
                b.ii(DUST.getMaterialIngredient(m, i));
            }
        });
        b.add(output.getId() + "_ingot", duration,power);
    }

    private static void addCalciteRecipe(Material ore, int input, ItemStack... outputs){
        PRIMITIVE_BLAST_FURNACE.RB().ii(RAW_ORE.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Calcite, 1)).io(outputs).add(ore.getId() + "_calcite", input * 1000);
        PRIMITIVE_BLAST_FURNACE.RB().ii(RAW_ORE.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Limestone, 1)).io(outputs).add(ore.getId() + "_limestone", input * 1000);
        PRIMITIVE_BLAST_FURNACE.RB().ii(RAW_ORE.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Marble, 1)).io(outputs).add(ore.getId() + "_marble", input * 1000);
        PRIMITIVE_BLAST_FURNACE.RB().ii(CRUSHED.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Calcite, 1)).io(outputs).add("crushed_" + ore.getId() + "_calcite", input * 1000);
        PRIMITIVE_BLAST_FURNACE.RB().ii(CRUSHED.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Limestone, 1)).io(outputs).add("crushed_" + ore.getId() + "_limestone", input * 1000);
        PRIMITIVE_BLAST_FURNACE.RB().ii(CRUSHED.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Marble, 1)).io(outputs).add("crushed_" + ore.getId() + "_marble", input * 1000);
        PRIMITIVE_BLAST_FURNACE.RB().ii(DUST_IMPURE.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Calcite, 1)).io(outputs).add("impure_" + ore.getId() + "_calcite", input * 1000);
        PRIMITIVE_BLAST_FURNACE.RB().ii(DUST_IMPURE.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Limestone, 1)).io(outputs).add("impure_" + ore.getId() + "_limestone", input * 1000);
        PRIMITIVE_BLAST_FURNACE.RB().ii(DUST_IMPURE.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Marble, 1)).io(outputs).add("impure_" + ore.getId() + "_marble", input * 1000);
        PRIMITIVE_BLAST_FURNACE.RB().ii(DUST.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Calcite, 1)).io(outputs).add("dust_" + ore.getId() + "_calcite", input * 1000);
        PRIMITIVE_BLAST_FURNACE.RB().ii(DUST.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Limestone, 1)).io(outputs).add("dust_" + ore.getId() + "_limestone", input * 1000);
        PRIMITIVE_BLAST_FURNACE.RB().ii(DUST.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Marble, 1)).io(outputs).add("dust_" + ore.getId() + "_marble", input * 1000);
        E_BLAST_FURNACE.RB().ii(DUST.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Calcite, 1)).io(outputs).add(ore.getId() + "_calcite", input * 500, 120);
        E_BLAST_FURNACE.RB().ii(DUST.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Limestone, 1)).io(outputs).add(ore.getId() + "_limestone", input * 500, 120);
        E_BLAST_FURNACE.RB().ii(DUST.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Marble, 1)).io(outputs).add(ore.getId() + "_marble", input * 500, 120);

    }
}
