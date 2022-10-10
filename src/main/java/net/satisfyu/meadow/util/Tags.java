package net.satisfyu.meadow.util;

import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class Tags {

    public static final TagKey<Block> LIMESTONE_ORE_REPLACEABLES = TagKey.of(Registry.BLOCK_KEY, new Identifier(MOD_ID, "limestone_ore_replaceables"));
}
