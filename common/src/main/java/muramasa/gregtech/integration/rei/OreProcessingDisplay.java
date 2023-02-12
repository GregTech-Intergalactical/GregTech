package muramasa.gregtech.integration.rei;

import com.ibm.icu.text.ListFormatter;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import me.shedaniel.rei.api.common.util.EntryStacks;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.map.RecipeMap;
import muramasa.gregtech.data.GregTechMaterialTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OreProcessingDisplay implements Display {
    Material ore;
    IRecipe[] recipes;
    private final List<EntryIngredient> input, output;
    SepMode sepMode;
    BathingMode bathingMode;
    Material byProduct1, byProduct2, byProduct3, byProduct4;

    public OreProcessingDisplay(Material material, BathingMode bathingMode){

        this.ore = material;
        this.bathingMode = bathingMode;
        this.sepMode = material.has(GregTechMaterialTags.ELECTROMAGNETIC_SEPARATOR_IRON) ? SepMode.IRON : material.has(GregTechMaterialTags.ELECTROMAGNETIC_SEPARATOR_GOLD) ? SepMode.GOLD : material.has(GregTechMaterialTags.ELECTROMAGNETIC_SEPARATOR_NEODYMIUM) ? SepMode.NEODYMIUM : SepMode.NONE;
        //this.recipes = recipes;
        this.input = createInputEntries(List.of(AntimatterMaterialTypes.ORE.getMaterialIngredient(material, 1)));
        Material aOreByProduct1 = ore.getByProducts().size() >= 1 ? ore.getByProducts().get(0) : MaterialTags.MACERATE_INTO.getMapping(ore);
        Material aOreByProduct2 = ore.getByProducts().size() >= 2 ? ore.getByProducts().get(1) : aOreByProduct1;
        Material aOreByProduct3 = ore.getByProducts().size() >= 3 ? ore.getByProducts().get(2) : aOreByProduct2;
        byProduct1 = aOreByProduct1;
        byProduct2 = aOreByProduct2;
        byProduct3 = aOreByProduct3;
        this.output = List.of(EntryIngredient.of(EntryStack.of(VanillaEntryTypes.ITEM, AntimatterMaterialTypes.DUST.get(material, 1)), EntryStack.of(VanillaEntryTypes.ITEM, AntimatterMaterialTypes.DUST.get(byProduct1, 1)), EntryStack.of(VanillaEntryTypes.ITEM, AntimatterMaterialTypes.DUST.get(byProduct2, 1)), EntryStack.of(VanillaEntryTypes.ITEM, AntimatterMaterialTypes.DUST.get(byProduct3, 1))));
    }

    public Material getOre() {
        return ore;
    }

    public static List<EntryIngredient> createInputEntries(List<Ingredient> input) {
        return input.stream().map(i -> {
            return Arrays.stream(i.getItems()).map(EntryStacks::of).toList();
        }).map(EntryIngredient::of).toList();
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return input;
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return output;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return OreProcessingCategory.id;
    }

    public enum SepMode {
        NONE,
        IRON,
        GOLD,
        NEODYMIUM
    }

    public enum BathingMode {
        NONE,
        MERCURY,
        PERSULFATE
    }
}
