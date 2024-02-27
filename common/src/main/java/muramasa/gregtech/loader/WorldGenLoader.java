package muramasa.gregtech.loader;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.event.WorldGenEvent;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.util.TagUtils;
import muramasa.antimatter.worldgen.StoneLayerOre;
import muramasa.antimatter.worldgen.object.WorldGenStoneLayerBuilder;
import muramasa.antimatter.worldgen.smallore.WorldGenSmallOreBuilder;
import muramasa.antimatter.worldgen.vanillaore.WorldGenVanillaOreBuilder;
import muramasa.antimatter.worldgen.vein.WorldGenVeinLayerBuilder;
import muramasa.gregtech.GregTechConfig;
import muramasa.gregtech.worldgen.OilSpoutFluid;
import muramasa.gregtech.worldgen.OilSpoutSavedData;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;

import static io.github.gregtechintergalactical.gtcore.data.GTCoreBlocks.*;
import static muramasa.antimatter.Ref.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.ORE_STONE;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.data.AntimatterStoneTypes.*;
import static muramasa.antimatter.data.AntimatterStoneTypes.DIORITE;
import static muramasa.gregtech.data.Materials.*;
import static net.minecraft.world.level.Level.END;
import static net.minecraft.world.level.Level.NETHER;
import static net.minecraft.world.level.Level.OVERWORLD;

public class WorldGenLoader {

