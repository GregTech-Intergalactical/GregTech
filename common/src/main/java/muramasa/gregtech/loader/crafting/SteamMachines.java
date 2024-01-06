package muramasa.gregtech.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.data.GregTechBlocks;
import muramasa.gregtech.data.Machines;
import muramasa.gregtech.data.Materials;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static muramasa.antimatter.data.AntimatterDefaultTools.HAMMER;
import static muramasa.antimatter.data.AntimatterDefaultTools.WRENCH;
import static muramasa.gregtech.data.Materials.Bronze;

public class SteamMachines {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        Object bronzePlate = AntimatterMaterialTypes.PLATE.getMaterialTag(Bronze);
        Object steelPlate = AntimatterMaterialTypes.PLATE.getMaterialTag(Materials.Steel);
        Object silverPlate = AntimatterMaterialTypes.PLATE.getMaterialTag(Materials.Silver);
        Object wrench = WRENCH.getTag();
        Object bricks = Items.BRICKS;
        Object furnace = Items.FURNACE;
        Object piston = TagUtils.getForgelikeItemTag("pistons");
        Object diamond = Items.DIAMOND;
        Object glass = Items.GLASS;
        Object steelPipe = GregTechBlocks.FLUID_PIPE_STEEL.getBlock(PipeSize.SMALL);
        Object bronzePipe = GregTechBlocks.FLUID_PIPE_BRONZE.getBlock(PipeSize.SMALL);
        Object hullBronze = GregTechBlocks.CASING_BRONZE;
        Object hullSteel = GregTechBlocks.CASING_STEEL;
        Object brickedBronzeHull = GregTechBlocks.CASING_BRICKED_BRONZE;
        Object brickedSteelHull = GregTechBlocks.CASING_BRICKED_STEEL;
        provider.addItemRecipe(output, "steam_machines", Machines.COAL_BOILER.getItem(Tier.BRONZE),
                ImmutableMap.of(
                        'P', bronzePlate,
                        'W', wrench,
                        'B', bricks,
                        'F', furnace
                ), "PPP", "PWP", "BFB");
        provider.addItemRecipe(output, "steam_machines", GregTechBlocks.CASING_BRONZE,
                ImmutableMap.of(
                        'P', bronzePlate,
                        'W', HAMMER.getTag()
                ), "PPP", "PWP", "PPP");
        provider.addItemRecipe(output, "steam_machines", Machines.COAL_BOILER.getItem(Tier.STEEL),
                ImmutableMap.of(
                        'P', steelPlate,
                        'W', wrench,
                        'B', bricks,
                        'F', furnace
                ), "PPP", "PWP", "BFB");
        provider.addItemRecipe(output, "steam_machines", Machines.LAVA_BOILER.getItem(Tier.STEEL),
                ImmutableMap.of(
                        'P', steelPlate,
                        'G', glass,
                        'H', brickedSteelHull
                ), "PPP", "GGG", "PHP");
        provider.addItemRecipe(output, "steam_machines", Machines.SOLAR_BOILER.getItem(Tier.BRONZE),
                ImmutableMap.of(
                        'P', silverPlate,
                        'G', glass,
                        'I', bronzePipe,
                        'H', brickedBronzeHull
                ), "GGG", "PPP", "IHI");
        provider.addItemRecipe(output, "steam_machines", Machines.STEAM_MACERATOR.getItem(Tier.BRONZE),
                ImmutableMap.of(
                        'B', bronzePipe,
                        'H', brickedBronzeHull,
                        'P', piston,
                        'D', diamond
                ), "DBD", "BHB", "PBP");
        provider.addItemRecipe(output, "steam_machines", Machines.STEAM_EXTRACTOR.getItem(Tier.BRONZE),
                ImmutableMap.of(
                        'B', bronzePipe,
                        'H', hullBronze,
                        'P', piston,
                        'G', glass
                ), "BBB", "PHG", "BBB");
        provider.addItemRecipe(output, "steam_machines", Machines.STEAM_FORGE_HAMMER.getItem(Tier.BRONZE),
                ImmutableMap.of(
                        'B', bronzePipe,
                        'H', hullBronze,
                        'P', piston,
                        'A', Items.ANVIL
                ), "BPB", "BHB", "BAB");
        provider.addItemRecipe(output, "steam_machines", Machines.STEAM_COMPRESSOR.getItem(Tier.BRONZE),
                ImmutableMap.of(
                        'B', bronzePipe,
                        'H', hullBronze,
                        'P', piston
                ), "BBB", "PHP", "BBB");
        provider.addItemRecipe(output, "steam_machines", Machines.STEAM_ALLOY_SMELTER.getItem(Tier.BRONZE),
                ImmutableMap.of(
                        'B', bronzePipe,
                        'H', brickedBronzeHull,
                        'F', Items.FURNACE
                ), "BBB", "FHF", "BBB");
        provider.addItemRecipe(output, "steam_machines", Machines.STEAM_SIFTER.getItem(Tier.BRONZE),
                ImmutableMap.of(
                        'B', bronzePipe,
                        'H', hullBronze,
                        'P', piston,
                        'S', GregTechBlocks.WIRE_STEEL.getBlock(PipeSize.VTINY)
                ), "SSS", "PHP", "BBB");
        provider.addItemRecipe(output, "steam_machines", Machines.STEAM_FURNACE.getItem(Tier.BRONZE),
                ImmutableMap.of(
                        'B', bronzePipe,
                        'H', brickedBronzeHull,
                        'F', Items.FURNACE
                ), "BBB", "BHB", "BFB");

