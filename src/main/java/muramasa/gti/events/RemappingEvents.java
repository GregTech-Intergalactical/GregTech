package muramasa.gti.events;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Data;
import muramasa.antimatter.fluid.AntimatterFluid;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.ore.BlockOre;
import muramasa.gti.Ref;
import muramasa.gti.data.Materials;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static muramasa.antimatter.Data.NULL;

public class RemappingEvents {

    @SubscribeEvent
    public static void remapMissingBlocks(final RegistryEvent.MissingMappings<Block> event){
        for (RegistryEvent.MissingMappings.Mapping<Block> map : event.getMappings(Ref.ID)) {
            String domain = map.key.getNamespace();
            String id = map.key.getPath();
            if (id.startsWith("block_")){
                Material mat = Material.get(id.replace("block_", ""));
                if (mat != NULL){
                    map.remap(Data.BLOCK.get().get(mat).asBlock());
                    continue;
                }
            }
            if (id.equals("ore_stone_salt")){
                map.remap(Data.ORE_STONE.get().get(Materials.Salt).asBlock());
                continue;
            }
            if (id.equals("ore_stone_rock_salt")){
                map.remap(Data.ORE_STONE.get().get(Materials.RockSalt).asBlock());
                continue;
            }
            if (id.startsWith("ore_")){
                Block replacement = AntimatterAPI.get(BlockOre.class, id);
                if (replacement != null){
                    map.remap(replacement);
                    continue;
                }
            }
            Block replacement = AntimatterAPI.get(Block.class, id, Ref.ANTIMATTER_SHARED);
            if (replacement != null){
                map.remap(replacement);
            }
        }
    }

    @SubscribeEvent
    public static void remapMissingItems(final RegistryEvent.MissingMappings<Item> event){
        for (RegistryEvent.MissingMappings.Mapping<Item> map : event.getMappings(Ref.ID)) {
            Item replacement = AntimatterAPI.get(Item.class, map.key.getPath(), Ref.ANTIMATTER_SHARED);
            if (replacement != null){
                map.remap(replacement);
            }
        }
    }

    @SubscribeEvent
    public static void remapMissingFluids(final RegistryEvent.MissingMappings<Fluid> event){
        for (RegistryEvent.MissingMappings.Mapping<Fluid> map : event.getMappings(Ref.ID)) {
            String id = map.key.getPath();
            String liquid = id.startsWith("flowing_") ? id.replace("flowing_", "") : id;
            AntimatterFluid fluid = AntimatterAPI.get(AntimatterFluid.class, liquid);
            if (fluid != null){
                map.remap(id.startsWith("flowing_") ? fluid.getFlowingFluid() : fluid.getFluid());
            }
        }
    }
}
