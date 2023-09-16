package muramasa.gregtech.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.tool.AntimatterItemTier;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.MaterialTool;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.data.ToolTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

import static muramasa.gregtech.data.Materials.*;

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

    public Material getRodMaterial(){
        if (type == ToolTypes.TURBINE_ROTOR) return Titanium;
        if (type == ToolTypes.LARGE_TURBINE_ROTOR) return TungstenSteel;
        if (type == ToolTypes.HUGE_TURBINE_ROTOR) return Americium;
        return Magnalium;
    }

    @Override
    public void onGenericAddInformation(ItemStack stack, List<Component> tooltip, TooltipFlag flag) {
        super.onGenericAddInformation(stack, tooltip, flag);
        tooltip.add(Utils.translatable("gti.rotor.tooltip.efficiency", Utils.literal("" + getEfficiency()).withStyle(ChatFormatting.BLUE)));
        tooltip.add(Utils.translatable("gti.rotor.tooltip.steam_flow", Utils.literal("" + Math.max(Float.MIN_NORMAL, getSpeed() * 1000)).withStyle(ChatFormatting.LIGHT_PURPLE)));
        tooltip.add(Utils.translatable("gti.rotor.tooltip.gas_flow", Utils.literal("" + Math.max(Float.MIN_NORMAL, getSpeed() * 50)).withStyle(ChatFormatting.LIGHT_PURPLE)));
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
