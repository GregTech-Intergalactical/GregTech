package muramasa.gregtech.loader.items;

import io.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.SubTag;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.GregTechTags;
import muramasa.gregtech.data.Materials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluids;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractGraphWrappers;

import static muramasa.antimatter.Ref.L;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Glowstone;
import static muramasa.antimatter.data.AntimatterMaterials.Lapis;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.GregTechMaterialTags.SOLDER;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.RecipeMaps.*;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

public class Circuitry {
    public static void init() {
        BLASTING.RB().temperature(1784).ii(DUST.getMaterialIngredient(Silicon, 16), INT_CIRCUITS.get(16)).fi(Helium.getGas(1000)).io(SiliconBoule).add("silicon_boule", 9000, 120);
        CUTTING.RB().ii(RecipeIngredient.of(SiliconBoule, 1))
                .fi(FluidPlatformUtils.createFluidStack(Fluids.WATER, 960 * TesseractGraphWrappers.dropletMultiplier))
                .io(new ItemStack(Wafer, 16)).add("wafer_with_water", 1600, 384);
        CUTTING.RB().ii(RecipeIngredient.of(SiliconBoule, 1))
                .fi(DistilledWater.getLiquid(721))
                .io(new ItemStack(Wafer, 16)).add("wafer_with_distilled_water", 1600, 384);
        CUTTING.RB().ii(RecipeIngredient.of(SiliconBoule, 1))
                .fi(Lubricant.getLiquid(240))
                .io(new ItemStack(Wafer, 16)).add("wafer_with_lubricant", 800, 384);
        //Coated
        ASSEMBLING.RB().ii(of(GTRubberData.StickyResin,1), of(PLATE.get(AntimatterMaterials.Wood),8))
                .fi(Glue.getLiquid(100))
                .io(new ItemStack(GregTechData.CircuitBoardCoated,8))
                .add("coated_circuit_board",8*20, 8);
        boards();
        circuits();
        //bloodyBoards();
        //bloodyCircuits();
    }

