package muramasa.gti.loader.crafting;

import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.util.TagUtils;
import muramasa.gti.Ref;
import muramasa.gti.data.GregTechData;
import muramasa.gti.data.Materials;
import net.minecraft.block.Blocks;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.Data.ROD;
import static muramasa.gti.data.GregTechData.ItemFilter;
import static muramasa.gti.data.Materials.IronMagnetic;
import static muramasa.gti.data.Materials.Neodymium;

public class VanillaExtensions {
    public static void loadRecipes(Consumer<IFinishedRecipe> consumer, AntimatterRecipeProvider provider) {
        provider.addConditionalRecipe(consumer, provider.getStackRecipe("", "has_sulfur_dust", provider.hasSafeItem(TagUtils.getForgeItemTag("dusts/sulfur")),
                new ItemStack(Blocks.TORCH, 6), of('D', TagUtils.getForgeItemTag("dusts/sulfur"), 'R', Tags.Items.RODS_WOODEN), "D", "R"), Ref.class, "sulfurTorch", Ref.ID, "sulfur_torch");

        provider.addItemRecipe(consumer, Ref.ID, "hopper", "", "has_wrench", provider.hasSafeItem(WRENCH.getTag()),
                Blocks.HOPPER, of('C', Blocks.CHEST, 'I', TagUtils.getForgeItemTag("plates/iron"), 'W', WRENCH.getTag()), "IWI", "ICI", " I ");

        provider.addItemRecipe(consumer,Ref.ID,"filter", "", "has_iron_plate", provider.hasSafeItem(WRENCH.getTag()),
                ItemFilter, of('Z', TagUtils.getForgeItemTag("foils/zinc"), 'I', TagUtils.getForgeItemTag("plates/iron")), "ZZZ", "ZIZ", "ZZZ");

        provider.addItemRecipe(consumer,Ref.ID,"gears", "gears", "has_wooden_rod", provider.hasSafeItem(Tags.Items.RODS_WOODEN),
                GEAR.get(Materials.Wood), of('P', ItemTags.PLANKS, 'W', WRENCH.getTag(), 'R', Tags.Items.RODS_WOODEN), "RPR", "PWP", "RPR");

        provider.addItemRecipe(consumer, Ref.ID, GEAR.get(Materials.Wood).getRegistryName().getPath() + "_alt", "gears", "has_wooden_rod", provider.hasSafeItem(Tags.Items.RODS_WOODEN),
                GEAR.get(Materials.Wood), of('P', ItemTags.PLANKS, 'W', WRENCH.getTag(), 'R', Tags.Items.RODS_WOODEN), "RPR", "PWP", "RPR");

        provider.addItemRecipe(consumer,Ref.ID, "gears", "gears", "has_stone", provider.hasSafeItem(Tags.Items.STONE),
                GEAR.get(Stone), of('S', Tags.Items.STONE, 'W', WRENCH.getTag(), 'C', Tags.Items.COBBLESTONE), "SCS", "CWC", "SCS");

        provider.addItemRecipe(consumer, Ref.ID, GEAR.get(Stone).getRegistryName().getPath() + "_alt", "gears", "has_stone", provider.hasSafeItem(Tags.Items.STONE),
                GEAR.get(Stone), of('S', Tags.Items.STONE, 'W', WRENCH.getTag(), 'C', Tags.Items.COBBLESTONE), "CSC", "SWS", "CSC");

        provider.addItemRecipe(consumer, Ref.ID, "piston_sticky","gears", "has_stone", provider.hasSafeItem(Blocks.PISTON), Blocks.STICKY_PISTON, of('S', GregTechData.StickyResin, 'P', Blocks.PISTON), "S", "P");

        provider.addItemRecipe(consumer, "magnetic_rods", "has_redstone", provider.hasSafeItem(Tags.Items.DUSTS_REDSTONE), ROD.get(IronMagnetic),
                of('R', Tags.Items.DUSTS_REDSTONE, 'S', TagUtils.getForgeItemTag("rods/iron")), " R ", "RSR", " R ");

        provider.addItemRecipe(consumer, "magnetic_rods", "has_redstone", provider.hasSafeItem(Tags.Items.DUSTS_REDSTONE), ROD.get(Neodymium),
                of('R', Tags.Items.DUSTS_REDSTONE, 'S', TagUtils.getForgeItemTag("rods/neodymium")), " R ", "RSR", " R ");
    }
}
