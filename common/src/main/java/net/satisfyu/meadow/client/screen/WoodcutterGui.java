package net.satisfyu.meadow.client.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.satisfyu.meadow.client.screen.handler.WoodcutterGuiHandler;
import net.satisfyu.meadow.recipes.woodcutting.WoodcuttingRecipe;
import net.satisfyu.meadow.util.MeadowIdentifier;

import java.util.List;

public class WoodcutterGui extends AbstractContainerScreen<WoodcutterGuiHandler> {
    private static final ResourceLocation SCROLLER_SPRITE = new MeadowIdentifier("container/woodcutter/scroller");
    private static final ResourceLocation RECIPE_SELECTED_SPRITE = new MeadowIdentifier("container/woodcutter/recipe_selected");
    private static final ResourceLocation RECIPE_HIGHLIGHTED_SPRITE = new MeadowIdentifier("container/woodcutter/recipe_highlighted");
    private static final ResourceLocation RECIPE_SPRITE = new MeadowIdentifier("container/woodcutter/recipe");
    private static final ResourceLocation BG_LOCATION = new MeadowIdentifier("textures/gui/container/woodcutter.png");
    private static final int SCROLLER_WIDTH = 11; //12
    private static final int SCROLLER_HEIGHT = 15;
    private static final int RECIPES_COLUMNS = 4;
    private static final int RECIPES_ROWS = 3;

    private static final int MAX_RECIPE_ICONS = RECIPES_ROWS * RECIPES_COLUMNS;
    private static final int RECIPES_IMAGE_SIZE_WIDTH = 16;
    private static final int RECIPES_IMAGE_SIZE_HEIGHT = 18;
    private static final int SCROLLER_FULL_HEIGHT = 56; //54
    private static final int SCROLLER_X_OFFSET = 124; //119

    private static final int RECIPES_X = 58; //52
    private static final int RECIPES_Y = 14;
    private float scrollOffs;
    private boolean scrolling;
    private int startIndex;
    private boolean displayRecipes;

    public WoodcutterGui(WoodcutterGuiHandler stonecutterMenu, Inventory inventory, Component component) {
        super(stonecutterMenu, inventory, component);
        stonecutterMenu.setContentsChangedListener(this::containerChanged);
        --this.titleLabelY;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        super.render(guiGraphics, i, j, f);
        this.renderTooltip(guiGraphics, i, j);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float f, int i, int j) {
        int k = this.leftPos;
        int l = this.topPos;
        guiGraphics.blit(BG_LOCATION, k, l, 0, 0, this.imageWidth, this.imageHeight);
        int m = (int)(41.0f * this.scrollOffs);
        if(this.isScrollBarActive()) guiGraphics.blitSprite(SCROLLER_SPRITE, k + SCROLLER_X_OFFSET, l + SCROLLER_HEIGHT + m - 1, SCROLLER_WIDTH, SCROLLER_HEIGHT);
        int n = this.leftPos + RECIPES_X;
        int o = this.topPos + RECIPES_Y;
        int p = this.startIndex + MAX_RECIPE_ICONS;
        this.renderButtons(guiGraphics, i, j, n, o, p);
        this.renderRecipes(guiGraphics, n, o, p);
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int i, int j) {
        super.renderTooltip(guiGraphics, i, j);
        if (this.displayRecipes) {
            int k = this.leftPos + RECIPES_X;
            int l = this.topPos + RECIPES_Y;
            int m = this.startIndex + MAX_RECIPE_ICONS;
            List<RecipeHolder<WoodcuttingRecipe>> list = this.menu.getAvailableRecipes();
            for (int n = this.startIndex; n < m && n < this.menu.getAvailableRecipeCount(); ++n) {
                int o = n - this.startIndex;
                int p = k + o % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH;
                int q = l + o / RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_HEIGHT + 2;
                if (i < p || i >= p + RECIPES_IMAGE_SIZE_WIDTH || j < q || j >= q + RECIPES_IMAGE_SIZE_HEIGHT) continue;
                guiGraphics.renderTooltip(this.font, list.get(n).value().getResultItem(), i, j);
            }
        }
    }