    public static final ResourceKey<Level> TWILIGHT_FOREST = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("twilightforest", "twilight_forest"));
    public static final ResourceKey<Level> BE_MOON = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("beyond_earth", "moon"));
    public static final ResourceKey<Level> AA_MOON = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("ad_astra", "moon"));
    public static final ResourceKey<Level> BE_MARS = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("beyond_earth", "mars"));
    public static final ResourceKey<Level> AA_MARS = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("ad_astra", "mars"));
    public static final ResourceKey<Level> BE_MERCURY = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("beyond_earth", "mercury"));
    public static final ResourceKey<Level> AA_MERCURY = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("ad_astra", "mercury"));
    public static final ResourceKey<Level> BE_VENUS = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("beyond_earth", "venus"));
    public static final ResourceKey<Level> AA_VENUS = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("ad_astra", "venus"));


    public static void init(WorldGenEvent ev) {
        if (AntimatterConfig.STONE_LAYERS.get()) {
            initStoneVeins(ev);
        }
        if (AntimatterConfig.ORE_VEINS.get()) {
            initOreVeins(ev);
        }
        if (AntimatterConfig.SMALL_ORES.get() && !AntimatterAPI.isModLoaded(MOD_TFC)){
            initSmallOres(ev);
        }
        OilSpoutSavedData.clearFluidMap();
        OilSpoutFluid.resetTotalWeight();
        new OilSpoutFluid("oil", Oil.getLiquid(), 20, 625, 4, 5);
        new OilSpoutFluid("light_oil", OilLight.getLiquid(), 20, 625, 3, 6);
        new OilSpoutFluid("heavy_oil", OilHeavy.getLiquid(), 20, 625, 5, 4);
        new OilSpoutFluid("natural_gas", NaturalGas.getGas(), 20, 625, 4, 7);
        if (AntimatterAPI.isModLoaded(MOD_TWILIGHT)){
            initTwilightForestOres(ev);
        }
    }

    private static void initTwilightForestOres(WorldGenEvent event){
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Coal).withMaterialType(ORE_STONE).withSize(50).withWeight(1).atHeight(-16, 0).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Lignite).withMaterialType(ORE_STONE).withSize(50).withWeight(1).atHeight(-16, 0).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Salt).withMaterialType(ORE_STONE).withSize(50).withWeight(1).atHeight(-16, 0).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(RockSalt).withMaterialType(ORE_STONE).withSize(50).withWeight(1).atHeight(-16, 0).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Bauxite).withMaterialType(ORE_STONE).withSize(50).withWeight(1).atHeight(-16, 0).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(OilShale).withMaterialType(ORE_STONE).withSize(50).withWeight(1).atHeight(-16, 0).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(MilkyQuartz).withSize(50).withWeight(1).atHeight(-16, 0).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());

        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Sulfur).withSize(16).withWeight(100).setRare(true).atHeight(-32, -24).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Apatite).withSize(16).withWeight(50).setRare(true).atHeight(-8, 0).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Ruby).withSize(12).withWeight(100).setRare(true).atHeight(8, 22).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Amber).withSize(12).withWeight(100).setRare(true).atHeight(8, 22).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Amethyst).withSize(12).withWeight(100).setRare(true).atHeight(8, 22).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Galena).withSize(24).withWeight(25).setRare(true).atHeight(-24, 0).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Tetrahedrite).withSize(24).withWeight(25).setRare(true).atHeight(-24, 0).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Cassiterite).withSize(24).withWeight(25).setRare(true).atHeight(-24, 0).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Sheldonite).withSize(6).withWeight(100).setRare(true).atHeight(8, 22).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Pentlandite).withSize(16).withWeight(25).setRare(true).atHeight(-24, -8).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Scheelite).withSize(12).withWeight(25).setRare(true).atHeight(-24, -8).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Rutile).withSize(6).withWeight(25).setRare(true).atHeight(-24, -8).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Bastnasite).withSize(16).withWeight(100).setRare(true).atHeight(8, 22).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Graphite).withSize(6).withWeight(50).setRare(true).atHeight(-32, -24).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Pitchblende).withSize(16).withWeight(100).setRare(true).atHeight(-24, -16).withDimensions(TWILIGHT_FOREST.location()).buildMaterial());
    }

    private static void initSmallOres(WorldGenEvent event){
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Copper).withAmountPerChunk(32).atHeight(16, 126).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Tin).withAmountPerChunk(32).atHeight(16, 126).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Bismuth).withAmountPerChunk(8).atHeight(76, 196).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Coal).withAmountPerChunk(24).atHeight(16, 126).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Iron).withAmountPerChunk(16).atHeight(16, 61).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Lead).withAmountPerChunk(16).atHeight(16, 61).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Zinc).withAmountPerChunk(12).atHeight(16, 96).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Gold).withAmountPerChunk(8).atHeight(-34, 16).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Silver).withAmountPerChunk(8).atHeight(-34, 16).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Nickel).withAmountPerChunk(8).atHeight(-34, 16).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Lapis).withAmountPerChunk(4).atHeight(-34, 16).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Diamond).withAmountPerChunk(3).atHeight(-59, -52).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Emerald).withAmountPerChunk(2).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Ruby).withAmountPerChunk(2).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Sapphire).withAmountPerChunk(2).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(GreenSapphire).withAmountPerChunk(2).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Olivine).withAmountPerChunk(2).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Topaz).withAmountPerChunk(2).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Tanzanite).withAmountPerChunk(2).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Amethyst).withAmountPerChunk(2).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Opal).withAmountPerChunk(2).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Jade).withAmountPerChunk(2).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(BlueTopaz).withAmountPerChunk(2).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Amber).withAmountPerChunk(2).buildMaterial());
        //event.smallOre(new WorldGenSmallOreBuilder().withMaterial(FoolsRuby).withAmountPerChunk(1).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(RedGarnet).withAmountPerChunk(2).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(YellowGarnet).withAmountPerChunk(2).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Redstone).withAmountPerChunk(8).atHeight(-59, -34).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Chromite).withAmountPerChunk(8).atHeight(20, 50).withDimensions(END.location()).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Platinum).withAmountPerChunk(8).atHeight(20, 40).withDimensions(END.location()).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Iridium).withAmountPerChunk(8).atHeight(20, 40).withDimensions(END.location()).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Quartz).withAmountPerChunk(64).atHeight(30, 120).withDimensions(NETHER.location()).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Saltpeter).withAmountPerChunk(8).atHeight(10, 60).withDimensions(NETHER.location()).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Sulfur).withAmountPerChunk(32).atHeight(5, 60).withDimensions(NETHER.location()).buildMaterial());
        event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Sulfur).withAmountPerChunk(8).atHeight(-59, -34).withCustomId("sulfur_overworld").buildMaterial());
    }

    private static void initStoneVeins(WorldGenEvent ev) {
        if (GregTechConfig.GT6_ORE_GEN.get()){
            ev.stoneLayer(new WorldGenStoneLayerBuilder("stone_ores").withStone(STONE).withWeight(1).addOres(
                    new StoneLayerOre(Emerald, U48, 16, 60).addFilteredBiome(BiomeTags.IS_MOUNTAIN),
                    new StoneLayerOre(Diamond, U128, -56, -40),
                    new StoneLayerOre(Lapis, U48, 16, 48),
                    new StoneLayerOre(Redstone, U32, 8, 24),
                    new StoneLayerOre(Gold, U64, 8, 32),
                    new StoneLayerOre(Gold, U32, 33, 64).addFilteredBiome(Biomes.BADLANDS).addFilteredBiome(Biomes.ERODED_BADLANDS),
                    new StoneLayerOre(Copper, U16, 20, 50),
                    new StoneLayerOre(Iron, U16, 40, 80)
            ).buildVein()); //Ores
            ev.stoneLayer(new WorldGenStoneLayerBuilder("deepslate_ores").withStone(STONE).withWeight(1).addOres(
                    new StoneLayerOre(Emerald, U64, 0, 32).addFilteredBiome(BiomeTags.IS_MOUNTAIN),
                    new StoneLayerOre(Diamond, U64, -64, -52).addFilteredBiome(BiomeTags.IS_JUNGLE),
                    new StoneLayerOre(Lapis, U12, 16, 24).addFilteredBiome(BiomeTags.IS_TAIGA).addFilteredBiome(Biomes.FROZEN_PEAKS).addFilteredBiome(Biomes.ICE_SPIKES),
                    new StoneLayerOre(Amber, U32, 16, 24).addFilteredBiome(BiomeTags.IS_OCEAN).addFilteredBiome(BiomeTags.IS_DEEP_OCEAN),
                    new StoneLayerOre(Redstone, U16, 0, 20),
                    new StoneLayerOre(Cinnabar, U64, 0, 20).addFilteredBiome(BiomeTags.IS_TAIGA).addFilteredBiome(Biomes.BADLANDS).addFilteredBiome(Biomes.ERODED_BADLANDS),
                    new StoneLayerOre(Uraninite, U64, 0, 12).addFilteredBiome(BiomeTags.IS_JUNGLE),
                    new StoneLayerOre(Thorium, U64, 0, 12).addFilteredBiome(BiomeTags.IS_JUNGLE),
                    new StoneLayerOre(Scheelite, U64, 0, 12).addFilteredBiome(Biomes.FROZEN_PEAKS).addFilteredBiome(Biomes.ICE_SPIKES)).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("granite_ores").withStone(GRANITE).withWeight(1).addOres(
                    new StoneLayerOre(BlueTopaz, U64, -24, 0).addFilteredBiome(BiomeTags.IS_DEEP_OCEAN).addFilteredBiome(BiomeTags.IS_OCEAN).addFilteredBiome(BiomeTags.IS_BEACH),
                    new StoneLayerOre(Topaz, U64, 24, 48).addFilteredBiome(Biomes.FROZEN_PEAKS).addFilteredBiome(Biomes.ICE_SPIKES)
            ).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("granite_ores_2").withStone(GRANITE).withWeight(1).addOres(
                    new StoneLayerOre(Apatite, U8, 32, 64),
                    new StoneLayerOre(Phosphate, U24, 36, 60),
                    new StoneLayerOre(TricalciumPhosphate, U24, 40, 56)
            ).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("diorite_ores").withStone(DIORITE).withWeight(1).addOres(
                    new StoneLayerOre(Sapphire, U64, -24, 0).addFilteredBiome(BiomeTags.IS_OCEAN).addFilteredBiome(BiomeTags.IS_DEEP_OCEAN).addFilteredBiome(BiomeTags.IS_BEACH),
                    new StoneLayerOre(GreenSapphire, U64, -8, 16).addFilteredBiome(BiomeTags.IS_JUNGLE),
                    new StoneLayerOre(Ruby, U64, -8, 16).addFilteredBiome(TagUtils.getBiomeTag(new ResourceLocation("is_desert"))).addFilteredBiome(Biomes.DESERT)
            ).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("diorite_ores_2").withStone(DIORITE).withWeight(1).addOres(
                    new StoneLayerOre(Garnierite, U8, 16, 48),
                    new StoneLayerOre(Pentlandite, U8, 24, 56),
                    new StoneLayerOre(Cobaltite, U8, 32, 64),
                    new StoneLayerOre(Amethyst, U64, 24, 48).addFilteredBiome(BiomeTags.IS_TAIGA)
            ).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("andesite_ores").withStone(ANDESITE).withWeight(1).addOres(
                    new StoneLayerOre(Gold, U12, -64, -32),
                    new StoneLayerOre(Gold, U8, 32, 64).addFilteredBiome(Biomes.BADLANDS).addFilteredBiome(Biomes.ERODED_BADLANDS)
            ).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("tuff_ores").withStone(TUFF).withWeight(1).addOres(
                    new StoneLayerOre(Magnetite, U8, -16, 84),
                    new StoneLayerOre(VanadiumMagnetite, U64, -16, 16)
            ).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("black_granite_ores").withStone(BLACK_GRANITE).withWeight(1).addOres(
                    new StoneLayerOre(Sheldonite, U32, -64, -32),
                    new StoneLayerOre(Iridium, U64, -64, -46)
            ).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("red_granite_ores").withStone(RED_GRANITE).withWeight(1).addOres(
                    new StoneLayerOre(Pitchblende, U32, -32, 0),
                    new StoneLayerOre(Uraninite, U32, -32, 0),
                    new StoneLayerOre(Tantalite, U16, -32, 0)
            ).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("komatiite_ores").withStone(KOMATIITE).withWeight(1).addOres(
                    new StoneLayerOre(Magnesite, U16, -54, -9),
                    new StoneLayerOre(Cinnabar, U12, -64, -19),
                    new StoneLayerOre(Redstone, U8, -54, -9),
                    new StoneLayerOre(Pyrite, U12, 5, 66)
            ).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("kimberlite_ores").withStone(KIMBERLITE).withWeight(1).addOres(
                    new StoneLayerOre(Diamond, U48, -64, -52)
            ).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("basalt_ores").withStone(BASALT).withWeight(1).addOres(
                    new StoneLayerOre(Olivine, U32, -48, -16),
                    new StoneLayerOre(Uvarovite, U32, -40, -8),
                    new StoneLayerOre(Grossular, U32, -32, 0),
                    new StoneLayerOre(Chromite, U8, -16, 16)
            ).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("basalt_ores_2").withStone(BASALT).withWeight(1).addOres(
                    new StoneLayerOre(Bastnasite, U24, 24, 32),
                    new StoneLayerOre(Monazite, U32, 24, 32),
                    new StoneLayerOre(Manganese, U8, 16, 48)
            ).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("marble_ores").withStone(MARBLE).withWeight(1).addOres(
                    new StoneLayerOre(Cassiterite, U16, 20, 120),
                    new StoneLayerOre(Tin, U16, 10, 100),
                    new StoneLayerOre(Sphalerite, U8 , 10, 50),
                    new StoneLayerOre(Chalcopyrite, U8 ,  0, 40),
                    new StoneLayerOre(Pyrite, U12,  0, 50)
            ).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("limestone_ores").withStone(LIMESTONE).withWeight(1).addOres(
                    new StoneLayerOre(Stibnite, U24, 10, 30),
                    new StoneLayerOre(Galena, U8, 30, 120),
                    new StoneLayerOre(Lead, U16, 50, 70)
            ).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("limestone_ores_2").withStone(LIMESTONE).withWeight(1).addOres(
                    new StoneLayerOre(Pyrite, U16, 0, 30),
                    new StoneLayerOre(Galena, U8, 5, 25),
                    new StoneLayerOre(Galena, U8, 80, 120),
                    new StoneLayerOre(Powellite, U32, 35, 50),
                    new StoneLayerOre(Molybdenite, U128, 30, 50),
                    new StoneLayerOre(Tetrahedrite, U8, 40, 80),
                    new StoneLayerOre(Copper, U16, 40, 80)
            ).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("limestone_ores_3").withStone(LIMESTONE).withWeight(1).addOres(
                    new StoneLayerOre(Scheelite, U64, -64, -48),
                    new StoneLayerOre(Tungstate, U64, -64, -48),
                    new StoneLayerOre(YellowLimonite, U8, -48, -16),
                    new StoneLayerOre(BrownLimonite, U8, -32, 0),
                    new StoneLayerOre(Malachite, U12, -48, 0)
            ).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("green_schist_ores").withStone(GREEN_SCHIST).withWeight(1).addOres(
                    new StoneLayerOre(Andradite, U32, -40, 8),
                    new StoneLayerOre(Almandine, U32, -32, 0)
            ).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("blue_schist_ores").withStone(BLUE_SCHIST).withWeight(1).addOres(
                    new StoneLayerOre(Spessartine, U32, -40, 8),
                    new StoneLayerOre(Pyrope, U32, -32, 0)
            ).buildVein());
            if (AntimatterAPI.isModLoaded(MOD_AE)){
                ev.stoneLayer(new WorldGenStoneLayerBuilder("quartzite_ores").withStone(QUARTZITE).withWeight(1).addOres(
                        new StoneLayerOre(CertusQuartz, U16, 16, 48),
                        new StoneLayerOre(MilkyQuartz, U16, 16, 48),
                        new StoneLayerOre(Barite, U32, 0, 32)
                ).buildVein());
            } else {
                ev.stoneLayer(new WorldGenStoneLayerBuilder("quartzite_ores").withStone(QUARTZITE).withWeight(1).addOres(
                        new StoneLayerOre(MilkyQuartz, U16, 16, 48),
                        new StoneLayerOre(Barite, U32, 0, 32)
                ).buildVein());
            }
            ev.stoneLayer(new WorldGenStoneLayerBuilder("kimberlite").withStone(KIMBERLITE).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("diorite").withStone(DIORITE).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("shale").withStone(SHALE).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("slate").withStone(SLATE).withWeight(1).buildVein());

            ev.addCollisionBothSides(ORE_STONE.get().get(Coal).asState(), STONE.getState(),
                    new StoneLayerOre(Amber, U4, 30, 70).addFilteredBiome(BiomeTags.IS_OCEAN).addFilteredBiome(BiomeTags.IS_DEEP_OCEAN).addFilteredBiome(BiomeTags.IS_BEACH),
                    new StoneLayerOre(Amber, U8, 30, 70).addFilteredBiome(BiomeTags.IS_RIVER));
            ev.addCollisionBothSides(ORE_STONE.get().get(Lignite).asState(), STONE.getState(),
                    new StoneLayerOre(Amber, U4, 30, 70).addFilteredBiome(BiomeTags.IS_OCEAN).addFilteredBiome(BiomeTags.IS_DEEP_OCEAN).addFilteredBiome(BiomeTags.IS_BEACH),
                    new StoneLayerOre(Amber, U8, 30, 70).addFilteredBiome(BiomeTags.IS_RIVER));
            ev.addCollisionBothSides(ORE_STONE.get().get(OilShale).asState(), STONE.getState(),
                    new StoneLayerOre(Amber, U4, 30, 70).addFilteredBiome(BiomeTags.IS_OCEAN).addFilteredBiome(BiomeTags.IS_DEEP_OCEAN).addFilteredBiome(BiomeTags.IS_BEACH),
                    new StoneLayerOre(Amber, U8, 30, 70).addFilteredBiome(BiomeTags.IS_RIVER));
            ev.addCollisionBothSides(BLACK_GRANITE.getState(), MARBLE.getState(),
                    new StoneLayerOre(Lapis, U8, 0, 48),
                    new StoneLayerOre(Sodalite, U16, 0, 48),
                    new StoneLayerOre(Lazurite, U16, 0, 48),
                    new StoneLayerOre(Pyrite, U16, 0, 48));
            ev.addCollisionTopBottom(BLACK_GRANITE.getState(), BASALT.getState(),
                    new StoneLayerOre(Diamond, U64, -64, -32),
                    new StoneLayerOre(Graphite, U8, -64, -32));
            ev.addCollisionBothSides(BLACK_GRANITE.getState(), GRANITE.getState(),
                    new StoneLayerOre(Zircon, U24, 0, 32));
            ev.addCollisionBothSides(BLACK_GRANITE.getState(), RED_GRANITE.getState(),
                    new StoneLayerOre(Zircon, U24, 0, 32));


        } else {
            ev.stoneLayer(new WorldGenStoneLayerBuilder("stone").withStone(STONE).withWeight(4).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("black_granite").withStone(BLACK_GRANITE).withWeight(2).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("red_granite").withStone(RED_GRANITE).withWeight(2).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("komatiite").withStone(KOMATIITE).withWeight(4).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("basalt").withStone(BASALT).withWeight(3).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("marble").withStone(MARBLE).withWeight(4).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("limestone").withStone(LIMESTONE).withWeight(3).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("green_schist").withStone(GREEN_SCHIST).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("blue_schist").withStone(BLUE_SCHIST).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("kimberlite").withStone(KIMBERLITE).withWeight(3).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("quartzite").withStone(QUARTZITE).withWeight(4).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("shale").withStone(SHALE).withWeight(3).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("slate").withStone(SLATE).withWeight(3).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("granite").withStone(GRANITE).withWeight(3).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("diorite").withStone(DIORITE).withWeight(3).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("andesite").withStone(ANDESITE).withWeight(4).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("tuff").withStone(TUFF).withWeight(3).buildVein());
        }
        ev.stoneLayer(new WorldGenStoneLayerBuilder("coal").withStone(ORE_STONE.get().get(Coal).asState()).withWeight(1).buildVein());
        ev.stoneLayer(new WorldGenStoneLayerBuilder("lignite").withStone(ORE_STONE.get().get(Lignite).asState()).withWeight(1).buildVein());
        ev.stoneLayer(new WorldGenStoneLayerBuilder("salt").withStone(ORE_STONE.get().get(Salt).asState()).withWeight(1).buildVein());
        ev.stoneLayer(new WorldGenStoneLayerBuilder("rock_salt").withStone(ORE_STONE.get().get(RockSalt).asState()).withWeight(1).buildVein());
        ev.stoneLayer(new WorldGenStoneLayerBuilder("bauxite").withStone(ORE_STONE.get().get(Bauxite).asState()).withWeight(1).buildVein());
        ev.stoneLayer(new WorldGenStoneLayerBuilder("oil_shale").withStone(ORE_STONE.get().get(OilShale).asState()).withWeight(1).buildVein());

        ev.addCollisionBothSides(BASALT.getState(), LIMESTONE.getState(),
                new StoneLayerOre(Ilmenite, U8, -64, 0),
                new StoneLayerOre(Rutile, U12, -64, 0)
        );

    }

    private static void initOreVeins(WorldGenEvent ev) {
        if (AntimatterAPI.isModLoaded(MOD_TFC) || GregTechConfig.GT6_ORE_GEN.get()){
            ev.vein(new WorldGenVeinLayerBuilder("gold").asOreVein(-4, 26, 160, 3, 32, Magnetite, Magnetite, VanadiumMagnetite, Gold,
                    BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("iron").asOreVein(-14, 51, 120, 4, 24, BrownLimonite, YellowLimonite, Hematite, Malachite,
                    NETHER, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("cassiterite").asOreVein(6, 126, 50, 5, 24, Tin, Tin, Cassiterite, Tin,
                    END, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("tetrahedrite").asOreVein(51, 131, 70, 4, 24, Tetrahedrite, Tetrahedrite, Copper, Stibnite,
                    NETHER, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        } else {
            ev.vein(new WorldGenVeinLayerBuilder("gold").asOreVein(-4, 26, 160, 3, 32, Magnetite, Magnetite, VanadiumMagnetite, Gold,
                    OVERWORLD, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("iron").asOreVein(-14, 51, 120, 4, 24, BrownLimonite, YellowLimonite, Hematite, Malachite,
                    OVERWORLD, NETHER, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("cassiterite").asOreVein(6, 126, 50, 5, 24, Tin, Tin, Cassiterite, Tin,
                    OVERWORLD, END, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("tetrahedrite").asOreVein(51, 131, 70, 4, 24, Tetrahedrite, Tetrahedrite, Copper, Stibnite,
                    OVERWORLD, NETHER, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        }
        Material sporadic = !AntimatterConfig.STONE_LAYERS.get() ? Calcite : Alumina;
        if (GregTechConfig.GT6_ORE_GEN.get()){
            ev.vein(new WorldGenVeinLayerBuilder("magnetite").asOreVein(-14, 91, 160, 3, 32, Magnetite, Magnetite, Iron, VanadiumMagnetite,
                    NETHER, BE_MARS, AA_MARS).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("copper").asOreVein(36, 66, 80, 4, 24, Chalcopyrite, Iron, Pyrite, Copper,
                    NETHER, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("salts").asOreVein(51, 66, 50, 3, 24, RockSalt, Salt, Lepidolite, Spodumene,
                    BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("redstone").asOreVein(-54, -9, 60, 3, 24, Redstone, Redstone, Ruby, Cinnabar,
                    BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("pitchblend").asOreVein(-54, -9, 20, 3, 20, Pitchblende, Pitchblende, Uraninite, Uraninite,
                    BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("soapstone").asOreVein(-54, -9, 40, 3, 16, Soapstone, Talc, Glauconite, Pentlandite,
                    BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("nickel").asOreVein(11, 56, 40, 3, 16, Garnierite, Nickel, Cobaltite, Pentlandite,
                    NETHER, END, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("platinum").asOreVein(-24, -9, 5, 3, 16, Sheldonite, Palladium, Platinum, Iridium,
                    BE_MARS, AA_MARS).buildVein());
            /*ev.vein(new WorldGenVeinLayerBuilder("uranium").asOreVein(-44, -29, 20, 3, 16, Uraninite, Uraninite, Uraninite, Uraninite,
                OVERWORLD, TWILIGHT_FOREST, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());*/
            ev.vein(new WorldGenVeinLayerBuilder("monazite").asOreVein(-44, -14, 30, 3, 16, Bastnasite, Bastnasite, Monazite, Neodymium,
                    BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("molybdenum").asOreVein(-44, 1, 5, 3, 16, Wulfenite, Molybdenite, Molybdenum, Powellite,
                    BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("tungstate").asOreVein(-44, 1, 10, 3, 16, Scheelite, Scheelite, Tungstate, Lithium,
                    BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("sapphire").asOreVein(-54, -9, 60, 3, 16, Almandine, Pyrope, Sapphire, GreenSapphire,
                    BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("manganese").asOreVein(-44, -29, 20, 3, 16, Grossular, Spessartine, Pyrolusite, Tantalite,
                    BE_MOON, AA_MOON).buildVein());
            Material third = CertusQuartz.enabled ? CertusQuartz : Barite;
            ev.vein(new WorldGenVeinLayerBuilder("quartz").asOreVein(6, 66, 60, 3, 16, MilkyQuartz, Barite, third, third,
                    BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("diamond").asOreVein(-59, -48, 40, 2, 16, Graphite, Graphite, Diamond, Coal,
                    BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("olivine").asOreVein(-54, -9, 60, 3, 16, Bentonite, Magnesite, Olivine, Glauconite,
                    BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("galena").asOreVein(6, 51, 40, 5, 16, Galena, Galena, Silver, Lead,
                    BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("lapis").asOreVein(-44, 1, 40, 5, 16, Lazurite, Sodalite, Lapis, sporadic,
                    BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("beryllium").asOreVein(-59, -21, 30, 3, 16, Beryllium, Beryllium, Emerald, Thorium,
                    BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        } else {
            ev.vein(new WorldGenVeinLayerBuilder("magnetite").asOreVein(-14, 91, 160, 3, 32, Magnetite, Magnetite, Iron, VanadiumMagnetite,
                    OVERWORLD, NETHER, BE_MARS, AA_MARS).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("copper").asOreVein(36, 66, 80, 4, 24, Chalcopyrite, Iron, Pyrite, Copper,
                    OVERWORLD, NETHER, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("salts").asOreVein(51, 66, 50, 3, 24, RockSalt, Salt, Lepidolite, Spodumene,
                    OVERWORLD, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("redstone").asOreVein(-54, -9, 60, 3, 24, Redstone, Redstone, Ruby, Cinnabar,
                    OVERWORLD, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("pitchblend").asOreVein(-54, -9, 20, 3, 20, Pitchblende, Pitchblende, Uraninite, Uraninite,
                    OVERWORLD, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("soapstone").asOreVein(-54, -9, 40, 3, 16, Soapstone, Talc, Glauconite, Pentlandite,
                    OVERWORLD, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("nickel").asOreVein(11, 56, 40, 3, 16, Garnierite, Nickel, Cobaltite, Pentlandite,
                    OVERWORLD, NETHER, END, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("platinum").asOreVein(-24, -9, 5, 3, 16, Sheldonite, Palladium, Platinum, Iridium,
                    OVERWORLD, BE_MARS, AA_MARS).buildVein());
            /*ev.vein(new WorldGenVeinLayerBuilder("uranium").asOreVein(-44, -29, 20, 3, 16, Uraninite, Uraninite, Uraninite, Uraninite,
                OVERWORLD, TWILIGHT_FOREST, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());*/
            ev.vein(new WorldGenVeinLayerBuilder("monazite").asOreVein(-44, -14, 30, 3, 16, Bastnasite, Bastnasite, Monazite, Neodymium,
                    OVERWORLD, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("molybdenum").asOreVein(-44, 1, 5, 3, 16, Wulfenite, Molybdenite, Molybdenum, Powellite,
                    OVERWORLD, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("tungstate").asOreVein(-44, 1, 10, 3, 16, Scheelite, Scheelite, Tungstate, Lithium,
                    OVERWORLD, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("sapphire").asOreVein(-54, -9, 60, 3, 16, Almandine, Pyrope, Sapphire, GreenSapphire,
                    OVERWORLD, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("manganese").asOreVein(-44, -29, 20, 3, 16, Grossular, Spessartine, Pyrolusite, Tantalite,
                    OVERWORLD, BE_MOON, AA_MOON).buildVein());
            Material third = CertusQuartz.enabled ? CertusQuartz : Barite;
            ev.vein(new WorldGenVeinLayerBuilder("quartz").asOreVein(6, 66, 60, 3, 16, MilkyQuartz, Barite, third, third,
                    OVERWORLD, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("diamond").asOreVein(-59, -48, 40, 2, 16, Graphite, Graphite, Diamond, Coal,
                    OVERWORLD, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("olivine").asOreVein(-54, -9, 60, 3, 16, Bentonite, Magnesite, Olivine, Glauconite,
                    OVERWORLD, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("apatite").asOreVein(-4, 41, 60, 3, 16, Apatite, Apatite, TricalciumPhosphate, Phosphate,
                    OVERWORLD).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("galena").asOreVein(6, 51, 40, 5, 16, Galena, Galena, Silver, Lead,
                    OVERWORLD, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("lapis").asOreVein(-44, 1, 40, 5, 16, Lazurite, Sodalite, Lapis, sporadic,
                    OVERWORLD, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("beryllium").asOreVein(-59, -21, 30, 3, 16, Beryllium, Beryllium, Emerald, Thorium,
                    OVERWORLD, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        }
        ev.vein(new WorldGenVeinLayerBuilder("naquadah").asOreVein(10, 60, 10, 5, 32, Naquadah, Naquadah, Naquadah, Naquadah,
                END, BE_MARS, AA_MARS).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("nether_quartz").asOreVein(40, 80, 80, 5, 24, Quartz, Quartz, Quartz, Quartz,
                NETHER).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("sulfur").asOreVein(5, 20, 100, 5, 24, Sulfur, Sulfur, Pyrite, Sphalerite,
                NETHER, BE_MARS, AA_MARS).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("redstone_nether").asOreVein(10, 40, 60, 3, 24, Redstone, Redstone, Ruby, Cinnabar,
                NETHER).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("platinum_end").asOreVein(40, 50, 5, 3, 16, Sheldonite, Palladium, Platinum, Iridium,
                END).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("molybdenum_end").asOreVein(20, 50, 5, 3, 16, Wulfenite, Molybdenite, Molybdenum,
                Powellite , END).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("tungstate_end").asOreVein(20, 50, 10, 3, 16, Scheelite, Scheelite, Tungstate, Lithium,
                END).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("manganese_end").asOreVein(20, 30, 20, 3, 16, Grossular, Spessartine, Pyrolusite, Tantalite,
                END).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("olivine_end").asOreVein(10, 40, 60, 3, 16, Bentonite, Magnesite, Olivine, Glauconite,
                END).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("lapis_end").asOreVein(20, 50, 40, 5, 16, Lazurite, Sodalite, Lapis, sporadic,
                END).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("beryllium_end").asOreVein(5, 30, 30, 3, 16, Beryllium, Beryllium, Emerald, Thorium,
                END).buildVein());
        if (GregTechConfig.GT6_ORE_GEN.get()) return;
        if (AntimatterAPI.isModLoaded(MOD_TFC) || !AntimatterConfig.STONE_LAYERS.get()){
            ev.vein(new WorldGenVeinLayerBuilder("bauxite").asOreVein(-14, 46, 80, 4, 24, Bauxite, Bauxite, Alumina, Ilmenite,
                    OVERWORLD, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        }
        if (!AntimatterConfig.STONE_LAYERS.get()){
            ev.vein(new WorldGenVeinLayerBuilder("oilshale").asOreVein(-14, 31, 80, 6, 32, OilShale, OilShale, OilShale, OilShale,
                    OVERWORLD).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("lignite").asOreVein(0, 200, 160, 8, 32, Lignite, Lignite, Lignite, Coal,
                    OVERWORLD).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("coal").asOreVein(0, 200, 80, 6, 32, Coal, Coal, Coal, Lignite,
                    OVERWORLD).buildVein());
        }
    }
}
