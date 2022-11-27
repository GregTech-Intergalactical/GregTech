package muramasa.gregtech.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.antimatter.recipe.map.RecipeMap;
import muramasa.antimatter.registration.RegistrationEvent;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterialTypes.GEM;

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
            if (recipe.getPower() > 0 && (recipe.getPower()-1)*2 <= Tier.LV.getVoltage()) {
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
                    GEM.get(AntimatterMaterials.Coal, 1),
                    DUST.get(AntimatterMaterials.Coal, 1),
                    GEM.get(Materials.Charcoal, 1),
                    DUST.get(Materials.Charcoal, 1),
                    GEM.get(Materials.CoalCoke, 1),
                    GEM.get(Materials.LigniteCoke, 1),
            });
        }

        @Override
        public Recipe add() {
          /*  Recipe r = null;
            List<RecipeIngredient> ings = this.ingredientInput;
            for (ItemStack fuel : FUELS) {
                int burn = 1000;//fuel.getBurnTime();
                int amount = (int) (((double)duration / (double)burn) * 10);
                List<RecipeIngredient> newList = new ObjectArrayList<>(ings);
                newList.add(RecipeIngredient.of(fuel.getItem(), amount));
                this.ingredientInput = newList;
                r = build(duration, power, special, amps);
                addToMap(r);
            }
            return r;*/
            return super.add();
        }
    }
}
