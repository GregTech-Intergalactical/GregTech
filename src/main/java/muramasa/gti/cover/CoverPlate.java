package muramasa.gti.cover;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.cover.Cover;
import muramasa.antimatter.cover.CoverMaterial;
import muramasa.antimatter.items.MaterialItem;
import muramasa.antimatter.materials.Material;
import muramasa.antimatter.materials.MaterialType;
import muramasa.antimatter.materials.TextureSet;
import muramasa.antimatter.texture.Texture;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class CoverPlate extends CoverMaterial {

    private MaterialType<?> type;
    private Material material;

    public CoverPlate() {
        //NOOP
    }

    public CoverPlate(MaterialType<?> type, Material material) {
        this.type = type;
        this.material = material;
    }

    @Override
    public String getId() {
        return "plate";
    }

    public MaterialType<?> getType() {
        return type;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public ItemStack getDroppedStack() {
        return MaterialType.PLATE.get(material, 1);
    }

    @Override
    public Cover onPlace(ItemStack stack) {
        Material material = MaterialItem.getMaterial(stack);
        if (material != null) return new CoverPlate(MaterialType.BLOCK, material);
        return super.onPlace(stack);
    }

    @Override
    public Texture[] getTextures() {
        ArrayList<Texture> textures = new ArrayList<>();
        for (TextureSet set : AntimatterAPI.all(TextureSet.class)) {
            //TODO fix domain
            textures.addAll(Arrays.asList(set.getTextures(MaterialType.BLOCK)));
        }
        return textures.toArray(new Texture[0]);
    }
}
