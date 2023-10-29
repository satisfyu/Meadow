package net.satisfyu.meadow.registry;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class CompostableRegistry {

    public static void init() {
        registerCompostableItem(ObjectRegistry.PIECE_OF_CHEESE.get(), .5f);
        registerCompostableItem(ObjectRegistry.PIECE_OF_GOAT_CHEESE.get(), .5f);
        registerCompostableItem(ObjectRegistry.PIECE_OF_GRAIN_CHEESE.get(), .5f);
        registerCompostableItem(ObjectRegistry.PIECE_OF_AMETHYST_CHEESE.get(), .5f);
        registerCompostableItem(ObjectRegistry.PIECE_OF_SHEEP_CHEESE.get(), .5f);
        registerCompostableItem(ObjectRegistry.PIECE_OF_WARPED_CHEESE.get(), .5f);
        registerCompostableItem(ObjectRegistry.CHEESECAKE_SLICE.get(), .5f);
        registerCompostableItem(ObjectRegistry.CHEESE_TART_SLICE.get(), .5f);
        registerCompostableItem(ObjectRegistry.CHEESE_SANDWICH.get(), .5f);
        registerCompostableItem(ObjectRegistry.CHEESE_ROLL.get(), .5f);
        registerCompostableItem(ObjectRegistry.CHEESE_STICK.get(), .5f);
        registerCompostableItem(ObjectRegistry.CHEESE_BLOCK.get(), 1f);
        registerCompostableItem(ObjectRegistry.WARPED_CHEESE_BLOCK.get(), 1f);
        registerCompostableItem(ObjectRegistry.AMETHYST_CHEESE_BLOCK.get(), 1f);
        registerCompostableItem(ObjectRegistry.GOAT_CHEESE_BLOCK.get(), 1f);
        registerCompostableItem(ObjectRegistry.SHEEP_CHEESE_BLOCK.get(), 1f);
        registerCompostableItem(ObjectRegistry.GRAIN_CHEESE_BLOCK.get(), 1f);
        registerCompostableItem(ObjectRegistry.ENZIAN.get(), .3f);
        registerCompostableItem(ObjectRegistry.DELPHINIUM.get(), .3f);
        registerCompostableItem(ObjectRegistry.ALPINE_POPPY.get(), .3f);
        registerCompostableItem(ObjectRegistry.SAXIFRAGE.get(), .3f);
        registerCompostableItem(ObjectRegistry.ERIOPHORUM.get(), .3f);
        registerCompostableItem(ObjectRegistry.SMALL_FIR.get(), .3f);
        registerCompostableItem(ObjectRegistry.PINE_SAPLING.get(), .3f);
        registerCompostableItem(ObjectRegistry.ERIOPHORUM_TALL.get(), .3f);
        registerCompostableItem(ObjectRegistry.FIRE_LILY.get(), .3f);
    }

    public static void registerCompostableItem(ItemLike item, float chance) {
        if (item.asItem() != Items.AIR) {
            ComposterBlock.COMPOSTABLES.put(item.asItem(), chance);
        }
    }
}
