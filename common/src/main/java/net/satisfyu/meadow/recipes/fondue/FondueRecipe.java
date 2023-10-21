package net.satisfyu.meadow.recipes.fondue;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.satisfyu.meadow.registry.RecipeRegistry;

import java.util.ArrayList;
import java.util.List;

public class FondueRecipe implements Recipe<Container> {
    private final Ingredient fuel;
    private final Ingredient bread;
    private final ItemStack result;

    public FondueRecipe(Ingredient fuel, Ingredient bread, ItemStack result) {
        this.fuel = fuel;
        this.bread = bread;
        this.result = result;
    }

    @Override
    public boolean matches(Container inventory, Level world) {
        NonNullList<Ingredient> ingredients = getIngredients();
        List<ItemStack> items = new ArrayList<>(List.of(inventory.getItem(1), inventory.getItem(0)));
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

    public Ingredient getFuel() {
        return fuel;
    }
    public Ingredient getBread() {
        return bread;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> defaultedList = NonNullList.create();
        defaultedList.add(this.bread);
        defaultedList.add(this.fuel);
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
        return RecipeRegistry.FONDUE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeRegistry.FONDUE.get();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public static class Serializer implements RecipeSerializer<FondueRecipe> {

        private static final Codec<FondueRecipe> CODEC = RecordCodecBuilder.create((instance) ->
                instance.group(
                                Ingredient.CODEC_NONEMPTY.fieldOf("fuel").forGetter((recipe) -> recipe.fuel),
                                Ingredient.CODEC_NONEMPTY.fieldOf("bread").forGetter((recipe) -> recipe.bread),
                                RecipeRegistry.RESULT_CODEC.forGetter((recipe) -> recipe.result))
                        .apply(instance, FondueRecipe::new));

        @Override
        public Codec<FondueRecipe> codec() {
            return CODEC;
        }

        @Override
        public FondueRecipe fromNetwork(FriendlyByteBuf buf) {
            Ingredient bucket = Ingredient.fromNetwork(buf);
            Ingredient input = Ingredient.fromNetwork(buf);
            ItemStack output = buf.readItem();
            return new FondueRecipe(bucket, input, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, FondueRecipe recipe) {
            recipe.getIngredients().forEach(ingredient -> ingredient.toNetwork(friendlyByteBuf));
            friendlyByteBuf.writeItem(recipe.result);
        }
    }

}