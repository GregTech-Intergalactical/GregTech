package muramasa.gregtech.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.tool.AntimatterItemTier;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.MaterialTool;
import muramasa.gregtech.data.ToolTypes;
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

    public float getEfficiency(){
        return 60.0F + (10.0F * (type.getBaseAttackDamage() + itemTier.getAttackDamageBonus()));
    }

    public int speedMultiplier(){
        if (type == ToolTypes.TURBINE_ROTOR) return 2;
        if (type == ToolTypes.LARGE_TURBINE_ROTOR) return 3;
        if (type == ToolTypes.HUGE_TURBINE_ROTOR) return 4;
        return 1;
    }

    public float getSpeed(){
        return itemTier.getSpeed() * speedMultiplier();
    }

    @Override
    public void onGenericAddInformation(ItemStack stack, List<Component> tooltip, TooltipFlag flag) {
        super.onGenericAddInformation(stack, tooltip, flag);
        tooltip.add(new TranslatableComponent("gti.rotor.tooltip.efficiency", new TextComponent("" + getEfficiency()).withStyle(ChatFormatting.BLUE)));
        tooltip.add(new TranslatableComponent("gti.rotor.tooltip.steam_flow", new TextComponent("" + Math.max(Float.MIN_NORMAL, getSpeed() * 1000)).withStyle(ChatFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslatableComponent("gti.rotor.tooltip.gas_flow", new TextComponent("" + Math.max(Float.MIN_NORMAL, getSpeed() * 50)).withStyle(ChatFormatting.LIGHT_PURPLE)));
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
