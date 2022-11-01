package net.satisfyu.meadow.item.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.satisfyu.meadow.item.ModItems;

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
            return Ingredient.ofItems(ModItems.BEAR_FUR);
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

