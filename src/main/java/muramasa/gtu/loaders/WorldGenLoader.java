package muramasa.gtu.loaders;

import muramasa.antimatter.worldgen.*;
import muramasa.gtu.common.Data;
import net.minecraft.block.Blocks;

import static muramasa.antimatter.Ref.*;
import static muramasa.gtu.data.Materials.*;

public class WorldGenLoader {

    public static void init() {
        //TODO probably increase max generation heights for most things
        //TODO add GC dims to all objects

        AntimatterWorldGenerator.STATES_TO_PURGE.put(Blocks.COAL_ORE.getDefaultState(), Blocks.STONE.getDefaultState());
        AntimatterWorldGenerator.STATES_TO_PURGE.put(Blocks.IRON_ORE.getDefaultState(), Blocks.STONE.getDefaultState());
        AntimatterWorldGenerator.STATES_TO_PURGE.put(Blocks.GOLD_ORE.getDefaultState(), Blocks.STONE.getDefaultState());
        AntimatterWorldGenerator.STATES_TO_PURGE.put(Blocks.DIAMOND_ORE.getDefaultState(), Blocks.STONE.getDefaultState());
        AntimatterWorldGenerator.STATES_TO_PURGE.put(Blocks.EMERALD_ORE.getDefaultState(), Blocks.STONE.getDefaultState());
        AntimatterWorldGenerator.STATES_TO_PURGE.put(Blocks.LAPIS_ORE.getDefaultState(), Blocks.STONE.getDefaultState());
        AntimatterWorldGenerator.STATES_TO_PURGE.put(Blocks.REDSTONE_ORE.getDefaultState(), Blocks.STONE.getDefaultState());

        new WorldGenRock("surface_rock", 1, OVERWORLD);

        //new WorldGenTree("rubber_tree", 5, 5, Data.RUBBER_SAPLING, OVERWORLD);

        new WorldGenAsteroid("asteroids", END, ASTEROIDS);

//        new WorldGenStone("granite_black_tiny", GRANITE_BLACK, 1, 75, 5, 0, 180, OVERWORLD);
//        new WorldGenStone("granite_black_small", GRANITE_BLACK, 1, 100, 10, 0, 180, OVERWORLD);
//        new WorldGenStone("granite_black_medium", GRANITE_BLACK, 1, 200, 10, 0, 120, OVERWORLD);
//        new WorldGenStone("granite_black_large", GRANITE_BLACK, 1, 300, 70, 0, 120, OVERWORLD);
//        new WorldGenStone("granite_black_huge", GRANITE_BLACK, 1, 400, 150, 0, 120, OVERWORLD);
//
//        new WorldGenStone("granite_red_tiny", GRANITE_RED, 1, 75, 5, 0, 180, OVERWORLD);
//        new WorldGenStone("granite_red_small", GRANITE_RED, 1, 100, 10, 0, 180, OVERWORLD);
//        new WorldGenStone("granite_red_medium", GRANITE_RED, 1, 200, 10, 0, 120, OVERWORLD);
//        new WorldGenStone("granite_red_large", GRANITE_RED, 1, 300, 70, 0, 120, OVERWORLD);
//        new WorldGenStone("granite_red_huge", GRANITE_RED, 1, 400, 150, 0, 120, OVERWORLD);
//
//        new WorldGenStone("marble_tiny", MARBLE, 1, 75, 5, 0, 180, OVERWORLD);
//        new WorldGenStone("marble_small", MARBLE, 1, 100, 10, 0, 180, OVERWORLD);
//        new WorldGenStone("marble_medium", MARBLE, 1, 200, 10, 0, 120, OVERWORLD);
//        new WorldGenStone("marble_large", MARBLE, 1, 300, 70, 0, 120, OVERWORLD);
//        new WorldGenStone("marble_huge", MARBLE, 1, 400, 150, 0, 120, OVERWORLD);
//
//        new WorldGenStone("basalt_tiny", BASALT, 1, 75, 5, 0, 180, OVERWORLD);
//        new WorldGenStone("basalt_small", BASALT, 1, 100, 10, 0, 180, OVERWORLD);
//        new WorldGenStone("basalt_medium", BASALT, 1, 200, 10, 0, 120, OVERWORLD);
//        new WorldGenStone("basalt_large", BASALT, 1, 300, 70, 0, 120, OVERWORLD);
//        new WorldGenStone("basalt_huge", BASALT, 1, 400, 150, 0, 120, OVERWORLD);

        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.STONE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.STONE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.STONE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.STONE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.STONE)); //Ores
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.STONE)); //Ores
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.STONE)); //Ores
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.STONE)); //Ores

        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.GRANITE_BLACK));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.GRANITE_BLACK));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.GRANITE_BLACK)); //Ores
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.GRANITE_BLACK)); //Ores
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.GRANITE_BLACK)); //Ores
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.GRANITE_BLACK)); //Ores

        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.GRANITE_RED));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.GRANITE_RED));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.GRANITE_RED)); //Ores

        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.KOMATIITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.KOMATIITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.KOMATIITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.KOMATIITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.KOMATIITE)); //Ores

        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.BASALT));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.BASALT));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.BASALT));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.BASALT)); //Ores
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.BASALT)); //Ores
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.BASALT)); //Ores
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.BASALT)); //Ores
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.BASALT)); //Ores

        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.MARBLE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.MARBLE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.MARBLE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.MARBLE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.MARBLE)); //Ores
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.MARBLE)); //Ores

        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.LIMESTONE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.LIMESTONE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.LIMESTONE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.LIMESTONE)); //Ores
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.LIMESTONE)); //Ores
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.LIMESTONE)); //Ores
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.LIMESTONE)); //Ores

        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.GREEN_SCHIST));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.GREEN_SCHIST)); //Ores

        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.BLUE_SCHIST));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.BLUE_SCHIST)); //Ores

        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.KIMBERLITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.KIMBERLITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.KIMBERLITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.KIMBERLITE)); //Ores

        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.QUARTZITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.QUARTZITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.QUARTZITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.QUARTZITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.QUARTZITE)); //Ores

        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.GRANITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.GRANITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.GRANITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.GRANITE)); //Ores
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.GRANITE)); //Ores

        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.DIORITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.DIORITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.DIORITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.DIORITE)); //Ores
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.DIORITE)); //Ores
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.DIORITE)); //Ores

        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.ANDESITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.ANDESITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.ANDESITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.ANDESITE));
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.ANDESITE)); //Ores
        AntimatterWorldGenerator.STONE_LAYERS.add(new StoneLayer(Data.ANDESITE)); //Ores

        new WorldGenVeinLayer("naquadah", 10, 60, 10, 5, 32, Naquadah, Naquadah, Naquadah, NaquadahEnriched, END);
        new WorldGenVeinLayer("lignite", 50, 130, 160, 8, 32, Lignite, Lignite, Lignite, Coal, OVERWORLD);
        new WorldGenVeinLayer("coal", 50, 80, 80, 6, 32, Coal, Coal, Coal, Lignite, OVERWORLD);
        new WorldGenVeinLayer("magnetite", 50, 120, 160, 3, 32, Magnetite, Magnetite, Iron, VanadiumMagnetite, OVERWORLD, NETHER);
        new WorldGenVeinLayer("gold", 60, 80, 160, 3, 32, Magnetite, Magnetite, VanadiumMagnetite, Gold, OVERWORLD);
        new WorldGenVeinLayer("iron", 10, 40, 120, 4, 24, BrownLimonite, YellowLimonite, BandedIron, Malachite, OVERWORLD, NETHER);
        new WorldGenVeinLayer("cassiterite", 40, 120, 50, 5, 24, Tin, Tin, Cassiterite, Tin, OVERWORLD, END);
        new WorldGenVeinLayer("tetrahedrite", 80, 120, 70, 4, 24, Tetrahedrite, Tetrahedrite, Copper, Stibnite, OVERWORLD, NETHER);
        new WorldGenVeinLayer("nether_quartz", 40, 80, 80, 5, 24, NetherQuartz, NetherQuartz, NetherQuartz, NetherQuartz, NETHER);
        new WorldGenVeinLayer("sulfur", 5, 20, 100, 5, 24, Sulfur, Sulfur, Pyrite, Sphalerite, NETHER);
        new WorldGenVeinLayer("copper", 10, 30, 80, 4, 24, Chalcopyrite, Iron, Pyrite, Copper, OVERWORLD, NETHER);
        new WorldGenVeinLayer("bauxite", 50, 90, 80, 4, 24, Bauxite, Bauxite, Aluminium, Ilmenite, OVERWORLD);
        new WorldGenVeinLayer("salts", 50, 60, 50, 3, 24, RockSalt, Salt, Lepidolite, Spodumene, OVERWORLD);
        new WorldGenVeinLayer("redstone", 10, 40, 60, 3, 24, Redstone, Redstone, Ruby, Cinnabar, OVERWORLD, NETHER);
        new WorldGenVeinLayer("soapstone", 10, 40, 40, 3, 16, Soapstone, Talc, Glauconite, Pentlandite, OVERWORLD);
        new WorldGenVeinLayer("nickel", 10, 40, 40, 3, 16, Garnierite, Nickel, Cobaltite, Pentlandite, OVERWORLD, NETHER, END);
        new WorldGenVeinLayer("platinum", 40, 50, 5, 3, 16, Cooperite, Palladium, Platinum, Iridium, OVERWORLD, END);
        new WorldGenVeinLayer("pitchblende", 10, 40, 40, 3, 16, Pitchblende, Pitchblende, Uraninite, Uraninite, OVERWORLD);
        new WorldGenVeinLayer("uranium", 20, 30, 20, 3, 16, Uraninite, Uraninite, Uranium, Uranium, OVERWORLD);
        new WorldGenVeinLayer("monazite", 20, 40, 30, 3, 16, Bastnasite, Bastnasite, Bastnasite/*Monazite*/, Neodymium, OVERWORLD);
        new WorldGenVeinLayer("molybdenum", 20, 50, 5, 3, 16, Wulfenite, Molybdenite, Molybdenum, Molybdenum/*Powellite*/, OVERWORLD, END);
        new WorldGenVeinLayer("tungstate", 20, 50, 10, 3, 16, Scheelite, Scheelite, Tungstate, Lithium, OVERWORLD, END);
        new WorldGenVeinLayer("sapphire", 10, 40, 60, 3, 16, Almandine, Pyrope, BlueSapphire, GreenSapphire, OVERWORLD);
        new WorldGenVeinLayer("manganese", 20, 30, 20, 3, 16, Grossular, Spessartine, Pyrolusite, Tantalite, OVERWORLD, END);
        new WorldGenVeinLayer("quartz", 40, 80, 60, 3, 16, Quartzite, Barite, Barite/*CertusQuartz*/, Barite/*CertusQuartz*/, OVERWORLD);
        new WorldGenVeinLayer("diamond", 5, 20, 40, 2, 16, Graphite, Graphite, Diamond, Coal, OVERWORLD);
        new WorldGenVeinLayer("olivine", 10, 40, 60, 3, 16, Bentonite, Magnesite, Olivine, Glauconite, OVERWORLD, END);
        //new WorldGenLayer("apatite", 40, 60, 60, 3, 16, Apatite, Apatite, TricalciumPhosphate, Pyrochlore);
        new WorldGenVeinLayer("gelena", 30, 60, 40, 5, 16, Galena, Galena, Silver, Lead, OVERWORLD);
        new WorldGenVeinLayer("lapis", 20, 50, 40, 5, 16, Lapis/*Lazurite*/, Lapis/*Sodalite*/, Lapis, Calcite, OVERWORLD, END);
        new WorldGenVeinLayer("beryllium", 5, 30, 30, 3, 16, Beryllium, Beryllium, Emerald, Thorium, OVERWORLD, END);
        new WorldGenVeinLayer("oilsands", 50, 80, 80, 6, 32, Oilsands, Oilsands, Oilsands, Oilsands, OVERWORLD);

        new WorldGenOreSmall("chalcopyrite", 60, 120, 16, Chalcopyrite, OVERWORLD, NETHER, END, MARS);
        new WorldGenOreSmall("cassiterite", 60, 120, 16, Cassiterite, OVERWORLD, NETHER, END, MARS);
        new WorldGenOreSmall("copper", 60, 120, 16, Copper, OVERWORLD, NETHER, END, MOON, MARS);
        new WorldGenOreSmall("tin", 60, 120, 16, Tin, OVERWORLD, NETHER, END, MOON, MARS, ASTEROIDS);
        new WorldGenOreSmall("bismuth", 80, 120, 8, Bismuth, OVERWORLD, NETHER, MOON, MARS);
        new WorldGenOreSmall("coal", 60, 100, 24, Coal, OVERWORLD);

        //TODO replace and also swap ORE_SMALL tags in Materials
        //new WorldGenOreSmall("hematite", 40, 80, 24, Hematite, OVERWORLD, NETHER, END, MOON, MARS);
        new WorldGenOreSmall("iron", 40, 80, 16, Iron, OVERWORLD, NETHER, END, MOON, MARS);

        new WorldGenOreSmall("salt", 40, 80, 6, Salt, OVERWORLD, NETHER, END, MARS);
        new WorldGenOreSmall("rock_salt", 40, 80, 6, RockSalt, OVERWORLD, NETHER, END, MARS);
        new WorldGenOreSmall("sphalerite", 30, 60, 12, Sphalerite, OVERWORLD, NETHER, END, MARS);
        new WorldGenOreSmall("zinc", 40, 70, 4, Zinc, OVERWORLD, NETHER, END, MOON, MARS, ASTEROIDS);

        //TODO replace and also swap ORE_SMALL tags in Materials
        //new WorldGenOreSmall("smithsonite", 30, 60, 2, Smithsonite, OVERWORLD, NETHER, END, MARS);
        new WorldGenOreSmall("nickel", 20, 40, 8, Nickel, OVERWORLD, NETHER, END, MOON, MARS, ASTEROIDS);

        new WorldGenOreSmall("galena", 40, 80, 8, Galena, OVERWORLD, NETHER, END, MARS);
        new WorldGenOreSmall("lead", 40, 80, 8, Lead, OVERWORLD, NETHER, END, MOON, MARS, ASTEROIDS);
        new WorldGenOreSmall("gold", 20, 40, 4, Gold, OVERWORLD, NETHER, END, MOON, MARS, ASTEROIDS);
        new WorldGenOreSmall("pyrite", 20, 40, 4, Pyrite, OVERWORLD, NETHER, END, MOON, MARS, ASTEROIDS);
        new WorldGenOreSmall("silver", 20, 40, 4, Silver, OVERWORLD, NETHER, END, MOON, MARS, ASTEROIDS);
        new WorldGenOreSmall("pyrolusite", 20, 40, 4, Pyrolusite, OVERWORLD, NETHER, END, MOON, MARS);
        new WorldGenOreSmall("garnierite", 20, 40, 4, Garnierite, OVERWORLD, NETHER, END, MARS);
        new WorldGenOreSmall("pentlandite", 20, 40, 4, Pentlandite, OVERWORLD, NETHER, END, MARS);
        new WorldGenOreSmall("stibnite", 20, 40, 2, Stibnite, OVERWORLD, NETHER, END, MARS);
        //new WorldGenOreSmall("asbestos", 20, 40, 8, Asbestos, OVERWORLD);
        //new WorldGenOreSmall("eudialyte", 20, 40, 4, Eudialyte, OVERWORLD);
        //new WorldGenOreSmall("azurite", 20, 40, 4, Azurite, OVERWORLD);
        //new WorldGenOreSmall("zeolite", 1, 250, 1, Zeolite, OVERWORLD);
        new WorldGenOreSmall("lapis", 20, 40, 4, Lapis, OVERWORLD);
        new WorldGenOreSmall("redstone", 5, 20, 8, Redstone, OVERWORLD, NETHER, MARS);
        new WorldGenOreSmall("graphite", 5, 10, 2, Graphite, OVERWORLD, NETHER, MARS);
        //new WorldGenOreSmall("scheelite", 5, 50, 1, Sheelite, OVERWORLD, END, MOON, MARS, ASTEROIDS);
        new WorldGenOreSmall("cooperite", 20, 40, 4, Cooperite, END, ASTEROIDS);
        //new WorldGenOreSmall("sperrylite", 20, 40, 4, Sperrylite, END, ASTEROIDS);
        new WorldGenOreSmall("platinum", 20, 40, 6, Platinum, END, ASTEROIDS);
        new WorldGenOreSmall("iridium", 20, 40, 6, Iridium, END, ASTEROIDS);
        new WorldGenOreSmall("nether_quartz", 30, 120, 64, NetherQuartz, NETHER);
        new WorldGenOreSmall("saltpeter", 10, 60, 8, Saltpeter, NETHER);
        //new WorldGenOreSmall("sodium_nitrate", 10, 60, 8, SodiumNitrate, NETHER);
        new WorldGenOreSmall("sulfur_n", 10, 60, 32, Sulfur, NETHER);
        new WorldGenOreSmall("sulfur_o", 5, 15, 8, Sulfur, OVERWORLD, MARS);

        //TODO replace with GarnetSand?
        new WorldGenOreSmall("red_garnet", 5, 250, 1, GarnetRed, OVERWORLD, NETHER, MARS, ASTEROIDS);
        new WorldGenOreSmall("yellow_garnet", 5, 250, 1, GarnetYellow, OVERWORLD, NETHER, MARS, ASTEROIDS);

        new WorldGenOreSmall("diamond", 5, 10, 2, Diamond, OVERWORLD, NETHER, MOON, MARS, ASTEROIDS);
        new WorldGenOreSmall("emerald", 5, 250, 1, Emerald, OVERWORLD, MARS);
        //new WorldGenOreSmall("aquamarine", 5, 250, 1, Aquamarine, OVERWORLD, MARS);
        //new WorldGenOreSmall("morganite", 5, 250, 1, Morganite, OVERWORLD, MARS);
        //new WorldGenOreSmall("heliodor", 5, 250, 1, Heliodor, OVERWORLD, MARS);
        //new WorldGenOreSmall("goshenite", 5, 250, 1, Goshenite, OVERWORLD, MARS);
        //new WorldGenOreSmall("bixbite", 5, 250, 1, Bixbite, OVERWORLD, MARS);
        //new WorldGenOreSmall("maxixe", 5, 250, 1, Maxixe, OVERWORLD, MARS);
        //new WorldGenOreSmall("dioptase", 5, 250, 1, Dioptase, OVERWORLD, MARS);
        new WorldGenOreSmall("ruby", 5, 250, 1, Ruby, OVERWORLD, MARS);
        new WorldGenOreSmall("bluesapphire", 5, 250, 1, BlueSapphire, OVERWORLD, MARS);
        new WorldGenOreSmall("greensapphire", 5, 250, 1, GreenSapphire, OVERWORLD, MARS);
        //new WorldGenOreSmall("yellowsapphire", 5, 250, 1, YellowSapphire, OVERWORLD, MARS);
        //new WorldGenOreSmall("orangesapphire", 5, 250, 1, OrangeSapphire, OVERWORLD, MARS);
        //new WorldGenOreSmall("purplesapphire", 5, 250, 1, PurpleSapphire, OVERWORLD, MARS);
        new WorldGenOreSmall("olivine", 5, 250, 1, Olivine, OVERWORLD, MARS);
        //new WorldGenOreSmall("craponite", 5, 250, 1, Craponite, OVERWORLD, MARS);
        new WorldGenOreSmall("topaz", 5, 250, 1, Topaz, OVERWORLD, MARS);
        new WorldGenOreSmall("tanzanite", 5, 250, 1, Tanzanite, OVERWORLD, MARS);
        new WorldGenOreSmall("amethyst", 5, 250, 1, Amethyst, OVERWORLD, MARS);
        //new WorldGenOreSmall("alexandrite", 5, 250, 1, Alexandrite, OVERWORLD, MARS);
        new WorldGenOreSmall("opal", 5, 250, 1, Opal, OVERWORLD, MARS);
        //new WorldGenOreSmall("jasper", 5, 250, 1, Jasper, OVERWORLD, MARS);
        new WorldGenOreSmall("blue_topaz", 5, 250, 1, BlueTopaz, OVERWORLD, MARS);
        //new WorldGenOreSmall("balasruby", 5, 250, 1, BalasRuby, OVERWORLD, MARS);
        //new WorldGenOreSmall("spinel", 5, 250, 1, Spinel, OVERWORLD, MARS);
        new WorldGenOreSmall("pyrope", 5, 250, 1, Pyrope, OVERWORLD, MARS);

        new WorldGenOreSmall("almandine", 5, 250, 1, Almandine, OVERWORLD, MARS);
        new WorldGenOreSmall("spessartine", 5, 250, 1, Spessartine, OVERWORLD, MARS);
        new WorldGenOreSmall("andradite", 5, 250, 1, Andradite, OVERWORLD, MARS);
        new WorldGenOreSmall("grossular", 5, 250, 1, Grossular, OVERWORLD, MARS);
        new WorldGenOreSmall("uvarovite", 5, 250, 1, Uvarovite, OVERWORLD, MARS);
        //new WorldGenOreSmall("jade", 5, 250, 1, Jade, OVERWORLD, MARS);
        //new WorldGenOreSmall("amazonite", 5, 250, 1, Amazonite, OVERWORLD, MARS);
        //new WorldGenOreSmall("redonyx", 5, 250, 1, RedOnyx, OVERWORLD, MARS);
        //new WorldGenOreSmall("blackonyx", 5, 250, 1, BlackOnyx, OVERWORLD, MARS);
        //new WorldGenOreSmall("amber", 5, 250, 1, Amber, OVERWORLD, MARS);
    }
}
