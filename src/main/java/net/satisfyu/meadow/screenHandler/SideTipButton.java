package net.satisfyu.meadow.screenHandler;


import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;

public class SideTipButton extends ButtonWidget  {

    private final Identifier TEXTURE = new Identifier(Meadow.MOD_ID, "textures/gui/cooking_cauldron_gui.png");

    public SideTipButton(int x, int y, ButtonWidget.PressAction pressAction) {
        super(x, y, 20, 18, ScreenTexts.EMPTY, pressAction, EMPTY);
    }

    @Override
    public void onPress() {
        System.out.println("PRESS");
        super.onPress();
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        System.out.println("CLICK");
        super.onClick(mouseX, mouseY);
    }

    @Override
    public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float delta) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int v = 168;
        if (this.isHovered()) {
            int hoveredVOffset = 19;
            v += hoveredVOffset;
        }

        RenderSystem.enableDepthTest();
        int u = 0;
        int TEXTURE_WIDTH = 256;
        int TEXTURE_HEIGHT = 256;
        drawTexture(matrixStack, this.x, this.y, (float) u, (float)v, this.width, this.height, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        if (this.hovered) {
            this.renderTooltip(matrixStack, mouseX, mouseY);
        }
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}