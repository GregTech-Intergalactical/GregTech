package muramasa.gregtech.block.tree;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.block.BlockPropertiesHelper;
import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.registration.IModelProvider;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import net.minecraft.world.level.block.LeavesBlock;

public class BlockRubberLeaves extends LeavesBlock implements IAntimatterObject, IModelProvider, ITextureProvider {

    protected final String domain, id;

    public BlockRubberLeaves(String domain, String id) {
        super(BlockPropertiesHelper.leaves());
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
        return new Texture[] { new Texture(domain, "block/tree/" + id) };
    }
}
