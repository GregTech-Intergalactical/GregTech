package muramasa.gregtech.items;

import muramasa.antimatter.Ref;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.IMaterialObject;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.blockentity.single.BlockEntityNuclearReactorCore;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class ItemEnrichedRod extends ItemBasic<ItemEnrichedRod> implements IItemReactorRod, IMaterialObject {
    private final Material material;
    private final Supplier<Item> breederRod;

    public ItemEnrichedRod(String domain, Material material, Supplier<Item> breederRod) {
        super(domain, material.getId() + "_enriched_rod", new Properties().stacksTo(16).tab(Ref.TAB_ITEMS));
        this.material = material;
        this.breederRod = breederRod;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        tooltipComponents.add(Utils.translatable("tooltip.gti.enriched_rod.0").withStyle(ChatFormatting.AQUA));
        tooltipComponents.add(Utils.translatable("tooltip.gti.depleted_rod.1").withStyle(ChatFormatting.AQUA));
        tooltipComponents.add(Utils.translatable("tooltip.gti.enriched_rod.1", breederRod.get().getDefaultInstance().getHoverName()).withStyle(ChatFormatting.GREEN));
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(GTIRef.ID, "item/basic/nuclear_fuel_rod"), new Texture(GTIRef.ID, "item/basic/empty_nuclear_fuel_rod")};
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        if (i == 0 && material != Material.NULL){
            return material.getRGB();
        }
        return IItemReactorRod.super.getItemColor(stack, block, i);
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public boolean isReactorRod(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isModerated(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack) {
        return false;
    }

    @Override
    public void updateModeration(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack) {

    }

    @Override
    public int getReactorRodNeutronEmission(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack) {
        return 0;
    }

    @Override
    public boolean getReactorRodNeutronReaction(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack) {
        reactor.heatHandler.ifPresent(h -> h.insertInternal(reactor.oNeutronCounts[slot] / 2, false));
        return true;
    }

    @Override
    public int getReactorRodNeutronReflection(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack, int neutrons, boolean moderated) {
        reactor.mNeutronCounts[slot] += neutrons;
        return 0;
    }

    @Override
    public int getReactorRodNeutronMaximum(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack) {
        return 0;
    }
}
