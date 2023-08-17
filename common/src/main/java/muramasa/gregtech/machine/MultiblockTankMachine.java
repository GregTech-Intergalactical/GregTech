package muramasa.gregtech.machine;

import com.gtnewhorizon.structurelib.structure.StructureUtility;
import io.github.gregtechintergalactical.gtutility.machine.MaterialBasicMultiMachine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.texture.Texture;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.tile.multi.TileEntityLargeTank;
import net.minecraft.world.level.block.Block;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static muramasa.antimatter.data.AntimatterMaterials.Wood;

public class MultiblockTankMachine extends MaterialBasicMultiMachine {
    final int capacity;
    public MultiblockTankMachine(String domain, Material material, boolean small, int capacity) {
        super(domain, (small ? "small" : "large") + "_" + material.getId() + "_tank_main_valve", material);
        if (small){
            this.setStructure(TileEntityLargeTank.class, b -> b.part("main")
                    .of("CCC", "CCC", "CCC").of("C~C", "C-C", "CCC").of(0).build()
                    .atElement('C', StructureUtility.lazy(t -> ofBlock(t.getCasing()))).offset(1, 1, 0)
                    .build());
        } else {
            this.setStructure(TileEntityLargeTank.class, b -> b.part("main")
                    .of("CCCCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC").of("CC~CC", "C---C", "C---C", "C---C", "CCCCC").of(0).build()
                    .atElement('C', StructureUtility.lazy(t -> ofBlock(t.getCasing()))).offset(1, 1, 0)
                    .build());
        }
        this.capacity = capacity;
        String prefix = material == Wood ? "wood" : "metal";
        baseTexture(new Texture(GTIRef.ID, "block/casing/wall/" + prefix));
        overlayTexture((type, state, tier) -> {
            Texture blank = new Texture(GTIRef.ID, "block/machine/empty");
            return new Texture[]{blank, blank, blank, new Texture(GTIRef.ID, "block/casing/wall/" + prefix + "_tank_side_overlay"), blank, blank};
        });
        setAllowVerticalFacing(true);
    }

    public int getCapacity() {
        return capacity;
    }
}
