//package muramasa.gtu.client.render.overrides;
//
//import muramasa.antimatter.items.ItemFluidCell;
//import muramasa.antimatter.client.ModelUtils;
//import muramasa.gtu.client.render.bakedmodels.BakedFluidCell;
//import muramasa.gtu.client.render.models.ModelFluidCell;
//import net.minecraft.client.renderer.model.IBakedModel;
//import net.minecraft.client.renderer.model.ItemOverrideList;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.world.World;
//import net.minecraftforge.common.model.TRSRTransformation;
//import net.minecraftforge.fluids.FluidStack;
//
//import javax.annotation.Nullable;
//import java.util.HashMap;
//
//public class ItemOverrideFluidCell extends ItemOverrideList {
//
//    private static HashMap<String, IBakedModel> CACHE = new HashMap<>();
//
//    @Nullable
//    @Override
//    public IBakedModel getModelWithOverrides(IBakedModel originalModel, ItemStack stack, @Nullable World world, @Nullable LivingEntity entity) {
//        FluidStack fluidStack = ItemFluidCell.getContents(stack);
//        if (fluidStack == null || fluidStack.getFluid().getRegistryName() == null) return originalModel;
//
//        //TODO Cache by fluid ID?
//        IBakedModel baked = CACHE.get(fluidStack.getFluid().getRegistryName().toString());
//        if (baked == null) {
//            BakedFluidCell bakedCell = (BakedFluidCell) originalModel;
//            ModelFluidCell model = new ModelFluidCell(fluidStack.getFluid());
//            CACHE.put(fluidStack.getFluid().getRegistryName().toString(), (baked = model.bake(TRSRTransformation.identity(), bakedCell.format, ModelUtils.getTextureGetter())));
//        }
//        return baked;
//    }
//}
