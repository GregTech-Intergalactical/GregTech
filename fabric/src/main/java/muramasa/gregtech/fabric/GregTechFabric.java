package muramasa.gregtech.fabric;

import muramasa.antimatter.event.fabric.CraftingEvents;
import muramasa.antimatter.event.fabric.LoaderEvents;
import muramasa.antimatter.event.fabric.ProviderEvents;
import muramasa.antimatter.event.fabric.WorldGenEvents;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.blockentity.miniportals.BlockEntityMiniPortal;
import muramasa.gregtech.loader.WorldGenLoader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.world.level.block.entity.BlockEntity;
import team.reborn.energy.api.EnergyStorage;
import tesseract.api.fabric.TesseractLookups;

public class GregTechFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        WorldGenEvents.WORLD_GEN.register(WorldGenLoader::init);
        LoaderEvents.LOADER.register(GregTech::registerRecipeLoaders);
        CraftingEvents.CRAFTING.register(GregTech::registerCraftingLoaders);
        ProviderEvents.PROVIDERS.register(GregTech::onProviders);
        EnergyStorage.SIDED.registerFallback(((world, pos, state, blockEntity, context) -> {
            if (blockEntity instanceof BlockEntityMiniPortal miniPortal && context != null){
                if (miniPortal.getOtherSide() != null){
                    BlockEntity cached = miniPortal.getOtherSide().getCachedBlockEntity(context.getOpposite());
                    if (cached != null){
                        return EnergyStorage.SIDED.find(cached.getLevel(), cached.getBlockPos(), context);
                    }
                }
            }
            return null;
        }));

        FluidStorage.SIDED.registerFallback(((world, pos, state, blockEntity, context) -> {
            if (blockEntity instanceof BlockEntityMiniPortal miniPortal && context != null){
                if (miniPortal.getOtherSide() != null){
                    BlockEntity cached = miniPortal.getOtherSide().getCachedBlockEntity(context.getOpposite());
                    if (cached != null){
                        return FluidStorage.SIDED.find(cached.getLevel(), cached.getBlockPos(), context);
                    }
                }
            }
            return null;
        }));
        ItemStorage.SIDED.registerFallback(((world, pos, state, blockEntity, context) -> {
            if (blockEntity instanceof BlockEntityMiniPortal miniPortal && context != null){
                if (miniPortal.getOtherSide() != null){
                    BlockEntity cached = miniPortal.getOtherSide().getCachedBlockEntity(context.getOpposite());
                    if (cached != null){
                        return ItemStorage.SIDED.find(cached.getLevel(), cached.getBlockPos(), context);
                    }
                }
            }
            return null;
        }));
        TesseractLookups.ENERGY_HANDLER_SIDED.registerFallback(((world, pos, state, blockEntity, context) -> {
            if (blockEntity instanceof BlockEntityMiniPortal miniPortal && context != null){
                if (miniPortal.getOtherSide() != null){
                    BlockEntity cached = miniPortal.getOtherSide().getCachedBlockEntity(context.getOpposite());
                    if (cached != null){
                        return TesseractLookups.ENERGY_HANDLER_SIDED.find(cached.getLevel(), cached.getBlockPos(), context);
                    }
                }
            }
            return null;
        }));
        TesseractLookups.HEAT_HANDLER_SIDED.registerFallback(((world, pos, state, blockEntity, context) -> {
            if (blockEntity instanceof BlockEntityMiniPortal miniPortal && context != null){
                if (miniPortal.getOtherSide() != null){
                    BlockEntity cached = miniPortal.getOtherSide().getCachedBlockEntity(context.getOpposite());
                    if (cached != null){
                        return TesseractLookups.HEAT_HANDLER_SIDED.find(cached.getLevel(), cached.getBlockPos(), context);
                    }
                }
            }
            return null;
        }));
    }
}
