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
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.ItemPipe;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.data.ToolTypes;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.data.AntimatterDefaultTools.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Copper;
import static muramasa.antimatter.data.AntimatterMaterials.Gold;
import static muramasa.antimatter.material.MaterialTags.*;
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
        provider.shapeless(output, GTIRef.ID, "", "dusts", AntimatterMaterialTypes.DUST_SMALL.get(Clay, 2), MORTAR.getTag(), Items.CLAY_BALL);
        loadAutoRecipes(output, provider);
    }

    public static void loadAutoRecipes(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider){
        DUST.all().forEach(m -> {
            provider.addStackRecipe(consumer, GTIRef.ID, m.getId() + "_small_dust", "antimatter_materials", DUST_SMALL.get(m, 4),
                    of('D', DUST.getMaterialTag(m)), " D");
            /*provider.addStackRecipe(consumer, GTIRef.ID, m.getId() + "_tiny_dust", "antimatter_materials", "has_wrench", in, DUST_TINY.get(m, 9),
                    of('D', DUST.getMaterialTag(m)), "D ");*/
        });
        ToolTypes.TURBINE_BLADE.all().forEach(m -> {
            provider.addStackRecipe(consumer, GTIRef.ID, "", "antimatter_materials",
                    ToolTypes.TURBINE_BLADE.get(m, 1), ImmutableMap.<Character, Object>builder()
                            .put('S', SCREWDRIVER.getTag())
                            .put('F', FILE.getTag())
                            .put('P', PLATE.getMaterialTag(m))
                            .put('s', SCREW.getMaterialTag(m)).build(), "FPS", "sPs", " P ");
        });
        AntimatterAPI.all(ItemPipe.class, i -> {
            if (i.getSizes().contains(PipeSize.NORMAL)){
                provider.addStackRecipe(consumer, GTIRef.ID, "", "antimatter_pipes", new ItemStack(i.getRestrictedBlock(PipeSize.NORMAL), 1), of('H', HAMMER.getTag(), 'R', RING.getMaterialTag(Steel), 'P', i.getBlock(PipeSize.NORMAL)), " H ", "RPR", " R ");
            }
            if (i.getSizes().contains(PipeSize.LARGE)){
                provider.addStackRecipe(consumer, GTIRef.ID, "", "antimatter_pipes", new ItemStack(i.getRestrictedBlock(PipeSize.LARGE), 1), of('H', HAMMER.getTag(), 'R', RING.getMaterialTag(Steel), 'P', i.getBlock(PipeSize.LARGE)), "HR ", "RPR", " R ");
            }
            if (i.getSizes().contains(PipeSize.HUGE)) {
                provider.addStackRecipe(consumer, GTIRef.ID, "", "antimatter_pipes", new ItemStack(i.getRestrictedBlock(PipeSize.HUGE), 1), of('H', HAMMER.getTag(), 'R', RING.getMaterialTag(Steel), 'P', i.getBlock(PipeSize.HUGE)), " H ", "RPR", "RRR");
            }
        });
        //todo move to gt core
        BLOCK.all().forEach(m -> {
            if (m.has(INGOT) || m.has(GEM)){
                MaterialTypeItem<?> input = m.has(GEM) ? GEM : INGOT;
                int output = m.has(QUARTZ_LIKE_BLOCKS) ? 4 : 9;
                String[] strings = m.has(QUARTZ_LIKE_BLOCKS) ? new String[]{"II", "II"} : new String[]{"III", "III", "III"};
                provider.addItemRecipe(consumer, "blocks", BLOCK.get().get(m).asItem(), of('I', input.getMaterialTag(m)), strings);
                provider.shapeless(consumer, "", "blocks", input.get(m, output), BLOCK.getMaterialTag(m));
            }
        });
        RAW_ORE_BLOCK.all().forEach(m -> {
            if (m.has(RAW_ORE)){
                provider.addItemRecipe(consumer, "blocks", RAW_ORE_BLOCK.get().get(m).asItem(), of('I', RAW_ORE.getMaterialTag(m)), "III", "III", "III");
                provider.shapeless(consumer, "", "blocks", RAW_ORE.get(m, 9), RAW_ORE_BLOCK.getMaterialTag(m));
            }
        });
    }

    private static void addShapelessDustRecipe(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, String recipeName, ItemStack outputItem, Object... inputs) {
        provider.shapeless(output, GTIRef.ID, recipeName, "misc", outputItem, inputs);
    }
}
