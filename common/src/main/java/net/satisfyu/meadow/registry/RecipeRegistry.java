package net.satisfyu.meadow.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.recipes.CheeseFormRecipe;
import net.satisfyu.meadow.recipes.CookingCauldronRecipe;
import net.satisfyu.meadow.recipes.FondueRecipe;
import net.satisfyu.meadow.recipes.WoodcuttingRecipe;

import java.util.function.Supplier;

public class RecipeRegistry {
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Meadow.MOD_ID, Registries.RECIPE_SERIALIZER);
    private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Meadow.MOD_ID, Registries.RECIPE_TYPE);

    public static final RegistrySupplier<RecipeType<WoodcuttingRecipe>> WOODCUTTING = create("woodcutting");
    public static final RegistrySupplier<RecipeSerializer<WoodcuttingRecipe>> WOODCUTTING_SERIALIZER = create("woodcutting", WoodcuttingRecipe.Serializer::new);

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
