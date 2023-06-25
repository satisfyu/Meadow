package net.satisfyu.meadow.block;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

public class CheeseBlock extends HFacingBlock {

    private static final VoxelShape SHAPE = Block.createCuboidShape(4, 0, 4, 12, 4, 12);

    private static final VoxelShape SHAPE_BIG = Block.createCuboidShape(2, 0, 2, 14, 6, 14);

    public static final IntProperty CUTS = IntProperty.of("cuts", 0, 3);
    private final RegistrySupplier<Item> slice;

    private final boolean big;

    public CheeseBlock(Settings settings, RegistrySupplier<Item> slice, boolean big) {
        super(settings);
        this.slice = slice;
        this.big = big;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(CUTS);
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return big ? SHAPE_BIG : SHAPE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (world.isClient) {
            if (tryEat(world, pos, state, player).isAccepted()) {
                return ActionResult.SUCCESS;
            }
            if (itemStack.isEmpty()) {
                return ActionResult.CONSUME;
            }
        }
        return tryEat(world, pos, state, player);
    }

    private ActionResult tryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player) {
        world.playSound(null, pos, SoundEvents.BLOCK_BEEHIVE_SHEAR, SoundCategory.BLOCKS, 1, 1);
        CheeseBlock.dropStack((World) world, pos, Direction.UP, new ItemStack(slice.get()));
        int i = state.get(CUTS);
        world.emitGameEvent(player, GameEvent.EAT, pos);
        if (i < 3) {
            world.setBlockState(pos, state.with(CUTS, i + 1), Block.NOTIFY_ALL);
        } else {
            world.removeBlock(pos, false);
            world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.DOWN && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).getMaterial().isSolid();
    }
}
