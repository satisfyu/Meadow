package net.satisfyu.meadow.client.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.client.gui.handler.CookingPotGuiHandler;
import net.satisfyu.meadow.client.screen.recipe.custom.CookingCauldronRecipeBook;


public class CookingPotGui extends AbstractRecipeBookGUIScreen<CookingPotGuiHandler> {
    private static final Identifier BACKGROUND;

    public CookingPotGui(CookingPotGuiHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title, new CookingCauldronRecipeBook(), BACKGROUND);
        textRenderer = MinecraftClient.getInstance().textRenderer;
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    public void renderProgressArrow(MatrixStack matrices) {
        int progress = this.handler.getScaledProgress(17);
        this.drawTexture(matrices, x + 92, y + 10, 178, 16, progress, 29); //Position Arrow (moved 8 pixels to the left)
    }



    @Override
    public void renderBurnIcon(MatrixStack matrices, int posX, int posY) {
        if (handler.isBeingBurned()) {
            this.drawTexture(matrices, posX + 124, posY + 51, 176, 0, 17, 15); //fire (moved 5 pixels up)
        }
    }


    static {
        BACKGROUND = new Identifier(Meadow.MOD_ID, "textures/gui/cooking_cauldron_gui.png");
    }
}