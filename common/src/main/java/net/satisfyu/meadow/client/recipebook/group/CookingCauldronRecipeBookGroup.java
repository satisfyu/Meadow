package net.satisfyu.meadow.client.recipebook.group;

import com.google.common.collect.ImmutableList;
import de.cristelknight.doapi.client.recipebook.IRecipeBookGroup;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.recipes.cooking.CookingCauldronRecipe;
import net.satisfyu.meadow.registry.ObjectRegistry;

import java.util.List;

@Environment(EnvType.CLIENT)
public enum CookingCauldronRecipeBookGroup implements IRecipeBookGroup {
    SEARCH(new ItemStack(Items.COMPASS)),
    MEADOW(new ItemStack(ObjectRegistry.RENNET.get())),
    MISC(new ItemStack(Items.COOKED_BEEF));

    public static final List<IRecipeBookGroup> CAULDRON_GROUPS = ImmutableList.of(SEARCH, MEADOW, MISC);

    private final List<ItemStack> icons;

    CookingCauldronRecipeBookGroup(ItemStack... entries) {
        this.icons = ImmutableList.copyOf(entries);
    }

    @Override
    public boolean fitRecipe(Recipe<? extends Inventory> recipe, DynamicRegistryManager dynamicRegistryManager) {
        if (recipe instanceof CookingCauldronRecipe cookingCauldronRecipe) {
            return switch (this) {
                case SEARCH -> true;
                case MEADOW -> recipe.getOutput(dynamicRegistryManager).isFood();
                case MISC -> recipe.getOutput(dynamicRegistryManager).getItem().isDamageable();
            };
        }
        return false;
    }

    @Override
    public List<ItemStack> getIcons() {
        return this.icons;
    }

}