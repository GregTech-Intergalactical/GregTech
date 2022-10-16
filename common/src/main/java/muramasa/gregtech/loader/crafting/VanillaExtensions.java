package muramasa.gregtech.loader.crafting;

import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.data.ForgeCTags;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.Ref;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.Materials;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.Data.ROD;
import static muramasa.gregtech.data.GregTechData.ItemFilter;
import static muramasa.gregtech.data.Materials.IronMagnetic;
import static muramasa.gregtech.data.Materials.Neodymium;

public class VanillaExtensions {
    public static void loadRecipes(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider) {
        provider.addConditionalRecipe(consumer, provider.getStackRecipe("", "has_sulfur_dust", provider.hasSafeItem(TagUtils.getForgelikeItemTag("dusts/sulfur")),
                new ItemStack(Blocks.TORCH, 6), of('D', TagUtils.getForgelikeItemTag("dusts/sulfur"), 'R', ForgeCTags.RODS_WOODEN), "D", "R"), Ref.class, "sulfurTorch", Ref.ID, "sulfur_torch");

        provider.addItemRecipe(consumer, Ref.ID, "hopper", "", "has_wrench", provider.hasSafeItem(WRENCH.getTag()),
                Blocks.HOPPER, of('C', Blocks.CHEST, 'I', TagUtils.getForgelikeItemTag("plates/iron"), 'W', WRENCH.getTag()), "IWI", "ICI", " I ");

        provider.addItemRecipe(consumer,Ref.ID,"filter", "", "has_iron_plate", provider.hasSafeItem(WRENCH.getTag()),
                ItemFilter, of('Z', TagUtils.getForgelikeItemTag("foils/zinc"), 'I', TagUtils.getForgelikeItemTag("plates/iron")), "ZZZ", "ZIZ", "ZZZ");

        provider.addItemRecipe(consumer,Ref.ID,"gears", "gears", "has_wooden_rod", provider.hasSafeItem(ForgeCTags.RODS_WOODEN),
                GEAR.get(Wood), of('P', ItemTags.PLANKS, 'W', WRENCH.getTag(), 'R', ForgeCTags.RODS_WOODEN), "RPR", "PWP", "RPR");

        provider.addItemRecipe(consumer, Ref.ID, GEAR.get(Wood).getRegistryName().getPath() + "_alt", "gears", "has_wooden_rod", provider.hasSafeItem(ForgeCTags.RODS_WOODEN),
                GEAR.get(Wood), of('P', ItemTags.PLANKS, 'W', WRENCH.getTag(), 'R', ForgeCTags.RODS_WOODEN), "RPR", "PWP", "RPR");

        provider.addItemRecipe(consumer,Ref.ID, "gears", "gears", "has_stone", provider.hasSafeItem(ForgeCTags.STONE),
                GEAR.get(Stone), of('S', ForgeCTags.STONE, 'W', WRENCH.getTag(), 'C', ForgeCTags.COBBLESTONE), "SCS", "CWC", "SCS");

        provider.addItemRecipe(consumer, Ref.ID, GEAR.get(Stone).getRegistryName().getPath() + "_alt", "gears", "has_stone", provider.hasSafeItem(ForgeCTags.STONE),
                GEAR.get(Stone), of('S', ForgeCTags.STONE, 'W', WRENCH.getTag(), 'C', ForgeCTags.COBBLESTONE), "CSC", "SWS", "CSC");

        provider.addItemRecipe(consumer, Ref.ID, "piston_sticky","gears", "has_stone", provider.hasSafeItem(Blocks.PISTON), Blocks.STICKY_PISTON, of('S', GTRubberData.StickyResin, 'P', Blocks.PISTON), "S", "P");

        provider.addItemRecipe(consumer, "magnetic_rods", "has_redstone", provider.hasSafeItem(ForgeCTags.DUSTS_REDSTONE), ROD.get(IronMagnetic),
                of('R', ForgeCTags.DUSTS_REDSTONE, 'S', TagUtils.getForgelikeItemTag("rods/iron")), " R ", "RSR", " R ");

        provider.addItemRecipe(consumer, "magnetic_rods", "has_redstone", provider.hasSafeItem(ForgeCTags.DUSTS_REDSTONE), ROD.get(Neodymium),
                of('R', ForgeCTags.DUSTS_REDSTONE, 'S', TagUtils.getForgelikeItemTag("rods/neodymium")), " R ", "RSR", " R ");
    }
}
