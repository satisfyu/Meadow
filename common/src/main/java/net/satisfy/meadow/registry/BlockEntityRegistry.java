package net.satisfy.meadow.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.satisfy.meadow.Meadow;
import net.satisfy.meadow.block.entity.*;

import java.util.function.Supplier;

public class BlockEntityRegistry {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Meadow.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<CookingCauldronBlockEntity>> COOKING_CAULDRON = createBlockEntity("cooking_cauldron", () -> BlockEntityType.Builder.of(CookingCauldronBlockEntity::new, ObjectRegistry.COOKING_CAULDRON.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<CheeseFormBlockEntity>> CHEESE_FORM_BLOCK_ENTITY = createBlockEntity("cheese_form", () -> BlockEntityType.Builder.of(CheeseFormBlockEntity::new, ObjectRegistry.CHEESE_FORM.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<CheeseRackBlockEntity>> CHEESE_RACK_BLOCK_ENTITY = createBlockEntity("cheese_rack", () -> BlockEntityType.Builder.of(CheeseRackBlockEntity::new, ObjectRegistry.CHEESE_RACK.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<FondueBlockEntity>> FONDUE = createBlockEntity("fondue", () -> BlockEntityType.Builder.of(FondueBlockEntity::new, ObjectRegistry.FONDUE.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<StoveBlockEntity>> STOVE_BLOCK_ENTITY = createBlockEntity("stove_block_entity", () -> BlockEntityType.Builder.of(StoveBlockEntity::new, ObjectRegistry.STOVE_LID.get()).build(null));

    private static <T extends BlockEntityType<?>> RegistrySupplier<T> createBlockEntity(final String path, final Supplier<T> type) {
        return BLOCK_ENTITY_TYPES.register(path, type);
    }

    public static void init() {
        Meadow.LOGGER.debug("Registering Mod BlockEntities for " + Meadow.MOD_ID);
        BLOCK_ENTITY_TYPES.register();
    }
}
