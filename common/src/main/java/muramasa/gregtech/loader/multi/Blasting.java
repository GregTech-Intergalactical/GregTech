package muramasa.gregtech.loader.multi;

import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.data.GregTechMaterialTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

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


        });

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
        BASIC_BLASTING.RB().ii(INGOT.getMaterialIngredient(AntimatterMaterials.Iron,1)).io(INGOT.get(Steel, 1), DUST_SMALL.get(DarkAsh,8)).chances(1.0, 0.5).add("steel_ingot",1200, 0);
        DUST.all().forEach(m -> {
            if (m.has(GregTechMaterialTags.NEEDS_BLAST_FURNACE) && m.has(GregTechMaterialTags.BLAST_FURNACE_TEMP)){
                ItemStack ingot = DIRECT_SMELT_INTO.getMapping(m).has(INGOT_HOT) ? INGOT_HOT.get(DIRECT_SMELT_INTO.getMapping(m), 1) : INGOT.get(DIRECT_SMELT_INTO.getMapping(m), 1);
                int heat = GregTechMaterialTags.BLAST_FURNACE_TEMP.getInt(m);
                BLASTING.RB().temperature(heat).ii(DUST.getMaterialIngredient(m, 1)).io(ingot).add(DIRECT_SMELT_INTO.getMapping(m).getId() + "_ingot_from_" + m.getId() + "_dust", Math.max(m.getMass() / 40L, 1L) * heat, 120);
            }
        });

        addCalciteRecipe(BandedIron, 4, new ItemStack(Items.IRON_INGOT, 1));
        addCalciteRecipe(YellowLimonite, 6, new ItemStack(Items.IRON_INGOT, 1));
        addCalciteRecipe(BrownLimonite, 6, new ItemStack(Items.IRON_INGOT, 1));
        addCalciteRecipe(Magnetite, 11, new ItemStack(Items.IRON_INGOT, 3));
        addCalciteRecipe(Cassiterite, 5, INGOT.get(Tin, 1));
        addCalciteRecipe(Chalcopyrite, 6, new ItemStack(Items.COPPER_INGOT), new ItemStack(Items.IRON_INGOT));
        addCalciteRecipe(Tetrahedrite, 12, new ItemStack(Items.COPPER_INGOT, 3), new ItemStack(Items.IRON_INGOT));
        addCalciteRecipe(Galena, 4, INGOT.get(Lead, 1), INGOT.get(Silver, 1));
        addCalciteRecipe(Garnierite, 3, INGOT.get(Nickel, 1));
        addCalciteRecipe(Cobaltite, 5, INGOT.get(Cobalt, 1));
        /* Annealed Copper*/
        BLASTING.RB().temperature(1200).ii(DUST.getMaterialIngredient(AntimatterMaterials.Copper, 1))
                .fi(Oxygen.getGas(1000))
                .io(INGOT.get(AnnealedCopper))
                .add("annealed_copper_ingot", 25 * 20, 120);
        /* Wrought Iron*/
        BLASTING.RB().temperature(1700).ii(DUST.getMaterialIngredient(Ilmenite, 1), DUST.getMaterialIngredient(Carbon, 1))
                .io(NUGGET.get(WroughtIron, 4), DUST_TINY.get(Rutile, 4))
                .add("wrought_iron_nuggets", 40 * 20, 500);
        /* Steel */
        BLASTING.RB().temperature(1000).ii(INGOT.getMaterialIngredient(AntimatterMaterials.Iron, 1))
                .fi(Oxygen.getGas(1000))
                .io(INGOT.get(Steel), DUST_SMALL.get(DarkAsh))
                .add("steel_ingot", 500, 120);
        BLASTING.RB().temperature(1000).ii(INGOT.getMaterialIngredient(WroughtIron, 1))
                .fi(Oxygen.getGas(1000))
                .io(INGOT.get(Steel), DUST_SMALL.get(DarkAsh))
                .add("steel_ingot_2", 100, 120);
        /* Aluminium*/
        BLASTING.RB().temperature(1200).ii(DUST.getMaterialIngredient(Ruby, 1))
                .io(NUGGET.get(Aluminium, 3), DUST_TINY.get(DarkAsh, 1))
                .add("aluminium_ingot_from_ruby", 400, 100);
        BLASTING.RB().temperature(1200).ii(DUST.getMaterialIngredient(BlueSapphire, 1))
                .io(NUGGET.get(Aluminium, 3))
                .add("aluminium_ingot_from_blud_sapphire", 400, 100);
        BLASTING.RB().temperature(1200).ii(DUST.getMaterialIngredient(GreenSapphire, 1))
                .io(NUGGET.get(Aluminium, 3), DUST_TINY.get(DarkAsh, 1))
                .add("aluminium_ingot_from_green_sapphire", 400, 100);
        /* Stainless Steel*/
        BLASTING.RB().temperature(1700).ii(INGOT.getMaterialIngredient(AntimatterMaterials.Iron, 6), INGOT.getMaterialIngredient(Nickel, 1), INGOT.getMaterialIngredient(Manganese, 1), INGOT.getMaterialIngredient(Chrome, 1))
                .io(INGOT.get(StainlessSteel, 9), DUST_SMALL.get(DarkAsh, 1))
                .add("stainless_steel_ingot", 1000, 120);
        /* TITANIUM */
        BLASTING.RB().temperature(2140).ii(RecipeIngredient.of(DUST.getMaterialTag(Magnesium), 2))
                .fi(Titaniumtetrachloride.getLiquid(1000))
                .io(INGOT_HOT.get(Titanium,1), DUST.get(MagnesiumChloride,2))
                .add("titanium_ingot",40*20, 480);
        /* Tungsten Steel/Carbide*/
        BLASTING.RB().temperature(3000).ii(INGOT.getMaterialIngredient(Tungsten, 1), INGOT.getMaterialIngredient(Steel, 1))
                .io(INGOT_HOT.get(TungstenSteel, 2), DUST_SMALL.get(DarkAsh, 1))
                .add("tungsten_steel_ingot", 150 * 20, 480);
        BLASTING.RB().temperature(3000).ii(INGOT.getMaterialIngredient(Tungsten, 1), DUST.getMaterialIngredient(Carbon, 1))
                .io(INGOT_HOT.get(TungstenCarbide, 1), DUST_SMALL.get(DarkAsh, 2))
                .add("tungsten_carbide_ingot", 246 * 20, 480);
        /* Niobium Titanium*/
        BLASTING.RB().temperature(4500).ii(INGOT.getMaterialIngredient(Niobium, 1), INGOT.getMaterialIngredient(Titanium, 1))
                .io(INGOT_HOT.get(NiobiumTitanium, 2), DUST_SMALL.get(DarkAsh, 1))
                .add("niobium_titanium_ingot",225 * 20, 480);
        /* Vanadium Gallium*/
        BLASTING.RB().temperature(4500).ii(INGOT.getMaterialIngredient(Vanadium, 3), INGOT.getMaterialIngredient(Gallium, 1))
                .io(INGOT_HOT.get(VanadiumGallium, 4))
                .add("vanadium_gallium_ingot", 225 * 20, 480);
        /* Kanthal*/
        BLASTING.RB().temperature(1800).ii(INGOT.getMaterialIngredient(AntimatterMaterials.Iron, 1), INGOT.getMaterialIngredient(Aluminium, 1), INGOT.getMaterialIngredient(Chrome, 1))
                .io(INGOT_HOT.get(Kanthal, 3), DUST_SMALL.get(DarkAsh, 1))
                .add("kanthal_ingot", 90 * 20, 120);
        /* Nichrome*/
        BLASTING.RB().temperature(2700).ii(INGOT.getMaterialIngredient(Nickel, 4), INGOT.getMaterialIngredient(Chrome, 1))
                .io(INGOT_HOT.get(Nichrome, 5), DUST_SMALL.get(DarkAsh, 2))
                .add("nichrome_ingot", 135 * 20, 480);
        /* Osmiridium*/
        BLASTING.RB().temperature(2900).ii(INGOT.getMaterialIngredient(Iridium, 3), INGOT.getMaterialIngredient(Osmium, 1))
                .fi(Helium.getGas(1000))
                .io(INGOT_HOT.get(Osmiridium, 4))
                .add("osmiridium_ingot", 25 * 20, 1920);
        /* Naquadah Alloy*/
        BLASTING.RB().temperature(7200).ii(INGOT.getMaterialIngredient(Naquadah, 1), INGOT.getMaterialIngredient(Osmiridium, 1))
                .fi(Argon.getGas(1000))
                .io(INGOT_HOT.get(NaquadahAlloy, 2))
                .add("naquadah_ingot",25 * 20, 30720);
        /* TFC stuff and hss */
        BLASTING.RB().temperature(1200).ii(INGOT.getMaterialIngredient(Nickel, 1), INGOT.getMaterialIngredient(BlackBronze, 1), INGOT.getMaterialIngredient(Steel, 3))
                .io(INGOT.get(BlackSteel, 5))
                .add("black_steel_ingot", 60 * 20, 120);
        BLASTING.RB().temperature(1400).ii(INGOT.getMaterialIngredient(SterlingSilver, 1), INGOT.getMaterialIngredient(BismuthBronze, 1), INGOT.getMaterialIngredient(Steel, 2), INGOT.getMaterialIngredient(BlackSteel, 4))
                .io(INGOT.get(BlueSteel, 8))
                .add("blue_steel_ingot", 70 * 20, 120);
        BLASTING.RB().temperature(1300).ii(INGOT.getMaterialIngredient(RoseGold, 1), INGOT.getMaterialIngredient(Brass, 1), INGOT.getMaterialIngredient(Steel, 2), INGOT.getMaterialIngredient(BlackSteel, 4))
                .io(INGOT.get(RedSteel, 8))
                .add("red_steel_ingot", 65 * 20, 120);
        BLASTING.RB().temperature(4500).ii(INGOT.getMaterialIngredient(TungstenSteel, 5), INGOT.getMaterialIngredient(Chrome, 1), INGOT.getMaterialIngredient(Molybdenum, 2), INGOT.getMaterialIngredient(Vanadium, 1))
                .io(INGOT_HOT.get(HSSG, 9))
                .add("hssg_ingot", 450 * 20, 120);
        BLASTING.RB().temperature(5400).ii(INGOT.getMaterialIngredient(HSSG, 6), INGOT.getMaterialIngredient(Cobalt, 1), INGOT.getMaterialIngredient(Manganese, 1), INGOT.getMaterialIngredient(Silicon, 1))
                .io(INGOT_HOT.get(HSSE, 8))
                .add("hsse_ingot", 540 * 20, 120);
        BLASTING.RB().temperature(5400).ii(INGOT.getMaterialIngredient(HSSG, 6), INGOT.getMaterialIngredient(Osmiridium, 2), INGOT.getMaterialIngredient(Iridium, 1))
                .io(INGOT_HOT.get(HSSS, 9))
                .add("hsss_ingot", 810 * 20, 120);

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

    private static void addCalciteRecipe(Material ore, int input, ItemStack... outputs){
        BASIC_BLASTING.RB().ii(RAW_ORE.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Calcite, 1)).io(outputs).add(ore.getId() + "_calcite", input * 1000);
        BASIC_BLASTING.RB().ii(RAW_ORE.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Limestone, 1)).io(outputs).add(ore.getId() + "_limestone", input * 1000);
        BASIC_BLASTING.RB().ii(RAW_ORE.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Marble, 1)).io(outputs).add(ore.getId() + "_marble", input * 1000);
        BLASTING.RB().ii(DUST.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Calcite, 1)).io(outputs).add(ore.getId() + "_calcite", input * 500, 120);
        BLASTING.RB().ii(DUST.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Limestone, 1)).io(outputs).add(ore.getId() + "_limestone", input * 500, 120);
        BLASTING.RB().ii(DUST.getMaterialIngredient(ore, input), DUST.getMaterialIngredient(Marble, 1)).io(outputs).add(ore.getId() + "_marble", input * 500, 120);
    }
}
