package muramasa.gregtech.block;

import muramasa.antimatter.block.BlockBasicSlab;
import muramasa.antimatter.registration.IColorHandler;
import muramasa.antimatter.texture.Texture;
import muramasa.gregtech.GTIRef;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

public class BlockAsphaltSlab extends BlockBasicSlab implements IColorHandler {
    final int color;
    public BlockAsphaltSlab(String domain, String id, int color) {
        super(domain, id, Properties.of(Material.STONE).strength(1.0f, 1.0f).sound(SoundType.STONE));
        this.color = color;
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        return color;
    }

    @Override
    public int getBlockColor(BlockState state, @Nullable BlockGetter world, @Nullable BlockPos pos, int i) {
        return color;
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(GTIRef.ID, "block/stone/asphalt")};
    }
}
