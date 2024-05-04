package net.satisfyu.meadow.block;

import de.cristelknight.doapi.common.util.GeneralUtil;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.satisfyu.meadow.entity.FlowerPotBlockEntity;
import net.satisfyu.meadow.entity.WheelBarrowBlockEntity;
import net.satisfyu.meadow.registry.TagRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class WheelBarrowBlock extends Block implements EntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    private static final Supplier<VoxelShape> voxelShapeSupplier = () -> {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.joinUnoptimized(shape, Shapes.box(-0.3125, 0.25, 0.5625, 0.125, 0.375, 0.625), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.8125, 0, 0.6875, 0.875, 0.125, 0.8125), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.8125, 0, 0.125, 0.875, 0.125, 0.25), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.75, 0, 0.125, 0.8125, 0.125, 0.1875), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.75, 0, 0.75, 0.8125, 0.125, 0.8125), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.125, 0.125, 0.125, 0.875, 0.75, 0.25), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.125, 0.125, 0.6875, 0.875, 0.75, 0.8125), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.125, 0.125, 0.25, 0.25, 0.75, 0.6875), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.75, 0.125, 0.25, 0.875, 0.75, 0.6875), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.25, 0.125, 0.25, 0.75, 0.6875, 0.6875), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(-0.3125, 0.25, 0.375, 0.125, 0.375, 0.4375), BooleanOp.OR);

        return shape;
    };

    public static final Map<Direction, VoxelShape> SHAPE = Util.make(new HashMap<>(), map -> {
        for (Direction direction : Direction.Plane.HORIZONTAL.stream().toList()) {
            map.put(direction, GeneralUtil.rotateShape(Direction.NORTH, direction, voxelShapeSupplier.get()));
        }
    });

    public WheelBarrowBlock(Properties settings) {
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
        return item.is(TagRegistry.SMALL_FLOWER);
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
        return SHAPE.get(state.getValue(FACING));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getClockWise(Direction.Axis.Y));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new WheelBarrowBlockEntity(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public @NotNull BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
}
