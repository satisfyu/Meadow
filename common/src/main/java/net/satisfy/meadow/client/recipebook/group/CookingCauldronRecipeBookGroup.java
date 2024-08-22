package net.satisfy.meadow.client.recipebook.group;

import com.google.common.collect.ImmutableList;
import de.cristelknight.doapi.client.recipebook.IRecipeBookGroup;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.satisfy.meadow.registry.ObjectRegistry;
import net.satisfy.meadow.registry.RecipeRegistry;

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
    public boolean fitRecipe(Recipe<? extends RecipeInput> recipe, RegistryAccess registryAccess) {
        if (recipe.getType() == RecipeRegistry.COOKING) {
            return switch (this) {
                case SEARCH -> true;
                case MEADOW -> recipe.getResultItem(registryAccess).has(DataComponents.FOOD);
                case MISC -> recipe.getResultItem(registryAccess).isDamaged();
            };
        }
        return false;
    }

    @Override
    public List<ItemStack> getIcons() {
        return this.icons;
    }

}