package net.satisfyu.meadow.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;


public class ModItems {

    public static final Item ALPINE_SALT = registerItem("alpine_salt",
            new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));




    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Meadow.MOD_ID, name), item);
    }
    public static void registerModItems () {
        Meadow.LOGGER.debug("Registering Mod Items for " + Meadow.MOD_ID);
    }
}
