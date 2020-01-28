package muramasa.gtu.datagen;

import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.gtu.Ref;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

public class GregTechBlockStateProvider extends AntimatterBlockStateProvider {

    public GregTechBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(Ref.ID, Ref.NAME + " Blockstates", gen, exFileHelper);
    }
}
