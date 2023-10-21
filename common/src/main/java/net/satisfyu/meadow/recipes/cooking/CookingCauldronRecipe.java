package net.satisfyu.meadow.recipes.cooking;

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
import net.satisfyu.meadow.util.GeneralUtil;

import java.util.List;

public class CookingCauldronRecipe implements Recipe<Container> {
    private final List<Ingredient> inputs;
    private final ItemStack output;

    public CookingCauldronRecipe(List<Ingredient> inputs, ItemStack output) {
        this.inputs = inputs;
        this.output = output;
    }

    @Override
    public boolean matches(Container inventory, Level world) {
        return GeneralUtil.matchesRecipe(inventory, getIngredients(), 0, 6);
    }

    public ItemStack assemble() {
        return assemble(null, null);
    }

    @Override
    public ItemStack assemble(Container inventory, RegistryAccess registryManager) {
        return this.output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    public ItemStack getResultItem() {
        return getResultItem(null);
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryManager) {
        return this.output;
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
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        for(Ingredient ingredient : inputs){
            if(ingredient == null) continue;
            list.add(ingredient);
        }
        return list;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public static class Serializer implements RecipeSerializer<CookingCauldronRecipe> {

        private static final Codec<CookingCauldronRecipe> CODEC = RecordCodecBuilder.create((instance) ->
                instance.group(
                        Codec.list(Ingredient.CODEC_NONEMPTY).fieldOf("ingredients").forGetter((recipe) -> recipe.inputs),
                        CraftingRecipeCodecs.ITEMSTACK_OBJECT_CODEC.fieldOf("result").forGetter((recipe) -> recipe.output))
                        .apply(instance, CookingCauldronRecipe::new));
        @Override
        public Codec<CookingCauldronRecipe> codec() {
            return CODEC;
        }

        @Override
        public CookingCauldronRecipe fromNetwork(FriendlyByteBuf buf) {
            final var ingredients = NonNullList.withSize(buf.readVarInt(), Ingredient.EMPTY);
            ingredients.replaceAll(ignored -> Ingredient.fromNetwork(buf));
            return new CookingCauldronRecipe(ingredients, buf.readItem());
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, CookingCauldronRecipe recipe) {
            buf.writeVarInt(recipe.inputs.size());
            recipe.inputs.forEach(entry -> entry.toNetwork(buf));
            buf.writeItem(recipe.output);
        }
    }

    public static class Type implements RecipeType<CookingCauldronRecipe> {
        private Type() {
        }

        public static final Type INSTANCE = new Type();

        public static final String ID = "cooking";
    }
}