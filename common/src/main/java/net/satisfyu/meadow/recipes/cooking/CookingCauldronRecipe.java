package net.satisfyu.meadow.recipes.cooking;

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

public class CookingCauldronRecipe implements Recipe<Inventory> {
    private final Ingredient input1;

    private final Ingredient input2;

    private final Ingredient input3;
    private final ItemStack outputStack;

    private final ItemStack outputStackIfWoodBucket;
    private final Identifier id;

    public CookingCauldronRecipe(Ingredient input1, Ingredient input2, Ingredient input3, ItemStack outputStack, ItemStack outputStackIfWoodBucket, Identifier id) {
        this.input1 = input1;
        this.input2 = input2;
        this.input3 = input3;
        this.outputStack = outputStack;
        this.outputStackIfWoodBucket = outputStackIfWoodBucket;
        this.id = id;
    }


    @Override
    public boolean matches(Inventory inventory, World world) {
        DefaultedList<Ingredient> items = getIngredients();
        List<ItemStack> items2 = new ArrayList<>(List.of(inventory.getStack(0), inventory.getStack(1), inventory.getStack(2)));
        for (Ingredient i : items) {
            boolean matches = false;
            for (ItemStack stack : items2) {
                if (i.test(stack)) {
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
        defaultedList.add(this.input1);
        defaultedList.add(this.input2);
        defaultedList.add(this.input3);
        return defaultedList;
    }

    @Override
    public ItemStack craft(Inventory inventory) {
        return this.outputStack.copy();
    }

    public ItemStack craftIfWoodBucket(Inventory inventory) {
        return this.outputStackIfWoodBucket.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return outputStack;
    }

    public ItemStack getOutputIfWoodBucket() {
        return outputStackIfWoodBucket;
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ObjectRegistry.COOKING_CAULDRON.get());
    }

    @Override
    public Identifier getId() {
        return id;
    }


    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegistry.COOKING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeRegistry.COOKING.get();
    }
}
