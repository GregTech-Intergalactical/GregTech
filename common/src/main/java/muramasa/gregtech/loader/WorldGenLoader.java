package muramasa.gregtech.loader;

import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.event.WorldGenEvent;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.worldgen.StoneLayerOre;
import muramasa.antimatter.worldgen.object.WorldGenStoneLayerBuilder;
import muramasa.antimatter.worldgen.smallore.WorldGenSmallOreBuilder;
import muramasa.antimatter.worldgen.vein.WorldGenVeinLayerBuilder;
import muramasa.gregtech.data.GregTechData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import static muramasa.antimatter.Ref.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.ORE_STONE;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.data.AntimatterStoneTypes.*;
import static muramasa.antimatter.data.AntimatterStoneTypes.DIORITE;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.Materials.*;
import static net.minecraft.world.level.Level.OVERWORLD;

public class WorldGenLoader {

  public static void init(WorldGenEvent ev) {
      if (AntimatterConfig.WORLD.STONE_LAYERS) {
          initStoneVeins(ev);
      }
      if (AntimatterConfig.WORLD.ORE_VEINS) {
          initOreVeins(ev);
          initStoneOreVeins(ev);
      }
      if (AntimatterConfig.WORLD.SMALL_ORES){
          initSmallOres(ev);
      }
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
      event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Platinum).withAmountPerChunk(8).atHeight(20, 40).withDimensions(new ResourceLocation("the_end")).buildMaterial());
      event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Iridium).withAmountPerChunk(8).atHeight(20, 40).withDimensions(new ResourceLocation("the_end")).buildMaterial());
      event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Quartz).withAmountPerChunk(64).atHeight(30, 120).withDimensions(new ResourceLocation("the_nether")).buildMaterial());
      event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Saltpeter).withAmountPerChunk(8).atHeight(10, 60).withDimensions(new ResourceLocation("the_nether")).buildMaterial());
      event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Sulfur).withAmountPerChunk(32).atHeight(10, 60).withDimensions(new ResourceLocation("the_nether")).buildMaterial());
      event.smallOre(new WorldGenSmallOreBuilder().withMaterial(Sulfur).withAmountPerChunk(8).atHeight(-59, -34).withCustomId("sulfur_overworld").buildMaterial());
  }

  private static void initStoneVeins(WorldGenEvent ev) {

      ev.stoneLayer(new WorldGenStoneLayerBuilder("stone").withStone(STONE).withWeight(4).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("stone_ores_1").withStone(STONE).withWeight(4).buildVein()); //Ores
      ev.stoneLayer(new WorldGenStoneLayerBuilder("stone_ores_2").withStone(STONE).withWeight(4).buildVein()); //Ores
      ev.stoneLayer(new WorldGenStoneLayerBuilder("stone_ores_3").withStone(STONE).withWeight(4).buildVein()); //Ores
      ev.stoneLayer(new WorldGenStoneLayerBuilder("stone_ores_4").withStone(STONE).withWeight(4).buildVein()); //Ores

      ev.stoneLayer(new WorldGenStoneLayerBuilder("black_granite").withStone(GRANITE_BLACK).withWeight(2).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("black_granite_ores").withStone(GRANITE_BLACK).withWeight(1).addOres(
              new StoneLayerOre(Cooperite, U32, -64, -32),
              new StoneLayerOre(Iridium, U64, -64, -46)
      ).buildVein());

      ev.stoneLayer(new WorldGenStoneLayerBuilder("red_granite").withStone(GRANITE_RED).withWeight(2).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("red_granite_ores").withStone(GRANITE_RED).withWeight(1).addOres(
              new StoneLayerOre(Pitchblende, U32, -32, 0),
              new StoneLayerOre(Uraninite, U32, -32, 0),
              new StoneLayerOre(Tantalite, U16, -32, 0)
      ).buildVein());

      ev.stoneLayer(new WorldGenStoneLayerBuilder("komatiite").withStone(KOMATIITE).withWeight(4).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("komatiite_ores").withStone(KOMATIITE).withWeight(1).addOres(
              new StoneLayerOre(Magnesite, U16, -54, -9),
              new StoneLayerOre(Cinnabar, U12, -64, -19),
              new StoneLayerOre(Redstone, U8, -54, -9),
              new StoneLayerOre(Pyrite, U12, 5, 66)
      ).buildVein());

      ev.stoneLayer(new WorldGenStoneLayerBuilder("basalt").withStone(BASALT).withWeight(3).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("basalt_ores").withStone(BASALT).withWeight(1).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("basalt_ores_2").withStone(BASALT).withWeight(1).buildVein());

      ev.stoneLayer(new WorldGenStoneLayerBuilder("marble").withStone(MARBLE).withWeight(4).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("marble_ores").withStone(MARBLE).withWeight(1).buildVein());

      ev.stoneLayer(new WorldGenStoneLayerBuilder("limestone").withStone(LIMESTONE).withWeight(3).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("limestone_ores").withStone(LIMESTONE).withWeight(1).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("limestone_ores_2").withStone(LIMESTONE).withWeight(1).buildVein());

      ev.stoneLayer(new WorldGenStoneLayerBuilder("green_schist").withStone(GREEN_SCHIST).withWeight(1).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("green_schist_ores").withStone(GREEN_SCHIST).withWeight(1).buildVein());

      ev.stoneLayer(new WorldGenStoneLayerBuilder("blue_schist").withStone(BLUE_SCHIST).withWeight(1).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("blue_schist_ores").withStone(BLUE_SCHIST).withWeight(1).buildVein());

      ev.stoneLayer(new WorldGenStoneLayerBuilder("kimberlite").withStone(KIMBERLITE).withWeight(3).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("kimberlite_ores").withStone(KIMBERLITE).withWeight(1).buildVein());

      ev.stoneLayer(new WorldGenStoneLayerBuilder("quartzite").withStone(QUARTZITE).withWeight(4).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("quartzite_ores").withStone(QUARTZITE).withWeight(1).buildVein());

      ev.stoneLayer(new WorldGenStoneLayerBuilder("granite").withStone(GRANITE).withWeight(3).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("granite_ores").withStone(GRANITE).withWeight(1).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("granite_ores").withStone(GRANITE).withWeight(1).buildVein());

      ev.stoneLayer(new WorldGenStoneLayerBuilder("diorite").withStone(DIORITE).withWeight(3).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("diorite_ores").withStone(DIORITE).withWeight(1).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("diorite_ores").withStone(DIORITE).withWeight(1).buildVein());

      ev.stoneLayer(new WorldGenStoneLayerBuilder("andesite").withStone(ANDESITE).withWeight(4).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("andesite_ores").withStone(ANDESITE).withWeight(1).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("andesite_ores").withStone(ANDESITE).withWeight(1).buildVein());

      ev.stoneLayer(new WorldGenStoneLayerBuilder("tuff").withStone(TUFF).withWeight(3).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("tuff_ores").withStone(TUFF).withWeight(1).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("tuff_ores").withStone(TUFF).withWeight(1).buildVein());

      ev.stoneLayer(new WorldGenStoneLayerBuilder("coal").withStone(ORE_STONE.get().get(Coal).asState()).withWeight(1).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("lignite").withStone(ORE_STONE.get().get(Lignite).asState()).withWeight(1).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("salt").withStone(ORE_STONE.get().get(Salt).asState()).withWeight(1).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("rock_salt").withStone(ORE_STONE.get().get(RockSalt).asState()).withWeight(1).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("bauxite").withStone(ORE_STONE.get().get(Bauxite).asState()).withWeight(1).buildVein());
      ev.stoneLayer(new WorldGenStoneLayerBuilder("oil_shale").withStone(ORE_STONE.get().get(OilShale).asState()).withWeight(1).buildVein());

      ev.addCollision(BASALT.getState(), GregTechData.LIMESTONE.getState(),
              new StoneLayerOre(Ilmenite, U8, -64, 0),
              new StoneLayerOre(Rutile, U12, -64, 0)
      );

  }

  private static void initStoneOreVeins(WorldGenEvent ev) {
    //WorldGenVein.setLayerChance(WorldGenVein.STONE_ORE_VEIN_LAYER, 0.05f);

  }

  private static void initOreVeins(WorldGenEvent ev) {
    //WorldGenVein.setLayerChance(WorldGenVein.ORE_VEIN_LAYER, 0.05f);
    // TODO: move veins from initOld here
      ev.vein(new WorldGenVeinLayerBuilder("naquadah").asOreVein(10, 60, 10, 5, 32, Naquadah, Naquadah, Naquadah, Naquadah,
        Level.END).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("lignite").asOreVein(0, 200, 160, 8, 32, Lignite, Lignite, Lignite, Coal,
        OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("coal").asOreVein(0, 200, 80, 6, 32, Coal, Coal, Coal, Lignite, OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("magnetite").asOreVein(-14, 91, 160, 3, 32, Magnetite, Magnetite, Iron, VanadiumMagnetite,
        OVERWORLD, Level.NETHER).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("gold").asOreVein(-4, 26, 160, 3, 32, Magnetite, Magnetite, VanadiumMagnetite, AntimatterMaterials.Gold,
        OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("iron").asOreVein(-14, 51, 120, 4, 24, BrownLimonite, YellowLimonite, BandedIron, Malachite,
        OVERWORLD, Level.NETHER).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("cassiterite").asOreVein(6, 126, 50, 5, 24, Tin, Tin, Cassiterite, Tin, OVERWORLD,
        Level.END).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("tetrahedrite").asOreVein(51, 131, 70, 4, 24, Tetrahedrite, Tetrahedrite, Copper, Stibnite,
        OVERWORLD, Level.NETHER).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("nether_quartz").asOreVein(40, 80, 80, 5, 24, Quartz, Quartz, Quartz, Quartz,
        Level.NETHER).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("sulfur").asOreVein(5, 20, 100, 5, 24, Sulfur, Sulfur, Pyrite, Sphalerite, Level.NETHER).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("copper").asOreVein(36, 66, 80, 4, 24, Chalcopyrite, Iron, Pyrite, Copper, OVERWORLD,
        Level.NETHER).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("bauxite").asOreVein(-14, 46, 80, 4, 24, Bauxite, Bauxite, Aluminium, Ilmenite, OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("salts").asOreVein(51, 66, 50, 3, 24, RockSalt, Salt, Lepidolite, Spodumene, OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("redstone").asOreVein(-54, -9, 60, 3, 24, Redstone, Redstone, Ruby, Cinnabar,
              OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("redstone_nether").asOreVein(10, 40, 60, 3, 24, Redstone, Redstone, Ruby, Cinnabar,
              Level.NETHER).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("soapstone").asOreVein(-54, -9, 40, 3, 16, Soapstone, Talc, Glauconite, Pentlandite,
        OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("nickel").asOreVein(11, 56, 40, 3, 16, Garnierite, Nickel, Cobaltite, Pentlandite,
        OVERWORLD, Level.NETHER, Level.END).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("platinum").asOreVein(-24, -9, 5, 3, 16, Cooperite, Palladium, Platinum, Iridium,
              OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("platinum_end").asOreVein(40, 50, 5, 3, 16, Cooperite, Palladium, Platinum, Iridium, OVERWORLD,
              Level.END).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("pitchblend").asOreVein(-54, -9, 40, 3, 16, Pitchblende, Pitchblende, Uraninite, Uraninite,
        OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("uranium").asOreVein(-44, -29, 20, 3, 16, Uraninite, Uraninite, Uranium, Uranium,
        OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("monazite").asOreVein(-44, -14, 30, 3, 16, Bastnasite, Bastnasite, Monazite,
        Neodymium, OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("molybdenum").asOreVein(-44, 1, 5, 3, 16, Wulfenite, Molybdenite, Molybdenum,
        Powellite , OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("molybdenum_end").asOreVein(20, 50, 5, 3, 16, Wulfenite, Molybdenite, Molybdenum,
              Powellite , Level.END).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("tungstate").asOreVein(-44, 1, 10, 3, 16, Scheelite, Scheelite, Tungstate, Lithium,
        OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("tungstate_end").asOreVein(20, 50, 10, 3, 16, Scheelite, Scheelite, Tungstate, Lithium,
              Level.END).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("sapphire").asOreVein(-54, -9, 60, 3, 16, Almandine, Pyrope, Sapphire, GreenSapphire,
        OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("manganese").asOreVein(-44, -29, 20, 3, 16, Grossular, Spessartine, Pyrolusite, Tantalite,
        OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("manganese_end").asOreVein(20, 30, 20, 3, 16, Grossular, Spessartine, Pyrolusite, Tantalite,
              Level.END).buildVein());
      Material third = CertusQuartz.enabled ? CertusQuartz : Barite;
      ev.vein(new WorldGenVeinLayerBuilder("quartz").asOreVein(6, 66, 60, 3, 16, Quartzite, Barite, third, third, OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("diamond").asOreVein(-59, -48, 40, 2, 16, Graphite, Graphite, Diamond, Coal, OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("olivine").asOreVein(-54, -9, 60, 3, 16, Bentonite, Magnesite, Olivine, Glauconite,
        OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("olivine_end").asOreVein(10, 40, 60, 3, 16, Bentonite, Magnesite, Olivine, Glauconite,
              Level.END).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("apatite").asOreVein(-4, 41, 60, 3, 16, Apatite, Apatite, Phosphorus, Phosphate, OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("galena").asOreVein(6, 51, 40, 5, 16, Galena, Galena, Silver, Lead, OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("lapis").asOreVein(-44, 1, 40, 5, 16, Lazurite, Sodalite, Lapis,
        Calcite, OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("lapis_end").asOreVein(20, 50, 40, 5, 16, Lazurite, Sodalite, Lapis,
              Calcite, Level.END).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("beryllium").asOreVein(-59, -21, 30, 3, 16, Beryllium, Beryllium, Emerald, Thorium,
        OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("beryllium_end").asOreVein(5, 30, 30, 3, 16, Beryllium, Beryllium, Emerald, Thorium,
              Level.END).buildVein());
      ev.vein(new WorldGenVeinLayerBuilder("oilshale").asOreVein(-14, 31, 80, 6, 32, OilShale, OilShale, OilShale, OilShale,
        OVERWORLD).buildVein());

  }

  // public static void initOld() {
  //
  // //TODO probably increase max generation heights for most things
  // //TODO add GC dims to all objects
  //
  // if (AntimatterConfig.WORLD.ORE_VEINS) {
  // //new WorldGenVeinLayer("naquadah", -54, 21, 10, 5, 32, Naquadah, Naquadah,
  // Naquadah, EnrichedNaquadah, Level.END);
  // //new WorldGenVeinLayer("lignite", -14, 106, 160, 8, 32, lignite, lignite,
  // lignite, coal, world.overworld);
  // //new WorldGenVeinLayer("coal", -14, 31, 80, 6, 32, Coal, Coal, Coal,
  // Lignite, World.OVERWORLD);
  // // //new WorldGenVeinLayer("bauxite", -14, 46, 80, 4, 24, Bauxite, Bauxite,
  // Aluminium, Ilmenite, World.OVERWORLD);
  // //new WorldGenVeinLayer("salts", -14, 1, 50, 3, 24, RockSalt, Salt,
  // Lepidolite, Spodumene, World.OVERWORLD);

  // }
  //
  // new WorldGenOreSmall(60, 120, 16, Chalcopyrite, OVERWORLD, Level.NETHER,
  // Level.END/*, MARS */);
  // new WorldGenOreSmall(60, 120, 16, Cassiterite, OVERWORLD, Level.NETHER,
  // Level.END/*, MARS */);
  // new WorldGenOreSmall(60, 120, 16, Copper, OVERWORLD, Level.NETHER,
  // Level.END/*, MOON *//*, MARS */);
  // new WorldGenOreSmall(60, 120, 16, Tin, OVERWORLD, Level.NETHER, Level.END/*,
  // MOON *//*, MARS *//*, ASTEROIDS */);
  // new WorldGenOreSmall(80, 120, 8, Bismuth, OVERWORLD, Level.NETHER/*, MOON
  // *//*, MARS */);
  // new WorldGenOreSmall(60, 100, 24, Coal, OVERWORLD);
  //
  // //TODO replace and also swap ORE_SMALL tags in Materials
  // //new WorldGenOreSmall(40, 80, 24, Hematite, World.OVERWORLD,
  // World.THE_NETHER, World.THE_END/*, MOON *//*, MARS */);
  // new WorldGenOreSmall(40, 80, 16, Iron, OVERWORLD, Level.NETHER, Level.END/*,
  // MOON *//*, MARS */);
  //
  // new WorldGenOreSmall(40, 80, 6, Salt, OVERWORLD, Level.NETHER, Level.END/*,
  // MARS */);
  // new WorldGenOreSmall(40, 80, 6, RockSalt, OVERWORLD, Level.NETHER,
  // Level.END/*, MARS */);
  // new WorldGenOreSmall(30, 60, 12, Sphalerite, OVERWORLD, Level.NETHER,
  // Level.END/*, MARS */);
  // new WorldGenOreSmall(40, 70, 4, Zinc, OVERWORLD, Level.NETHER, Level.END/*,
  // MOON *//*, MARS *//*, ASTEROIDS */);
  //
  // //TODO replace and also swap ORE_SMALL tags in Materials
  // //new WorldGenOreSmall(30, 60, 2, Smithsonite, World.OVERWORLD,
  // World.THE_NETHER, World.THE_END/*, MARS */);
  // new WorldGenOreSmall(20, 40, 8, Nickel, OVERWORLD, Level.NETHER, Level.END/*,
  // MOON *//*, MARS *//*, ASTEROIDS */);
  //
  // new WorldGenOreSmall(40, 80, 8, Galena, OVERWORLD, Level.NETHER, Level.END/*,
  // MARS */);
  // new WorldGenOreSmall(40, 80, 8, Lead, OVERWORLD, Level.NETHER, Level.END/*,
  // MOON *//*, MARS *//*, ASTEROIDS */);
  // new WorldGenOreSmall(20, 40, 4, Gold, OVERWORLD, Level.NETHER, Level.END/*,
  // MOON *//*, MARS *//*, ASTEROIDS */);
  // new WorldGenOreSmall(20, 40, 4, Pyrite, OVERWORLD, Level.NETHER, Level.END/*,
  // MOON *//*, MARS *//*, ASTEROIDS */);
  // new WorldGenOreSmall(20, 40, 4, Silver, OVERWORLD, Level.NETHER, Level.END/*,
  // MOON *//*, MARS *//*, ASTEROIDS */);
  // new WorldGenOreSmall(20, 40, 4, Pyrolusite, OVERWORLD, Level.NETHER,
  // Level.END/*, MOON *//*, MARS */);
  // new WorldGenOreSmall(20, 40, 4, Garnierite, OVERWORLD, Level.NETHER,
  // Level.END/*, MARS */);
  // new WorldGenOreSmall(20, 40, 4, Pentlandite, OVERWORLD, Level.NETHER,
  // Level.END/*, MARS */);
  // new WorldGenOreSmall(20, 40, 2, Stibnite, OVERWORLD, Level.NETHER,
  // Level.END/*, MARS */);
  // //new WorldGenOreSmall(20, 40, 8, Asbestos, World.OVERWORLD);
  // //new WorldGenOreSmall(20, 40, 4, Eudialyte, World.OVERWORLD);
  // //new WorldGenOreSmall(20, 40, 4, Azurite, World.OVERWORLD);
  // //new WorldGenOreSmall(1, 250, 1, Zeolite, World.OVERWORLD);
  // new WorldGenOreSmall(20, 40, 4, Lapis, OVERWORLD);
  // new WorldGenOreSmall(5, 20, 8, Redstone, OVERWORLD, Level.NETHER/*, MARS */);
  // new WorldGenOreSmall(5, 10, 2, Graphite, OVERWORLD, Level.NETHER/*, MARS */);
  // //new WorldGenOreSmall(5, 50, 1, Sheelite, World.OVERWORLD, World.THE_END/*,
  // MOON *//*, MARS *//*, ASTEROIDS */);
  // new WorldGenOreSmall(20, 40, 4, Cooperite, Level.END/*, ASTEROIDS */);
  // //new WorldGenOreSmall(20, 40, 4, Sperrylite, World.THE_END/*, ASTEROIDS */);
  // new WorldGenOreSmall(20, 40, 6, Platinum, Level.END/*, ASTEROIDS */);
  // new WorldGenOreSmall(20, 40, 6, Iridium, Level.END/*, ASTEROIDS */);
  // new WorldGenOreSmall(30, 120, 64, Quartz, Level.NETHER);
  // new WorldGenOreSmall(10, 60, 8, Saltpeter, Level.NETHER);
  // //new WorldGenOreSmall(10, 60, 8, SodiumNitrate, World.THE_NETHER);
  // new WorldGenOreSmall("sulfur_World.THE_NETHER", 10, 60, 32, Sulfur,
  // Level.NETHER);
  // new WorldGenOreSmall("sulfur_World.OVERWORLD", 5, 15, 8, Sulfur, OVERWORLD/*,
  // MARS */);
  //
  // //TODO replace with GarnetSand?
  // new WorldGenOreSmall(5, 250, 1, RedGarnet, OVERWORLD, Level.NETHER/*, MARS
  // *//*, ASTEROIDS */);
  // new WorldGenOreSmall(5, 250, 1, YellowGarnet, OVERWORLD, Level.NETHER/*, MARS
  // *//*, ASTEROIDS */);
  //
  // new WorldGenOreSmall(5, 10, 2, Diamond, OVERWORLD, Level.NETHER/*, MOON *//*,
  // MARS *//*, ASTEROIDS */);
  // new WorldGenOreSmall(5, 250, 1, Emerald, OVERWORLD/*, MARS */);
  // //new WorldGenOreSmall(5, 250, 1, Aquamarine, World.OVERWORLD/*, MARS */);
  // //new WorldGenOreSmall(5, 250, 1, Morganite, World.OVERWORLD/*, MARS */);
  // //new WorldGenOreSmall(5, 250, 1, Heliodor, World.OVERWORLD/*, MARS */);
  // //new WorldGenOreSmall(5, 250, 1, Goshenite, World.OVERWORLD/*, MARS */);
  // //new WorldGenOreSmall(5, 250, 1, Bixbite, World.OVERWORLD/*, MARS */);
  // //new WorldGenOreSmall(5, 250, 1, Maxixe, World.OVERWORLD/*, MARS */);
  // //new WorldGenOreSmall(5, 250, 1, Dioptase, World.OVERWORLD/*, MARS */);
  // new WorldGenOreSmall(5, 250, 1, Ruby, OVERWORLD/*, MARS */);
  // new WorldGenOreSmall(5, 250, 1, BlueSapphire, OVERWORLD/*, MARS */);
  // new WorldGenOreSmall(5, 250, 1, GreenSapphire, OVERWORLD/*, MARS */);
  // //new WorldGenOreSmall(5, 250, 1, YellowSapphire, World.OVERWORLD/*, MARS
  // */);
  // //new WorldGenOreSmall(5, 250, 1, OrangeSapphire, World.OVERWORLD/*, MARS
  // */);
  // //new WorldGenOreSmall(5, 250, 1, PurpleSapphire, World.OVERWORLD/*, MARS
  // */);
  // new WorldGenOreSmall(5, 250, 1, Olivine, OVERWORLD/*, MARS */);
  // //new WorldGenOreSmall(5, 250, 1, Craponite, World.OVERWORLD/*, MARS */);
  // new WorldGenOreSmall(5, 250, 1, Topaz, OVERWORLD/*, MARS */);
  // new WorldGenOreSmall(5, 250, 1, Tanzanite, OVERWORLD/*, MARS */);
  // new WorldGenOreSmall(5, 250, 1, Amethyst, OVERWORLD/*, MARS */);
  // //new WorldGenOreSmall(5, 250, 1, Alexandrite, World.OVERWORLD/*, MARS */);
  // new WorldGenOreSmall(5, 250, 1, Opal, OVERWORLD/*, MARS */);
  // //new WorldGenOreSmall(5, 250, 1, Jasper, World.OVERWORLD/*, MARS */);
  // new WorldGenOreSmall(5, 250, 1, BlueTopaz, OVERWORLD/*, MARS */);
  // //new WorldGenOreSmall(5, 250, 1, BalasRuby, World.OVERWORLD/*, MARS */);
  // //new WorldGenOreSmall(5, 250, 1, Spinel, World.OVERWORLD/*, MARS */);
  // new WorldGenOreSmall(5, 250, 1, Pyrope, OVERWORLD/*, MARS */);
  //
  // new WorldGenOreSmall(5, 250, 1, Almandine, OVERWORLD/*, MARS */);
  // new WorldGenOreSmall(5, 250, 1, Spessartine, OVERWORLD/*, MARS */);
  // new WorldGenOreSmall(5, 250, 1, Andradite, OVERWORLD/*, MARS */);
  // new WorldGenOreSmall(5, 250, 1, Grossular, OVERWORLD/*, MARS */);
  // new WorldGenOreSmall(5, 250, 1, Uvarovite, OVERWORLD/*, MARS */);
  // //new WorldGenOreSmall(5, 250, 1, Jade, World.OVERWORLD/*, MARS */);
  // //new WorldGenOreSmall(5, 250, 1, Amazonite, World.OVERWORLD/*, MARS */);
  // //new WorldGenOreSmall(5, 250, 1, RedOnyx, World.OVERWORLD/*, MARS */);
  // //new WorldGenOreSmall(5, 250, 1, BlackOnyx, World.OVERWORLD/*, MARS */);
  // //new WorldGenOreSmall(5, 250, 1, Amber, World.OVERWORLD/*, MARS */);
  // }
}
