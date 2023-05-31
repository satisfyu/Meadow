package net.satisfyu.meadow.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class MeadowTags {
    public static final TagKey<Biome> MEADOW_RABBIT_BIOMES = TagKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "meadow_rabbit_biomes"));

    public static final TagKey<Item> CHEESE_MASS = TagKey.of(Registry.ITEM_KEY, new Identifier(MOD_ID, "cheese_mass"));

    public static final TagKey<Item> CHEESE_BLOCKS = TagKey.of(Registry.ITEM_KEY, new Identifier(MOD_ID, "cheese_blocks"));

    public static final TagKey<Block> ALLOWS_COOKING_ON_POT = TagKey.of(Registry.BLOCK_KEY, new Identifier(MOD_ID, "allows_cooking_on_pot"));

    public static final TagKey<Item> WOODEN_BUCKETS = TagKey.of(Registry.ITEM_KEY, new Identifier(MOD_ID, "wooden_buckets"));

    public static final TagKey<Item> BUCKETS = TagKey.of(Registry.ITEM_KEY, new Identifier(MOD_ID, "buckets"));

    public static final TagKey<Item> SMALL_FLOWER = TagKey.of(Registry.ITEM_KEY, new Identifier(MOD_ID, "small_flower"));

    public static final TagKey<Item> BIG_FLOWER = TagKey.of(Registry.ITEM_KEY, new Identifier(MOD_ID, "big_flower"));

    public static final TagKey<Item> CHEESE = TagKey.of(Registry.ITEM_KEY, new Identifier(MOD_ID, "cheese"));

    public static final TagKey<Block> CAN_NOT_CONNECT = TagKey.of(Registry.BLOCK_KEY, new Identifier(MOD_ID, "can_not_connect"));
    public static final TagKey<Item> SHOVEL = TagKey.of(Registry.ITEM_KEY, new Identifier(MOD_ID, "shovel"));

}
