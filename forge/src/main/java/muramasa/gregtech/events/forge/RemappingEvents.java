package muramasa.gregtech.events.forge;

import io.github.gregtechintergalactical.gtrubber.GTRubberData;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import muramasa.antimatter.Antimatter;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.common.event.forge.ForgeCommonEvents;
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
        REMAPPING_MAP.put("steam_generator_lv", "steam_turbine_lv");
        REMAPPING_MAP.put("steam_generator_mv", "steam_turbine_mv");
        REMAPPING_MAP.put("steam_generator_hv", "steam_turbine_hv");
        REMAPPING_MAP.put("gas_generator_lv", "gas_turbine_lv");
        REMAPPING_MAP.put("gas_generator_mv", "gas_turbine_mv");
        REMAPPING_MAP.put("gas_generator_hv", "gas_turbine_hv");
        REMAPPING_MAP.put("circuit_basic", "basic_integrated_circuit");
        REMAPPING_MAP.put("circuit_basic_electronic", "basic_electronic_circuit");
        REMAPPING_MAP.put("circuit_good", "good_electronic_circuit");
        REMAPPING_MAP.put("circuit_adv", "advanced_circuit");
        REMAPPING_MAP.put("circuit_nanoprocessor", "nanoprocessor");
        REMAPPING_MAP.put("circuit_quantumprocessor", "quantumprocessor");
        REMAPPING_MAP.put("circuit_energy_flow", "energy_flow_circuit");
        REMAPPING_MAP.put("circuit_wetware", "wetware_circuit");
        REMAPPING_MAP.put("vacuumtube", "vacuum_tube");
        REMAPPING_MAP.put("adv_circuit_parts", "advanced_circuit_parts");
        REMAPPING_MAP.put("battery_hull_small", "small_battery_hull");
        REMAPPING_MAP.put("battery_hull_medium", "medium_battery_hull");
        REMAPPING_MAP.put("battery_hull_large", "large_battery_hull");
        REMAPPING_MAP.put("battery_small_mercury", "small_mercury_battery");
        REMAPPING_MAP.put("battery_small_acid", "small_acid_battery");
        REMAPPING_MAP.put("battery_small_cadmium", "small_cadmium_battery");
        REMAPPING_MAP.put("battery_small_lithium", "small_lithium_battery");
        REMAPPING_MAP.put("battery_small_sodium", "small_sodium_battery");
        REMAPPING_MAP.put("battery_medium_mercury", "medium_mercury_battery");
        REMAPPING_MAP.put("battery_medium_acid", "medium_acid_battery");
        REMAPPING_MAP.put("battery_medium_cadmium", "medium_cadmium_battery");
        REMAPPING_MAP.put("battery_medium_lithium", "medium_lithium_battery");
        REMAPPING_MAP.put("battery_medium_sodium", "medium_sodium_battery");
        REMAPPING_MAP.put("battery_large_mercury", "large_mercury_battery");
        REMAPPING_MAP.put("battery_large_acid", "large_acid_battery");
        REMAPPING_MAP.put("battery_large_cadmium", "large_cadmium_battery");
        REMAPPING_MAP.put("battery_large_lithium", "large_lithium_battery");
        REMAPPING_MAP.put("battery_large_sodium", "large_sodium_battery");
        REMAPPING_MAP.put("coil_cupronickel", "cupronickel_coil");
        REMAPPING_MAP.put("coil_kanthal", "kanthal_coil");
        REMAPPING_MAP.put("coil_nichrome", "nichrome_coil");
        REMAPPING_MAP.put("coil_tungstensteel", "tungstensteel_coil");
        REMAPPING_MAP.put("coil_hssg", "hssg_coil");
        REMAPPING_MAP.put("coil_naquadah", "naquadah_coil");
        REMAPPING_MAP.put("coil_naquadah_alloy", "naquadah_alloy_coil");
        REMAPPING_MAP.put("coil_fusion", "fusion_coil");
        REMAPPING_MAP.put("coil_superconductor", "superconductor_coil");
        REMAPPING_MAP.put("casing_bronze", "bronze_casing");
        REMAPPING_MAP.put("casing_bricked_bronze", "bricked_bronze_casing");
        REMAPPING_MAP.put("casing_steel", "steel_casing");
        REMAPPING_MAP.put("casing_bricked_steel", "bricked_steel_casing");
        REMAPPING_MAP.put("casing_bronze_plated_brick", "bronze_plated_brick_casing");
        REMAPPING_MAP.put("casing_solid_steel", "solid_steel_casing");
        REMAPPING_MAP.put("casing_stainless_steel", "stainless_steel_casing");
        REMAPPING_MAP.put("casing_titanium", "titanium_casing");
        REMAPPING_MAP.put("casing_tungstensteel", "tungstensteel_casing");
        REMAPPING_MAP.put("casing_heat_proof", "heat_proof_casing");
        REMAPPING_MAP.put("casing_frost_proof", "frost_proof_casing");
        REMAPPING_MAP.put("casing_radiation_proof", "radiation_proof_casing");
        REMAPPING_MAP.put("casing_firebox_bronze", "bronze_firebox_casing");
        REMAPPING_MAP.put("casing_firebox_steel", "steel_firebox_casing");
        REMAPPING_MAP.put("casing_firebox_titanium", "titanium_firebox_casing");
        REMAPPING_MAP.put("casing_firebox_tungstensteel", "tungstensteel_firebox_casing");
        REMAPPING_MAP.put("casing_gearbox_bronze", "bronze_gearbox_casing");
        REMAPPING_MAP.put("casing_gearbox_steel", "steel_gearbox_casing");
        REMAPPING_MAP.put("casing_gearbox_titanium", "titanium_gearbox_casing");
        REMAPPING_MAP.put("casing_gearbox_tungstensteel", "tungstensteel_gearbox_casing");
        REMAPPING_MAP.put("casing_pipe_bronze", "bronze_pipe_casing");
        REMAPPING_MAP.put("casing_pipe_steel", "steel_pipe_casing");
        REMAPPING_MAP.put("casing_pipe_titanium", "titanium_pipe_casing");
        REMAPPING_MAP.put("casing_pipe_tungstensteel", "tungstensteel_pipe_casing");
        REMAPPING_MAP.put("casing_engine_intake", "engine_intake_casing");
        REMAPPING_MAP.put("casing_turbine_1", "steel_turbine_casing");
        REMAPPING_MAP.put("casing_turbine_2", "stainless_steel_turbine_casing");
        REMAPPING_MAP.put("casing_turbine_3", "titanium_turbine_casing");
        REMAPPING_MAP.put("casing_turbine_4", "tungstensteel_turbine_casing");
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
            if (id.contains("blue_sapphire")){
                Item block = AntimatterAPI.get(Item.class, id.replace("blue_sapphire", "sapphire").replace("granite_black", "black_granite").replace("granite_red", "red_granite"), Ref.SHARED_ID);
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
}
