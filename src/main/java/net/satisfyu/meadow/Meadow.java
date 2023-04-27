package net.satisfyu.meadow;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.block.woodenCauldren.WoodenCauldronBehavior;
import net.satisfyu.meadow.effects.MeadowEffects;
import net.satisfyu.meadow.entity.ModEntities;
import net.satisfyu.meadow.item.ModItemGroup;
import net.satisfyu.meadow.item.ModItems;
import net.satisfyu.meadow.painting.ModPaintings;
import net.satisfyu.meadow.particle.ModParticles;
import net.satisfyu.meadow.recipes.ModRecipes;
import net.satisfyu.meadow.screenHandler.ModScreenHandlers;
import net.satisfyu.meadow.sound.ModSounds;
import net.satisfyu.meadow.util.MeadowIdentifier;
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


	public static final Identifier PINE_ID = new Identifier(MOD_ID, "pine");
	public static final Identifier PINE_BOAT_ID = new Identifier(MOD_ID, "pine_boat");
	public static final Identifier PINE_CHEST_BOAT_ID = new Identifier(MOD_ID, "pine_chest_boat");
	public static TerraformBoatType PINE_BOAT;
	public static final Item PINE_BOAT_ITEM = TerraformBoatItemHelper.registerBoatItem(PINE_BOAT_ID, () -> PINE_BOAT, false, ModItemGroup.ALPINE_SALT);
	public static final Item PINE_BOAT_CHEST_ITEM = TerraformBoatItemHelper.registerBoatItem(PINE_CHEST_BOAT_ID, () -> PINE_BOAT, true, ModItemGroup.ALPINE_SALT);

	@Override
	public void onInitialize() {

		ModBlocks.registerFuel();
		ModBlocks.registerModBlocks();
		ModEntities.registerEntities();
		ModItems.registerModItems();
		ModStrippableBlocks.registerStrippables();
		WoodenCauldronBehavior.registerBehavior();
		MeadowEffects.init();
		ModPaintings.registerPaintings();
		ModVillagers.init();
		ModRecipes.registerRecipes();
		ModScreenHandlers.registerScreenHandler();

		ModSounds.registerSounds();
		ModParticles.registerParticles();
		ModFeatures.registerFeatures();
		registerCompostables();

		FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(container -> {
			ResourceManagerHelper.registerBuiltinResourcePack(new MeadowIdentifier("bushy_leaves"), container, ResourcePackActivationType.NORMAL);
		});

		FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(container -> {
			ResourceManagerHelper.registerBuiltinResourcePack(new MeadowIdentifier("optifine_support"), container, ResourcePackActivationType.NORMAL);
		});

		PINE_BOAT = new TerraformBoatType.Builder()
				.item(PINE_BOAT_ITEM)
				.chestItem(PINE_BOAT_CHEST_ITEM)
				.planks(ModBlocks.PINE_PLANKS.asItem())
				.build();
		Registry.register(TerraformBoatTypeRegistry.INSTANCE, PINE_ID, PINE_BOAT);
	}

	protected void registerCompostables(){
		//Seeds
		CompostingChanceRegistry.INSTANCE.add(ModItems.YARROW_SEEDS,.1f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.JUNIPER_SEEDS,.1f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.MOUNTAIN_LAVENDER_SEEDS,.1f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.OAT_SEEDS,.1f);

		//Foods
		CompostingChanceRegistry.INSTANCE.add(ModItems.PIECE_OF_CHEESE,.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.PIECE_OF_GOAT_CHEESE,.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.PIECE_OF_OAT_CHEESE,.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.PIECE_OF_HERB_CHEESE,.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.PIECE_OF_LAVENDER_CHEESE,.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.PIECE_OF_SHEEP_CHEESE,.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.PIECE_OF_WARPED_CHEESE,.5f);

		CompostingChanceRegistry.INSTANCE.add(ModItems.CHEESECAKE_SLICE,.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.CHEESE_TART_SLICE,.5f);

		CompostingChanceRegistry.INSTANCE.add(ModItems.CREAM_CHEESE,.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.BOWL_MOZRELLA,.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.CHEESE_SANDWICH,.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.CHEESE_ROLL,.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.CHEESE_STICK,.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.OAT_BREAD,.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.RICOLA,.5f);

		//Foods
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.CHEESE_BLOCK,1f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.WARPED_CHEESE_BLOCK,1f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.GOAT_CHEESE_BLOCK,1f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.SHEEP_CHEESE_BLOCK,1f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.HERB_CHEESE_BLOCK,1f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.LAVENDER_CHEESE_BLOCK,1f);


		//Plants
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.FIR_CONE,.3f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.ENZIAN,.3f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.DELPHINIUM,.3f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.ALPINE_POPPY,.3f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.SAXIFRAGE,.3f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.ERIOPHORUM,.3f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.FIRE_LILY,.3f);

		CompostingChanceRegistry.INSTANCE.add(ModBlocks.SMALL_FIR,.3f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.PINE_SAPLING,.3f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.PINE_LEAVES,.3f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.PINE_LEAVES_2,.3f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.ALPINE_BIRCH_LEAVES_HANGING,.3f);

		CompostingChanceRegistry.INSTANCE.add(ModBlocks.WILD_JUNIPER,.3f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.WILD_MOUNTAIN_LAVENDER,.3f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.WILD_YARROW,.3f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.ERIOPHORUM_TALL,.3f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.ALPINE_GRASS_TALL,.3f);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.ALPINE_GRASS,.3f);
	}



	@Override
	public void onTerraBlenderInitialized() {
		ModRegion.loadTerrablender();
	}
}
