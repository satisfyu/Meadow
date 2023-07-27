package net.satisfyu.meadow.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.client.screen.handler.FondueGuiHandler;

public class FondueGui extends AbstractContainerScreen<FondueGuiHandler> {

    private final ResourceLocation background = new ResourceLocation(Meadow.MOD_ID, "textures/gui/fondue_gui.png");

    public FondueGui(FondueGuiHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }


    private void renderProgressArrow(GuiGraphics context, int x, int y) {
        int progress = this.menu.getScaledProgress();
        context.blit(background, x + 87, y + 25, 176, 17, progress, 14); //Position Arrow
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        renderTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        titleLabelX = (imageWidth - font.width(title)) / 2;
    }

    @Override
    protected void renderBg(GuiGraphics context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, background);

        final int posX = this.leftPos;
        final int posY = this.topPos;
        context.blit(background, posX, posY, 0, 0, this.imageWidth - 1, this.imageHeight);

        renderProgressArrow(context, posX, posY);
        if (menu.getIsCooking()) {
            context.blit(background, posX + 40, posY + 54, 176, 0, 16, 14);
        }
    }
}
