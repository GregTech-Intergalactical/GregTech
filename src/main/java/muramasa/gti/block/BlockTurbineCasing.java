package muramasa.gti.block;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import muramasa.antimatter.datagen.builder.AntimatterBlockModelBuilder;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemModelProvider;
import muramasa.antimatter.dynamic.ModelConfig;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.mixin.ChunkReaderAccessor;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.structure.StructureCache;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.util.int3;
import muramasa.gti.tile.multi.TileEntityLargeTurbine;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.chunk.ChunkRenderCache;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static muramasa.antimatter.client.AntimatterModelManager.LOADER_DYNAMIC;

public class BlockTurbineCasing extends BlockCasingMachine {

    public BlockTurbineCasing(String domain, String id) {
        super(domain, id);
    }

    @Override
    protected String getTextureID() {
        return "turbine";
    }

    @Override
    public boolean canConnect(IBlockReader world, BlockState state, @Nullable TileEntity tile, BlockPos pos) {
        return false;
    }

    @Override
    public void onItemModelBuild(IItemProvider item, AntimatterItemModelProvider prov) {
        prov.modelAndTexture(item, AntimatterBlockModelBuilder.getSimple()).tex(t -> t.putAll(AntimatterBlockModelBuilder.buildTextures(((ITextureProvider) item).getTextures())));
    }

    @Override
    public ModelConfig getConfig(BlockState state, IBlockReader world, BlockPos.Mutable mut, BlockPos pos) {
        int[] conf = super.getConfig(state, world, mut, pos).getConfig();
        int[] ct = new int[conf.length+1];
        int3 mutable = new int3();
        mutable.set(pos);
        TileEntityLargeTurbine turbine = getTurbine(world, pos);
        if (turbine != null) {
            BlockPos controllerPos = turbine.getBlockPos();
            Vector3i vec = new Vector3i((pos.getX() - controllerPos.getX()), (pos.getY() - controllerPos.getY()), (pos.getZ() - controllerPos.getZ()));
            int c = getOffset(vec, turbine.getFacing());
            c += (turbine.getMachineState() == MachineState.ACTIVE ? 10000 : 0);
            ct[1] = c;
        }
        ct[0] = conf[0];
        return config.set(ct);
    }

    protected Texture[] turbineTextures() {
        Texture[] tex = new Texture[9];
        for (int i = 0; i <= 8; i++) {
            tex[i] = new Texture(domain,"block/ct/turbine/large_turbine_active_"+i);
        }
        return tex;
    }

    protected Texture[] turbineTexturesInactive() {
        Texture[] tex = new Texture[9];
        for (int i = 0; i <= 8; i++) {
            tex[i] = new Texture(domain,"block/ct/turbine/large_turbine_"+i);
        }
        return tex;
    }


    protected int getOffset(Vector3i vec) {
        return vec.getX() + vec.getY()*10 + vec.getZ()*100;
    }

    //essentially a facing transformation
    protected int getOffset(Vector3i vec, Direction dir) {
        return getOffset(vec) + dir.get2DDataValue()*1000;
    }

    private final static String SIDED = "antimatter:block/preset/simple_overlay";
    private final static String SIMPLE = "antimatter:block/preset/simple";

    private final Vector3i[] VECS = new Vector3i[]{
            new Vector3i(1,-1,0),
            new Vector3i(0,-1,0),
            new Vector3i(-1,-1,0),
            new Vector3i(1,0,0),
            new Vector3i(0,0,0),
            new Vector3i(-1,0,0),
            new Vector3i(1,1,0),
            new Vector3i(0,1,0),
            new Vector3i(-1,1,0),
    };

    private final void forSides(Direction dir, BiConsumer<int3, Integer> consumer) {
        int3 pos = new int3(dir);
        int i = 0;
        pos.set(0, 0, 0);
        pos = pos.above(1);
        pos = pos.right(1);
        consumer.accept(pos,i++);
        pos = pos.left(1);
        consumer.accept(pos,i++);
        pos = pos.left(1);
        consumer.accept(pos,i++);
        pos = pos.right(2);
        pos = pos.below(1);
        consumer.accept(pos,i++);
        i++;
        pos = pos.left(2);
        consumer.accept(pos,i++);
        pos = pos.right(2);
        pos = pos.below(1);
        consumer.accept(pos,i++);
        pos = pos.left(1);
        consumer.accept(pos,i++);
        pos = pos.left(1);
        consumer.accept(pos,i++);
    }

