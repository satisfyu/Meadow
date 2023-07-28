package net.satisfyu.meadow.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.satisfyu.meadow.client.screen.handler.CheeseFormGuiHandler;
import net.satisfyu.meadow.client.screen.handler.CookingCauldronGuiHandler;
import net.satisfyu.meadow.compat.jei.category.CheesePressCategory;
import net.satisfyu.meadow.compat.jei.category.CookingCauldronCategory;
import net.satisfyu.meadow.compat.jei.category.WoodCutterCategory;
import net.satisfyu.meadow.recipes.cheese.CheeseFormRecipe;
import net.satisfyu.meadow.recipes.cooking.CookingCauldronRecipe;
import net.satisfyu.meadow.recipes.woodcutting.WoodcuttingRecipe;
import net.satisfyu.meadow.registry.ObjectRegistry;
import net.satisfyu.meadow.registry.RecipeRegistry;
import net.satisfyu.meadow.registry.ScreenHandlerRegistry;
import net.satisfyu.meadow.util.MeadowIdentifier;

import java.util.List;
import java.util.Objects;


@JeiPlugin
public class MeadowJEIPlugin implements IModPlugin {

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new WoodCutterCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new CookingCauldronCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new CheesePressCategory(registration.getJeiHelpers().getGuiHelper()));
    }


    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<WoodcuttingRecipe> woodcuttingRecipes = rm.getAllRecipesFor(RecipeRegistry.WOODCUTTING.get());
        registration.addRecipes(WoodCutterCategory.WOODCUTTER, woodcuttingRecipes);

        List<CookingCauldronRecipe> cookingCauldronRecipes = rm.getAllRecipesFor(RecipeRegistry.COOKING.get());
        registration.addRecipes(CookingCauldronCategory.COOKING_CAULDRON, cookingCauldronRecipes);

        List<CheeseFormRecipe> cheesePressRecipes = rm.getAllRecipesFor(RecipeRegistry.CHEESE.get());
        registration.addRecipes(CheesePressCategory.CHEESE_PRESS, cheesePressRecipes);
    }

    @Override
    public ResourceLocation getPluginUid() {
        return new MeadowIdentifier("jei_plugin");
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(CheeseFormGuiHandler.class, ScreenHandlerRegistry.CHEESE_FORM_SCREEN_HANDLER.get(), CheesePressCategory.CHEESE_PRESS,
                1, 2, 3, 36);

        registration.addRecipeTransferHandler(CookingCauldronGuiHandler.class, ScreenHandlerRegistry.COOKING_CAULDRON_SCREEN_HANDLER.get(), CookingCauldronCategory.COOKING_CAULDRON,
                0, 6, 7, 36);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ObjectRegistry.WOODCUTTER.get().asItem().getDefaultInstance(), WoodCutterCategory.WOODCUTTER);
        registration.addRecipeCatalyst(ObjectRegistry.COOKING_CAULDRON.get().asItem().getDefaultInstance(), CookingCauldronCategory.COOKING_CAULDRON);
        registration.addRecipeCatalyst(ObjectRegistry.CHEESE_FORM.get().asItem().getDefaultInstance(), CheesePressCategory.CHEESE_PRESS);
    }

    public static void addSlot(IRecipeLayoutBuilder builder, int x, int y, Ingredient ingredient){
        builder.addSlot(RecipeIngredientRole.INPUT, x, y).addIngredients(ingredient);
    }
}
