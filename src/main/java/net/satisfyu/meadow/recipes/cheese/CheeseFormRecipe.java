package net.satisfyu.meadow.recipes.cheese;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.satisfyu.meadow.recipes.ModRecipes;
import net.satisfyu.meadow.util.GeneralUtil;

public class CheeseFormRecipe implements Recipe<Inventory> {

    private final Identifier id;
    private final Ingredient input;
    private final ItemStack output;

    public CheeseFormRecipe(Identifier id, Ingredient input, ItemStack output) {
        this.id = id;
        this.input = input;
        this.output = output;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        return GeneralUtil.matchesRecipe(inventory, input, 0, 6);
    }

    @Override
    public ItemStack craft(Inventory inventory) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getOutput() {
        return this.output.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CHEESE_FORM_RECIPE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.CHEESE_CRAFTING;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> ingredients = DefaultedList.of();
        ingredients.add(input);
        return ingredients;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }

    public static class Serializer implements RecipeSerializer<CheeseFormRecipe> {

        @Override
        public CheeseFormRecipe read(Identifier id, JsonObject json) {
            final Ingredient ingredient = GeneralUtil.deserializeIngredient(JsonHelper.getObject(json, "ingredient"));
            return new CheeseFormRecipe(id, ingredient, ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "result")));
        }

        @Override
        public CheeseFormRecipe read(Identifier id, PacketByteBuf buf) {
            final Ingredient ingredient = Ingredient.fromPacket(buf);
            return new CheeseFormRecipe(id, ingredient, buf.readItemStack());
        }

        @Override
        public void write(PacketByteBuf buf, CheeseFormRecipe recipe) {
            recipe.input.write(buf);
            buf.writeItemStack(recipe.getOutput());
        }
    }

    public static class Type implements RecipeType<CheeseFormRecipe> {
        private Type() {}

        public static final CheeseFormRecipe.Type INSTANCE = new CheeseFormRecipe.Type();

        public static final String ID = "cooking";
    }
}
