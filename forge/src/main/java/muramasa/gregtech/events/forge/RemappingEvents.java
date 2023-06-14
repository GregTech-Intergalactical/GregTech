package muramasa.gregtech.events.forge;

import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import muramasa.antimatter.Antimatter;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.fluid.AntimatterFluid;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.ore.BlockOre;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.data.Materials;
import muramasa.gregtech.machine.BlockEntityHatchHeat;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tesseract.api.forge.TesseractCaps;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

import static muramasa.antimatter.material.Material.NULL;

public class RemappingEvents {
    UUID bearUUID = UUID.fromString("1964e3d1-6500-40e7-9ff2-e6161d41a8c2");

    private static final Map<String, String> REMAPPING_MAP = new Object2ObjectArrayMap<>();

    public static void init(){
        REMAPPING_MAP.put("coke_oven_bronze", "coke_oven");
        REMAPPING_MAP.put("primitive_blast_furnace_bronze", "primitive_blast_furnace");
        REMAPPING_MAP.put("pulverizer_lv", "macerator_lv");
        REMAPPING_MAP.put("pulverizer_mv", "macerator_mv");
        REMAPPING_MAP.put("pulverizer_hv", "macerator_hv");
        REMAPPING_MAP.put("pulverizer_ev", "macerator_ev");
        REMAPPING_MAP.put("pulverizer_iv", "macerator_iv");
    }

    @SubscribeEvent
    public static void rightClickEntity(PlayerInteractEvent.EntityInteract event){
        /*if (event.getTarget() instanceof Player targetPlayer){

        }*/
        /*ItemStack handStack = event.getPlayer().getItemInHand(event.getHand());
        if(handStack.is(AntimatterDefaultTools.WRENCH.getTag())){
            Random random = event.getPlayer().getRandom();

            float x = random.nextInt(10 + 10) - 10;

            float y = random.nextInt(90) - 45;

            float yaw = event.getPlayer().getYRot();

            Antimatter.LOGGER.info("yaw: " + yaw);
            Antimatter.LOGGER.info("pitch: " + event.getPlayer().getXRot());
            event.getPlayer().moveTo(event.getPlayer().getOnPos().above(), yaw + y, 180);
        }
        if (handStack.is(AntimatterDefaultTools.HAMMER.getTag())){
            event.getPlayer().setSwimming(true);
        }*/
    }

