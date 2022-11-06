package net.satisfyu.meadow.recipes;

import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.woodCutter.WoodcuttingRecipe;
import net.satisfyu.meadow.block.woodCutter.WoodcuttingRecipeSerializer;

public class ModRecipes {

    public static RecipeType<WoodcuttingRecipe> WOODCUTTING;


    public static void registerRecipes(){
        WOODCUTTING = Registry.register(Registry.RECIPE_TYPE, new Identifier(Meadow.MOD_ID, WoodcuttingRecipe.Type.ID), WoodcuttingRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_SERIALIZER, WoodcuttingRecipeSerializer.ID, WoodcuttingRecipeSerializer.INSTANCE);
    }
}
