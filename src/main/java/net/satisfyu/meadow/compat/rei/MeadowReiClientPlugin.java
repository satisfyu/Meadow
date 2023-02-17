package net.satisfyu.meadow.compat.rei;


import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.common.BuiltinPlugin;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.compat.rei.cooking.CookingPotCategory;
import net.satisfyu.meadow.compat.rei.cooking.CookingPotDisplay;
import net.satisfyu.meadow.recipes.pot.CookingPotRecipe;

import java.util.ArrayList;
import java.util.List;

public class MeadowReiClientPlugin implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new CookingPotCategory());


        registry.addWorkstations(CookingPotDisplay.COOKING_POT_DISPLAY, EntryStacks.of(ModBlocks.COOKING_POT));

    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(CookingPotRecipe.class, CookingPotDisplay::new);
    }


    public static List<Ingredient> ingredients(Recipe<Inventory> recipe, ItemStack stack){
        List<Ingredient> l = new ArrayList<>(recipe.getIngredients());
        l.add(0, Ingredient.ofItems(stack.getItem()));
        return l;
    }

}
