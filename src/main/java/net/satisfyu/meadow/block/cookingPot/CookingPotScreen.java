package net.satisfyu.meadow.block.cookingPot;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.screenHandler.SideTip;
import net.satisfyu.meadow.screenHandler.SideTipButton;

public class CookingPotScreen extends HandledScreen<CookingPotScreenHandler> {
    private final SideTipButton SIDE_TIP_BUTTON;
    private final SideTip SIDE_TIP;
    private static final Identifier LEFT_TIP = new Identifier(Meadow.MOD_ID, "textures/gui/container/left.png");
    private static final Identifier BACKGROUND = new Identifier(Meadow.MOD_ID, "textures/gui/pot_gui.png");


    public CookingPotScreen(CookingPotScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        SIDE_TIP = new SideTip(this.x, this.y, 128, 128, LEFT_TIP, 128, 128);
        SIDE_TIP_BUTTON = new SideTipButton(this.x, this.y, (buttonWidget) -> showSideTip());
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);

        SIDE_TIP_BUTTON.setPos(this.x + 90, this.y + 5);
        addDrawableChild(SIDE_TIP_BUTTON);

        SIDE_TIP.setPos(this.x + 176, this.y);
        addDrawable(SIDE_TIP);

        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, BACKGROUND);
        final int posX = this.x;
        final int posY = this.y;
        this.drawTexture(matrices, posX, posY, 0, 0, this.backgroundWidth - 1, this.backgroundHeight);

        renderProgressArrow(matrices, posX, posY);
        if (handler.isBeingBurned()) {
            this.drawTexture(matrices, posX + 124, posY + 56, 176, 0, 17, 15); //fire
        }
    }

    private void renderProgressArrow(MatrixStack matrices, int x, int y) {
        final int progress = this.handler.getScaledProgress();
        this.drawTexture(matrices, x + 94, y + 28, 176, 16, 12, progress);
    }

    private void showSideTip() {
        SIDE_TIP.visible = !SIDE_TIP.visible;
        this.backgroundWidth += 128;
    }

}
