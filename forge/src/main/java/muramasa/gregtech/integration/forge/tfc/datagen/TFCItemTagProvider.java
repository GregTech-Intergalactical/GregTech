package muramasa.gregtech.integration.forge.tfc.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemTagProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.material.data.ToolData;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.integration.forge.tfc.TFCRegistrar;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import static muramasa.antimatter.data.AntimatterDefaultTools.*;
import static muramasa.antimatter.data.AntimatterMaterials.Copper;
import static muramasa.gregtech.data.Materials.*;

public class TFCItemTagProvider extends AntimatterItemTagProvider {
    public TFCItemTagProvider(String providerDomain, String providerName, boolean replace, AntimatterBlockTagProvider p) {
        super(providerDomain, providerName, replace, p);
    }

    @Override
    protected void processTags(String domain) {
        super.processTags(domain);
        this.tag(TagUtils.getItemTag(new ResourceLocation(Ref.MOD_TFC, "swords"))).addTag(SWORD.getTag());
        this.tag(TagUtils.getItemTag(new ResourceLocation(Ref.MOD_TFC, "pickaxes"))).addTag(PICKAXE.getTag());
        this.tag(TagUtils.getItemTag(new ResourceLocation(Ref.MOD_TFC, "shovels"))).addTag(SHOVEL.getTag());
        this.tag(TagUtils.getItemTag(new ResourceLocation(Ref.MOD_TFC, "axes"))).addTag(AXE.getTag());
        this.tag(TagUtils.getItemTag(new ResourceLocation(Ref.MOD_TFC, "hoes"))).addTag(HOE.getTag());
        this.tag(TagUtils.getItemTag(new ResourceLocation(Ref.MOD_TFC, "saws"))).addTag(SAW.getTag());
        this.tag(TagUtils.getItemTag(new ResourceLocation(Ref.MOD_TFC, "knives"))).addTag(KNIFE.getTag());
        Material[] tfcTools = new Material[]{BismuthBronze, BlackBronze, Bronze, Copper, WroughtIron, Steel, BlackSteel, BlueSteel, RedSteel};
        AntimatterAPI.all(AntimatterToolType.class).forEach(t -> {
            if (t.hasOriginalTag()) {
                tag(TagUtils.getItemTag(new ResourceLocation(Ref.MOD_TFC, "usable_on_tool_rack"))).addTag(t.getTag());
            }
            if (!t.isSimple()) return;
            for (Material tfcTool : tfcTools) {
                ToolData data = MaterialTags.TOOLS.get(tfcTool);
                if (data != null && data.toolTypes().contains(t)) {
                    tag(TagUtils.getItemTag(new ResourceLocation(Ref.MOD_TFC, "metal_item/" + tfcTool.getId() + "_tools"))).add(t.getToolItem(tfcTool));
                    tag(TagUtils.getItemTag(new ResourceLocation(Ref.MOD_TFC, "metal_item/" + tfcTool.getId()))).add(t.getToolItem(tfcTool));
                    if (t == AXE || t == SWORD){
                        tag(TagUtils.getItemTag(new ResourceLocation(Ref.MOD_TFC, "mob_mainhand_weapons"))).add(t.getToolItem(tfcTool));
                    }
                }
            }

        });
        /*for (Material material : TFCRegistrar.array) {
            this.tag(TagUtils.getItemTag(new ResourceLocation(Ref.MOD_TFC, "ore_pieces"))).add(GregTech.get(Item.class, "poor_" + material.getId()), GregTech.get(Item.class, "normal_" + material.getId()), GregTech.get(Item.class, "rich_" + material.getId()));
        }*/
    }
}
