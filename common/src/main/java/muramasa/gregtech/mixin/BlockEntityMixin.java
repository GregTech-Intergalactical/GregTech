package muramasa.gregtech.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.GTRemapping;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(BlockEntity.class)
public class BlockEntityMixin {
    @WrapOperation(method = "loadStatic", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Registry;getOptional(Lnet/minecraft/resources/ResourceLocation;)Ljava/util/Optional;"))
    private static Optional<BlockEntity> wrapBlockEntityRemap(Registry registry, ResourceLocation id, Operation<Optional<BlockEntity>> original){
        if (id.getNamespace().equals(GTIRef.ID) && GTRemapping.getBeRemappingMap().containsKey(id.getPath())){
            return original.call(new ResourceLocation(GTIRef.ID, GTRemapping.getBeRemappingMap().get(id.getPath())));
        }
        return original.call(id);
    }
}
