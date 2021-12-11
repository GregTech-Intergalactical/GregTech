package muramasa.gti.data;


import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.block.BlockStone;
import muramasa.antimatter.datagen.providers.AntimatterLanguageProvider;
import muramasa.antimatter.item.*;
import muramasa.antimatter.machine.MachineFlag;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.*;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialItem;
import muramasa.antimatter.material.MaterialType;
import muramasa.antimatter.ore.BlockOreStone;
import muramasa.antimatter.pipe.BlockPipe;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.gti.Ref;
import muramasa.gti.block.BlockCasing;
import muramasa.gti.block.BlockCoil;
import muramasa.gti.items.ItemIntCircuit;
import net.minecraft.data.DataGenerator;

import java.nio.charset.StandardCharsets;
import java.util.*;

import java.io.*;
import com.google.gson.*;
import java.util.stream.Collectors;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.util.Utils.*;

public class GregTechLocalizations {

    public static class en_US extends AntimatterLanguageProvider {

        public en_US(DataGenerator gen) {
            super(Ref.ID, Ref.NAME + " en_us Localization", "en_us", gen);
        }

        @Override
        protected void addTranslations() {
            super.addTranslations();
            add(Ref.ID + ".advancements.greg", "GregTech Intergalactical");
            add(Ref.ID + ".advancements.greg.desc", "Getting familiar with your surroundings");
            add(Ref.ID + ".tooltip.occurrence", "Indicates occurrence of ");
        }

