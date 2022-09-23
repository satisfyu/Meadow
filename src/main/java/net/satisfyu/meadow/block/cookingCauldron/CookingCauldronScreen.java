package net.satisfyu.meadow.block.cookingCauldron;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;

public class CookingCauldronScreen extends HandledScreen<CookingCauldronScreenHandler> {

    private final Identifier background = new Identifier(Meadow.MOD_ID, "textures/gui/cooking_cauldron_gui.png");

    public CookingCauldronScreen(CookingCauldronScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        //handler.setContentsChangedListener(this::onInventoryChange);
    }



    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        int k;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, background);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);


        k = this.handler.getSyncedNumber() / (getTimeToCook() / 22);
        this.drawTexture(matrices, x + 79 + 11, y + 34 - 8, 176, 14 + 1, k + 1, 16);

        if(handler.getIsCooking()) this.drawTexture(matrices, x + 124, y + 51, 176, 0, 16, 14);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        //textRenderer.draw(matrices, Integer.toString(this.handler.getSyncedNumber()), 0, 0, 65280);
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    public static int getTimeToCook(){
        return 48000;
    }




    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}
