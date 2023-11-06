package muramasa.gregtech.data;

import io.github.gregtechintergalactical.gtcore.GTCore;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.TextureSet;
import muramasa.gregtech.GTIRef;

import static muramasa.antimatter.material.Element.*;
import static muramasa.antimatter.material.TextureSet.*;

public class Materials {
    //TODO add Zincite, chromium dioxide(mass multi=3), niobium nitride, nitro carbon, wollastonite, kyanite, chromite, pyrochlore, gypsum,
    // dymethylamine, mirabilite, dolomite, borax, kaolinite, asbestos, glycerol, chlorobenzene, trona, Pollucite, Fullers Earth, alunite, mica, vermiculate, zeolite


    /**
     *** PSE (No Isotopes)
     **/

    public static Material Hydrogen = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hydrogen", 0x0000ff, NONE, H));
    public static Material Helium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "helium", 0xffff00, NONE, He));
    public static Material Lithium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "lithium", 0xe1dcff, DULL, Li));
    public static Material Beryllium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "beryllium", 0x64b464, METALLIC, Be));
    public static Material Boron = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "boron", 0xfafafa, DULL, B));
    public static Material Carbon = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "carbon", 0x141414, DULL, C));
    public static Material Nitrogen = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "nitrogen", 0x0096c8, NONE, N));
    public static Material Oxygen = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "oxygen", 0x0064c8, NONE, O));
    public static Material Fluorine = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "fluorine", 0xffffff, NONE, F));
    public static Material Neon = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "neon", 0xffff00, NONE, Ne));
    public static Material Sodium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sodium", 0x000096, METALLIC, Na));
    public static Material Magnesium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "magnesium", 0xffc8c8, METALLIC, Mg));
    public static Material Aluminium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "aluminium", 0x80c8f0, DULL, Al));
    public static Material Silicon = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "silicon", 0x3c3c50, METALLIC, Si));
    public static Material Phosphor = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "phosphor", 0xffff00, DULL, P));
    public static Material Sulfur = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sulfur", 0xc8c800, DULL, S));
    public static Material Chlorine = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "chlorine", 0x00ffff, NONE, Cl));
    public static Material Argon = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "argon", 0xff00f0, NONE, Ar));
    public static Material Potassium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "potassium", 0xfafafa, METALLIC, K));
    public static Material Calcium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "calcium", 0xfff5f5, METALLIC, Ca));
    public static Material Titanium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "titanium", 0xdca0f0, METALLIC, Ti));
    public static Material Vanadium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "vanadium", 0x323232, METALLIC, V));
    public static Material Chromium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "chromium", 0xffe6e6, SHINY, Cr));
    public static Material Manganese = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "manganese", 0xfafafa, DULL, Mn));
    public static Material Cobalt = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "cobalt", 0x5050fa, METALLIC, Co));
    public static Material Nickel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "nickel", 0xc8c8fa, METALLIC, Ni));
    public static Material Zinc = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "zinc", 0xfaf0f0, METALLIC, Zn));
    public static Material Gallium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "gallium", 0xdcdcff, SHINY, Ga));
    public static Material Germanium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "germanium", 0xb2a57b, SHINY, Ge));
    public static Material Arsenic = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "arsenic", 0xa6a586, SHINY, As));
    public static Material Selenium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "selenium", 0xb18bd6, SHINY, Se));
    public static Material Krypton = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "krypton", 0xffffff, DULL, Kr));
    public static Material Rubidium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "rubidium", 0x6e6a61, SHINY, Ru));
    public static Material Strontium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "strontium", 0xd0c49e, SHINY, Sr));
    public static Material Yttrium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "yttrium", 0xdcfadc, METALLIC, Y));
    public static Material Zirconium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "zirconium", 0xeee7d7, SHINY, Zr));
    public static Material Niobium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "niobium", 0xbeb4c8, METALLIC, Nb));
    public static Material Molybdenum = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "molybdenum", 0xb4b4dc, SHINY, Mo));
    public static Material Technetium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "technetium", 0xa3a09b, METALLIC, Tc));
    public static Material Ruthenium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "ruthenium", 0x9e9a9e, SHINY, Ru));
    public static Material Rhodium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "rhodium", 0x797665, SHINY, Rh));
    public static Material Palladium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "palladium", 0x808080, SHINY, Pd));
    public static Material Silver = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "silver", 0xdcdcff, SHINY, Ag));
    public static Material Cadmium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "cadmium", 0x32323c, SHINY, Cd));
    public static Material Indium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "indium", 0x400080, METALLIC, In));
    public static Material Tin = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "tin", 0xdcdcdc, DULL, Sn));
    public static Material Antimony = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "antimony", 0xdcdcf0, SHINY, Sb));
    public static Material Tellurium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "tellurium", 0xc1bbc9, SHINY, Te));
    public static Material Iodine = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "iodine", 0xbd4eaa, DULL, I));
    public static Material Xenon = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "xenon", 0xffff00, NONE, Xe));
    public static Material Caesium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "caesium", 0x6c5f3f, SHINY, Cs));
    public static Material Barium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "barium", 0x818ca8, METALLIC, Ba));
    public static Material Lanthanum = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "lanthanum", 0x807e65, METALLIC, La));
    public static Material Cerium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "cerium", 0x8390B2, METALLIC, Ce));
    public static Material Praseodymium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "praseodymium", 0xadac90, METALLIC, Pr));
    public static Material Neodymium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "neodymium", 0x646464, METALLIC, Nd));
    public static Material Promethium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "promethium", 0x4c3d39, SHINY, Pm));
    public static Material Samarium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "samarium", 0xeef2d7, SHINY, Sm));
    public static Material Europium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "europium", 0xc7ae5c, SHINY, Eu));
    public static Material Gadolinium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "gadolinium", 0x86837e, SHINY, Gd));
    public static Material Terbium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "terbium", 0x87897e, METALLIC, Tb));
    public static Material Dysprosium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "dysprosium", 0xcfd2b7, METALLIC, Dy));
    public static Material Holmium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "holmium", 0x9b9d88, METALLIC, Ho));
    public static Material Erbium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "erbium", 0xa8a6b4, SHINY, Er));
    public static Material Thulium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "thulium", 0xa39e9B, SHINY, Tm));
    public static Material Ytterbium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "ytterbium", 0xc1cac5, SHINY, Yb));
    public static Material Lutetium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "lutetium", 0xe1e4dd, SHINY, Lu));
    public static Material Hafnium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hafnium", 0xa29791, SHINY, Hf));
    public static Material Tantalum = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "tantalum", 0x9da0a5, SHINY, Ta));
    public static Material Tungsten = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "tungsten", 0x323232, METALLIC, W));
    public static Material Rhenium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "rhenium", 0x61615f, SHINY, Re));
    public static Material Osmium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "osmium", 0x3232ff, METALLIC, Os));
    public static Material Iridium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "iridium", 0xf0f0f5, DULL, Ir));
    public static Material Platinum = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "platinum", 0x64b4fa, SHINY, Pt));
    public static Material Mercury = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "mercury", 0xffdcdc, SHINY, Hg));
    public static Material Thallium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "thallium", 0xB6B6D2, SHINY, Tl));
    public static Material Lead = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "lead", 0x8c648c, DULL, Pb));
    public static Material Bismuth = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "bismuth", 0x64a0a0, METALLIC, Bi));
    public static Material Polonium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "polonium", 0x707646, SHINY, Po));
    public static Material Astatine = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "astatine", 0x140E14, SHINY, At));
    public static Material Radon = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "radon", 0xff00ff, NONE, Rn));
    public static Material Francium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "francium", 0xaaaaaa, RAD, Fr));
    public static Material Radium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "radium", 0xf1bd3c, RAD, Ra));
    public static Material Actinium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "actinium", 0xb8c5f1, RAD, Ac));
    public static Material Thorium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "thorium", 0x001e00, RAD, Th)).setDisplayNameString("Thorium 232");
    public static Material Protactinium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "protactinium", 0x8a735a, RAD, Pa));
    public static Material Uranium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "uranium", 0x32f032, RAD, U)).setDisplayNameString("Uranium 238");
    public static Material Neptunium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "neptunium", 0x203f64, RAD, Np));
    public static Material Plutonium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "plutonium", 0xf03232, RAD, Pu));
    public static Material Americium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "americium", 0xc8c8c8, RAD, Am));
    public static Material Curium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "curium", 0x664540, RAD, Cm));
    public static Material Berkelium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "berkelium", 0x88490f, RAD, Bk));
    public static Material Californium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "californium", 0xa78100, RAD, Cf));
    public static Material Einsteinium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "einsteinium", 0xaa8400, RAD, Es));
    public static Material Fermium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "fermium", 0x7b3cab, RAD, Fm));
    public static Material Mendelevium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "mendelevium", 0x183dab, RAD, Md));

    /**
     *** Isotopes
     **/
     
    public static Material Deuterium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "deuterium", 0xffff00, NONE, D));
    public static Material Tritium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "tritium", 0xff0000, METALLIC, T));
    public static Material Helium3 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "helium_3", 0xffffff, NONE, He3));
    //public static Material Thallium207 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "thallium_207", 0xB6B6D2, METALLIC, Tl207));
    //public static Material Thallium209 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "thallium_209", 0xB6B6D2, METALLIC, Tl209));
    //public static Material Thallium210 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "thallium_210", 0xB6B6D2, METALLIC, Tl210));
    //public static Material Bismuth211 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "bismuth_211", 0x64a0a0, METALLIC, Bi211));
    //public static Material Bismuth213 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "bismuth_213", 0x64a0a0, METALLIC, Bi213));
    //public static Material Bismuth214 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "bismuth_214", 0x64a0a0, METALLIC, Bi214));
    //public static Material Bismuth215 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "bismuth_215", 0x64a0a0, METALLIC, Bi215));
    public static Material Cobalt60 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "cobalt_60", 0x5a5afa, RAD, Co60));
    //public static Material Polonium211 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "polonium_211", 0x707646, METALLIC, Po211));
    //public static Material Polonium213 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "polonium_213", 0x707646, METALLIC, Po213));
    //public static Material Polonium214 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "polonium_214", 0x707646, METALLIC, Po214));
    //public static Material Polonium215 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "polonium_215", 0x707646, METALLIC, Po215));
    //public static Material Polonium216 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "polonium_216", 0x707646, METALLIC, Po216));
    //public static Material Polonium218 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "polonium_218", 0x707646, METALLIC, Po218));
    //public static Material Astatine215 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "astatine_215", 0x140E14, METALLIC, At215));
    //public static Material Astatine217 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "astatine_217", 0x140E14, METALLIC, At217));
    //public static Material Astatine218 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "astatine_218", 0x140E14, METALLIC, At218));
    //public static Material Astatine219 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "astatine_219", 0x140E14, METALLIC, At219));
    //public static Material Radon217 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "radon_217", 0xff00ff, NONE, Rn217));
    //public static Material Radon218 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "radon_218", 0xff00ff, NONE, Rn218));
    //public static Material Radon219 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "radon_219", 0xff00ff, NONE, Rn219));
    //public static Material Radon220 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "radon_220", 0xff00ff, NONE, Rn220));
    //public static Material Radon222 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "radon_222", 0xff00ff, NONE, Rn222));
    //public static Material Francium221 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "francium_221", 0xAAAAAA, METALLIC, Fr221));
    //public static Material Francium223 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "francium_223", 0xAAAAAA, METALLIC, Fr223));
    //public static Material Radium221 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "radium_221", 0xF1BD3C, NONE, Ra221));
    //public static Material Radium223 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "radium_223", 0xF1BD3C, NONE, Ra223));
    //public static Material Radium224 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "radium_224", 0xF1BD3C, NONE, Ra224));
    //public static Material Radium225 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "radium_225", 0xF1BD3C, NONE, Ra225));
    //public static Material Radium226 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "radium_226", 0xF1BD3C, NONE, Ra226));
    //public static Material Radium228 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "radium_228", 0xF1BD3C, NONE, Ra228));
    //public static Material Actinium225 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "actinium_225", 0xB8C5F1, METALLIC, Ac225));
    //public static Material Actinium227 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "actinium_227", 0xB8C5F1, METALLIC, Ac227));
    //public static Material Actinium228 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "actinium_228", 0xB8C5F1, METALLIC, Ac228));
    //public static Material Thorium227 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "thorium_227", 0x001e00, METALLIC, Th227));
    //public static Material Thorium228 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "thorium_228", 0x001e00, METALLIC, Th228));
    //public static Material Thorium229 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "thorium_229", 0x001e00, METALLIC, Th229));
    public static Material Thorium230 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "thorium_230", 0x001400, RAD, Th230));
    //public static Material Thorium231 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "thorium_231", 0x001e00, METALLIC, Th231));
    //public static Material Thorium233 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "thorium_233", 0x001e00, METALLIC, Th233));
    //public static Material Thorium234 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "thorium_234", 0x001e00, METALLIC, Th234));
    //public static Material Protactinium231 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "protactinium_231", 0x8A735A, METALLIC, Pa231));
    //public static Material Protactinium232 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "protactinium_232", 0x8A735A, METALLIC, Pa232));
    //public static Material Protactinium233 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "protactinium_233", 0x8A735A, METALLIC, Pa233));
    //public static Material Protactinium234 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "protactinium_234", 0x8A735A, METALLIC, Pa234));
    public static Material Uranium233 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "uranium_233", 0x46fa32, RAD, U233));
    public static Material Uranium235 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "uranium_235", 0x46fa46, RAD, U235));
    //public static Material Neptunium236 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "neptunium_236", 0x203F64, METALLIC, Np236));
    //public static Material Neptunium237 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "neptunium_237", 0x203F64, METALLIC, Np237));
    //public static Material Neptunium238 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "neptunium_238", 0x203F64, METALLIC, Np238));
    //public static Material Neptunium239 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "neptunium_239", 0x203F64, METALLIC, Np239));
    //public static Material Neptunium240 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "neptunium_240", 0x203F64, METALLIC, Np240));
    //public static Material Plutonium236 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "plutonium_236", 0xC32929, METALLIC, Pu236));
    //public static Material Plutonium238 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "plutonium_238", 0xfa1e1e, METALLIC, Pu238));
    public static Material Plutonium239 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "plutonium_239", 0xeb3232, RAD, Pu239));
    //public static Material Plutonium240 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "plutonium_240", 0xeb1e1e, METALLIC, Pu240));
    public static Material Plutonium241 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "plutonium_241", 0xf54646, RAD, Pu241));
    //public static Material Plutonium242 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "plutonium_242", 0xC32929, METALLIC, Pu242));
    public static Material Plutonium243 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "plutonium_243", 0xfa4646, RAD, Pu243));
    //public static Material Plutonium246 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "plutonium_246", 0xC32929, METALLIC, Pu246));
    public static Material Americium241 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "americium_241", 0xd2d2d2, RAD, Am241));
    public static Material Americium242 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "americium_242", 0xd2d2d2, RAD, Am242));
    //public static Material Americium244 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "americium_244", 0xA2A2A2, METALLIC, Am244));
    //public static Material Americium245 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "americium_245", 0xA2A2A2, METALLIC, Am245));
    //public static Material Americium246 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "americium_246", 0xA2A2A2, METALLIC, Am246));
    //public static Material Curium242 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "curium_242", 0x664540, METALLIC, Cm242));
    //public static Material Curium244 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "curium_244", 0x664540, METALLIC, Cm244));
    //public static Material Curium245 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "curium_245", 0x664540, METALLIC, Cm245));
    //public static Material Curium246 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "curium_246", 0x664540, METALLIC, Cm246));
    //public static Material Curium247 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "curium_247", 0x664540, METALLIC, Cm247));
    //public static Material Curium248 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "curium_248", 0x664540, METALLIC, Cm248));
    //public static Material Curium249 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "curium_249", 0x664540, METALLIC, Cm249));
    //public static Material Curium250 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "curium_250", 0x664540, METALLIC, Cm250));
    //public static Material Berkelium248 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "berkelium_248", 0x88490F, METALLIC, Bk248));
    //public static Material Berkelium249 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "berkelium_249", 0x88490F, METALLIC, Bk249));
    //public static Material Berkelium250 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "berkelium_250", 0x88490F, METALLIC, Bk250));
    //public static Material Berkelium251 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "berkelium_251", 0x88490F, METALLIC, Bk251));
    //public static Material Californium248 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "californium_248", 0xA78100, METALLIC, Cf248));
    //public static Material Californium249 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "californium_249", 0xA78100, METALLIC, Cf249));
    //public static Material Californium250 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "californium_250", 0xA78100, METALLIC, Cf250));
    //public static Material Californium251 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "californium_251", 0xA78100, METALLIC, Cf251));
    //public static Material Californium252 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "californium_252", 0xA78100, METALLIC, Cf252));
    //public static Material Californium253 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "californium_253", 0xA78100, METALLIC, Cf253));
    //public static Material Californium254 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "californium_254", 0xA78100, METALLIC, Cf254));
    //public static Material Californium255 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "californium_255", 0xA78100, METALLIC, Cf255));
    //public static Material Einsteinium253 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "einsteinium_253", 0xAA8400, METALLIC, Es253));
    //public static Material Einsteinium254 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "einsteinium_254", 0xAA8400, METALLIC, Es254));
    //public static Material Einsteinium255 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "einsteinium_255", 0xAA8400, METALLIC, Es255));
    //public static Material Einsteinium256 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "einsteinium_256", 0xAA8400, METALLIC, Es256));
    //public static Material Fermium255 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "fermium_255", 0x7B3CA8, METALLIC, Fm255));
    //public static Material Fermium256 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "fermium_256", 0x7B3CA8, METALLIC, Fm256));
    //public static Material Fermium257 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "fermium_257", 0x7B3CA8, METALLIC, Fm257));
    //public static Material Fermium258 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "fermium_258", 0x7B3CA8, METALLIC, Fm258));
    //public static Material Fermium259 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "fermium_259", 0x7B3CA8, METALLIC, Fm259));
    //public static Material Fermium260 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "fermium_260", 0x7B3CA8, METALLIC, Fm260));
    //public static Material Mendelevium259 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "mendelevium_259", 0x183DAB, METALLIC, Md259));
    //public static Material Mendelevium260 = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "mendelevium_260", 0x183DAB, METALLIC, Md260));

    /**
     *** Solids
     **/

    /**
     ** Metals
     **/

    public static Material AnnealedCopper = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "annealed_copper", 0xff7814, SHINY));
    public static Material BatteryAlloy = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "battery_alloy", 0x9c7ca0, DULL));
    public static Material BismuthBronze = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "bismuth_bronze", 0x647d7d, DULL));
    public static Material BlackBronze = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "black_bronze", 0x64327d, DULL));
    public static Material BlackSteel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "black_steel", 0x646464, METALLIC));
    public static Material BlueSteel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "blue_steel", 0x64648c, METALLIC));
    public static Material Brass = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "brass", 0xffb400, METALLIC));
    public static Material Bronze = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "bronze", 0xff8000, METALLIC));
    public static Material CobaltBrass = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "cobalt_brass", 0xb4b4a0, METALLIC));
    public static Material Cupronickel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "cupronickel", 0xe39680, METALLIC));
    public static Material Duranium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "duranium", 0xffffff, METALLIC));
    public static Material Electrum = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "electrum", 0xffff64, SHINY));
    public static Material EnrichedNaquadah = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "enriched_naquadah", 0x323232, SHINY));
    public static Material HSSE = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hsse", 0x336600, METALLIC)).setDisplayNameString("HSS-E");
    public static Material HSSG = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hssg", 0x999900, METALLIC)).setDisplayNameString("HSS-G");
    public static Material HSSS = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hsss", 0x660033, METALLIC)).setDisplayNameString("HSS-S");
    public static Material Invar = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "invar", 0xb4b478, METALLIC));
    public static Material IronMagnetic = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "magnetic_iron", 0xc8c8c8, MAGNETIC)).setMassMultiplierAndDivider(51, 50);
    public static Material Kanthal = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "kanthal", 0xc2d2df, METALLIC));
    public static Material Magnalium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "magnalium", 0xc8beff, DULL));
    public static Material Naquadah = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "naquadah", 0x323232, METALLIC, Naq));
    public static Material NaquadahAlloy = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "naquadah_alloy", 0x282828, METALLIC));
    public static Material Naquadria = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "naquadria", 0x1e1e1e, SHINY));
    public static Material NeodymiumMagnetic = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "magnetic_neodymium", 0x646464, MAGNETIC)).setMassMultiplierAndDivider(51, 50);
    public static Material Neutronium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "neutronium", 0xfafafa, DULL, Nt));
    public static Material Nichrome = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "nichrome", 0xcdcef6, METALLIC));
    public static Material NickelZincFerrite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "nickel_zinc_ferrite", 0x3c3c3c, ROUGH));
    public static Material NiobiumTitanium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "niobium_titanium", 0x1d1d29, DULL));
    public static Material Osmiridium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "osmiridium", 0x6464ff, METALLIC));
    public static Material RedAlloy = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "red_alloy", 0xc80000, DULL)).setMassMultiplierAndDivider(5, 4);
    public static Material RedSteel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "red_steel", 0x8c6464, METALLIC));
    public static Material RoseGold = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "rose_gold", 0xffe61e, SHINY));
    public static Material SolderingAlloy = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "soldering_alloy", 0xdcdce6, DULL));
    public static Material Steel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "steel", 0x808080, METALLIC)).setMassMultiplierAndDivider(51, 50);
    public static Material SteelMagnetic = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "magnetic_steel", 0x808080, MAGNETIC)).setMassMultiplierAndDivider(51, 50);
    public static Material SterlingSilver = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sterling_silver", 0xfadce1, SHINY));
    public static Material StainlessSteel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "stainless_steel", 0xc8c8dc, SHINY));
    public static Material TinAlloy = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "tin_alloy", 0x9fadbb, NONE));
    public static Material Tritanium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "tritanium", 0xffffff, SHINY));
    public static Material TungstenCarbide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "tungsten_carbide", 0x330066, METALLIC));
    public static Material TungstenSteel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "tungstensteel", 0x6464a0, METALLIC));
    public static Material Ultimet = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "ultimet", 0xb4b4e6, SHINY));
    public static Material VanadiumGallium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "vanadium_gallium", 0x80808c, SHINY));
    public static Material VanadiumSteel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "vanadium_steel", 0xc0c0c0, METALLIC));
    public static Material Vibranium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "vibranium", 0x00ffff, SHINY));
    public static Material WroughtIron = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "wrought_iron", 0xc8b4b4, METALLIC));
    public static Material YttriumBariumCuprate = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "yttrium_barium_cuprate", 0x504046, METALLIC));

    /**
     ** Dusts
     **/

    public static Material AluminiumHydroxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID,"aluminium_hydroxide", 0xbebec8, DULL));
    public static Material AluminiumTrichloride = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "aluminium_trichloride", 0xf0d77d, FINE));
    public static Material Aluminosilicate = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "aluminosilicate", 0xbfbdb0, FINE));
    public static Material AmmoniumChloride = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "ammonium_chloride", 0xffffff, DULL));
    public static Material AntimonyTrioxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "antimony_trioxide", 0xe6e6f0, DULL));
    public static Material ArsenicTrioxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "arsenic_trioxide", 0xffffff, SHINY));
    public static Material Ash = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "ash", 0x969696, DULL));
    public static Material BenzoylChloride = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "benzoyl_chloride", 0xf7f5eb, NONE));
    public static Material Biotite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "biotite", 0x141e14, METALLIC));
    public static Material BorosilicateGlass = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "borosilicate_glass", 0xfafafa, NONE));
    public static Material Brick = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "brick", 0x9b5643, ROUGH));
    public static Material Calcite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "calcite", 0xfae6dc, DULL));
    public static Material CalciumChloride = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "calcium_chloride", 0xebebfa, DULL));
    public static Material CalciumSulfate = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "calcium_sulfate", 0xf0dcd2, DULL));
    public static Material Clay = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "clay", 0xc8c8dc, ROUGH));
    public static Material CobaltOxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "cobalt_oxide", 0x668000, DULL));
    public static Material Concrete = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "concrete", 0x646464, ROUGH));
    public static Material CupricOxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "cupric_oxide", 0x0f0f0f, DULL));
    public static Material DarkAsh = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "dark_ash", 0x323232, DULL)).setMassMultiplierAndDivider(2, 1);
    public static Material DialuminiumTrioxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "dialuminium_trioxide", 0xfaf6e6, FINE));
    public static Material Dibenzene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "dibenzene", 0xfaf0c8, FINE));
    public static Material DibenzoylPeroxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "dibenzoyl_peroxide", 0xf7f5eb, FINE));
    public static Material Dichloroethane = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "dichloroethane", 0xf8f6fc, NONE));
    public static Material Energium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "energium", 0xe81e21, NONE));
    public static Material FerricChloride = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "ferric_chloride", 0xb4b478, METALLIC));
    public static Material FerriteMixture = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "ferrite_mixture", 0xb4b4b4, METALLIC));
    public static Material Ferrosilite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "ferrosilite", 0x97632a, DULL));
    public static Material Fireclay = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "fireclay", 0xada09b, ROUGH));
    public static Material Fluorite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "fluorite", 0xFFB98C, NONE));
    public static Material GelledToluene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "gelled_toluene", 0xeeeeee, NONE));
    public static Material Graphene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "graphene", 0x808080, DULL));
    public static Material IndiumGalliumPhosphide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "indium_gallium_phosphide", 0x570b79, NONE));
    public static Material IridiumSodiumOxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "iridium_sodium_oxide", 0xffffff, NONE));
    public static Material LithiumChloride = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "lithium_chloride", 0xdedefa, DULL));
    public static Material Magnesia = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "magnesia", 0xffffff, DULL));
    public static Material MagnesiumCarbonate = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "magnesium_carbonate", 0xF0E6E6, DULL));
    public static Material MagnesiumChloride = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "magnesium_chloride", 0xd40d5c, DULL));
    public static Material Massicot = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "massicot", 0xffdd55, DULL));
    public static Material Obsidian = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "obsidian", 0x503264, DULL));
    public static Material PhosphorousPentoxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "phosphorous_pentoxide", 0xdcdc00, NONE));
    public static Material PlatinumGroupSludge = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "platinum_group_sludge", 0x001e00, NONE));
    public static Material Polydimethylsiloxane = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "polydimethylsiloxane", 0xf5f5f5, NONE));
    public static Material Potash = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "potash", 0x784237, DULL));
    public static Material PotassiumFeldspar = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "potassium_feldspar", 0x782828, FINE));
    public static Material Quicklime = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "quicklime", 0xf0f0f0, DULL));
    public static Material RareEarth = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "rare_earth", 0x808064, FINE));
    public static Material RawRubber = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "raw_rubber", 0xccc789, DULL));
    public static Material RawStyreneButadieneRubber = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "raw_styrene_butadiene_rubber", 0x54403d, SHINY));
    public static Material ReactionCatalyst = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "reaction_catalyst", 0x43ab43, NONE));
    public static Material Rubber = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "rubber", 0x141414, SHINY));
    public static Material SiliconDioxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "silicon_dioxide", 0xc8c8c8, QUARTZ));
    public static Material SodaAsh = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "soda_ash", 0xdcdcff, DULL));
    public static Material SodiumAluminate = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sodium_aluminate", 0xE6E6FA, NONE));
    public static Material SodiumBisulfate = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sodium_bisulfate", 0x004455, NONE));
    public static Material SodiumHydroxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sodium_hydroxide", 0x003380, DULL));
    public static Material SodiumSulfate = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sodium_sulfate", 0x004455, NONE));
    public static Material SodiumSulfide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sodium_sulfide", 0xffe680, NONE));
    public static Material TungsticAcid = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "tungstic_acid", 0xb4c800, SHINY));
    public static Material TungstenTrioxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "tungsten_trioxide", 0xc7d300, DULL));

    /**
     ** Ores
     **/

    public static Material Almandine = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "almandine", 0xff0000, ROUGH));
    public static Material Alumina = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "alumina", 0x78C3EB, METALLIC));
    public static Material Andradite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "andradite", 0x967800, ROUGH));
    public static Material Hematite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hematite", 0x915a5a, DULL));
    public static Material Barite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "barite", 0xe6ebff, DULL));
    public static Material Bastnasite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "bastnasite", 0xc86e2d, FINE));
    public static Material Bentonite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "bentonite", 0xf5d7d2, ROUGH)); // TODO: Ore Gen
    public static Material BrownLimonite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "brown_limonite", 0xc86400, METALLIC));
    public static Material Cassiterite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "cassiterite", 0xdcdcdc, METALLIC)).setMassMultiplierAndDivider(3, 1);
    public static Material Chalcopyrite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "chalcopyrite", 0xa07828, DULL));
    public static Material Chromite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "chromite", 0x23140F, DULL));
    public static Material Cobaltite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "cobaltite", 0x5050fa, METALLIC));
    public static Material Cooperite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "cooperite", 0xffffc8, METALLIC));
    public static Material Galena = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "galena", 0x643c64, DULL));
    public static Material Garnierite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "garnierite", 0x32c846, METALLIC));
    public static Material Glauconite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "glauconite", 0x82b43c, DULL)); // TODO: Ore Gen;
    public static Material Graphite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "graphite", 0x808080, DULL));
    public static Material Grossular = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "grossular", 0xc86400, ROUGH));
    public static Material Ilmenite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "ilmenite", 0x463732, METALLIC)).setMassMultiplierAndDivider(2, 1);
    public static Material Lepidolite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "lepidolite", 0xf0328c, FINE)); // TODO: Ore Gen;
    public static Material Magnesite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "magnesite", 0xfafab4, METALLIC));
    public static Material Magnetite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "magnetite", 0x1e1e1e, METALLIC));
    public static Material Malachite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "malachite", 0x055f05, DULL));
    public static Material Molybdenite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "molybdenite", 0x91919, METALLIC));
    public static Material Pentlandite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "pentlandite", 0xa59605, DULL));
    public static Material Phosphate = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "phosphate", 0xffff00, DULL));
    public static Material Pitchblende = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "pitchblende", 0xc8d200, DULL));
    public static Material Powellite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "powellite", 0xffff00, DULL));
    public static Material Pyrite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "pyrite", 0x967828, ROUGH));
    // public static Material Pyrochlore = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "pyrochlore", 0x2b1100,METALLIC));
    public static Material Pyrolusite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "pyrolusite", 0x9696aa, DULL));
    public static Material Pyrope = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "pyrope", 0x783264, METALLIC));
    public static Material Rutile = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "rutile", 0xd40d5c, GEM_H)).setMassMultiplierAndDivider(2, 1);
    public static Material Saltpeter = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "saltpeter", 0xe6e6e6, FINE));
    public static Material Scheelite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "scheelite", 0xc88c14, DULL));
    public static Material Soapstone = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "soapstone", 0x5f915f, DULL)); // TODO: Ore Gen;
    public static Material Spessartine = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "spessartine", 0xff6464, DULL));
    public static Material Sphalerite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sphalerite", 0xffffff, DULL));
    public static Material Spodumene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "spodumene", 0xbeaaaa, DULL));
    public static Material Stibnite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "stibnite", 0x464646, METALLIC));
    public static Material Talc = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "talc", 0x5ab45a, DULL));
    public static Material Tantalite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "tantalite", 0x915028, METALLIC));
    public static Material Tetrahedrite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "tetrahedrite", 0xc82000, DULL));
    public static Material Wulfenite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "wulfenite", 0xff8000, DULL));
    public static Material VanadiumMagnetite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "vanadium_magnetite", 0x23233c, METALLIC));
    public static Material Tungstate = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "tungstate", 0x373223, DULL));
    public static Material Uraninite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "uraninite", 0x232323, METALLIC));
    public static Material Uvarovite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "uvarovite", 0xb4ffb4, DIAMOND));
    public static Material YellowLimonite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "yellow_limonite", 0xc8c800, METALLIC));

    /**
     ** Ore Stones
     **/

    public static Material Bauxite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "bauxite", 0xc86400, DULL));
    public static Material Lignite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "lignite_coal", 0x644646, LIGNITE));
    public static Material OilShale = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "oil_shale", 0x32323c, NONE));
    public static Material Salt = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "salt", 0xfafafa, FINE));
    public static Material RockSalt = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "rock_salt", 0xf0c8c8, FINE));

    /**
     ** Gems
     **/

    public static Material Apatite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "apatite", 0x78B4FA, DIAMOND));
    public static Material Amber = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "amber", 0xFFB400, RUBY));
    public static Material Amethyst = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "amethyst", 0xd232d2, RUBY));
    public static Material Sapphire = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sapphire", 0x6464c8, GEM_V));
    public static Material BlueTopaz = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "blue_topaz", 0x0000ff, GEM_H));
    public static Material MilkyQuartz = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "milky_quartz", 0xd2d2d2, QUARTZ));
    public static Material CertusQuartz = AntimatterAPI.register(Material.class,new Material(GTIRef.ID, "certus_quartz", 0xd2d2e6, QUARTZ, Ref.MOD_AE));
    public static Material ChargedCertusQuartz = AntimatterAPI.register(Material.class,new Material(GTIRef.ID, "charged_certus_quartz", 0xd2d2e6, QUARTZ, Ref.MOD_AE));
    public static Material Fluix = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "fluix", 0x78468C, QUARTZ, Ref.MOD_AE));
    public static Material CoalCoke = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "coal_coke", 0x8c8caa, LIGNITE));
    public static Material Dilithium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "dilithium", 0xfffafa, DIAMOND));
    public static Material Glass = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "glass", 0xfafafa, SHINY));
    public static Material GreenSapphire = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "green_sapphire", 0x64c882, GEM_H));
    public static Material Jade = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "jade", 0x64ff7d, LAPIS));
    public static Material Lazurite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "lazurite", 0x6478ff, LAPIS));
    public static Material LigniteCoke = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "lignite_coke", 0x8c6464, LIGNITE));
    public static Material Monazite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "monazite", 0x324632, DIAMOND));
    public static Material NetherStar = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "nether_star", 0xffffff, NONE));
    public static Material Olivine = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "olivine", 0x96ff96, RUBY));
    public static Material Opal = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "opal", 0x0000ff, RUBY));
    public static Material Phosphorus = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "phosphorus", 0xffff00, TextureSet.FLINT));
    public static Material RedGarnet = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "red_garnet", 0xc85050, GARNET));
    public static Material Ruby = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "ruby", 0xff6464, RUBY));
    public static Material Sodalite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sodalite", 0x1414ff, LAPIS));
    public static Material Tanzanite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "tanzanite", 0x4000c8, GEM_V));
    public static Material Topaz = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "topaz", 0xff8000, GEM_H));
    public static Material YellowGarnet = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "yellow_garnet", 0xc8c850, GARNET));

    /**
     ** Plastic
     **/

    public static Material EpoxyResin = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "epoxy_resin", 0xc88c14, DULL));
    public static Material FiberReinforcedEpoxyResin = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "fiber_reinforced_epoxy_resin", 0xa07010, DULL));
    public static Material Polycaprolactam = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "polycaprolactam", 0x323232, DULL));
    public static Material Polyethylene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "polyethylene", 0xc8c8c8, DULL)).setDisplayNameString("Plastic (Polyethylene)");
    public static Material PolyphenyleneSulfide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "polyphenylene_sulfide", 0xaa8800, DULL));
    public static Material Polystyrene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "polystyrene", 0xbeb4aa, DULL)).setDisplayNameString("Plastic (Polystyrene)");
    public static Material Polytetrafluoroethylene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "polytetrafluoroethylene", 0x646464, DULL));
    public static Material PolyvinylChloride = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "polyvinyl_chloride", 0xd7e6e6, NONE));
    public static Material Silicone = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "silicone", 0xdcdcdc, DULL));
    public static Material SiliconeRubber = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "siliconerubber", 0x9fadbb, NONE));
    public static Material StyreneButadieneRubber = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "styrene_butadiene_rubber", 0x211a18, SHINY));

    /**
     ** Stones
     **/

    public static Material BlackGranite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "black_granite", 0x0a0a0a, ROUGH));
    public static Material BlueSchist = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "blue_schist", 0x0569be, NONE));
    public static Material GreenSchist = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "green_schist", 0x69be69, NONE));
    public static Material Kimberlite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "kimberlite", 0x64460a, NONE));
    public static Material Komatiite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "komatiite", 0xbebe69, NONE));
    public static Material Limestone = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "limestone", 0xe6c882, NONE));
    public static Material Marble = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "marble", 0xc8c8c8, NONE));
    public static Material Quartzite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "quartzite", 0xe6cdcd, QUARTZ));
    public static Material RedGranite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "red_granite", 0xff0080, ROUGH));
    public static Material Shale = AntimatterAPI.register(Material.class, new Material(GTCore.ID, "shale", 0x8E8EA8, NONE));
    public static Material Slate = AntimatterAPI.register(Material.class, new Material(GTCore.ID, "slate", 0x94979C, NONE));

    /**
     ** Misc
     **/

    public static Material Cinnabar = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "cinnabar", 0x960000, ROUGH));
    public static Material GalliumArsenide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "gallium_arsenide", 0xa0a0a0, DULL));
    public static Material HighPressure = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "high_pressure", 0xc80000, NONE));
    public static Material HighCapacity = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "high_capacity", 0xb00b69, NONE));
    public static Material PlasmaContainment = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "plasma_containment", 0xffff00, NONE));
    public static Material Superconductor = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "superconductor", 0xffffff, NONE));

    /**
     *** Fluids
     **/

    /**
     ** Organic
     **/

    public static Material AceticAcid = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "acetic_acid", 0xc8b4a0, NONE));
    public static Material Acetone = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "acetone", 0xafafaf, NONE));
    public static Material AllylChloride = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "allyl_chloride", 0x87deaa, NONE));
    public static Material Benzaldehyde = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "benzaldehyde", 0xf7dea3, NONE));
    public static Material Benzene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "benzene", 0x1a1a1a, NONE));
    public static Material Biomass = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "biomass", 0x00ff00, NONE));
    public static Material BisphenolA = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "bisphenol_a", 0xd4b300, NONE));
    public static Material Butanediol = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "butanediol", 0xff8000, NONE));
    public static Material Butanol = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "butanol", 0xff8000, NONE));
    public static Material Butenol = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "butenol", 0xff8000, NONE));
    public static Material CharcoalByproducts = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "charcoal_byproducts", 0x784421, NONE));
    public static Material Chloramine = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "chloramine", 0x3f9f80, NONE));
    public static Material Chloroform = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "chloroform", 0x892ca0, NONE));
    public static Material Cumene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "cumene", 0x552200, NONE));
    public static Material Chlorobenzene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "dhlorobenzene", 0x004455, NONE));
    public static Material Dichlorobenzene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "dichlorobenzene", 0x004455, NONE));
    public static Material Dimethyldichlorosilane = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "dimethyldichlorosilane", 0x441650, NONE));
    public static Material Dimethylhydrazine = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "dimethylhydrazine", 0x000055, NONE));
    public static Material Epichlorohydrin = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "epichlorohydrin", 0x501d05, NONE));
    public static Material Ethanediol = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "ethanediol", 0xff8000, NONE));
    public static Material Ethanol = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "ethanol", 0xff8000, NONE));
    public static Material Ethenol = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "ethenol", 0xff8000, NONE));
    public static Material EthylTertButylEther = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "ethyl_tert_butyl_ether", 0xffffff, NONE));
    public static Material FermentedBiomass = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "fermented_biomass", 0x09964a, NONE));
    public static Material Glue = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "glue", 0xc8c400, NONE));
    public static Material Heptanol = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "heptanol", 0xff8000, NONE));
    public static Material Honey = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "honey", 0xd2c800, NONE)); // TODO: Only when Forestry's present;
    public static Material Isoprene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "isoprene", 0x141414, NONE));
    public static Material Methanol = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "methanol", 0xaa8800, NONE));
    public static Material MethylAcetate = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "methyl_acetate", 0xeec6af, NONE));
    public static Material Naphtha = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "naphtha", 0xffff00, NONE));
    public static Material Phenol = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "phenol", 0x784421, NONE));
    public static Material PhosphoricAcid = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "phosphoric_acid", 0xdcdc00, NONE));
    public static Material PolyvinylAcetate = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "polyvinyl_acetate", 0xff9955, NONE));
    public static Material Propanediol = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "propanediol", 0xff8000, NONE));
    public static Material Propenol = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "propenol", 0xff8000, NONE));
    public static Material Propanol = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "propanol", 0xff8000, NONE));
    public static Material Styrene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "styrene", 0xd2c8be, NONE));
    public static Material SulfuricNaphtha = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sulfuric_naphtha", 0xffff00, NONE));
    public static Material Tetranitromethane = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "tetranitromethane", 0x0f2828, NONE));
    public static Material Toluene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "toluene", 0x501d05, NONE));
    public static Material VinylAcetate = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "vinyl_acetate", 0xffb380, NONE));
    public static Material WoodTar = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "wood_tar", 0x28170b, NONE));
    public static Material WoodVinegar = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "wood_vinegar", 0xd45500, NONE));

    /**
     ** Inorganic
     **/

    public static Material AluminiumFluoride = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "aluminium_fluoride", 0xc8bebe, NONE));
    public static Material Antimatter = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "anti_matter", 0x8000c4, NONE));
    public static Material BlueVitriol = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "blue_vitriol_water_solution", 0x4242DE, NONE));

    public static Material CalciumAcetateSolution = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "calcium_acetate_solution", 0xDCC8B4, NONE));
    public static Material Coolant = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "coolant", 0x0506be, NONE));
    public static Material Cryolite = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "cryolite", 0xc8bebe, NONE));
    public static Material DilutedHydrochloricAcid = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "diluted_hydrochloric_acid", 0x99a7a3, NONE));
    public static Material DilutedSulfuricAcid = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "diluted_sulfuric_acid", 0xc07820, NONE));
    public static Material DistilledWater = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "distilled_water", 0x5C5CFF, NONE));
    public static Material DrillingFluid = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "drilling_fluid", 0xffffff, NONE)); // TODO: Perhaps for a bedrock drill;
    public static Material HexafluorosilicicAcid = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hexafluorosilicic_acid", 0xbec8be, NONE));
    public static Material HotCoolant = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hot_coolant", 0x7a111a, NONE));
    public static Material HydrochloricAcid = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hydrochloric_acid", 0x6f8a91, NONE));
    public static Material HydrofluoricAcid = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hydrofluoric_acid", 0x0088aa, NONE));
    public static Material HydrogenFluoride = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hydrogen_fluoride", 0x00f0f0, NONE));
    public static Material HydrogenPeroxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hydrogen_peroxide", 0xf8efb4, NONE));
    public static Material HypochlorousAcid = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hypochlorous_acid", 0x6f8a91, NONE));
    public static Material IndiumConcentrate = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "indium_concentrate", 0xffffff, NONE));
    public static Material LeadZincSolution = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "lead_zinc_solution", 0xffffff, NONE));
    public static Material LiquidAir = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "liquid_air", 0xa9d0f5, NONE));
    public static Material Lubricant = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "lubricant", 0xffc400, NONE));
    public static Material NickelSulfate = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "nickel_sulfate", 0xffffff, NONE));
    public static Material NitrationMixture = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "nitration_mixture", 0xe6e2ab, NONE));
    public static Material NitricAcid = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "nitric_acid", 0xe6e2ab, NONE));
    public static Material PeroxydisulfuricAcid = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "peroxydisulfuricacid", 0xff9000, NONE));
    public static Material SaltWater = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "salt_water", 0x0760b9, NONE));
    public static Material SodiumBicarbonateSolution = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sodium_bicarbonate_solution", 0xffffff, NONE));
    public static Material SodiumCarbonateSolution = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sodium_carbonate_solution", 0xffffff, NONE));
    public static Material SodiumPersulfate = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sodium_persulfate", 0xffffff, NONE));
    public static Material SulfuricAcid = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sulfuric_acid", 0xff8000, NONE));
    public static Material TitaniumTetrachloride = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "titanium_tetrachloride", 0xd40d5c, NONE));
    public static Material UUAmplifier = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "uu_amplifier", 0x600080, NONE));
    public static Material UUMatter = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "uu_matter", 0x8000c4, NONE));

    /**
     ** Fuels
     **/

    public static Material BioDiesel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "bio_diesel", 0xff8000, NONE));
    public static Material Creosote = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "creosote", 0x804000, NONE));
    public static Material Diesel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "diesel", 0xffff00, NONE));
    public static Material FishOil = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "fish_oil", 0xffc400, NONE));
    public static Material HeavyFuel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "heavy_fuel", 0xffff00, NONE));
    public static Material CetaneBoostedDiesel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "cetane_boosted_diesel", 0xc8ff00, NONE));
    public static Material LightFuel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "light_fuel", 0xffff00, NONE));
    public static Material RocketFuel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "rocket_fuel", 0xffffff, NONE));
    public static Material Gasoline = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "gasoline", 0xFFA500, NONE));
    public static Material HighOctaneGasoline = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "high_octane_gasoline", 0xFFA500, NONE));
    public static Material Oil = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "oil", 0x0a0a0a, NONE));
    public static Material OilHeavy = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "heavy_oil", 0x0a0a0a, NONE));
    public static Material OilMedium = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "raw_oil", 0x0a0a0a, NONE));
    public static Material OilLight = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "light_oil", 0x0a0a0a, NONE));
    public static Material SeedOil = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "seed_oil", 0xc4ff00, NONE));
    public static Material SulfuricLightFuel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sulfuric_light_fuel", 0xffff00, NONE));
    public static Material SulfuricHeavyFuel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sulfuric_heavy_fuel", 0xffff00, NONE));
    public static Material Glycerol = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "glycerol", 0x87de87, NONE));
    public static Material GlycerylTrinitrate = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "glyceryl_trinitrate", 0x87de87, NONE));

    /**
     *** Gases/Plasmas
     **/

    /**
     ** Organic
     **/

    public static Material Butane = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "butane", 0xb6371e, NONE));
    public static Material Butadiene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "butadiene", 0xe86900, NONE));
    public static Material Butene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "butene", 0xcf5005, NONE));
    public static Material CarbonDioxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "carbon_dioxide", 0xa9d0f5, NONE));
    public static Material CarbonMonoxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "carbon_monoxide", 0x0e4880, NONE));
    public static Material Chloromethane = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "chloromethane", 0xc82ca0, NONE));
    public static Material Dimethylamine = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "dimethylamine", 0x554469, NONE));
    public static Material Ethane = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "ethane", 0xc8c8ff, NONE));
    public static Material Ethenone = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "ethenone", 0x141446, NONE));
    public static Material Ethylene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "ethylene", 0xe1e1e1, NONE));
    public static Material LPG = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "lpg", 0xffff00, NONE));
    public static Material Methane = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "methane", 0xffffff, NONE));
    public static Material NaturalGas = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "natural_gas", 0xffffff, NONE));
    public static Material Propane = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "propane", 0xfae250, NONE));
    public static Material Propene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "propene", 0xffdd55, NONE));
    public static Material RefineryGas = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "refinery_gas", 0xffffff, NONE));
    public static Material SulfuricGas = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sulfuric_gas", 0xffffff, NONE));
    public static Material Tetrafluoroethylene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "tetrafluoroethylene", 0x7d7d7d, NONE));
    public static Material VinylChloride = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "vinyl_chloride", 0xfff0f0, NONE));
    public static Material WoodGas = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "wood_gas", 0xdecd87, NONE));

    /**
     ** Inorganic
     **/

    public static Material Air = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "air", 0xc9e3fc, NONE));
    public static Material Ammonia = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "ammonia", 0x3f3480, NONE));
    public static Material DinitrogenTetroxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "dinitrogen_tetroxide", 0x004184, NONE));
    public static Material HydrogenSulfide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hydrogen_sulfide", 0xffffff, NONE));
    public static Material NitricOxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "nitric_oxide", 0x7dc8f0, NONE));
    public static Material NitrousOxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "nitrous_oxide", 0x7DC8FF, NONE));
    public static Material NitrogenDioxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "nitrogen_dioxide", 0x64afff, NONE));
    public static Material NobleGases = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "noble_gases", 0xc9e3fc, NONE));
    public static Material Steam = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "steam", 0xa0a0a0, NONE));
    public static Material SuperheatedSteam = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "superheated_steam", 0xa0a0a0, NONE));
    public static Material SulfurDioxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sulfur_dioxide", 0xc8c819, NONE));
    public static Material SulfurTrioxide = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "sulfur_trioxide", 0xa0a014, NONE));

    /**
     ** Cracked
     **/

    public static Material HydroCrackedEthane = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hydro_cracked_ethane", 0xc8c8ff, NONE));
    //public static Material HydroCrackedEthylene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hydro_cracked_ethylene", 0xe1e1e1, NONE));
    public static Material HydroCrackedPropane = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hydro_cracked_propane", 0xfae250, NONE));
    //public static Material HydroCrackedPropene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hydro_cracked_propene", 0xffdd55, NONE));
    public static Material HydroCrackedButane = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hydro_cracked_butane", 0xb6371e, NONE));
    //public static Material HydroCrackedButene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hydro_cracked_butene", 0xcf5005, NONE));
    //public static Material HydroCrackedButadiene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hydro_cracked_butadiene", 0xe86900, NONE));
    public static Material HydroCrackedHeavyFuel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hydro_cracked_heavy_fuel", 0xffff00, NONE));
    public static Material HydroCrackedLightFuel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hydro_cracked_light_fuel", 0xffff00, NONE));
    public static Material HydroCrackedNaphtha = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hydro_cracked_naphtha", 0xffff00, NONE));
    public static Material HydroCrackedRefineryGas = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "hydro_cracked_refinery_gas", 0xffffff, NONE));
    public static Material SteamCrackedEthane = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "steam_cracked_ethane", 0xc8c8ff, NONE));
    //public static Material SteamCrackedEthylene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "steam_cracked_ethylene", 0xe1e1e1, NONE));
    public static Material SteamCrackedPropane = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "steam_cracked_propane", 0xfae250, NONE));
    //public static Material SteamCrackedPropene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "steam_cracked_propene", 0xffdd55, NONE));
    public static Material SteamCrackedButane = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "steam_cracked_butane", 0xb6371e, NONE));
    //public static Material SteamCrackedButene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "steam_cracked_butene", 0xcf5005, NONE));
    //public static Material SteamCrackedButadiene = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "steam_cracked_butadiene", 0xe86900, NONE));
    public static Material SteamCrackedHeavyFuel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "steam_cracked_heavy_fuel", 0xffff00, NONE));
    public static Material SteamCrackedLightFuel = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "steam_cracked_light_fuel", 0xffff00, NONE));
    public static Material SteamCrackedNaphtha = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "steam_cracked_naphtha", 0xffff00, NONE));
    public static Material SteamCrackedRefineryGas = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "steam_cracked_refinery_gas", 0xffffff, NONE));

    /**
     *  Tetrafluorides
     **/
    public static Material UraniumTetrafluoride = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "uranium_tetrafluoride", 0x21d921, NONE));
    public static Material Uranium238Tetrafluoride = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "uranium_238_tetrafluoride", 0x21d921, NONE));
    public static Material Uranium235Tetrafluoride = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "uranium_235_tetrafluoride", 0x21d921, NONE));

    /**
     *  Hexafluorides
     **/
    public static Material UraniumHexafluoride = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "uranium_hexafluoride", 0x10c810, NONE));
    public static Material Uranium235Hexafluoride = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "uranium_235_hexafluoride", 0x10c810, NONE));
    public static Material Uranium238Hexafluoride = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, "uranium_238_hexafluoride", 0x10c810, NONE));

    // TODO go through the GT_Loader_Item_Block_And_Fluid and make sure all explicitly added fluids have the LIQUID tag
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
    }
}
