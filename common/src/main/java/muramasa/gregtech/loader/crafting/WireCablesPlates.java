package muramasa.gregtech.loader.crafting;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.GTIRef;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static muramasa.antimatter.data.AntimatterMaterialTypes.PLATE;
import static muramasa.antimatter.pipe.PipeSize.*;
import static muramasa.antimatter.pipe.PipeSize.HUGE;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.Materials.Rubber;

public class WireCablesPlates {
    @SuppressWarnings("unchecked")
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        AntimatterAPI.all(Wire.class, wire -> {
            Cable<?> cable = AntimatterAPI.get(Cable.class, "cable" + "_" + wire.getMaterial().getId());
            ImmutableSet<PipeSize> sizes = wire.getSizes();
            Map<PipeSize, Item> wires = sizes.stream().map(s -> new Pair<>(s, wire.getBlockItem(s))).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
            PipeSize[] val = values();
            for (int i = 1; i < val.length; i += 1) {
                oneToTwo(wires, val[i], val[i-1], output, provider);
                twoToOne(wires, val[i-1], val[i], output,provider);
                if (i > 1) {
                    fourToOne(wires, val[i-2], val[i], output, provider);
                }
            }
            if (wire.getMaterial().has(PLATE)) {
                provider.shapeless(output,  wire.getMaterial().getId() + "_plate_to_wire","wire","has_cutter", provider.hasSafeItem(AntimatterDefaultTools.WIRE_CUTTER.getTag()),
                        new ItemStack(wires.get(VTINY)),
                        AntimatterDefaultTools.WIRE_CUTTER.getTag(), PLATE.get(wire.getMaterial()));
            }
            if ((wire.getTier() == Tier.LV || wire.getTier() == Tier.ULV) && cable != null){ //manual lv cable crafting
                provider.shapeless(output, GTIRef.ID, wire.getId() + "_cable_1x", "cables", "has_wire1x", provider.hasSafeItem(wire.getBlockItem(VTINY)), new ItemStack(cable.getBlockItem(VTINY)), wire.getBlockItem(VTINY), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber));
                provider.shapeless(output, GTIRef.ID, wire.getId() + "_cable_2x", "cables", "has_wire2x", provider.hasSafeItem(wire.getBlockItem(TINY)), new ItemStack(cable.getBlockItem(TINY)), wire.getBlockItem(TINY), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber));
                provider.shapeless(output, GTIRef.ID, wire.getId() + "_cable_4x", "cables", "has_wire4x", provider.hasSafeItem(wire.getBlockItem(SMALL)), new ItemStack(cable.getBlockItem(SMALL)), wire.getBlockItem(SMALL), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber));
                provider.shapeless(output, GTIRef.ID, wire.getId() + "_cable_8x", "cables", "has_wire8x", provider.hasSafeItem(wire.getBlockItem(NORMAL)), new ItemStack(cable.getBlockItem(NORMAL)), wire.getBlockItem(NORMAL), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber));
                provider.shapeless(output, GTIRef.ID, wire.getId() + "_cable_12x", "cables", "has_wire12x", provider.hasSafeItem(wire.getBlockItem(LARGE)), new ItemStack(cable.getBlockItem(LARGE)), wire.getBlockItem(LARGE), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber));
                provider.shapeless(output, GTIRef.ID, wire.getId() + "_cable_16x", "cables", "has_wire16x", provider.hasSafeItem(wire.getBlockItem(HUGE)), new ItemStack(cable.getBlockItem(HUGE)), wire.getBlockItem(HUGE), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber));
            }
        });
    }

    private static void twoToOne(Map<PipeSize, Item> wires, PipeSize from, PipeSize to, Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.shapeless(output,"two_to_one_" + AntimatterPlatformUtils.getIdFromItem(wires.get(to)).getPath(),"wire","has_cutter",provider.hasSafeItem(AntimatterDefaultTools.WIRE_CUTTER.getTag()),
                new ItemStack(wires.get(to),1),wires.get(from),wires.get(from));
    }

    private static void oneToTwo(Map<PipeSize, Item> wires, PipeSize from, PipeSize to, Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.shapeless(output,"one_to_two_" + AntimatterPlatformUtils.getIdFromItem(wires.get(to)).getPath(),"wire","has_cutter",provider.hasSafeItem(AntimatterDefaultTools.WIRE_CUTTER.getTag()),
                new ItemStack(wires.get(to),2),wires.get(from));
    }

    private static void fourToOne(Map<PipeSize, Item> wires, PipeSize from, PipeSize to, Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.shapeless(output,"four_to_one_" + AntimatterPlatformUtils.getIdFromItem(wires.get(to)).getPath(),"wire","has_cutter",provider.hasSafeItem(AntimatterDefaultTools.WIRE_CUTTER.getTag()),
                new ItemStack(wires.get(to),1),wires.get(from),wires.get(from),wires.get(from),wires.get(from));
    }
}
