package muramasa.gregtech.items;

import muramasa.antimatter.Ref;
import muramasa.antimatter.item.ItemBasic;
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

public class ItemBreederRod extends ItemBasic<ItemBreederRod> implements IItemReactorRod {
    private final Material material;
    private final Supplier<Item> enrichedRod;
    private final int loss;
    private final long needed;

    public ItemBreederRod(String domain, Material material, Supplier<Item> enrichedRod, int loss, long needed) {
        super(domain, material.getId() + "_breeder_rod", new Properties().stacksTo(16).tab(Ref.TAB_ITEMS));
        this.material = material;
        this.enrichedRod = enrichedRod;
        this.loss = loss;
        this.needed = needed;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        tooltipComponents.add(Utils.translatable("tooltip.gti.breeder_rod.0", Utils.translatable("tooltip.gti.breeder_rod.enriched").withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.AQUA));
        tooltipComponents.add(Utils.translatable("tooltip.gti.breeder_rod.1").withStyle(ChatFormatting.AQUA));
        tooltipComponents.add(Utils.translatable("tooltip.gti.breeder_rod.2", Utils.translatable("tooltip.gti.nuclear_rod.moderated.1").withStyle(ChatFormatting.RED)).withStyle(ChatFormatting.AQUA));
        tooltipComponents.add(Utils.translatable("tooltip.gti.breeder_rod.3", Utils.translatable("tooltip.gti.breeder_rod.loss").withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.AQUA));
        tooltipComponents.add(Utils.translatable("tooltip.gti.breeder_rod.4").withStyle(ChatFormatting.AQUA));
        tooltipComponents.add(Utils.translatable("tooltip.gti.breeder_rod.5").withStyle(ChatFormatting.AQUA));
        tooltipComponents.add(Utils.translatable("tooltip.gti.breeder_rod.6", enrichedRod.get().getDefaultInstance().getHoverName().copy().withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GREEN));
        long needed = stack.getTag() != null && stack.getTag().contains("neededNeutrons") ? stack.getTag().getLong("neededNeutrons") : this.needed;
        tooltipComponents.add(Utils.translatable("tooltip.gti.breeder_rod.7", Utils.literal("" + needed).withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.breeder_rod.neutrons").withStyle(ChatFormatting.LIGHT_PURPLE)).withStyle(ChatFormatting.AQUA));
        tooltipComponents.add(Utils.translatable("tooltip.gti.breeder_rod.8", Utils.literal("" + loss).withStyle(ChatFormatting.WHITE), Utils.translatable("tooltip.gti.breeder_rod.neutrons").withStyle(ChatFormatting.LIGHT_PURPLE)).withStyle(ChatFormatting.AQUA));
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
        long needed = stack.getTag() != null && stack.getTag().contains("neededNeutrons") ? stack.getTag().getLong("neededNeutrons") : this.needed;
        needed -= reactor.oNeutronCounts[slot];
        if (needed <= 0){
            reactor.setRod(slot, new ItemStack(enrichedRod.get()));
        } else {
            stack.getOrCreateTag().putLong("neededNeutrons", needed);
        }
        return true;
    }

    @Override
    public int getReactorRodNeutronReflection(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack, int neutrons, boolean moderated) {
        if (!moderated && neutrons > loss) reactor.mNeutronCounts[slot] += neutrons - loss;
        return 0;
    }

    @Override
    public int getReactorRodNeutronMaximum(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack) {
        return 0;
    }
}
