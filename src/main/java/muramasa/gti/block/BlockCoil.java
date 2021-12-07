package muramasa.gti.block;

import muramasa.antimatter.block.BlockBasic;
import muramasa.antimatter.texture.Texture;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

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

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(getRegistryName().getNamespace(), "block/coil/" + getRegistryName().getPath().replaceAll("coil_", ""))};
    }
}
