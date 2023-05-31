package net.satisfyu.meadow.item.custom;


import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;
import net.satisfyu.meadow.registry.ObjectRegistry;


import java.util.function.Supplier;

public enum BowUtil implements BowLevel {
    HUNTING(768, 2.0f, 1, () -> {
        return Ingredient.ofItems(ObjectRegistry.CORD.get());
    });


    private final int uses;
    private final float damage;
    private final int enchantmentValue;
    private final Lazy<Ingredient> repairIngredient;

    private BowUtil(int uses, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.uses = uses;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new Lazy<Ingredient>(repairIngredient);
    }

    public int getUses() {
        return this.uses;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
