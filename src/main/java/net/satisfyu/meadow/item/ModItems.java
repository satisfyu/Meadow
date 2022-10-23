package net.satisfyu.meadow.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.entity.ModEntities;
import net.satisfyu.meadow.item.custom.*;

import java.util.Arrays;


public class ModItems {

    public static final Item ALPINE_SALT = registerItem("alpine_salt",
            new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item ALBINO_COW_SPAWN_EGG_ITEM = registerSpawnEgg(ModEntities.ALBINO_COW, 16777215, 12632256);

    public static final Item ASHEN_COW_SPAWN_EGG_ITEM = registerSpawnEgg(ModEntities.ASHEN_COW, 16777215, 14740948);

    public static final Item COOKIE_COW_SPAWN_EGG_ITEM = registerSpawnEgg(ModEntities.COOKIE_COW, 16777215, 329011);

    public static final Item CREAM_COW_SPAWN_EGG_ITEM = registerSpawnEgg(ModEntities.CREAM_COW, 16777215, 5208616);

    public static final Item DAIRY_COW_SPAWN_EGG_ITEM = registerSpawnEgg(ModEntities.DAIRY_COW, 16777215, 4159204);

    public static final Item DARK_COW_SPAWN_EGG_ITEM = registerSpawnEgg(ModEntities.DARK_COW, 16777215, 6172907);

    public static final Item PINTO_COW_SPAWN_EGG_ITEM = registerSpawnEgg(ModEntities.PINTO_COW, 16777215, 9757694);

    public static final Item SUNSET_COW_SPAWN_EGG_ITEM = registerSpawnEgg(ModEntities.SUNSET_COW, 16777215,8014336);

    public static final Item PINE_SIGN = registerItem("pine_sign",
            new SignItem(new FabricItemSettings().maxCount(16).group(ModItemGroup.ALPINE_SALT), ModBlocks.PINE_SIGN, ModBlocks.PINE_WALL_SIGN));

    public static final Item PIECE_OF_CHEESE = registerItem("piece_of_cheese",
            new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)
                    .food(new FoodComponent.Builder().hunger(2).saturationModifier(0.3f).build())));

    public static final Item PIECE_OF_GOAT_CHEESE = registerItem("piece_of_goat_cheese",
            new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)
                    .food(new FoodComponent.Builder().hunger(2).saturationModifier(0.3f).build())));

    public static final Item PIECE_OF_OAT_CHEESE = registerItem("piece_of_oat_cheese",
            new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)
                    .food(new FoodComponent.Builder().hunger(2).saturationModifier(0.3f).build())));

    public static final Item PIECE_OF_HERB_CHEESE = registerItem("piece_of_herb_cheese",
            new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)
                    .food(new FoodComponent.Builder().hunger(2).saturationModifier(0.3f).build())));

    public static final Item PIECE_OF_LAVENDER_CHEESE = registerItem("piece_of_lavender_cheese",
            new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)
                    .food(new FoodComponent.Builder().hunger(2).saturationModifier(0.3f).build())));

    public static final Item PIECE_OF_SHEEP_CHEESE = registerItem("piece_of_sheep_cheese",
            new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)
                    .food(new FoodComponent.Builder().hunger(2).saturationModifier(0.3f).build())));

    public static final Item CHEESECAKE_SLICE = registerItem("cheesecake_slice",
            new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)
                    .food(new FoodComponent.Builder().hunger(2).saturationModifier(0.3f).build())));

    public static final Item CHEESE_TART_SLICE = registerItem("cheese_tart_slice",
            new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)
                    .food(new FoodComponent.Builder().hunger(2).saturationModifier(0.3f).build())));

    public static final Item CREAM_CHEESE = registerItem("cream_cheese",
            new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)
                    .food(new FoodComponent.Builder().hunger(5).saturationModifier(0.6f).build())));

    public static final Item CHEESE_SANDWICH = registerItem("cheese_sandwich",
            new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)
                    .food(new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).build())));

    public static final Item CHEESE_ROLL = registerItem("cheese_roll",
            new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)
                    .food(new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).build())));

    public static final Item CHEESE_STICK = registerItem("cheese_stick",
            new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)
                    .food(new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).build())));

    public static final Item OAT_BREAD = registerItem("oat_bread",
            new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)
                    .food(new FoodComponent.Builder().hunger(5).saturationModifier(0.6f).build())));

    public static final Item RICOLA = registerItem("ricola",
            new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)
                    .food(new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).build())));

    public static final Item JUG = registerItem("jug", new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item JUG_MILK = registerItem("jug_milk",
            new DrinkItem(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)
                    .food(new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).build())));

    public static final Item JUG_YARROW_TEA = registerItem("jug_yarrow_tea",
            new DrinkItem(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)
                    .food(new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).build())));

    public static final Item JUG_JUNIPER_TEA = registerItem("jug_juniper_tea",
            new DrinkItem(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)
                    .food(new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).build())));

    public static final Item LAB = registerItem("lab", new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item WATERING_CAN = registerItem("watering_can", new WateringCanItem(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT).maxDamage(25)));

    public static final Item OAT_OIL = registerItem("oat_oil", new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item OAT_SEEDS = registerItem("oat_seeds",
            new AliasedBlockItem(ModBlocks.OAT_CROP,
                    new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item YARROW_SEEDS = registerItem("yarrow_seeds",
            new AliasedBlockItem(ModBlocks.YARROW_CROP,
                    new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item JUNIPER_SEEDS = registerItem("juniper_seeds",
            new AliasedBlockItem(ModBlocks.JUNIPER_CROP,
                    new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item MOUNTAIN_LAVENDER_SEEDS = registerItem("mountain_lavender_seeds",
            new AliasedBlockItem(ModBlocks.MOUNTAIN_LAVENDER_CROP,
                    new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));
    public static final Item BAG_OF_LAVENDER = registerItem("bag_of_lavender", new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item BAG_OF_JUNIPER = registerItem("bag_of_juniper", new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item BAG_OF_YARROW = registerItem("bag_of_yarrow", new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item OAT = registerItem("oat",
            new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));
    public static final Item CHEESE_MASS = registerItem("cow_cheese_mass_bucket", new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item BUFFALO_CHEESE_MASS = registerItem("buffalo_cheese_mass_bucket", new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item GOAT_CHEESE_MASS = registerItem("goat_cheese_mass_bucket", new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item OAT_CHEESE_MASS = registerItem("oat_cheese_mass_bucket", new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item SHEEP_CHEESE_MASS = registerItem("sheep_cheese_mass_bucket", new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item LAVENDER_CHEESE_MASS = registerItem("lavender_cheese_mass_bucket", new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item HERBS_CHEESE_MASS = registerItem("herbs_cheese_mass_bucket", new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));


    public static final Item WOODEN_CHEESE_MASS = registerItem("wooden_cow_cheese_mass_bucket", new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item WOODEN_BUFFALO_CHEESE_MASS = registerItem("wooden_buffalo_cheese_mass_bucket", new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item WOODEN_GOAT_CHEESE_MASS = registerItem("wooden_goat_cheese_mass_bucket", new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item WOODEN_OAT_CHEESE_MASS = registerItem("wooden_oat_cheese_mass_bucket", new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item WOODEN_SHEEP_CHEESE_MASS = registerItem("wooden_sheep_cheese_mass_bucket", new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item WOODEN_LAVENDER_CHEESE_MASS = registerItem("wooden_lavender_cheese_mass_bucket", new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item WOODEN_HERBS_CHEESE_MASS = registerItem("wooden_herbs_cheese_mass_bucket", new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));


    public static final Item BUFFALO_MILK = registerItem("buffalo_milk_bucket", new MilkBucketItem(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item GOAT_MILK = registerItem("goat_milk_bucket", new MilkBucketItem(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item OAT_MILK = registerItem("oat_milk_bucket", new MilkBucketItem(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item SHEEP_MILK = registerItem("sheep_milk_bucket", new MilkBucketItem(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item WOODEN_BUCKET = registerItem("wooden_bucket", new WoodenBucket(Fluids.EMPTY, new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item WOODEN_WATER_BUCKET = registerItem("wooden_water_bucket", new WoodenBucket(Fluids.WATER, new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));
    public static final Item WOODEN_POWDER_SNOW_BUCKET = registerItem("wooden_powder_snow_bucket", new WoodenPowderSnowBucket(Blocks.POWDER_SNOW, SoundEvents.ITEM_BUCKET_EMPTY_POWDER_SNOW, new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item WOODEN_MILK_BUCKET = registerItem("wooden_milk_bucket", new WoodenMilkBucket(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item WOODEN_BUFFALO_MILK = registerItem("wooden_buffalo_milk_bucket", new WoodenMilkBucket(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item WOODEN_GOAT_MILK = registerItem("wooden_goat_milk_bucket", new WoodenMilkBucket(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item WOODEN_OAT_MILK = registerItem("wooden_oat_milk_bucket", new WoodenMilkBucket(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item WOODEN_SHEEP_MILK = registerItem("wooden_sheep_milk_bucket", new WoodenMilkBucket(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item BEAR_FUR = registerItem("bear_fur",
            new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Meadow.MOD_ID, name), item);
    }
    public static void registerModItems () {
        Meadow.LOGGER.debug("Registering Mod Items for " + Meadow.MOD_ID);
    }

    private static Item registerSpawnEgg(EntityType entityType, int color1, int color2){
        String s = Registry.ENTITY_TYPE.getId(entityType).toString();
        s = Arrays.stream(s.split(":")).toList().get(1);
        return registerItem(s + "_spawn_egg", new SpawnEggItem(entityType, color1, color2, new Item.Settings().group(ModItemGroup.ALPINE_SALT)));
    }
}
