package net.satisfyu.meadow.block.windowShutter;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.satisfyu.meadow.block.ModBlockProperties;
import org.jetbrains.annotations.Nullable;

public class ShutterBlock extends Block implements Waterloggable {

    public static final DirectionProperty FACING;
    public static final EnumProperty<ShutterType> TYPE;
    public static final BooleanProperty LEFT;
    public static final BooleanProperty OPEN;
    public static final BooleanProperty POWERED;
    public static final BooleanProperty WATERLOGGED;
    protected static final VoxelShape[] SHAPES;

    public ShutterBlock(Settings settings) {
        super(settings);
        this.setDefaultState(((((this.stateManager.getDefaultState().with(FACING, Direction.NORTH)).with(TYPE, ShutterType.NONE)).with(OPEN, false)).with(LEFT, false).with(POWERED, false)).with(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int shape = state.get(FACING).getHorizontal() + (state.get(OPEN) ? (state.get(LEFT) ? 3 : 1) : 0);
        return SHAPES[shape % 4];
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        Direction facing = context.getPlayerFacing().getOpposite();
        BlockState blockState = this.getDefaultState().with(FACING, facing);

        World world = context.getWorld();
        BlockPos clickedPos = context.getBlockPos();
        Vec3d clickLocation = context.getHitPos();

        boolean left;
        if (facing.getAxis() == Direction.Axis.X) {
            left = clickLocation.z - (double)clickedPos.getZ() > 0.5D;
        } else {
            left = clickLocation.x - (double)clickedPos.getX() > 0.5D;
        }
        if (context.getPlayerLookDirection() == Direction.NORTH || context.getPlayerLookDirection() == Direction.EAST) left = !left;
        blockState = blockState.with(LEFT, left);

        if (world.isReceivingRedstonePower(clickedPos)) {
            blockState = blockState.with(OPEN, true).with(POWERED, true);
        }

        blockState = blockState.with(TYPE, getType(blockState, world.getBlockState(clickedPos.up()), world.getBlockState(clickedPos.down())));

        return blockState.with(WATERLOGGED, world.getFluidState(clickedPos).getFluid() == Fluids.WATER);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (world.isClient) return;

        boolean powered = world.isReceivingRedstonePower(pos);
        if (powered != state.get(POWERED)) {
            if (state.get(OPEN) != powered) {
                state = state.with(OPEN, powered);
                world.playSound(null, pos, shutterSound(powered), SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
            state = state.with(POWERED, powered);
            if (state.get(WATERLOGGED)) {
                world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            }
        }
        ShutterType type = getType(state, world.getBlockState(pos.up()), world.getBlockState(pos.down()));
        if (state.get(TYPE) != type) {
            state = state.with(TYPE, type);
        }
        world.setBlockState(pos, state, 3);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (this.material == Material.METAL) {
            return ActionResult.PASS;
        } else {
            state = state.cycle(OPEN);
            world.setBlockState(pos, state, 3);
            if (!player.isInSneakingPose()) {
                toggleShutters(state, world, pos, state.get(OPEN));
            }
            world.playSound(null, pos, shutterSound(state.get(OPEN)), SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (state.get(WATERLOGGED)) {
                world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            }

            return ActionResult.success(world.isClient);
        }
    }

    public void toggleShutters(BlockState state, World world, BlockPos pos, boolean open) {
        BlockState updateState = state;
        BlockPos updatePos = pos;
        if (state.get(TYPE) == ShutterType.MIDDLE || state.get(TYPE) == ShutterType.BOTTOM) {
            int heightUp = world.getDimension().height() - updatePos.getY();
            for (int i = 0; i < heightUp; i++) {
                BlockState above = world.getBlockState(updatePos.up());
                if (above.getBlock() == state.getBlock() && above.get(FACING) == updateState.get(FACING) && above.get(LEFT) == updateState.get(LEFT) && above.get(OPEN) != open) {
                    updateState = above;
                    updatePos = updatePos.up();
                    world.setBlockState(updatePos, updateState.with(OPEN, open), 3);
                } else {
                    break;
                }
            }
        }
        if (state.get(TYPE) == ShutterType.MIDDLE || state.get(TYPE) == ShutterType.TOP) {
            updateState = state;
            updatePos = pos;
            int heightDown = world.getDimension().minY() - updatePos.getY();
            heightDown = (heightDown < 0) ? -heightDown : heightDown;
            for (int i = 0; i < heightDown; i++) {
                BlockState below = world.getBlockState(updatePos.down());
                if (below.getBlock() == state.getBlock() && below.get(FACING) == updateState.get(FACING) && below.get(LEFT) == updateState.get(LEFT) && below.get(OPEN) != open) {
                    updateState = below;
                    updatePos = updatePos.down();
                    world.setBlockState(updatePos, updateState.with(OPEN, open), 3);
                } else {
                    break;
                }
            }
        }
    }

    public static SoundEvent shutterSound(boolean open) {
        if (open) {
            return SoundEvents.BLOCK_WOODEN_TRAPDOOR_OPEN;
        }
        return SoundEvents.BLOCK_WOODEN_TRAPDOOR_CLOSE;
    }

    public ShutterType getType(BlockState state, BlockState above, BlockState below) {
        boolean shape_above_same = above.getBlock() == state.getBlock() && above.get(FACING) == state.get(FACING)
                && above.get(OPEN) == state.get(OPEN) && above.get(LEFT) == state.get(LEFT);
        boolean shape_below_same = below.getBlock() == state.getBlock() && below.get(FACING) == state.get(FACING)
                && below.get(OPEN) == state.get(OPEN) && below.get(LEFT) == state.get(LEFT);

        if (shape_above_same && !shape_below_same) {
            return ShutterType.BOTTOM;
        } else if (!shape_above_same && shape_below_same) {
            return ShutterType.TOP;
        } else if (shape_above_same) {
            return ShutterType.MIDDLE;
        }
        return ShutterType.NONE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, OPEN, LEFT, POWERED, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    static {
        FACING = Properties.HORIZONTAL_FACING;
        TYPE = ModBlockProperties.SHUTTER_TYPE;
        LEFT = BooleanProperty.of("left");
        OPEN = Properties.OPEN;
        POWERED = Properties.POWERED;
        WATERLOGGED = Properties.WATERLOGGED;
        SHAPES = new VoxelShape[]{
                Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D),  //south
                Block.createCuboidShape(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D), //west
                Block.createCuboidShape(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D),//north
                Block.createCuboidShape(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D)  //east
        };
    }
}
