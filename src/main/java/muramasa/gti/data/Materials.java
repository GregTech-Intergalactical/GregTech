package muramasa.gti.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Data;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.SubTag;
import muramasa.antimatter.material.TextureSet;
import muramasa.gti.Ref;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemTier;
import net.minecraft.item.Items;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.material.Element.*;
import static muramasa.antimatter.material.MaterialTag.*;
import static muramasa.antimatter.material.TextureSet.*;
import static net.minecraft.item.ItemTier.NETHERITE;

public class Materials {

    public static Material Aluminium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "aluminium", 0x80c8f0, DULL, Al))
            .asMetal(933, 1700, PLATE, ROD, SCREW, BOLT, RING, GEAR, FRAME, GEAR_SMALL).asOre()
            .addTools(1.5F, 10.0F, 140, 2);
    public static Material Beryllium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "beryllium", 0x64b464, METALLIC, Be)).asMetal(1560, 0)
            .asOre().addTools(2.0F, 14.0F, 64, 2);
    public static Material Bismuth = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "bismuth", 0x64a0a0, METALLIC, Bi)).asMetal(544, 0, PLATE)
            .asOre();
    public static Material Carbon = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "carbon", 0x141414, DULL, C)).asSolid(); // TODO: Tools,
                                                                                                    // Carbon
    // Fluid? Removed Tool;
    public static Material Chrome = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "chrome", 0xffe6e6, SHINY, Cr))
            .asMetal(2180, 1700, SCREW, BOLT, RING, PLATE, ROTOR).addTools(2.5F, 11.0F, 256, 3);
    public static Material Cobalt = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cobalt", 0x5050fa, METALLIC, Co)).asMetal(1768, 0)
            .addTools(3.0F, 8.0F, 512, 3);
    public static Material Iridium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "iridium", 0xf0f0f5, DULL, Ir)).asMetal(2719, 2719, FRAME)
            .asOre().addTools(5.0F, 8.0F, 2560, 4);
    public static Material Lanthanum = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "lanthanum", 0xffffff, METALLIC, La)).asSolid(1193, 1193);
    public static Material Lead = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "lead", 0x8c648c, DULL, Pb))
            .asMetal(600, 0, PLATE, PLATE_DENSE, FOIL, ROD, FRAME).asOre();
    public static Material Manganese = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "manganese", 0xfafafa, DULL, Mn)).asMetal(1519, 0).asOre();
    public static Material Molybdenum = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "molybdenum", 0xb4b4dc, SHINY, Mo)).asMetal(2896, 0).asOre()
            .addTools(2.0F, 7.0F, 512, 2);
    public static Material Neodymium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "neodymium", 0x646464, METALLIC, Nd))
            .asMetal(1297, 1297, PLATE, ROD).asOre(); // TODO: Bastnasite or Monazite for Ore For;
    public static Material Neutronium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "neutronium", 0xfafafa, DULL, Nt))
            .asMetal(10000, 10000, SCREW, BOLT, RING, GEAR, FRAME).addTools(9.0F, 24.0F, 655360, 6); // TODO Vibraniu;
    public static Material Nickel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "nickel", 0xc8c8fa, METALLIC, Ni)).asMetal(1728, 0, PLATE)
            .asOre().asPlasma();
    public static Material Osmium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "osmium", 0x3232ff, METALLIC, Os))
            .asMetal(3306, 3306, SCREW, BOLT, RING, PLATE, FOIL, ROD, WIRE_FINE).addTools(4.0F, 16.0F, 1080, 4);
    public static Material Palladium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "palladium", 0x808080, SHINY, Pd)).asMetal(1828, 1828)
            .asOre().addTools(3.0F, 10.0F, 420, 2);
    public static Material Platinum = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "platinum", 0xffffc8, SHINY, Pt))
            .asMetal(2041, 0, PLATE, FOIL, ROD, WIRE_FINE).asOre().addTools(4.5F, 18.0F, 48, 2);
    public static Material Plutonium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "plutonium_244", 0xf03232, METALLIC, Pu)).asMetal(912, 0)
            .addTools(2.5F, 6.0F, 280, 3, of(Enchantments.FIRE_ASPECT, 2)); // TODO: Enchantment: Radioactivit;
    public static Material Plutonium241 = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "plutonium_241", 0xfa4646, SHINY, Pu241)).asMetal(912, 0)
            .addTools(2.5F, 6.0F, 280, 3);
    public static Material Silver = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "silver", 0xdcdcff, SHINY, Ag)).asMetal(1234, 0, PLATE)
            .asOre();
    public static Material Thorium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "thorium", 0x001e00, SHINY, Th)).asMetal(2115, 0).asOre()
            .addTools(1.5F, 6.0F, 512, 2);
    public static Material Titanium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "titanium", 0xdca0f0, METALLIC, Ti))
            .asMetal(1941, 1940, PLATE, ROD, SCREW, BOLT, RING, GEAR, FRAME, GEAR_SMALL, ROTOR).asOre()
            .addTools(2.5F, 7.0F, 1600, 3);
    public static Material Tungsten = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tungsten", 0x323232, METALLIC, W)).asMetal(3695, 3000, FOIL)
            .addTools(2.0F, 6.0F, 512, 3); // Tungstensteel would be the one with tool;
    public static Material Uranium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "uranium_238", 0x32f032, METALLIC, U)).asMetal(1405, 0)
            .asOre();
    public static Material Uranium235 = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "uranium_235", 0x46fa46, METALLIC, U235)).asMetal(1405, 0)
            .addTools(3.0F, 6.0F, 512, 3);
    public static Material Graphite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "graphite", 0x808080, DULL)).asDust().asOre();
    public static Material Americium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "americium", 0xc8c8c8, METALLIC, Am)).asMetal(1149, 0); // TODO:
    // When
    // we're
    // thinking
    // about
    // fusio;
    public static Material Antimony = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "antimony", 0xdcdcf0, SHINY, Sb)).asMetal(1449, 0);
    public static Material Argon = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "argon", 0xff00f0, NONE, Ar)).asGas();
    public static Material Arsenic = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "arsenic", 0xffffff, DULL, As)).asSolid();
    public static Material Barium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "barium", 0xffffff, METALLIC, Ba)).asDust(1000);
    public static Material Boron = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "boron", 0xfafafa, DULL, B)).asDust(2349);
    public static Material Caesium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "caesium", 0xffffff, METALLIC, Cs)).asMetal(2349, 0);
    public static Material Calcium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "calcium", 0xfff5f5, METALLIC, Ca)).asDust(1115);
    public static Material Cadmium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cadmium", 0x32323c, SHINY, Cd)).asDust(594);
    public static Material Cerium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cerium", 0xffffff, METALLIC, Ce)).asSolid(1068, 1068);
    public static Material Chlorine = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "chlorine", 0x00ffff, NONE, Cr)).asGas();
    public static Material Deuterium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "deuterium", 0xffff00, NONE, D)).asGas();
    public static Material Dysprosium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "dysprosium", 0xffffff, METALLIC, D)).asMetal(1680, 1680);
    public static Material Europium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "europium", 0xffffff, METALLIC, Eu)).asMetal(1099, 1099);
    public static Material Fluorine = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "fluorine", 0xffffff, NONE, F)).asGas();
    public static Material Gallium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "gallium", 0xdcdcff, SHINY, Ga)).asMetal(302, 0);
    public static Material Hydrogen = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "hydrogen", 0x0000ff, NONE, H)).asGas();
    public static Material Helium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "helium", 0xffff00, NONE, He)).asPlasma();
    public static Material Helium3 = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "helium_3", 0xffffff, NONE, He_3)).asGas();
    public static Material Indium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "indium", 0x400080, METALLIC, In)).asSolid(429, 0);
    public static Material Lithium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "lithium", 0xe1dcff, DULL, Li)).asSolid(454, 0).asOre();
    public static Material Lutetium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "lutetium", 0xffffff, DULL, Lu)).asMetal(1925, 1925);
    public static Material Magnesium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "magnesium", 0xffc8c8, METALLIC, Mg)).asMetal(923, 0);
    public static Material Mercury = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "mercury", 0xffdcdc, SHINY, Hg)).asFluid();
    public static Material Niobium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "niobium", 0xbeb4c8, METALLIC, Nb)).asMetal(2750, 2750);
    public static Material Nitrogen = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "nitrogen", 0x0096c8, NONE, N)).asPlasma();
    public static Material Oxygen = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "oxygen", 0x0064c8, NONE, O)).asPlasma();
    public static Material Phosphor = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "phosphor", 0xffff00, DULL, P)).asDust(317);
    public static Material Potassium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "potassium", 0xfafafa, METALLIC, K)).asSolid(336, 0);
    public static Material Radon = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "radon", 0xff00ff, NONE, Rn)).asGas();
    public static Material Silicon = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "silicon", 0x3c3c50, METALLIC, Si))
            .asMetal(1687, 1687, PLATE, FOIL);
    public static Material Sodium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sodium", 0x000096, METALLIC, Na)).asDust(370);
    public static Material Sulfur = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sulfur", 0xc8c800, DULL, S)).asDust(388).asOre().asPlasma();
    public static Material Tantalum = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tantalum", 0xffffff, METALLIC, Ta)).asSolid(3290, 0);
    public static Material Tin = AntimatterAPI.register(Material.class, new Material(Ref.ID, "tin", 0xdcdcdc, DULL, Sn))
            .asMetal(505, 505, PLATE, ROD, SCREW, BOLT, RING, GEAR, FOIL, WIRE_FINE, FRAME, ROTOR).asOre();
    public static Material Tritium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tritium", 0xff0000, METALLIC, T)).asGas();
    public static Material Vanadium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "vanadium", 0x323232, METALLIC, V)).asMetal(2183, 2183);
    public static Material Yttrium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "yttrium", 0xdcfadc, METALLIC, Y)).asMetal(1799, 1799);
    public static Material Zinc = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "zinc", 0xfaf0f0, METALLIC, Zn)).asMetal(692, 0, PLATE, FOIL)
            .asOre();

    // TODO: We can be more lenient about what fluids we have in, its not as bad as
    // solids above, and we can stop them from showing in JEI (I think...)

    /**
     * Gases
     **/
    public static Material WoodGas = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "wood_gas", 0xdecd87, NONE)).asGas(24);
    public static Material Methane = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "methane", 0xffffff, NONE)).asGas(104)
            .mats(of(Carbon, 1, Hydrogen, 4));
    public static Material CarbonDioxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "carbon_dioxide", 0xa9d0f5, NONE)).asGas()
            .mats(of(Carbon, 1, Oxygen, 2));
    // public static Material NobleGases = AntimatterAPI.register(Material.class,
    // new Material(Ref.ID, "noble_gases", 0xc9e3fc, NONE)).asGas()/*.setTemp(79,
    // 0)*/.addComposition(of(CarbonDioxide, 21, Helium, 9, Methane, 3, Deuterium,
    // 1));
    public static Material Air = AntimatterAPI.register(Material.class, new Material(Ref.ID, "air", 0xc9e3fc, NONE))
            .asGas().mats(of(Nitrogen, 40, Oxygen, 11, Argon, 1/* , NobleGases, 1 */));
    public static Material NitrogenDioxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "nitrogen_dioxide", 0x64afff, NONE)).asGas()
            .mats(of(Nitrogen, 1, Oxygen, 2));
    public static Material NaturalGas = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "natural_gas", 0xffffff, NONE)).asGas(15);
    public static Material SulfuricGas = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sulfuric_gas", 0xffffff, NONE)).asGas(20);
    public static Material RefineryGas = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "refinery_gas", 0xffffff, NONE)).asGas(128);
    public static Material LPG = AntimatterAPI.register(Material.class, new Material(Ref.ID, "lpg", 0xffff00, NONE))
            .asGas(256);
    public static Material Ethane = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "ethane", 0xc8c8ff, NONE)).asGas(168)
            .mats(of(Carbon, 2, Hydrogen, 6));
    public static Material Propane = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "propane", 0xfae250, NONE)).asGas(232)
            .mats(of(Carbon, 2, Hydrogen, 6));
    public static Material Butane = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "butane", 0xb6371e, NONE)).asGas(296)
            .mats(of(Carbon, 4, Hydrogen, 10));
    public static Material Butene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "butene", 0xcf5005, NONE)).asGas(256)
            .mats(of(Carbon, 4, Hydrogen, 8));
    public static Material Butadiene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "butadiene", 0xe86900, NONE)).asGas(206)
            .mats(of(Carbon, 4, Hydrogen, 6));
    public static Material VinylChloride = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "vinyl_chloride", 0xfff0f0, NONE)).asGas()
            .mats(of(Carbon, 2, Hydrogen, 3, Chlorine, 1));
    public static Material SulfurDioxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sulfur_dioxide", 0xc8c819, NONE)).asGas()
            .mats(of(Sulfur, 1, Oxygen, 2));
    public static Material SulfurTrioxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sulfur_trioxide", 0xa0a014, NONE)).asGas()
            /* .setTemp(344, 1) */.mats(of(Sulfur, 1, Oxygen, 3));
    public static Material Dimethylamine = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "dimethylamine", 0x554469, NONE)).asGas()
            .mats(of(Carbon, 2, Hydrogen, 7, Nitrogen, 1));
    public static Material DinitrogenTetroxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "dinitrogen_tetroxide", 0x004184, NONE)).asGas()
            .mats(of(Nitrogen, 2, Oxygen, 4));
    public static Material NitricOxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "nitric_oxide", 0x7dc8f0, NONE)).asGas()
            .mats(of(Nitrogen, 1, Oxygen, 1));
    public static Material Ammonia = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "ammonia", 0x3f3480, NONE)).asGas()
            .mats(of(Nitrogen, 1, Hydrogen, 3));
    public static Material Chloromethane = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "chloromethane", 0xc82ca0, NONE)).asGas()
            .mats(of(Carbon, 1, Hydrogen, 3, Chlorine, 1));
    public static Material Tetrafluoroethylene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tetrafluoroethylene", 0x7d7d7d, NONE)).asGas()
            .mats(of(Carbon, 2, Fluorine, 4));
    public static Material CarbonMonoxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "carbon_monoxide", 0x0e4880, NONE)).asGas(24)
            .mats(of(Carbon, 1, Oxygen, 1));
    public static Material Ethylene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "ethylene", 0xe1e1e1, NONE)).asGas(128)
            .mats(of(Carbon, 2, Hydrogen, 4));
    public static Material Propene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "propene", 0xffdd55, NONE)).asGas(192)
            .mats(of(Carbon, 3, Hydrogen, 6));
    public static Material Ethenone = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "ethenone", 0x141446, NONE)).asGas()
            .mats(of(Carbon, 2, Hydrogen, 2, Oxygen, 1));
    public static Material HydricSulfide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "hydric_sulfide", 0xffffff, NONE)).asGas()
            .mats(of(Hydrogen, 2, Sulfur, 1));

    /**
     * Fluids
     **/
    public static Material Steam = AntimatterAPI.register(Material.class, new Material(Ref.ID, "steam", 0xa0a0a0, NONE))
            .asGas(1, 395);
    public static Material UUAmplifier = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "uu_amplifier", 0x600080, NONE)).asFluid();
    public static Material UUMatter = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "uu_matter", 0x8000c4, NONE)).asFluid();
    public static Material Antimatter = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "anti_matter", 0x8000c4, NONE)).asFluid();
    // public static Material CharcoalByproducts =
    // AntimatterAPI.register(Material.class, new Material(Ref.ID,
    // "charcoal_byproducts", 0x784421, NONE)).asFluid(; //TODO I'll think about
    // this and woods when I get started on pyrolysi;
    public static Material Glue = AntimatterAPI.register(Material.class, new Material(Ref.ID, "glue", 0xc8c400, NONE))
            .asFluid();
    public static Material Honey = AntimatterAPI.register(Material.class, new Material(Ref.ID, "honey", 0xd2c800, NONE))
            .asFluid(); // TODO: Only when Forestry's present;
    public static Material Lubricant = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "lubricant", 0xffc400, NONE)).asFluid();
    // public static Material WoodTar = AntimatterAPI.register(Material.class, new
    // Material(Ref.ID, "wood_tar", 0x28170b, NONE)).asFluid(; TODO: not sure if
    // neede;
    public static Material WoodVinegar = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "wood_vinegar", 0xd45500, NONE)).asFluid();
    public static Material LiquidAir = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "liquid_air", 0xa9d0f5, NONE)).asFluid()
            /* .setTemp(79, 0) */.mats(of(Nitrogen, 40, Oxygen, 11, Argon, 1/* , NobleGases, 1 */)); // TODO Rrename to
                                                                                                     // liquid
    // oxygen <- Nope, add
    // fluid to Oxyge;
    public static Material DistilledWater = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "distilled_water", 0x5C5CFF, NONE)).asFluid()
            .mats(of(Hydrogen, 2, Oxygen, 1));
    public static Material Glyceryl = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "glyceryl", 0x009696, NONE)).asFluid()
            .mats(of(Carbon, 3, Hydrogen, 5, Nitrogen, 3, Oxygen, 9));
    public static Material Titaniumtetrachloride = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "titaniumtetrachloride", 0xd40d5c, NONE)).asFluid()
            .mats(of(Titanium, 1, Chlorine, 4));
    public static Material SodiumPersulfate = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sodium_persulfate", 0xffffff, NONE)).asFluid()
            .mats(of(Sodium, 2, Sulfur, 2, Oxygen, 8));
    public static Material DilutedHydrochloricAcid = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "diluted_hydrochloric_acid", 0x99a7a3, NONE)).asFluid()
            .mats(of(Hydrogen, 1, Chlorine, 1));
    public static Material NitrationMixture = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "nitration_mixture", 0xe6e2ab, NONE)).asFluid();
    public static Material Dichlorobenzene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "dichlorobenzene", 0x004455, NONE)).asFluid()
            .mats(of(Carbon, 6, Hydrogen, 4, Chlorine, 2));
    public static Material Styrene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "styrene", 0xd2c8be, NONE)).asFluid()
            .mats(of(Carbon, 8, Hydrogen, 8));
    public static Material Isoprene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "isoprene", 0x141414, NONE)).asFluid()
            .mats(of(Carbon, 8, Hydrogen, 8));
    public static Material Tetranitromethane = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tetranitromethane", 0x0f2828, NONE)).asFluid()
            .mats(of(Carbon, 1, Nitrogen, 4, Oxygen, 8));
    public static Material Epichlorohydrin = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "epichlorohydrin", 0x501d05, NONE)).asFluid()
            .mats(of(Carbon, 3, Hydrogen, 5, Chlorine, 1, Oxygen, 1));
    public static Material NitricAcid = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "nitric_acid", 0xe6e2ab, NONE)).asFluid()
            .mats(of(Hydrogen, 1, Nitrogen, 1, Oxygen, 3));
    public static Material Dimethylhydrazine = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "dimethylhydrazine", 0x000055, NONE)).asFluid()
            .mats(of(Carbon, 2, Hydrogen, 8, Nitrogen, 2));
    public static Material Chloramine = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "chloramine", 0x3f9f80, NONE)).asFluid()
            .mats(of(Nitrogen, 1, Hydrogen, 2, Chlorine, 1));
    public static Material Dimethyldichlorosilane = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "dimethyldichlorosilane", 0x441650, NONE)).asFluid()
            .mats(of(Carbon, 2, Hydrogen, 6, Chlorine, 2, Silicon, 1));
    public static Material HydrofluoricAcid = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "hydrofluoric_acid", 0x0088aa, NONE)).asFluid()
            .mats(of(Hydrogen, 1, Fluorine, 1));
    public static Material Chloroform = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "chloroform", 0x892ca0, NONE)).asFluid()
            .mats(of(Carbon, 1, Hydrogen, 1, Chlorine, 3));
    public static Material BisphenolA = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "bisphenol_a", 0xd4b300, NONE)).asFluid()
            .mats(of(Carbon, 15, Hydrogen, 16, Oxygen, 2));
    public static Material AceticAcid = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "acetic_acid", 0xc8b4a0, NONE)).asFluid()
            .mats(of(Carbon, 2, Hydrogen, 4, Oxygen, 2));
    // public static Material CalciumAcetateSolution =
    // AntimatterAPI.register(Material.class, new Material(Ref.ID,
    // "calcium_acetate_solution", 0xdcc8b4,
    // RUBY)).asFluid().addComposition(of(Calcium, 1, Carbon, 2, Oxygen, 4,
    // Hydrogen, 6);
    public static Material Acetone = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "acetone", 0xafafaf, NONE)).asFluid()
            .mats(of(Carbon, 3, Hydrogen, 6, Oxygen, 1));
    public static Material Methanol = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "methanol", 0xaa8800, NONE)).asFluid(84)
            .mats(of(Carbon, 1, Hydrogen, 4, Oxygen, 1));
    public static Material VinylAcetate = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "vinyl_acetate", 0xffb380, NONE)).asFluid()
            .mats(of(Carbon, 4, Hydrogen, 6, Oxygen, 2));
    public static Material PolyvinylAcetate = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "polyvinyl_acetate", 0xff9955, NONE)).asFluid()
            .mats(of(Carbon, 4, Hydrogen, 6, Oxygen, 2));
    public static Material MethylAcetate = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "methyl_acetate", 0xeec6af, NONE)).asFluid()
            .mats(of(Carbon, 3, Hydrogen, 6, Oxygen, 2));
    public static Material AllylChloride = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "allyl_chloride", 0x87deaa, NONE)).asFluid()
            .mats(of(Carbon, 3, Hydrogen, 5, Chlorine, 1));
    public static Material HydrochloricAcid = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "hydrochloric_acid", 0x6f8a91, NONE)).asFluid()
            .mats(of(Hydrogen, 1, Chlorine, 1));
    public static Material HypochlorousAcid = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "hypochlorous_acid", 0x6f8a91, NONE)).asFluid()
            .mats(of(Hydrogen, 1, Chlorine, 1, Oxygen, 1));
    public static Material Cumene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cumene", 0x552200, NONE)).asFluid()
            .mats(of(Carbon, 9, Hydrogen, 12));
    public static Material PhosphoricAcid = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "phosphoric_acid", 0xdcdc00, NONE)).asFluid()
            .mats(of(Hydrogen, 3, Phosphor, 1, Oxygen, 4));
    public static Material SulfuricAcid = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sulfuric_acid", 0xff8000, NONE)).asFluid()
            .mats(of(Hydrogen, 2, Sulfur, 1, Oxygen, 4));
    public static Material DilutedSulfuricAcid = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "diluted_sulfuric_acid", 0xc07820, NONE)).asFluid()
            .mats(of(SulfuricAcid, 1));
    public static Material Benzene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "benzene", 0x1a1a1a, NONE)).asFluid(288)
            .mats(of(Carbon, 6, Hydrogen, 6));
    public static Material Phenol = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "phenol", 0x784421, NONE)).asFluid(288)
            .mats(of(Carbon, 6, Hydrogen, 6, Oxygen, 1));
    public static Material Toluene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "toluene", 0x501d05, NONE)).asFluid(328)
            .mats(of(Carbon, 7, Hydrogen, 8));
    public static Material SulfuricNaphtha = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sulfuric_naphtha", 0xffff00, NONE)).asFluid(32);
    public static Material Naphtha = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "naphtha", 0xffff00, NONE)).asFluid(256);
    public static Material DrillingFluid = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "drilling_fluid", 0xffffff, NONE)).asFluid(); // TODO:
                                                                                                         // Perhaps for
    // a bedrock drill;
    public static Material BlueVitriol = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "blue_vitriol_water_solution", 0xffffff, NONE)).asFluid();
    public static Material IndiumConcentrate = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "indium_concentrate", 0xffffff, NONE)).asFluid();
    public static Material NickelSulfate = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "nickel_sulfate", 0xffffff, NONE)).asFluid();
    public static Material RocketFuel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "rocket_fuel", 0xffffff, NONE)).asFluid();
    public static Material LeadZincSolution = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "lead_zinc_solution", 0xffffff, NONE)).asFluid();

    /**
     * Fuels
     **/
    public static Material Diesel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "diesel", 0xffff00, NONE)).asFluid(128);
    public static Material NitroFuel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cetane_boosted_diesel", 0xc8ff00, NONE)).asFluid(512);
    public static Material BioDiesel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "bio_diesel", 0xff8000, NONE)).asFluid(192);
    public static Material Biomass = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "biomass", 0x00ff00, NONE)).asFluid(8);
    public static Material Ethanol = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "ethanol", 0xff8000, NONE)).asFluid(148)
            .mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 1));
    public static Material Creosote = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "creosote", 0x804000, NONE)).asFluid(8);
    public static Material FishOil = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "fish_oil", 0xffc400, NONE)).asFluid(2);
    public static Material Oil = AntimatterAPI.register(Material.class, new Material(Ref.ID, "oil", 0x0a0a0a, NONE))
            .asFluid(16);
    public static Material SeedOil = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "seed_oil", 0xc4ff00, NONE)).asFluid(2);
    // public static Materials SeedOilHemp = new Materials(722, "Hemp Seed Oil",
    // 196, 255, 0, lime, NONE).asSemi(2;
    // public static Materials SeedOilLin = new Materials(723, "Lin Seed Oil", 196,
    // 255, 0, lime, NONE).asSemi(2;
    // public static Material OilExtraHeavy = AntimatterAPI.register(Material.class,
    // new Material(Ref.ID, "extra_heavy_oil", 0x0a0a0a, NONE)).asFluid(40);
    public static Material OilHeavy = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "heavy_oil", 0x0a0a0a, NONE)).asFluid(32);
    public static Material OilMedium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "raw_oil", 0x0a0a0a, NONE)).asFluid(24);
    public static Material OilLight = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "light_oil", 0x0a0a0a, NONE)).asFluid(16);
    public static Material SulfuricLightFuel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sulfuric_light_diesel", 0xffff00, NONE)).asFluid(32);
    public static Material SulfuricHeavyFuel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sulfuric_heavy_diesel", 0xffff00, NONE)).asFluid(32);
    public static Material LightDiesel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "light_diesel", 0xffff00, NONE)).asFluid(256);
    public static Material HeavyDiesel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "heavy_diesel", 0xffff00, NONE)).asFluid(192);
    public static Material Glycerol = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "glycerol", 0x87de87, NONE)).asFluid(164)
            .mats(of(Carbon, 3, Hydrogen, 8, Oxygen, 3));

    /**
     * Dusts
     **/
    public static Material SodiumSulfide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sodium_sulfide", 0xffe680, NONE)).asDust()
            .mats(of(Sodium, 2, Sulfur, 1));
    public static Material TinAlloy = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tin_alloy", 0x9fadbb, NONE)).asDust()
            .mats(of(Tin, 1, Iron, 1));
    public static Material Energium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "energium", 0xe81e21, NONE)).asDust();
    public static Material IridiumSodiumOxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "iridium_sodium_oxide", 0xffffff, NONE)).asDust();
    public static Material IndiumGalliumPhosphide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "indium_gallium_phosphide", 0x570b79, NONE)).asDust()
            .mats(of(Indium, 1, Gallium, 1, Phosphor, 1));
    public static Material PlatinumGroupSludge = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "platinum_group_sludge", 0x001e00, NONE)).asDust();
    public static Material Glowstone = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "glowstone", 0xffff00, SHINY)).asDust();
    public static Material Graphene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "graphene", 0x808080, DULL)).asDust(PLATE);
    public static Material Oilsands = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "oilsands", 0x0a0a0a, NONE)).asOre(true);
    public static Material RareEarth = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "rare_earth", 0x808064, FINE)).asDust();
    public static Material Almandine = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "almandine", 0xff0000, ROUGH)).asOre()
            .mats(of(Aluminium, 2, Iron, 3, Silicon, 3, Oxygen, 12));
    public static Material Andradite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "andradite", 0x967800, ROUGH)).asOre()
            .mats(of(Calcium, 3, Iron, 2, Silicon, 3, Oxygen, 12));
    public static Material Ash = AntimatterAPI.register(Material.class, new Material(Ref.ID, "ash", 0x969696, DULL))
            .asDust();
    public static Material BandedIron = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "banded_iron", 0x915a5a, DULL)).asOre(true)
            .mats(of(Iron, 2, Oxygen, 3));
    public static Material BrownLimonite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "brown_limonite", 0xc86400, METALLIC)).asOre(true)
            .mats(of(Iron, 1, Hydrogen, 1, Oxygen, 2));
    public static Material Calcite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "calcite", 0xfae6dc, DULL)).asOre(true)
            .mats(of(Calcium, 1, Carbon, 1, Oxygen, 3));
    public static Material Cassiterite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cassiterite", 0xdcdcdc, METALLIC)).asOre()
            .mats(of(Tin, 1, Oxygen, 2));
    public static Material Chalcopyrite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "chalcopyrite", 0xa07828, DULL)).asOre()
            .mats(of(Copper, 1, Iron, 1, Sulfur, 2));
    public static Material Clay = AntimatterAPI.register(Material.class, new Material(Ref.ID, "clay", 0xc8c8dc, ROUGH))
            .asDust().mats(of(Sodium, 2, Lithium, 1, Aluminium, 2, Silicon, 2, Water, 6));
    public static Material Cobaltite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cobaltite", 0x5050fa, METALLIC)).asOre(true)
            .mats(of(Cobalt, 1, Arsenic, 1, Sulfur, 1));
    public static Material Cooperite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cooperite", 0xffffc8, METALLIC)).asOre()
            .mats(of(Platinum, 3, Nickel, 1, Sulfur, 1, Palladium, 1));
    public static Material DarkAsh = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "dark_ash", 0x323232, DULL)).asDust();
    public static Material Galena = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "galena", 0x643c64, DULL)).asOre()
            .mats(of(Lead, 3, Silver, 3, Sulfur, 2));
    public static Material Garnierite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "garnierite", 0x32c846, METALLIC)).asOre()
            .mats(of(Nickel, 1, Oxygen, 1));
    public static Material Grossular = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "grossular", 0xc86400, ROUGH)).asOre()
            .mats(of(Calcium, 3, Aluminium, 2, Silicon, 3, Oxygen, 12));
    public static Material Ilmenite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "ilmenite", 0x463732, METALLIC)).asOre()
            .mats(of(Iron, 1, Titanium, 1, Oxygen, 3));
    public static Material Rutile = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "rutile", 0xd40d5c, GEM_H)).asOre()
            .mats(of(Titanium, 1, Oxygen, 2));
    public static Material MagnesiumChloride = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "magnesiumchloride", 0xd40d5c, DULL)).asDust()
            .mats(of(Magnesium, 1, Chlorine, 2));
    public static Material Magnesite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "magnesite", 0xfafab4, METALLIC)).asOre(true)
            .mats(of(Magnesium, 1, Carbon, 1, Oxygen, 3));
    public static Material Magnetite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "magnetite", 0x1e1e1e, METALLIC)).asOre()
            .mats(of(Iron, 3, Oxygen, 4));
    public static Material Molybdenite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "molybdenite", 0x91919, METALLIC)).asOre(true)
            .mats(of(Molybdenum, 1, Sulfur, 2));
    public static Material Obsidian = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "obsidian", 0x503264, DULL)).asDust()
            .addHandleStat(222, -0.5F, of(Enchantments.UNBREAKING, 2))
            .mats(of(Magnesium, 1, Iron, 1, Silicon, 2, Oxygen, 8));
    public static Material Phosphate = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "phosphate", 0xffff00, DULL)).asOre(true)
            .mats(of(Phosphor, 1, Oxygen, 4));
    public static Material Polydimethylsiloxane = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "polydimethylsiloxane", 0xf5f5f5, NONE)).asDust()
            .mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 1, Silicon, 1));
    // public static Material Powellite = AntimatterAPI.register(Material.class, new
    // Material(Ref.ID, "powellite", 0xffff00,
    // DULL)).asDust(ORE).addComposition(of(Calcium, 1, Molybdenum, 1, Oxygen, 4));
    public static Material Pyrite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "pyrite", 0x967828, ROUGH)).asOre()
            .mats(of(Iron, 1, Sulfur, 2));
    public static Material Pyrolusite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "pyrolusite", 0x9696aa, DULL)).asOre()
            .mats(of(Manganese, 1, Oxygen, 2));
    public static Material Pyrope = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "pyrope", 0x783264, METALLIC)).asOre()
            .mats(of(Aluminium, 2, Magnesium, 3, Silicon, 3, Oxygen, 12));
    public static Material RawRubber = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "raw_rubber", 0xccc789, DULL)).asDust()
            .mats(of(Carbon, 5, Hydrogen, 8));
    public static Material Saltpeter = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "saltpeter", 0xe6e6e6, FINE)).asOre()
            .mats(of(Potassium, 1, Nitrogen, 1, Oxygen, 3));
    public static Material Scheelite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "scheelite", 0xc88c14, DULL)).asDust(2500).asOre(true)
            .mats(of(Tungsten, 1, Calcium, 2, Oxygen, 4));
    public static Material SiliconDioxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "silicon_dioxide", 0xc8c8c8, QUARTZ)).asDust()
            .mats(of(Silicon, 1, Oxygen, 2));
    // public static Material Pyrochlore = AntimatterAPI.register(Material.class,
    // new Material(Ref.ID, "pyrochlore", 0x2b1100,
    // METALLIC)).asDust(ORE).addComposition(of(Calcium, 2, Niobium, 2, Oxygen, 7));
    public static Material FerriteMixture = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "ferrite_mixture", 0xb4b4b4, METALLIC)).asDust()
            .mats(of(Nickel, 1, Zinc, 1, Iron, 4));
    public static Material Massicot = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "massicot", 0xffdd55, DULL)).asDust()
            .mats(of(Lead, 1, Oxygen, 1));
    public static Material ArsenicTrioxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "arsenic_trioxide", 0xffffff, SHINY)).asDust()
            .mats(of(Arsenic, 2, Oxygen, 3));
    public static Material CobaltOxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cobalt_oxide", 0x668000, DULL)).asDust()
            .mats(of(Cobalt, 1, Oxygen, 1));
    public static Material Magnesia = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "magnesia", 0xffffff, DULL)).asDust()
            .mats(of(Magnesium, 1, Oxygen, 1));
    public static Material Quicklime = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "quicklime", 0xf0f0f0, DULL)).asDust()
            .mats(of(Calcium, 1, Oxygen, 1));
    public static Material Potash = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "potash", 0x784237, DULL)).asDust()
            .mats(of(Potassium, 2, Oxygen, 1));
    public static Material SodaAsh = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "soda_ash", 0xdcdcff, DULL)).asDust()
            .mats(of(Sodium, 2, Carbon, 1, Oxygen, 3));
    public static Material Brick = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "brick", 0x9b5643, ROUGH)).asDust()
            .mats(of(Aluminium, 4, Silicon, 3, Oxygen, 12));
    public static Material Fireclay = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "fireclay", 0xada09b, ROUGH)).asDust().mats(of(Brick, 1));
    public static Material SodiumBisulfate = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sodium_bisulfate", 0x004455, NONE)).asDust()
            .mats(of(Sodium, 1, Hydrogen, 1, Sulfur, 1, Oxygen, 4));
    public static Material RawStyreneButadieneRubber = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "raw_styrene_butadiene_rubber", 0x54403d, SHINY)).asDust()
            .mats(of(Styrene, 1, Butadiene, 3));
    public static Material PhosphorousPentoxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "phosphorous_pentoxide", 0xdcdc00, NONE)).asDust()
            .mats(of(Phosphor, 4, Oxygen, 10));
    public static Material SodiumHydroxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sodium_hydroxide", 0x003380, DULL)).asDust()
            .mats(of(Sodium, 1, Oxygen, 1, Hydrogen, 1));
    public static Material Spessartine = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "spessartine", 0xff6464, DULL)).asOre()
            .mats(of(Aluminium, 2, Manganese, 3, Silicon, 3, Oxygen, 12));
    public static Material Sphalerite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sphalerite", 0xffffff, DULL)).asOre()
            .mats(of(Zinc, 1, Sulfur, 1));
    public static Material Stibnite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "stibnite", 0x464646, METALLIC)).asOre()
            .mats(of(Antimony, 2, Sulfur, 3));
    public static Material Tetrahedrite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tetrahedrite", 0xc82000, DULL)).asOre(true)
            .mats(of(Copper, 3, Antimony, 1, Sulfur, 3, Iron, 1));
    public static Material Tungstate = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tungstate", 0x373223, DULL)).asOre(true)
            .mats(of(Tungsten, 1, Lithium, 2, Oxygen, 4));
    public static Material Uraninite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "uraninite", 0x232323, METALLIC)).asOre(true)
            .mats(of(Uranium, 1, Oxygen, 2));
    public static Material Uvarovite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "uvarovite", 0xb4ffb4, DIAMOND)).asOre()
            .mats(of(Calcium, 3, Chrome, 2, Silicon, 3, Oxygen, 12));
    public static Material Wood = AntimatterAPI.register(Material.class, new Material(Ref.ID, "wood", 0x643200, NONE))
            .asDust(GEAR).addHandleStat(12, 0.0F).mats(of(Carbon, 1, Oxygen, 1, Hydrogen, 1));
    public static Material Wulfenite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "wulfenite", 0xff8000, DULL)).asOre(true)
            .mats(of(Lead, 1, Molybdenum, 1, Oxygen, 4));
    public static Material YellowLimonite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "yellow_limonite", 0xc8c800, METALLIC)).asOre(true)
            .mats(of(Iron, 1, Hydrogen, 1, Oxygen, 2));
    // public static Material SealedWood = AntimatterAPI.register(Material.class,
    // new Material(Ref.ID, "sealed_wood", 0x502800, NONE)).asDust().addTools(3.0F,
    // 24, 0).addComposition(of(Wood, 1; TODO: Perhaps with IE integration or when
    // we have some utility stuf;
    public static Material Blaze = AntimatterAPI.register(Material.class, new Material(Ref.ID, "blaze", 0xffc800, NONE))
            .asDust().addHandleStat(-10, -0.5F, of(Enchantments.FIRE_ASPECT, 1))
            .mats(of(Sulfur, 1, DarkAsh, 1/* , Magic, 1 */));
    public static Material Flint = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "flint", 0x002040, TextureSet.FLINT)).asDust(ROCK)
            .addTools(0.0F, 2.0F, 48, 1, of(Enchantments.FIRE_ASPECT, 1)).mats(of(SiliconDioxide, 1));
    public static Material PotassiumFeldspar = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "potassium_feldspar", 0x782828, FINE)).asDust()
            .mats(of(Potassium, 1, Aluminium, 1, Silicon, 3, Oxygen, 8));
    public static Material Biotite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "biotite", 0x141e14, METALLIC)).asDust()
            .mats(b -> b.put(Potassium, 1).put(Magnesium, 3).put(Aluminium, 3).put(Fluorine, 2).put(Silicon, 3)
                    .put(Oxygen, 10));
    public static Material VanadiumMagnetite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "vanadium_magnetite", 0x23233c, METALLIC)).asOre(true)
            .mats(of(Magnetite, 1, Vanadium, 1));
    public static Material Bastnasite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "bastnasite", 0xc86e2d, FINE)).asOre(true)
            .mats(of(Cerium, 1, Carbon, 1, Fluorine, 1, Oxygen, 3));
    public static Material Pentlandite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "pentlandite", 0xa59605, DULL)).asOre()
            .mats(of(Nickel, 9, Sulfur, 8));
    public static Material Spodumene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "spodumene", 0xbeaaaa, DULL)).asOre(true)
            .mats(of(Lithium, 1, Aluminium, 1, Silicon, 2, Oxygen, 6));
    public static Material Tantalite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tantalite", 0x915028, METALLIC)).asOre(true)
            .mats(of(Manganese, 1, Tantalum, 2, Oxygen, 6));
    public static Material Lepidolite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "lepidolite", 0xf0328c, FINE)).asOre(true)
            .mats(of(Potassium, 1, Lithium, 3, Aluminium, 4, Fluorine, 2, Oxygen, 10)); // TODO: Ore Ge;
    public static Material Glauconite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "glauconite", 0x82b43c, DULL)).asOre(true)
            .mats(of(Potassium, 1, Magnesium, 2, Aluminium, 4, Hydrogen, 2, Oxygen, 12)); // TODO: Ore Ge;
    public static Material Bentonite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "bentonite", 0xf5d7d2, ROUGH)).asOre(true).mats(b -> b
                    .put(Sodium, 1).put(Magnesium, 6).put(Silicon, 12).put(Hydrogen, 6).put(Water, 5).put(Oxygen, 36)); // TODO:
    // Ore
    // Ge;
    public static Material Pitchblende = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "pitchblende", 0xc8d200, DULL)).asOre(true)
            .mats(of(Uraninite, 3, Thorium, 1, Lead, 1));
    public static Material Malachite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "malachite", 0x055f05, DULL)).asOre(true)
            .mats(of(Copper, 2, Carbon, 1, Hydrogen, 2, Oxygen, 5));
    public static Material Barite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "barite", 0xe6ebff, DULL)).asOre(true)
            .mats(of(Barium, 1, Sulfur, 1, Oxygen, 4));
    public static Material Talc = AntimatterAPI.register(Material.class, new Material(Ref.ID, "talc", 0x5ab45a, DULL))
            .asOre(true).mats(of(Magnesium, 3, Silicon, 4, Hydrogen, 2, Oxygen, 12));
    public static Material Soapstone = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "soapstone", 0x5f915f, DULL)).asOre(true)
            .mats(of(Magnesium, 3, Silicon, 4, Hydrogen, 2, Oxygen, 12)); // TODO: Ore Ge;
    public static Material Concrete = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "concrete", 0x646464, ROUGH)).asDust(300).mats(of(Stone, 1));
    public static Material AntimonyTrioxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "antimony_trioxide", 0xe6e6f0, DULL)).asDust()
            .mats(of(Antimony, 2, Oxygen, 3));
    public static Material CupricOxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cupric_oxide", 0x0f0f0f, DULL)).asDust()
            .mats(of(Copper, 1, Oxygen, 1));
    public static Material Ferrosilite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "ferrosilite", 0x97632a, DULL)).asDust()
            .mats(of(Iron, 1, Silicon, 1, Oxygen, 3));

    /**
     * Gems
     **/
    // public static Material CertusQuartz = AntimatterAPI.register(Material.class,
    // new Material(Ref.ID, "certus_quartz", 0xd2d2e6, QUARTZ).asGemBasic(false,
    // PLATE, ORE).addTools(5.0F, 32, 1; TODO: Only when AE2 is loade;
    public static Material Dilithium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "dilithium", 0xfffafa, DIAMOND)).asGemBasic(true);
    public static Material NetherStar = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "nether_star", 0xffffff, NONE)).asGemBasic(false)
            .addTools(3.5F, 6.0F, 3620, 4, of(Enchantments.SILK_TOUCH, 1)); // Made Nether Stars usabl;

    // Brittle Gems
    public static Material BlueTopaz = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "blue_topaz", 0x0000ff, GEM_H)).asGem(true).asOre(3, 7, true)
            .addTools(2.5F, 7.0F, 256, 3).mats(of(Aluminium, 2, Silicon, 1, Fluorine, 2, Hydrogen, 2, Oxygen, 6));
    public static Material Charcoal = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "charcoal", 0x644646, LIGNITE)).asGemBasic(false)
            .mats(of(Carbon, 1));
    public static Material CoalCoke = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "coal_coke", 0x8c8caa, LIGNITE)).asGemBasic(false);
    public static Material LigniteCoke = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "lignite_coke", 0x8c6464, LIGNITE)).asGemBasic(false);

    public static Material GreenSapphire = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "green_sapphire", 0x64c882, GEM_H)).asGem(true)
            .asOre(3, 7, true).addTools(2.0F, 7.0F, 256, 2).mats(of(Aluminium, 2, Oxygen, 3));
    public static Material Lazurite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "lazurite", 0x6478ff, LAPIS)).asGemBasic(false)
            .asOre(2, 5, true).mats(of(Aluminium, 6, Silicon, 6, Calcium, 8, Sodium, 8)); // TODO I think this is
                                                                                          // needed;
    public static Material Ruby = AntimatterAPI.register(Material.class, new Material(Ref.ID, "ruby", 0xff6464, RUBY))
            .asGem(true).asOre(3, 7, true).addTools(2.0F, 7.0F, 256, 2).mats(of(Chrome, 1, Aluminium, 2, Oxygen, 3));
    public static Material BlueSapphire = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "blue_sapphire", 0x6464c8, GEM_V)).asGem(true)
            .asOre(3, 7, true).addTools(2.0F, 7.0F, 256, 2).mats(of(Aluminium, 2, Oxygen, 3));
    public static Material Sodalite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sodalite", 0x1414ff, LAPIS)).asGemBasic(false)
            .asOre(2, 5, true).mats(of(Aluminium, 3, Silicon, 3, Sodium, 4, Chlorine, 1)); // TODO I think this is
                                                                                           // needed;
    public static Material Tanzanite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tanzanite", 0x4000c8, GEM_V)).asGem(true).asOre(3, 7, true)
            .addTools(2.0F, 7.0F, 256, 2).mats(of(Calcium, 2, Aluminium, 3, Silicon, 3, Hydrogen, 1, Oxygen, 13));
    public static Material Topaz = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "topaz", 0xff8000, GEM_H)).asGem(true).asOre(3, 7, true)
            .addTools(2.0F, 7.0F, 256, 2).mats(of(Aluminium, 2, Silicon, 1, Fluorine, 2, Hydrogen, 2, Oxygen, 6));
    public static Material Glass = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "glass", 0xfafafa, SHINY)).asDust(PLATE, LENS)
            .mats(of(SiliconDioxide, 1));
    public static Material Olivine = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "olivine", 0x96ff96, RUBY)).asGem(true).asOre(3, 7, true)
            .addTools(2.0F, 7.0F, 256, 2, of(Enchantments.SILK_TOUCH, 1))
            .mats(of(Magnesium, 2, Iron, 1, SiliconDioxide, 2));
    public static Material Opal = AntimatterAPI.register(Material.class, new Material(Ref.ID, "opal", 0x0000ff, RUBY))
            .asGem(true).asOre(3, 7, true).addTools(2.0F, 7.0F, 256, 2).mats(of(SiliconDioxide, 1));
    public static Material Amethyst = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "amethyst", 0xd232d2, RUBY)).asGem(true).asOre(3, 7, true)
            .addTools(2.0F, 7.0F, 256, 3).mats(of(SiliconDioxide, 4, Iron, 1));
    public static Material Phosphorus = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "phosphorus", 0xffff00, TextureSet.FLINT)).asGemBasic(false)
            .asOre(3, 7, true).mats(of(Calcium, 3, Phosphate, 2));
    public static Material RedGarnet = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "red_garnet", 0xc85050, GARNET)).asGemBasic(true)
            .asOre(3, 7, true).mats(of(Pyrope, 3, Almandine, 5, Spessartine, 8));
    public static Material YellowGarnet = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "yellow_garnet", 0xc8c850, GARNET)).asGemBasic(true)
            .asOre(3, 7, true).mats(of(Andradite, 5, Grossular, 8, Uvarovite, 3));
    // public static Material Monazite = AntimatterAPI.register(Material.class, new
    // Material(Ref.ID, "monazite", 0x324632, DIAMOND).asGemBasic(false,
    // ORE).addComposition(of(RareEarth, 1, Phosphate, 1));

    /**
     *
     **/
    public static Material Cinnabar = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cinnabar", 0x960000, ROUGH)).asOre(true)
            .mats(of(Mercury, 1, Sulfur, 1));

    /**
     * Metals
     **/
    public static Material AnnealedCopper = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "annealed_copper", 0xff7814, SHINY))
            .asMetal(1357, 0, PLATE, FOIL, ROD, WIRE_FINE).mats(of(Copper, 1));
    public static Material BatteryAlloy = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "battery_alloy", 0x9c7ca0, DULL)).asMetal(295, 0, PLATE)
            .mats(of(Lead, 4, Antimony, 1));
    public static Material Brass = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "brass", 0xffb400, METALLIC)).asMetal(1170, 0, FRAME, ROD)
            .mats(of(Zinc, 1, Copper, 3));
    public static Material Bronze = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "bronze", 0xff8000, METALLIC))
            .asMetal(1125, 0, GEAR, FRAME, ROTOR).addTools(1.5F, 6.5F, 182, 2, of(Enchantments.UNBREAKING, 1))
            .mats(of(Tin, 1, Copper, 3));
    public static Material Cupronickel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cupronickel", 0xe39680, METALLIC)).asMetal(1728, 0, PLATE)
            .mats(of(Copper, 1, Nickel, 1));
    public static Material Electrum = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "electrum", 0xffff64, SHINY))
            .asMetal(1330, 0, PLATE, FOIL, ROD, WIRE_FINE).addTools(1.0F, 13.0F, 48, 2, of(Enchantments.UNBREAKING, 3))
            .mats(of(Silver, 1, Gold, 1));
    public static Material Invar = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "invar", 0xb4b478, METALLIC)).asMetal(1700, 0, FRAME)
            .addTools(2.5F, 7.0F, 320, 2, of(Enchantments.BANE_OF_ARTHROPODS, 2)).mats(of(Iron, 2, Nickel, 1));
    public static Material Kanthal = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "kanthal", 0xc2d2df, METALLIC)).asMetal(1800, 1800)
            .addTools(2.5F, 6.0F, 64, 2, of(Enchantments.BANE_OF_ARTHROPODS, 1))
            .mats(of(Iron, 1, Aluminium, 1, Chrome, 1));
    public static Material Magnalium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "magnalium", 0xc8beff, DULL)).asMetal(870, 0)
            .mats(of(Magnesium, 1, Aluminium, 2));
    public static Material Nichrome = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "nichrome", 0xcdcef6, METALLIC)).asMetal(2700, 2700)
            .addTools(2.0F, 6.0F, 81, 2, of(Enchantments.BANE_OF_ARTHROPODS, 3)).mats(of(Nickel, 4, Chrome, 1));
    public static Material NiobiumTitanium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "niobium_titanium", 0x1d1d29, DULL))
            .asMetal(4500, 4500, PLATE, FOIL, ROD, WIRE_FINE).mats(of(Nickel, 4, Chrome, 1));
    public static Material SolderingAlloy = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "soldering_alloy", 0xdcdce6, DULL))
            .asMetal(400, 400, PLATE, FOIL, ROD, WIRE_FINE).mats(of(Tin, 9, Antimony, 1));
    public static Material Steel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "steel", 0x808080, METALLIC))
            .asMetal(1811, 1000, PLATE, ROD, SCREW, BOLT, RING, GEAR, FRAME, ROTOR, GEAR_SMALL).addTools(Iron)
            .mats(of(Iron, 50, Carbon, 1));
    public static Material StainlessSteel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "stainless_steel", 0xc8c8dc, SHINY))
            .asMetal(1700, 1700, PLATE, ROD, SCREW, BOLT, RING, GEAR, FRAME, ROTOR, GEAR_SMALL).addTools(Steel)
            .mats(of(Iron, 6, Chrome, 1, Manganese, 1, Nickel, 1));
    public static Material Ultimet = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "ultimet", 0xb4b4e6, SHINY)).asMetal(2700, 2700)
            .mats(of(Cobalt, 5, Chrome, 2, Nickel, 1, Molybdenum, 1));
    public static Material VanadiumGallium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "vanadium_gallium", 0x80808c, SHINY))
            .asMetal(4500, 4500, ROD, PLATE).mats(of(Vanadium, 3, Gallium, 1));
    public static Material WroughtIron = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "wrought_iron", 0xc8b4b4, METALLIC))
            .asMetal(1811, 0, PLATE, ROD, SCREW, BOLT, RING, GEAR, FRAME, ROTOR, GEAR_SMALL).addTools(Iron)
            .mats(of(Iron, 1)).asOre();
    public static Material YttriumBariumCuprate = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "yttrium_barium_cuprate", 0x504046, METALLIC))
            .asMetal(4500, 4500, PLATE, FOIL, ROD, WIRE_FINE).mats(of(Yttrium, 1, Barium, 2, Copper, 3, Oxygen, 7));
    public static Material SterlingSilver = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sterling_silver", 0xfadce1, SHINY)).asMetal(1700, 1700)
            .addTools(3.0F, 10.5F, 96, 2, of(Enchantments.EFFICIENCY, 2)).mats(of(Copper, 1, Silver, 4));
    public static Material RoseGold = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "rose_gold", 0xffe61e, SHINY)).asMetal(1600, 1600)
            .addTools(Gold, of(Enchantments.FORTUNE, 3, Enchantments.SMITE, 3)).mats(of(Copper, 1, Gold, 4));
    public static Material BlackBronze = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "black_bronze", 0x64327d, DULL)).asMetal(2000, 2000)
            .addTools(Bronze, of(Enchantments.SWEEPING, 1)).mats(of(Gold, 1, Silver, 1, Copper, 3));
    public static Material BismuthBronze = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "bismuth_bronze", 0x647d7d, DULL)).asMetal(1100, 900, PLATE)
            .addTools(2.5F, Bronze.getToolSpeed() + 2.0F, 350, 2, of(Enchantments.BANE_OF_ARTHROPODS, 4))
            .mats(of(Bismuth, 1, Zinc, 1, Copper, 3));
    public static Material BlackSteel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "black_steel", 0x646464, METALLIC))
            .asMetal(1200, 1200, FRAME, PLATE).addTools(3.5F, 6.5F, 768, 2)
            .mats(of(Nickel, 1, BlackBronze, 1, Steel, 3));
    public static Material RedSteel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "red_steel", 0x8c6464, METALLIC)).asMetal(1300, 1300)
            .addTools(3.5F, 7.0F, 896, 2).mats(of(SterlingSilver, 1, BismuthBronze, 1, Steel, 2, BlackSteel, 4));
    public static Material BlueSteel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "blue_steel", 0x64648c, METALLIC)).asMetal(1400, 1400, FRAME)
            .addTools(3.5F, 7.5F, 1024, 2).mats(of(RoseGold, 1, Brass, 1, Steel, 2, BlackSteel, 4));
    // public static Material DamascusSteel = AntimatterAPI.register(Material.class,
    // new Material(Ref.ID, "damascus_steel", 0x6e6e6e, METALLIC)).asMetal(2500,
    // 1500).addTools(8.0F, 1280, 2).addComposition(of(Steel, 1); //TODO: Sorta a
    // fantasy meta;
    public static Material TungstenSteel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tungstensteel", 0x6464a0, METALLIC))
            .asMetal(3000, 3000, PLATE, ROD, SCREW, BOLT, RING, GEAR, FRAME, ROTOR, GEAR_SMALL).asOre()
            .addTools(4.0F, 8.0F, 2560, 4).mats(of(Steel, 1, Tungsten, 1));
    public static Material RedAlloy = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "red_alloy", 0xc80000, DULL))
            .asMetal(295, 0, PLATE, FOIL, ROD, WIRE_FINE).mats(of(Copper, 1, Redstone, 4));
    public static Material CobaltBrass = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cobalt_brass", 0xb4b4a0, METALLIC)).asMetal(1500, 0, GEAR)
            .addTools(2.5F, 8.0F, 256, 2).mats(of(Brass, 7, Aluminium, 1, Cobalt, 1));
    public static Material IronMagnetic = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "magnetic_iron", 0xc8c8c8, MAGNETIC)).asMetal(1811, 0, ROD)
            .addTools(Iron).mats(of(Iron, 1));
    public static Material SteelMagnetic = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "magnetic_steel", 0x808080, MAGNETIC))
            .asMetal(1000, 1000, ROD).addTools(Steel).mats(of(Steel, 1));
    public static Material NeodymiumMagnetic = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "magnetic_neodymium", 0x646464, MAGNETIC))
            .asMetal(1297, 1297, ROD).mats(of(Neodymium, 1));
    public static Material NickelZincFerrite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "nickel_zinc_ferrite", 0x3c3c3c, ROUGH)).asMetal(1500, 1500)
            .addTools(0.0F, 3.0F, 32, 1).mats(of(Nickel, 1, Zinc, 1, Iron, 4, Oxygen, 8));
    public static Material TungstenCarbide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tungsten_carbide", 0x330066, METALLIC)).asMetal(2460, 2460)
            .addTools(5.0F, 14.0F, 1280, 4).mats(of(Tungsten, 1, Carbon, 1));
    public static Material VanadiumSteel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "vanadium_steel", 0xc0c0c0, METALLIC)).asMetal(1453, 1453)
            .addTools(3.0F, 5.0F, 1920, 3).mats(of(Vanadium, 1, Chrome, 1, Steel, 7));
    public static Material HSSG = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "hssg", 0x999900, METALLIC)).asMetal(4500, 4500, GEAR, FRAME)
            .addTools(3.8F, 10.0F, 4000, 3).mats(of(TungstenSteel, 5, Chrome, 1, Molybdenum, 2, Vanadium, 1));
    public static Material HSSE = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "hsse", 0x336600, METALLIC)).asMetal(5400, 5400, GEAR, FRAME)
            .addTools(4.2F, 10.0F, 5120, 4).mats(of(HSSG, 6, Cobalt, 1, Manganese, 1, Silicon, 1));
    public static Material HSSS = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "hsss", 0x660033, METALLIC)).asMetal(5400, 5400)
            .addTools(5.0F, 14.0F, 3000, 4).mats(of(HSSG, 6, Iridium, 2, Osmium, 1));
    public static Material Osmiridium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "osmiridium", 0x6464ff, METALLIC)).asMetal(3333, 2500, FRAME)
            .addTools(6.0F, 15.0F, 1940, 5).mats(of(Iridium, 3, Osmium, 1));
    public static Material Duranium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "duranium", 0xffffff, METALLIC)).asMetal(295, 0)
            .addHandleStat(620, -1.0F, of(Enchantments.SILK_TOUCH, 1)).addTools(6.5F, 16.0F, 5120, 5);
    public static Material Naquadah = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "naquadah", 0x323232, METALLIC)).asMetal(5400, 5400).asOre()
            .addHandleStat(102, 0.5F, of(Enchantments.EFFICIENCY, 2)).addTools(4.0F, 6.0F, 890, 4);
    public static Material NaquadahAlloy = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "naquadah_alloy", 0x282828, METALLIC)).asMetal(7200, 7200)
            .addTools(4.5F, 8.0F, 5120, 5);
    public static Material EnrichedNaquadah = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "enriched_naquadah", 0x323232, SHINY)).asMetal(4500, 4500)
            .asOre().addTools(5.0F, 6.0F, 1280, 4);
    public static Material Naquadria = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "naquadria", 0x1e1e1e, SHINY)).asMetal(9000, 9000);
    public static Material Tritanium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tritanium", 0xffffff, SHINY)).asMetal(295, 0, FRAME)
            .addTools(9.0F, 15.0F, 9400, 6);
    public static Material Vibranium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "vibranium", 0x00ffff, SHINY)).asMetal(295, 0, FRAME)
            .addTools(10.0F, 20.0F, 12240, 6);

    /**
     * Solids (Plastic Related Stuff)
     **/
    public static Material Plastic = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "plastic", 0xc8c8c8, DULL)).asSolid(295, 0, PLATE)
            .addHandleStat(66, 0.5F).mats(of(Carbon, 1, Hydrogen, 2));
    public static Material Epoxid = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "epoxid", 0xc88c14, DULL)).asSolid(400, 0, PLATE)
            .addHandleStat(70, 1.5F).mats(of(Carbon, 2, Hydrogen, 4, Oxygen, 1));
    public static Material Silicone = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "silicone", 0xdcdcdc, DULL)).asSolid(900, 0, PLATE, FOIL)
            .addHandleStat(-40, 2.0F).mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 1, Silicon, 1));
    public static Material Polycaprolactam = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "polycaprolactam", 0x323232, DULL)).asSolid(500, 0)
            .mats(of(Carbon, 6, Hydrogen, 11, Nitrogen, 1, Oxygen, 1));
    public static Material Polytetrafluoroethylene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "polytetrafluoroethylene", 0x646464, DULL))
            .asSolid(1400, 0, PLATE, FRAME).mats(of(Carbon, 2, Fluorine, 4));
    public static Material Rubber = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "rubber", 0x000000, SHINY)).asSolid(295, 0, PLATE, RING)
            .addHandleStat(11, 0.4F).mats(of(Carbon, 5, Hydrogen, 8));
    public static Material PolyphenyleneSulfide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "polyphenylene_sulfide", 0xaa8800, DULL))
            .asSolid(295, 0, PLATE, FOIL).mats(of(Carbon, 6, Hydrogen, 4, Sulfur, 1));
    public static Material Polystyrene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "polystyrene", 0xbeb4aa, DULL)).asSolid(295, 0)
            .addHandleStat(3, 1.0F).mats(of(Carbon, 8, Hydrogen, 8));
    public static Material StyreneButadieneRubber = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "styrene_butadiene_rubber", 0x211a18, SHINY))
            .asSolid(295, 0, PLATE, RING).addHandleStat(66, 1.2F).mats(of(Styrene, 1, Butadiene, 3));
    public static Material PolyvinylChloride = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "polyvinyl_chloride", 0xd7e6e6, NONE))
            .asSolid(295, 0, PLATE, FOIL).addHandleStat(210, 0.5F).mats(of(Carbon, 2, Hydrogen, 3, Chlorine, 1));
    public static Material GalliumArsenide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "gallium_arsenide", 0xa0a0a0, DULL)).asSolid(295, 1200)
            .mats(of(Arsenic, 1, Gallium, 1));
    public static Material EpoxidFiberReinforced = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "fiber_reinforced_epoxy_resin", 0xa07010, DULL))
            .asSolid(400, 0).mats(of(Epoxid, 1));

    public static Material RedGranite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "red_granite", 0xff0080, ROUGH)).asDust(ROCK)
            .addHandleStat(74, 1.0F, of(Enchantments.UNBREAKING, 1))
            .mats(of(Aluminium, 2, PotassiumFeldspar, 1, Oxygen, 3));
    public static Material BlackGranite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "black_granite", 0x0a0a0a, ROUGH)).asDust(ROCK)
            .addHandleStat(74, 1.0F, of(Enchantments.UNBREAKING, 1)).mats(of(SiliconDioxide, 4, Biotite, 1));
    public static Material Marble = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "marble", 0xc8c8c8, NONE)).asDust(ROCK)
            .mats(of(Magnesium, 1, Calcite, 7));
    public static Material Komatiite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "komatiite", 0xbebe69, NONE)).asDust(ROCK)
            .mats(of(Olivine, 1, /* MgCO3, 2, */Flint, 6, DarkAsh, 3));
    public static Material Limestone = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "limestone", 0xe6c882, NONE)).asDust(ROCK)
            .mats(of(Calcite, 1));
    public static Material GreenSchist = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "green_schist", 0x69be69, NONE)).asDust(ROCK);
    public static Material BlueSchist = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "blue_schist", 0x0569be, NONE)).asDust(ROCK);
    public static Material Kimberlite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "kimberlite", 0x64460a, NONE)).asDust(ROCK);
    public static Material Quartzite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "quartzite", 0xe6cdcd, QUARTZ)).asGemBasic(false, ROCK, ROD)
            .asOre(2, 5, true).mats(of(Silicon, 1, Oxygen, 2));

    /**
     * Ore Stones
     **/
    public static Material Lignite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "lignite_coal", 0x644646, LIGNITE)).asGemBasic(false)
            .asOreStone(0, 2, ORE_SMALL).mats(of(Carbon, 3, Water, 1));
    public static Material Salt = AntimatterAPI.register(Material.class, new Material(Ref.ID, "salt", 0xfafafa, FINE))
            .asOreStone(ORE_SMALL).mats(of(Sodium, 1, Chlorine, 1));
    public static Material RockSalt = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "rock_salt", 0xf0c8c8, FINE)).asOreStone(ORE_SMALL)
            .mats(of(Potassium, 1, Chlorine, 1));
    public static Material Bauxite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "bauxite", 0xc86400, DULL)).asOreStone(ORE_SMALL)
            .mats(of(Rutile, 2, Aluminium, 16, Hydrogen, 10, Oxygen, 11));
    public static Material OilShale = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "oil_shale", 0x32323c, NONE)).asOreStone(ORE_SMALL);

    /**
     * Reference Materials
     **/
    public static Material Superconductor = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "superconductor", 0xffffff, NONE)).asSolid(PLATE);
    public static Material HighPressure = AntimatterAPI.register(Material.class,
            new Material(Ref.ID, "high_pressure", 0xc80000, NONE));
    public static Material HighCapacity = AntimatterAPI.register(Material.class,
            new Material(Ref.ID, "high_capacity", 0xb00b69, NONE));
    public static Material PlasmaContainment = AntimatterAPI.register(Material.class,
            new Material(Ref.ID, "plasma_containment", 0xffff00, NONE));

    static {
        ELECSEPI.add(Bastnasite/* , Monazite */);
        ELECSEPG.add(Magnetite, VanadiumMagnetite);
        ELECSEPN.add(YellowLimonite, BrownLimonite, Pyrite, BandedIron, Nickel, Glauconite, Pentlandite, Tin, Antimony,
                Ilmenite, Manganese, Chrome, Andradite);
        ELEC.add(Methane, CarbonDioxide, NitrogenDioxide, Toluene, VinylChloride, SulfurDioxide, SulfurTrioxide,
                Dimethylamine, DinitrogenTetroxide, NitricOxide, Ammonia, Chloromethane, Tetrafluoroethylene,
                CarbonMonoxide, Ethylene, Propane, Ethenone, Ethanol, Glyceryl, SodiumPersulfate, Dichlorobenzene,
                Styrene, Isoprene, Tetranitromethane, Epichlorohydrin, NitricAcid, Dimethylhydrazine, Chloramine,
                Dimethyldichlorosilane, HydrofluoricAcid, Chloroform, BisphenolA, AceticAcid, Acetone, Methanol,
                VinylAcetate, MethylAcetate, AllylChloride, HypochlorousAcid, Cumene, PhosphoricAcid, SulfuricAcid,
                Benzene, Phenol, Glycerol, SodiumSulfide, Almandine, Andradite, BandedIron, Calcite, Cassiterite,
                Chalcopyrite, Cobaltite, Galena, Garnierite, Grossular, Bauxite, Magnesite, Magnetite, Molybdenite,
                Obsidian, Phosphate, Polydimethylsiloxane, Pyrite, Pyrolusite, Pyrope, RockSalt, Saltpeter,
                SiliconDioxide, Massicot, ArsenicTrioxide, CobaltOxide, Magnesia, Quicklime, Potash, SodaAsh,
                PhosphorousPentoxide, SodiumHydroxide, Spessartine, Sphalerite, Uvarovite, PotassiumFeldspar, Biotite,
                RedGranite, Bastnasite, Pentlandite, Spodumene, Glauconite, Bentonite, Malachite, Barite, Talc,
                AntimonyTrioxide, CupricOxide, Ferrosilite, Quartzite, Charcoal, Coal, Lignite, Diamond, Emerald, Ruby,
                BlueSapphire, Tanzanite, Topaz, Olivine, Opal, Amethyst, EnderPearl, StainlessSteel, Steel, Ultimet,
                IronMagnetic, SteelMagnetic, NeodymiumMagnetic, Osmiridium);
        CENT.add(/* NobleGases, */Air, BrownLimonite, Cinnabar, Clay, Cooperite/* , Powellite */, Stibnite,
                Tetrahedrite, Uraninite, Wulfenite, YellowLimonite, Blaze, Flint, Marble, BlackGranite,
                VanadiumMagnetite, Pitchblende, Glass, Lapis, EnderEye, Phosphorus, Redstone, Basalt, AnnealedCopper,
                BatteryAlloy, Brass, Bronze, Cupronickel, Electrum, Invar, Kanthal, Magnalium, Nichrome,
                NiobiumTitanium, SolderingAlloy, VanadiumGallium, WroughtIron, SterlingSilver, RoseGold, BismuthBronze,
                TungstenSteel, RedAlloy, CobaltBrass, TungstenCarbide, VanadiumSteel, HSSG, HSSE, HSSS,
                GalliumArsenide/* , IndiumGalliumPhosphide, BorosilicateGlass */);
        CRACK.add(RefineryGas, Naphtha, Ethane, Propane, Butane, Butene, Ethylene, Propene, LightDiesel, HeavyDiesel);
        CALCITE2X.add(Pyrite, BrownLimonite, YellowLimonite, Magnetite);
        CALCITE3X.add(Iron, WroughtIron);
        WASHM.add(Gold, Silver, Osmium, Platinum, Cooperite, Galena, Nickel, Tungstate, Lead, Magnetite, Iridium,
                Copper, Chalcopyrite);
        WASHS.add(Zinc, Nickel, Copper, Cobaltite, Tetrahedrite, Gold, Sphalerite, Garnierite, Chalcopyrite, Cooperite,
                Platinum, Pentlandite, Tin, Malachite, YellowLimonite);
        NOSMELT.add(Wood/* , WoodSealed */, Sulfur, Saltpeter, Graphite, /* Paper, */Coal, Charcoal, Lignite, Glyceryl,
                NitroFuel, Emerald, Amethyst, Tanzanite, Topaz, /* Amber, */ BlueSapphire, Ruby, Opal, Olivine,
                Lapis/* , Sodalite, Lazurite, Monazite */, Quartzite, Quartz, Phosphorus, Phosphate, NetherStar,
                EnderPearl, EnderEye, Blaze);
        NOSMASH.add(Wood/* WoodSealed */, Sulfur, Saltpeter, Graphite, /* Paper, */Coal, Charcoal, Lignite, Rubber,
                StyreneButadieneRubber, Plastic, PolyvinylChloride, Polystyrene, Silicone, Glyceryl, NitroFuel,
                Concrete, Redstone, Glowstone, Netherrack, Stone, Brick, Endstone, Marble, Basalt, Obsidian, Flint,
                RedGranite, BlackGranite, Salt, RockSalt, Glass, Diamond, Emerald, Amethyst, Tanzanite, Topaz,
                /* Amber, */ BlueSapphire, Ruby, Opal, Olivine, Lapis, Quartzite, Quartz, Phosphorus, Phosphate,
                NetherStar, EnderPearl, EnderEye);
        GRINDABLE.add(/* Paper, */Coal, Charcoal, Lignite, Lead, Tin, SolderingAlloy, Flint, Gold, Silver, Iron,
                IronMagnetic, Steel, SteelMagnetic, Zinc, Antimony, Copper, AnnealedCopper, Bronze, Nickel, Invar,
                Brass, WroughtIron, Electrum, Clay, Blaze);
        SMELTF.add(Concrete, Redstone, Glowstone, Glass, Blaze);
        // TODO explicit recipe SMELTG.add(Mercury, CINNABAR); //TODO Remove
        NOBBF.add(Tetrahedrite, Chalcopyrite, Cooperite, Pyrolusite, Magnesite, Molybdenite, Galena);
        CRYSTALLIZE.add(Lapis, Quartzite, Quartz);
        BRITTLEG.add(Coal, Charcoal, Lignite);
        RUBBERTOOLS.add(Rubber, StyreneButadieneRubber, Plastic, PolyvinylChloride, Polystyrene, Silicone);

        SOLDER.subTag(SubTag.BAD_SOLDER, Lead, Tin);
        SOLDER.subTag(SubTag.GOOD_SOLDER, SolderingAlloy, Tin);
        SOLDER.add(Lead, Tin, SolderingAlloy);
        WIRE.subTag(SubTag.COPPER_WIRE, AnnealedCopper);
        WIRE.subTag(SubTag.COPPER_WIRE, Copper);

        Data.Redstone.mats(of(Silicon, 1, Pyrite, 5, Ruby, 1, Mercury, 3)).flags(LIQUID);//.setOreMulti(4);
        Data.Prismarine.mats(of(Potassium, 2, Oxygen, 8, Manganese, 1, Silicon, 5));
        Data.Basalt.mats(of(Olivine, 1, Calcite, 3, Flint, 8, DarkAsh, 4));
        Data.Lapis.mats(of(Lazurite, 12, Sodalite, 2, Pyrite, 1, Calcite, 1));//.setOreMulti(6);
        Data.EnderEye.asGemBasic(true, ROD, PLATE).mats(of(EnderPearl, 1, Blaze, 1));
        Data.EnderPearl.mats(of(Beryllium, 1, Potassium, 4, Nitrogen, 5, Chlorine, 6));
        Data.Diamond.asGem(true).mats(of(Carbon, 64));
        Data.Emerald.asGem(true).mats(of(Beryllium, 3, Aluminium, 2, Silicon, 3, Oxygen, 18));//.addTools(3.0F, 9.0F, 590, 3);
        Data.Coal.flags(ORE_STONE).mats(of(Carbon, 2));
        Data.Iron.flags(RING, GEAR, FRAME);
        Data.Gold.flags(FOIL, ROD, WIRE_FINE, GEAR);
        Data.Copper.flags(PLATE, ROD, FOIL, WIRE_FINE, GEAR);
        Quartz.asOre();
        Data.Water.mats(of(Hydrogen, 2, Oxygen, 1));

        BlueTopaz.remove(ORE);

        NeodymiumMagnetic.setSmeltInto(Neodymium).setMacerateInto(Neodymium).setArcSmeltInto(Neodymium);
        SteelMagnetic.setSmeltInto(Steel).setMacerateInto(Steel).setArcSmeltInto(Steel);
        Iron.setSmeltInto(Iron).setMacerateInto(Iron).setArcSmeltInto(WroughtIron);
        WroughtIron.setSmeltInto(Iron).setMacerateInto(Iron).setArcSmeltInto(WroughtIron);
        IronMagnetic.setSmeltInto(Iron).setMacerateInto(Iron).setArcSmeltInto(WroughtIron);
        Copper.setSmeltInto(Copper).setMacerateInto(Copper).setArcSmeltInto(AnnealedCopper);
        AnnealedCopper.setSmeltInto(Copper).setMacerateInto(Copper).setArcSmeltInto(AnnealedCopper);

        Cinnabar.setDirectSmeltInto(Mercury);
        Tetrahedrite.setDirectSmeltInto(Copper);
        Chalcopyrite.setDirectSmeltInto(Copper);
        Malachite.setDirectSmeltInto(Copper);
        Pentlandite.setDirectSmeltInto(Nickel);
        Sphalerite.setDirectSmeltInto(Zinc);
        Pyrite.setDirectSmeltInto(Iron);
        YellowLimonite.setDirectSmeltInto(Iron);
        BrownLimonite.setDirectSmeltInto(Iron);
        BandedIron.setDirectSmeltInto(Iron);
        Magnetite.setDirectSmeltInto(Iron);
        Cassiterite.setDirectSmeltInto(Tin);
        Garnierite.setDirectSmeltInto(Nickel);
        Cobaltite.setDirectSmeltInto(Cobalt);
        Stibnite.setDirectSmeltInto(Antimony);
        Cooperite.setDirectSmeltInto(Platinum);
        Pyrolusite.setDirectSmeltInto(Manganese);
        Magnesite.setDirectSmeltInto(Magnesium);
        Molybdenite.setDirectSmeltInto(Molybdenum);
        Galena.setDirectSmeltInto(Lead);
        Salt.setOreMulti(2).setSmeltingMulti(2);
        RockSalt.setOreMulti(2).setSmeltingMulti(2);
        Scheelite.setOreMulti(2).setSmeltingMulti(2);
        Tungstate.setOreMulti(2).setSmeltingMulti(2);
        Cassiterite.setOreMulti(2).setSmeltingMulti(2);
        Quartz.setOreMulti(2).setSmeltingMulti(2);
        Phosphorus.setOreMulti(3).setSmeltingMulti(3);
        Saltpeter.setOreMulti(4).setSmeltingMulti(4);
        Redstone.setOreMulti(5).setSmeltingMulti(5);
        Glowstone.setOreMulti(5).setSmeltingMulti(5);
        Lapis.setOreMulti(6).setSmeltingMulti(6).setByProductMulti(4);
        // Plastic.setEnchantmentForTools(Enchantment.knockback, 1);
        // PolyvinylChloride.setEnchantmentForTools(Enchantment.knockback, 1);
        // Polystyrene.setEnchantmentForTools(Enchantment.knockback, 1);
        // Rubber.setEnchantmentForTools(Enchantment.knockback, 2);
        // StyreneButadieneRubber.setEnchantmentForTools(Enchantment.knockback, 2);
        // Flint.setEnchantmentForTools(Enchantment.fireAspect, 1);
        // Blaze.setEnchantmentForTools(Enchantment.fireAspect, 3);
        // EnderPearl.setEnchantmentForTools(Enchantment.silkTouch, 1);
        // NetherStar.setEnchantmentForTools(Enchantment.silkTouch, 1);
        // BlackBronze.setEnchantmentForTools(Enchantment.smite, 2);
        // Gold.setEnchantmentForTools(Enchantment.smite, 3);
        // RoseGold.setEnchantmentForTools(Enchantment.smite, 4);
        // Platinum.setEnchantmentForTools(Enchantment.smite, 5);
        // Lead.setEnchantmentForTools(Enchantment.baneOfArthropods, 2);
        // Nickel.setEnchantmentForTools(Enchantment.baneOfArthropods, 2);
        // Invar.setEnchantmentForTools(Enchantment.baneOfArthropods, 3);
        // Antimony.setEnchantmentForTools(Enchantment.baneOfArthropods, 3);
        // BatteryAlloy.setEnchantmentForTools(Enchantment.baneOfArthropods, 4);
        // Bismuth.setEnchantmentForTools(Enchantment.baneOfArthropods, 4);
        // BismuthBronze.setEnchantmentForTools(Enchantment.baneOfArthropods, 5);
        // Iron.setEnchantmentForTools(Enchantment.sharpness, 1);
        // Bronze.setEnchantmentForTools(Enchantment.sharpness, 1);
        // Brass.setEnchantmentForTools(Enchantment.sharpness, 2);
        // Steel.setEnchantmentForTools(Enchantment.sharpness, 2);
        // WroughtIron.setEnchantmentForTools(Enchantment.sharpness, 2);
        // StainlessSteel.setEnchantmentForTools(Enchantment.sharpness, 3);
        // BlackSteel.setEnchantmentForTools(Enchantment.sharpness, 4);
        // RedSteel.setEnchantmentForTools(Enchantment.sharpness, 4);
        // BlueSteel.setEnchantmentForTools(Enchantment.sharpness, 5);
        // DamascusSteel.setEnchantmentForTools(Enchantment.sharpness, 5);
        // TungstenCarbide.setEnchantmentForTools(Enchantment.sharpness, 5);
        // HSSE.setEnchantmentForTools(Enchantment.sharpness, 5);
        // HSSG.setEnchantmentForTools(Enchantment.sharpness, 4);
        // HSSS.setEnchantmentForTools(Enchantment.sharpness, 5);
        // Lava.setTemperatureDamage(3.0F);
        Chalcopyrite.addByProduct(Pyrite, Cobalt, Cadmium, Gold);
        Sphalerite.addByProduct(YellowGarnet, Cadmium, Gallium, Zinc);
        Glauconite.addByProduct(Sodium, Aluminium, Iron);
        Bentonite.addByProduct(Aluminium, Calcium, Magnesium);
        Uraninite.addByProduct(Uranium, Thorium, Uranium235);
        Pitchblende.addByProduct(Thorium, Uranium, Lead);
        Galena.addByProduct(Sulfur, Silver, Lead);
        Lapis.addByProduct(Calcite, Pyrite);
        Pyrite.addByProduct(Sulfur, Phosphorus, Iron);
        Copper.addByProduct(Cobalt, Gold, Nickel);
        Nickel.addByProduct(Cobalt, Platinum, Iron);
        RedGarnet.addByProduct(Spessartine, Pyrope, Almandine);
        YellowGarnet.addByProduct(Andradite, Grossular, Uvarovite);
        Cooperite.addByProduct(Palladium, Nickel, Iridium);
        Cinnabar.addByProduct(Redstone, Sulfur, Glowstone);
        Tantalite.addByProduct(Manganese, Niobium, Tantalum);
        Pentlandite.addByProduct(Iron, Sulfur, Cobalt);
        Uranium.addByProduct(Lead, Uranium235, Thorium);
        Scheelite.addByProduct(Manganese, Molybdenum, Calcium);
        Tungstate.addByProduct(Manganese, Silver, Lithium);
        Bauxite.addByProduct(Grossular, Rutile, Gallium);
        Redstone.addByProduct(Cinnabar, RareEarth, Glowstone);
        Malachite.addByProduct(Copper, BrownLimonite, Calcite);
        YellowLimonite.addByProduct(Nickel, BrownLimonite, Cobalt);
        Andradite.addByProduct(YellowGarnet, Iron, Boron);
        Quartzite.addByProduct(Barite);
        BrownLimonite.addByProduct(Malachite, YellowLimonite);
        Neodymium.addByProduct(RareEarth);
        Bastnasite.addByProduct(Neodymium, RareEarth);
        Glowstone.addByProduct(Redstone, Gold);
        Zinc.addByProduct(Tin, Gallium);
        Tungsten.addByProduct(Manganese, Molybdenum);
        Iron.addByProduct(Nickel, Tin);
        Gold.addByProduct(Copper, Nickel);
        Tin.addByProduct(Iron, Zinc);
        Antimony.addByProduct(Zinc, Iron);
        Silver.addByProduct(Lead, Sulfur);
        Lead.addByProduct(Silver, Sulfur);
        Thorium.addByProduct(Uranium, Lead);
        Plutonium.addByProduct(Uranium, Lead);
        Electrum.addByProduct(Gold, Silver);
        Bronze.addByProduct(Copper, Tin);
        Brass.addByProduct(Copper, Zinc);
        Coal.addByProduct(Lignite, Thorium);
        Ilmenite.addByProduct(Iron, Rutile);
        Manganese.addByProduct(Chrome, Iron);
        BlueSapphire.addByProduct(Aluminium);
        Platinum.addByProduct(Nickel, Iridium);
        Emerald.addByProduct(Beryllium, Aluminium);
        Olivine.addByProduct(Pyrope, Magnesium);
        Chrome.addByProduct(Iron, Magnesium);
        Tetrahedrite.addByProduct(Antimony, Zinc);
        Magnetite.addByProduct(Iron, Gold);
        Basalt.addByProduct(Olivine, DarkAsh);
        VanadiumMagnetite.addByProduct(Magnetite, Vanadium);
        Spodumene.addByProduct(Aluminium, Lithium);
        Ruby.addByProduct(Chrome, RedGarnet);
        Phosphorus.addByProduct(Phosphate);
        Iridium.addByProduct(Platinum, Osmium);
        Pyrope.addByProduct(RedGarnet, Magnesium);
        Almandine.addByProduct(RedGarnet, Aluminium);
        Spessartine.addByProduct(RedGarnet, Manganese);
        Grossular.addByProduct(YellowGarnet, Calcium);
        Uvarovite.addByProduct(YellowGarnet, Chrome);
        Calcite.addByProduct(Andradite, Malachite);
        EnrichedNaquadah.addByProduct(Naquadah, Naquadria);
        Naquadah.addByProduct(EnrichedNaquadah);
        Pyrolusite.addByProduct(Manganese);
        Molybdenite.addByProduct(Molybdenum);
        Stibnite.addByProduct(Antimony);
        Garnierite.addByProduct(Nickel);
        Lignite.addByProduct(Coal);
        Diamond.addByProduct(Graphite);
        Beryllium.addByProduct(Emerald);
        Magnesite.addByProduct(Magnesium);
        Quartz.addByProduct(Netherrack);
        Steel.addByProduct(Iron);
        Graphite.addByProduct(Carbon);
        Netherrack.addByProduct(Sulfur);
        Flint.addByProduct(Obsidian);
        Cobaltite.addByProduct(Cobalt);
        Cobalt.addByProduct(Cobaltite);
        Sulfur.addByProduct(Sulfur);
        Saltpeter.addByProduct(Saltpeter);
        Endstone.addByProduct(Helium3);
        Osmium.addByProduct(Iridium);
        Magnesium.addByProduct(Olivine);
        Aluminium.addByProduct(Bauxite);
        Titanium.addByProduct(Almandine);
        Obsidian.addByProduct(Olivine);
        Ash.addByProduct(Carbon);
        DarkAsh.addByProduct(Carbon);
        Marble.addByProduct(Calcite);
        Clay.addByProduct(Clay);
        Cassiterite.addByProduct(Tin);
        BlackGranite.addByProduct(Biotite);
        RedGranite.addByProduct(PotassiumFeldspar);
        Phosphate.addByProduct(Phosphor);
        Phosphor.addByProduct(Phosphate);
        Tanzanite.addByProduct(Opal);
        Opal.addByProduct(Tanzanite);
        Amethyst.addByProduct(Amethyst);
        // Amber.addByProduct(Amber);
        Neutronium.addByProduct(Neutronium);
        Lithium.addByProduct(Lithium);
        Silicon.addByProduct(SiliconDioxide);
        Salt.addByProduct(RockSalt);
        RockSalt.addByProduct(Salt);

        // Glue.mChemicalFormula = "No Horses were harmed for the Production";
        // UUAmplifier.mChemicalFormula = "Accelerates the Mass Fabricator";
        // WoodSealed.mChemicalFormula = "";
        // Wood.mChemicalFormula = "";

        // Naquadah.mMoltenRGBa[0] = 0;
        // Naquadah.mMoltenRGBa[1] = 255;
        // Naquadah.mMoltenRGBa[2] = 0;
        // Naquadah.mMoltenRGBa[3] = 0;
        // NaquadahEnriched.mMoltenRGBa[0] = 64;
        // NaquadahEnriched.mMoltenRGBa[1] = 255;
        // NaquadahEnriched.mMoltenRGBa[2] = 64;
        // NaquadahEnriched.mMoltenRGBa[3] = 0;
        // Naquadria.mMoltenRGBa[0] = 128;
        // Naquadria.mMoltenRGBa[1] = 255;
        // Naquadria.mMoltenRGBa[2] = 128;
        // Naquadria.mMoltenRGBa[3] = 0;

        // NaquadahEnriched.mChemicalFormula = "Nq+";
        // Naquadah.mChemicalFormula = "Nq";
        // Naquadria.mChemicalFormula = "NqX";
    }

    // TODO go through the GT_Loader_Item_Block_And_Fluid and make sure all
    // explicitly added fluids have the LIQUID tag
    public static void init() {
        // TODO assign correct handle materials
        // for (Material material : generated) {
        // if (material == Blaze) {
        // material.handleMaterial = "blaze";
        // } /*else if (aMaterial.contains(SubTag.MAGICAL) &&
        // aMaterial.contains(SubTag.CRYSTAL) && Utils.isModLoaded(MOD_ID_TC)) {
        // aMaterial.mHandleMaterial = Thaumium;
        // }*/ else if (material.getMass() > Element.Tc.getMass() * 2) {
        // material.handleMaterial = Tungstensteel.;
        // } else if (material.getMass() > Element.Tc.getMass()) {
        // material.handleMaterial = Steel;
        // } else {
        // material.handleMaterial = Wood;
        // }
        // }
        // If using small ore markers, every normal ore needs a small version. This
        // greatly increases block usage
        overrides();
    }

    private static void overrides() {
        DUST.forceOverride(Redstone, Items.REDSTONE);
        DUST.forceOverride(Glowstone, Items.GLOWSTONE);

        INGOT.forceOverride(Iron, Items.IRON_INGOT);
        INGOT.forceOverride(Gold, Items.GOLD_INGOT);
        INGOT.forceOverride(Netherite, Items.NETHERITE_INGOT);

        GEM.forceOverride(Charcoal, Items.CHARCOAL);
        GEM.forceOverride(Coal, Items.COAL);
        GEM.forceOverride(Diamond, Items.DIAMOND);

        BLOCK.forceOverride(Iron, Items.IRON_BLOCK);
        BLOCK.forceOverride(Gold, Items.GOLD_BLOCK);
        BLOCK.forceOverride(Emerald, Items.EMERALD_BLOCK);
        BLOCK.forceOverride(Diamond, Items.DIAMOND_BLOCK);

        NUGGET.forceOverride(Iron, Items.IRON_NUGGET);
        NUGGET.forceOverride(Gold, Items.GOLD_NUGGET);

        ROD.forceOverride(Blaze, Items.BLAZE_ROD);
        ROD.forceOverride(Wood, Items.STICK);
    }
}
