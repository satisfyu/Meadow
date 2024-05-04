package net.satisfyu.meadow.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.satisfyu.meadow.entity.FlowerPotBlockEntity;
import net.satisfyu.meadow.entity.WoodenFlowerPotSmallBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("deprecation")
public class WoodenFlowerPotSmallBlock extends Block implements EntityBlock {
    protected static final VoxelShape SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 6.0, 11.0);

    @Override
    public @NotNull InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (world.isClientSide || hand == InteractionHand.OFF_HAND) return InteractionResult.SUCCESS;
        FlowerPotBlockEntity be = (FlowerPotBlockEntity) world.getBlockEntity(pos);
        if (be == null || player.isShiftKeyDown()) return InteractionResult.PASS;

        ItemStack handStack = player.getItemInHand(hand);
        Item flower = be.getFlower();

        if (handStack.isEmpty()) {
            if (flower != null) {
                player.addItem(flower.getDefaultInstance());
                be.setFlower(null);
                return InteractionResult.SUCCESS;
            }
        } else if (fitInPot(handStack) && flower == null) {
            be.setFlower(handStack.getItem());
            if (!player.isCreative()) {
                handStack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }
        return super.use(state, world, pos, player, hand, hit);
    }

    public WoodenFlowerPotSmallBlock(Properties settings) {
        super(settings);
    }

    public boolean fitInPot(ItemStack item) {
        return item.is(ItemTags.SMALL_FLOWERS);
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof FlowerPotBlockEntity be) {
                Item flower = be.getFlower();
                if (flower != null) {
                    Containers.dropItemStack(world, pos.getX(), pos.getY(), pos.getZ(), flower.getDefaultInstance());
                }
                world.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, world, pos, newState, moved);
        }
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new WoodenFlowerPotSmallBlockEntity(pos, state);
    }
}
