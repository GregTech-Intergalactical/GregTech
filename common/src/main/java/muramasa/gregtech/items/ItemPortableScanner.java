package muramasa.gregtech.items;

import muramasa.antimatter.Ref;
import muramasa.antimatter.capability.energy.ItemEnergyHandler;
import muramasa.antimatter.item.ScannerItem;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.GTIRef;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tesseract.TesseractCapUtils;
import tesseract.api.context.TesseractItemContext;
import tesseract.api.gt.IEnergyHandlerItem;
import tesseract.api.gt.IEnergyItem;
import tesseract.api.gt.IGTNode;

import java.util.List;

public class ItemPortableScanner extends ScannerItem implements IEnergyItem {
    public ItemPortableScanner() {
        super(GTIRef.ID, "portable_scanner", false, new Properties().stacksTo(1).tab(Ref.TAB_ITEMS));
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        long energy = context.getItemInHand().getOrCreateTagElement(Ref.TAG_ITEM_ENERGY_DATA).getLong(Ref.KEY_ITEM_ENERGY);
        if (energy < 2200) return InteractionResult.PASS;
        InteractionResult result = super.useOn(context);
        if (result == InteractionResult.SUCCESS){
            TesseractCapUtils.getEnergyHandlerItem(context.getItemInHand()).ifPresent(i -> i.extractEu(2200, false));
        }
        return super.useOn(context);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        long energy = stack.getOrCreateTagElement(Ref.TAG_ITEM_ENERGY_DATA).getLong(Ref.KEY_ITEM_ENERGY);
        if (energy <= 0) return super.getBarColor(stack);
        return 0x00BFFF;
        //return TesseractCapUtils.getEnergyHandlerItem(stack).map(IEnergyHandler::getEnergy).filter(l -> l <= 0).map(l -> super.getBarColor(stack)).orElse(0x00BFFF);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return (int)(13.0f* (stack.getOrCreateTagElement(Ref.TAG_ITEM_ENERGY_DATA).getLong(Ref.KEY_ITEM_ENERGY) / (double) 400000));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        long energy = TesseractCapUtils.getEnergyHandlerItem(stack).map(IGTNode::getEnergy).orElse(0L);
        tooltip.add(Utils.translatable("item.charge").append(": ").append(Utils.literal(energy + "/" + 400000).withStyle(energy == 0 ? ChatFormatting.RED : ChatFormatting.GREEN)).append(" (MV)"));
        super.appendHoverText(stack, worldIn, tooltip, flag);
    }

    @Override
    public IEnergyHandlerItem createEnergyHandler(TesseractItemContext context) {
        return new ItemEnergyHandler(context, 400000, 128, 0, 1, 0);
    }
}
