package muramasa.gregtech.data;

import com.sun.jna.platform.mac.Carbon;
import muramasa.antimatter.Antimatter;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Data;
import muramasa.antimatter.event.MaterialEvent;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.material.TextureSet;
import muramasa.gregtech.Ref;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.redstone.Redstone;
import org.checkerframework.checker.units.qual.C;
import org.checkerframework.checker.units.qual.K;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.Data.Blaze;
import static muramasa.antimatter.Data.Charcoal;
import static muramasa.antimatter.Data.Glowstone;
import static muramasa.antimatter.Data.Wood;
import static muramasa.antimatter.material.Element.*;
import static muramasa.antimatter.material.Element.Zn;
import static muramasa.antimatter.material.MaterialTags.ELEC;
import static muramasa.antimatter.material.MaterialTags.RUBBERTOOLS;
import static muramasa.antimatter.material.TextureSet.*;
import static muramasa.antimatter.material.TextureSet.NONE;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.Materials.Flint;
import static net.minecraft.world.item.Tiers.IRON;

([a-zA-Z0-9]+)\.(.*)
        event.setMaterial($1).$2
public class GregTechMaterialEvents {

    Aluminium
            .asMetal(933, 1700, PLATE, ROD, SCREW, BOLT, RING, GEAR, FRAME, GEAR_SMALL, FOIL).asOre()
            .addTools(1.5F, 10.0F, 140, 2);
    Beryllium
            .asOre().addTools(2.0F, 14.0F, 64, 2);
    Bismuth
            .asOre();
    Materials.Carbon.asSolid(); // TODO: Tools,
    // Carbon
    // Fluid? Removed Tool;
    Chrome
            .asMetal(2180, 1700, SCREW, BOLT, RING, PLATE, ROTOR).addTools(2.5F, 11.0F, 256, 3);
    Cobalt.asMetal(1768, 0).asOre()
            .addTools(3.0F, 8.0F, 512, 3);
    Iridium.asMetal(2719, 2719, FRAME)
            .asOre().addTools(5.0F, 8.0F, 2560, 4);
    Lanthanum.asSolid(1193, 1193);
    Lead
            .asMetal(600, 0, PLATE, PLATE_DENSE, FOIL, ROD, FRAME, BOLT).asOre();
    Manganese.asMetal(1519, 0).asOre();
    Molybdenum.asMetal(2896, 0).asOre()
            .addTools(2.0F, 7.0F, 512, 2);
    Neodymium
            .asMetal(1297, 1297, PLATE, ROD).asOre(); // TODO: Bastnasite or Monazite for Ore For;
    Neutronium
            .asMetal(10000, 10000, SCREW, BOLT, RING, GEAR, FRAME).addTools(9.0F, 24.0F, 655360, 6); // TODO Vibraniu;
    Nickel.asMetal(1728, 0, PLATE)
            .asOre().asPlasma();
    Osmium
            .asMetal(3306, 3306, SCREW, BOLT, RING, PLATE, FOIL, ROD, WIRE_FINE).addTools(4.0F, 16.0F, 1080, 4);
    Palladium.asMetal(1828, 1828)
            .asOre().addTools(3.0F, 10.0F, 420, 2);
    Platinum
            .asMetal(2041, 0, PLATE, FOIL, ROD, WIRE_FINE).asOre().addTools(4.5F, 18.0F, 48, 2);
    Plutonium.asMetal(912, 0)
            .addTools(2.5F, 6.0F, 280, 3, of(Enchantments.FIRE_ASPECT, 2)); // TODO: Enchantment: Radioactivit;
    Plutonium241.asMetal(912, 0)
            .addTools(2.5F, 6.0F, 280, 3);
    Silver.asMetal(1234, 0, PLATE, SCREW)
            .asOre();
    Thorium.asMetal(2115, 0).asOre()
            .addTools(1.5F, 6.0F, 512, 2);
    Titanium
            .asMetal(1941, 1940, PLATE, ROD, SCREW, BOLT, RING, GEAR, FRAME, GEAR_SMALL, ROTOR).asOre()
            .addTools(2.5F, 7.0F, 1600, 3);
    Tungsten.asMetal(3695, 3000, FOIL)
            .addTools(2.0F, 6.0F, 512, 3); // Tungstensteel would be the one with tool;
    Uranium.asMetal(1405, 0)
            .asOre();
    Uranium235.asMetal(1405, 0)
            .addTools(3.0F, 6.0F, 512, 3);
    Graphite.asDust().asOre();
    Americium.asMetal(1149, 0); // TODO:
    // When
    // we're
    // thinking
    // about
    // fusio;
    Antimony.asMetal(1449, 0);
    Argon.asGas();
    Arsenic.asSolid();
    Barium.asDust(1000);
    Boron.asDust(2349);
    Caesium.asMetal(2349, 0);
    Calcium.asDust(1115);
    Cadmium.asDust(594);
    Cerium.asSolid(1068, 1068);
    Chlorine.asGas();
    Deuterium.asGas();
    Dysprosium.asMetal(1680, 1680);
    Europium.asMetal(1099, 1099);
    Fluorine.asGas();
    Gallium.asMetal(302, 0);
    Hydrogen.asGas();
    Helium.asPlasma();
    Helium3.asGas();
    Indium.asSolid(429, 0);
    Lithium.asSolid(454, 0).asOre();
    Lutetium.asMetal(1925, 1925);
    Magnesium.asMetal(923, 0);
    Mercury.asFluid();
    Niobium.asMetal(2750, 2750);
    Nitrogen.asPlasma();
    Oxygen.asPlasma();
    Phosphor.asDust(317);
    Potassium.asSolid(336, 0);
    Radon.asGas();
    Silicon
            .asMetal(1687, 1687, PLATE, FOIL);
    Sodium.asDust(370);
    Sulfur.asDust(388).asOre().asPlasma();
    Tantalum.asSolid(3290, 0);
    Tin
            .asMetal(505, 505, PLATE, ROD, SCREW, BOLT, RING, GEAR, FOIL, WIRE_FINE, FRAME, ROTOR).asOre();
    Tritium.asGas();
    Vanadium.asMetal(2183, 2183);
    Yttrium.asMetal(1799, 1799);
    Zinc.asMetal(692, 0, PLATE, FOIL)
            .asOre();

    // TODO: We can be more lenient about what fluids we have in, its not as bad as
    // solids above, and we can stop them from showing in JEI (I think...)

    /**
     * Gases
     **/
    WoodGas.asGas(24);
    Methane.asGas(104)
            .mats(of(Carbon, 1, Hydrogen, 4));
    CarbonDioxide.asGas()
            .mats(of(Carbon, 1, Oxygen, 2));
    // NobleGases = AntimatterAPI.register(Material.class,
    // new Material(Ref.ID, "noble_gases", 0xc9e3fc, NONE)).asGas()/*.setTemp(79,
    // 0)*/.addComposition(of(CarbonDioxide, 21, Helium, 9, Methane, 3, Deuterium,
    // 1));
    Air.asGas().mats(of(Nitrogen, 40, Oxygen, 11, Argon, 1/* , NobleGases, 1 */));
    NitrogenDioxide.asGas()
            .mats(of(Nitrogen, 1, Oxygen, 2));
    NaturalGas.asGas(15);
    SulfuricGas.asGas(20);
    RefineryGas.asGas(128);
    LPG.asGas(256);
    Ethane.asGas(168)
            .mats(of(Carbon, 2, Hydrogen, 6));
    Propane.asGas(232)
            .mats(of(Carbon, 2, Hydrogen, 6));
    Butane.asGas(296)
            .mats(of(Carbon, 4, Hydrogen, 10));
    Butene.asGas(256)
            .mats(of(Carbon, 4, Hydrogen, 8));
    Butadiene.asGas(206)
            .mats(of(Carbon, 4, Hydrogen, 6));
    VinylChloride.asGas()
            .mats(of(Carbon, 2, Hydrogen, 3, Chlorine, 1));
    SulfurDioxide.asGas()
            .mats(of(Sulfur, 1, Oxygen, 2));
    SulfurTrioxide.asGas()
            /* .setTemp(344, 1) */.mats(of(Sulfur, 1, Oxygen, 3));
    Dimethylamine.asGas()
            .mats(of(Carbon, 2, Hydrogen, 7, Nitrogen, 1));
    DinitrogenTetroxide.asGas()
            .mats(of(Nitrogen, 2, Oxygen, 4));
    NitricOxide.asGas()
            .mats(of(Nitrogen, 1, Oxygen, 1));
    Ammonia.asGas()
            .mats(of(Nitrogen, 1, Hydrogen, 3));
    Chloromethane.asGas()
            .mats(of(Carbon, 1, Hydrogen, 3, Chlorine, 1));
    Tetrafluoroethylene.asGas()
            .mats(of(Carbon, 2, Fluorine, 4));
    CarbonMonoxide.asGas(24)
            .mats(of(Carbon, 1, Oxygen, 1));
    Ethylene.asGas(128)
            .mats(of(Carbon, 2, Hydrogen, 4));
    Propene.asGas(192)
            .mats(of(Carbon, 3, Hydrogen, 6));
    Ethenone.asGas()
            .mats(of(Carbon, 2, Hydrogen, 2, Oxygen, 1));
    HydricSulfide.asGas()
            .mats(of(Hydrogen, 2, Sulfur, 1));

