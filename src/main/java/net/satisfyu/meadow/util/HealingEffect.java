package net.satisfyu.meadow.util;

import net.satisfyu.meadow.block.ModBlocks;

public class HealingEffect {{
    public static void playerTickEvent(ServerLevel world, ServerPlayer player) {
        if (player.tickCount % ConfigHandler.checkForCampfireDelayInTicks != 0) {
            return;
        }

        List<BlockPos> nearbycampfires = FABFunctions.getAllTaggedTileEntityPositionsNearbyEntity(ModBlocks.STOVE_WOOD, ConfigHandler.healingRadius, world, player);
        if (nearbycampfires.size() == 0) {
            return;
        }

        MobEffectInstance effectinstance;
        if (ConfigHandler.hideEffectParticles) {
            effectinstance = new MobEffectInstance(MobEffects.REGENERATION, ConfigHandler.effectDurationSeconds*20, ConfigHandler.regenerationLevel-1, true, false);
        }
        else {
            effectinstance = new MobEffectInstance(MobEffects.REGENERATION, ConfigHandler.effectDurationSeconds*20, ConfigHandler.regenerationLevel-1);
        }
}
