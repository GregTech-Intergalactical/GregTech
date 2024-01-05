package muramasa.gregtech.loader.machines;

import muramasa.gregtech.GregTechConfig;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.DEHYDRATOR;

public class Dehydrator {
    public static void init() {
        DEHYDRATOR.RB().ii(DUST.getMaterialIngredient(TungsticAcid, 7)).io(DUST.get(TungstenTrioxide, 4)).fo(DistilledWater.getLiquid(3000)).add("tungsten_trioxide", 300 * 20, 16);
        DEHYDRATOR.RB().fi(SaltWater.getFluidTag(1000)).io(DUST_SMALL.get(Salt, 1)).fo(DistilledWater.getLiquid(750)).add("salt_water_drying", 8 * 20, 16);
        if (GregTechConfig.HARDER_ALUMINIUM_PROCESSING.get()) {
            DEHYDRATOR.RB().ii(DUST.getMaterialIngredient(AluminiumHydroxide, 14)).io(DUST.get(Alumina, 5)).fo(DistilledWater.getLiquid(9000)).add("alumina", 200 * 20, 16);
        }
    }
}
