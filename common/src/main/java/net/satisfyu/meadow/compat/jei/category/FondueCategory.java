package net.satisfyu.meadow.compat.jei.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Ingredient;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.client.gui.FondueGui;
import net.satisfyu.meadow.compat.jei.MeadowJEIPlugin;
import net.satisfyu.meadow.block.entity.FondueBlockEntity;
import net.satisfyu.meadow.recipes.FondueRecipe;
import net.satisfyu.meadow.registry.ObjectRegistry;

public class FondueCategory implements IRecipeCategory<FondueRecipe> {
    public static final RecipeType<FondueRecipe> FONDUE = RecipeType.create(Meadow.MOD_ID, "fondue", FondueRecipe.class);
    public static final int WIDTH = 124;
    public static final int HEIGHT = 65;
    public static final int WIDTH_OF = 26;
    public static final int HEIGHT_OF = 6;
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;
    private final Component localizedName;

    public FondueCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(FondueGui.BACKGROUND, WIDTH_OF, HEIGHT_OF, WIDTH, HEIGHT);
        this.arrow = helper.drawableBuilder(FondueGui.BACKGROUND, 176, 17, 15, 14)
                .buildAnimated(FondueBlockEntity.MAX_PROGRESS, IDrawableAnimated.StartDirection.LEFT, false);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, ObjectRegistry.FONDUE.get().asItem().getDefaultInstance());
        this.localizedName = Component.translatable("rei.meadow.fondue_category");
    }


    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FondueRecipe recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> ingredients = recipe.getIngredients();

        MeadowJEIPlugin.addSlot(builder,41 - WIDTH_OF, 9 - HEIGHT_OF, ingredients.get(0));
        MeadowJEIPlugin.addSlot(builder,41 - WIDTH_OF, 33 - HEIGHT_OF, ingredients.get(1));

        // Output
        builder.addSlot(RecipeIngredientRole.OUTPUT, 120 - WIDTH_OF,  25 - HEIGHT_OF).addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(FondueRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, FondueGui.ARROW_X - WIDTH_OF, FondueGui.ARROW_Y - HEIGHT_OF);
        drawAmount(guiGraphics);
    }

    protected void drawAmount(GuiGraphics guiGraphics) {
        Component amountString = Component.translatable("rei.meadow.fondue_category.amount", 10);

        Minecraft minecraft = Minecraft.getInstance();
        Font fontRenderer = minecraft.font;
        int stringWidth = fontRenderer.width(amountString);
        guiGraphics.drawString(fontRenderer, amountString, getWidth() - stringWidth, HEIGHT - HEIGHT_OF, 0xFF808080, false);
    }

    @Override
    public RecipeType<FondueRecipe> getRecipeType() {
        return FONDUE;
    }

    @Override
    public Component getTitle() {
        return this.localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }
}
