package net.satisfy.meadow.item.armor;

import de.cristelknight.doapi.common.item.CustomArmorItem;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.satisfy.meadow.registry.ArmorRegistry;

import java.util.List;


public class FurChest extends CustomArmorItem {


    public FurChest(Holder<ArmorMaterial> holder, Properties properties) {
        super(holder, Type.CHESTPLATE, properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        ArmorRegistry.appendToolTip(list);
    }
}