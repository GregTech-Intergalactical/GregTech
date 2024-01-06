package muramasa.gregtech.data;

import muramasa.antimatter.registration.Side;
import muramasa.gregtech.GTIRef;
import net.minecraft.resources.ResourceLocation;

import java.util.HashSet;
import java.util.Set;

public class GregTechData {


    private static final String CAPE_PATH = "textures/capes/";
    public static final ResourceLocation[] CAPE_LOCATIONS = new ResourceLocation[] {new ResourceLocation(GTIRef.ID,  CAPE_PATH + "braintech.png"), new ResourceLocation(GTIRef.ID, CAPE_PATH + "silver.png"), new ResourceLocation(GTIRef.ID, CAPE_PATH + "mrbrain.png"), new ResourceLocation(GTIRef.ID, CAPE_PATH + "dev.png"), new ResourceLocation(GTIRef.ID, CAPE_PATH + "gold.png"), new ResourceLocation(GTIRef.ID, CAPE_PATH + "crazy.png"), new ResourceLocation(GTIRef.ID, CAPE_PATH + "fake.png")};

    public static final Set<String> SupporterListSilver = new HashSet<>(), SupporterListGold = new HashSet<>();

    public static void init(Side side) {
        if (side == Side.CLIENT)
            RecipeMaps.clientMaps();
        /*AntimatterAPI.all(MaterialType.class, t -> {
            if (t instanceof MaterialTypeFluid) return;
            if (t.getClass() == MaterialType.class) return;
            //TODO: add better check
            if (t == AntimatterMaterialTypes.ORE_STONE) return;
            CoverFactory.builder((a,b,c,d) -> new CoverTypeFilter(a,b,c,d,t)).addTextures(Material.NULL.getSet().getTextures(t)).item((a, b) -> {
            return new ItemCover(a.getDomain(), a.getId()).tip("Filters for " + t.getId()).texture(Material.NULL.getSet().getTextures(t));}).build(GTIRef.ID, "cover_type_" + t.getId());
        });*/
    }

    //public static BlockBasic ANTHRACITE_COAL = new BlockBasic(GTIRef.ID, "anthracite_coal", new Texture(GTIRef.ID, "block/basic/anthracite_coal");
    //public static BlockBasic ANTHRACITE_COAL = new BlockBasic(GTIRef.ID, "anthracite_coal", new Texture(GTIRef.ID, "block/basic/anthracite_coal");


}
