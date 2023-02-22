package net.satisfyu.meadow.item.custom;

import net.minecraft.recipe.Ingredient;

public interface BowLevel {
    int getUses();

    float getAttackDamageBonus();

    int getEnchantmentValue();

    Ingredient getRepairIngredient();
}
