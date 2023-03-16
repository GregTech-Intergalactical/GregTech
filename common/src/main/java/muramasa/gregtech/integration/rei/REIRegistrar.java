package muramasa.gregtech.integration.rei;

import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.integration.rei.REIUtils;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.MaterialTags;
import muramasa.gregtech.data.Machines;
import net.minecraft.world.item.ItemStack;

public class REIRegistrar {

    public static void init(){
        REIUtils.addExtraCategory(r -> {
            OreProcessingCategory cat = new OreProcessingCategory();
            r.add(cat);
            r.addWorkstations(cat.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(Machines.MACERATOR.getItem(Tier.LV))));
            r.addWorkstations(cat.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(Machines.ORE_WASHER.getItem(Tier.LV))));
            r.addWorkstations(cat.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(Machines.CENTRIFUGE.getItem(Tier.LV))));
            r.addWorkstations(cat.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(Machines.THERMAL_CENTRIFUGE.getItem(Tier.LV))));
            r.addWorkstations(cat.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(Machines.CHEMICAL_BATH.getItem(Tier.LV))));
            r.addWorkstations(cat.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(Machines.ELECTROMAGNETIC_SEPARATOR.getItem(Tier.LV))));
            r.addWorkstations(cat.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(Machines.SIFTER.getItem(Tier.LV))));
            MaterialTreeCategory cat2 = new MaterialTreeCategory();
            r.add(cat2);
            r.addWorkstations(cat2.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(Machines.LATHE.getItem(Tier.LV))));
            r.addWorkstations(cat2.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(Machines.BENDER.getItem(Tier.LV))));
            r.addWorkstations(cat2.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(Machines.CUTTER.getItem(Tier.LV))));
            r.addWorkstations(cat2.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(Machines.ASSEMBLER.getItem(Tier.LV))));
            r.addWorkstations(cat2.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(Machines.COMPRESSOR.getItem(Tier.LV))));
            r.addWorkstations(cat2.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(Machines.FLUID_EXTRACTOR.getItem(Tier.LV))));
        });
        REIUtils.addExtraDisplay(r -> {
            AntimatterMaterialTypes.ORE.all().forEach(m -> {
                if (m.has(MaterialTags.CHEMBATH_PERSULFATE) || m.has(MaterialTags.CHEMBATH_MERCURY)){
                    if (m.has(MaterialTags.CHEMBATH_MERCURY)){
                        r.add(new OreProcessingDisplay(m, OreProcessingDisplay.BathingMode.MERCURY));
                    }
                    if (m.has(MaterialTags.CHEMBATH_PERSULFATE)){
                        r.add(new OreProcessingDisplay(m, OreProcessingDisplay.BathingMode.PERSULFATE));
                    }
                } else {
                    r.add(new OreProcessingDisplay(m, OreProcessingDisplay.BathingMode.NONE));
                }
            });
            AntimatterMaterialTypes.DUST.all().forEach(m -> {
                r.add(new MaterialTreeDisplay(m));
            });
        });
    }
}
