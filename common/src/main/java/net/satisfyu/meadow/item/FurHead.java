package net.satisfyu.meadow.item;

import de.cristelknight.doapi.common.item.CustomHatItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.util.MeadowIdentifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class FurHead extends CustomHatItem {
    public FurHead(ArmorMaterial material, Settings settings) {
        super(material, EquipmentSlot.HEAD, settings);
    }

    @Override
    public Identifier getTexture() {
        return new MeadowIdentifier("textures/models/armor/fur.png");
    }

    @Override
    public Float getOffset() {
        return -1.9f;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, @NotNull List<Text> tooltip, TooltipContext context) {
        if(world != null && world.isClient()){
            MeadowClient.appendToolTip(tooltip);
        }
    }
}
