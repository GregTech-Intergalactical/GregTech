package muramasa.gregtech.worldgen;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import muramasa.antimatter.util.XSTR;
import muramasa.antimatter.worldgen.AntimatterWorldGenerator;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.RandomSource;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class OilSpoutSavedData extends SavedData {

    private static Map<String, OilSpoutFluid> FLUID_MAP = new Object2ObjectOpenHashMap<>();
    public static final int VEIN_CHUNK_SIZE = 6; // veins are 6x6 chunk squares
    public static final int MAXIMUM_VEIN_OPERATIONS = 100_000;
    public final HashMap<ChunkPos, OilSpoutEntry> veinFluids = new HashMap<>();


    // runtime

    private final ServerLevel serverLevel;

    public static OilSpoutSavedData getOrCreate(ServerLevel serverLevel) {
        return serverLevel.getDataStorage().computeIfAbsent(tag -> new OilSpoutSavedData(serverLevel, tag), () -> new OilSpoutSavedData(serverLevel), "gti_oil_spouts");
    }

    public OilSpoutSavedData(ServerLevel serverLevel) {
        this.serverLevel = serverLevel;
    }

    public OilSpoutSavedData(ServerLevel serverLevel, CompoundTag nbt) {
        this(serverLevel);
        var list = nbt.getList("oilSpout", Tag.TAG_COMPOUND);
        for (Tag tag : list) {
            if (tag instanceof CompoundTag compoundTag) {
                var chunkPos = new ChunkPos(compoundTag.getLong("p"));
                veinFluids.put(chunkPos, OilSpoutEntry.readFromNBT(compoundTag.getCompound("d")));
            }
        }
    }

    @Override
    public CompoundTag save(CompoundTag compoundTag) {
        var oilList = new ListTag();
        for (var entry : veinFluids.entrySet()) {
            var tag = new CompoundTag();
            tag.putLong("p", entry.getKey().toLong());
            tag.put("d", entry.getValue().writeToNBT());
            oilList.add(tag);
        }
        compoundTag.put("oilSpout", oilList);
        return compoundTag;
    }

    /**
     * Gets the FluidVeinWorldInfo object associated with the given chunk
     *
     * @param chunkX X coordinate of desired chunk
     * @param chunkZ Z coordinate of desired chunk
     * @return The FluidVeinWorldInfo corresponding with the given chunk
     */
    public OilSpoutEntry getFluidVeinWorldEntry(int chunkX, int chunkZ) {
        if (!veinFluids.containsKey(new ChunkPos(chunkX, chunkZ))) {
            Random random = new XSTR( (serverLevel.getSeed() +
                    ((int)Math.floor((double)chunkX/(double)VEIN_CHUNK_SIZE)) +
                    (7 * ((int)Math.floor((double)chunkZ/VEIN_CHUNK_SIZE)))));
            OilSpoutFluid fluid = getRandomFluid(random);
            random = serverLevel.random;
            int maximumYield = 0;
            if (fluid != null) {
                if (fluid.getMaxYield() - fluid.getMinYield() <= 0) {
                    maximumYield = fluid.getMinYield();
                } else {
                    maximumYield = random.nextInt(fluid.getMaxYield() - fluid.getMinYield()) + fluid.getMinYield();
                }
                maximumYield = Math.min(maximumYield, fluid.getMaxYield());
            }
            veinFluids.put(new ChunkPos(chunkX, chunkZ), new OilSpoutEntry(fluid, maximumYield));
        }
        setDirty();
        return veinFluids.get(new ChunkPos(chunkX, chunkZ));
    }

    public OilSpoutFluid getRandomFluid (Random aRandom) {
        int random = aRandom.nextInt(1000);
        for (OilSpoutFluid fl : FLUID_MAP.values()) {
            int chance = fl.chance()*1000/ OilSpoutFluid.getTotalWeight();
            if (random<=chance) return fl;
            //System.out.println("GT UO "+fl.getValue().Registry+" Chance:"+chance+" Random:"+random);
            random-=chance;
        }
        return null;
    }

    public static void clearFluidMap(){
        FLUID_MAP.clear();
    }

    public static void registerOilSpoutFluid(String id, OilSpoutFluid fluid){
        FLUID_MAP.put(id, fluid);
    }

    public static OilSpoutFluid getOilSpoutFluid(String fluidId){
        return FLUID_MAP.get(fluidId);
    }
}
