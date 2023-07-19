package net.satisfyu.meadow.client.screen;

import de.cristelknight.doapi.client.recipebook.screen.AbstractRecipeBookGUIScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.client.screen.handler.CheeseFormGuiHandler;
import net.satisfyu.meadow.client.recipebook.CheeseFormRecipeBook;

public class CheeseFormGui extends AbstractRecipeBookGUIScreen<CheeseFormGuiHandler> {
    private static final Identifier BACKGROUND;

    public CheeseFormGui(CheeseFormGuiHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title, new CheeseFormRecipeBook(), BACKGROUND);
        textRenderer = MinecraftClient.getInstance().textRenderer;
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void renderProgressArrow(DrawContext guiGraphics) {
        final int progressX = this.handler.getScaledXProgress();
        guiGraphics.drawTexture(BACKGROUND, x + 79, y + 36, 178, 4, progressX, 10);
        final int progressY = this.handler.getScaledYProgress();
        guiGraphics.drawTexture(BACKGROUND, x + 81, y + 8, 180, 22, 16, progressY);
    }

    static {
        BACKGROUND = new Identifier(Meadow.MOD_ID, "textures/gui/cheese_form_gui.png");
    }
}
