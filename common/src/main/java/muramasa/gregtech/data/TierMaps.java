package muramasa.gregtech.data;

import com.google.common.collect.ImmutableMap;
import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import io.github.gregtechintergalactical.gtcore.data.GTCoreTags;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.SubTag;
import muramasa.antimatter.pipe.PipeItemBlock;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.GregTechConfig;
import muramasa.gregtech.items.ItemIntCircuit;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;
import java.util.function.Function;

import static muramasa.antimatter.data.AntimatterMaterials.Copper;
import static muramasa.antimatter.data.AntimatterMaterialTypes.GEM;
import static muramasa.antimatter.data.AntimatterMaterialTypes.ROTOR;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.gregtech.data.Materials.*;

public class TierMaps {

    public static final ImmutableMap<Integer, RecipeIngredient> INT_CIRCUITS;
    public static final ImmutableMap<Tier, Material> TIER_MATERIALS;
    public static final ImmutableMap<Tier, Material> TIER_PIPE_MATERIAL;
    public static ImmutableMap<Tier, PipeItemBlock> TIER_WIRES;
    //public static ImmutableMap<Tier, Item> TIER_CABLES;
    public static Function<Tier, TagKey<Item>> TIER_CIRCUITS;
    public static ImmutableMap<Tier, ItemBasic<?>> TIER_BOARDS;

    public static ImmutableMap<Tier, Material> EMITTER_RODS;
    public static ImmutableMap<Tier, Object> EMITTER_GEMS;

    public static ImmutableMap<Tier, Material> TIER_ROTORS;
    public static ImmutableMap<Tier, Function<PipeSize, Item>> TIER_PIPES;

    public static final BiFunction<PipeSize, Tier, Object> WIRE_GETTER;

    public static final TriFunction<PipeSize, Tier, Boolean, Object> CABLE_GETTER;

    static {
        {
            ImmutableMap.Builder<Integer, RecipeIngredient> builder = ImmutableMap.builder();
            for (int i = 0; i <= 24; i++) {
                builder.put(i, RecipeIngredient.of(new ItemIntCircuit(GTIRef.ID, "int_circuit_"+i,i),1).setNoConsume());
            }
            INT_CIRCUITS = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Material> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, WroughtIron);
            builder.put(Tier.LV, Steel);
            builder.put(Tier.MV, Aluminium);
            builder.put(Tier.HV, StainlessSteel);
            builder.put(Tier.EV, Titanium);
            builder.put(Tier.IV, TungstenSteel);
            builder.put(LUV, Chromium);
            builder.put(ZPM, Iridium);
            builder.put(UV, Osmium);
            builder.put(UHV, Neutronium);
            TIER_MATERIALS = builder.build();
        }

