package net.satisfyu.meadow.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.ModBlocks;


public class ModItems {

    public static final Item ALPINE_SALT = registerItem("alpine_salt",
            new Item(new FabricItemSettings().group(ModItemGroup.ALPINE_SALT)));

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
}
