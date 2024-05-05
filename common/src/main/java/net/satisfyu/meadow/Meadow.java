package net.satisfyu.meadow;

import de.cristelknight.doapi.DoApiEP;
import dev.architectury.hooks.item.tool.AxeItemHooks;
import net.minecraft.world.level.block.Blocks;
import net.satisfyu.meadow.registry.*;
import net.satisfyu.meadow.util.MeadowIdentifier;
import net.satisfyu.meadow.util.WoodenCauldronBehavior;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Meadow {
    public static final String MOD_ID = "meadow";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void init() {
        DataFixerRegistry.init();
        TabRegistry.init();
        ObjectRegistry.init();
        BoatsAndSignsRegistry.init();
        EntityRegistry.init();
        BlockEntityRegistry.init();
        RecipeRegistry.init();
        SoundRegistry.init();
        ScreenHandlerRegistry.init();
        DoApiEP.registerBuiltInPack(Meadow.MOD_ID, new MeadowIdentifier("better_leaves"), false);
        DoApiEP.registerBuiltInPack(Meadow.MOD_ID, new MeadowIdentifier("green_stove"), false);
        DoApiEP.registerBuiltInPack(Meadow.MOD_ID, new MeadowIdentifier("optifine_support"), false);
    }

    public static void commonSetup() {
        FlammableBlockRegistry.init();
        WoodenCauldronBehavior.bootStrap();
        AxeItemHooks.addStrippable(ObjectRegistry.PINE_LOG.get(), ObjectRegistry.STRIPPED_PINE_LOG.get());
        AxeItemHooks.addStrippable(ObjectRegistry.PINE_WOOD.get(), ObjectRegistry.STRIPPED_PINE_WOOD.get());
        AxeItemHooks.addStrippable(ObjectRegistry.ALPINE_BIRCH_LOG.get(), Blocks.STRIPPED_BIRCH_LOG);
        AxeItemHooks.addStrippable(ObjectRegistry.ALPINE_OAK_LOG.get(), Blocks.STRIPPED_OAK_LOG);
    }
}


