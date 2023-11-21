package muramasa.gregtech.loader.machines;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.GregTechConfig;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.integration.SpaceModRegistrar;
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
        addVitriolRecipe(Chalcopyrite, BlueVitriol);
        addVitriolRecipe(Copper, BlueVitriol);
        addVitriolRecipe(Gold, BlueVitriol);
        addVitriolRecipe(Malachite, BlueVitriol);
        addVitriolRecipe(Tetrahedrite, BlueVitriol);
        addVitriolRecipe(Andradite, GreenVitriol);
        addVitriolRecipe(Chromite, GreenVitriol);
        addVitriolRecipe(Hematite, GreenVitriol);
        addVitriolRecipe(Ilmenite, GreenVitriol);
        BATHING.RB().ii(DUST.getMaterialIngredient(Ilmenite, 5)).fi(SulfuricAcid.getLiquid(7000)).io(DUST.get(Rutile)).fo(GreenVitriol.getLiquid(6000), Water.getLiquid(3000)).add("ilmenite_to_rutile", 512);
        addVitriolRecipe(Magnetite, GreenVitriol);
        addVitriolRecipe(Pyrite, GreenVitriol);
        addVitriolRecipe(Cobaltite, RedVitriol);
        addVitriolRecipe(Magnesite, PinkVitriol);
        addVitriolRecipe(Olivine, PinkVitriol);
        addVitriolRecipe(Pyrope, PinkVitriol);
        addVitriolRecipe(Sheldonite, CyanVitriol);
        addVitriolRecipe(Garnierite, CyanVitriol);
        addVitriolRecipe(Nickel, CyanVitriol);
        addVitriolRecipe(Pentlandite, CyanVitriol);
        addVitriolRecipe(Platinum, CyanVitriol);
        addVitriolRecipe(Sphalerite, WhiteVitriol);
        addVitriolRecipe(Tin, WhiteVitriol);
        addVitriolRecipe(Zinc, WhiteVitriol);
        addVitriolRecipe(Manganese, GrayVitriol);
        addVitriolRecipe(Pyrolusite, GrayVitriol);
        addVitriolRecipe(Scheelite, GrayVitriol);
        addVitriolRecipe(Spessartine, GrayVitriol);
        addVitriolRecipe(Tungstate, GrayVitriol);
        addVitriolRecipe(Almandine, VitriolOfClay);
        addVitriolRecipe(Bauxite, VitriolOfClay);
        if (SpaceModRegistrar.Desh != null){
            addVitriolRecipe(SpaceModRegistrar.Desh, MartianVitriol);
        }
        addPSGRecipe(Sheldonite);
        addPSGRecipe(Iridium);
        addPSGRecipe(Nickel);
        addPSGRecipe(Osmium);
        addPSGRecipe(Palladium);
        addPSGRecipe(Platinum);
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

    private static void addVitriolRecipe(Material input, Material vitriol){
        BATHING.RB().ii(CRUSHED_PURIFIED.getMaterialIngredient(input, 1))
                .fi(SulfuricAcid.getLiquid(vitriol == VitriolOfClay ? 10500 : 3000))
                .fo(vitriol.getLiquid(vitriol == VitriolOfClay ? 8500 : 3000), Hydrogen.getGas(vitriol == VitriolOfClay ? 3000 : 1000))
                .io(CRUSHED_REFINED.get(input, 1), DUST_SMALL.get(input, 2)).add(vitriol.getId() + "_from_" + input.getId(), 256);
    }

    private static void addPSGRecipe(Material input){
        BATHING.RB().ii(CRUSHED_PURIFIED.getMaterialIngredient(input, 1))
                .fi(AquaRegia.getLiquid(9750))
                //.fo(ChloroplatnicAcid.getLiquid(4500), NitrogenMonoxide.getGas(1500), Water.getLiquid(4125))
                .io(CRUSHED_REFINED.get(input, 1), DUST_TINY.get(PlatinumGroupSludge, 1)).add( "psg_from_" + input.getId(), 256);
    }
}
