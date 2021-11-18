package muramasa.gti.tree;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.registration.IModelProvider;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockRubberLeaves extends LeavesBlock implements IAntimatterObject, IModelProvider, ITextureProvider {

    protected String domain, id;

    public BlockRubberLeaves(String domain, String id) {
        super(Block.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion());
        this.domain = domain;
        this.id = id;
        AntimatterAPI.register(BlockRubberLeaves.class, this);
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(getRegistryName().getNamespace(), "block/tree/" + getId())};
    }
}
