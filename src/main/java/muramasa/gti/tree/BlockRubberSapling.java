package muramasa.gti.tree;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemModelProvider;
import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.registration.IModelProvider;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.state.StateContainer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class BlockRubberSapling extends SaplingBlock implements IGrowable, IAntimatterObject, IModelProvider, ITextureProvider {

    final static RubberTree TREE = new RubberTree();

    protected String domain;

    public BlockRubberSapling(String domain) {
        super(TREE, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.PLANT));
        setRegistryName(domain, getId());
        this.domain = domain;
        AntimatterAPI.register(BlockRubberSapling.class, this);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(STAGE);
    }

    @Override
    public String getId() {
        return "rubber_sapling";
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(domain, "block/tree/" + getId())};
    }

    @Override
    public void onBlockModelBuild(Block block, AntimatterBlockStateProvider prov) {
        prov.state(block, prov.getBuilder(block).parent(prov.existing("minecraft", "block/cross")).texture("cross", getTextures()[0]));
    }

    @Override
    public void onItemModelBuild(IItemProvider item, AntimatterItemModelProvider prov) {
        prov.getBuilder(item).parent(prov.getExistingFile(new ResourceLocation("item/generated"))).texture("layer0", getTextures()[0]);
    }

    @Override
    public void func_226942_a_(ServerWorld world, BlockPos pos, BlockState state, Random random) {
        if (RubberTreeWorldGen.getValidBiomes().test(world.getBiome(pos))) super.func_226942_a_(world, pos, state, random);
    }
}
