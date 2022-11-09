package muramasa.gregtech.datagen;

import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemTagProvider;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.data.GregTechTags;

public class GregTechItemTagProvider  extends AntimatterItemTagProvider {
    public GregTechItemTagProvider(String providerDomain, String providerName, boolean replace, AntimatterBlockTagProvider p) {
        super(providerDomain, providerName, replace, p);
    }

    @Override
    protected void processTags(String domain) {
        super.processTags(domain);
        this.tag(GregTechTags.PLATES_IRON_ALUMINIUM).addTag(TagUtils.getForgelikeItemTag("plates/iron")).addTag(TagUtils.getForgelikeItemTag("plates/wrought_iron")).addTag(TagUtils.getForgelikeItemTag("plates/aluminium"));
    }
}
