package muramasa.gregtech.blockentity.multi;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.ServerLevelData;
import org.jetbrains.annotations.Nullable;
import tesseract.api.item.ExtendedItemContainer;

import java.util.List;
import java.util.concurrent.Executor;

public class BlockEntityTreeGrowthSimulator extends BlockEntityMultiMachine<BlockEntityTreeGrowthSimulator> {
    public BlockEntityTreeGrowthSimulator(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new MachineRecipeHandler<>(this){
            @Override
            public IRecipe findRecipe() {
                if (lastRecipe != null) {
                    activeRecipe = lastRecipe;
                    if (canRecipeContinue()) {
                        activeRecipe = null;
                        return lastRecipe;
                    }
                    activeRecipe = null;
                }
                ExtendedItemContainer container = itemHandler.map(i -> i.getInputHandler()).orElse(null);
                if (container != null && container.getContainerSize() > 0){
                    for (int i = 0; i < container.getContainerSize(); i++) {
                        ItemStack stack = container.getItem(i);
                        if (stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof SaplingBlock){
                            ResourceLocation id = AntimatterPlatformUtils.getIdFromBlock(blockItem.getBlock());
                            ResourceLocation logId = new ResourceLocation(id.getNamespace(), id.getPath().replace("_sapling", "_log"));
                            if (AntimatterPlatformUtils.blockExists(logId)){
                                return new RecipeBuilder().recipeMapOnly().ii(RecipeIngredient.of(stack.getItem(), 1).setNoConsume()).io(new ItemStack(AntimatterPlatformUtils.getItemFromID(logId), 10)).add(id.getPath(), 10, 16);
                            }
                        }
                    }
                }
                return null;
            }

            @Override
            public boolean accepts(ItemStack stack) {
                return stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof SaplingBlock;
            }
        });
    }
}
