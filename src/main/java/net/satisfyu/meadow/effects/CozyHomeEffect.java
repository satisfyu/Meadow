package net.satisfyu.meadow.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class CozyHomeEffect extends StatusEffect {
    public CozyHomeEffect() {
        super(
                StatusEffectCategory.BENEFICIAL,
                0x98D982);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.world.getTime() % 6 == 0) {
            if (entity instanceof PlayerEntity _ent)
                _ent.heal((float) 1);
        }
    }
}