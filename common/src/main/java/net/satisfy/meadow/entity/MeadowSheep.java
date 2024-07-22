package net.satisfy.meadow.entity;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.satisfy.meadow.client.render.entity.SheepColor;
import net.satisfy.meadow.registry.EntityRegistry;
import net.satisfy.meadow.registry.ObjectRegistry;
import net.satisfy.meadow.util.MeadowIdentifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import static net.satisfy.meadow.registry.ObjectRegistry.*;

public class MeadowSheep extends Animal implements Shearable {
    private static final EntityDataAccessor<Byte> DATA_WOOL_TYPE = SynchedEntityData.defineId(MeadowSheep.class, EntityDataSerializers.BYTE);
    private static final Map<Byte, Block> WOOL_TYPES = Util.make(Maps.newHashMap(), map -> {
        map.put((byte) 0, FLECKED_WOOL.get());
        map.put((byte) 1, PATCHED_WOOL.get());
        map.put((byte) 2, ROCKY_WOOL.get());
        map.put((byte) 3, INKY_WOOL.get());
    });
    private EatBlockGoal eatBlockGoal;
    private int eatAnimationTick;
    private SheepTexture sheepTexture = SheepTexture.FLECKED; 

    public enum SheepTexture {
        FLECKED, PATCHED, ROCKY, INKY
    }

    public void setSheepTexture(SheepTexture texture) {
        this.sheepTexture = texture;
        System.out.println("Texture set to: " + texture);
    }


    public SheepTexture getSheepTexture() {
        return sheepTexture;
    }


