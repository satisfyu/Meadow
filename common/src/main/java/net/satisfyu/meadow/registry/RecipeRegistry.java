package net.satisfyu.meadow.registry;

import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.recipes.cheese.CheeseFormRecipe;
import net.satisfyu.meadow.recipes.cooking.CookingCauldronRecipe;
import net.satisfyu.meadow.recipes.fondue.FondueRecipe;
import net.satisfyu.meadow.recipes.woodcutting.WoodcuttingRecipe;

import java.util.function.Supplier;

public class RecipeRegistry {
    public static final MapCodec<ItemStack> RESULT_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            (BuiltInRegistries.ITEM.byNameCodec().fieldOf("result")).forGetter(ItemStack::getItem))
            .apply(instance, ItemStack::new));

    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Meadow.MOD_ID, Registries.RECIPE_SERIALIZER);
    private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Meadow.MOD_ID, Registries.RECIPE_TYPE);

    public static final RegistrySupplier<RecipeType<WoodcuttingRecipe>> WOODCUTTING = create("woodcutting");
    public static final RegistrySupplier<RecipeSerializer<WoodcuttingRecipe>> WOODCUTTING_SERIALIZER = create("woodcutting", () -> new SingleItemRecipe.Serializer<>(WoodcuttingRecipe::new));

    public static final RegistrySupplier<RecipeType<CookingCauldronRecipe>> COOKING = create("cooking");
    public static final RegistrySupplier<RecipeSerializer<CookingCauldronRecipe>> COOKING_SERIALIZER = create("cooking", CookingCauldronRecipe.Serializer::new);

    public static final RegistrySupplier<RecipeType<CheeseFormRecipe>> CHEESE = create("cheese");
    public static final RegistrySupplier<RecipeSerializer<CheeseFormRecipe>> CHEESE_SERIALIZER = create("cheese", CheeseFormRecipe.Serializer::new);

    public static final RegistrySupplier<RecipeType<FondueRecipe>> FONDUE = create("fondue");
    public static final RegistrySupplier<RecipeSerializer<FondueRecipe>> FONDUE_SERIALIZER = create("fondue", FondueRecipe.Serializer::new);


    public static void init() {
        Meadow.LOGGER.debug("Registering Recipies for " + Meadow.MOD_ID);
        RECIPE_SERIALIZERS.register();
        RECIPE_TYPES.register();
    }

    private static <T extends Recipe<?>> RegistrySupplier<RecipeType<T>> create(String name) {
        Supplier<RecipeType<T>> type = () -> new RecipeType<>() {
            @Override
            public String toString() {
                return name;
            }
        };
        return RECIPE_TYPES.register(name, type);
    }

    private static <T extends Recipe<?>> RegistrySupplier<RecipeSerializer<T>> create(String name, Supplier<RecipeSerializer<T>> serializer) {
        return RECIPE_SERIALIZERS.register(name, serializer);
    }
}
