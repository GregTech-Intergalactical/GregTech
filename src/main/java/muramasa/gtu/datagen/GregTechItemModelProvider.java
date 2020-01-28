package muramasa.gtu.datagen;

import muramasa.antimatter.datagen.providers.AntimatterItemModelProvider;
import muramasa.gtu.Ref;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

public class GregTechItemModelProvider extends AntimatterItemModelProvider {

    public GregTechItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(Ref.ID, Ref.NAME + " Item Models", generator, existingFileHelper);
    }
}
