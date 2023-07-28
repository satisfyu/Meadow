package net.satisfyu.meadow.client.screen;

import de.cristelknight.doapi.client.recipebook.screen.AbstractRecipeBookGUIScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.client.screen.handler.CheeseFormGuiHandler;
import net.satisfyu.meadow.client.recipebook.CheeseFormRecipeBook;

public class CheeseFormGui extends AbstractRecipeBookGUIScreen<CheeseFormGuiHandler> {
    public static final ResourceLocation BACKGROUND;

    public static final int ARROW_X = 79;
    public static final int ARROW_Y = 36;
    public CheeseFormGui(CheeseFormGuiHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title, new CheeseFormRecipeBook(), BACKGROUND);
        font = Minecraft.getInstance().font;
        titleLabelX = (imageWidth - font.width(title)) / 2;
    }

    @Override
    protected void renderProgressArrow(GuiGraphics guiGraphics) {
        int progress = this.menu.getScaledProgress(22);
        guiGraphics.blit(BACKGROUND, leftPos + 79, topPos + 36, 178, 4, progress, 10);
    }

    static {
        BACKGROUND = new ResourceLocation(Meadow.MOD_ID, "textures/gui/cheese_form_gui.png");
    }
}
