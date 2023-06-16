package net.satisfyu.meadow.client.screen;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.client.screen.screenhandler.CookingCauldronScreenHandler;
import net.satisfyu.meadow.client.screen.screenhandler.screenHandler.RecipeHandledScreen;
import net.satisfyu.meadow.client.screen.screenhandler.screenHandler.SideToolTip;

public class CookingCauldronScreen extends RecipeHandledScreen<CookingCauldronScreenHandler> {
    private static final Identifier BACKGROUND;
    private static final Identifier SIDE_TIP;
    private static final int FRAMES = 2;

    public CookingCauldronScreen(CookingCauldronScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title, BACKGROUND, SIDE_TIP, FRAMES);
        titleX = this.x + 27;
    }

    @Override
    public void renderProgressArrow(MatrixStack matrices, int x, int y) {
        int progress = this.handler.getScaledProgress(17);
        this.drawTexture(matrices, x + 92, y + 10, 178, 16, progress, 29); //Position Arrow
    }

    @Override
    public void renderBurnIcon(MatrixStack matrices, int posX, int posY) {
        if(handler.getIsCooking()) {
            this.drawTexture(matrices, posX + 124, posY + 51, 176, 0, 16, 14);
        }
    }

    @Override
    public void addToolTips() {
        super.addToolTips();
        addToolTip(new SideToolTip(24, 20, 34, 30, Text.translatable("tooltip.meadow.cooking_cauldron.milk_bucket")));
        addToolTip(new SideToolTip(48, 20, 34, 30, Text.translatable("tooltip.meadow.cooking_cauldron.rennet")));
        addToolTip(new SideToolTip(86, 20, 34, 30, Text.translatable("tooltip.meadow.cooking_cauldron.alpine_salt")));
        addToolTip(new SideToolTip(54, 111, 34, 30, Text.translatable("tooltip.meadow.cooking_cauldron.output_slot")));
    }

    static {
        BACKGROUND = new Identifier(Meadow.MOD_ID, "textures/gui/cooking_cauldron_gui.png");
        SIDE_TIP = new Identifier(Meadow.MOD_ID, "textures/gui/cooking_cauldron_recipe_book.png");
    }
}
