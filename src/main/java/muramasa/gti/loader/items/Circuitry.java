package muramasa.gti.loader.items;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.SubTag;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.gti.data.GregTechData;
import net.minecraft.item.ItemStack;

import static muramasa.antimatter.material.MaterialTag.SOLDER;
import static muramasa.gti.data.Materials.*;
import static muramasa.gti.data.RecipeMaps.ASSEMBLING;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;

public class Circuitry {
    public static void init() {
        Wire<?> redAlloyWire = AntimatterAPI.getOrThrow(Wire.class,"wire_" + RedAlloy.getId(), () -> new RuntimeException("Missing Red Alloy Wire in Circuitry.java"));
        Wire<?> copperWire = AntimatterAPI.getOrThrow(Wire.class,"wire_" + Copper.getId(), () -> new RuntimeException("Missing Copper Wire in Circuitry.java"));
        Wire<?> goldWire = AntimatterAPI.getOrThrow(Wire.class,"wire_" + Gold.getId(), () -> new RuntimeException("Missing Copper Wire in Circuitry.java"));
        for (Material material : SOLDER.all()) {
            int base = 18;
            boolean hasGood = SOLDER.has(SubTag.GOOD_SOLDER, material);
            boolean hasBad = SOLDER.has(SubTag.BAD_SOLDER, material);
            base *= hasBad ? (hasGood ? 2 : 4) : 1;
            //LV
            ASSEMBLING.RB().ii(of(new ItemStack(GregTechData.CircuitBoardBasic)),
                    of(new ItemStack(GregTechData.NandChip, 2)), of(new ItemStack(redAlloyWire.getBlockItem(PipeSize.VTINY), 2)))
                    .io(new ItemStack(GregTechData.CircuitBasic))
                    .fi(material.getLiquid(base * 4)).add(40, 24);

            //MV
            ASSEMBLING.RB().ii(of(new ItemStack(GregTechData.CircuitBoardBasic)),
                    of(new ItemStack(GregTechData.CircuitBasic, 2)), of(new ItemStack(copperWire.getBlockItem(PipeSize.VTINY), 2)))
                    .io(new ItemStack(GregTechData.CircuitGood))
                    .fi(material.getLiquid(base * 6)).add(40, 24);

            //HV
            ASSEMBLING.RB().ii(of(new ItemStack(GregTechData.CircuitBoardAdv)),
                    of(new ItemStack(GregTechData.CircuitGood, 2)), of(new ItemStack(goldWire.getBlockItem(PipeSize.VTINY), 4)))
                    .io(new ItemStack(GregTechData.CircuitAdv))
                    .fi(material.getLiquid(base * 8)).add(40, 24);
        }
        //BOARDS
        ASSEMBLING.RB().ii(of(new ItemStack(GregTechData.StickyResin)), of(new ItemStack(copperWire.getBlockItem(PipeSize.VTINY),4)))
                .io(new ItemStack(GregTechData.CircuitBoardBasic))
                .add(60, 8);
    }
}
