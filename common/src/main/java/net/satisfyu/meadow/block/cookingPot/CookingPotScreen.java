package net.satisfyu.meadow.block.cookingPot;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.screenHandler.RecipeHandledScreen;
import net.satisfyu.meadow.screenHandler.SideToolTip;

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

    @Override
    public void addToolTips() {
        super.addToolTips();
        final int normalWidthAndHeight = 18;
        final int firstRow = 18;
        final int secondRow = 42;
        final int containerRow = 66;
        final int resultRow = 102;

        final int firstLine = 19;
        addToolTip(new SideToolTip(firstRow, firstLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.meadow.alpine_salt")));
        addToolTip(new SideToolTip(secondRow, firstLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.meadow.raw_bear_meat")));
        addToolTip(new SideToolTip(containerRow, firstLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.minecraft.bowl")));
        addToolTip(new SideToolTip(resultRow, firstLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.meadow.bear_stew")));

        final int secondLine = 43;
        addToolTip(new SideToolTip(firstRow, secondLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.minecraft.water_bucket")));
        addToolTip(new SideToolTip(secondRow, secondLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.meadow.bag_of_yarrow")));
        addToolTip(new SideToolTip(containerRow, secondLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.meadow.jug")));
        addToolTip(new SideToolTip(resultRow, secondLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.meadow.jug_yarrow_tea")));

        final int thirdLine = 67;
        addToolTip(new SideToolTip(firstRow, thirdLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.minecraft.water_bucket")));
        addToolTip(new SideToolTip(secondRow, thirdLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.meadow.bag_of_juniper")));
        addToolTip(new SideToolTip(containerRow, thirdLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.meadow.jug")));
        addToolTip(new SideToolTip(resultRow, thirdLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.meadow.jug_juniper_tea")));

        final int fourthLine = 91;
        addToolTip(new SideToolTip(firstRow, fourthLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.meadow.alpine_salt")));
        addToolTip(new SideToolTip(secondRow, fourthLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.minecraft.beef")));
        addToolTip(new SideToolTip(containerRow, fourthLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.minecraft.glass_bottle")));
        addToolTip(new SideToolTip(resultRow, fourthLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.meadow.lab")));

        final int fifthLine = 115;
        addToolTip(new SideToolTip(firstRow, fifthLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.minecraft.sugar")));
        addToolTip(new SideToolTip(secondRow, fifthLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("tooltip.meadow.cooking_pot.crops")));
        addToolTip(new SideToolTip(containerRow, fifthLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.minecraft.paper")));
        addToolTip(new SideToolTip(resultRow, fifthLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.meadow.ricola")));

        final int sixthLine = 139;
        addToolTip(new SideToolTip(firstRow, sixthLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.minecraft.water_bucket")));
        addToolTip(new SideToolTip(secondRow, sixthLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.meadow.oat")));
        addToolTip(new SideToolTip(containerRow, sixthLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("item.minecraft.paper")));
        addToolTip(new SideToolTip(resultRow, sixthLine, normalWidthAndHeight, normalWidthAndHeight, Text.translatable("tooltip.meadow.cooking_pot.oat_milk")));

    }

    static {
        BACKGROUND = new Identifier(Meadow.MOD_ID, "textures/gui/cooking_pot_gui.png");
        SIDE_TIP = new Identifier(Meadow.MOD_ID, "textures/gui/cooking_pot_recipe_book.png");
    }
}