    private void renderButtons(GuiGraphics guiGraphics, int i, int j, int k, int l, int m) {
        for (int n = this.startIndex; n < m && n < this.menu.getAvailableRecipeCount(); ++n) {
            int o = n - this.startIndex;
            int p = k + o % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH;
            int q = o / RECIPES_COLUMNS;
            int r = l + q * RECIPES_IMAGE_SIZE_HEIGHT + 2;
            ResourceLocation resourceLocation = n == this.menu.getSelectedRecipe() ? RECIPE_SELECTED_SPRITE : (i >= p && j >= r && i < p + RECIPES_IMAGE_SIZE_WIDTH && j < r + RECIPES_IMAGE_SIZE_HEIGHT ? RECIPE_HIGHLIGHTED_SPRITE : RECIPE_SPRITE);
            guiGraphics.blitSprite(resourceLocation, p, r - 1, RECIPES_IMAGE_SIZE_WIDTH, RECIPES_IMAGE_SIZE_HEIGHT);
        }
    }

    private void renderRecipes(GuiGraphics guiGraphics, int i, int j, int k) {
        List<RecipeHolder<WoodcuttingRecipe>> list = this.menu.getAvailableRecipes();
        for (int l = this.startIndex; l < k && l < this.menu.getAvailableRecipeCount(); ++l) {
            int m = l - this.startIndex;
            int n = i + m % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH;
            int o = m / RECIPES_COLUMNS;
            int p = j + o * RECIPES_IMAGE_SIZE_HEIGHT + 2;
            guiGraphics.renderItem(list.get(l).value().getResultItem(), n, p);
        }
    }

    @Override
    public boolean mouseClicked(double d, double e, int i) {
        this.scrolling = false;
        if (this.displayRecipes) {
            int j = this.leftPos + RECIPES_X;
            int k = this.topPos + RECIPES_Y;
            int l = this.startIndex + MAX_RECIPE_ICONS;
            for (int m = this.startIndex; m < l; ++m) {
                int n = m - this.startIndex;
                double f = d - (double)(j + n % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH);
                double g = e - (double)(k + n / RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_HEIGHT);
                if (!(f >= 0.0) || !(g >= 0.0) || !(f < RECIPES_IMAGE_SIZE_WIDTH) || !(g < RECIPES_IMAGE_SIZE_HEIGHT) || !this.menu.clickMenuButton(this.minecraft.player, m)) continue;
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0f));
                this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, m);
                return true;
            }
            j = this.leftPos + SCROLLER_X_OFFSET;
            k = this.topPos + 9; //Todo: Why 9?
            if (d >= (double)j && d < (double)(j + SCROLLER_WIDTH) && e >= (double)k && e < (double)(k + SCROLLER_FULL_HEIGHT)) {
                this.scrolling = true;
            }
        }
        return super.mouseClicked(d, e, i);
    }

    @Override
    public boolean mouseDragged(double d, double e, int i, double f, double g) {
        if (this.scrolling && this.isScrollBarActive()) {
            int j = this.topPos + RECIPES_Y;
            int k = j + SCROLLER_FULL_HEIGHT;
            this.scrollOffs = ((float)e - (float)j - 7.5f) / ((float)(k - j) - SCROLLER_HEIGHT); // I guess? initially: 15.0f
            this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0f, 1.0f);
            this.startIndex = (int)((double)(this.scrollOffs * (float)this.getOffscreenRows()) + 0.5) * RECIPES_COLUMNS; // I guess? initially: 4
            return true;
        }
        return super.mouseDragged(d, e, i, f, g);
    }

    @Override
    public boolean mouseScrolled(double d, double e, double f, double g) {
        if (this.isScrollBarActive()) {
            int i = this.getOffscreenRows();
            float h = (float)g / (float)i;
            this.scrollOffs = Mth.clamp(this.scrollOffs - h, 0.0f, 1.0f);
            this.startIndex = (int)((double)(this.scrollOffs * (float)i) + 0.5) * RECIPES_COLUMNS; // I guess? initially: 4
        }
        return true;
    }

    private boolean isScrollBarActive() {
        return this.displayRecipes && this.menu.getAvailableRecipeCount() > MAX_RECIPE_ICONS;
    }

    protected int getOffscreenRows() {
        return (this.menu.getAvailableRecipeCount() + RECIPES_COLUMNS - 1) / RECIPES_COLUMNS - RECIPES_ROWS;
    }

    private void containerChanged() {
        this.displayRecipes = this.menu.hasInputItem();
        if (!this.displayRecipes) {
            this.scrollOffs = 0.0f;
            this.startIndex = 0;
        }
    }
}
