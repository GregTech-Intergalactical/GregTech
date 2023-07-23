package muramasa.gregtech.block;

import muramasa.antimatter.block.BlockBasic;
import muramasa.antimatter.texture.Texture;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockCoil extends BlockBasic {

    protected int heatCapacity;
    protected CoilData coilData;

    public BlockCoil(String domain, String id, CoilData coilData, Block.Properties properties) {
        super(domain, id, properties);
        this.coilData = coilData;
        this.heatCapacity = heatCapacity;
    }

    public BlockCoil(String domain, String id, CoilData coilData) {
        this(domain, id, coilData, Block.Properties.of(Material.METAL).strength(1.0f, 10.0f).sound(SoundType.METAL));
    }

    public int getHeatCapacity() {
        return heatCapacity;
    }

    public CoilData getCoilData() {
        return coilData;
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(getRegistryName().getNamespace(), "block/coil/" + getRegistryName().getPath().replaceAll("coil_", ""))};
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, @Nullable BlockGetter p_49817_, List<Component> tooltip, TooltipFlag p_49819_) {
        super.appendHoverText(p_49816_, p_49817_, tooltip, p_49819_);
        tooltip.add(new TranslatableComponent("antimatter.tooltip.heat_capacity").append(": ").append("" + this.heatCapacity));
        tooltip.add(new TranslatableComponent("antimatter.tooltip.heat_capacity_total").append(": ").append("" + this.heatCapacity*16));
    }

    public record CoilData(int heat, float percentage){}
}