    /**
     * Fluids
     **/
    Steam
            .asGas(1, 395);
    SaltWater
            .asFluid();
    UUAmplifier.asFluid();
    UUMatter.asFluid();
    Antimatter.asFluid();
    // CharcoalByproducts.asFluid(; //TODO I'll think about
    // this and woods when I get started on pyrolysi;
    Glue
            .asFluid();
    Honey
            .asFluid(); // TODO: Only when Forestry's present;
    Lubricant.asFluid();
    // WoodTar = AntimatterAPI.register(Material.class, new
    // Material(Ref.ID, "wood_tar", 0x28170b, NONE)).asFluid(; TODO: not sure if
    // neede;
    WoodVinegar.asFluid();
    LiquidAir.asFluid()
            /* .setTemp(79, 0) */.mats(of(Nitrogen, 40, Oxygen, 11, Argon, 1/* , NobleGases, 1 */)); // TODO Rrename to
    // liquid
    // oxygen <- Nope, add
    // fluid to Oxyge;
    DistilledWater.asFluid()
            .mats(of(Hydrogen, 2, Oxygen, 1));
    Glyceryl.asFluid()
            .mats(of(Carbon, 3, Hydrogen, 5, Nitrogen, 3, Oxygen, 9));
    Titaniumtetrachloride.asFluid()
            .mats(of(Titanium, 1, Chlorine, 4));
    SodiumPersulfate.asFluid()
            .mats(of(Sodium, 2, Sulfur, 2, Oxygen, 8));
    DilutedHydrochloricAcid.asFluid()
            .mats(of(Hydrogen, 1, Chlorine, 1));
    NitrationMixture.asFluid();
    Dichlorobenzene.asFluid()
            .mats(of(Carbon, 6, Hydrogen, 4, Chlorine, 2));
    Styrene.asFluid()
            .mats(of(Carbon, 8, Hydrogen, 8));
    Isoprene.asFluid()
            .mats(of(Carbon, 8, Hydrogen, 8));
    Tetranitromethane.asFluid()
            .mats(of(Carbon, 1, Nitrogen, 4, Oxygen, 8));
    Epichlorohydrin.asFluid()
            .mats(of(Carbon, 3, Hydrogen, 5, Chlorine, 1, Oxygen, 1));
    NitricAcid.asFluid()
            .mats(of(Hydrogen, 1, Nitrogen, 1, Oxygen, 3));
    Dimethylhydrazine.asFluid()
            .mats(of(Carbon, 2, Hydrogen, 8, Nitrogen, 2));
    Chloramine.asFluid()
            .mats(of(Nitrogen, 1, Hydrogen, 2, Chlorine, 1));
    Dimethyldichlorosilane.asFluid()
            .mats(of(Carbon, 2, Hydrogen, 6, Chlorine, 2, Silicon, 1));
    HydrofluoricAcid.asFluid()
            .mats(of(Hydrogen, 1, Fluorine, 1));
    Chloroform.asFluid()
            .mats(of(Carbon, 1, Hydrogen, 1, Chlorine, 3));
    BisphenolA.asFluid()
            .mats(of(Carbon, 15, Hydrogen, 16, Oxygen, 2));
    AceticAcid.asFluid()
            .mats(of(Carbon, 2, Hydrogen, 4, Oxygen, 2));
    // CalciumAcetateSolution.asFluid().addComposition(of(Calcium, 1, Carbon, 2, Oxygen, 4,
    // Hydrogen, 6);
    Acetone.asFluid()
            .mats(of(Carbon, 3, Hydrogen, 6, Oxygen, 1));
    Methanol = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "methanol", 0xaa8800, NONE)).asFluid(84)
            .mats(of(Carbon, 1, Hydrogen, 4, Oxygen, 1));
    VinylAcetate = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "vinyl_acetate", 0xffb380, NONE)).asFluid()
            .mats(of(Carbon, 4, Hydrogen, 6, Oxygen, 2));
    PolyvinylAcetate = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "polyvinyl_acetate", 0xff9955, NONE)).asFluid()
            .mats(of(Carbon, 4, Hydrogen, 6, Oxygen, 2));
    MethylAcetate = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "methyl_acetate", 0xeec6af, NONE)).asFluid()
            .mats(of(Carbon, 3, Hydrogen, 6, Oxygen, 2));
    AllylChloride = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "allyl_chloride", 0x87deaa, NONE)).asFluid()
            .mats(of(Carbon, 3, Hydrogen, 5, Chlorine, 1));
    HydrochloricAcid = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "hydrochloric_acid", 0x6f8a91, NONE)).asFluid()
            .mats(of(Hydrogen, 1, Chlorine, 1));
    HypochlorousAcid = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "hypochlorous_acid", 0x6f8a91, NONE)).asFluid()
            .mats(of(Hydrogen, 1, Chlorine, 1, Oxygen, 1));
    Cumene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cumene", 0x552200, NONE)).asFluid()
            .mats(of(Carbon, 9, Hydrogen, 12));
    PhosphoricAcid = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "phosphoric_acid", 0xdcdc00, NONE)).asFluid()
            .mats(of(Hydrogen, 3, Phosphor, 1, Oxygen, 4));
    SulfuricAcid = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sulfuric_acid", 0xff8000, NONE)).asFluid()
            .mats(of(Hydrogen, 2, Sulfur, 1, Oxygen, 4));
    SulfuricTrioxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sulfuric_trioxide", 0x8d8d21, NONE)).asGas()
            .mats(of(Sulfur, 1, Oxygen, 3));
    SulfuricDioxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sulfuric_dioxide", 0x8d8d27, NONE)).asGas()
            .mats(of(Sulfur, 1, Oxygen, 2));
    DilutedSulfuricAcid = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "diluted_sulfuric_acid", 0xc07820, NONE)).asFluid()
            .mats(of(SulfuricAcid, 1));
    Benzene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "benzene", 0x1a1a1a, NONE)).asFluid(288)
            .mats(of(Carbon, 6, Hydrogen, 6));
    Phenol = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "phenol", 0x784421, NONE)).asFluid(288)
            .mats(of(Carbon, 6, Hydrogen, 6, Oxygen, 1));
    Toluene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "toluene", 0x501d05, NONE)).asFluid(328)
            .mats(of(Carbon, 7, Hydrogen, 8));
    SulfuricNaphtha = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sulfuric_naphtha", 0xffff00, NONE)).asFluid(32);
    Naphtha = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "naphtha", 0xffff00, NONE)).asFluid(256);
    DrillingFluid = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "drilling_fluid", 0xffffff, NONE)).asFluid(); // TODO:
    // Perhaps for
    // a bedrock drill;
    BlueVitriol = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "blue_vitriol_water_solution", 0xffffff, NONE)).asFluid();
    IndiumConcentrate = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "indium_concentrate", 0xffffff, NONE)).asFluid();
    NickelSulfate = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "nickel_sulfate", 0xffffff, NONE)).asFluid();
    RocketFuel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "rocket_fuel", 0xffffff, NONE)).asFluid();
    LeadZincSolution = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "lead_zinc_solution", 0xffffff, NONE)).asFluid();

    /**
     * Fuels
     **/
    Diesel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "diesel", 0xffff00, NONE)).asFluid(128);
    NitroFuel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cetane_boosted_diesel", 0xc8ff00, NONE)).asFluid(512);
    BioDiesel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "bio_diesel", 0xff8000, NONE)).asFluid(192);
    Biomass = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "biomass", 0x00ff00, NONE)).asFluid(8);
    FermentedBiomass = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "fermented_biomass", 0x09964a, NONE)).asFluid(16);
    Ethanol = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "ethanol", 0xff8000, NONE)).asFluid(148)
            .mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 1));
    Ethanediol = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "ethanediol", 0xff8000, NONE)).asFluid(216)
            .mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 2));
    Propanol = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "propanol", 0xff8000, NONE)).asFluid(196)
            .mats(of(Carbon, 3, Hydrogen, 8, Oxygen, 1));
    Ethenol = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "ethenol", 0xff8000, NONE)).asFluid(120)
            .mats(of(Carbon, 2, Hydrogen, 4, Oxygen, 1));
    Propanediol = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "propanediol", 0xff8000, NONE)).asFluid(256)
            .mats(of(Carbon, 3, Hydrogen, 8, Oxygen, 2));
    Propenol = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "propenol", 0xff8000, NONE)).asFluid(196)
            .mats(of(Carbon, 3, Hydrogen, 6, Oxygen, 1));
    Butanol = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "butanol", 0xff8000, NONE)).asFluid(166)
            .mats(of(Carbon, 4, Hydrogen, 10, Oxygen, 1));
    Butenol = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "butenol", 0xff8000, NONE)).asFluid(186)
            .mats(of(Carbon, 4, Hydrogen, 8, Oxygen, 1));
    Butanediol = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "butanediol", 0xff8000, NONE)).asFluid(286)
            .mats(of(Carbon, 4, Hydrogen, 10, Oxygen, 2));
    Creosote = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "creosote", 0x804000, NONE)).asFluid(8);
    FishOil = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "fish_oil", 0xffc400, NONE)).asFluid(2);
    Oil = AntimatterAPI.register(Material.class, new Material(Ref.ID, "oil", 0x0a0a0a, NONE))
            .asFluid(16);
    SeedOil = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "seed_oil", 0xc4ff00, NONE)).asFluid(2);
    // public static Materials SeedOilHemp = new Materials(722, "Hemp Seed Oil",
    // 196, 255, 0, lime, NONE).asSemi(2;
    // public static Materials SeedOilLin = new Materials(723, "Lin Seed Oil", 196,
    // 255, 0, lime, NONE).asSemi(2;
    // OilExtraHeavy = AntimatterAPI.register(Material.class,
    // new Material(Ref.ID, "extra_heavy_oil", 0x0a0a0a, NONE)).asFluid(40);
    OilHeavy = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "heavy_oil", 0x0a0a0a, NONE)).asFluid(32);
    OilMedium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "raw_oil", 0x0a0a0a, NONE)).asFluid(24);
    OilLight = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "light_oil", 0x0a0a0a, NONE)).asFluid(16);
    SulfuricLightFuel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sulfuric_light_diesel", 0xffff00, NONE)).asFluid(32);
    SulfuricHeavyFuel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sulfuric_heavy_diesel", 0xffff00, NONE)).asFluid(32);
    LightDiesel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "light_diesel", 0xffff00, NONE)).asFluid(256);
    HeavyDiesel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "heavy_diesel", 0xffff00, NONE)).asFluid(192);
    Glycerol = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "glycerol", 0x87de87, NONE)).asFluid(164)
            .mats(of(Carbon, 3, Hydrogen, 8, Oxygen, 3));

    /**
     * Dusts
     **/
    WoodPulp = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "wood_pulp", 0x3f2620, NONE)).asDust();
    SodiumSulfide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sodium_sulfide", 0xffe680, NONE)).asDust()
            .mats(of(Sodium, 2, Sulfur, 1));
    TinAlloy = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tin_alloy", 0x9fadbb, NONE)).asDust()
            .mats(of(Tin, 1, Iron, 1));
    Energium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "energium", 0xe81e21, NONE)).asDust();
    BorosilicateGlass = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "borosilicate_glass", 0xfafafa, NONE)).asDust()
            .mats(of(Boron, 1, Silicon, 7, Oxygen,14));
    IridiumSodiumOxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "iridium_sodium_oxide", 0xffffff, NONE)).asDust();
    IndiumGalliumPhosphide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "indium_gallium_phosphide", 0x570b79, NONE)).asDust()
            .mats(of(Indium, 1, Gallium, 1, Phosphor, 1));
    PlatinumGroupSludge = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "platinum_group_sludge", 0x001e00, NONE)).asDust();
    Glowstone = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "glowstone", 0xffff00, SHINY)).asDust();
    Graphene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "graphene", 0x808080, DULL)).asDust(PLATE);
    Oilsands = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "oilsands", 0x0a0a0a, NONE)).asOre(true);
    RareEarth = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "rare_earth", 0x808064, FINE)).asDust();
    Almandine = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "almandine", 0xff0000, ROUGH)).asOre()
            .mats(of(Aluminium, 2, Iron, 3, Silicon, 3, Oxygen, 12));
    Andradite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "andradite", 0x967800, ROUGH)).asOre()
            .mats(of(Calcium, 3, Iron, 2, Silicon, 3, Oxygen, 12));
    Ash = AntimatterAPI.register(Material.class, new Material(Ref.ID, "ash", 0x969696, DULL))
            .asDust();
    BandedIron = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "banded_iron", 0x915a5a, DULL)).asOre(true)
            .mats(of(Iron, 2, Oxygen, 3));
    BrownLimonite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "brown_limonite", 0xc86400, METALLIC)).asOre(true)
            .mats(of(Iron, 1, Hydrogen, 1, Oxygen, 2));
    Calcite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "calcite", 0xfae6dc, DULL)).asOre(true)
            .mats(of(Calcium, 1, Carbon, 1, Oxygen, 3));
    Cassiterite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cassiterite", 0xdcdcdc, METALLIC)).asOre()
            .mats(of(Tin, 1, Oxygen, 2));
    Chalcopyrite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "chalcopyrite", 0xa07828, DULL)).asOre()
            .mats(of(Copper, 1, Iron, 1, Sulfur, 2));
    Clay = AntimatterAPI.register(Material.class, new Material(Ref.ID, "clay", 0xc8c8dc, ROUGH))
            .asDust().mats(of(Sodium, 2, Lithium, 1, Aluminium, 2, Silicon, 2, Water, 6));
    Cobaltite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cobaltite", 0x5050fa, METALLIC)).asOre(true)
            .mats(of(Cobalt, 1, Arsenic, 1, Sulfur, 1));
    Cooperite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cooperite", 0xffffc8, METALLIC)).asOre()
            .mats(of(Platinum, 3, Nickel, 1, Sulfur, 1, Palladium, 1));
    DarkAsh = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "dark_ash", 0x323232, DULL)).asDust();
    Galena = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "galena", 0x643c64, DULL)).asOre()
            .mats(of(Lead, 3, Silver, 3, Sulfur, 2));
    Garnierite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "garnierite", 0x32c846, METALLIC)).asOre()
            .mats(of(Nickel, 1, Oxygen, 1));
    Grossular = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "grossular", 0xc86400, ROUGH)).asOre()
            .mats(of(Calcium, 3, Aluminium, 2, Silicon, 3, Oxygen, 12));
    Ilmenite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "ilmenite", 0x463732, METALLIC)).asOre()
            .mats(of(Iron, 1, Titanium, 1, Oxygen, 3));
    Rutile = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "rutile", 0xd40d5c, GEM_H)).asOre()
            .mats(of(Titanium, 1, Oxygen, 2));
    MagnesiumChloride = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "magnesiumchloride", 0xd40d5c, DULL)).asDust()
            .mats(of(Magnesium, 1, Chlorine, 2));
    Magnesite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "magnesite", 0xfafab4, METALLIC)).asOre(true)
            .mats(of(Magnesium, 1, Carbon, 1, Oxygen, 3));
    Magnetite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "magnetite", 0x1e1e1e, METALLIC)).asOre()
            .mats(of(Iron, 3, Oxygen, 4));
    Molybdenite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "molybdenite", 0x91919, METALLIC)).asOre(true)
            .mats(of(Molybdenum, 1, Sulfur, 2));
    Obsidian = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "obsidian", 0x503264, DULL)).asDust()
            .addHandleStat(222, -0.5F, of(Enchantments.UNBREAKING, 2))
            .mats(of(Magnesium, 1, Iron, 1, Silicon, 2, Oxygen, 8));
    Phosphate = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "phosphate", 0xffff00, DULL)).asOre(true)
            .mats(of(Phosphor, 1, Oxygen, 4));
    Polydimethylsiloxane = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "polydimethylsiloxane", 0xf5f5f5, NONE)).asDust()
            .mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 1, Silicon, 1));
    // Powellite = AntimatterAPI.register(Material.class, new
    // Material(Ref.ID, "powellite", 0xffff00,
    // DULL)).asDust(ORE).addComposition(of(Calcium, 1, Molybdenum, 1, Oxygen, 4));
    Pyrite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "pyrite", 0x967828, ROUGH)).asOre()
            .mats(of(Iron, 1, Sulfur, 2));
    Pyrolusite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "pyrolusite", 0x9696aa, DULL)).asOre()
            .mats(of(Manganese, 1, Oxygen, 2));
    Pyrope = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "pyrope", 0x783264, METALLIC)).asOre()
            .mats(of(Aluminium, 2, Magnesium, 3, Silicon, 3, Oxygen, 12));
    RawRubber = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "raw_rubber", 0xccc789, DULL)).asDust()
            .mats(of(Carbon, 5, Hydrogen, 8));
    Saltpeter = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "saltpeter", 0xe6e6e6, FINE)).asOre()
            .mats(of(Potassium, 1, Nitrogen, 1, Oxygen, 3));
    Scheelite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "scheelite", 0xc88c14, DULL)).asDust(2500).asOre(true)
            .mats(of(Tungsten, 1, Calcium, 2, Oxygen, 4));
    SiliconDioxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "silicon_dioxide", 0xc8c8c8, QUARTZ)).asDust()
            .mats(of(Silicon, 1, Oxygen, 2));
    // Pyrochlore = AntimatterAPI.register(Material.class,
    // new Material(Ref.ID, "pyrochlore", 0x2b1100,
    // METALLIC)).asDust(ORE).addComposition(of(Calcium, 2, Niobium, 2, Oxygen, 7));
    FerriteMixture = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "ferrite_mixture", 0xb4b4b4, METALLIC)).asDust()
            .mats(of(Nickel, 1, Zinc, 1, Iron, 4));
    Massicot = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "massicot", 0xffdd55, DULL)).asDust()
            .mats(of(Lead, 1, Oxygen, 1));
    ArsenicTrioxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "arsenic_trioxide", 0xffffff, SHINY)).asDust()
            .mats(of(Arsenic, 2, Oxygen, 3));
    CobaltOxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cobalt_oxide", 0x668000, DULL)).asDust()
            .mats(of(Cobalt, 1, Oxygen, 1));
    Magnesia = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "magnesia", 0xffffff, DULL)).asDust()
            .mats(of(Magnesium, 1, Oxygen, 1));
    Quicklime = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "quicklime", 0xf0f0f0, DULL)).asDust()
            .mats(of(Calcium, 1, Oxygen, 1));
    Potash = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "potash", 0x784237, DULL)).asDust()
            .mats(of(Potassium, 2, Oxygen, 1));
    SodaAsh = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "soda_ash", 0xdcdcff, DULL)).asDust()
            .mats(of(Sodium, 2, Carbon, 1, Oxygen, 3));
    Brick = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "brick", 0x9b5643, ROUGH)).asDust()
            .mats(of(Aluminium, 4, Silicon, 3, Oxygen, 12));
    Fireclay = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "fireclay", 0xada09b, ROUGH)).asDust(INGOT, PLATE).mats(of(Brick, 1));
    SodiumBisulfate = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sodium_bisulfate", 0x004455, NONE)).asDust()
            .mats(of(Sodium, 1, Hydrogen, 1, Sulfur, 1, Oxygen, 4));
    RawStyreneButadieneRubber = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "raw_styrene_butadiene_rubber", 0x54403d, SHINY)).asDust()
            .mats(of(Styrene, 1, Butadiene, 3));
    PhosphorousPentoxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "phosphorous_pentoxide", 0xdcdc00, NONE)).asDust()
            .mats(of(Phosphor, 4, Oxygen, 10));
    SodiumHydroxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sodium_hydroxide", 0x003380, DULL)).asDust()
            .mats(of(Sodium, 1, Oxygen, 1, Hydrogen, 1));
    Spessartine = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "spessartine", 0xff6464, DULL)).asOre()
            .mats(of(Aluminium, 2, Manganese, 3, Silicon, 3, Oxygen, 12));
    Sphalerite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sphalerite", 0xffffff, DULL)).asOre()
            .mats(of(Zinc, 1, Sulfur, 1));
    Stibnite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "stibnite", 0x464646, METALLIC)).asOre()
            .mats(of(Antimony, 2, Sulfur, 3));
    Tetrahedrite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tetrahedrite", 0xc82000, DULL)).asOre(true)
            .mats(of(Copper, 3, Antimony, 1, Sulfur, 3, Iron, 1));
    Tungstate = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tungstate", 0x373223, DULL)).asOre(true)
            .mats(of(Tungsten, 1, Lithium, 2, Oxygen, 4));
    Uraninite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "uraninite", 0x232323, METALLIC)).asOre(true)
            .mats(of(Uranium, 1, Oxygen, 2));
    Uvarovite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "uvarovite", 0xb4ffb4, DIAMOND)).asOre()
            .mats(of(Calcium, 3, Chrome, 2, Silicon, 3, Oxygen, 12));
    Wood = AntimatterAPI.register(Material.class, new Material(Ref.ID, "wood", 0x643200, NONE))
            .asDust(GEAR).addHandleStat(12, 0.0F).mats(of(Carbon, 1, Oxygen, 1, Hydrogen, 1));
    Wulfenite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "wulfenite", 0xff8000, DULL)).asOre(true)
            .mats(of(Lead, 1, Molybdenum, 1, Oxygen, 4));
    YellowLimonite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "yellow_limonite", 0xc8c800, METALLIC)).asOre(true)
            .mats(of(Iron, 1, Hydrogen, 1, Oxygen, 2));
    // SealedWood = AntimatterAPI.register(Material.class,
    // new Material(Ref.ID, "sealed_wood", 0x502800, NONE)).asDust().addTools(3.0F,
    // 24, 0).addComposition(of(Wood, 1; TODO: Perhaps with IE integration or when
    // we have some utility stuf;
    net.minecraft.world.entity.monster.Blaze = AntimatterAPI.register(Material.class, new Material(Ref.ID, "blaze", 0xffc800,NONE))
            .asDust().addHandleStat(-10, -0.5F, of(Enchantments.FIRE_ASPECT, 1))
            .mats(of(Sulfur, 1, DarkAsh, 1/* , Magic, 1 */));
    Flint = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "flint", 0x002040, TextureSet.FLINT)).asDust(ROCK)
            .addTools(0.0F, 2.0F, 48, 1, of(Enchantments.FIRE_ASPECT, 1)).mats(of(SiliconDioxide, 1));
    PotassiumFeldspar = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "potassium_feldspar", 0x782828, FINE)).asDust()
            .mats(of(Potassium, 1, Aluminium, 1, Silicon, 3, Oxygen, 8));
    Biotite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "biotite", 0x141e14, METALLIC)).asDust()
            .mats(b -> b.put(Potassium, 1).put(Magnesium, 3).put(Aluminium, 3).put(Fluorine, 2).put(Silicon, 3)
                    .put(Oxygen, 10));
    VanadiumMagnetite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "vanadium_magnetite", 0x23233c, METALLIC)).asOre(true)
            .mats(of(Magnetite, 1, Vanadium, 1));
    Bastnasite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "bastnasite", 0xc86e2d, FINE)).asOre(true)
            .mats(of(Cerium, 1, Carbon, 1, Fluorine, 1, Oxygen, 3));
    Pentlandite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "pentlandite", 0xa59605, DULL)).asOre()
            .mats(of(Nickel, 9, Sulfur, 8));
    Spodumene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "spodumene", 0xbeaaaa, DULL)).asOre(true)
            .mats(of(Lithium, 1, Aluminium, 1, Silicon, 2, Oxygen, 6));
    Tantalite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tantalite", 0x915028, METALLIC)).asOre(true)
            .mats(of(Manganese, 1, Tantalum, 2, Oxygen, 6));
    Lepidolite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "lepidolite", 0xf0328c, FINE)).asOre(true)
            .mats(of(Potassium, 1, Lithium, 3, Aluminium, 4, Fluorine, 2, Oxygen, 10)); // TODO: Ore Ge;
    Glauconite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "glauconite", 0x82b43c, DULL)).asOre(true)
            .mats(of(Potassium, 1, Magnesium, 2, Aluminium, 4, Hydrogen, 2, Oxygen, 12)); // TODO: Ore Ge;
    Bentonite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "bentonite", 0xf5d7d2, ROUGH)).asOre(true).mats(b -> b
                    .put(Sodium, 1).put(Magnesium, 6).put(Silicon, 12).put(Hydrogen, 6).put(Water, 5).put(Oxygen, 36)); // TODO:
    // Ore
    // Ge;
    Pitchblende = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "pitchblende", 0xc8d200, DULL)).asOre(true)
            .mats(of(Uraninite, 3, Thorium, 1, Lead, 1));
    Malachite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "malachite", 0x055f05, DULL)).asOre(true)
            .mats(of(Copper, 2, Carbon, 1, Hydrogen, 2, Oxygen, 5));
    Barite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "barite", 0xe6ebff, DULL)).asOre(true)
            .mats(of(Barium, 1, Sulfur, 1, Oxygen, 4));
    Talc = AntimatterAPI.register(Material.class, new Material(Ref.ID, "talc", 0x5ab45a, DULL))
            .asOre(true).mats(of(Magnesium, 3, Silicon, 4, Hydrogen, 2, Oxygen, 12));
    Soapstone = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "soapstone", 0x5f915f, DULL)).asOre(true)
            .mats(of(Magnesium, 3, Silicon, 4, Hydrogen, 2, Oxygen, 12)); // TODO: Ore Ge;
    Concrete = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "concrete", 0x646464, ROUGH)).asDust(300).mats(of(Stone, 1)).asFluid();
    AntimonyTrioxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "antimony_trioxide", 0xe6e6f0, DULL)).asDust()
            .mats(of(Antimony, 2, Oxygen, 3));
    CupricOxide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cupric_oxide", 0x0f0f0f, DULL)).asDust()
            .mats(of(Copper, 1, Oxygen, 1));
    Ferrosilite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "ferrosilite", 0x97632a, DULL)).asDust()
            .mats(of(Iron, 1, Silicon, 1, Oxygen, 3));

    /**
     * Gems
     **/
    // CertusQuartz = AntimatterAPI.register(Material.class,
    // new Material(Ref.ID, "certus_quartz", 0xd2d2e6, QUARTZ).asGemBasic(false,
    // PLATE, ORE).addTools(5.0F, 32, 1; TODO: Only when AE2 is loade;
    Dilithium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "dilithium", 0xfffafa, DIAMOND)).asGemBasic(true);
    NetherStar = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "nether_star", 0xffffff, NONE)).asGemBasic(false)
            .addTools(3.5F, 6.0F, 3620, 4, of(Enchantments.SILK_TOUCH, 1)); // Made Nether Stars usabl;

    // Brittle Gems
    BlueTopaz = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "blue_topaz", 0x0000ff, GEM_H)).asGem(true).asOre(3, 7, true)
            .addTools(2.5F, 7.0F, 256, 3).mats(of(Aluminium, 2, Silicon, 1, Fluorine, 2, Hydrogen, 2, Oxygen, 6));
    Charcoal = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "charcoal", 0x644646, LIGNITE)).asGemBasic(false)
            .mats(of(Carbon, 1));
    CoalCoke = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "coal_coke", 0x8c8caa, LIGNITE)).asGemBasic(false);
    LigniteCoke = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "lignite_coke", 0x8c6464, LIGNITE)).asGemBasic(false);

    GreenSapphire = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "green_sapphire", 0x64c882, GEM_H)).asGem(true)
            .asOre(3, 7, true).addTools(2.0F, 7.0F, 256, 2).mats(of(Aluminium, 2, Oxygen, 3));
    Lazurite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "lazurite", 0x6478ff, LAPIS)).asGemBasic(false)
            .asOre(2, 5, true).mats(of(Aluminium, 6, Silicon, 6, Calcium, 8, Sodium, 8)); // TODO I think this is
    // needed;
    Ruby = AntimatterAPI.register(Material.class, new Material(Ref.ID, "ruby", 0xff6464, RUBY))
            .asGem(true).asOre(3, 7, true).addTools(2.0F, 7.0F, 256, 2).mats(of(Chrome, 1, Aluminium, 2, Oxygen, 3));
    BlueSapphire = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "blue_sapphire", 0x6464c8, GEM_V)).asGem(true)
            .asOre(3, 7, true).addTools(2.0F, 7.0F, 256, 2).mats(of(Aluminium, 2, Oxygen, 3));
    Sodalite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sodalite", 0x1414ff, LAPIS)).asGemBasic(false)
            .asOre(2, 5, true).mats(of(Aluminium, 3, Silicon, 3, Sodium, 4, Chlorine, 1)); // TODO I think this is
    // needed;
    Tanzanite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tanzanite", 0x4000c8, GEM_V)).asGem(true).asOre(3, 7, true)
            .addTools(2.0F, 7.0F, 256, 2).mats(of(Calcium, 2, Aluminium, 3, Silicon, 3, Hydrogen, 1, Oxygen, 13));
    Topaz = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "topaz", 0xff8000, GEM_H)).asGem(true).asOre(3, 7, true)
            .addTools(2.0F, 7.0F, 256, 2).mats(of(Aluminium, 2, Silicon, 1, Fluorine, 2, Hydrogen, 2, Oxygen, 6));
    Glass = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "glass", 0xfafafa, SHINY)).asDust(PLATE, LENS)
            .mats(of(SiliconDioxide, 1));
    Olivine = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "olivine", 0x96ff96, RUBY)).asGem(true).asOre(3, 7, true)
            .addTools(2.0F, 7.0F, 256, 2, of(Enchantments.SILK_TOUCH, 1))
            .mats(of(Magnesium, 2, Iron, 1, SiliconDioxide, 2));
    Opal = AntimatterAPI.register(Material.class, new Material(Ref.ID, "opal", 0x0000ff, RUBY))
            .asGem(true).asOre(3, 7, true).addTools(2.0F, 7.0F, 256, 2).mats(of(SiliconDioxide, 1));
    Amethyst = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "amethyst", 0xd232d2, RUBY)).asGem(true).asOre(3, 7, true)
            .addTools(2.0F, 7.0F, 256, 3).mats(of(SiliconDioxide, 4, Iron, 1));
    Phosphorus = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "phosphorus", 0xffff00, TextureSet.FLINT)).asGemBasic(false)
            .asOre(3, 7, true).mats(of(Calcium, 3, Phosphate, 2));
    RedGarnet = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "red_garnet", 0xc85050, GARNET)).asGemBasic(true)
            .asOre(3, 7, true).mats(of(Pyrope, 3, Almandine, 5, Spessartine, 8));
    YellowGarnet = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "yellow_garnet", 0xc8c850, GARNET)).asGemBasic(true)
            .asOre(3, 7, true).mats(of(Andradite, 5, Grossular, 8, Uvarovite, 3));
    // Monazite = AntimatterAPI.register(Material.class, new
    // Material(Ref.ID, "monazite", 0x324632, DIAMOND).asGemBasic(false,
    // ORE).addComposition(of(RareEarth, 1, Phosphate, 1));

    /**
     *
     **/
    Cinnabar = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cinnabar", 0x960000, ROUGH)).asOre(true)
            .mats(of(Mercury, 1, Sulfur, 1));

    /**
     * Metals
     **/
    AnnealedCopper = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "annealed_copper", 0xff7814, SHINY))
            .asMetal(1357, 0, PLATE, FOIL, ROD, WIRE_FINE, SCREW).mats(of(Copper, 1));
    BatteryAlloy = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "battery_alloy", 0x9c7ca0, DULL)).asMetal(295, 0, PLATE)
            .mats(of(Lead, 4, Antimony, 1));
    Brass = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "brass", 0xffb400, METALLIC)).asMetal(1170, 0, FRAME, ROD, PLATE)
            .mats(of(Zinc, 1, Copper, 3));
    Bronze = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "bronze", 0xff8000, METALLIC))
            .asMetal(1125, 0, GEAR, FRAME, ROTOR).addTools(1.5F, 6.5F, 182, 2, of(Enchantments.UNBREAKING, 1))
            .mats(of(Tin, 1, Copper, 3));
    Cupronickel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cupronickel", 0xe39680, METALLIC)).asMetal(1728, 0, PLATE)
            .mats(of(Copper, 1, Nickel, 1));
    Electrum = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "electrum", 0xffff64, SHINY))
            .asMetal(1330, 0, PLATE, FOIL, ROD, WIRE_FINE).addTools(1.0F, 13.0F, 48, 2, of(Enchantments.UNBREAKING, 3))
            .mats(of(Silver, 1, Gold, 1));
    Invar = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "invar", 0xb4b478, METALLIC)).asMetal(1700, 0, FRAME)
            .addTools(2.5F, 7.0F, 320, 2, of(Enchantments.BANE_OF_ARTHROPODS, 2)).mats(of(Iron, 2, Nickel, 1));
    Kanthal = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "kanthal", 0xc2d2df, METALLIC)).asMetal(1800, 1800)
            .addTools(2.5F, 6.0F, 64, 2, of(Enchantments.BANE_OF_ARTHROPODS, 1))
            .mats(of(Iron, 1, Aluminium, 1, Chrome, 1));
    Magnalium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "magnalium", 0xc8beff, DULL)).asMetal(870, 0, PLATE)
            .mats(of(Magnesium, 1, Aluminium, 2));
    Nichrome = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "nichrome", 0xcdcef6, METALLIC)).asMetal(2700, 2700)
            .addTools(2.0F, 6.0F, 81, 2, of(Enchantments.BANE_OF_ARTHROPODS, 3)).mats(of(Nickel, 4, Chrome, 1));
    NiobiumTitanium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "niobium_titanium", 0x1d1d29, DULL))
            .asMetal(4500, 4500, PLATE, FOIL, ROD, WIRE_FINE).mats(of(Nickel, 4, Chrome, 1));
    SolderingAlloy = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "soldering_alloy", 0xdcdce6, DULL))
            .asMetal(400, 400, PLATE, FOIL, ROD, WIRE_FINE).mats(of(Tin, 9, Antimony, 1));
    Steel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "steel", 0x808080, METALLIC))
            .asMetal(1811, 1000, PLATE, ROD, SCREW, BOLT, RING, GEAR, FRAME, ROTOR, GEAR_SMALL).addTools(Iron)
            .mats(of(Iron, 50, Carbon, 1));
    StainlessSteel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "stainless_steel", 0xc8c8dc, SHINY))
            .asMetal(1700, 1700, PLATE, ROD, SCREW, BOLT, RING, GEAR, FRAME, ROTOR, GEAR_SMALL).addTools(Steel)
            .mats(of(Iron, 6, Chrome, 1, Manganese, 1, Nickel, 1));
    Ultimet = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "ultimet", 0xb4b4e6, SHINY)).asMetal(2700, 2700, PLATE)
            .mats(of(Cobalt, 5, Chrome, 2, Nickel, 1, Molybdenum, 1));
    VanadiumGallium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "vanadium_gallium", 0x80808c, SHINY))
            .asMetal(4500, 4500, ROD, PLATE).mats(of(Vanadium, 3, Gallium, 1));
    WroughtIron = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "wrought_iron", 0xc8b4b4, METALLIC))
            .asMetal(1811, 0, PLATE, ROD, SCREW, BOLT, RING, GEAR, FRAME, ROTOR, GEAR_SMALL).addTools(Iron)
            .mats(of(Iron, 1)).asOre();
    YttriumBariumCuprate = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "yttrium_barium_cuprate", 0x504046, METALLIC))
            .asMetal(4500, 4500, PLATE, FOIL, ROD, WIRE_FINE).mats(of(Yttrium, 1, Barium, 2, Copper, 3, Oxygen, 7));
    SterlingSilver = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "sterling_silver", 0xfadce1, SHINY)).asMetal(1700, 1700)
            .addTools(3.0F, 10.5F, 96, 2, of(Enchantments.BLOCK_EFFICIENCY, 2)).mats(of(Copper, 1, Silver, 4));
    RoseGold = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "rose_gold", 0xffe61e, SHINY)).asMetal(1600, 1600, WIRE_FINE)
            .addTools(Gold, of(Enchantments.BLOCK_FORTUNE, 3, Enchantments.SMITE, 3)).mats(of(Copper, 1, Gold, 4));
    BlackBronze = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "black_bronze", 0x64327d, DULL)).asMetal(2000, 2000)
            .addTools(Bronze, of(Enchantments.SWEEPING_EDGE, 1)).mats(of(Gold, 1, Silver, 1, Copper, 3));
    BismuthBronze = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "bismuth_bronze", 0x647d7d, DULL)).asMetal(1100, 900, PLATE)
            .addTools(2.5F, Bronze.getToolSpeed() + 2.0F, 350, 2, of(Enchantments.BANE_OF_ARTHROPODS, 4))
            .mats(of(Bismuth, 1, Zinc, 1, Copper, 3));
    BlackSteel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "black_steel", 0x646464, METALLIC))
            .asMetal(1200, 1200, FRAME, PLATE).addTools(3.5F, 6.5F, 768, 2)
            .mats(of(Nickel, 1, BlackBronze, 1, Steel, 3));
    RedSteel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "red_steel", 0x8c6464, METALLIC)).asMetal(1300, 1300)
            .addTools(3.5F, 7.0F, 896, 2).mats(of(SterlingSilver, 1, BismuthBronze, 1, Steel, 2, BlackSteel, 4));
    BlueSteel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "blue_steel", 0x64648c, METALLIC)).asMetal(1400, 1400, FRAME)
            .addTools(3.5F, 7.5F, 1024, 2).mats(of(RoseGold, 1, Brass, 1, Steel, 2, BlackSteel, 4));
    // DamascusSteel = AntimatterAPI.register(Material.class,
    // new Material(Ref.ID, "damascus_steel", 0x6e6e6e, METALLIC)).asMetal(2500,
    // 1500).addTools(8.0F, 1280, 2).addComposition(of(Steel, 1); //TODO: Sorta a
    // fantasy meta;
    TungstenSteel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tungstensteel", 0x6464a0, METALLIC))
            .asMetal(3000, 3000, PLATE, ROD, SCREW, BOLT, RING, GEAR, FRAME, ROTOR, GEAR_SMALL).asOre()
            .addTools(4.0F, 8.0F, 2560, 4).mats(of(Steel, 1, Tungsten, 1));
    RedAlloy = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "red_alloy", 0xc80000, DULL))
            .asMetal(295, 0, PLATE, FOIL, ROD, WIRE_FINE).mats(of(Copper, 1,net.minecraft.world.level.redstone.Redstone, 4));
    CobaltBrass = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "cobalt_brass", 0xb4b4a0, METALLIC)).asMetal(1500, 0, GEAR)
            .addTools(2.5F, 8.0F, 256, 2).mats(of(Brass, 7, Aluminium, 1, Cobalt, 1));
    IronMagnetic = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "magnetic_iron", 0xc8c8c8, MAGNETIC)).asMetal(1811, 0, ROD)
            .addTools(Iron).mats(of(Iron, 1));
    SteelMagnetic = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "magnetic_steel", 0x808080, MAGNETIC))
            .asMetal(1000, 1000, ROD).addTools(Steel).mats(of(Steel, 1));
    NeodymiumMagnetic = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "magnetic_neodymium", 0x646464, MAGNETIC))
            .asMetal(1297, 1297, ROD).mats(of(Neodymium, 1));
    NickelZincFerrite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "nickel_zinc_ferrite", 0x3c3c3c, ROUGH)).asMetal(1500, 1500)
            .addTools(0.0F, 3.0F, 32, 1).mats(of(Nickel, 1, Zinc, 1, Iron, 4, Oxygen, 8));
    TungstenCarbide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tungsten_carbide", 0x330066, METALLIC)).asMetal(2460, 2460)
            .addTools(5.0F, 14.0F, 1280, 4).mats(of(Tungsten, 1, Carbon, 1));
    VanadiumSteel = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "vanadium_steel", 0xc0c0c0, METALLIC)).asMetal(1453, 1453)
            .addTools(3.0F, 5.0F, 1920, 3).mats(of(Vanadium, 1, Chrome, 1, Steel, 7));
    HSSG = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "hssg", 0x999900, METALLIC)).asMetal(4500, 4500, GEAR, FRAME)
            .addTools(3.8F, 10.0F, 4000, 3).mats(of(TungstenSteel, 5, Chrome, 1, Molybdenum, 2, Vanadium, 1));
    HSSE = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "hsse", 0x336600, METALLIC)).asMetal(5400, 5400, GEAR, FRAME)
            .addTools(4.2F, 10.0F, 5120, 4).mats(of(HSSG, 6, Cobalt, 1, Manganese, 1, Silicon, 1));
    HSSS = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "hsss", 0x660033, METALLIC)).asMetal(5400, 5400)
            .addTools(5.0F, 14.0F, 3000, 4).mats(of(HSSG, 6, Iridium, 2, Osmium, 1));
    Osmiridium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "osmiridium", 0x6464ff, METALLIC)).asMetal(3333, 2500, FRAME)
            .addTools(6.0F, 15.0F, 1940, 5).mats(of(Iridium, 3, Osmium, 1));
    Duranium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "duranium", 0xffffff, METALLIC)).asMetal(295, 0)
            .addHandleStat(620, -1.0F, of(Enchantments.SILK_TOUCH, 1)).addTools(6.5F, 16.0F, 5120, 5);
    Naquadah = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "naquadah", 0x323232, METALLIC)).asMetal(5400, 5400).asOre()
            .addHandleStat(102, 0.5F, of(Enchantments.BLOCK_EFFICIENCY, 2)).addTools(4.0F, 6.0F, 890, 4);
    NaquadahAlloy = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "naquadah_alloy", 0x282828, METALLIC)).asMetal(7200, 7200)
            .addTools(4.5F, 8.0F, 5120, 5);
    EnrichedNaquadah = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "enriched_naquadah", 0x323232, SHINY)).asMetal(4500, 4500)
            .asOre().addTools(5.0F, 6.0F, 1280, 4);
    Naquadria = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "naquadria", 0x1e1e1e, SHINY)).asMetal(9000, 9000);
    Tritanium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "tritanium", 0xffffff, SHINY)).asMetal(295, 0, FRAME)
            .addTools(9.0F, 15.0F, 9400, 6);
    Vibranium = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "vibranium", 0x00ffff, SHINY)).asMetal(295, 0, FRAME)
            .addTools(10.0F, 20.0F, 12240, 6);

    /**
     * Solids (Plastic Related Stuff)
     **/
    Polyethylene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "polyethylene", 0xc8c8c8, DULL)).asSolid(295, 0, PLATE)
            .asFluid()
            .addHandleStat(66, 0.5F).mats(of(Carbon, 1, Hydrogen, 2));
    SiliconRubber = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "siliconrubber", 0x9fadbb, NONE)).asFluid()
            .mats(of(Sulfur, 1, Polydimethylsiloxane, 9));
    Epoxid = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "epoxid", 0xc88c14, DULL)).asSolid(400, 0, PLATE)
            .addHandleStat(70, 1.5F).mats(of(Carbon, 2, Hydrogen, 4, Oxygen, 1));
    Silicone = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "silicone", 0xdcdcdc, DULL)).asSolid(900, 0, PLATE, FOIL)
            .addHandleStat(-40, 2.0F).mats(of(Carbon, 2, Hydrogen, 6, Oxygen, 1, Silicon, 1));
    Polycaprolactam = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "polycaprolactam", 0x323232, DULL)).asSolid(500, 0)
            .mats(of(Carbon, 6, Hydrogen, 11, Nitrogen, 1, Oxygen, 1));
    Polytetrafluoroethylene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "polytetrafluoroethylene", 0x646464, DULL))
            .asSolid(1400, 0, PLATE, FRAME).mats(of(Carbon, 2, Fluorine, 4));
    Rubber = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "rubber", 0x000000, SHINY)).asSolid(295, 0, PLATE, RING)
            .addHandleStat(11, 0.4F).mats(of(Carbon, 5, Hydrogen, 8));
    PolyphenyleneSulfide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "polyphenylene_sulfide", 0xaa8800, DULL))
            .asSolid(295, 0, PLATE, FOIL).mats(of(Carbon, 6, Hydrogen, 4, Sulfur, 1));
    Polystyrene = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "polystyrene", 0xbeb4aa, DULL)).asSolid(295, 0)
            .addHandleStat(3, 1.0F).mats(of(Carbon, 8, Hydrogen, 8));
    StyreneButadieneRubber = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "styrene_butadiene_rubber", 0x211a18, SHINY))
            .asSolid(295, 0, PLATE, RING).addHandleStat(66, 1.2F).mats(of(Styrene, 1, Butadiene, 3));
    PolyvinylChloride = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "polyvinyl_chloride", 0xd7e6e6, NONE))
            .asSolid(295, 0, PLATE, FOIL).addHandleStat(210, 0.5F).mats(of(Carbon, 2, Hydrogen, 3, Chlorine, 1));
    GalliumArsenide = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "gallium_arsenide", 0xa0a0a0, DULL)).asSolid(295, 1200)
            .mats(of(Arsenic, 1, Gallium, 1));
    EpoxidFiberReinforced = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "fiber_reinforced_epoxy_resin", 0xa07010, DULL))
            .asSolid(400, 0, PLATE).mats(of(Epoxid, 1));

    RedGranite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "red_granite", 0xff0080, ROUGH)).asDust(ROCK)
            .addHandleStat(74, 1.0F, of(Enchantments.UNBREAKING, 1))
            .mats(of(Aluminium, 2, PotassiumFeldspar, 1, Oxygen, 3));
    BlackGranite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "black_granite", 0x0a0a0a, ROUGH)).asDust(ROCK)
            .addHandleStat(74, 1.0F, of(Enchantments.UNBREAKING, 1)).mats(of(SiliconDioxide, 4, Biotite, 1));
    Marble = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "marble", 0xc8c8c8, NONE)).asDust(ROCK)
            .mats(of(Magnesium, 1, Calcite, 7));
    Komatiite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "komatiite", 0xbebe69, NONE)).asDust(ROCK)
            .mats(of(Olivine, 1, /* MgCO3, 2, */Flint, 6, DarkAsh, 3));
    Limestone = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "limestone", 0xe6c882, NONE)).asDust(ROCK)
            .mats(of(Calcite, 1));
    GreenSchist = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "green_schist", 0x69be69, NONE)).asDust(ROCK);
    BlueSchist = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "blue_schist", 0x0569be, NONE)).asDust(ROCK);
    Kimberlite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "kimberlite", 0x64460a, NONE)).asDust(ROCK);
    Quartzite = AntimatterAPI
            .register(Material.class, new Material(Ref.ID, "quartzite", 0xe6cdcd, QUARTZ)).asGemBasic(false, ROCK, ROD)
            .asOre(2, 5, true).mats(of(Silicon, 1, Oxygen, 2));

    /**
     * Ore Stones
     **/
    Lignite.asGemBasic(false)
            .asOreStone(0, 2, ORE_SMALL).mats(of(Carbon, 3, Water, 1));
    Salt.asOreStone(ORE_SMALL).mats(of(Sodium, 1, Chlorine, 1));
    RockSalt.asOreStone(ORE_SMALL)
            .mats(of(Potassium, 1, Chlorine, 1));
    Bauxite.asOreStone(ORE_SMALL)
            .mats(of(Rutile, 2, Aluminium, 16, Hydrogen, 10, Oxygen, 11));
    OilShale.asOreStone(ORE_SMALL);

    /**
     * Reference Materials
     **/
    Superconductor.asSolid(PLATE);
    public static void onMaterialEvent(MaterialEvent event){
        flags(event);
        antimatterMaterials(event);
        byproducts(event);



    }

    public static void byproducts(MaterialEvent event){
        event.setMaterial(Chalcopyrite).addByProduct(Pyrite, Cobalt, Cadmium, Gold);
        event.setMaterial(Sphalerite).addByProduct(YellowGarnet, Cadmium, Gallium, Zinc);
        event.setMaterial(Glauconite).addByProduct(Sodium, Aluminium, Iron);
        event.setMaterial(Bentonite).addByProduct(Aluminium, Calcium, Magnesium);
        event.setMaterial(Uraninite).addByProduct(Uranium, Thorium, Uranium235);
        event.setMaterial(Pitchblende).addByProduct(Thorium, Uranium, Lead);
        event.setMaterial(Galena).addByProduct(Sulfur, Silver, Lead);
        event.setMaterial(Lapis).addByProduct(Calcite, Pyrite);
        event.setMaterial(Pyrite).addByProduct(Sulfur, Phosphorus, Iron);
        event.setMaterial(Copper).addByProduct(Cobalt, Gold, Nickel);
        event.setMaterial(Nickel).addByProduct(Cobalt, Platinum, Iron);
        event.setMaterial(RedGarnet).addByProduct(Spessartine, Pyrope, Almandine);
        event.setMaterial(YellowGarnet).addByProduct(Andradite, Grossular, Uvarovite);
        event.setMaterial(Cooperite).addByProduct(Palladium, Nickel, Iridium);
        event.setMaterial(Cinnabar).addByProduct(Redstone, Sulfur, Glowstone);
        event.setMaterial(Tantalite).addByProduct(Manganese, Niobium, Tantalum);
        event.setMaterial(Pentlandite).addByProduct(Iron, Sulfur, Cobalt);
        event.setMaterial(Uranium).addByProduct(Lead, Uranium235, Thorium);
        event.setMaterial(Scheelite).addByProduct(Manganese, Molybdenum, Calcium);
        event.setMaterial(Tungstate).addByProduct(Manganese, Silver, Lithium);
        event.setMaterial(Bauxite).addByProduct(Grossular, Rutile, Gallium);
        event.setMaterial(Redstone).addByProduct(Cinnabar, RareEarth, Glowstone);
        event.setMaterial(Malachite).addByProduct(Copper, BrownLimonite, Calcite);
        event.setMaterial(YellowLimonite).addByProduct(Nickel, BrownLimonite, Cobalt);
        event.setMaterial(Andradite).addByProduct(YellowGarnet, Iron, Boron);
        event.setMaterial(Quartzite).addByProduct(Barite);
        event.setMaterial(BrownLimonite).addByProduct(Malachite, YellowLimonite);
        event.setMaterial(Neodymium).addByProduct(RareEarth);
        event.setMaterial(Bastnasite).addByProduct(Neodymium, RareEarth);
        event.setMaterial(Glowstone).addByProduct(Redstone, Gold);
        event.setMaterial(Zinc).addByProduct(Tin, Gallium);
        event.setMaterial(Tungsten).addByProduct(Manganese, Molybdenum);
        event.setMaterial(Iron).addByProduct(Nickel, Tin);
        event.setMaterial(Gold).addByProduct(Copper, Nickel);
        event.setMaterial(Tin).addByProduct(Iron, Zinc);
        event.setMaterial(Antimony).addByProduct(Zinc, Iron);
        event.setMaterial(Silver).addByProduct(Lead, Sulfur);
        event.setMaterial(Lead).addByProduct(Silver, Sulfur);
        event.setMaterial(Thorium).addByProduct(Uranium, Lead);
        event.setMaterial(Plutonium).addByProduct(Uranium, Lead);
        event.setMaterial(Electrum).addByProduct(Gold, Silver);
        event.setMaterial(Bronze).addByProduct(Copper, Tin);
        event.setMaterial(Brass).addByProduct(Copper, Zinc);
        event.setMaterial(Coal).addByProduct(Lignite, Thorium);
        event.setMaterial(Ilmenite).addByProduct(Iron, Rutile);
        event.setMaterial(Manganese).addByProduct(Chrome, Iron);
        event.setMaterial(BlueSapphire).addByProduct(Aluminium);
        event.setMaterial(Platinum).addByProduct(Nickel, Iridium);
        event.setMaterial(Emerald).addByProduct(Beryllium, Aluminium);
        event.setMaterial(Olivine).addByProduct(Pyrope, Magnesium);
        event.setMaterial(Chrome).addByProduct(Iron, Magnesium);
        event.setMaterial(Tetrahedrite).addByProduct(Antimony, Zinc);
        event.setMaterial(Magnetite).addByProduct(Iron, Gold);
        event.setMaterial(Basalt).addByProduct(Olivine, DarkAsh);
        event.setMaterial(VanadiumMagnetite).addByProduct(Magnetite, Vanadium);
        event.setMaterial(Spodumene).addByProduct(Aluminium, Lithium);
        event.setMaterial(Ruby).addByProduct(Chrome, RedGarnet);
        event.setMaterial(Phosphorus).addByProduct(Phosphate);
        event.setMaterial(Iridium).addByProduct(Platinum, Osmium);
        event.setMaterial(Pyrope).addByProduct(RedGarnet, Magnesium);
        event.setMaterial(Almandine).addByProduct(RedGarnet, Aluminium);
        event.setMaterial(Spessartine).addByProduct(RedGarnet, Manganese);
        event.setMaterial(Grossular).addByProduct(YellowGarnet, Calcium);
        event.setMaterial(Uvarovite).addByProduct(YellowGarnet, Chrome);
        event.setMaterial(Calcite).addByProduct(Andradite, Malachite);
        event.setMaterial(EnrichedNaquadah).addByProduct(Naquadah, Naquadria);
        event.setMaterial(Naquadah).addByProduct(EnrichedNaquadah);
        event.setMaterial(Pyrolusite).addByProduct(Manganese);
        event.setMaterial(Molybdenite).addByProduct(Molybdenum);
        event.setMaterial(Stibnite).addByProduct(Antimony);
        event.setMaterial(Garnierite).addByProduct(Nickel);
        event.setMaterial(Lignite).addByProduct(Coal);
        event.setMaterial(Diamond).addByProduct(Graphite);
        event.setMaterial(Beryllium).addByProduct(Emerald);
        event.setMaterial(Magnesite).addByProduct(Magnesium);
        event.setMaterial(Quartz).addByProduct(Netherrack);
        event.setMaterial(Steel).addByProduct(Iron);
        event.setMaterial(Graphite).addByProduct(Carbon);
        event.setMaterial(Netherrack).addByProduct(Sulfur);
        event.setMaterial(Flint).addByProduct(Obsidian);
        event.setMaterial(Cobaltite).addByProduct(Cobalt);
        event.setMaterial(Cobalt).addByProduct(Cobaltite);
        event.setMaterial(Sulfur).addByProduct(Sulfur);
        event.setMaterial(Saltpeter).addByProduct(Saltpeter);
        event.setMaterial(Endstone).addByProduct(Helium3);
        event.setMaterial(Osmium).addByProduct(Iridium);
        event.setMaterial(Magnesium).addByProduct(Olivine);
        event.setMaterial(Aluminium).addByProduct(Bauxite);
        event.setMaterial(Titanium).addByProduct(Almandine);
        event.setMaterial(Obsidian).addByProduct(Olivine);
        event.setMaterial(Ash).addByProduct(Carbon);
        event.setMaterial(DarkAsh).addByProduct(Carbon);
        event.setMaterial(Marble).addByProduct(Calcite);
        event.setMaterial(Clay).addByProduct(Clay);
        event.setMaterial(Cassiterite).addByProduct(Tin);
        event.setMaterial(BlackGranite).addByProduct(Biotite);
        event.setMaterial(RedGranite).addByProduct(PotassiumFeldspar);
        event.setMaterial(Phosphate).addByProduct(Phosphor);
        event.setMaterial(Phosphor).addByProduct(Phosphate);
        event.setMaterial(Tanzanite).addByProduct(Opal);
        event.setMaterial(Opal).addByProduct(Tanzanite);
        event.setMaterial(Amethyst).addByProduct(Amethyst);
        // event.setMaterial(Amber).addByProduct(Amber);
        event.setMaterial(Neutronium).addByProduct(Neutronium);
        event.setMaterial(Lithium).addByProduct(Lithium);
        event.setMaterial(Silicon).addByProduct(SiliconDioxide);
        event.setMaterial(Salt).addByProduct(RockSalt);
        event.setMaterial(RockSalt).addByProduct(Salt);
    }

    private static void flags(MaterialEvent event){
        GT4RMaterialTags.BATHING_PERSULFATE.add(Copper, Copper);
        GT4RMaterialTags.BATHING_PERSULFATE.add(Gold, Copper);
        GT4RMaterialTags.BATHING_PERSULFATE.add(Iron, Nickel);
        GT4RMaterialTags.BATHING_PERSULFATE.add(Sphalerite, Zinc);
        GT4RMaterialTags.BATHING_PERSULFATE.add(Tetrahedrite, Tetrahedrite);
        GT4RMaterialTags.BATHING_PERSULFATE.add(Tin, Zinc);
        GT4RMaterialTags.BATHING_PERSULFATE.add(Platinum, Nickel);
        GT4RMaterialTags.BATHING_MERCURY.add(Galena, Silver);
        GT4RMaterialTags.BATHING_MERCURY.add(Tungstate, Silver);
        GT4RMaterialTags.BATHING_MERCURY.add(Gold, Gold);
        GT4RMaterialTags.BATHING_MERCURY.add(Iridium, Platinum);
        GT4RMaterialTags.BATHING_MERCURY.add(Copper, Gold);
        GT4RMaterialTags.BATHING_MERCURY.add(Platinum, Platinum);
        GT4RMaterialTags.ELEC30.add(Charcoal, Prismarine, Salt, RockSalt, Quartzite);
        GT4RMaterialTags.ELEC60.add(Cassiterite, SodiumSulfide, Sapphire, SiliconDioxide, Methane, Pyrite, Sphalerite, NitrogenDioxide, Phosphate, Magnesite);
        GT4RMaterialTags.ELEC90.add(Calcite, EnderPearl, SulfuricAcid, RedGranite, Saltpeter, Chromite, SodiumPersulfate, Glyceryl, Ruby, Olivine, Galena, Tungstate/* CalciumCarbonate*/);
        GT4RMaterialTags.ELEC120.add(Emerald, Grossular, Clay, StainlessSteel, Sodalite, Bauxite, Obsidian, Pyrope, Uvarovite, Almandine, Andradite, Lazurite, Spessartine, PotassiumFeldspar, Biotite);
        GT4RMaterialTags.ROCK_CUTTER.add(Diamond, Ruby, Sapphire, NetherizedDiamond, Amethyst);

        GT4RMaterialTags.CABINET.add(Iron, Aluminium, WroughtIron, Brass, Cupronickel, Electrum, Gold, Silver, Magnalium, Platinum, Osmium);
        GT4RMaterialTags.CABINET.all().forEach(m -> event.setMaterial(m).flags(ROD));
        GT4RMaterialTags.WORKBENCH.add(Bronze, Iron, Aluminium);
        GT4RMaterialTags.CHARGING_WORKBENCH.add(Iron, Aluminium);
        GT4RMaterialTags.LOCKER.add(Iron, Aluminium);
        GT4RMaterialTags.CHARGING_LOCKER.add(Iron, Aluminium);
        GT4RMaterialTags.SEMIFLUID.add(Biomass, Creosote, FishOil, Oil, SeedOil);
        MaterialTags.FLINT.add(Flint);

        ELEC.add(GT4RMaterialTags.ELEC30.all().toArray(new Material[0]));
        ELEC.add(GT4RMaterialTags.ELEC60.all().toArray(new Material[0]));
        ELEC.add(GT4RMaterialTags.ELEC90.all().toArray(new Material[0]));
        ELEC.add(GT4RMaterialTags.ELEC120.all().toArray(new Material[0]));
    }

    private static void antimatterMaterials(MaterialEvent event){
        event.setMaterial(Data.Redstone).mats(of(Silicon, 1, Pyrite, 5, Ruby, 1, Mercury, 3)).setOreMulti(5).setSmeltingMulti(5)
                .addByProduct(Cinnabar, RareEarth, Glowstone);
        event.setMaterial(Prismarine).mats(of(Potassium, 2, Oxygen, 8, Manganese, 1, Silicon, 5));
        event.setMaterial(Basalt).mats(of(Olivine, 1, Calcite, 3, Flint, 8, DarkAsh, 4));
        event.setMaterial(Lapis).mats(of(Lazurite, 12, Sodalite, 2, Pyrite, 1, Calcite, 1)).setOreMulti(6)
                .addByProduct(Lazurite, Sodalite, Pyrite);
        event.setMaterial(EnderEye).mats(of(EnderPearl, 1, Blaze, 1));
        event.setMaterial(EnderPearl).mats(of(Beryllium, 1, Potassium, 4, Nitrogen, 5, Chlorine, 6));
        event.setMaterial(Diamond).mats(of(Carbon, 128)).addByProduct(Carbon/*Graphite*/);
        event.setMaterial(Emerald).mats(of(Beryllium, 3, Aluminium, 2, Silicon, 3, Oxygen, 18)).addByProduct(Beryllium, Aluminium);
        event.setMaterial(Coal).mats(of(Carbon, 2));
        event.setMaterial(Iron).flags(PLATE, ROD).addByProduct(Nickel, Tin);
        event.setMaterial(Gold).flags(GEAR).addByProduct(Copper, Nickel);
        event.setMaterial(Copper).flags(PLATE, ROD, GEAR).addByProduct(Gold, Nickel);
        event.setMaterial(Water).mats(of(Hydrogen, 2, Oxygen, 1));
        event.setMaterial(Flint).setAllowedTypes(PICKAXE, AXE, SHOVEL, SWORD, HOE, MORTAR, KNIFE).mats(of(SiliconDioxide, 1));
        event.setMaterial(Wood).mats(of(Carbon, 1, Oxygen, 1, Hydrogen, 1));
        event.setMaterial(Blaze).mats(of(Sulfur, 1, DarkAsh, 1/*, Magic, 1*/));
        event.setMaterial(Charcoal).mats(of(Carbon, 1));
        event.setMaterial(Lava).mats(of(Copper, 1, Tin, 1, Gold, 1, Silver, 1, Tungsten, 1));
        event.setMaterial(Granite).mats(of(Aluminium, 2, Flint, 1, Clay, 1));
        event.setMaterial(Glowstone).mats(of(Redstone, 8, Gold, 8, Helium, 1));
        event.setMaterial(Diorite).mats(of(Nickel, 1));
        event.setMaterial(NetheriteScrap).addByProduct(Quartz);
        event.setMaterial(Basalt).addByProduct(Olivine, DarkAsh);
        event.setMaterial(Netherrack).addByProduct(Sulfur);
        event.setMaterial(Flint).addByProduct(Obsidian);
        event.setMaterial(Endstone).addByProduct(Helium3);
    }
}
