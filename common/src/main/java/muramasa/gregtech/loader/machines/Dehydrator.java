package muramasa.gregtech.loader.machines;

import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.DEHYDRATING;

public class Dehydrator {
    public static void init() {
        DEHYDRATING.RB().ii(DUST.getMaterialIngredient(TungsticAcid, 7)).io(DUST.get(TungstenTrioxide, 4)).fo(DistilledWater.getLiquid(3000)).add("tungsten_trioxide", 300 * 20, 16);
        DEHYDRATING.RB().fi(SaltWater.getFluidTag(1000)).io(DUST_SMALL.get(Salt, 1)).fo(DistilledWater.getLiquid(750)).add("salt_water_drying", 8 * 20, 16);
        DEHYDRATING.RB().ii(DUST.getMaterialIngredient(AluminiumHydroxide, 14)).io(DUST.get(Alumina, 5)).fo(DistilledWater.getLiquid(9000)).add("alumina", 900 * 20, 16);
        //Waste Processing
        DEHYDRATING.RB().ii(of(ThoriumWaste)).fi(SulfuricAcid.getLiquid(100)).io(new ItemStack(NuclearWaste),DUST_SMALL.get(Rhodium,4)).add("thorium_waste_reprocessing",500,8);
        DEHYDRATING.RB().ii(of(ProtactiniumWaste)).fi(SulfuricAcid.getLiquid(100)).io(new ItemStack(NuclearWaste),DUST_SMALL.get(Rhodium,2),DUST_SMALL.get(Palladium,2)).add("protactinium_waste_reprocessing",500,8);
        DEHYDRATING.RB().ii(of(UraniumWaste)).fi(SulfuricAcid.getLiquid(100)).io(new ItemStack(NuclearWaste),DUST_SMALL.get(Palladium,4)).add("uranium_waste_reprocessing",500,8);
        DEHYDRATING.RB().ii(of(NeptuniumWaste)).fi(SulfuricAcid.getLiquid(100)).io(new ItemStack(NuclearWaste),DUST_SMALL.get(Palladium,2),DUST_SMALL.get(Silver,2)).add("neptunium_waste_reprocessing",500,8);
        DEHYDRATING.RB().ii(of(PlutoniumWaste)).fi(SulfuricAcid.getLiquid(100)).io(new ItemStack(NuclearWaste),DUST_SMALL.get(Silver,4)).add("plutonium_waste_reprocessing",500,8);
        DEHYDRATING.RB().ii(of(AmericiumWaste)).fi(SulfuricAcid.getLiquid(100)).io(new ItemStack(NuclearWaste),DUST_SMALL.get(Silver,2),DUST_SMALL.get(Cadmium,2)).add("americium_waste_reprocessing",500,8);
        DEHYDRATING.RB().ii(of(CuriumWaste)).fi(SulfuricAcid.getLiquid(100)).io(new ItemStack(NuclearWaste),DUST_SMALL.get(Cadmium,4)).add("curium_waste_reprocessing",500,8);
        DEHYDRATING.RB().ii(of(BerkeliumWaste)).fi(SulfuricAcid.getLiquid(100)).io(new ItemStack(NuclearWaste),DUST_SMALL.get(Cadmium,2),DUST_SMALL.get(Indium,2)).add("berkelium_waste_reprocessing",500,8);
        DEHYDRATING.RB().ii(of(CaliforniumWaste)).fi(SulfuricAcid.getLiquid(100)).io(new ItemStack(NuclearWaste),DUST_SMALL.get(Indium,4)).add("californium_waste_reprocessing",500,8);
        DEHYDRATING.RB().ii(of(EinsteiniumWaste)).fi(SulfuricAcid.getLiquid(100)).io(new ItemStack(NuclearWaste),DUST_SMALL.get(Indium,2),DUST_SMALL.get(Tin,2)).add("einsteinium_waste_reprocessing",500,8);
        DEHYDRATING.RB().ii(of(FermiumWaste)).fi(SulfuricAcid.getLiquid(100)).io(new ItemStack(NuclearWaste),DUST_SMALL.get(Tin,4)).add("fermium_waste_reprocessing",500,8);
        DEHYDRATING.RB().ii(of(MendeleviumWaste)).fi(SulfuricAcid.getLiquid(100)).io(new ItemStack(NuclearWaste),DUST_SMALL.get(Tin,2),DUST_SMALL.get(Antimony,2)).add("mendelevium_waste_reprocessing",500,8);

        DEHYDRATING.RB().ii(of(NuclearWaste)).fi(SulfuricAcid.getLiquid(1000))
                        .io(new ItemStack(AlkalineWaste),new ItemStack(HeavyMetalWaste),new ItemStack(LanthanideGroupAWaste),
                        new ItemStack(LanthanideGroupBWaste), new ItemStack(MetalGroupAWaste), new ItemStack(MetalGroupBWaste),new ItemStack(MetalGroupCWaste),
                        new ItemStack(MetaloidWaste),new ItemStack(NonmetalWaste))
                .add("nuclear_waste_reprocessing",400,8);

        DEHYDRATING.RB().ii(of(AlkalineWaste)).fi(SulfuricAcid.getLiquid(100))
                        .io(DUST_TINY.get(Rubidium,2),DUST_TINY.get(Strontium,2),DUST_TINY.get(Caesium,2),
                        DUST_TINY.get(Barium,2),DUST_TINY.get(Francium,2),DUST_TINY.get(Radium,2))
                .add("alkaline_waste_reprocessing",400,8);

        DEHYDRATING.RB().ii(of(HeavyMetalWaste)).fi(SulfuricAcid.getLiquid(100))
                        .io(DUST_TINY.get(Zinc,2),DUST_TINY.get(Gallium,2),DUST_TINY.get(Cadmium,2),
                        DUST_TINY.get(Indium,2),DUST_TINY.get(Tin,2),DUST_TINY.get(Thallium,2),
                        DUST_TINY.get(Lead,2),DUST_TINY.get(Bismuth,2),DUST_TINY.get(Polonium,2))
                        .fo(Mercury.getLiquid(250))
                .add("heavy_metal_waste_reprocessing",400,8);

        DEHYDRATING.RB().ii(of(LanthanideGroupAWaste)).fi(SulfuricAcid.getLiquid(100))
                        .io(DUST_TINY.get(Dysprosium,2),DUST_TINY.get(Holmium,2),DUST_TINY.get(Erbium,2),
                        DUST_TINY.get(Thulium,2),DUST_TINY.get(Ytterbium,2),DUST_TINY.get(Lutetium,2))
                .add("lanthanide_group_a_waste_reprocessing",400,8);

        DEHYDRATING.RB().ii(of(LanthanideGroupBWaste)).fi(SulfuricAcid.getLiquid(100))
                        .io(DUST_TINY.get(Lanthanum,2),DUST_TINY.get(Cerium,2),DUST_TINY.get(Praseodymium,2),
                        DUST_TINY.get(Neodymium,2),DUST_TINY.get(Promethium,2),DUST_TINY.get(Samarium,2),
                        DUST_TINY.get(Europium,2),DUST_TINY.get(Gadolinium,2),DUST_TINY.get(Terbium,2))
                .add("lanthanide_group_b_waste_reprocessing",400,8);

        DEHYDRATING.RB().ii(of(MetalGroupAWaste)).fi(SulfuricAcid.getLiquid(100))
                        .io(DUST_TINY.get(Hafnium,2),DUST_TINY.get(Tantalum,2),DUST_TINY.get(Tungsten,2),
                        DUST_TINY.get(Rhenium,2),DUST_TINY.get(Osmium,2),DUST_TINY.get(Iridium,2),
                        DUST_TINY.get(Platinum,2),DUST_TINY.get(Gold,2))
                .add("metal_group_a_waste_reprocessing",400,8);

        DEHYDRATING.RB().ii(of(MetalGroupBWaste)).fi(SulfuricAcid.getLiquid(100))
                        .io(DUST_TINY.get(Yttrium,2),DUST_TINY.get(Zirconium,2),DUST_TINY.get(Niobium,2),
                        DUST_TINY.get(Molybdenum,2),DUST_TINY.get(Technetium,2),DUST_TINY.get(Ruthenium,2),
                        DUST_TINY.get(Rhodium,2),DUST_TINY.get(Palladium,2),DUST_TINY.get(Silver,2))
                .add("metal_group_b_waste_reprocessing",400,8);

        DEHYDRATING.RB().ii(of(MetalGroupCWaste)).fi(SulfuricAcid.getLiquid(100))
                        .io(DUST_TINY.get(Iron,2),DUST_TINY.get(Cobalt,2),DUST_TINY.get(Nickel,2),DUST_TINY.get(Copper,2))
                .add("metal_group_c_waste_reprocessing",400,8);

        DEHYDRATING.RB().ii(of(MetaloidWaste)).fi(SulfuricAcid.getLiquid(100))
                        .io(DUST_TINY.get(Germanium,2),DUST_TINY.get(Arsenic,2),DUST_TINY.get(Antimony,2),
                        DUST_TINY.get(Tellurium,2),DUST_TINY.get(Astatine,2),DUST_TINY.get(Actinium,2))
                .add("metaloid_waste_reprocessing",400,8);

        DEHYDRATING.RB().ii(of(NonmetalWaste)).fi(SulfuricAcid.getLiquid(100)).io(DUST_TINY.get(Selenium,2),DUST_TINY.get(Iodine,2))
                .fo(Krypton.getGas(500),Xenon.getGas(500),Radon.getGas(500))
                .add("nonmetal_waste_reprocessing",400,8);
    }
}
