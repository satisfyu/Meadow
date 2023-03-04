package net.satisfyu.meadow.block.cookingPot;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.screenHandler.RecipeHandledScreen;

public class CookingPotScreen extends RecipeHandledScreen<CookingPotScreenHandler> {
    private static final Identifier BACKGROUND;
    private static final Identifier SIDE_TIP;
    private static final int FRAMES = 3;

    public CookingPotScreen(CookingPotScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title, BACKGROUND, SIDE_TIP, FRAMES);
        titleX = this.x + 27;
    }

    @Override
    public void renderProgressArrow(MatrixStack matrices, int x, int y) {
        int progress = this.handler.getScaledProgress(17);
        this.drawTexture(matrices, x + 95, y + 10, 178, 16, progress, 29); //Position Arrow
    }

    @Override
    public void renderBurnIcon(MatrixStack matrices, int posX, int posY) {
        if (handler.isBeingBurned()) {
            this.drawTexture(matrices, posX + 124, posY + 56, 176, 0, 17, 15); //fire
        }
    }

    static {
        BACKGROUND = new Identifier(Meadow.MOD_ID, "textures/gui/cooking_pot_gui.png");
        SIDE_TIP = new Identifier(Meadow.MOD_ID, "textures/gui/cooking_pot_recipe_book.png");
    }
}