        @Override
        protected void processTranslations(String domain, String locale) {
            super.processTranslations(domain, locale);
            AntimatterAPI.all(BlockCasing.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpaced(i.getId())));
            AntimatterAPI.all(BlockCoil.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpaced(i.getId())));
            AntimatterAPI.all(ItemIntCircuit.class, domain).forEach(i -> add(i, "Integrated Circuit (" + i.circuitId + ")"));
            add(GregTechData.RUBBER_LEAVES, "Rubber Leaves");
            add(GregTechData.RUBBER_LOG, "Rubber Log");
            add(GregTechData.RUBBER_SAPLING, "Rubber Sapling");
        }
    }

    public static class ru_RU extends AntimatterLanguageProvider {

        public ru_RU(DataGenerator gen) {
            super(Ref.ID, Ref.NAME + " ru_ru Localization", "ru_ru", gen);
        }

        private String capitalize(String word)
        {
            return word.substring(0, 1).toUpperCase() + word.substring(1);
        }

        @Override
        protected void addTranslations() {
            super.addTranslations();

            InputStream stream = getClass().getClassLoader().getResourceAsStream("lang_generation/ru_ru.json");

            assert stream != null;
            String basicWords = new BufferedReader(
                    new InputStreamReader(stream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            Gson gson = new Gson();

            final Map<String, String> translations = gson.fromJson(basicWords, Map.class);

            add(Ref.ID + ".advancements.greg", "GregTech Intergalactical");
            add(Ref.ID + ".advancements.greg.desc", translations.get(".advancements.greg.desc"));
            add(Ref.ID + ".tooltip.occurrence", translations.get(".tooltip.occurrence"));
            add(Ref.ID + ".fusion_1", translations.get("fusion_1"));
            add(Ref.ID + ".fusion_2", translations.get("fusion_2"));
            add(Ref.ID + ".fusion_3", translations.get("fusion_3"));
        }

        @Override
        protected void processTranslations(String domain, String locale) {
            super.processTranslations(domain, locale);
            if (!locale.startsWith("ru")) return;

            InputStream stream = getClass().getClassLoader().getResourceAsStream("lang_generation/ru_ru.json");

            assert stream != null;
            String basicWords = new BufferedReader(
                    new InputStreamReader(stream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            Gson gson = new Gson();

            final Map<String, String> translations = gson.fromJson(basicWords, Map.class);

            AntimatterAPI.all(ItemIntCircuit.class, domain).forEach(i -> add(i, translations.get("ccircuit") + " (" + i.circuitId + ")"));

            add(GregTechData.RUBBER_LEAVES, translations.get("rubber_leaves"));
            add(GregTechData.RUBBER_LOG, translations.get("rubber_log"));
            add(GregTechData.RUBBER_SAPLING, translations.get("rubber_sapling"));

            add(muramasa.antimatter.Ref.TAB_BLOCKS, translations.get("tab_blocks"));
            add(muramasa.antimatter.Ref.TAB_ITEMS, translations.get("tab_items"));
            add(muramasa.antimatter.Ref.TAB_MACHINES, translations.get("tab_machines"));
            add(muramasa.antimatter.Ref.TAB_MATERIALS, translations.get("tab_materials"));
            add(muramasa.antimatter.Ref.TAB_TOOLS, translations.get("tab_tools"));

            add("machine.voltage.in", translations.get("machine.voltage.in"));
            add("machine.power.capacity", translations.get("machine.power.capacity"));
            add("generic.amp", translations.get("generic.amp"));
            add("generic.tier", translations.get("generic.tier"));
            add("generic.voltage", translations.get("machine.voltage"));
            add("generic.loss", translations.get("generic.loss"));
            add("message.discharge.on", translations.get("message.discharge.on"));
            add("message.discharge.off", translations.get("message.discharge.off"));
            add("item.charge", translations.get("item.charge"));
            add("item.reusable", translations.get("item.reusable"));

            AntimatterAPI.all(Machine.class, domain).forEach(i -> {
                Collection<Tier> tiers = i.getTiers();
                if (i.has(MachineFlag.BASIC)) {
                    tiers.forEach(t ->  {
                        if (translations.containsKey(i.getId())){
                            if (!t.getId().equals("steel") && !t.getId().equals("bronze"))
                                add("machine." + i.getId() + "." + t.getId(), translations.get(i.getId()) + " " + t.getId().toUpperCase());
                            else
                            {
                                if (translations.get(i.getId()).contains(translations.get("oi")) && t.getId().contains("steel"))
                                    add("machine." + i.getId() + "." + t.getId(), translations.get("steel_machine") + translations.get("oi") + " " + translations.get(i.getId()));
                                else if
                                (translations.get(i.getId()).contains(translations.get("ii")) && t.getId().contains("bronze"))
                                    add("machine." + i.getId() + "." + t.getId(), translations.get("bronze_machine") + translations.get("ii") + " " + translations.get(i.getId()));
                                else if (translations.get(i.getId()).contains(translations.get("aya")))
                                    add("machine." + i.getId() + "." + t.getId(), translations.get(t.getId() + "_machine") + translations.get("aya") + " " + translations.get(i.getId()));
                            }
                        }
                        else
                            add("machine." + i.getId() + "." + t.getId(), lowerUnderscoreToUpperSpacedRotated(i.getId() + "_" + t.getId()));
                    });
                } else {
                    if (translations.containsKey(i.getId()))
                        add("machine." + i.getId(), translations.get(i.getId()));
                    else
                        add("machine." + i.getId(), lowerUnderscoreToUpperSpacedRotated(i.getId()));
                }
            });

            AntimatterAPI.all(BlockCasing.class, domain).forEach(i -> {
                if (translations.containsKey(i.getId()))
                    add(i, translations.get(i.getId()));
            });

            AntimatterAPI.all(BlockCoil.class, domain).forEach(i -> {
                if (translations.containsKey(i.getId()))
                    add(i, translations.get(i.getId()));
            });

            AntimatterAPI.all(ItemBasic.class, domain).forEach(i -> {
                String[] spl = i.getId().split("_");
                String key = String.join("_", Arrays.copyOfRange(spl, 0, spl.length - 1));

                if (translations.containsKey(i.getId())){
                    add(i, translations.get(i.getId()));
                }
                else if (translations.containsKey(key)){
                    add(i, translations.get(key) + " " + translations.get(spl[spl.length - 1]));
                }
                else {
                    String translation = "";
                    for (String word: spl) {
                        translation += translations.getOrDefault(word, word) + ' ';
                    }
                    add(i, capitalize(translation));
                }
            });


            AntimatterAPI.all(ItemFluidCell.class, domain).forEach(i -> {
                if (translations.containsKey(i.getId())){
                    add(i, translations.get(i.getId()));
                } else add(i, lowerUnderscoreToUpperSpacedRotated(i.getId()));
            });

            AntimatterAPI.all(ItemCover.class, domain).forEach(i -> {
                String[] spl = i.getId().split("_");
                if (translations.containsKey(i.getId())){
                    add(i, translations.get(i.getId()));
                }
                else if (spl.length > 2 && (spl[0] + spl[1]).equals("covertype")){
                    String key = String.join("_", Arrays.copyOfRange(spl, 2, spl.length));
                    if (translations.containsKey(key))
                        add(i, translations.get(key) + translations.get("covertype"));
                    else
                        add(i, lowerUnderscoreToUpperSpaced(i.getId()));
                }
                else {
                    String translation = "";
                    for (String word: spl) {
                        translation += translations.getOrDefault(word, word) + ' ';
                    }
                    add(i, capitalize(translation));
                }
            });

            AntimatterAPI.all(ItemBattery.class, domain).forEach(i -> {
                String[] spl = i.getId().split("_");
                List<String> bTiers = Arrays.asList("small", "medium", "large");

                if (translations.containsKey(i.getId())) {
                    add(i, translations.get(i.getId()));
                }
                else if (spl.length > 2 && spl[0].equals("battery") &&(bTiers.contains(spl[1]))){
                    if (translations.containsKey(spl[2]))
                        add(i, capitalize(translations.get(spl[0] + "_" + spl[1]).split(" ")[0] + " " +
                                translations.get(spl[2]) + translations.get("aya") + " " +
                                translations.get(spl[0] + "_" + spl[1]).split(" ")[1]));
                    else
                        add(i, lowerUnderscoreToUpperSpaced(i.getId()));
                }
            });

            AntimatterAPI.all(BlockStone.class).forEach(i -> {
                if (translations.containsKey(i.getId()))
                    add(i, translations.get(i.getId()));
                else
                    add(i, getLocalizedType(i).replaceAll("Stone ", ""));
            });

            AntimatterAPI.all(MaterialItem.class).forEach(item -> {
                MaterialType<?> type = item.getType();
                if (translations.containsKey(item.getId())) {
                    add(item, translations.get(item.getId()));
                }
                else if (type == ROCK) {
                    if (translations.containsKey(getLocalizedType(item.getMaterial())))
                        add(item, String.join("", translations.get(getLocalizedType(item.getMaterial()) + "_1"), translations.get("bearing_rock")));
                    else
                        add(item, String.join("", getLocalizedType(item.getMaterial()), " Bearing Rock"));
                }
                else if (type == CRUSHED){
                    if (translations.containsKey(getLocalizedType(item.getMaterial())))
                        add(item, String.join("", translations.get("Crushed"), translations.get(getLocalizedType(item.getMaterial())), translations.get("aya"), translations.get("Ore")));
                    else
                        add(item, String.join("", "Crushed ", getLocalizedType(item.getMaterial()), " Ore"));
                }
                else if (type == CRUSHED_PURIFIED) {
                    if (translations.containsKey(getLocalizedType(item.getMaterial())))
                        add(item, String.join("", translations.get("Purified_Crushed"), translations.get(getLocalizedType(item.getMaterial())), translations.get("aya"), translations.get("Ore")));
                    else
                        add(item, String.join("", "Purified Crushed ", getLocalizedType(item.getMaterial()), " Ore"));
                }
                else if (type == CRUSHED_CENTRIFUGED) {
                    if (translations.containsKey(getLocalizedType(item.getMaterial())))
                        add(item, String.join("", translations.get("Centrifuged_Crushed"), translations.get(getLocalizedType(item.getMaterial())), translations.get("aya"), translations.get("Ore")));
                    else
                        add(item, String.join("", "Centrifuged Crushed ", getLocalizedType(item.getMaterial()), " Ore"));
                }
                else {
                    String[] split = getLocalizedMaterialType(type);
                    if (split.length > 1) {
                        if (type.isSplitName())
                            add(item, String.join("", split[0], " ", getLocalizedType(item.getMaterial()), " ", split[1]));
                        else
                            add(item, String.join("", getLocalizedType(item.getMaterial()), " ", split[1], " ", split[0]));
                    } else add(item, String.join("", getLocalizedType(item.getMaterial()), " ", split[0]));
                }
            });


//            try{
//                BufferedWriter writer = new BufferedWriter(new FileWriter("c:/users/mihag/desktop/GTI/aboba.txt"));
//                AntimatterAPI.all(BlockStone.class).forEach(i -> {
//                    try{
//                    writer.write(i.getId() + " " + getLocalizedType(i) + '\n');
//                    System.out.println(i.getId() + " " + getLocalizedType(i));
//
//                    }
//                    catch (Exception e){
//                        System.out.println(e.toString());
//                    }
//                });
//                writer.close();
//            }
//            catch (Exception e){
//                System.out.println(e.toString());
//            }
        }
    }
}
