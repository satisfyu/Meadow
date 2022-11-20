package net.satisfyu.meadow.block.custom;

import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import net.satisfyu.meadow.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

public class StoveBlockWood extends StoveBlock {

    public static final BooleanProperty LIT = BooleanProperty.of("lit");

    public StoveBlockWood(Settings settings, Direction directionToCheck) {
        super(settings, directionToCheck);
        this.setDefaultState(this.getDefaultState().with(LIT, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(world.isClient) return ActionResult.SUCCESS;
        boolean lit = state.get(LIT);
        ItemStack stack = player.getStackInHand(hand);
        boolean isFlint;
        if(lit && stack.isIn(ConventionalItemTags.SHOVELS)){
            world.setBlockState(pos, state.with(LIT, false));
            world.syncWorldEvent(null, WorldEvents.FIRE_EXTINGUISHED, pos, 0);
            if(!player.getAbilities().creativeMode) stack.damage(1, player, p -> p.sendToolBreakStatus(hand));
            return ActionResult.SUCCESS;
        }
        else if(!lit && ((isFlint = stack.getItem() instanceof FlintAndSteelItem) || stack.getItem() instanceof FireChargeItem)) {
            world.setBlockState(pos, state.with(LIT, true));
            if(!player.getAbilities().creativeMode){
                if (isFlint) stack.damage(1, player, p -> p.sendToolBreakStatus(hand));
                else stack.decrement(1);
            }
            world.playSound(null, pos, isFlint ? SoundEvents.ITEM_FLINTANDSTEEL_USE : SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1, 1);
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if(state.get(LIT)){
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
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(LIT);
    }
}
