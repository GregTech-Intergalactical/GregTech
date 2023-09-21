package muramasa.gregtech.machine;

import com.gtnewhorizon.structurelib.structure.StructureUtility;
import io.github.gregtechintergalactical.gtutility.machine.MaterialBasicMultiMachine;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.machine.MachineFlag;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.structure.FakeTileElement;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.blockentity.multi.TileEntityLargeTank;

import static muramasa.antimatter.data.AntimatterMaterials.Wood;

public class MultiblockTankMachine extends MaterialBasicMultiMachine {
    final int capacity;
    final boolean small;
    boolean acidProof;
    public MultiblockTankMachine(String domain, Material material, boolean small, int capacity) {
        super(domain, (small ? "small" : "large") + "_" + material.getId() + "_tank_main_valve", material);
        setTiers(Tier.NONE);
        if (small){
            this.setStructure(TileEntityLargeTank.class, b -> b.part("main")
                    .of("CCC", "CCC", "CCC").of("C~C", "C-C", "CCC").of(0).build()
                    .atElement('C', StructureUtility.lazy(t -> new FakeTileElement<>(t.getCasing()))).offset(1, 1, 0)
                    .build());
        } else {
            this.setStructure(TileEntityLargeTank.class, b -> b.part("main")
                    .of("CCCCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC").of("CC~CC", "C---C", "C---C", "C---C", "CCCCC").of(0).build()
                    .atElement('C', StructureUtility.lazy(t -> new FakeTileElement<>(t.getCasing()))).offset(2, 2, 0)
                    .build());
        }
        setTooltipInfo((machine, stack, world, tooltip, flag) -> {
            tooltip.add(Utils.translatable("machine.drum.capacity", capacity));
        });
        addFlags(MachineFlag.FLUID);
        setTile((type1, pos, state1) -> new TileEntityLargeTank(this, pos, state1));
        this.capacity = capacity;
        this.small = small;
        String prefix = material == Wood ? "wood" : "metal";
        baseTexture(new Texture(GTIRef.ID, "block/casing/wall/" + prefix));
        overlayTexture((type, state, tier) -> {
            Texture blank = new Texture(GTIRef.ID, "block/machine/empty");
            return new Texture[]{blank, blank, blank, new Texture(GTIRef.ID, "block/casing/wall/" + prefix + "_tank_side_overlay"), blank, blank};
        });
        setAllowVerticalFacing(true);
        AntimatterAPI.register(MultiblockTankMachine.class, this);
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isSmall() {
        return small;
    }

    public MultiblockTankMachine acidProof(){
        acidProof = true;
        return this;
    }

    public boolean isAcidProof() {
        return acidProof;
    }
}
