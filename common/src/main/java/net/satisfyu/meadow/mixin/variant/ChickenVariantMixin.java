package net.satisfyu.meadow.mixin.variant;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.ServerLevelAccessor;
import net.satisfyu.meadow.entity.chicken.ChickenVar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Chicken.class)
public abstract class ChickenVariantMixin extends MobVariantMixin {

    @Override
    protected void onFinalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, SpawnGroupData spawnGroupData, CompoundTag compoundTag, CallbackInfoReturnable<SpawnGroupData> cir) {
        ChickenVar.setVariant(getChicken(), ChickenVar.getRandomVariant(serverLevelAccessor, getChicken().blockPosition(), mobSpawnType.equals(MobSpawnType.SPAWN_EGG)));
    }

    @Inject(
            method = "getBreedOffspring(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/animal/Chicken;",
            at = @At("HEAD"),
            cancellable = true)
    protected void onGetBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob, CallbackInfoReturnable<Chicken> cir) {
        Chicken chicken = EntityType.CHICKEN.create(serverLevel);
        if(chicken == null) return;

        RandomSource random = serverLevel.getRandom();
        ChickenVar var = ChickenVar.getVariant(getChicken());
        if(random.nextBoolean() && ageableMob instanceof Chicken varChicken){
            var = ChickenVar.getVariant(varChicken);
        }
        ChickenVar.setVariant(chicken, var);
        cir.setReturnValue(chicken);
    }

    /*
    @Override
    protected void onDefineSynchedData(CallbackInfo ci) {
        getChicken().getEntityData().define(ChickenVar.DATA_ID_TYPE_VARIANT, 0);
    }

    @Override
    protected void onAddAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        compoundTag.putInt("Variant", ChickenVar.getTypeVariant(getChicken()));
    }

    @Override
    protected void onReadAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        ChickenVar.setTypeVariant(getChicken(), compoundTag.getInt("Variant"));
    }
    */


    @Unique
    private Chicken getChicken(){
        return (Chicken) (Object)this;
    }
}