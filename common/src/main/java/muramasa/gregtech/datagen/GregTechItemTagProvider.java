package muramasa.gregtech.datagen;

import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemTagProvider;
import net.minecraft.world.item.Items;

import static io.github.gregtechintergalactical.gtcore.data.GTCoreTags.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.Materials.*;

public class GregTechItemTagProvider  extends AntimatterItemTagProvider {
    public GregTechItemTagProvider(String providerDomain, String providerName, boolean replace, AntimatterBlockTagProvider p) {
        super(providerDomain, providerName, replace, p);
    }

    @Override
    protected void processTags(String domain) {
        super.processTags(domain);
        //this.tag(GregTechTags.CIRCUITS_EXTREME).add(GregTechData.CircuitDataStorage);
        this.tag(CIRCUITS_ELITE).add(NanoProcessor);
        this.tag(CIRCUITS_MASTER).add(QuantumProcessor);
        this.tag(RESISTORS).add(Resistor, SMDResistor);
        this.tag(CAPACITORS).add(Capacitor, SMDCapacitor);
        this.tag(TRANSISTORS).add(Transistor, SMDTransistor);
        this.tag(DIODES).add(Diode, SMDDiode);
        this.tag(FIRESTARTER).add(Items.FLINT_AND_STEEL);
        this.tag(GEM.getMaterialTag(Amethyst)).remove(Items.AMETHYST_SHARD);
        this.tag(GEM.getTag()).remove(Items.AMETHYST_SHARD);
        this.tag(BLOCK.getMaterialTag(Amethyst)).remove(Items.AMETHYST_BLOCK);
        this.tag(GEM.getMaterialTag(Quartz)).add(GEM.get(MilkyQuartz));
        this.tag(PLATES_IRON_ALUMINIUM).addTag(PLATE.getMaterialTag(Iron)).addTag(PLATE.getMaterialTag(WroughtIron)).addTag(PLATE.getMaterialTag(Aluminium));
        this.tag(DUST_LAPIS_LAZURITE).addTag(DUST.getMaterialTag(Lapis)).addTag(DUST.getMaterialTag(Lazurite));
        this.tag(DUST_COALS).addTag(DUST.getMaterialTag(Coal)).addTag(DUST.getMaterialTag(Charcoal)).addTag(DUST.getMaterialTag(Carbon));
    }
}
