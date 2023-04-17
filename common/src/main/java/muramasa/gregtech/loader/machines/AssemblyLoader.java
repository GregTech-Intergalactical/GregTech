package muramasa.gregtech.loader.machines;

import com.google.common.collect.ImmutableSet;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTypeBlock;
import muramasa.antimatter.pipe.PipeItemBlock;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.block.BlockCasing;
import muramasa.gregtech.block.BlockCoil;
import muramasa.gregtech.data.TierMaps;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.tags.ItemTags;

import java.util.Arrays;

import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.ofObject;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.GregTechTags.PLATES_IRON_ALUMINIUM;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.ASSEMBLING;
import static muramasa.gregtech.data.TierMaps.*;

public class AssemblyLoader {
    public static void init() {
        batteries();
        casings();
        cables();
        coils();
        frames();
        misc();
        motors();
        pistons();
        rotors();
        smdComponents();
    }

    //TODO proper type check for the cables
    private static void batteries() {
        ASSEMBLING.RB().ii(PLATE.getIngredient(BatteryAlloy,1), ofObject(CABLE_GETTER.apply(PipeSize.VTINY, LV, false) ,1)).fi(Polyethylene.getLiquid(144)).io(BatteryHullSmall.getDefaultInstance()).add("battery_hull_small",80, 2);
        ASSEMBLING.RB().ii(PLATE.getIngredient(BatteryAlloy,3), ofObject(CABLE_GETTER.apply(PipeSize.VTINY, MV, false) ,2)).fi(Polyethylene.getLiquid(432)).io(BatteryHullMedium.getDefaultInstance()).add("battery_hull_medium",120, 4);
        ASSEMBLING.RB().ii(PLATE.getIngredient(BatteryAlloy,9), ofObject(CABLE_GETTER.apply(PipeSize.VTINY, HV, false) ,3)).fi(Polyethylene.getLiquid(1296)).io(BatteryHullLarge.getDefaultInstance()).add("battery_hull_large",160, 8);
    }

    private static void casings() {
        addTierCasing(ULV);
        addTierCasing(LV);
        addTierCasing(MV);
        addTierCasing(HV);
        addTierCasing(EV);
        addTierCasing(IV);
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

        addCasing(Bronze, GregTech.get(BlockCasing.class,"casing_bronze"));
        addCasing(Steel, GregTech.get(BlockCasing.class,"casing_solid_steel"));
        addCasing(StainlessSteel, GregTech.get(BlockCasing.class,"casing_stainless_steel"));
        addCasing(Titanium, GregTech.get(BlockCasing.class,"casing_titanium"));
        addCasing(TungstenSteel, GregTech.get(BlockCasing.class,"casing_tungstensteel"));
        addCasing(Invar, GregTech.get(BlockCasing.class,"casing_heat_proof"));
        addCasing(Aluminium, GregTech.get(BlockCasing.class,"casing_frost_proof"));
        addCasing(Lead, GregTech.get(BlockCasing.class,"casing_radiation_proof"));
    }

    private static void cables(){
        AntimatterAPI.all(Wire.class,t -> {
            Cable<?> cable = AntimatterAPI.get(Cable.class, "cable" + "_" + t.getMaterial().getId());
            if (cable == null) return;
            ImmutableSet<PipeSize> sizes = t.getSizes();
            sizes.forEach(size -> {
                Item wireItem = t.getBlockItem(size);
                Item cableItem = cable.getBlockItem(size);
                long multiplier = AntimatterPlatformUtils.isFabric() ? 1000L : 16L;
                ASSEMBLING.RB().ii(of(wireItem,1), INT_CIRCUITS.get(size.getCableThickness())).fi(Rubber.getLiquid(size.getCableThickness()*multiplier)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId(),size.getCableThickness()* 20L,8);
            });
        });
    }


    private static void coils(){
        addCoil(GregTech.get(BlockCoil.class, "coil_cupronickel"), WIRE_CUPRONICKEL.getBlockItem(PipeSize.SMALL), Tin, 1);
        addCoil(GregTech.get(BlockCoil.class, "coil_kanthal"), WIRE_KANTHAL.getBlockItem(PipeSize.SMALL), AnnealedCopper, 2);
        addCoil(GregTech.get(BlockCoil.class, "coil_nichrome"), WIRE_NICHROME.getBlockItem(PipeSize.SMALL), Kanthal, 3);
        addCoil(GregTech.get(BlockCoil.class, "coil_tungstensteel"), WIRE_TUNGSTEN_STEEL.getBlockItem(PipeSize.SMALL), Nichrome, 4);
        addCoil(GregTech.get(BlockCoil.class, "coil_hssg"), WIRE_HSSG.getBlockItem(PipeSize.SMALL), TungstenSteel, 5);
        addCoil(GregTech.get(BlockCoil.class, "coil_naquadah"), WIRE_NAQUADAH.getBlockItem(PipeSize.SMALL), HSSG, 6);
        addCoil(GregTech.get(BlockCoil.class, "coil_naquadah_alloy"), WIRE_NAQUADAH_ALLOY.getBlockItem(PipeSize.SMALL), Naquadah, 7);
        addCoil(GregTech.get(BlockCoil.class, "coil_superconductor"), WIRE_SUPERCONDUCTOR.getBlockItem(PipeSize.SMALL), NaquadahAlloy, 8);
    }

