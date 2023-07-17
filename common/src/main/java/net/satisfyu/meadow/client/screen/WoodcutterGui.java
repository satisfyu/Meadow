package net.satisfyu.meadow.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.StonecutterScreenHandler;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.satisfyu.meadow.client.screen.handler.WoodcutterGuiHandler;
import net.satisfyu.meadow.recipes.woodcutting.WoodcuttingRecipe;

import java.util.List;

public class WoodcutterGui extends HandledScreen<WoodcutterGuiHandler> {
    private static final Identifier TEXTURE = new Identifier("meadow", "textures/gui/woodcutter.png");
    private final int recipeIconPosX = 58;
    private final int recipeIconPosY = 15;
    private final int maxRecipeIcons = 12;
    private final int recipeIconWidth = 16;
    private final int recipeIconHeight = 18;
    private final int recipeIconPerLine = 4;
    private boolean canCraft;
    private float scrollAmount;
    private boolean mouseClicked;
    private int scrollOffset;

    public WoodcutterGui(WoodcutterGuiHandler handler, PlayerInventory inventory, Text title) {
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

        int k = (int)(41.0f * this.scrollAmount);
        if(this.shouldScroll()) this.drawTexture(matrices, posX + 124, posY + 14 + k, 176, 0, 11, 15);
        int recipeX = posX + recipeIconPosX;
        int recipeY = posY + recipeIconPosY;

        int scrollOffsetOFF = scrollOffset + maxRecipeIcons;
        this.renderRecipeBackground(matrices, mouseX, mouseY, recipeX, recipeY, scrollOffsetOFF);
        this.renderRecipeIcons(recipeX, recipeY, scrollOffsetOFF);
    }

    private void renderRecipeBackground(MatrixStack matrices, int mouseX, int mouseY, int x, int y, int scrollOffsetOFF) {
        for (int i = this.scrollOffset; i < scrollOffsetOFF && i < this.handler.getAvailableRecipeCount(); i++) {
            int offsetedI = i - this.scrollOffset;

            int posX = x + recipeIconWidth * (offsetedI % recipeIconPerLine);
            int l = offsetedI / recipeIconPerLine;
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

    private void renderRecipeIcons(int x, int y, int scrollOffsetOFF) {
        List<WoodcuttingRecipe> list = this.handler.getAvailableRecipes();
        for (int i = this.scrollOffset; i < scrollOffsetOFF && i < this.handler.getAvailableRecipeCount(); i++) {
            int offsetedI = i - this.scrollOffset;
            int k = x + recipeIconWidth * (offsetedI % recipeIconPerLine);
            int l = offsetedI / recipeIconPerLine;
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
            int scrollOffsetOFF = this.scrollOffset + maxRecipeIcons;
            List<WoodcuttingRecipe> list = this.handler.getAvailableRecipes();
            for (int l = scrollOffset; l < scrollOffsetOFF && l < this.handler.getAvailableRecipeCount(); l++) {
                int offsetedL = l - this.scrollOffset;

                int n = i + offsetedL % recipeIconPerLine * recipeIconWidth;
                int o = j + offsetedL / recipeIconPerLine * recipeIconHeight;
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
        this.mouseClicked = false;
        if (this.canCraft) {
            int i = this.x + recipeIconPosX;
            int j = this.y + recipeIconPosY;

            int k = scrollOffset + maxRecipeIcons;
            for (int l = scrollOffset; l < k; l++) {
                int offsetedL = l - this.scrollOffset;
                double d = mouseX - (double) (i + offsetedL % recipeIconPerLine * recipeIconWidth);
                double e = mouseY - (double) (j + offsetedL / recipeIconPerLine * recipeIconHeight);
                if (!(d >= 0.0) || !(e >= 0.0) || !(d < (float) recipeIconWidth) || !(e < (float) recipeIconHeight) || !handler.onButtonClick(client.player, l))
                    continue;
                MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0f));
                this.client.interactionManager.clickButton(handler.syncId, l);
                return true;
            }

            i = this.x + 124;
            j = this.y + 9;
            if (mouseX >= i && mouseX < (i + 11) && mouseY >= j && mouseY < (j + recipeIconPosX)) {
                this.mouseClicked = true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.mouseClicked && this.shouldScroll()) {
            int i = this.y + recipeIconPosY;
            int j = i + recipeIconPosX;
            this.scrollAmount = ((float)mouseY - (float)i - 7.5f) / ((float)(j - i) - 15.0f);
            this.scrollAmount = MathHelper.clamp(this.scrollAmount, 0.0f, 1.0f);
            this.scrollOffset = (int)((double)(this.scrollAmount * (float)this.getMaxScroll()) + 0.5) * 4;
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (this.shouldScroll()) {
            int i = this.getMaxScroll();
            float f = (float)amount / (float)i;
            this.scrollAmount = MathHelper.clamp(this.scrollAmount - f, 0.0f, 1.0f);
            this.scrollOffset = (int)((double)(this.scrollAmount * (float)i) + 0.5) * 4;
        }
        return true;
    }

    private boolean shouldScroll() {
        return this.canCraft && this.handler.getAvailableRecipeCount() > maxRecipeIcons;
    }

    protected int getMaxScroll() {
        return (this.handler.getAvailableRecipeCount() + 4 - 1) / 4 - 3;
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}
