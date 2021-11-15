package muramasa.gti.data;


import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterLanguageProvider;
import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.machine.MachineFlag;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.*;
import muramasa.gti.Ref;
import muramasa.gti.block.BlockCasing;
import muramasa.gti.block.BlockCoil;
import muramasa.gti.items.ItemIntCircuit;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemGroup;

import java.nio.charset.StandardCharsets;
import java.util.*;
import static muramasa.antimatter.util.Utils.lowerUnderscoreToUpperSpaced;
import static muramasa.antimatter.util.Utils.lowerUnderscoreToUpperSpacedRotated;

import java.io.*;
import com.google.gson.*;
import java.util.stream.Collectors;

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

        @Override
        protected void addTranslations() {
            super.addTranslations();
            add(Ref.ID + ".advancements.greg", "GregTech Intergalactical");
            add(Ref.ID + ".advancements.greg.desc", "Познакомиться с окружением");
            add(Ref.ID + ".tooltip.occurrence", "Заметить присутствие...");
            add(Ref.ID + ".fusion_1", "Термоядерный реактор 1");
            add(Ref.ID + ".fusion_2", "Термоядерный реактор 2");
            add(Ref.ID + ".fusion_3", "Термоядерный реактор 3");
        }

        @Override
        protected void processTranslations(String domain, String locale) {
            super.processTranslations(domain, locale);
            if (!locale.startsWith("ru")) return;

            InputStream stream = getClass().getClassLoader().getResourceAsStream("ru_ru.json");

            assert stream != null;
            String basicWords = new BufferedReader(
                    new InputStreamReader(stream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            Gson gson = new Gson();

            final Map<String, String> translations = gson.fromJson(basicWords, Map.class);

            //translations.keySet().forEach(i-> System.out.println(i + ' ' + translations.get(i)));

            
//            AntimatterAPI.all(BlockCasing.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpaced(i.getId())));
//            AntimatterAPI.all(BlockCoil.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpaced(i.getId())));
            AntimatterAPI.all(ItemIntCircuit.class, domain).forEach(i -> add(i, "Микросхема (" + i.circuitId + ")"));

            add(GregTechData.RUBBER_LEAVES, "Листья резинового дерева");
            add(GregTechData.RUBBER_LOG, "Древесина резинового дерева");
            add(GregTechData.RUBBER_SAPLING, "Саженец резинового дерева");
            add(GregTechData.CASING_FUSION_1, "Корпус термоядерного реактора 1");
            add(GregTechData.CASING_FUSION_2, "Корпус термоядерного реактора 2");
            add(GregTechData.CASING_FUSION_3, "Корпус термоядерного реактора 3");
            add(GregTechData.COIL_FUSION, "Катушка термоядерного реактора");

            add(muramasa.antimatter.Ref.TAB_BLOCKS, "GTI Блоки");
            add(muramasa.antimatter.Ref.TAB_ITEMS, "GTI Предметы");
            add(muramasa.antimatter.Ref.TAB_MACHINES, "GTI Машины");
            add(muramasa.antimatter.Ref.TAB_MATERIALS, "GTI Материалы");
            add(muramasa.antimatter.Ref.TAB_TOOLS, "GTI Инструменты");

            add("machine.voltage.in", "Напряжение");
            add("machine.power.capacity", "Ёмкость хранилища");
            add("generic.amp", "Сила тока");
            add("generic.tier", "Уровень");
            add("generic.voltage", "Напряжение");
            //Is this loss?
            add("generic.loss", "Потери на блок");
            add("message.discharge.on", "Разрядка включена");
            add("message.discharge.off", "Разрядка отключена");
            add("item.charge", "Энергия");
            add("item.reusable", "Перезаряжаемый");

            AntimatterAPI.all(Machine.class, domain).forEach(i -> {
                Collection<Tier> tiers = i.getTiers();
                if (i.has(MachineFlag.BASIC)) {
                    tiers.forEach(t ->  {
                        if (translations.containsKey(i.getId())){
                            if (!t.getId().equals("steel") && !t.getId().equals("bronze"))
                                add("machine." + i.getId() + "." + t.getId(), translations.get(i.getId()) + " " + t.getId().toUpperCase());
                            else
                            {
                                if (translations.get(i.getId()).contains("ой") && t.getId().contains("steel"))
                                    add("machine." + i.getId() + "." + t.getId(), translations.get("steel_machine") + "ой " + translations.get(i.getId()));
                                else if
                                (translations.get(i.getId()).contains("ой") && t.getId().contains("bronze"))
                                    add("machine." + i.getId() + "." + t.getId(), translations.get("bronze_machine") + "ый " + translations.get(i.getId()));
                                else if (translations.get(i.getId()).contains("ая"))
                                    add("machine." + i.getId() + "." + t.getId(), translations.get(t.getId() + "_machine") + "ая " + translations.get(i.getId()));
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

        }
    }

}
