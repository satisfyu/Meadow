package net.satisfyu.meadow.mixin;

import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.client.recipebook.RecipeBookGroup;
import net.minecraft.recipe.Recipe;
import net.satisfyu.meadow.recipes.cheese.CheeseFormRecipe;
import net.satisfyu.meadow.recipes.woodcutting.WoodcuttingRecipe;
import net.satisfyu.meadow.recipes.cooking.CookingCauldronRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientRecipeBook.class)
public abstract class NoUnknowRecipeCategoryMixin {

    @Inject(
            method = "getGroupForRecipe",
            at = @At(value = "HEAD"), cancellable = true)


    private static void get(Recipe<?> recipe, CallbackInfoReturnable<RecipeBookGroup> cir) {
        if(recipe.getType() == WoodcuttingRecipe.Type.INSTANCE || recipe.getType() == CookingCauldronRecipe.Type.INSTANCE || recipe.getType() == CheeseFormRecipe.Type.INSTANCE){
            cir.setReturnValue(RecipeBookGroup.UNKNOWN);
        }
    }
}
