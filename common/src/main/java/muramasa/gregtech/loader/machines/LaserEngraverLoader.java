package muramasa.gregtech.loader.machines;

import io.github.gregtechintergalactical.gtcore.data.GTCoreItems;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.RecipeMaps;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.gregtech.data.Materials.*;

public class LaserEngraverLoader {
    public static void init(){
        RecipeMaps.LASER_ENGRAVING.RB().ii(RecipeIngredient.of(GTCoreItems.LapotronCrystal, 1), LENS.getMaterialIngredient(Sapphire, 1).setNoConsume().setNoConsume()).io(new ItemStack(GregTechData.EngravedLapotronChip, 3)).add("engraved_lapotron_chip_sapphire", 256, 480);
        RecipeMaps.LASER_ENGRAVING.RB().ii(RecipeIngredient.of(GTCoreItems.LapotronCrystal, 1), LENS.getMaterialIngredient(BlueTopaz, 1).setNoConsume().setNoConsume()).io(new ItemStack(GregTechData.EngravedLapotronChip, 3)).add("engraved_lapotron_chip_blue_topaz", 256, 480);
        RecipeMaps.LASER_ENGRAVING.RB().ii(RecipeIngredient.of(GTCoreItems.LapotronCrystal, 1), LENS.getMaterialIngredient(Opal, 1).setNoConsume().setNoConsume()).io(new ItemStack(GregTechData.EngravedLapotronChip, 3)).add("engraved_lapotron_chip_opal", 256, 480);
        RecipeMaps.LASER_ENGRAVING.RB().ii(PLATE.getMaterialIngredient(Emerald, 1), LENS.getMaterialIngredient(Emerald, 1).setNoConsume()).io(new ItemStack(GregTechData.EngravedCrystalChip)).add("engraved_crystal_chip_emerald", 256, 480);
        RecipeMaps.LASER_ENGRAVING.RB().ii(PLATE.getMaterialIngredient(Olivine, 1), LENS.getMaterialIngredient(Emerald, 1).setNoConsume()).io(new ItemStack(GregTechData.EngravedCrystalChip)).add("engraved_crystal_chip_olivine", 256, 480);
        RecipeMaps.LASER_ENGRAVING.RB().ii(FOIL.getMaterialIngredient(Copper, 1), LENS.getMaterialIngredient(Ruby, 1).setNoConsume()).io(new ItemStack(GregTechData.EtchedWiringMV)).add("etched_wiring_mv_1", 64, 30);
        RecipeMaps.LASER_ENGRAVING.RB().ii(FOIL.getMaterialIngredient(AnnealedCopper, 1), LENS.getMaterialIngredient(Ruby, 1).setNoConsume()).io(new ItemStack(GregTechData.EtchedWiringMV)).add("etched_wiring_mv_2", 64, 30);
        RecipeMaps.LASER_ENGRAVING.RB().ii(FOIL.getMaterialIngredient(Copper, 1), LENS.getMaterialIngredient(RedGarnet, 1).setNoConsume()).io(new ItemStack(GregTechData.EtchedWiringMV)).add("etched_wiring_mv_3", 64, 30);
        RecipeMaps.LASER_ENGRAVING.RB().ii(FOIL.getMaterialIngredient(AnnealedCopper, 1), LENS.getMaterialIngredient(RedGarnet, 1).setNoConsume()).io(new ItemStack(GregTechData.EtchedWiringMV)).add("etched_wiring_mv_4", 64, 30);
        RecipeMaps.LASER_ENGRAVING.RB().ii(FOIL.getMaterialIngredient(Gold, 1), LENS.getMaterialIngredient(Ruby, 1).setNoConsume()).io(new ItemStack(GregTechData.EtchedWiringHV)).add("etched_wiring_hv_1", 64, 120);
        RecipeMaps.LASER_ENGRAVING.RB().ii(FOIL.getMaterialIngredient(Electrum, 1), LENS.getMaterialIngredient(Ruby, 1).setNoConsume()).io(new ItemStack(GregTechData.EtchedWiringHV)).add("etched_wiring_hv_2", 64, 120);
        RecipeMaps.LASER_ENGRAVING.RB().ii(FOIL.getMaterialIngredient(Gold, 1), LENS.getMaterialIngredient(RedGarnet, 1).setNoConsume()).io(new ItemStack(GregTechData.EtchedWiringHV)).add("etched_wiring_hv_3", 64, 120);
        RecipeMaps.LASER_ENGRAVING.RB().ii(FOIL.getMaterialIngredient(Electrum, 1), LENS.getMaterialIngredient(RedGarnet, 1).setNoConsume()).io(new ItemStack(GregTechData.EtchedWiringHV)).add("etched_wiring_hv_4", 64, 120);
        RecipeMaps.LASER_ENGRAVING.RB().ii(FOIL.getMaterialIngredient(Platinum, 1), LENS.getMaterialIngredient(Ruby, 1).setNoConsume()).io(new ItemStack(GregTechData.EtchedWiringEV)).add("etched_wiring_ev_1", 64, 480);
        RecipeMaps.LASER_ENGRAVING.RB().ii(FOIL.getMaterialIngredient(Platinum, 1), LENS.getMaterialIngredient(RedGarnet, 1).setNoConsume()).io(new ItemStack(GregTechData.EtchedWiringEV)).add("etched_wiring_ev_2", 64, 480);
    }
}
