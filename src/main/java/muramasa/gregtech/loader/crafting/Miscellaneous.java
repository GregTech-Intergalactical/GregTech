package muramasa.gregtech.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialItem;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.Ref;
import muramasa.gregtech.data.Materials;
import net.minecraft.client.Minecraft;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraftforge.common.Tags;

import java.util.Arrays;
import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.TierMaps.*;

public class Miscellaneous {
  public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
      provider.addItemRecipe(output, Ref.ID, "plantball", "misc", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), PlantBall, of(
              'C', Tags.Items.CROPS
      ), "CCC", "C C", "CCC");

      provider.addItemRecipe(output, Ref.ID, "plantball2", "misc", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), PlantBall, of(
              'S', Tags.Items.SEEDS
      ), "SSS", "S S", "SSS");

      provider.addStackRecipe(output, Ref.ID, "stickyresin", "misc", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), new ItemStack(StickyResin,8), of(
            'P', PlantBall,
            'D', DUST.get(RawRubber)
      ), "PPP", "PDP", "PPP");
  }
}
