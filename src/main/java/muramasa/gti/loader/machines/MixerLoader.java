package muramasa.gti.loader.machines;

import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTypeFluid;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gti.data.Materials;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;
import java.util.stream.Collectors;

import static muramasa.antimatter.Data.*;
import static muramasa.gti.data.RecipeMaps.CHEMICAL_REACTING;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.*;

import static muramasa.gti.data.TierMaps.INT_CIRCUITS;
import static muramasa.gti.data.Materials.*;
import static muramasa.gti.data.RecipeMaps.MIXING;



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
        for (MaterialTypeItem<?> type : new MaterialTypeItem[]{DUST, DUST_SMALL, DUST_TINY}) {
            List<RecipeIngredient> ings = mat.getProcessInto().stream().map(t -> type.getMaterialIngredient(t.m, t.s)).collect(Collectors.toList());
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
        MIXING.RB().fi(LightDiesel.getLiquid(5000),HeavyDiesel.getLiquid(1000)).fo(Diesel.getLiquid(6000)).add(8 * 2, 120);
        MIXING.RB().ii(of(DUST.get(WoodPulp,4))).fi(SulfuricAcid.getLiquid(1000)).io(Items.CHARCOAL.getDefaultInstance()).fo(DilutedSulfuricAcid.getLiquid(1000)).add(60*20, 2);
        MIXING.RB().ii(of(DUST.get(Sugar,4))).fi(SulfuricAcid.getLiquid(1000)).io(Items.CHARCOAL.getDefaultInstance()).fo(DilutedSulfuricAcid.getLiquid(1000)).add(60*20, 2);
        MIXING.RB().ii(of(DUST.get(Salt,2))).fi(Water.getLiquid(1000)).fo(SaltWater.getLiquid(1000)).add(2*20, 8);
        MIXING.RB().ii(of(DUST.get(Copper,3)),of(DUST.get(Barium,2)),of(DUST.get(Yttrium,1))).fi(Oxygen.getGas(7000)).io(DUST.get(YttriumBariumCuprate,13)).add(40*20, 8);
        MIXING.RB().ii(of(DUST.get(Talc,1))).fi(Oil.getLiquid(750)).fo(Lubricant.getLiquid(750)).add(64*2, 4);
        MIXING.RB().ii(of(DUST.get(Talc,1))).fi(Creosote.getLiquid(750)).fo(Lubricant.getLiquid(750)).add(64*2, 4);
        MIXING.RB().ii(of(DUST.get(Talc,1))).fi(SeedOil.getLiquid(750)).fo(Lubricant.getLiquid(750)).add(64*2, 4);
        MIXING.RB().ii(of(DUST.get(Soapstone,1))).fi(Oil.getLiquid(750)).fo(Lubricant.getLiquid(750)).add(64*2, 4);
        MIXING.RB().ii(of(DUST.get(Soapstone,1))).fi(Creosote.getLiquid(750)).fo(Lubricant.getLiquid(750)).add(64*2, 4);
        MIXING.RB().ii(of(DUST.get(Soapstone,1))).fi(SeedOil.getLiquid(750)).fo(Lubricant.getLiquid(750)).add(64*2, 4);
        MIXING.RB().ii(of(DUST.get(Redstone,1))).fi(Oil.getLiquid(750)).fo(Lubricant.getLiquid(750)).add(64*2, 4);
        MIXING.RB().ii(of(DUST.get(Redstone,1))).fi(Creosote.getLiquid(750)).fo(Lubricant.getLiquid(750)).add(64*2, 4);
        MIXING.RB().ii(of(DUST.get(Redstone,1))).fi(SeedOil.getLiquid(750)).fo(Lubricant.getLiquid(750)).add(64*2, 4);
        MIXING.RB().ii(of(DUST.get(Glass,7)),of(DUST.get(Boron,1))).io(DUST.get(BorosilicateGlass,8)).add(40*20, 8);
        MIXING.RB().ii(of(DUST.get(EnderPearl,1)),of(DUST.get(Materials.Blaze,1))).io(DUST.get(EnderEye,1)).add(5*20, 8);
        MIXING.RB().ii(of(DUST.get(Saltpeter,2)),of(DUST.get(Sulfur,1)),of(DUST.get(Coal,1))).io().add(20*20, 8);
        MIXING.RB().ii(of(DUST.get(Saltpeter,2)),of(DUST.get(Sulfur,1)),of(DUST.get(Materials.Charcoal,1))).io(new ItemStack(Items.GUNPOWDER,2)).add(15*20, 8);
        MIXING.RB().ii(of(DUST.get(Stone,1))).fi(Lubricant.getLiquid(20),Water.getLiquid(4980)).fo(DrillingFluid.getLiquid(5000)).add(32*2, 16);
        MIXING.RB().ii(of(DUST.get(Stone,3)),of(DUST.get(Clay,1))).fi(Water.getLiquid(500)).fo(Concrete.getLiquid(576)).add(1*20, 16);
    }

}