    private static void boards(){
        CUTTING.RB().ii(RecipeIngredient.of(Wafer, 1))
                .fi(FluidPlatformUtils.createFluidStack(Fluids.WATER, 960 * TesseractGraphWrappers.dropletMultiplier))
                .io(new ItemStack(SiliconChip, 8)).add("silicon_chip_with_water", 1600, 384);
        CUTTING.RB().ii(RecipeIngredient.of(Wafer, 1))
                .fi(DistilledWater.getLiquid(721))
                .io(new ItemStack(SiliconChip, 8)).add("silicon_chip_with_distilled_water", 1600, 384);
        CUTTING.RB().ii(RecipeIngredient.of(Wafer, 1))
                .fi(Lubricant.getLiquid(240))
                .io(new ItemStack(SiliconChip, 8)).add("silicon_chip_with_lubricant", 800, 384);
        ASSEMBLING.RB().ii(of(Wafer), PLATE.getMaterialIngredient(Polyethylene, 1)).io(new ItemStack(CircuitBoardEmpty)).add("empty_circuit_board", 32, 16);
        ASSEMBLING.RB().ii(of(CircuitBoardCoated), PLATE.getMaterialIngredient(Polyethylene, 1)).io(new ItemStack(CircuitBoardEmpty)).add("empty_circuit_board_1", 32, 16);
        PRESSING.RB().ii(of(CircuitBoardEmpty), of(EtchedWiringMV, 4)).io(new ItemStack(CircuitBoardBasic)).add("basic_circuit_board", 32, 16);
        PRESSING.RB().ii(of(CircuitBoardEmpty), of(EtchedWiringHV, 4)).io(new ItemStack(CircuitBoardAdvanced)).add("advanced_circuit_board", 32, 16);
        PRESSING.RB().ii(of(CircuitBoardProcessorEmpty), of(EtchedWiringEV, 4)).io(new ItemStack(CircuitBoardProcessor)).add("processor_circuit_board", 32, 256);
        ASSEMBLING.RB().ii(of(Wafer, 2), PLATE.getMaterialIngredient(Polytetrafluoroethylene, 1)).io(new ItemStack(CircuitBoardProcessorEmpty)).add("empty_processor_circuit_board", 32, 256);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Lazurite, 1), DUST.getMaterialIngredient(AntimatterMaterials.Glowstone, 1)).io(new ItemStack(AdvCircuitParts, 2)).add("advanced_circuit_parts", 32, 64);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Lapis, 1), DUST.getMaterialIngredient(AntimatterMaterials.Glowstone, 1)).io(new ItemStack(AdvCircuitParts, 2)).add("advanced_circuit_parts", 32, 64);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Polyethylene, 1), of(WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY), 1)).fi(Tin.getLiquid(L / 4)).io(new ItemStack(NandChip)).add("nand_chip_tin_poly", 32, 16);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Polyethylene, 1), of(WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY), 1)).fi(SolderingAlloy.getLiquid(L / 8)).io(new ItemStack(NandChip)).add("nand_chip_soldering_alloy_poly", 32, 16);
        ASSEMBLING.RB().ii(PLATE.getMaterialIngredient(Polyethylene, 1), of(WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY), 1)).fi(Lead.getLiquid(L / 2)).io(new ItemStack(NandChip)).add("nand_chip_lead_poly", 32, 16);
        ASSEMBLING.RB().ii(ITEM_CASING.getMaterialIngredient(Steel, 1), of(WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY), 2)).fi(Tin.getLiquid(L / 4)).io(new ItemStack(NandChip)).add("nand_chip_tin_steel", 32, 16);
        ASSEMBLING.RB().ii(ITEM_CASING.getMaterialIngredient(Steel, 1), of(WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY), 2)).fi(SolderingAlloy.getLiquid(L / 8)).io(new ItemStack(NandChip)).add("nand_chip_soldering_alloy_steel", 32, 16);
        ASSEMBLING.RB().ii(ITEM_CASING.getMaterialIngredient(Steel, 1), of(WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY), 2)).fi(Lead.getLiquid(L / 2)).io(new ItemStack(NandChip)).add("nand_chip_lead_steel", 32, 16);
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
        BLASTING.RB().temperature(2484).ii(DUST.getMaterialIngredient(Silicon, 16), DUST.getMaterialIngredient(Glowstone, 1)).fi(Nitrogen.getGas(1000)).io(GlowstoneDopedSiliconBoule).add("glowstone_doped_silicon_boule", 12000, 480);
        BLASTING.RB().temperature(2484).ii(DUST.getMaterialIngredient(Silicon, 16), DUST.getMaterialIngredient(Naquadah, 1)).fi(Argon.getGas(1000)).io(NaquadahDopedSiliconBoule).add("naquadah_doped_silicon_boule", 15000, 1920);
        //Phenolic
        ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardCoated,1), of(PLATE.get(AntimatterMaterials.Wood),1))
                .fi(Phenol.getLiquid(100))
                .io(new ItemStack(GregTechData.CircuitBoardPhenolic,1))
                .add("phenolic_circuit_board",8*20, 32);
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

    private static void bloodyCircuits() {
        for (Material material : SOLDER.all()) {
            long base = L / 8;
            boolean hasGood = SOLDER.has(SubTag.GOOD_SOLDER, material);
            boolean hasBad = SOLDER.has(SubTag.BAD_SOLDER, material);
            base *= hasBad ? (hasGood ? 2 : 4) : 1;
            //Basic
            ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardCoated, 1), of(GregTechData.Resistor, 2),of(GregTechData.Transistor, 2),
                            of(GregTechData.Capacitor, 2),of(AntimatterMaterialTypes.WIRE_FINE.get(AntimatterMaterials.Copper), 2))
                    .io(new ItemStack(GregTechData.CircuitBasicElectronic,1))
                    .fi(material.getLiquid(base * 4)).add("basic_circuit_using_" + material.getId(),20*20, 8);
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
