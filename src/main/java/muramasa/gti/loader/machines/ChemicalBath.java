package muramasa.gti.loader.machines;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gti.data.GregTechData;
import muramasa.gti.data.Materials;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import static muramasa.antimatter.Data.*;
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
        mercurybathing(Chalcopyrite,Gold);
        mercurybathing(Gold,Nickel);
        mercurybathing(Silver,Sulfur);
        mercurybathing(Osmium,Iridium);
        mercurybathing(Platinum,Iridium);
        persulfatebathing(Nickel,Iron);
        persulfatebathing(Cobalt,Cobaltite);
        persulfatebathing(Sphalerite,Zinc);
        persulfatebathing(Tetrahedrite,Zinc);
        persulfatebathing(Zinc,Gallium);
        persulfatebathing(Copper,Nickel);
        persulfatebathing(Cobaltite,Cobalt);
    }
    public static void mercurybathing(Material mat, Material mat2){
        CHEMICAL_BATHING.RB()
                .ii(RecipeIngredient.of(CRUSHED.get(mat),1))
                .fi(Mercury.getLiquid(1000))
                .io(new ItemStack(CRUSHED_PURIFIED.get(mat)),new ItemStack(DUST.get(Stone)),new ItemStack(DUST.get(mat2)))
                .add(40*20,8);
    }
    public static void persulfatebathing(Material mat, Material mat2){
        CHEMICAL_BATHING.RB()
                .ii(RecipeIngredient.of(CRUSHED.get(mat),1))
                .fi(SodiumPersulfate.getLiquid(100))
                .io(new ItemStack(CRUSHED_PURIFIED.get(mat)),new ItemStack(DUST.get(Stone)),new ItemStack(DUST.get(mat2)))
                .add(40*20,8);
    }
}
