package net.satisfyu.meadow.client.screen;

import de.cristelknight.doapi.client.recipebook.screen.AbstractRecipeBookGUIScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.client.screen.handler.CookingCauldronGuiHandler;
import net.satisfyu.meadow.client.recipebook.screen.CookingCauldronRecipeBook;

public class CookingCauldronGui extends AbstractRecipeBookGUIScreen<CookingCauldronGuiHandler> {
    private static final Identifier BACKGROUND;

    public CookingCauldronGui(CookingCauldronGuiHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title, new CookingCauldronRecipeBook(), BACKGROUND);
        titleX = this.x + 27;
    }

    @Override
    public void renderProgressArrow(MatrixStack matrices) {
        int progress = this.handler.getScaledProgress(17);
        this.drawTexture(matrices, x + 92, y + 10, 178, 16, progress, 29); //Position Arrow
    }

    @Override
    public void renderBurnIcon(MatrixStack matrices, int posX, int posY) {
        if (handler.getIsCooking()) {
            this.drawTexture(matrices, posX + 124, posY + 51, 176, 0, 16, 14);
        }
    }

    static {
        BACKGROUND = new Identifier(Meadow.MOD_ID, "textures/gui/cooking_cauldron_gui.png");
    }
}
