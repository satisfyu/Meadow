package net.satisfyu.meadow.fabric.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.satisfyu.meadow.fabric.rei.category.CookingCauldronCategory;
import net.satisfyu.meadow.fabric.rei.display.CookingCauldronDisplay;
import net.satisfyu.meadow.recipes.cooking.CookingPotRecipe;
import net.satisfyu.meadow.registry.ObjectRegistry;

import java.util.ArrayList;
import java.util.List;

public class MeadowReiClientPlugin implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new CookingCauldronCategory());

        registry.addWorkstations(CookingCauldronCategory.COOKING_CAULDRON_DISPLAY, EntryStacks.of(ObjectRegistry.COOKING_POT.get()));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(CookingPotRecipe.class, CookingCauldronDisplay::new);
    }

    public static List<Ingredient> ingredients(Recipe<Inventory> recipe, ItemStack stack){
        List<Ingredient> l = new ArrayList<>(recipe.getIngredients());
        l.add(0, Ingredient.ofItems(stack.getItem()));
        return l;
    }
}
