package muramasa.gti.data;


import muramasa.antimatter.datagen.providers.AntimatterLanguageProvider;
import muramasa.gti.Ref;
import net.minecraft.data.DataGenerator;

public class GregTechLocalizations {

    public static class en_US extends AntimatterLanguageProvider {

        public en_US(DataGenerator gen) {
            super(Ref.ID, Ref.NAME + " en_us Localization", "en_us", gen);
        }

        @Override
        protected void addTranslations() {
            super.processTranslations(Ref.ID, "en_us");
            add(Ref.ID + ".advancements.greg", "GregTech Intergalactical");
            add(Ref.ID + ".advancements.greg.desc", "Getting familiar with your surroundings");
            add(Ref.ID + ".tooltip.occurrence", "Indicates occurrence of ");
        }

    }

}
