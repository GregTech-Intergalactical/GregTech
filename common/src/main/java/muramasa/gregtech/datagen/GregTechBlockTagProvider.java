package muramasa.gregtech.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.block.BlockCasing;
import muramasa.gregtech.block.BlockCoil;
import muramasa.gregtech.block.BlockColoredWall;
import muramasa.gregtech.block.BlockFakeCasing;
import muramasa.gregtech.data.GregTechData;

import static muramasa.antimatter.data.AntimatterMaterials.Wood;

public class GregTechBlockTagProvider extends AntimatterBlockTagProvider {

    public GregTechBlockTagProvider(String providerDomain, String providerName, boolean replace) {
        super(providerDomain, providerName, replace);
    }

    @Override
    public void processTags(String domain){
        super.processTags(domain);
        AntimatterAPI.all(BlockCasing.class, GTIRef.ID, cas -> {
            if (cas.getId().contains("long_distance_wire")){
                this.tag(AntimatterDefaultTools.WIRE_CUTTER.getToolType()).add(cas);
                return;
            }
            this.tag(AntimatterDefaultTools.WRENCH.getToolType()).add(cas);
        });
        AntimatterAPI.all(BlockColoredWall.class, GTIRef.ID, cas -> {
            if (cas.getMaterial() == Wood){
                this.tag(AntimatterDefaultTools.AXE.getToolType()).add(cas);
            } else {
                this.tag(AntimatterDefaultTools.WRENCH.getToolType()).add(cas);
            }
        });
        AntimatterAPI.all(BlockFakeCasing.class, GTIRef.ID, cas -> {
            this.tag(AntimatterDefaultTools.PICKAXE.getToolType()).add(cas);
        });
        AntimatterAPI.all(BlockCoil.class, GTIRef.ID, cas -> {
            this.tag(AntimatterDefaultTools.WRENCH.getToolType()).add(cas);
        });
        this.tag(AntimatterDefaultTools.AXE.getToolType()).add(GregTechData.BRITTLE_CHARCOAL);
        this.tag(AntimatterDefaultTools.PICKAXE.getToolType()).add(GregTechData.MINING_PIPE, GregTechData.MINING_PIPE_THIN);
    }
}
