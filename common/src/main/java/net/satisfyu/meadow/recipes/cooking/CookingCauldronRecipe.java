package net.satisfyu.meadow.recipes.cooking;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
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
import net.satisfyu.meadow.util.GeneralUtil;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CookingCauldronRecipe implements Recipe<Inventory> {
    private final DefaultedList<Ingredient> ingredients;
    private final ItemStack outputStack;
    @Nullable
    private final ItemStack outputStackIfWoodBucket;
    private final Identifier id;

    public CookingCauldronRecipe(Identifier id, DefaultedList<Ingredient> ingredients, ItemStack outputStack, @Nullable ItemStack outputStackIfWoodBucket) {
        this.id = id;
        this.ingredients = ingredients;
        this.outputStack = outputStack;
        this.outputStackIfWoodBucket = outputStackIfWoodBucket;
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
        return this.ingredients;
    }

    @Override
    public ItemStack craft(Inventory inventory) {
        return this.outputStack.copy();
    }

    public ItemStack craftIfWoodBucket() {
        return this.outputStackIfWoodBucket == null ? this.outputStack : this.outputStackIfWoodBucket.copy();
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

    public static class Serializer implements RecipeSerializer<CookingCauldronRecipe> {
        @Override
        public CookingCauldronRecipe read(Identifier id, JsonObject json) {
            final var ingredients = GeneralUtil.deserializeIngredients(json.getAsJsonArray("ingredients"));

            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for CookingCauldron Recipe");
            } else if (ingredients.size() > 6) {
                throw new JsonParseException("Too many ingredients for CookingCauldron Recipe");
            } else {
                JsonElement jsonResultElement = json.get("result");
                JsonElement jsonWoodResultElement = json.get("resultIfWoodBucket");
                return new CookingCauldronRecipe(id, ingredients, JsonHelper.asItem(jsonResultElement, jsonResultElement.getAsString()).getDefaultStack(), JsonHelper.asItem(jsonWoodResultElement, jsonWoodResultElement.getAsString()).getDefaultStack());
            }
        }

        @Override
        public void write(PacketByteBuf buf, CookingCauldronRecipe recipe) {
            buf.writeVarInt(recipe.ingredients.size());
            recipe.ingredients.forEach(entry -> entry.write(buf));
            buf.writeItemStack(recipe.outputStack);
            buf.writeItemStack(recipe.outputStackIfWoodBucket);
        }

        @Override
        public CookingCauldronRecipe read(Identifier id, PacketByteBuf buf) {
            final var ingredients = DefaultedList.ofSize(buf.readVarInt(), Ingredient.EMPTY);
            ingredients.replaceAll(ignored -> Ingredient.fromPacket(buf));
            return new CookingCauldronRecipe(id, ingredients, buf.readItemStack(), buf.readItemStack());
        }
    }
}
