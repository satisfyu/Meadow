package net.satisfyu.meadow.recipes;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.recipes.cheese.CheeseFormRecipe;
import net.satisfyu.meadow.recipes.cheese.CheeseFormRecipeSerializer;
import net.satisfyu.meadow.recipes.pot.CookingPotRecipe;
import net.satisfyu.meadow.recipes.woodcutting.WoodcuttingRecipe;
import net.satisfyu.meadow.recipes.woodcutting.WoodcuttingRecipeSerializer;
import net.satisfyu.meadow.recipes.cooking.CookingCauldronRecipe;
import net.satisfyu.meadow.recipes.cooking.CookingCauldronRecipeSerializer;

public class ModRecipes {

    public static RecipeType<WoodcuttingRecipe> WOODCUTTING;

    public static RecipeType<CookingCauldronRecipe> COOKING;

    public static RecipeType<CheeseFormRecipe> CHEESE;

    public static RecipeType<CookingPotRecipe> POT_COOKING;

    public static RecipeSerializer<CookingPotRecipe> COOKING_POT_RECIPE_SERIALIZER;



    public static void registerRecipes(){
        WOODCUTTING = Registry.register(Registry.RECIPE_TYPE, new Identifier(Meadow.MOD_ID, WoodcuttingRecipe.Type.ID), WoodcuttingRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_SERIALIZER, WoodcuttingRecipeSerializer.ID, WoodcuttingRecipeSerializer.INSTANCE);

        COOKING = Registry.register(Registry.RECIPE_TYPE, new Identifier(Meadow.MOD_ID, CookingCauldronRecipe.Type.ID), CookingCauldronRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_SERIALIZER, CookingCauldronRecipeSerializer.ID, CookingCauldronRecipeSerializer.INSTANCE);

        POT_COOKING = create("pot_cooking");
        COOKING_POT_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Meadow.MOD_ID, "pot_cooking"), new CookingPotRecipe.Serializer());

        CHEESE = Registry.register(Registry.RECIPE_TYPE, new Identifier(Meadow.MOD_ID, CheeseFormRecipe.Type.ID), CheeseFormRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_SERIALIZER, CheeseFormRecipeSerializer.ID, CheeseFormRecipeSerializer.INSTANCE);
    }

    private static <T extends Recipe<?>> RecipeType<T> create(String name) {
        final RecipeType<T> type = new RecipeType<>() {
            @Override
            public String toString() {
                return name;
            }
        };
        Registry.register(Registry.RECIPE_TYPE, new Identifier(Meadow.MOD_ID, name), type);
        return type;
    }
}
