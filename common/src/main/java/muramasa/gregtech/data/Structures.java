package muramasa.gregtech.data;

import com.gtnewhorizon.structurelib.structure.IStructureElement;
import com.gtnewhorizon.structurelib.structure.StructureUtility;
import muramasa.antimatter.structure.FakeTileElement;
import muramasa.antimatter.util.int3;
import muramasa.gregtech.block.BlockCoil;
import muramasa.gregtech.blockentity.multi.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.BLOCK;
import static muramasa.antimatter.data.AntimatterMaterialTypes.FRAME;
import static muramasa.antimatter.structure.AntimatterStructureUtility.ofHatch;
import static muramasa.gregtech.data.Machines.*;
import static muramasa.gregtech.data.Materials.Lithium;
import static muramasa.gregtech.data.Materials.Steel;

public class Structures {

    /** Special Case Elements **/
    public static IStructureElement<?> AIR_OR_LAVA = ofChain(StructureUtility.isAir(), StructureUtility.ofBlockAdder((w, b) -> b == Blocks.LAVA || b == GregTechBlocks.LAVA, Blocks.LAVA));
    public static IStructureElement<?> GLASS_BLOCK = ofBlock(Blocks.GLASS);
    public static IStructureElement<?> LITHIUM_BLOCK = ofBlock(BLOCK.getBlockMaterialTag(Lithium));
    public static final FakeTileElement<BlockEntityCokeOven> FAKE_CASING = new FakeTileElement<>(GregTechBlocks.CASING_FIRE_BRICK);
    public static void init() {

        BLAST_FURNACE.setStructure(BlockEntityElectricBlastFurnace.class, b -> b.part("main")
                .of("CCC", "CFC", "CCC").of("BBB", "B-B", "BBB").of(1).of("H~H", "HCH", "HHH").build()
                .at('F',HATCH_MUFFLER)
                .at('B', ofCoil(BlockEntityElectricBlastFurnace::setCoilData, BlockEntityElectricBlastFurnace::getCoilData))
                .at('C', GregTechBlocks.CASING_HEAT_PROOF)
                .at('H', GregTechBlocks.CASING_HEAT_PROOF, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_FLUID_I, HATCH_FLUID_O, HATCH_ENERGY)
                .min(1, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY).offset(1, 3, 0).build()
        );

        COKE_OVEN.setStructure(BlockEntityCokeOven.class, b -> b.part("main")
            .of("CCC", "CCC", "CCC").of("C~C", "C-C", "CCC").of(0).build()
            .atElement('C', FAKE_CASING).offset(1, 1, 0)
            .build()
        );

        COMBUSTION_ENGINE.setStructure(BlockEntityCombustionEngine.class, b -> b.part("main")
                .of("VVV", "CCC", "CCC", "CCC").of("V~V", "H-H", "H-H", "CEC").of(0).build()
                .at('C', GregTechBlocks.CASING_TITANIUM).at('V', GregTechBlocks.CASING_ENGINE_INTAKE).at('H', GregTechBlocks.CASING_TITANIUM, HATCH_FLUID_I, HATCH_FLUID_O).at('E', HATCH_DYNAMO)
                .offset(1, 1, 0).min(1, HATCH_FLUID_I, HATCH_FLUID_O).build()
        );

        CRACKING_UNIT.setStructure(BlockEntityOilCrackingUnit.class, b -> b.part("main")
                .of("CBMBC", "CBMBC", "CBMBC").of( "CB~BC", "L---R", "CBMBC").of(0).build()
                .at('C', GregTechBlocks.CASING_STAINLESS_STEEL)
                //.at("B", "coil", AntimatterAPI.all(BlockCoil.class))
                .at('B', GregTechBlocks.COIL_CUPRONICKEL)
                .at('L', HATCH_FLUID_O, GregTechBlocks.CASING_STAINLESS_STEEL)
                .at('R', HATCH_FLUID_I)
                .at('M', GregTechBlocks.CASING_STAINLESS_STEEL, HATCH_ITEM_I, HATCH_FLUID_I, HATCH_ITEM_O, HATCH_ENERGY)
                .offset(2, 1, 0).max(1, HATCH_FLUID_O).min(2, HATCH_FLUID_I).min(1, HATCH_ENERGY).build()
        );

        DISTLLATION_TOWER.setStructure(BlockEntityDistillationTower.class, b -> b.part("bottom")
                .of("H~H", "HHH", "HHH").build()
                .part("layer").of("CCC", "C-C", "CCC").offsetFunction((i, int3) -> new int3(int3.getX(), int3.getY() + i, int3.getZ())).max(11).build()
                .part("top").of("CCC", "CCC", "CCC").offsetFunction((i, int3) -> new int3(int3.getX(), int3.getY() + i, int3.getZ())).build()
                .atElement('C', ofChain(StructureUtility.<BlockEntityDistillationTower>ofBlock(GregTechBlocks.CASING_STAINLESS_STEEL), ofHatch(HATCH_FLUID_O, (distillationTower, world, pos, machine, handler) -> {
                    int currentY = pos.getY() - distillationTower.getBlockPos().getY();
                    if (distillationTower.HATCH_LAYERS.contains(currentY)) return false;
                    distillationTower.HATCH_LAYERS.add(currentY);
                    distillationTower.FO_HATCHES.add(handler);
                    return true;
                })))
                .at('H', GregTechBlocks.CASING_STAINLESS_STEEL, HATCH_FLUID_I, HATCH_ENERGY, HATCH_ITEM_O)
                .offset(1, 0, 0).min(1, HATCH_ENERGY, HATCH_FLUID_O).exact(1, HATCH_FLUID_I)
                .setStructurePartCheckCallback((structureDefinition, tile, part, i, newOffset) -> {
                    tile.FO_HATCHES.clear();
                    boolean check = structureDefinition.check(tile, part, tile.getLevel(), tile.getExtendedFacing(), tile.getBlockPos().getX(), tile.getBlockPos().getY(), tile.getBlockPos().getZ(), newOffset.getX(), newOffset.getY(), newOffset.getZ(), !tile.isStructureValid());
                     if (!part.equals("bottom")){
                        if (check){
                            tile.FO_HATCHES.forEach(h -> tile.addComponent(HATCH_FLUID_O.getId(), h));
                            tile.LAYERS.add(i);
                        } else {
                            tile.HATCH_LAYERS.remove(i);
                        }
                    }
                    return check && tile.LAYERS.size() == tile.HATCH_LAYERS.size();
                }).build());
        CRYO_DISTLLATION_TOWER.setStructure(BlockEntityDistillationTower.class, b -> b.part("bottom")
                .of("H~H", "HHH", "HHH").build()
                .part("layer").of("CCC", "C-C", "CCC").offsetFunction((i, int3) -> new int3(int3.getX(), int3.getY() + i, int3.getZ())).max(11).build()
                .part("top").of("CCC", "CCC", "CCC").offsetFunction((i, int3) -> new int3(int3.getX(), int3.getY() + i, int3.getZ())).build()
                .atElement('C', ofChain(StructureUtility.<BlockEntityDistillationTower>ofBlock(GregTechBlocks.CASING_FROST_PROOF), ofHatch(HATCH_FLUID_O, (distillationTower, world, pos, machine, handler) -> {
                    int currentY = pos.getY() - distillationTower.getBlockPos().getY();
                    if (distillationTower.HATCH_LAYERS.contains(currentY)) return false;
                    distillationTower.HATCH_LAYERS.add(currentY);
                    distillationTower.FO_HATCHES.add(handler);
                    return true;
                })))
                .at('H', GregTechBlocks.CASING_FROST_PROOF, HATCH_FLUID_I, HATCH_ENERGY)
                .offset(1, 0, 0).min(1, HATCH_ENERGY, HATCH_FLUID_O).exact(1, HATCH_FLUID_I)
                .setStructurePartCheckCallback((structureDefinition, tile, part, i, newOffset) -> {
                    tile.FO_HATCHES.clear();
                    boolean check = structureDefinition.check(tile, part, tile.getLevel(), tile.getExtendedFacing(), tile.getBlockPos().getX(), tile.getBlockPos().getY(), tile.getBlockPos().getZ(), newOffset.getX(), newOffset.getY(), newOffset.getZ(), !tile.isStructureValid());
                    if (!part.equals("bottom")){
                        if (check){
                            tile.FO_HATCHES.forEach(h -> tile.addComponent(HATCH_FLUID_O.getId(), h));
                            tile.LAYERS.add(i);
                        } else {
                            tile.HATCH_LAYERS.remove(i);
                        }
                    }
                    return check && tile.LAYERS.size() == tile.HATCH_LAYERS.size();
                }).build());
        HEAT_EXCHANGER.setStructure(BlockEntityHeatExchanger.class, b -> b.part("main")
                .of("DDD", "DOD", "DDD").of("CCC", "CPC", "CCC").of(1).of("D~D", "DID", "DDD").build()
                .at('D', GregTechBlocks.CASING_TITANIUM, HATCH_ITEM_I, HATCH_ITEM_O)
                .at('C', GregTechBlocks.CASING_TITANIUM, HATCH_FLUID_O, HATCH_FLUID_I).at('P', GregTechBlocks.CASING_PIPE_TITANIUM)
                .at('I', HATCH_FLUID_I).at('O', HATCH_FLUID_O)
                .offset(1, 3, 0).min(2, HATCH_FLUID_O, HATCH_FLUID_I).build());
        IMPLOSION_COMPRESSOR.setStructure(BlockEntityImplosionCompressor.class, b -> b.part("main")
                .of("CCC", "CCC", "CCC").of("C~C", "C-C", "CCC").of("CCC", "CCC", "CCC").build()
                .at('C', GregTechBlocks.CASING_SOLID_STEEL, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY, HATCH_MUFFLER)
                .min(1, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY).exact(1, HATCH_MUFFLER).offset(1, 1, 0).build()
        );
        LARGE_BOILER.setStructure(BlockEntityLargeBoiler.class, b -> b.part("main")
                .of("BBB", "BBB", "BBB").of("BBB", "BPB", "BBB").of(1).of(1).of("F~F", "FFF", "FFF").build()
                .atElement('F', StructureUtility.<BlockEntityLargeBoiler>ofChain(
                        lazy(t -> ofBlock(t.getFireboxCasing())),
                        ofHatch(HATCH_FLUID_I),
                        ofHatch(HATCH_ITEM_I),
                        ofHatch(HATCH_MUFFLER)))
                .atElement('B', StructureUtility.<BlockEntityLargeBoiler>ofChain(
                        lazy(t -> ofBlock(t.getCasing())),
                        ofHatch(HATCH_FLUID_O)))
                .atElement('P', lazy(t -> ofBlock(t.getPipeCasing())))
                .max(1, HATCH_ITEM_I).minMax(1, 2, HATCH_FLUID_I).exact(1, HATCH_MUFFLER).offset(1, 4, 0).build());
        LARGE_CENTRIFUGE.setStructure(BlockEntityLargeCentrifuge.class, b -> b.part("main")
                .of("CCC", "CcC", "CCC").of("C~C", "CCC", "CCC").build()
                .at('C', GregTechBlocks.CASING_TUNGSTENSTEEL, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_FLUID_I, HATCH_FLUID_O, HATCH_ENERGY)
                .at('c', GregTechBlocks.CASING_TUNGSTENSTEEL)
                .offset(1, 1, 0).min(1, HATCH_ENERGY).build()
        );
        LARGE_CHEMICAL_REACTOR.setStructure(BlockEntityLargeChemicalReactor.class, b -> b.part("main")
                .of("CCC", "CCC", "CCC").of("C~C", "CPC", "CCC").of("CCC", "CcC", "CCC").build()
                .at('C', GregTechBlocks.CASING_CHEMICALLY_INERT, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_FLUID_I, HATCH_FLUID_O, HATCH_ENERGY)
                .at('P', GregTechBlocks.CASING_PIPE_PTFE).at('c', ofCoil(BlockEntityLargeChemicalReactor::setCoilData, BlockEntityLargeChemicalReactor::getCoilData))
                .offset(1, 1, 0).min(1, HATCH_ENERGY).build()
        );

        LARGE_ELECTROLYZER.setStructure(BlockEntityLargeElectrolyzer.class, b -> b.part("main")
                .of("CCCCC", "cEEEc", "cEEEc", "cEEEc", "CCCCC").of("CC~CC", "CCCCC", "CCCCC", "CCCCC", "CCCCC").build()
                .at('C', GregTechBlocks.CASING_STAINLESS_STEEL, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_FLUID_I, HATCH_FLUID_O, HATCH_ENERGY)
                .at('c', GregTechBlocks.COIL_NICHROME).at('E', GregTechBlocks.ELECTROLYTIC_CELL)
                .offset(2, 1, 0).min(1, HATCH_ENERGY).build()
        );

        LARGE_MACERATOR.setStructure(BlockEntityLargeMacerator.class, b -> b.part("main")
                .of("CCCCC", "CGGGC", "CGGGC", "CGGGC", "CCCCC").of(0).of("CC~CC", "CCCCC", "CCCCC", "CCCCC", "CCCCC").build()
                .at('C', GregTechBlocks.CASING_TUNGSTENSTEEL, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY)
                .at('G', GregTechBlocks.GRINDING_WHEELS)
                .offset(2, 2, 0).min(1, HATCH_ENERGY, HATCH_ITEM_I, HATCH_ITEM_O).build()
        );

        LARGE_TURBINE.setStructure(BlockEntityLargeTurbine.class, b -> b.part("main")
                .of("CCC", "CCC", "CCC", "CCC").of("C~C", "H-H", "H-H", "CEC").of(0).build()
                .atElement('C', StructureUtility.lazy(t -> ofBlock(t.getCasing())))
                .atElement('H', StructureUtility.<BlockEntityLargeTurbine>ofChain(
                        StructureUtility.lazy(t -> ofBlock(t.getCasing())),
                        ofHatch(HATCH_FLUID_I),
                        ofHatch(HATCH_FLUID_O)))
                .atElement('E', ofHatch(HATCH_DYNAMO))
                .min(1, HATCH_FLUID_I, HATCH_FLUID_O).offset(1, 1, 0).build()
        );

        MULTI_SMELTER.setStructure(BlockEntityMultiSmelter.class, b -> b.part("main")
                .of("CCC", "CMC", "CCC").of("BBB", "B-B", "BBB").of("H~H", "HHH", "HHH").build()
                .atElement('B', ofCoil(BlockEntityMultiSmelter::setCoilData, BlockEntityMultiSmelter::getCoilData)).at('H', GregTechBlocks.CASING_HEAT_PROOF, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY)
                .at('C', GregTechBlocks.CASING_HEAT_PROOF).at('M', HATCH_MUFFLER)
                .offset(1, 2, 0).min(1, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY).build()
        );

        PRIMITIVE_BLAST_FURNACE.setStructure(BlockEntityPrimitiveBlastFurnace.class, b -> b.part("main")
            .of("CCC", "C-C", "CCC").of("CCC", "CBC", "CCC").of("C~C", "CBC", "CCC").of("CCC", "CCC", "CCC").build()
            .at('C', GregTechBlocks.CASING_FIRE_BRICK).atElement('B', (IStructureElement<BlockEntityPrimitiveBlastFurnace>) AIR_OR_LAVA)
                .offset(1, 2, 0).build()
        );

        PROCESSING_ARRAY.setStructure(BlockEntityProcessingArray.class, b -> b.part("main")
                .of("CCC", "CCC", "CCC").of("C~C", "C-C", "CCC").of(0).build()
                .at('C', GregTechBlocks.CASING_TUNGSTENSTEEL, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_FLUID_I, HATCH_FLUID_O, HATCH_ENERGY)
                .offset(1, 1, 0).min(1, HATCH_ENERGY).build()
        );

        PYROLYSIS_OVEN.setStructure(BlockEntityPyrolysisOven.class, b -> b.part("main")
                .of("UUUUU", "UHHHU", "UHHHU", "UHHHU", "UUUUU").of("UUUUU", "U---U", "U---U", "U---U", "UUUUU").of(1)
                .of("BB~BB", "BCCCB", "BCCCB", "BCCCB", "BBBBB").build()
                .atElement('C', ofCoil(BlockEntityPyrolysisOven::setCoilData, BlockEntityPyrolysisOven::getCoilData)).at('B', GregTechBlocks.CASING_ULV, HATCH_ITEM_O, HATCH_ENERGY, HATCH_FLUID_O)
                .at('U', GregTechBlocks.CASING_ULV).at('H', GregTechBlocks.CASING_ULV, HATCH_ITEM_I, HATCH_FLUID_I, HATCH_MUFFLER)
                .offset(2, 3, 0).min(1, HATCH_ITEM_I, HATCH_ENERGY).exact(1, HATCH_MUFFLER).build()
        );

        OIL_DRILLING_RIG.setStructure(BlockEntityOilDrillingRig.class, b -> b.part("main")
                .of("   ", " F ", "   ").of(0).of(0).of(" F ", "FCF", " F ").of(3).of(3).of("H~H", "HCH", "HHH").build()
                .at('F', FRAME.get().get(Steel).asBlock()).at('C', GregTechBlocks.CASING_SOLID_STEEL)
                .at('H', GregTechBlocks.CASING_SOLID_STEEL, HATCH_ENERGY, HATCH_FLUID_O)
                .offset(1, 6, 0).min(1, HATCH_FLUID_O, HATCH_ENERGY).build());

        VACUUM_FREEZER.setStructure(BlockEntityVacuumFreezer.class, b -> b.part("main")
            .of("CCC", "CCC", "CCC").of("C~C", "C-C", "CCC").of(0).build()
            .at('C', GregTechBlocks.CASING_FROST_PROOF, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY)
            .offset(1, 1, 0).min(1, HATCH_ITEM_I, HATCH_ITEM_O, HATCH_ENERGY).build()
        );

        FUSION_REACTOR.setStructure(BlockEntityFusionReactor.class, b -> b.part("main")
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
            ).of("      XOX      ",
                 "    OOCCCOO    ",
                 "   ECCXOXCCE   ",
                 "  ECEO   OECE  ",
                 " OCE       ECO ",
                 " OCO       OCO ",
                 "XCX         XCX",
                 "OCO         OCO",
                 "XCX         XCX",
                 " OCO       OCO ",
                 " OCE       ECO ",
                 "  ECEO   OECE  ",
                 "   ECCX~XCCE   ",
                 "    OOCCCOO    ",
                 "      XOX      ").of(0).build()
                .at('O', GregTechBlocks.CASING_FUSION).at('C', GregTechBlocks.COIL_FUSION)
                .atElement('B', StructureUtility.<BlockEntityFusionReactor>ofChain(
                        ofBlock(GregTechBlocks.CASING_FUSION),
                        ofHatch(HATCH_FLUID_I),
                        ofHatch(HATCH_ITEM_I)))
                .atElement('X', StructureUtility.<BlockEntityFusionReactor>ofChain(
                        ofBlock(GregTechBlocks.CASING_FUSION),
                        ofHatch(HATCH_FLUID_O),
                        ofHatch(HATCH_ITEM_O)))
                .atElement('E', StructureUtility.<BlockEntityFusionReactor>ofChain(ofBlock(GregTechBlocks.CASING_FUSION), ofHatch(HATCH_ENERGY)))
            .offset(7, 1, 12).min(1, HATCH_FLUID_I).min(1, HATCH_FLUID_O).min(4, HATCH_ENERGY).build());
    }

    /**
     * Assume all coils accepted.
     *
     * @see #ofCoil(BiPredicate, Function)
     */
    public static <T> IStructureElement<T> ofCoil(BiConsumer<T, BlockCoil.CoilData> aHeatingCoilSetter,
                                                  Function<T, BlockCoil.CoilData> aHeatingCoilGetter) {
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
    public static <T> IStructureElement<T> ofCoil(BiPredicate<T, BlockCoil.CoilData> aHeatingCoilSetter,
                                                  Function<T, BlockCoil.CoilData> aHeatingCoilGetter) {
        if (aHeatingCoilSetter == null || aHeatingCoilGetter == null) {
            throw new IllegalArgumentException();
        }
        return new IStructureElement<>() {

            @Override
            public boolean check(T t, Level world, int x, int y, int z) {
                Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
                if (!(block instanceof BlockCoil coil)) return false;
                BlockCoil.CoilData existingHeat = aHeatingCoilGetter.apply(t),
                        newLevel = coil.getCoilData();
                if (existingHeat == null) {
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
                if (aHeatingCoilGetter.apply(t) != null){
                    aHeatingCoilSetter.test(t, null);
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
