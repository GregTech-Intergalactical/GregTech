package muramasa.gregtech.cover;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.client.RenderHelper;
import muramasa.antimatter.cover.CoverEnergy;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import muramasa.gregtech.data.TierMaps;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CoverEnergyColored extends CoverEnergy {
    public CoverEnergyColored(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    public List<BakedQuad> transformQuads(BlockState state, List<BakedQuad> quads) {
        if (handler.getTile() instanceof BlockEntityMachine<?> machine){
            quads.forEach(q -> {
                if (q.getSprite().getName().getPath().contains("energy")){
                    RenderHelper.colorQuad(q, TierMaps.TIER_WIRES.get(machine.getMachineTier()).getPipe().getRGB());
                }
            });
        }
        return quads;
    }
}