        provider.addItemRecipe(output, "steam_machines", Machines.STEAM_FURNACE.getItem(Tier.STEEL),
                ImmutableMap.<Character, Object>builder()
                        .put('H', brickedSteelHull)
                        .put('F', Items.FURNACE)
                        .put('P', steelPipe).build(), "PPP", "PHP", "PFP");

        provider.addItemRecipe(output, "steam_machines", Machines.STEAM_MACERATOR.getItem(Tier.STEEL),
                ImmutableMap.of(
                        'B', steelPipe,
                        'H', brickedSteelHull,
                        'P', piston,
                        'D', diamond
                ), "DBD", "BHB", "PBP");

        provider.addItemRecipe(output, "steam_machines", Machines.STEAM_ALLOY_SMELTER.getItem(Tier.STEEL),
                ImmutableMap.of(
                        'B', steelPipe,
                        'H', brickedSteelHull,
                        'F', Items.FURNACE
                ), "BBB", "FHF", "BBB");

        provider.addItemRecipe(output, "steam_machines", Machines.STEAM_EXTRACTOR.getItem(Tier.STEEL),
                ImmutableMap.of(
                        'B', steelPipe,
                        'H', hullSteel,
                        'P', piston,
                        'G', glass
                ), "BBB", "PHG", "BBB");

        provider.addItemRecipe(output, "steam_machines", Machines.STEAM_COMPRESSOR.getItem(Tier.STEEL),
                ImmutableMap.of(
                        'B', steelPipe,
                        'H', hullSteel,
                        'P', piston
                ), "BBB", "PHP", "BBB");

        provider.addItemRecipe(output, "steam_machines", Machines.STEAM_FORGE_HAMMER.getItem(Tier.STEEL),
                ImmutableMap.of(
                        'B', steelPipe,
                        'H', hullSteel,
                        'P', piston,
                        'A', Items.ANVIL
                ), "BPB", "BHB", "BAB");
        provider.addItemRecipe(output, "steam_machines", Machines.STEAM_SIFTER.getItem(Tier.STEEL),
                ImmutableMap.of(
                        'B', steelPipe,
                        'H', hullSteel,
                        'P', piston,
                        'S', GregTechBlocks.WIRE_STEEL.getBlock(PipeSize.VTINY)
                ), "SSS", "PHP", "BBB");
    }
}
