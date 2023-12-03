package muramasa.gregtech.loader.crafting;

import com.google.common.collect.ImmutableMap;
import io.github.gregtechintergalactical.gtcore.GTCore;
import io.github.gregtechintergalactical.gtcore.data.GTCoreBlocks;
import io.github.gregtechintergalactical.gtcore.data.GTCoreMaterials;
import io.github.gregtechintergalactical.gtcore.machine.*;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.ForgeCTags;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.GregTechConfig;
import muramasa.gregtech.block.BlockCasing;
import muramasa.gregtech.data.Materials;
import muramasa.gregtech.data.TierMaps;
import muramasa.gregtech.machine.MultiblockTankMachine;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static io.github.gregtechintergalactical.gtcore.data.GTCoreTags.*;
import static muramasa.antimatter.data.AntimatterDefaultTools.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Iron;
import static muramasa.antimatter.data.AntimatterMaterials.Wood;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.gregtech.data.GregTechData.*;
import static io.github.gregtechintergalactical.gtcore.data.GTCoreItems.*;
import static muramasa.gregtech.data.Machines.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.TierMaps.*;

public class Machines {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        Arrays.stream(Tier.getAllElectric()).forEach(tier -> {
            Item motor = AntimatterAPI.get(ItemBasic.class, "motor_"+tier.getId(), GTCore.ID);
            if (motor == null) return;
            Item piston = GregTech.get(ItemBasic.class, "piston_"+tier.getId());
            if (piston == null) return;
            Item arm = GregTech.get(ItemCover.class, "robot_arm_"+tier.getId());
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
            TagKey<Item> circuit = TIER_CIRCUITS.apply(tier);
            if (circuit == null) return;
            Object cable = CABLE_GETTER.apply(PipeSize.VTINY, tier, true);
            if (cable == null) return;
            Item rotor = TierMaps.TIER_ROTORS.get(tier);
            if (rotor == null) return;
            Item glass = Items.GLASS;
            Object diamond = Items.DIAMOND;
            Material material = TIER_MATERIALS.getOrDefault(tier, Material.NULL);


            add(MACERATOR, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder().put('P', piston).put('M', motor).put('C', circuit).put('L', cable).put('H', hull).put('D', Items.DIAMOND).build(), "PMD", "LLH", "CCL"));

            add(ALLOY_SMELTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder().put('L', TierMaps.WIRE_GETTER.apply(PipeSize.SMALL, tier)).put('H', hull).put('C', circuit).put('G', cable).build(), "CLC", "LHL", "GLG"));

