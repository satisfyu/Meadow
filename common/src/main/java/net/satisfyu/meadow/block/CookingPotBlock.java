package net.satisfyu.meadow.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.satisfyu.meadow.entity.blockentities.CookingPotBlockEntity;
import net.satisfyu.meadow.registry.BlockEntityRegistry;
import net.satisfyu.meadow.registry.ObjectRegistry;
import net.satisfyu.meadow.util.GeneralUtil;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class CookingPotBlock extends BlockWithEntity {
        protected static final VoxelShape HANGING_SHAPE = makeShapeHanging();

        private static final Supplier<VoxelShape> voxelShapeSupplier = () -> {
            VoxelShape shape = VoxelShapes.empty();
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.436875, 0.374375, 0.124375, 0.563125, 0.438125, 0.188125), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.436875, 0.374375, 0.811875, 0.563125, 0.438125, 0.875625), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.2475, 0, 0.25, 0.7475, 0.0625, 0.75), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.185, 0.0625, 0.1875, 0.81, 0.5625, 0.25), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.185, 0.0625, 0.75, 0.81, 0.5625, 0.8125), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.185, 0.0625, 0.25, 0.2475, 0.5625, 0.75), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.7475, 0.0625, 0.25, 0.81, 0.5625, 0.75), BooleanBiFunction.OR);
            return shape;
        };

        public static VoxelShape makeShapeHanging() {
            VoxelShape shape = VoxelShapes.empty();
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.436875, 0.374375, 0.124375, 0.563125, 0.438125, 0.188125), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.436875, 0.374375, 0.811875, 0.563125, 0.438125, 0.875625), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.2475, 0, 0.25, 0.7475, 0.0625, 0.75), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.185, 0.0625, 0.1875, 0.81, 0.5625, 0.25), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.185, 0.0625, 0.75, 0.81, 0.5625, 0.8125), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.185, 0.0625, 0.25, 0.2475, 0.5625, 0.75), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.7475, 0.0625, 0.25, 0.81, 0.5625, 0.75), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.1875, 1.875, 0.4375, 1.0625, 2, 0.5625), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(-0.0625, 1, 0.4375, 0.0625, 1.875, 0.5625), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(-0.0625, 0, 0.4375, 0.0625, 1, 0.5625), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.9375, 0, 0.4375, 1.0625, 1, 0.5625), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.9375, 1, 0.4375, 1.0625, 1.875, 0.5625), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(-0.0625, 1.875, 0.4375, 0.1875, 2, 0.5625), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.5, 1.25, 0.4375, 0.5, 1.9375, 0.5625), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.4375, 1.25, 0.5, 0.5625, 1.9375, 0.5), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.5125, 0, 0.125, 0.5125, 0.75, 0.875), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.125, 0, 0.4875, 0.875, 0.75, 0.4875), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.1875, 0, 0.125, 0.375, 0.1875, 0.875), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.625, 0, 0.125, 0.8125, 0.1875, 0.875), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.125, 0.1875, 0.1875, 0.875, 0.375, 0.375), BooleanBiFunction.OR);
            shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.125, 0.1875, 0.625, 0.875, 0.375, 0.8125), BooleanBiFunction.OR);
            return shape;
        };

        public static final Map<Direction, VoxelShape> SHAPE = Util.make(new HashMap<>(), map -> {
            for (Direction direction : Direction.Type.HORIZONTAL.stream().toList()) {
                map.put(direction, GeneralUtil.rotateShape(Direction.NORTH, direction, voxelShapeSupplier.get()));
            }
        });

        @Override
        public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
            if (state.get(HANGING)) {
                return HANGING_SHAPE;
            } else {
                return SHAPE.get(state.get(FACING));
            }
        }

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BooleanProperty.of("lit");
    public static final BooleanProperty COOKING = BooleanProperty.of("cooking");
    public static final BooleanProperty HANGING = BooleanProperty.of("hanging");

    public CookingPotBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(HANGING, false).with(COOKING, false).with(LIT, false));
    }


    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }




    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        final BlockEntity entity = world.getBlockEntity(pos);
        if (entity instanceof NamedScreenHandlerFactory factory) {
            player.openHandledScreen(factory);
            return ActionResult.SUCCESS;
        } else {
            return super.onUse(state, world, pos, player, hand, hit);
        }
    }


    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof CookingPotBlockEntity pot) {
                if (world instanceof ServerWorld) {
                    ItemScatterer.spawn(world, pos, pot);
                }
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(COOKING)) {
            double d = (double) pos.getX() + 0.5;
            double e = pos.getY() + 0.7;
            double f = (double) pos.getZ() + 0.5;
            if (random.nextDouble() < 0.3) {
                world.playSound(d, e, f, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }
            Direction direction = state.get(FACING);
            Direction.Axis axis = direction.getAxis();
            double h = random.nextDouble() * 0.6 - 0.3;
            double i = axis == Direction.Axis.X ? (double) direction.getOffsetX() * 0.0 : h;
            double j = random.nextDouble() * 9.0 / 16.0;
            double k = axis == Direction.Axis.Z ? (double) direction.getOffsetZ() * 0.0 : h;
            world.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, d + i, e + j, f + k, 0.0, 0.0, 0.0);
            world.addParticle(ParticleTypes.BUBBLE, d + i, e + j, f + k, 0.0, 0.0, 0.0);
            world.addParticle(ParticleTypes.BUBBLE_POP, d + i, e + j, f + k, 0.0, 0.0, 0.0);
        }
            if (state.get(LIT)) {
                double d = (double) pos.getX() + 0.5;
                double e = pos.getY() + 0.7;
                double f = (double) pos.getZ() + 0.5;
                if (random.nextDouble() < 0.3) {
                    world.playSound(d, e, f, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
                }
                Direction direction = state.get(FACING);
                Direction.Axis axis = direction.getAxis();
                double h = random.nextDouble() * 0.6 - 0.3;
                double i = axis == Direction.Axis.X ? (double) direction.getOffsetX() * 0.0 : h;
                double j = random.nextDouble() * 9.0 / 16.0;
                double k = axis == Direction.Axis.Z ? (double) direction.getOffsetZ() * 0.0 : h;
                world.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, d + i, e + j, f + k, 0.0, 0.0, 0.0);
                world.addParticle(ParticleTypes.BUBBLE, d + i, e + j, f + k, 0.0, 0.0, 0.0);
                world.addParticle(ParticleTypes.BUBBLE_POP, d + i, e + j, f + k, 0.0, 0.0, 0.0);
        }
        if (state.get(HANGING)) {
            double d = (double) pos.getX() + 0.5;
            double e = pos.getY() + 1.0;
            double f = (double) pos.getZ() + 0.5;
            if (random.nextDouble() < 0.3) {
                world.playSound(d, e, f, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }
            Direction direction = state.get(FACING);
            Direction.Axis axis = direction.getAxis();
            double h = random.nextDouble() * 0.6 - 0.3;
            double i = axis == Direction.Axis.X ? (double) direction.getOffsetX() * 0.0 : h;
            double j = random.nextDouble() * 9.0 / 16.0;
            double k = axis == Direction.Axis.Z ? (double) direction.getOffsetZ() * 0.0 : h;
            world.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, d + i, e + j, f + k, 0.0, 0.0, 0.0);
            world.addParticle(ParticleTypes.BUBBLE, d + i, e + j, f + k, 0.0, 0.0, 0.0);
            world.addParticle(ParticleTypes.BUBBLE_POP, d + i, e + j, f + k, 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, COOKING, LIT, HANGING);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, BlockEntityRegistry.COOKING_POT.get(), (world1, pos, state1, be) -> be.tick(world1, pos, state1, be));
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> list = new ArrayList<>();
        list.add(new ItemStack(this));
        if (state.get(HANGING)) {
            list.add(new ItemStack(ObjectRegistry.FRAME.get()));
        }
        return list;
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CookingPotBlockEntity(pos, state);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, BlockView world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("block.meadow.canbeplaced.tooltip").formatted(Formatting.ITALIC, Formatting.GRAY));
    }
}