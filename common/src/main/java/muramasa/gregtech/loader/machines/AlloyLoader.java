package muramasa.gregtech.loader.machines;

import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialStack;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.GregTechMaterialTags;
import muramasa.gregtech.data.Materials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.material.MaterialTags.METAL;
import static muramasa.antimatter.material.MaterialTags.RUBBERTOOLS;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.ALLOY_SMELTING;

public class AlloyLoader {

    public static void init() {
        INGOT.all().forEach(t -> {
            if (t.has(GregTechMaterialTags.NEEDS_BLAST_FURNACE)) return;
            if (!t.has(METAL)) return;
            List<MaterialStack> stacks = t.getProcessInto();
            if (stacks.size() != 2) return;
            int cumulative = t == RedAlloy ? 1 : stacks.get(0).s + stacks.get(1).s;
            MaterialStack first = stacks.get(0);
            MaterialStack second = stacks.get(1);
            ALLOY_SMELTING.RB().ii(of(DUST.getMaterialTag(first.m),first.s),of(DUST.getMaterialTag(second.m),second.s)).io(new ItemStack(INGOT.get(t),cumulative)).add(t.getId() +"_ingot",100, 16);
            boolean firstIngot = first.m.has(INGOT);
            boolean secondIngot = second.m.has(INGOT);
            if (firstIngot && secondIngot) ALLOY_SMELTING.RB().ii(of(INGOT.getMaterialTag(first.m),first.s),of(INGOT.getMaterialTag(second.m),second.s)).io(new ItemStack(INGOT.get(t),cumulative)).add(t.getId() +"_ingot_2",100, 12);
            if (firstIngot) ALLOY_SMELTING.RB().ii(of(INGOT.getMaterialTag(first.m),first.s),of(DUST.getMaterialTag(second.m),second.s)).io(new ItemStack(INGOT.get(t),cumulative)).add(t.getId() +"_ingot_3",100, 12);
            if (secondIngot) ALLOY_SMELTING.RB().ii(of(DUST.getMaterialTag(first.m),first.s),of(INGOT.getMaterialTag(second.m),second.s)).io(new ItemStack(INGOT.get(t),cumulative)).add(t.getId() +"_ingot_4",100, 12);
        });
        addAlloyRecipes(Copper, 3,Electrum, 2, BlackBronze, 5);
        addAlloyRecipes(Bismuth, 1, Brass, 4, BismuthBronze, 5);
        //pre Chemical Reactor Rubber
        ALLOY_SMELTING.RB().ii(of(DUST.get(RawRubber), 3), of(DUST.getMaterialTag(Sulfur), 1))
                .io(INGOT.get(Rubber, 1)).add("rubber_via_alloy_smelter",20, 10);
        PLATE.all().stream().filter(m -> !m.has(GregTechMaterialTags.NEEDS_BLAST_FURNACE) && m.has(INGOT)).forEach(m ->{
            int euPerTick = m.has(RUBBERTOOLS) ? 8 : 32;
            ALLOY_SMELTING.RB().ii(INGOT.getMaterialIngredient(m, 2), RecipeIngredient.of(GTCoreItems.MoldPlate, 1).setNoConsume()).io(PLATE.get(m, 1)).add(m.getId() + "_plate", m.getMass() * 2, euPerTick);
            if (m.has(RUBBERTOOLS)) {
                ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(m, 2), RecipeIngredient.of(GTCoreItems.MoldPlate, 1).setNoConsume()).io(PLATE.get(m, 1)).add(m.getId() + "_plate_from_dust", m.getMass() * 2, euPerTick);
            }
        });
        INGOT.all().stream().filter(m -> !m.has(GregTechMaterialTags.NEEDS_BLAST_FURNACE)).forEach(m -> { //TODO other ingot recipes
            if (m.has(NUGGET)){
                ALLOY_SMELTING.RB().ii(NUGGET.getMaterialIngredient(m, 9), RecipeIngredient.of(GTCoreItems.MoldIngot, 1).setNoConsume()).io(INGOT.get(m, 1)).add(m.getId() + "_ingot_from_nugget", 200, 2);
            }
            if (m.has(RUBBERTOOLS)) {
                ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(m, 1), RecipeIngredient.of(GTCoreItems.MoldIngot, 1).setNoConsume()).io(INGOT.get(m, 1)).add(m.getId() + "_ingot_from_dust", m.getMass(), 10);
            }
        });
        ITEM_CASING.all().forEach(m -> {
            ALLOY_SMELTING.RB().ii(INGOT.getMaterialIngredient(m, 2), of(GTCoreItems.MoldCasing, 1).setNoConsume()).io(ITEM_CASING.get(m, 3)).add(m.getId() + "_item_casing", Math.max(m.getMass() * 2 / 3, 1), 16);
        });
        GEAR.all().stream().filter(m -> !m.has(GregTechMaterialTags.NEEDS_BLAST_FURNACE) && m.has(INGOT)).forEach(m ->{
            int euPerTick = m.has(RUBBERTOOLS) ? 8 : 32;
            ALLOY_SMELTING.RB().ii(INGOT.getMaterialIngredient(m, 8), RecipeIngredient.of(GTCoreItems.MoldGear, 1).setNoConsume()).io(GEAR.get(m, 1)).add(m.getId() + "_gear", m.getMass() * 8, euPerTick);
            if (m.has(RUBBERTOOLS)) {
                ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(m, 8), RecipeIngredient.of(GTCoreItems.MoldGear, 1).setNoConsume()).io(GEAR.get(m, 1)).add(m.getId() + "_gear_from_dust", m.getMass() * 8, euPerTick);
            }
        });
        ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(Glass, 1), RecipeIngredient.of(GTCoreItems.MoldBall, 1).setNoConsume()).io(GregTechData.GlassTube).add("glass_tube", 160, 8);
        ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(Glass, 1), RecipeIngredient.of(GTCoreItems.MoldBottle, 1).setNoConsume()).io(Items.GLASS_BOTTLE).add("glass_bottle", 64, 4);
        ALLOY_SMELTING.RB().ii(INGOT.getMaterialIngredient(Iron, 31), RecipeIngredient.of(GTCoreItems.MoldAnvil, 1).setNoConsume()).io(Items.ANVIL).add("anvil", 512, 64);

        //Fissile Fuels
        FISSILE_FUEL.all().forEach(f -> ALLOY_SMELTING.RB().ii(of(INGOT.get(f), 1), of(INGOT.get(f))).io(FISSILE_FUEL.get(f)).add(f.getId() + "_fissile_fuel_smelting"));

        //Breeder Fuels
        //BREEDER_FUEL.all().forEach(f -> {
            //Item fuel = BREEDER_FUEL.get(f);
            //Material fissilemat = fuel.fissileMaterial, breedermat = fuel.breederMaterial;
            //ALLOY_SMELTING.RB().ii(of(FISSILE_FUEL.get(fissilemat), 1), of(INGOT.get(breedermat))).io(new ItemStack(fuel)).add(f.getId() + "_breeder_fuel_smelting");
        //});
    }

    private static void addAlloyRecipes(Material input1, int count1, Material input2, int count2, Material output, int countO){
        if (input1.has(INGOT) && input2.has(INGOT)) {
            ALLOY_SMELTING.RB().ii(INGOT.getMaterialIngredient(input1, count1), INGOT.getMaterialIngredient(input2, count2)).io(INGOT.get(output, countO)).add(output.getId() + "_ingot", 100, 12);
        }
        if (input2.has(INGOT)) {
            ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(input1, count1), INGOT.getMaterialIngredient(input2, count2)).io(INGOT.get(output, countO)).add(output.getId() + "_ingot_1", 100, 12);
        }
        if (input1.has(INGOT)) {
            ALLOY_SMELTING.RB().ii(INGOT.getMaterialIngredient(input1, count1), DUST.getMaterialIngredient(input2, count2)).io(INGOT.get(output, countO)).add(output.getId() + "_ingot_2", 100, 12);
        }
        ALLOY_SMELTING.RB().ii(DUST.getMaterialIngredient(input1, count1), DUST.getMaterialIngredient(input2, count2)).io(INGOT.get(output, countO)).add(output.getId() + "_ingot_3", 100, 12);
    }
}
