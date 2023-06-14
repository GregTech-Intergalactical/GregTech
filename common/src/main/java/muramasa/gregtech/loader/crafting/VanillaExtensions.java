package muramasa.gregtech.loader.crafting;

import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.data.ForgeCTags;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemTag;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.GTIRef;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.data.AntimatterDefaultTools.HAMMER;
import static muramasa.antimatter.data.AntimatterDefaultTools.WRENCH;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Coal;
import static muramasa.antimatter.data.AntimatterMaterials.Iron;
import static muramasa.gregtech.data.GregTechData.ItemFilter;
import static muramasa.gregtech.data.Materials.*;

public class VanillaExtensions {
    public static void loadRecipes(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider) {
        provider.addConditionalRecipe(consumer, provider.getStackRecipe("", "has_sulfur_dust", provider.hasSafeItem(TagUtils.getForgelikeItemTag("dusts/sulfur")),
                new ItemStack(Blocks.TORCH, 2), of('D', TagUtils.getForgelikeItemTag("dusts/sulfur"), 'R', ForgeCTags.RODS_WOODEN), "D", "R"), GTIRef.class, "sulfurTorch", GTIRef.ID, "sulfur_torch");

        provider.addItemRecipe(consumer, GTIRef.ID, "hopper", "", "has_wrench", provider.hasSafeItem(WRENCH.getTag()),
                Blocks.HOPPER, of('C', Blocks.CHEST, 'I', TagUtils.getForgelikeItemTag("plates/iron"), 'W', WRENCH.getTag()), "IWI", "ICI", " I ");

        provider.addItemRecipe(consumer, GTIRef.ID,"filter", "", "has_iron_plate", provider.hasSafeItem(WRENCH.getTag()),
                ItemFilter, of('Z', TagUtils.getForgelikeItemTag("foils/zinc"), 'I', TagUtils.getForgelikeItemTag("plates/iron")), "ZZZ", "ZIZ", "ZZZ");

        provider.addItemRecipe(consumer, GTIRef.ID,AntimatterMaterialTypes.GEAR.get(AntimatterMaterials.Wood).getRegistryName().getPath() , "gears", "has_wooden_rod", provider.hasSafeItem(ForgeCTags.RODS_WOODEN),
                AntimatterMaterialTypes.GEAR.get(AntimatterMaterials.Wood), of('P', ItemTags.PLANKS, 'W', WRENCH.getTag(), 'R', ForgeCTags.RODS_WOODEN), "RPR", "PWP", "RPR");

        provider.addItemRecipe(consumer, GTIRef.ID, AntimatterMaterialTypes.GEAR.get(AntimatterMaterials.Wood).getRegistryName().getPath() + "_alt", "gears", "has_wooden_rod", provider.hasSafeItem(ForgeCTags.RODS_WOODEN),
                AntimatterMaterialTypes.GEAR.get(AntimatterMaterials.Wood), of('P', ItemTags.PLANKS, 'W', WRENCH.getTag(), 'R', ForgeCTags.RODS_WOODEN), "RPR", "PWP", "RPR");

        provider.addItemRecipe(consumer, GTIRef.ID, AntimatterMaterialTypes.GEAR.get(AntimatterMaterials.Stone).getRegistryName().getPath() , "gears", "has_stone", provider.hasSafeItem(ForgeCTags.STONE),
                AntimatterMaterialTypes.GEAR.get(AntimatterMaterials.Stone), of('S', ForgeCTags.STONE, 'W', WRENCH.getTag(), 'C', ForgeCTags.COBBLESTONE), "SCS", "CWC", "SCS");

        provider.addItemRecipe(consumer, GTIRef.ID, AntimatterMaterialTypes.GEAR.get(AntimatterMaterials.Stone).getRegistryName().getPath() + "_alt", "gears", "has_stone", provider.hasSafeItem(ForgeCTags.STONE),
                AntimatterMaterialTypes.GEAR.get(AntimatterMaterials.Stone), of('S', ForgeCTags.STONE, 'W', WRENCH.getTag(), 'C', ForgeCTags.COBBLESTONE), "CSC", "SWS", "CSC");

        provider.addItemRecipe(consumer, GTIRef.ID, "chainmail_helmet", "chainmail_armor", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                Items.CHAINMAIL_HELMET, of('R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'H', HAMMER.getTag()), "RRR", "RHR");
        provider.addItemRecipe(consumer, GTIRef.ID, "chainmail_chestplate", "chainmail_armor", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                Items.CHAINMAIL_CHESTPLATE, of('R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'H', HAMMER.getTag()), "RHR", "RRR", "RRR");
        provider.addItemRecipe(consumer, GTIRef.ID, "chainmail_leggings", "chainmail_armor", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                Items.CHAINMAIL_LEGGINGS, of('R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'H', HAMMER.getTag()), "RRR", "RHR", "R R");
        provider.addItemRecipe(consumer, GTIRef.ID, "chainmail_boots", "chainmail_armor", "has_hammer", provider.hasSafeItem(HAMMER.getTag()),
                Items.CHAINMAIL_BOOTS, of('R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'H', HAMMER.getTag()), "R R", "RHR");
        provider.addItemRecipe(consumer, GTIRef.ID, "saddle", "", "has_leather", provider.hasSafeItem(Items.LEATHER), Items.SADDLE,
                of('L', Items.LEATHER, 'R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'S', AntimatterMaterialTypes.SCREW.getMaterialTag(Steel)), "LLL", "LSL", "R R");
        provider.addStackRecipe(consumer, GTIRef.ID, "lead_from_resin", "", "has_resin", provider.hasSafeItem(GTRubberData.StickyResin), new ItemStack(Items.LEAD, 2), of('S', Items.STRING, 'R', GTRubberData.StickyResin), "SS ", "SR ", "  S");
        provider.shapeless(consumer, "gravel_to_flint", "mortar_recipes", "has_mortar", provider.hasSafeItem(AntimatterDefaultTools.MORTAR.getTag()), new ItemStack(Items.FLINT), AntimatterDefaultTools.MORTAR.getTag(), Items.GRAVEL);

        provider.addStackRecipe(consumer, "minecraft", "", "misc", "has_iron_rod", provider.hasSafeItem(ROD.getMaterialTag(Iron)), new ItemStack(Items.IRON_BARS, 8), of('R', ROD.getMaterialTag(Iron)), "RRR", "RRR");
        provider.addItemRecipe(consumer, GTIRef.ID, "piston_sticky","gears", "has_stone", provider.hasSafeItem(Blocks.PISTON), Blocks.STICKY_PISTON, of('S', GTRubberData.StickyResin, 'P', Blocks.PISTON), "S", "P");

        provider.addItemRecipe(consumer, "magnetic_rods_iron", "has_redstone", provider.hasSafeItem(ForgeCTags.DUSTS_REDSTONE), ROD.get(IronMagnetic),
                of('R', ForgeCTags.DUSTS_REDSTONE, 'S', TagUtils.getForgelikeItemTag("rods/iron")), " R ", "RSR", " R ");

        provider.addItemRecipe(consumer, "magnetic_rods_neodymium", "has_redstone", provider.hasSafeItem(ForgeCTags.DUSTS_REDSTONE), ROD.get(Neodymium),
                of('R', ForgeCTags.DUSTS_REDSTONE, 'S', TagUtils.getForgelikeItemTag("rods/neodymium")), " R ", "RSR", " R ");
        provider.addStackRecipe(consumer, GTIRef.ID, "torch_from_coal", "torches", "has_coal_dust", provider.hasSafeItem(DUST_IMPURE.get(Coal)), new ItemStack(Items.TORCH, 4),
                of('C', Ingredient.of(RAW_ORE.get(Coal), DUST.get(Coal), DUST_IMPURE.get(Coal), DUST_PURE.get(Coal), CRUSHED.get(Coal),CRUSHED_PURIFIED.get(Coal), CRUSHED_REFINED.get(Coal)), 'S', Items.STICK), "C", "S");
        provider.addStackRecipe(consumer, GTIRef.ID, "torch_from_lignite", "torches", "has_lignite_dust", provider.hasSafeItem(DUST_IMPURE.get(Lignite)), new ItemStack(Items.TORCH, 2),
                of('C', Ingredient.of(GEM.get(Lignite), RAW_ORE.get(Lignite), DUST.get(Lignite), DUST_IMPURE.get(Lignite), DUST_PURE.get(Lignite), CRUSHED.get(Lignite),CRUSHED_PURIFIED.get(Lignite), CRUSHED_REFINED.get(Lignite)), 'S', Items.STICK), "C", "S");
        provider.addStackRecipe(consumer, GTIRef.ID, "torch_from_creosote", "torches", "has_creosote", provider.hasSafeItem(Creosote.getLiquid().getBucket()), new ItemStack(Items.TORCH, 6),
                of('W', ItemTags.WOOL, 'C', Creosote.getLiquid().getBucket(), 'S', Items.STICK), "C", "W", "S");
        provider.addItemRecipe(consumer, "minecraft", "", "misc", "has_iron", provider.hasSafeItem(PLATE.getMaterialTag(Iron)),
                Items.BUCKET, of('I', PLATE.getMaterialTag(Iron), 'H', HAMMER.getTag()), "IHI", " I ");
        provider.addItemRecipe(consumer, "minecraft", "", "misc", "has_iron", provider.hasSafeItem(PLATE.getMaterialTag(Iron)),
                Items.HOPPER, of('I', PLATE.getMaterialTag(Iron), 'W', WRENCH.getTag(), 'C', ForgeCTags.CHESTS), "IWI", "ICI", " I ");
        provider.addStackRecipe(consumer, "minecraft", "", "cauldrons", "has_iron_plate", provider.hasSafeItem(PLATE.getMaterialTag(Iron)), new ItemStack(Items.CAULDRON),
                of('P', PLATE.getMaterialTag(Iron), 'H', HAMMER.getTag()), "P P", "PHP", "PPP");
    }
}
