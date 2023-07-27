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
    private static final ResourceLocation BACKGROUND;

    public CheeseFormGui(CheeseFormGuiHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title, new CheeseFormRecipeBook(), BACKGROUND);
        font = Minecraft.getInstance().font;
        titleLabelX = (imageWidth - font.width(title)) / 2;
    }

    @Override
    protected void renderProgressArrow(GuiGraphics guiGraphics) {
        final int progressX = this.menu.getScaledXProgress();
        guiGraphics.blit(BACKGROUND, leftPos + 79, topPos + 36, 178, 4, progressX, 10);
        final int progressY = this.menu.getScaledYProgress();
        guiGraphics.blit(BACKGROUND, leftPos + 81, topPos + 8, 180, 22, 16, progressY);
    }

    static {
        BACKGROUND = new ResourceLocation(Meadow.MOD_ID, "textures/gui/cheese_form_gui.png");
    }
}
