//package muramasa.gtu.loaders;
//
//import org.apache.commons.lang3.StringUtils;
//
//import muramasa.antimatter.GregTechAPI;
//import muramasa.antimatter.blocks.BlockStorage;
//import muramasa.antimatter.items.MaterialItem;
//import muramasa.antimatter.items.MaterialTool;
//import muramasa.antimatter.materials.Material;
//import muramasa.antimatter.materials.MaterialType;
//import muramasa.antimatter.ore.BlockOre;
//import muramasa.antimatter.ore.StoneType;
//import muramasa.antimatter.util.Utils;
//import muramasa.gtu.common.Data;
//import net.minecraft.item.EnumDyeColor;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.oredict.OreDictionary;
//
//public class OreDictLoader {
//
//    public static void init() {
//        //Register materialItem entries (materialTypeMaterialName)
//        GregTechAPI.all(MaterialItem.class).forEach(i -> {
//            Material material = i.getMaterial();
//            OreDictionary.registerOre(i.getType().oreName(material), i);
//            if (i.getType() == MaterialType.ROD) {
//                OreDictionary.registerOre("stick".concat(Utils.underscoreToUpperCamel(material.getId())), i);
//            }
//            if (i.getType() == MaterialType.LENS) { // Can apply to other MaterialItems too
//                EnumDyeColor colour = Utils.determineColour(material.getRGB());
//                OreDictionary.registerOre(i.getType().getId().concat(Utils.underscoreToUpperCamel(colour.getName())), i);
//            }
//        });
//
//        GregTechAPI.all(BlockOre.class).forEach(o -> {
//            StoneType[] stoneTypes = o.getStoneTypesFromSet(o.getSetId());
//            for (int i = 0; i < stoneTypes.length; i++) {
//                StoneType currentType = stoneTypes[i];
//                String oreName = o.getType().getMaterialType().getId() + "_" + currentType.getOreId() + "_" + o.getMaterial().getId();
//                if (currentType == StoneType.ENDSTONE || currentType == StoneType.NETHERRACK) {
//                    OreDictionary.registerOre(Utils.underscoreToLowerCamel(oreName), new ItemStack(o, 1, i));
//                    oreName = o.getType().getMaterialType().getId() + "_" + currentType.getId() + "_" + o.getMaterial().getId();
//                    OreDictionary.registerOre(Utils.underscoreToLowerCamel(oreName), new ItemStack(o, 1, i));
//                }
//                else OreDictionary.registerOre(Utils.underscoreToLowerCamel(oreName), new ItemStack(o, 1, i));
//            }
//        });
//
//        GregTechAPI.all(BlockStorage.class).forEach(b -> {
//            Material[] materials = b.getMaterials();
//            for (int i = 0; i < materials.length; i++) {
//                String matId = Utils.underscoreToUpperCamel(StringUtils.capitalize(materials[i].getId()));
//                if (b.getType() == MaterialType.BLOCK) OreDictionary.registerOre("block".concat(matId), new ItemStack(b, 1, i));
//                else OreDictionary.registerOre("frame".concat(matId), new ItemStack(b, 1, i));
//            }
//        });
//
//        //Register craftingTool entries (craftingToolType)
//        GregTechAPI.all(MaterialTool.class).forEach(t -> OreDictionary.registerOre(t.getType().getOreDict(), t));
//
//        //OreDictionary.registerOre("resinSticky", Data.StickyResin);
//        OreDictionary.registerOre("dropRubber", Data.StickyResin);
//        OreDictionary.registerOre("craftingSawDiamond", Data.DiamondSawBlade);
//        OreDictionary.registerOre("craftingGrinderDiamond", Data.DiamondGrindHead);
//        OreDictionary.registerOre("craftingGrinderTungsten", Data.TungstenGrindHead);
//        OreDictionary.registerOre("ingotAlloyIridium", Data.IridiumAlloyIngot);
//        OreDictionary.registerOre("plateReinforcedIridium", Data.IridiumReinforcedPlate);
//    }
//}
