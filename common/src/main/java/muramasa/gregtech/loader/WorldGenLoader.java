package muramasa.gregtech.loader;

import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.event.AntimatterWorldGenEvent;
import muramasa.antimatter.worldgen.vein.WorldGenVein;
import muramasa.antimatter.worldgen.vein.WorldGenVeinBuilder;
import net.minecraft.world.level.Level;

import static muramasa.antimatter.Data.*;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.Materials.*;
import static net.minecraft.world.level.Level.*;

public class WorldGenLoader {

  public static void init(AntimatterWorldGenEvent ev) {
      if (AntimatterConfig.WORLD.STONE_LAYERS) {
          initStoneVeins(ev);
      }
      if (AntimatterConfig.WORLD.STONE_LAYER_ORES) {
          initStoneOreVeins(ev);
      }
      if (AntimatterConfig.WORLD.ORE_VEINS) {
          initOreVeins(ev);
      }
  }

  private static void initStoneVeins(AntimatterWorldGenEvent ev) {
    WorldGenVein.setLayerChance(WorldGenVein.STONE_VEIN_LAYER, 0.25f);

    ev.vein(new WorldGenVeinBuilder("vein_gravel")
        .asSmallStoneVein(10, -64, 320, GRAVEL, OVERWORLD)
        .buildVein());

      ev.vein(new WorldGenVeinBuilder("vein_dirt")
        .asSmallStoneVein(10, 0, 320, DIRT, OVERWORLD)
        .buildVein());

      ev.vein(new WorldGenVeinBuilder("vein_tuff")
        .asSmallStoneVein(5, -64, 16, TUFF, OVERWORLD)
        .buildVein());

      ev.vein(new WorldGenVeinBuilder("vein_granite")
        .asMediumStoneVein(5, 128, 32, GRANITE, OVERWORLD)
        .buildVein());

      ev.vein(new WorldGenVeinBuilder("vein_diorite")
        .asMediumStoneVein(5, 128, 32, DIORITE, OVERWORLD)
        .buildVein());

      ev.vein(new WorldGenVeinBuilder("vein_andesite")
        .asMediumStoneVein(5, 128, 32, ANDESITE, OVERWORLD)
        .buildVein());

      ev.vein(new WorldGenVeinBuilder("vein_granite_black")
        .asLargeStoneVein(2, -64, 0, GRANITE_BLACK, OVERWORLD)
        .withVariant(93)
        .buildVariant()
        .withVariant(5)
        .withThinChance()
        .withMaterial(Cooperite, 1, -64, -32)
        .buildVariant()
        .withVariant(2)
        .withThinChance()
        .withMaterial(Iridium, 1, -64, -46)
        .buildVariant()
        .buildVein());

      ev.vein(new WorldGenVeinBuilder("vein_granite_red")
        .asLargeStoneVein(2, -32, 32, GRANITE_RED, OVERWORLD)
        .withVariant(85)
        .buildVariant()
        .withVariant(10)
        .withThinChance()
        .withMaterial(Pitchblende, -64, -32)
        .withMaterial(Uraninite, -64, -32)
        .buildVariant()
        .withVariant(5)
        .withThinChance()
        .withMaterial(Tantalite)
        .buildVariant()
        .buildVein());

      ev.vein(new WorldGenVeinBuilder("vein_basalt")
        .asMediumStoneVein(5, -64, 0, BASALT, OVERWORLD)
        .withVariant(90)
        .buildVariant()
        .withVariant(7)
        .withThinChance()
        .withMaterial(Ilmenite)
        .buildVariant()
        .withVariant(3)
        .withThinChance()
        .withMaterial(Rutile)
        .buildVariant()
        .buildVein());

      ev.vein(new WorldGenVeinBuilder("vein_komatiite")
        .asMediumStoneVein(4, 0, 64, KOMATIITE, OVERWORLD)
        .withVariant(86)
        .buildVariant()
        .withVariant(7)
        .withThinChance()
        .withMaterial(Cinnabar, 1)
        .withMaterial(Redstone, 2)
        .buildVariant()
        .withVariant(4)
        .withThinChance()
        .withMaterial(Magnesite)
        .buildVariant()
        .withVariant(3)
        .withThinChance()
        .withMaterial(Pyrite)
        .buildVariant()
        .buildVein());

      ev.vein(new WorldGenVeinBuilder("vein_marble")
        .asLargeStoneVein(4, 32, 160, MARBLE, OVERWORLD)
        .buildVein());

      ev.vein(new WorldGenVeinBuilder("vein_limestone")
        .asLargeStoneVein(4, 32, 160, LIMESTONE, OVERWORLD)
        .buildVein());

      ev.vein(new WorldGenVeinBuilder("vein_green_schist")
        .asMediumStoneVein(2, 64, 320, GREEN_SCHIST, OVERWORLD)
        .buildVein());

      ev.vein(new WorldGenVeinBuilder("vein_blue_schist")
        .asMediumStoneVein(2, 64, 320, BLUE_SCHIST, OVERWORLD)
        .buildVein());

      ev.vein(new WorldGenVeinBuilder("vein_kimberlite")
        .asMediumStoneVein(3, 64, 320, KIMBERLITE, OVERWORLD)
        .buildVein());

      ev.vein(new WorldGenVeinBuilder("vein_quartzite")
        .asMediumStoneVein(3, 64, 320, QUARTZITE, OVERWORLD)
        .buildVein());

  }