    @SubscribeEvent
    public static void remapMissingBlocks(final RegistryEvent.MissingMappings<Block> event){
        event.getMappings(Ref.SHARED_ID).forEach(map -> {
            String id = map.key.getPath();
            if (id.contains("oilsands")){
                Block block = AntimatterAPI.get(Block.class, id.replace("oilsands", "oil_shale").replace("granite_black", "black_granite").replace("granite_red", "red_granite"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block);
                    return;
                }
            }
            if (id.contains("granite_red") || id.contains("granite_black")){
                Block block = AntimatterAPI.get(Block.class, id.replace("granite_black", "black_granite").replace("granite_red", "red_granite"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block);
                }
            }

        });
        for (RegistryEvent.MissingMappings.Mapping<Block> map : event.getMappings("gregtech")) {

            String id = map.key.getPath();
            if (id.startsWith("block_")){
                Material mat = Material.get(id.replace("block_", ""));
                if (mat != NULL){
                    map.remap(AntimatterMaterialTypes.BLOCK.get().get(mat).asBlock());
                    continue;
                }
            }
            if (id.equals("ore_stone_salt")){
                map.remap(AntimatterMaterialTypes.ORE_STONE.get().get(Materials.Salt).asBlock());
                continue;
            }
            if (id.equals("ore_stone_rock_salt")){
                map.remap(AntimatterMaterialTypes.ORE_STONE.get().get(Materials.RockSalt).asBlock());
                continue;
            }
            if (id.startsWith("ore_")){
                Block replacement = AntimatterAPI.get(BlockOre.class, id);
                if (replacement != null){
                    map.remap(replacement);
                    continue;
                }
            }
            Block replacement = AntimatterAPI.get(Block.class, id, GTIRef.ANTIMATTER_SHARED);
            if (replacement != null){
                map.remap(replacement);
                continue;
            }
            replacement = AntimatterAPI.get(Block.class, id, GTIRef.ID);
            if (replacement != null){
                map.remap(replacement);
                continue;
            }
            if (id.equals("rubber_log")){
                map.remap(GTRubberData.RUBBER_LOG);
            }
            if (id.equals("rubber_leaves")){
                map.remap(GTRubberData.RUBBER_LEAVES);
            }
            if (id.equals("rubber_sapling")){
                map.remap(GTRubberData.RUBBER_SAPLING);
            }

            if (id.contains("battery_buffer")){
                String suffix = id.contains("one") ? "one" : id.contains("four") ? "four" : "nine";
                String prefix = id.contains("one") ? "1x" : id.contains("four") ? "4x" : "8x";
                Block block = AntimatterAPI.get(Block.class, id.replaceAll("battery_buffer_" + suffix, prefix + "_battery_buffer"), GTIRef.ID);
                if (block != null){
                    map.remap(block);
                }
            }
            if (REMAPPING_MAP.containsKey(id)){
                Block block = AntimatterAPI.get(Block.class, REMAPPING_MAP.get(id), GTIRef.ID);
                if (block != null){
                    map.remap(block);
                }
            }

        }
        for (RegistryEvent.MissingMappings.Mapping<Block> map : event.getMappings(GTIRef.ID)) {
            String id = map.key.getPath();

            if (id.contains("battery_buffer")){
                String suffix = id.contains("one") ? "one" : id.contains("four") ? "four" : "nine";
                String prefix = id.contains("one") ? "1x" : id.contains("four") ? "4x" : "8x";
                Block block = AntimatterAPI.get(Block.class, id.replaceAll("battery_buffer_" + suffix, prefix + "_battery_buffer"), GTIRef.ID);
                if (block != null){
                    map.remap(block);
                }
            }
            if (REMAPPING_MAP.containsKey(id)){
                Block block = AntimatterAPI.get(Block.class, REMAPPING_MAP.get(id), GTIRef.ID);
                if (block != null){
                    map.remap(block);
                }
            }
        }
    }

    @SubscribeEvent
    public static void remapMissingBlockEntities(final RegistryEvent.MissingMappings<BlockEntityType<?>> event){
        for (RegistryEvent.MissingMappings.Mapping<BlockEntityType<?>> map : event.getMappings("gregtech")) {
            String domain = map.key.getNamespace();
            String id = map.key.getPath();
            BlockEntityType<?> block = AntimatterAPI.get(BlockEntityType.class, id, GTIRef.ID);
            if (block != null){
                map.remap(block);
                continue;
            }

            if (id.contains("battery_buffer")){
                String suffix = id.contains("one") ? "one" : id.contains("four") ? "four" : "nine";
                String prefix = id.contains("one") ? "1x" : id.contains("four") ? "4x" : "8x";
                block = AntimatterAPI.get(BlockEntityType.class, id.replaceAll("battery_buffer_" + suffix, prefix + "_battery_buffer"), GTIRef.ID);
                if (block != null){
                    map.remap(block);
                }
            }
            if (REMAPPING_MAP.containsKey(id)){
                block = AntimatterAPI.get(BlockEntityType.class, REMAPPING_MAP.get(id), GTIRef.ID);
                if (block != null){
                    map.remap(block);
                }
            }
        }
        for (RegistryEvent.MissingMappings.Mapping<BlockEntityType<?>> map : event.getMappings(GTIRef.ID)) {
            String id = map.key.getPath();
            BlockEntityType<?> block;


            if (id.contains("battery_buffer")){
                String suffix = id.contains("one") ? "one" : id.contains("four") ? "four" : "nine";
                String prefix = id.contains("one") ? "1x" : id.contains("four") ? "4x" : "8x";
                block = AntimatterAPI.get(BlockEntityType.class, id.replaceAll("battery_buffer_" + suffix, prefix + "_battery_buffer"), GTIRef.ID);
                if (block != null){
                    map.remap(block);
                }
            }
            if (REMAPPING_MAP.containsKey(id)){
                block = AntimatterAPI.get(BlockEntityType.class, REMAPPING_MAP.get(id), GTIRef.ID);
                if (block != null){
                    map.remap(block);
                }
            }
        }
    }

