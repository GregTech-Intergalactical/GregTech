package muramasa.gregtech.loader.machines;

import com.google.common.collect.ImmutableSet;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.block.BlockCasing;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.tags.ItemTags;

import java.util.Arrays;

import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.GregTechTags.PLATES_IRON_ALUMINIUM;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.ASSEMBLING;
import static muramasa.gregtech.data.TierMaps.*;

public class AssemblyLoader {
    public static void init() {
        cables();
        misc();
        motors();
        pistons();
        rotors();
        casings();
        batteries();
        smdComponents();
    }

    private static void cables(){
        AntimatterAPI.all(Wire.class,t -> {
            Cable<?> cable = AntimatterAPI.get(Cable.class, "cable" + "_" + t.getMaterial().getId());
            if (cable == null) return;
            ImmutableSet<PipeSize> sizes = t.getSizes();
            sizes.forEach(size -> {
                Item wireItem = t.getBlockItem(size);
                Item cableItem = cable.getBlockItem(size);
                ASSEMBLING.RB().ii(of(wireItem,1), INT_CIRCUITS.get(size.getCableThickness())).fi(Rubber.getLiquid(size.getCableThickness()*16)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId(),size.getCableThickness()* 20L,8);
            });
        });
    }

    private static void misc(){
        ASSEMBLING.RB().ii(of(ItemTags.PLANKS,8), INT_CIRCUITS.get(8)).io(new ItemStack(Items.CHEST,1)).add("chest",100,4);
        ASSEMBLING.RB().ii(of(PLATES_IRON_ALUMINIUM, 2), of(Items.IRON_BARS, 2)).io(new ItemStack(GregTech.get(ItemCover.class, COVER_DRAIN.getId()))).add("drain",800, 16);
    }

    private static void motors(){
        Arrays.stream(Tier.getStandard()).forEach(t -> {
            Material magnet = (t == Tier.ULV || t == Tier.LV) ? IronMagnetic : (t == Tier.EV || t == Tier.IV ? NeodymiumMagnetic : SteelMagnetic);
            ASSEMBLING.RB().ii(of(TIER_WIRES.get(t),4), of(ROD.get(TIER_MATERIALS.get(t)),2),
                    of(ROD.get(magnet),1)
                    , of(TIER_CABLES.get(t),2)).io(new ItemStack(GregTech.get(ItemBasic.class,"motor_"+t.getId()))).add("motor_"+t.getId(),150,16);
        });
    }

    private static void pistons(){
        Arrays.stream(Tier.getStandard()).forEach(t -> {
            ASSEMBLING.RB().ii(of(TIER_CABLES.get(t),2),
                            of(ROD.get(TIER_MATERIALS.get(t)),2),
                            of(PLATE.get(TIER_MATERIALS.get(t)),3),
                            of(GregTech.get(ItemBasic.class,"motor_"+t.getId()),1),
                            of(GEAR.get(TIER_MATERIALS.get(t)),1))
                    .io(new ItemStack(GregTech.get(ItemBasic.class,"piston_"+t.getId())))
                    .add("piston_"+t.getId(),150,16);
        });
    }

