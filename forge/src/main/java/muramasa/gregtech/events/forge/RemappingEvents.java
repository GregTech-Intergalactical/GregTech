package muramasa.gregtech.events.forge;

import io.github.gregtechintergalactical.gtcore.data.GTCoreBlocks;
import io.github.gregtechintergalactical.gtcore.events.GTCommonEvents;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterRemapping;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.fluid.AntimatterFluid;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.ore.BlockOre;
import muramasa.antimatter.structure.StructureCache;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.blockentity.miniportals.BlockEntityMiniPortal;
import muramasa.gregtech.blockentity.multi.MiningPipeStructureCache;
import muramasa.gregtech.data.Materials;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.UUID;

import static muramasa.antimatter.material.Material.NULL;

public class RemappingEvents {
    UUID bearUUID = UUID.fromString("1964e3d1-6500-40e7-9ff2-e6161d41a8c2");

    @SubscribeEvent
    public static void rightClickEntity(PlayerInteractEvent.EntityInteract event){
        /*if (event.getTarget() instanceof Player targetPlayer){

        }*/
        ItemStack handStack = event.getPlayer().getItemInHand(event.getHand());
        if(handStack.is(AntimatterDefaultTools.WRENCH.getTag()) && event.getTarget() instanceof Player targetPlayer && targetPlayer.getUUID().equals(GTCommonEvents.BEAR_UUID)){
            Random random = event.getPlayer().getRandom();
            targetPlayer.moveTo(targetPlayer.getX(), targetPlayer.getY(), targetPlayer.getZ(), random.nextInt(180), targetPlayer.getXRot());
        }
    }

