package net.satisfyu.meadow.item;

import dev.architectury.registry.CreativeTabRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.registry.ObjectRegistry;

public class ModItemGroup {
        public static final ItemGroup MEADOW_TAB = CreativeTabRegistry.create(new Identifier(Meadow.MOD_ID, "example_tab"), () ->
                new ItemStack(ObjectRegistry.PIECE_OF_CHEESE.get()));
}