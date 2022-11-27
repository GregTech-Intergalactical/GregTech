package muramasa.gregtech.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.gregtech.data.Materials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.stream.Collectors;

import static muramasa.antimatter.data.AntimatterMaterials.Blaze;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.*;

import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.MIXING;



public class MixerLoader {

    public static void init() {
        addDust(StainlessSteel, 7, 45*20);
        addDust(Nichrome, 7, 25*20);
        addDust(Invar, 7, 15*20);
        addDust(Bronze, 7, 10*20);
        addDust(FerriteMixture, 7, 30*20);
        addDust(IndiumGalliumPhosphide, 7, 20*20);
        addDust(Energium, 7, 10*20);
        addDust(GalliumArsenide, 7, 15*20);
        addDust(VanadiumSteel, 7, 50*20);
        addDust(CobaltBrass, 7, 45*20);
        addDust(BlueSteel, 7, 40*20);
        addDust(RedSteel, 7, 40*20);
        addDust(BlackSteel, 7, 25*20);
        addDust(SterlingSilver, 7, 25*20);
        addDust(RoseGold, 7, 25*20);
        addDust(BismuthBronze, 7, 25*20);
        addDust(Osmiridium, 7, 50*20);
        addDust(VanadiumGallium, 7, 20*20);
        addDust(Ultimet, 7, 45*20);
        addDust(TinAlloy, 7, 10*20);
        addDust(SodiumSulfide, 7, 3*20);
        addDust(Magnalium, 7, 20*20);
        addDust(Kanthal, 7, 15*20);
        addDust(Electrum, 7, 10*20);
        addDust(Brass, 7, 20*20);
        addDust(BatteryAlloy, 7, 25*20);
        addDust(Cupronickel, 7, 10*20);

        recipes();
    }

    private static void addDust(Material mat, int eut, int duration) {
        for (MaterialTypeItem<?> type : new MaterialTypeItem[]{AntimatterMaterialTypes.DUST, AntimatterMaterialTypes.DUST_SMALL, AntimatterMaterialTypes.DUST_TINY}) {
            List<Ingredient> ings = mat.getProcessInto().stream().map(t -> type.getMaterialIngredient(t.m, t.s)).collect(Collectors.toList());
            if (ings.size() == 0) return;
            int count = mat.getProcessInto().stream().mapToInt(t -> t.s).sum();
            ings.add(INT_CIRCUITS.get(4));
            MIXING.RB().ii(ings).io(type.get(mat,count)).add(duration,eut);
        }
    }

