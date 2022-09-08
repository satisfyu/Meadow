package net.satisfyu.meadow.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.entity.ModEntities;

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

    public static final Item OAT_SEEDS = registerItem("oat_seeds",
            new AliasedBlockItem(ModBlocks.OAT_CROP,
                    new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

    public static final Item BAG_OF_OAT = registerItem("bag_of_oat",
            new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(1f).build())));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Meadow.MOD_ID, name), item);
    }
    public static void registerModItems () {
        Meadow.LOGGER.debug("Registering Mod Items for " + Meadow.MOD_ID);
    }

    private static Item registerSpawnEgg(EntityType entityType, int color1, int color2){
        String s = Registry.ENTITY_TYPE.getId(entityType).toString();
        s = Arrays.stream(s.split(":")).toList().get(1);
        return registerItem(s + "_spawn_egg", new SpawnEggItem(entityType, color1, color2, new Item.Settings().group(ItemGroup.MISC)));
    }
}
