package muramasa.gregtech.data;

import com.gtnewhorizon.structurelib.StructureLibAPI;
import com.gtnewhorizon.structurelib.structure.AutoPlaceEnvironment;
import com.gtnewhorizon.structurelib.structure.IItemSource;
import com.gtnewhorizon.structurelib.structure.IStructureElement;
import com.gtnewhorizon.structurelib.structure.StructureUtility;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.structure.BlockStateElement;
import muramasa.antimatter.structure.FakeTileElement;
import muramasa.gregtech.block.BlockCoil;
import muramasa.gregtech.tile.multi.TileEntityCokeOven;
import muramasa.gregtech.tile.multi.TileEntityElectricBlastFurnace;
import muramasa.gregtech.tile.multi.TileEntityPrimitiveBlastFurnace;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.gtnewhorizon.structurelib.structure.IStructureElement.PlaceResult.SKIP;
import static muramasa.antimatter.data.AntimatterMaterialTypes.BLOCK;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.Machines.*;
import static muramasa.gregtech.data.Materials.Lithium;

public class Structures {

    /** Special Case Elements **/
    public static IStructureElement<?> AIR_OR_LAVA = StructureUtility.ofChain(StructureUtility.isAir(), StructureUtility.ofBlockAdder((w, b) -> b == Blocks.LAVA || b == LAVA, Blocks.LAVA));//new BlockStateElement("air_or_lava", (w, p, s) -> s.isAir() || s.getBlock() == Blocks.LAVA || s.getBlock() == LAVA);
    public static IStructureElement<?> GLASS_BLOCK = StructureUtility.ofBlock(Blocks.GLASS);
    public static IStructureElement<?> LITHIUM_BLOCK = StructureUtility.ofBlock(BLOCK.get().get(Lithium).asBlock());
    public static final FakeTileElement<TileEntityCokeOven> FAKE_CASING = new FakeTileElement<>(CASING_FIRE_BRICK);
    public static void init() {

        BLAST_FURNACE.setStructure(TileEntityElectricBlastFurnace.class, b -> b.part("main")
                .of("CCC", "CFC", "CCC").of("BBB", "B-B", "BBB").of(1).of("C~C", "CCC", "CCC").build()
                .at('F',HATCH_MUFFLER)
                .at('B', ofCoil(TileEntityElectricBlastFurnace::setHeatingCapacity, TileEntityElectricBlastFurnace::getHeatingCapacity)).at('C', CASING_HEAT_PROOF, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_FLUID_I, HATCH_FLUID_O, HATCH_ENERGY)
                .min(1, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY).offset(1, 0, 0).build()
        );

        COKE_OVEN.setStructure(TileEntityCokeOven.class, b -> b.part("main")
            .of("CCC", "CCC", "CCC").of("C~C", "C-C", "CCC").of(0).build()
            .atElement('C', FAKE_CASING).offset(1, 1, 0)
            .build()
        );

        /*COMBUSTION_ENGINE.setStructure(b -> b.part("main")
                .of("CCCV", "CCCV", "CCCV").of("CHHV", "EAAM", "CHHV").of(0).build()
                .at("M", COMBUSTION_ENGINE).at("C", CASING_TITANIUM).at("V", CASING_ENGINE_INTAKE).at("H", CASING_TITANIUM, HATCH_FLUID_I, HATCH_FLUID_O).at("E", HATCH_DYNAMO)
                .build().offset(3, -1).min(19, CASING_TITANIUM).min(1, HATCH_FLUID_I, HATCH_FLUID_O)
        );

        CRACKING_UNIT.setStructure(b -> b.part("main")
                .of("CCC", "BBB", "CCC", "BBB", "CCC").of("HHH","BAB", "HAM","BAB", "HHH").of("CCC", "BBB", "CCC", "BBB", "CCC").build()
                .at("C", CASING_STAINLESS_STEEL).at("M", CRACKING_UNIT)
                .at("B", "coil", AntimatterAPI.all(BlockCoil.class))
                .at("H", CASING_STAINLESS_STEEL, HATCH_ITEM_I, HATCH_FLUID_I, HATCH_FLUID_O, HATCH_ENERGY)
                .build().offset(2, -1)
        );*/
        /*List<Structure> structures = new ArrayList<>();
        for (int i = 0; i < 11; i++){
            StructureBuilder builder = new StructureBuilder().of("ccc", "ccM", "ccc").of("CCC","CAC", "CCC");
            if (i != 0){
                for (int j = 0; j < i; j++){
                    builder.of(1);
                }
            }
            builder.of("CCC", "CCC", "CCC").at("M", DISTLLATION_TOWER)
                    .at("c", CASING_STAINLESS_STEEL,HATCH_FLUID_I, HATCH_ENERGY)
                    .at("C", CASING_STAINLESS_STEEL,HATCH_FLUID_O);;
            Structure structure = builder
                    .build().offset(2,0).min(20 + (i * 7), CASING_STAINLESS_STEEL).min(1, HATCH_ENERGY).exact(1, HATCH_FLUID_I).min(i+2, HATCH_FLUID_O);
            structures.add(structure);
        }
        DISTLLATION_TOWER.setStructures(structures);*/
        /*DISTLLATION_TOWER.setStructure(b -> b.part("main")
                .of("CCC", "CCM", "CCC").of("CCC","CAC", "CCC").of(1).of(1).of(1).of(1).of(1).of(1).of(1).of(1).of("CCC", "CCC", "CCC").build()
                .at("M", DISTLLATION_TOWER)
                .at("C", CASING_STAINLESS_STEEL,HATCH_FLUID_I, HATCH_FLUID_O, HATCH_ENERGY)
                .build().offset(2,0).min(30, CASING_STAINLESS_STEEL).min(1, HATCH_ENERGY).exact(1, HATCH_FLUID_I).min(4, HATCH_FLUID_O)
        );

        HEAT_EXCHANGER.setStructure(b -> b.part("main")
                .of("DDD", "DDM", "DDD").of("CCC","CPC", "CCC").of(1).of("DDD", "DDD", "DDD").build()
                .at("M", HEAT_EXCHANGER).at("D", CASING_TITANIUM, HATCH_ITEM_I, HATCH_FLUID_I)
                .at("C", CASING_TITANIUM, HATCH_FLUID_O).at("P", CASING_PIPE_TITANIUM)
                .build().offset(2,0).min(30, CASING_TITANIUM).min(1, HATCH_FLUID_I, HATCH_FLUID_O).min(2, HATCH_FLUID_I)
        );

        IMPLOSION_COMPRESSOR.setStructure(b -> b.part("main")
                .of("CCC", "CCC", "CCC").of("CCC", "CAC", "CCC").of("CCC", "CAM", "CCC").of(0).build()
                .at("M", IMPLOSION_COMPRESSOR).at("C", CASING_SOLID_STEEL, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY)
                .build().offset(2, -2).min(16, CASING_SOLID_STEEL).min(1, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY)
        );

        LARGE_TURBINE.setStructure(b -> b.part("main")
                .of("CCCC", "CCCC", "CCCC").of("CHHC", "EAAM", "CHHC").of(0).build()
                .at("M", LARGE_TURBINE).at("C", CASING_TURBINE_4).at("H", CASING_TURBINE_4, HATCH_FLUID_I, HATCH_FLUID_O).at("E", HATCH_DYNAMO)
                .build().offset(3, -1).min(28, CASING_TURBINE_4).min(1, HATCH_FLUID_I, HATCH_FLUID_O)
        );

        MULTI_SMELTER.setStructure(b -> b.part("main")
                .of("CCC", "CCM", "CCC").of("BBB", "BAB", "BBB").of("CCC", "CCC", "CCC").build()
                .at("M", MULTI_SMELTER).at("B", "coil", AntimatterAPI.all(BlockCoil.class)).at("C", CASING_HEAT_PROOF, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY)
                .build().offset(2, 0).min(12, CASING_HEAT_PROOF).min(1, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY)
        );

        NUCLEAR_REACTOR.setStructure(b -> b.part("main")
                .of("CCC", "CCM", "CCC").of("CGC", "GLG", "CGC").of(1).of(1).of("CCC", "CCC", "CCC").build()
                .at("C", CASING_RADIATION_PROOF, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_FLUID_I, HATCH_FLUID_O)
                .at("G", GLASS_BLOCK).at("L", LITHIUM_BLOCK).at("M", NUCLEAR_REACTOR)
                .build().offset(2,0).min(1, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_FLUID_I, HATCH_FLUID_O)
        );*/

        PRIMITIVE_BLAST_FURNACE.setStructure(TileEntityPrimitiveBlastFurnace.class, b -> b.part("main")
            .of("CCC", "C-C", "CCC").of("CCC", "CBC", "CCC").of("C~C", "CBC", "CCC").of("CCC", "CCC", "CCC").build()
            .at('C', CASING_FIRE_BRICK).atElement('B', (IStructureElement<TileEntityPrimitiveBlastFurnace>) AIR_OR_LAVA)
                .offset(1, 1, 0).build()
        );

        /*PYROLYSIS_OVEN.setStructure(b -> b.part("main")
                .of("BCCCB", "BCCCB", "BCCCB").of("BCCCB", "BCACM", "BCCCB").of(0).build()
                .at("M", PYROLYSIS_OVEN).at("C", AntimatterAPI.all(BlockCoil.class)).at("B", CASING_ULV, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY)
                .build().offset(4, -1).min(1, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY)
        );

        VACUUM_FREEZER.setStructure(b -> b.part("main")
            .of("CCC", "CCC", "CCC").of("CCC", "CAM", "CCC").of(0).build()
            .at("M", VACUUM_FREEZER).at("C", CASING_FROST_PROOF, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_FLUID_I, HATCH_ENERGY)
            .build().offset(2, -1).min(22, CASING_FROST_PROOF).min(1, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY)
        );

        //TODO Tier sensitive...144
        FUSION_REACTOR.setStructure(Tier.LUV, b -> b.part("main")
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
            ).of(0).build()
            .at("O", CASING_FUSION_2).at("C", COIL_FUSION).at("M", FUSION_REACTOR).at("B", CASING_FUSION_2, HATCH_FLUID_I).at("H", CASING_FUSION_2, HATCH_FLUID_O).at("E", CASING_FUSION_2, HATCH_ENERGY)
            .build().offset(2, -1).min(2, HATCH_FLUID_I).min(1, HATCH_FLUID_O, HATCH_ENERGY));*/
    }

