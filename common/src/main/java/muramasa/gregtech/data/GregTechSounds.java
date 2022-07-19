package muramasa.gregtech.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.gregtech.Ref;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class GregTechSounds {

    public static void init() {

    }

    public static final SoundEvent EXTRACTOR = AntimatterAPI.register(SoundEvent.class, Ref.ID, "extractor", new SoundEvent(new ResourceLocation(Ref.ID, "extractor")));
    public static final SoundEvent PULVERIZER = AntimatterAPI.register(SoundEvent.class, Ref.ID, "pulverizer", new SoundEvent(new ResourceLocation(Ref.ID, "pulverizer")));
    public static final SoundEvent MAGNETIZER = AntimatterAPI.register(SoundEvent.class, Ref.ID, "magnetizer", new SoundEvent(new ResourceLocation(Ref.ID, "magnetizer")));
    public static final SoundEvent FURNACE = AntimatterAPI.register(SoundEvent.class, Ref.ID, "furnace", new SoundEvent(new ResourceLocation(Ref.ID, "furnace")));
    public static final SoundEvent TREETAP = AntimatterAPI.register(SoundEvent.class, Ref.ID, "treetap", new SoundEvent(new ResourceLocation(Ref.ID, "treetap")));
}
