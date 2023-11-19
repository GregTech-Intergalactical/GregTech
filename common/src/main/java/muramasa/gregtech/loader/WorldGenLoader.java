package muramasa.gregtech.loader;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.event.WorldGenEvent;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.worldgen.StoneLayerOre;
import muramasa.antimatter.worldgen.object.WorldGenStoneLayerBuilder;
import muramasa.antimatter.worldgen.smallore.WorldGenSmallOreBuilder;
import muramasa.antimatter.worldgen.vanillaore.WorldGenVanillaOreBuilder;
import muramasa.antimatter.worldgen.vein.WorldGenVeinLayerBuilder;
import muramasa.gregtech.worldgen.OilSpoutFluid;
import muramasa.gregtech.worldgen.OilSpoutSavedData;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

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
        if (AntimatterConfig.SMALL_ORES.get() && !AntimatterAPI.isModLoaded("tfc")){
            initSmallOres(ev);
        }
        OilSpoutSavedData.clearFluidMap();
        OilSpoutFluid.resetTotalWeight();
        new OilSpoutFluid("oil", Oil.getLiquid(), 20, 625, 4, 5);
        new OilSpoutFluid("light_oil", OilLight.getLiquid(), 20, 625, 3, 6);
        new OilSpoutFluid("heavy_oil", OilHeavy.getLiquid(), 20, 625, 5, 4);
        new OilSpoutFluid("natural_gas", NaturalGas.getGas(), 20, 625, 4, 7);
    }

    private static void initTwilightForestOres(WorldGenEvent event){
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Coal).withMaterialType(ORE_STONE).withSize(50).withWeight(1).atHeight(-16, 0).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Lignite).withMaterialType(ORE_STONE).withSize(50).withWeight(1).atHeight(-16, 0).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Salt).withMaterialType(ORE_STONE).withSize(50).withWeight(1).atHeight(-16, 0).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(RockSalt).withMaterialType(ORE_STONE).withSize(50).withWeight(1).atHeight(-16, 0).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(Bauxite).withMaterialType(ORE_STONE).withSize(50).withWeight(1).atHeight(-16, 0).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(OilShale).withMaterialType(ORE_STONE).withSize(50).withWeight(1).atHeight(-16, 0).buildMaterial());
        event.vanillaOre(new WorldGenVanillaOreBuilder().withMaterial(MilkyQuartz).withSize(50).withWeight(1).atHeight(-16, 0).buildMaterial());
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

    static boolean GT6_ORES = false;

    private static void initStoneVeins(WorldGenEvent ev) {
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



        if (GT6_ORES){
            ev.stoneLayer(new WorldGenStoneLayerBuilder("stone_ores_1").withStone(STONE).withWeight(4).buildVein()); //Ores
            ev.stoneLayer(new WorldGenStoneLayerBuilder("stone_ores_2").withStone(STONE).withWeight(4).buildVein()); //Ores
            ev.stoneLayer(new WorldGenStoneLayerBuilder("stone_ores_3").withStone(STONE).withWeight(4).buildVein()); //Ores
            ev.stoneLayer(new WorldGenStoneLayerBuilder("stone_ores_4").withStone(STONE).withWeight(4).buildVein()); //Ores
            ev.stoneLayer(new WorldGenStoneLayerBuilder("black_granite_ores").withStone(BLACK_GRANITE).withWeight(1).addOres(
                    new StoneLayerOre(Cooperite, O32, -64, -32),
                    new StoneLayerOre(Iridium, O64, -64, -46)
            ).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("red_granite_ores").withStone(RED_GRANITE).withWeight(1).addOres(
                    new StoneLayerOre(Pitchblende, O32, -32, 0),
                    new StoneLayerOre(Uraninite, O32, -32, 0),
                    new StoneLayerOre(Tantalite, O16, -32, 0)
            ).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("komatiite_ores").withStone(KOMATIITE).withWeight(1).addOres(
                    new StoneLayerOre(Magnesite, O16, -54, -9),
                    new StoneLayerOre(Cinnabar, O12, -64, -19),
                    new StoneLayerOre(Redstone, O8, -54, -9),
                    new StoneLayerOre(Pyrite, O12, 5, 66)
            ).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("basalt_ores").withStone(BASALT).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("basalt_ores_2").withStone(BASALT).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("marble_ores").withStone(MARBLE).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("limestone_ores").withStone(LIMESTONE).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("limestone_ores_2").withStone(LIMESTONE).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("green_schist_ores").withStone(GREEN_SCHIST).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("blue_schist_ores").withStone(BLUE_SCHIST).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("kimberlite_ores").withStone(KIMBERLITE).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("quartzite_ores").withStone(QUARTZITE).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("granite_ores").withStone(GRANITE).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("granite_ores").withStone(GRANITE).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("diorite_ores").withStone(DIORITE).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("diorite_ores").withStone(DIORITE).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("andesite_ores").withStone(ANDESITE).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("andesite_ores").withStone(ANDESITE).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("tuff_ores").withStone(TUFF).withWeight(1).buildVein());
            ev.stoneLayer(new WorldGenStoneLayerBuilder("tuff_ores").withStone(TUFF).withWeight(1).buildVein());
        }
        ev.stoneLayer(new WorldGenStoneLayerBuilder("coal").withStone(ORE_STONE.get().get(Coal).asState()).withWeight(1).buildVein());
        ev.stoneLayer(new WorldGenStoneLayerBuilder("lignite").withStone(ORE_STONE.get().get(Lignite).asState()).withWeight(1).buildVein());
        ev.stoneLayer(new WorldGenStoneLayerBuilder("salt").withStone(ORE_STONE.get().get(Salt).asState()).withWeight(1).buildVein());
        ev.stoneLayer(new WorldGenStoneLayerBuilder("rock_salt").withStone(ORE_STONE.get().get(RockSalt).asState()).withWeight(1).buildVein());
        ev.stoneLayer(new WorldGenStoneLayerBuilder("bauxite").withStone(ORE_STONE.get().get(Bauxite).asState()).withWeight(1).buildVein());
        ev.stoneLayer(new WorldGenStoneLayerBuilder("oil_shale").withStone(ORE_STONE.get().get(OilShale).asState()).withWeight(1).buildVein());

        ev.addCollision(BASALT.getState(), LIMESTONE.getState(),
                new StoneLayerOre(Ilmenite, O8, -64, 0),
                new StoneLayerOre(Rutile, O12, -64, 0)
        );

    }

    private static void initOreVeins(WorldGenEvent ev) {
        if (GT6_ORES) return;
        ev.vein(new WorldGenVeinLayerBuilder("naquadah").asOreVein(10, 60, 10, 5, 32, Naquadah, Naquadah, Naquadah, Naquadah,
                END, BE_MARS, AA_MARS).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("magnetite").asOreVein(-14, 91, 160, 3, 32, Magnetite, Magnetite, Iron, VanadiumMagnetite,
                OVERWORLD, NETHER, TWILIGHT_FOREST, BE_MARS, AA_MARS).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("gold").asOreVein(-4, 26, 160, 3, 32, Magnetite, Magnetite, VanadiumMagnetite, AntimatterMaterials.Gold,
                OVERWORLD, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("iron").asOreVein(-14, 51, 120, 4, 24, BrownLimonite, YellowLimonite, Hematite, Malachite,
                OVERWORLD, NETHER, TWILIGHT_FOREST, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("cassiterite").asOreVein(6, 126, 50, 5, 24, Tin, Tin, Cassiterite, Tin,
                OVERWORLD, END, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("tetrahedrite").asOreVein(51, 131, 70, 4, 24, Tetrahedrite, Tetrahedrite, Copper, Stibnite,
                OVERWORLD, NETHER, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("nether_quartz").asOreVein(40, 80, 80, 5, 24, Quartz, Quartz, Quartz, Quartz,
                NETHER).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("sulfur").asOreVein(5, 20, 100, 5, 24, Sulfur, Sulfur, Pyrite, Sphalerite,
                NETHER, BE_MARS, AA_MARS).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("copper").asOreVein(36, 66, 80, 4, 24, Chalcopyrite, Iron, Pyrite, Copper,
                OVERWORLD, NETHER, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("salts").asOreVein(51, 66, 50, 3, 24, RockSalt, Salt, Lepidolite, Spodumene,
                OVERWORLD, BE_MOON, AA_MOON).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("redstone").asOreVein(-54, -9, 60, 3, 24, Redstone, Redstone, Ruby, Cinnabar,
                OVERWORLD, TWILIGHT_FOREST, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("redstone_nether").asOreVein(10, 40, 60, 3, 24, Redstone, Redstone, Ruby, Cinnabar,
                NETHER).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("pitchblend").asOreVein(-54, -9, 20, 3, 20, Pitchblende, Pitchblende, Uraninite, Uraninite,
                OVERWORLD, TWILIGHT_FOREST, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        /*ev.vein(new WorldGenVeinLayerBuilder("uranium").asOreVein(-44, -29, 20, 3, 16, Uraninite, Uraninite, Uraninite, Uraninite,
                OVERWORLD, TWILIGHT_FOREST, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());*/
        ev.vein(new WorldGenVeinLayerBuilder("soapstone").asOreVein(-54, -9, 40, 3, 16, Soapstone, Talc, Glauconite, Pentlandite,
                OVERWORLD, TWILIGHT_FOREST, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("nickel").asOreVein(11, 56, 40, 3, 16, Garnierite, Nickel, Cobaltite, Pentlandite,
                OVERWORLD, NETHER, END, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("platinum").asOreVein(-24, -9, 5, 3, 16, Cooperite, Palladium, Platinum, Iridium,
                OVERWORLD, TWILIGHT_FOREST, BE_MARS, AA_MARS).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("platinum_end").asOreVein(40, 50, 5, 3, 16, Cooperite, Palladium, Platinum, Iridium,
                END).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("monazite").asOreVein(-44, -14, 30, 3, 16, Bastnasite, Bastnasite, Monazite, Neodymium,
                OVERWORLD, TWILIGHT_FOREST, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("molybdenum").asOreVein(-44, 1, 5, 3, 16, Wulfenite, Molybdenite, Molybdenum, Powellite,
                OVERWORLD, TWILIGHT_FOREST, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("molybdenum_end").asOreVein(20, 50, 5, 3, 16, Wulfenite, Molybdenite, Molybdenum,
                Powellite , END).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("tungstate").asOreVein(-44, 1, 10, 3, 16, Scheelite, Scheelite, Tungstate, Lithium,
                OVERWORLD, TWILIGHT_FOREST, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("tungstate_end").asOreVein(20, 50, 10, 3, 16, Scheelite, Scheelite, Tungstate, Lithium,
                END).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("sapphire").asOreVein(-54, -9, 60, 3, 16, Almandine, Pyrope, Sapphire, GreenSapphire,
                OVERWORLD, TWILIGHT_FOREST, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("manganese").asOreVein(-44, -29, 20, 3, 16, Grossular, Spessartine, Pyrolusite, Tantalite,
                OVERWORLD, TWILIGHT_FOREST, BE_MOON, AA_MOON).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("manganese_end").asOreVein(20, 30, 20, 3, 16, Grossular, Spessartine, Pyrolusite, Tantalite,
                END).buildVein());
        Material third = CertusQuartz.enabled ? CertusQuartz : Barite;
        ev.vein(new WorldGenVeinLayerBuilder("quartz").asOreVein(6, 66, 60, 3, 16, MilkyQuartz, Barite, third, third,
                OVERWORLD, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("diamond").asOreVein(-59, -48, 40, 2, 16, Graphite, Graphite, Diamond, Coal,
                OVERWORLD, TWILIGHT_FOREST, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("olivine").asOreVein(-54, -9, 60, 3, 16, Bentonite, Magnesite, Olivine, Glauconite,
                OVERWORLD, TWILIGHT_FOREST, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("olivine_end").asOreVein(10, 40, 60, 3, 16, Bentonite, Magnesite, Olivine, Glauconite,
                END).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("apatite").asOreVein(-4, 41, 60, 3, 16, Apatite, Apatite, TricalciumPhosphate, Phosphate,
                OVERWORLD).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("galena").asOreVein(6, 51, 40, 5, 16, Galena, Galena, Silver, Lead,
                OVERWORLD, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        Material sporadic = !AntimatterConfig.STONE_LAYERS.get() ? Calcite : Alumina;
        ev.vein(new WorldGenVeinLayerBuilder("lapis").asOreVein(-44, 1, 40, 5, 16, Lazurite, Sodalite, Lapis, sporadic,
                OVERWORLD, TWILIGHT_FOREST, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("lapis_end").asOreVein(20, 50, 40, 5, 16, Lazurite, Sodalite, Lapis, sporadic,
                END).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("beryllium").asOreVein(-59, -21, 30, 3, 16, Beryllium, Beryllium, Emerald, Thorium,
                OVERWORLD, TWILIGHT_FOREST, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
        ev.vein(new WorldGenVeinLayerBuilder("beryllium_end").asOreVein(5, 30, 30, 3, 16, Beryllium, Beryllium, Emerald, Thorium,
                END).buildVein());

        if (!AntimatterConfig.STONE_LAYERS.get()){
            ev.vein(new WorldGenVeinLayerBuilder("bauxite").asOreVein(-14, 46, 80, 4, 24, Bauxite, Bauxite, Alumina, Ilmenite,
                    OVERWORLD, TWILIGHT_FOREST, BE_MARS, AA_MARS, BE_MOON, AA_MOON).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("oilshale").asOreVein(-14, 31, 80, 6, 32, OilShale, OilShale, OilShale, OilShale,
                    OVERWORLD, TWILIGHT_FOREST).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("lignite").asOreVein(0, 200, 160, 8, 32, Lignite, Lignite, Lignite, Coal,
                    OVERWORLD).buildVein());
            ev.vein(new WorldGenVeinLayerBuilder("coal").asOreVein(0, 200, 80, 6, 32, Coal, Coal, Coal, Lignite,
                    OVERWORLD).buildVein());
        }
    }
}
