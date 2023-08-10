package net.satisfyu.meadow.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.satisfyu.meadow.registry.TagRegistry;
import net.satisfyu.meadow.util.BlockStateUtils;
import net.satisfyu.meadow.util.MathUtils;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StoveTilesFurnaceBlock extends Block {


    public static final VoxelShape SHAPE = Shapes.or(Block.box(0, 0, 0, 4, 2, 4), Block.box(12, 0, 0, 16, 2, 4), Block.box(0, 0, 12, 16, 2, 16), Block.box(12, 0, 12, 16, 2, 16));

    public static final VoxelShape SHAPE_BIG = Shapes.or(SHAPE, Block.box(0, 2, 0, 16, 16, 16));

    public static final VoxelShape SHAPE_SMALL = Shapes.or(SHAPE, Block.box(0, 2, 0, 16, 6, 16));

    private final boolean isBig;

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public StoveTilesFurnaceBlock(Properties settings, boolean isBig) {
        super(settings);
        this.isBig = isBig;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return isBig ? SHAPE_BIG : SHAPE_SMALL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, LIT);
    }


    protected InteractionResult tryLightUpByPlayerHand(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand) {
        InteractionResult actionResult = InteractionResult.PASS;
        ItemStack stackHand = player.getItemInHand(hand);

        if (stackHand.getItem() instanceof FlintAndSteelItem) {
            world.playSound(player, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.f,
                    MathUtils.RAND.nextFloat() * .4f + .8f);
            stackHand.hurtAndBreak(1, player, playerEntity -> playerEntity.broadcastBreakEvent(hand));

            actionResult = InteractionResult.SUCCESS;
        } else if (stackHand.getItem() instanceof FireChargeItem) {
            world.playSound(player, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.f,
                    (MathUtils.RAND.nextFloat() - MathUtils.RAND.nextFloat()) * .2f + 1.f);
            if (!player.isCreative()) {
                stackHand.shrink(1);
            }

            actionResult = InteractionResult.SUCCESS;
        }

        if (actionResult.consumesAction()) {
            world.setBlock(pos, state.setValue(LIT, Boolean.TRUE), BlockStateUtils.DEFAULT_AND_RERENDER);
        }

        return actionResult;
    }


    protected InteractionResult tryExtinguishByPlayerHand(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand) {
        ItemStack stackHand = player.getItemInHand(hand);
        Item usedItem = stackHand.getItem();

        if (!(stackHand.is(TagRegistry.SHOVEL)) && usedItem != Items.WATER_BUCKET) {
            return InteractionResult.PASS;
        }

        extinguish(state, world, pos);
        if (!player.isCreative() && usedItem == Items.WATER_BUCKET) {
            player.setItemInHand(hand, new ItemStack(Items.BUCKET));
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(LIT, true);
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        boolean isLit = world.getBlockState(pos).getValue(LIT);
        if (isLit && !entity.fireImmune() && entity instanceof LivingEntity livingEntity &&
                !EnchantmentHelper.hasFrostWalker(livingEntity)) {
            entity.hurt(world.damageSources().inFire(), 1.f);
        }

        super.stepOn(world, pos, state, entity);
    }

    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        double d = (double) pos.getX() + 0.5;
        double e = pos.getY() + 0.24;
        double f = (double) pos.getZ() + 0.5;
        if (random.nextDouble() < 0.1)
            world.playLocalSound(d, e, f, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0f, 1.0f, false);

        Direction direction = state.getValue(FACING);
        Direction.Axis axis = direction.getAxis();
        double h = random.nextDouble() * 0.6 - 0.3;
        double i = axis == Direction.Axis.X ? (double) direction.getStepX() * 0.52 : h;
        double j = random.nextDouble() * 6.0 / 16.0;
        double k = axis == Direction.Axis.Z ? (double) direction.getStepZ() * 0.52 : h;
        world.addParticle(ParticleTypes.SMOKE, d + i, e + j, f + k, 0.0, 0.0, 0.0);
        world.addParticle(ParticleTypes.FLAME, d + i, e + j, f + k, 0.0, 0.0, 0.0);
    }


    private void extinguish(BlockState state, Level world, BlockPos pos) {
        world.setBlockAndUpdate(pos, state.setValue(LIT, false));
        double dx = pos.getX() + .5d;
        double dy = pos.getY();
        double dz = pos.getZ() + .5d;
        world.playLocalSound(dx, dy, dz, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5f, 2.6f, false);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, BlockGetter world, List<Component> tooltip, TooltipFlag tooltipContext) {
        tooltip.add(Component.translatable("block.meadow.stove.tooltip").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
    }
}


