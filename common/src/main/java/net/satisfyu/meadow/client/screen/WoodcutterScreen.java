package net.satisfyu.meadow.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.client.screen.screenhandler.WoodcutterScreenHandler;
import net.satisfyu.meadow.recipes.woodcutting.WoodcuttingRecipe;

import java.util.List;

public class WoodcutterScreen extends HandledScreen<WoodcutterScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("meadow","textures/gui/woodcutter.png");
    private final int recipeIconPosX = 58;
    private final int recipeIconPosY = 15;
    private final int maxRecipeIcons = 16;
    private final int recipeIconWidth = 16;
    private final int recipeIconHeight = 18;
    private final int recipeIconPerLine = 4;
    private boolean canCraft;

    public WoodcutterScreen(WoodcutterScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        handler.setContentsChangedListener(this::onInventoryChange);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        this.renderBackground(matrices);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int posX = this.x;
        int posY = this.y;
        this.drawTexture(matrices, posX, posY, 0, 0, this.backgroundWidth, this.backgroundHeight);
        int recipeX = posX + recipeIconPosX;
        int recipeY = posY + recipeIconPosY;
        this.renderRecipeBackground(matrices, mouseX, mouseY, recipeX, recipeY);
        this.renderRecipeIcons(recipeX, recipeY);
    }

    private void renderRecipeBackground(MatrixStack matrices, int mouseX, int mouseY, int x, int y) {
        for (int i = 0; i < maxRecipeIcons && i < this.handler.getAvailableRecipeCount(); i++) {
            int posX = x + recipeIconWidth * (i % recipeIconPerLine);
            int l = i / recipeIconPerLine;
            int posY = y + recipeIconHeight * l;
            int offsetY = this.backgroundHeight;
            if (i == this.handler.getSelectedRecipe()) {
                offsetY += recipeIconHeight;
            } else if (mouseX >= posX && mouseY >= posY && mouseX < posX + recipeIconWidth && mouseY < posY + recipeIconHeight) {
                offsetY += recipeIconHeight * 2;
            }
            this.drawTexture(matrices, posX, posY, 0, offsetY, recipeIconWidth, recipeIconHeight);
        }
    }

    private void renderRecipeIcons(int x, int y) {
        List<WoodcuttingRecipe> list = this.handler.getAvailableRecipes();
        for (int i = 0; i < maxRecipeIcons && i < this.handler.getAvailableRecipeCount(); i++) {
            int k = x + recipeIconWidth * (i % recipeIconPerLine);
            int l = i / recipeIconPerLine;
            int m = y + recipeIconHeight * l + 1;
            this.client.getItemRenderer().renderInGuiWithOverrides(list.get(i).getOutput(), k, m);
        }
    }

    @Override
    protected void drawMouseoverTooltip(MatrixStack matrices, int x, int y) {
        super.drawMouseoverTooltip(matrices, x, y);
        if (this.canCraft) {
            int i = this.x + recipeIconPosX;
            int j = this.y + recipeIconPosY;
            List<WoodcuttingRecipe> list = this.handler.getAvailableRecipes();
            for (int l = 0; l < maxRecipeIcons && l < this.handler.getAvailableRecipeCount(); l++) {
                int n = i + l % recipeIconPerLine * recipeIconWidth;
                int o = j + l / recipeIconPerLine * recipeIconHeight;
                if (x < n || x >= n + recipeIconWidth || y < o || y >= o + recipeIconHeight) continue;
                this.renderTooltip(matrices, list.get(l).getOutput(), x, y);
            }
        }
    }

    private void onInventoryChange() {
        this.canCraft = this.handler.canCraft();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.canCraft) {
            int i = this.x + recipeIconPosX;
            int j = this.y + recipeIconPosY;
            for (int l = 0; l < maxRecipeIcons; l++) {
                double d = mouseX - (double)(i + l % recipeIconPerLine * recipeIconWidth);
                double e = mouseY - (double)(j + l / recipeIconPerLine * recipeIconHeight);
                if (!(d >= 0.0) || !(e >= 0.0) || !(d < (float)recipeIconWidth) || !(e < (float)recipeIconHeight) || !this.handler.onButtonClick(this.client.player, l)) continue;
                MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0f));
                this.client.interactionManager.clickButton(this.handler.syncId, l);
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}
