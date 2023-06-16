package net.satisfyu.meadow;

import dev.architectury.hooks.item.tool.AxeItemHooks;
import dev.architectury.registry.CreativeTabRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceFactory;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.registry.BlockEntityRegistry;
import net.satisfyu.meadow.util.MeadowIdentifier;
import net.satisfyu.meadow.util.WoodenCauldronBehavior;
import net.satisfyu.meadow.registry.EffectRegistry;
import net.satisfyu.meadow.registry.EntityRegistry;
import net.satisfyu.meadow.registry.RecipeRegistry;
import net.satisfyu.meadow.registry.ObjectRegistry;
import net.satisfyu.meadow.registry.ScreenHandlerRegistry;
import net.satisfyu.meadow.registry.SoundRegistry;
import net.satisfyu.meadow.registry.FeatureRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Meadow {
    public static final String MOD_ID = "meadow";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final ItemGroup MEADOW_TAB = CreativeTabRegistry.create(new Identifier(Meadow.MOD_ID, "meadow_tab"), () ->
            new ItemStack(ObjectRegistry.PIECE_OF_CHEESE.get()));


    public static final Identifier PINE_ID = new Identifier(MOD_ID, "pine");
    public static final Identifier PINE_BOAT_ID = new Identifier(MOD_ID, "pine_boat");
    public static final Identifier PINE_CHEST_BOAT_ID = new Identifier(MOD_ID, "pine_chest_boat");

    public static void init() {
        SoundRegistry.init();
        ObjectRegistry.init();
        EntityRegistry.init();
        BlockEntityRegistry.init();
        EffectRegistry.init();
        RecipeRegistry.init();
        ScreenHandlerRegistry.init();
        FeatureRegistry.init();
    }

    public static void commonSetup(){
        ObjectRegistry.commonInit();
        WoodenCauldronBehavior.registerBehavior();

        AxeItemHooks.addStrippable(ObjectRegistry.PINE_LOG.get(), ObjectRegistry.STRIPPED_PINE_LOG.get());
        AxeItemHooks.addStrippable(ObjectRegistry.PINE_WOOD.get(), ObjectRegistry.STRIPPED_PINE_WOOD.get());
        AxeItemHooks.addStrippable(ObjectRegistry.ALPINE_BIRCH_LOG.get(), Blocks.STRIPPED_BIRCH_LOG);
        AxeItemHooks.addStrippable(ObjectRegistry.ALPINE_OAK_LOG.get(), Blocks.STRIPPED_OAK_LOG);
    }
}
