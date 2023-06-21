package net.satisfyu.meadow.item;

import de.cristelknight.doapi.item.CustomArmorItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;


public class FurBoots extends CustomArmorItem {
    public FurBoots(ArmorMaterial material, Settings settings) {
        super(material, EquipmentSlot.FEET, settings);
    }

}
