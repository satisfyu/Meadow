package net.satisfyu.meadow.client;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.ModBlockEntities;
import net.satisfyu.meadow.block.cheeseForm.CheeseFormScreen;
import net.satisfyu.meadow.block.cookingCauldron.CookingCauldronScreen;
import net.satisfyu.meadow.block.cookingPot.CookingPotScreen;
import net.satisfyu.meadow.block.fondueBlock.FondueScreen;
import net.satisfyu.meadow.block.woodCutter.WoodcutterScreen;
import net.satisfyu.meadow.entity.ModEntities;
import net.satisfyu.meadow.entity.custom.bear.brown.BrownBearEntityModel;
import net.satisfyu.meadow.entity.custom.bear.brown.BrownBearEntityRenderer;
import net.satisfyu.meadow.entity.custom.buffalo.water_buffalo.WaterBuffaloEntityModel;
import net.satisfyu.meadow.entity.custom.buffalo.water_buffalo.WaterBuffaloEntityRenderer;
import net.satisfyu.meadow.entity.custom.chair.ChairEntityRenderer;
import net.satisfyu.meadow.entity.custom.chicken.chicken1.Chicken1Renderer;
import net.satisfyu.meadow.entity.custom.chicken.chicken2.Chicken2Renderer;
import net.satisfyu.meadow.entity.custom.chicken.chicken3.Chicken3Renderer;
import net.satisfyu.meadow.entity.custom.cow.albino_cow.AlbinoCowRenderer;
import net.satisfyu.meadow.entity.custom.cow.cookie_cow.CookieCowRenderer;
import net.satisfyu.meadow.entity.custom.cow.cream_cow.CreamCowRenderer;
import net.satisfyu.meadow.entity.custom.cow.dairy_cow.DairyCowRenderer;
import net.satisfyu.meadow.entity.custom.cow.dark_cow.DarkCowRenderer;
import net.satisfyu.meadow.entity.custom.cow.pinto_cow.PintoCowRenderer;
import net.satisfyu.meadow.entity.custom.cow.shearable.WoolyCowModel;
import net.satisfyu.meadow.entity.custom.cow.shearable.highland_cattle.HighlandCattleRenderer;
import net.satisfyu.meadow.entity.custom.cow.shearable.umbra.UmbraCowRenderer;
import net.satisfyu.meadow.entity.custom.cow.shearable.warped.WarpedCowRenderer;
import net.satisfyu.meadow.entity.custom.cow.sunset_cow.SunsetCowRenderer;
import net.satisfyu.meadow.entity.custom.sheep.flecked.FleckedSheepRenderer;
import net.satisfyu.meadow.entity.custom.sheep.fuzzy.FuzzySheepRenderer;
import net.satisfyu.meadow.entity.custom.sheep.horned.HornedSheepModel;
import net.satisfyu.meadow.entity.custom.sheep.horned.HornedSheepRenderer;
import net.satisfyu.meadow.entity.custom.sheep.inky.InkySheepRenderer;
import net.satisfyu.meadow.entity.custom.sheep.long_nosed.LongNosedSheepRenderer;
import net.satisfyu.meadow.entity.custom.sheep.patched.PatchedSheepRenderer;
import net.satisfyu.meadow.entity.custom.sheep.rocky.RockySheepRenderer;
import net.satisfyu.meadow.item.custom.BowModelProvider;
import net.satisfyu.meadow.item.custom.FurArmorItem;
import net.satisfyu.meadow.registry.ObjectRegistry;
import net.satisfyu.meadow.render.FlowerBoxBlockEntityRenderer;
import net.satisfyu.meadow.screenHandler.ModScreenHandlers;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MeadowClient {

    public static final EntityModelLayer FLECKED_SHEEP_FUR = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "flecked_sheep"), "fur");
    public static final EntityModelLayer FLECKED_SHEEP_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "flecked_sheep"), "main");

    public static final EntityModelLayer FUZZY_SHEEP_FUR = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "fuzzy_sheep"), "fur");
    public static final EntityModelLayer FUZZY_SHEEP_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "fuzzy_sheep"), "main");

    public static final EntityModelLayer HORNED_SHEEP_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "horned_sheep"), "main");

    public static final EntityModelLayer BROWN_BEAR_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "brown_bear"), "main");

    public static final EntityModelLayer INKY_SHEEP_FUR = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "inky_sheep"), "fur");
    public static final EntityModelLayer INKY_SHEEP_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "inky_sheep"), "main");

    public static final EntityModelLayer LONG_NOSED_SHEEP_FUR = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "long_nosed_sheep"), "fur");
    public static final EntityModelLayer LONG_NOSED_SHEEP_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "long_nosed_sheep"), "main");

    public static final EntityModelLayer PATCHED_SHEEP_FUR = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "patched_sheep"), "fur");
    public static final EntityModelLayer PATCHED_SHEEP_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "patched_sheep"), "main");

    public static final EntityModelLayer ROCKY_SHEEP_FUR = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "rocky_sheep"), "fur");
    public static final EntityModelLayer ROCKY_SHEEP_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "rocky_sheep"), "main");

    public static final EntityModelLayer UMBRA_COW_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "umbra_cow"), "head");
    public static final EntityModelLayer WARPED_COW_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "warped_cow"), "head");

    public static final EntityModelLayer HIGHLAND_CATTLE_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "highland_cattle"), "head");

    public static final EntityModelLayer ALBINO_COW_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "albino_cow"), "head");
    public static final EntityModelLayer WATER_BUFFALO_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "water_buffalo"), "head");

    public static final EntityModelLayer COOKIE_COW_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "cookie_cow"), "head");

    public static final EntityModelLayer CREAM_COW_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "cream_cow"), "head");

    public static final EntityModelLayer DAIRY_COW_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "dairy_cow"), "head");

    public static final EntityModelLayer DARK_COW_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "dark_cow"), "head");

    public static final EntityModelLayer PINTO_COW_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "pinto_cow"), "head");

    public static final EntityModelLayer SUNSET_COW_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "sunset_cow"), "head");

    public static final EntityModelLayer CHICKEN1_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "chicken_1"), "main");

    public static final EntityModelLayer CHICKEN2_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "chicken_2"), "main");

    public static final EntityModelLayer CHICKEN3_MODEL_LAYER = new EntityModelLayer(new Identifier(Meadow.MOD_ID, "chicken_3"), "main");

    public static void preInitClient(){
        registerEntityRenderers();
        registerEntityModelLayers();

        registerCows();
        registerSheeps();
        registerChicken();
    }

    public static void initClient() {
        RenderTypeRegistry.register(RenderLayer.getCutout(), ObjectRegistry.WOODCUTTER.get(), ObjectRegistry.OAT_CROP.get(), ObjectRegistry.JUNIPER_CROP.get(), ObjectRegistry.YARROW_CROP.get(), ObjectRegistry.MOUNTAIN_LAVENDER_CROP.get(), ObjectRegistry.ALPINE_GRASS.get(), ObjectRegistry.ALPINE_GRASS_TALL.get(),
                ObjectRegistry.PINE_DOOR.get(), ObjectRegistry.PINE_TRAPDOOR.get(), ObjectRegistry.DELPHINIUM.get(), ObjectRegistry.ALPINE_POPPY.get(), ObjectRegistry.SAXIFRAGE.get(),
                ObjectRegistry.ENZIAN.get(), ObjectRegistry.COOKING_CAULDRON.get(), ObjectRegistry.FRAME.get(), ObjectRegistry.TABLE.get(), ObjectRegistry.FIRE_LOG.get(), ObjectRegistry.ERIOPHORUM.get(),
                ObjectRegistry.ERIOPHORUM_TALL.get(), ObjectRegistry.FURNACE_COBBLESTONE.get(), ObjectRegistry.SMALL_FIR.get(), ObjectRegistry.PINE_SAPLING.get(), ObjectRegistry.CHAIR.get(), ObjectRegistry.OATBLOCK_RUG.get(),
                ObjectRegistry.POTTED_DELPHINIUM.get(), ObjectRegistry.POTTED_ALPINE_POPPY.get(), ObjectRegistry.POTTED_SAXIFRAGE.get(), ObjectRegistry.POTTED_ENZIAN.get(),
                ObjectRegistry.POTTED_ERIOPHORUM.get(), ObjectRegistry.WILD_JUNIPER.get(), ObjectRegistry.WILD_MOUNTAIN_LAVENDER.get(), ObjectRegistry.WILD_YARROW.get(),
                ObjectRegistry.POTTED_ERIOPHORUM.get(), ObjectRegistry.ERIOPHORUM_TALL.get(), ObjectRegistry.PINE_SAPLING.get(), ObjectRegistry.POTTED_PINE_SAPLING.get(), ObjectRegistry.FIRE_LILY.get(), ObjectRegistry.POTTED_FIRE_LILY.get(),
                ObjectRegistry.POTTED_OAK_SAPLING.get(), ObjectRegistry.POTTED_SPRUCE_SAPLING.get(), ObjectRegistry.POTTED_BIRCH_SAPLING.get(), ObjectRegistry.POTTED_JUNGLE_SAPLING.get(), ObjectRegistry.POTTED_ACACIA_SAPLING.get(),
                ObjectRegistry.POTTED_DARK_OAK_SAPLING.get(), ObjectRegistry.POTTED_DANDELION.get(), ObjectRegistry.POTTED_POPPY.get(), ObjectRegistry.POTTED_BLUE_ORCHID.get(), ObjectRegistry.POTTED_ALLIUM.get(), ObjectRegistry.POTTED_AZURE_BLUET.get(),
                ObjectRegistry.POTTED_RED_TULIP.get(), ObjectRegistry.POTTED_ORANGE_TULIP.get(), ObjectRegistry.POTTED_WHITE_TULIP.get(), ObjectRegistry.POTTED_PINK_TULIP.get(), ObjectRegistry.POTTED_OXEYE_DAISY.get(), ObjectRegistry.POTTED_CORNFLOWER.get(),
                ObjectRegistry.POTTED_LILY_OF_THE_VALLEY.get(), ObjectRegistry.POTTED_WITHER_ROSE.get(), ObjectRegistry.W_POTTED_PINE_SAPLING.get(), ObjectRegistry.W_POTTED_DELPHINIUM.get(), ObjectRegistry.W_POTTED_ALPINE_POPPY.get(), ObjectRegistry.W_POTTED_SAXIFRAGE.get(),
                ObjectRegistry.W_POTTED_ENZIAN.get(), ObjectRegistry.W_POTTED_FIRE_LILY.get(), ObjectRegistry.W_POTTED_ERIOPHORUM.get(), ObjectRegistry.POTTED_AZALEA.get(), ObjectRegistry.POTTED_FLOWERING_AZALEA.get(), ObjectRegistry.FLOWER_POT_BIG.get(), ObjectRegistry.FONDUE.get(), ObjectRegistry.OIL_LANTERN.get(), ObjectRegistry.WHEELBARROW.get());

        RenderTypeRegistry.register(RenderLayer.getTranslucent(), ObjectRegistry.WINDOW.get(), ObjectRegistry.WINDOW_2.get(), ObjectRegistry.WINDOW_3.get());

        //TODO
        //SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, ObjectRegistry.SIGN_TEXTURE_ID));

        registerBlockRenderer();
        registerClientScreens();
        registerParticles();

        BowModelProvider.registerModModels();

        //TerraformBoatClientHelper.registers(PINE_ID);
    }

    private static void registerBlockRenderer() {
        BlockEntityRendererRegistry.register(ModBlockEntities.FLOWER_BOX_BLOCK_ENTITY.get(), FlowerBoxBlockEntityRenderer::new);
    }

    private static void registerClientScreens() {
        MenuRegistry.registerScreenFactory(ModScreenHandlers.CHEESE_FORM_SCREEN_HANDLER.get(), CheeseFormScreen::new);
        MenuRegistry.registerScreenFactory(ModScreenHandlers.WOODCUTTOR_SCREEN_HANDLER.get(), WoodcutterScreen::new);
        MenuRegistry.registerScreenFactory(ModScreenHandlers.COOKING_CAULDRON_SCREEN_HANDLER.get(), CookingCauldronScreen::new);
        MenuRegistry.registerScreenFactory(ModScreenHandlers.COOKING_POT_SCREEN_HANDLER.get(), CookingPotScreen::new);
        MenuRegistry.registerScreenFactory(ModScreenHandlers.FONDUE_SCREEN_HANDLER.get(), FondueScreen::new);
    }

    private static void registerParticles() {
        //TODO
        //ParticleFactoryRegistry.getInstance().registerArmor(ModParticles.CHEESE_SPLASH, SplashParticle.Factory::new);
    }

    private static void registerEntityRenderers(){
        EntityRendererRegistry.register(ModEntities.BROWN_BEAR, BrownBearEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.CHAIR, ChairEntityRenderer::new);
    }

    public static void registerEntityModelLayers(){
        EntityModelLayerRegistry.register(BROWN_BEAR_MODEL_LAYER, BrownBearEntityModel::getTexturedModelData);
    }

    //TODO
    /*
    private void registerBowPredicate(HuntingBowItem bowItem) {
        ModelPredicateProviderRegistry.registerArmor(bowItem, new Identifier("pull"),
                (itemStack, clientWorld, livingEntity, i) -> {
                    if (livingEntity == null) {
                        return 0.0F;
                    }
                    return livingEntity.getActiveItem() != itemStack ? 0.0F
                            : (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20.0F;
                });
        ModelPredicateProviderRegistry.registerArmor(bowItem, new Identifier("pulling"),
                (itemStack, clientWorld, livingEntity, i) -> {
                    if (livingEntity == null) {
                        return 0.0F;
                    }
                    return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
                });
    }
    
     */

    private static void registerCows(){
        EntityRendererRegistry.register(ModEntities.UMBRA_COW, UmbraCowRenderer::new);
        EntityModelLayerRegistry.register(UMBRA_COW_MODEL_LAYER, WoolyCowModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.WARPED_COW, WarpedCowRenderer::new);
        EntityModelLayerRegistry.register(WARPED_COW_MODEL_LAYER, WoolyCowModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.HIGHLAND_CATTLE, HighlandCattleRenderer::new);
        EntityModelLayerRegistry.register(HIGHLAND_CATTLE_MODEL_LAYER, WoolyCowModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.ALBINO_COW, AlbinoCowRenderer::new);
        EntityModelLayerRegistry.register(ALBINO_COW_MODEL_LAYER, CowEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.WATER_BUFFALO, WaterBuffaloEntityRenderer::new);
        EntityModelLayerRegistry.register(WATER_BUFFALO_MODEL_LAYER, WaterBuffaloEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.COOKIE_COW, CookieCowRenderer::new);
        EntityModelLayerRegistry.register(COOKIE_COW_MODEL_LAYER, CowEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.CREAM_COW, CreamCowRenderer::new);
        EntityModelLayerRegistry.register(CREAM_COW_MODEL_LAYER, CowEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.DAIRY_COW, DairyCowRenderer::new);
        EntityModelLayerRegistry.register(DAIRY_COW_MODEL_LAYER, CowEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.DARK_COW, DarkCowRenderer::new);
        EntityModelLayerRegistry.register(DARK_COW_MODEL_LAYER, CowEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.PINTO_COW, PintoCowRenderer::new);
        EntityModelLayerRegistry.register(PINTO_COW_MODEL_LAYER, CowEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.SUNSET_COW, SunsetCowRenderer::new);
        EntityModelLayerRegistry.register(SUNSET_COW_MODEL_LAYER, CowEntityModel::getTexturedModelData);
    }

    private static void registerSheeps(){
        EntityRendererRegistry.register(ModEntities.FLECKED_SHEEP, FleckedSheepRenderer::new);
        EntityModelLayerRegistry.register(FLECKED_SHEEP_MODEL_LAYER, SheepEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.register(FLECKED_SHEEP_FUR, SheepWoolEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.FUZZY_SHEEP, FuzzySheepRenderer::new);
        EntityModelLayerRegistry.register(FUZZY_SHEEP_MODEL_LAYER, SheepEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.register(FUZZY_SHEEP_FUR, SheepWoolEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.HORNED_SHEEP, HornedSheepRenderer::new);
        EntityModelLayerRegistry.register(HORNED_SHEEP_MODEL_LAYER, HornedSheepModel::getTexturedModelData);


        EntityRendererRegistry.register(ModEntities.INKY_SHEEP, InkySheepRenderer::new);
        EntityModelLayerRegistry.register(INKY_SHEEP_MODEL_LAYER, SheepEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.register(INKY_SHEEP_FUR, SheepWoolEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.LONG_NOSED_SHEEP, LongNosedSheepRenderer::new);
        EntityModelLayerRegistry.register(LONG_NOSED_SHEEP_MODEL_LAYER, SheepEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.register(LONG_NOSED_SHEEP_FUR, SheepWoolEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.PATCHED_SHEEP, PatchedSheepRenderer::new);
        EntityModelLayerRegistry.register(PATCHED_SHEEP_MODEL_LAYER, SheepEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.register(PATCHED_SHEEP_FUR, SheepWoolEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.ROCKY_SHEEP, RockySheepRenderer::new);
        EntityModelLayerRegistry.register(ROCKY_SHEEP_MODEL_LAYER, SheepEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.register(ROCKY_SHEEP_FUR, SheepWoolEntityModel::getTexturedModelData);
    }

    private static void registerChicken(){
        EntityRendererRegistry.register(ModEntities.CHICKEN1, Chicken1Renderer::new);
        EntityModelLayerRegistry.register(CHICKEN1_MODEL_LAYER, ChickenEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.CHICKEN2, Chicken2Renderer::new);
        EntityModelLayerRegistry.register(CHICKEN2_MODEL_LAYER, ChickenEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.CHICKEN3, Chicken3Renderer::new);
        EntityModelLayerRegistry.register(CHICKEN3_MODEL_LAYER, ChickenEntityModel::getTexturedModelData);
    }

    public static void appendToolTip(@NotNull List<Text> tooltip){
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;
        ItemStack helmet = player.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chestplate = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack leggings = player.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack boots = player.getEquippedStack(EquipmentSlot.FEET);
        tooltip.add(Text.of(""));
        tooltip.add(Text.of(Formatting.DARK_GREEN + I18n.translate("meadow.tooltip.fur_armor")));
        tooltip.add(Text.of((helmet != null && helmet.getItem() instanceof FurArmorItem ? Formatting.GREEN.toString() : Formatting.GRAY.toString()) + "- [" + ObjectRegistry.BEAR_FUR_HELMET.get().getName().getString() + "]"));
        tooltip.add(Text.of((chestplate != null && chestplate.getItem() instanceof FurArmorItem ? Formatting.GREEN.toString() : Formatting.GRAY.toString()) + "- [" + ObjectRegistry.BEAR_FUR_CHESTPLATE.get().getName().getString() + "]"));
        tooltip.add(Text.of((leggings != null && leggings.getItem() instanceof FurArmorItem ? Formatting.GREEN.toString() : Formatting.GRAY.toString()) + "- [" + ObjectRegistry.BEAR_FUR_LEGGINGS.get().getName().getString() + "]"));
        tooltip.add(Text.of((boots != null && boots.getItem() instanceof FurArmorItem ? Formatting.GREEN.toString() : Formatting.GRAY.toString()) + "- [" + ObjectRegistry.BEAR_FUR_BOOTS.get().getName().getString() + "]"));
        tooltip.add(Text.of(""));
        tooltip.add(Text.of(Formatting.GRAY + I18n.translate("meadow.tooltip.fur_armor2")));
        tooltip.add(Text.of(((helmet != null && helmet.getItem() instanceof FurArmorItem &&
                chestplate != null && chestplate.getItem() instanceof FurArmorItem &&
                leggings != null && leggings.getItem() instanceof FurArmorItem &&
                boots != null && boots.getItem() instanceof FurArmorItem) ? Formatting.DARK_GREEN.toString() : Formatting.GRAY.toString()) + I18n.translate("meadow.tooltip.fur_armor3")));
    }
}