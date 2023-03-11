package muramasa.gregtech.material;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialItem;
import muramasa.antimatter.material.MaterialType;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.registration.RegistryType;
import muramasa.antimatter.util.Utils;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DoubleMaterialTypeItem<T> extends MaterialType<T> {

    public interface ItemSupplier {
        void createItems(String domain, MaterialType<?> type, Material material, Material inner);
    }

    private final ItemSupplier itemSupplier;
    protected final Map<Material, Set<Material>> materialMap = new Object2ObjectLinkedOpenHashMap<>();
    public DoubleMaterialTypeItem(String id, int layers, boolean visible, int unitValue) {
        super(id, layers, visible, unitValue);
        itemSupplier = DoubleMaterialItem::new;
    }

    public ItemSupplier getSupplier() {
        return itemSupplier;
    }

    public Item get(Material material, Material inner) {
        if (!allowItemGen(material, inner))
            Utils.onInvalidData(String.join("", "GET ERROR - DOES NOT GENERATE: T(", id, ") M(", material.getId(), ", ", inner.getId(), ")"));
        return AntimatterAPI.get(DoubleMaterialItem.class, id + "_" + material.getId() + "_" + inner.getId());
    }

    public ItemStack get(Material material, Material inner, int count) {
        if (count < 1)
            Utils.onInvalidData(String.join("", "GET ERROR - MAT STACK EMPTY: T(", id, ") M(", material.getId(), ", ", inner.getId(), ")"));
        return new ItemStack(get(material, inner), count);
    }

    public RecipeIngredient getIngredient(Material material, Material inner, int count) {
        if (count < 1)
            Utils.onInvalidData(String.join("", "GET ERROR - MAT STACK EMPTY: T(", id, ") M(", material.getId(), ")"));
        return RecipeIngredient.of(getMaterialTag(material, inner), count);
    }

    @SuppressWarnings("unchecked")
    public TagKey<Item> getMaterialTag(Material m, Material i) {
        return (TagKey<Item>) tagFromString(String.join("", Utils.getConventionalMaterialType(this), "/", m.getId(), "_", i.getId()));
    }

    public boolean allowItemGen(Material material, Material inner) {
        return allowGen(material) && materialMap.get(material) != null && materialMap.get(material).contains(inner) && !blockType;
    }

    public boolean allowGen(Material material) {
        return generating && materials.contains(material);
    }

    public void add(Material main, Material... inner){
        if (!materials.contains(main)){
            super.add(main);
        }
        materialMap.computeIfAbsent(main, (m) -> new ObjectLinkedOpenHashSet<>());
        materialMap.get(main).addAll(List.of(inner));
    }

    @Override
    public void onRegistryBuild(RegistryType registry) {
        super.onRegistryBuild(registry);
        if (doRegister()) {
            for (Material material : this.materials) {
                if (!material.enabled) continue;
                Set<Material> materials = materialMap.get(material);
                if (materials != null && !materials.isEmpty()){
                    materials.forEach(m -> {
                        if (!m.enabled) return;
                        if (allowItemGen(material, m)) getSupplier().createItems(material.materialDomain(), this, material, m);
                    });
                }
            }
        }
    }
}
