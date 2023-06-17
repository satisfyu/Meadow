package net.satisfyu.meadow.entity.chicken;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.registry.EntityRegistry;

import java.util.Random;

public class MeadowChickenEntity extends ChickenEntity {
    private static final Random random = new Random();
    private Identifier textureVariant;

    public MeadowChickenEntity(EntityType<? extends ChickenEntity> entityType, World world) {
        super(entityType, world);
        this.textureVariant = getRandomTextureVariant();
    }

    private Identifier getRandomTextureVariant() {
        int variantIndex = random.nextInt(3);
        return new Identifier(Meadow.MOD_ID, "textures/entity/chicken/meadow_chicken_" + (variantIndex + 1) + ".png");
    }

    @Override
    public MeadowChickenEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return EntityRegistry.MEADOW_CHICKEN.get().create(serverWorld);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound tag) {
        super.writeCustomDataToNbt(tag);
        tag.putString("TextureVariant", textureVariant.toString());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound tag) {
        super.readCustomDataFromNbt(tag);
        if (tag.contains("TextureVariant")) {
            textureVariant = new Identifier(tag.getString("TextureVariant"));
        }
    }

    public Identifier getTextureVariant() {
        return textureVariant;
    }
}
