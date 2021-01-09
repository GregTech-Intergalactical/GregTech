package muramasa.gti.block;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Data;
import muramasa.antimatter.datagen.builder.AntimatterBlockModelBuilder;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.dynamic.BlockDynamic;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class BlockCasing extends BlockDynamic {

    public BlockCasing(String domain, String id, Block.Properties properties) {
        super(domain, id, properties);
    }

    public BlockCasing(String domain, String id) {
        this(domain, id, Block.Properties.create(Material.IRON).hardnessAndResistance(1.0f, 10.0f).sound(SoundType.METAL));
    }

    @Nullable
    @Override
    public ToolType getHarvestTool(BlockState state) {
        return Data.WRENCH.getToolType();
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(getRegistryName().getNamespace(), "block/casing/" + getRegistryName().getPath().replaceAll("casing_", ""))};
    }

    @Override
    public void onBlockModelBuild(Block block, AntimatterBlockStateProvider prov) {
        AntimatterBlockModelBuilder builder = buildBlock(block,prov);
        if (builder != null) {
            prov.state(block, builder);
        } else {
            super.onBlockModelBuild(block, prov);
        }
    }
    //Hierarchial block builder.
    protected AntimatterBlockModelBuilder buildBlock(Block block, AntimatterBlockStateProvider prov) {
        return null;
    }
}