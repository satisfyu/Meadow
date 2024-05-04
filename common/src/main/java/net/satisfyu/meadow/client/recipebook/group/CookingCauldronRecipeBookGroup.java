package net.satisfyu.meadow.client.recipebook.group;

import com.google.common.collect.ImmutableList;
import de.cristelknight.doapi.client.recipebook.IRecipeBookGroup;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.satisfyu.meadow.recipes.CookingCauldronRecipe;
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
    public boolean fitRecipe(Recipe<? extends Container> recipe, RegistryAccess dynamicRegistryManager) {
        if (recipe instanceof CookingCauldronRecipe cookingCauldronRecipe) {
            return switch (this) {
                case SEARCH -> true;
                case MEADOW -> recipe.getResultItem(dynamicRegistryManager).isEdible();
                case MISC -> recipe.getResultItem(dynamicRegistryManager).getItem().canBeDepleted();
            };
        }
        return false;
    }

    @Override
    public List<ItemStack> getIcons() {
        return this.icons;
    }

}