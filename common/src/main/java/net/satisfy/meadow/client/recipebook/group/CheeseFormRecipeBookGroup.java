package net.satisfy.meadow.client.recipebook.group;

import com.google.common.collect.ImmutableList;
import de.cristelknight.doapi.client.recipebook.IRecipeBookGroup;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.satisfy.meadow.block.CheeseBlock;
import net.satisfy.meadow.recipes.CheeseFormRecipe;
import net.satisfy.meadow.registry.ObjectRegistry;

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
    public boolean fitRecipe(Recipe<? extends Container> recipe, RegistryAccess dynamicRegistryManager) {
        if (recipe instanceof CheeseFormRecipe cheeseFormRecipe) {
            return switch (this) {
                case SEARCH -> true;
                case CHEESE ->
                        cheeseFormRecipe.getResultItem(dynamicRegistryManager).getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof CheeseBlock;
                case MISC ->
                        cheeseFormRecipe.getResultItem(dynamicRegistryManager).getItem() instanceof BlockItem blockItem && !(blockItem.getBlock() instanceof CheeseBlock);
            };
        }
        return false;
    }

    @Override
    public List<ItemStack> getIcons() {
        return this.icons;
    }

}