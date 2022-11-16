package net.satisfyu.meadow.recipes.cooking;

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

public class CookingCauldronRecipeSerializer implements RecipeSerializer<CookingCauldronRecipe> {

    private CookingCauldronRecipeSerializer() {
    }

    public static final CookingCauldronRecipeSerializer INSTANCE = new CookingCauldronRecipeSerializer();

    // This will be the "type" field in the json
    public static final Identifier ID = new Identifier(MOD_ID,"cooking");

    @Override
    // Turns json into Recipe
    public CookingCauldronRecipe read(Identifier id, JsonObject json) {
        CookingCauldronRecipeJsonFormat recipeJson = new Gson().fromJson(json, CookingCauldronRecipeJsonFormat.class);

        if (recipeJson.inputItem1 == null || recipeJson.inputItem2 == null || recipeJson.inputItem3 == null || recipeJson.outputItem == null || recipeJson.outputItemIfWoodBucket == null) {
            throw new JsonSyntaxException("A required attribute is missing!");
        }

        Ingredient input1 = Ingredient.fromJson(recipeJson.inputItem1);
        Ingredient input2 = Ingredient.fromJson(recipeJson.inputItem2);
        Ingredient input3 = Ingredient.fromJson(recipeJson.inputItem3);

        Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
                // Validate the inputted item actually exists
                .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.outputItem));
        Item outputItemIfBucket = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
                // Validate the inputted item actually exists
                .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.outputItemIfWoodBucket));
        ItemStack output = new ItemStack(outputItem);

        ItemStack outputIfBucket = new ItemStack(outputItemIfBucket);

        return new CookingCauldronRecipe(input1, input2, input3, output, outputIfBucket,  id);
    }
    @Override
    // Turns Recipe into PacketByteBuf
    public void write(PacketByteBuf packetData, CookingCauldronRecipe recipe) {
        recipe.getIngredients().forEach(ingredient -> ingredient.write(packetData));
        packetData.writeItemStack(recipe.getOutput());
        packetData.writeItemStack(recipe.getOutputIfWoodBucket());
    }

    @Override
    // Turns PacketByteBuf into Recipe
    public CookingCauldronRecipe read(Identifier id, PacketByteBuf packetData) {
        Ingredient input1 = Ingredient.fromPacket(packetData);
        Ingredient input2 = Ingredient.fromPacket(packetData);
        Ingredient input3 = Ingredient.fromPacket(packetData);
        ItemStack output = packetData.readItemStack();
        ItemStack output2 = packetData.readItemStack();
        return new CookingCauldronRecipe(input1, input2, input3, output, output2, id);
    }
}
