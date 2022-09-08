package net.satisfyu.meadow;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.block.woodCutter.WoodcutterScreen;
import net.satisfyu.meadow.entity.ModEntities;
import net.satisfyu.meadow.entity.cow.MeadowCowEntityMdodel;
import net.satisfyu.meadow.entity.cow.albino_cow.AlbinoCowRenderer;
import net.satisfyu.meadow.entity.cow.ashen_cow.AshenCowRenderer;
import net.satisfyu.meadow.entity.cow.cookie_cow.CookieCowRenderer;
import net.satisfyu.meadow.entity.cow.cream_cow.CreamCowRenderer;
import net.satisfyu.meadow.entity.cow.dairy_cow.DairyCowRenderer;
import net.satisfyu.meadow.entity.cow.dark_cow.DarkCowEntity;
import net.satisfyu.meadow.entity.cow.dark_cow.DarkCowRenderer;
import net.satisfyu.meadow.entity.cow.pinto_cow.PintoCowRenderer;
import net.satisfyu.meadow.entity.cow.sunset_cow.SunsetCowRenderer;

import static net.satisfyu.meadow.Meadow.MOD_ID;
import static net.satisfyu.meadow.Meadow.WOODCUTTOR_SCREEN_HANDLER;

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
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.WOODCUTTER, ModBlocks.OAT_CROP, ModBlocks.ALPINE_GRASS, ModBlocks.ALPINE_GRASS_TALL);

        registerEntityStuff();
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
    }

}
