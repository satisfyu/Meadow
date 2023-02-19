package net.satisfyu.meadow.block.cheeseRack;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
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
import net.satisfyu.meadow.block.custom.HFacingBlock;
import net.satisfyu.meadow.util.GeneralUtil;
import net.satisfyu.meadow.util.Tags;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class CheeseRackBlock extends HFacingBlock implements BlockEntityProvider {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    
    private static final Supplier<VoxelShape> voxelShapeSupplier = () -> {
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.01, 0.01, 0.01, 0.99, 0.99, 0.99), BooleanBiFunction.OR);
        return shape;
    };

    public static final Map<Direction, VoxelShape> SHAPE = Util.make(new HashMap<>(), map -> {
        for (Direction direction : Direction.Type.HORIZONTAL.stream().toList()) {
            map.put(direction, GeneralUtil.rotateShape(Direction.NORTH, direction, voxelShapeSupplier.get()));
        }
    });

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE.get(state.get(FACING));
    }

    public CheeseRackBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;
        CheeseRackBlockEntity be = (CheeseRackBlockEntity) world.getBlockEntity(pos);
        if(be == null || player.isSneaking()) return ActionResult.PASS;
        ItemStack handStack = player.getStackInHand(hand);
        int slot = -1;
        Optional<Pair<Float, Float>> optional = GeneralUtil.getRelativeHitCoordinatesForBlockFace(hit, state.get(FACING), null);
        if(optional.isPresent()){
            Pair<Float, Float> pair = optional.get();
            slot = pair.getRight() > 0.5 ? 1 : 0;
            System.out.println(pair.getLeft() + " " + pair.getRight());
        }
        
        if (slot == -1)
            return ActionResult.PASS;

        if (handStack.isEmpty() && be.hasStack(slot)) {
            player.giveItemStack(be.removeStack(slot));
            return ActionResult.SUCCESS;
        } else if (handStack.isIn(Tags.CHEESE_BLOCKS) && !be.hasStack(slot)) {
            be.setStack(slot, new ItemStack(handStack.getItem()));
            handStack.decrement(1);
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.IGNORE;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof CheeseRackBlockEntity be) {
                for(Item stack : be.getItems()){
                    ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(stack));
                }
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CheeseRackBlockEntity(pos, state);
    }
    
    @Override
    public void appendTooltip(ItemStack itemStack, BlockView world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("block.meadow.rack.tooltip").formatted(Formatting.ITALIC, Formatting.GRAY));
    }
}

