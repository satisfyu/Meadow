package net.satisfyu.meadow.util;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.StringIdentifiable;

public enum EnumFlower implements StringIdentifiable {
	NONE("none", Blocks.AIR),
	DANDELION("dandelion", Blocks.DANDELION),
	POPPY("poppy", Blocks.POPPY),
	BLUE_ORCHID("blue_orchid", Blocks.BLUE_ORCHID),
	ALLIUM("allium", Blocks.ALLIUM),
	AZURE_BLUET("azure_bluet", Blocks.AZURE_BLUET),
	RED_TULIP("red_tulip", Blocks.RED_TULIP),
	WHITE_TULIP("white_tulip", Blocks.WHITE_TULIP),
	ORANGE_TULIP("orange_tulip", Blocks.ORANGE_TULIP),
	PINK_TULIP("pink_tulip", Blocks.PINK_TULIP),
	OXEYE_DAISY("oxeye_daisy", Blocks.OXEYE_DAISY),
	CORNFLOWER("cornflower", Blocks.CORNFLOWER),
	LILY_OF_THE_VALLEY("lily_of_the_valley", Blocks.LILY_OF_THE_VALLEY),
	WITHER_ROSE("wither_rose", Blocks.WITHER_ROSE);


	private final String name;
	private final Block flower;

	private EnumFlower(String name, Block flower) {
		this.name = name;
		this.flower = flower;
	}
	
	@Override
	public String asString() {
		return this.name;
	}
	
	public Block getFlower() {
		return this.flower;
	}
}
