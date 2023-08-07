package trinsdar.gt4r.forge.mixin;

import net.minecraftforge.common.capabilities.CapabilityDispatcher;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import javax.annotation.Nullable;

@Mixin(CapabilityProvider.class)
public interface GTCapabilityProviderAccessor {

    @Accessor(value = "capabilities", remap = false)
    @Nullable
    CapabilityDispatcher getCapabilitiesGT();
}
