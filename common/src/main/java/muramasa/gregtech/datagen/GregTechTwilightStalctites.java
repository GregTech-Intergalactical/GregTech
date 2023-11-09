package muramasa.gregtech.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.AntimatterDynamics;
import muramasa.antimatter.ore.BlockOre;
import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.GTIRef;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.github.gregtechintergalactical.gtcore.data.GTCoreMaterials.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.ORE;
import static muramasa.antimatter.data.AntimatterStoneTypes.STONE;

public class GregTechTwilightStalctites {
    private static Map<Integer, List<ResourceLocation>> hollowHillEntries = new Object2ObjectOpenHashMap<>();

    public static void init(){
        if (!AntimatterAPI.isModLoaded("twilightforest")) return;
        addStalctite("ruby", 2, 6, ORE.get().get(Ruby, STONE).asBlock(), 0.6f, 20);
        addStalctite("sapphire", 2, 6, ORE.get().get(Sapphire, STONE).asBlock(), 0.6f, 20);
        addStalctite("green_sapphire", 2, 6, ORE.get().get(GreenSapphire, STONE).asBlock(), 0.6f, 20);
        addStalctite("amber", 2, 6, ORE.get().get(Amber, STONE).asBlock(), 0.6f, 20);
        addStalctite("amethyst", 2, 6, ORE.get().get(Amethyst, STONE).asBlock(), 0.6f, 20);
        addStalctite("jade", 2, 6, ORE.get().get(Jade, STONE).asBlock(), 0.6f, 20);
        addStalctite("blue_topaz", 2, 6, ORE.get().get(BlueTopaz, STONE).asBlock(), 0.6f, 20);
        addStalctite("topaz", 2, 6, ORE.get().get(Topaz, STONE).asBlock(), 0.6f, 20);
        addStalctite("olivine", 2, 6, ORE.get().get(Olivine, STONE).asBlock(), 0.6f, 20);
        addStalctite("opal", 2, 6, ORE.get().get(Opal, STONE).asBlock(), 0.6f, 20);
        addStalctite("tanzanite", 2, 6, ORE.get().get(Tanzanite, STONE).asBlock(), 0.6f, 20);
        addStalctite("red_garnet", 2, 6, ORE.get().get(RedGarnet, STONE).asBlock(), 0.6f, 20);
        addStalctite("yellow_garnet", 2, 6, ORE.get().get(YellowGarnet, STONE).asBlock(), 0.6f, 20);
        addStalctite("iridium", 3, 4, ORE.get().get(Iridium, STONE).asBlock(), 0.5f, 30);
        JsonObject medium = new JsonObject();
        medium.addProperty("replace", false);
        JsonArray array = new JsonArray();
        hollowHillEntries.get(2).forEach(r -> array.add(r.toString()));
        medium.add("stalactites", array);
        AntimatterDynamics.RUNTIME_DATA_PACK.addData(new ResourceLocation("twilightforest", "stalactites/medium_hollow_hill.json"), medium.toString().getBytes());
        JsonObject large = new JsonObject();
        large.addProperty("replace", false);
        JsonArray array2 = new JsonArray();
        hollowHillEntries.get(3).forEach(r -> array2.add(r.toString()));
        large.add("stalactites", array2);
        AntimatterDynamics.RUNTIME_DATA_PACK.addData(new ResourceLocation("twilightforest", "stalactites/large_hollow_hill.json"), large.toString().getBytes());
        hollowHillEntries = null;
    }

    private static void addStalctite(String name, int hillSize, int maxLength, Block ore, float sizeVariation, int weight){
        JsonObject object = new JsonObject();
        object.addProperty("max_length", maxLength);
        object.addProperty("ore", AntimatterPlatformUtils.getIdFromBlock(ore).toString());
        object.addProperty("size_variation", sizeVariation);
        object.addProperty("weight", weight);
        ResourceLocation id = new ResourceLocation(GTIRef.ID, name);
        hollowHillEntries.computeIfAbsent(hillSize, i -> new ArrayList<>()).add(id);
        AntimatterDynamics.RUNTIME_DATA_PACK.addData(AntimatterDynamics.fix(id, "stalactite/entries", ".json"), object.toString().getBytes());
    }
}
