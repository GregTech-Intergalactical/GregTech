package muramasa.gregtech.loader.machines;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.gregtech.GregTechConfig;
import muramasa.gregtech.data.GregTechMaterialTags;
import muramasa.gregtech.data.Materials;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

import static muramasa.antimatter.Ref.L;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Charcoal;
import static muramasa.antimatter.data.AntimatterMaterials.Coal;
import static muramasa.gregtech.data.GregTechMaterialTags.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.ELECTROLYZING;

public class ElectrolyzerLoader {
    public static void init() {
        List<Material> elecMaterials = new ArrayList<>(ELEC.all().stream().toList());
        if (!GregTechConfig.GAMEPLAY.HARDER_ALUMINIUM_PROCESSING){
            elecMaterials.add(Alumina);
        }
        elecMaterials.forEach(t -> {
            if (!t.has(DUST) && !t.has(LIQUID) && !t.has(GAS)) return;
            FluidHolder[] fluids = t.getProcessInto().stream().filter(mat -> ((mat.m.has(GAS) || mat.m.has(AntimatterMaterialTypes.LIQUID)) && !mat.m.has(AntimatterMaterialTypes.DUST))).map(mat -> mat.m.has(GAS) ? mat.m.getGas(mat.s*1000) : mat.m.getLiquid(mat.s*1000)).toArray(FluidHolder[]::new);
            for (FluidHolder fluid : fluids) {
                if (fluid.isEmpty())
                    return;
            }
            if (fluids.length > 6) return;
            int euPerTick = t.has(ELEC30) ? 30 : t.has(ELEC60) || t == Alumina ? 60 : t.has(ELEC90) ? 90 : 120;
            ItemStack[] items = t.getProcessInto().stream().filter(mat -> mat.m.has(AntimatterMaterialTypes.DUST)).map(mat -> AntimatterMaterialTypes.DUST.get(mat.m, mat.s)).toArray(ItemStack[]::new);
            int inputAmount = MaterialTags.PROCESS_INTO.get(t).getRight() > 0 ? MaterialTags.PROCESS_INTO.get(t).getRight() : t.getProcessInto().stream().mapToInt(mat -> mat.s).sum();
            RecipeBuilder b = ELECTROLYZING.RB();
            if (t.has(DUST)){
                b.ii(DUST.getMaterialIngredient(t, inputAmount));
            } else {
                b.fi(t.getFluidTag(inputAmount * 1000));
            }
            long duration = t.has(ELEC_TICKS) ? ELEC_TICKS.getInt(t) : t.getMass() * 20;
            b.io(items).fo(fluids).add("dust_" + t.getId(),duration, euPerTick);
        });
        ELECTROLYZING.RB().ii(RecipeIngredient.of(ItemTags.SAND, 8)).io(DUST.get(Materials.SiliconDioxide)).add("sand_to_silicon_dioxide", 500, 25);
        ELECTROLYZING.RB().ii(RecipeIngredient.of(Items.BONE_MEAL, 3)).io(DUST.get(Materials.Calcium)).add("bone_meal", 98, 26);
        if (GregTechConfig.GAMEPLAY.HARDER_ALUMINIUM_PROCESSING) {
            ELECTROLYZING.RB().ii(DUST.getMaterialIngredient(Carbon, 3), DUST.getMaterialIngredient(Alumina, 10))
                    .fi(AluminiumFluoride.getLiquid(L / 36), Cryolite.getLiquid(L / 72)).io(DUST.get(Aluminium, 4))
                    .fo(CarbonDioxide.getGas(9000), Fluorine.getGas(29)).add("alumina_carbon", 2040, 16);
            ELECTROLYZING.RB().ii(DUST.getMaterialIngredient(Charcoal, 3), DUST.getMaterialIngredient(Alumina, 10))
                    .fi(AluminiumFluoride.getLiquid(L / 36), Cryolite.getLiquid(L / 72)).io(DUST.get(Aluminium, 4))
                    .fo(CarbonDioxide.getGas(9000), Fluorine.getGas(29)).add("alumina_charcoal", 2040, 16);
            ELECTROLYZING.RB().ii(DUST.getMaterialIngredient(Coal, 3), DUST.getMaterialIngredient(Alumina, 10))
                    .fi(AluminiumFluoride.getLiquid(L / 36), Cryolite.getLiquid(L / 72)).io(DUST.get(Aluminium, 4))
                    .fo(CarbonDioxide.getGas(9000), Fluorine.getGas(29)).add("alumina_coal", 2040, 16);
            ELECTROLYZING.RB().ii(DUST.getMaterialIngredient(CoalCoke, 3), DUST.getMaterialIngredient(Alumina, 10))
                    .fi(AluminiumFluoride.getLiquid(L / 36), Cryolite.getLiquid(L / 72)).io(DUST.get(Aluminium, 4))
                    .fo(CarbonDioxide.getGas(9000), Fluorine.getGas(29)).add("alumina_coke", 2040, 16);
            ELECTROLYZING.RB().ii(DUST.getMaterialIngredient(Graphite, 3), DUST.getMaterialIngredient(Alumina, 10))
                    .fi(AluminiumFluoride.getLiquid(L / 36), Cryolite.getLiquid(L / 72)).io(DUST.get(Aluminium, 4))
                    .fo(CarbonDioxide.getGas(9000), Fluorine.getGas(29)).add("alumina_graphite", 2040, 16);
        }
    }

}
