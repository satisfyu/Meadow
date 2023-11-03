package net.satisfyu.meadow.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.entity.blockentities.*;

import java.util.function.Supplier;

public class BlockEntityRegistry {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Meadow.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<CookingCauldronBlockEntity>> COOKING_CAULDRON = createBlockEntity("cooking_pot", () -> BlockEntityType.Builder.of(CookingCauldronBlockEntity::new, ObjectRegistry.COOKING_CAULDRON.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<CheeseFormBlockEntity>> CHEESE_FORM_BLOCK_ENTITY = createBlockEntity("cheese_form", () -> BlockEntityType.Builder.of(CheeseFormBlockEntity::new, ObjectRegistry.CHEESE_FORM.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<CheeseRackBlockEntity>> CHEESE_RACK_BLOCK_ENTITY = createBlockEntity("cheese_rack", () -> BlockEntityType.Builder.of(CheeseRackBlockEntity::new, ObjectRegistry.CHEESE_RACK.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<WheelBarrowBlockEntity>> WHEEL_BARROW_BLOCK_ENTITY = createBlockEntity("wheel_barrow", () -> BlockEntityType.Builder.of(WheelBarrowBlockEntity::new, ObjectRegistry.WHEELBARROW.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<FlowerBoxBlockEntity>> FLOWER_BOX_BLOCK_ENTITY = createBlockEntity("flower_box", () -> BlockEntityType.Builder.of(FlowerBoxBlockEntity::new, ObjectRegistry.WOODEN_FLOWER_BOX.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<BigFlowerPotBlockEntity>> BIG_FLOWER_POT_BLOCK_ENTITY = createBlockEntity("big_flower_pot", () -> BlockEntityType.Builder.of(BigFlowerPotBlockEntity::new, ObjectRegistry.FLOWER_POT_BIG.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<FondueBlockEntity>> FONDUE = createBlockEntity("fondue", () -> BlockEntityType.Builder.of(FondueBlockEntity::new, ObjectRegistry.FONDUE.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<ShelfBlockEntity>> SHELF_BLOCK_ENTITY = createBlockEntity("shelf", () -> BlockEntityType.Builder.of(ShelfBlockEntity::new, ObjectRegistry.SHELF.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<StoveBlockWoodBlockEntity>> STOVE_BLOCK_WOOD_BLOCK_ENTITY = createBlockEntity("stove_block_wood", () -> BlockEntityType.Builder.of(StoveBlockWoodBlockEntity::new, ObjectRegistry.STOVE_WOOD.get()).build(null));


    private static <T extends BlockEntityType<?>> RegistrySupplier<T> createBlockEntity(final String path, final Supplier<T> type) {
        return BLOCK_ENTITY_TYPES.register(path, type);
    }

    public static void init() {
        Meadow.LOGGER.debug("Registering Mod BlockEntities for " + Meadow.MOD_ID);
        BLOCK_ENTITY_TYPES.register();
    }
}
