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
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.satisfyu.meadow.entity.FlowerPotBlockEntity;
import net.satisfyu.meadow.entity.WoodenFlowerPotBigBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class WoodenFlowerPotBigBlock extends Block implements EntityBlock {
    private static final VoxelShape SHAPE;
    private static final Supplier<VoxelShape> voxelShapeSupplier = () -> {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.25, 0, 0.25, 0.75, 0.4375, 0.75), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.1875, 0, 0.1875, 0.25, 0.5625, 0.8125), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.75, 0, 0.1875, 0.8125, 0.5625, 0.8125), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.25, 0, 0.1875, 0.75, 0.5625, 0.25), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.25, 0, 0.75, 0.75, 0.5625, 0.8125), BooleanOp.OR);
        return shape;
    };

    public WoodenFlowerPotBigBlock(Properties settings) {
        super(settings);
    }

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

    public boolean fitInPot(ItemStack item) {
        return item.is(ItemTags.TALL_FLOWERS);
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
        return new WoodenFlowerPotBigBlockEntity(pos, state);
    }

    static {
        SHAPE = voxelShapeSupplier.get();
    }
}
