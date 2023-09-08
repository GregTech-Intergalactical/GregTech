package muramasa.gregtech.integration.forge.tfc.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterLanguageProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.gregtech.integration.forge.tfc.ore.GTTFCOreBlock;
import muramasa.gregtech.integration.forge.tfc.ore.GTTFCOreItem;

import static muramasa.antimatter.util.Utils.lowerUnderscoreToUpperSpaced;

public class TFCLangProvider extends AntimatterLanguageProvider {
    public TFCLangProvider(String providerDomain, String providerName, String locale) {
        super(providerDomain, providerName, locale);
    }

    @Override
    protected void english(String domain, String locale) {
        super.english(domain, locale);
        AntimatterAPI.all(GTTFCOreItem.class, domain).forEach(i -> {
            add(i, lowerUnderscoreToUpperSpaced(i.getId()));
        });
        AntimatterAPI.all(GTTFCOreBlock.class, domain).forEach(i -> {
            add(i, lowerUnderscoreToUpperSpaced(i.getId()));
        });
    }
}
