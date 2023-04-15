package muramasa.gregtech.datagen;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.gregtech.GTIRef;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

import static muramasa.antimatter.data.AntimatterMaterials.Flint;
import static muramasa.antimatter.datagen.providers.AntimatterAdvancementProvider.buildRootAdvancement;
import static muramasa.antimatter.datagen.providers.AntimatterAdvancementProvider.getLoc;
import static muramasa.antimatter.util.TagUtils.getForgelikeItemTag;
import static muramasa.antimatter.util.Utils.hasItem;

public class ProgressionAdvancements implements Consumer<Consumer<Advancement>> {

    public static Advancement progressionRoot;

    @Override
    public void accept(Consumer<Advancement> consumer) {
        progressionRoot = buildRootAdvancement(AntimatterMaterialTypes.ROCK.get(Flint), new ResourceLocation("textures/gui/advancements/backgrounds/stone.png"),
                GTIRef.ID + ".advancements.greg", GTIRef.ID + ".advancements.greg.desc", FrameType.TASK, true, true, false)
                .addCriterion("has_rocks", hasItem(getForgelikeItemTag("rocks"))).save(consumer, getLoc(GTIRef.ID, "progression/root"));
    }

}
