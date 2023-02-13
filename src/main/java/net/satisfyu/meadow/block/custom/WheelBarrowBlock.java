package net.satisfyu.meadow.block.custom;

import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import net.satisfyu.meadow.util.EnumFlower;
import net.satisfyu.meadow.util.GeneralUtil;

import java.util.*;
import java.util.function.Supplier;


public class WheelBarrowBlock extends Block {
    public WheelBarrowBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(CONTENT, EnumFlower.NONE));
    }

    public static final EnumProperty<EnumFlower> CONTENT = EnumProperty.of("content", EnumFlower.class);
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    @Override
    protected void appendProperties(StateManager.Builder< Block, BlockState> builder) {
        builder.add(CONTENT, FACING);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand
            hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (item instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            if (block instanceof PlantBlock) {
                EnumFlower flower = EnumFlower.NONE;
                for (EnumFlower f : EnumFlower.values()) {
                    if (f.getFlower() == block) {
                        flower = f;
                        break;
                    }
                }
                if (flower == EnumFlower.NONE) {
                    return ActionResult.FAIL;
                }
                world.setBlockState(pos, state.with(CONTENT, flower));
                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }
                world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                return ActionResult.success(world.isClient);
            }
        } else if (player.isSneaking() && !isEmpty(state)) {
            player.giveItemStack(new ItemStack(state.get(CONTENT).getFlower()));
            world.setBlockState(pos, state.with(CONTENT, EnumFlower.NONE));
            world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return this.isEmpty(state) ? super.getPickStack(world, pos, state) : new ItemStack(state.get(CONTENT).getFlower());
    }

    private boolean isEmpty(BlockState state) {
        return state.get(CONTENT) == EnumFlower.NONE;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess
            world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> list = new ArrayList<>();
        list.add(new ItemStack(this));
        Optional.of(state.get(CONTENT)).filter(a -> a != EnumFlower.NONE).map(EnumFlower::getFlower).map(ItemStack::new).ifPresent(list::add);
        return list;
    }

    private static final Supplier<VoxelShape> voxelShapeSupplier = () -> {
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(-0.3125, 0.25, 0.5625, 0.125, 0.375, 0.625), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.8125, 0, 0.6875, 0.875, 0.125, 0.8125), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.8125, 0, 0.125, 0.875, 0.125, 0.25), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.75, 0, 0.125, 0.8125, 0.125, 0.1875), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.75, 0, 0.75, 0.8125, 0.125, 0.8125), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.125, 0.125, 0.125, 0.875, 0.75, 0.25), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.125, 0.125, 0.6875, 0.875, 0.75, 0.8125), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.125, 0.125, 0.25, 0.25, 0.75, 0.6875), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.75, 0.125, 0.25, 0.875, 0.75, 0.6875), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0.125, 0.25, 0.75, 0.6875, 0.6875), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(-0.3125, 0.25, 0.375, 0.125, 0.375, 0.4375), BooleanBiFunction.OR);

        return shape;
    };

    public static final Map<Direction, VoxelShape> SHAPE = Util.make(new HashMap<>(), map -> {
        for (Direction direction : Direction.Type.HORIZONTAL.stream().toList()) {
            map.put(direction, GeneralUtil.rotateShape(Direction.NORTH, direction, voxelShapeSupplier.get()));
        }
    });

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE.get(state.get(FACING));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, BlockView world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("block.meadow.canbeplaced.tooltip").formatted(Formatting.ITALIC, Formatting.GRAY));
        tooltip.add(Text.translatable("block.meadow.wheelbarrow.tooltip").formatted(Formatting.GRAY, Formatting.ITALIC));

    }
}
