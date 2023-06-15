package net.satisfyu.meadow.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.satisfyu.meadow.entity.blockentities.FlowerPotBlockEntity;
import org.jetbrains.annotations.Nullable;

public abstract class FlowerPotBlock extends Block implements BlockEntityProvider {

    public FlowerPotBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient || hand == Hand.OFF_HAND) return ActionResult.SUCCESS;
        FlowerPotBlockEntity be = (FlowerPotBlockEntity)world.getBlockEntity(pos);
        if (be == null || player.isSneaking()) return ActionResult.PASS;

        ItemStack handStack = player.getStackInHand(hand);
        Item flower = be.getFlower();

        if (handStack.isEmpty()) {
            if (flower != null) {
                player.giveItemStack(flower.getDefaultStack());
                be.setFlower(null);
                return ActionResult.SUCCESS;
            }
        } else if (fitInPot(handStack) && flower == null) {
            be.setFlower(handStack.getItem());
            if (!player.isCreative()) {
                handStack.decrement(1);
            }
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    public abstract boolean fitInPot(ItemStack item);

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof FlowerPotBlockEntity be) {
                Item flower = be.getFlower();
                if (flower != null) {
                    ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), flower.getDefaultStack());
                }
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.IGNORE;
    }

    @Nullable
    @Override
    public abstract BlockEntity createBlockEntity(BlockPos pos, BlockState state);
}
