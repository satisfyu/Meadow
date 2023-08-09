package net.satisfyu.meadow.registry;

import de.cristelknight.doapi.DoApiExpectPlatform;
import de.cristelknight.doapi.terraform.boat.TerraformBoatType;
import de.cristelknight.doapi.terraform.boat.item.TerraformBoatItemHelper;
import de.cristelknight.doapi.terraform.sign.TerraformSignHelper;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;
import net.satisfyu.meadow.util.MeadowIdentifier;

public class MeadowWoodRegistry {
    public static ResourceLocation PINE_BOAT_TYPE = new MeadowIdentifier("pine");

    public static final ResourceLocation PINE_SIGN_TEXTURE = new MeadowIdentifier("entity/signs/pine");
    public static final RegistrySupplier<Block> PINE_SIGN = ObjectRegistry.registerBlockWithoutItem("pine_sign", () -> TerraformSignHelper.getSign(PINE_SIGN_TEXTURE));
    public static final RegistrySupplier<Block> PINE_WALL_SIGN = ObjectRegistry.registerBlockWithoutItem("pine_wall_sign", () -> TerraformSignHelper.getWallSign(PINE_SIGN_TEXTURE));
    public static final RegistrySupplier<Item> PINE_SIGN_ITEM = ObjectRegistry.registerItem("pine_sign", () -> new SignItem(ObjectRegistry.getSettings().stacksTo(16), PINE_SIGN.get(), PINE_WALL_SIGN.get()));


    public static final ResourceLocation PINE_HANGING_SIGN_TEXTURE = new MeadowIdentifier("entity/signs/hanging/pine");
    public static final ResourceLocation PINE_HANGING_SIGN_GUI_TEXTURE = new MeadowIdentifier("textures/gui/hanging_signs/pine");

    public static final RegistrySupplier<Block> PINE_HANGING_SIGN = ObjectRegistry.registerBlockWithoutItem("pine_hanging_sign", () -> TerraformSignHelper.getHangingSign(PINE_HANGING_SIGN_TEXTURE, PINE_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Block> PINE_WALL_HANGING_SIGN = ObjectRegistry.registerBlockWithoutItem("pine_wall_hanging_sign", () -> TerraformSignHelper.getWallHangingSign(PINE_HANGING_SIGN_TEXTURE, PINE_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Item> PINE_HANGING_SIGN_ITEM = ObjectRegistry.registerItem("pine_hanging_sign", () -> new HangingSignItem(PINE_HANGING_SIGN.get(), PINE_WALL_HANGING_SIGN.get(), ObjectRegistry.getSettings().stacksTo(16)));


    public static RegistrySupplier<Item> PINE_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "pine_boat", PINE_BOAT_TYPE, false);
    public static RegistrySupplier<Item> PINE_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "pine_chest_boat", PINE_BOAT_TYPE, true);
    public static void init() {
        DoApiExpectPlatform.registerBoatType(PINE_BOAT_TYPE, new TerraformBoatType.Builder().item(PINE_BOAT).chestItem(PINE_CHEST_BOAT).build());
    }
}
