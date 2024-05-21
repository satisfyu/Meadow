package net.satisfy.meadow.registry;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class ArmorMaterialRegistry {

    public static final ArmorMaterial FUR_ARMOR = new ArmorMaterial() {


        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return ArmorMaterials.CHAIN.getDurabilityForType(type);
        }

        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return ArmorMaterials.CHAIN.getDefenseForType(type);
        }

        @Override
        public int getEnchantmentValue() {
            return ArmorMaterials.CHAIN.getEnchantmentValue();
        }

        @Override
        public @NotNull SoundEvent getEquipSound() {
            return SoundRegistry.FUR.get();
        }

        @Override
        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.of(Items.RABBIT_HIDE);
        }

        @Override
        public @NotNull String getName() {
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

