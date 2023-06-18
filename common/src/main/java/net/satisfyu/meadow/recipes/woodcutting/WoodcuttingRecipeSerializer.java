package net.satisfyu.meadow.recipes.woodcutting;

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

public class WoodcuttingRecipeSerializer implements RecipeSerializer<WoodcuttingRecipe> {

    private WoodcuttingRecipeSerializer() {
    }

    public static final WoodcuttingRecipeSerializer INSTANCE = new WoodcuttingRecipeSerializer();

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
