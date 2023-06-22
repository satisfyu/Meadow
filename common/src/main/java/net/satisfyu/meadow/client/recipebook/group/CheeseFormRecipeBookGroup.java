package net.satisfyu.meadow.client.recipebook.group;

import com.google.common.collect.ImmutableList;
import de.cristelknight.doapi.client.recipebook.IRecipeBookGroup;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Recipe;
import net.satisfyu.meadow.block.CheeseBlock;
import net.satisfyu.meadow.recipes.cheese.CheeseFormRecipe;
import net.satisfyu.meadow.registry.ObjectRegistry;

import java.util.List;

@Environment(EnvType.CLIENT)
public enum CheeseFormRecipeBookGroup implements IRecipeBookGroup {
    SEARCH(new ItemStack(Items.COMPASS)),
    CHEESE(new ItemStack(ObjectRegistry.PIECE_OF_CHEESE.get())),
    MISC(new ItemStack(ObjectRegistry.RENNET.get()));

    public static final List<IRecipeBookGroup> CHEESE_GROUPS = ImmutableList.of(SEARCH, CHEESE, MISC);

    private final List<ItemStack> icons;

    CheeseFormRecipeBookGroup(ItemStack... entries) {
        this.icons = ImmutableList.copyOf(entries);
    }

    public boolean fitRecipe(Recipe<?> recipe) {
        if (recipe instanceof CheeseFormRecipe cheeseFormRecipe) {
            return switch (this) {
                case SEARCH -> true;
                case CHEESE ->
                        recipe.getOutput().getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof CheeseBlock;
                case MISC ->
                        recipe.getOutput().getItem() instanceof BlockItem blockItem && !(blockItem.getBlock() instanceof CheeseBlock);
            };
        }
        return false;
    }

    @Override
    public List<ItemStack> getIcons() {
        return this.icons;
    }

}