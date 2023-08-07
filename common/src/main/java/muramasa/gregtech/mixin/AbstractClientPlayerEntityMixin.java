package muramasa.gregtech.mixin;

import com.mojang.authlib.GameProfile;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerEntityMixin extends Player {

    public AbstractClientPlayerEntityMixin(Level p_i241920_1_, BlockPos p_i241920_2_, float p_i241920_3_, GameProfile p_i241920_4_) {
        super(p_i241920_1_, p_i241920_2_, p_i241920_3_, p_i241920_4_);
    }

    @Inject(method = "getCloakTextureLocation", at = @At(value = "HEAD"), cancellable = true)
    private void getLocationGTCape(CallbackInfoReturnable<ResourceLocation> info){
        String playerName = this.getDisplayName().getString();
        if (!AntimatterPlatformUtils.isProduction()) info.setReturnValue(GregTechData.CAPE_LOCATIONS[3]);
        if (orString(playerName, "GregoriusT", "OvermindDL1", "jihuayu123", "Yuesha_Kev14", "Evanvenir", "Trinsdar")) info.setReturnValue(GregTechData.CAPE_LOCATIONS[3]);
        if (playerName.equals("CrazyJ1984")) info.setReturnValue(GregTechData.CAPE_LOCATIONS[5]);
        if (playerName.equals("Mr_Brain")) info.setReturnValue(GregTechData.CAPE_LOCATIONS[2]);
        if (playerName.equals("Friedi4321")) info.setReturnValue(GregTechData.CAPE_LOCATIONS[0]);
        if (GregTechData.SupporterListGold.contains(playerName)) info.setReturnValue(GregTechData.CAPE_LOCATIONS[4]);
        if (GregTechData.SupporterListSilver.contains(playerName)) info.setReturnValue(GregTechData.CAPE_LOCATIONS[1]);
    }

    private boolean orString(String compare, String... strings){
        List<String> list = List.of(strings);
        return list.contains(compare);
    }
}
