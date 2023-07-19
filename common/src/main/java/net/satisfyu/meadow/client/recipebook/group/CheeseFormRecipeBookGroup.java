package net.satisfyu.meadow.client.recipebook.group;

import com.google.common.collect.ImmutableList;
import de.cristelknight.doapi.client.recipebook.IRecipeBookGroup;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.DynamicRegistryManager;
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

    @Override
    public boolean fitRecipe(Recipe<? extends Inventory> recipe, DynamicRegistryManager dynamicRegistryManager) {
        if (recipe instanceof CheeseFormRecipe cheeseFormRecipe) {
            return switch (this) {
                case SEARCH -> true;
                case CHEESE ->
                        cheeseFormRecipe.getOutput(dynamicRegistryManager).getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof CheeseBlock;
                case MISC ->
                        cheeseFormRecipe.getOutput(dynamicRegistryManager).getItem() instanceof BlockItem blockItem && !(blockItem.getBlock() instanceof CheeseBlock);
            };
        }
        return false;
    }

    @Override
    public List<ItemStack> getIcons() {
        return this.icons;
    }

}