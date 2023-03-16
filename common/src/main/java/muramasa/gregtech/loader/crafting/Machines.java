package muramasa.gregtech.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.block.BlockCasing;
import muramasa.gregtech.data.Materials;
import muramasa.gregtech.data.TierMaps;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static muramasa.antimatter.data.AntimatterDefaultTools.WRENCH;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Iron;
import static muramasa.antimatter.machine.Tier.IV;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.Machines.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.TierMaps.TIER_CIRCUITS;
import static muramasa.gregtech.data.TierMaps.WIRE_GETTER;

public class Machines {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        Arrays.stream(Tier.getAllElectric()).forEach(tier -> {
            Item motor = GregTech.get(ItemBasic.class, "motor_"+tier.getId());
            if (motor == null) return;
            Item piston = GregTech.get(ItemBasic.class, "piston_"+tier.getId());
            if (piston == null) return;
            Item arm = GregTech.get(ItemBasic.class, "robot_arm_"+tier.getId());
            if (arm == null) return;
            Item conveyor = GregTech.get(ItemCover.class, "conveyor_"+tier.getId());
            if (conveyor == null) return;
            Item pump = GregTech.get(ItemCover.class, "pump_"+tier.getId());
            if (pump == null) return;
            Item hull = Item.BY_BLOCK.get(GregTech.get(BlockCasing.class, "hull_" + tier.getId()));
            if (hull == null) return;
            Item sensor = GregTech.get(ItemBasic.class, "sensor_"+tier.getId());
            if (sensor == null) return;
            Item emitter = GregTech.get(ItemBasic.class, "emitter_"+tier.getId());
            if (emitter == null) return;
            Item field = GregTech.get(ItemBasic.class, "field_gen_"+tier.getId());
            if (field == null) return;
            Item circuit = TIER_CIRCUITS.getOrDefault(tier, CircuitBasic);
            if (circuit == null) return;
            Item cable = TierMaps.TIER_CABLES.get(tier);
            if (cable == null) return;
            Item rotor = TierMaps.TIER_ROTORS.get(tier);
            if (rotor == null) return;
            Item glass = Items.GLASS;
            Object diamond = Items.DIAMOND;

            add(MACERATOR, tier, (m, item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder().put('P', piston).put('M', motor).put('C', circuit).put('L', cable).put('H', hull).put('D', Items.DIAMOND).build(), "PMD", "LLH", "CCL"));

            add(ALLOY_SMELTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder().put('L', TierMaps.WIRE_GETTER.apply(PipeSize.SMALL, tier)).put('H', hull).put('C', circuit).put('G', cable).build(), "CLC", "LHL", "GLG"));

            add(COMPRESSOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.of('C', circuit, 'P', piston, 'L', cable, 'H', hull), "LCL", "PHP", "LCL"));

            add(CUTTER, tier, (m, item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder().put('M', motor).put('C', circuit).put('L', cable).put('H', hull).put('D', DiamondSawBlade).put('V', conveyor).put('G', glass).build(), "LCG", "VHD", "CLM"));

