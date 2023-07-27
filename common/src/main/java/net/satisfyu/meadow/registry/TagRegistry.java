package net.satisfyu.meadow.registry;

import static net.satisfyu.meadow.Meadow.MOD_ID;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class TagRegistry {
    public static final TagKey<Biome> IS_MEADOW = TagKey.create(Registries.BIOME, new ResourceLocation(MOD_ID, "is_meadow"));

    public static final TagKey<Item> MILK = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "milk"));

    public static final TagKey<Item> CHEESE_BLOCKS = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "cheese_blocks"));

    public static final TagKey<Block> ALLOWS_COOKING = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "allows_cooking"));

    public static final TagKey<Item> WOODEN_BUCKETS = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "wooden_buckets"));

    public static final TagKey<Item> BUCKETS = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "buckets"));

    public static final TagKey<Item> SMALL_FLOWER = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "small_flower"));

    public static final TagKey<Item> BIG_FLOWER = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "big_flower"));

    public static final TagKey<Item> CHEESE = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "cheese"));

    public static final TagKey<Block> CAN_NOT_CONNECT = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "can_not_connect"));

    public static final TagKey<Item> SHOVEL = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "shovel"));
}
