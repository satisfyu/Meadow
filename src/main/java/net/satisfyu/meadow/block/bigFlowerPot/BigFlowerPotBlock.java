package net.satisfyu.meadow.block.bigFlowerPot;

import net.minecraft.block.*;
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

import java.util.*;
import java.util.function.Supplier;


public class BigFlowerPotBlock extends HFacingBlock implements BlockEntityProvider {
	public BigFlowerPotBlock(AbstractBlock.Settings settings) {
		super(settings);
	}

	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;


	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (world.isClient) return ActionResult.SUCCESS;
		BigFlowerPotBlockEntity be = (BigFlowerPotBlockEntity) world.getBlockEntity(pos);
		if(be == null || player.isSneaking()) return ActionResult.PASS;
		ItemStack stack = player.getStackInHand(hand);
		List<Item> list = be.getItems();

		if (stack.isEmpty()) {
			Optional<Item> stackOutOfList = list.stream().findFirst();
			if(stackOutOfList.isPresent()){
				Item item = stackOutOfList.get();
				player.giveItemStack(new ItemStack(item));
				list.remove(item);
				be.setItems(list);
				return ActionResult.SUCCESS;
			}
		} else if (stack.isIn(Tags.BIG_FLOWER) && list.size() < 1) {
			list.add(stack.getItem());
			stack.decrement(1);
			be.setItems(list);
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
			if (blockEntity instanceof BigFlowerPotBlockEntity be) {
				for(Item stack : be.getItems()){
					ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(stack));
				}
				world.updateComparators(pos,this);
			}
			super.onStateReplaced(state, world, pos, newState, moved);
		}
	}


	private static final Supplier<VoxelShape> voxelShapeSupplier = () -> {
		VoxelShape shape = VoxelShapes.empty();
		shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0, 0.25, 0.75, 0.4375, 0.75), BooleanBiFunction.OR);
		shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.1875, 0, 0.1875, 0.25, 0.5625, 0.8125), BooleanBiFunction.OR);
		shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.75, 0, 0.1875, 0.8125, 0.5625, 0.8125), BooleanBiFunction.OR);
		shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0, 0.1875, 0.75, 0.5625, 0.25), BooleanBiFunction.OR);
		shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0, 0.75, 0.75, 0.5625, 0.8125), BooleanBiFunction.OR);
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

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new BigFlowerPotBlockEntity(pos, state);
	}

	@Override
	public void appendTooltip(ItemStack itemStack, BlockView world, List<Text> tooltip, TooltipContext tooltipContext) {
		tooltip.add(Text.translatable("block.meadow.canbeplaced.tooltip").formatted(Formatting.ITALIC, Formatting.GRAY));
	}
}