  private static void initStoneOreVeins(AntimatterWorldGenEvent ev) {
    WorldGenVein.setLayerChance(WorldGenVein.STONE_ORE_VEIN_LAYER, 0.05f);

      ev.vein( new WorldGenVeinBuilder("vein_coal")
        .asMediumStoneOreVein(1, 32, 320, Coal, OVERWORLD)
        .buildVein());

      ev.vein(new WorldGenVeinBuilder("vein_lignite")
        .asMediumStoneOreVein(1, 32, 320, Lignite, OVERWORLD)
        .buildVein());

      ev.vein(new WorldGenVeinBuilder("vein_salt")
        .asMediumStoneOreVein(1, 32, 320, Salt, OVERWORLD)
        .buildVein());

      ev.vein(new WorldGenVeinBuilder("vein_rock_salt")
        .asMediumStoneOreVein(1, 32, 320, RockSalt, OVERWORLD)
        .buildVein());

      ev.vein(new WorldGenVeinBuilder("vein_bauxite")
        .asMediumStoneOreVein(1, 32, 320, Bauxite, OVERWORLD)
        .buildVein());

      ev.vein(new WorldGenVeinBuilder("vein_oil_shale")
        .asMediumStoneOreVein(1, 32, 320, OilShale, OVERWORLD)
        .buildVein());
  }

