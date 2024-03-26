package muramasa.gregtech.data;

import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.GTIRef;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class GregTechTags {
    public static final TagKey<Fluid> STEAM =  TagKey.create(Registry.FLUID_REGISTRY, new ResourceLocation((AntimatterPlatformUtils.isForge() ? "forge" : "c"), "steam"));
    public static final TagKey<Item> GRIND_HEADS = getTag("grind_heads");
    public static final TagKey<Item> GEM_SAPPHIRES = getTag("gem/sapphires");
    public static final TagKey<Block> ASPHALT = TagUtils.getBlockTag(new ResourceLocation(GTIRef.ID, "asphalt"));
    public static TagKey<Item> getTag(String id){
        return TagUtils.getForgelikeItemTag(id);
    }
}
