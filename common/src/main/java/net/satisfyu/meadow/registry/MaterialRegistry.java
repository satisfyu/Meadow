package net.satisfyu.meadow.registry;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public class MaterialRegistry {

    public static final ArmorMaterial FUR_ARMOR = new ArmorMaterial() {


        @Override
        public int getDurability(ArmorItem.Type type) {
            return ArmorMaterials.CHAIN.getDurability(type);
        }

        @Override
        public int getProtection(ArmorItem.Type type) {
            return ArmorMaterials.CHAIN.getProtection(type);
        }

        @Override
        public int getEnchantability() {
            return ArmorMaterials.CHAIN.getEnchantability();
        }

        @Override
        public SoundEvent getEquipSound() {
            return ArmorMaterials.LEATHER.getEquipSound();
        }


        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(Items.RABBIT_HIDE);
        }


        @Override
        public String getName() {
            return "fur";
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

