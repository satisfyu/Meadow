package net.satisfyu.meadow.mixin.variant;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.ServerLevelAccessor;
import net.satisfyu.meadow.entity.cow.CowVar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Cow.class)
public abstract class CowVariantMixin extends MobVariantMixin {


    @Override
    protected void onTick(CallbackInfo ci) {
        if (CowVar.getVariant(getCow()).equals(CowVar.ALBINO) && !getCow().isBaby() && getCow().level().isDay() && getCow().level().canSeeSky(getCow().blockPosition())) {
            getCow().hurt(getCow().level().damageSources().inFire(), 0.5f);
        }
    }

    @Override
    protected void onFinalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, SpawnGroupData spawnGroupData, CompoundTag compoundTag, CallbackInfoReturnable<SpawnGroupData> cir) {
        CowVar.setVariant(getCow(), CowVar.getRandomVariant(serverLevelAccessor, getCow().blockPosition(), mobSpawnType.equals(MobSpawnType.SPAWN_EGG)));
    }

    @Inject(
            method = "getBreedOffspring(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/animal/Cow;",
            at = @At("HEAD"),
            cancellable = true)
    protected void onGetBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob, CallbackInfoReturnable<Cow> cir) {
        Cow cow = EntityType.COW.create(serverLevel);
        if(cow == null) return;

        RandomSource random = serverLevel.getRandom();
        CowVar var = CowVar.getVariant(getCow());
        if(random.nextBoolean() && ageableMob instanceof Cow varCow){
            var = CowVar.getVariant(varCow);
        }
        CowVar.setVariant(cow, var);
        cir.setReturnValue(cow);
    }

    @Unique
    private Cow getCow(){
        return (Cow) (Object)this;
    }
}