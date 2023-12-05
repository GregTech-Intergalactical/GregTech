package muramasa.gregtech.loader.machines;

import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.GregTechConfig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.stream.Collectors;

import static muramasa.antimatter.data.AntimatterMaterialTypes.BLOCK;
import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.*;

import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.MIXER;



public class MixerLoader {

    public static void init() {
        addDust(StainlessSteel, 8, 45*20);
        addDust(Nichrome, 8, 25*20);
        addDust(Invar, 8, 15*20);
        addDust(Bronze, 8, 10*20);
        addDust(BlackBronze, 8, 25*20);
        addDust(FerriteMixture, 8, 30*20);
        addDust(IndiumGalliumPhosphide, 8, 20*20);
        addDust(Energium, 8, 10*20);
        addDust(GalliumArsenide, 8, 15*20);
        addDust(VanadiumSteel, 8, 50*20);
        addDust(CobaltBrass, 8, 45*20);
        addDust(BlueSteel, 8, 40*20);
        addDust(RedSteel, 8, 40*20);
        addDust(BlackSteel, 8, 25*20);
        addDust(SterlingSilver, 8, 25*20);
        addDust(RoseGold, 8, 25*20);
        addDust(BismuthBronze, 8, 25*20);
        addDust(VanadiumGallium, 8, 20*20);
        addDust(Ultimet, 8, 45*20);
        addDust(TinAlloy, 8, 10*20);
        addDust(SodiumSulfide, 8, 3*20);
        addDust(Magnalium, 8, 20*20);
        addDust(Kanthal, 8, 15*20);
        addDust(Electrum, 8, 10*20);
        addDust(Brass, 8, 20*20);
        addDust(BatteryAlloy, 8, 25*20);
        addDust(Cupronickel, 8, 10*20);
        addDust(EnderEye, 8, 5*20);
        recipes();
    }

    private static void addDust(Material mat, int eut, int duration) {
        for (MaterialTypeItem<?> type : new MaterialTypeItem[]{DUST, AntimatterMaterialTypes.DUST_SMALL, AntimatterMaterialTypes.DUST_TINY}) {
            List<Ingredient> ings = mat.getProcessInto().stream().map(t -> type.getMaterialIngredient(t.m, t.s)).collect(Collectors.toList());
            if (ings.size() == 0) return;
            var type2 = type;
            int count = mat.getProcessInto().stream().mapToInt(t -> t.s).sum();
            if (type != DUST){
                if (type == AntimatterMaterialTypes.DUST_SMALL){
                    if (count % 4 == 0){
                        count /=4;
                        type2 = DUST;
                    }
                } else {
                    if (count % 9 == 0){
                        count /=9;
                        type2 = DUST;
                    }
                }
            }


            MIXER.RB().ii(ings).io(type2.get(mat,count)).add(type.getId() + "_" + mat.getId(),duration,eut);
        }
    }

