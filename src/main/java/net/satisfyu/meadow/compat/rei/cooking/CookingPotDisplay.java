package net.satisfyu.meadow.compat.rei.cooking;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.compat.rei.MeadowReiClientPlugin;
import net.satisfyu.meadow.recipes.pot.CookingPotRecipe;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CookingPotDisplay extends BasicDisplay {


    public CookingPotDisplay(CookingPotRecipe recipe) {
        this(EntryIngredients.ofIngredients(MeadowReiClientPlugin.ingredients(recipe, getContainer(recipe))), Collections.singletonList(EntryIngredients.of(recipe.getOutput())), Optional.ofNullable(recipe.getId()));
    }

    public CookingPotDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<Identifier> location) {
        super(inputs, outputs, location);
    }


    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CookingPotCategory.COOKING_POT_DISPLAY;
    }


    public static ItemStack getContainer(Recipe<Inventory> recipe){
        if(recipe instanceof CookingPotRecipe c){
            return c.getContainer();
        }
        else return ItemStack.EMPTY;
    }

}
