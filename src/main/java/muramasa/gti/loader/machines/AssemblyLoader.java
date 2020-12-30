package muramasa.gti.loader.machines;

import com.google.common.collect.ImmutableSet;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import muramasa.gti.data.GregTechData;
import muramasa.gti.data.Materials;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;

import java.util.Set;

import static muramasa.antimatter.Data.ROD;
import static muramasa.gti.data.GregTechData.*;
import static muramasa.gti.data.Materials.Rubber;
import static muramasa.gti.data.RecipeMaps.ASSEMBLING;

public class AssemblyLoader {
    public static void init() {
        AntimatterAPI.all(Wire.class,t -> {
            Cable<?> cable = AntimatterAPI.get(Cable.class, "cable" + "_" + t.getMaterial().getId());
            if (cable == null) return;
            ImmutableSet<PipeSize> sizes = t.getSizes();
            sizes.forEach(size -> {
                Item wireItem = t.getBlockItem(size);
                Item cableItem = cable.getBlockItem(size);
                ASSEMBLING.RB().ii(AntimatterIngredient.of(wireItem,1), INT_CIRCUITS.get(24)).fi(Rubber.getLiquid(size.getCableThickness()*16)).io(new ItemStack(cableItem,1)).add(size.getCableThickness()* 20L,8);
            });
        });

        ASSEMBLING.RB().ii(AntimatterIngredient.of(ItemTags.PLANKS,8), INT_CIRCUITS.get(8)).io(new ItemStack(Items.CHEST,1)).add(100,4);
        ASSEMBLING.RB().ii(AntimatterIngredient.of(WIRE_COPPER.getBlockItem(PipeSize.TINY),4), AntimatterIngredient.of(ROD.get(Materials.Iron),2)
        , AntimatterIngredient.of(CABLE_TIN.getBlockItem(PipeSize.TINY),2)).io(new ItemStack(MotorLV)).add(150,16);
    }
}
