package net.satisfyu.meadow.item;

import de.cristelknight.doapi.common.item.CustomArmorItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.satisfyu.meadow.client.MeadowClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class FurChest extends CustomArmorItem {
    public FurChest(ArmorMaterial material, Settings settings) {
        super(material, Type.CHESTPLATE, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, @NotNull List<Text> tooltip, TooltipContext context) {
        if(world != null && world.isClient()){
            MeadowClient.appendToolTip(tooltip);
        }
    }
}