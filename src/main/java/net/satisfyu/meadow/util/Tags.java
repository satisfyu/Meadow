package net.satisfyu.meadow.util;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class Tags {
    public static final TagKey<Biome> MEADOW_RABBIT_BIOMES = TagKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "meadow_rabbit_biomes"));

    public static final TagKey<Item> CHEESE_MASS = TagKey.of(Registry.ITEM_KEY, new Identifier(MOD_ID, "cheese_mass"));

    public static final TagKey<Item> WOODEN_BUCKETS = TagKey.of(Registry.ITEM_KEY, new Identifier(MOD_ID, "wooden_buckets"));

    public static final TagKey<Item> BUCKETS = TagKey.of(Registry.ITEM_KEY, new Identifier(MOD_ID, "buckets"));
}
