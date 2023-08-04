package net.satisfyu.meadow.entity.cow;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.satisfyu.meadow.registry.EntityRegistry;
import net.satisfyu.meadow.registry.ObjectRegistry;
import net.satisfyu.meadow.registry.TagRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class MeadowVarCowEntity extends Cow implements VariantHolder<CowVar> {

    private static final Map<CowVar, TagKey<Biome>> SPAWNS = Util.make(new HashMap<>(), map -> {
        map.put(CowVar.COOKIE, TagRegistry.IS_MEADOW);
        map.put(CowVar.DAIRY, TagRegistry.IS_MEADOW);
        map.put(CowVar.CREAM, TagRegistry.SPAWNS_COW);
        map.put(CowVar.PINTO, TagRegistry.SPAWNS_COW);
        map.put(CowVar.DARK, TagRegistry.SPAWNS_DARK_COW);
        map.put(CowVar.ALBINO, TagRegistry.SPAWNS_BEAR);
        map.put(CowVar.SUNSET, TagRegistry.SPAWNS_SUNSET_COW);
    });

    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(MeadowVarCowEntity.class, EntityDataSerializers.INT);
    public MeadowVarCowEntity(EntityType<? extends Cow> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        boolean bl;
        if (((bl = itemStack.is(Items.BUCKET)) || itemStack.is(ObjectRegistry.WOODEN_BUCKET.get())) && !this.isBaby()) {
            player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            ItemStack itemStack2 = ItemUtils.createFilledResult(itemStack, player, bl ? Items.MILK_BUCKET.getDefaultInstance() : ObjectRegistry.WOODEN_MILK_BUCKET.get().getDefaultInstance());
            player.setItemInHand(hand, itemStack2);
            return InteractionResult.sidedSuccess(level().isClientSide);
        } else {
            return super.mobInteract(player, hand);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (getVariant().equals(CowVar.ALBINO) && !isBaby() && level().isDay() && level().canSeeSky(blockPosition())) {
            hurt(level().damageSources().inFire(), 1.0f);
        }
    }

    @Nullable
    @Override
    public Cow getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        MeadowVarCowEntity cow = EntityRegistry.MEADOW_VAR_COW.get().create(serverLevel);
        if(cow == null) return null;

        RandomSource random = serverLevel.getRandom();
        CowVar var = this.getVariant();
        if(random.nextBoolean() && ageableMob instanceof MeadowVarCowEntity varCow){
            var = varCow.getVariant();
        }
        cow.setVariant(var);
        return cow;
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag) {
        CowVar variant;
        if (spawnGroupData instanceof MeadowVarCowGroupData data) {
            variant = data.variant;
        } else {
            variant = getRandomVariant(serverLevelAccessor, blockPosition());
            spawnGroupData = new MeadowVarCowGroupData(variant);
        }

        setVariant(variant);
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);
    }



    private static CowVar getRandomVariant(LevelAccessor levelAccessor, BlockPos blockPos) {
        Holder<Biome> holder = levelAccessor.getBiome(blockPos);
        RandomSource random = levelAccessor.getRandom();
        List<CowVar> possibleVars = getCowVariantsInBiome(holder);
        int size = possibleVars.size();
        if(size == 0) return Util.getRandom(CowVar.values(), random);

        return possibleVars.get(levelAccessor.getRandom().nextInt(size));
    }


    public static List<CowVar> getCowVariantsInBiome(Holder<Biome> biome) {
        return SPAWNS.keySet().stream()
                .filter(cowVariant -> biome.is(SPAWNS.get(cowVariant)))
                .collect(Collectors.toList());
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_ID_TYPE_VARIANT, 0);
    }
    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("Variant", getTypeVariant());
    }
    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        setTypeVariant(compoundTag.getInt("Variant"));
    }
    @Override
    public void setVariant(CowVar variant) {
        setTypeVariant(variant.getId() & 255 | this.getTypeVariant() & -256);
    }
    @Override
    public CowVar getVariant() {
        return CowVar.byId(getTypeVariant() & 255);
    }
    private void setTypeVariant(int i) {
        entityData.set(DATA_ID_TYPE_VARIANT, i);
    }
    private int getTypeVariant() {
        return entityData.get(DATA_ID_TYPE_VARIANT);
    }

    public static class MeadowVarCowGroupData extends AgeableMob.AgeableMobGroupData {
        public final CowVar variant;
        public MeadowVarCowGroupData(CowVar variant) {
            super(true);
            this.variant = variant;
        }
    }
}
