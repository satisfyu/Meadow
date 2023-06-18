package net.satisfyu.meadow.recipes.cheese;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.satisfyu.meadow.registry.ObjectRegistry;
import net.satisfyu.meadow.registry.RecipeRegistry;

import java.util.ArrayList;
import java.util.List;

public class CheeseFormRecipe implements Recipe<Inventory> {
    private final Ingredient bucket;
    private final Ingredient input;

    private final ItemStack outputStack;

    private final Identifier id;

    public CheeseFormRecipe(Ingredient bucket, Ingredient input, ItemStack outputStack, Identifier id) {
        this.bucket = bucket;
        this.input = input;
        this.outputStack = outputStack;
        this.id = id;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        DefaultedList<Ingredient> ingredients = getIngredients();
        List<ItemStack> items = new ArrayList<>(List.of(inventory.getStack(1), inventory.getStack(2)));
        for (Ingredient ingredient : ingredients) {
            boolean matches = false;
            for (ItemStack stack : items) {
                if (ingredient.test(stack)) {
                    matches = true;
                    break;
                }
            }
            if (!matches) {
                return false;
            }
        }
        return true;
    }


    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> defaultedList = DefaultedList.of();
        defaultedList.add(this.bucket);
        defaultedList.add(this.input);
        return defaultedList;
    }

    @Override
    public ItemStack craft(Inventory inventory) {
        return this.outputStack.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return outputStack;
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ObjectRegistry.CHEESE_FORM.get());
    }

    @Override
    public Identifier getId() {
        return id;
    }


    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegistry.CHEESE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeRegistry.CHEESE.get();
    }
}
