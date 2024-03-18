package muramasa.gregtech.loader.machines;

import io.github.gregtechintergalactical.gtcore.block.RedstoneWire;
import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.GregTechConfig;
import muramasa.gregtech.data.GregTechBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static muramasa.gregtech.data.GregTechBlocks.FLUID_PIPE_STEEL;
import static muramasa.gregtech.data.Materials.Carbon;
import static muramasa.gregtech.data.RecipeMaps.WIRE_MILL;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;

public class WiremillLoader {
    public static void init() {
        AntimatterAPI.all(Wire.class).forEach(t -> {
            Item wireItem = t.getBlockItem(PipeSize.VTINY);
            ItemStack stack = new ItemStack(wireItem,2);
            RecipeIngredient ing = t.getMaterial().has(INGOT) ? INGOT.getMaterialIngredient(t.getMaterial(),1) : DUST.getMaterialIngredient(t.getMaterial(),1);
            WIRE_MILL.RB().ii(ing).io(stack).add(t.getMaterial().getId() + "_wire", 100,4);
            if (WIRE_FINE.allowItemGen(t.getMaterial())) {
                WIRE_MILL.RB().ii(wireItem).io(WIRE_FINE.get(t.getMaterial(),4)).add(t.getMaterial().getId() + "_wire_fine", 200,8);
            }
        });
        AntimatterAPI.all(RedstoneWire.class).forEach(t -> {
            Item wireItem = t.getBlockItem(PipeSize.VTINY);
            ItemStack stack = new ItemStack(wireItem,2);
            RecipeIngredient ing = t.getMaterial().has(INGOT) ? INGOT.getMaterialIngredient(t.getMaterial(),1) : DUST.getMaterialIngredient(t.getMaterial(),1);
            WIRE_MILL.RB().ii(ing).io(stack).add(t.getMaterial().getId() + "_wire", 100,4);
            if (WIRE_FINE.allowItemGen(t.getMaterial())) {
                WIRE_MILL.RB().ii(wireItem).io(WIRE_FINE.get(t.getMaterial(),4)).add(t.getMaterial().getId() + "_wire_fine", 200,8);
            }
        });
        WIRE_MILL.RB().ii(FLUID_PIPE_STEEL.getBlockItem(PipeSize.TINY)).io(GregTechBlocks.MINING_PIPE_THIN.asItem()).add("mining_pipe", 200, 16);
        if (!GregTechConfig.HARD_CARBON.get()){
            WIRE_MILL.RB().ii(DUST.getMaterialIngredient(Carbon, 8)).io(GTCoreItems.CarbonFibre).add("carbon_fibre", 400, 2);
        }
    }
}
