package muramasa.gregtech.material;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialItem;
import muramasa.antimatter.material.MaterialType;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.Utils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DoubleMaterialTypeItem<T> extends MaterialTypeItem<T> {
    protected final Map<Material, Set<Material>> materialMap = new Object2ObjectLinkedOpenHashMap<>();
    public DoubleMaterialTypeItem(String id, int layers, boolean visible, int unitValue) {
        super(id, layers, visible, unitValue, DoubleMaterialTypeItem::generateItems);
    }

    static void generateItems(String domain, MaterialType<?> type, Material material){
        if (type instanceof DoubleMaterialTypeItem<?> doubleTyep){
            Set<Material> materials = doubleTyep.materialMap.get(material);
            if (materials != null && !materials.isEmpty()){
                materials.forEach(m -> {
                    new DoubleMaterialItem(domain, type, material, m);
                });
            }
        }
    }

    public Item get(Material material, Material inner) {
        if (!allowItemGen(material))
            Utils.onInvalidData(String.join("", "GET ERROR - DOES NOT GENERATE: T(", id, ") M(", material.getId(), ", ", inner.getId(), ")"));
        return AntimatterAPI.get(DoubleMaterialItem.class, id + "_" + material.getId() + "_" + inner.getId());
    }

    public ItemStack get(Material material, Material inner, int count) {
        if (count < 1)
            Utils.onInvalidData(String.join("", "GET ERROR - MAT STACK EMPTY: T(", id, ") M(", material.getId(), ", ", inner.getId(), ")"));
        return new ItemStack(get(material), count);
    }

    public RecipeIngredient getIngredient(Material material, Material inner, int count) {
        if (count < 1)
            Utils.onInvalidData(String.join("", "GET ERROR - MAT STACK EMPTY: T(", id, ") M(", material.getId(), ")"));
        return RecipeIngredient.of(getMaterialTag(material), count);
    }

    public void add(Material main, Material... inner){
        if (!materials.contains(main)){
            super.add(main);
        }
        materialMap.computeIfAbsent(main, (m) -> new ObjectLinkedOpenHashSet<>());
        materialMap.get(main).addAll(List.of(inner));
    }

    public Item get(Material material) {
        return Items.AIR;
    }

    public ItemStack get(Material material, int count) {
        return ItemStack.EMPTY;
    }

    public RecipeIngredient getIngredient(Material material, int count) {
        return RecipeIngredient.of(Ingredient.EMPTY, 1);
    }
}
