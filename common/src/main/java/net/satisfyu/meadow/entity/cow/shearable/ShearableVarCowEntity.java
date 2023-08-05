package net.satisfyu.meadow.entity.cow.shearable;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.satisfyu.meadow.registry.EntityRegistry;
import net.satisfyu.meadow.registry.TagRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShearableVarCowEntity extends Animal implements Shearable, VariantHolder<ShearableCowVar> {
    private static final EntityDataAccessor<Boolean> IS_SHEARED = SynchedEntityData.defineId(ShearableVarCowEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(ShearableVarCowEntity.class, EntityDataSerializers.INT);

    private static final Map<ShearableCowVar, TagKey<Biome>> SPAWNS = Util.make(new HashMap<>(), map -> {
        map.put(ShearableCowVar.HIGHLAND, TagRegistry.IS_MEADOW);
        map.put(ShearableCowVar.UMBRA, TagRegistry.SPAWNS_DARK_COW);
        map.put(ShearableCowVar.WARPED, TagRegistry.SPAWNS_WARPED_COW);
    });

    private int eatGrassTimer;
    private EatBlockGoal eatGrassGoal;

    public ShearableVarCowEntity(EntityType<ShearableVarCowEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public InteractionResult mobInteract(Player player2, InteractionHand hand) {
        ItemStack itemStack = player2.getItemInHand(hand);
        if (itemStack.is(Items.SHEARS)) {
            if (!this.level().isClientSide && this.readyForShearing()) {
                this.shear(SoundSource.PLAYERS);
                this.gameEvent(GameEvent.SHEAR, player2);
                itemStack.hurtAndBreak(1, player2, player -> player.broadcastBreakEvent(hand));
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.CONSUME;
        }
        return super.mobInteract(player2, hand);
    }

    @Override
    public void shear(SoundSource shearedSoundCategory) {
        this.level().playSound(null, this, SoundEvents.SHEEP_SHEAR, shearedSoundCategory, 1.0f, 1.0f);
        this.setSheared(true);
        int i = 1 + this.random.nextInt(3);
        for (int j = 0; j < i; ++j) {
            ItemEntity itemEntity = this.spawnAtLocation(Blocks.BLACK_WOOL, 1);
            if (itemEntity == null) continue;
            itemEntity.setDeltaMovement(itemEntity.getDeltaMovement().add((this.random.nextFloat() - this.random.nextFloat()) * 0.1f, this.random.nextFloat() * 0.05f, (this.random.nextFloat() - this.random.nextFloat()) * 0.1f));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("Sheared", this.isSheared());
        nbt.putInt("Variant", getTypeVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        setSheared(nbt.getBoolean("Sheared"));
        setTypeVariant(nbt.getInt("Variant"));
    }

    @Override
    public void ate() {
        super.ate();
        this.setSheared(false);
        if (this.isBaby()) {
            this.ageUp(60);
        }
    }

    @Override
    protected void customServerAiStep() {
        this.eatGrassTimer = this.eatGrassGoal.getEatAnimationTick();
        super.customServerAiStep();
    }

    @Override
    public void aiStep() {
        if (this.level().isClientSide) {
            this.eatGrassTimer = Math.max(0, this.eatGrassTimer - 1);
        }
        super.aiStep();
    }

    public void setSheared(boolean sheared) {
        if (sheared) {
            this.entityData.set(IS_SHEARED, true);
        } else {
            this.entityData.set(IS_SHEARED, false);
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(IS_SHEARED, false);
        entityData.define(DATA_ID_TYPE_VARIANT, 0);
    }

    public boolean isSheared() {
        return entityData.get(IS_SHEARED);
    }


    @Override
    public boolean readyForShearing() {
        return this.isAlive() && !this.isSheared() && !this.isBaby();
    }

    @Override
    public void handleEntityEvent(byte status) {
        if (status == EntityEvent.EAT_GRASS) {
            this.eatGrassTimer = 40;
        } else {
            super.handleEntityEvent(status);
        }
    }

    public float getNeckAngle(float delta) {
        if (this.eatGrassTimer <= 0) {
            return 0.0f;
        }
        if (this.eatGrassTimer >= 4 && this.eatGrassTimer <= 36) {
            return 1.0f;
        }
        if (this.eatGrassTimer < 4) {
            return ((float) this.eatGrassTimer - delta) / 4.0f;
        }
        return -((float) (this.eatGrassTimer - 40) - delta) / 4.0f;
    }

    public float getHeadAngle(float delta) {
        if (this.eatGrassTimer > 4 && this.eatGrassTimer <= 36) {
            float f = ((float) (this.eatGrassTimer - 4) - delta) / 32.0f;
            return 0.62831855f + 0.21991149f * Mth.sin(f * 28.7f);
        }
        if (this.eatGrassTimer > 0) {
            return 0.62831855f;
        }
        return this.getXRot() * ((float) Math.PI / 180);
    }



    @Nullable
    @Override
    public ShearableVarCowEntity getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        ShearableVarCowEntity cow = EntityRegistry.SHEARABLE_MEADOW_VAR_COW.get().create(serverLevel);
        if(cow == null) return null;

        RandomSource random = serverLevel.getRandom();
        ShearableCowVar var = this.getVariant();
        if(random.nextBoolean() && ageableMob instanceof ShearableVarCowEntity varCow){
            var = varCow.getVariant();
        }
        cow.setVariant(var);
        return cow;
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag) {

        ShearableCowVar variant;
        if (spawnGroupData instanceof ShearableVarCowGroupData data) {
            variant = data.variant;
        } else {
            variant = getRandomVariant(serverLevelAccessor, blockPosition(), mobSpawnType.equals(MobSpawnType.SPAWN_EGG));
            spawnGroupData = new ShearableVarCowGroupData(variant);
        }

        setVariant(variant);
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);
    }



    private static ShearableCowVar getRandomVariant(LevelAccessor levelAccessor, BlockPos blockPos, boolean spawnEgg) {
        Holder<Biome> holder = levelAccessor.getBiome(blockPos);
        RandomSource random = levelAccessor.getRandom();
        List<ShearableCowVar> possibleVars = getShearableCowVariantsInBiome(holder);
        int size = possibleVars.size();
        if(size == 0){
            if(!spawnEgg){
                if(holder.is(BiomeTags.IS_NETHER)) return ShearableCowVar.WARPED;
                List<ShearableCowVar> list = new java.util.ArrayList<>(List.of(ShearableCowVar.values()));
                list.remove(ShearableCowVar.WARPED);
                return Util.getRandom(list, random);
            }
            return Util.getRandom(ShearableCowVar.values(), random);
        }

        return possibleVars.get(levelAccessor.getRandom().nextInt(size));
    }


    public static List<ShearableCowVar> getShearableCowVariantsInBiome(Holder<Biome> biome) {
        return SPAWNS.keySet().stream()
                .filter(ShearableCowVariant -> biome.is(SPAWNS.get(ShearableCowVariant)))
                .collect(Collectors.toList());
    }

    public void setVariant(ShearableCowVar variant) {
        setTypeVariant(variant.getId() & 255 | this.getTypeVariant() & -256);
    }
    @Override
    public ShearableCowVar getVariant() {
        return ShearableCowVar.byId(getTypeVariant() & 255);
    }
    private void setTypeVariant(int i) {
        entityData.set(DATA_ID_TYPE_VARIANT, i);
    }
    private int getTypeVariant() {
        return entityData.get(DATA_ID_TYPE_VARIANT);
    }

    public static class ShearableVarCowGroupData extends AgeableMob.AgeableMobGroupData {
        public final ShearableCowVar variant;
        public ShearableVarCowGroupData(ShearableCowVar variant) {
            super(true);
            this.variant = variant;
        }
    }

    // COW //
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, Ingredient.of(new ItemLike[]{Items.WHEAT}), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        //
        this.eatGrassGoal = new EatBlockGoal(this);
        this.goalSelector.addGoal(5, this.eatGrassGoal);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.COW_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.COW_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COW_DEATH;
    }

    protected void playStepSound(BlockPos blockPos, BlockState blockState) {
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    protected float getStandingEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return this.isBaby() ? entityDimensions.height * 0.95F : 1.3F;
    }
}
