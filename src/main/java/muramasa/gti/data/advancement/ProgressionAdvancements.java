package muramasa.gti.data.advancement;

import muramasa.antimatter.advancement.trigger.TagSensitiveInventoryChangedTrigger;
import muramasa.gti.Ref;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.util.ResourceLocation;
import java.util.function.Consumer;

import static muramasa.antimatter.datagen.providers.AntimatterAdvancementProvider.*;
import static muramasa.antimatter.materials.MaterialType.*;
import static muramasa.antimatter.util.Utils.getForgeItemTag;
import static muramasa.gti.data.Materials.*;

public class ProgressionAdvancements implements Consumer<Consumer<Advancement>> {

    @Override
    public void accept(Consumer<Advancement> consumer) {
        Advancement greg = buildRootAdvancement(/*Data.DEBUG_SCANNER*/ROCK.get(Flint),
                new ResourceLocation("textures/gui/advancements/backgrounds/stone.png"),
            Ref.ID + ".advancements.greg", Ref.ID + ".advancements.greg.desc", FrameType.TASK, true, true, false)
                .withCriterion("has_rocks", new TagSensitiveInventoryChangedTrigger.Instance(getForgeItemTag("rocks")))
                .register(consumer, "progression/root");
    }

}
