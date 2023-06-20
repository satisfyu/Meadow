package net.satisfyu.meadow.entity.bear;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.AttackGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.satisfyu.meadow.registry.EntityRegistry;

public class BrownBearEntity extends PolarBearEntity {
    public BrownBearEntity(EntityType<? extends PolarBearEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public BrownBearEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return EntityRegistry.BROWN_BEAR.get().create(serverWorld);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new AttackGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, AnimalEntity.class, true, livingEntity -> {
            if (livingEntity instanceof BrownBearEntity) {
                return false;
            } else if (livingEntity instanceof PlayerEntity) {
                return true;
            } else {
                return livingEntity instanceof SheepEntity;
            }
        }));
    }
}