    private static void recipes() {
        if (GregTechConfig.MORE_COMPLICATED_CHEMICAL_RECIPES){
            MIXER.RB().fi(PolyvinylAcetate.getLiquid(1000),Acetone.getLiquid(1500)).fo(Glue.getLiquid(2500)).add("glue",25 * 2, 8);
            MIXER.RB().fi(PolyvinylAcetate.getLiquid(1000),MethylAcetate.getLiquid(1500)).fo(Glue.getLiquid(2500)).add("glue_1",25 * 2, 8);
        }
        MIXER.RB().fi(NitricAcid.getLiquid(1000),SulfuricAcid.getLiquid(1000)).fo(NitrationMixture.getLiquid(2000)).add("nitration_mixture",25 * 20, 2);
        MIXER.RB().fi(BioDiesel.getLiquid(1000),Tetranitromethane.getLiquid(40)).fo(CetaneBoostedDiesel.getLiquid(750)).add("nitro_fuel", 20, 480);
        MIXER.RB().fi(Diesel.getLiquid(1000),Tetranitromethane.getLiquid(20)).fo(CetaneBoostedDiesel.getLiquid(1000)).add("nitro_fuel_1", 20, 480);
        MIXER.RB().fi(LightFuel.getLiquid(5000),HeavyFuel.getLiquid(1000)).fo(Diesel.getLiquid(6000)).add("diesel",8 * 2, 120);
        MIXER.RB().ii(of(DUST.get(Wood,4))).fi(SulfuricAcid.getLiquid(1000)).io(Items.CHARCOAL.getDefaultInstance()).fo(DilutedSulfuricAcid.getLiquid(1000)).add("diluted_sulfuric_acid",60*20, 2);
        MIXER.RB().ii(of(DUST.get(AntimatterMaterials.Sugar,4))).fi(SulfuricAcid.getLiquid(1000)).io(Items.CHARCOAL.getDefaultInstance()).fo(DilutedSulfuricAcid.getLiquid(1000)).add("diluted_sulfuric_acid_1",60*20, 2);
        MIXER.RB().ii(of(DUST.get(Salt,2))).fi(AntimatterMaterials.Water.getLiquid(1000)).fo(SaltWater.getLiquid(1000)).add("salt_water",2*20, 8);
        MIXER.RB().ii(of(DUST.get(AntimatterMaterials.Copper,3)),of(DUST.get(Barium,2)),of(DUST.get(Yttrium,1))).fi(Oxygen.getGas(7000)).io(DUST.get(YttriumBariumCuprate,13)).add("yttrium_barium_cuprate",40*20, 8);
        MIXER.RB().ii(of(DUST.get(Talc,1))).fi(Oil.getLiquid(750)).fo(Lubricant.getLiquid(750)).add("lubricant",64*2, 4);
        MIXER.RB().ii(of(DUST.get(Talc,1))).fi(Creosote.getLiquid(750)).fo(Lubricant.getLiquid(750)).add("lubricant_1",64*2, 4);
        MIXER.RB().ii(of(DUST.get(Talc,1))).fi(SeedOil.getLiquid(750)).fo(Lubricant.getLiquid(750)).add("lubricant_2",64*2, 4);
        MIXER.RB().ii(of(DUST.get(Soapstone,1))).fi(Oil.getLiquid(750)).fo(Lubricant.getLiquid(750)).add("lubricant_3",64*2, 4);
        MIXER.RB().ii(of(DUST.get(Soapstone,1))).fi(Creosote.getLiquid(750)).fo(Lubricant.getLiquid(750)).add("lubricant_4",64*2, 4);
        MIXER.RB().ii(of(DUST.get(Soapstone,1))).fi(SeedOil.getLiquid(750)).fo(Lubricant.getLiquid(750)).add("lubricant_5",64*2, 4);
        MIXER.RB().ii(of(DUST.get(AntimatterMaterials.Redstone,1))).fi(Oil.getLiquid(750)).fo(Lubricant.getLiquid(750)).add("lubricant_6",64*2, 4);
        MIXER.RB().ii(of(DUST.get(AntimatterMaterials.Redstone,1))).fi(Creosote.getLiquid(750)).fo(Lubricant.getLiquid(750)).add("lubricant_7",64*2, 4);
        MIXER.RB().ii(of(DUST.get(AntimatterMaterials.Redstone,1))).fi(SeedOil.getLiquid(750)).fo(Lubricant.getLiquid(750)).add("lubricant_8",64*2, 4);
        MIXER.RB().ii(of(DUST.get(Glass,7)),of(DUST.get(Boron,1))).io(DUST.get(BorosilicateGlass,8)).add("borosilicate_glass",40*20, 8);
        MIXER.RB().ii(of(DUST.get(Saltpeter,2)),of(DUST.get(Sulfur,1)),of(DUST.get(AntimatterMaterials.Coal,1))).io(Items.GUNPOWDER).add("gunpowder",20*20, 8);
        MIXER.RB().ii(of(DUST.get(Saltpeter,2)),of(DUST.get(Sulfur,1)),of(DUST.get(AntimatterMaterials.Charcoal,1))).io(new ItemStack(Items.GUNPOWDER,2)).add("gunpowder_1",15*20, 8);
        MIXER.RB().ii(of(DUST.get(AntimatterMaterials.Stone,1))).fi(Lubricant.getLiquid(20), AntimatterMaterials.Water.getLiquid(4980)).fo(DrillingFluid.getLiquid(5000)).add("drilling_fluid",32*2, 16);
        MIXER.RB().ii(of(DUST.get(AntimatterMaterials.Stone,3)),of(DUST.get(Clay,1))).fi(AntimatterMaterials.Water.getLiquid(500)).fo(Concrete.getLiquid(576)).add("concrete", 20, 16);
        MIXER.RB().ii(RecipeIngredient.of(GTCoreItems.Biochaff)).fi(AntimatterMaterials.Water.getLiquid(1000)).fo(Biomass.getLiquid(1000)).add("biomass", 400, 8);
        MIXER.RB().ii(DUST.getMaterialIngredient(Lapis, 1)).fi(DistilledWater.getLiquid(125)).fo(Coolant.getLiquid(125)).add("cold_coolant", 256, 48);
        long oneThird = AntimatterPlatformUtils.isFabric() ? 27000 : 333;
        MIXER.RB().ii(DUST.getMaterialIngredient(Wood, 4), DUST.getMaterialIngredient(Sulfur, 1), DUST.getMaterialIngredient(Lithium, 1)).fi(Glue.getLiquid(oneThird)).io(new ItemStack(SuperFuelBinder, 8)).add("super_fuel_binder", 40 * 20, 16);
        MIXER.RB().ii(DUST.getMaterialIngredient(Wood, 4), DUST.getMaterialIngredient(Sulfur, 1), DUST.getMaterialIngredient(Sodium, 1)).fi(Glue.getLiquid(oneThird)).io(new ItemStack(SuperFuelBinder, 8)).add("super_fuel_binder_1", 40 * 20, 16);
        MIXER.RB().ii(DUST.getMaterialIngredient(Wood, 4), DUST.getMaterialIngredient(Sulfur, 1), DUST.getMaterialIngredient(Caesium, 1)).fi(Glue.getLiquid(oneThird)).io(new ItemStack(SuperFuelBinder, 12)).add("super_fuel_binder_2", 40 * 20, 16);
        MIXER.RB().ii(DUST.getMaterialIngredient(Wood, 4), DUST.getMaterialIngredient(Sulfur, 1), DUST.getMaterialIngredient(Lithium, 1)).fi(Lubricant.getLiquid(300)).io(new ItemStack(SuperFuelBinder, 8)).add("super_fuel_binder_3", 60 * 20, 16);
        MIXER.RB().ii(DUST.getMaterialIngredient(Wood, 4), DUST.getMaterialIngredient(Sulfur, 1), DUST.getMaterialIngredient(Sodium, 1)).fi(Lubricant.getLiquid(300)).io(new ItemStack(SuperFuelBinder, 8)).add("super_fuel_binder_4", 60 * 20, 16);
        MIXER.RB().ii(DUST.getMaterialIngredient(Wood, 4), DUST.getMaterialIngredient(Sulfur, 1), DUST.getMaterialIngredient(Caesium, 1)).fi(Lubricant.getLiquid(300)).io(new ItemStack(SuperFuelBinder, 12)).add("super_fuel_binder_5", 60 * 20, 16);
        MIXER.RB().ii(DUST.getMaterialIngredient(Wood, 4), DUST.getMaterialIngredient(Sulfur, 1), DUST.getMaterialIngredient(Lithium, 1)).fi(Creosote.getLiquid(1000)).io(new ItemStack(SuperFuelBinder, 8)).add("super_fuel_binder_6", 80 * 20, 16);
        MIXER.RB().ii(DUST.getMaterialIngredient(Wood, 4), DUST.getMaterialIngredient(Sulfur, 1), DUST.getMaterialIngredient(Sodium, 1)).fi(Creosote.getLiquid(1000)).io(new ItemStack(SuperFuelBinder, 8)).add("super_fuel_binder_7", 80 * 20, 16);
        MIXER.RB().ii(DUST.getMaterialIngredient(Wood, 4), DUST.getMaterialIngredient(Sulfur, 1), DUST.getMaterialIngredient(Caesium, 1)).fi(Creosote.getLiquid(1000)).io(new ItemStack(SuperFuelBinder, 12)).add("super_fuel_binder_8", 80 * 20, 16);
        MIXER.RB().ii(BLOCK.getMaterialIngredient(Lignite, 1), of(SuperFuelBinder, 6)).fi(HeavyFuel.getLiquid(1500)).io(SOLID_SUPER_FUEL.asItem()).add("solid_super_fuel_lignite", 120, 96);
        MIXER.RB().ii(BLOCK.getMaterialIngredient(Charcoal, 1), of(SuperFuelBinder, 4)).fi(HeavyFuel.getLiquid(1200)).io(SOLID_SUPER_FUEL.asItem()).add("solid_super_fuel_charcoal", 120, 96);
        MIXER.RB().ii(BLOCK.getMaterialIngredient(Coal, 1), of(SuperFuelBinder, 2)).fi(HeavyFuel.getLiquid(750)).io(SOLID_SUPER_FUEL.asItem()).add("solid_super_fuel_coal", 120, 96);
        MIXER.RB().ii(BLOCK.getMaterialIngredient(Lignite, 1), of(SuperFuelBinder, 6)).fi(LPG.getLiquid(1500)).io(SOLID_SUPER_FUEL.asItem()).add("solid_super_fuel_lignite_lpg", 120, 96);
        MIXER.RB().ii(BLOCK.getMaterialIngredient(Charcoal, 1), of(SuperFuelBinder, 4)).fi(LPG.getLiquid(1200)).io(SOLID_SUPER_FUEL.asItem()).add("solid_super_fuel_charcoal_lpg", 120, 96);
        MIXER.RB().ii(BLOCK.getMaterialIngredient(Coal, 1), of(SuperFuelBinder, 2)).fi(LPG.getLiquid(750)).io(SOLID_SUPER_FUEL.asItem()).add("solid_super_fuel_coal_lpg", 120, 96);
        MIXER.RB().ii(BLOCK.getMaterialIngredient(Lignite, 1), of(SuperFuelBinder, 6)).fi(CetaneBoostedDiesel.getLiquid(1000)).io(SOLID_SUPER_FUEL.asItem()).add("solid_super_fuel_lignite_nitro", 120, 96);
        MIXER.RB().ii(BLOCK.getMaterialIngredient(Charcoal, 1), of(SuperFuelBinder, 4)).fi(CetaneBoostedDiesel.getLiquid(800)).io(SOLID_SUPER_FUEL.asItem()).add("solid_super_fuel_charcoal_nitro", 120, 96);
        MIXER.RB().ii(BLOCK.getMaterialIngredient(Coal, 1), of(SuperFuelBinder, 2)).fi(CetaneBoostedDiesel.getLiquid(500)).io(SOLID_SUPER_FUEL.asItem()).add("solid_super_fuel_coal_nitro", 120, 96);
        MIXER.RB().ii(DUST.getMaterialIngredient(Wood, 1)).fi(Glue.getLiquid(25)).io(WoodPellet).add("wood_pellet", 16, 16);
    }

}