            add(FURNACE, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.of('C', circuit, 'L', cable, 'H', hull, 'W', TierMaps.WIRE_GETTER.apply(PipeSize.TINY, tier)), "CWC", "CHC", "LWL"));

            add(EXTRACTOR, tier, (m, item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder().put('P', piston).put('M', pump).put('G', glass).put('C', circuit).put('L', cable).put('H', hull).build(), "GCG", "PHM", "LCL"));

            add(EXTRUDER, tier, (m, item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder().put('P', piston).put('I', AntimatterAPI.get(FluidPipe.class,"fluid_"+ TierMaps.TIER_PIPE_MATERIAL.get(tier).getId()).getBlockItem(PipeSize.NORMAL)).put('W', WIRE_GETTER.apply(PipeSize.SMALL, tier)).put('C', circuit).put('H', hull).build(), "WWC", "PHI", "WWC"));

            add(LATHE, tier, (m, item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder().put('P', piston).put('M', motor).put('C', circuit).put('L', cable).put('H', hull).put('D', diamond).build(), "LCL", "MHD", "CLP"));

            add(ELECTROLYZER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.of(
                            'C', circuit,
                            'W', WIRE_SILVER.getBlockItem(PipeSize.VTINY),
                            'L', cable,
                            'H', hull,
                            'G', glass
                    ), "WGW", "WHW", "CLC"));

            add(ARC_FURNACE, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.of(
                            'C', circuit,
                            'W', DUST.get(Materials.Graphite),
                            'L', TierMaps.EXTRA_4_CABLES_TIER.get(tier),
                            'H', PLATE.get(TierMaps.TIER_MATERIALS.get(tier))
                    ), "LWL", "CHC", "HHH"));

            add(CHEMICAL_BATH, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', circuit)
                            .put('W', conveyor)
                            .put('P', pump)
                            .put('L', cable)
                            .put('H', hull)
                            .put('G', glass)
                    .build(), "WGL", "PGW", "CHC"));

            add(SIFTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', circuit)
                            .put('P', piston)
                            .put('L', cable)
                            .put('H', hull)
                            .put('F', ItemFilter)
                            .build(), "LFL", "PHP", "CFC"));

            add(BENDER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.of(
                            'P', piston,
                            'M', motor,
                            'C', circuit,
                            'L', cable,
                            'H', hull
                    ), "PLP", "CHC", "MLM"));

            add(WIRE_MILL, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.of(
                            'M', motor,
                            'C', circuit,
                            'L', cable,
                            'H', hull
                    ), "MLM", "CHC", "MLM"));

            add(ASSEMBLER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.of(
                            'R', arm,
                            'O', conveyor,
                            'C', circuit,
                            'L', cable,
                            'H', hull
                    ), "RCR", "OHO", "LCL"));

            add(CENTRIFUGE, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.of(
                            'M', motor,
                            'C', circuit,
                            'L', cable,
                            'H', hull
                    ), "CMC", "LHL", "CHC"));

            add(MIXER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.of(
                            'M', motor,
                            'R', rotor,
                            'G', glass,
                            'C', circuit,
                            'H', hull
                    ), "GRG", "GMG", "CHC"));

            add(STEAM_GENERATOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('L', cable)
                            .put('H', hull)
                            .put('R', rotor)
                            .put('C', circuit)
                            .put('P',  AntimatterAPI.get(FluidPipe.class,"fluid_"+ TierMaps.TIER_PIPE_MATERIAL.get(tier).getId()).getBlockItem(PipeSize.NORMAL))
                            .build(), "PCP", "RHR", "MLM"));

            add(BLAST_FURNACE, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('L', cable)
                            .put('H', CASING_HEAT_PROOF)
                            .put('C', circuit)
                            .put('F', Items.FURNACE)
                            .build(), "FFF", "CHC", "LCL"));

            add(VACUUM_FREEZER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('L', cable)
                            .put('F', CASING_FROST_PROOF)
                            .put('C', circuit)
                            .put('P', pump)
                            .build(), "PPP", "CFC", "LCL"));

            add(IMPLOSION_COMPRESSOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('L', cable)
                            .put('O', Items.OBSIDIAN)
                            .put('C', circuit)
                            .put('S', CASING_SOLID_STEEL)
                            .build(), "OOO", "CSC", "LCL"));

            add(MULTI_SMELTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('L', cable)
                            .put('F', Items.FURNACE)
                            .put('C', circuit)
                            .put('H', CASING_HEAT_PROOF)
                            .build(), "FFF", "CHC", "LCL"));

            add(HEAT_EXCHANGER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('I', FLUID_PIPE_TITANIUM.getBlock(PipeSize.SMALL))
                            .put('H', CASING_PIPE_TITANIUM)
                            .build(), "PIP", "IHI", "PIP"));

            add(DISTLLATION_TOWER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('I', FLUID_PIPE_STAINLESS_STEEL.getBlock(PipeSize.LARGE))
                            .put('H', hull)
                            .put('C', circuit)
                            .build(), "CIC", "PHP", "CIC"));

            add(CRACKING_UNIT, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('O', COIL_CUPRONICKEL)
                            .put('H', hull)
                            .put('C', circuit)
                            .build(), "POP", "CHC", "POP"));

            add(CANNER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                    .put('L', cable)
                    .put('P', pump)
                    .put('C', circuit)
                    .put('H', hull)
                    .put('G', glass).build(), "LPL", "CHC", "GGG"));

            add(FORGE_HAMMER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('L', cable)
                            .put('P', piston)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('A', Items.ANVIL).build(), "LPL", "CHC", "LAL"));
            add(RECYCLER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('G', Items.GLOWSTONE_DUST)
                            .put('P', piston)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable).build(), "GCG", "PHP", "LCL"));
            add(SCANNER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('S', sensor)
                            .put('E', emitter)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable).build(), "CEC", "LHL", "CSC"));
            add(THERMAL_CENTRIFUGE, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('W', TierMaps.WIRE_GETTER.apply(tier == IV ? PipeSize.HUGE : PipeSize.SMALL, tier))
                            .put('L', cable).build(), "CMC", "WHW", "LML"));
            add(ORE_WASHER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('G', glass)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('R', rotor)
                            .put('L', cable).build(), "RGR", "CMC", "LHL"));
            add(CHEMICAL_REACTOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('G', glass)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('R', rotor)
                            .put('L', cable).build(), "GRG", "LML", "CHC"));
            add(FLUID_CANNER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('G', glass)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable).build(), "GCG", "PHP", "LCL"));
            add(DISASSEMBLER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('R', arm)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable).build(), "RCR", "LHL", "RCR"));
            add(MASS_FABRICATOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('F', field)
                            .put('B', TierMaps.EXTRA_4_CABLES_TIER.get(tier))
                            .put('C', circuit)
                            .put('H', hull).build(), "CFC", "BHB", "CFC"));
            add(REPLICATOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('F', field)
                            .put('E', emitter)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('B', TierMaps.EXTRA_4_CABLES_TIER.get(tier))
                            .build(), "EFE", "CHC", "EBE"));
            add(FERMENTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('L', cable).build(), "LPL", "GHG", "LCL"));
            add(FLUID_EXTRACTOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('T', piston)
                            .put('L', cable).build(), "GCG", "PHT", "LCL"));
            add(FLUID_SOLIDIFIER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('E', Items.CHEST)
                            .put('L', cable).build(), "PGP", "LHL", "CEC"));
            add(DISTILLERY, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('Z', Items.BLAZE_ROD)
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('L', cable).build(), "GZG", "CHC", "LPL"));
            add(AUTOCLAVE, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('T', AntimatterMaterialTypes.PLATE.getMaterialTag(TierMaps.TIER_MATERIALS.get(tier)))
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('P', pump).build(), "TGT", "THT", "CPC"));
            add(LASER_ENGRAVER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', piston)
                            .put('E', emitter)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable).build(), "PEP", "CHC", "LCL"));
            add(PACKAGER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', piston)
                            .put('E', emitter)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable).build(), "PEP", "CHC", "LCL"));
            add(BATTERY_BUFFER_ONE, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', Items.CHEST)
                            .put('H', hull)
                            .put('L', TierMaps.TIER_WIRES.get(tier).getPipe().getType().getBlockItem(PipeSize.TINY)).build(), "LCL", "LHL", "   "));

            add(BATTERY_BUFFER_FOUR, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', Items.CHEST)
                            .put('H', hull)
                            .put('L', TierMaps.TIER_WIRES.get(tier).getPipe().getType().getBlockItem(PipeSize.NORMAL)).build(), "LCL", "LHL", "   "));

            add(BATTERY_BUFFER_EIGHT, tier, (m, item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', Items.CHEST)
                            .put('H', hull)
                            .put('L', TierMaps.TIER_WIRES.get(tier).getPipe().getType().getBlockItem(PipeSize.LARGE)).build(), "LCL", "LHL", "   "));

            add(HATCH_ITEM_I, tier, (m,item) ->  provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', Items.CHEST)
                            .put('H', hull)
                            .put('L', TierMaps.TIER_WIRES.get(tier).getPipe().getType().getBlockItem(PipeSize.LARGE)).build(), "LCL", "LHL", "   "));

            add(HATCH_FLUID_I, tier, (m,item) ->  provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('G', Items.GLASS)
                            .put('H', hull)
                            .build(), " G ", " H ", "   "));

            add(HATCH_ITEM_O, tier, (m,item) ->  provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', Items.CHEST)
                            .put('H', hull)
                            .put('L', TierMaps.TIER_WIRES.get(tier).getPipe().getType().getBlockItem(PipeSize.LARGE)).build(), "LHL", "LCL", "   "));

            add(HATCH_FLUID_O, tier, (m,item) ->  provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('G', Items.GLASS)
                            .put('H', hull)
                            .build(), " H ", " G ", "   "));

            add(HATCH_ENERGY, tier, (m,item) ->  provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', cable)
                            .put('H', hull)
                            .build(), "CH ", "   ", "   "));

            if (!AntimatterAPI.isModLoaded("gt4r")){
                add(ELECTRIC_TYPE_FILTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorHV), item,
                        ImmutableMap.<Character, Object>builder()
                                .put('H', hull)
                                .put('C', CircuitAdv)
                                .put('F', ItemFilter)
                                .put('E', Items.CHEST).build(), " H ", "ECE", " F "));
                add(ELECTRIC_ITEM_FILTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                        ImmutableMap.<Character, Object>builder()
                                .put('H', hull)
                                .put('C', CircuitBasic)
                                .put('F', ItemFilter)
                                .put('E', Items.CHEST).build(), " H ", "ECE", " F "));
            }
        });
        provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(CASING_FIRE_BRICK), PRIMITIVE_BLAST_FURNACE.getItem(PRIMITIVE_BLAST_FURNACE.getFirstTier()),
                ImmutableMap.<Character, Object>builder()
                        .put('H', CASING_BLAST_BRICK)
                        .put('F', Items.FURNACE)
                        .put('C', Items.IRON_BLOCK).build(), "HFH", "HCH", "HFH");

        provider.addItemRecipe(output, "machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), COKE_OVEN.getItem(COKE_OVEN.getFirstTier()),
                ImmutableMap.<Character, Object>builder()
                        .put('H', CASING_FIRE_BRICK)
                        .put('W', Items.FURNACE)
                        .put('P', PLATE.get(Iron)).build(), "HPH", "PWP", "HPH");

        provider.addItemRecipe(output, "machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), BRONZE_BLAST_FURNACE.getItem(BRONZE_BLAST_FURNACE.getFirstTier()),
                ImmutableMap.<Character, Object>builder()
                        .put('H', CASING_BRONZE_PLATED_BRICK)
                        .put('F', PRIMITIVE_BLAST_FURNACE.getItem(PRIMITIVE_BLAST_FURNACE.getFirstTier()))
                        .put('C', CASING_FIRE_BRICK).build(), "HCH", "CFC", "HCH");

        provider.addItemRecipe(output, "machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), CHARCOAL_PIT.getItem(CHARCOAL_PIT.getFirstTier()),
                ImmutableMap.<Character, Object>builder()
                        .put('H', CASING_BRONZE_PLATED_BRICK)
                        .put('G', GEAR.get(Steel))
                        .put('B', Blocks.COAL_BLOCK).build(), "GHG", "HBH", "GHG");

        provider.addItemRecipe(output, "machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), PYROLYSIS_OVEN.getItem(PYROLYSIS_OVEN.getFirstTier()),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_MV)
                        .put('C', CircuitGood)
                        .put('P', COVER_PUMP.getItem(Tier.MV).getItem())
                        .put('M', MotorMV)
                        .put('W', CABLE_CUPRONICKEL.getBlockItem(PipeSize.SMALL))
                        .put('B', Blocks.PISTON).build(), "BMW", "CHC", "MPW");

        provider.addItemRecipe(output, "machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), NUCLEAR_REACTOR.getItem(NUCLEAR_REACTOR.getFirstTier()),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_EV)
                        .put('A', RobotArmEV)
                        .put('G', GEAR.get(NiobiumTitanium))
                        .put('P', PLATE.get(Iridium))
                        .put('T', PLATE_DENSE.get(Thorium)).build(), "GTG", "AHA", "PTP");

        provider.addItemRecipe(output, "machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), QUANTUM_TANK.getItem(Tier.LV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_LV)
                        .put('C', CircuitBasicElectronic)
                        .put('F', FieldGenLV)
                        .put('P', PLATE.get(Steel)).build(), "CFC", "PHP", "CPC");

        provider.addItemRecipe(output, "machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), QUANTUM_TANK.getItem(Tier.MV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_MV)
                        .put('C', CircuitGood)
                        .put('F', FieldGenMV)
                        .put('P', PLATE.get(Aluminium)).build(), "CFC", "PHP", "CPC");

        provider.addItemRecipe(output, "machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), QUANTUM_TANK.getItem(Tier.HV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_HV)
                        .put('C', CircuitAdv)
                        .put('F', FieldGenHV)
                        .put('P', PLATE.get(StainlessSteel)).build(), "CFC", "PHP", "CPC");

        provider.addItemRecipe(output, "machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), QUANTUM_TANK.getItem(Tier.EV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_EV)
                        .put('C', CircuitNanoProcessor)
                        .put('F', FieldGenEV)
                        .put('P', PLATE.get(Titanium)).build(), "CFC", "PHP", "CPC");

        provider.addItemRecipe(output, "machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), QUANTUM_TANK.getItem(Tier.IV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_IV)
                        .put('C', CircuitQuantumProcessor)
                        .put('F', FieldGenIV)
                        .put('P', PLATE.get(TungstenSteel)).build(), "CFC", "PHP", "CPC");

        provider.addItemRecipe(output, "machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), TRANSFORMER.getItem(Tier.LV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_LV)
                        .put('C', CABLE_LEAD.getBlockItem(PipeSize.VTINY))
                        .put('W', CABLE_TIN.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");

        provider.addItemRecipe(output, "machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), TRANSFORMER.getItem(Tier.MV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_MV)
                        .put('C', CABLE_TIN.getBlockItem(PipeSize.VTINY))
                        .put('W', CABLE_COPPER.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");

        provider.addItemRecipe(output, "machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), TRANSFORMER.getItem(Tier.HV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_HV)
                        .put('C', CABLE_COPPER.getBlockItem(PipeSize.VTINY))
                        .put('W', CABLE_GOLD.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");

        provider.addItemRecipe(output, "machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), TRANSFORMER.getItem(Tier.EV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_EV)
                        .put('C', CABLE_GOLD.getBlockItem(PipeSize.VTINY))
                        .put('W', CABLE_ALUMINIUM.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");

        provider.addItemRecipe(output, "machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), TRANSFORMER.getItem(Tier.IV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_IV)
                        .put('C', CABLE_ALUMINIUM.getBlockItem(PipeSize.VTINY))
                        .put('W', CABLE_PLATINUM.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");
    }

    private static <T extends Machine<T>> void add(T machine, Tier tier, BiConsumer<T, Item> callback) {
        Item item = machine.getItem(tier);
        if (item != null) callback.accept(machine, item);
    }
}
