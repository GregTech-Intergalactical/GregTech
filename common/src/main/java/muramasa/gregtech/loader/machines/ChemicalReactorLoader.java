package muramasa.gregtech.loader.machines;


import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.item.ItemBattery;
import muramasa.antimatter.item.ItemFluidCell;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.GregTechData;
import muramasa.antimatter.material.Material;
import net.minecraft.world.item.ItemStack;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.TierMaps.INT_CIRCUITS;

import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.CHEMICAL_REACTING;

public class ChemicalReactorLoader {
    public static void init() {
        batteries();
        titanium();
        sulfuric();
        organicChemistry();
        inorganicChemistry();
    }

    private static void organicChemistry() {
        rubber();
        plastics();
        miscCarbohydrates();
        alkaloidsToAlkanols();
        alkanolsToAlkenes();
    }

    private static void titanium() {
        CHEMICAL_REACTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Rutile, 1)),of(AntimatterMaterialTypes.DUST.get(Carbon, 2))).fi(Chlorine.getGas(288)).fo(Titaniumtetrachloride.getLiquid(144),CarbonMonoxide.getGas(288)).add(500, 480);
    }

    private static void sulfuric() {
        CHEMICAL_REACTING.RB().fi(SulfuricTrioxide.getGas(144), AntimatterMaterials.Water.getLiquid(144)).fo(SulfuricAcid.getLiquid(144)).add(320, 7);
        CHEMICAL_REACTING.RB().fi(SulfuricDioxide.getGas(288), Oxygen.getGas(288)).fo(SulfuricTrioxide.getGas(288)).add(200, 30);
        CHEMICAL_REACTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Sulfur, 1))).fi(Oxygen.getGas(144)).fo(SulfuricDioxide.getGas(144)).add(60, 7);
        CHEMICAL_REACTING.RB().fi(HydrogenSulfide.getGas(288), Oxygen.getGas(3*144)).fo(SulfuricDioxide.getGas(288), AntimatterMaterials.Water.getLiquid(288)).add(120, 30);
        CHEMICAL_REACTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Sulfur,1))).fi(Hydrogen.getGas(144)).fo(HydrogenSulfide.getGas(144)).add(60, 8);
    }

    private static void batteries() {
        CHEMICAL_REACTING.RB().ii(of(GregTechData.BatteryHullSmall, 1)).fi(SulfuricAcid.getLiquid(1000)).io(ItemBattery.getFilledBattery(GregTechData.BatterySmallAcid)).add(40, 2);
        CHEMICAL_REACTING.RB().ii(of(GregTechData.BatteryHullMedium, 1)).fi(SulfuricAcid.getLiquid(4000)).io(ItemBattery.getFilledBattery(GregTechData.BatteryMediumAcid)).add(40, 2);
        CHEMICAL_REACTING.RB().ii(of(GregTechData.BatteryHullLarge, 1)).fi(SulfuricAcid.getLiquid(16000)).io(ItemBattery.getFilledBattery(GregTechData.BatteryLargeAcid)).add(40, 2);
    }

    private static void rubber() {
        CHEMICAL_REACTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Sulfur), 1), of(AntimatterMaterialTypes.DUST.get(RawRubber), 9)).fo(Rubber.getLiquid(144 * 9)).add(200, 8);
        CHEMICAL_REACTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Sulfur), 1), of(AntimatterMaterialTypes.DUST.get(Polydimethylsiloxane), 9)).fo(SiliconRubber.getLiquid(144 * 9)).add(200, 32);
        CHEMICAL_REACTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Sulfur), 1), of(AntimatterMaterialTypes.DUST.get(RawStyreneButadieneRubber), 9)).fo(StyreneButadieneRubber.getLiquid(144 * 9)).add(200, 128);
        CHEMICAL_REACTING.RB().fi(Air.getGas(2000),Isoprene.getLiquid(144)).io(new ItemStack(AntimatterMaterialTypes.DUST.get(RawRubber),1)).add(160, 30);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(2000),Isoprene.getLiquid(144)).io(new ItemStack(AntimatterMaterialTypes.DUST.get(RawRubber),3)).add(160, 30);
        CHEMICAL_REACTING.RB().fi(Air.getGas(2000),Styrene.getLiquid(36),Butadiene.getGas(108)).io(new ItemStack(AntimatterMaterialTypes.DUST.get(RawStyreneButadieneRubber),1)).add(160, 240);
        CHEMICAL_REACTING.RB().fi(Oxygen.getGas(2000),Styrene.getLiquid(36),Butadiene.getGas(108)).io(new ItemStack(AntimatterMaterialTypes.DUST.get(RawStyreneButadieneRubber),3)).add(160, 240);
    }


    private static void plastics() {
        AntimatterAPI.all(ItemFluidCell.class, cell -> CHEMICAL_REACTING.RB().ii(of(cell.fill(Oxygen.getGas()))).fi(Ethylene.getGas(144)).fo(Polyethylene.getLiquid(144)).add(8 * 20, 30));
        CHEMICAL_REACTING.RB().fi(Chloroform.getLiquid(1000), HydrofluoricAcid.getLiquid(2000)).fo(Tetrafluoroethylene.getGas(500), DilutedHydrochloricAcid.getLiquid(3000)).add(240, 240);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(1000), Fluorine.getGas(1000)).fo(HydrofluoricAcid.getLiquid(1000)).add(60, 7);
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(6000), Methane.getGas(1000)).fo(Chloroform.getLiquid(1000), HydrofluoricAcid.getLiquid(3000)).add(80, 30);
        CHEMICAL_REACTING.RB().ii(AntimatterMaterialTypes.DUST.getMaterialIngredient(Carbon, 1)).fi(Hydrogen.getGas(4000)).fo(Methane.getGas(1000)).add(200, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Air.getGas(1000),Ethylene.getGas(144)).fo(Polyethylene.getLiquid(144)).add(160, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Oxygen.getGas(1000),Ethylene.getGas(144)).fo(Polyethylene.getLiquid(216)).add(160, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Air.getGas(1000),VinylChloride.getGas(144)).io(AntimatterMaterialTypes.DUST.get(PolyvinylChloride,1)).add(160, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Oxygen.getGas(2000),VinylChloride.getGas(288)).io(AntimatterMaterialTypes.DUST.get(PolyvinylChloride,3)).add(160, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Air.getGas(1000),Tetrafluoroethylene.getGas(144)).io(AntimatterMaterialTypes.DUST.get(Polytetrafluoroethylene,1)).add(160, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Oxygen.getGas(2000),Tetrafluoroethylene.getGas(288)).io(AntimatterMaterialTypes.DUST.get(Polytetrafluoroethylene,3)).add(160, 30);
    }

    private static void miscCarbohydrates() {
        CHEMICAL_REACTING.RB().ii(AntimatterMaterialTypes.DUST.getMaterialIngredient(Carbon, 1)).fi(Hydrogen.getGas(144*4)).fo(Methane.getGas(144)).add(200, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Propene.getGas(288)).fo(Methane.getGas(144),Isoprene.getLiquid(1000)).add(120, 30);
        CHEMICAL_REACTING.RB().fi(Propene.getGas(144),Ethylene.getGas(144)).fo(Hydrogen.getGas(288),Isoprene.getLiquid(144)).add(120, 30);
        CHEMICAL_REACTING.RB().fi(Phenol.getLiquid(288),Acetone.getLiquid(144),HydrochloricAcid.getLiquid(144)).fo(BisphenolA.getLiquid(144),DilutedHydrochloricAcid.getLiquid(1000)).add(160, 30);
        CHEMICAL_REACTING.RB().fi(Heptanol.getLiquid(144),Epichlorohydrin.getLiquid(144)).ii(of(AntimatterMaterialTypes.DUST.get(SodiumHydroxide,1))).fo(SaltWater.getLiquid(144)).io(AntimatterMaterialTypes.INGOT.get(Epoxid,5)).add(160, 30);
        CHEMICAL_REACTING.RB().fi(Dichlorobenzene.getLiquid(144),Epichlorohydrin.getLiquid(144)).ii(of(AntimatterMaterialTypes.DUST.get(SodiumSulfide,1))).fo(PolyphenyleneSulfide.getLiquid(144)).io(AntimatterMaterialTypes.DUST.get(Salt,5)).add(160, 30);
        CHEMICAL_REACTING.RB().fi(Dimethyldichlorosilane.getLiquid(144), AntimatterMaterials.Water.getLiquid(288)).fo(HydrochloricAcid.getLiquid(288)).io(AntimatterMaterialTypes.DUST.get(Polydimethylsiloxane,1)).add(240, 96);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(3).setNoConsume()).fi(Methane.getGas(144),Chlorine.getGas(3*144)).fo(HydrochloricAcid.getLiquid(3*144),Chloroform.getLiquid(144)).add(80, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Methane.getGas(144),Chlorine.getGas(144)).fo(HydrochloricAcid.getLiquid(144),Chloromethane.getGas(144)).add(80, 30);
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(288),Benzene.getLiquid(144)).fo(Dichlorobenzene.getLiquid(144),HydrochloricAcid.getLiquid(288)).add(120, 30);
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(144),Propene.getGas(144)).fo(AllylChloride.getLiquid(144),HydrochloricAcid.getLiquid(144)).add(160, 30);
        CHEMICAL_REACTING.RB().fi(AceticAcid.getLiquid(144),SulfuricAcid.getLiquid(144)).fo(Ethenone.getGas(144),DilutedSulfuricAcid.getLiquid(144)).add(160, 120);
        CHEMICAL_REACTING.RB().fi(AceticAcid.getLiquid(144),Methanol.getLiquid(144)).fo(MethylAcetate.getLiquid(144), AntimatterMaterials.Water.getLiquid(144)).add(240, 30);
        CHEMICAL_REACTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(SodiumHydroxide,1))).fi(Glycerol.getLiquid(144),HydrochloricAcid.getLiquid(144)).io(AntimatterMaterialTypes.DUST.get(Salt,1)).fo(Epichlorohydrin.getLiquid(144), AntimatterMaterials.Water.getLiquid(288)).add(480, 30);
        CHEMICAL_REACTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(SodiumHydroxide),1)).fi(HypochlorousAcid.getLiquid(144),AllylChloride.getLiquid(144)).fo(Epichlorohydrin.getLiquid(144),SaltWater.getLiquid(144)).add(480, 30);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(3*144),CarbonDioxide.getGas(144)).fo(Methanol.getLiquid(144), AntimatterMaterials.Water.getLiquid(144)).add(360, 96);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Hydrogen.getGas(288),CarbonMonoxide.getGas(288)).fo(AceticAcid.getLiquid(144)).add(320, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Hydrogen.getGas(288),CarbonMonoxide.getGas(144)).fo(Methanol.getLiquid(144)).add(300, 30);
        CHEMICAL_REACTING.RB().fi(Methanol.getLiquid(144),CarbonMonoxide.getGas(144)).fo(AceticAcid.getLiquid(144)).add(300, 30);
        CHEMICAL_REACTING.RB().fi(Chloramine.getLiquid(144),Dimethylamine.getLiquid(144)).fo(Dimethylhydrazine.getLiquid(144),HydrochloricAcid.getLiquid(144)).add(960, 480);
        CHEMICAL_REACTING.RB().fi(Ethylene.getGas(144),Benzene.getLiquid(144)).fo(Styrene.getLiquid(144),Hydrogen.getGas(144)).add(120, 30);
        CHEMICAL_REACTING.RB().fi(Propene.getGas(144),Benzene.getLiquid(144)).fo(Cumene.getLiquid(144)).add(600, 96);
        CHEMICAL_REACTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Silicon,1))).fi(Chloromethane.getLiquid(288)).fo(Dimethyldichlorosilane.getLiquid(144)).add(240, 96);
        CHEMICAL_REACTING.RB().fi(Ethylene.getGas(288),Oxygen.getGas(144),AceticAcid.getLiquid(288)).fo(VinylAcetate.getLiquid(288), AntimatterMaterials.Water.getLiquid(288)).add(180, 30);
        CHEMICAL_REACTING.RB().fi(Methanol.getLiquid(144),HydrochloricAcid.getLiquid(144)).fo(Chloromethane.getLiquid(144), AntimatterMaterials.Water.getLiquid(144)).add(160, 30);
        CHEMICAL_REACTING.RB().fi(Methane.getGas(144),Steam.getGas(288)).fo(CarbonDioxide.getGas(144),Hydrogen.getGas(4*144)).add(140, 480);
        CHEMICAL_REACTING.RB().fi(Benzene.getLiquid(288),Oxygen.getGas(144)).fo(Phenol.getLiquid(288)).add(240, 2000);
        CHEMICAL_REACTING.RB().fi(Glycerol.getLiquid(288),CarbonDioxide.getGas(288)).fo(Ethylene.getLiquid(4*144),Oxygen.getGas(5*144)).add(240, 200);
        CHEMICAL_REACTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(SodiumHydroxide),1).setNoConsume()).fi(MethylAcetate.getLiquid(144), AntimatterMaterials.Water.getLiquid(144)).fo(AceticAcid.getLiquid(144),Methanol.getLiquid(144)).add(260, 60);
    }

    private static void inorganicChemistry(){
        CHEMICAL_REACTING.RB().fi(Chlorine.getGas(144),Hydrogen.getGas(144)).fo(HydrochloricAcid.getLiquid(144)).add(60, 8);
        CHEMICAL_REACTING.RB().fi(SulfuricAcid.getLiquid(144)).ii(of(AntimatterMaterialTypes.DUST.get(Salt,1))).fo(HydrochloricAcid.getLiquid(288)).io(AntimatterMaterialTypes.DUST.get(SodiumSulfate,1)).add(60, 30);
        CHEMICAL_REACTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Sodium,2))).fi(AntimatterMaterials.Water.getLiquid(288)).fo(Hydrogen.getGas(144)).io(AntimatterMaterialTypes.DUST.get(SodiumHydroxide,2)).add(40, 8);
        CHEMICAL_REACTING.RB().fi(Nitrogen.getGas(144),Oxygen.getGas(288)).fo(NitrogenDioxide.getGas(288)).add(1200, 30);
        CHEMICAL_REACTING.RB().fi(Fluorine.getGas(144),Hydrogen.getGas(144)).fo(HydrofluoricAcid.getLiquid(288)).add(60, 8);
        CHEMICAL_REACTING.RB().fi(NitrogenDioxide.getGas(3*144), AntimatterMaterials.Water.getLiquid(144)).fo(NitricOxide.getGas(144),NitricAcid.getLiquid(288)).add(40, 240);
        CHEMICAL_REACTING.RB().fi(Ammonia.getGas(4*144),Oxygen.getGas(5*144)).fo(NitricOxide.getGas(4*144), AntimatterMaterials.Water.getLiquid(6*144)).add(320, 30);
        CHEMICAL_REACTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Carbon,1))).fi(CarbonDioxide.getGas(144)).fo(CarbonMonoxide.getGas(288)).add(800, 8);
        CHEMICAL_REACTING.RB().fi(Hydrogen.getGas(3*144),Nitrogen.getGas(288)).fo(Ammonia.getGas(288)).add(320, 384);
        CHEMICAL_REACTING.RB().fi(HypochlorousAcid.getLiquid(144),Ammonia.getGas(144)).fo(Chloramine.getLiquid(144), AntimatterMaterials.Water.getLiquid(144)).add(160, 30);
        CHEMICAL_REACTING.RB().fi(Methanol.getLiquid(288),Ammonia.getGas(144)).fo(Dimethylamine.getLiquid(144), AntimatterMaterials.Water.getLiquid(288)).add(240, 120);
        CHEMICAL_REACTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Phosphorus, 4))).fi(Oxygen.getGas(5*144)).io(AntimatterMaterialTypes.DUST.get(PhosphorousPentoxide, 1)).add(40, 30);
        CHEMICAL_REACTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Carbon,2)),INT_CIRCUITS.get(1).setNoConsume()).fi(Oxygen.getGas(144)).fo(CarbonMonoxide.getGas(288)).add(80, 8);
        CHEMICAL_REACTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Carbon,1)),INT_CIRCUITS.get(2).setNoConsume()).fi(Oxygen.getGas(144)).fo(CarbonDioxide.getGas(144)).add(80, 8);
        CHEMICAL_REACTING.RB().fi(NitricAcid.getLiquid(4*144),MethylAcetate.getLiquid(288)).io(AntimatterMaterialTypes.DUST.get(Carbon,5)).fo(Tetranitromethane.getLiquid(144), AntimatterMaterials.Water.getLiquid(8*144)).add(480, 120);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(1).setNoConsume()).fi(Oxygen.getGas(7*144),Ammonia.getGas(4*144)).fo(DinitrogenTetroxide.getGas(288), AntimatterMaterials.Water.getLiquid(6*144)).add(480, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(Oxygen.getGas(288),Ammonia.getGas(144)).fo(NitricAcid.getLiquid(144), AntimatterMaterials.Water.getLiquid(144)).add(480, 30);
        CHEMICAL_REACTING.RB().ii(INT_CIRCUITS.get(2).setNoConsume()).fi(NitrogenDioxide.getGas(288)).fo(DinitrogenTetroxide.getGas(144)).add(640, 30);
        CHEMICAL_REACTING.RB().fi(Methanol.getLiquid(144),SeedOil.getLiquid(6*144)).fo(Glycerol.getLiquid(144),BioDiesel.getLiquid(6*144)).add(600, 30);
        CHEMICAL_REACTING.RB().fi(Ethanol.getLiquid(144),SeedOil.getLiquid(6*144)).fo(Glycerol.getLiquid(144),BioDiesel.getLiquid(6*144)).add(600, 30);
        CHEMICAL_REACTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Carbon,2)),of(AntimatterMaterialTypes.DUST.get(Calcium,2))).fi(Oxygen.getGas(3*144)).io(AntimatterMaterialTypes.DUST.get(Calcite,2)).add(500, 30);
        CHEMICAL_REACTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Quicklime,1))).fi(CarbonDioxide.getGas(144)).io(AntimatterMaterialTypes.DUST.get(Calcite,1)).add(80, 30);
        CHEMICAL_REACTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(Magnesia,1))).fi(CarbonDioxide.getGas(144)).io(AntimatterMaterialTypes.DUST.get(Magnesite,1)).add(80, 30);
        CHEMICAL_REACTING.RB().ii(of(AntimatterMaterialTypes.DUST.get(SodiumHydroxide,4))).fi(Dichlorobenzene.getLiquid(288)).io(AntimatterMaterialTypes.DUST.get(Salt,4)).fo(Phenol.getLiquid(288),Oxygen.getGas(144)).add(120, 30);
    }

    private static void alkaloidsToAlkanols(){
        alkaneToAlkanol(Methane,Methanol,2,1);
        alkaneToAlkanol(Ethane,Ethanol,2,1,INT_CIRCUITS.get(1).setNoConsume());
        alkaneToAlkanol(Ethane,Ethanediol,1,1,INT_CIRCUITS.get(2).setNoConsume());
        alkaneToAlkanol(Propane,Propanol,2,1,INT_CIRCUITS.get(1).setNoConsume());
        alkaneToAlkanol(Propane,Propanediol,1,1,INT_CIRCUITS.get(2).setNoConsume());
        alkaneToAlkanol(Propene,Propenol,2,1);
        alkaneToAlkanol(Butane,Butanol,2,1,INT_CIRCUITS.get(1).setNoConsume());
        alkaneToAlkanol(Butane,Butanediol,1,1,INT_CIRCUITS.get(2).setNoConsume());
        alkaneToAlkanol(Butene,Butenol,2,1);
    }

    private static void alkanolsToAlkenes(){
        alkanolToAlkene(Ethanol,Ethylene,1,1);
        alkanolToAlkene(Propanol,Propene,1,1);
        alkanolToAlkene(Butane,Butene,1,1);
        alkanolToAlkene(Butanediol,Butadiene,1,2);
    }

    private static void alkaneToAlkanol(Material alkane, Material alkanol, int mol_alk, int mol_ox, RecipeIngredient... circuit){
        if(circuit!=null){
            if(alkane.has(AntimatterMaterialTypes.LIQUID)){
                CHEMICAL_REACTING.RB().ii(circuit).fi(alkane.getLiquid(mol_alk*144),Oxygen.getGas(mol_ox*144)).fo(alkanol.getLiquid(mol_alk*144)).add(200,50);
            }else if(alkane.has(AntimatterMaterialTypes.GAS)){
                CHEMICAL_REACTING.RB().ii(circuit).fi(alkane.getGas(mol_alk*144),Oxygen.getGas(mol_ox*144)).fo(alkanol.getLiquid(mol_alk*144)).add(200,50);
            }else{
                return;
            }
        }else{
            if(alkane.has(AntimatterMaterialTypes.LIQUID)){
                CHEMICAL_REACTING.RB().fi(alkane.getLiquid(mol_alk*144),Oxygen.getGas(mol_ox*144)).fo(alkanol.getLiquid(mol_alk*144)).add(200,50);
            }else if(alkane.has(AntimatterMaterialTypes.GAS)){
                CHEMICAL_REACTING.RB().fi(alkane.getGas(mol_alk*144),Oxygen.getGas(mol_ox*144)).fo(alkanol.getLiquid(mol_alk*144)).add(200,50);
            }else{
                return;
            }
        }
    }

    private static void alkanolToAlkene(Material alkanol, Material alkene, int mol_alk, int mol_sulf){
        if(alkene.has(AntimatterMaterialTypes.LIQUID)){
            CHEMICAL_REACTING.RB().fi(alkanol.getLiquid(mol_alk*144),SulfuricAcid.getLiquid(mol_sulf*144)).fo(alkene.getLiquid(mol_alk*144),DilutedSulfuricAcid.getLiquid(mol_sulf*144)).add(200,50);
        }else if(alkene.has(AntimatterMaterialTypes.GAS)){
            CHEMICAL_REACTING.RB().fi(alkanol.getLiquid(mol_alk*144),SulfuricAcid.getGas(mol_sulf*144)).fo(alkene.getLiquid(mol_alk*144),DilutedSulfuricAcid.getLiquid(mol_sulf*144)).add(200,50);
        }else{
            return;
        }
    }
}