    private static void recipes() {
        MIXING.RB().fi(NitricAcid.getLiquid(1000),SulfuricAcid.getLiquid(1000)).fo(NitrationMixture.getLiquid(2000)).add(25 * 20, 2);
        MIXING.RB().fi(PolyvinylAcetate.getLiquid(1000),Acetone.getLiquid(1500)).fo(Glue.getLiquid(2500)).add(25 * 2, 8);
        MIXING.RB().fi(PolyvinylAcetate.getLiquid(1000),MethylAcetate.getLiquid(1500)).fo(Glue.getLiquid(2500)).add(25 * 2, 8);
        MIXING.RB().fi(BioDiesel.getLiquid(1000),Tetranitromethane.getLiquid(40)).fo(NitroFuel.getLiquid(750)).add(1 * 20, 480);
        MIXING.RB().fi(Diesel.getLiquid(1000),Tetranitromethane.getLiquid(20)).fo(NitroFuel.getLiquid(1000)).add(1 * 20, 480);
        MIXING.RB().fi(Oxygen.getGas(1000),Dimethylhydrazine.getLiquid(1000)).fo(RocketFuel.getLiquid(3000)).add(3 * 20, 16);
        MIXING.RB().fi(DinitrogenTetroxide.getGas(1000),Dimethylhydrazine.getLiquid(1000)).fo(RocketFuel.getLiquid(6000)).add(3 * 20, 16);
        MIXING.RB().fi(LightFuel.getLiquid(5000),HeavyFuel.getLiquid(1000)).fo(Diesel.getLiquid(6000)).add(8 * 2, 120);
        MIXING.RB().ii(of(AntimatterMaterialTypes.DUST.get(WoodPulp,4))).fi(SulfuricAcid.getLiquid(1000)).io(Items.CHARCOAL.getDefaultInstance()).fo(DilutedSulfuricAcid.getLiquid(1000)).add(60*20, 2);
        MIXING.RB().ii(of(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Sugar,4))).fi(SulfuricAcid.getLiquid(1000)).io(Items.CHARCOAL.getDefaultInstance()).fo(DilutedSulfuricAcid.getLiquid(1000)).add(60*20, 2);
        MIXING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Salt,2))).fi(AntimatterMaterials.Water.getLiquid(1000)).fo(SaltWater.getLiquid(1000)).add(2*20, 8);
        MIXING.RB().ii(of(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Copper,3)),of(AntimatterMaterialTypes.DUST.get(Barium,2)),of(AntimatterMaterialTypes.DUST.get(Yttrium,1))).fi(Oxygen.getGas(7000)).io(AntimatterMaterialTypes.DUST.get(YttriumBariumCuprate,13)).add(40*20, 8);
        MIXING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Talc,1))).fi(Oil.getLiquid(750)).fo(Lubricant.getLiquid(750)).add(64*2, 4);
        MIXING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Talc,1))).fi(Creosote.getLiquid(750)).fo(Lubricant.getLiquid(750)).add(64*2, 4);
        MIXING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Talc,1))).fi(SeedOil.getLiquid(750)).fo(Lubricant.getLiquid(750)).add(64*2, 4);
        MIXING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Soapstone,1))).fi(Oil.getLiquid(750)).fo(Lubricant.getLiquid(750)).add(64*2, 4);
        MIXING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Soapstone,1))).fi(Creosote.getLiquid(750)).fo(Lubricant.getLiquid(750)).add(64*2, 4);
        MIXING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Soapstone,1))).fi(SeedOil.getLiquid(750)).fo(Lubricant.getLiquid(750)).add(64*2, 4);
        MIXING.RB().ii(of(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Redstone,1))).fi(Oil.getLiquid(750)).fo(Lubricant.getLiquid(750)).add(64*2, 4);
        MIXING.RB().ii(of(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Redstone,1))).fi(Creosote.getLiquid(750)).fo(Lubricant.getLiquid(750)).add(64*2, 4);
        MIXING.RB().ii(of(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Redstone,1))).fi(SeedOil.getLiquid(750)).fo(Lubricant.getLiquid(750)).add(64*2, 4);
        MIXING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Glass,7)),of(AntimatterMaterialTypes.DUST.get(Boron,1))).io(AntimatterMaterialTypes.DUST.get(BorosilicateGlass,8)).add(40*20, 8);
        MIXING.RB().ii(of(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.EnderPearl,1)),of(AntimatterMaterialTypes.DUST.get(Blaze,1))).io(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.EnderEye,1)).add(5*20, 8);
        MIXING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Saltpeter,2)),of(AntimatterMaterialTypes.DUST.get(Sulfur,1)),of(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Coal,1))).io(Items.GUNPOWDER).add(20*20, 8);
        MIXING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Saltpeter,2)),of(AntimatterMaterialTypes.DUST.get(Sulfur,1)),of(AntimatterMaterialTypes.DUST.get(Materials.Charcoal,1))).io(new ItemStack(Items.GUNPOWDER,2)).add(15*20, 8);
        MIXING.RB().ii(of(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Stone,1))).fi(Lubricant.getLiquid(20), AntimatterMaterials.Water.getLiquid(4980)).fo(DrillingFluid.getLiquid(5000)).add(32*2, 16);
        MIXING.RB().ii(of(AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Stone,3)),of(AntimatterMaterialTypes.DUST.get(Clay,1))).fi(AntimatterMaterials.Water.getLiquid(500)).fo(Concrete.getLiquid(576)).add(1*20, 16);
    }

}
