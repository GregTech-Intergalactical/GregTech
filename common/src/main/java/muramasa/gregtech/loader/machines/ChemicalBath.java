package muramasa.gregtech.loader.machines;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static muramasa.antimatter.material.MaterialTags.CHEMBATH_MERCURY;
import static muramasa.antimatter.material.MaterialTags.CHEMBATH_PERSULFATE;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.CHEMICAL_BATHING;

public class ChemicalBath {
    public static void init() {
        CHEMICAL_BATHING.RB()
                .ii(RecipeIngredient.of(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Wood),1))
                .fi(AntimatterMaterials.Water.getLiquid(100))
                .io(new ItemStack(Items.PAPER))
                .add("paper",200,4);
        CHEMICAL_BATHING.RB()
                .ii(RecipeIngredient.of(Items.SUGAR_CANE,1))
                .fi(AntimatterMaterials.Water.getLiquid(100))
                .io(new ItemStack(Items.PAPER))
                .add("paper_1",100,4);
        CHEMICAL_BATHING.RB()
                .ii(RecipeIngredient.of(Items.NETHER_STAR,1))
                .fi(Plutonium.getLiquid(1152))
                .io(new ItemStack(GregTechData.QuantumStat))
                .add("quantum_stat",96*20,384);
        mercurybathing();
        persulfatebathing();
    }
    public static void mercurybathing(){
        CHEMBATH_MERCURY.getAll().forEach((main,side) ->
            CHEMICAL_BATHING.RB()
                    .ii(RecipeIngredient.of(AntimatterMaterialTypes.CRUSHED.get(main),1))
                    .fi(Mercury.getLiquid(1000))
                    .io(new ItemStack(AntimatterMaterialTypes.CRUSHED_PURIFIED.get(main)),new ItemStack(AntimatterMaterialTypes.DUST.get(side)),new ItemStack(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Stone)))
                    .add("mercury_" + main.getId(),40*20,8));
    }
    public static void persulfatebathing(){
        CHEMBATH_PERSULFATE.getAll().forEach((main,side) ->
                CHEMICAL_BATHING.RB()
                        .ii(RecipeIngredient.of(AntimatterMaterialTypes.CRUSHED.get(main),1))
                        .fi(SodiumPersulfate.getLiquid(1000))
                        .io(new ItemStack(AntimatterMaterialTypes.CRUSHED_PURIFIED.get(main)),new ItemStack(AntimatterMaterialTypes.DUST.get(side)),new ItemStack(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Stone)))
                        .add("persulfate_"+main.getId(),40*20,8));
    }
}
