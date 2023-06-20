package muramasa.gregtech.data;

import muramasa.antimatter.util.TagUtils;
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
    public static TagKey<Item> DUST_LAPIS_LAZURITE = getTag("dusts/lapislazurite");

    public static TagKey<Item> getTag(String id){
        return TagUtils.getForgelikeItemTag(id);
    }
}
