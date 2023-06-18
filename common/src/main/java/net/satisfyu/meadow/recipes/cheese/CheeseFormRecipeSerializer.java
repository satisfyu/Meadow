package net.satisfyu.meadow.recipes.cheese;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class CheeseFormRecipeSerializer implements RecipeSerializer<CheeseFormRecipe> {

    private CheeseFormRecipeSerializer() {
    }

    public static final CheeseFormRecipeSerializer INSTANCE = new CheeseFormRecipeSerializer();

    @Override
    // Turns json into Recipe
    public CheeseFormRecipe read(Identifier id, JsonObject json) {
        CheeseFormRecipeJsonFormat recipeJson = new Gson().fromJson(json, CheeseFormRecipeJsonFormat.class);

        if (recipeJson.bucketItem == null || recipeJson.inputItem == null || recipeJson.outputItem == null) {
            throw new JsonSyntaxException("A required attribute is missing!");
        }

        Ingredient bucket = Ingredient.fromJson(recipeJson.bucketItem);
        Ingredient input = Ingredient.fromJson(recipeJson.inputItem);

        Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
                // Validate the inputted item actually exists
                .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.outputItem));
        ItemStack output = new ItemStack(outputItem);


        return new CheeseFormRecipe(bucket, input, output, id);
    }
    @Override
    // Turns Recipe into PacketByteBuf
    public void write(PacketByteBuf packetData, CheeseFormRecipe recipe) {
        recipe.getIngredients().forEach(ingredient -> ingredient.write(packetData));
        packetData.writeItemStack(recipe.getOutput());
    }

    @Override
    // Turns PacketByteBuf into Recipe
    public CheeseFormRecipe read(Identifier id, PacketByteBuf packetData) {
        Ingredient bucket = Ingredient.fromPacket(packetData);
        Ingredient input = Ingredient.fromPacket(packetData);
        ItemStack output = packetData.readItemStack();
        return new CheeseFormRecipe(bucket, input, output, id);
    }
}
