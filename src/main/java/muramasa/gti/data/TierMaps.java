package muramasa.gti.data;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.SubTag;
import muramasa.antimatter.pipe.PipeItemBlock;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.TagUtils;
import muramasa.gti.Ref;
import muramasa.gti.items.ItemIntCircuit;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiFunction;
import java.util.function.Function;

import static muramasa.antimatter.Data.Copper;
import static muramasa.antimatter.Data.GEM;
import static muramasa.antimatter.Data.ROTOR;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.gti.data.GregTechData.*;
import static muramasa.gti.data.Materials.*;

public class TierMaps {

    public static final ImmutableMap<Integer, RecipeIngredient> INT_CIRCUITS;
    public static final ImmutableMap<Tier, Material> TIER_MATERIALS;
    public static final ImmutableMap<Tier, Material> TIER_PIPE_MATERIAL;
    public static ImmutableMap<Tier, PipeItemBlock> TIER_WIRES;
    public static ImmutableMap<Tier, Item> TIER_CABLES;
    public static ImmutableMap<Tier, ItemBasic<?>> TIER_CIRCUITS;
    public static ImmutableMap<Tier, ItemBasic<?>> TIER_BOARDS;
    //Used by special machines, 4x cables.
    public static ImmutableMap<Tier, Item> EXTRA_4_CABLES_TIER;

    public static ImmutableMap<Tier, Material> EMITTER_RODS;
    public static ImmutableMap<Tier, Object> EMITTER_GEMS;

    public static ImmutableMap<Tier, Item> TIER_ROTORS;
    public static ImmutableMap<Tier, Item> TIER_PIPES;

    public static final BiFunction<PipeSize, Tier, Object> WIRE_GETTER;

