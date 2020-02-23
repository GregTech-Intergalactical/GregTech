//package muramasa.gti.tools;
//
//import muramasa.antimatter.items.MaterialTool;
//import muramasa.antimatter.materials.Material;
//import muramasa.antimatter.tools.AntimatterToolType;
//import net.minecraft.item.ItemStack;
//
//public class ToolPlunger extends MaterialTool {
//
//    public ToolPlunger(String namespace) {
//        super(namespace, AntimatterToolType.PLUNGER);
//    }
//
//    public ToolPlunger(String namespace, AntimatterToolType type) {
//        super(namespace, type);
//    }
//
//    @Override
//    public int getRGB(ItemStack stack, int i) {
//        Material mat = getSecondary(stack);
//        return i == 0 ? -1 : mat != null ? mat.getRGB() : -1;
//    }
//}
