package muramasa.gregtech.loader.machines;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.GregTechConfig;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import static muramasa.antimatter.Ref.L;
import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterialTypes.GEM;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.gregtech.data.GregTechMaterialTags.CHEMBATH_MERCURY;
import static muramasa.gregtech.data.GregTechMaterialTags.CHEMBATH_PERSULFATE;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.BATHING;

public class Bath {
    public static void init() {
        BATHING.RB()
                .ii(RecipeIngredient.of(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Wood),1))
                .fi(AntimatterMaterials.Water.getLiquid(125))
                .io(new ItemStack(Items.PAPER))
                .add("paper",16);
        BATHING.RB()
                .ii(RecipeIngredient.of(Items.SUGAR_CANE,1))
                .fi(AntimatterMaterials.Water.getLiquid(125))
                .io(new ItemStack(Items.PAPER))
                .add("paper_1",16);
        BATHING.RB()
                .ii(RecipeIngredient.of(Items.NETHER_STAR,1))
                .fi(Radon.getGas(1250))
                .io(new ItemStack(GregTechData.QuantumStar))
                .add("quantum_star",96*20);
        BATHING.RB()
                .ii(GEM.getMaterialIngredient(Diamond, 1))
                .fi(Netherite.getLiquid(L/4))
                .io(GEM.get(NetherizedDiamond))
                .add("netherized_diamond_recipe",144);
        BATHING.RB().ii(DUST.getMaterialIngredient(Tungstate, 7)).fi(HydrochloricAcid.getLiquid(4000)).io(DUST.get(LithiumChloride, 4), DUST.get(TungsticAcid, 7)).add("tungstate", 512);
        BATHING.RB().ii(DUST.getMaterialIngredient(Scheelite, 6)).fi(HydrochloricAcid.getLiquid(4000)).io(DUST.get(CalciumChloride, 3), DUST.get(TungsticAcid, 7)).add("scheelite", 512);
        if (GregTechConfig.GAMEPLAY.HARDER_ALUMINIUM_PROCESSING){
            BATHING.RB().ii(DUST.getMaterialIngredient(SodiumAluminate, 4)).fi(Water.getLiquid(6000)).io(DUST.get(AluminiumHydroxide, 7), DUST.get(SodiumHydroxide, 3)).add("aluminium_hydroxide", 102 * 20);
            BATHING.RB().ii(DUST.getMaterialIngredient(SodiumAluminate, 4)).fi(DistilledWater.getLiquid(6000)).io(DUST.get(AluminiumHydroxide, 7), DUST.get(SodiumHydroxide, 3)).add("aluminium_hydroxide_distilled_water", 102 * 20);
        }
        mercurybathing();
        persulfatebathing();
    }
    public static void mercurybathing(){
        CHEMBATH_MERCURY.getAll().forEach((main,side) ->
            BATHING.RB()
                    .ii(RecipeIngredient.of(AntimatterMaterialTypes.CRUSHED.get(main),1))
                    .fi(Mercury.getLiquid(1000))
                    .io(new ItemStack(AntimatterMaterialTypes.CRUSHED_PURIFIED.get(main)),new ItemStack(AntimatterMaterialTypes.DUST.get(side)),new ItemStack(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Stone)))
                    .add("mercury_" + main.getId(),40*20));
    }
    public static void persulfatebathing(){
        CHEMBATH_PERSULFATE.getAll().forEach((main,side) ->
                BATHING.RB()
                        .ii(RecipeIngredient.of(AntimatterMaterialTypes.CRUSHED.get(main),1))
                        .fi(SodiumPersulfate.getLiquid(1000))
                        .io(new ItemStack(AntimatterMaterialTypes.CRUSHED_PURIFIED.get(main)),new ItemStack(AntimatterMaterialTypes.DUST.get(side)),new ItemStack(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Stone)))
                        .add("persulfate_"+main.getId(),40*20));
    }
}