    public MeadowSheep(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.setWoolType((byte) this.random.nextInt(4));
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0).add(Attributes.MOVEMENT_SPEED, 0.23000000417232513);
    }

    protected void registerGoals() {
        this.eatBlockGoal = new EatBlockGoal(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1));
        this.goalSelector.addGoal(5, this.eatBlockGoal);
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    public void customServerAiStep() {
        if (this.eatBlockGoal == null) {
            this.eatBlockGoal = new EatBlockGoal(this);
        }
        super.customServerAiStep();
    }

    @Override
    public void aiStep() {
        if (this.level().isClientSide) {
            this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
        }
        super.aiStep();
    }

    @Override
    public void handleEntityEvent(byte b) {
        if (b == 10) {
            this.eatAnimationTick = 40;
        } else {
            super.handleEntityEvent(b);
        }
    }

    public float getHeadEatPositionScale(float f) {
        if (this.eatAnimationTick <= 0) {
            return 0.0F;
        } else if (this.eatAnimationTick >= 4 && this.eatAnimationTick <= 36) {
            return 1.0F;
        } else {
            return this.eatAnimationTick < 4 ? ((float)this.eatAnimationTick - f) / 4.0F : -((float)(this.eatAnimationTick - 40) - f) / 4.0F;
        }
    }

    public float getHeadEatAngleScale(float f) {
        if (this.eatAnimationTick > 4 && this.eatAnimationTick <= 36) {
            float g = ((float)(this.eatAnimationTick - 4) - f) / 32.0F;
            return 0.62831855F + 0.21991149F * Mth.sin(g * 28.7F);
        } else {
            return this.eatAnimationTick > 0 ? 0.62831855F : this.getXRot() * 0.017453292F;
        }
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (itemStack.is(Items.SHEARS)) {
            if (!this.level().isClientSide && this.readyForShearing()) {
                this.shear(SoundSource.PLAYERS);
                this.gameEvent(GameEvent.SHEAR, player);
                itemStack.hurtAndBreak(1, player, (playerx) -> playerx.broadcastBreakEvent(interactionHand));
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.CONSUME;
            }
        } else {
            return super.mobInteract(player, interactionHand);
        }
    }

    @Override
    public void shear(SoundSource soundSource) {
        if (!this.level().isClientSide && this.readyForShearing()) {
            this.level().playSound(null, this, SoundEvents.SHEEP_SHEAR, soundSource, 1.0F, 1.0F);
            this.setSheared(true);
            int count = 1 + this.random.nextInt(3);

            Block woolBlock = getWoolBlockByTexture(this.getSheepTexture());
            System.out.println("Dropping wool block: " + woolBlock);

            for (int i = 0; i < count; i++) {
                Item woolItem = woolBlock.asItem();
                ItemStack woolStack = new ItemStack(woolItem);
                ItemEntity itemEntity = new ItemEntity(this.level(), this.getX(), this.getY() + 1.0D, this.getZ(), woolStack);
                itemEntity.setDeltaMovement(itemEntity.getDeltaMovement().add((this.random.nextFloat() - this.random.nextFloat()) * 0.1F, this.random.nextFloat() * 0.05F, (this.random.nextFloat() - this.random.nextFloat()) * 0.1F));
                this.level().addFreshEntity(itemEntity);
            }
        }
    }


    private Block getWoolBlockByTexture(MeadowSheep.SheepTexture texture) {
        return switch (texture) {
            case PATCHED -> ObjectRegistry.PATCHED_WOOL.get();
            case ROCKY -> ObjectRegistry.ROCKY_WOOL.get();
            case INKY -> ObjectRegistry.INKY_WOOL.get();
            default -> ObjectRegistry.FLECKED_WOOL.get();
        };
    }




    @Override
    public @NotNull ResourceLocation getDefaultLootTable() {
        return switch (this.getTextureColor()) {
            case FLECKED_WOOL -> new MeadowIdentifier("entities/sheep/flecked_sheep");
            case PATCHED_WOOL -> new MeadowIdentifier("entities/sheep/patched_sheep");
            case ROCKY_WOOL -> new MeadowIdentifier("entities/sheep/rocky_sheep");
            case INKY_WOOL -> new MeadowIdentifier("entities/sheep/inky_sheep");
        };
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_WOOL_TYPE, (byte) 0);
    }

    public void setWoolType(byte woolType) {
        this.entityData.set(DATA_WOOL_TYPE, woolType);
        System.out.println("Wool type set to: " + woolType); // Fügen Sie diese Zeile hinzu, um das Setzen zu überprüfen
    }


    public byte getWoolType() {
        byte rawWoolType = this.entityData.get(DATA_WOOL_TYPE);
        byte cleanedWoolType = (byte) (rawWoolType & 0x0F);
        System.out.println("Raw wool type: " + rawWoolType + ", Cleaned wool type: " + cleanedWoolType);
        return cleanedWoolType;
    }



    @Nullable
    @Override
    public MeadowSheep getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        MeadowSheep sheep = EntityRegistry.MEADOW_SHEEP.get().create(serverLevel);
        if (sheep != null) {
            sheep.setWoolType((byte) this.random.nextInt(4));
        }
        return sheep;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag) {
        this.setWoolType((byte) this.random.nextInt(4));
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);
    }

    public SheepColor getTextureColor() {
        return switch (this.getWoolType()) {
            case 1 -> SheepColor.PATCHED_WOOL;
            case 2 -> SheepColor.ROCKY_WOOL;
            case 3 -> SheepColor.INKY_WOOL;
            default -> SheepColor.FLECKED_WOOL;
        };
    }



    public boolean readyForShearing() {
        return this.isAlive() && !this.isSheared() && !this.isBaby();
    }

    public boolean isSheared() {
        return (this.entityData.get(DATA_WOOL_TYPE) & 16) != 0;
    }

    public void setSheared(boolean bl) {
        byte b = this.entityData.get(DATA_WOOL_TYPE);
        if (bl) {
            this.entityData.set(DATA_WOOL_TYPE, (byte) (b | 16));
        } else {
            this.entityData.set(DATA_WOOL_TYPE, (byte) (b & -17));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putBoolean("Sheared", this.isSheared());
        compoundTag.putByte("WoolType", this.getWoolType());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.setSheared(compoundTag.getBoolean("Sheared"));
        this.setWoolType(compoundTag.getByte("WoolType"));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SHEEP_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.SHEEP_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SHEEP_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos blockPos, BlockState blockState) {
        this.playSound(SoundEvents.SHEEP_STEP, 0.15F, 1.0F);
    }
}
