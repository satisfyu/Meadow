package net.satisfyu.meadow.mixin.variant;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public abstract class MobVariantMixin {

    @Inject(
            method = "defineSynchedData",
            at = @At("RETURN")
    )
    protected void onDefineSynchedData(CallbackInfo ci) {

    }

    /*
    @Inject(
            method = "addAdditionalSaveData",
            at = @At("RETURN")
    )
    protected void onAddAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {

    }

    @Inject(
            method = "readAdditionalSaveData",
            at = @At("RETURN")
    )
    protected void onReadAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {

    }
     */

    @Inject(
            method = "finalizeSpawn",
            at = @At("RETURN")
    )
    protected void onFinalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, SpawnGroupData spawnGroupData, CompoundTag compoundTag, CallbackInfoReturnable<SpawnGroupData> cir) {

    }

    @Inject(
            method = "tick",
            at = @At("RETURN")
    )
    protected void onTick(CallbackInfo ci) {

    }
}