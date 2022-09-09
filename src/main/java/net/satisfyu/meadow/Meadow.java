package net.satisfyu.meadow;

import net.fabricmc.api.ModInitializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.block.woodCutter.WoodcuttingRecipe;
import net.satisfyu.meadow.block.woodCutter.WoodcuttingRecipeSerializer;
import net.satisfyu.meadow.block.woodCutter.WoodcuttorScreenHandler;
import net.satisfyu.meadow.entity.ModEntities;
import net.satisfyu.meadow.item.ModItems;
import net.satisfyu.meadow.sounds.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class  Meadow implements ModInitializer {
	public static final String MOD_ID = "meadow";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	//public static final Identifier FILL_CAN = ItemAccessor.callRegister("fill_can", StatFormatter.DEFAULT);

	public static ScreenHandlerType<WoodcuttorScreenHandler> WOODCUTTOR_SCREEN_HANDLER;


	public static RecipeType<WoodcuttingRecipe> WOODCUTTING;

	@Override
	public void onInitialize() {
		WOODCUTTING = Registry.register(Registry.RECIPE_TYPE, new Identifier(MOD_ID, WoodcuttingRecipe.Type.ID), WoodcuttingRecipe.Type.INSTANCE);
		WOODCUTTOR_SCREEN_HANDLER = Registry.register(Registry.SCREEN_HANDLER, new Identifier(MOD_ID, "woodcutter"), new ScreenHandlerType<>(WoodcuttorScreenHandler::new));
		Registry.register(Registry.RECIPE_SERIALIZER, WoodcuttingRecipeSerializer.ID, WoodcuttingRecipeSerializer.INSTANCE);

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModSounds.registerSounds();
		ModEntities.register();
	}
}
