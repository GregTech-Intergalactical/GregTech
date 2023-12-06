package muramasa.gregtech.loader.machines;

import com.google.common.collect.ImmutableSet;
import io.github.gregtechintergalactical.gtcore.GTCore;
import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.material.MaterialTypeBlock;
import muramasa.antimatter.pipe.PipeItemBlock;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.block.BlockCasing;
import muramasa.gregtech.block.BlockCoil;
import muramasa.gregtech.block.BlockColoredWall;
import muramasa.gregtech.data.Machines;
import muramasa.gregtech.data.ToolTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Arrays;

import static io.github.gregtechintergalactical.gtcore.data.GTCoreItems.*;
import static io.github.gregtechintergalactical.gtcore.data.GTCoreTags.*;
import static muramasa.antimatter.Ref.L;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.ofObject;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.ASSEMBLER;
import static muramasa.gregtech.data.TierMaps.*;
import static muramasa.gregtech.loader.crafting.Parts.fromTier;

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
        ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(BatteryAlloy,1), ofObject(CABLE_GETTER.apply(PipeSize.VTINY, LV, false) ,1)).fi(Plastic.getLiquid(L)).io(GTCoreItems.BatteryHullSmall.getDefaultInstance()).add("battery_hull_small",800, 1);
        ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(BatteryAlloy,3), ofObject(CABLE_GETTER.apply(PipeSize.VTINY, MV, false) ,2)).fi(Plastic.getLiquid(L * 3)).io(GTCoreItems.BatteryHullMedium.getDefaultInstance()).add("battery_hull_medium",1600, 2);
        ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(BatteryAlloy,9), ofObject(CABLE_GETTER.apply(PipeSize.VTINY, HV, false) ,4)).fi(Plastic.getLiquid(L * 9)).io(GTCoreItems.BatteryHullLarge.getDefaultInstance()).add("battery_hull_large",3200, 4);
        ASSEMBLER.RB().ii(DUST.getMaterialIngredient(Tantalum, 1), FOIL.getMaterialIngredient(Manganese, 1)).fi(Plastic.getLiquid(L)).io(new ItemStack(BatteryTantalum, 8)).add("tantalum_capacitor", 100, 4);

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

        addWall(Steel, STEEL_WALL);
        addWall(Invar, INVAR_WALL);
        addWall(StainlessSteel, STAINLESS_STEEL_WALL);
        addWall(Tungsten, TUNGSTEN_WALL);
        addWall(Titanium, TITANIUM_WALL);
        addWall(TungstenSteel, TUNGSTENSTEEL_WALL);
        addWall(Netherite, NETHERITE_WALL);

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
                ASSEMBLER.RB().ii(of(wireItem,1), INT_CIRCUITS.get(1)).fi(Rubber.getLiquid(amount)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_rubber",size.getCableThickness()* 20L,8);
                ASSEMBLER.RB().ii(of(wireItem,1), INT_CIRCUITS.get(1)).fi(StyreneButadieneRubber.getLiquid((amount * 3) / 4)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_styrene_butadiene_rubber",100,8);
                ASSEMBLER.RB().ii(of(wireItem,1), DUST_SMALL.getMaterialIngredient(PolyvinylChloride, multiplier)).fi(StyreneButadieneRubber.getLiquid(amount / 4)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_styrene_butadiene_rubber_2",100,8);
                ASSEMBLER.RB().ii(of(wireItem,1), DUST_SMALL.getMaterialIngredient(Polydimethylsiloxane, multiplier)).fi(StyreneButadieneRubber.getLiquid(amount / 4)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_styrene_butadiene_rubber_3",100,8);
                ASSEMBLER.RB().ii(of(wireItem,1), INT_CIRCUITS.get(1)).fi(SiliconeRubber.getLiquid(amount /2)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_silicone_rubber",100,8);
                ASSEMBLER.RB().ii(of(wireItem,1), DUST_SMALL.getMaterialIngredient(PolyvinylChloride, multiplier)).fi(SiliconeRubber.getLiquid(amount /4)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_silicone_rubber_2",100,8);
                ASSEMBLER.RB().ii(of(wireItem,1), DUST_SMALL.getMaterialIngredient(Polydimethylsiloxane, multiplier)).fi(SiliconeRubber.getLiquid(amount /4)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_silicone_rubber_3",100,8);
            });
        });
    }


    private static void coils(){
        addCoil(COIL_CUPRONICKEL, WIRE_CUPRONICKEL.getBlockItem(PipeSize.TINY));
        addCoil(COIL_KANTHAL, WIRE_KANTHAL.getBlockItem(PipeSize.TINY));
        addCoil(COIL_NICHROME, WIRE_NICHROME.getBlockItem(PipeSize.TINY));
        addCoil(COIL_TUNGSTENSTEEL, WIRE_TUNGSTEN_STEEL.getBlockItem(PipeSize.TINY));
        addCoil(COIL_HSSG, WIRE_HSSG.getBlockItem(PipeSize.TINY));
        addCoil(COIL_NAQUADAH, WIRE_NAQUADAH.getBlockItem(PipeSize.TINY));
        addCoil(COIL_NAQUADAH_ALLOY, WIRE_NAQUADAH_ALLOY.getBlockItem(PipeSize.TINY));
        addCoil(COIL_SUPERCONDUCTOR, WIRE_SUPERCONDUCTOR.getBlockItem(PipeSize.TINY));
    }

    private static void frames(){
        FRAME.all().forEach(m -> {
            MaterialTypeBlock.Container f = FRAME.get().get(m);
            ASSEMBLER.RB().ii(of(ROD.get(m),4), INT_CIRCUITS.get(4)).io(f.asItem()).add(AntimatterPlatformUtils.getIdFromBlock(f.asBlock()).getPath(),40,24);
        });
    }

    private static void misc(){
        ASSEMBLER.RB().ii(PrintedPages, Items.LEATHER).fi(Glue.getLiquid(20)).io(Items.WRITTEN_BOOK).fake().add("written_book", 32, 8);
        ASSEMBLER.RB().ii(of(Machines.TRANSFORMER.getItem(ULV), 8), of(Machines.TRANSFORMER.getItem(LV), 4), of(Machines.TRANSFORMER.getItem(MV), 2),
                of(Machines.TRANSFORMER.getItem(HV), 1), of(ComputerMonitor), of(TIER_CIRCUITS.apply(EV), 4)).io(Machines.ADJUSTABLE_TRANSFORMER.getItem(EV)).add("ev_adjustable_transformer", 50, 1920);
        ASSEMBLER.RB().ii(of(Machines.TRANSFORMER.getItem(EV), 1), of(Machines.ADJUSTABLE_TRANSFORMER.getItem(EV), 2), of(ComputerMonitor), of(TIER_CIRCUITS.apply(IV), 4)).io(Machines.ADJUSTABLE_TRANSFORMER.getItem(IV)).add("iv_adjustable_transformer", 50, 1920);
        ASSEMBLER.RB().ii(of(ItemTags.PLANKS,8), INT_CIRCUITS.get(8)).io(new ItemStack(Items.CHEST,1)).add("chest",100,4);

        ASSEMBLER.RB().ii(of(PLATES_IRON_ALUMINIUM, 2), of(Items.IRON_BARS, 2)).io(COVER_DRAIN.getItem()).add("drain",800, 16);
        ASSEMBLER.RB().ii(of(PLATES_IRON_ALUMINIUM, 2), of(COVER_PUMP.getItem(LV))).io(COVER_AIR_VENT .getItem()).add("air_vent",800, 16);
        addCoverRecipe(COVER_REDSTONE_MACHINE_CONTROLLER.getItem(), of(PLATES_IRON_ALUMINIUM, 1), of(Items.LEVER, 1));
        addCoverRecipe(COVER_ENERGY_DETECTOR.getItem(), of(PLATES_IRON_ALUMINIUM, 1), of(CIRCUITS_BASIC, 1));
        addCoverRecipe(COVER_FLUID_DETECTOR.getItem(), of(PLATES_IRON_ALUMINIUM, 1), of(Items.HEAVY_WEIGHTED_PRESSURE_PLATE, 1));
        addCoverRecipe(COVER_ITEM_DETECTOR.getItem(), of(PLATES_IRON_ALUMINIUM, 1), of(Items.LIGHT_WEIGHTED_PRESSURE_PLATE, 1));
        ASSEMBLER.RB().ii(of(CarbonFibre, 2), INT_CIRCUITS.get(2)).io(CarbonMesh).add("carbon_mesh", 800, 2);
        ASSEMBLER.RB().ii(of(CarbonFibre, 4), FOIL.getMaterialIngredient(Zinc, 16)).io(COVER_ITEM_FILTER.getItem()).add("item_filter", 1600, 32);
        ASSEMBLER.RB().ii(WIRE_FINE.getMaterialIngredient(Steel, 64), FOIL.getMaterialIngredient(Zinc, 16)).io(COVER_ITEM_FILTER.getItem()).add("item_filter_cheap", 1600, 32);
        ASSEMBLER.RB().ii(of(COVER_SHUTTER.getItem()), of(CIRCUITS_GOOD, 2)).io(COVER_FLUID_FILTER.getItem()).add("fluid_filter", 800, 4);
        ASSEMBLER.RB().ii(of(PLATES_IRON_ALUMINIUM, 2), of(Items.IRON_TRAPDOOR)).io(new ItemStack(COVER_SHUTTER.getItem().getItem(), 2)).add("shutter",800, 16);
        ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Invar, 2), of(Items.FLINT, 1)).io(GTCoreItems.LighterEmpty).add("empty_lighter", 256, 16);
        ASSEMBLER.RB().ii(of(Match, 64), of(Items.PAPER, 2)).fi(Glue.getLiquid(10)).io(MatchBook).add("matchbook", 100, 16);
    }

    private static void addCoverRecipe(ItemStack cover, Ingredient... inputs){
        ASSEMBLER.RB().ii(inputs).fi(SolderingAlloy.getLiquid(L / 2)).io(cover).add(AntimatterPlatformUtils.getIdFromItem(cover.getItem()).getPath() + "_soldering_alloy", 800, 16);
        ASSEMBLER.RB().ii(inputs).fi(Lead.getLiquid(L * 2)).io(cover).add(AntimatterPlatformUtils.getIdFromItem(cover.getItem()).getPath() + "_lead", 800, 16);
        ASSEMBLER.RB().ii(inputs).fi(Tin.getLiquid(L)).io(cover).add(AntimatterPlatformUtils.getIdFromItem(cover.getItem()).getPath() + "_tin", 800, 16);
    }

    private static void motors(){
        Arrays.stream(Tier.getStandard()).forEach(t -> {
            Material magnet = (t == Tier.ULV || t == Tier.LV) ? IronMagnetic : (t == Tier.EV || t == Tier.IV ? NeodymiumMagnetic : SteelMagnetic);
            ASSEMBLER.RB().ii(ofObject(WIRE_GETTER.apply(fromTier(t), LV),4), of(ROD.get(TIER_MATERIALS.get(t)),2),
                    of(ROD.get(magnet),1)
                    , ofObject(CABLE_GETTER.apply(PipeSize.VTINY, t, false), 2)).io(new ItemStack(AntimatterAPI.get(ItemBasic.class,"motor_"+t.getId(), GTCore.ID))).add("motor_"+t.getId(),150,16);
            ASSEMBLER.RB().ii(of(COVER_PUMP.getItem(t)), of(TIER_CIRCUITS.apply(t), 2)).io(new ItemStack(GregTech.get(ItemBasic.class, "fluid_regulator_" + t.getId()))).add("fluid_regulator_" + t.getId(), 800, 8);
        });
    }

    private static void pistons(){
        Arrays.stream(Tier.getStandard()).forEach(t -> {
            ASSEMBLER.RB().ii(ofObject(CABLE_GETTER.apply(PipeSize.VTINY, t, false),2),
                            of(ROD.get(TIER_MATERIALS.get(t)),2),
                            of(PLATE.get(TIER_MATERIALS.get(t)),3),
                            of(AntimatterAPI.get(ItemBasic.class,"motor_"+t.getId(), GTCore.ID),1),
                            of(GEAR.get(TIER_MATERIALS.get(t)),1))
                    .io(new ItemStack(GregTech.get(ItemBasic.class,"piston_"+t.getId())))
                    .add("piston_"+t.getId(),150,16);
        });
    }

    private static void rotors(){
        ROTOR.all().forEach(r -> {
            ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(r,4),RING.getMaterialIngredient(r,1)).fi(SolderingAlloy.getLiquid(144)).io(new ItemStack(ROTOR.get(r),1)).add(r.getId() + "_rotor",240,24);
        });
    }

    private static void turbines(){
        MaterialTags.TOOLS.getAll().forEach((m,t) -> {
            if (t.toolTypes().contains(ToolTypes.SMALL_TURBINE_ROTOR)){
                ASSEMBLER.RB().ii(ToolTypes.TURBINE_BLADE.getMaterialIngredient(m, 4), ROD_LONG.getMaterialIngredient(Magnalium, 1)).io(ToolTypes.SMALL_TURBINE_ROTOR.getToolStack(m)).add(m.getId() + "_small_turbine_rotor", 320, 16);
            }
            if (t.toolTypes().contains(ToolTypes.TURBINE_ROTOR)){
                ASSEMBLER.RB().ii(ToolTypes.TURBINE_BLADE.getMaterialIngredient(m, 8), ROD_LONG.getMaterialIngredient(Titanium, 1)).io(ToolTypes.TURBINE_ROTOR.getToolStack(m)).add(m.getId() + "_turbine_rotor", 480, 64);
            }
            if (t.toolTypes().contains(ToolTypes.LARGE_TURBINE_ROTOR)){
                ASSEMBLER.RB().ii(ToolTypes.TURBINE_BLADE.getMaterialIngredient(m, 12), ROD_LONG.getMaterialIngredient(TungstenSteel, 1)).io(ToolTypes.LARGE_TURBINE_ROTOR.getToolStack(m)).add(m.getId() + "_large_turbine_rotor", 640, 64);
            }
            if (t.toolTypes().contains(ToolTypes.HUGE_TURBINE_ROTOR)){
                ASSEMBLER.RB().ii(ToolTypes.TURBINE_BLADE.getMaterialIngredient(m, 16), ROD_LONG.getMaterialIngredient(Americium, 1)).io(ToolTypes.HUGE_TURBINE_ROTOR.getToolStack(m)).add(m.getId() + "_huge_turbine_rotor", 960, 256);
            }
        });
    }

    private static void addTierCasing (Tier tier) {
        ASSEMBLER.RB().ii(of(PLATE.getMaterialTag(TIER_MATERIALS.get(tier)), 8), INT_CIRCUITS.get(8)).io(new ItemStack(AntimatterAPI.get(BlockCasing.class, "casing_" + tier.getId(), GTIRef.ID))).add("casing_" + tier.getId(),50, 16);
    }

    private static void addTierHull(Tier tier) {
        Material liquid = tier == ZPM || tier == UV || tier == UHV ? Polytetrafluoroethylene : Plastic;
        ASSEMBLER.RB().ii(ofObject(CABLE_GETTER.apply(tier == Tier.UV ? PipeSize.SMALL : PipeSize.VTINY, tier, false), 2), of(AntimatterAPI.get(BlockCasing.class, "casing_" + tier.getId(), GTIRef.ID)))
                .fi(liquid.getLiquid(L * 2)).io(new ItemStack(AntimatterAPI.get(BlockCasing.class, "hull_" + tier.getId(), GTIRef.ID))).add("hull_" + tier.getId(), 50, 16);
    }

    private static void addCasing (Material mat, BlockCasing casing) {
        ASSEMBLER.RB().ii(of(FRAME.get().get(mat).asItem(), 1), of(PLATE.get(mat), 6)).io(new ItemStack(casing,1)).add(AntimatterPlatformUtils.getIdFromBlock(casing).getPath(),80, 30);
    }

    private static void addWall(Material mat, BlockColoredWall casing) {
        ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(mat, 4), INT_CIRCUITS.get(4)).io(new ItemStack(casing,1)).add(AntimatterPlatformUtils.getIdFromBlock(casing).getPath(),80, 30);
    }

    private static void addCoil (BlockCoil coil, PipeItemBlock wire) {
        ASSEMBLER.RB().ii(of(wire,8), INT_CIRCUITS.get(8)).io(new ItemStack(coil,1)).add(AntimatterPlatformUtils.getIdFromBlock(coil).getPath(), 100, 30);
    }
}
