package net.satisfyu.meadow.recipes.cheese;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.satisfyu.meadow.registry.RecipeRegistry;

import java.util.ArrayList;
import java.util.List;

public class CheeseFormRecipe implements Recipe<Container> {
    private final Ingredient bucket;
    private final Ingredient ingredient;
    private final ItemStack result;

    public CheeseFormRecipe(Ingredient bucket, Ingredient ingredient, ItemStack result) {
        this.bucket = bucket;
        this.ingredient = ingredient;
        this.result = result;
    }

    @Override
    public boolean matches(Container inventory, Level world) {
        NonNullList<Ingredient> ingredients = getIngredients();
        List<ItemStack> items = new ArrayList<>(List.of(inventory.getItem(1), inventory.getItem(2)));
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

    public ItemStack assemble() {
        return assemble(null, null);
    }

    @Override
    public ItemStack assemble(Container inventory, RegistryAccess registryManager) {
        return this.result.copy();
    }


    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> defaultedList = NonNullList.create();
        defaultedList.add(bucket);
        if(ingredient != null) defaultedList.add(ingredient);
        return defaultedList;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    public ItemStack getResultItem() {
        return getResultItem(null);
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryManager) {
        return result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegistry.CHEESE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeRegistry.CHEESE.get();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public static class Serializer implements RecipeSerializer<CheeseFormRecipe> {

        private static final Codec<CheeseFormRecipe> CODEC = RecordCodecBuilder.create((instance) ->
                instance.group(
                                Ingredient.CODEC_NONEMPTY.fieldOf("bucket").forGetter((recipe) -> recipe.bucket),
                                Ingredient.CODEC.fieldOf("ingredient").forGetter((recipe) -> recipe.ingredient),
                                RecipeRegistry.RESULT_CODEC.forGetter((recipe) -> recipe.result))
                        .apply(instance, CheeseFormRecipe::new));

        @Override
        public Codec<CheeseFormRecipe> codec() {
            return CODEC;
        }

        @Override
        public CheeseFormRecipe fromNetwork(FriendlyByteBuf buf) {
            Ingredient bucket = Ingredient.fromNetwork(buf);
            Ingredient input = Ingredient.fromNetwork(buf);
            ItemStack output = buf.readItem();
            return new CheeseFormRecipe(bucket, input, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, CheeseFormRecipe recipe) {
            recipe.getIngredients().forEach(ingredient -> ingredient.toNetwork(friendlyByteBuf));
            friendlyByteBuf.writeItem(recipe.result);
        }
    }

}
