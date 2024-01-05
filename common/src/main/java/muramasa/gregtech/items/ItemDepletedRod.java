package muramasa.gregtech.items;

import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.registration.IColorHandler;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemDepletedRod extends ItemBasic<ItemDepletedRod> implements IColorHandler {
    private final Material material;

    public ItemDepletedRod(String domain, Material material) {
        super(domain, material.getId() + "_depleted_rod");
        this.material = material;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        tooltipComponents.add(Utils.translatable("tooltip.gti.depleted_rod.0", Utils.translatable("tooltip.gti.depleted_rod.depleted").withStyle(ChatFormatting.RED)).withStyle(ChatFormatting.AQUA));
        tooltipComponents.add(Utils.translatable("tooltip.gti.depleted_rod.1").withStyle(ChatFormatting.AQUA));
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
        return IColorHandler.super.getItemColor(stack, block, i);
    }

    public Material getMaterial() {
        return material;
    }
}
