package muramasa.gti.datagen;

import muramasa.gti.Ref;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.util.ResourceLocation;
import java.util.function.Consumer;

import static muramasa.antimatter.datagen.providers.AntimatterAdvancementProvider.*;
import static muramasa.antimatter.material.MaterialType.*;
import static muramasa.antimatter.util.Utils.getForgeItemTag;
import static muramasa.antimatter.util.Utils.hasItem;
import static muramasa.gti.data.Materials.*;

public class ProgressionAdvancements implements Consumer<Consumer<Advancement>> {

    public static Advancement progressionRoot;

    @Override
    public void accept(Consumer<Advancement> consumer) {
        progressionRoot = buildRootAdvancement(ROCK.get(Flint), new ResourceLocation("textures/gui/advancements/backgrounds/stone.png"),
                        Ref.ID + ".advancements.greg", Ref.ID + ".advancements.greg.desc", FrameType.TASK, true, true, false)
                        .withCriterion("has_rocks", hasItem(getForgeItemTag("rocks"))).register(consumer, "progression/root");
    }

}
