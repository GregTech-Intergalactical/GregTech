package muramasa.gregtech.loader.items;

import com.google.common.collect.ImmutableMap;
import io.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
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
import static muramasa.gregtech.data.GregTechTags.CIRCUITS_ADVANCED;
import static muramasa.gregtech.data.GregTechTags.DUST_COALS;
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
        addCuttingRecipe(SiliconBoule, new ItemStack(Wafer, 16), "wafer", 1600, 384);
        addCuttingRecipe(Wafer, new ItemStack(SiliconChip, 8), "silicon_chip", 1600, 384);
        if (GregTechConfig.GAMEPLAY.HARDER_CIRCUITS){
            BLASTING.RB().temperature(2484).ii(DUST.getMaterialIngredient(Silicon, 16), DUST.getMaterialIngredient(Glowstone, 1)).fi(Nitrogen.getGas(1000)).io(GlowstoneDopedSiliconBoule).add("glowstone_doped_silicon_boule", 12000, 480);
            BLASTING.RB().temperature(2484).ii(DUST.getMaterialIngredient(Silicon, 16), DUST.getMaterialIngredient(Naquadah, 1)).fi(Argon.getGas(1000)).io(NaquadahDopedSiliconBoule).add("naquadah_doped_silicon_boule", 15000, 1920);
            addCuttingRecipe(GlowstoneDopedSiliconBoule, new ItemStack(GlowstoneDopedWafer, 32), "glowstone_doped_wafer", 800, 64);
            addCuttingRecipe(NaquadahDopedSiliconBoule, new ItemStack(NaquadahDopedWafer, 64), "naquadah_doped_wafer", 1600, 64);
        }
    }

    private static void addCuttingRecipe(Item input, ItemStack output, String id, int ticks, int power){
        CUTTING.RB().ii(RecipeIngredient.of(input, 1))
                .fi(FluidPlatformUtils.createFluidStack(Fluids.WATER, 960 * TesseractGraphWrappers.dropletMultiplier))
                .io(output).add(id + "_with_water", ticks, power);
        CUTTING.RB().ii(RecipeIngredient.of(input, 1))
                .fi(DistilledWater.getLiquid(721))
                .io(output).add(id + "_with_distilled_water", ticks, power);
        CUTTING.RB().ii(RecipeIngredient.of(input, 1))
                .fi(Lubricant.getLiquid(240))
                .io(output).add(id + "_with_lubricant", ticks / 2, power);
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
        CHEMICAL_REACTING.RB().ii(of(GregTechData.CircuitBoardPhenolic,1), of(PLATE.get(Polyethylene),1), of(AntimatterMaterialTypes.FOIL.get(AntimatterMaterials.Copper),1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(GregTechData.CircuitBoardPlastic,1))
                .add("plastic_circuit_board",8*20, 128);
        CHEMICAL_REACTING.RB().ii(of(GregTechData.CircuitBoardPhenolic,1), of(PLATE.get(PolyvinylChloride),1), of(AntimatterMaterialTypes.FOIL.get(AntimatterMaterials.Copper),1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(GregTechData.CircuitBoardPlastic,2))
                .add("plastic_circuit_board_2",8*20, 128);
        CHEMICAL_REACTING.RB().ii(of(GregTechData.CircuitBoardPhenolic,1), of(PLATE.get(Polytetrafluoroethylene),1), of(AntimatterMaterialTypes.FOIL.get(AntimatterMaterials.Copper),1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(GregTechData.CircuitBoardPlastic,4))
                .add("plastic_circuit_board_3",8*20, 128);
        //Epoxy
        CHEMICAL_REACTING.RB().ii(of(GregTechData.CircuitBoardPlastic,1), of(PLATE.get(EpoxyResin),1), of(AntimatterMaterialTypes.FOIL.get(AnnealedCopper),1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(GregTechData.CircuitBoardEpoxy,1))
                .add("epoxy_circuit_board",8*20, 512);
        //Fiber
        CHEMICAL_REACTING.RB().ii(of(GregTechData.CircuitBoardEpoxy,1), of(PLATE.get(FiberReinforcedEpoxyResin),1), of(AntimatterMaterialTypes.FOIL.get(AnnealedCopper),1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(GregTechData.CircuitBoardFiber,1))
                .add("fiber_circuit_board",8*20, 2048);
        //MultiFiber
        CHEMICAL_REACTING.RB().ii(of(GregTechData.CircuitBoardFiber,1), of(AntimatterMaterialTypes.FOIL.get(Electrum),1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(GregTechData.CircuitBoardMultiFiber,1))
                .add("multi_fiber_circuit_board",8*20, 8192);
        //Wetware
        ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardMultiFiber,1), of(GregTechData.CircuitEnergyFlow,1), of(AntimatterMaterialTypes.FOIL.get(Electrum),1))
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
            //Good
            ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardPhenolic, 1), of(GregTechData.Resistor, 2),of(GregTechData.Transistor, 2),
                            of(GregTechData.Capacitor, 2),of(AntimatterMaterialTypes.WIRE_FINE.get(Electrum), 2))
                    .io(new ItemStack(GregTechData.CircuitGoodElectronic,1))
                    .fi(material.getLiquid(base * 4)).add("good_circuit_using_" + material.getId(),20*20, 32);
            //Advanced
            ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardPlastic, 1), of(GregTechData.Resistor, 4),of(GregTechData.Transistor, 4),
                            of(GregTechData.Capacitor, 4),of(AntimatterMaterialTypes.WIRE_FINE.get(RedAlloy), 2))
                    .io(new ItemStack(GregTechData.CircuitAdv,1))
                    .fi(material.getLiquid(base * 4)).add("advanced_circuit_using_" + material.getId(),20*20, 128);
            //Nano
            ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardEpoxy, 1), of(GregTechData.Resistor, 4),of(GregTechData.Transistor, 4),
                            of(GregTechData.Capacitor, 4),of(AntimatterMaterialTypes.WIRE_FINE.get(RoseGold), 2))
                    .io(new ItemStack(GregTechData.NanoProcessor,1))
                    .fi(material.getLiquid(base * 4)).add("nano_processor_circuit_using_" + material.getId(),20*20, 512);
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
