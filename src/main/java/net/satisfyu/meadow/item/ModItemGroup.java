package net.satisfyu.meadow.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;

public class ModItemGroup {
        public static final ItemGroup ALPINE_SALT = FabricItemGroupBuilder.build(
                new Identifier(Meadow.MOD_ID, "alpine_salt"), () -> new ItemStack(ModItems.PIECE_OF_CHEESE));
}