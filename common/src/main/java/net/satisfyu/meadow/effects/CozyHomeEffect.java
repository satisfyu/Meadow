package net.satisfyu.meadow.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class CozyHomeEffect extends MobEffect {
    public CozyHomeEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x98D982);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().getGameTime() % 6 == 0) {
            if (entity instanceof Player player) {
                player.heal(0.77f);
            }
        }
    }
}
