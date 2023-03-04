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
        int k;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, background);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        this.drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
        k = this.handler.getSyncedNumber() / (getTimeToCook() / 22);
        this.drawTexture(matrices, x + 90, y + 26, 176, 15, k + 1, 16);
        if(handler.getIsCooking()) this.drawTexture(matrices, x + 124, y + 51, 176, 0, 16, 14);
    }


    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    public static int getTimeToCook(){
        return 6000;
    }




    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}
