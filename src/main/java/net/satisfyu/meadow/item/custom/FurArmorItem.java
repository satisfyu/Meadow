package net.satisfyu.meadow.item.custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.satisfyu.meadow.item.ModItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FurArmorItem extends ArmorItem {
    public FurArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, @NotNull List<Text> tooltip, TooltipContext context) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;
        ItemStack helmet = player.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chestplate = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack leggings = player.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack boots = player.getEquippedStack(EquipmentSlot.FEET);
        tooltip.add(Text.of(""));
        tooltip.add(Text.of(Formatting.DARK_GREEN + I18n.translate("meadow.tooltip.fur_armor")));
        tooltip.add(Text.of((helmet != null && helmet.getItem() instanceof FurArmorItem ? Formatting.GREEN.toString() : Formatting.GRAY.toString()) + "- [" + ModItems.BEAR_FUR_HELMET.getName().getString() + "]"));
        tooltip.add(Text.of((chestplate != null && chestplate.getItem() instanceof FurArmorItem ? Formatting.GREEN.toString() : Formatting.GRAY.toString()) + "- [" + ModItems.BEAR_FUR_CHESTPLATE.getName().getString() + "]"));
        tooltip.add(Text.of((leggings != null && leggings.getItem() instanceof FurArmorItem ? Formatting.GREEN.toString() : Formatting.GRAY.toString()) + "- [" + ModItems.BEAR_FUR_LEGGINGS.getName().getString() + "]"));
        tooltip.add(Text.of((boots != null && boots.getItem() instanceof FurArmorItem ? Formatting.GREEN.toString() : Formatting.GRAY.toString()) + "- [" + ModItems.BEAR_FUR_BOOTS.getName().getString() + "]"));
        tooltip.add(Text.of(""));
        tooltip.add(Text.of(Formatting.GRAY + I18n.translate("meadow.tooltip.fur_armor2")));
        tooltip.add(Text.of(((helmet != null && helmet.getItem() instanceof FurArmorItem &&
                chestplate != null && chestplate.getItem() instanceof FurArmorItem &&
                leggings != null && leggings.getItem() instanceof FurArmorItem &&
                boots != null && boots.getItem() instanceof FurArmorItem) ? Formatting.DARK_GREEN.toString() : Formatting.GRAY.toString()) + I18n.translate("meadow.tooltip.fur_armor3")));
    }
}