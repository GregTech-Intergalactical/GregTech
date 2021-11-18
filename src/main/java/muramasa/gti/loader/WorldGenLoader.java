package muramasa.gti.loader;

import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.Data;
import muramasa.antimatter.worldgen.StoneLayerOre;
import muramasa.antimatter.worldgen.object.WorldGenOreSmall;
import muramasa.antimatter.worldgen.object.WorldGenStoneLayer;
import muramasa.antimatter.worldgen.object.WorldGenVeinLayer;
import muramasa.gti.data.GregTechData;
import net.minecraft.world.World;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.Ref.*;
import static muramasa.gti.data.Materials.*;

public class WorldGenLoader {


    public static void init() {
        //TODO probably increase max generation heights for most things
        //TODO add GC dims to all objects

     //   new WorldGenAsteroid("asteroids", World.THE_END/*, ASTEROIDS */);

//        new WorldGenStone("granite_black_tiny", GRANITE_BLACK, 1, 75, 5, 0, 180, World.OVERWORLD);
//        new WorldGenStone("granite_black_small", GRANITE_BLACK, 1, 100, 10, 0, 180, World.OVERWORLD);
//        new WorldGenStone("granite_black_medium", GRANITE_BLACK, 1, 200, 10, 0, 120, World.OVERWORLD);
//        new WorldGenStone("granite_black_large", GRANITE_BLACK, 1, 300, 70, 0, 120, World.OVERWORLD);
//        new WorldGenStone("granite_black_huge", GRANITE_BLACK, 1, 400, 150, 0, 120, World.OVERWORLD);
//
//        new WorldGenStone("granite_red_tiny", GRANITE_RED, 1, 75, 5, 0, 180, World.OVERWORLD);
//        new WorldGenStone("granite_red_small", GRANITE_RED, 1, 100, 10, 0, 180, World.OVERWORLD);
//        new WorldGenStone("granite_red_medium", GRANITE_RED, 1, 200, 10, 0, 120, World.OVERWORLD);
//        new WorldGenStone("granite_red_large", GRANITE_RED, 1, 300, 70, 0, 120, World.OVERWORLD);
//        new WorldGenStone("granite_red_huge", GRANITE_RED, 1, 400, 150, 0, 120, World.OVERWORLD);
//
//        new WorldGenStone("marble_tiny", MARBLE, 1, 75, 5, 0, 180, World.OVERWORLD);
//        new WorldGenStone("marble_small", MARBLE, 1, 100, 10, 0, 180, World.OVERWORLD);
//        new WorldGenStone("marble_medium", MARBLE, 1, 200, 10, 0, 120, World.OVERWORLD);
//        new WorldGenStone("marble_large", MARBLE, 1, 300, 70, 0, 120, World.OVERWORLD);
//        new WorldGenStone("marble_huge", MARBLE, 1, 400, 150, 0, 120, World.OVERWORLD);
//
//        new WorldGenStone("basalt_tiny", BASALT, 1, 75, 5, 0, 180, World.OVERWORLD);
//        new WorldGenStone("basalt_small", BASALT, 1, 100, 10, 0, 180, World.OVERWORLD);
//        new WorldGenStone("basalt_medium", BASALT, 1, 200, 10, 0, 120, World.OVERWORLD);
//        new WorldGenStone("basalt_large", BASALT, 1, 300, 70, 0, 120, World.OVERWORLD);
//        new WorldGenStone("basalt_huge", BASALT, 1, 400, 150, 0, 120, World.OVERWORLD);
        
        WorldGenStoneLayer.add(Data.STONE, 4, World.OVERWORLD);
        WorldGenStoneLayer.add(Data.STONE, 1, World.OVERWORLD); //Ores
        WorldGenStoneLayer.add(Data.STONE, 1, World.OVERWORLD); //Ores
        WorldGenStoneLayer.add(Data.STONE, 1, World.OVERWORLD); //Ores
        WorldGenStoneLayer.add(Data.STONE, 1, World.OVERWORLD); //Ores
        
        WorldGenStoneLayer.add(GregTechData.GRANITE_BLACK, 2, World.OVERWORLD);
        WorldGenStoneLayer.add(GregTechData.GRANITE_BLACK, 1, World.OVERWORLD).forEach(w -> w.addOres(
            new StoneLayerOre(Cooperite, U32, 0, 16),
            new StoneLayerOre(Iridium, U64, 0, 8)
        ));

        WorldGenStoneLayer.add(GregTechData.GRANITE_RED, 2, World.OVERWORLD);
        WorldGenStoneLayer.add(GregTechData.GRANITE_RED, 1, World.OVERWORLD).forEach(w -> w.addOres(
            new StoneLayerOre(Pitchblende, U32, 0, 18),
            new StoneLayerOre(Uraninite, U32, 0, 16),
            new StoneLayerOre(Tantalite, U16, 20, 50)
        ));

        WorldGenStoneLayer.add(GregTechData.KOMATIITE, 4, World.OVERWORLD);
        WorldGenStoneLayer.add(GregTechData.KOMATIITE, 1, World.OVERWORLD).forEach(w -> w.addOres(
            new StoneLayerOre(Magnesite, U16, 20, 50),
            new StoneLayerOre(Cinnabar, U12, 0, 32),
            new StoneLayerOre(Redstone, U8, 0, 30),
            new StoneLayerOre(Pyrite, U12, 0, 30)
        ));

        WorldGenStoneLayer.add(GregTechData.BASALT, 3, World.OVERWORLD);
        WorldGenStoneLayer.add(GregTechData.BASALT, 1, World.OVERWORLD); //Ores
        WorldGenStoneLayer.add(GregTechData.BASALT, 1, World.OVERWORLD); //Ores

        WorldGenStoneLayer.add(GregTechData.MARBLE, 4, World.OVERWORLD);
        WorldGenStoneLayer.add(GregTechData.MARBLE, 1, World.OVERWORLD); //Ores

        WorldGenStoneLayer.add(GregTechData.LIMESTONE, 3, World.OVERWORLD);
        WorldGenStoneLayer.add(GregTechData.LIMESTONE, 1, World.OVERWORLD); //Ores
        WorldGenStoneLayer.add(GregTechData.LIMESTONE, 1, World.OVERWORLD); //Ores

        WorldGenStoneLayer.add(GregTechData.GREEN_SCHIST, 1, World.OVERWORLD);
        WorldGenStoneLayer.add(GregTechData.GREEN_SCHIST, 1, World.OVERWORLD); //Ores

        WorldGenStoneLayer.add(GregTechData.BLUE_SCHIST, 1, World.OVERWORLD);
        WorldGenStoneLayer.add(GregTechData.BLUE_SCHIST, 1, World.OVERWORLD); //Ores

        WorldGenStoneLayer.add(GregTechData.KIMBERLITE, 3, World.OVERWORLD);
        WorldGenStoneLayer.add(GregTechData.KIMBERLITE, 1, World.OVERWORLD); //Ores

        WorldGenStoneLayer.add(GregTechData.QUARTZITE, 4, World.OVERWORLD);
        WorldGenStoneLayer.add(GregTechData.QUARTZITE, 1, World.OVERWORLD); //Ores

        WorldGenStoneLayer.add(Data.GRANITE, 3, World.OVERWORLD);
        WorldGenStoneLayer.add(Data.GRANITE, 1, World.OVERWORLD); //Ores
        WorldGenStoneLayer.add(Data.GRANITE, 1, World.OVERWORLD); //Ores

        WorldGenStoneLayer.add(Data.DIORITE, 3, World.OVERWORLD);
        WorldGenStoneLayer.add(Data.DIORITE, 1, World.OVERWORLD); //Ores
        WorldGenStoneLayer.add(Data.DIORITE, 1, World.OVERWORLD); //Ores

        WorldGenStoneLayer.add(Data.ANDESITE, 4, World.OVERWORLD);
        WorldGenStoneLayer.add(Data.ANDESITE, 1, World.OVERWORLD); //Ores
        WorldGenStoneLayer.add(Data.ANDESITE, 1, World.OVERWORLD); //Ores

        WorldGenStoneLayer.add(Data.ORE_STONE.get().get(Coal).asState(), 1, World.OVERWORLD);
        WorldGenStoneLayer.add(Data.ORE_STONE.get().get(Lignite).asState(), 1, World.OVERWORLD);
        WorldGenStoneLayer.add(Data.ORE_STONE.get().get(Salt).asState(), 1, World.OVERWORLD);
        WorldGenStoneLayer.add(Data.ORE_STONE.get().get(RockSalt).asState(), 1, World.OVERWORLD);
        WorldGenStoneLayer.add(Data.ORE_STONE.get().get(Bauxite).asState(), 1, World.OVERWORLD);
        WorldGenStoneLayer.add(Data.ORE_STONE.get().get(OilShale).asState(), 1, World.OVERWORLD);

        WorldGenStoneLayer.addCollision(GregTechData.BASALT.getState(), GregTechData.LIMESTONE.getState(),
            new StoneLayerOre(Ilmenite, U8, 0, 32),
            new StoneLayerOre(Rutile, U12, 0, 32)
        );

        if (AntimatterConfig.WORLD.ORE_VEINS) {
            new WorldGenVeinLayer("naquadah", 10, 60, 10, 5, 32, Naquadah, Naquadah, Naquadah, EnrichedNaquadah, World.END);
            //new WorldGenVeinLayer("lignite", 50, 130, 160, 8, 32, Lignite, Lignite, Lignite, Coal, World.OVERWORLD);
            //new WorldGenVeinLayer("coal", 50, 80, 80, 6, 32, Coal, Coal, Coal, Lignite, World.OVERWORLD);
            new WorldGenVeinLayer("magnetite", 50, 120, 160, 3, 32, Magnetite, Magnetite, Iron, VanadiumMagnetite, World.OVERWORLD, World.NETHER);
            new WorldGenVeinLayer("gold", 60, 80, 160, 3, 32, Magnetite, Magnetite, VanadiumMagnetite, Gold, World.OVERWORLD);
            new WorldGenVeinLayer("iron", 10, 40, 120, 4, 24, BrownLimonite, YellowLimonite, BandedIron, Malachite, World.OVERWORLD, World.NETHER);
            new WorldGenVeinLayer("cassiterite", 40, 120, 50, 5, 24, Tin, Tin, Cassiterite, Tin, World.OVERWORLD, World.END);
            new WorldGenVeinLayer("tetrahedrite", 80, 120, 70, 4, 24, Tetrahedrite, Tetrahedrite, Copper, Stibnite, World.OVERWORLD, World.NETHER);
            new WorldGenVeinLayer("neter_quartz", 40, 80, 80, 5, 24, Quartz, Quartz, Quartz, Quartz, World.NETHER);
            new WorldGenVeinLayer("sulfur", 5, 20, 100, 5, 24, Sulfur, Sulfur, Pyrite, Sphalerite, World.NETHER);
            new WorldGenVeinLayer("copper", 10, 30, 80, 4, 24, Chalcopyrite, Iron, Pyrite, Copper, World.OVERWORLD, World.NETHER);
            //new WorldGenVeinLayer("bauxite", 50, 90, 80, 4, 24, Bauxite, Bauxite, Aluminium, Ilmenite, World.OVERWORLD);
            //new WorldGenVeinLayer("salts", 50, 60, 50, 3, 24, RockSalt, Salt, Lepidolite, Spodumene, World.OVERWORLD);
            new WorldGenVeinLayer("redstone", 10, 40, 60, 3, 24, Redstone, Redstone, Ruby, Cinnabar, World.OVERWORLD, World.NETHER);
            new WorldGenVeinLayer("soapstone", 10, 40, 40, 3, 16, Soapstone, Talc, Glauconite, Pentlandite, World.OVERWORLD);
            new WorldGenVeinLayer("nickel", 10, 40, 40, 3, 16, Garnierite, Nickel, Cobaltite, Pentlandite, World.OVERWORLD, World.NETHER, World.END);
            new WorldGenVeinLayer("platinum", 40, 50, 5, 3, 16, Cooperite, Palladium, Platinum, Iridium, World.OVERWORLD, World.END);
            new WorldGenVeinLayer("pitchblend", 10, 40, 40, 3, 16, Pitchblende, Pitchblende, Uraninite, Uraninite, World.OVERWORLD);
            new WorldGenVeinLayer("uranium", 20, 30, 20, 3, 16, Uraninite, Uraninite, Uranium, Uranium, World.OVERWORLD);
            new WorldGenVeinLayer("monazite", 20, 40, 30, 3, 16, Bastnasite, Bastnasite, Bastnasite/*Monazite*/, Neodymium, World.OVERWORLD);
            new WorldGenVeinLayer("molybdenum", 20, 50, 5, 3, 16, Wulfenite, Molybdenite, Molybdenum, Molybdenum/*Powellite*/, World.OVERWORLD, World.END);
            new WorldGenVeinLayer("tungstate", 20, 50, 10, 3, 16, Scheelite, Scheelite, Tungstate, Lithium, World.OVERWORLD, World.END);
            new WorldGenVeinLayer("sapphire", 10, 40, 60, 3, 16, Almandine, Pyrope, BlueSapphire, GreenSapphire, World.OVERWORLD);
            new WorldGenVeinLayer("manganese", 20, 30, 20, 3, 16, Grossular, Spessartine, Pyrolusite, Tantalite, World.OVERWORLD, World.END);
            new WorldGenVeinLayer("quartz", 40, 80, 60, 3, 16, Quartzite, Barite, Barite/*CertusQuartz*/, Barite/*CertusQuartz*/, World.OVERWORLD);
            new WorldGenVeinLayer("diamond", 5, 20, 40, 2, 16, Graphite, Graphite, Diamond, Coal, World.OVERWORLD);
            new WorldGenVeinLayer("olivine", 10, 40, 60, 3, 16, Bentonite, Magnesite, Olivine, Glauconite, World.OVERWORLD, World.END);
            //new WorldGenLayer("apatite", 40, 60, 60, 3, 16, Apatite, Apatite, TricalciumPhosphate, Pyrochlore);
            new WorldGenVeinLayer("gelena", 30, 60, 40, 5, 16, Galena, Galena, Silver, Lead, World.OVERWORLD);
            new WorldGenVeinLayer("lapis", 20, 50, 40, 5, 16, Lapis/*Lazurite*/, Lapis/*Sodalite*/, Lapis, Calcite, World.OVERWORLD, World.END);
            new WorldGenVeinLayer("beryllium", 5, 30, 30, 3, 16, Beryllium, Beryllium, Emerald, Thorium, World.OVERWORLD, World.END);
            new WorldGenVeinLayer("oilsands", 50, 80, 80, 6, 32, Oilsands, Oilsands, Oilsands, Oilsands, World.OVERWORLD);
        }

        new WorldGenOreSmall(60, 120, 16, Chalcopyrite, World.OVERWORLD, World.NETHER, World.END/*, MARS */);
        new WorldGenOreSmall(60, 120, 16, Cassiterite, World.OVERWORLD, World.NETHER, World.END/*, MARS */);
        new WorldGenOreSmall(60, 120, 16, Copper, World.OVERWORLD, World.NETHER, World.END/*, MOON *//*, MARS */);
        new WorldGenOreSmall(60, 120, 16, Tin, World.OVERWORLD, World.NETHER, World.END/*, MOON *//*, MARS *//*, ASTEROIDS */);
        new WorldGenOreSmall(80, 120, 8, Bismuth, World.OVERWORLD, World.NETHER/*, MOON *//*, MARS */);
        new WorldGenOreSmall(60, 100, 24, Coal, World.OVERWORLD);

        //TODO replace and also swap ORE_SMALL tags in Materials
        //new WorldGenOreSmall(40, 80, 24, Hematite, World.OVERWORLD, World.THE_NETHER, World.THE_END/*, MOON *//*, MARS */);
        new WorldGenOreSmall(40, 80, 16, Iron, World.OVERWORLD, World.NETHER, World.END/*, MOON *//*, MARS */);

        new WorldGenOreSmall(40, 80, 6, Salt, World.OVERWORLD, World.NETHER, World.END/*, MARS */);
        new WorldGenOreSmall(40, 80, 6, RockSalt, World.OVERWORLD, World.NETHER, World.END/*, MARS */);
        new WorldGenOreSmall(30, 60, 12, Sphalerite, World.OVERWORLD, World.NETHER, World.END/*, MARS */);
        new WorldGenOreSmall(40, 70, 4, Zinc, World.OVERWORLD, World.NETHER, World.END/*, MOON *//*, MARS *//*, ASTEROIDS */);

        //TODO replace and also swap ORE_SMALL tags in Materials
        //new WorldGenOreSmall(30, 60, 2, Smithsonite, World.OVERWORLD, World.THE_NETHER, World.THE_END/*, MARS */);
        new WorldGenOreSmall(20, 40, 8, Nickel, World.OVERWORLD, World.NETHER, World.END/*, MOON *//*, MARS *//*, ASTEROIDS */);

        new WorldGenOreSmall(40, 80, 8, Galena, World.OVERWORLD, World.NETHER, World.END/*, MARS */);
        new WorldGenOreSmall(40, 80, 8, Lead, World.OVERWORLD, World.NETHER, World.END/*, MOON *//*, MARS *//*, ASTEROIDS */);
        new WorldGenOreSmall(20, 40, 4, Gold, World.OVERWORLD, World.NETHER, World.END/*, MOON *//*, MARS *//*, ASTEROIDS */);
        new WorldGenOreSmall(20, 40, 4, Pyrite, World.OVERWORLD, World.NETHER, World.END/*, MOON *//*, MARS *//*, ASTEROIDS */);
        new WorldGenOreSmall(20, 40, 4, Silver, World.OVERWORLD, World.NETHER, World.END/*, MOON *//*, MARS *//*, ASTEROIDS */);
        new WorldGenOreSmall(20, 40, 4, Pyrolusite, World.OVERWORLD, World.NETHER, World.END/*, MOON *//*, MARS */);
        new WorldGenOreSmall(20, 40, 4, Garnierite, World.OVERWORLD, World.NETHER, World.END/*, MARS */);
        new WorldGenOreSmall(20, 40, 4, Pentlandite, World.OVERWORLD, World.NETHER, World.END/*, MARS */);
        new WorldGenOreSmall(20, 40, 2, Stibnite, World.OVERWORLD, World.NETHER, World.END/*, MARS */);
        //new WorldGenOreSmall(20, 40, 8, Asbestos, World.OVERWORLD);
        //new WorldGenOreSmall(20, 40, 4, Eudialyte, World.OVERWORLD);
        //new WorldGenOreSmall(20, 40, 4, Azurite, World.OVERWORLD);
        //new WorldGenOreSmall(1, 250, 1, Zeolite, World.OVERWORLD);
        new WorldGenOreSmall(20, 40, 4, Lapis, World.OVERWORLD);
        new WorldGenOreSmall(5, 20, 8, Redstone, World.OVERWORLD, World.NETHER/*, MARS */);
        new WorldGenOreSmall(5, 10, 2, Graphite, World.OVERWORLD, World.NETHER/*, MARS */);
        //new WorldGenOreSmall(5, 50, 1, Sheelite, World.OVERWORLD, World.THE_END/*, MOON *//*, MARS *//*, ASTEROIDS */);
        new WorldGenOreSmall(20, 40, 4, Cooperite, World.END/*, ASTEROIDS */);
        //new WorldGenOreSmall(20, 40, 4, Sperrylite, World.THE_END/*, ASTEROIDS */);
        new WorldGenOreSmall(20, 40, 6, Platinum, World.END/*, ASTEROIDS */);
        new WorldGenOreSmall(20, 40, 6, Iridium, World.END/*, ASTEROIDS */);
        new WorldGenOreSmall(30, 120, 64, Quartz, World.NETHER);
        new WorldGenOreSmall(10, 60, 8, Saltpeter, World.NETHER);
        //new WorldGenOreSmall(10, 60, 8, SodiumNitrate, World.THE_NETHER);
        new WorldGenOreSmall("sulfur_World.THE_NETHER", 10, 60, 32, Sulfur, World.NETHER);
        new WorldGenOreSmall("sulfur_World.OVERWORLD", 5, 15, 8, Sulfur, World.OVERWORLD/*, MARS */);

        //TODO replace with GarnetSand?
        new WorldGenOreSmall(5, 250, 1, RedGarnet, World.OVERWORLD, World.NETHER/*, MARS *//*, ASTEROIDS */);
        new WorldGenOreSmall(5, 250, 1, YellowGarnet, World.OVERWORLD, World.NETHER/*, MARS *//*, ASTEROIDS */);

        new WorldGenOreSmall(5, 10, 2, Diamond, World.OVERWORLD, World.NETHER/*, MOON *//*, MARS *//*, ASTEROIDS */);
        new WorldGenOreSmall(5, 250, 1, Emerald, World.OVERWORLD/*, MARS */);
        //new WorldGenOreSmall(5, 250, 1, Aquamarine, World.OVERWORLD/*, MARS */);
        //new WorldGenOreSmall(5, 250, 1, Morganite, World.OVERWORLD/*, MARS */);
        //new WorldGenOreSmall(5, 250, 1, Heliodor, World.OVERWORLD/*, MARS */);
        //new WorldGenOreSmall(5, 250, 1, Goshenite, World.OVERWORLD/*, MARS */);
        //new WorldGenOreSmall(5, 250, 1, Bixbite, World.OVERWORLD/*, MARS */);
        //new WorldGenOreSmall(5, 250, 1, Maxixe, World.OVERWORLD/*, MARS */);
        //new WorldGenOreSmall(5, 250, 1, Dioptase, World.OVERWORLD/*, MARS */);
        new WorldGenOreSmall(5, 250, 1, Ruby, World.OVERWORLD/*, MARS */);
        new WorldGenOreSmall(5, 250, 1, BlueSapphire, World.OVERWORLD/*, MARS */);
        new WorldGenOreSmall(5, 250, 1, GreenSapphire, World.OVERWORLD/*, MARS */);
        //new WorldGenOreSmall(5, 250, 1, YellowSapphire, World.OVERWORLD/*, MARS */);
        //new WorldGenOreSmall(5, 250, 1, OrangeSapphire, World.OVERWORLD/*, MARS */);
        //new WorldGenOreSmall(5, 250, 1, PurpleSapphire, World.OVERWORLD/*, MARS */);
        new WorldGenOreSmall(5, 250, 1, Olivine, World.OVERWORLD/*, MARS */);
        //new WorldGenOreSmall(5, 250, 1, Craponite, World.OVERWORLD/*, MARS */);
        new WorldGenOreSmall(5, 250, 1, Topaz, World.OVERWORLD/*, MARS */);
        new WorldGenOreSmall(5, 250, 1, Tanzanite, World.OVERWORLD/*, MARS */);
        new WorldGenOreSmall(5, 250, 1, Amethyst, World.OVERWORLD/*, MARS */);
        //new WorldGenOreSmall(5, 250, 1, Alexandrite, World.OVERWORLD/*, MARS */);
        new WorldGenOreSmall(5, 250, 1, Opal, World.OVERWORLD/*, MARS */);
        //new WorldGenOreSmall(5, 250, 1, Jasper, World.OVERWORLD/*, MARS */);
        new WorldGenOreSmall(5, 250, 1, BlueTopaz, World.OVERWORLD/*, MARS */);
        //new WorldGenOreSmall(5, 250, 1, BalasRuby, World.OVERWORLD/*, MARS */);
        //new WorldGenOreSmall(5, 250, 1, Spinel, World.OVERWORLD/*, MARS */);
        new WorldGenOreSmall(5, 250, 1, Pyrope, World.OVERWORLD/*, MARS */);

        new WorldGenOreSmall(5, 250, 1, Almandine, World.OVERWORLD/*, MARS */);
        new WorldGenOreSmall(5, 250, 1, Spessartine, World.OVERWORLD/*, MARS */);
        new WorldGenOreSmall(5, 250, 1, Andradite, World.OVERWORLD/*, MARS */);
        new WorldGenOreSmall(5, 250, 1, Grossular, World.OVERWORLD/*, MARS */);
        new WorldGenOreSmall(5, 250, 1, Uvarovite, World.OVERWORLD/*, MARS */);
        //new WorldGenOreSmall(5, 250, 1, Jade, World.OVERWORLD/*, MARS */);
        //new WorldGenOreSmall(5, 250, 1, Amazonite, World.OVERWORLD/*, MARS */);
        //new WorldGenOreSmall(5, 250, 1, RedOnyx, World.OVERWORLD/*, MARS */);
        //new WorldGenOreSmall(5, 250, 1, BlackOnyx, World.OVERWORLD/*, MARS */);
        //new WorldGenOreSmall(5, 250, 1, Amber, World.OVERWORLD/*, MARS */);
    }
}
