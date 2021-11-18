package muramasa.gti.block;

import muramasa.antimatter.Data;
import muramasa.antimatter.block.BlockBasic;
import muramasa.antimatter.texture.Texture;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class BlockCoil extends BlockBasic {

    protected int heatCapacity;

    public BlockCoil(String domain, String id, int heatCapacity, Block.Properties properties) {
        super(domain, id, properties);
        this.heatCapacity = heatCapacity;
    }

    public BlockCoil(String domain, String id, int heatCapacity) {
        this(domain, id, heatCapacity, Block.Properties.of(Material.METAL).strength(1.0f, 10.0f).sound(SoundType.METAL));
    }

    public int getHeatCapacity() {
        return heatCapacity;
    }

    @Nullable
    @Override
    public ToolType getHarvestTool(BlockState state) {
        return Data.WRENCH.getToolType();
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(getRegistryName().getNamespace(), "block/coil/" + getRegistryName().getPath().replaceAll("coil_", ""))};
    }
}
