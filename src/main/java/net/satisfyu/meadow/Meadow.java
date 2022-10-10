package net.satisfyu.meadow;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.block.cookingCauldron.CookingCauldronScreenHandler;
import net.satisfyu.meadow.block.woodCutter.WoodcuttingRecipe;
import net.satisfyu.meadow.block.woodCutter.WoodcuttingRecipeSerializer;
import net.satisfyu.meadow.block.woodCutter.WoodcuttorScreenHandler;
import net.satisfyu.meadow.block.woodenCauldren.WoodenCauldronBehavior;
import net.satisfyu.meadow.entity.ModEntities;
import net.satisfyu.meadow.item.ModItems;
import net.satisfyu.meadow.mixin.SignTypeAccessor;
import net.satisfyu.meadow.painting.ModPaintings;
import net.satisfyu.meadow.particle.ModParticles;
import net.satisfyu.meadow.sound.ModSounds;
import net.satisfyu.meadow.util.ModFlammableBlocks;
import net.satisfyu.meadow.util.ModStrippableBlocks;
import net.satisfyu.meadow.world.feature.ModConfiguredFeatures;
import net.satisfyu.meadow.world.gen.ModWorldGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class  Meadow implements ModInitializer {
	public static final String MOD_ID = "meadow";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static ScreenHandlerType<WoodcuttorScreenHandler> WOODCUTTOR_SCREEN_HANDLER;
	public static ScreenHandlerType<CookingCauldronScreenHandler> COOKING_CAULDRON_SCREEN_HANDLER;
	public static RecipeType<WoodcuttingRecipe> WOODCUTTING;

	public static final SignType PINE = SignTypeAccessor.callRegister(SignTypeAccessor.callCreate("pine"));


	@Override
	public void onInitialize() {
		ModConfiguredFeatures.registerConfiguredFeatures();
		WOODCUTTING = Registry.register(Registry.RECIPE_TYPE, new Identifier(MOD_ID, WoodcuttingRecipe.Type.ID), WoodcuttingRecipe.Type.INSTANCE);
		WOODCUTTOR_SCREEN_HANDLER = Registry.register(Registry.SCREEN_HANDLER, new Identifier(MOD_ID, "woodcutter"), new ScreenHandlerType<>(WoodcuttorScreenHandler::new));
		COOKING_CAULDRON_SCREEN_HANDLER = Registry.register(Registry.SCREEN_HANDLER, new Identifier(MOD_ID, "cooking_cauldron"), new ScreenHandlerType<>(CookingCauldronScreenHandler::new));

		Registry.register(Registry.RECIPE_SERIALIZER, WoodcuttingRecipeSerializer.ID, WoodcuttingRecipeSerializer.INSTANCE);

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModPaintings.registerPaintings();
		ModWorldGen.generateWorldGen();

		ModFlammableBlocks.registerFlammableBlocks();
		ModStrippableBlocks.registerStrippables();

		ModSounds.registerSounds();
		ModEntities.register();

		WoodenCauldronBehavior.registerBehavior();
		ModParticles.registerParticles();
	}
}
