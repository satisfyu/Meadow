package net.satisfy.meadow.recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.satisfy.meadow.registry.RecipeRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FondueRecipe implements Recipe<RecipeInput> {
    private final ResourceLocation id;
    private final Ingredient fuel;
    private final Ingredient bread;
    private final ItemStack result;

    public FondueRecipe(ResourceLocation id, Ingredient fuel, Ingredient bread, ItemStack result) {
        this.id = id;
        this.fuel = fuel;
        this.bread = bread;
        this.result = result;
    }

    public FondueRecipe(Optional<ResourceLocation> id, Ingredient fuel, Ingredient bread, ItemStack result) {
        this.id = id.orElseGet(() -> ResourceLocation.fromNamespaceAndPath("meadow", "fondue"));
        this.fuel = fuel;
        this.bread = bread;
        this.result = result;
    }

    public Ingredient getFuel() {
        return fuel;
    }
    public Ingredient getBread() {
        return bread;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> defaultedList = NonNullList.create();
        defaultedList.add(this.bread);
        defaultedList.add(this.fuel);
        return defaultedList;
    }

    @Override
    public boolean matches(RecipeInput recipeInput, Level level) {
        NonNullList<Ingredient> ingredients = getIngredients();
        List<ItemStack> items = new ArrayList<>(List.of(recipeInput.getItem(1), recipeInput.getItem(0)));
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

    @Override
    public @NotNull ItemStack assemble(RecipeInput recipeInput, HolderLookup.Provider provider) {
        return this.result.copy();
    }

    public ItemStack assemble() {
        return assemble(null, null);
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.Provider provider) {
        return result;
    }

    public ItemStack getResultItem() {
        return getResultItem(null);
    }

    public ResourceLocation getId() {
        return id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return RecipeRegistry.FONDUE_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return RecipeRegistry.FONDUE.get();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public static class Serializer implements RecipeSerializer<FondueRecipe> {
        public static final MapCodec<FondueRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                        ResourceLocation.CODEC.optionalFieldOf("id").forGetter(recipe -> Optional.of(recipe.getId())),
                        Ingredient.CODEC.fieldOf("fuel").forGetter(FondueRecipe::getFuel),
                        Ingredient.CODEC.fieldOf("bread").forGetter(FondueRecipe::getBread),
                        ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
                ).apply(instance, FondueRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, FondueRecipe> STREAM_CODEC = StreamCodec.of(FondueRecipe.Serializer::toNetwork, FondueRecipe.Serializer::fromNetwork);

        public static FondueRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
            ResourceLocation id = buf.readResourceLocation();
            Ingredient fuel = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            Ingredient bread = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            ItemStack result = ItemStack.OPTIONAL_STREAM_CODEC.decode(buf);
            return new FondueRecipe(id, fuel, bread, result);
        }

        public static void toNetwork(RegistryFriendlyByteBuf buf, FondueRecipe recipe) {
            buf.writeResourceLocation(recipe.getId());
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.fuel);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.bread);
            ItemStack.OPTIONAL_STREAM_CODEC.encode(buf, recipe.result);
        }

        @Override
        public @NotNull MapCodec<FondueRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, FondueRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }

}