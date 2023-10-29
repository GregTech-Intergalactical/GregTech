package muramasa.gregtech.datagen;


import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterLanguageProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemBattery;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.block.BlockCasing;
import muramasa.gregtech.block.BlockCoil;
import muramasa.gregtech.block.BlockColoredWall;
import muramasa.gregtech.block.BlockFakeCasing;
import muramasa.gregtech.data.GregTechData;
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
            add(GTIRef.ID + ".rotor.tooltip.efficiency", "Turbine Efficiency: %s");
            add(GTIRef.ID + ".rotor.tooltip.steam_flow", "Optimal Steam flow: %sL/sec");
            add(GTIRef.ID + ".rotor.tooltip.gas_flow", "Optimal Gas flow(EU burnvalue per tick): %sEU/t");
            add("tooltip.gti.redstone_mode.2", "Ignore Redstone");
            add("tooltip.gti.redstone_mode.1", "Invert Conditional");
            add("tooltip.gti.redstone_mode.0", "Conditional");
            add("tooltip.gti.redstone_mode.normal", "Normal");
            add("tooltip.gti.redstone_mode.inverted", "Inverted");
            add("tooltip.gti.export_mode.0", "Export");
            add("tooltip.gti.export_mode.1", "Import");
            add("tooltip.gti.export_mode.2", "Export allow Import");
            add("tooltip.gti.export_mode.3", "Import allow Export");
            add("tooltip.gti.whitelist", "Whitelist");
            add("tooltip.gti.blacklist", "Blacklist");
            add("tooltip.gti.nbt.on", "Don't ignore nbt");
            add("tooltip.gti.nbt.off", "Ignore nbt");
        }

        @Override
        protected void english(String domain, String locale) {
            super.english(domain, locale);
            AntimatterAPI.all(BlockCasing.class, domain).forEach(i -> {
                if (i.getId().startsWith("casing_") || i.getId().startsWith("hull_")){
                    add(i, lowerUnderscoreToUpperSpacedRotated(i.getId()));
                    return;
                }
                if (i.getId().contains("long_distance_cable")){
                    String tier = i.getId().replace("long_distance_cable_", "");
                    add(i, "Long Distance Cable (" + tier.toUpperCase() + ")");
                }
                add(i, lowerUnderscoreToUpperSpaced(i.getId()));
            });

            add(GregTechData.MINING_PIPE, "Mining Pipe");
            add(GregTechData.MINING_PIPE_THIN, "Mining Pipe");
            add(GregTechData.BRITTLE_CHARCOAL, "Brittle Charcoal");
            add(GregTechData.SOLID_SUPER_FUEL, "Solid Super Fuel");
            AntimatterAPI.all(BlockFakeCasing.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpaced(i.getId())));
            AntimatterAPI.all(BlockColoredWall.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpaced(i.getId())));
            AntimatterAPI.all(BlockCoil.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpaced(i.getId())));
            AntimatterAPI.all(ItemIntCircuit.class, domain).forEach(i -> override(i.getDescriptionId(), "Integrated Circuit (" + i.circuitId + ")"));
//            AntimatterAPI.all(ItemPowerUnit.class, domain).stream().filter(i -> i.getId().startsWith("power_unit") || i.getId().startsWith("small_power_unit")).forEach(i -> override(i.getDescriptionId(), lowerUnderscoreToUpperSpaced(i.getId())));
            override("machine.large_turbine.hv", "Large Steam Turbine");
            override("machine.large_turbine.ev", "Large Gas Turbine");
            override("machine.large_turbine.iv", "Large HP Steam Turbine");
        }
    }

}
