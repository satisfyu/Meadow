package net.satisfyu.meadow.block;

import de.cristelknight.doapi.common.block.FacingBlock;
import de.cristelknight.doapi.common.util.GeneralUtil;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Tuple;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.satisfyu.meadow.entity.CheeseRackBlockEntity;
import net.satisfyu.meadow.registry.TagRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class CheeseRackBlock extends FacingBlock implements EntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final Supplier<VoxelShape> voxelShapeSupplier = () -> {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.01, 0.01, 0.01, 0.99, 0.99, 0.99), BooleanOp.OR);
        return shape;
    };

    public static final Map<Direction, VoxelShape> SHAPE = Util.make(new HashMap<>(), map -> {
        for (Direction direction : Direction.Plane.HORIZONTAL.stream().toList()) {
            map.put(direction, GeneralUtil.rotateShape(Direction.NORTH, direction, voxelShapeSupplier.get()));
        }
    });

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE.get(state.getValue(FACING));
    }

    public CheeseRackBlock(Properties settings) {
        super(settings);
    }

    @Override
    public @NotNull InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (world.isClientSide) return InteractionResult.SUCCESS;
        CheeseRackBlockEntity be = (CheeseRackBlockEntity) world.getBlockEntity(pos);
        if (be == null || player.isShiftKeyDown()) return InteractionResult.PASS;
        ItemStack handStack = player.getItemInHand(hand);
        int slot = -1;
        Optional<Tuple<Float, Float>> optional = GeneralUtil.getRelativeHitCoordinatesForBlockFace(hit, state.getValue(FACING), null);
        if (optional.isPresent()) {
            Tuple<Float, Float> pair = optional.get();
            slot = pair.getB() > 0.5 ? 1 : 0;
            System.out.println(pair.getA() + " " + pair.getB());
        }

        if (slot == -1)
            return InteractionResult.PASS;

        if (handStack.isEmpty() && be.hasStack(slot)) {
            player.addItem(be.removeStack(slot));
            return InteractionResult.SUCCESS;
        } else if (handStack.is(TagRegistry.CHEESE_BLOCKS) && !be.hasStack(slot)) {
            be.setStack(slot, new ItemStack(handStack.getItem()));
            handStack.shrink(1);
            return InteractionResult.SUCCESS;
        }
        return super.use(state, world, pos, player, hand, hit);
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof CheeseRackBlockEntity be) {
                for (Item stack : be.getItems()) {
                    Containers.dropItemStack(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(stack));
                }
                world.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, world, pos, newState, moved);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CheeseRackBlockEntity(pos, state);
    }
}

