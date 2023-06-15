package net.satisfyu.meadow.item;

import net.minecraft.recipe.Ingredient;

public interface BowLevel {
    int getUses();

    float getAttackDamageBonus();

    int getEnchantmentValue();

    Ingredient getRepairIngredient();
}
