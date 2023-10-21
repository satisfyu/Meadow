package net.satisfyu.meadow.recipes.woodcutting;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.satisfyu.meadow.registry.RecipeRegistry;

public class WoodcuttingRecipe
        extends SingleItemRecipe {
    public WoodcuttingRecipe(String string, Ingredient ingredient, ItemStack itemStack) {
        super(RecipeRegistry.WOODCUTTING.get(), RecipeRegistry.WOODCUTTING_SERIALIZER.get(), string, ingredient, itemStack);
    }

    @Override
    public boolean matches(Container container, Level level) {
        return this.ingredient.test(container.getItem(0));
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(Blocks.STONECUTTER);
    }
}