            add(COMPRESSOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.of('C', circuit, 'P', piston, 'L', cable, 'H', hull), "LCL", "PHP", "LCL"));

            add(CUTTER, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder().put('M', motor).put('C', circuit).put('L', cable).put('H', hull).put('D', DiamondSawBlade).put('V', conveyor).put('G', glass).build(), "LCG", "VHD", "CLM"));

            add(FURNACE, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.of('C', circuit, 'L', cable, 'H', hull, 'W', TierMaps.WIRE_GETTER.apply(PipeSize.TINY, tier)), "CWC", "WHW", "LWL"));

            add(EXTRACTOR, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder().put('P', piston).put('M', pump).put('G', glass).put('C', circuit).put('L', cable).put('H', hull).build(), "GCG", "PHM", "LCL"));

            add(EXTRUDER, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder().put('P', piston).put('I', TIER_PIPES.get(tier).apply(PipeSize.NORMAL)).put('W', WIRE_GETTER.apply(PipeSize.SMALL, tier)).put('C', circuit).put('H', hull).build(), "WWC", "PHI", "WWC"));

            add(LATHE, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder().put('P', piston).put('M', motor).put('C', circuit).put('L', cable).put('H', hull).put('D', diamond).build(), "LCL", "MHD", "CLP"));

            Wire<?> electroWire = tier == LV ? WIRE_GOLD : tier == MV ? WIRE_SILVER : tier == HV ? WIRE_ELECTRUM : tier == EV ? WIRE_PLATINUM : WIRE_OSMIUM;
            add(ELECTROLYZER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.of(
                            'C', circuit,
                            'W', electroWire.getBlockItem(PipeSize.VTINY),
                            'L', cable,
                            'H', hull,
                            'G', glass
                    ), "WGW", "WHW", "CLC"));

            add(ARC_FURNACE, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.of(
                            'C', circuit,
                            'W', DUST.get(Materials.Graphite),
                            'L', CABLE_GETTER.apply(PipeSize.SMALL, tier, true),
                            'H', PLATE.get(material),
                            'M', hull
                    ), "LWL", "CMC", "HHH"));
            add(PLASMA_ARC_FURNACE, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.of(
                            'C', circuit,
                            'W', DUST.get(Materials.Graphite),
                            'L', CABLE_GETTER.apply(PipeSize.SMALL, tier, true),
                            'H', PLATE.get(material),
                            'M', hull,
                            'P', pump
                    ), "LWL", "CMC", "PHP"));

            add(BATH, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('W', WRENCH.getTag())
                            .put('H', hull)
                            .put('C', ITEM_CASING.getMaterialTag(material))
                            .put('S', PLATE.getMaterialTag(material))
                            .build(), "CWC", "SHS", "SSS"));

            add(SIFTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', circuit)
                            .put('P', piston)
                            .put('L', cable)
                            .put('H', hull)
                            .put('F', COVER_ITEM_FILTER.getItem())
                            .build(), "LFL", "PHP", "CFC"));

            Item esWire = tier == LV ? WIRE_TIN.getBlockItem(PipeSize.TINY) : tier == MV ? WIRE_COPPER.getBlockItem(PipeSize.TINY) : tier == HV ? WIRE_COPPER.getBlockItem(PipeSize.SMALL) : WIRE_ANNEALED_COPPER.getBlockItem(PipeSize.NORMAL);
            add(ELECTROMAGNETIC_SEPARATOR, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('c', conveyor)
                            .put('W', cable)
                            .put('w', esWire)
                            .put('H', hull)
                            .put('R', ROD.getMaterialTag(material))
                            .put('C', circuit)
                            .build(), "cWw", "WHR", "CWw"));

            add(BENDER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.of(
                            'P', piston,
                            'M', motor,
                            'C', circuit,
                            'L', cable,
                            'H', hull
                    ), "PLP", "CHC", "MLM"));

            add(WIRE_MILL, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.of(
                            'M', motor,
                            'C', circuit,
                            'L', cable,
                            'H', hull
                    ), "MLM", "CHC", "MLM"));

            add(ASSEMBLER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.of(
                            'R', arm,
                            'O', conveyor,
                            'C', circuit,
                            'L', cable,
                            'H', hull
                    ), "RCR", "OHO", "LCL"));

            add(CENTRIFUGE, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.of(
                            'M', motor,
                            'C', circuit,
                            'L', cable,
                            'H', hull
                    ), "CMC", "LHL", "CMC"));

            add(MIXER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.of(
                            'M', motor,
                            'R', rotor,
                            'G', glass,
                            'C', circuit,
                            'H', hull
                    ), "GRG", "GMG", "CHC"));

            add(STEAM_GENERATOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('L', cable)
                            .put('H', hull)
                            .put('R', rotor)
                            .put('C', circuit)
                            .put('P',  TIER_PIPES.get(tier).apply(PipeSize.NORMAL))
                            .build(), "PCP", "RHR", "MLM"));
            add(COMBUSTION_GENERATOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('L', cable)
                            .put('H', hull)
                            .put('G', GEAR.getMaterialTag(material))
                            .put('C', circuit)
                            .put('P',  piston)
                            .build(), "PCP", "MHM", "GLG"));
            add(SEMIFLUID_GENERATOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('L', cable)
                            .put('H', hull)
                            .put('G', Blocks.MAGMA_BLOCK)
                            .put('C', circuit)
                            .put('P',  PLATE.getMaterialTag(Invar))
                            .build(), "PCP", "MHM", "GLG"));
            add(GAS_GENERATOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('L', cable)
                            .put('H', hull)
                            .put('R', rotor)
                            .put('C', circuit)
                            .build(), "CRC", "RHR", "MLM"));

            add(CANNER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                    .put('L', cable)
                    .put('P', pump)
                    .put('C', circuit)
                    .put('H', hull)
                    .put('G', glass).build(), "LPL", "CHC", "GGG"));

            add(FORGE_HAMMER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('L', cable)
                            .put('P', piston)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('A', Items.ANVIL).build(), "LPL", "CHC", "LAL"));
            add(FORMING_PRESS, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('L', cable)
                            .put('P', piston)
                            .put('C', circuit)
                            .put('H', hull)
                            .build(), "LPL", "CHC", "LPL"));
            add(RECYCLER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('G', Items.GLOWSTONE_DUST)
                            .put('P', piston)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable).build(), "GCG", "PHP", "LCL"));
            add(SCANNER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('S', sensor)
                            .put('E', emitter)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', CABLE_GETTER.apply(PipeSize.VTINY, tier, false)).build(), "CEC", "LHL", "CSC"));
            add(THERMAL_CENTRIFUGE, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('W', TierMaps.WIRE_GETTER.apply(tier == IV ? PipeSize.HUGE : PipeSize.SMALL, tier))
                            .put('L', cable).build(), "CMC", "WHW", "LML"));
            add(ORE_WASHER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('G', glass)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('R', rotor)
                            .put('L', cable).build(), "RGR", "CMC", "LHL"));
            add(CHEMICAL_REACTOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('G', glass)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('R', rotor)
                            .put('L', cable).build(), "GRG", "LML", "CHC"));
            add(FLUID_CANNER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('G', glass)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable).build(), "GCG", "PHP", "LCL"));
            add(DISASSEMBLER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('R', arm)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', CABLE_GETTER.apply(PipeSize.VTINY, tier, false)).build(), "RCR", "LHL", "RCR"));
            add(MASS_FABRICATOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('F', field)
                            .put('B', CABLE_GETTER.apply(PipeSize.SMALL, tier, true))
                            .put('C', circuit)
                            .put('H', hull).build(), "CFC", "BHB", "CFC"));


            Tier tier2 = tier == LV ? tier : Tier.getTier(tier.getVoltage() * 4);
            add(AMP_FABRICATOR, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', TIER_CIRCUITS.apply(tier2))
                            .put('W', CABLE_GETTER.apply(PipeSize.SMALL, tier, true))
                            .put('H', hull)
                            .put('P', pump).build(), "WPW", "PHP", "CPC"));
            add(REPLICATOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('F', field)
                            .put('E', emitter)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('B', CABLE_GETTER.apply(PipeSize.SMALL, tier, true))
                            .build(), "EFE", "CHC", "EBE"));
            add(FERMENTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('L', cable).build(), "LPL", "GHG", "LCL"));
            add(FLUID_EXTRACTOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('T', piston)
                            .put('L', cable).build(), "GCG", "PHT", "LCL"));
            add(FLUID_HEATER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('W', WIRE_GETTER.apply(PipeSize.SMALL, tier))
                            .put('L', cable).build(), "WGW", "PHP", "LCL"));
            add(FLUID_SOLIDIFIER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('E', ForgeCTags.CHESTS)
                            .put('L', cable).build(), "PGP", "LHL", "CEC"));
            add(DISTILLERY, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('Z', Items.BLAZE_ROD)
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('L', cable).build(), "GZG", "CHC", "LPL"));
            add(AUTOCLAVE, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('T', AntimatterMaterialTypes.PLATE.getMaterialTag(material))
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('P', pump).build(), "TGT", "THT", "CPC"));
            add(LASER_ENGRAVER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', piston)
                            .put('E', emitter)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable).build(), "PEP", "CHC", "LCL"));
            add(PACKAGER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('c', conveyor)
                            .put('R', arm)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable)
                            .put('S', ForgeCTags.CHESTS).build(), "SCS", "RHc", "LCL"));
            Item wire = tier == LV ? WIRE_TIN.getBlockItem(PipeSize.TINY) : tier == MV ? WIRE_COPPER.getBlockItem(PipeSize.TINY) : tier == HV ? WIRE_COPPER.getBlockItem(PipeSize.SMALL) : WIRE_ANNEALED_COPPER.getBlockItem(PipeSize.NORMAL);
            Material rodMaterial = tier == LV ? Iron : tier == MV || tier == HV ? Steel : tier == EV ? Neodymium : VanadiumGallium;
            add(POLARIZER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('R', ROD.getMaterialTag(rodMaterial))
                            .put('W', wire)
                            .put('H', hull)
                            .put('L', cable).build(), "WRW", "LHL", "WRW"));
            add(PRINTER, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable).build(), "MLM", "CHC", "LML"));

            add(DEHYDRATOR, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('W', WIRE_GETTER.apply(PipeSize.SMALL, tier))
                            .put('C', CABLE_GETTER.apply(PipeSize.SMALL, tier, true))
                            .put('H', hull)
                            .put('c', circuit)
                            .put('P', PLATE.getMaterialTag(material))
                            .put('R', arm).build(), "WcW", "CHC", "PRP"));

            add(PUMP, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('R', rotor)
                            .put('H', hull)
                            .put('M', motor)
                            .put('P', TIER_PIPES.get(tier).apply(PipeSize.LARGE))
                            .put('C', circuit).build(), "MCM", "PHP", "RPR"));
            add(ROASTER, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('S', CABLE_GETTER.apply(PipeSize.SMALL, tier, true))
                            .put('V', CABLE_GETTER.apply(PipeSize.VTINY, tier, true))
                            .put('C', circuit)
                            .put('H', hull)
                            .put('W', WIRE_GETTER.apply(PipeSize.TINY, tier)).build(), "SVS", "CHC", "WWW"));
            add(BATTERY_BUFFER_ONE, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', ForgeCTags.CHESTS)
                            .put('H', hull)
                            .put('L', TierMaps.TIER_WIRES.get(tier).getPipe().getType().getBlockItem(PipeSize.VTINY)).build(), "LCL", "LHL"));

            add(BATTERY_BUFFER_FOUR, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', ForgeCTags.CHESTS)
                            .put('H', hull)
                            .put('L', TierMaps.TIER_WIRES.get(tier).getPipe().getType().getBlockItem(PipeSize.SMALL)).build(), "LCL", "LHL"));

            add(BATTERY_BUFFER_EIGHT, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', ForgeCTags.CHESTS)
                            .put('H', hull)
                            .put('L', TierMaps.TIER_WIRES.get(tier).getPipe().getType().getBlockItem(PipeSize.NORMAL)).build(), "LCL", "LHL"));
            add(BATTERY_BUFFER_SIXTEEN, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', ForgeCTags.CHESTS)
                            .put('H', hull)
                            .put('L', TierMaps.TIER_WIRES.get(tier).getPipe().getType().getBlockItem(PipeSize.HUGE)).build(), "LCL", "LHL"));

            add(CROP_HARVESTER, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('R', arm)
                            .put('C', circuit)
                            .put('P', piston)
                            .put('H', hull)
                            .put('S', sensor)
                            .put('B', SWORD_HEAD.getMaterialTag(material))
                            .put('c', conveyor)
                            .build(), "RCR", "PHS", "BcB"));
            TagKey<Item> plate = PLATE.getMaterialTag(tier == LV ? Steel : VanadiumSteel);
            add(SEISMIC_PROSPECTOR, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    of('P', plate, 'C', circuit, 'H', hull, 'S', sensor), "PPP", "CHC", "SSS"));

            add(SUPER_BUFFER, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('D', DataOrb)
                            .put('M', hull)
                            .put('C', conveyor).build(), "DMC"));
            add(SUPER_BUFFER, tier, (m, item) -> provider.addItemRecipe(output, GTIRef.ID, "super_buffer_" + tier.getId() +"_1", "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('D', DataStick)
                            .put('M', hull)
                            .put('C', conveyor).build(), "DMC", "DDD"));
            add(CHEST_BUFFER, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('D', ForgeCTags.CHESTS_WOODEN)
                            .put('M', hull)
                            .put('C', conveyor)
                            .put('c', TIER_CIRCUITS.apply(LV)).build(), "DMC", " c "));

            add(ELECTRIC_TYPE_FILTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('H', hull)
                            .put('C', TIER_CIRCUITS.apply(HV))
                            .put('F', COVER_ITEM_FILTER.getItem())
                            .put('E', ForgeCTags.CHESTS)
                            .put('c', conveyor).build(), " F ", "EHc", " C "));
            add(ELECTRIC_ITEM_FILTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('H', hull)
                            .put('C', TIER_CIRCUITS.apply(LV))
                            .put('F', COVER_ITEM_FILTER.getItem())
                            .put('E', ForgeCTags.CHESTS)
                            .put('c', conveyor).build(), " F ", "EHc", " C "));
        });

        var circuit = GregTechConfig.HARDER_CIRCUITS ? CIRCUITS_ADVANCED : EngravedCrystalChip;
        provider.addItemRecipe(output, "machines", QUANTUM_TANK.getItem(Tier.LV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_LV)
                        .put('C', circuit)
                        .put('F', FieldGenLV)
                        .put('P', PLATE.get(Steel)).build(), "CFC", "PHP", "CPC");

        circuit = GregTechConfig.HARDER_CIRCUITS ? CIRCUITS_COMPLEX : CIRCUITS_DATA;
        provider.addItemRecipe(output, "machines", QUANTUM_TANK.getItem(MV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_MV)
                        .put('C', circuit)
                        .put('F', FieldGenMV)
                        .put('P', PLATE.get(Aluminium)).build(), "CFC", "PHP", "CPC");

        provider.addItemRecipe(output, "machines", QUANTUM_TANK.getItem(Tier.HV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_HV)
                        .put('C', CIRCUITS_ELITE)
                        .put('F', FieldGenHV)
                        .put('P', PLATE.get(StainlessSteel)).build(), "CFC", "PHP", "CPC");

        provider.addItemRecipe(output, "machines", QUANTUM_TANK.getItem(Tier.EV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_EV)
                        .put('C', CIRCUITS_MASTER)
                        .put('F', FieldGenEV)
                        .put('P', PLATE.get(Titanium)).build(), "CFC", "PHP", "CPC");

        circuit = GregTechConfig.HARDER_CIRCUITS ? CIRCUITS_DATA_ORB : CIRCUITS_DATA_ORB;
        provider.addItemRecipe(output, "machines", QUANTUM_TANK.getItem(Tier.IV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_IV)
                        .put('C', circuit)
                        .put('F', FieldGenIV)
                        .put('P', PLATE.get(TungstenSteel)).build(), "CFC", "PHP", "CPC");

        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(Tier.ULV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_ULV)
                        .put('C', CABLE_LEAD.getBlockItem(PipeSize.VTINY))
                        .put('W', CABLE_TIN.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");

        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(Tier.LV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_LV)
                        .put('C', CABLE_TIN.getBlockItem(PipeSize.VTINY))
                        .put('W', CABLE_COPPER.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");

        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(MV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_MV)
                        .put('C', CABLE_COPPER.getBlockItem(PipeSize.VTINY))
                        .put('W', CABLE_GOLD.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");

        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(Tier.HV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_HV)
                        .put('C', CABLE_GOLD.getBlockItem(PipeSize.VTINY))
                        .put('W', CABLE_ALUMINIUM.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");

        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(Tier.EV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_EV)
                        .put('C', CABLE_ALUMINIUM.getBlockItem(PipeSize.VTINY))
                        .put('W', CABLE_TUNGSTEN.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");

        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(Tier.IV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_IV)
                        .put('C', CABLE_TUNGSTEN.getBlockItem(PipeSize.VTINY))
                        .put('W', CABLE_VANADIUM_GALLIUM.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");
        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(Tier.LUV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_LUV)
                        .put('C', CABLE_VANADIUM_GALLIUM.getBlockItem(PipeSize.VTINY))
                        .put('W', CABLE_NAQUADAH.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");
        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(ZPM),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_ZPM)
                        .put('C', CABLE_NAQUADAH.getBlockItem(PipeSize.VTINY))
                        .put('W', WIRE_NAQUADAH_ALLOY.getBlockItem(PipeSize.SMALL)).build(), " CC", "WH ", " CC");
        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(UV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_UV)
                        .put('C', WIRE_NAQUADAH_ALLOY.getBlockItem(PipeSize.SMALL))
                        .put('W', WIRE_SUPERCONDUCTOR.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");
        provider.addItemRecipe(output, "machines", LONG_DISTANCE_TRANSFORMER_ENDPOINT.getItem(EV),
                of('T', TRANSFORMER.getItem(EV), 'C', CABLE_GETTER.apply(PipeSize.SMALL, MV, false), 'W', WIRE_CUTTER.getTag()), "CTC", "TWT", "CTC");
        provider.addItemRecipe(output, "machines", LONG_DISTANCE_TRANSFORMER_ENDPOINT.getItem(IV),
                of('T', TRANSFORMER.getItem(IV), 'C', CABLE_GETTER.apply(PipeSize.SMALL, MV, false), 'W', WIRE_CUTTER.getTag()), "CTC", "TWT", "CTC");
        provider.addItemRecipe(output, "machines", LONG_DISTANCE_TRANSFORMER_ENDPOINT.getItem(LUV),
                of('T', TRANSFORMER.getItem(LUV), 'C', CABLE_GETTER.apply(PipeSize.SMALL, MV, false), 'W', WIRE_CUTTER.getTag()), "CTC", "TWT", "CTC");
        provider.addItemRecipe(output, "machines", LONG_DISTANCE_TRANSFORMER_ENDPOINT.getItem(ZPM),
                of('T', TRANSFORMER.getItem(ZPM), 'C', CABLE_GETTER.apply(PipeSize.SMALL, MV, false), 'W', WIRE_CUTTER.getTag()), "CTC", "TWT", "CTC");
        provider.addItemRecipe(output, "machines", LONG_DISTANCE_TRANSFORMER_ENDPOINT.getItem(UV),
                of('T', TRANSFORMER.getItem(UV), 'C', CABLE_GETTER.apply(PipeSize.SMALL, MV, false), 'W', WIRE_CUTTER.getTag()), "CTC", "TWT", "CTC");
        provider.addItemRecipe(output, "machines", LONG_DISTANCE_FLUID_ENDPOINT.getItem(NONE),
                of('T', FLUID_PIPE_TUNGSTEN.getBlock(PipeSize.NORMAL), 'C', PLATE.getMaterialTag(Plastic), 'W', CASING_TUNGSTEN), "CTC", "TWT", "CTC");
        provider.addItemRecipe(output, "machines", LONG_DISTANCE_ITEM_ENDPOINT.getItem(NONE),
                of('T', ITEM_PIPE_PLATINUM.getBlock(PipeSize.NORMAL), 'C', PLATE.getMaterialTag(Plastic), 'W', CASING_PLATINUM), "CTC", "TWT", "CTC");
        addHatchRecipes(output, provider);
        addMultiblockRecipes(output, provider);

        AntimatterAPI.all(WorkbenchMachine.class).forEach(m -> {
            if (!m.getId().contains("charging")) {
                provider.addItemRecipe(output, GTIRef.ID, "", "machines", m.getItem(NONE),
                        of('P', PLATE.getMaterialTag(m.getMaterial()), 'C', ForgeCTags.CHESTS_WOODEN, 'c', Items.CRAFTING_TABLE, 'S', SCREWDRIVER.getTag()), "PSP", "PcP", "PCP");
            } else {
                provider.addItemRecipe(output, Ref.ID, "", "machines", m.getItem(HV),
                        of('S', SCREWDRIVER.getTag(), 'w', WIRE_CUTTER.getTag(), 'W', Machine.get(m.getId().replace("charging_", ""), GTCore.ID).map(mch -> mch.getItem(NONE)).orElse(Items.AIR), 'c', CABLE_GETTER.apply(PipeSize.SMALL, HV, false), 'C', CIRCUITS_ADVANCED, 'R', ROD.getMaterialTag(m.getMaterial())), "RCR", "SWw", "ccc");
            }
        });
        AntimatterAPI.all(LockerMachine.class).forEach(m -> {
            Material material = m.getMaterial();
            ChestMachine chest = AntimatterAPI.get(ChestMachine.class, material.getId() + "_chest", GTCore.ID);
            if (material.has(SCREW) && chest != null){
                if (!m.getId().contains("charging")) {
                    provider.addItemRecipe(output, GTIRef.ID, "", "machines", m.getItem(NONE),
                            of('s', SCREW.getMaterialTag(m.getMaterial()), 'C', chest.getItem(NONE), 'c', CASING_SOLID_STEEL, 'S', SCREWDRIVER.getTag(), 'R', ROD.getMaterialTag(material), 'L', Items.LEATHER), "RSR", "LCL", "scs");
                } else {
                    provider.addItemRecipe(output, Ref.ID, "", "machines", m.getItem(HV),
                            of('W', Machine.get(m.getId().replace("charging_", ""), GTCore.ID).map(mch -> mch.getItem(NONE)).orElse(Items.AIR), 'c', CABLE_GETTER.apply(PipeSize.VTINY, HV, false), 'C', CIRCUITS_ADVANCED), "cCc", "cWc", "cCc");
                }
            }

        });
        AntimatterAPI.all(ChestMachine.class).forEach(m -> {
            Material material = m.getMaterial();
            if (material.has(RING) && material.has(PLATE)){
                provider.addItemRecipe(output, GTIRef.ID, "", "machines", m.getItem(NONE),
                        of('P', PLATE.getMaterialTag(material), 'R', ROD.getMaterialTag(material), 'r', RING.getMaterialTag(material), 'S', SAW.getTag(), 'W', WRENCH.getTag()), "SPW", "rRr", "PPP");
            }
        });
        AntimatterAPI.all(BarrelMachine.class).forEach(m -> {
            Material material = m.getMaterial();
            if (material.has(ROD) && material.has(PLATE)){
                provider.addItemRecipe(output, GTIRef.ID, "", "machines", m.getItem(NONE),
                        of('P', PLATE.getMaterialTag(material), 'R', ROD.getMaterialTag(material), 'S', SAW.getTag(), 'W', WRENCH.getTag()), "SPW", "PRP", " P ");
            }
        });
        AntimatterAPI.all(MassStorageMachine.class).forEach(m -> {
            Material material = m.getMaterial();
            ChestMachine chest = AntimatterAPI.get(ChestMachine.class, material.getId() + "_chest", GTCore.ID);
            if (material.has(SCREW) && material.has(PLATE) && !material.has(MaterialTags.WOOD) && chest != null){
                provider.addItemRecipe(output, GTIRef.ID, "", "machines", m.getItem(NONE),
                        of('C', chest.getItem(NONE), 'S', SCREW.getMaterialTag(material), 'c', CASING_SOLID_STEEL, 's', SCREWDRIVER.getTag(), 'W', WRENCH.getTag()), "SCS", "Wcs", "SCS");
            }
        });

        AntimatterAPI.all(MultiblockTankMachine.class).forEach(m -> {
            if (m.isSmall()){
                Block block = AntimatterAPI.get(Block.class, m.getMaterial().getId() + "_wall", GTIRef.ID);
                if (block == null) return;
                Material ringMaterial = m.getMaterial() == Wood ? Lead : m.getMaterial();
                TagKey<Item> hammer = m.getMaterial() == Wood ? SOFT_HAMMER.getTag() : HAMMER.getTag();
                provider.addItemRecipe(output, "multiblock_tanks", m.getItem(NONE),
                        of('R', RING.getMaterialTag(ringMaterial), 'S', SAW.getTag(), 'H', hammer, 'W', block.asItem()), " R ", "HWS", " R ");
            } else {
                Block block = AntimatterAPI.get(Block.class, m.getId().replace("large", "small"), GTIRef.ID);
                if (block == null) return;
                provider.addItemRecipe(output, "multiblock_tanks", m.getItem(NONE),
                        of('P', PLATE.getMaterialTag(m.getMaterial()), 'S', SAW.getTag(), 'H', HAMMER.getTag(), 'W', block.asItem()), "PPP", "HWS", "PPP");
            }
        });
        provider.addItemRecipe(output, "item_barrels", GTCoreBlocks.WOOD_ITEM_BARREL.getItem(NONE), of('S', SOFT_HAMMER.getTag(), 'C', ForgeCTags.CHESTS, 'R', ROD_LONG.getMaterialTag(Lead), 'W', ItemTags.PLANKS, 's', SAW.getTag()), "SCs", "WRW", "WRW");
        if (GTCoreBlocks.IRONWOOD_ITEM_BARREL != null) {
            provider.addItemRecipe(output, "item_barrels", GTCoreBlocks.IRONWOOD_ITEM_BARREL.getItem(NONE), of('S', SOFT_HAMMER.getTag(), 'C', ForgeCTags.CHESTS, 'R', ROD_LONG.getMaterialTag(Iron), 'W', PLATE.getMaterialTag(GTCoreMaterials.Ironwood), 's', SAW.getTag()), "SCs", "WRW", "WRW");
        }
    }

    private static void addHatchRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        Arrays.stream(Tier.getAllElectric()).forEach(tier -> {
            Item hull = Item.BY_BLOCK.get(GregTech.get(BlockCasing.class, "hull_" + tier.getId()));
            if (hull == null) return;
            add(HATCH_ITEM_I, tier, (m,item) ->  provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', ForgeCTags.CHESTS)
                            .put('H', hull)
                            .build(), "C", "H"));

            add(HATCH_FLUID_I, tier, (m,item) ->  provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('G', Items.GLASS)
                            .put('H', hull)
                            .build(), "G", "H"));

            add(HATCH_ITEM_O, tier, (m,item) ->  provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', ForgeCTags.CHESTS)
                            .put('H', hull)
                            .build(), "H", "C"));

            add(HATCH_FLUID_O, tier, (m,item) ->  provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('G', Items.GLASS)
                            .put('H', hull)
                            .build(), "H", "G"));

            add(HATCH_ENERGY, tier, (m,item) ->  provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', CABLE_GETTER.apply(PipeSize.VTINY, tier, false))
                            .put('H', hull)
                            .build(), "CH"));
            add(HATCH_DYNAMO, tier, (m,item) ->  provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', CABLE_GETTER.apply(PipeSize.VTINY, tier, false))
                            .put('H', hull)
                            .build(), "HC"));
            add(HATCH_MUFFLER, tier, (m,item) ->  provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', FLUID_PIPE_STEEL.getBlockItem(PipeSize.NORMAL))
                            .put('H', hull)
                            .build(), "H", "C"));
        });
    }

    private static void addMultiblockRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        add(BLAST_FURNACE, LV, (m,item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('L', CABLE_GETTER.apply(PipeSize.VTINY, LV, true))
                        .put('H', CASING_HEAT_PROOF)
                        .put('C', TIER_CIRCUITS.apply(LV))
                        .put('F', Items.FURNACE)
                        .build(), "FFF", "CHC", "LCL"));
        /*provider.addItemRecipe(output, "machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), CHARCOAL_PIT.getItem(CHARCOAL_PIT.getFirstTier()),
                ImmutableMap.<Character, Object>builder()
                        .put('H', CASING_BRICKED_BRONZE)
                        .put('W', NUGGET.getMaterialTag(WroughtIron))
                        .put('F', Items.FLINT).build(), "WHW", "FFF");*/
        provider.addItemRecipe(output, "machines", COKE_OVEN.getItem(COKE_OVEN.getFirstTier()),
                ImmutableMap.<Character, Object>builder()
                        .put('H', FireBrick).build(), "HHH", "H H", "HHH");
        add(COMBUSTION_ENGINE, EV, (m,item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('L', CABLE_TUNGSTEN_STEEL.getBlockItem(PipeSize.VTINY))
                        .put('H', HULL_EV)
                        .put('C', TIER_CIRCUITS.apply(EV))
                        .put('P', PistonEV)
                        .put('G', GEAR.getMaterialTag(Titanium))
                        .put('M', MotorEV)
                        .build(), "PCP", "MHM", "GLG"));
        add(CRACKING_UNIT, HV, (m,item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('P', COVER_PUMP.getItem(HV).getItem())
                        .put('O', COIL_CUPRONICKEL)
                        .put('H', HULL_HV)
                        .put('C', TIER_CIRCUITS.apply(HV))
                        .build(), "OPO", "CHC", "OPO"));
        add(DISTLLATION_TOWER, HV, (m,item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('P', COVER_PUMP.getItem(HV).getItem())
                        .put('I', FLUID_PIPE_STAINLESS_STEEL.getBlock(PipeSize.LARGE))
                        .put('H', HULL_HV)
                        .put('C', TIER_CIRCUITS.apply(HV))
                        .build(), "CIC", "PHP", "CIC"));
        add(CRYO_DISTLLATION_TOWER, HV, (m,item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('P', COVER_PUMP.getItem(HV).getItem())
                        .put('I', FLUID_PIPE_COPPER.getBlock(PipeSize.LARGE))
                        .put('H', HULL_HV)
                        .put('C', TIER_CIRCUITS.apply(HV))
                        .build(), "CIC", "PHP", "CIC"));
        add(HEAT_EXCHANGER, EV, (m,item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('P', COVER_PUMP.getItem(EV).getItem())
                        .put('I', FLUID_PIPE_TITANIUM.getBlock(PipeSize.NORMAL))
                        .put('H', CASING_PIPE_TITANIUM)
                        .build(), "PIP", "IHI", "PIP"));
        add(IMPLOSION_COMPRESSOR, HV, (m,item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('L', CABLE_GETTER.apply(PipeSize.VTINY, HV, true))
                        .put('O', Items.OBSIDIAN)
                        .put('C', TIER_CIRCUITS.apply(HV))
                        .put('S', CASING_SOLID_STEEL)
                        .build(), "OOO", "CSC", "LCL"));
        Arrays.stream(getStandard()).filter(t -> t !=IV).forEach(tier -> {
            Block firebox = tier == LV ? CASING_FIREBOX_BRONZE : tier == MV ? CASING_FIREBOX_STEEL : tier == HV ? CASING_FIREBOX_TITANIUM : CASING_FIREBOX_TUNGSTENSTEEL;
            TagKey<Item> circuit2 = tier == LV ? TIER_CIRCUITS.apply(tier) : tier == MV ? CIRCUITS_ADVANCED : tier == HV ? CIRCUITS_ELITE : CIRCUITS_MASTER;
            add(LARGE_BOILER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('L', CABLE_GETTER.apply(PipeSize.VTINY, tier, true))
                            .put('H', firebox)
                            .put('C', circuit2)
                            .build(), "LCL", "CHC", "LCL"));
        });

        Arrays.stream(new Tier[]{HV, EV, IV}).forEach(tier -> {
            Material gear = tier == HV ? Steel : tier == EV ? StainlessSteel : tier == IV ? Titanium : TungstenSteel;
            Tier pipe = tier == UV ? IV : Tier.getTier(tier.getVoltage() / 4);
            add(LARGE_TURBINE, tier, (m, item) -> {
                provider.addItemRecipe(output, "machines", item,
                        ImmutableMap.of('G', GEAR.getMaterialTag(gear),
                                'H', GregTech.get(BlockCasing.class, "hull_" + tier.getId()),
                                'C', TIER_CIRCUITS.apply(tier),
                                'P', TIER_PIPES.get(pipe).apply(PipeSize.LARGE)), "CGC", "GHG", "PGP");
            });
        });
        add(MULTI_SMELTER, HV, (m,item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('L', CABLE_GETTER.apply(PipeSize.VTINY, HV, false))
                        .put('F', Items.FURNACE)
                        .put('C', TIER_CIRCUITS.apply(HV))
                        .put('H', CASING_HEAT_PROOF)
                        .build(), "FFF", "CHC", "LCL"));
        add(NUCLEAR_REACTOR, EV, (m, item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_EV)
                        .put('A', COVER_ROBOT_ARM.getItem(EV))
                        .put('G', GEAR.get(NiobiumTitanium))
                        .put('P', PLATE.get(Iridium))
                        .put('T', PLATE_DENSE.get(Thorium)).build(), "GTG", "AHA", "PTP"));
        add(OIL_DRILLING_RIG, MV, (m, item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('M', MotorMV)
                        .put('C', TIER_CIRCUITS.apply(MV))
                        .put('H', HULL_MV)
                        .put('F', FRAME.getMaterialTag(Steel)).build(), "FFF", "CHC", "MMM"));
        provider.addItemRecipe(output, "machines", PRIMITIVE_BLAST_FURNACE.getItem(PRIMITIVE_BLAST_FURNACE.getFirstTier()),
                ImmutableMap.<Character, Object>builder()
                        .put('H', FireBrick)
                        .put('C', PLATE.getMaterialTag(Iron)).build(), "HHH", "HCH", "HHH");
        provider.addItemRecipe(output, "machines", PYROLYSIS_OVEN.getItem(PYROLYSIS_OVEN.getFirstTier()),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL_MV)
                        .put('C', CIRCUITS_GOOD)
                        .put('P', COVER_PUMP.getItem(MV).getItem())
                        .put('W', WIRE_CUPRONICKEL.getBlockItem(PipeSize.SMALL))
                        .put('B', PistonMV).build(), "BCW", "CHC", "BPW");
        add(VACUUM_FREEZER, HV, (m,item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('L', CABLE_GETTER.apply(PipeSize.VTINY, HV, true))
                        .put('F', CASING_FROST_PROOF)
                        .put('C', TIER_CIRCUITS.apply(HV))
                        .put('P', COVER_PUMP.getItem(HV).getItem())
                        .build(), "PPP", "CFC", "LCL"));
    }

    private static <T extends Machine<T>> void add(T machine, Tier tier, BiConsumer<T, Item> callback) {
        Item item = machine.getItem(tier);
        if (item != null) callback.accept(machine, item);
    }
}
