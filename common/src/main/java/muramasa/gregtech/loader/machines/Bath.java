package muramasa.gregtech.loader.machines;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.GregTechConfig;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import static muramasa.antimatter.Ref.L;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
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
        if (GregTechConfig.HARDER_ALUMINIUM_PROCESSING.get()){
            BATHING.RB().ii(DUST.getMaterialIngredient(SodiumAluminate, 4)).fi(Water.getLiquid(6000)).io(DUST.get(AluminiumHydroxide, 7), DUST.get(SodiumHydroxide, 3)).add("aluminium_hydroxide", 102 * 20);
            BATHING.RB().ii(DUST.getMaterialIngredient(SodiumAluminate, 4)).fi(DistilledWater.getLiquid(6000)).io(DUST.get(AluminiumHydroxide, 7), DUST.get(SodiumHydroxide, 3)).add("aluminium_hydroxide_distilled_water", 102 * 20);
        }
        BATHING.RB().ii(DUST.getMaterialIngredient(Magnesium, 2)).fi(TitaniumTetrachloride.getLiquid(5000)).io(DUST.get(Titanium, 1), DUST.get(MagnesiumChloride, 6)).add("titanium", 512);
        mercurybathing();
        persulfatebathing();
        addVitriolRecipe(Chalcopyrite, BlueVitriol, 256);
        addVitriolRecipe(Copper, BlueVitriol, 256);
        addVitriolRecipe(Gold, BlueVitriol, 256);
        addVitriolRecipe(Chalcopyrite, BlueVitriol, 256);
        addVitriolRecipe(Malachite, BlueVitriol, 256);
        addVitriolRecipe(Tetrahedrite, BlueVitriol, 256);
        addVitriolRecipe(Andradite, GreenVitriol, 256);
        addVitriolRecipe(Chromite, GreenVitriol, 256);
        addVitriolRecipe(Hematite, GreenVitriol, 256);
        addVitriolRecipe(Ilmenite, GreenVitriol, 256);
        BATHING.RB().ii(DUST.getMaterialIngredient(Ilmenite, 5)).fi(SulfuricAcid.getLiquid(7000)).io(DUST.get(Rutile)).fo(GreenVitriol.getLiquid(6000), Water.getLiquid(3000)).add("ilmenite_to_rutile");
        addVitriolRecipe(Magnetite, GreenVitriol, 256);
        addVitriolRecipe(Pyrite, GreenVitriol, 256);
        addVitriolRecipe(Cobaltite, RedVitriol, 256);
        addVitriolRecipe(Magnesite, PinkVitriol, 256);
        addVitriolRecipe(Olivine, PinkVitriol, 256);
        addVitriolRecipe(Pyrope, PinkVitriol, 256);
        addVitriolRecipe(Cooperite, CyanVitriol, 256);
        addVitriolRecipe(Garnierite, CyanVitriol, 256);
        addVitriolRecipe(Nickel, CyanVitriol, 256);
        addVitriolRecipe(Pentlandite, CyanVitriol, 256);
        addVitriolRecipe(Platinum, CyanVitriol, 256);
    }
    public static void mercurybathing(){
        CHEMBATH_MERCURY.getAll().forEach((main,side) ->
            BATHING.RB()
                    .ii(RecipeIngredient.of(AntimatterMaterialTypes.CRUSHED.get(main),1))
                    .fi(Mercury.getLiquid(1000))
                    .io(new ItemStack(AntimatterMaterialTypes.CRUSHED_PURIFIED.get(main)),new ItemStack(AntimatterMaterialTypes.DUST.get(side)),new ItemStack(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Stone)))
                    .chances(1.0, 0.7, 1.0)
                    .add("mercury_" + main.getId(),40*20));
    }
    public static void persulfatebathing(){
        CHEMBATH_PERSULFATE.getAll().forEach((main,side) ->
                BATHING.RB()
                        .ii(RecipeIngredient.of(AntimatterMaterialTypes.CRUSHED.get(main),1))
                        .fi(SodiumPersulfate.getLiquid(1000))
                        .io(new ItemStack(AntimatterMaterialTypes.CRUSHED_PURIFIED.get(main)),new ItemStack(AntimatterMaterialTypes.DUST.get(side)),new ItemStack(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Stone)))
                        .chances(1.0, 0.7, 1.0)
                        .add("persulfate_"+main.getId(),40*20));
    }

    private static void addVitriolRecipe(Material input, Material vitriol, int ticks){
        BATHING.RB().ii(CRUSHED_PURIFIED.getMaterialIngredient(input, 1))
                .fi(SulfuricAcid.getLiquid(3000))
                .fo(vitriol.getLiquid(3000), Hydrogen.getGas(1000))
                .io(CRUSHED_REFINED.get(input), CRUSHED_REFINED.get(input)).chances(1.0, 0.005).add(vitriol.getId() + "_from_" + input.getId(), ticks);
    }
}
