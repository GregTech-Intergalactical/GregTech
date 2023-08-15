package muramasa.gregtech.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.tool.AntimatterItemTier;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.MaterialTool;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemTurbineRotor extends MaterialTool {
    public ItemTurbineRotor(String domain, AntimatterToolType type, AntimatterItemTier tier, Properties properties) {
        super(domain, type, tier, properties);
    }

    public int getEfficiency(){
        return (int) (50.0F + (10.0F * (type.getBaseAttackDamage() + itemTier.getAttackDamageBonus())));
    }

    @Override
    public void onGenericAddInformation(ItemStack stack, List<Component> tooltip, TooltipFlag flag) {
        super.onGenericAddInformation(stack, tooltip, flag);
        tooltip.add(new TranslatableComponent("gti.rotor.tooltip.efficiency", new TextComponent("" + getEfficiency()).withStyle(ChatFormatting.BLUE)));
    }

    @Override
    public ItemStack resolveStack(Material primary, Material secondary, long startingEnergy, long maxEnergy) {
        return new ItemStack(this);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slotType, ItemStack stack) {
        return HashMultimap.create();
    }
}
