package net.satisfyu.meadow;

import net.fabricmc.api.ModInitializer;
import net.satisfyu.meadow.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class  Meadow implements ModInitializer {
	public static final String MOD_ID = "meadow";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
	}
}
