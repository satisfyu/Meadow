package net.satisfyu.meadow.screenHandler;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class SideTip extends DrawableHelper implements Drawable, Element{
    public final Identifier TEXTURE;
    protected int width;
    protected int height;
    public int x;
    public int y;
    private final int textureWidth;
    private final int textureHeight;
    protected boolean hovered;
    public boolean visible = false;

    public SideTip(int x, int y, int width, int height, Identifier texture, int textureWidth, int textureHeight) {
        TEXTURE = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if (this.visible) {
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, TEXTURE);
            RenderSystem.enableDepthTest();
            drawTexture(matrices, this.x, this.y, 0, 0, this.width, this.height, this.textureWidth, this.textureHeight);
        }
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
