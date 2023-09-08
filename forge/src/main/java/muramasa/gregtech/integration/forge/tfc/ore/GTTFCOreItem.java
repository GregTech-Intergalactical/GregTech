package muramasa.gregtech.integration.forge.tfc.ore;

import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.texture.Texture;

public class GTTFCOreItem extends ItemBasic<GTTFCOreItem> {
    public GTTFCOreItem(String domain, String id) {
        super(domain, id);
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(domain, "item/tfc/ore/" + getId())};
    }
}
