package muramasa.gregtech.integration.forge.tfc.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemTagProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.integration.forge.tfc.TFCRegistrar;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import static muramasa.antimatter.data.AntimatterDefaultTools.SAW;

public class TFCItemTagProvider extends AntimatterItemTagProvider {
    public TFCItemTagProvider(String providerDomain, String providerName, boolean replace, AntimatterBlockTagProvider p) {
        super(providerDomain, providerName, replace, p);
    }

    @Override
    protected void processTags(String domain) {
        super.processTags(domain);
        this.tag(TagUtils.getItemTag(new ResourceLocation("tfc", "saws"))).addTag(SAW.getTag());
        for (Material material : TFCRegistrar.array) {
            this.tag(TagUtils.getItemTag(new ResourceLocation("tfc", "ore_pieces"))).add(GregTech.get(Item.class, "poor_" + material.getId()), GregTech.get(Item.class, "normal_" + material.getId()), GregTech.get(Item.class, "rich_" + material.getId()));
        }
    }
}
