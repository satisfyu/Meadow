package net.satisfyu.meadow.recipes.cooking;

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
import net.satisfyu.meadow.registry.RecipeRegistry;
import net.satisfyu.meadow.util.GeneralUtil;

public class CookingPotRecipe implements Recipe<Inventory> {

    final Identifier id;
    private final DefaultedList<Ingredient> inputs;
    private final ItemStack output;

    public CookingPotRecipe(Identifier id, DefaultedList<Ingredient> inputs, ItemStack output) {
        this.id = id;
        this.inputs = inputs;
        this.output = output;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        return GeneralUtil.matchesRecipe(inventory, inputs, 0, 6);
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
        return RecipeRegistry.COOKING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeRegistry.COOKING.get();
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return this.inputs;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }

    public static class Serializer implements RecipeSerializer<CookingPotRecipe> {

        @Override
        public CookingPotRecipe read(Identifier id, JsonObject json) {
            final var ingredients = GeneralUtil.deserializeIngredients(JsonHelper.getArray(json, "ingredients"));
            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for CookingPot Recipe");
            } else if (ingredients.size() > 6) {
                throw new JsonParseException("Too many ingredients for CookingPot Recipe");
            } else {
                return new CookingPotRecipe(id, ingredients, ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "result")));
            }
        }

        @Override
        public CookingPotRecipe read(Identifier id, PacketByteBuf buf) {
            final var ingredients = DefaultedList.ofSize(buf.readVarInt(), Ingredient.EMPTY);
            ingredients.replaceAll(ignored -> Ingredient.fromPacket(buf));
            return new CookingPotRecipe(id, ingredients, buf.readItemStack());
        }

        @Override
        public void write(PacketByteBuf buf, CookingPotRecipe recipe) {
            buf.writeVarInt(recipe.inputs.size());
            recipe.inputs.forEach(entry -> entry.write(buf));
            buf.writeItemStack(recipe.getOutput());
        }
    }

    public static class Type implements RecipeType<CookingPotRecipe> {
        private Type() {
        }

        public static final CookingPotRecipe.Type INSTANCE = new CookingPotRecipe.Type();

        public static final String ID = "cooking";
    }
}