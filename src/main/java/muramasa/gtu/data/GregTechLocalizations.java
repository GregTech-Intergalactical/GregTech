package muramasa.gtu.data;

import muramasa.antimatter.datagen.IAntimatterLanguageProvider;

public final class GregTechLocalizations implements IAntimatterLanguageProvider {

    @Override
    public void processTranslations() {
        add("gtu.advancements.greg", "GregTech Intergalactical");
        add("gtu.advancements.greg.desc", "Getting familiar with your surroundings");
        add("gtu.tooltip.occurrence", "Indicates occurrence of: ");
    }
}