        {
            ImmutableMap.Builder<Tier, Material> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, Copper);
            builder.put(Tier.LV, Bronze);
            builder.put(Tier.MV, Steel);
            builder.put(Tier.HV, StainlessSteel);
            builder.put(Tier.EV, Titanium);
            builder.put(Tier.IV, TungstenSteel);
            TIER_PIPE_MATERIAL = builder.build();
        }

        WIRE_GETTER = (size, tier) -> {
            if (tier == LV) {
                return TagUtils.getItemTag(new ResourceLocation(GTIRef.ANTIMATTER, SubTag.COPPER_WIRE.getId()+"_"+ size.getId()));
            }
            if (tier == MV) {
                return GregTechBlocks.WIRE_CUPRONICKEL.getBlockItem(size);
            }
            if (tier == HV) {
                return GregTechBlocks.WIRE_KANTHAL.getBlockItem(size);
            }
            if (tier == EV) {
                return GregTechBlocks.WIRE_NICHROME.getBlockItem(size);
            }
            if (tier == IV) {
                return GregTechBlocks.WIRE_TUNGSTEN_STEEL.getBlockItem(size);
            }
            throw new IllegalArgumentException("Too high tier in WIRE_GETTER");
        };
        CABLE_GETTER = (size, tier, machine) -> {
            if (tier == ULV) return GregTechBlocks.CABLE_SOLDERING_ALLOY.getBlockItem(size);
            if (tier == LV) return GregTechBlocks.CABLE_TIN.getBlockItem(size);
            if (tier == MV){
                return TagUtils.getItemTag(new ResourceLocation(GTIRef.ANTIMATTER, SubTag.COPPER_CABLE.getId()+"_"+ size.getId()));
            }
            if (tier == HV) return GregTechBlocks.CABLE_GOLD.getBlockItem(size);
            if (tier == EV) return GregTechBlocks.CABLE_ALUMINIUM.getBlockItem(size);
            if (tier == IV) return machine ? GregTechBlocks.CABLE_PLATINUM.getBlockItem(size) : GregTechBlocks.CABLE_TUNGSTEN.getBlockItem(size);
            if(tier == LUV) return GregTechBlocks.CABLE_VANADIUM_GALLIUM.getBlockItem(size);
            if(tier == ZPM) return GregTechBlocks.CABLE_NAQUADAH.getBlockItem(size);
            if(tier == UV) return GregTechBlocks.CABLE_NAQUADAH_ALLOY.getBlockItem(size);
            if(tier == UHV) return GregTechBlocks.WIRE_SUPERCONDUCTOR.getBlockItem(size);
            throw new IllegalArgumentException("Invalid tier in CABLE_GETTER");
        };
    }
    //Called to init the INT CIRCUITS and tier materials early on.
    public static void init() {

    }
    //ProviderInit is called by the RecipeProvider during construction.
    private static boolean doneMaps = false;
    public static void providerInit() {
        if (doneMaps) return;
        doneMaps = true;
        {
            ImmutableMap.Builder<Tier, PipeItemBlock> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, GregTechBlocks.WIRE_SOLDERING_ALLOY.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.LV, GregTechBlocks.WIRE_TIN.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.MV, GregTechBlocks.WIRE_COPPER.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.HV, GregTechBlocks.WIRE_GOLD.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.EV, GregTechBlocks.WIRE_ALUMINIUM.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.IV, GregTechBlocks.WIRE_TUNGSTEN.getBlockItem(PipeSize.VTINY));
            builder.put(LUV, GregTechBlocks.WIRE_VANADIUM_GALLIUM.getBlockItem(PipeSize.VTINY));
            builder.put(ZPM, GregTechBlocks.WIRE_NAQUADAH.getBlockItem(PipeSize.VTINY));
            builder.put(UV, GregTechBlocks.WIRE_NAQUADAH_ALLOY.getBlockItem(PipeSize.SMALL));
            builder.put(UHV, GregTechBlocks.WIRE_SUPERCONDUCTOR.getBlockItem(PipeSize.VTINY));
            TIER_WIRES = builder.build();
        }
        /*{
            ImmutableMap.Builder<Tier, Item> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, CABLE_RED_ALLOY.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.LV, CABLE_TIN.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.MV, CABLE_COPPER.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.HV, CABLE_GOLD.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.EV, CABLE_ALUMINIUM.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.IV, CABLE_TUNGSTEN.getBlockItem(PipeSize.VTINY));
            builder.put(LUV, CABLE_VANADIUM_GALLIUM.getBlockItem(PipeSize.VTINY));
            builder.put(ZPM, CABLE_NAQUADAH.getBlockItem(PipeSize.VTINY));
            builder.put(UV, CABLE_NAQUADAH_ALLOY.getBlockItem(PipeSize.SMALL));
            builder.put(MAX, WIRE_SUPERCONDUCTOR.getBlockItem(PipeSize.VTINY));
            TIER_CABLES = builder.build();
        }*/
        {
            ImmutableMap.Builder<Tier, Material> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, Bronze);
            builder.put(Tier.LV, Tin);
            builder.put(Tier.MV, Bronze);
            builder.put(Tier.HV, Steel);
            builder.put(Tier.EV, StainlessSteel);
            builder.put(Tier.IV, TungstenSteel);
            TIER_ROTORS = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Material> builder = ImmutableMap.builder();
            builder.put(Tier.LV, Brass);
            builder.put(Tier.MV, Electrum);
            builder.put(Tier.HV, Chromium);
            builder.put(Tier.EV, Platinum);
            builder.put(Tier.IV, Osmium);
            EMITTER_RODS = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Object> builder = ImmutableMap.builder();
            builder.put(Tier.LV, GEM.getMaterialTag(MilkyQuartz));
            builder.put(Tier.MV, Items.QUARTZ);
            builder.put(Tier.HV, Items.EMERALD);
            builder.put(Tier.EV, Items.ENDER_PEARL);
            builder.put(Tier.IV, Items.ENDER_EYE);
            EMITTER_GEMS = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Function<PipeSize, Item>> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, GregTechBlocks.FLUID_PIPE_COPPER::getBlockItem);
            builder.put(Tier.LV, GregTechBlocks.FLUID_PIPE_BRONZE::getBlockItem);
            builder.put(Tier.MV, GregTechBlocks.FLUID_PIPE_STEEL::getBlockItem);
            builder.put(Tier.HV, GregTechBlocks.FLUID_PIPE_STAINLESS_STEEL::getBlockItem);
            builder.put(Tier.EV, GregTechBlocks.FLUID_PIPE_TITANIUM::getBlockItem);
            builder.put(Tier.IV, GregTechBlocks.FLUID_PIPE_TUNGSTEN_STEEL::getBlockItem);
            TIER_PIPES = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, TagKey<Item>> builder = ImmutableMap.builder();
            builder.put(Tier.LV, GTCoreTags.CIRCUITS_BASIC);
            builder.put(Tier.MV, GTCoreTags.CIRCUITS_GOOD);
            builder.put(Tier.HV, GTCoreTags.CIRCUITS_ADVANCED);
            builder.put(Tier.EV, GTCoreTags.CIRCUITS_ELITE);
            builder.put(Tier.IV, GTCoreTags.CIRCUITS_MASTER);
            builder.put(Tier.LUV, GTCoreTags.CIRCUITS_DATA_ORB);
            builder.put(Tier.ZPM, GTCoreTags.CIRCUITS_DATA_ORB);
            TIER_CIRCUITS = t ->{
                boolean hardMode = GregTechConfig.HARDER_CIRCUITS;
                if (t == LV){
                    return GTCoreTags.CIRCUITS_BASIC;
                }
                if (t == MV){
                    return GTCoreTags.CIRCUITS_GOOD;
                }
                if (t == HV){
                    return GTCoreTags.CIRCUITS_ADVANCED;
                }
                if (t == EV){
                    return hardMode ? GTCoreTags.CIRCUITS_COMPLEX : GTCoreTags.CIRCUITS_ELITE;
                }
                if (t == IV){
                    return hardMode ? GTCoreTags.CIRCUITS_ELITE : GTCoreTags.CIRCUITS_MASTER;
                }
                if (t == LUV){
                    return hardMode ? GTCoreTags.CIRCUITS_MASTER : GTCoreTags.CIRCUITS_DATA_ORB;
                }
                if (t == ZPM){
                    return hardMode ? GTCoreTags.CIRCUITS_FUTURISTIC : GTCoreTags.CIRCUITS_DATA_ORB;
                }
                if (t == UV){
                    return hardMode ? GTCoreTags.CIRCUITS_3D : GTCoreTags.CIRCUITS_DATA_ORB;
                }
                if (t == UHV){
                    return hardMode ? GTCoreTags.CIRCUITS_INFINITE : GTCoreTags.CIRCUITS_DATA_ORB;
                }
                return GTCoreTags.CIRCUITS_BASIC;
            };
        }
        {
            ImmutableMap.Builder<Tier, ItemBasic<?>> builder = ImmutableMap.builder();
            builder.put(Tier.LV, GTCoreItems.CircuitBoardCoated);
            builder.put(Tier.MV, GTCoreItems.CircuitBoardPhenolic);
            builder.put(Tier.HV, GTCoreItems.CircuitBoardPlastic);
            builder.put(Tier.EV, GTCoreItems.CircuitBoardEpoxy);
            builder.put(Tier.IV, GTCoreItems.CircuitBoardFiber);
            builder.put(Tier.LUV, GTCoreItems.CircuitBoardMultiFiber);
            builder.put(Tier.ZPM, GTCoreItems.CircuitBoardWetware);
            TIER_BOARDS = builder.build();
        }
    }
}