    private static void frames(){
        FRAME.all().forEach(m -> {
            MaterialTypeBlock.Container f = FRAME.get().get(m);
            ASSEMBLING.RB().ii(of(ROD.get(m),6)).io(new ItemStack(f.asItem(),2)).add(AntimatterPlatformUtils.getIdFromBlock(f.asBlock()).getPath(),40,24);
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
                    , ofObject(CABLE_GETTER.apply(PipeSize.VTINY, t, false), 2)).io(new ItemStack(GregTech.get(ItemBasic.class,"motor_"+t.getId()))).add("motor_"+t.getId(),150,16);
        });
    }

    private static void pistons(){
        Arrays.stream(Tier.getStandard()).forEach(t -> {
            ASSEMBLING.RB().ii(ofObject(CABLE_GETTER.apply(PipeSize.VTINY, t, false),2),
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

    private static void smdComponents () {
        ASSEMBLING.RB().ii(of(PLATE.get(Silicon), 1), of(WIRE_FINE.get(AnnealedCopper), 8)).fi(Polyethylene.getLiquid(288)).io(new ItemStack(Transistor,32)).add("transistor",80, 96);
        ASSEMBLING.RB().ii(of(DUST.get(Carbon), 1), of(WIRE_FINE.get(Electrum), 4)).fi(Polyethylene.getLiquid(288)).io(new ItemStack(Resistor,24)).add("resistor",80, 96);
        ASSEMBLING.RB().ii(of(FOIL.get(PolyvinylChloride), 4), of(FOIL.get(Aluminium), 2)).fi(Polyethylene.getLiquid(288)).io(new ItemStack(Capacitor,16)).add("capacitor",80, 96);
    }

    private static void addTierCasing (Tier tier) {
        ASSEMBLING.RB().ii(of(PLATE.getMaterialTag(TIER_MATERIALS.get(tier)), 4)).io(new ItemStack(AntimatterAPI.get(BlockCasing.class, "casing_" + tier.getId(), GTIRef.ID))).add("casing_" + tier.getId(),5 * 20, (long) Math.pow(2, 2 * tier.getIntegerId() + 1));
    }

    private static void addTierHull (Material mat, Wire w, ItemBasic circ, Block casing, Block hull, int tier) {
        ASSEMBLING.RB().ii(of(SCREW.get(mat), 2), of(casing, 1), of(Items.REDSTONE, 1), of(w.getBlockItem(PipeSize.VTINY), 1), of(TIER_CIRCUITS.getOrDefault(tier, circ), 1)).io(new ItemStack(hull)).add(AntimatterPlatformUtils.getIdFromBlock(hull).getPath(),5 * 20, (long) Math.pow(2, 2 * tier + 1));
    }

    private static void addTierHull (Material mat, Wire w, ItemBasic circ, Block casing, Block hull, int tier, int idOffset) {
        ASSEMBLING.RB().ii(of(SCREW.get(mat), 2), of(casing, 1), of(Items.REDSTONE, 1), of(w.getBlockItem(PipeSize.VTINY), 1), of(TIER_CIRCUITS.getOrDefault(tier, circ), 1)).io(new ItemStack(hull)).add(AntimatterPlatformUtils.getIdFromBlock(hull).getPath() + "_" + idOffset,5 * 20, (long) Math.pow(2, 2 * tier + 1));
    }

    private static void addCasing (Material mat, BlockCasing casing) {
        ASSEMBLING.RB().ii(of(FRAME.get().get(mat).asItem(), 1), of(PLATE.get(mat), 4)).io(new ItemStack(casing,2)).add(AntimatterPlatformUtils.getIdFromBlock(casing).getPath(),80, 30);
    }

    private static void addCoil (BlockCoil coil, PipeItemBlock wire, Material fluidmat, int tier) {
        ASSEMBLING.RB().ii(of(wire,8), of(DUST.get(Aluminosilicate), 4)).fi(fluidmat.getLiquid(288)).io(new ItemStack(coil,1)).add(AntimatterPlatformUtils.getIdFromBlock(coil).getPath(), 100*tier, (long) (30*Math.pow(4,tier-1)));
    }
}
