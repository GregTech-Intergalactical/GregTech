package muramasa.gregtech.items;

import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.registration.IColorHandler;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.data.GregTechItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemComponentRod extends ItemBasic<ItemComponentRod> implements IColorHandler {
    private final Material material;
    private final int tooltips;

    public ItemComponentRod(String domain, String id, Material material, int tooltips) {
        super(domain, id);
        this.material = material;
        this.tooltips = tooltips;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        for (int i = 0; i < tooltips; i++) {
            tooltipComponents.add(Utils.translatable("tooltip." + getDomain() + "." + getId().replace("/", ".") + "." + i).withStyle(ChatFormatting.AQUA));
        }
    }

    @Override
    public Texture[] getTextures() {
        if (this == GregTechItems.EmptyNuclearFuelRod){
            return new Texture[]{new Texture(GTIRef.ID, "item/basic/empty_nuclear_fuel_rod")};
        }
        return new Texture[]{new Texture(GTIRef.ID, "item/basic/nuclear_fuel_rod"), new Texture(GTIRef.ID, "item/basic/empty_nuclear_fuel_rod")};
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        if (i == 0 && material != Material.NULL){
            return material.getRGB();
        }
        return IColorHandler.super.getItemColor(stack, block, i);
    }
}
