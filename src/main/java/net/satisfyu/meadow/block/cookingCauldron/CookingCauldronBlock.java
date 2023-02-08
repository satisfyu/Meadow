package net.satisfyu.meadow.block.cookingCauldron;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.block.custom.FrameBlock;
import net.satisfyu.meadow.entity.ModEntities;
import net.satisfyu.meadow.particle.ModParticles;
import net.satisfyu.meadow.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CookingCauldronBlock extends BlockWithEntity {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final IntProperty VAR = IntProperty.of("var", 0, 7);

    public static final BooleanProperty HANGING = BooleanProperty.of("hanging");

    public static final BooleanProperty DONE = BooleanProperty.of("done");

    private static final VoxelShape SHAPE = Block.createCuboidShape(3, 0, 3, 13, 5, 13);

    private static final VoxelShape SHAPE_H = Block.createCuboidShape(3, 0, 3, 13, 21, 13);

    public CookingCauldronBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(VAR, 0).with(HANGING, false).with(DONE, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Boolean hanging = state.get(HANGING);
        if(hanging) return SHAPE_H;
        return SHAPE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModEntities.COOKING_CAULDRON_BLOCK_ENTITY, (world1, pos, state1, be) -> be.tick(world1, pos, state1, be));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CookingCauldronBlockEntity(pos, state);
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.IGNORE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof CookingCauldronBlockEntity be) {
                ItemScatterer.spawn(world, pos, be);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(VAR, HANGING, DONE, FACING);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return Block.sideCoversSmallSquare(world, pos.down(), Direction.UP);
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
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if(state.get(HANGING)){
            FrameBlock.displayTickLikeCampfire(state, world, pos, random, world.getBlockState(pos.down()).isOf(Blocks.HAY_BLOCK));
        }
        if (state.get(VAR) <= 0) {
            return;
        }
        double d = (double)pos.getX() + 0.5;
        double e = pos.getY() + (state.get(HANGING) ? 0.9 : 0.45);
        double f = (double)pos.getZ() + 0.5;
        if (random.nextDouble() < 0.1) {
            world.playSound(d, e, f, ModSounds.COOKING_CAULDRON, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
        }
        if(random.nextFloat() < 0.15f && !state.get(DONE)){
            world.addImportantParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, true, (double)pos.getX() + 0.5 + random.nextDouble() / 3.0 * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + random.nextDouble() + random.nextDouble() + (state.get(HANGING) ? 0.45 : 0), (double)pos.getZ() + 0.5 + random.nextDouble() / 3.0 * (double)(random.nextBoolean() ? 1 : -1), 0.0, 0.07, 0.0);
        }
        java.util.Random r = new java.util.Random();
        if(r.nextFloat() < 0.3f){
            if(!state.get(DONE)){
                world.addParticle(ParticleTypes.SMOKE, d, e, f, r.nextFloat(-0.1f, 0.1f), 0, r.nextFloat(-0.1f, 0.1f));
                world.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, d, e, f, r.nextFloat(-0.1f, 0.1f), 0, r.nextFloat(-0.1f, 0.1f));
                world.addParticle(ParticleTypes.BUBBLE, d, e, f, r.nextFloat(-0.1f, 0.1f), 0, r.nextFloat(-0.1f, 0.1f));
                world.addParticle(ParticleTypes.BUBBLE_POP, d, e, f, r.nextFloat(-0.1f, 0.1f), 0, r.nextFloat(-0.1f, 0.1f));
            }
            else {
                world.addParticle(ParticleTypes.BUBBLE_POP, d, e , f, r.nextFloat(-0.03f, 0.03f), 0, r.nextFloat(-0.03f, 0.03f));
            }
        }
    }
    @Override
    public void appendTooltip(ItemStack itemStack, BlockView world, List< Text > tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("block.meadow.cauldron.tooltip").formatted(Formatting.ITALIC, Formatting.GRAY));
    }
    
    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> list = new ArrayList<>();
        list.add(new ItemStack(this));
        if (state.get(HANGING)) {
            list.add(new ItemStack(ModBlocks.FRAME));
        }
        return list;
    }
    
}