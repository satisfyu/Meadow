package net.satisfyu.meadow.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.recipes.cheese.CheeseFormRecipe;
import net.satisfyu.meadow.recipes.cooking.CookingCauldronRecipe;
import net.satisfyu.meadow.recipes.woodcutting.WoodcuttingRecipe;
import net.satisfyu.meadow.util.MeadowIdentifier;

import java.util.function.Supplier;

public class RecipeRegistry {
    private static final Registrar<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Meadow.MOD_ID, Registry.RECIPE_TYPE_KEY).getRegistrar();
    private static final Registrar<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Meadow.MOD_ID, Registry.RECIPE_SERIALIZER_KEY).getRegistrar();

    public static final RegistrySupplier<RecipeType<WoodcuttingRecipe>> WOODCUTTING = create("woodcutting");
    public static final RegistrySupplier<RecipeSerializer<WoodcuttingRecipe>> WOODCUTTING_SERIALIZER = create("woodcutting", WoodcuttingRecipe.Serializer::new);

    public static final RegistrySupplier<RecipeType<CookingCauldronRecipe>> COOKING = create("cooking");
    public static final RegistrySupplier<RecipeSerializer<CookingCauldronRecipe>> COOKING_SERIALIZER = create("cooking", CookingCauldronRecipe.Serializer::new);

    public static final RegistrySupplier<RecipeType<CheeseFormRecipe>> CHEESE = create("cheese");
    public static final RegistrySupplier<RecipeSerializer<CheeseFormRecipe>> CHEESE_SERIALIZER = create("cheese", CheeseFormRecipe.Serializer::new);


    public static void init() {
        Meadow.LOGGER.debug("Registering Recipies for " + Meadow.MOD_ID);
    }

    private static <T extends Recipe<?>> RegistrySupplier<RecipeSerializer<T>> create(String name, Supplier<RecipeSerializer<T>> serializer) {
        return RECIPE_SERIALIZERS.register(new MeadowIdentifier(name), serializer);
    }

    private static <T extends Recipe<?>> RegistrySupplier<RecipeType<T>> create(String name) {
        Supplier<RecipeType<T>> type = () -> new RecipeType<>() {
            @Override
            public String toString() {
                return name;
            }
        };
        return RECIPE_TYPES.register(new MeadowIdentifier(name), type);
    }
}
