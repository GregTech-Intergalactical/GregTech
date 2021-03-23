package muramasa.gti.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialItem;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.recipe.RecipeBuilder;
import muramasa.antimatter.recipe.RecipeMap;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.util.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.LazyValue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static muramasa.antimatter.Data.*;

public class RecipeBuilders {

    public static class SmeltingBuilder extends RecipeBuilder {
        @Override
        public Recipe build(int duration, long power, int special, int amps) {
            Recipe r = super.build(duration, power, special, amps);
            addRecipeToSteamMap(RecipeMaps.STEAM_SMELTING, r);
            return r;
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

    public static class BlastingBuilder extends RecipeBuilder {
        public BlastingBuilder temperature(int temperature) {
            this.special = temperature;
            return this;
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
                map.RB().ii(recipe.getInputItems()).io(recipe.getFlatOutputItems()).add(recipe.getDuration()* 3L, recipe.getPower() * 2);
            }
        } catch (Exception e) {
            System.out.println("bleh");
        }
        return recipe;
    }

    public static class BasicBlastingBuilder extends RecipeBuilder {

        public static ItemStack[] FUELS = new ItemStack[0];

        static {
            AntimatterAPI.runOnEvent(RegistrationEvent.DATA_READY, () -> FUELS = new ItemStack[]{
                    GEM.get(Materials.Coal, 1),
                    DUST.get(Materials.Coal, 1),
                    GEM.get(Materials.Charcoal, 1),
                    DUST.get(Materials.Charcoal, 1),
                    GEM.get(Materials.CoalCoke, 1),
                    GEM.get(Materials.LigniteCoke, 1),
            });
        }

        @Override
        public Recipe add() {
            Recipe r = null;
            List<LazyValue<AntimatterIngredient>> ings = this.ingredientInput;
            for (ItemStack fuel : FUELS) {
                int burn = 1000;//fuel.getBurnTime();
                int amount = (int) (((double)duration / (double)burn) * 10);
                List<LazyValue<AntimatterIngredient>> newList = new ObjectArrayList<>(ings);
                newList.add(AntimatterIngredient.of(fuel.getItem(), amount));
                this.ingredientInput = newList;
                r = build(duration, power, special, amps);
                addToMap(r);
            }
            return r;
        }
    }
}
