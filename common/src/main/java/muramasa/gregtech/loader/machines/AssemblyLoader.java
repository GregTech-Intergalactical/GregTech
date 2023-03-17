package muramasa.gregtech.loader.machines;

import com.google.common.collect.ImmutableSet;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.tags.ItemTags;

import java.util.Arrays;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.GregTechTags.PLATES_IRON_ALUMINIUM;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.ASSEMBLING;
import static muramasa.gregtech.data.TierMaps.*;


public class AssemblyLoader {
    public static void init() {
        AntimatterAPI.all(Wire.class,t -> {
            Cable<?> cable = AntimatterAPI.get(Cable.class, "cable" + "_" + t.getMaterial().getId());
            if (cable == null) return;
            ImmutableSet<PipeSize> sizes = t.getSizes();
            sizes.forEach(size -> {
                Item wireItem = t.getBlockItem(size);
                Item cableItem = cable.getBlockItem(size);
                ASSEMBLING.RB().ii(RecipeIngredient.of(wireItem,1), INT_CIRCUITS.get(size.getCableThickness())).fi(Rubber.getLiquid(size.getCableThickness()*16)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId(),size.getCableThickness()* 20L,8);
            });
        });

        ROTOR.all().forEach(r -> {
            ASSEMBLING.RB().ii(AntimatterMaterialTypes.PLATE.getMaterialIngredient(r,4),AntimatterMaterialTypes.SCREW.getMaterialIngredient(r,1),of(RING.get(Rubber,1))).fi(SolderingAlloy.getLiquid(144)).io(new ItemStack(ROTOR.get(r),1)).add(r.getId() + "_rotor",240,24);
        });

        ASSEMBLING.RB().ii(RecipeIngredient.of(ItemTags.PLANKS,8), INT_CIRCUITS.get(8)).io(new ItemStack(Items.CHEST,1)).add("chest",100,4);
        Arrays.stream(Tier.getStandard()).forEach(t -> {
            Material magnet = (t == Tier.ULV || t == Tier.LV) ? IronMagnetic : (t == Tier.EV || t == Tier.IV ? NeodymiumMagnetic : SteelMagnetic);
            ASSEMBLING.RB().ii(RecipeIngredient.of(TIER_WIRES.get(t),4), RecipeIngredient.of(AntimatterMaterialTypes.ROD.get(TIER_MATERIALS.get(t)),2),
                    RecipeIngredient.of(AntimatterMaterialTypes.ROD.get(magnet),1)
                    , RecipeIngredient.of(TIER_CABLES.get(t),2)).io(new ItemStack(GregTech.get(ItemBasic.class,"motor_"+t.getId()))).add("motor_"+t.getId(),150,16);

            ASSEMBLING.RB().ii(RecipeIngredient.of(TIER_CABLES.get(t),2),
                    RecipeIngredient.of(AntimatterMaterialTypes.ROD.get(TIER_MATERIALS.get(t)),2),
                    RecipeIngredient.of(PLATE.get(TIER_MATERIALS.get(t)),3),
                    RecipeIngredient.of(GregTech.get(ItemBasic.class,"motor_"+t.getId()),1),
                    RecipeIngredient.of(AntimatterMaterialTypes.GEAR.get(TIER_MATERIALS.get(t)),1))
                    .io(new ItemStack(GregTech.get(ItemBasic.class,"piston_"+t.getId())))
                    .add("piston_"+t.getId(),150,16);
        });
        casings();
        batteries();
        smdComponents();
        ASSEMBLING.RB().ii(of(PLATES_IRON_ALUMINIUM, 2), of(Items.IRON_BARS, 2)).io(new ItemStack(GregTech.get(ItemCover.class, COVER_DRAIN.getId()))).add("drain",800, 16);
    }

    private static void batteries() {
        ASSEMBLING.RB().ii(PLATE.getIngredient(BatteryAlloy,1), RecipeIngredient.of(TIER_CABLES.get(Tier.LV) ,1)).fi(Polyethylene.getLiquid(144)).io(BatteryHullSmall.getDefaultInstance()).add("battery_hull_small",80, 2);
        ASSEMBLING.RB().ii(PLATE.getIngredient(BatteryAlloy,3), RecipeIngredient.of(TIER_CABLES.get(Tier.MV) ,2)).fi(Polyethylene.getLiquid(432)).io(BatteryHullMedium.getDefaultInstance()).add("battery_hull_medium",120, 4);
        ASSEMBLING.RB().ii(PLATE.getIngredient(BatteryAlloy,9), RecipeIngredient.of(TIER_CABLES.get(Tier.HV) ,3)).fi(Polyethylene.getLiquid(1296)).io(BatteryHullLarge.getDefaultInstance()).add("battery_hull_large",160, 8);
    }

