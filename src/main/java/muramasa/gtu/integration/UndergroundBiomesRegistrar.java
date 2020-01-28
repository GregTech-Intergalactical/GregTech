//package muramasa.gtu.integration;
//
//import muramasa.gtu.Configs;
//import muramasa.gtu.Ref;
//import muramasa.antimatter.GregTechAPI;
//import muramasa.antimatter.materials.MaterialType;
//import muramasa.antimatter.ore.BlockOre;
//import muramasa.antimatter.ore.OreType;
//import muramasa.antimatter.ore.StoneType;
//import muramasa.antimatter.registration.IGregTechRegistrar;
//import muramasa.antimatter.registration.RegistrationEvent;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.oredict.OreDictionary;
//
//import java.util.ArrayList;
//
//import org.apache.commons.lang3.StringUtils;
//
//import exterminatorjeff.undergroundbiomes.api.API;
//import exterminatorjeff.undergroundbiomes.intermod.ModOreRegistrar;
//import exterminatorjeff.undergroundbiomes.intermod.UBOreConfig;
//
//import static exterminatorjeff.undergroundbiomes.api.API.*;
//import static exterminatorjeff.undergroundbiomes.api.enums.IgneousVariant.*;
//import static exterminatorjeff.undergroundbiomes.api.enums.MetamorphicVariant.*;
//import static exterminatorjeff.undergroundbiomes.api.enums.SedimentaryVariant.*;
//
//public class UndergroundBiomesRegistrar implements IGregTechRegistrar {
//
//    /*
//    //UB Igneous
//    public static StoneType UB_RED_GRANITE = new StoneType("ub_red_granite", Configs.MODCOMPAT.ENABLE_UB_CROSS_OREDICT ? "granite_red" : "", Ref.MOD_UB, Materials.GraniteRed, new Texture(Ref.MOD_UB, "blocks/red_granite"));
//    public static StoneType UB_BLACK_GRANITE = new StoneType("ub_black_granite", Configs.MODCOMPAT.ENABLE_UB_CROSS_OREDICT ? "granite_black" : "", Ref.MOD_UB, Materials.GraniteBlack, new Texture(Ref.MOD_UB, "blocks/black_granite"));
//    public static StoneType UB_RHYOLITE = new StoneType("ub_rhyolite", Ref.MOD_UB, new Texture(Ref.MOD_UB, "blocks/rhyolite"));
//    public static StoneType UB_ANDESITE = new StoneType("ub_andesite", Ref.MOD_UB, new Texture(Ref.MOD_UB, "blocks/andesite"));
//    public static StoneType UB_GABBRO = new StoneType("ub_gabbro", Ref.MOD_UB, new Texture(Ref.MOD_UB, "blocks/gabbro"));
//    public static StoneType UB_BASALT = new StoneType("ub_basalt", Configs.MODCOMPAT.ENABLE_UB_CROSS_OREDICT ? "basalt" : "", Ref.MOD_UB, Materials.Basalt, new Texture(Ref.MOD_UB, "blocks/basalt"));
//    public static StoneType UB_KOMATIITE = new StoneType("ub_komatiite", Ref.MOD_UB, new Texture(Ref.MOD_UB, "blocks/komatiite"));
//    public static StoneType UB_DACITE = new StoneType("ub_dacite", Ref.MOD_UB, new Texture(Ref.MOD_UB, "blocks/dacite"));
//
//    //UB Metamorphic
//    public static StoneType UB_GNEISS = new StoneType("ub_gneiss", Ref.MOD_UB, new Texture(Ref.MOD_UB, "blocks/gneiss"));
//    public static StoneType UB_ECLOGITE = new StoneType("ub_eclogite", Ref.MOD_UB, new Texture(Ref.MOD_UB, "blocks/eclogite"));
//    public static StoneType UB_MARBLE = new StoneType("ub_marble", Configs.MODCOMPAT.ENABLE_UB_CROSS_OREDICT ? "marble" : "", Ref.MOD_UB, Materials.Marble, new Texture(Ref.MOD_UB, "blocks/marble"));
//    public static StoneType UB_QUARTZITE = new StoneType("ub_quartzite", Ref.MOD_UB, new Texture(Ref.MOD_UB, "blocks/quartzite"));
//    public static StoneType UB_BLUE_SCHIST = new StoneType("ub_blue_schist", Ref.MOD_UB, new Texture(Ref.MOD_UB, "blocks/blueschist"));
//    public static StoneType UB_GREEN_SCHIST = new StoneType("ub_green_schist", Ref.MOD_UB, new Texture(Ref.MOD_UB, "blocks/greenschist"));
//    public static StoneType UB_SOAPSTONE = new StoneType("ub_soapstone", Ref.MOD_UB, new Texture(Ref.MOD_UB, "blocks/soapstone"));
//    public static StoneType UB_MIGMATITE = new StoneType("ub_migmatite", Ref.MOD_UB, new Texture(Ref.MOD_UB, "blocks/migmatite"));
//
//    //UB Sedimentary
//    public static StoneType UB_LIMESTONE = new StoneType("ub_limestone", Ref.MOD_UB, new Texture(Ref.MOD_UB, "blocks/limestone"));
//    public static StoneType UB_CHALK = new StoneType("ub_chalk", Ref.MOD_UB, new Texture(Ref.MOD_UB, "blocks/chalk"));
//    public static StoneType UB_SHALE = new StoneType("ub_shale", Ref.MOD_UB, new Texture(Ref.MOD_UB, "blocks/shale"));
//    public static StoneType UB_SILTSTONE = new StoneType("ub_siltstone", Ref.MOD_UB, new Texture(Ref.MOD_UB, "blocks/siltstone"));
//    public static StoneType UB_LIGNITE = new StoneType("ub_lignite", Ref.MOD_UB, new Texture(Ref.MOD_UB, "blocks/lignite")); //Should we have lignite ores?
//    public static StoneType UB_DOLOMITE = new StoneType("ub_dolomite", Ref.MOD_UB, new Texture(Ref.MOD_UB, "blocks/dolomite"));
//    public static StoneType UB_GREYWACKE = new StoneType("ub_greywacke", Ref.MOD_UB, new Texture(Ref.MOD_UB, "blocks/greywacke"));
//    public static StoneType UB_CHERT = new StoneType("ub_chert", Ref.MOD_UB, new Texture(Ref.MOD_UB, "blocks/chert"));
//    */
//
//    @Override
//    public String getId() {
//        return Ref.MOD_UB;
//    }
//
//    @Override
//    public void onRegistrationEvent(RegistrationEvent event) {
//        switch (event) {
//            /*case DATA:
//                BlockOre.addStoneSet("ub", new StoneType[] {
//                        UB_RED_GRANITE, UB_BLACK_GRANITE, UB_RHYOLITE, UB_ANDESITE, UB_GABBRO, UB_BASALT, UB_KOMATIITE, UB_DACITE, UB_GNEISS, UB_ECLOGITE, UB_MARBLE, UB_QUARTZITE,
//                        UB_BLUE_SCHIST, UB_GREEN_SCHIST, UB_SOAPSTONE, UB_MIGMATITE, UB_LIMESTONE, UB_CHALK, UB_SHALE, UB_SILTSTONE, UB_LIGNITE, UB_DOLOMITE, UB_GREYWACKE, UB_CHERT
//                });
//                break;*/
//            case BLOCK:
//                MaterialType.ORE.all().forEach(m -> {
//                    BlockOre ore = (BlockOre) BlockOre.get(OreType.NORMAL, m, StoneType.STONE).getBlock();
//                    ResourceLocation loc = m.getSet().getTexture(MaterialType.ORE, 0);
//                    ArrayList<String> oreNames = new ArrayList<>();
//                    oreNames.add("ore".concat(StringUtils.capitalize(m.getId())));
//                    API.ORES_REGISTRY.requestOreSetup(ore, new UBOreConfig(ore.getId(), 0, loc.toString(), oreNames, Integer.toString(m.getRGB())));
//                    API.ORES_REGISTRY.registerOreOverlay(ore, 0, loc);
//                });
//                MaterialType.ORE_SMALL.all().forEach(m -> {
//                    BlockOre ore = (BlockOre) BlockOre.get(OreType.SMALL, m, StoneType.STONE).getBlock();
//                    ResourceLocation loc = m.getSet().getTexture(MaterialType.ORE_SMALL, 0);
//                    ArrayList<String> oreNames = new ArrayList<>();
//                    oreNames.add("oreSmall".concat(StringUtils.capitalize(m.getId())));
//                    API.ORES_REGISTRY.requestOreSetup(ore, new UBOreConfig(ore.getId(), 0, loc.toString(), oreNames, Integer.toString(m.getRGB())));
//                    API.ORES_REGISTRY.registerOreOverlay(ore, 0, loc);
//                });
//            case RECIPE:
//                if (Configs.MODCOMPAT.ENABLE_UB_CROSS_OREDICT) {
//                    MaterialType.ORE.all().forEach(m -> {
//                        String matId = m.getId();
//                        String capitalizedMatId = StringUtils.capitalize(matId);
//                        String baseName = "ore_".concat(matId);
//                        Block igneousBlock = GregTechAPI.getBlock(Ref.MOD_UB, "igneous_stone_gtu_".concat(baseName));
//                        OreDictionary.registerOre("oreGraniteRed".concat(capitalizedMatId), new ItemStack(igneousBlock, 1, 0));
//                        OreDictionary.registerOre("oreGraniteBlack".concat(capitalizedMatId), new ItemStack(igneousBlock, 1, 1));
//                        OreDictionary.registerOre("oreBasalt".concat(capitalizedMatId), new ItemStack(igneousBlock, 1, 5));
//                        OreDictionary.registerOre("oreMarble".concat(capitalizedMatId), new ItemStack(GregTechAPI.getBlock(Ref.MOD_UB, "metamorphic_stone_gtu_".concat(baseName)), 1, 2));
//                    });
//                    MaterialType.ORE_SMALL.all().forEach(m -> {
//                        String matId = m.getId();
//                        String capitalizedMatId = StringUtils.capitalize(matId);
//                        String baseName = "ore_small_".concat(matId);
//                        Block igneousBlock = GregTechAPI.getBlock(Ref.MOD_UB, "igneous_stone_gtu_".concat(baseName));
//                        OreDictionary.registerOre("oreSmallGraniteRed".concat(capitalizedMatId), new ItemStack(igneousBlock, 1, 0));
//                        OreDictionary.registerOre("oreSmallGraniteBlack".concat(capitalizedMatId), new ItemStack(igneousBlock, 1, 1));
//                        OreDictionary.registerOre("oreSmallBasalt".concat(capitalizedMatId), new ItemStack(igneousBlock, 1, 5));
//                        OreDictionary.registerOre("oreSmallMarble".concat(capitalizedMatId), new ItemStack(GregTechAPI.getBlock(Ref.MOD_UB, "metamorphic_stone_gtu_".concat(baseName)), 1, 2));
//                    });
//                }
//                break;
//            case WORLDGEN:
//              /*IBlockState IGNEOUS = IGNEOUS_STONE.getBlock().getDefaultState();
//                IBlockState METAMORPHIC = METAMORPHIC_STONE.getBlock().getDefaultState();
//                IBlockState SEDIMENTARY = SEDIMENTARY_STONE.getBlock().getDefaultState();
//                UB_RED_GRANITE.setBaseState(IGNEOUS.withProperty(IGNEOUS_VARIANT_PROPERTY, RED_GRANITE));
//                UB_BLACK_GRANITE.setBaseState(IGNEOUS.withProperty(IGNEOUS_VARIANT_PROPERTY, BLACK_GRANITE));
//                UB_RHYOLITE.setBaseState(IGNEOUS.withProperty(IGNEOUS_VARIANT_PROPERTY, RHYOLITE));
//                UB_ANDESITE.setBaseState(IGNEOUS.withProperty(IGNEOUS_VARIANT_PROPERTY, ANDESITE));
//                UB_GABBRO.setBaseState(IGNEOUS.withProperty(IGNEOUS_VARIANT_PROPERTY, GABBRO));
//                UB_BASALT.setBaseState(IGNEOUS.withProperty(IGNEOUS_VARIANT_PROPERTY, BASALT));
//                UB_KOMATIITE.setBaseState(IGNEOUS.withProperty(IGNEOUS_VARIANT_PROPERTY, KOMATIITE));
//                UB_DACITE.setBaseState(IGNEOUS.withProperty(IGNEOUS_VARIANT_PROPERTY, DACITE));
//                UB_GNEISS.setBaseState(METAMORPHIC.withProperty(METAMORPHIC_VARIANT_PROPERTY, GNEISS));
//                UB_ECLOGITE.setBaseState(METAMORPHIC.withProperty(METAMORPHIC_VARIANT_PROPERTY, ECLOGITE));
//                UB_MARBLE.setBaseState(METAMORPHIC.withProperty(METAMORPHIC_VARIANT_PROPERTY, MARBLE));
//                UB_QUARTZITE.setBaseState(METAMORPHIC.withProperty(METAMORPHIC_VARIANT_PROPERTY, QUARTZITE));
//                UB_BLUE_SCHIST.setBaseState(METAMORPHIC.withProperty(METAMORPHIC_VARIANT_PROPERTY, BLUESCHIST));
//                UB_GREEN_SCHIST.setBaseState(METAMORPHIC.withProperty(METAMORPHIC_VARIANT_PROPERTY, GREENSCHIST));
//                UB_SOAPSTONE.setBaseState(METAMORPHIC.withProperty(METAMORPHIC_VARIANT_PROPERTY, SOAPSTONE));
//                UB_MIGMATITE.setBaseState(METAMORPHIC.withProperty(METAMORPHIC_VARIANT_PROPERTY, MIGMATITE));
//                UB_LIMESTONE.setBaseState(SEDIMENTARY.withProperty(SEDIMENTARY_VARIANT_PROPERTY, LIMESTONE));
//                UB_CHALK.setBaseState(SEDIMENTARY.withProperty(SEDIMENTARY_VARIANT_PROPERTY, CHALK));
//                UB_SHALE.setBaseState(SEDIMENTARY.withProperty(SEDIMENTARY_VARIANT_PROPERTY, SHALE));
//                UB_SILTSTONE.setBaseState(SEDIMENTARY.withProperty(SEDIMENTARY_VARIANT_PROPERTY, SILTSTONE));
//                UB_LIGNITE.setBaseState(SEDIMENTARY.withProperty(SEDIMENTARY_VARIANT_PROPERTY, LIGNITE));
//                UB_DOLOMITE.setBaseState(SEDIMENTARY.withProperty(SEDIMENTARY_VARIANT_PROPERTY, DOLOMITE));
//                UB_GREYWACKE.setBaseState(SEDIMENTARY.withProperty(SEDIMENTARY_VARIANT_PROPERTY, GREYWACKE));
//                UB_CHERT.setBaseState(SEDIMENTARY.withProperty(SEDIMENTARY_VARIANT_PROPERTY, CHERT));
//
//                WorldGenHelper.STONE_MAP.put(API.IGNEOUS_STONE.getBlock().getDefaultState().withProperty(IgneousVariant.IGNEOUS_VARIANT_PROPERTY, IgneousVariant.RED_GRANITE), BlockOre.get(UB_RED_GRANIITE).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.IGNEOUS_STONE.getBlock().getDefaultState().withProperty(IgneousVariant.IGNEOUS_VARIANT_PROPERTY, IgneousVariant.BLACK_GRANITE), BlockOre.get(UB_BLACK_GRANIITE).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.IGNEOUS_STONE.getBlock().getDefaultState().withProperty(IgneousVariant.IGNEOUS_VARIANT_PROPERTY, IgneousVariant.RHYOLITE), BlockOre.get(UB_RHYOLITE).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.IGNEOUS_STONE.getBlock().getDefaultState().withProperty(IgneousVariant.IGNEOUS_VARIANT_PROPERTY, IgneousVariant.ANDESITE), BlockOre.get(UB_ANDESITE).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.IGNEOUS_STONE.getBlock().getDefaultState().withProperty(IgneousVariant.IGNEOUS_VARIANT_PROPERTY, IgneousVariant.GABBRO), BlockOre.get(UB_GABBRO).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.IGNEOUS_STONE.getBlock().getDefaultState().withProperty(IgneousVariant.IGNEOUS_VARIANT_PROPERTY, IgneousVariant.BASALT), BlockOre.get(UB_BASALT).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.IGNEOUS_STONE.getBlock().getDefaultState().withProperty(IgneousVariant.IGNEOUS_VARIANT_PROPERTY, IgneousVariant.KOMATIITE), BlockOre.get(UB_KOMATIITE).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.IGNEOUS_STONE.getBlock().getDefaultState().withProperty(IgneousVariant.IGNEOUS_VARIANT_PROPERTY, IgneousVariant.DACITE), BlockOre.get(UB_DACITE).getDefaultState());
//
//                WorldGenHelper.STONE_MAP.put(API.METAMORPHIC_STONE.getBlock().getDefaultState().withProperty(MetamorphicVariant.METAMORPHIC_VARIANT_PROPERTY, MetamorphicVariant.GNEISS), BlockOre.get(UB_GNEISS).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.METAMORPHIC_STONE.getBlock().getDefaultState().withProperty(MetamorphicVariant.METAMORPHIC_VARIANT_PROPERTY, MetamorphicVariant.ECLOGITE), BlockOre.get(UB_ECLOGITE).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.METAMORPHIC_STONE.getBlock().getDefaultState().withProperty(MetamorphicVariant.METAMORPHIC_VARIANT_PROPERTY, MetamorphicVariant.MARBLE), BlockOre.get(UB_MARBLE).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.METAMORPHIC_STONE.getBlock().getDefaultState().withProperty(MetamorphicVariant.METAMORPHIC_VARIANT_PROPERTY, MetamorphicVariant.QUARTZITE), BlockOre.get(UB_QUARTZITE).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.METAMORPHIC_STONE.getBlock().getDefaultState().withProperty(MetamorphicVariant.METAMORPHIC_VARIANT_PROPERTY, MetamorphicVariant.BLUESCHIST), BlockOre.get(UB_BLUE_SCHIST).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.METAMORPHIC_STONE.getBlock().getDefaultState().withProperty(MetamorphicVariant.METAMORPHIC_VARIANT_PROPERTY, MetamorphicVariant.GREENSCHIST), BlockOre.get(UB_GREEN_SCHIST).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.METAMORPHIC_STONE.getBlock().getDefaultState().withProperty(MetamorphicVariant.METAMORPHIC_VARIANT_PROPERTY, MetamorphicVariant.SOAPSTONE), BlockOre.get(UB_SOAPSTONE).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.METAMORPHIC_STONE.getBlock().getDefaultState().withProperty(MetamorphicVariant.METAMORPHIC_VARIANT_PROPERTY, MetamorphicVariant.MIGMATITE), BlockOre.get(UB_MIGMATITE).getDefaultState());
//
//                WorldGenHelper.STONE_MAP.put(API.SEDIMENTARY_STONE.getBlock().getDefaultState().withProperty(SedimentaryVariant.SEDIMENTARY_VARIANT_PROPERTY, SedimentaryVariant.LIMESTONE), BlockOre.get(UB_LIMESTONE).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.SEDIMENTARY_STONE.getBlock().getDefaultState().withProperty(SedimentaryVariant.SEDIMENTARY_VARIANT_PROPERTY, SedimentaryVariant.CHALK), BlockOre.get(UB_CHALK).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.SEDIMENTARY_STONE.getBlock().getDefaultState().withProperty(SedimentaryVariant.SEDIMENTARY_VARIANT_PROPERTY, SedimentaryVariant.SHALE), BlockOre.get(UB_SHALE).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.SEDIMENTARY_STONE.getBlock().getDefaultState().withProperty(SedimentaryVariant.SEDIMENTARY_VARIANT_PROPERTY, SedimentaryVariant.SILTSTONE), BlockOre.get(UB_SILTSTONE).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.SEDIMENTARY_STONE.getBlock().getDefaultState().withProperty(SedimentaryVariant.SEDIMENTARY_VARIANT_PROPERTY, SedimentaryVariant.LIGNITE), BlockOre.get(UB_LIGNITE).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.SEDIMENTARY_STONE.getBlock().getDefaultState().withProperty(SedimentaryVariant.SEDIMENTARY_VARIANT_PROPERTY, SedimentaryVariant.DOLOMITE), BlockOre.get(UB_DOLOMITE).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.SEDIMENTARY_STONE.getBlock().getDefaultState().withProperty(SedimentaryVariant.SEDIMENTARY_VARIANT_PROPERTY, SedimentaryVariant.GREYWACKE), BlockOre.get(UB_GREYWACKE).getDefaultState());
//                WorldGenHelper.STONE_MAP.put(API.SEDIMENTARY_STONE.getBlock().getDefaultState().withProperty(SedimentaryVariant.SEDIMENTARY_VARIANT_PROPERTY, SedimentaryVariant.CHERT), BlockOre.get(UB_CHERT).getDefaultState());
//                break;*/
//        }
//    }
//}
