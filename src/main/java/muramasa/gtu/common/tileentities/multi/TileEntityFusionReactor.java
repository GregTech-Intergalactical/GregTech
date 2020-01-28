package muramasa.gtu.common.tileentities.multi;

import muramasa.antimatter.tileentities.multi.TileEntityMultiMachine;

public class TileEntityFusionReactor extends TileEntityMultiMachine {

    @Override
    public void onRecipeFound() {
        consumeEnergy(activeRecipe.getSpecialValue());
        System.out.println("Consumed Starting Energy");
    }
}