    private static void casings() {
        addTierCasing(WroughtIron, Lead, GregTechData.CASING_ULV, 1);
        addTierCasing(Steel, AntimatterMaterials.Copper, GregTechData.CASING_LV, 2);
        addTierCasing(Aluminium, StainlessSteel, GregTechData.CASING_MV, 3);
        addTierCasing(TungstenSteel, TungstenCarbide, GregTechData.CASING_HV, 4);
        addTierCasing(Ultimet, HSSG, GregTechData.CASING_EV, 5);
        addTierCasing(HSSE, HSSS, GregTechData.CASING_IV, 6);
        //addTierCasing(TungstenSteel, GregTechData.CASING_LUV);
        //addTierCasing(Osmiridium, GregTechData.CASING_ZPM);
        //addTierCasing(Ultimet, GregTechData.CASING_UV);
        //addTierCasing(RedSteel, GregTechData.CASING_MAX);

        addTierHull(AntimatterMaterials.Iron, WIRE_RED_ALLOY, CircuitBasic, GregTechData.CASING_ULV, GregTechData.HULL_ULV, 1);
        addTierHull(AntimatterMaterials.Iron, WIRE_RED_ALLOY, CircuitBasicElectronic, GregTechData.CASING_ULV, GregTechData.HULL_ULV, 1, 1);
        addTierHull(AnnealedCopper, WIRE_TIN, CircuitGood, GregTechData.CASING_LV, GregTechData.HULL_LV, 2);
        addTierHull(Silver, WIRE_CUPRONICKEL, CircuitAdv, GregTechData.CASING_MV, GregTechData.HULL_MV, 3);
        addTierHull(SterlingSilver, WIRE_ELECTRUM, CircuitNanoProcessor, GregTechData.CASING_HV, GregTechData.HULL_HV, 4);
        addTierHull(RoseGold, WIRE_NICHROME, CircuitQuantumProcessor, GregTechData.CASING_EV, GregTechData.HULL_EV, 5);
        addTierHull(RedSteel, WIRE_NIOBIUM_TITANIUM, CircuitEnergyFlow, GregTechData.CASING_IV, GregTechData.HULL_IV, 6);
    }

    private static void addTierCasing (Material mat, Material mat2, Block casing, int tier) {
        ASSEMBLING.RB().ii(of(PLATE.get(mat, 2)), of(AntimatterMaterialTypes.BOLT.get(mat2, 2))).io(new ItemStack(casing)).add(AntimatterPlatformUtils.getIdFromBlock(casing).getPath(),5 * 20, (long) Math.pow(2, 2 * tier + 1));
    }

    private static void addTierHull (Material mat, Wire w, ItemBasic circ, Block casing, Block hull, int tier) {
        ASSEMBLING.RB().ii(of(AntimatterMaterialTypes.SCREW.get(mat), 2), of(casing, 1), of(Items.REDSTONE, 1), of(w.getBlockItem(PipeSize.VTINY), 1), of(TIER_CIRCUITS.getOrDefault(tier, circ), 1)).io(new ItemStack(hull)).add(AntimatterPlatformUtils.getIdFromBlock(hull).getPath(),5 * 20, (long) Math.pow(2, 2 * tier + 1));
    }

    private static void addTierHull (Material mat, Wire w, ItemBasic circ, Block casing, Block hull, int tier, int idOffset) {
        ASSEMBLING.RB().ii(of(AntimatterMaterialTypes.SCREW.get(mat), 2), of(casing, 1), of(Items.REDSTONE, 1), of(w.getBlockItem(PipeSize.VTINY), 1), of(TIER_CIRCUITS.getOrDefault(tier, circ), 1)).io(new ItemStack(hull)).add(AntimatterPlatformUtils.getIdFromBlock(hull).getPath() + "_" + idOffset,5 * 20, (long) Math.pow(2, 2 * tier + 1));
    }

    private static void smdComponents () {
        ASSEMBLING.RB().ii(of(PLATE.get(Silicon), 1), of(WIRE_FINE.get(AnnealedCopper), 8)).fi(Polyethylene.getLiquid(288)).io(new ItemStack(Transistor,32)).add("transistor",80, 96);
        ASSEMBLING.RB().ii(of(DUST.get(Carbon), 1), of(WIRE_FINE.get(Electrum), 4)).fi(Polyethylene.getLiquid(288)).io(new ItemStack(Resistor,24)).add("resistor",80, 96);
        ASSEMBLING.RB().ii(of(FOIL.get(PolyvinylChloride), 4), of(FOIL.get(Aluminium), 2)).fi(Polyethylene.getLiquid(288)).io(new ItemStack(Capacitor,16)).add("capacitor",80, 96);
    }

}
