package muramasa.gregtech.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.structure.BlockStateElement;
import muramasa.antimatter.structure.FakeTileElement;
import muramasa.gregtech.block.BlockCoil;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;

import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.Machines.*;

public class Structures {

    /** Special Case Elements **/
    public static BlockStateElement AIR_OR_LAVA = new BlockStateElement("air_or_lava", (w, p, s) -> s.isAir() || s.getBlock() == Blocks.LAVA/* || s.getBlock() == Blocks.FLOWING_LAVA*/);
    public static final FakeTileElement FAKE_CASING = new FakeTileElement(CASING_FIRE_BRICK);
    public static void init() {
        COKE_OVEN.setStructure(b -> b
            .of("CCC", "CCC", "CCC").of("CCC", "CAM", "CCC").of(0)
            .at("C", FAKE_CASING).at("M", COKE_OVEN)
            .build().offset(2, -1));//.min(25, FAKE_CASING)
        CRACKING_UNIT.setStructure(b -> b
                .of("CCC", "BBB", "CCC", "BBB", "CCC").of("HHH","BAB", "HAM","BAB", "HHH").of("CCC", "BBB", "CCC", "BBB", "CCC")
                .at("C", CASING_STAINLESS_STEEL).at("M", CRACKING_UNIT)
                .at("B", "coil", AntimatterAPI.all(BlockCoil.class))
                .at("H", CASING_STAINLESS_STEEL, HATCH_ITEM_I, HATCH_FLUID_I, HATCH_FLUID_O, HATCH_ENERGY)
                .build().offset(2, -1)
        );
        PRIMITIVE_BLAST_FURNACE.setStructure(b -> b
            .of("CCC", "CCC", "CCC").of("CCC", "CBM", "CCC").of("CCC", "CBC", "CCC").of("CCC", "CAC", "CCC")
            .at("C", CASING_BLAST_BRICK).at("B", AIR_OR_LAVA).at("M", PRIMITIVE_BLAST_FURNACE)
            .build().offset(2, -1).min(32, CASING_BLAST_BRICK)
        );
        BRONZE_BLAST_FURNACE.setStructure(b -> b
            .of("CCC", "CCC", "CCC").of("CCC", "CBM", "CCC").of("CCC", "CBC", "CCC").of("CCC", "CAC", "CCC")
            .at("C", CASING_BRONZE_PLATED_BRICK).at("B", AIR_OR_LAVA).at("M", BRONZE_BLAST_FURNACE)
            .build().offset(2, -1).min(32, CASING_BRONZE_PLATED_BRICK)
        );
        BLAST_FURNACE.setStructure(b -> b
            .of("CCC", "CCM", "CCC").of("BBB", "BAB", "BBB").of(1).of("CCC", "CFC", "CCC")
            .at("F",HATCH_MUFFLER)
            .at("M", BLAST_FURNACE).at("B", "coil", AntimatterAPI.all(BlockCoil.class)).at("C", CASING_HEAT_PROOF, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_FLUID_I, HATCH_FLUID_O, HATCH_ENERGY)
            .build().offset(2, 0).min(12, CASING_HEAT_PROOF).min(1, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY)
        );
        
        MULTI_SMELTER.setStructure(b -> b
            .of("CCC", "CCM", "CCC").of("BBB", "BAB", "BBB").of("CCC", "CCC", "CCC")
            .at("M", MULTI_SMELTER).at("B", "coil", AntimatterAPI.all(BlockCoil.class)).at("C", CASING_HEAT_PROOF, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY)
            .build().offset(2, 0).min(12, CASING_HEAT_PROOF).min(1, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY)
        );
        VACUUM_FREEZER.setStructure(b -> b
            .of("CCC", "CCC", "CCC").of("CCC", "CAM", "CCC").of(0)
            .at("M", VACUUM_FREEZER).at("C", CASING_FROST_PROOF, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_FLUID_I, HATCH_ENERGY)
            .build().offset(2, -1).min(22, CASING_FROST_PROOF).min(1, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY)
        );
        IMPLOSION_COMPRESSOR.setStructure(b -> b
            .of("CCC", "CCC", "CCC").of("CCC", "CAM", "CCC").of(0)
            .at("M", IMPLOSION_COMPRESSOR).at("C", CASING_SOLID_STEEL, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY)
            .build().offset(2, -1).min(16, CASING_SOLID_STEEL).min(1, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY)
        );
        PYROLYSIS_OVEN.setStructure(b -> b
            .of("BBBBB", "BCCCB", "BCCCM", "BCCCB", "BBBBB").of("SSSSS", "SAAAS", "SAAAS", "SAAAS", "SSSSS").of(1).of("TTTTT", "TTTTT", "TTYTT", "TTTTT", "TTTTT")
            .at("M", PYROLYSIS_OVEN).at("S", CASING_ULV).at("C", AntimatterAPI.all(BlockCoil.class)).at("B", CASING_ULV, HATCH_ITEM_O, HATCH_ENERGY).at("T", CASING_ULV, HATCH_ITEM_I).at("Y", HATCH_MUFFLER)
            .build().offset(4, 0).min(60, CASING_ULV).min(1, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY, HATCH_MUFFLER)
        );
        LARGE_TURBINE.setStructure(b -> b
            .of("CCCC", "CCCC", "CCCC").of("CHHC", "EAAM", "CHHC").of(0)
            .at("M", LARGE_TURBINE).at("C", CASING_TURBINE_4).at("H", CASING_TURBINE_4, HATCH_FLUID_I, HATCH_FLUID_O).at("E", HATCH_DYNAMO)
            .build().offset(3, -1).min(28, CASING_TURBINE_4).min(1, HATCH_FLUID_I, HATCH_FLUID_O)
        );
        COMBUSTION_ENGINE.setStructure(b -> b
            .of("CCCV", "CCCV", "CCCV").of("CHHV", "EAAM", "CHHV").of(0)
            .at("M", COMBUSTION_ENGINE).at("C", CASING_TITANIUM).at("V", CASING_ENGINE_INTAKE).at("H", CASING_TITANIUM, HATCH_FLUID_I, HATCH_FLUID_O).at("E", HATCH_DYNAMO)
            .build().offset(3, -1).min(19, CASING_TITANIUM).min(1, HATCH_FLUID_I, HATCH_FLUID_O)
        );
        DISTLLATION_TOWER.setStructure(b -> b
        .of("DDD", "DDM", "DDD").of("CCC","CAC", "CCC").of(1).of(1).of(1)
        .at("M", DISTLLATION_TOWER).at("D", CASING_STAINLESS_STEEL,HATCH_ITEM_I, HATCH_ITEM_O, HATCH_FLUID_I, HATCH_ENERGY)
                .at("C", CASING_STAINLESS_STEEL,HATCH_FLUID_O)
                .build().offset(2,0).min(30, CASING_STAINLESS_STEEL).min(1, HATCH_ENERGY, HATCH_FLUID_I).min(4, HATCH_FLUID_O)
        );
        /*HEAT_EXCHANGER.setStructure(b -> b
                .of("DDD", "DDM", "DDD").of("CCC","CAC", "CCC").of(1).of(1).of(1)
                .at("M", HEAT_EXCHANGER).at("D", CASING_TITANIUM,HATCH_ITEM_I, HATCH_FLUID_I, HATCH_HEAT_COPPER)
                .at("C", CASING_TITANIUM,HATCH_FLUID_O)
                .build().offset(2,0).min(30, CASING_TITANIUM).min(1, HATCH_FLUID_I, HATCH_FLUID_O).min(1, HATCH_HEAT_COPPER)
        );
        NUCLEAR_REACTOR.setStructure(b -> b
                .of("CCC", "CCC", "CCC").of("CCC", "CAM", "CCC").of(0)
                .at("C", "components", CASING_RADIATION_PROOF, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_FLUID_I, HATCH_HEAT_COPPER).at("M", NUCLEAR_REACTOR)
                .facings(Direction.UP)
                .build().offset(2,-1).exact(1, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_FLUID_I, HATCH_HEAT_COPPER));*/
        //TODO Tier sensitive...144
        FUSION_REACTOR.setStructure(Tier.LUV, b -> b
            .of(
                "               ",
                "      BOB      ",
                "    OO   OO    ",
                "   O       O   ",
                "  O         O  ",
                "  O         O  ",
                " B           B ",
                " O           O ",
                " B           B ",
                "  O         O  ",
                "  O         O  ",
                "   O       O   ",
                "    OO   OO    ",
                "      BOB      ",
                "               "
            ).of(
                "      OOO      ",
                "    OOCCCOO    ",
                "   OCCHOHCCO   ",
                "  OCEO   OECO  ",
                " OCE       ECO ",
                " OCO       OCO ",
                "OCH         HCO",
                "OCM         HCO",
                "OCH         HCO",
                " OCO       OCO ",
                " OCE       ECO ",
                "  OCEO   OECO  ",
                "   OCCHOHCCO   ",
                "    OOCCCOO    ",
                "      OOO      "
            ).of(0)
            .at("O", CASING_FUSION_3).at("C", COIL_FUSION).at("M", FUSION_REACTOR).at("B", CASING_FUSION_3, HATCH_FLUID_I).at("H", CASING_FUSION_3, HATCH_FLUID_O).at("E", CASING_FUSION_3, HATCH_ENERGY)
            .build().offset(2, -1).min(2, HATCH_FLUID_I).min(1, HATCH_FLUID_O, HATCH_ENERGY));
    }
}