    static {
        {
            ImmutableMap.Builder<Integer, RecipeIngredient> builder = ImmutableMap.builder();
            for (int i = 0; i <= 24; i++) {
                builder.put(i, RecipeIngredient.of(new ItemIntCircuit(Ref.ID, "int_circuit_"+i,i).tip("ID: " + i),1).setNoConsume());
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
            TIER_MATERIALS = builder.build();
        }

        {
            ImmutableMap.Builder<Tier, Material> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, WroughtIron);
            builder.put(Tier.LV, Bronze);
            builder.put(Tier.MV, Bronze);
            builder.put(Tier.HV, StainlessSteel);
            builder.put(Tier.EV, Titanium);
            builder.put(Tier.IV, TungstenSteel);
            TIER_PIPE_MATERIAL = builder.build();
        }

        WIRE_GETTER = (size, tier) -> {
            if (tier == LV) {
                return TagUtils.getItemTag(new ResourceLocation(Ref.ANTIMATTER, SubTag.COPPER_WIRE.getId()+"_"+ size.getId()));
            }
            if (tier == MV) {
                return WIRE_CUPRONICKEL.getBlockItem(size);
            }
            if (tier == HV) {
                return WIRE_KANTHAL.getBlockItem(size);
            }
            if (tier == EV) {
                return WIRE_NICHROME.getBlockItem(size);
            }
            if (tier == IV) {
                return WIRE_TUNGSTEN_STEEL.getBlockItem(size);
            }
            throw new IllegalArgumentException("Too high tier in WIRE_GETTER");
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
            builder.put(Tier.ULV, WIRE_LEAD.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.LV, WIRE_COPPER.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.MV, WIRE_COPPER.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.HV, WIRE_GOLD.getBlockItem(PipeSize.TINY));
            builder.put(Tier.EV, WIRE_ANNEALED_COPPER.getBlockItem(PipeSize.SMALL));
            builder.put(Tier.IV, WIRE_PLATINUM.getBlockItem(PipeSize.TINY));
            TIER_WIRES = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Item> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, CABLE_LEAD.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.LV, CABLE_TIN.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.MV, CABLE_COPPER.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.HV, CABLE_SILVER.getBlockItem(PipeSize.TINY));
            builder.put(Tier.EV, CABLE_ALUMINIUM.getBlockItem(PipeSize.SMALL));
            builder.put(Tier.IV, CABLE_PLATINUM.getBlockItem(PipeSize.TINY));
            TIER_CABLES = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Item> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, ROTOR.get(Bronze));
            builder.put(Tier.LV, ROTOR.get(Bronze));
            builder.put(Tier.MV, ROTOR.get(Steel));
            builder.put(Tier.HV, ROTOR.get(StainlessSteel));
            builder.put(Tier.EV, ROTOR.get(Titanium));
            builder.put(Tier.IV, ROTOR.get(TungstenSteel));
            TIER_ROTORS = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Material> builder = ImmutableMap.builder();
            builder.put(Tier.LV, Brass);
            builder.put(Tier.MV, Electrum);
            builder.put(Tier.HV, Chrome);
            builder.put(Tier.EV, Platinum);
            builder.put(Tier.IV, Osmium);
            EMITTER_RODS = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Object> builder = ImmutableMap.builder();
            builder.put(Tier.LV, GEM.getMaterialTag(Quartzite));
            builder.put(Tier.MV, Items.QUARTZ);
            builder.put(Tier.HV, Items.EMERALD);
            builder.put(Tier.EV, Items.ENDER_PEARL);
            builder.put(Tier.IV, Items.ENDER_EYE);
            EMITTER_GEMS = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Item> builder = ImmutableMap.builder();
            builder.put(Tier.LV, CABLE_TIN.getBlockItem(PipeSize.SMALL));
            builder.put(Tier.MV, CABLE_COPPER.getBlockItem(PipeSize.SMALL));
            builder.put(Tier.HV, CABLE_GOLD.getBlockItem(PipeSize.SMALL));
            builder.put(Tier.EV, CABLE_ALUMINIUM.getBlockItem(PipeSize.SMALL));
            builder.put(Tier.IV, CABLE_TUNGSTEN.getBlockItem(PipeSize.SMALL));
            EXTRA_4_CABLES_TIER = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Item> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, AntimatterAPI.get(FluidPipe.class, "fluid_"+Copper.getId()).getBlockItem(PipeSize.NORMAL));
            builder.put(Tier.LV, AntimatterAPI.get(FluidPipe.class, "fluid_"+Copper.getId()).getBlockItem(PipeSize.NORMAL));
            builder.put(Tier.MV, AntimatterAPI.get(FluidPipe.class, "fluid_"+Copper.getId()).getBlockItem(PipeSize.NORMAL));
            builder.put(Tier.HV, AntimatterAPI.get(FluidPipe.class, "fluid_"+StainlessSteel.getId()).getBlockItem(PipeSize.NORMAL));
            builder.put(Tier.EV, AntimatterAPI.get(FluidPipe.class, "fluid_"+Titanium.getId()).getBlockItem(PipeSize.NORMAL));
            builder.put(Tier.IV, AntimatterAPI.get(FluidPipe.class, "fluid_"+TungstenSteel.getId()).getBlockItem(PipeSize.NORMAL));
            TIER_PIPES = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, ItemBasic<?>> builder = ImmutableMap.builder();
            builder.put(Tier.LV, CircuitBasic);
            builder.put(Tier.MV, CircuitGood);
            builder.put(Tier.HV, CircuitAdv);
            builder.put(Tier.EV, CircuitNanoProcessor);
            builder.put(Tier.IV, CircuitQuantumProcessor);
            builder.put(Tier.LUV, CircuitEnergyFlow);
            builder.put(Tier.ZPM, CircuitWetware);
            TIER_CIRCUITS = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, ItemBasic<?>> builder = ImmutableMap.builder();
            builder.put(Tier.LV, CircuitBoardCoated);
            builder.put(Tier.MV, CircuitBoardPhenolic);
            builder.put(Tier.HV, CircuitBoardPlastic);
            builder.put(Tier.EV, CircuitBoardEpoxy);
            builder.put(Tier.IV, CircuitBoardFiber);
            builder.put(Tier.LUV, CircuitBoardMultiFiber);
            builder.put(Tier.ZPM, CircuitBoardWetware);
            TIER_BOARDS = builder.build();
        }
    }
}
