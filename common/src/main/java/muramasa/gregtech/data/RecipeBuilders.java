package muramasa.gregtech.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.antimatter.recipe.map.RecipeMap;
import muramasa.antimatter.registration.RegistrationEvent;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterialTypes.GEM;

public class RecipeBuilders {

    public static class SmeltingBuilder extends RecipeBuilder {
        @Override
        public IRecipe add(String domain, String id) {
            return addRecipeToSteamMap(RecipeMaps.STEAM_SMELTING, super.add(domain, id), domain, id);
        }
    }

    public static class MaceratingBuilder extends RecipeBuilder {
        @Override
        public IRecipe add(String domain, String id) {
            return addRecipeToSteamMap(RecipeMaps.STEAM_MACERATING, super.add(domain, id), domain, id);
        }
    }

    public static class ExtractingBuilder extends RecipeBuilder {
        @Override
        public IRecipe add(String domain, String id) {
            return addRecipeToSteamMap(RecipeMaps.STEAM_EXTRACTING, super.add(domain, id), domain, id);
        }
    }

    public static class HammeringBuilder extends RecipeBuilder {
        @Override
        public IRecipe add(String domain, String id) {
            return addRecipeToSteamMap(RecipeMaps.STEAM_HAMMERING, super.add(domain, id), domain, id);
        }
    }

    public static class CompressingBuilder extends RecipeBuilder {
        @Override
        public IRecipe add(String domain, String id) {
            return addRecipeToSteamMap(RecipeMaps.STEAM_COMPRESSING, super.add(domain, id), domain, id);
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
        public IRecipe add(String domain, String id) {
            return addRecipeToSteamMap(RecipeMaps.STEAM_ALLOY_SMELTING, super.add(domain, id), domain, id);
        }
    }

    public static IRecipe addRecipeToSteamMap(RecipeMap map, IRecipe recipe, String domain, String id) {
        try {
            if (recipe.getPower() > 0 && (recipe.getPower()-1)*2 <= Tier.LV.getVoltage()) {
                map.RB().ii(recipe.getInputItems()).io(recipe.getFlatOutputItems()).add(domain, id,recipe.getDuration()* 3L, recipe.getPower() * 2, 0, 1);
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
                    GEM.get(AntimatterMaterials.Charcoal, 1),
                    DUST.get(AntimatterMaterials.Charcoal, 1),
                    GEM.get(Materials.CoalCoke, 1),
                    GEM.get(Materials.LigniteCoke, 1),
            });
        }

        @Override
        public IRecipe add(String domain, String id) {
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
            return super.add(domain, id);
        }
    }
}
