package muramasa.gregtech.data;

import muramasa.antimatter.util.TagUtils;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class GregTechTags {
    public static TagKey<Item> PLATES_IRON_ALUMINIUM = getTag("plates/ironaluminium");

    public static TagKey<Item> getTag(String id){
        return TagUtils.getForgelikeItemTag(id);
    }
}