    private static void rotors(){
        ROTOR.all().forEach(r -> {
            ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(r,4),SCREW.getMaterialIngredient(r,1),of(RING.get(Rubber,1))).fi(SolderingAlloy.getLiquid(144)).io(new ItemStack(ROTOR.get(r),1)).add(r.getId() + "_rotor",240,24);
        });
    }

    private static void batteries() {
        ASSEMBLING.RB().ii(PLATE.getIngredient(BatteryAlloy,1), of(TIER_CABLES.get(Tier.LV) ,1)).fi(Polyethylene.getLiquid(144)).io(BatteryHullSmall.getDefaultInstance()).add("battery_hull_small",80, 2);
        ASSEMBLING.RB().ii(PLATE.getIngredient(BatteryAlloy,3), of(TIER_CABLES.get(Tier.MV) ,2)).fi(Polyethylene.getLiquid(432)).io(BatteryHullMedium.getDefaultInstance()).add("battery_hull_medium",120, 4);
        ASSEMBLING.RB().ii(PLATE.getIngredient(BatteryAlloy,9), of(TIER_CABLES.get(Tier.HV) ,3)).fi(Polyethylene.getLiquid(1296)).io(BatteryHullLarge.getDefaultInstance()).add("battery_hull_large",160, 8);
    }

    private static void casings() {
        addTierCasing(WroughtIron, Lead, CASING_ULV, 1);
        addTierCasing(Steel, Copper, CASING_LV, 2);
        addTierCasing(Aluminium, StainlessSteel, CASING_MV, 3);
        addTierCasing(TungstenSteel, TungstenCarbide, CASING_HV, 4);
        addTierCasing(Ultimet, HSSG, CASING_EV, 5);
        addTierCasing(HSSE, HSSS, CASING_IV, 6);
        //addTierCasing(TungstenSteel, CASING_LUV);
        //addTierCasing(Osmiridium, CASING_ZPM);
        //addTierCasing(Ultimet, CASING_UV);
        //addTierCasing(RedSteel, CASING_MAX);

        addTierHull(Iron, WIRE_RED_ALLOY, CircuitBasic, CASING_ULV, HULL_ULV, 1);
        addTierHull(Iron, WIRE_RED_ALLOY, CircuitBasicElectronic, CASING_ULV, HULL_ULV, 1, 1);
        addTierHull(AnnealedCopper, WIRE_TIN, CircuitGood, CASING_LV, HULL_LV, 2);
        addTierHull(Silver, WIRE_CUPRONICKEL, CircuitAdv, CASING_MV, HULL_MV, 3);
        addTierHull(SterlingSilver, WIRE_ELECTRUM, CircuitNanoProcessor, CASING_HV, HULL_HV, 4);
        addTierHull(RoseGold, WIRE_NICHROME, CircuitQuantumProcessor, CASING_EV, HULL_EV, 5);
        addTierHull(RedSteel, WIRE_NIOBIUM_TITANIUM, CircuitEnergyFlow, CASING_IV, HULL_IV, 6);

        FRAME.all().forEach(f -> addFrame(f, FRAME.get(f)));

        addCasing(Bronze, GregTech.get(BlockCasing.class,"casing_bronze"));
        addCasing(Steel, GregTech.get(BlockCasing.class,"casing_solid_steel"));
        addCasing(StainlessSteel, GregTech.get(BlockCasing.class,"casing_stainless_steel"));
        addCasing(Titanium, GregTech.get(BlockCasing.class,"casing_titanium"));
        addCasing(TungstenSteel, GregTech.get(BlockCasing.class,"casing_tungstensteel"));
        addCasing(Invar, GregTech.get(BlockCasing.class,"casing_heat_proof"));
        addCasing(Aluminium, GregTech.get(BlockCasing.class,"casing_frost_proof"));
        addCasing(Lead, GregTech.get(BlockCasing.class,"casing_radiation_proof"));
    }

    private static void addTierCasing (Material mat, Material mat2, Block casing, int tier) {
        ASSEMBLING.RB().ii(of(PLATE.get(mat, 2)), of(BOLT.get(mat2, 2))).io(new ItemStack(casing)).add(AntimatterPlatformUtils.getIdFromBlock(casing).getPath(),5 * 20, (long) Math.pow(2, 2 * tier + 1));
    }

    private static void addTierHull (Material mat, Wire w, ItemBasic circ, Block casing, Block hull, int tier) {
        ASSEMBLING.RB().ii(of(SCREW.get(mat), 2), of(casing, 1), of(Items.REDSTONE, 1), of(w.getBlockItem(PipeSize.VTINY), 1), of(TIER_CIRCUITS.getOrDefault(tier, circ), 1)).io(new ItemStack(hull)).add(AntimatterPlatformUtils.getIdFromBlock(hull).getPath(),5 * 20, (long) Math.pow(2, 2 * tier + 1));
    }

    private static void addTierHull (Material mat, Wire w, ItemBasic circ, Block casing, Block hull, int tier, int idOffset) {
        ASSEMBLING.RB().ii(of(SCREW.get(mat), 2), of(casing, 1), of(Items.REDSTONE, 1), of(w.getBlockItem(PipeSize.VTINY), 1), of(TIER_CIRCUITS.getOrDefault(tier, circ), 1)).io(new ItemStack(hull)).add(AntimatterPlatformUtils.getIdFromBlock(hull).getPath() + "_" + idOffset,5 * 20, (long) Math.pow(2, 2 * tier + 1));
    }

    private static void addFrame (Material mat, Item frame) {
        ASSEMBLING.RB().ii(of(ROD.get(mat), 4), of(PLATE.get(mat), 4)).io(new ItemStack(frame,2)).add(AntimatterPlatformUtils.getIdFromBlock(frame).getPath(),40, 24);
    }

    private static void addCasing (Material mat, BlockCasing casing) {
        ASSEMBLING.RB().ii(of(ROD.get(mat), 4), of(PLATE.get(mat), 4)).io(new ItemStack(casing,2)).add(AntimatterPlatformUtils.getIdFromBlock(casing).getPath(),40, 24);
    }

    private static void smdComponents () {
        ASSEMBLING.RB().ii(of(PLATE.get(Silicon), 1), of(WIRE_FINE.get(AnnealedCopper), 8)).fi(Polyethylene.getLiquid(288)).io(new ItemStack(Transistor,32)).add("transistor",80, 96);
        ASSEMBLING.RB().ii(of(DUST.get(Carbon), 1), of(WIRE_FINE.get(Electrum), 4)).fi(Polyethylene.getLiquid(288)).io(new ItemStack(Resistor,24)).add("resistor",80, 96);
        ASSEMBLING.RB().ii(of(FOIL.get(PolyvinylChloride), 4), of(FOIL.get(Aluminium), 2)).fi(Polyethylene.getLiquid(288)).io(new ItemStack(Capacitor,16)).add("capacitor",80, 96);
    }

}