    @Override
    public AntimatterBlockModelBuilder buildBlock(Block block, AntimatterBlockStateProvider prov) {
        Texture[] tex = turbineTextures();
        Texture[] inactive = turbineTexturesInactive();
        AntimatterBlockModelBuilder builder = prov.getBuilder(block);
        for (Direction dir : dirs) {
            forSides(dir, (b,c) -> {
                builder.config(getOffset(b, dir), SIDED, a ->
                a.tex(t -> t.put("base", inactive[c])).rot(dir));
                builder.config(getOffset(b, dir) + 10000, SIDED, a ->
                        a.tex(t -> t.put("base", tex[c])).rot(dir));
            });
        }
        Texture[] texes = ((ITextureProvider)block).getTextures();
        builder.particle(texes[0]);
        builder.config(0, (b, l) -> l.add(b.of(SIMPLE).tex(texes)));
        builder.loader(LOADER_DYNAMIC);
        return builder;
    }
    //just all horizontal dirs
    private static final List<Direction> dirs = Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST);
    //cache.
    private static final Long2ObjectMap<TileEntityLargeTurbine> MAP = new Long2ObjectOpenHashMap<>();

    private TileEntityLargeTurbine checkTurbine(IBlockReader reader, BlockPos pos) {
        TileEntity tile = reader.getBlockEntity(pos);
        return tile instanceof TileEntityLargeTurbine ? (TileEntityLargeTurbine) tile : null;
    }

    protected World getWorld(IBlockReader reader) {
        if (!(reader instanceof ChunkRenderCache)) return null;
        ChunkReaderAccessor cache = (ChunkReaderAccessor) reader;
        return cache.getLevel();
    }

    protected TileEntityLargeTurbine getTurbine(IBlockReader world, BlockPos pos) {
        World w = getWorld(world);
        if (w != null) {
            TileEntityLargeTurbine turbine = StructureCache.getAnyMulti(w, pos, TileEntityLargeTurbine.class);
            if (turbine != null) return turbine;
        }
        return getTurbineSlow(world, pos);
    }

    protected TileEntityLargeTurbine getTurbineSlow(IBlockReader world, BlockPos pos) {
        int3 mutable = new int3();
        mutable.set(pos);
        return MAP.compute(pos.asLong(), (a, b) -> {
            if (b != null && !(b.isRemoved())) return b;
            TileEntityLargeTurbine t;
            for (Direction d : dirs) {
                mutable.set(pos);
                mutable.relative(d, 1);
                t = checkTurbine(world, mutable);
                if (t != null) return t;

                mutable.set(pos);
                mutable.relative(d, -1);
                t = checkTurbine(world, mutable);
                if (t != null) return t;

                mutable.set(pos);
                mutable.relative(Direction.UP, 1);
                t = checkTurbine(world, mutable);
                if (t != null) return t;

                mutable.set(pos);
                mutable.relative(Direction.UP, -1);
                t = checkTurbine(world, mutable);
                if (t != null) return t;

                mutable.set(pos);
                mutable.relative(d, 1);
                mutable.relative(Direction.UP, -1);
                t = checkTurbine(world, mutable);
                if (t != null) return t;

                mutable.set(pos);
                mutable.relative(d);
                mutable.relative(Direction.UP, 1);
                t = checkTurbine(world, mutable);
                if (t != null) return t;

                mutable.set(pos);
                mutable.relative(d, -1);
                mutable.relative(Direction.UP, 1);
                t = checkTurbine(world, mutable);
                if (t != null) return t;

                mutable.set(pos);
                mutable.relative(d, -1);
                mutable.relative(Direction.UP, -1);
                t = checkTurbine(world, mutable);
                if (t != null) return t;

            }
            return null;
        });
    }
}
