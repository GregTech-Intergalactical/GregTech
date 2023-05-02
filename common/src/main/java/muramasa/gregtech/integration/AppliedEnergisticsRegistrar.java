package muramasa.gregtech.integration;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.registration.IAntimatterRegistrar;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.data.RecipeMaps;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.Materials.*;

public class AppliedEnergisticsRegistrar implements IAntimatterRegistrar {

    public AppliedEnergisticsRegistrar(){
        onRegistrarInit();
    }

    @Override
    public String getId() {
        return Ref.MOD_AE;
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {
        if (event == RegistrationEvent.DATA_INIT){
            GEM.replacement(CertusQuartz, () -> getAe2Item("certus_quartz_crystal"));
            GEM.replacement(ChargedCertusQuartz, () -> getAe2Item("charged_certus_quartz_crystal"));
            GEM.replacement(Fluix, () -> getAe2Item("fluix_crystal"));
            DUST.replacement(CertusQuartz, () -> getAe2Item("certus_quartz_dust"));
            DUST.replacement(Fluix, () -> getAe2Item("fluix_dust"));
        }
    }

    @Override
    public boolean isEnabled() {
        return AntimatterAPI.isModLoaded(getId());
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void onRegistrarInit() {
        AntimatterAPI.addRegistrar(this);
    }

    public static void machineRecipes(){
        RecipeMaps.PRESSING.RB().ii(GEM.getMaterialIngredient(CertusQuartz, 1), of(getAe2Item("calculation_processor_press"))).io(new ItemStack(getAe2Item("printed_calculation_processor"))).add("printed_calculation_processor", 200, 16);
        RecipeMaps.PRESSING.RB().ii(GEM.getMaterialIngredient(Diamond, 1), of(getAe2Item("engineering_processor_press"))).io(new ItemStack(getAe2Item("printed_engineering_processor"))).add("printed_engineering_processor", 200, 16);
        RecipeMaps.PRESSING.RB().ii(PLATE.getMaterialIngredient(Gold, 1), of(getAe2Item("logic_processor_press"))).io(new ItemStack(getAe2Item("printed_logic_processor"))).add("printed_logic_processor", 200, 16);
        RecipeMaps.PRESSING.RB().ii(PLATE.getMaterialIngredient(Silicon, 1), of(getAe2Item("silicon_press"))).io(new ItemStack(getAe2Item("printed_silicon"))).add("printed_silicon", 200, 16);
        RecipeMaps.CENTRIFUGING.RB().ii(of(getAe2Item("sky_dust")))
                .io(/*DUST_SMALL.get(BasalticMineralSand, 1), */DUST_SMALL.get(Olivine, 1), DUST_SMALL.get(Obsidian, 1), DUST_SMALL.get(Basalt, 1), DUST_SMALL.get(Flint, 1),DUST_SMALL.get(RareEarth, 1))
                .chances(0.2,0.2,0.2,0.2,0.2)
                .add("sky_dust", 64, 20);
        RecipeMaps.AUTOCLAVING.RB().ii(of(getAe2Item("certus_crystal_seed"), 1).setIgnoreNbt()).fi(Water.getLiquid(200)).io(GEM.get(CertusQuartz, 1)).add("certus_quartz_from_seed", 2000, 24);
        RecipeMaps.AUTOCLAVING.RB().ii(of(getAe2Item("fluix_crystal_seed"), 1).setIgnoreNbt()).fi(Water.getLiquid(200)).io(GEM.get(Fluix, 1)).add("fluix_from_seed", 2000, 24);
        RecipeMaps.AUTOCLAVING.RB().ii(of(getAe2Item("certus_crystal_seed"), 1).setIgnoreNbt()).fi(DistilledWater.getLiquid(200)).io(GEM.get(CertusQuartz, 1)).add("certus_quartz_from_seed_2", 1000, 24);
        RecipeMaps.AUTOCLAVING.RB().ii(of(getAe2Item("fluix_crystal_seed"), 1).setIgnoreNbt()).fi(DistilledWater.getLiquid(200)).io(GEM.get(Fluix, 1)).add("fluix_from_seed_2", 1000, 24);
        RecipeMaps.MIXING.RB().ii(GEM.getIngredient(ChargedCertusQuartz, 1), DUST.getMaterialIngredient(Redstone, 1), GEM.getMaterialIngredient(Quartz, 1)).fi(Water.getLiquid(500)).io(DUST.get(Fluix, 2)).add("fluix_crystal", 20, 16);
        RecipeMaps.MIXING.RB().ii(GEM.getIngredient(ChargedCertusQuartz, 1), DUST.getMaterialIngredient(Redstone, 1), GEM.getMaterialIngredient(Quartz, 1)).fi(DistilledWater.getLiquid(500)).io(DUST.get(Fluix, 2)).add("fluix_crystal_2", 20, 16);
        RecipeMaps.ASSEMBLING.RB().ii(of(getAe2Item("printed_logic_processor")), of(getAe2Item("printed_silicon"))).fi(Redstone.getLiquid(AntimatterPlatformUtils.isForge() ? 144L : 9000L)).io(new ItemStack(getAe2Item("logic_processor"))).add("logic_processor", 64, 32);
        RecipeMaps.ASSEMBLING.RB().ii(of(getAe2Item("printed_engineering_processor")), of(getAe2Item("printed_silicon"))).fi(Redstone.getLiquid(AntimatterPlatformUtils.isForge() ? 144L : 9000L)).io(new ItemStack(getAe2Item("engineering_processor"))).add("engineering_processor", 64, 32);
        RecipeMaps.ASSEMBLING.RB().ii(of(getAe2Item("printed_calculation_processor")), of(getAe2Item("printed_silicon"))).fi(Redstone.getLiquid(AntimatterPlatformUtils.isForge() ? 144L : 9000L)).io(new ItemStack(getAe2Item("calculation_processor"))).add("calculation_processor", 64, 32);
        RecipeMaps.ASSEMBLING.RB().ii(DUST.getMaterialIngredient(CertusQuartz, 1), of(ItemTags.SAND)).io(new ItemStack(getAe2Item("certus_crystal_seed"))).add("certus_crystal_seed", 64, 8);
        RecipeMaps.ASSEMBLING.RB().ii(DUST.getMaterialIngredient(Fluix, 1), of(ItemTags.SAND)).io(new ItemStack(getAe2Item("fluix_seed"))).add("fluix_seed", 64, 8);
        RecipeMaps.MACERATING.RB().ii(of(getAe2Item("sky_stone_chest"))).io(new ItemStack(getAe2Item("sky_dust"), 8)).add("sky_dust_from_chest", 400, 2);
        RecipeMaps.MACERATING.RB().ii(of(getAe2Item("sky_stone_block"))).io(new ItemStack(getAe2Item("sky_dust"))).add("sky_dust", 400, 2);
    }

    private static Item getAe2Item(String id){
        return AntimatterPlatformUtils.getItemFromID(Ref.MOD_AE, id);
    }
}
