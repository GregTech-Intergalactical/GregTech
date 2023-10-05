package muramasa.gregtech.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.ItemPipe;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.data.ToolTypes;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.data.AntimatterDefaultTools.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Copper;
import static muramasa.antimatter.data.AntimatterMaterials.Gold;
import static muramasa.antimatter.material.MaterialTags.NOSMASH;
import static muramasa.antimatter.material.MaterialTags.RUBBERTOOLS;
import static muramasa.gregtech.data.Materials.*;

public class MaterialCrafting {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        addShapelessDustRecipe(output, provider, "bronze_dust", DUST.get(Bronze, 4), DUST.get(Copper), DUST.get(Copper), DUST.get(Copper), DUST.get(Tin));
        addShapelessDustRecipe(output, provider, "brass_dust", DUST.get(Brass, 4), DUST.get(Copper), DUST.get(Copper), DUST.get(Copper), DUST.get(Zinc));
        addShapelessDustRecipe(output, provider, "black_bronze_dust", DUST.get(BlackBronze, 5), DUST.getMaterialTag(Copper), DUST.getMaterialTag(Copper), DUST.getMaterialTag(Copper), DUST.getMaterialTag(Silver), DUST.getMaterialTag(Gold));
        addShapelessDustRecipe(output, provider, "black_steel_dust", DUST.get(BlackSteel, 5), DUST.getMaterialTag(Steel), DUST.getMaterialTag(Steel), DUST.getMaterialTag(Steel), DUST.getMaterialTag(BlackBronze), DUST.getMaterialTag(Nickel));
        addShapelessDustRecipe(output, provider, "rose_gold", DUST.get(RoseGold, 5), DUST.getMaterialTag(Gold), DUST.getMaterialTag(Gold), DUST.getMaterialTag(Gold), DUST.getMaterialTag(Gold), DUST.getMaterialTag(Copper));
        addShapelessDustRecipe(output, provider, "sterling_silver", DUST.get(SterlingSilver, 5), DUST.getMaterialTag(Silver), DUST.getMaterialTag(Silver), DUST.getMaterialTag(Silver), DUST.getMaterialTag(Silver), DUST.getMaterialTag(Copper));
        addShapelessDustRecipe(output, provider, "bismuth_bronze", DUST.get(BismuthBronze, 5), DUST.getMaterialTag(Copper), DUST.getMaterialTag(Copper), DUST.getMaterialTag(Copper), DUST.getMaterialTag(Bismuth), DUST.getMaterialTag(Zinc));
        addShapelessDustRecipe(output, provider, "red_steel", DUST.get(RedSteel, 8), DUST.getMaterialTag(BlackSteel), DUST.getMaterialTag(BlackSteel), DUST.getMaterialTag(BlackSteel), DUST.getMaterialTag(BlackSteel),
                DUST.getMaterialTag(Steel), DUST.getMaterialTag(Steel), DUST.getMaterialTag(Brass), DUST.getMaterialTag(RoseGold));
        addShapelessDustRecipe(output, provider, "blue_steel", DUST.get(BlueSteel, 8), DUST.getMaterialTag(BlackSteel), DUST.getMaterialTag(BlackSteel), DUST.getMaterialTag(BlackSteel), DUST.getMaterialTag(BlackSteel),
                DUST.getMaterialTag(Steel), DUST.getMaterialTag(Steel), DUST.getMaterialTag(BismuthBronze), DUST.getMaterialTag(SterlingSilver));
        addShapelessDustRecipe(output, provider, "cobalt_brass", DUST.get(CobaltBrass, 9), DUST.getMaterialTag(Brass), DUST.getMaterialTag(Brass), DUST.getMaterialTag(Brass),
                DUST.getMaterialTag(Brass), DUST.getMaterialTag(Brass), DUST.getMaterialTag(Brass), DUST.getMaterialTag(Brass), DUST.getMaterialTag(Aluminium), DUST.getMaterialTag(Cobalt));
        provider.shapeless(output, GTIRef.ID, "", "dusts", "has_mortor", provider.hasSafeItem(MORTAR.getTag()), AntimatterMaterialTypes.DUST_SMALL.get(Clay, 2), MORTAR.getTag(), Items.CLAY_BALL);
        loadAutoRecipes(output, provider);
    }

    public static void loadAutoRecipes(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider){
        final CriterionTriggerInstance in = provider.hasSafeItem(AntimatterDefaultTools.WRENCH.getTag());
        int craftingMultiplier = AntimatterConfig.GAMEPLAY.LOSSY_PART_CRAFTING ? 1 : 2;
        DUST.all().forEach(m -> {
            provider.addStackRecipe(consumer, GTIRef.ID, m.getId() + "_small_dust", "antimatter_materials", "has_wrench", in, DUST_SMALL.get(m, 4),
                    of('D', DUST.getMaterialTag(m)), " D");
            provider.addStackRecipe(consumer, GTIRef.ID, m.getId() + "_tiny_dust", "antimatter_materials", "has_wrench", in, DUST_TINY.get(m, 9),
                    of('D', DUST.getMaterialTag(m)), "D ");
        });
        AntimatterMaterialTypes.ROD.all().forEach(m -> {
            if (m.has(AntimatterMaterialTypes.INGOT)) {
                provider.addStackRecipe(consumer, GTIRef.ID, m.getId() + "_rod", "antimatter_material", "has_wrench", in, AntimatterMaterialTypes.ROD.get(m, craftingMultiplier), of('F', AntimatterDefaultTools.FILE.getTag(), 'I', AntimatterMaterialTypes.INGOT.getMaterialTag(m)), "F", "I");
            }
            if (m.has(AntimatterMaterialTypes.BOLT)) {
                provider.addStackRecipe(consumer, GTIRef.ID, m.getId() + "_bolt", "antimatter_material", "has_wrench", in, AntimatterMaterialTypes.BOLT.get(m, 2 * craftingMultiplier), of('F', AntimatterDefaultTools.SAW.getTag(), 'I', AntimatterMaterialTypes.ROD.getMaterialTag(m)), "F ", " I");
                if (m.has(AntimatterMaterialTypes.SCREW)) {
                    String[] pattern = AntimatterConfig.GAMEPLAY.LOSSY_PART_CRAFTING ? new String[]{"FI", "I "} : new String[]{"F", "I"};
                    provider.addStackRecipe(consumer, GTIRef.ID, m.getId() + "_screw", "antimatter_material",
                            "has_wrench", in, AntimatterMaterialTypes.SCREW.get(m, 1), of('F', AntimatterDefaultTools.FILE.getTag(), 'I', AntimatterMaterialTypes.BOLT.getMaterialTag(m)), pattern);
                }
            }
            if (m.has(AntimatterMaterialTypes.RING)) {
                if (!m.has(NOSMASH)){
                    provider.addStackRecipe(consumer, GTIRef.ID, m.getId() + "_ring", "antimatter_material", "has_hammer", provider.hasSafeItem(AntimatterDefaultTools.HAMMER.getTag()),
                            AntimatterMaterialTypes.RING.get(m, craftingMultiplier), ImmutableMap.of('H', AntimatterDefaultTools.HAMMER.getTag(), 'W', AntimatterMaterialTypes.ROD.getMaterialTag(m)), "H ", " W");
                }
            }
            if (m.has(ROD_LONG)){
                provider.addStackRecipe(consumer, GTIRef.ID, m.getId() + "_rod_from_long_rod", "rods", "has_saw", provider.hasSafeItem(SAW.getTag()), ROD.get(m, 2),
                        ImmutableMap.of('S', SAW.getTag(), 'R', ROD_LONG.getMaterialTag(m)), "SR");
                if (!m.has(NOSMASH)){
                    provider.addStackRecipe(consumer, GTIRef.ID, "", "rods", "has_hammer", provider.hasSafeItem(HAMMER.getTag()), ROD_LONG.get(m, 1),
                            ImmutableMap.of('S', HAMMER.getTag(), 'R', ROD.getMaterialTag(m)), "RSR");
                }
            }
        });
        AntimatterMaterialTypes.ROTOR.all().forEach(m -> {
            provider.addStackRecipe(consumer, GTIRef.ID, m.getId() + "_rotors", "antimatter_material", "has_screwdriver", provider.hasSafeItem(AntimatterDefaultTools.SCREWDRIVER.getTag()),
                    AntimatterMaterialTypes.ROTOR.get(m, 1), ImmutableMap.<Character, Object>builder()
                            .put('S', AntimatterDefaultTools.SCREWDRIVER.getTag())
                            .put('F', AntimatterDefaultTools.FILE.getTag())
                            .put('H', AntimatterDefaultTools.HAMMER.getTag())
                            .put('P', AntimatterMaterialTypes.PLATE.getMaterialTag(m))
                            .put('W', AntimatterMaterialTypes.SCREW.getMaterialTag(m))
                            .put('R', AntimatterMaterialTypes.RING.getMaterialTag(m))
                            .build(),
                    "PHP", "WRF", "PSP");
        });
        ToolTypes.TURBINE_BLADE.all().forEach(m -> {
            provider.addStackRecipe(consumer, GTIRef.ID, "", "antimatter_materials", "has_screwdriver", provider.hasSafeItem(SCREWDRIVER.getTag()),
                    ToolTypes.TURBINE_BLADE.get(m, 1), ImmutableMap.<Character, Object>builder()
                            .put('S', SCREWDRIVER.getTag())
                            .put('F', FILE.getTag())
                            .put('P', PLATE.getMaterialTag(m))
                            .put('s', SCREW.getMaterialTag(m)).build(), "FPS", "sPs", " P ");
        });
        AntimatterMaterialTypes.PLATE.all().forEach(m -> {
            if (!m.has(NOSMASH)){
                if (m.has(AntimatterMaterialTypes.INGOT)){
                    Object[] array = AntimatterConfig.GAMEPLAY.LOSSY_PART_CRAFTING ? new Object[]{AntimatterDefaultTools.HAMMER.getTag(), AntimatterMaterialTypes.INGOT.getMaterialTag(m), AntimatterMaterialTypes.INGOT.getMaterialTag(m)} : new Object[]{AntimatterDefaultTools.HAMMER.getTag(), AntimatterMaterialTypes.INGOT.getMaterialTag(m)};
                    provider.shapeless(consumer, GTIRef.ID, "", "antimatter_material", "has_hammer", provider.hasSafeItem(AntimatterDefaultTools.HAMMER.getTag()), AntimatterMaterialTypes.PLATE.get(m, 1), array);
                }
                if (m.has(AntimatterMaterialTypes.GEAR_SMALL)) {
                    provider.addStackRecipe(consumer, GTIRef.ID, "", "antimatter_material", "has_hammer", provider.hasSafeItem(AntimatterDefaultTools.HAMMER.getTag()),
                            AntimatterMaterialTypes.GEAR_SMALL.get(m, 1), ImmutableMap.of('H', AntimatterDefaultTools.HAMMER.getTag(),'P', AntimatterMaterialTypes.PLATE.getMaterialTag(m)), "P ", " H");
                }
                if (m.has(AntimatterMaterialTypes.ITEM_CASING)) {
                    provider.addStackRecipe(consumer, GTIRef.ID, "", "antimatter_material", "has_hammer", provider.hasSafeItem(AntimatterDefaultTools.HAMMER.getTag()),
                            AntimatterMaterialTypes.ITEM_CASING.get(m, 1), ImmutableMap.of('H', AntimatterDefaultTools.HAMMER.getTag(),'P', AntimatterMaterialTypes.PLATE.getMaterialTag(m)), "H P");
                }
                if (m.has(FOIL)){
                    provider.addStackRecipe(consumer, GTIRef.ID, "", "antimatter_materials", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                            FOIL.get(m, 2), of('H', HAMMER.getTag(), 'P', PLATE.getMaterialTag(m)), "HP");
                    if (m.has(WIRE_FINE)){
                        provider.addItemRecipe(consumer, GTIRef.ID, "", "antimatter_materials", "has_wire_cutters", provider.hasSafeItem(WIRE_CUTTER.getTag()),
                                WIRE_FINE.get(m), of('F', FOIL.getMaterialTag(m), 'W', WIRE_CUTTER.getTag()), "FW");
                    }
                }
            }
            if (m.has(AntimatterMaterialTypes.GEAR)){
                provider.addStackRecipe(consumer, GTIRef.ID, m.getId() + "_gear", "antimatter_material", "has_hammer", provider.hasSafeItem(AntimatterDefaultTools.HAMMER.getTag()),
                        AntimatterMaterialTypes.GEAR.get(m, 1), ImmutableMap.<Character, Object>builder()
                                .put('W', AntimatterDefaultTools.WRENCH.getTag())
                                .put('P', AntimatterMaterialTypes.PLATE.getMaterialTag(m))
                                .put('R', AntimatterMaterialTypes.ROD.getMaterialTag(m))
                                .build(),
                        "RPR", "PWP", "RPR");
            }
            if (m.has(AntimatterMaterialTypes.RING)) {
                if (m.has(RUBBERTOOLS)){
                    provider.addStackRecipe(consumer, GTIRef.ID, m.getId() + "_ring", "antimatter_material", "has_wire_cutter", provider.hasSafeItem(AntimatterDefaultTools.WIRE_CUTTER.getTag()),
                            AntimatterMaterialTypes.RING.get(m, craftingMultiplier), ImmutableMap.of('H', AntimatterDefaultTools.WIRE_CUTTER.getTag(), 'W', AntimatterMaterialTypes.PLATE.getMaterialTag(m)), "H ", " W");
                }
            }
        });
        AntimatterAPI.all(ItemPipe.class, i -> {
            if (i.getSizes().contains(PipeSize.NORMAL)){
                provider.addStackRecipe(consumer, GTIRef.ID, "", "antimatter_pipes", "has_wrench", in, new ItemStack(i.getRestrictedBlock(PipeSize.NORMAL), 1), of('H', HAMMER.getTag(), 'R', RING.getMaterialTag(Steel), 'P', i.getBlock(PipeSize.NORMAL)), " H ", "RPR", " R ");
            }
            if (i.getSizes().contains(PipeSize.LARGE)){
                provider.addStackRecipe(consumer, GTIRef.ID, "", "antimatter_pipes", "has_wrench", in, new ItemStack(i.getRestrictedBlock(PipeSize.LARGE), 1), of('H', HAMMER.getTag(), 'R', RING.getMaterialTag(Steel), 'P', i.getBlock(PipeSize.LARGE)), "HR ", "RPR", " R ");
            }
            if (i.getSizes().contains(PipeSize.HUGE)) {
                provider.addStackRecipe(consumer, GTIRef.ID, "", "antimatter_pipes", "has_wrench", in, new ItemStack(i.getRestrictedBlock(PipeSize.HUGE), 1), of('H', HAMMER.getTag(), 'R', RING.getMaterialTag(Steel), 'P', i.getBlock(PipeSize.HUGE)), " H ", "RPR", "RRR");
            }
        });
    }

    private static void addShapelessDustRecipe(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, String recipeName, ItemStack outputItem, Object... inputs)
    {
        provider.shapeless(output, GTIRef.ID, recipeName, "misc", "has_wrench",
                provider.hasSafeItem(AntimatterDefaultTools.WRENCH.getTag()), outputItem, inputs);
    }
}
