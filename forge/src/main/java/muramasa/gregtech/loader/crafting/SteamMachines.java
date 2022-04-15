package muramasa.gregtech.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.data.Machines;
import muramasa.gregtech.data.Materials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static muramasa.antimatter.Data.*;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.Materials.Bronze;

public class SteamMachines {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        Object bronzePlate = PLATE.get(Bronze);
        Object steelPlate = PLATE.get(Materials.Steel);
        Object wrench = WRENCH.getTag();
        Object bricks = Items.BRICKS;
        Object furnace = Items.FURNACE;
        Object piston = TagUtils.getForgelikeItemTag("pistons");
        Object diamond = Items.DIAMOND;
        Object glass = Items.GLASS;

        Object bronzePipe = FLUID_PIPE_BRONZE.getBlock(PipeSize.SMALL);
        Object hull = CASING_BRONZE;
        Object brickedHull = CASING_BRICKED_BRONZE;
        provider.addItemRecipe(output, "steam_machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), Machines.COAL_BOILER.getItem(Tier.BRONZE),
                ImmutableMap.of(
                        'P', bronzePlate,
                        'W', wrench,
                        'B', bricks,
                        'F', furnace
                ), "PPP", "PWP", "BFB");
        provider.addItemRecipe(output, "steam_machines", "has_bronze", provider.hasSafeItem(INGOT.getMaterialTag(Bronze)), CASING_BRONZE,
                ImmutableMap.of(
                        'P', bronzePlate,
                        'W', wrench
                ), "PPP", "PWP", "PPP");
        provider.addItemRecipe(output, "steam_machines", "has_bronze", provider.hasSafeItem(INGOT.getMaterialTag(Bronze)), CASING_BRICKED_BRONZE,
                ImmutableMap.of(
                        'P', bronzePlate,
                        'W', wrench,
                        'B', Items.BRICKS
                ), "PPP", "PWP", "BBB");
        provider.addItemRecipe(output, "steam_machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), Machines.COAL_BOILER.getItem(Tier.STEEL),
                ImmutableMap.of(
                        'P', steelPlate,
                        'W', wrench,
                        'B', bricks,
                        'F', furnace
                ), "PPP", "PWP", "BFB");
        provider.addItemRecipe(output, "steam_machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), Machines.STEAM_PULVERIZER.getItem(Tier.BRONZE),
                ImmutableMap.of(
                        'B', bronzePipe,
                        'H', brickedHull,
                        'P', piston,
                        'D', diamond
                ), "DBD", "BHB", "PBP");
        provider.addItemRecipe(output, "steam_machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), Machines.STEAM_EXTRACTOR.getItem(Tier.BRONZE),
                ImmutableMap.of(
                        'B', bronzePipe,
                        'H', hull,
                        'P', piston,
                        'G', glass
                ), "BBB", "PHG", "BBB");
        provider.addItemRecipe(output, "steam_machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), Machines.STEAM_FORGE_HAMMER.getItem(Tier.BRONZE),
                ImmutableMap.of(
                        'B', bronzePipe,
                        'H', hull,
                        'P', piston,
                        'A', Items.ANVIL
                ), "BPB", "BHB", "BAB");
        provider.addItemRecipe(output, "steam_machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), Machines.STEAM_COMPRESSOR.getItem(Tier.BRONZE),
                ImmutableMap.of(
                        'B', bronzePipe,
                        'H', hull,
                        'P', piston
                ), "BBB", "PHP", "BBB");
        provider.addItemRecipe(output, "steam_machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), Machines.STEAM_ALLOY_SMELTER.getItem(Tier.BRONZE),
                ImmutableMap.of(
                        'B', bronzePipe,
                        'H', brickedHull,
                        'F', Items.FURNACE
                ), "BBB", "FHF", "BBB");
        provider.addItemRecipe(output, "steam_machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), Machines.STEAM_FURNACE.getItem(Tier.BRONZE),
                ImmutableMap.of(
                        'B', bronzePipe,
                        'H', brickedHull,
                        'F', Items.FURNACE
                ), "BBB", "BHB", "BFB");
    }
}
