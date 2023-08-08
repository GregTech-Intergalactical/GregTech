package muramasa.gregtech.data;

import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.GTIRef;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class GregTechTags {
    public static TagKey<Item> PLATES_IRON_ALUMINIUM = getTag("plates/ironaluminium");
    public static TagKey<Item> CIRCUITS_BASIC = getTag("circuits/basic");
    public static TagKey<Item> CIRCUITS_GOOD = getTag("circuits/good");
    public static TagKey<Item> CIRCUITS_ADVANCED = getTag("circuits/advanced");
    public static TagKey<Item> CIRCUITS_EXTREME = getTag("circuits/extreme");
    public static TagKey<Item> CIRCUITS_ELITE = getTag("circuits/elite");
    public static TagKey<Item> CIRCUITS_MASTER = getTag("circuits/master");
    public static TagKey<Item> CIRCUITS_DATA_ORB = getTag("circuits/data_orb");
    public static TagKey<Item> DUST_LAPIS_LAZURITE = getTag("dusts/lapislazurite");
    public static TagKey<Item> DUST_COALS = getTag("dusts/coals");
    public static TagKey<Item> BATTERIES_RE = getTag("batteries/re");
    public static TagKey<Item> BATTERIES_SMALL = getTag("batteries/small");
    public static TagKey<Item> BATTERIES_MEDIUM = getTag("batteries/medium");
    public static TagKey<Item> BATTERIES_LARGE = getTag("batteries/large");
    public static TagKey<Item> POWER_UNIT_LV = getTag("power_units/lv");
    public static TagKey<Item> POWER_UNIT_MV = getTag("power_units/mv");
    public static TagKey<Item> POWER_UNIT_HV = getTag("power_units/hv");
    public static TagKey<Item> POWER_UNIT_SMALL = getTag("power_units/small");
    public static TagKey<Item> RESISTORS = TagUtils.getItemTag(new ResourceLocation(GTIRef.ID, "resistors"));

    public static TagKey<Item> getTag(String id){
        return TagUtils.getForgelikeItemTag(id);
    }
}
