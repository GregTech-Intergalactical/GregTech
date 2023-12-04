package muramasa.gregtech.loader.crafting;

import com.google.common.collect.ImmutableMap;
import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.data.ForgeCTags;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemTag;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.GTIRef;
import net.minecraft.resources.ResourceLocation;
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
import static muramasa.gregtech.data.Materials.*;

public class VanillaExtensions {
    public static void loadRecipes(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider) {
        provider.addConditionalRecipe(consumer, provider.getStackRecipe("", false,
                new ItemStack(Blocks.TORCH, 2), of('D', TagUtils.getForgelikeItemTag("dusts/sulfur"), 'R', ForgeCTags.RODS_WOODEN), "D", "R"), GTIRef.class, "sulfurTorch", GTIRef.ID, "sulfur_torch");

       provider.addItemRecipe(consumer, GTIRef.ID, "", "gears",
                AntimatterMaterialTypes.GEAR.get(AntimatterMaterials.Wood), of('P', ItemTags.PLANKS, 'W', WRENCH.getTag(), 'R', ForgeCTags.RODS_WOODEN), "RPR", "PWP", "RPR");

        provider.addItemRecipe(consumer, GTIRef.ID, "gear_wood_alt", "gears",
                AntimatterMaterialTypes.GEAR.get(AntimatterMaterials.Wood), of('P', ItemTags.PLANKS, 'W', WRENCH.getTag(), 'R', ForgeCTags.RODS_WOODEN), "RPR", "PWP", "RPR");

        provider.addItemRecipe(consumer, GTIRef.ID, "" , "gears",
                AntimatterMaterialTypes.GEAR.get(AntimatterMaterials.Stone), of('S', ForgeCTags.STONE, 'W', WRENCH.getTag(), 'C', ForgeCTags.COBBLESTONE), "SCS", "CWC", "SCS");

        provider.addItemRecipe(consumer, GTIRef.ID,  "gear_stone_alt", "gears",
                AntimatterMaterialTypes.GEAR.get(AntimatterMaterials.Stone), of('S', ForgeCTags.STONE, 'W', WRENCH.getTag(), 'C', ForgeCTags.COBBLESTONE), "CSC", "SWS", "CSC");

        provider.addItemRecipe(consumer, GTIRef.ID, "chainmail_helmet", "chainmail_armor",
                Items.CHAINMAIL_HELMET, of('R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'H', HAMMER.getTag()), "RRR", "RHR");
        provider.addItemRecipe(consumer, GTIRef.ID, "chainmail_chestplate", "chainmail_armor",
                Items.CHAINMAIL_CHESTPLATE, of('R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'H', HAMMER.getTag()), "RHR", "RRR", "RRR");
        provider.addItemRecipe(consumer, GTIRef.ID, "chainmail_leggings", "chainmail_armor",
                Items.CHAINMAIL_LEGGINGS, of('R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'H', HAMMER.getTag()), "RRR", "RHR", "R R");
        provider.addItemRecipe(consumer, GTIRef.ID, "chainmail_boots", "chainmail_armor",
                Items.CHAINMAIL_BOOTS, of('R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'H', HAMMER.getTag()), "R R", "RHR");
        provider.addItemRecipe(consumer, GTIRef.ID, "saddle", "", Items.SADDLE,
                of('L', Items.LEATHER, 'R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'S', AntimatterMaterialTypes.SCREW.getMaterialTag(Steel)), "LLL", "LSL", "R R");

        provider.addItemRecipe(consumer, "magnetic_rods_iron", ROD.get(IronMagnetic),
                of('R', ForgeCTags.DUSTS_REDSTONE, 'S', TagUtils.getForgelikeItemTag("rods/iron")), " R ", "RSR", " R ");

        provider.addItemRecipe(consumer, "magnetic_rods_neodymium", ROD.get(Neodymium),
                of('R', ForgeCTags.DUSTS_REDSTONE, 'S', TagUtils.getForgelikeItemTag("rods/neodymium")), " R ", "RSR", " R ");
        provider.addStackRecipe(consumer, GTIRef.ID, "torch_from_coal", "torches", new ItemStack(Items.TORCH, 4),
                of('C', Ingredient.of(RAW_ORE.get(Coal), DUST.get(Coal), DUST_IMPURE.get(Coal), DUST_PURE.get(Coal), CRUSHED.get(Coal),CRUSHED_PURIFIED.get(Coal), CRUSHED_REFINED.get(Coal)), 'S', Items.STICK), "C", "S");
        provider.addStackRecipe(consumer, GTIRef.ID, "torch_from_lignite", "torches", new ItemStack(Items.TORCH, 2),
                of('C', Ingredient.of(GEM.get(Lignite), RAW_ORE.get(Lignite), DUST.get(Lignite), DUST_IMPURE.get(Lignite), DUST_PURE.get(Lignite), CRUSHED.get(Lignite),CRUSHED_PURIFIED.get(Lignite), CRUSHED_REFINED.get(Lignite)), 'S', Items.STICK), "C", "S");
        provider.addStackRecipe(consumer, GTIRef.ID, "torch_from_creosote", "torches", new ItemStack(Items.TORCH, 6),
                of('W', ItemTags.WOOL, 'C', Creosote.getLiquid().getBucket(), 'S', Items.STICK), "C", "W", "S");
    }
}
