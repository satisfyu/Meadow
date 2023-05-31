package net.satisfyu.meadow.block.fondueBlock;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;

public class FondueScreen extends HandledScreen<FondueScreenHandler> {

    private final Identifier background = new Identifier(Meadow.MOD_ID, "textures/gui/fondue_gui.png");

    public FondueScreen(FondueScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, background);

        final int posX = this.x;
        final int posY = this.y;
        this.drawTexture(matrices, posX, posY, 0, 0, this.backgroundWidth - 1, this.backgroundHeight);

        renderProgressArrow(matrices, posX, posY);
        if(handler.getIsCooking())  {
            this.drawTexture(matrices, posX + 40, posY + 54, 176, 0, 16, 14);
        }
    }

    private void renderProgressArrow(MatrixStack matrices, int x, int y) {
        int progress = this.handler.getScaledProgress();
        this.drawTexture(matrices, x + 87, y + 25, 176, 17, progress, 14); //Position Arrow
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}
