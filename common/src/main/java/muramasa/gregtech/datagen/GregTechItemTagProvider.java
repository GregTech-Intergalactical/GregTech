package muramasa.gregtech.datagen;

import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemTagProvider;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.data.GregTechTags;

import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.GregTechTags.*;

public class GregTechItemTagProvider  extends AntimatterItemTagProvider {
    public GregTechItemTagProvider(String providerDomain, String providerName, boolean replace, AntimatterBlockTagProvider p) {
        super(providerDomain, providerName, replace, p);
    }

    @Override
    protected void processTags(String domain) {
        super.processTags(domain);
        this.tag(CIRCUITS_BASIC).add(CircuitBasicIntegrated, CircuitBasicElectronic);
        this.tag(CIRCUITS_GOOD).add(CircuitGoodElectronic, CircuitGoodIntegrated);
        this.tag(CIRCUITS_ADVANCED).add(CircuitAdv);
        //this.tag(GregTechTags.CIRCUITS_EXTREME).add(GregTechData.CircuitDataStorage);
        this.tag(CIRCUITS_ELITE).add(CircuitDataControl, NanoProcessor);
        this.tag(GregTechTags.CIRCUITS_MASTER).add(CircuitEnergyFlow, QuantumProcessor);
        this.tag(CIRCUITS_DATA_ORB).add(DataOrb);
        this.tag(BATTERIES_RE).add(BatteryRE);
        this.tag(BATTERIES_SMALL).add(BatterySmallSodium, BatterySmallCadmium, BatterySmallLithium);
        this.tag(BATTERIES_MEDIUM).add(BatteryMediumSodium, BatteryMediumCadmium, BatteryMediumLithium);
        this.tag(BATTERIES_LARGE).add(BatteryLargeSodium, BatteryLargeCadmium, BatteryLargeLithium, EnergyCrystal);
        this.tag(POWER_UNIT_LV).add(PowerUnitLV);
        this.tag(POWER_UNIT_MV).add(PowerUnitMV);
        this.tag(POWER_UNIT_HV).add(PowerUnitHV);
        this.tag(POWER_UNIT_SMALL).add(SmallPowerUnit);
        this.tag(PLATES_IRON_ALUMINIUM).addTag(TagUtils.getForgelikeItemTag("plates/iron")).addTag(TagUtils.getForgelikeItemTag("plates/wrought_iron")).addTag(TagUtils.getForgelikeItemTag("plates/aluminium"));
        this.tag(DUST_LAPIS_LAZURITE).addTag(TagUtils.getForgelikeItemTag("dusts/lapis")).addTag(TagUtils.getForgelikeItemTag("dusts/lazurite"));
    }
}
