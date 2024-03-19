package muramasa.gregtech.machine;

import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.machine.MachineFlag;
import muramasa.antimatter.machine.types.BasicMachine;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.data.Textures;
import net.minecraft.resources.ResourceLocation;

import static muramasa.antimatter.machine.MachineFlag.UNCULLED;
import static muramasa.antimatter.machine.Tier.NONE;

public class MiniPortalMachine extends BasicMachine {
    public MiniPortalMachine(String domain, String id) {
        super(domain, id);
        this.setTiers(NONE);
        this.addFlags(UNCULLED);
        this.noCovers().allowFrontIO();
        this.itemModelParent(new ResourceLocation(GTIRef.ID, "block/mini_portal"));
        this.custom(Textures.MINI_PORTAL);
        this.removeFlags(MachineFlag.COVERABLE);
        this.setToolTag(AntimatterDefaultTools.PICKAXE.getToolType());
    }
}
