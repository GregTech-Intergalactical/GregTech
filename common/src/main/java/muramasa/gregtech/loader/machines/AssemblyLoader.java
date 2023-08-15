package muramasa.gregtech.loader.machines;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
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
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.Machines;
import muramasa.gregtech.data.TierMaps;
import muramasa.gregtech.data.ToolTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.tags.ItemTags;

import java.util.Arrays;
import java.util.function.Consumer;

import static muramasa.antimatter.Ref.L;
import static muramasa.antimatter.data.AntimatterDefaultTools.WRENCH;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.ofObject;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.GregTechTags.CIRCUITS_ADVANCED;
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
        turbines();
    }

    //TODO proper type check for the cables
    private static void batteries() {
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(BatteryAlloy,1), ofObject(CABLE_GETTER.apply(PipeSize.VTINY, LV, false) ,1)).fi(Polyethylene.getLiquid(L)).io(BatteryHullSmall.getDefaultInstance()).add("battery_hull_small",800, 1);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(BatteryAlloy,3), ofObject(CABLE_GETTER.apply(PipeSize.VTINY, MV, false) ,2)).fi(Polyethylene.getLiquid(L * 3)).io(BatteryHullMedium.getDefaultInstance()).add("battery_hull_medium",1600, 2);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(BatteryAlloy,9), ofObject(CABLE_GETTER.apply(PipeSize.VTINY, HV, false) ,4)).fi(Polyethylene.getLiquid(L * 9)).io(BatteryHullLarge.getDefaultInstance()).add("battery_hull_large",3200, 4);
        ASSEMBLING.RB().ii(DUST.getMaterialIngredient(Tantalum, 1), FOIL.getMaterialIngredient(Manganese, 1)).fi(Polyethylene.getLiquid(L)).io(new ItemStack(BatteryTantalum, 8)).add("tantalum_capacitor", 100, 4);

    }

    private static void casings() {
        addTierCasing(ULV);
        addTierCasing(LV);
        addTierCasing(MV);
        addTierCasing(HV);
        addTierCasing(EV);
        addTierCasing(IV);
        addTierCasing(LUV);
        addTierCasing(ZPM);
        addTierCasing(UV);
        addTierCasing(UHV);


        addTierHull(Tier.ULV);
        addTierHull(Tier.LV);
        addTierHull(Tier.MV);
        addTierHull(Tier.HV);
        addTierHull(Tier.EV);
        addTierHull(Tier.IV);
        addTierHull(Tier.LUV);
        addTierHull(Tier.ZPM);
        addTierHull(Tier.UV);
        addTierHull(Tier.UHV);

        addCasing(Bronze, CASING_BRONZE);
        addCasing(Steel, CASING_SOLID_STEEL);
        addCasing(StainlessSteel, CASING_STAINLESS_STEEL);
        addCasing(Titanium, CASING_TITANIUM);
        addCasing(TungstenSteel, CASING_TUNGSTENSTEEL);
        addCasing(Invar, CASING_HEAT_PROOF);
        addCasing(Aluminium, CASING_FROST_PROOF);
        addCasing(Lead, CASING_RADIATION_PROOF);
    }

    private static void cables(){
        AntimatterAPI.all(Wire.class,t -> {
            Cable<?> cable = AntimatterAPI.get(Cable.class, "cable" + "_" + t.getMaterial().getId());
            if (cable == null) return;
            ImmutableSet<PipeSize> sizes = t.getSizes();
            sizes.forEach(size -> {
                Item wireItem = t.getBlockItem(size);
                Item cableItem = cable.getBlockItem(size);
                int ct = size.getCableThickness();
                int multiplier = ct == 16 ?  5 : ct == 12 ? 4 : ct == 8 ? 3 : ct == 4 ? 2 : 1;
                long amount = L * multiplier;
                ASSEMBLING.RB().ii(of(wireItem,1), INT_CIRCUITS.get(1)).fi(Rubber.getLiquid(amount)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_rubber",size.getCableThickness()* 20L,8);
                ASSEMBLING.RB().ii(of(wireItem,1), INT_CIRCUITS.get(1)).fi(StyreneButadieneRubber.getLiquid((amount * 3) / 4)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_styrene_butadiene_rubber",100,8);
                ASSEMBLING.RB().ii(of(wireItem,1), DUST_SMALL.getMaterialIngredient(PolyvinylChloride, multiplier)).fi(StyreneButadieneRubber.getLiquid(amount / 4)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_styrene_butadiene_rubber_2",100,8);
                ASSEMBLING.RB().ii(of(wireItem,1), DUST_SMALL.getMaterialIngredient(Polydimethylsiloxane, multiplier)).fi(StyreneButadieneRubber.getLiquid(amount / 4)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_styrene_butadiene_rubber_3",100,8);
                ASSEMBLING.RB().ii(of(wireItem,1), INT_CIRCUITS.get(1)).fi(SiliconeRubber.getLiquid(amount /2)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_silicone_rubber",100,8);
                ASSEMBLING.RB().ii(of(wireItem,1), DUST_SMALL.getMaterialIngredient(PolyvinylChloride, multiplier)).fi(SiliconeRubber.getLiquid(amount /4)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_silicone_rubber_2",100,8);
                ASSEMBLING.RB().ii(of(wireItem,1), DUST_SMALL.getMaterialIngredient(Polydimethylsiloxane, multiplier)).fi(SiliconeRubber.getLiquid(amount /4)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_silicone_rubber_3",100,8);
            });
        });
    }


    private static void coils(){
        addCoil(COIL_CUPRONICKEL, WIRE_CUPRONICKEL.getBlockItem(PipeSize.SMALL), 1);
        addCoil(COIL_KANTHAL, WIRE_KANTHAL.getBlockItem(PipeSize.SMALL), 2);
        addCoil(COIL_NICHROME, WIRE_NICHROME.getBlockItem(PipeSize.SMALL), 3);
        addCoil(COIL_TUNGSTENSTEEL, WIRE_TUNGSTEN_STEEL.getBlockItem(PipeSize.SMALL), 4);
        addCoil(COIL_HSSG, WIRE_HSSG.getBlockItem(PipeSize.SMALL), 5);
        addCoil(COIL_NAQUADAH, WIRE_NAQUADAH.getBlockItem(PipeSize.SMALL), 6);
        addCoil(COIL_NAQUADAH_ALLOY, WIRE_NAQUADAH_ALLOY.getBlockItem(PipeSize.SMALL), 7);
        addCoil(COIL_SUPERCONDUCTOR, WIRE_SUPERCONDUCTOR.getBlockItem(PipeSize.SMALL), 8);
    }

    private static void frames(){
        FRAME.all().forEach(m -> {
            MaterialTypeBlock.Container f = FRAME.get().get(m);
            ASSEMBLING.RB().ii(of(ROD.get(m),4), INT_CIRCUITS.get(4)).io(new ItemStack(f.asItem(),2)).add(AntimatterPlatformUtils.getIdFromBlock(f.asBlock()).getPath(),40,24);
        });
    }

    private static void misc(){
        ASSEMBLING.RB().ii(of(ItemTags.PLANKS,8), INT_CIRCUITS.get(8)).io(new ItemStack(Items.CHEST,1)).add("chest",100,4);
        ASSEMBLING.RB().ii(of(PLATES_IRON_ALUMINIUM, 2), of(Items.IRON_BARS, 2)).io(COVER_DRAIN.getItem()).add("drain",800, 16);
        ASSEMBLING.RB().ii(of(PLATES_IRON_ALUMINIUM, 1), of(Items.LEVER, 1)).fi(SolderingAlloy.getLiquid(L / 2)).io(COVER_REDSTONE_MACHINE_CONTROLLER.getItem()).add("redstone_machine_controller_soldering_alloy", 800, 16);
        ASSEMBLING.RB().ii(of(PLATES_IRON_ALUMINIUM, 1), of(Items.LEVER, 1)).fi(Lead.getLiquid(L * 2)).io(COVER_REDSTONE_MACHINE_CONTROLLER.getItem()).add("redstone_machine_controller_lead", 800, 16);
        ASSEMBLING.RB().ii(of(PLATES_IRON_ALUMINIUM, 1), of(Items.LEVER, 1)).fi(Tin.getLiquid(L)).io(COVER_REDSTONE_MACHINE_CONTROLLER.getItem()).add("redstone_machine_controller_tin", 800, 16);
        ASSEMBLING.RB().ii(of(CarbonFibre, 2), INT_CIRCUITS.get(2)).io(CarbonMesh).add("carbon_mesh", 800, 2);
        ASSEMBLING.RB().ii(of(CarbonFibre, 4), FOIL.getMaterialIngredient(Zinc, 16)).io(COVER_ITEM_FILTER.getItem()).add("item_filter", 1600, 32);
        ASSEMBLING.RB().ii(WIRE_FINE.getMaterialIngredient(Steel, 64), FOIL.getMaterialIngredient(Zinc, 16)).io(COVER_ITEM_FILTER.getItem()).add("item_filter_cheap", 1600, 32);
        ASSEMBLING.RB().ii(of(COVER_SHUTTER.getItem()), of(CIRCUITS_ADVANCED, 2)).io(COVER_FLUID_FILTER.getItem()).add("fluid_filter", 800, 4);
        ASSEMBLING.RB().ii(of(PLATES_IRON_ALUMINIUM, 2), of(Items.IRON_DOOR)).io(new ItemStack(COVER_SHUTTER.getItem().getItem(), 2)).add("shutter",800, 16);
    }

    private static void motors(){
        Arrays.stream(Tier.getStandard()).forEach(t -> {
            Material magnet = (t == Tier.ULV || t == Tier.LV) ? IronMagnetic : (t == Tier.EV || t == Tier.IV ? NeodymiumMagnetic : SteelMagnetic);
            ASSEMBLING.RB().ii(of(TIER_WIRES.get(t),4), of(ROD.get(TIER_MATERIALS.get(t)),2),
                    of(ROD.get(magnet),1)
                    , ofObject(CABLE_GETTER.apply(PipeSize.VTINY, t, false), 2)).io(new ItemStack(GregTech.get(ItemBasic.class,"motor_"+t.getId()))).add("motor_"+t.getId(),150,16);
            ASSEMBLING.RB().ii(of(COVER_PUMP.getItem(t)), of(TIER_CIRCUITS.get(t), 2)).io(new ItemStack(GregTech.get(ItemBasic.class, "fluid_regulator_" + t.getId()))).add("fluid_regulator_" + t.getId(), 800, 8);
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
            ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(r,4),RING.getMaterialIngredient(r,1)).fi(SolderingAlloy.getLiquid(144)).io(new ItemStack(ROTOR.get(r),1)).add(r.getId() + "_rotor",240,24);
        });
    }

    private static void turbines(){
        MaterialTags.TOOLS.getAll().forEach((m,t) -> {
            if (t.toolTypes().contains(ToolTypes.SMALL_TURBINE_ROTOR)){
                ASSEMBLING.RB().ii(ToolTypes.TURBINE_BLADE.getMaterialIngredient(m, 4), ROD_LONG.getMaterialIngredient(Magnalium, 1)).io(ToolTypes.SMALL_TURBINE_ROTOR.getToolStack(m)).add(m.getId() + "_small_turbine_rotor", 160, 100);
            }
            if (t.toolTypes().contains(ToolTypes.TURBINE_ROTOR)){
                ASSEMBLING.RB().ii(ToolTypes.TURBINE_BLADE.getMaterialIngredient(m, 8), ROD_LONG.getMaterialIngredient(Titanium, 1)).io(ToolTypes.TURBINE_ROTOR.getToolStack(m)).add(m.getId() + "_turbine_rotor", 320, 400);
            }
            if (t.toolTypes().contains(ToolTypes.LARGE_TURBINE_ROTOR)){
                ASSEMBLING.RB().ii(ToolTypes.TURBINE_BLADE.getMaterialIngredient(m, 12), ROD_LONG.getMaterialIngredient(TungstenSteel, 1)).io(ToolTypes.LARGE_TURBINE_ROTOR.getToolStack(m)).add(m.getId() + "_large_turbine_rotor", 640, 1600);
            }
            if (t.toolTypes().contains(ToolTypes.HUGE_TURBINE_ROTOR)){
                ASSEMBLING.RB().ii(ToolTypes.TURBINE_BLADE.getMaterialIngredient(m, 16), ROD_LONG.getMaterialIngredient(Americium, 1)).io(ToolTypes.HUGE_TURBINE_ROTOR.getToolStack(m)).add(m.getId() + "_huge_turbine_rotor", 1280, 6400);
            }
        });
    }

    private static void addTierCasing (Tier tier) {
        ASSEMBLING.RB().ii(of(PLATE.getMaterialTag(TIER_MATERIALS.get(tier)), 8), INT_CIRCUITS.get(8)).io(new ItemStack(AntimatterAPI.get(BlockCasing.class, "casing_" + tier.getId(), GTIRef.ID))).add("casing_" + tier.getId(),5 * 20, (long) Math.pow(2, 2 * tier.getIntegerId() + 1));
    }

    private static void addTierHull(Tier tier) {
        Material liquid = tier == ZPM || tier == UV || tier == UHV ? Polytetrafluoroethylene : Polyethylene;
        ASSEMBLING.RB().ii(ofObject(CABLE_GETTER.apply(tier == Tier.UV ? PipeSize.SMALL : PipeSize.VTINY, tier, false), 2), of(AntimatterAPI.get(BlockCasing.class, "casing_" + tier.getId(), GTIRef.ID)))
                .fi(liquid.getLiquid(L * 2)).io(new ItemStack(AntimatterAPI.get(BlockCasing.class, "hull_" + tier.getId(), GTIRef.ID))).add("hull_" + tier.getId(), 5 * 20, (long) Math.pow(2, 2 * tier.getIntegerId() + 1));
    }

    private static void addCasing (Material mat, BlockCasing casing) {
        ASSEMBLING.RB().ii(of(FRAME.get().get(mat).asItem(), 1), of(PLATE.get(mat), 6)).io(new ItemStack(casing,2)).add(AntimatterPlatformUtils.getIdFromBlock(casing).getPath(),80, 30);
    }

    private static void addCoil (BlockCoil coil, PipeItemBlock wire, int tier) {
        ASSEMBLING.RB().ii(of(wire,8), INT_CIRCUITS.get(8)).io(new ItemStack(coil,1)).add(AntimatterPlatformUtils.getIdFromBlock(coil).getPath(), 100*tier, (long) (30*Math.pow(4,tier-1)));
    }
}
