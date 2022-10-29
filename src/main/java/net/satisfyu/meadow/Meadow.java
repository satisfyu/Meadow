package net.satisfyu.meadow;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.item.Item;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.block.cookingCauldron.CookingCauldronScreenHandler;
import net.satisfyu.meadow.block.cookingPot.CookingPotRecipe;
import net.satisfyu.meadow.block.cookingPot.CookingPotScreenHandler;
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
import net.satisfyu.meadow.util.ModStrippableBlocks;
import net.satisfyu.meadow.villager.ModVillagers;
import net.satisfyu.meadow.world.ModRegion;
import net.satisfyu.meadow.world.feature.ModFeatures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import terrablender.api.TerraBlenderApi;


public class Meadow implements ModInitializer, TerraBlenderApi {
	public static final String MOD_ID = "meadow";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static RecipeType<CookingPotRecipe> COOKING_POT_RECIPE_TYPE;
	public static RecipeSerializer<CookingPotRecipe> COOKING_POT_RECIPE_SERIALIZER;

	public static ScreenHandlerType<WoodcuttorScreenHandler> WOODCUTTOR_SCREEN_HANDLER;
	public static ScreenHandlerType<CookingCauldronScreenHandler> COOKING_CAULDRON_SCREEN_HANDLER;
	public static ScreenHandlerType<CookingPotScreenHandler> COOKING_POT_SCREEN_HANDLER;
	public static RecipeType<WoodcuttingRecipe> WOODCUTTING;

	public static final Identifier PINE_ID = new Identifier(MOD_ID, "pine");
	public static final Identifier PINE_BOAT_ID = new Identifier(MOD_ID, "pine_boat");
	public static final Identifier PINE_CHEST_BOAT_ID = new Identifier(MOD_ID, "pine_chest_boat");

	public static TerraformBoatType PINE_BOAT;

	public static final Item PINE_BOAT_ITEM = TerraformBoatItemHelper.registerBoatItem(PINE_BOAT_ID, () -> PINE_BOAT, false);

	public static final Item PINE_BOAT_CHEST_ITEM = TerraformBoatItemHelper.registerBoatItem(PINE_CHEST_BOAT_ID, () -> PINE_BOAT, true);
	public static final SignType PINE = SignTypeAccessor.callRegister(SignTypeAccessor.callCreate("pine"));

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModStrippableBlocks.registerStrippables();
		WoodenCauldronBehavior.registerBehavior();

		ModPaintings.registerPaintings();
		ModVillagers.registerVillagers();
		ModVillagers.registerTrades();

		ModSounds.registerSounds();
		ModParticles.registerParticles();

		ModEntities.registerEntities();

		ModFeatures.registerFeatures();


		PINE_BOAT = new TerraformBoatType.Builder()
				.item(PINE_BOAT_ITEM)
				.chestItem(PINE_BOAT_CHEST_ITEM)
				.planks(ModBlocks.PINE_PLANKS.asItem())
				.build();
		Registry.register(TerraformBoatTypeRegistry.INSTANCE, PINE_ID, PINE_BOAT);


		WOODCUTTING = Registry.register(Registry.RECIPE_TYPE, new Identifier(MOD_ID, WoodcuttingRecipe.Type.ID), WoodcuttingRecipe.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, WoodcuttingRecipeSerializer.ID, WoodcuttingRecipeSerializer.INSTANCE);

		COOKING_POT_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(MOD_ID, CookingPotRecipe.Type.ID), CookingPotRecipe.Type.INSTANCE);
		COOKING_POT_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, CookingPotRecipe.Serializer.ID, CookingPotRecipe.Serializer.INSTANCE);


		WOODCUTTOR_SCREEN_HANDLER = Registry.register(Registry.SCREEN_HANDLER, new Identifier(MOD_ID, "woodcutter"), new ScreenHandlerType<>(WoodcuttorScreenHandler::new));
		COOKING_CAULDRON_SCREEN_HANDLER = Registry.register(Registry.SCREEN_HANDLER, new Identifier(MOD_ID, "cooking_cauldron"), new ScreenHandlerType<>(CookingCauldronScreenHandler::new));
		COOKING_POT_SCREEN_HANDLER = Registry.register(Registry.SCREEN_HANDLER, new Identifier(MOD_ID, "cooking_pot"), new ExtendedScreenHandlerType<>(CookingPotScreenHandler::new));


	}

	@Override
	public void onTerraBlenderInitialized() {
		ModRegion.loadTerrablender();
	}
}
