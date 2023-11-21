package muramasa.gregtech.integration;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterStoneTypes;
import muramasa.antimatter.event.MaterialEvent;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.TextureSet;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.GTIRef;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Diamond;
import static muramasa.antimatter.data.AntimatterMaterials.Iron;

public class SpaceModRegistrar extends AntimatterMod {
    public static Material Desh;
    public SpaceModRegistrar(){
        if (AntimatterPlatformUtils.isFabric()) {
            onRegistrarInit();
        }
    }

    @Override
    public String getId() {
        return "gt_space";
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {
        if (event == RegistrationEvent.DATA_INIT){
            Desh = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "desh", 0x282828, TextureSet.DULL));
            String block = AntimatterAPI.isModLoaded("ad_astra") ? "block" : "blocks";
            AntimatterAPI.register(StoneType.class, new StoneType(GTIRef.ID, "moon_sand", Material.NULL, new Texture(getMod(), block + "/moon_sand"), SoundType.SAND, false).setState(getSpaceBlock("moon_sand")).setSandLike(true));
            var moonStone = AntimatterAPI.register(StoneType.class, new StoneType(GTIRef.ID, "moon_stone", Material.NULL, new Texture(getMod(), block + "/moon_stone"), SoundType.STONE, false).setState(getSpaceBlock("moon_stone")));
            AntimatterAPI.register(StoneType.class, new StoneType(GTIRef.ID, "mars_sand", Material.NULL, new Texture(getMod(), block + "/mars_sand"), SoundType.SAND, false).setState(getSpaceBlock("mars_sand")).setSandLike(true));
            var marsStone = AntimatterAPI.register(StoneType.class, new StoneType(GTIRef.ID, "mars_stone", Material.NULL, new Texture(getMod(), block + "/mars_stone"), SoundType.STONE, false).setState(getSpaceBlock("mars_stone")));
            ORE.replacement(Iron, moonStone, () -> getSpaceBlock("moon_iron_ore").asItem());
            ORE.replacement(Iron, marsStone, () -> getSpaceBlock("mars_iron_ore").asItem());
            ORE.replacement(Diamond, marsStone, () -> getSpaceBlock("mars_diamond_ore").asItem());
            INGOT.replacement(Desh, () -> getSpaceItem("desh_ingot"));
            PLATE.replacement(Desh, () -> getSpaceItem("desh_plate"));
            NUGGET.replacement(Desh, () -> getSpaceItem("desh_nugget"));
            RAW_ORE.replacement(Desh, () -> getSpaceItem("raw_desh"));
            BLOCK.replacement(Desh, () -> getSpaceBlock("desh_block").asItem());
            RAW_ORE_BLOCK.replacement(Desh, () -> getSpaceBlock("raw_desh_block").asItem());
            ORE.replacement(Desh, moonStone, () -> getSpaceBlock("moon_desh_ore").asItem());
        }
    }

    @Override
    public void onMaterialEvent(MaterialEvent event) {
        super.onMaterialEvent(event);
        event.setMaterial(Desh).asOre().asMetal().tool().toolQuality(3).toolSpeed(4.0f).toolDurability(1280).toolDamage(2.5f).handleMaterial(Desh).build();
    }

    @Override
    public boolean isEnabled() {
        return (AntimatterAPI.isModLoaded("ad_astra") || AntimatterAPI.isModLoaded("beyond_earth")) && !AntimatterAPI.isModLoaded(Ref.MOD_GC);
    }

    private static String getMod(){
        return AntimatterAPI.isModLoaded("ad_astra") ? "ad_astra" : "beyond_earth";
    }

    public static Block getSpaceBlock(String id){
        return AntimatterPlatformUtils.getBlockFromId(getMod(), id);
    }
    public static Item getSpaceItem(String id){
        return AntimatterPlatformUtils.getItemFromID(getMod(), id);
    }
}
