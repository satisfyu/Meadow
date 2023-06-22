package net.satisfyu.meadow.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.StoveBlockWoodBlockEntity;
import net.satisfyu.meadow.entity.blockentities.*;

import java.util.function.Supplier;

public class BlockEntityRegistry {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Meadow.MOD_ID, Registry.BLOCK_ENTITY_TYPE_KEY);

    public static final RegistrySupplier<BlockEntityType<CookingCauldronBlockEntity>> COOKING_POT = createBlockEntity("cooking_pot", () -> BlockEntityType.Builder.create(CookingCauldronBlockEntity::new, ObjectRegistry.COOKING_POT.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<CheeseFormBlockEntity>> CHEESE_FORM_BLOCK_ENTITY = createBlockEntity("cheese_form", () -> BlockEntityType.Builder.create(CheeseFormBlockEntity::new, ObjectRegistry.CHEESE_FORM.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<CheeseRackBlockEntity>> CHEESE_RACK_BLOCK_ENTITY = createBlockEntity("cheese_rack", () -> BlockEntityType.Builder.create(CheeseRackBlockEntity::new, ObjectRegistry.CHEESE_RACK.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<WheelBarrowBlockEntity>> WHEEL_BARROW_BLOCK_ENTITY = createBlockEntity("wheel_barrow", () -> BlockEntityType.Builder.create(WheelBarrowBlockEntity::new, ObjectRegistry.WHEELBARROW.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<FlowerBoxBlockEntity>> FLOWER_BOX_BLOCK_ENTITY = createBlockEntity("flower_box", () -> BlockEntityType.Builder.create(FlowerBoxBlockEntity::new, ObjectRegistry.WOODEN_FLOWER_BOX.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<BigFlowerPotBlockEntity>> BIG_FLOWER_POT_BLOCK_ENTITY = createBlockEntity("big_flower_pot", () -> BlockEntityType.Builder.create(BigFlowerPotBlockEntity::new, ObjectRegistry.FLOWER_POT_BIG.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<FondueBlockEntity>> FONDUE = createBlockEntity("fondue", () -> BlockEntityType.Builder.create(FondueBlockEntity::new, ObjectRegistry.FONDUE.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<ShelfBlockEntity>> SHELF_BLOCK_ENTITY = createBlockEntity("shelf", () -> BlockEntityType.Builder.create(ShelfBlockEntity::new, ObjectRegistry.SHELF.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<StoveBlockWoodBlockEntity>> STOVE_BLOCK_WOOD_BLOCK_ENTITY = createBlockEntity("stove_block_wood", () -> BlockEntityType.Builder.create(StoveBlockWoodBlockEntity::new, ObjectRegistry.STOVE_WOOD.get()).build(null));


    private static <T extends BlockEntityType<?>> RegistrySupplier<T> createBlockEntity(final String path, final Supplier<T> type) {
        return BLOCK_ENTITY_TYPES.register(path, type);
    }

    public static void init() {
        Meadow.LOGGER.debug("Registering Mod BlockEntities for " + Meadow.MOD_ID);
        BLOCK_ENTITY_TYPES.register();
    }
}