  private static void initOreVeins(AntimatterWorldGenEvent ev) {
    WorldGenVein.setLayerChance(WorldGenVein.ORE_VEIN_LAYER, 0.1f);
    // TODO: move veins from initOld here
      ev.vein(new WorldGenVeinBuilder("naquadah").asOreVein(-54, 21, 10, 5, 32, Naquadah, Naquadah, Naquadah, EnrichedNaquadah,
        Level.END).buildVein());
      ev.vein(new WorldGenVeinBuilder("lignite").asOreVein(-54, 21, 10, 5, 32, Naquadah, Naquadah, Naquadah, EnrichedNaquadah,
        Level.END).buildVein());
      ev.vein(new WorldGenVeinBuilder("coal").asOreVein(-14, 31, 80, 6, 32, Coal, Coal, Coal, Lignite, OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinBuilder("magnetite").asOreVein(-14, 91, 160, 3, 32, Magnetite, Magnetite, Iron, VanadiumMagnetite,
        OVERWORLD, Level.NETHER).buildVein());
      ev.vein(new WorldGenVeinBuilder("gold").asOreVein(-4, 26, 160, 3, 32, Magnetite, Magnetite, VanadiumMagnetite, Gold,
        OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinBuilder("iron").asOreVein(-54, -9, 120, 4, 24, BrownLimonite, YellowLimonite, BandedIron, Malachite,
        OVERWORLD, Level.NETHER).buildVein());
      ev.vein(new WorldGenVeinBuilder("cassiterite").asOreVein(-24, 96, 50, 5, 24, Tin, Tin, Cassiterite, Tin, OVERWORLD,
        Level.END).buildVein());
      ev.vein(new WorldGenVeinBuilder("tetrahedrite").asOreVein(16, 76, 70, 4, 24, Tetrahedrite, Tetrahedrite, Copper, Stibnite,
        OVERWORLD, Level.NETHER).buildVein());
      ev.vein(new WorldGenVeinBuilder("nether_quartz").asOreVein(-24, 36, 80, 5, 24, Quartz, Quartz, Quartz, Quartz,
        Level.NETHER).buildVein());
      ev.vein(new WorldGenVeinBuilder("sulfur").asOreVein(-59, -36, 100, 5, 24, Sulfur, Sulfur, Pyrite, Sphalerite, Level.NETHER).buildVein());
      ev.vein(new WorldGenVeinBuilder("copper").asOreVein(-54, -24, 80, 4, 24, Chalcopyrite, Iron, Pyrite, Copper, OVERWORLD,
        Level.NETHER).buildVein());
      ev.vein(new WorldGenVeinBuilder("redstone").asOreVein(-54, -9, 60, 3, 24, Redstone, Redstone, Ruby, Cinnabar, OVERWORLD,
        Level.NETHER).buildVein());
      ev.vein(new WorldGenVeinBuilder("soapstone").asOreVein(-54, -9, 40, 3, 16, Soapstone, Talc, Glauconite, Pentlandite,
        OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinBuilder("nickel").asOreVein(-54, -9, 40, 3, 16, Garnierite, Nickel, Cobaltite, Pentlandite,
        OVERWORLD, Level.NETHER, Level.END).buildVein());
      ev.vein(new WorldGenVeinBuilder("platinum").asOreVein(-24, -9, 5, 3, 16, Cooperite, Palladium, Platinum, Iridium, OVERWORLD,
        Level.END).buildVein());
      ev.vein(new WorldGenVeinBuilder("pitchblend").asOreVein(-54, -9, 40, 3, 16, Pitchblende, Pitchblende, Uraninite, Uraninite,
        OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinBuilder("uranium").asOreVein(-44, -29, 20, 3, 16, Uraninite, Uraninite, Uranium, Uranium,
        OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinBuilder("monazite").asOreVein(-44, -14, 30, 3, 16, Bastnasite, Bastnasite, Bastnasite/* Monazite */,
        Neodymium, OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinBuilder("molybdenum").asOreVein(-44, 1, 5, 3, 16, Wulfenite, Molybdenite, Molybdenum,
        Molybdenum/* Powellite */, OVERWORLD, Level.END).buildVein());
      ev.vein(new WorldGenVeinBuilder("tungstate").asOreVein(-44, 1, 10, 3, 16, Scheelite, Scheelite, Tungstate, Lithium,
        OVERWORLD, Level.END).buildVein());
      ev.vein(new WorldGenVeinBuilder("sapphire").asOreVein(-54, -9, 60, 3, 16, Almandine, Pyrope, BlueSapphire, GreenSapphire,
        OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinBuilder("manganese").asOreVein(-44, -29, 20, 3, 16, Grossular, Spessartine, Pyrolusite, Tantalite,
        OVERWORLD, Level.END).buildVein());
      ev.vein(new WorldGenVeinBuilder("quartz").asOreVein(-24, 36, 60, 3, 16, Quartzite, Barite, Barite/* CertusQuartz */,
        Barite/* CertusQuartz */, OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinBuilder("diamond").asOreVein(-119, -81, 40, 2, 16, Graphite, Graphite, Diamond, Coal, OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinBuilder("olivine").asOreVein(-54, -9, 60, 3, 16, Bentonite, Magnesite, Olivine, Glauconite,
        OVERWORLD, Level.END).buildVein());
      ev.vein(new WorldGenVeinBuilder("galena").asOreVein(-34, 11, 40, 5, 16, Galena, Galena, Silver, Lead, OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinBuilder("lapis").asOreVein(-44, 1, 40, 5, 16, Lapis/* Lazurite */, Lapis/* Sodalite */, Lapis,
        Calcite, OVERWORLD, Level.END).buildVein());
      ev.vein(new WorldGenVeinBuilder("beryllium").asOreVein(-59, -21, 30, 3, 16, Beryllium, Beryllium, Emerald, Thorium,
        OVERWORLD, Level.END).buildVein());
      ev.vein(new WorldGenVeinBuilder("oilsands").asOreVein(-14, 31, 80, 6, 32, Oilsands, Oilsands, Oilsands, Oilsands,
        OVERWORLD).buildVein());
      ev.vein(new WorldGenVeinBuilder("apatite").asOreVein(-14, 31, 80, 6, 32, Oilsands, Oilsands, Oilsands, Oilsands, OVERWORLD).buildVein());

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
