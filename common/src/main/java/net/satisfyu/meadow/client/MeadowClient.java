package net.satisfyu.meadow.client;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.SheepFurModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.client.render.BigFlowerPotBlockEntityRenderer;
import net.satisfyu.meadow.client.render.CheeseRackBlockEntityRenderer;
import net.satisfyu.meadow.client.render.FlowerBoxBlockEntityRenderer;
import net.satisfyu.meadow.client.render.WheelBarrowBlockEntityRenderer;
import net.satisfyu.meadow.client.screen.CheeseFormGui;
import net.satisfyu.meadow.client.screen.CookingCauldronGui;
import net.satisfyu.meadow.client.screen.FondueGui;
import net.satisfyu.meadow.client.screen.WoodcutterGui;
import net.satisfyu.meadow.entity.bear.BrownBearEntityModel;
import net.satisfyu.meadow.entity.bear.BrownBearEntityRenderer;
import net.satisfyu.meadow.entity.buffalo.WaterBuffaloEntityModel;
import net.satisfyu.meadow.entity.buffalo.WaterBuffaloEntityRenderer;
import net.satisfyu.meadow.entity.chicken.MeadowChickenRenderer;
import net.satisfyu.meadow.entity.cow.albino_cow.AlbinoCowRenderer;
import net.satisfyu.meadow.entity.cow.cookie_cow.CookieCowRenderer;
import net.satisfyu.meadow.entity.cow.cream_cow.CreamCowRenderer;
import net.satisfyu.meadow.entity.cow.dairy_cow.DairyCowRenderer;
import net.satisfyu.meadow.entity.cow.dark_cow.DarkCowRenderer;
import net.satisfyu.meadow.entity.cow.pinto_cow.PintoCowRenderer;
import net.satisfyu.meadow.entity.cow.shearable.WoolyCowModel;
import net.satisfyu.meadow.entity.cow.shearable.highland_cattle.HighlandCattleRenderer;
import net.satisfyu.meadow.entity.cow.shearable.umbra.UmbraCowRenderer;
import net.satisfyu.meadow.entity.cow.shearable.warped.WarpedCowRenderer;
import net.satisfyu.meadow.entity.cow.sunset_cow.SunsetCowRenderer;
import net.satisfyu.meadow.entity.sheep.flecked.FleckedSheepRenderer;
import net.satisfyu.meadow.entity.sheep.fuzzy.FuzzySheepRenderer;
import net.satisfyu.meadow.entity.sheep.horned.HornedSheepModel;
import net.satisfyu.meadow.entity.sheep.horned.HornedSheepRenderer;
import net.satisfyu.meadow.entity.sheep.inky.InkySheepRenderer;
import net.satisfyu.meadow.entity.sheep.long_nosed.LongNosedSheepRenderer;
import net.satisfyu.meadow.entity.sheep.patched.PatchedSheepRenderer;
import net.satisfyu.meadow.entity.sheep.rocky.RockySheepRenderer;
import net.satisfyu.meadow.item.FurBoots;
import net.satisfyu.meadow.item.FurChest;
import net.satisfyu.meadow.item.FurHead;
import net.satisfyu.meadow.item.FurLegs;
import net.satisfyu.meadow.registry.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MeadowClient {

    public static final ModelLayerLocation FLECKED_SHEEP_FUR = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "flecked_sheep"), "fur");
    public static final ModelLayerLocation FLECKED_SHEEP_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "flecked_sheep"), "main");

    public static final ModelLayerLocation FUZZY_SHEEP_FUR = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "fuzzy_sheep"), "fur");
    public static final ModelLayerLocation FUZZY_SHEEP_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "fuzzy_sheep"), "main");

    public static final ModelLayerLocation HORNED_SHEEP_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "horned_sheep"), "main");

    public static final ModelLayerLocation BROWN_BEAR_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "brown_bear"), "main");

    public static final ModelLayerLocation INKY_SHEEP_FUR = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "inky_sheep"), "fur");
    public static final ModelLayerLocation INKY_SHEEP_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "inky_sheep"), "main");

    public static final ModelLayerLocation LONG_NOSED_SHEEP_FUR = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "long_nosed_sheep"), "fur");
    public static final ModelLayerLocation LONG_NOSED_SHEEP_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "long_nosed_sheep"), "main");

    public static final ModelLayerLocation PATCHED_SHEEP_FUR = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "patched_sheep"), "fur");
    public static final ModelLayerLocation PATCHED_SHEEP_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "patched_sheep"), "main");

    public static final ModelLayerLocation ROCKY_SHEEP_FUR = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "rocky_sheep"), "fur");
    public static final ModelLayerLocation ROCKY_SHEEP_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "rocky_sheep"), "main");

    public static final ModelLayerLocation UMBRA_COW_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "umbra_cow"), "head");
    public static final ModelLayerLocation WARPED_COW_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "warped_cow"), "head");

    public static final ModelLayerLocation HIGHLAND_CATTLE_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "highland_cattle"), "head");

    public static final ModelLayerLocation ALBINO_COW_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "albino_cow"), "head");
    public static final ModelLayerLocation WATER_BUFFALO_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "water_buffalo"), "head");

    public static final ModelLayerLocation COOKIE_COW_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "cookie_cow"), "head");

    public static final ModelLayerLocation CREAM_COW_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "cream_cow"), "head");

    public static final ModelLayerLocation DAIRY_COW_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "dairy_cow"), "head");

    public static final ModelLayerLocation DARK_COW_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "dark_cow"), "head");

    public static final ModelLayerLocation PINTO_COW_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "pinto_cow"), "head");

    public static final ModelLayerLocation SUNSET_COW_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "sunset_cow"), "head");

    public static final ModelLayerLocation MEADOW_CHICKEN_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(Meadow.MOD_ID, "meadow_chicken"), "main");

    public static void preInitClient() {
        registerEntityRenderers();
        registerEntityModelLayer();
        registerCows();
        registerEntityModelLayers();
        registerSheeps();
        registerChicken();
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
        EntityRendererRegistry.register(EntityRegistry.BROWN_BEAR, BrownBearEntityRenderer::new);
    }

    public static void registerEntityModelLayers() {
        EntityModelLayerRegistry.register(BROWN_BEAR_MODEL_LAYER, BrownBearEntityModel::createBodyLayer);
    }


    private static void registerCows() {
        EntityRendererRegistry.register(EntityRegistry.UMBRA_COW, UmbraCowRenderer::new);
        EntityModelLayerRegistry.register(UMBRA_COW_MODEL_LAYER, WoolyCowModel::createBodyLayer);

        EntityRendererRegistry.register(EntityRegistry.WARPED_COW, WarpedCowRenderer::new);
        EntityModelLayerRegistry.register(WARPED_COW_MODEL_LAYER, WoolyCowModel::createBodyLayer);

        EntityRendererRegistry.register(EntityRegistry.HIGHLAND_CATTLE, HighlandCattleRenderer::new);
        EntityModelLayerRegistry.register(HIGHLAND_CATTLE_MODEL_LAYER, WoolyCowModel::createBodyLayer);

        EntityRendererRegistry.register(EntityRegistry.ALBINO_COW, AlbinoCowRenderer::new);
        EntityModelLayerRegistry.register(ALBINO_COW_MODEL_LAYER, CowModel::createBodyLayer);

        EntityRendererRegistry.register(EntityRegistry.WATER_BUFFALO, WaterBuffaloEntityRenderer::new);
        EntityModelLayerRegistry.register(WATER_BUFFALO_MODEL_LAYER, WaterBuffaloEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(EntityRegistry.COOKIE_COW, CookieCowRenderer::new);
        EntityModelLayerRegistry.register(COOKIE_COW_MODEL_LAYER, CowModel::createBodyLayer);

        EntityRendererRegistry.register(EntityRegistry.CREAM_COW, CreamCowRenderer::new);
        EntityModelLayerRegistry.register(CREAM_COW_MODEL_LAYER, CowModel::createBodyLayer);

        EntityRendererRegistry.register(EntityRegistry.DAIRY_COW, DairyCowRenderer::new);
        EntityModelLayerRegistry.register(DAIRY_COW_MODEL_LAYER, CowModel::createBodyLayer);

        EntityRendererRegistry.register(EntityRegistry.DARK_COW, DarkCowRenderer::new);
        EntityModelLayerRegistry.register(DARK_COW_MODEL_LAYER, CowModel::createBodyLayer);

        EntityRendererRegistry.register(EntityRegistry.PINTO_COW, PintoCowRenderer::new);
        EntityModelLayerRegistry.register(PINTO_COW_MODEL_LAYER, CowModel::createBodyLayer);

        EntityRendererRegistry.register(EntityRegistry.SUNSET_COW, SunsetCowRenderer::new);
        EntityModelLayerRegistry.register(SUNSET_COW_MODEL_LAYER, CowModel::createBodyLayer);
    }

    private static void registerSheeps() {
        EntityRendererRegistry.register(EntityRegistry.FLECKED_SHEEP, FleckedSheepRenderer::new);
        EntityModelLayerRegistry.register(FLECKED_SHEEP_MODEL_LAYER, SheepModel::createBodyLayer);
        EntityModelLayerRegistry.register(FLECKED_SHEEP_FUR, SheepFurModel::createFurLayer);

        EntityRendererRegistry.register(EntityRegistry.FUZZY_SHEEP, FuzzySheepRenderer::new);
        EntityModelLayerRegistry.register(FUZZY_SHEEP_MODEL_LAYER, SheepModel::createBodyLayer);
        EntityModelLayerRegistry.register(FUZZY_SHEEP_FUR, SheepFurModel::createFurLayer);

        EntityRendererRegistry.register(EntityRegistry.HORNED_SHEEP, HornedSheepRenderer::new);
        EntityModelLayerRegistry.register(HORNED_SHEEP_MODEL_LAYER, HornedSheepModel::createBodyLayer);


        EntityRendererRegistry.register(EntityRegistry.INKY_SHEEP, InkySheepRenderer::new);
        EntityModelLayerRegistry.register(INKY_SHEEP_MODEL_LAYER, SheepModel::createBodyLayer);
        EntityModelLayerRegistry.register(INKY_SHEEP_FUR, SheepFurModel::createFurLayer);

        EntityRendererRegistry.register(EntityRegistry.LONG_NOSED_SHEEP, LongNosedSheepRenderer::new);
        EntityModelLayerRegistry.register(LONG_NOSED_SHEEP_MODEL_LAYER, SheepModel::createBodyLayer);
        EntityModelLayerRegistry.register(LONG_NOSED_SHEEP_FUR, SheepFurModel::createFurLayer);

        EntityRendererRegistry.register(EntityRegistry.PATCHED_SHEEP, PatchedSheepRenderer::new);
        EntityModelLayerRegistry.register(PATCHED_SHEEP_MODEL_LAYER, SheepModel::createBodyLayer);
        EntityModelLayerRegistry.register(PATCHED_SHEEP_FUR, SheepFurModel::createFurLayer);

        EntityRendererRegistry.register(EntityRegistry.ROCKY_SHEEP, RockySheepRenderer::new);
        EntityModelLayerRegistry.register(ROCKY_SHEEP_MODEL_LAYER, SheepModel::createBodyLayer);
        EntityModelLayerRegistry.register(ROCKY_SHEEP_FUR, SheepFurModel::createFurLayer);
    }

    private static void registerChicken() {
        EntityRendererRegistry.register(EntityRegistry.MEADOW_CHICKEN, MeadowChickenRenderer::new);
        EntityModelLayerRegistry.register(MEADOW_CHICKEN_MODEL_LAYER, ChickenModel::createBodyLayer);

    }

    public static void registerEntityModelLayer() {
        ArmorRegistry.registerArmorModelLayers();
    }


    public static void appendToolTip(@NotNull List<Component> tooltip) {
        Player player = Minecraft.getInstance().player;
        if (player == null) return;
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        tooltip.add(Component.nullToEmpty(""));
        tooltip.add(Component.nullToEmpty(ChatFormatting.DARK_GREEN + I18n.get("meadow.tooltip.fur_armor")));
        tooltip.add(Component.nullToEmpty((helmet != null && helmet.getItem() instanceof FurHead ? ChatFormatting.GREEN.toString() : ChatFormatting.GRAY.toString()) + "- [" + ObjectRegistry.FUR_HELMET.get().getDescription().getString() + "]"));
        tooltip.add(Component.nullToEmpty((chestplate != null && chestplate.getItem() instanceof FurChest ? ChatFormatting.GREEN.toString() : ChatFormatting.GRAY.toString()) + "- [" + ObjectRegistry.FUR_CHESTPLATE.get().getDescription().getString() + "]"));
        tooltip.add(Component.nullToEmpty((leggings != null && leggings.getItem() instanceof FurLegs ? ChatFormatting.GREEN.toString() : ChatFormatting.GRAY.toString()) + "- [" + ObjectRegistry.FUR_LEGGINGS.get().getDescription().getString() + "]"));
        tooltip.add(Component.nullToEmpty((boots != null && boots.getItem() instanceof FurBoots ? ChatFormatting.GREEN.toString() : ChatFormatting.GRAY.toString()) + "- [" + ObjectRegistry.FUR_BOOTS.get().getDescription().getString() + "]"));
        tooltip.add(Component.nullToEmpty(""));
        tooltip.add(Component.nullToEmpty(ChatFormatting.GRAY + I18n.get("meadow.tooltip.fur_armor2")));
        tooltip.add(Component.nullToEmpty(((helmet != null && helmet.getItem() instanceof FurHead &&
                chestplate != null && chestplate.getItem() instanceof FurChest &&
                leggings != null && leggings.getItem() instanceof FurLegs &&
                boots != null && boots.getItem() instanceof FurBoots) ? ChatFormatting.DARK_GREEN.toString() : ChatFormatting.GRAY.toString()) + I18n.get("meadow.tooltip.fur_armor3")));
    }
}