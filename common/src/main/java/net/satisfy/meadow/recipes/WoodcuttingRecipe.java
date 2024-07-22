package net.satisfy.meadow.recipes;

import com.mojang.serialization.Codec;
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

import java.util.Optional;

public class WoodcuttingRecipe implements Recipe<RecipeInput> {
    private final Ingredient input;
    private final ItemStack outputStack;
    private final ResourceLocation id;

    public WoodcuttingRecipe(Ingredient input, ItemStack outputStack, ResourceLocation id) {
        this.id = id;
        this.input = input;
        this.outputStack = outputStack;
    }

    public WoodcuttingRecipe(Optional<ResourceLocation> resourceLocation, ItemStack itemStack, Integer integer, Ingredient ingredient) {
        this.id = resourceLocation.orElseGet(() -> ResourceLocation.fromNamespaceAndPath("meadow", "woodcutting"));
        this.outputStack = new ItemStack(itemStack.getItem(), integer);
        this.input = ingredient;
    }

    public Ingredient getInput() {
        return input;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> defaultedList = NonNullList.create();
        defaultedList.add(this.input);
        return defaultedList;
    }

    @Override
    public boolean matches(RecipeInput recipeInput, Level level) {
        return this.input.test(recipeInput.getItem(0));
    }

    @Override
    public @NotNull ItemStack assemble(RecipeInput recipeInput, HolderLookup.Provider provider) {
        return this.outputStack.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.outputStack.copy();
    }

    public ItemStack getResultItem() {
        return getResultItem(null);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public ResourceLocation getId() {
        return id;
    }


    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return RecipeRegistry.WOODCUTTING_SERIALIZER.get();
    }


    @Override
    public @NotNull RecipeType<?> getType() {
        return RecipeRegistry.WOODCUTTING.get();
    }

    public static class Serializer implements RecipeSerializer<WoodcuttingRecipe> {
        public static final MapCodec<WoodcuttingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                        ResourceLocation.CODEC.optionalFieldOf("id").forGetter(recipe -> Optional.of(recipe.getId())),
                        ItemStack.CODEC.fieldOf("outputItem").forGetter(recipe -> recipe.outputStack),
                        Codec.INT.fieldOf("outputAmount").forGetter(recipe -> recipe.outputStack.getCount()),
                        Ingredient.CODEC.fieldOf("inputItem").forGetter(WoodcuttingRecipe::getInput)
                ).apply(instance, WoodcuttingRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, WoodcuttingRecipe> STREAM_CODEC = StreamCodec.of(WoodcuttingRecipe.Serializer::toNetwork, WoodcuttingRecipe.Serializer::fromNetwork);

        public static WoodcuttingRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
            ResourceLocation id = buf.readResourceLocation();
            ItemStack output = ItemStack.OPTIONAL_STREAM_CODEC.decode(buf);
            Ingredient input = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            return new WoodcuttingRecipe(input, output, id);
        }

        public static void toNetwork(RegistryFriendlyByteBuf buf, WoodcuttingRecipe recipe) {
            buf.writeResourceLocation(recipe.getId());
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.input);
            ItemStack.OPTIONAL_STREAM_CODEC.encode(buf, recipe.outputStack);
        }

        @Override
        public @NotNull MapCodec<WoodcuttingRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, WoodcuttingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
