package net.satisfyu.meadow.item;

import de.cristelknight.doapi.item.CustomArmorItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;


public class FurLegs extends CustomArmorItem {
    public FurLegs(ArmorMaterial material, Settings settings) {
        super(material, EquipmentSlot.LEGS, settings);
    }

}
