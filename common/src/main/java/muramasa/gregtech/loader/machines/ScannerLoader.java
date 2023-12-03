package muramasa.gregtech.loader.machines;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.RecipeMaps;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ScannerLoader {
    public static void init(){
        ItemStack rawDataStick = new ItemStack(GregTechData.DataStick);
        CompoundTag display = rawDataStick.getOrCreateTagElement("display");
        CompoundTag name = new CompoundTag();
        name.putString("text", "Raw Prospection Data");
        display.put("Name", name);
        ItemStack analyzedDataStick = new ItemStack(GregTechData.DataStick);
        display = analyzedDataStick.getOrCreateTagElement("display");
        name = new CompoundTag();
        name.putString("text", "Analyzed Prospection Data");
        display.put("Name", name);
        RecipeMaps.SCANNING.RB().ii(RecipeIngredient.of(rawDataStick)).io(analyzedDataStick).fake().add("prospection_data_stick", 1000, 32);
        ItemStack emptyDataStick = new ItemStack(GregTechData.DataStick);
        display = emptyDataStick.getOrCreateTagElement("display");
        name = new CompoundTag();
        name.putString("text", "Stick to save it to");
        display.put("Name", name);
        ItemStack outputDataStick = new ItemStack(GregTechData.DataStick);
        display = outputDataStick.getOrCreateTagElement("display");
        name = new CompoundTag();
        name.putString("text", "Scanned Book Data");
        display.put("Name", name);
        RecipeMaps.SCANNING.RB().ii(RecipeIngredient.of(Items.WRITTEN_BOOK), RecipeIngredient.of(emptyDataStick)).io(outputDataStick).fake().add("book_data_stick", 128, 32);
    }
}
