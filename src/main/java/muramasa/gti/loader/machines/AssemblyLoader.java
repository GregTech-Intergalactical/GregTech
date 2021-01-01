package muramasa.gti.loader.machines;

import com.google.common.collect.ImmutableSet;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.AntimatterIngredient;
import muramasa.gti.block.BlockCasing;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;

import java.util.Arrays;
import java.util.Set;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.Data.WRENCH;
import static muramasa.gti.data.GregTechData.*;
import static muramasa.gti.data.Materials.*;
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
        Arrays.stream(Tier.getStandard()).forEach(t -> {
            Material magnet = (t == Tier.ULV || t == Tier.LV) ? IronMagnetic : (t == Tier.EV || t == Tier.IV ? NeodymiumMagnetic : SteelMagnetic);
            ASSEMBLING.RB().fi(Plastic.getLiquid(288)).ii(AntimatterIngredient.of(AntimatterAPI.get(BlockCasing.class, "casing_"+t.getId()).asItem(),1)
                    , AntimatterIngredient.of(TIER_CABLES.get(t),2)).io(new ItemStack(AntimatterAPI.get(BlockCasing.class, "casing_"+t.getId()).asItem(),1)).add(40,8);
            ASSEMBLING.RB().ii(AntimatterIngredient.of(TIER_WIRES.get(t),4), AntimatterIngredient.of(ROD.get(TIER_MATERIALS.get(t)),2),
                    AntimatterIngredient.of(ROD.get(magnet),1)
                    , AntimatterIngredient.of(TIER_CABLES.get(t),2)).io(new ItemStack(AntimatterAPI.get(ItemBasic.class,"motor_"+t.getId()))).add(150,16);

            ASSEMBLING.RB().ii(AntimatterIngredient.of(TIER_CABLES.get(t),2),
                    AntimatterIngredient.of(ROD.get(TIER_MATERIALS.get(t)),2),
                    AntimatterIngredient.of(PLATE.get(TIER_MATERIALS.get(t)),3),
                    AntimatterIngredient.of(AntimatterAPI.get(ItemBasic.class,"motor_"+t.getId()),1),
                    AntimatterIngredient.of(GEAR.get(TIER_MATERIALS.get(t)),1))
                    .io(new ItemStack(AntimatterAPI.get(ItemBasic.class,"piston_"+t.getId())))
                    .add(150,16);
        });
    }
}
