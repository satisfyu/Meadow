package net.satisfyu.meadow.recipes.woodcutting;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.satisfyu.meadow.registry.RecipeRegistry;

public class WoodcuttingRecipe implements Recipe<Container> {
    private final Ingredient input;
    private final ItemStack outputStack;
    private final ResourceLocation id;

    public WoodcuttingRecipe(Ingredient input, ItemStack outputStack, ResourceLocation id) {
        this.id = id;
        this.input = input;
        this.outputStack = outputStack;
    }

    public Ingredient getInput() {
        return input;
    }


    @Override
    public boolean matches(Container inventory, Level world) {
        return this.input.test(inventory.getItem(0));
    }

    @Override
    public ItemStack assemble(Container inventory, RegistryAccess registryManager) {
        return this.outputStack.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> defaultedList = NonNullList.create();
        defaultedList.add(this.input);
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
    public ResourceLocation getId() {
        return id;
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
        @Override
        // Turns json into Recipe
        public WoodcuttingRecipe fromJson(ResourceLocation id, JsonObject json) {
            WoodcuttingRecipeJsonFormat recipeJson = new Gson().fromJson(json, WoodcuttingRecipeJsonFormat.class);

            if (recipeJson.inputItem == null || recipeJson.outputItem == null) {
                throw new JsonSyntaxException("A required attribute is missing!");
            }
            // We'll allow to not specify the output, and default it to 1.
            if (recipeJson.outputAmount == 0) recipeJson.outputAmount = 1;


            Ingredient input = Ingredient.fromJson(recipeJson.inputItem);

            Item outputItem = BuiltInRegistries.ITEM.getOptional(new ResourceLocation(recipeJson.outputItem))
                    // Validate the inputted item actually exists
                    .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.outputItem));
            ItemStack output = new ItemStack(outputItem, recipeJson.outputAmount);

            return new WoodcuttingRecipe(input, output, id);
        }

        @Override
        // Turns PacketByteBuf into Recipe
        public WoodcuttingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf packetData) {
            Ingredient input = Ingredient.fromNetwork(packetData);
            ItemStack output = packetData.readItem();
            return new WoodcuttingRecipe(input, output, id);
        }

        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, WoodcuttingRecipe recipe) {
            recipe.getInput().toNetwork(friendlyByteBuf);
            friendlyByteBuf.writeItem(recipe.outputStack);
        }
    }
}
