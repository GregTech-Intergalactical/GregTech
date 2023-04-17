package muramasa.gregtech.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.gregtech.GTIRef;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class GregTechSounds {

    public static void init() {

    }

    public static final SoundEvent EXTRACTOR = AntimatterAPI.register(SoundEvent.class, GTIRef.ID, "extractor", new SoundEvent(new ResourceLocation(GTIRef.ID, "extractor")));
    public static final SoundEvent MACERATOR = AntimatterAPI.register(SoundEvent.class, GTIRef.ID, "macerator", new SoundEvent(new ResourceLocation(GTIRef.ID, "macerator")));
    public static final SoundEvent MAGNETIZER = AntimatterAPI.register(SoundEvent.class, GTIRef.ID, "magnetizer", new SoundEvent(new ResourceLocation(GTIRef.ID, "magnetizer")));
    public static final SoundEvent FURNACE = AntimatterAPI.register(SoundEvent.class, GTIRef.ID, "furnace", new SoundEvent(new ResourceLocation(GTIRef.ID, "furnace")));
    public static final SoundEvent TREETAP = AntimatterAPI.register(SoundEvent.class, GTIRef.ID, "treetap", new SoundEvent(new ResourceLocation(GTIRef.ID, "treetap")));
}
