package net.satisfyu.meadow.mixin.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import net.satisfyu.meadow.util.MeadowTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RabbitEntity.class)
public abstract class RabbitSpawnMixin extends AnimalEntity {

    protected RabbitSpawnMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = "chooseType",
            at = @At(value = "HEAD"),
            cancellable = true)

    private void getTexture(WorldAccess world, CallbackInfoReturnable<Integer> cir) {
        RegistryEntry<Biome> registryEntry = world.getBiome(this.getBlockPos());
        int i = world.getRandom().nextInt(100);
        if(i < 80){
            if (registryEntry.isIn(MeadowTags.MEADOW_RABBIT_BIOMES)) {
                cir.setReturnValue(world.getRandom().nextBoolean() ? 6 : 7);
            }
        }

    }
}
