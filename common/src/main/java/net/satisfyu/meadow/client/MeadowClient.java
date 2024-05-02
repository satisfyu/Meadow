package net.satisfyu.meadow.client;

import de.cristelknight.doapi.terraform.sign.TerraformSignHelper;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.client.render.*;
import net.satisfyu.meadow.client.gui.CheeseFormGui;
import net.satisfyu.meadow.client.gui.CookingCauldronGui;
import net.satisfyu.meadow.client.gui.FondueGui;
import net.satisfyu.meadow.client.gui.WoodcutterGui;
import net.satisfyu.meadow.client.model.WaterBuffaloModel;
import net.satisfyu.meadow.client.render.WaterBuffaloRenderer;
import net.satisfyu.meadow.client.render.ShearableVarCowRenderer;
import net.satisfyu.meadow.client.model.WoolyCowModel;
import net.satisfyu.meadow.registry.*;

public class MeadowClient {
    public static final ModelLayerLocation BROWN_BEAR_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "brown_bear"), "main");
    public static final ModelLayerLocation SHEARABLE_MEADOW_COW_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "shearable_meadow_cow"), "head");
    public static final ModelLayerLocation WATER_BUFFALO_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "water_buffalo"), "head");

    public static void preInitClient() {
        registerEntityRenderers();
        registerEntityModelLayers();

        TerraformSignHelper.regsterSignSprite(BoatsAndSignsRegistry.PINE_SIGN_TEXTURE);
        TerraformSignHelper.regsterSignSprite(BoatsAndSignsRegistry.PINE_HANGING_SIGN_TEXTURE);
    }

    public static void initClient() {
        RenderTypeRegistry.register(RenderType.cutout(), ObjectRegistry.WOODCUTTER.get(),
                ObjectRegistry.PINE_DOOR.get(), ObjectRegistry.PINE_TRAPDOOR.get(), ObjectRegistry.DELPHINIUM.get(), ObjectRegistry.ALPINE_POPPY.get(), ObjectRegistry.SAXIFRAGE.get(),
                ObjectRegistry.ENZIAN.get(), ObjectRegistry.COOKING_CAULDRON.get(), ObjectRegistry.FRAME.get(), ObjectRegistry.TABLE.get(), ObjectRegistry.FIRE_LOG.get(), ObjectRegistry.ERIOPHORUM.get(),
                ObjectRegistry.ERIOPHORUM_TALL.get(), ObjectRegistry.SMALL_FIR.get(), ObjectRegistry.PINE_SAPLING.get(), ObjectRegistry.CHAIR.get(),
                ObjectRegistry.POTTED_DELPHINIUM.get(), ObjectRegistry.POTTED_ALPINE_POPPY.get(), ObjectRegistry.POTTED_SAXIFRAGE.get(), ObjectRegistry.POTTED_ENZIAN.get(),
                ObjectRegistry.POTTED_ERIOPHORUM.get(), ObjectRegistry.ERIOPHORUM_TALL.get(), ObjectRegistry.PINE_SAPLING.get(), ObjectRegistry.POTTED_PINE_SAPLING.get(), ObjectRegistry.FIRE_LILY.get(), ObjectRegistry.POTTED_FIRE_LILY.get(),
                ObjectRegistry.POTTED_OAK_SAPLING.get(), ObjectRegistry.POTTED_SPRUCE_SAPLING.get(), ObjectRegistry.POTTED_BIRCH_SAPLING.get(), ObjectRegistry.POTTED_JUNGLE_SAPLING.get(), ObjectRegistry.POTTED_ACACIA_SAPLING.get(),
                ObjectRegistry.POTTED_DARK_OAK_SAPLING.get(), ObjectRegistry.POTTED_DANDELION.get(), ObjectRegistry.POTTED_POPPY.get(), ObjectRegistry.POTTED_BLUE_ORCHID.get(), ObjectRegistry.POTTED_ALLIUM.get(), ObjectRegistry.POTTED_AZURE_BLUET.get(),
                ObjectRegistry.POTTED_RED_TULIP.get(), ObjectRegistry.POTTED_ORANGE_TULIP.get(), ObjectRegistry.POTTED_WHITE_TULIP.get(), ObjectRegistry.POTTED_PINK_TULIP.get(), ObjectRegistry.POTTED_OXEYE_DAISY.get(), ObjectRegistry.POTTED_CORNFLOWER.get(),
                ObjectRegistry.POTTED_LILY_OF_THE_VALLEY.get(), ObjectRegistry.POTTED_WITHER_ROSE.get(), ObjectRegistry.W_POTTED_PINE_SAPLING.get(), ObjectRegistry.W_POTTED_DELPHINIUM.get(), ObjectRegistry.W_POTTED_ALPINE_POPPY.get(), ObjectRegistry.W_POTTED_SAXIFRAGE.get(),
                ObjectRegistry.W_POTTED_ENZIAN.get(), ObjectRegistry.W_POTTED_FIRE_LILY.get(), ObjectRegistry.W_POTTED_ERIOPHORUM.get(), ObjectRegistry.POTTED_AZALEA.get(), ObjectRegistry.POTTED_FLOWERING_AZALEA.get(), ObjectRegistry.FLOWER_POT_BIG.get(), ObjectRegistry.FONDUE.get(),
                ObjectRegistry.OIL_LANTERN.get(), ObjectRegistry.WHEELBARROW.get(), ObjectRegistry.POTTED_MANGROVE_PROPAGULE.get(), ObjectRegistry.PINE_LEAVES_2.get());

        RenderTypeRegistry.register(RenderType.translucent(), ObjectRegistry.WINDOW.get(), ObjectRegistry.WINDOW_2.get(), ObjectRegistry.WINDOW_3.get());

        registerBlockRenderer();
        registerClientScreens();

        ColorHandlerRegistry.registerBlockColors((state, world, pos, tintIndex) -> {
                    if (world == null || pos == null) {
                        return -1;
                    }
                    return BiomeColors.getAverageWaterColor(world, pos);
                }, ObjectRegistry.WOODEN_WATER_CAULDRON
        );


    }

    private static void registerBlockRenderer() {
        BlockEntityRendererRegistry.register(BlockEntityRegistry.FLOWER_BOX_BLOCK_ENTITY.get(), FlowerBoxBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(BlockEntityRegistry.WHEEL_BARROW_BLOCK_ENTITY.get(), WheelBarrowBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(BlockEntityRegistry.BIG_FLOWER_POT_BLOCK_ENTITY.get(), BigFlowerPotBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(BlockEntityRegistry.CHEESE_RACK_BLOCK_ENTITY.get(), CheeseRackBlockEntityRenderer::new);
    }

    private static void registerClientScreens() {
        MenuRegistry.registerScreenFactory(ScreenHandlerRegistry.CHEESE_FORM_SCREEN_HANDLER.get(), CheeseFormGui::new);
        MenuRegistry.registerScreenFactory(ScreenHandlerRegistry.WOODCUTTER_SCREEN_HANDLER.get(), WoodcutterGui::new);
        MenuRegistry.registerScreenFactory(ScreenHandlerRegistry.COOKING_CAULDRON_SCREEN_HANDLER.get(), CookingCauldronGui::new);
        MenuRegistry.registerScreenFactory(ScreenHandlerRegistry.FONDUE_SCREEN_HANDLER.get(), FondueGui::new);
    }

    private static void registerEntityRenderers() {
        EntityRendererRegistry.register(EntityRegistry.SHEARABLE_MEADOW_VAR_COW, ShearableVarCowRenderer::new);

        EntityRendererRegistry.register(EntityRegistry.WATER_BUFFALO, WaterBuffaloRenderer::new);
    }

    public static void registerEntityModelLayers() {
        EntityModelLayerRegistry.register(SHEARABLE_MEADOW_COW_MODEL_LAYER, WoolyCowModel::createBodyLayer);
        EntityModelLayerRegistry.register(WATER_BUFFALO_MODEL_LAYER, WaterBuffaloModel::getTexturedModelData);

        ArmorRegistry.registerArmorModelLayers();
    }
}