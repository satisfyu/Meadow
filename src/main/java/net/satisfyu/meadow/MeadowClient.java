package net.satisfyu.meadow;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.block.cookingCauldron.CookingCauldronScreen;
import net.satisfyu.meadow.block.woodCutter.WoodcutterScreen;
import net.satisfyu.meadow.entity.ModEntities;
import net.satisfyu.meadow.entity.custom.chair.ChairEntity;
import net.satisfyu.meadow.entity.custom.chair.ChairRenderer;
import net.satisfyu.meadow.entity.custom.cow.MeadowCowEntityMdodel;
import net.satisfyu.meadow.entity.custom.cow.albino_cow.AlbinoCowRenderer;
import net.satisfyu.meadow.entity.custom.cow.ashen_cow.AshenCowRenderer;
import net.satisfyu.meadow.entity.custom.cow.cookie_cow.CookieCowRenderer;
import net.satisfyu.meadow.entity.custom.cow.cream_cow.CreamCowRenderer;
import net.satisfyu.meadow.entity.custom.cow.dairy_cow.DairyCowRenderer;
import net.satisfyu.meadow.entity.custom.cow.dark_cow.DarkCowRenderer;
import net.satisfyu.meadow.entity.custom.cow.pinto_cow.PintoCowRenderer;
import net.satisfyu.meadow.entity.custom.cow.sunset_cow.SunsetCowRenderer;
import net.satisfyu.meadow.particle.ModParticles;
import net.satisfyu.meadow.particle.custom.SplashParticle;

import static net.satisfyu.meadow.Meadow.*;

public class MeadowClient implements ClientModInitializer {

    public static final EntityModelLayer ALBINO_COW_MODEL_LAYER = new EntityModelLayer(new Identifier(MOD_ID, "albino_cow"), "head");

    public static final EntityModelLayer ASHEN_COW_MODEL_LAYER = new EntityModelLayer(new Identifier(MOD_ID, "ashen_cow"), "head");

    public static final EntityModelLayer COOKIE_COW_MODEL_LAYER = new EntityModelLayer(new Identifier(MOD_ID, "cookie_cow"), "head");

    public static final EntityModelLayer CREAM_COW_MODEL_LAYER = new EntityModelLayer(new Identifier(MOD_ID, "cream_cow"), "head");

    public static final EntityModelLayer DAIRY_COW_MODEL_LAYER = new EntityModelLayer(new Identifier(MOD_ID, "dairy_cow"), "head");

    public static final EntityModelLayer DARK_COW_MODEL_LAYER = new EntityModelLayer(new Identifier(MOD_ID, "dark_cow"), "head");

    public static final EntityModelLayer PINTO_COW_MODEL_LAYER = new EntityModelLayer(new Identifier(MOD_ID, "pinto_cow"), "head");

    public static final EntityModelLayer SUNSET_COW_MODEL_LAYER = new EntityModelLayer(new Identifier(MOD_ID, "sunset_cow"), "head");

    @Override
    public void onInitializeClient() {
        HandledScreens.register(WOODCUTTOR_SCREEN_HANDLER, WoodcutterScreen::new);
        HandledScreens.register(COOKING_CAULDRON_SCREEN_HANDLER, CookingCauldronScreen::new);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.WOODCUTTER, ModBlocks.OAT_CROP, ModBlocks.ALPINE_GRASS, ModBlocks.ALPINE_GRASS_TALL,
                ModBlocks.PINE_DOOR, ModBlocks.HAYBLOCK_RUG, ModBlocks.PINE_TRAPDOOR, ModBlocks.DELPHINIUM, ModBlocks.ALPINE_POPPY, ModBlocks.SAXIFRAGE,
                ModBlocks.ENZIAN, ModBlocks.COOKING_CAULDRON, ModBlocks.FRAME, ModBlocks.TABLE, ModBlocks.AXE_WOODSTACK, ModBlocks.ERIOPHORUM,
                ModBlocks.ERIOPHORUM_TALL, ModBlocks.FURNACE_COBBLESTONE, ModBlocks.SMALL_FIR, ModBlocks.PINE_SAPLING, ModBlocks.CHAIR, ModBlocks.OATBLOCK_RUG,
                ModBlocks.POTTED_DELPHINIUM, ModBlocks.POTTED_ALPINE_POPPY, ModBlocks.POTTED_SAXIFRAGE, ModBlocks.POTTED_ENZIAN,
                ModBlocks.POTTED_ERIOPHORUM, ModBlocks.WILD_JUNIPER, ModBlocks.WILD_MOUNTAIN_LAVENDER, ModBlocks.WILD_YARROW,
                ModBlocks.POTTED_ERIOPHORUM, ModBlocks.ERIOPHORUM_TALL, ModBlocks.PINE_SAPLING, ModBlocks.POTTED_PINE_SAPLING, ModBlocks.FIRE_LILY, ModBlocks.POTTED_FIRE_LILY);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(), ModBlocks.WINDOW);

        registerEntityStuff();
        registerParticles();
    }

    private void registerParticles(){
        ParticleFactoryRegistry.getInstance().register(ModParticles.SPLASH, SplashParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.CHEESE_SPLASH, SplashParticle.Factory::new);
    }

    private void registerEntityStuff(){
        EntityRendererRegistry.register(ModEntities.ALBINO_COW, AlbinoCowRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ALBINO_COW_MODEL_LAYER, MeadowCowEntityMdodel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.ASHEN_COW, AshenCowRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ASHEN_COW_MODEL_LAYER, MeadowCowEntityMdodel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.COOKIE_COW, CookieCowRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(COOKIE_COW_MODEL_LAYER, MeadowCowEntityMdodel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.CREAM_COW, CreamCowRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(CREAM_COW_MODEL_LAYER, MeadowCowEntityMdodel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.DAIRY_COW, DairyCowRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(DAIRY_COW_MODEL_LAYER, MeadowCowEntityMdodel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.DARK_COW, DarkCowRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(DARK_COW_MODEL_LAYER, MeadowCowEntityMdodel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.PINTO_COW, PintoCowRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(PINTO_COW_MODEL_LAYER, MeadowCowEntityMdodel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.SUNSET_COW, SunsetCowRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(SUNSET_COW_MODEL_LAYER, MeadowCowEntityMdodel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.CHAIR, ChairRenderer::new);

    }

}
