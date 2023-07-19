package net.satisfyu.meadow.compat.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.compat.rei.category.CheeseFormCategory;
import net.satisfyu.meadow.recipes.cheese.CheeseFormRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CheeseFormDisplay extends BasicDisplay {


    public CheeseFormDisplay(CheeseFormRecipe recipe) {
        this(EntryIngredients.ofIngredients(new ArrayList<>(recipe.getIngredients())), Collections.singletonList(EntryIngredients.of(recipe.getOutput(BasicDisplay.registryAccess()))), Optional.ofNullable(recipe.getId()));
    }

    public CheeseFormDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<Identifier> location) {
        super(inputs, outputs, location);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CheeseFormCategory.CHEESE_FORM_DISPLAY;
    }
}
