package net.satisfyu.meadow.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class CompostableRegistry {
    public static void registerCompostable() {
        registerCompostableItem(ObjectRegistry.PIECE_OF_CHEESE, 0.5f);
        registerCompostableItem(ObjectRegistry.PIECE_OF_GOAT_CHEESE, 0.5f);
        registerCompostableItem(ObjectRegistry.PIECE_OF_CHEESE, 0.5f);
        registerCompostableItem(ObjectRegistry.PIECE_OF_GRAIN_CHEESE, 0.5f);
        registerCompostableItem(ObjectRegistry.PIECE_OF_AMETHYST_CHEESE, 0.5f);
        registerCompostableItem(ObjectRegistry.PIECE_OF_SHEEP_CHEESE, 0.5f);
        registerCompostableItem(ObjectRegistry.PIECE_OF_WARPED_CHEESE, 0.5f);
        registerCompostableItem(ObjectRegistry.CHEESECAKE_SLICE, 0.5f);
        registerCompostableItem(ObjectRegistry.CHEESE_TART_SLICE, 0.5f);
        registerCompostableItem(ObjectRegistry.CHEESE_SANDWICH, 0.5f);
        registerCompostableItem(ObjectRegistry.CHEESE_ROLL, 0.5f);
        registerCompostableItem(ObjectRegistry.CHEESE_STICK, 0.5f);
        registerCompostableItem(ObjectRegistry.CHEESE_BLOCK, 1f);
        registerCompostableItem(ObjectRegistry.WARPED_CHEESE_BLOCK, 1f);
        registerCompostableItem(ObjectRegistry.AMETHYST_CHEESE_BLOCK, 1f);
        registerCompostableItem(ObjectRegistry.GOAT_CHEESE_BLOCK, 1f);
        registerCompostableItem(ObjectRegistry.SHEEP_CHEESE_BLOCK, 1f);
        registerCompostableItem(ObjectRegistry.GRAIN_CHEESE_BLOCK, 1f);
        registerCompostableItem(ObjectRegistry.ENZIAN, 0.3f);
        registerCompostableItem(ObjectRegistry.DELPHINIUM, 0.3f);
        registerCompostableItem(ObjectRegistry.ALPINE_POPPY, 0.3f);
        registerCompostableItem(ObjectRegistry.SAXIFRAGE, 0.3f);
        registerCompostableItem(ObjectRegistry.ERIOPHORUM, 0.3f);
        registerCompostableItem(ObjectRegistry.SMALL_FIR, 0.3f);
        registerCompostableItem(ObjectRegistry.PINE_SAPLING, 0.3f);
        registerCompostableItem(ObjectRegistry.ERIOPHORUM_TALL, 0.3f);
        registerCompostableItem(ObjectRegistry.FIRE_LILY, 0.3f);
    }

    public static <T extends ItemLike> void registerCompostableItem(RegistrySupplier<T> item, float chance) {
        if (item.get().asItem() != Items.AIR) {
            ComposterBlock.COMPOSTABLES.put(item.get(), chance);
        }
    }
}
