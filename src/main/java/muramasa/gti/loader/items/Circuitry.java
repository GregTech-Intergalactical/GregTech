package muramasa.gti.loader.items;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.SubTag;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gti.data.GregTechData;
import muramasa.gti.data.Materials;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.Data.BOLT;
import static muramasa.antimatter.material.MaterialTag.SOLDER;
import static muramasa.gti.data.Materials.*;
import static muramasa.gti.data.RecipeMaps.ASSEMBLING;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gti.data.RecipeMaps.CHEMICAL_REACTING;

public class Circuitry {
    public static void init() {
        Wire<?> redAlloyWire = AntimatterAPI.getOrThrow(Wire.class,"wire_" + RedAlloy.getId(), () -> new RuntimeException("Missing Red Alloy Wire in Circuitry.java"));
        Wire<?> copperWire = AntimatterAPI.getOrThrow(Wire.class,"wire_" + Copper.getId(), () -> new RuntimeException("Missing Copper Wire in Circuitry.java"));
        Wire<?> goldWire = AntimatterAPI.getOrThrow(Wire.class,"wire_" + Gold.getId(), () -> new RuntimeException("Missing Copper Wire in Circuitry.java"));
        boards();
        circuits();
        parts();
    }

    private static void boards () {
        //Coated
        ASSEMBLING.RB().ii(of(GregTechData.StickyResin,2), of(PLATE.get(Materials.Wood),8))
                .fi(Glue.getLiquid(100))
                .io(new ItemStack(GregTechData.CircuitBoardCoated,8))
                .add(8*20, 8);
        //Phenolic
        ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardCoated,1), of(PLATE.get(Materials.Wood),1))
                .fi(Phenol.getLiquid(100))
                .io(new ItemStack(GregTechData.CircuitBoardPhenolic,1))
                .add(8*20, 32);
        //Plastic
        CHEMICAL_REACTING.RB().ii(of(GregTechData.CircuitBoardPhenolic,1), of(PLATE.get(Polyethylene),1), of(FOIL.get(Copper),1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(GregTechData.CircuitBoardPlastic,1))
                .add(8*20, 128);
        CHEMICAL_REACTING.RB().ii(of(GregTechData.CircuitBoardPhenolic,1), of(PLATE.get(PolyvinylChloride),1), of(FOIL.get(Copper),1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(GregTechData.CircuitBoardPlastic,2))
                .add(8*20, 128);
        CHEMICAL_REACTING.RB().ii(of(GregTechData.CircuitBoardPhenolic,1), of(PLATE.get(Polytetrafluoroethylene),1), of(FOIL.get(Copper),1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(GregTechData.CircuitBoardPlastic,4))
                .add(8*20, 128);
        //Epoxy
        CHEMICAL_REACTING.RB().ii(of(GregTechData.CircuitBoardPlastic,1), of(PLATE.get(Epoxid),1), of(FOIL.get(AnnealedCopper),1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(GregTechData.CircuitBoardEpoxy,1))
                .add(8*20, 512);
        //Fiber
        CHEMICAL_REACTING.RB().ii(of(GregTechData.CircuitBoardEpoxy,1), of(PLATE.get(EpoxidFiberReinforced),1), of(FOIL.get(AnnealedCopper),1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(GregTechData.CircuitBoardFiber,1))
                .add(8*20, 2048);
        //MultiFiber
        CHEMICAL_REACTING.RB().ii(of(GregTechData.CircuitBoardFiber,1), of(FOIL.get(Electrum),1))
                .fi(SulfuricAcid.getLiquid(125))
                .io(new ItemStack(GregTechData.CircuitBoardMultiFiber,1))
                .add(8*20, 8192);
        //Wetware
        ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardMultiFiber,1), of(GregTechData.CircuitEnergyFlow,1), of(FOIL.get(Electrum),1))
                .fi(Polystyrene.getLiquid(144))
                .io(new ItemStack(GregTechData.CircuitBoardWetware,1))
                .add(8*20, 32768);
    }

    private static void circuits () {
        for (Material material : SOLDER.all()) {
            int base = 18;
            boolean hasGood = SOLDER.has(SubTag.GOOD_SOLDER, material);
            boolean hasBad = SOLDER.has(SubTag.BAD_SOLDER, material);
            base *= hasBad ? (hasGood ? 2 : 4) : 1;
            //Basic
            ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardCoated, 1), of(GregTechData.Resistor, 2),of(GregTechData.Transistor, 2),
                            of(GregTechData.Capacitor, 2),of(WIRE_FINE.get(Copper), 2))
                    .io(new ItemStack(GregTechData.CircuitBasicElectronic,1))
                    .fi(material.getLiquid(base * 4)).add(20*20, 8);
            //Good
            ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardPhenolic, 1), of(GregTechData.Resistor, 2),of(GregTechData.Transistor, 2),
                            of(GregTechData.Capacitor, 2),of(WIRE_FINE.get(Electrum), 2))
                    .io(new ItemStack(GregTechData.CircuitGood,1))
                    .fi(material.getLiquid(base * 4)).add(20*20, 32);
            //Advanced
            ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardPlastic, 1), of(GregTechData.Resistor, 4),of(GregTechData.Transistor, 4),
                            of(GregTechData.Capacitor, 4),of(WIRE_FINE.get(RedAlloy), 2))
                    .io(new ItemStack(GregTechData.CircuitAdv,1))
                    .fi(material.getLiquid(base * 4)).add(20*20, 128);
            //Nano
            ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardEpoxy, 1), of(GregTechData.Resistor, 4),of(GregTechData.Transistor, 4),
                            of(GregTechData.Capacitor, 4),of(WIRE_FINE.get(RoseGold), 2))
                    .io(new ItemStack(GregTechData.CircuitNanoProcessor,1))
                    .fi(material.getLiquid(base * 4)).add(20*20, 512);
            //Quantum
            ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardFiber, 1), of(GregTechData.Resistor, 4),of(GregTechData.Transistor, 4),
                            of(GregTechData.Capacitor, 4),of(WIRE_FINE.get(Platinum), 2))
                    .io(new ItemStack(GregTechData.CircuitQuantumProcessor,1))
                    .fi(material.getLiquid(base * 4)).add(20*20, 2048);
            //Energy Flow
            ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardMultiFiber, 1), of(GregTechData.Resistor, 8),of(GregTechData.Transistor, 8),
                            of(GregTechData.Capacitor, 8),of(WIRE_FINE.get(NiobiumTitanium), 4))
                    .io(new ItemStack(GregTechData.CircuitEnergyFlow,1))
                    .fi(material.getLiquid(base * 4)).add(20*20, 8192);
            //Wetware
            ASSEMBLING.RB().ii(of(GregTechData.CircuitBoardWetware, 1), of(GregTechData.Resistor, 8),of(GregTechData.Transistor, 8),
                            of(GregTechData.Capacitor, 8),of(WIRE_FINE.get(YttriumBariumCuprate), 4))
                    .io(new ItemStack(GregTechData.CircuitWetware,1))
                    .fi(material.getLiquid(base * 4)).add(20*20, 32768);
        }
    }

    private static void parts () {
        //Transistor
        ASSEMBLING.RB().ii(of(PLATE.get(Silicon),1), of(FOIL.get(Tin),6))
                .fi(Polyethylene.getLiquid(144))
                .io(new ItemStack(GregTechData.Transistor,6))
                .add(8*20, 32);
        //Resistor
        ASSEMBLING.RB().ii(of(DUST.get(Coal),2), of(WIRE_FINE.get(Copper),4))
                .io(new ItemStack(GregTechData.Resistor,6))
                .add(8*20, 32);
        //Capacitor
        ASSEMBLING.RB().ii(of(PLATE.get(Polyethylene),1), of(FOIL.get(Aluminium),4))
                .io(new ItemStack(GregTechData.Capacitor,6))
                .add(8*20, 32);
    }
}
