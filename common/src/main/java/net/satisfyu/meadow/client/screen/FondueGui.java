package net.satisfyu.meadow.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.client.screen.handler.FondueGuiHandler;

public class FondueGui extends HandledScreen<FondueGuiHandler> {

    private final Identifier background = new Identifier(Meadow.MOD_ID, "textures/gui/fondue_gui.png");

    public FondueGui(FondueGuiHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }


    private void renderProgressArrow(DrawContext context, int x, int y) {
        int progress = this.handler.getScaledProgress();
        context.drawTexture(background, x + 87, y + 25, 176, 17, progress, 14); //Position Arrow
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, background);

        final int posX = this.x;
        final int posY = this.y;
        context.drawTexture(background, posX, posY, 0, 0, this.backgroundWidth - 1, this.backgroundHeight);

        renderProgressArrow(context, posX, posY);
        if (handler.getIsCooking()) {
            context.drawTexture(background, posX + 40, posY + 54, 176, 0, 16, 14);
        }
    }
}
