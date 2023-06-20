package muramasa.gregtech.datagen;

import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemTagProvider;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.GregTechTags;

import static muramasa.gregtech.data.GregTechData.*;

public class GregTechItemTagProvider  extends AntimatterItemTagProvider {
    public GregTechItemTagProvider(String providerDomain, String providerName, boolean replace, AntimatterBlockTagProvider p) {
        super(providerDomain, providerName, replace, p);
    }

    @Override
    protected void processTags(String domain) {
        super.processTags(domain);
        this.tag(GregTechTags.CIRCUITS_BASIC).add(CircuitBasic, CircuitBasicElectronic);
        this.tag(GregTechTags.CIRCUITS_GOOD).add(CircuitGood);
        this.tag(GregTechTags.CIRCUITS_ADVANCED).add(CircuitAdv);
        //this.tag(GregTechTags.CIRCUITS_EXTREME).add(GregTechData.CircuitDataStorage);
        this.tag(GregTechTags.CIRCUITS_ELITE).add(CircuitDataControl, CircuitNanoProcessor);
        this.tag(GregTechTags.CIRCUITS_MASTER).add(CircuitEnergyFlow, CircuitQuantumProcessor);
        this.tag(GregTechTags.PLATES_IRON_ALUMINIUM).addTag(TagUtils.getForgelikeItemTag("plates/iron")).addTag(TagUtils.getForgelikeItemTag("plates/wrought_iron")).addTag(TagUtils.getForgelikeItemTag("plates/aluminium"));
        this.tag(GregTechTags.DUST_LAPIS_LAZURITE).addTag(TagUtils.getForgelikeItemTag("dusts/lapis")).addTag(TagUtils.getForgelikeItemTag("dusts/lazurite"));
    }
}
