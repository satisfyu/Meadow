package net.satisfyu.meadow.util;

import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class Tags {

    public static final TagKey<Biome> MEADOW_RABBIT_BIOMES = TagKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "meadow_rabbit_biomes"));
}
