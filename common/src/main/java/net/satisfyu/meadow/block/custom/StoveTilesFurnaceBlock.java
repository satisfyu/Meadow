package net.satisfyu.meadow.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.satisfyu.meadow.block.woodenCauldren.DamageSourceRegistry;
import net.satisfyu.meadow.util.BlockStateUtils;
import net.satisfyu.meadow.util.MathUtils;
import net.satisfyu.meadow.util.MeadowTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StoveTilesFurnaceBlock extends Block {


    public static final VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 4, 2, 4), Block.createCuboidShape(12, 0, 0, 16, 2, 4), Block.createCuboidShape(0, 0, 12, 16, 2, 16), Block.createCuboidShape(12, 0, 12, 16, 2, 16));

    public static final VoxelShape SHAPE_BIG = VoxelShapes.union(SHAPE, Block.createCuboidShape(0, 2, 0, 16, 16, 16));

    public static final VoxelShape SHAPE_SMALL = VoxelShapes.union(SHAPE, Block.createCuboidShape(0, 2, 0, 16, 6, 16));

    private final boolean isBig;

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = Properties.LIT;

    public StoveTilesFurnaceBlock(Settings settings, boolean isBig) {
        super(settings);
        this.isBig = isBig;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return isBig ? SHAPE_BIG : SHAPE_SMALL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING, LIT);
    }



    protected ActionResult tryLightUpByPlayerHand(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand) {
        ActionResult actionResult = ActionResult.PASS;
        ItemStack stackHand = player.getStackInHand(hand);

        if (stackHand.getItem() instanceof FlintAndSteelItem) {
            world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.f,
                    MathUtils.RAND.nextFloat() * .4f + .8f);
            stackHand.damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(hand));

            actionResult = ActionResult.SUCCESS;
        } else if (stackHand.getItem() instanceof FireChargeItem) {
            world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.f,
                    (MathUtils.RAND.nextFloat() - MathUtils.RAND.nextFloat()) * .2f + 1.f);
            if (!player.isCreative()) {
                stackHand.decrement(1);
            }

            actionResult = ActionResult.SUCCESS;
        }

        if (actionResult.isAccepted()) {
            world.setBlockState(pos, state.with(LIT, Boolean.TRUE), BlockStateUtils.DEFAULT_AND_RERENDER);
        }

        return actionResult;
    }



    protected ActionResult tryExtinguishByPlayerHand(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand) {
        ItemStack stackHand = player.getStackInHand(hand);
        Item usedItem = stackHand.getItem();

        if (!(stackHand.isIn(MeadowTags.SHOVEL)) && usedItem != Items.WATER_BUCKET) {
            return ActionResult.PASS;
        }

        extinguish(state, world, pos);
        if (!player.isCreative() && usedItem == Items.WATER_BUCKET) {
            player.setStackInHand(hand, new ItemStack(Items.BUCKET));
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return getDefaultState().with(FACING, context.getPlayerFacing().getOpposite()).with(LIT, true);
    }
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        boolean isLit = world.getBlockState(pos).get(LIT);
        if (isLit && !entity.isFireImmune() && entity instanceof LivingEntity livingEntity &&
                !EnchantmentHelper.hasFrostWalker(livingEntity)) {
            entity.damage(DamageSourceRegistry.COBBLESTONE_FURNACE_BLOCK, 1.f);
        }

        super.onSteppedOn(world, pos, state, entity);
    }
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        double d = (double)pos.getX() + 0.5;
        double e = pos.getY() + 0.24;
        double f = (double)pos.getZ() + 0.5;
        if (random.nextDouble() < 0.1)
            world.playSound(d, e, f, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0f, 1.0f, false);

        Direction direction = state.get(FACING);
        Direction.Axis axis = direction.getAxis();
        double h = random.nextDouble() * 0.6 - 0.3;
        double i = axis == Direction.Axis.X ? (double)direction.getOffsetX() * 0.52 : h;
        double j = random.nextDouble() * 6.0 / 16.0;
        double k = axis == Direction.Axis.Z ? (double)direction.getOffsetZ() * 0.52 : h;
        world.addParticle(ParticleTypes.SMOKE, d + i, e + j, f + k, 0.0, 0.0, 0.0);
        world.addParticle(ParticleTypes.FLAME, d + i, e + j, f + k, 0.0, 0.0, 0.0);
    }



    private void extinguish(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, state.with(LIT, false));
        double dx = pos.getX() + .5d;
        double dy = pos.getY();
        double dz = pos.getZ() + .5d;
        world.playSound(dx, dy, dz, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5f, 2.6f, false);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, BlockView world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("block.meadow.stove.tooltip").formatted(Formatting.ITALIC, Formatting.GRAY));
    }
}


