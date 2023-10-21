package net.satisfyu.meadow.compat.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.resources.ResourceLocation;
import net.satisfyu.meadow.compat.rei.category.CookingCauldronCategory;
import net.satisfyu.meadow.recipes.cooking.CookingCauldronRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CookingCauldronDisplay extends BasicDisplay {
    public CookingCauldronDisplay(CookingCauldronRecipe recipe) {
        this(EntryIngredients.ofIngredients(new ArrayList<>(recipe.getIngredients())), Collections.singletonList(EntryIngredients.of(recipe.getResultItem(BasicDisplay.registryAccess()))));
    }

    public CookingCauldronDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CookingCauldronCategory.COOKING_CAULDRON_DISPLAY;
    }
}
