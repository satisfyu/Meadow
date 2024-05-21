package net.satisfy.meadow.compat.jei.category;

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
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Ingredient;
import net.satisfy.meadow.Meadow;
import net.satisfy.meadow.client.gui.CookingCauldronGui;
import net.satisfy.meadow.compat.jei.MeadowJEIPlugin;
import net.satisfy.meadow.block.entity.CookingCauldronBlockEntity;
import net.satisfy.meadow.recipes.CookingCauldronRecipe;
import net.satisfy.meadow.registry.ObjectRegistry;
import org.jetbrains.annotations.NotNull;

public class CookingCauldronCategory implements IRecipeCategory<CookingCauldronRecipe> {
    public static final RecipeType<CookingCauldronRecipe> COOKING_CAULDRON = RecipeType.create(Meadow.MOD_ID, "cooking", CookingCauldronRecipe.class);
    public static final int WIDTH = 124;
    public static final int HEIGHT = 55;
    public static final int WIDTH_OF = 26;
    public static final int HEIGHT_OF = 10;
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable burnicon;
    private final IDrawableAnimated arrow;
    private final Component localizedName;

    public CookingCauldronCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(CookingCauldronGui.BACKGROUND, WIDTH_OF, HEIGHT_OF, WIDTH, HEIGHT);
        this.arrow = helper.drawableBuilder(CookingCauldronGui.BACKGROUND, 178, 16, 17, 29)
                .buildAnimated(CookingCauldronBlockEntity.getMaxCookingTime(), IDrawableAnimated.StartDirection.LEFT, false);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, ObjectRegistry.COOKING_CAULDRON.get().asItem().getDefaultInstance());
        this.burnicon = helper.createDrawable(CookingCauldronGui.BACKGROUND, 176, 0, 17, 15);
        this.localizedName = Component.translatable("rei.meadow.cooking_cauldron_category");
    }


    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CookingCauldronRecipe recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> ingredients = recipe.getIngredients();
        int s = ingredients.size();

        for (int row = 0; row < 2; row++) {
            for (int slot = 0; slot < 3; slot++) {
                int current = slot + row + (row * 2);
                if(s - 1 < current) break;
                MeadowJEIPlugin.addSlot(builder,30 + (slot * 18) - WIDTH_OF, 17 + (row * 18) - HEIGHT_OF, ingredients.get(current));
            }
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 124 - WIDTH_OF,  26 - HEIGHT_OF).addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(CookingCauldronRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, CookingCauldronGui.ARROW_X - WIDTH_OF, CookingCauldronGui.ARROW_Y - HEIGHT_OF);
        burnicon.draw(guiGraphics, 124 - WIDTH_OF, 51 - HEIGHT_OF);
    }

    @Override
    public @NotNull RecipeType<CookingCauldronRecipe> getRecipeType() {
        return COOKING_CAULDRON;
    }

    @Override
    public @NotNull Component getTitle() {
        return this.localizedName;
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return this.icon;
    }
}
