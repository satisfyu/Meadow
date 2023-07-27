package net.satisfyu.meadow.entity.chicken;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.registry.EntityRegistry;

import java.util.Random;

public class MeadowChickenEntity extends Chicken {
    private static final Random random = new Random();
    private ResourceLocation textureVariant;

    public MeadowChickenEntity(EntityType<? extends Chicken> entityType, Level world) {
        super(entityType, world);
        this.textureVariant = getRandomTextureVariant();
    }

    private ResourceLocation getRandomTextureVariant() {
        int variantIndex = random.nextInt(3);
        return new ResourceLocation(Meadow.MOD_ID, "textures/entity/chicken/meadow_chicken_" + (variantIndex + 1) + ".png");
    }

    @Override
    public MeadowChickenEntity getBreedOffspring(ServerLevel serverWorld, AgeableMob passiveEntity) {
        return EntityRegistry.MEADOW_CHICKEN.get().create(serverWorld);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("TextureVariant", textureVariant.toString());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("TextureVariant")) {
            textureVariant = new ResourceLocation(tag.getString("TextureVariant"));
        }
    }

    public ResourceLocation getTextureVariant() {
        return textureVariant;
    }
}
