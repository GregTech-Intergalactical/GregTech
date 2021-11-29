package muramasa.gti.loader.machines;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gti.GregTech;
import muramasa.gti.block.BlockCasing;
import muramasa.gti.data.GregTechData;
import net.minecraft.block.Block;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;

import java.util.Arrays;
import java.util.function.Consumer;

import static muramasa.antimatter.Data.*;
import static muramasa.gti.data.GregTechData.*;
import static muramasa.gti.data.Materials.*;
import static muramasa.gti.data.RecipeMaps.ASSEMBLING;
import static muramasa.gti.data.TierMaps.*;


public class AssemblyLoader {
    public static void init() {
        AntimatterAPI.all(Wire.class,t -> {
            Cable<?> cable = AntimatterAPI.get(Cable.class, "cable" + "_" + t.getMaterial().getId());
            if (cable == null) return;
            ImmutableSet<PipeSize> sizes = t.getSizes();
            sizes.forEach(size -> {
                Item wireItem = t.getBlockItem(size);
                Item cableItem = cable.getBlockItem(size);
                ASSEMBLING.RB().ii(RecipeIngredient.of(wireItem,1), INT_CIRCUITS.get(24)).fi(Rubber.getLiquid(size.getCableThickness()*16)).io(new ItemStack(cableItem,1)).add(size.getCableThickness()* 20L,8);
            });
        });

        ASSEMBLING.RB().ii(RecipeIngredient.of(ItemTags.PLANKS,8), INT_CIRCUITS.get(8)).io(new ItemStack(Items.CHEST,1)).add(100,4);
        Arrays.stream(Tier.getStandard()).forEach(t -> {
            Material magnet = (t == Tier.ULV || t == Tier.LV) ? IronMagnetic : (t == Tier.EV || t == Tier.IV ? NeodymiumMagnetic : SteelMagnetic);
            ASSEMBLING.RB().ii(RecipeIngredient.of(TIER_WIRES.get(t),4), RecipeIngredient.of(ROD.get(TIER_MATERIALS.get(t)),2),
                    RecipeIngredient.of(ROD.get(magnet),1)
                    , RecipeIngredient.of(TIER_CABLES.get(t),2)).io(new ItemStack(GregTech.get(ItemBasic.class,"motor_"+t.getId()))).add(150,16);

            ASSEMBLING.RB().ii(RecipeIngredient.of(TIER_CABLES.get(t),2),
                    RecipeIngredient.of(ROD.get(TIER_MATERIALS.get(t)),2),
                    RecipeIngredient.of(PLATE.get(TIER_MATERIALS.get(t)),3),
                    RecipeIngredient.of(GregTech.get(ItemBasic.class,"motor_"+t.getId()),1),
                    RecipeIngredient.of(GEAR.get(TIER_MATERIALS.get(t)),1))
                    .io(new ItemStack(GregTech.get(ItemBasic.class,"piston_"+t.getId())))
                    .add(150,16);
        });
        casings();
    }
    public static void casings() {
        addTierCasing(WroughtIron, Lead, GregTechData.CASING_ULV, 1);
        addTierCasing(Steel, Copper, GregTechData.CASING_LV, 2);
        addTierCasing(Aluminium, StainlessSteel, GregTechData.CASING_MV, 3);
        addTierCasing(TungstenSteel, TungstenCarbide, GregTechData.CASING_HV, 4);
        addTierCasing(Ultimet, HSSG, GregTechData.CASING_EV, 5);
        addTierCasing(HSSE, HSSS, GregTechData.CASING_IV, 6);
        //addTierCasing(TungstenSteel, GregTechData.CASING_LUV);
        //addTierCasing(Osmiridium, GregTechData.CASING_ZPM);
        //addTierCasing(Ultimet, GregTechData.CASING_UV);
        //addTierCasing(RedSteel, GregTechData.CASING_MAX);

        addTierHull(Iron, WIRE_RED_ALLOY, CircuitBasic, GregTechData.CASING_ULV, GregTechData.HULL_ULV, 1);
        addTierHull(Iron, WIRE_RED_ALLOY, CircuitBasicElectronic, GregTechData.CASING_ULV, GregTechData.HULL_ULV, 1);
        addTierHull(AnnealedCopper, WIRE_TIN, CircuitGood, GregTechData.CASING_LV, GregTechData.HULL_LV, 2);
        addTierHull(Silver, WIRE_CUPRONICKEL, CircuitAdv, GregTechData.CASING_MV, GregTechData.HULL_MV, 3);
        addTierHull(SterlingSilver, WIRE_ELECTRUM, CircuitNanoProcessor, GregTechData.CASING_HV, GregTechData.HULL_HV, 4);
        addTierHull(RoseGold, WIRE_NICHROME, CircuitQuantumProcessor, GregTechData.CASING_EV, GregTechData.HULL_EV, 5);
        addTierHull(RedSteel, WIRE_NIOBIUM_TITANIUM, CircuitEnergyFlow, GregTechData.CASING_IV, GregTechData.HULL_IV, 6);
    }

    private static void addTierCasing (Material mat, Material mat2, Block casing, int tier) {
        ASSEMBLING.RB().ii(RecipeIngredient.of(PLATE.get(mat, 2)), RecipeIngredient.of(BOLT.get(mat2, 2))).io(new ItemStack(casing)).add(5 * 20, (long) Math.pow(2, 2 * tier + 1));
    }

    private static void addTierHull (Material mat, Wire w, ItemBasic circ, Block casing, Block hull, int tier) {
            ASSEMBLING.RB().ii(RecipeIngredient.of(SCREW.get(mat), 2), RecipeIngredient.of(casing, 1), RecipeIngredient.of(Items.REDSTONE, 1), RecipeIngredient.of(w.getBlockItem(PipeSize.VTINY), 1), RecipeIngredient.of(TIER_CIRCUITS.getOrDefault(tier, circ), 1)).io(new ItemStack(hull)).add(5 * 20, (long) Math.pow(2, 2 * tier + 1));
    }

}
