package muramasa.gregtech.loader.items;

import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.SubTag;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.world.item.ItemStack;

import static muramasa.gregtech.data.GregTechMaterialTags.SOLDER;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.ASSEMBLING;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.RecipeMaps.CHEMICAL_REACTING;

public class Circuitry {
    public static void init() {
        boards();
        circuits();
    }

    private static void boards () {
        //Coated
        ASSEMBLING.RB().ii(of(GTRubberData.StickyResin,2), of(AntimatterMaterialTypes.PLATE.get(AntimatterMaterials.Wood),8))
                .fi(Glue.getLiquid(100))
                .io(new ItemStack(GregTechData.CircuitBoardCoated,8))
                .add("coated_circuit_board",8*20, 8);
        //Phenolic
        ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardCoated,1), of(AntimatterMaterialTypes.PLATE.get(AntimatterMaterials.Wood),1))
                .fi(Phenol.getLiquid(100))
                .io(new ItemStack(GregTechData.CircuitBoardPhenolic,1))
                .add("phenolic_circuit_board",8*20, 32);
        //Plastic
        CHEMICAL_REACTING.RB().ii(of(GregTechData.CircuitBoardPhenolic,1), of(AntimatterMaterialTypes.PLATE.get(Polyethylene),1), of(AntimatterMaterialTypes.FOIL.get(AntimatterMaterials.Copper),1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(GregTechData.CircuitBoardPlastic,1))
                .add("plastic_circuit_board",8*20, 128);
        CHEMICAL_REACTING.RB().ii(of(GregTechData.CircuitBoardPhenolic,1), of(AntimatterMaterialTypes.PLATE.get(PolyvinylChloride),1), of(AntimatterMaterialTypes.FOIL.get(AntimatterMaterials.Copper),1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(GregTechData.CircuitBoardPlastic,2))
                .add("plastic_circuit_board_2",8*20, 128);
        CHEMICAL_REACTING.RB().ii(of(GregTechData.CircuitBoardPhenolic,1), of(AntimatterMaterialTypes.PLATE.get(Polytetrafluoroethylene),1), of(AntimatterMaterialTypes.FOIL.get(AntimatterMaterials.Copper),1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(GregTechData.CircuitBoardPlastic,4))
                .add("plastic_circuit_board_3",8*20, 128);
        //Epoxy
        CHEMICAL_REACTING.RB().ii(of(GregTechData.CircuitBoardPlastic,1), of(AntimatterMaterialTypes.PLATE.get(Epoxid),1), of(AntimatterMaterialTypes.FOIL.get(AnnealedCopper),1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(GregTechData.CircuitBoardEpoxy,1))
                .add("epoxy_circuit_board",8*20, 512);
        //Fiber
        CHEMICAL_REACTING.RB().ii(of(GregTechData.CircuitBoardEpoxy,1), of(AntimatterMaterialTypes.PLATE.get(EpoxidFiberReinforced),1), of(AntimatterMaterialTypes.FOIL.get(AnnealedCopper),1))
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

    private static void circuits () {
        for (Material material : SOLDER.all()) {
            int base = 18;
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
                    .io(new ItemStack(GregTechData.CircuitGood,1))
                    .fi(material.getLiquid(base * 4)).add("good_circuit_using_" + material.getId(),20*20, 32);
            //Advanced
            ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardPlastic, 1), of(GregTechData.Resistor, 4),of(GregTechData.Transistor, 4),
                            of(GregTechData.Capacitor, 4),of(AntimatterMaterialTypes.WIRE_FINE.get(RedAlloy), 2))
                    .io(new ItemStack(GregTechData.CircuitAdv,1))
                    .fi(material.getLiquid(base * 4)).add("advanced_circuit_using_" + material.getId(),20*20, 128);
            //Nano
            ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardEpoxy, 1), of(GregTechData.Resistor, 4),of(GregTechData.Transistor, 4),
                            of(GregTechData.Capacitor, 4),of(AntimatterMaterialTypes.WIRE_FINE.get(RoseGold), 2))
                    .io(new ItemStack(GregTechData.CircuitNanoProcessor,1))
                    .fi(material.getLiquid(base * 4)).add("nano_processor_circuit_using_" + material.getId(),20*20, 512);
            //Quantum
            ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardFiber, 1), of(GregTechData.Resistor, 4),of(GregTechData.Transistor, 4),
                            of(GregTechData.Capacitor, 4),of(AntimatterMaterialTypes.WIRE_FINE.get(Platinum), 2))
                    .io(new ItemStack(GregTechData.CircuitQuantumProcessor,1))
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
