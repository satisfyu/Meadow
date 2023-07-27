package net.satisfyu.meadow.item;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.satisfyu.meadow.registry.ObjectRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WoodenMilkBucket extends MilkBucketItem {
    public WoodenMilkBucket(Properties settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        if (user instanceof ServerPlayer serverPlayerEntity) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.awardStat(Stats.ITEM_USED.get(this));
        }
        if (user instanceof Player && !((Player) user).getAbilities().instabuild) {
            stack.shrink(1);
        }
        if (!world.isClientSide) {
            user.removeAllEffects();
        }
        if (stack.isEmpty()) {
            return new ItemStack(ObjectRegistry.WOODEN_BUCKET.get());
        }
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, TooltipFlag context) {
        tooltip.add(Component.translatable("item.meadow.ingredient.tooltip" + this.getDescriptionId()).withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));

    }
}