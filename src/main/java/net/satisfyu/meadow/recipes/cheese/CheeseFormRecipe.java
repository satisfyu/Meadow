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
import net.satisfyu.meadow.block.ModBlocks;

public class CheeseFormRecipe implements Recipe<Inventory> {
    private final Ingredient input;

    private final ItemStack outputStack;

    private final Identifier id;

    public CheeseFormRecipe(Ingredient input, ItemStack outputStack, Identifier id) {
        this.input = input;
        this.outputStack = outputStack;
        this.id = id;
    }



    @Override
    public boolean matches(Inventory inventory, World world) {
        return input.test(inventory.getStack(0));
    }


    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> defaultedList = DefaultedList.of();
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

    public Ingredient getInput() {
        return input;
    }


    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModBlocks.CHEESE_FORM);
    }

    @Override
    public Identifier getId() {
        return id;
    }


    @Override
    public RecipeSerializer<?> getSerializer() {
        return CheeseFormRecipeSerializer.INSTANCE;
    }

    public static class Type implements RecipeType<CheeseFormRecipe> {
        private Type() {}

        public static final Type INSTANCE = new Type();

        public static final String ID = "cheese";
    }
    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
}
