package muramasa.gregtech.integration.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import muramasa.antimatter.material.Material;

import java.util.List;

public class OreProcessingDisplay implements Display {
    Material ore;

    public OreProcessingDisplay(Material material){
        this.ore = material;
    }

    public Material getOre() {
        return ore;
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return null;
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return null;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return null;
    }
}
