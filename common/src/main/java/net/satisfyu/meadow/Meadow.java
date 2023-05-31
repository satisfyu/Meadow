package net.satisfyu.meadow;

import dev.architectury.hooks.item.tool.AxeItemHooks;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.block.ModBlockEntities;
import net.satisfyu.meadow.block.woodenCauldren.WoodenCauldronBehavior;
import net.satisfyu.meadow.effects.MeadowEffects;
import net.satisfyu.meadow.entity.ModEntities;
import net.satisfyu.meadow.painting.ModPaintings;
import net.satisfyu.meadow.particle.ModParticles;
import net.satisfyu.meadow.recipes.ModRecipes;
import net.satisfyu.meadow.registry.ObjectRegistry;
import net.satisfyu.meadow.screenHandler.ModScreenHandlers;
import net.satisfyu.meadow.sound.ModSounds;
import net.satisfyu.meadow.world.feature.ModFeatures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Meadow {
    public static final String MOD_ID = "meadow";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Identifier PINE_ID = new Identifier(MOD_ID, "pine");
    public static final Identifier PINE_BOAT_ID = new Identifier(MOD_ID, "pine_boat");
    public static final Identifier PINE_CHEST_BOAT_ID = new Identifier(MOD_ID, "pine_chest_boat");

    public static void init() {
        ModSounds.init();
        ObjectRegistry.init();
        ModEntities.init();
        ModBlockEntities.init();
        MeadowEffects.init();
        ModPaintings.init();
        ModRecipes.init();
        ModScreenHandlers.init();
        ModParticles.registerParticles();
        ModFeatures.init();

        //TODO
        /*
        FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(container -> {
            ResourceManagerHelper.registerBuiltinResourcePack(new MeadowIdentifier("bushy_leaves"), container, ResourcePackActivationType.NORMAL);
        });

        FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(container -> {
            ResourceManagerHelper.registerBuiltinResourcePack(new MeadowIdentifier("optifine_support"), container, ResourcePackActivationType.NORMAL);
        });
         */
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
