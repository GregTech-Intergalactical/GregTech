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

    protected String domain;

    public BlockRubberLeaves(String domain) {
        super(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid());
        setRegistryName(domain, getId());
        this.domain = domain;
        AntimatterAPI.register(BlockRubberLeaves.class,this);
    }

    @Override
    public String getId() {
        return "rubber_leaves";
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(domain, "block/tree/" + getId())};
    }
}
