package muramasa.gregtech.data;


import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterLanguageProvider;
import muramasa.gregtech.Ref;
import muramasa.gregtech.block.BlockCasing;
import muramasa.gregtech.block.BlockCoil;
import muramasa.gregtech.items.ItemIntCircuit;
import net.minecraft.data.DataGenerator;

import static muramasa.antimatter.util.Utils.lowerUnderscoreToUpperSpaced;

public class GregTechLocalizations {

    public static class en_US extends AntimatterLanguageProvider {

        public en_US() {
            super(Ref.ID, Ref.NAME + " en_us Localization", "en_us");
        }

        @Override
        protected void addTranslations() {
            super.addTranslations();
            add(Ref.ID + ".advancements.greg", "GregTech Intergalactical");
            add(Ref.ID + ".advancements.greg.desc", "Getting familiar with your surroundings");
            add(Ref.ID + ".tooltip.occurrence", "Indicates occurrence of ");
            add(Ref.ID + ".rei.tooltip.ore.byproducts", "Ore Byproducts List");
        }

        @Override
        protected void english(String domain, String locale) {
            super.english(domain, locale);
            AntimatterAPI.all(BlockCasing.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpaced(i.getId())));
            AntimatterAPI.all(BlockCoil.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpaced(i.getId())));
            AntimatterAPI.all(ItemIntCircuit.class, domain).forEach(i -> add(i, "Integrated Circuit (" + i.circuitId + ")"));
        }
    }

}
