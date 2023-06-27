package net.satisfyu.meadow.forge.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.forge.REIPluginClient;
import net.satisfyu.meadow.compat.rei.MeadowReiClientPlugin;
import net.satisfyu.meadow.compat.rei.category.CheeseFormCategory;
import net.satisfyu.meadow.compat.rei.category.CookingCauldronCategory;
import net.satisfyu.meadow.compat.rei.display.CheeseFormDisplay;
import net.satisfyu.meadow.compat.rei.display.CookingCauldronDisplay;
import net.satisfyu.meadow.recipes.cheese.CheeseFormRecipe;
import net.satisfyu.meadow.recipes.cooking.CookingCauldronRecipe;
import net.satisfyu.meadow.registry.ObjectRegistry;

@REIPluginClient
public class MeadowReiClientPluginForge implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        MeadowReiClientPlugin.registerCategories(registry);
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        MeadowReiClientPlugin.registerDisplays(registry);
    }
}
