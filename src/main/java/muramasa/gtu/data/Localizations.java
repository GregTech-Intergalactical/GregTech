package muramasa.gtu.data;


import muramasa.antimatter.datagen.providers.AntimatterLanguageProvider;
import muramasa.gtu.Ref;
import net.minecraft.data.DataGenerator;

public class Localizations {

    public static final class en_US extends AntimatterLanguageProvider {

        public en_US(DataGenerator gen) {
            super(Ref.ID, Ref.NAME + " en_us Localization", "en_us", gen);
        }

        @Override
        protected void addTranslations() {
            processTranslations(Ref.ID, "en_us");
            add("gtu.advancements.greg", "GregTech Intergalactical");
            add("gtu.advancements.greg.desc", "Getting familiar with your surroundings");
            add("gtu.tooltip.occurrence", "Indicates occurrence of: ");
        }

    }

}