    @SubscribeEvent
    public static void remapMissingItems(final RegistryEvent.MissingMappings<Item> event){
        event.getMappings(Ref.SHARED_ID).forEach(map -> {
            String id = map.key.getPath();
            if (id.contains("oilsands")){
                Item block = AntimatterAPI.get(Item.class, id.replace("oilsands", "oil_shale").replace("granite_black", "black_granite").replace("granite_red", "red_granite"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block);
                    return;
                }
            }
            if (id.contains("granite_red") || id.contains("granite_black")){
                Block block = AntimatterAPI.get(Block.class, id.replace("granite_black", "black_granite").replace("granite_red", "red_granite"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block.asItem());
                }
            }
        });
        for (RegistryEvent.MissingMappings.Mapping<Item> map : event.getMappings("gregtech")) {
            String id = map.key.getPath();
            Item replacement = AntimatterAPI.get(Item.class, id, GTIRef.ANTIMATTER_SHARED);
            if (replacement != null){
                map.remap(replacement);
                continue;
            }
            replacement = AntimatterAPI.get(Item.class, id, GTIRef.ID);
            if (replacement != null){
                map.remap(replacement);
                continue;
            }
            if (id.equals("rubber_log")){
                map.remap(GTRubberData.RUBBER_LOG.asItem());
            }
            if (id.equals("rubber_leaves")){
                map.remap(GTRubberData.RUBBER_LEAVES.asItem());
            }
            if (id.equals("rubber_sapling")){
                map.remap(GTRubberData.RUBBER_SAPLING.asItem());
            }
            if (id.equals("sticky_resin")){
                map.remap(GTRubberData.StickyResin);
            }

            if (id.contains("battery_buffer")){
                String suffix = id.contains("one") ? "one" : id.contains("four") ? "four" : "nine";
                String prefix = id.contains("one") ? "1x" : id.contains("four") ? "4x" : "8x";
                Block block = AntimatterAPI.get(Block.class, id.replaceAll("battery_buffer_" + suffix, prefix + "_battery_buffer"), GTIRef.ID);
                if (block != null){
                    map.remap(block.asItem());
                }
            }
            if (REMAPPING_MAP.containsKey(id)){
                Item block = AntimatterAPI.get(Item.class, REMAPPING_MAP.get(id), GTIRef.ID);
                if (block != null){
                    map.remap(block);
                }
            }
        }
        for (RegistryEvent.MissingMappings.Mapping<Item> map : event.getMappings(GTIRef.ID)) {
            String id = map.key.getPath();

            if (id.contains("battery_buffer")){
                String suffix = id.contains("one") ? "one" : id.contains("four") ? "four" : "nine";
                String prefix = id.contains("one") ? "1x" : id.contains("four") ? "4x" : "8x";
                Block block = AntimatterAPI.get(Block.class, id.replaceAll("battery_buffer_" + suffix, prefix + "_battery_buffer"), GTIRef.ID);
                if (block != null){
                    map.remap(block.asItem());
                }
            }
            if (REMAPPING_MAP.containsKey(id)){
                Item block = AntimatterAPI.get(Item.class, REMAPPING_MAP.get(id), GTIRef.ID);
                if (block != null){
                    map.remap(block);
                }
            }
        }
    }

    @SubscribeEvent
    public static void remapMissingFluids(final RegistryEvent.MissingMappings<Fluid> event){
        for (RegistryEvent.MissingMappings.Mapping<Fluid> map : event.getMappings(GTIRef.ID)) {
            String id = map.key.getPath();
            String liquid = id.startsWith("flowing_") ? id.replace("flowing_", "") : id;
            AntimatterFluid fluid = AntimatterAPI.get(AntimatterFluid.class, liquid);
            if (fluid != null){
                map.remap(id.startsWith("flowing_") ? fluid.getFlowingFluid() : fluid.getFluid());
            }
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<BlockEntity> event){
        if (event.getObject() instanceof BlockEntityHatchHeat<?> heat){
            event.addCapability(new ResourceLocation(GTIRef.ID, "heat_hatch"), new ICapabilityProvider() {
                @NotNull
                @Override
                public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
                    if (capability == TesseractCaps.HEAT_CAPABILITY && heat.heatHandler.isPresent()) return heat.heatHandler.side(arg).cast();
                    return LazyOptional.empty();
                }
            });
        }
    }
}
