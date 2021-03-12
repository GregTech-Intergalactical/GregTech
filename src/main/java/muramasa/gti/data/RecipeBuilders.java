package muramasa.gti.data;

import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.recipe.RecipeBuilder;
import muramasa.antimatter.recipe.RecipeMap;
import muramasa.antimatter.util.Utils;
import net.minecraft.item.ItemStack;

import java.util.Arrays;

public class RecipeBuilders {

    public static class SmeltingBuilder extends RecipeBuilder {
        @Override
        public Recipe add() {
            return addRecipeToSteamMap(RecipeMaps.STEAM_SMELTING, super.add());
        }
    }

    public static class PulverizingBuilder extends RecipeBuilder {
        @Override
        public Recipe add() {
            return addRecipeToSteamMap(RecipeMaps.STEAM_PULVERIZING, super.add());
        }
    }

    public static class ExtractingBuilder extends RecipeBuilder {
        @Override
        public Recipe add() {
            return addRecipeToSteamMap(RecipeMaps.STEAM_EXTRACTING, super.add());
        }
    }

    public static class HammeringBuilder extends RecipeBuilder {
        @Override
        public Recipe add() {
            return addRecipeToSteamMap(RecipeMaps.STEAM_HAMMERING, super.add());
        }
    }

    public static class CompressingBuilder extends RecipeBuilder {
        @Override
        public Recipe add() {
            return addRecipeToSteamMap(RecipeMaps.STEAM_COMPRESSING, super.add());
        }
    }

    public static class AlloySmeltingBuilder extends RecipeBuilder {
        @Override
        public Recipe add() {
            return addRecipeToSteamMap(RecipeMaps.STEAM_ALLOY_SMELTING, super.add());
        }
    }

    public static Recipe addRecipeToSteamMap(RecipeMap map, Recipe recipe) {
        try {
            if (recipe.getPower() <= Tier.LV.getVoltage()/2) {
                map.RB().ii(recipe.getInputItems()).fi(Materials.Steam.getGas((int)(recipe.getPower() * 2))).io(recipe.getOutputItems()).add(recipe.getDuration(), recipe.getPower() * 2, recipe.getTotalPower() * 2);
            }
        } catch (Exception e) {
            System.out.println("bleh");
        }
        return recipe;
    }

    public static class BasicBlastingBuilder extends RecipeBuilder {

        public static ItemStack[] FUELS = new ItemStack[0];

        static {
//            AntimatterAPI.onEvent(RegistrationEvent.DATA_READY, () -> FUELS = new ItemStack[] {
//                MaterialType.GEM.get(Materials.Coal, 1),
//                MaterialType.DUST.get(Materials.Coal, 1),
//                MaterialType.GEM.get(Materials.Charcoal, 1),
//                MaterialType.DUST.get(Materials.Charcoal, 1),
//                MaterialType.GEM.get(Materials.CoalCoke, 1),
//                MaterialType.GEM.get(Materials.LigniteCoke, 1),
//            });
        }

        public void add(ItemStack[] inputs, ItemStack[] outputs, int coal, int duration) {
            duration = 20;//TODO temp
            ItemStack[] inputsCpy = Arrays.copyOf(inputs, inputs.length + 1);
            for (int i = 0; i < FUELS.length; i++) {
                inputsCpy[inputsCpy.length - 1] = Utils.ca(coal, FUELS[i]);
                //ii(AntimatterIngredient.fromStacksList(inputsCpy)).io(outputs).add(duration);
            }
        }
    }
}
