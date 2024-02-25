package muramasa.gregtech.loader.machines;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

import static muramasa.gregtech.data.RecipeMaps.ORE_BYPRODUCTS;

public class OreByproducts {
    public static void init() {
        AntimatterMaterialTypes.CRUSHED.all().forEach(m -> {
            if (!m.has(AntimatterMaterialTypes.ORE)) return;
            if (!m.has(AntimatterMaterialTypes.INGOT)) return;
            RecipeIngredient ore = AntimatterMaterialTypes.ORE.getMaterialIngredient(m, 1), crushed = AntimatterMaterialTypes.CRUSHED.getIngredient(m, 1);
            if (m.hasByProducts()) {
                List<Material> byProducts = m.getByProducts();
                int byProductsCount = byProducts.size();

                List<Ingredient> ores = new ObjectArrayList<>();
                if (m.has(AntimatterMaterialTypes.ORE)) ores.add(ore);
                if (m.has(AntimatterMaterialTypes.BEARING_ROCK)) ores.add(AntimatterMaterialTypes.BEARING_ROCK.getIngredient(m, 1));
                if (m.has(AntimatterMaterialTypes.CRUSHED)) ores.add(crushed);
                if (m.has(AntimatterMaterialTypes.CRUSHED_PURIFIED)) ores.add(AntimatterMaterialTypes.CRUSHED_PURIFIED.getIngredient(m, 1));
                if (m.has(AntimatterMaterialTypes.CRUSHED_REFINED)) ores.add(AntimatterMaterialTypes.CRUSHED_REFINED.getIngredient(m, 1));


                List<ItemStack> dusts = new ObjectArrayList<>(byProductsCount);
                byProducts.forEach(p -> dusts.add(AntimatterMaterialTypes.DUST.get(p, 1)));
                ORE_BYPRODUCTS.RB().ii(ores).io(dusts.toArray(new ItemStack[byProductsCount])).add(m.getId() + "_byproducts");
            }
        });
    }
}
