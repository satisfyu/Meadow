package net.satisfyu.meadow.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.satisfyu.meadow.registry.ObjectRegistry;

public class MeadowMaterials {

    public static final ArmorMaterial BEAR_FUR_ARMOR = new ArmorMaterial() {
        @Override
        public int getDurability(EquipmentSlot slot) {
            return ArmorMaterials.CHAIN.getDurability(slot);
        }

        @Override
        public int getProtectionAmount(EquipmentSlot slot) {
            return ArmorMaterials.IRON.getProtectionAmount(slot);
        }

        @Override
        public int getEnchantability() {
            return ArmorMaterials.LEATHER.getEnchantability();
        }

        @Override
        public SoundEvent getEquipSound() {
            return ArmorMaterials.LEATHER.getEquipSound();
        }


        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(ObjectRegistry.BEAR_FUR.get());
        }


        @Override
        public String getName() {
            return "bear_fur";
        }

        @Override
        public float getToughness() {
            return ArmorMaterials.IRON.getToughness();
        }

        @Override
        public float getKnockbackResistance() {
            return ArmorMaterials.NETHERITE.getKnockbackResistance();
        }
    };
}

