package muramasa.gregtech.loader.items;

import com.google.common.collect.ImmutableMap;
import io.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.data.ForgeCTags;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.SubTag;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.GregTechConfig;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.GregTechTags;
import muramasa.gregtech.data.Materials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractGraphWrappers;

import java.util.function.Consumer;

import static muramasa.antimatter.Ref.L;
import static muramasa.antimatter.data.AntimatterDefaultTools.WRENCH;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.machine.Tier.LV;
import static muramasa.antimatter.machine.Tier.MV;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.GregTechMaterialTags.SOLDER;
import static muramasa.gregtech.data.GregTechTags.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.RecipeMaps.*;
import static muramasa.gregtech.data.TierMaps.*;

public class Circuitry {
    public static void loadCraftingRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        // MANUAL COATED BOARD CRAFTING
        provider.addStackRecipe(output, GTIRef.ID, "", "board_basic", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), new ItemStack(CircuitBoardCoated, 3),
                ImmutableMap.<Character, Object>builder()
                        .put('R', GTRubberData.StickyResin)
                        .put('P', PLATE.get(Wood))
                        .build(),
                " R ", "PPP", " R ");
        if (GregTechConfig.GAMEPLAY.HARDER_CIRCUITS){
            bloodyCircuits(output, provider);
        } else {
            circuits(output, provider);
        }
    }

    private static void bloodyCircuits(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        // MANUAL TIER 0 CIRCUIT CRAFTING
        provider.addItemRecipe(output, "circuit_basic", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), CircuitBasicElectronic,
                ImmutableMap.<Character, Object>builder()
                        .put('V', VacuumTube).put('B', CircuitBoardCoated)
                        .put('W', CABLE_RED_ALLOY.getBlockItem(PipeSize.VTINY))
                        .put('R',Resistor).put('P', ITEM_CASING.get(Steel))
                        .build(),
                "RPR", "VBV", "WWW");
        provider.addItemRecipe(output, "circuits", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), CircuitGoodElectronic,
                ImmutableMap.<Character, Object>builder()
                        .put('S', ITEM_CASING.getMaterialTag(Steel))
                        .put('C', CIRCUITS_BASIC)
                        .put('c', CABLE_RED_ALLOY.getBlockItem(PipeSize.VTINY))
                        .put('D', Diode).build(), "SCc", "CDC", "cCS");

        // MANUAL VAC TUBE CRAFTING
        provider.addItemRecipe(output, "vac_tube", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), VacuumTube,
                ImmutableMap.<Character, Object>builder()
                        .put('G', GlassTube)
                        .put('P', Items.PAPER)
                        .put('W', WIRE_GETTER.apply(PipeSize.VTINY, LV))
                        .build(),
                "PGP", "WWW");

        provider.addItemRecipe(output, GTIRef.ID, "vacuum_tube_1", "vac_tube", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), VacuumTube,
                ImmutableMap.<Character, Object>builder()
                        .put('G', GlassTube)
                        .put('P', Items.PAPER)
                        .put('W', WIRE_FINE.getMaterialTag(Copper))
                        .build(),
                "PGP", "WWW");

        // MANUAL RESISTOR CRAFTING
        provider.addItemRecipe(output, "resistor", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), Resistor,
                ImmutableMap.<Character, Object>builder()
                        .put('C', DUST_COALS)
                        .put('P', Items.PAPER)
                        .put('W', WIRE_FINE.getMaterialTag(Copper))
                        .build(),
                " P ", "WCW", " P ");
        provider.addItemRecipe(output, GTIRef.ID, "", "diodes", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), Diode,
                ImmutableMap.<Character, Object>builder()
                        .put('B', ForgeCTags.DYES_BLACK)
                        .put('T', WIRE_TIN.getBlockItem(PipeSize.VTINY))
                        .put('W', Wafer)
                        .put('G', ForgeCTags.GLASS_PANES).build(), "BG ", "TWT", "BG ");
        provider.addItemRecipe(output, GTIRef.ID, "diode_2", "diodes", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), Diode,
                ImmutableMap.<Character, Object>builder()
                        .put('B', ForgeCTags.DYES_BLACK)
                        .put('T', WIRE_FINE.getMaterialTag(Tin))
                        .put('W', Wafer)
                        .put('G', ForgeCTags.GLASS_PANES).build(), "BG ", "TWT", "BG ");
        provider.addStackRecipe(output, GTIRef.ID, "diode_#", "diodes", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), new ItemStack(Diode),
                ImmutableMap.<Character, Object>builder()
                        .put('B', ForgeCTags.DYES_BLACK)
                        .put('T', WIRE_TIN.getBlockItem(PipeSize.VTINY))
                        .put('W', DUST_TINY.getMaterialTag(Gallium))
                        .put('G', ForgeCTags.GLASS_PANES).build(), "BG ", "TWT", "BG ");
        provider.addStackRecipe(output, GTIRef.ID, "diode_4", "diodes", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), new ItemStack(Diode),
                ImmutableMap.<Character, Object>builder()
                        .put('B', ForgeCTags.DYES_BLACK)
                        .put('T', WIRE_FINE.getMaterialTag(Tin))
                        .put('W', DUST_TINY.getMaterialTag(Gallium))
                        .put('G', ForgeCTags.GLASS_PANES).build(), "BG ", "TWT", "BG ");
    }

    private static void circuits(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        provider.addItemRecipe(output, GTIRef.ID, "circuit_basic_copper_h", "circuits", "has_copper_cable", provider.hasSafeItem((TagKey<Item>) CABLE_GETTER.apply(PipeSize.VTINY, MV, false)), CircuitBasicElectronic,
                ImmutableMap.<Character, Object>builder()
                        .put('C', CABLE_GETTER.apply(PipeSize.VTINY, MV, false))
                        .put('N', NandChip)
                        .put('S', CircuitBoardCoated)
                        .build(), "CCC", "NSN", "CCC");
        provider.addItemRecipe(output, GTIRef.ID, "circuit_basic_copper_v", "circuits", "has_copper_cable", provider.hasSafeItem((TagKey<Item>) CABLE_GETTER.apply(PipeSize.VTINY, MV, false)), CircuitBasicElectronic,
                ImmutableMap.<Character, Object>builder()
                        .put('C', CABLE_GETTER.apply(PipeSize.VTINY, MV, false))
                        .put('N', NandChip)
                        .put('S', CircuitBoardCoated)
                        .build(), "CNC", "CSC", "CNC");
        provider.addItemRecipe(output, GTIRef.ID, "circuit_basic_red_alloy_h", "circuits", "has_red_alloy_cable", provider.hasSafeItem(CABLE_RED_ALLOY.getBlockItem(PipeSize.VTINY)), CircuitBasicElectronic,
                ImmutableMap.<Character, Object>builder()
                        .put('C', CABLE_RED_ALLOY.getBlockItem(PipeSize.VTINY))
                        .put('N', NandChip)
                        .put('S', CircuitBoardCoated)
                        .build(), "CCC", "NSN", "CCC");
        provider.addItemRecipe(output, GTIRef.ID, "circuit_basic_red_alloy_v", "circuits", "has_red_alloy_cable", provider.hasSafeItem(CABLE_RED_ALLOY.getBlockItem(PipeSize.VTINY)), CircuitBasicElectronic,
                ImmutableMap.<Character, Object>builder()
                        .put('C', CABLE_RED_ALLOY.getBlockItem(PipeSize.VTINY))
                        .put('N', NandChip)
                        .put('S', CircuitBoardCoated)
                        .build(), "CNC", "CSC", "CNC");
        provider.addItemRecipe(output, GTIRef.ID, "", "circuits", "has_item_casing", provider.hasSafeItem(ITEM_CASING.getMaterialTag(Steel)), NandChip,
                ImmutableMap.of('C', ITEM_CASING.getMaterialTag(Steel), 'R', WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY), 'T', WIRE_GETTER.apply(PipeSize.VTINY, LV)), "CR", "RT");
        provider.addItemRecipe(output, GTIRef.ID, "lapotron_crystal_upgrade", "energy_orbs", "has_circuit", provider.hasSafeItem(CIRCUITS_ADVANCED), LapotronCrystal,
                ImmutableMap.of('C', CIRCUITS_ADVANCED, 'L', GregTechTags.DUST_LAPIS_LAZURITE, 'E', EnergyCrystal), "LCL", "LEL", "LCL");
        provider.addItemRecipe(output, GTIRef.ID, "", "energy_orbs", "has_circuit", provider.hasSafeItem(CIRCUITS_ADVANCED), LapotronCrystal,
                ImmutableMap.of('C', CIRCUITS_ADVANCED, 'L', GregTechTags.DUST_LAPIS_LAZURITE, 'S', GEM.getMaterialTag(Sapphire)), "LCL", "LSL", "LCL");
    }

    public static void init() {

        silicon();
        //phenolic
        ASSEMBLING.RB().ii(DUST.getMaterialIngredient(Wood, 1), RecipeIngredient.of(MoldPlate, 1).setNoConsume())
                .fi(Glue.getLiquid(100))
                .io(new ItemStack(CircuitBoardPhenolic,8))
                .add("phenolic_circuit_board",30, 8);
        if (!GregTechConfig.GAMEPLAY.HARDER_CIRCUITS){
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
        BLASTING.RB().temperature(1784).ii(DUST.getMaterialIngredient(Silicon, 16), INT_CIRCUITS.get(16)).fi(Helium.getGas(1000)).io(SiliconBoule).add("silicon_boule", 9000, 120);
        addCuttingRecipe(SiliconBoule, Wafer, 16, 1600, 384, 1);
        addCuttingRecipe(Wafer, SiliconChip, 8, 600, 48, 22);
        if (GregTechConfig.GAMEPLAY.HARDER_CIRCUITS){
            BLASTING.RB().temperature(2484).ii(DUST.getMaterialIngredient(Silicon, 16), DUST.getMaterialIngredient(Glowstone, 1)).fi(Nitrogen.getGas(1000)).io(GlowstoneDopedSiliconBoule).add("glowstone_doped_silicon_boule", 12000, 480);
            BLASTING.RB().temperature(2484).ii(DUST.getMaterialIngredient(Silicon, 16), DUST.getMaterialIngredient(Naquadah, 1)).fi(Argon.getGas(1000)).io(NaquadahDopedSiliconBoule).add("naquadah_doped_silicon_boule", 15000, 1920);
            addCuttingRecipe(GlowstoneDopedSiliconBoule, GlowstoneDopedWafer, 32, 800, 64, 20);
            addCuttingRecipe(NaquadahDopedSiliconBoule, NaquadahDopedWafer, 64, 1600, 64, 240);
            addLensRecipe(NaquadahDopedWafer, ASoCWafer, 1, 200, 1920, Amber, Topaz);
            addCuttingRecipe(ASoCWafer, ASoC, 8, 600, 48, 22);
            addLensRecipe(Wafer, CentralProcessingUnitWafer, 1, 900, 120, Diamond, Glass, Dilithium);
            addLensRecipe(GlowstoneDopedWafer, CentralProcessingUnitWafer, 4, 500, 480, Diamond, Glass, Dilithium);
            addLensRecipe(NaquadahDopedWafer, CentralProcessingUnitWafer, 8, 200, 1920, Diamond, Glass, Dilithium);
            addCuttingRecipe(CentralProcessingUnitWafer, CentralProcessingUnit, 8, 600, 48, 22);
            CHEMICAL_REACTING.RB().ii(of(PICWafer), DUST.getMaterialIngredient(IndiumGalliumPhosphide, 2)).fi(RedAlloy.getLiquid(L * 2)).io(HPICWafer).add("hpic_wafer", 1200, 1920);
            addCuttingRecipe(HPICWafer, HighPowerIC, 2, 600, 48, 22);
            addLensRecipe(Wafer, IntegratedLogicCircuitWafer, 1, 900, 120, Ruby, RedGarnet, Jade);
            addLensRecipe(GlowstoneDopedWafer, IntegratedLogicCircuitWafer, 4, 500, 480, Ruby, RedGarnet, Jade);
            addLensRecipe(NaquadahDopedWafer, IntegratedLogicCircuitWafer, 8, 200, 1920, Ruby, RedGarnet, Jade);
            addCuttingRecipe(IntegratedLogicCircuitWafer, IntegratedLogicCircuit, 8, 600, 48, 22);
            addLensRecipe(GlowstoneDopedWafer, NANDMemoryChipWafer, 1, 500, 480, EnderPearl);
            addLensRecipe(NaquadahDopedWafer, NANDMemoryChipWafer, 4, 200, 1920, EnderPearl);
            addCuttingRecipe(NANDMemoryChipWafer, NANDMemoryChip, 32, 600, 48, 22);
            CHEMICAL_REACTING.RB().ii(of(CentralProcessingUnitWafer), of(CarbonFibre, 16)).fi(Glowstone.getLiquid(L * 4)).io(NanoCpuWafer).add("nano_cpu_wafer", 400, 1920);
            addCuttingRecipe(NanoCpuWafer, NanoCpu, 7, 600, 48, 22);
            addLensRecipe(GlowstoneDopedWafer, NorMemoryChipWafer, 1, 500, 480, EnderEye);
            addLensRecipe(NaquadahDopedWafer, NorMemoryChipWafer, 4, 200, 1920, EnderEye);
            addCuttingRecipe(NorMemoryChipWafer, NorMemoryChip, 16, 600, 48, 22);
            addLensRecipe(GlowstoneDopedWafer, PICWafer, 1, 500, 480, Opal, Sapphire, BlueTopaz);
            addLensRecipe(NaquadahDopedWafer, PICWafer, 4, 200, 1920, Opal, Sapphire, BlueTopaz);
            addCuttingRecipe(PICWafer, PowerIC, 4, 600, 48, 22);
            CHEMICAL_REACTING.RB().ii(of(NanoCpuWafer), of(QuantumEye, 2)).fi(GalliumArsenide.getLiquid(L * 2)).io(QBitWafer).add("qbit_wafer", 400, 1920);
            CHEMICAL_REACTING.RB().ii(of(NanoCpuWafer), DUST.getMaterialIngredient(IndiumGalliumPhosphide, 1)).fi(Radon.getGas(50)).io(QBitWafer).add("qbit_wafer_2", 600, 1920);
            addCuttingRecipe(QBitWafer, QBitProcessingUnit, 5, 600, 48, 22);
            addLensRecipe(Wafer, RandomAccessMemoryChipWafer, 1, 900, 120, GreenSapphire);
            addLensRecipe(GlowstoneDopedWafer, RandomAccessMemoryChipWafer, 4, 500, 480, GreenSapphire);
            addLensRecipe(NaquadahDopedWafer, RandomAccessMemoryChipWafer, 8, 200, 1920, GreenSapphire);
            addCuttingRecipe(RandomAccessMemoryChipWafer, RandomAccessMemoryChip, 32, 600, 48, 22);
            addLensRecipe(GlowstoneDopedWafer, SOCWafer, 1, 500, 480, YellowGarnet);
            addLensRecipe(NaquadahDopedWafer, SOCWafer, 4, 200, 1920, YellowGarnet);
            addCuttingRecipe(SOCWafer, SOC, 10, 600, 48, 22);

        }
    }

    private static void addLensRecipe(ItemBasic<?> input, ItemBasic<?> output, int count, int ticks, int power, Material... lenses){
        String extra = input.getId().replace("doped_", "").replace("wafer", "");
        for (Material lens : lenses){
            LASER_ENGRAVING.RB().ii(of(input), LENS.getMaterialIngredient(lens, 1).setNoConsume()).io(new ItemStack(output, count)).add(output.getId() + "_" + extra + lens.getId(), ticks, power);
        }
    }

    private static void addCuttingRecipe(Item input, ItemBasic<?> output, int amount, int ticks, int power, int liquidMultiplier){
        CUTTING.RB().ii(RecipeIngredient.of(input, 1))
                .fi(FluidPlatformUtils.createFluidStack(Fluids.WATER, 5 * liquidMultiplier * TesseractGraphWrappers.dropletMultiplier))
                .io(new ItemStack(output, amount)).add(output.getId() + "_with_water", ticks, power);
        CUTTING.RB().ii(RecipeIngredient.of(input, 1))
                .fi(DistilledWater.getLiquid(3 * liquidMultiplier))
                .io(new ItemStack(output, amount)).add(output.getId() + "_with_distilled_water", ticks, power);
        CUTTING.RB().ii(RecipeIngredient.of(input, 1))
                .fi(Lubricant.getLiquid(liquidMultiplier))
                .io(new ItemStack(output, amount)).add(output.getId() + "_with_lubricant", ticks / 2, power);
    }

    private static void circuitParts(){
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Lazurite, 1), DUST.getMaterialIngredient(AntimatterMaterials.Glowstone, 1)).io(new ItemStack(AdvCircuitParts, 2)).add("advanced_circuit_parts", 32, 64);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Lapis, 1), DUST.getMaterialIngredient(AntimatterMaterials.Glowstone, 1)).io(new ItemStack(AdvCircuitParts, 2)).add("advanced_circuit_parts_1", 32, 64);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Polyethylene, 1), of(WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY), 1)).fi(Tin.getLiquid(L / 4)).io(new ItemStack(NandChip)).add("nand_chip_tin_poly", 32, 16);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Polyethylene, 1), of(WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY), 1)).fi(SolderingAlloy.getLiquid(L / 8)).io(new ItemStack(NandChip)).add("nand_chip_soldering_alloy_poly", 32, 16);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Polyethylene, 1), of(WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY), 1)).fi(Lead.getLiquid(L / 2)).io(new ItemStack(NandChip)).add("nand_chip_lead_poly", 32, 16);
        ASSEMBLING.RB().ii(ITEM_CASING.getMaterialIngredient(Steel, 1), of(WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY), 2)).fi(Tin.getLiquid(L / 4)).io(new ItemStack(NandChip)).add("nand_chip_tin_steel", 32, 16);
        ASSEMBLING.RB().ii(ITEM_CASING.getMaterialIngredient(Steel, 1), of(WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY), 2)).fi(SolderingAlloy.getLiquid(L / 8)).io(new ItemStack(NandChip)).add("nand_chip_soldering_alloy_steel", 32, 16);
        ASSEMBLING.RB().ii(ITEM_CASING.getMaterialIngredient(Steel, 1), of(WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY), 2)).fi(Lead.getLiquid(L / 2)).io(new ItemStack(NandChip)).add("nand_chip_lead_steel", 32, 16);
    }

    private static void boards(){
        ASSEMBLING.RB().ii(of(Wafer), PLATE.getMaterialIngredient(Polyethylene, 1)).io(new ItemStack(CircuitBoardEmpty)).add("empty_circuit_board", 32, 16);
        ASSEMBLING.RB().ii(of(CircuitBoardPhenolic), PLATE.getMaterialIngredient(Polyethylene, 1)).io(new ItemStack(CircuitBoardEmpty)).add("empty_circuit_board_1", 32, 16);
        PRESSING.RB().ii(of(CircuitBoardEmpty), of(EtchedWiringMV, 4)).io(new ItemStack(CircuitBoardBasic)).add("basic_circuit_board", 32, 16);
        PRESSING.RB().ii(of(CircuitBoardEmpty), of(EtchedWiringHV, 4)).io(new ItemStack(CircuitBoardAdvanced)).add("advanced_circuit_board", 32, 16);
        PRESSING.RB().ii(of(CircuitBoardProcessorEmpty), of(EtchedWiringEV, 4)).io(new ItemStack(CircuitBoardProcessor)).add("processor_circuit_board", 32, 256);
        ASSEMBLING.RB().ii(of(Wafer, 2), PLATE.getMaterialIngredient(Polytetrafluoroethylene, 1)).io(new ItemStack(CircuitBoardProcessorEmpty)).add("empty_processor_circuit_board", 32, 256);
    }

    private static void circuits(){
        ASSEMBLING.RB().ii(of(CircuitBoardBasic), of(NandChip, 2)).fi(Tin.getLiquid(L / 2)).io(new ItemStack(CircuitBasicElectronic)).add("basic_circuit_tin", 32, 16);
        ASSEMBLING.RB().ii(of(CircuitBoardBasic), of(NandChip, 2)).fi(SolderingAlloy.getLiquid(L / 4)).io(new ItemStack(CircuitBasicElectronic)).add("basic_circuit_soldering_alloy", 32, 16);
        ASSEMBLING.RB().ii(of(CircuitBoardBasic), of(NandChip, 2)).fi(Lead.getLiquid(L)).io(new ItemStack(CircuitBasicElectronic)).add("basic_circuit_lead", 32, 16);
        ASSEMBLING.RB().ii(of(GregTechTags.CIRCUITS_BASIC), of(NandChip, 2)).fi(Tin.getLiquid(L / 2)).io(new ItemStack(CircuitGoodElectronic)).add("good_circuit_tin", 32, 16);
        ASSEMBLING.RB().ii(of(GregTechTags.CIRCUITS_BASIC), of(NandChip, 2)).fi(SolderingAlloy.getLiquid(L / 4)).io(new ItemStack(CircuitGoodElectronic)).add("good_circuit_soldering_alloy", 32, 16);
        ASSEMBLING.RB().ii(of(GregTechTags.CIRCUITS_BASIC), of(NandChip, 2)).fi(Lead.getLiquid(L)).io(new ItemStack(CircuitGoodElectronic)).add("good_circuit_lead", 32, 16);
        ASSEMBLING.RB().ii(of(CircuitBoardAdvanced), of(AdvCircuitParts, 2)).fi(Tin.getLiquid(L)).io(new ItemStack(CircuitAdv)).add("advanced_circuit_tin", 32, 64);
        ASSEMBLING.RB().ii(of(CircuitBoardAdvanced), of(AdvCircuitParts, 2)).fi(SolderingAlloy.getLiquid(L / 2)).io(new ItemStack(CircuitAdv)).add("advanced_circuit_soldering_alloy", 32, 64);
        ASSEMBLING.RB().ii(of(CircuitBoardAdvanced), of(AdvCircuitParts, 2)).fi(Lead.getLiquid(L * 2)).io(new ItemStack(CircuitAdv)).add("adv_storage_circuit_lead", 32, 64);
        ASSEMBLING.RB().ii(of(CircuitBoardAdvanced), of(EngravedCrystalChip)).fi(Tin.getLiquid(L)).io(new ItemStack(CircuitDataStorage)).add("data_storage_circuit_tin", 32, 64);
        ASSEMBLING.RB().ii(of(CircuitBoardAdvanced), of(EngravedCrystalChip)).fi(SolderingAlloy.getLiquid(L / 2)).io(new ItemStack(CircuitDataStorage)).add("data_storage__circuit_soldering_alloy", 32, 64);
        ASSEMBLING.RB().ii(of(CircuitBoardAdvanced), of(EngravedCrystalChip)).fi(Lead.getLiquid(L * 2)).io(new ItemStack(CircuitDataStorage)).add("data_storage_circuit_lead", 32, 64);
        ASSEMBLING.RB().ii(of(CircuitBoardProcessor), of(CircuitDataStorage, 3)).fi(Tin.getLiquid(L * 2)).io(new ItemStack(CircuitDataControl)).add("data_control_circuit_tin", 32, 256);
        ASSEMBLING.RB().ii(of(CircuitBoardProcessor), of(CircuitDataStorage, 3)).fi(SolderingAlloy.getLiquid(L)).io(new ItemStack(CircuitDataControl)).add("data_control_circuit_soldering_alloy", 32, 256);
        ASSEMBLING.RB().ii(of(CircuitBoardProcessor), of(CircuitDataStorage, 3)).fi(Lead.getLiquid(L * 4)).io(new ItemStack(CircuitDataControl)).add("data_control_circuit_lead", 32, 256);
        ASSEMBLING.RB().ii(of(CircuitBoardProcessor), of(EngravedLapotronChip, 3)).fi(Tin.getLiquid(L * 2)).io(new ItemStack(CircuitEnergyFlow)).add("energy_flow_circuit_tin", 32, 256);
        ASSEMBLING.RB().ii(of(CircuitBoardProcessor), of(EngravedLapotronChip, 3)).fi(SolderingAlloy.getLiquid(L)).io(new ItemStack(CircuitEnergyFlow)).add("energy_flow_circuit_soldering_alloy", 32, 256);
        ASSEMBLING.RB().ii(of(CircuitBoardProcessor), of(EngravedLapotronChip, 3)).fi(Lead.getLiquid(L * 4)).io(new ItemStack(CircuitEnergyFlow)).add("energy_flow_circuit_lead", 32, 256);
    }

    private static void bloodyBoards() {
        //Plastic
        CHEMICAL_REACTING.RB().ii(PLATE.getMaterialIngredient(Polyethylene, 1), FOIL.getMaterialIngredient(Copper, 1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(GregTechData.CircuitBoardPlastic,1))
                .add("plastic_circuit_board",25*20, 10);
        CHEMICAL_REACTING.RB().ii(PLATE.getMaterialIngredient(PolyvinylChloride, 1), FOIL.getMaterialIngredient(Copper, 1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(GregTechData.CircuitBoardPlastic,2))
                .add("plastic_circuit_board_2",25*20, 10);
        CHEMICAL_REACTING.RB().ii(PLATE.getMaterialIngredient(Polytetrafluoroethylene, 1), FOIL.getMaterialIngredient(Copper, 1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(GregTechData.CircuitBoardPlastic,4))
                .add("plastic_circuit_board_4",25*20, 10);
        //Epoxy
        CHEMICAL_REACTING.RB().ii(PLATE.getMaterialIngredient(EpoxyResin, 1), FOIL.getMaterialIngredient(Copper, 1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(GregTechData.CircuitBoardEpoxy,1))
                .add("epoxy_circuit_board",25*20, 10);
        //Fiber
        CHEMICAL_REACTING.RB().ii(PLATE.getMaterialIngredient(FiberReinforcedEpoxyResin, 1), FOIL.getMaterialIngredient(Copper, 1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(CircuitBoardFiber,1))
                .add("fiber_circuit_board",25*20, 10);
        //MultiFiber
        CHEMICAL_REACTING.RB().ii(of(GregTechData.CircuitBoardFiber,1), FOIL.getMaterialIngredient(Electrum, 16))
                .fi(SulfuricAcid.getLiquid(250))
                .io(new ItemStack(GregTechData.CircuitBoardMultiFiber,1))
                .add("multi_fiber_circuit_board",5*20, 480);
        //Wetware
        ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardMultiFiber,1), of(GregTechTags.CIRCUITS_GOOD,1), of(PetriDish), of(SensorLV), of(COVER_PUMP.getItem(LV)))
                .fi(Polystyrene.getLiquid(144))
                .io(new ItemStack(GregTechData.CircuitBoardWetware,1))
                .add("wetware_circuit_board",8*20, 32768);
    }

    private static void bloodyCircuitParts(){
        ASSEMBLING.RB().ii(of(SiliconChip, 1), WIRE_FINE.getMaterialIngredient(Tin, 6)).fi(Polyethylene.getLiquid(L)).io(new ItemStack(Transistor,8)).add("transistor",80, 24);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Polyethylene, 1), FOIL.getMaterialIngredient(Aluminium, 2)).io(new ItemStack(Capacitor, 2)).add("capacitor", 80, 96);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Gallium, 1), WIRE_FINE.getMaterialIngredient(AnnealedCopper, 6)).fi(Polyethylene.getLiquid(L * 2)).io(new ItemStack(SMDTransistor,32)).add("smd_transistor",80, 96);
        ASSEMBLING.RB().ii(of(DUST.get(Carbon), 1), of(WIRE_FINE.get(Electrum), 4)).fi(Polyethylene.getLiquid(L)).io(new ItemStack(SMDResistor,24)).add("smd_resistor",80, 96);
        ASSEMBLING.RB().ii(FOIL.getMaterialIngredient(PolyvinylChloride, 4), FOIL.getMaterialIngredient(Tantalum, 1)).fi(Polyethylene.getLiquid(L / 4)).io(new ItemStack(SMDCapacitor,32)).add("smd_capacitor_tantalum",50, 96);
        ASSEMBLING.RB().ii(FOIL.getMaterialIngredient(PolyvinylChloride, 4), FOIL.getMaterialIngredient(Aluminium, 1)).fi(Polyethylene.getLiquid(L / 4)).io(new ItemStack(SMDCapacitor,16)).add("smd_capacitor_aluminium",50, 96);
        ASSEMBLING.RB().ii(FOIL.getMaterialIngredient(SiliconeRubber, 4), FOIL.getMaterialIngredient(Tantalum, 1)).fi(Polyethylene.getLiquid(L / 4)).io(new ItemStack(SMDCapacitor,32)).add("smd_capacitor_tantalum_rubber",60, 120);
        ASSEMBLING.RB().ii(FOIL.getMaterialIngredient(SiliconeRubber, 4), FOIL.getMaterialIngredient(Aluminium, 1)).fi(Polyethylene.getLiquid(L / 4)).io(new ItemStack(SMDCapacitor,16)).add("smd_capacitor_aluminium_rubber",60, 120);
        ASSEMBLING.RB().ii(WIRE_FINE.getMaterialIngredient(AnnealedCopper, 4), DUST_SMALL.getMaterialIngredient(Gallium, 1)).fi(Polyethylene.getLiquid(L * 2)).io(new ItemStack(Diode, 16)).add("diode", 400, 48);
    }

    private static void bloodyCircuits() {
        for (Material material : SOLDER.all()) {
            long base = L / 8;
            boolean hasGood = SOLDER.has(SubTag.GOOD_SOLDER, material);
            boolean hasBad = SOLDER.has(SubTag.BAD_SOLDER, material);
            base *= hasBad ? (hasGood ? 2 : 4) : 1;
            //Basic
            CIRCUIT_ASSEMBLING.RB().ii(of(CircuitBoardPhenolic, 1), of(GregTechTags.RESISTORS, 2),
                            WIRE_FINE.getMaterialIngredient(Copper, 4), of(IntegratedLogicCircuit, 1))
                    .io(new ItemStack(CircuitBasicIntegrated,1))
                    .fi(material.getLiquid(base * 4)).add("basic_circuit_using_" + material.getId(),200, 8);
            CIRCUIT_ASSEMBLING.RB().ii(of(CircuitBoardPlastic), of(IntegratedLogicCircuit, 4),
                            of(GregTechTags.RESISTORS, 4), of(GregTechTags.CAPACITORS, 4),
                            of(GregTechTags.TRANSISTORS, 4), WIRE_FINE.getMaterialIngredient(Copper, 2))
                    .io(new ItemStack(MicroProcessor, 4))
                    .fi(material.getLiquid(base * 4)).add("microprocessor_using_" + material.getId(), 200, 60);
            CIRCUIT_ASSEMBLING.RB().ii(of(CircuitBoardPlastic), of(SOC, 4), WIRE_FINE.getMaterialIngredient(Copper, 2))
                    .io(new ItemStack(MicroProcessor, 4))
                    .fi(material.getLiquid(base * 4)).add("microprocessor_soc_using_" + material.getId(), 200, 60);
            //Good
            CIRCUIT_ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardPhenolic, 1), of(RESISTORS, 4),of(CircuitBasicIntegrated, 3),
                            WIRE_FINE.getMaterialIngredient(Electrum, 8))
                    .io(new ItemStack(CircuitGoodIntegrated,1))
                    .fi(material.getLiquid(base * 4)).add("good_circuit_using_" + material.getId(),20*20, 16);
            CIRCUIT_ASSEMBLING.RB().ii(of(CircuitBoardPlastic), of(CentralProcessingUnit, 1),
                            of(GregTechTags.RESISTORS, 2), of(GregTechTags.CAPACITORS, 2),
                            of(GregTechTags.TRANSISTORS, 2), WIRE_FINE.getMaterialIngredient(RedAlloy, 2))
                    .io(new ItemStack(IntegratedProcessor, 4))
                    .fi(material.getLiquid(base * 4)).add("integrated_processor_using_" + material.getId(), 200, 60);
            CIRCUIT_ASSEMBLING.RB().ii(of(CircuitBoardPlastic), of(SOC, 1), WIRE_FINE.getMaterialIngredient(RedAlloy, 2))
                    .io(new ItemStack(IntegratedProcessor, 1))
                    .fi(material.getLiquid(base * 4)).add("integrated_processor_soc_using_" + material.getId(), 200, 60);
            //Advanced
            CIRCUIT_ASSEMBLING.RB().ii(of(CircuitGoodIntegrated, 2), of(IntegratedLogicCircuit, 3),of(RandomAccessMemoryChip, 1),
                            of(TRANSISTORS, 4), WIRE_FINE.getMaterialIngredient(Electrum, 16))
                    .io(new ItemStack(GregTechData.CircuitAdv,1))
                    .fi(material.getLiquid(base * 4)).add("advanced_circuit_using_" + material.getId(),800, 28);
            CIRCUIT_ASSEMBLING.RB().ii(of(CircuitBoardPlastic), of(IntegratedProcessor, 2), of(SmallCoil, 4),
                    of(CAPACITORS, 4), of(RandomAccessMemoryChip, 4), WIRE_FINE.getMaterialIngredient(RedAlloy, 12))
                    .io(ProcessorAssembly)
                    .fi(material.getLiquid(base * 4)).add("processor_assembly_using_" + material.getId(), 200, 600);
            CIRCUIT_ASSEMBLING.RB().ii(of(CircuitBoardEpoxy), of(NanoCpu, 1),
                            of(GregTechTags.RESISTORS, 2), of(GregTechTags.CAPACITORS, 2),
                            of(GregTechTags.TRANSISTORS, 2), WIRE_FINE.getMaterialIngredient(Electrum, 2))
                    .io(new ItemStack(NanoProcessor))
                    .fi(material.getLiquid(base * 4)).add("nano_processor_using_" + material.getId(), 200, 600);
            CIRCUIT_ASSEMBLING.RB().ii(of(CircuitBoardPlastic), of(ASoC, 1), WIRE_FINE.getMaterialIngredient(Electrum, 2))
                    .io(new ItemStack(NanoProcessor))
                    .fi(material.getLiquid(base * 4)).add("nano_processor_soc_using_" + material.getId(), 300, 8192);

            //Quantum
            ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardFiber, 1), of(GregTechData.Resistor, 4),of(GregTechData.Transistor, 4),
                            of(GregTechData.Capacitor, 4),of(AntimatterMaterialTypes.WIRE_FINE.get(Platinum), 2))
                    .io(new ItemStack(GregTechData.QuantumProcessor,1))
                    .fi(material.getLiquid(base * 4)).add("quantum_processor_circuit_using_" + material.getId(),20*20, 2048);
            //Energy Flow
            ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardMultiFiber, 1), of(GregTechData.Resistor, 8),of(GregTechData.Transistor, 8),
                            of(GregTechData.Capacitor, 8),of(AntimatterMaterialTypes.WIRE_FINE.get(NiobiumTitanium), 4))
                    .io(new ItemStack(GregTechData.CircuitEnergyFlow,1))
                    .fi(material.getLiquid(base * 4)).add("energy_flow_circuit_using_" + material.getId(),20*20, 8192);
            //Wetware
            ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardWetware, 1), of(GregTechData.Resistor, 8),of(GregTechData.Transistor, 8),
                            of(GregTechData.Capacitor, 8),of(AntimatterMaterialTypes.WIRE_FINE.get(YttriumBariumCuprate), 4))
                    .io(new ItemStack(GregTechData.CircuitWetware,1))
                    .fi(material.getLiquid(base * 4)).add("wetware_circuit_using_" + material.getId(),20*20, 32768);
        }
    }
}
