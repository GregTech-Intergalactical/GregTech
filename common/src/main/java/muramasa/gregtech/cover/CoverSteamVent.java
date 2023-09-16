package muramasa.gregtech.cover;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.Tier;
import muramasa.gregtech.blockentity.single.BlockEntitySteamMachine;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.Random;

public class CoverSteamVent extends BaseCover {


    public CoverSteamVent(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    protected String getRenderId() {
        return "output";
    }

    @Override
    public String getId() {
        return "cover_steam_vent";
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicDepthModel();
    }

    @Override
    public void onBlockUpdate() {
        if (handler.getTile() instanceof BlockEntityMachine){
            ((BlockEntityMachine<?>)handler.getTile()).recipeHandler.ifPresent(h -> {
                if (h instanceof BlockEntitySteamMachine.SteamMachineRecipeHandler steamMachineRecipeHandler){
                    steamMachineRecipeHandler.setSteamClear(handler.getTile().getLevel().isEmptyBlock(handler.getTile().getBlockPos().relative(side)));
                }
            });
        }
    }

    @Override
    public void onUpdate() {
        BlockPos offset = this.handler.getTile().getBlockPos().relative(this.side);
        Level level = handler.getTile().getLevel();
        if (level == null) return;
        if (!(handler.getTile() instanceof BlockEntityMachine<?> machine) || machine.getMachineState() != MachineState.ACTIVE) return;
        level.getEntitiesOfClass(Player.class, new AABB(offset)).forEach(p -> {
            p.hurt(DamageSource.HOT_FLOOR, 1.0f);
        });
        if (level.isClientSide && level.getGameTime() % 20 == 0){
            addParticle(level, offset);
        }
    }

    @Environment(EnvType.CLIENT)
    private void addParticle(Level level, BlockPos offset){
        ClientLevel clientLevel = (ClientLevel) level;
        Random random = level.random;
        double xR = random.nextDouble();
        double yR = random.nextDouble();
        double zR = random.nextDouble();
        clientLevel.addParticle(ParticleTypes.SMOKE, offset.getX() + + xR, offset.getY() + yR, offset.getZ() + zR, 0, 0, 0);
        xR = random.nextDouble();
        yR = random.nextDouble();
        zR = random.nextDouble();
        clientLevel.addParticle(ParticleTypes.SMOKE, offset.getX() + + xR, offset.getY() + yR, offset.getZ() + zR, 0, 0, 0);
    }
}
