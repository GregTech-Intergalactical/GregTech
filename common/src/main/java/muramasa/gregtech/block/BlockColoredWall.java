package muramasa.gregtech.block;

import muramasa.antimatter.Ref;
import muramasa.antimatter.block.BlockFakeTile;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.registration.IColorHandler;
import muramasa.antimatter.texture.Texture;
import muramasa.gregtech.GTIRef;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import static muramasa.antimatter.data.AntimatterMaterials.Wood;

public class BlockColoredWall  extends BlockFakeTile implements IColorHandler {
    final Material material;
    public BlockColoredWall(String domain, Material material, Properties properties) {
        super(domain, material.getId() + "_wall", properties);
        this.material = material;
    }

    public BlockColoredWall(String domain, Material material){
        this(domain, material, Block.Properties.of(net.minecraft.world.level.material.Material.METAL).strength(1.0f, 10.0f).sound(SoundType.METAL).requiresCorrectToolForDrops());
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public Texture[] getTextures() {
        Texture side = material == Wood ? new Texture(GTIRef.ID, "block/casing/wall/wood") : new Texture(GTIRef.ID, "block/casing/wall/metal");
        Texture overlay = new Texture(GTIRef.ID, "block/machine/empty");
        Texture sideOverlay = material == Wood ? new Texture(GTIRef.ID, "block/casing/wall/wood_overlay_side") : overlay;
        return new Texture[]{side, side, side, side, side, side, overlay, overlay, sideOverlay, sideOverlay, sideOverlay, sideOverlay};
    }

    @Override
    public int getBlockColor(BlockState state, @Nullable BlockGetter world, @Nullable BlockPos pos, int i) {
        return i == 0 ? material.getRGB() : -1;
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        return i == 0 ? material.getRGB() : -1;
    }
}
