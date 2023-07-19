package muramasa.gregtech.datagen;


import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterLanguageProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemBattery;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.block.BlockCasing;
import muramasa.gregtech.block.BlockCoil;
import muramasa.gregtech.block.BlockFakeCasing;
import muramasa.gregtech.items.ItemIntCircuit;

import static muramasa.antimatter.util.Utils.*;

public class GregTechLocalizations {

    public static class en_US extends AntimatterLanguageProvider {

        public en_US() {
            super(GTIRef.ID, GTIRef.NAME + " en_us Localization", "en_us");
        }

        @Override
        protected void addTranslations() {
            super.addTranslations();
            add(GTIRef.ID + ".advancements.greg", "GregTech Intergalactical");
            add(GTIRef.ID + ".advancements.greg.desc", "Getting familiar with your surroundings");
            add(GTIRef.ID + ".rei.tooltip.ore.byproducts", "Ore Byproducts List");
            add(GTIRef.ID + ".rei.tooltip.material_tree", "Material Tree");
            add("machine.transformer.voltage_info", "%s -> %s (Use Soft Hammer to invert)");
        }

        @Override
        protected void english(String domain, String locale) {
            super.english(domain, locale);
            AntimatterAPI.all(BlockCasing.class, domain).forEach(i -> {
                if (i.getId().contains("fusion")){
                    add(i, "Fusion Casing MK " + i.getId().replace("fusion_", ""));
                    return;
                }
                if (i.getId().contains("turbine")){
                    add(i, "Turbine Casing MK " + i.getId().replace("casing_turbine_", ""));
                    return;
                }
                if (i.getId().contains("firebox") || i.getId().contains("gearbox") || i.getId().contains("pipe")){
                    add(i, lowerUnderscoreToUpperSpacedReversed(i.getId()));
                    return;
                }
                if (i.getId().startsWith("casing_") || i.getId().startsWith("hull_")){
                    add(i, lowerUnderscoreToUpperSpacedRotated(i.getId()));
                    return;
                }
                add(i, lowerUnderscoreToUpperSpaced(i.getId()));
            });

            AntimatterAPI.all(BlockFakeCasing.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpaced(i.getId())));
            AntimatterAPI.all(BlockCoil.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpacedRotated(i.getId())));
            AntimatterAPI.all(ItemIntCircuit.class, domain).forEach(i -> add(i, "Integrated Circuit (" + i.circuitId + ")"));
            AntimatterAPI.all(ItemBasic.class, domain).stream().filter(i -> i.getId().startsWith("circuit")).forEach(i -> override(i.getDescriptionId(), lowerUnderscoreToUpperSpacedRotated(i.getId())));
            AntimatterAPI.all(ItemBattery.class, domain).stream().filter(i -> i.getId().startsWith("battery")).forEach(i -> override(i.getDescriptionId(), lowerUnderscoreToUpperSpacedRotated(i.getId())));
        }
    }

}
