package net.satisfyu.meadow.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.OcelotAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.satisfyu.meadow.registry.EntityRegistry;

public class BrownBear extends PolarBear {
    public BrownBear(EntityType<? extends PolarBear> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public BrownBear getBreedOffspring(ServerLevel serverWorld, AgeableMob passiveEntity) {
        return EntityRegistry.BROWN_BEAR.get().create(serverWorld);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new OcelotAttackGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Animal.class, true, livingEntity -> {
            if (livingEntity instanceof BrownBear) {
                return false;
            } else if (livingEntity instanceof Player) {
                return true;
            } else {
                return livingEntity instanceof Sheep;
            }
        }));
    }
}
