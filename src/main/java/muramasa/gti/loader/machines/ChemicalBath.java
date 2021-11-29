package muramasa.gti.loader.machines;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gti.data.GregTechData;
import muramasa.gti.data.Materials;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.material.MaterialTag.CHEMBATH_MERCURY;
import static muramasa.antimatter.material.MaterialTag.CHEMBATH_PERSULFATE;
import static muramasa.gti.data.Materials.*;
import static muramasa.gti.data.RecipeMaps.CHEMICAL_BATHING;

public class ChemicalBath {
    public static void init() {
        CHEMICAL_BATHING.RB()
                .ii(RecipeIngredient.of(DUST.get(Materials.Wood),1))
                .fi(Water.getLiquid(100))
                .io(new ItemStack(Items.PAPER))
                .add(200,4);
        CHEMICAL_BATHING.RB()
                .ii(RecipeIngredient.of(Items.SUGAR_CANE,1))
                .fi(Water.getLiquid(100))
                .io(new ItemStack(Items.PAPER))
                .add(100,4);
        CHEMICAL_BATHING.RB()
                .ii(RecipeIngredient.of(Items.NETHER_STAR,1))
                .fi(Plutonium.getLiquid(1152))
                .io(new ItemStack(GregTechData.QuantumStat))
                .add(96*20,384);
        mercurybathing();
        persulfatebathing();
    }
    public static void mercurybathing(){
        CHEMBATH_MERCURY.getAll().forEach((main,side) ->
            CHEMICAL_BATHING.RB()
                    .ii(RecipeIngredient.of(CRUSHED.get(main),1))
                    .fi(Mercury.getLiquid(1000))
                    .io(new ItemStack(CRUSHED_PURIFIED.get(main)),new ItemStack(DUST.get(side)),new ItemStack(DUST.get(Stone)))
                    .add(40*20,8));
    }
    public static void persulfatebathing(){
        CHEMBATH_PERSULFATE.getAll().forEach((main,side) ->
                CHEMICAL_BATHING.RB()
                        .ii(RecipeIngredient.of(CRUSHED.get(main),1))
                        .fi(SodiumPersulfate.getLiquid(1000))
                        .io(new ItemStack(CRUSHED_PURIFIED.get(main)),new ItemStack(DUST.get(side)),new ItemStack(DUST.get(Stone)))
                        .add(40*20,8));
    }
}
