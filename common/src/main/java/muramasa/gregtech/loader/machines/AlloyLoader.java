package muramasa.gregtech.loader.machines;

import muramasa.antimatter.material.MaterialStack;
import muramasa.antimatter.material.MaterialTags;
import muramasa.gregtech.data.Materials;
import net.minecraft.world.item.ItemStack;

import java.util.List;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.material.MaterialTags.METAL;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.ALLOY_SMELTING;

public class AlloyLoader {

    public static void init() {
        INGOT.all().forEach(t -> {
            if (t.has(MaterialTags.NEEDS_BLAST_FURNACE)) return;
            if (!t.has(METAL)) return;
            List<MaterialStack> stacks = t.getProcessInto();
            if (stacks.size() != 2) return;
            int cumulative = stacks.get(0).s + stacks.get(1).s;
            MaterialStack first = stacks.get(0);
            MaterialStack second = stacks.get(1);
            ALLOY_SMELTING.RB().ii(of(DUST.getMaterialTag(first.m),first.s),of(DUST.getMaterialTag(second.m),second.s)).io(new ItemStack(INGOT.get(t),cumulative)).add(t.getId() +"_ingot",100, 16);
            boolean firstIngot = first.m.has(INGOT);
            boolean secondIngot = second.m.has(INGOT);
            if (firstIngot && secondIngot) ALLOY_SMELTING.RB().ii(of(INGOT.getMaterialTag(first.m),first.s),of(INGOT.getMaterialTag(second.m),second.s)).io(new ItemStack(INGOT.get(t),cumulative)).add(t.getId() +"_ingot_2",100, 12);
            if (firstIngot) ALLOY_SMELTING.RB().ii(of(INGOT.getMaterialTag(first.m),first.s),of(DUST.getMaterialTag(second.m),second.s)).io(new ItemStack(INGOT.get(t),cumulative)).add(t.getId() +"_ingot_3",100, 12);
            if (secondIngot) ALLOY_SMELTING.RB().ii(of(DUST.getMaterialTag(first.m),first.s),of(INGOT.getMaterialTag(second.m),second.s)).io(new ItemStack(INGOT.get(t),cumulative)).add(t.getId() +"_ingot_4",100, 12);
        });
        //Fireclay
        ALLOY_SMELTING.RB().ii(of(DUST.getMaterialTag(Brick), 1), of(DUST.getMaterialTag(Sand), 2))
                .io(DUST.get(Fireclay, 1)).add("fireclay",20, 10);
        //pre Chemical Reactor Rubber
        ALLOY_SMELTING.RB().ii(of(DUST.get(RawRubber), 1), of(DUST.getMaterialTag(Sulfur), 1))
                .io(DUST.get(Rubber, 1)).add("rubber_via_alloy_smelter",20, 10);
        //Fissile Fuels
        FISSILE_FUEL.all().forEach(f -> ALLOY_SMELTING.RB().ii(of(INGOT.get(f), 1), of(INGOT.get(f))).io(FISSILE_FUEL.get(f)).add(f.getId() + "_fissile_fuel_smelting"));

        //Breeder Fuels
        //BREEDER_FUEL.all().forEach(f -> {
            //Item fuel = BREEDER_FUEL.get(f);
            //Material fissilemat = fuel.fissileMaterial, breedermat = fuel.breederMaterial;
            //ALLOY_SMELTING.RB().ii(of(FISSILE_FUEL.get(fissilemat), 1), of(INGOT.get(breedermat))).io(new ItemStack(fuel)).add(f.getId() + "_breeder_fuel_smelting");
        //});
    }
}
