package net.satisfyu.meadow.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.satisfyu.meadow.registry.ObjectRegistry;
import org.jetbrains.annotations.NotNull;

public class WoodenBucket extends BucketItem {
    private final Fluid fluid;

    public WoodenBucket(Fluid fluid, Properties settings) {
        super(fluid, settings);
        this.fluid = fluid;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        BlockHitResult blockHitResult = WoodenBucket.getPlayerPOVHitResult(world, user, this.fluid == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
        if (blockHitResult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemStack);
        }
        if (blockHitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockPos3;
            BlockPos blockPos = blockHitResult.getBlockPos();
            Direction direction = blockHitResult.getDirection();
            BlockPos blockPos2 = blockPos.relative(direction);
            if (!world.mayInteract(user, blockPos) || !user.mayUseItemAt(blockPos2, direction, itemStack)) {
                return InteractionResultHolder.fail(itemStack);
            }
            if (this.fluid == Fluids.EMPTY) {
                BucketPickup fluidDrainable;
                ItemStack itemStack2;
                BlockState blockState = world.getBlockState(blockPos);
                if (blockState.getBlock() instanceof BucketPickup f) {

                    if (!blockState.getFluidState().isSource() || blockState.getBlock().equals(Blocks.LAVA) || blockState.getBlock().equals(Blocks.POWDER_SNOW)) {
                        return InteractionResultHolder.pass(itemStack);
                    }
                    itemStack2 = (fluidDrainable = f).pickupBlock(world, blockPos, blockState);

                    if (itemStack2.isEmpty()) {
                        return InteractionResultHolder.pass(itemStack);
                    }

                    if (itemStack2.getItem().equals(Items.WATER_BUCKET)) {
                        itemStack2 = new ItemStack(ObjectRegistry.WOODEN_WATER_BUCKET.get());
                    }

                    user.awardStat(Stats.ITEM_USED.get(this));
                    fluidDrainable.getPickupSound().ifPresent(sound -> user.playSound(sound, 1.0f, 1.0f));
                    world.gameEvent(user, GameEvent.FLUID_PICKUP, blockPos);
                    ItemStack itemStack3 = ItemUtils.createFilledResult(itemStack, user, itemStack2);
                    if (!world.isClientSide) {
                        CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) user, itemStack2);
                    }
                    return InteractionResultHolder.sidedSuccess(itemStack3, world.isClientSide());
                }
                return InteractionResultHolder.fail(itemStack);
            }
            BlockState blockState = world.getBlockState(blockPos);
            blockPos3 = blockState.getBlock() instanceof LiquidBlockContainer && this.fluid == Fluids.WATER ? blockPos : blockPos2;
            if (this.emptyContents(user, world, blockPos3, blockHitResult)) {
                this.checkExtraContent(user, world, itemStack, blockPos3);
                if (user instanceof ServerPlayer) {
                    CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) user, blockPos3, itemStack);
                }
                user.awardStat(Stats.ITEM_USED.get(this));
                return InteractionResultHolder.sidedSuccess(WoodenBucket.getEmptySuccessItem(itemStack, user), world.isClientSide());
            }
            return InteractionResultHolder.fail(itemStack);
        }
        return super.use(world, user, hand);
    }

    public static @NotNull ItemStack getEmptySuccessItem(ItemStack stack, Player player) {
        if (!player.getAbilities().instabuild) {
            return new ItemStack(ObjectRegistry.WOODEN_BUCKET.get());
        }
        return stack;
    }
}
