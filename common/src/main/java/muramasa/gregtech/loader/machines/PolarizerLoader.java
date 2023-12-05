package muramasa.gregtech.loader.machines;

import muramasa.antimatter.Ref;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTypeBlock;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.gregtech.data.RecipeMaps;
import net.minecraft.world.item.Item;

import static muramasa.antimatter.data.AntimatterMaterials.Iron;
import static muramasa.gregtech.data.Materials.*;

public class PolarizerLoader {
    public static void init(){
        Material[] materials = new Material[]{IronMagnetic, SteelMagnetic, NeodymiumMagnetic};
        Material[] materials2 = new Material[]{Iron, Steel, Neodymium};
        for (int i = 0; i < materials.length; i++) {
            Material out = materials[i];
            Material in = materials2[i];
            out.getTypes().forEach(t -> {
                if (t.getUnitValue() <= 0) return;
                if (!in.has(t)) return;
                Item output = t instanceof MaterialTypeItem<?> typeItem ? typeItem.get(out) : t instanceof MaterialTypeBlock<?> typeBlock && typeBlock.get() instanceof MaterialTypeBlock.IBlockGetter getter ? getter.get(out).asItem() : null;
                if (output != null){
                    RecipeMaps.POLARIZER.RB().ii(t.getMaterialIngredient(in, 1)).io(output).add(in.getId() + "_" + t.getId() + "_to_" + out.getId(), Math.max(1, (128 * t.getUnitValue()) / Ref.U), 16);
                }
            });
        }
    }
}
