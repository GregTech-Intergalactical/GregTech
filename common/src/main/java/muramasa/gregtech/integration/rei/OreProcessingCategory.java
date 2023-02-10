package muramasa.gregtech.integration.rei;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import muramasa.gregtech.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

public class OreProcessingCategory implements DisplayCategory<OreProcessingDisplay> {
    protected static Renderer icon = EntryStacks.of(Items.IRON_ORE);
    private static Component title = new TranslatableComponent(Ref.ID + ".rei.tooltip.ore.byproducts");

    @Override
    public CategoryIdentifier<? extends OreProcessingDisplay> getCategoryIdentifier() {
        return null;
    }

    @Override
    public List<Widget> setupDisplay(OreProcessingDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createDrawableWidget((helper, matrices, mouseX, mouseY, delta) -> {
            drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/ore_byproducts/background.png"), bounds.x + 3, bounds.y + 3, 3 + 1, 3 + 1, bounds.getWidth() - 6, bounds.getHeight() - 6);
            drawTexture(matrices, new ResourceLocation(Ref.ID, "textures/gui/ore_byproducts/base.png"), bounds.x + 3, bounds.y + 3, 3 + 1, 3 + 1, bounds.getWidth() - 6, bounds.getHeight() - 6);
        }));
        //widgets.addAll(setupSlots(display, bounds));
        return widgets;
    }

    private static void drawTexture(PoseStack stack, ResourceLocation loc, int left, int top, int x, int y, int sizeX, int sizeY) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, loc);
        //AbstractGui.blit(stack, left, top, x, y, sizeX, sizeY);
        GuiComponent.blit(stack, left, top, 0, x, y, sizeX, sizeY, 256, 256);
    }

    @Override
    public Component getTitle() {
        return title;
    }

    @Override
    public Renderer getIcon() {
        return icon;
    }
}
