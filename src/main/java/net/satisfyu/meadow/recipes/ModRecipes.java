package net.satisfyu.meadow.recipes;

import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.recipes.cheese.CheeseFormRecipe;
import net.satisfyu.meadow.recipes.cheese.CheeseFormRecipeSerializer;
import net.satisfyu.meadow.recipes.woodcutting.WoodcuttingRecipe;
import net.satisfyu.meadow.recipes.woodcutting.WoodcuttingRecipeSerializer;
import net.satisfyu.meadow.recipes.cooking.CookingCauldronRecipe;
import net.satisfyu.meadow.recipes.cooking.CookingCauldronRecipeSerializer;

public class ModRecipes {

    public static RecipeType<WoodcuttingRecipe> WOODCUTTING;

    public static RecipeType<CookingCauldronRecipe> COOKING;

    public static RecipeType<CheeseFormRecipe> CHEESE;


    public static void registerRecipes(){
        WOODCUTTING = Registry.register(Registry.RECIPE_TYPE, new Identifier(Meadow.MOD_ID, WoodcuttingRecipe.Type.ID), WoodcuttingRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_SERIALIZER, WoodcuttingRecipeSerializer.ID, WoodcuttingRecipeSerializer.INSTANCE);

        COOKING = Registry.register(Registry.RECIPE_TYPE, new Identifier(Meadow.MOD_ID, CookingCauldronRecipe.Type.ID), CookingCauldronRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_SERIALIZER, CookingCauldronRecipeSerializer.ID, CookingCauldronRecipeSerializer.INSTANCE);

        CHEESE = Registry.register(Registry.RECIPE_TYPE, new Identifier(Meadow.MOD_ID, CheeseFormRecipe.Type.ID), CheeseFormRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_SERIALIZER, CheeseFormRecipeSerializer.ID, CheeseFormRecipeSerializer.INSTANCE);
    }
}
