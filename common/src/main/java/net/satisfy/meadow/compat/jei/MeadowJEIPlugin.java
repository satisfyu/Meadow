package net.satisfy.meadow.compat.jei;

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
import net.minecraft.world.item.crafting.*;
import net.satisfy.meadow.client.gui.handler.CheeseFormGuiHandler;
import net.satisfy.meadow.client.gui.handler.CookingCauldronGuiHandler;
import net.satisfy.meadow.client.gui.handler.FondueGuiHandler;
import net.satisfy.meadow.compat.jei.category.CheesePressCategory;
import net.satisfy.meadow.compat.jei.category.CookingCauldronCategory;
import net.satisfy.meadow.compat.jei.category.FondueCategory;
import net.satisfy.meadow.compat.jei.category.WoodCutterCategory;
import net.satisfy.meadow.recipes.CheeseFormRecipe;
import net.satisfy.meadow.recipes.CookingCauldronRecipe;
import net.satisfy.meadow.recipes.FondueRecipe;
import net.satisfy.meadow.recipes.WoodcuttingRecipe;
import net.satisfy.meadow.registry.ObjectRegistry;
import net.satisfy.meadow.registry.RecipeRegistry;
import net.satisfy.meadow.registry.ScreenHandlerRegistry;
import net.satisfy.meadow.util.MeadowIdentifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@JeiPlugin
public class MeadowJEIPlugin implements IModPlugin {

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new WoodCutterCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new CookingCauldronCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new CheesePressCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new FondueCategory(registration.getJeiHelpers().getGuiHelper()));
    }


    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<RecipeHolder<WoodcuttingRecipe>> woodcuttingRecipesHolder = rm.getAllRecipesFor(RecipeRegistry.WOODCUTTING.get());
        List<WoodcuttingRecipe> woodcuttingRecipes = new ArrayList<>();
        woodcuttingRecipesHolder.iterator().forEachRemaining(recipeHolder -> {
            woodcuttingRecipes.add(recipeHolder.value());
        });

        registration.addRecipes(WoodCutterCategory.WOODCUTTER, woodcuttingRecipes);

        List<RecipeHolder<CookingCauldronRecipe>> cookingCauldronRecipesHolders = rm.getAllRecipesFor(RecipeRegistry.COOKING.get());
        List<CookingCauldronRecipe> cookingCauldronRecipes = new ArrayList<>();
        cookingCauldronRecipesHolders.iterator().forEachRemaining(recipeHolder -> {
            cookingCauldronRecipes.add(recipeHolder.value());
        });

        registration.addRecipes(CookingCauldronCategory.COOKING_CAULDRON, cookingCauldronRecipes);

        List<RecipeHolder<CheeseFormRecipe>> cheesePressRecipesHolders = rm.getAllRecipesFor(RecipeRegistry.CHEESE.get());
        List<CheeseFormRecipe> cheesePressRecipes = new ArrayList<>();
        cheesePressRecipesHolders.iterator().forEachRemaining(recipeHolder -> {
            cheesePressRecipes.add(recipeHolder.value());
        });
        registration.addRecipes(CheesePressCategory.CHEESE_PRESS, cheesePressRecipes);

        List<RecipeHolder<FondueRecipe>> fondueRecipesHolders = rm.getAllRecipesFor(RecipeRegistry.FONDUE.get());
        List<FondueRecipe> fondueRecipes = new ArrayList<>();
        fondueRecipesHolders.iterator().forEachRemaining(recipeHolder -> {
            fondueRecipes.add(recipeHolder.value());
        });
        registration.addRecipes(FondueCategory.FONDUE, fondueRecipes);
    }

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return MeadowIdentifier.of("jei_plugin");
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(CheeseFormGuiHandler.class, ScreenHandlerRegistry.CHEESE_FORM_SCREEN_HANDLER.get(), CheesePressCategory.CHEESE_PRESS,
                1, 2, 3, 36);

        registration.addRecipeTransferHandler(CookingCauldronGuiHandler.class, ScreenHandlerRegistry.COOKING_CAULDRON_SCREEN_HANDLER.get(), CookingCauldronCategory.COOKING_CAULDRON,
                1, 6, 7, 36);

        registration.addRecipeTransferHandler(FondueGuiHandler.class, ScreenHandlerRegistry.FONDUE_SCREEN_HANDLER.get(), FondueCategory.FONDUE,
                0, 2, 3, 36);
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
