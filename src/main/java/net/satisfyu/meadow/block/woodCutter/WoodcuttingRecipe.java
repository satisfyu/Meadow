package net.satisfyu.meadow.block.woodCutter;

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

public class WoodcuttingRecipe implements Recipe<Inventory> {
    private final Ingredient input;
    private final ItemStack outputStack;
    private final Identifier id;

    public WoodcuttingRecipe(Ingredient input, ItemStack outputStack, Identifier id) {
        this.input = input;
        this.outputStack = outputStack;
        this.id = id;
    }

    public Ingredient getInput() {
        return input;
    }


    @Override
    public boolean matches(Inventory inventory, World world) {
        return this.input.test(inventory.getStack(0));
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

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModBlocks.WOODCUTTER);
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return WoodcuttingRecipeSerializer.INSTANCE;
    }

    public static class Type implements RecipeType<WoodcuttingRecipe> {
        private Type() {}

        public static final Type INSTANCE = new Type();

        public static final String ID = "woodcutting";
    }
    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
}
