package net.satisfyu.meadow.fabric.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.satisfyu.meadow.fabric.rei.category.CheeseFormCategory;
import net.satisfyu.meadow.fabric.rei.category.CookingCauldronCategory;
import net.satisfyu.meadow.fabric.rei.display.CheeseFormDisplay;
import net.satisfyu.meadow.fabric.rei.display.CookingCauldronDisplay;
import net.satisfyu.meadow.recipes.cheese.CheeseFormRecipe;
import net.satisfyu.meadow.recipes.cooking.CookingCauldronRecipe;
import net.satisfyu.meadow.recipes.cooking.CookingPotRecipe;
import net.satisfyu.meadow.registry.ObjectRegistry;

public class MeadowReiClientPlugin implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new CookingCauldronCategory());
        registry.add(new CheeseFormCategory());

        registry.addWorkstations(CookingCauldronCategory.COOKING_CAULDRON_DISPLAY, EntryStacks.of(ObjectRegistry.COOKING_POT.get()));
        registry.addWorkstations(CheeseFormCategory.CHEESE_FORM_DISPLAY, EntryStacks.of(ObjectRegistry.CHEESE_FORM.get()));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(CookingCauldronRecipe.class, CookingCauldronDisplay::new);
        registry.registerFiller(CheeseFormRecipe.class, CheeseFormDisplay::new);
    }
}
