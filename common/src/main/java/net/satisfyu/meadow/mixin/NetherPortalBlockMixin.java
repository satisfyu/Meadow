package net.satisfyu.meadow.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfyu.meadow.entity.var.ShearableCowVar;
import net.satisfyu.meadow.entity.ShearableVarCow;
import net.satisfyu.meadow.registry.EntityRegistry;
import net.satisfyu.meadow.registry.TagRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetherPortalBlock.class)
public class NetherPortalBlockMixin {

    @Inject(method = "randomTick", at = @At("HEAD"))
    private void checkCannotConnect(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource, CallbackInfo ci) {
        if (!serverLevel.dimensionType().natural() && serverLevel.getRandom().nextFloat() < 0.005F && serverLevel.getBiome(blockPos).is(TagRegistry.SPAWNS_WARPED_COW) && serverLevel.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) {
            while(serverLevel.getBlockState(blockPos).is(Blocks.NETHER_PORTAL)) {
                blockPos = blockPos.below();
            }

            if (serverLevel.getBlockState(blockPos).isValidSpawn(serverLevel, blockPos, EntityRegistry.SHEARABLE_MEADOW_VAR_COW.get())) {
                ShearableVarCow entity = EntityRegistry.SHEARABLE_MEADOW_VAR_COW.get().spawn(serverLevel, blockPos.above(), MobSpawnType.STRUCTURE);
                if (entity != null) {
                    entity.setVariant(ShearableCowVar.WARPED);
                    entity.setPortalCooldown();
                }
            }
        }
    }
}
