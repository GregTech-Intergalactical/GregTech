package muramasa.gregtech.loader.items;

import com.google.common.collect.ImmutableMap;
import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.data.ForgeCTags;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.SubTag;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.GregTechConfig;
import muramasa.gregtech.data.GregTechBlocks;
import muramasa.gregtech.data.GregTechCovers;
import muramasa.gregtech.data.GregTechItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractGraphWrappers;

import java.util.function.Consumer;

import static io.github.gregtechintergalactical.gtcore.data.GTCoreItems.*;
import static io.github.gregtechintergalactical.gtcore.data.GTCoreTags.*;
import static muramasa.antimatter.Ref.L;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.machine.Tier.LV;
import static muramasa.antimatter.machine.Tier.MV;
import static muramasa.gregtech.data.GregTechMaterialTags.SOLDER;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.RecipeMaps.*;
import static muramasa.gregtech.data.TierMaps.*;

public class Circuitry {
    public static void loadCraftingRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        // MANUAL COATED BOARD CRAFTING
        provider.addStackRecipe(output, GTIRef.ID, "", "board_basic", new ItemStack(CircuitBoardCoated, 3),
                ImmutableMap.<Character, Object>builder()
                        .put('R', GTCoreItems.StickyResin)
                        .put('P', PLATE.get(Wood))
                        .build(),
                " R ", "PPP", " R ");
        if (GregTechConfig.HARDER_CIRCUITS){
            bloodyCircuits(output, provider);
        } else {
            circuits(output, provider);
        }
    }

    private static void bloodyCircuits(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        // MANUAL TIER 0 CIRCUIT CRAFTING
        provider.addItemRecipe(output, "circuit_basic", CircuitBasic,
                ImmutableMap.<Character, Object>builder()
                        .put('V', VacuumTube).put('B', CircuitBoardCoated)
                        .put('W', GregTechBlocks.WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY))
                        .put('R',Resistor).put('P', ITEM_CASING.get(Steel))
                        .build(),
                "RPR", "VBV", "WWW");
        provider.addItemRecipe(output, "circuits", CircuitGood,
                ImmutableMap.<Character, Object>builder()
                        .put('S', ITEM_CASING.getMaterialTag(Steel))
                        .put('C', CIRCUITS_BASIC)
                        .put('c', GregTechBlocks.WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY))
                        .put('D', Diode).build(), "SCc", "CDC", "cCS");

        var wire = TagUtils.getItemTag(new ResourceLocation(GTIRef.ANTIMATTER, SubTag.COPPER_WIRE.getId()+"_"+ PipeSize.VTINY.getId()));
        // MANUAL VAC TUBE CRAFTING
        provider.addItemRecipe(output, "vac_tube", VacuumTube,
                ImmutableMap.<Character, Object>builder()
                        .put('G', GlassTube)
                        .put('P', Items.PAPER)
                        .put('W', wire)
                        .build(),
                "PGP", "WWW");

        provider.addItemRecipe(output, GTIRef.ID, "vacuum_tube_1", "vac_tube", VacuumTube,
                ImmutableMap.<Character, Object>builder()
                        .put('G', GlassTube)
                        .put('P', Items.PAPER)
                        .put('W', WIRE_FINE.getMaterialTag(Copper))
                        .build(),
                "PGP", "WWW");

        // MANUAL RESISTOR CRAFTING
        provider.addItemRecipe(output, "resistor", Resistor,
                ImmutableMap.<Character, Object>builder()
                        .put('C', DUST_COALS)
                        .put('P', Items.PAPER)
                        .put('W', WIRE_FINE.getMaterialTag(Copper))
                        .build(),
                " P ", "WCW", " P ");
        provider.addItemRecipe(output, GTIRef.ID, "", "diodes", Diode,
                ImmutableMap.<Character, Object>builder()
                        .put('B', ForgeCTags.DYES_BLACK)
                        .put('T', GregTechBlocks.WIRE_TIN.getBlockItem(PipeSize.VTINY))
                        .put('W', GregTechItems.Wafer)
                        .put('G', ForgeCTags.GLASS_PANES).build(), "BG ", "TWT", "BG ");
        provider.addItemRecipe(output, GTIRef.ID, "diode_2", "diodes", Diode,
                ImmutableMap.<Character, Object>builder()
                        .put('B', ForgeCTags.DYES_BLACK)
                        .put('T', WIRE_FINE.getMaterialTag(Tin))
                        .put('W', GregTechItems.Wafer)
                        .put('G', ForgeCTags.GLASS_PANES).build(), "BG ", "TWT", "BG ");
        provider.addStackRecipe(output, GTIRef.ID, "diode_3", "diodes", new ItemStack(Diode),
                ImmutableMap.<Character, Object>builder()
                        .put('B', ForgeCTags.DYES_BLACK)
                        .put('T', GregTechBlocks.WIRE_TIN.getBlockItem(PipeSize.VTINY))
                        .put('W', DUST_TINY.getMaterialTag(Gallium))
                        .put('G', ForgeCTags.GLASS_PANES).build(), "BG ", "TWT", "BG ");
        provider.addStackRecipe(output, GTIRef.ID, "diode_4", "diodes", new ItemStack(Diode),
                ImmutableMap.<Character, Object>builder()
                        .put('B', ForgeCTags.DYES_BLACK)
                        .put('T', WIRE_FINE.getMaterialTag(Tin))
                        .put('W', DUST_TINY.getMaterialTag(Gallium))
                        .put('G', ForgeCTags.GLASS_PANES).build(), "BG ", "TWT", "BG ");
        provider.addStackRecipe(output, GTIRef.ID, "", "small_coils", new ItemStack(GregTechItems.SmallCoil, 2),
                ImmutableMap.of('W', WIRE_FINE.getMaterialTag(Copper), 'B', BOLT.getMaterialTag(Steel)), "WWW", "WBW", "WWW");
        provider.addStackRecipe(output, GTIRef.ID, "small_coil_1", "small_coils", new ItemStack(GregTechItems.SmallCoil, 4),
                ImmutableMap.of('W', WIRE_FINE.getMaterialTag(Copper), 'B', BOLT.getMaterialTag(NickelZincFerrite)), "WWW", "WBW", "WWW");
    }

    private static void circuits(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        provider.addItemRecipe(output, GTIRef.ID, "circuit_basic_h", "circuits", CircuitBasic,
                ImmutableMap.<Character, Object>builder()
                        .put('C', CABLE_GETTER.apply(PipeSize.VTINY, MV, false))
                        .put('N', NandChip)
                        .put('S', CircuitBoardCoated)
                        .build(), "CCC", "NSN", "CCC");
        provider.addItemRecipe(output, GTIRef.ID, "circuit_basic_v", "circuits", CircuitBasic,
                ImmutableMap.<Character, Object>builder()
                        .put('C', CABLE_GETTER.apply(PipeSize.VTINY, MV, false))
                        .put('N', NandChip)
                        .put('S', CircuitBoardCoated)
                        .build(), "CNC", "CSC", "CNC");
        provider.addItemRecipe(output, GTIRef.ID, "", "circuits", NandChip,
                ImmutableMap.of('C', ITEM_CASING.getMaterialTag(Steel), 'R', GregTechBlocks.WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY), 'T', GregTechBlocks.WIRE_TIN.getBlockItem(PipeSize.VTINY)), "CR", "RT");
        provider.addItemRecipe(output, GTIRef.ID, "lapotron_crystal_upgrade", "energy_orbs", GTCoreItems.LapotronCrystal,
                ImmutableMap.of('C', CIRCUITS_ADVANCED, 'L', DUST_LAPIS_LAZURITE, 'E', GTCoreItems.EnergyCrystal), "LCL", "LEL", "LCL");
        provider.addItemRecipe(output, GTIRef.ID, "", "energy_orbs", GTCoreItems.LapotronCrystal,
                ImmutableMap.of('C', CIRCUITS_DATA, 'L', DUST_LAPIS_LAZURITE, 'S', GEM.getMaterialTag(Sapphire)), "LCL", "LSL", "LCL");
    }

    public static void init() {

        silicon();
        //phenolic
        ASSEMBLER.RB().ii(DUST.getMaterialIngredient(Wood, 1), RecipeIngredient.of(MoldPlate, 1).setNoConsume())
                .fi(Glue.getLiquid(100))
                .io(new ItemStack(CircuitBoardPhenolic,8))
                .add("phenolic_circuit_board",30, 8);
        if (!GregTechConfig.HARDER_CIRCUITS){
            boards();
            circuitParts();
            circuits();
        } else {
            bloodyBoards();
            bloodyCircuitParts();
            bloodyCircuits();
        }
    }

    private static void silicon(){
        E_BLAST_FURNACE.RB().temperature(1784).ii(DUST.getMaterialIngredient(Silicon, 16), INT_CIRCUITS.get(16)).fi(Helium.getGas(1000)).io(GregTechItems.SiliconBoule).add("silicon_boule", 9000, 120);
        addCuttingRecipe(GregTechItems.SiliconBoule, GregTechItems.Wafer, 16, 1600, 384, 1);
        addCuttingRecipe(GregTechItems.Wafer, GregTechItems.SiliconChip, 8, 600, 48, 22);
        if (GregTechConfig.HARDER_CIRCUITS){
            E_BLAST_FURNACE.RB().temperature(2484).ii(DUST.getMaterialIngredient(Silicon, 16), DUST.getMaterialIngredient(Glowstone, 1)).fi(Nitrogen.getGas(1000)).io(GregTechItems.GlowstoneDopedSiliconBoule).add("glowstone_doped_silicon_boule", 12000, 480);
            E_BLAST_FURNACE.RB().temperature(2484).ii(DUST.getMaterialIngredient(Silicon, 16), DUST.getMaterialIngredient(Naquadah, 1)).fi(Argon.getGas(1000)).io(GregTechItems.NaquadahDopedSiliconBoule).add("naquadah_doped_silicon_boule", 15000, 1920);
            addCuttingRecipe(GregTechItems.GlowstoneDopedSiliconBoule, GregTechItems.GlowstoneDopedWafer, 32, 800, 64, 20);
            addCuttingRecipe(GregTechItems.NaquadahDopedSiliconBoule, GregTechItems.NaquadahDopedWafer, 64, 1600, 64, 240);
            addLensRecipe(GregTechItems.NaquadahDopedWafer, GregTechItems.ASoCWafer, 1, 200, 1920, Amber, Topaz);
            addCuttingRecipe(GregTechItems.ASoCWafer, GregTechItems.ASoC, 8, 600, 48, 22);
            addLensRecipe(GregTechItems.Wafer, GregTechItems.CentralProcessingUnitWafer, 1, 900, 120, Diamond, Glass, Dilithium);
            addLensRecipe(GregTechItems.GlowstoneDopedWafer, GregTechItems.CentralProcessingUnitWafer, 4, 500, 480, Diamond, Glass, Dilithium);
            addLensRecipe(GregTechItems.NaquadahDopedWafer, GregTechItems.CentralProcessingUnitWafer, 8, 200, 1920, Diamond, Glass, Dilithium);
            addCuttingRecipe(GregTechItems.CentralProcessingUnitWafer, GregTechItems.CentralProcessingUnit, 8, 600, 48, 22);
            CHEMICAL_REACTOR.RB().ii(of(GregTechItems.PICWafer), DUST.getMaterialIngredient(IndiumGalliumPhosphide, 2)).fi(RedAlloy.getLiquid(L * 2)).io(GregTechItems.HPICWafer).add("hpic_wafer", 1200, 1920);
            addCuttingRecipe(GregTechItems.HPICWafer, GregTechItems.HighPowerIC, 2, 600, 48, 22);
            addLensRecipe(GregTechItems.Wafer, GregTechItems.IntegratedLogicCircuitWafer, 1, 900, 120, Ruby, RedGarnet, Jade);
            addLensRecipe(GregTechItems.GlowstoneDopedWafer, GregTechItems.IntegratedLogicCircuitWafer, 4, 500, 480, Ruby, RedGarnet, Jade);
            addLensRecipe(GregTechItems.NaquadahDopedWafer, GregTechItems.IntegratedLogicCircuitWafer, 8, 200, 1920, Ruby, RedGarnet, Jade);
            addCuttingRecipe(GregTechItems.IntegratedLogicCircuitWafer, GregTechItems.IntegratedLogicCircuit, 8, 600, 48, 22);
            addLensRecipe(GregTechItems.GlowstoneDopedWafer, GregTechItems.NANDMemoryChipWafer, 1, 500, 480, EnderPearl);
            addLensRecipe(GregTechItems.NaquadahDopedWafer, GregTechItems.NANDMemoryChipWafer, 4, 200, 1920, EnderPearl);
            addCuttingRecipe(GregTechItems.NANDMemoryChipWafer, GregTechItems.NANDMemoryChip, 32, 600, 48, 22);
            CHEMICAL_REACTOR.RB().ii(of(GregTechItems.CentralProcessingUnitWafer), of(CarbonFibre, 16)).fi(Glowstone.getLiquid(L * 4)).io(GregTechItems.NanoCpuWafer).add("nano_cpu_wafer", 400, 1920);
            addCuttingRecipe(GregTechItems.NanoCpuWafer, GregTechItems.NanoCpu, 7, 600, 48, 22);
            addLensRecipe(GregTechItems.GlowstoneDopedWafer, GregTechItems.NorMemoryChipWafer, 1, 500, 480, EnderEye);
            addLensRecipe(GregTechItems.NaquadahDopedWafer, GregTechItems.NorMemoryChipWafer, 4, 200, 1920, EnderEye);
            addCuttingRecipe(GregTechItems.NorMemoryChipWafer, GregTechItems.NorMemoryChip, 16, 600, 48, 22);
            addLensRecipe(GregTechItems.GlowstoneDopedWafer, GregTechItems.PICWafer, 1, 500, 480, Opal, Sapphire, BlueTopaz);
            addLensRecipe(GregTechItems.NaquadahDopedWafer, GregTechItems.PICWafer, 4, 200, 1920, Opal, Sapphire, BlueTopaz);
            addCuttingRecipe(GregTechItems.PICWafer, GregTechItems.PowerIC, 4, 600, 48, 22);
            CHEMICAL_REACTOR.RB().ii(of(GregTechItems.NanoCpuWafer), of(GregTechItems.QuantumEye, 2)).fi(GalliumArsenide.getLiquid(L * 2)).io(GregTechItems.QBitWafer).add("qbit_wafer", 400, 1920);
            CHEMICAL_REACTOR.RB().ii(of(GregTechItems.NanoCpuWafer), DUST.getMaterialIngredient(IndiumGalliumPhosphide, 1)).fi(Radon.getGas(50)).io(GregTechItems.QBitWafer).add("qbit_wafer_2", 600, 1920);
            addCuttingRecipe(GregTechItems.QBitWafer, GregTechItems.QBitProcessingUnit, 5, 600, 48, 22);
            addLensRecipe(GregTechItems.Wafer, GregTechItems.RandomAccessMemoryChipWafer, 1, 900, 120, GreenSapphire);
            addLensRecipe(GregTechItems.GlowstoneDopedWafer, GregTechItems.RandomAccessMemoryChipWafer, 4, 500, 480, GreenSapphire);
            addLensRecipe(GregTechItems.NaquadahDopedWafer, GregTechItems.RandomAccessMemoryChipWafer, 8, 200, 1920, GreenSapphire);
            addCuttingRecipe(GregTechItems.RandomAccessMemoryChipWafer, GregTechItems.RandomAccessMemoryChip, 32, 600, 48, 22);
            addLensRecipe(GregTechItems.GlowstoneDopedWafer, GregTechItems.SOCWafer, 1, 500, 480, YellowGarnet);
            addLensRecipe(GregTechItems.NaquadahDopedWafer, GregTechItems.SOCWafer, 4, 200, 1920, YellowGarnet);
            addCuttingRecipe(GregTechItems.SOCWafer, GregTechItems.SOC, 10, 600, 48, 22);

        }
    }

    private static void addLensRecipe(ItemBasic<?> input, ItemBasic<?> output, int count, int ticks, int power, Material... lenses){
        String extra = input.getId().replace("doped_", "").replace("wafer", "");
        for (Material lens : lenses){
            LASER_ENGRAVER.RB().ii(of(input), LENS.getMaterialIngredient(lens, 1).setNoConsume()).io(new ItemStack(output, count)).add(output.getId() + "_" + extra + lens.getId(), ticks, power);
        }
    }

    private static void addCuttingRecipe(Item input, ItemBasic<?> output, int amount, int ticks, int power, int liquidMultiplier){
        CUTTER.RB().ii(RecipeIngredient.of(input, 1))
                .fi(FluidPlatformUtils.createFluidStack(Fluids.WATER, 5 * liquidMultiplier * TesseractGraphWrappers.dropletMultiplier))
                .io(new ItemStack(output, amount)).add(output.getId() + "_with_water", ticks, power);
        CUTTER.RB().ii(RecipeIngredient.of(input, 1))
                .fi(DistilledWater.getLiquid(3 * liquidMultiplier))
                .io(new ItemStack(output, amount)).add(output.getId() + "_with_distilled_water", ticks, power);
        CUTTER.RB().ii(RecipeIngredient.of(input, 1))
                .fi(Lubricant.getLiquid(liquidMultiplier))
                .io(new ItemStack(output, amount)).add(output.getId() + "_with_lubricant", ticks / 2, power);
    }

    private static void circuitParts(){
        ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Lazurite, 1), DUST.getMaterialIngredient(AntimatterMaterials.Glowstone, 1)).io(new ItemStack(AdvCircuitParts, 2)).add("advanced_circuit_parts", 32, 64);
        ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Lapis, 1), DUST.getMaterialIngredient(AntimatterMaterials.Glowstone, 1)).io(new ItemStack(AdvCircuitParts, 2)).add("advanced_circuit_parts_1", 32, 64);
        ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Plastic, 1), of(GregTechBlocks.WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY), 1)).fi(Tin.getLiquid(L / 4)).io(new ItemStack(NandChip)).add("nand_chip_tin_poly", 32, 16);
        ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Plastic, 1), of(GregTechBlocks.WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY), 1)).fi(SolderingAlloy.getLiquid(L / 8)).io(new ItemStack(NandChip)).add("nand_chip_soldering_alloy_poly", 32, 16);
        ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Plastic, 1), of(GregTechBlocks.WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY), 1)).fi(Lead.getLiquid(L / 2)).io(new ItemStack(NandChip)).add("nand_chip_lead_poly", 32, 16);
        ASSEMBLER.RB().ii(ITEM_CASING.getMaterialIngredient(Steel, 1), of(GregTechBlocks.WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY), 2)).fi(Tin.getLiquid(L / 4)).io(new ItemStack(NandChip)).add("nand_chip_tin_steel", 32, 16);
        ASSEMBLER.RB().ii(ITEM_CASING.getMaterialIngredient(Steel, 1), of(GregTechBlocks.WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY), 2)).fi(SolderingAlloy.getLiquid(L / 8)).io(new ItemStack(NandChip)).add("nand_chip_soldering_alloy_steel", 32, 16);
        ASSEMBLER.RB().ii(ITEM_CASING.getMaterialIngredient(Steel, 1), of(GregTechBlocks.WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY), 2)).fi(Lead.getLiquid(L / 2)).io(new ItemStack(NandChip)).add("nand_chip_lead_steel", 32, 16);
    }

    private static void boards(){
        ASSEMBLER.RB().ii(DUST.getMaterialIngredient(Silicon, 1), PLATE.getMaterialIngredient(Plastic, 1)).io(new ItemStack(CircuitBoardEmpty)).add("empty_circuit_board", 32, 16);
        FORMING_PRESS.RB().ii(of(CircuitBoardEmpty), of(EtchedWiringMV, 4)).io(new ItemStack(CircuitBoardBasic)).add("basic_circuit_board", 32, 16);
        FORMING_PRESS.RB().ii(of(CircuitBoardEmpty), of(EtchedWiringHV, 4)).io(new ItemStack(CircuitBoardAdvanced)).add("advanced_circuit_board", 32, 16);
        FORMING_PRESS.RB().ii(of(CircuitBoardProcessorEmpty), of(EtchedWiringEV, 4)).io(new ItemStack(CircuitBoardProcessor)).add("processor_circuit_board", 32, 256);
        ASSEMBLER.RB().ii(of(GregTechItems.Wafer, 2), PLATE.getMaterialIngredient(Polytetrafluoroethylene, 1)).io(new ItemStack(CircuitBoardProcessorEmpty)).add("empty_processor_circuit_board", 32, 256);
    }

    private static void circuits(){
        ASSEMBLER.RB().ii(of(CircuitBoardBasic), of(NandChip, 2)).fi(Tin.getLiquid(L / 2)).io(new ItemStack(CircuitBasic)).add("basic_circuit_tin", 32, 16);
        ASSEMBLER.RB().ii(of(CircuitBoardBasic), of(NandChip, 2)).fi(SolderingAlloy.getLiquid(L / 4)).io(new ItemStack(CircuitBasic)).add("basic_circuit_soldering_alloy", 32, 16);
        ASSEMBLER.RB().ii(of(CircuitBoardBasic), of(NandChip, 2)).fi(Lead.getLiquid(L)).io(new ItemStack(CircuitBasic)).add("basic_circuit_lead", 32, 16);
        ASSEMBLER.RB().ii(of(CIRCUITS_BASIC), of(NandChip, 2)).fi(Tin.getLiquid(L / 2)).io(new ItemStack(CircuitGood)).add("good_circuit_tin", 32, 16);
        ASSEMBLER.RB().ii(of(CIRCUITS_BASIC), of(NandChip, 2)).fi(SolderingAlloy.getLiquid(L / 4)).io(new ItemStack(CircuitGood)).add("good_circuit_soldering_alloy", 32, 16);
        ASSEMBLER.RB().ii(of(CIRCUITS_BASIC), of(NandChip, 2)).fi(Lead.getLiquid(L)).io(new ItemStack(CircuitGood)).add("good_circuit_lead", 32, 16);
        ASSEMBLER.RB().ii(of(CircuitBoardAdvanced), of(AdvCircuitParts, 2)).fi(Tin.getLiquid(L)).io(new ItemStack(CircuitAdv)).add("advanced_circuit_tin", 32, 64);
        ASSEMBLER.RB().ii(of(CircuitBoardAdvanced), of(AdvCircuitParts, 2)).fi(SolderingAlloy.getLiquid(L / 2)).io(new ItemStack(CircuitAdv)).add("advanced_circuit_soldering_alloy", 32, 64);
        ASSEMBLER.RB().ii(of(CircuitBoardAdvanced), of(AdvCircuitParts, 2)).fi(Lead.getLiquid(L * 2)).io(new ItemStack(CircuitAdv)).add("adv_storage_circuit_lead", 32, 64);
        ASSEMBLER.RB().ii(of(CircuitBoardEpoxy), of(EngravedCrystalChip)).fi(Tin.getLiquid(L)).io(new ItemStack(CircuitDataStorage)).add("data_storage_circuit_tin", 32, 64);
        ASSEMBLER.RB().ii(of(CircuitBoardEpoxy), of(EngravedCrystalChip)).fi(SolderingAlloy.getLiquid(L / 2)).io(new ItemStack(CircuitDataStorage)).add("data_storage__circuit_soldering_alloy", 32, 64);
        ASSEMBLER.RB().ii(of(CircuitBoardEpoxy), of(EngravedCrystalChip)).fi(Lead.getLiquid(L * 2)).io(new ItemStack(CircuitDataStorage)).add("data_storage_circuit_lead", 32, 64);
        ASSEMBLER.RB().ii(of(CircuitBoardProcessor), of(CircuitDataStorage, 3)).fi(Tin.getLiquid(L * 2)).io(new ItemStack(CircuitDataControl)).add("data_control_circuit_tin", 32, 256);
        ASSEMBLER.RB().ii(of(CircuitBoardProcessor), of(CircuitDataStorage, 3)).fi(SolderingAlloy.getLiquid(L)).io(new ItemStack(CircuitDataControl)).add("data_control_circuit_soldering_alloy", 32, 256);
        ASSEMBLER.RB().ii(of(CircuitBoardProcessor), of(CircuitDataStorage, 3)).fi(Lead.getLiquid(L * 4)).io(new ItemStack(CircuitDataControl)).add("data_control_circuit_lead", 32, 256);
        ASSEMBLER.RB().ii(of(CircuitBoardProcessor), of(EngravedLapotronChip, 3)).fi(Tin.getLiquid(L * 2)).io(new ItemStack(CircuitEnergyFlow)).add("energy_flow_circuit_tin", 32, 256);
        ASSEMBLER.RB().ii(of(CircuitBoardProcessor), of(EngravedLapotronChip, 3)).fi(SolderingAlloy.getLiquid(L)).io(new ItemStack(CircuitEnergyFlow)).add("energy_flow_circuit_soldering_alloy", 32, 256);
        ASSEMBLER.RB().ii(of(CircuitBoardProcessor), of(EngravedLapotronChip, 3)).fi(Lead.getLiquid(L * 4)).io(new ItemStack(CircuitEnergyFlow)).add("energy_flow_circuit_lead", 32, 256);
        ASSEMBLER.RB().ii(of(CIRCUITS_ELITE, 2), of(EngravedCrystalChip, 18)).io(DataOrb).add("data_orb", 25 * 20, 256);
        ASSEMBLER.RB().ii(of(CIRCUITS_MASTER, 2), of(EngravedLapotronChip, 18)).io(BatteryEnergyOrb).add("lapotronic_energy_orb", 25 * 20, 1024);
        ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Europium, 4), of(BatteryEnergyOrb, 8)).io(BatteryEnergyOrbCluster).add("lapotronic_energy_orb_cluster", 102 * 20, 4096);
        ASSEMBLER.RB().ii(of(CIRCUITS_ADVANCED), of(NandChip, 2), of(AdvCircuitParts, 2)).fi(Lead.getLiquid(L * 2)).io(GregTechItems.DataStick).add("data_stick_lead", 120, 64);
        ASSEMBLER.RB().ii(of(CIRCUITS_ADVANCED), of(NandChip, 2), of(AdvCircuitParts, 2)).fi(Tin.getLiquid(L)).io(GregTechItems.DataStick).add("data_stick_tin", 120, 64);
        ASSEMBLER.RB().ii(of(CIRCUITS_ADVANCED), of(NandChip, 2), of(AdvCircuitParts, 2)).fi(SolderingAlloy.getLiquid(L/2)).io(GregTechItems.DataStick).add("data_stick_soldering_alloy", 120, 64);
        CHEMICAL_REACTOR.RB().ii(FOIL.getMaterialIngredient(Copper, 4)).fi(EpoxyResin.getLiquid(L)).io(CircuitBoardEpoxy).add("epoxy_circuit_board", 500, 10);
    }

    private static void bloodyBoards() {
        //Plastic
        CHEMICAL_REACTOR.RB().ii(PLATE.getMaterialIngredient(Plastic, 1), FOIL.getMaterialIngredient(Copper, 1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(CircuitBoardPlastic,1))
                .add("plastic_circuit_board",25*20, 10);
        CHEMICAL_REACTOR.RB().ii(PLATE.getMaterialIngredient(PolyvinylChloride, 1), FOIL.getMaterialIngredient(Copper, 1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(CircuitBoardPlastic,2))
                .add("plastic_circuit_board_2",25*20, 10);
        CHEMICAL_REACTOR.RB().ii(PLATE.getMaterialIngredient(Polytetrafluoroethylene, 1), FOIL.getMaterialIngredient(Copper, 1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(CircuitBoardPlastic,4))
                .add("plastic_circuit_board_4",25*20, 10);
        //Epoxy
        CHEMICAL_REACTOR.RB().ii(PLATE.getMaterialIngredient(EpoxyResin, 1), FOIL.getMaterialIngredient(Copper, 1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(CircuitBoardEpoxy,1))
                .add("epoxy_circuit_board",25*20, 10);
        //Fiber
        CHEMICAL_REACTOR.RB().ii(PLATE.getMaterialIngredient(FiberReinforcedEpoxyResin, 1), FOIL.getMaterialIngredient(Copper, 1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(CircuitBoardFiber,1))
                .add("fiber_circuit_board",25*20, 10);
        //MultiFiber
        CHEMICAL_REACTOR.RB().ii(of(CircuitBoardFiber,1), FOIL.getMaterialIngredient(Electrum, 16))
                .fi(SulfuricAcid.getLiquid(250))
                .io(new ItemStack(CircuitBoardMultiFiber,1))
                .add("multi_fiber_circuit_board",5*20, 480);
        //Wetware
        ASSEMBLER.RB().ii(of(CircuitBoardMultiFiber,1), of(CIRCUITS_GOOD,1), of(GregTechItems.PetriDish), of(GregTechItems.SensorLV), of(GregTechCovers.COVER_PUMP.getItem(LV)))
                .fi(Polystyrene.getLiquid(144))
                .io(new ItemStack(CircuitBoardWetware,1))
                .add("wetware_circuit_board",8*20, 32768);
    }

    private static void bloodyCircuitParts(){
        ASSEMBLER.RB().ii(of(GregTechItems.SiliconChip, 1), WIRE_FINE.getMaterialIngredient(Tin, 6)).fi(Plastic.getLiquid(L)).io(new ItemStack(Transistor,8)).add("transistor",80, 24);
        ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Plastic, 1), FOIL.getMaterialIngredient(Aluminium, 2)).io(new ItemStack(Capacitor, 2)).add("capacitor", 80, 96);
        ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Gallium, 1), WIRE_FINE.getMaterialIngredient(AnnealedCopper, 6)).fi(Plastic.getLiquid(L * 2)).io(new ItemStack(SMDTransistor,32)).add("smd_transistor",80, 96);
        ASSEMBLER.RB().ii(of(DUST.get(Carbon), 1), of(WIRE_FINE.get(Electrum), 4)).fi(Plastic.getLiquid(L)).io(new ItemStack(SMDResistor,24)).add("smd_resistor",80, 96);
        ASSEMBLER.RB().ii(FOIL.getMaterialIngredient(PolyvinylChloride, 4), FOIL.getMaterialIngredient(Tantalum, 1)).fi(Plastic.getLiquid(L / 4)).io(new ItemStack(SMDCapacitor,32)).add("smd_capacitor_tantalum",50, 96);
        ASSEMBLER.RB().ii(FOIL.getMaterialIngredient(PolyvinylChloride, 4), FOIL.getMaterialIngredient(Aluminium, 1)).fi(Plastic.getLiquid(L / 4)).io(new ItemStack(SMDCapacitor,16)).add("smd_capacitor_aluminium",50, 96);
        ASSEMBLER.RB().ii(FOIL.getMaterialIngredient(SiliconeRubber, 4), FOIL.getMaterialIngredient(Tantalum, 1)).fi(Plastic.getLiquid(L / 4)).io(new ItemStack(SMDCapacitor,32)).add("smd_capacitor_tantalum_rubber",60, 120);
        ASSEMBLER.RB().ii(FOIL.getMaterialIngredient(SiliconeRubber, 4), FOIL.getMaterialIngredient(Aluminium, 1)).fi(Plastic.getLiquid(L / 4)).io(new ItemStack(SMDCapacitor,16)).add("smd_capacitor_aluminium_rubber",60, 120);
        ASSEMBLER.RB().ii(WIRE_FINE.getMaterialIngredient(AnnealedCopper, 4), DUST_SMALL.getMaterialIngredient(Gallium, 1)).fi(Plastic.getLiquid(L * 2)).io(new ItemStack(Diode, 16)).add("diode", 400, 48);
        ASSEMBLER.RB().ii(WIRE_FINE.getMaterialIngredient(Copper, 8), BOLT.getMaterialIngredient(Steel, 1)).io(new ItemStack(GregTechItems.SmallCoil, 2)).add("small_coil_1", 80, 8);
        ASSEMBLER.RB().ii(WIRE_FINE.getMaterialIngredient(Copper, 8), BOLT.getMaterialIngredient(NickelZincFerrite, 1)).io(new ItemStack(GregTechItems.SmallCoil, 4)).add("small_coil_2", 80, 8);
        ASSEMBLER.RB().ii(WIRE_FINE.getMaterialIngredient(AnnealedCopper, 8), BOLT.getMaterialIngredient(Steel, 1)).io(new ItemStack(GregTechItems.SmallCoil, 2)).add("small_coil_3", 80, 8);
        ASSEMBLER.RB().ii(WIRE_FINE.getMaterialIngredient(AnnealedCopper, 8), BOLT.getMaterialIngredient(NickelZincFerrite, 1)).io(new ItemStack(GregTechItems.SmallCoil, 4)).add("small_coil_4", 80, 8);
    }

    private static void bloodyCircuits() {
        for (Material material : SOLDER.all()) {
            long base = L / 8;
            boolean hasGood = SOLDER.has(SubTag.GOOD_SOLDER, material);
            boolean hasBad = SOLDER.has(SubTag.BAD_SOLDER, material);
            base *= hasBad ? (hasGood ? 2 : 4) : 1;
            //Basic
            CIRCUIT_ASSEMBLER.RB().ii(of(CircuitBoardPhenolic, 1), of(RESISTORS, 2),
                            WIRE_FINE.getMaterialIngredient(Copper, 4), of(GregTechItems.IntegratedLogicCircuit, 1))
                    .io(new ItemStack(CircuitBasic,1))
                    .fi(material.getLiquid(base * 4)).add("basic_circuit_using_" + material.getId(),200, 8);
            CIRCUIT_ASSEMBLER.RB().ii(of(CircuitBoardPlastic), of(GregTechItems.IntegratedLogicCircuit, 4),
                            of(RESISTORS, 4), of(CAPACITORS, 4),
                            of(TRANSISTORS, 4), WIRE_FINE.getMaterialIngredient(Copper, 2))
                    .io(new ItemStack(GregTechItems.MicroProcessor, 4))
                    .fi(material.getLiquid(base * 4)).add("microprocessor_using_" + material.getId(), 200, 60);
            CIRCUIT_ASSEMBLER.RB().ii(of(CircuitBoardPlastic), of(GregTechItems.SOC, 4), WIRE_FINE.getMaterialIngredient(Copper, 2))
                    .io(new ItemStack(GregTechItems.MicroProcessor, 4))
                    .fi(material.getLiquid(base * 4)).add("microprocessor_soc_using_" + material.getId(), 200, 60);
            //Good
            CIRCUIT_ASSEMBLER.RB().ii(of(CircuitBoardPhenolic, 1), of(RESISTORS, 4),of(CircuitBasic, 3),
                            WIRE_FINE.getMaterialIngredient(Electrum, 8))
                    .io(new ItemStack(CircuitGood,1))
                    .fi(material.getLiquid(base * 4)).add("good_circuit_using_" + material.getId(),20*20, 16);
            CIRCUIT_ASSEMBLER.RB().ii(of(CircuitBoardPlastic), of(GregTechItems.CentralProcessingUnit, 1),
                            of(RESISTORS, 2), of(CAPACITORS, 2),
                            of(TRANSISTORS, 2), WIRE_FINE.getMaterialIngredient(RedAlloy, 2))
                    .io(new ItemStack(GregTechItems.IntegratedProcessor, 4))
                    .fi(material.getLiquid(base * 4)).add("integrated_processor_using_" + material.getId(), 200, 60);
            CIRCUIT_ASSEMBLER.RB().ii(of(CircuitBoardPlastic), of(GregTechItems.SOC, 1), WIRE_FINE.getMaterialIngredient(RedAlloy, 2))
                    .io(new ItemStack(GregTechItems.IntegratedProcessor, 1))
                    .fi(material.getLiquid(base * 4)).add("integrated_processor_soc_using_" + material.getId(), 200, 60);
            //Advanced
            CIRCUIT_ASSEMBLER.RB().ii(of(CircuitGood, 2), of(GregTechItems.IntegratedLogicCircuit, 3),of(GregTechItems.RandomAccessMemoryChip, 1),
                            of(TRANSISTORS, 4), WIRE_FINE.getMaterialIngredient(Electrum, 16))
                    .io(new ItemStack(CircuitAdv,1))
                    .fi(material.getLiquid(base * 4)).add("advanced_circuit_using_" + material.getId(),800, 28);
            CIRCUIT_ASSEMBLER.RB().ii(of(CircuitBoardPlastic), of(GregTechItems.IntegratedProcessor, 2), of(GregTechItems.SmallCoil, 4),
                    of(CAPACITORS, 4), of(GregTechItems.RandomAccessMemoryChip, 4), WIRE_FINE.getMaterialIngredient(RedAlloy, 12))
                    .io(GregTechItems.ProcessorAssembly)
                    .fi(material.getLiquid(base * 4)).add("processor_assembly_using_" + material.getId(), 200, 600);
            CIRCUIT_ASSEMBLER.RB().ii(of(CircuitBoardEpoxy), of(GregTechItems.NanoCpu, 1),
                            of(RESISTORS, 2), of(CAPACITORS, 2),
                            of(TRANSISTORS, 2), WIRE_FINE.getMaterialIngredient(Electrum, 2))
                    .io(new ItemStack(GregTechItems.NanoProcessor))
                    .fi(material.getLiquid(base * 4)).add("nano_processor_using_" + material.getId(), 200, 600);
            CIRCUIT_ASSEMBLER.RB().ii(of(CircuitBoardPlastic), of(GregTechItems.ASoC, 1), WIRE_FINE.getMaterialIngredient(Electrum, 2))
                    .io(new ItemStack(GregTechItems.NanoProcessor))
                    .fi(material.getLiquid(base * 4)).add("nano_processor_asoc_using_" + material.getId(), 300, 8192);

            //Extreme
            CIRCUIT_ASSEMBLER.RB().ii(of(CircuitBoardPlastic, 2), of(GregTechItems.ProcessorAssembly, 3), of(DIODES, 4),
                            of(GregTechItems.RandomAccessMemoryChip, 4), WIRE_FINE.getMaterialIngredient(Electrum, 6))
                    .io(new ItemStack(GregTechItems.Workstation))
                    .fi(material.getLiquid(base * 8)).add("workstation_using_" + material.getId(), 400, 90);
            CIRCUIT_ASSEMBLER.RB().ii(of(CircuitBoardEpoxy), of(GregTechItems.NanoProcessor, 2), of(GregTechItems.SmallCoil, 4), of(SMDCapacitor, 4),
                            of(GregTechItems.RandomAccessMemoryChip, 4), WIRE_FINE.getMaterialIngredient(Electrum, 6))
                    .io(new ItemStack(GregTechItems.NanoprocessorAssembly))
                    .fi(material.getLiquid(base * 8)).add("nanoprocessor_assembly_using_" + material.getId(), 400, 600);
            CIRCUIT_ASSEMBLER.RB().ii(of(CircuitBoardFiber), of(GregTechItems.QBitProcessingUnit), of(GregTechItems.NanoCpu), of(SMDCapacitor, 2),
                            of(SMDTransistor, 2), WIRE_FINE.getMaterialIngredient(Platinum, 2))
                    .io(new ItemStack(GregTechItems.QuantumProcessor))
                    .fi(material.getLiquid(base * 8)).add("quantum_processor_using_" + material.getId(), 200, 2400);
            CIRCUIT_ASSEMBLER.RB().ii(of(CircuitBoardEpoxy), of(GregTechItems.ASoC, 1), WIRE_FINE.getMaterialIngredient(Platinum, 2))
                    .io(new ItemStack(GregTechItems.QuantumProcessor))
                    .fi(material.getLiquid(base * 4)).add("quantum_processor_asoc_using_" + material.getId(), 50, 8192);
            //Energy Flow
            ASSEMBLER.RB().ii(of(CircuitBoardMultiFiber, 1), of(Resistor, 8),of(Transistor, 8),
                            of(Capacitor, 8),of(AntimatterMaterialTypes.WIRE_FINE.get(NiobiumTitanium), 4))
                    .io(new ItemStack(CircuitEnergyFlow,1))
                    .fi(material.getLiquid(base * 4)).add("energy_flow_circuit_using_" + material.getId(),20*20, 8192);
            //Wetware
            ASSEMBLER.RB().ii(of(CircuitBoardWetware, 1), of(Resistor, 8),of(Transistor, 8),
                            of(Capacitor, 8),of(AntimatterMaterialTypes.WIRE_FINE.get(YttriumBariumCuprate), 4))
                    .io(new ItemStack(GregTechItems.CircuitWetware,1))
                    .fi(material.getLiquid(base * 4)).add("wetware_circuit_using_" + material.getId(),20*20, 32768);
        }
    }
}
