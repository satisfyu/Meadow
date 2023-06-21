package net.satisfyu.meadow.recipes.woodcutting;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.satisfyu.meadow.registry.ObjectRegistry;
import net.satisfyu.meadow.registry.RecipeRegistry;

public class WoodcuttingRecipe implements Recipe<Inventory> {
    private final Ingredient input;
    private final ItemStack outputStack;
    private final Identifier id;

    public WoodcuttingRecipe(Ingredient input, ItemStack outputStack, Identifier id) {
        this.id = id;
        this.input = input;
        this.outputStack = outputStack;
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
        return new ItemStack(ObjectRegistry.WOODCUTTER.get());
    }

    @Override
    public Identifier getId() {
        return id;
    }


    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegistry.WOODCUTTING_SERIALIZER.get();
    }


    @Override
    public RecipeType<?> getType() {
        return RecipeRegistry.WOODCUTTING.get();
    }

    public static class Serializer implements RecipeSerializer<WoodcuttingRecipe> {
        @Override
        // Turns json into Recipe
        public WoodcuttingRecipe read(Identifier id, JsonObject json) {
            WoodcuttingRecipeJsonFormat recipeJson = new Gson().fromJson(json, WoodcuttingRecipeJsonFormat.class);

            if (recipeJson.inputItem == null || recipeJson.outputItem == null) {
                throw new JsonSyntaxException("A required attribute is missing!");
            }
            // We'll allow to not specify the output, and default it to 1.
            if (recipeJson.outputAmount == 0) recipeJson.outputAmount = 1;


            Ingredient input = Ingredient.fromJson(recipeJson.inputItem);

            Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
                    // Validate the inputted item actually exists
                    .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.outputItem));
            ItemStack output = new ItemStack(outputItem, recipeJson.outputAmount);

            return new WoodcuttingRecipe(input, output, id);
        }

        @Override
        // Turns Recipe into PacketByteBuf
        public void write(PacketByteBuf packetData, WoodcuttingRecipe recipe) {
            recipe.getInput().write(packetData);
            packetData.writeItemStack(recipe.getOutput());
        }

        @Override
        // Turns PacketByteBuf into Recipe
        public WoodcuttingRecipe read(Identifier id, PacketByteBuf packetData) {
            Ingredient input = Ingredient.fromPacket(packetData);
            ItemStack output = packetData.readItemStack();
            return new WoodcuttingRecipe(input, output, id);
        }
    }
}
