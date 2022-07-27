package muramasa.gregtech.block.tree;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemModelProvider;
import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.registration.IModelProvider;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.ItemLike;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.server.level.ServerLevel;

import java.util.Random;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockRubberSapling extends SaplingBlock implements IAntimatterObject, IModelProvider, ITextureProvider {

    protected final String domain, id;

    public BlockRubberSapling(String domain, String id) {
        super(new RubberTree(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().strength(0.0F).sound(SoundType.GRASS).instabreak());
        this.domain = domain;
        this.id = id;
        AntimatterAPI.register(BlockRubberSapling.class, this);
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STAGE);
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[] { new Texture(domain, "block/tree/" + id) };
    }

    @Override
    public void onBlockModelBuild(Block block, AntimatterBlockStateProvider prov) {
        prov.state(block, prov.getBuilder(block).parent(prov.existing("minecraft", "block/cross")).texture("cross", getTextures()[0]));
    }

    @Override
    public void onItemModelBuild(ItemLike item, AntimatterItemModelProvider prov) {
        prov.getBuilder(item).parent(new ResourceLocation("item/generated")).texture("layer0", getTextures()[0]);
    }

    @Override
    public void advanceTree(ServerLevel world, BlockPos pos, BlockState state, Random random) {
        if (Biome.getBiomeCategory(world.getBiome(pos)) != Biome.BiomeCategory.NETHER && Biome.getBiomeCategory(world.getBiome(pos)) != Biome.BiomeCategory.THEEND)
            super.advanceTree(world, pos, state, random);
    }
}
