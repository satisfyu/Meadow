package net.satisfyu.meadow.recipes.cheese;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.satisfyu.meadow.registry.ObjectRegistry;
import net.satisfyu.meadow.registry.RecipeRegistry;

import java.util.ArrayList;
import java.util.List;

public class CheeseFormRecipe implements Recipe<Inventory> {
    private final Identifier id;
    private final Ingredient bucket;
    private final Ingredient ingredient;
    private final ItemStack result;

    public CheeseFormRecipe(Identifier id, Ingredient bucket, Ingredient ingredient, ItemStack result) {
        this.id = id;
        this.bucket = bucket;
        this.ingredient = ingredient;
        this.result = result;
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
        defaultedList.add(this.ingredient);
        return defaultedList;
    }

    @Override
    public ItemStack craft(Inventory inventory) {
        return this.result.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return result;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
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

    public static class Serializer implements RecipeSerializer<CheeseFormRecipe> {

        @Override
        public CheeseFormRecipe read(Identifier id, JsonObject json) {
            JsonElement jsonResultElement = json.get("result");
            ItemStack resultItem = JsonHelper.asItem(jsonResultElement, jsonResultElement.getAsString()).getDefaultStack();

            JsonObject jsonBucketObject = json.getAsJsonObject("bucket");
            Ingredient bucket = Ingredient.fromJson(jsonBucketObject);

            JsonObject jsonIngredientObject = json.getAsJsonObject("ingredient");
            Ingredient ingredient = Ingredient.fromJson(jsonIngredientObject);

            return new CheeseFormRecipe(id, bucket, ingredient, resultItem);
        }

        @Override
        public void write(PacketByteBuf packetData, CheeseFormRecipe recipe) {
            recipe.getIngredients().forEach(ingredient -> ingredient.write(packetData));
            packetData.writeItemStack(recipe.getOutput());
        }

        @Override
        public CheeseFormRecipe read(Identifier id, PacketByteBuf packetData) {
            Ingredient bucket = Ingredient.fromPacket(packetData);
            Ingredient input = Ingredient.fromPacket(packetData);
            ItemStack output = packetData.readItemStack();
            return new CheeseFormRecipe(id, bucket, input, output);
        }
    }

}