    /**
     * Assume all coils accepted.
     *
     * @see #ofCoil(BiPredicate, Function)
     */
    public static <T> IStructureElement<T> ofCoil(BiConsumer<T, Integer> aHeatingCoilSetter,
                                                  Function<T, Integer> aHeatingCoilGetter) {
        return ofCoil((t, l) -> {
            aHeatingCoilSetter.accept(t, l);
            return true;
        }, aHeatingCoilGetter);
    }

    /**
     * Heating coil structure element.
     *
     * @param aHeatingCoilSetter Notify the controller of this new coil. Got called exactly once per coil. Might be
     *                           called less times if structure test fails. If the setter returns false then it assumes
     *                           the coil is rejected.
     * @param aHeatingCoilGetter Get the current heating level. Null means no coil recorded yet.
     */
    public static <T> IStructureElement<T> ofCoil(BiPredicate<T, Integer> aHeatingCoilSetter,
                                                  Function<T, Integer> aHeatingCoilGetter) {
        if (aHeatingCoilSetter == null || aHeatingCoilGetter == null) {
            throw new IllegalArgumentException();
        }
        return new IStructureElement<>() {

            @Override
            public boolean check(T t, Level world, int x, int y, int z) {
                Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
                if (!(block instanceof BlockCoil coil)) return false;
                int existingHeat = aHeatingCoilGetter.apply(t),
                        newLevel = coil.getHeatCapacity();
                if (existingHeat == 0) {
                    return aHeatingCoilSetter.test(t, newLevel);
                } else {
                    return newLevel == existingHeat;
                }
            }

            @Override
            public boolean spawnHint(T t, Level world, int x, int y, int z, ItemStack trigger) {
                //StructureLibAPI.hintParticle(world, x, y, z, GregTech_API.sBlockCasings5, getMetaFromHint(trigger));
                return true;
            }

            @Override
            public void onStructureFail(T t, Level world, int x, int y, int z) {
                if (aHeatingCoilGetter.apply(t) != 0){
                    aHeatingCoilSetter.test(t, 0);
                }
            }

            @Override
            public boolean placeBlock(T t, Level world, int x, int y, int z, ItemStack trigger) {
                return false;
                //return world.setBlock(x, y, z, GregTech_API.sBlockCasings5, getMetaFromHint(trigger), 3);
            }

            /*private int getMetaFromHint(ItemStack trigger) {
                return GT_Block_Casings5.getMetaFromCoilHeat(getHeatFromHint(trigger));
            }

            private HeatingCoilLevel getHeatFromHint(ItemStack trigger) {
                return HeatingCoilLevel
                        .getFromTier((byte) Math.min(HeatingCoilLevel.getMaxTier(), Math.max(0, trigger.stackSize - 1)));
            }



            @Override
            public BlocksToPlace getBlocksToPlace(T t, World world, int x, int y, int z, ItemStack trigger,
                                                  AutoPlaceEnvironment env) {
                return BlocksToPlace.create(GregTech_API.sBlockCasings5, getMetaFromHint(trigger));
            }

            @Override
            public PlaceResult survivalPlaceBlock(T t, World world, int x, int y, int z, ItemStack trigger,
                                                  IItemSource s, EntityPlayerMP actor, Consumer<IChatComponent> chatter) {
                return survivalPlaceBlock(
                        t,
                        world,
                        x,
                        y,
                        z,
                        trigger,
                        AutoPlaceEnvironment.fromLegacy(s, actor, chatter));
            }

            @Override
            public PlaceResult survivalPlaceBlock(T t, World world, int x, int y, int z, ItemStack trigger,
                                                  AutoPlaceEnvironment env) {
                Block block = world.getBlock(x, y, z);
                boolean isCoil = block instanceof IHeatingCoil
                        && ((IHeatingCoil) block).getCoilHeat(world.getBlockMetadata(x, y, z)) == getHeatFromHint(trigger);
                if (isCoil) return SKIP;
                return StructureUtility.survivalPlaceBlock(
                        GregTech_API.sBlockCasings5,
                        getMetaFromHint(trigger),
                        world,
                        x,
                        y,
                        z,
                        env.getSource(),
                        env.getActor(),
                        env.getChatter());
            }*/
        };
    }
}
