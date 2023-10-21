package net.satisfyu.meadow.recipes.woodcutting;

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

public class WoodcuttingRecipe implements Recipe<Container> {
    private final Ingredient ingredient;
    private final ItemStack outputStack;

    public WoodcuttingRecipe(Ingredient input, ItemStack outputStack) {
        this.ingredient = input;
        this.outputStack = outputStack;
    }

    public Ingredient getInput() {
        return ingredient;
    }


    @Override
    public boolean matches(Container inventory, Level world) {
        return this.ingredient.test(inventory.getItem(0));
    }

    @Override
    public ItemStack assemble(Container inventory, RegistryAccess registryManager) {
        return this.outputStack.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> defaultedList = NonNullList.create();
        defaultedList.add(this.ingredient);
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
        return outputStack;
    }

    @Override
    public boolean isSpecial() {
        return true;
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

        private static final Codec<WoodcuttingRecipe> CODEC = RecordCodecBuilder.create((instance) ->
                instance.group(
                                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter((recipe) -> recipe.ingredient),
                                RecipeRegistry.RESULT_CODEC.forGetter((recipe) -> recipe.outputStack))
                        .apply(instance, WoodcuttingRecipe::new));

        @Override
        public Codec<WoodcuttingRecipe> codec() {
            return CODEC;
        }

        @Override
        public WoodcuttingRecipe fromNetwork(FriendlyByteBuf buf) {
            Ingredient input = Ingredient.fromNetwork(buf);
            ItemStack output = buf.readItem();
            return new WoodcuttingRecipe(input, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, WoodcuttingRecipe recipe) {
            recipe.getInput().toNetwork(friendlyByteBuf);
            friendlyByteBuf.writeItem(recipe.outputStack);
        }
    }
}