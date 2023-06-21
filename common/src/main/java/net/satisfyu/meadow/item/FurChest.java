package net.satisfyu.meadow.item;

import de.cristelknight.doapi.item.CustomArmorItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;


public class FurChest extends CustomArmorItem {
    public FurChest(ArmorMaterial material, Settings settings) {
        super(material, EquipmentSlot.CHEST, settings);
    }

}
