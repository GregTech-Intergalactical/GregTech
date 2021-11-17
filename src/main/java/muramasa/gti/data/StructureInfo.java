package muramasa.gti.data;

import muramasa.antimatter.structure.PatternBuilder;
import muramasa.antimatter.structure.StructureElement;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.text.TranslationTextComponent;

import static muramasa.gti.data.GregTechData.*;
import static muramasa.gti.data.Machines.*;
import static muramasa.gti.data.Machines.HATCH_ENERGY;

public class StructureInfo {

    public static void init() {
        // pattern demo
        PatternBuilder builder = new PatternBuilder()
                .of("CCO", "ECM", "CCI").of("BBB", "BAB", "BBB").of(1).of("CCC", "CFC", "CCC")
                .at("F", HATCH_MUFFLER, HATCH_MUFFLER.getFirstTier(), Direction.UP)
                .at("M", BLAST_FURNACE, BLAST_FURNACE.getFirstTier(), Direction.SOUTH)
                .at("C", CASING_HEAT_PROOF.getDefaultState())
                .at("I", HATCH_ITEM_I, HATCH_ITEM_I.getFirstTier(), Direction.SOUTH)
                .at("O", HATCH_ITEM_O, HATCH_ITEM_O.getFirstTier(), Direction.SOUTH)
                .at("E", HATCH_ENERGY, HATCH_ENERGY.getFirstTier(), Direction.NORTH);
        BLAST_FURNACE.setStructurePattern(
                // reuse the builder for page COIL_CUPRONICKEL
                builder.at("B", COIL_CUPRONICKEL.getDefaultState()).description(COIL_CUPRONICKEL.getTranslationKey()).build(),
                // reuse the builder for page COIL_HSSG
                builder.at("B", COIL_HSSG.getDefaultState()).description(COIL_HSSG.getTranslationKey()).build(),
                // reuse the builder for page COIL_KANTHAL
                builder.at("B", COIL_KANTHAL.getDefaultState()).description(COIL_KANTHAL.getTranslationKey()).build(),
                // reuse the builder for page COIL_NAQUADAH and replace one casing with(C) the fluid hatch (K).
                builder.of(3, "CCC", "CFC", "CCK")
                        .at("K", HATCH_FLUID_I, HATCH_FLUID_I.getFirstTier(), Direction.EAST)
                        .at("B", COIL_NAQUADAH.getDefaultState())
                        .description(COIL_NAQUADAH.getTranslationKey())
                        .build());
        builder = new PatternBuilder()
                .of("CCC", "CCC", "CCC").of("CCC", "CAM", "CCC").of(0)
                .at("M", COKE_OVEN, COKE_OVEN.getFirstTier(), Direction.SOUTH)
                .at("C", CASING_FIRE_BRICK.getDefaultState())
                .description(COKE_OVEN.getDisplayName(COKE_OVEN.getFirstTier()));
        COKE_OVEN.setStructurePattern(builder.build());
        builder = new PatternBuilder()
                .of("CCC", "CCC", "CCC").of("CCC", "CAM", "CCC").of("CCC", "CAC", "CCC").of(2)
                .at("M", PRIMITIVE_BLAST_FURNACE, PRIMITIVE_BLAST_FURNACE.getFirstTier(), Direction.SOUTH)
                .at("C", CASING_FIRE_BRICK.getDefaultState());
        PRIMITIVE_BLAST_FURNACE.setStructurePattern(builder
        .at("A", Blocks.AIR.getDefaultState()).description(PRIMITIVE_BLAST_FURNACE.getDisplayName(PRIMITIVE_BLAST_FURNACE.getFirstTier())).build());

        builder = new PatternBuilder()
                .of("CCC", "CCC", "CCC").of("CCI", "EAM", "CCO").of("CCC", "CCC", "CCC")
                .at("M", VACUUM_FREEZER, VACUUM_FREEZER.getFirstTier(), Direction.SOUTH)
                .at("C", CASING_FROST_PROOF.getDefaultState())
                .at("I", HATCH_ITEM_I, HATCH_ITEM_I.getFirstTier(), Direction.SOUTH)
                .at("O", HATCH_ITEM_O, HATCH_ITEM_O.getFirstTier(), Direction.SOUTH)
                .at("E", HATCH_ENERGY, HATCH_ENERGY.getFirstTier(), Direction.NORTH);
        VACUUM_FREEZER.setStructurePattern(builder.build());
        builder = new PatternBuilder()
                .of("CCCC", "CCCC", "CCCC").of("CCCC", "EAAM", "CIOC").of(0)
                .at("M", LARGE_TURBINE, LARGE_TURBINE.getFirstTier(), Direction.SOUTH)
                .at("C", CASING_TURBINE_4.getDefaultState())
                .at("E", HATCH_DYNAMO, HATCH_DYNAMO.getFirstTier(), Direction.NORTH)
                .at("I", HATCH_FLUID_I, HATCH_FLUID_I.getFirstTier(), Direction.EAST)
                .at("O", HATCH_FLUID_O, HATCH_FLUID_O.getFirstTier(), Direction.EAST)
                .description(LARGE_TURBINE.getDisplayName(LARGE_TURBINE.getFirstTier()));
        LARGE_TURBINE.setStructurePattern(builder.build());
    }
}
