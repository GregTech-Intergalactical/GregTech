package muramasa.gregtech.integration;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterStoneTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.GTIRef;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

import static muramasa.antimatter.data.AntimatterMaterialTypes.ORE;
import static muramasa.antimatter.data.AntimatterMaterials.Diamond;
import static muramasa.antimatter.data.AntimatterMaterials.Iron;

public class SpaceModRegistrar extends AntimatterMod {
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
            String block = AntimatterAPI.isModLoaded("ad_astra") ? "block" : "blocks";
            AntimatterAPI.register(StoneType.class, new StoneType(GTIRef.ID, "moon_sand", Material.NULL, new Texture(getMod(), block + "/moon_sand"), SoundType.SAND, false).setState(getSpaceBlock("moon_sand")).setSandLike(true));
            var moonStone = AntimatterAPI.register(StoneType.class, new StoneType(GTIRef.ID, "moon_stone", Material.NULL, new Texture(getMod(), block + "/moon_stone"), SoundType.STONE, false).setState(getSpaceBlock("moon_stone")));
            AntimatterAPI.register(StoneType.class, new StoneType(GTIRef.ID, "mars_sand", Material.NULL, new Texture(getMod(), block + "/mars_sand"), SoundType.SAND, false).setState(getSpaceBlock("mars_sand")).setSandLike(true));
            var marsStone = AntimatterAPI.register(StoneType.class, new StoneType(GTIRef.ID, "mars_stone", Material.NULL, new Texture(getMod(), block + "/mars_stone"), SoundType.STONE, false).setState(getSpaceBlock("mars_stone")));
            ORE.replacement(Iron, moonStone, () -> getSpaceBlock("moon_iron_ore").asItem());
            ORE.replacement(Iron, marsStone, () -> getSpaceBlock("mars_iron_ore").asItem());
            ORE.replacement(Diamond, marsStone, () -> getSpaceBlock("mars_diamond_ore").asItem());
        }
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
}