    @SubscribeEvent
    public static void onAttackCapabilitiesEvent(AttachCapabilitiesEvent<BlockEntity> event){
        if (event.getObject() instanceof BlockEntityMiniPortal miniPortal){
            event.addCapability(new ResourceLocation(GTIRef.ID, "mini_portal"), new ICapabilityProvider() {
                @NotNull
                @Override
                public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
                    if (arg == null || miniPortal.getOtherSide() == null) return LazyOptional.empty();
                    BlockEntity offset = miniPortal.getOtherSide().getCachedBlockEntity(arg.getOpposite());
                    if (offset != null){
                        return offset.getCapability(capability, arg);
                    }
                    return LazyOptional.empty();
                }
            });
        }
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
            if (id.contains("quartzite") && !id.contains("__")){
                Block block = AntimatterAPI.get(Block.class, id.replace("quartzite", "milky_quartz").replace("granite_black", "black_granite").replace("granite_red", "red_granite"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block);
                    return;
                }
            }
            if (id.contains("blue_sapphire")){
                Block block = AntimatterAPI.get(Block.class, id.replace("blue_sapphire", "sapphire").replace("granite_black", "black_granite").replace("granite_red", "red_granite"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block);
                    return;
                }
            }
            if (id.contains("cooperite")){
                Block block = AntimatterAPI.get(Block.class, id.replace("cooperite", "sheldonite").replace("granite_black", "black_granite").replace("granite_red", "red_granite"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block);
                    return;
                }
            }
            if (id.contains("phosphorus")){
                Block block = AntimatterAPI.get(Block.class, id.replace("phosphorus", "tricalcium_phosphate").replace("granite_black", "black_granite").replace("granite_red", "red_granite"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block);
                    return;
                }
            }
            if (id.contains("polyethylene")){
                Block block = AntimatterAPI.get(Block.class, id.replace("polyethylene", "plastic"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block);
                    return;
                }
            }
            if (id.contains("nitric_oxide")){
                Block block = AntimatterAPI.get(Block.class, id.replace("nitric_oxide", "nitrogen_monoxide"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block);
                    return;
                }
            }
            if (id.contains("banded_iron")){
                Block block = AntimatterAPI.get(Block.class, id.replace("banded_iron", "hematite").replace("granite_black", "black_granite").replace("granite_red", "red_granite"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block);
                    return;
                }
            }
            if (id.contains("chrome")){
                Block block = AntimatterAPI.get(Block.class, id.replace("chrome", "chromium").replace("granite_black", "black_granite").replace("granite_red", "red_granite"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block);
                    return;
                }
            }
            if (id.contains("aluminium")){
                Block block = AntimatterAPI.get(Block.class, id.replace("aluminium", "alumina").replace("granite_black", "black_granite").replace("granite_red", "red_granite"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block);
                    return;
                }
            }
            if (id.contains("uranium") && !id.contains("uranium_2")){
                Block block = AntimatterAPI.get(Block.class, id.replace("uranium", "uraninite").replace("granite_black", "black_granite").replace("granite_red", "red_granite"), Ref.SHARED_ID);
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
        for (var map : event.getMappings("gregtech")) {

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
                map.remap(GTCoreBlocks.RUBBER_LOG);
            }
            if (id.equals("rubber_leaves")){
                map.remap(GTCoreBlocks.RUBBER_LEAVES);
            }
            if (id.equals("rubber_sapling")){
                map.remap(GTCoreBlocks.RUBBER_SAPLING);
            }
            if (AntimatterRemapping.getRemappingMap().get(GTIRef.ID).containsKey(id)){
                Block block = AntimatterAPI.get(Block.class, AntimatterRemapping.getRemappingMap().get(GTIRef.ID).get(id));
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
            if (id.contains("blue_sapphire")){
                Item block = AntimatterAPI.get(Item.class, id.replace("blue_sapphire", "sapphire").replace("granite_black", "black_granite").replace("granite_red", "red_granite"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block);
                    return;
                }
            }
            if (id.contains("cooperite")){
                Item block = AntimatterAPI.get(Item.class, id.replace("cooperite", "sheldonite").replace("granite_black", "black_granite").replace("granite_red", "red_granite"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block);
                    return;
                }
            }
            if (id.contains("phosphorus")){
                Item block = AntimatterAPI.get(Item.class, id.replace("phosphorus", "tricalcium_phosphate").replace("granite_black", "black_granite").replace("granite_red", "red_granite"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block);
                    return;
                }
            }
            if (id.contains("polyethylene")){
                Item block = AntimatterAPI.get(Item.class, id.replace("polyethylene", "plastic"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block);
                    return;
                }
            }
            if (id.contains("nitric_oxide")){
                Item block = AntimatterAPI.get(Item.class, id.replace("nitric_oxide", "nitrogen_monoxide"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block);
                    return;
                }
            }
            if (id.contains("banded_iron")){
                Item block = AntimatterAPI.get(Item.class, id.replace("banded_iron", "hematite").replace("granite_black", "black_granite").replace("granite_red", "red_granite"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block);
                    return;
                }
            }
            if (id.contains("chrome")){
                Item block = AntimatterAPI.get(Item.class, id.replace("chrome", "chromium").replace("granite_black", "black_granite").replace("granite_red", "red_granite"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block);
                    return;
                }
            }
            if (id.contains("aluminium")){
                Item block = AntimatterAPI.get(Item.class, id.replace("aluminium", "alumina").replace("granite_black", "black_granite").replace("granite_red", "red_granite"), Ref.SHARED_ID);
                if (block != null){
                    map.remap(block);
                    return;
                }
            }
            if (id.contains("uranium") && !id.contains("uranium_2")){
                Item block = AntimatterAPI.get(Item.class, id.replace("uranium", "uraninite").replace("granite_black", "black_granite").replace("granite_red", "red_granite"), Ref.SHARED_ID);
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
        for (var map : event.getMappings("gregtech")) {
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

            if (AntimatterRemapping.getRemappingMap().get(GTIRef.ID).containsKey(id)){
                Item block = AntimatterAPI.get(Item.class, AntimatterRemapping.getRemappingMap().get(GTIRef.ID).get(id));
                if (block != null){
                    map.remap(block);
                }
            }
        }
    }

    @SubscribeEvent
    public static void remapMissingFluids(final RegistryEvent.MissingMappings<Fluid> event){
        for (var map : event.getMappings(GTIRef.ID)) {
            String id = map.key.getPath();
            String liquid = id.startsWith("flowing_") ? id.replace("flowing_", "") : id;
            if (id.contains("polethylene")){
                liquid = liquid.replace("polethylene", "plastic");
            }
            AntimatterFluid fluid = AntimatterAPI.get(AntimatterFluid.class, liquid);
            if (fluid != null){
                map.remap(id.startsWith("flowing_") ? fluid.getFlowingFluid() : fluid.getFluid());
            }
        }
        for (var map : event.getMappings(Ref.SHARED_ID)) {
            String id = map.key.getPath();
            String liquid = id.startsWith("flowing_") ? id.replace("flowing_", "") : id;
            if (id.contains("polethylene")){
                liquid = liquid.replace("polethylene", "plastic");
            }
            if (id.contains("nitric_oxide")){
                liquid = liquid.replace("nitric_oxide", "nitrogen_monoxide");
            }
            AntimatterFluid fluid = AntimatterAPI.get(AntimatterFluid.class, liquid);
            if (fluid != null){
                map.remap(id.startsWith("flowing_") ? fluid.getFlowingFluid() : fluid.getFluid());
            }
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<BlockEntity> event){
        /*if (event.getObject() instanceof BlockEntityHatchHeat<?> heat){
            event.addCapability(new ResourceLocation(GTIRef.ID, "heat_hatch"), new ICapabilityProvider() {
                @NotNull
                @Override
                public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
                    if (capability == TesseractCaps.HEAT_CAPABILITY && heat.heatHandler.isPresent()) return ForgeCommonEvents.fromHolder(heat.heatHandler, arg).cast();
                    return LazyOptional.empty();
                }
            });
        }*/
    }

    @SubscribeEvent
    public static void onWorldUnload(WorldEvent.Unload event){
        MiningPipeStructureCache.onWorldUnload(event.getWorld());
    }
}
