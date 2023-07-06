package muramasa.gregtech.block;

import muramasa.antimatter.block.BlockFakeTile;
import muramasa.antimatter.texture.Texture;

public class BlockFakeCasing extends BlockFakeTile {
    public BlockFakeCasing(String domain, String id, Properties properties) {
        super(domain, id, properties);
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(getRegistryName().getNamespace(), "block/casing/" + getRegistryName().getPath().replaceAll("casing_", ""))};
    }
}
