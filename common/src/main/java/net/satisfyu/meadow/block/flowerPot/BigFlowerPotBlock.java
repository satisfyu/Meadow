package net.satisfyu.meadow.block.flowerPot;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.satisfyu.meadow.util.MeadowTags;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Supplier;


public class BigFlowerPotBlock extends ModFlowerPotBlock implements BlockEntityProvider {
	private static final VoxelShape SHAPE;
	private static final Supplier<VoxelShape> voxelShapeSupplier = () -> {
		VoxelShape shape = VoxelShapes.empty();
		shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0, 0.25, 0.75, 0.4375, 0.75), BooleanBiFunction.OR);
		shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.1875, 0, 0.1875, 0.25, 0.5625, 0.8125), BooleanBiFunction.OR);
		shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.75, 0, 0.1875, 0.8125, 0.5625, 0.8125), BooleanBiFunction.OR);
		shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0, 0.1875, 0.75, 0.5625, 0.25), BooleanBiFunction.OR);
		shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0, 0.75, 0.75, 0.5625, 0.8125), BooleanBiFunction.OR);
		return shape;
	};;

	public BigFlowerPotBlock(AbstractBlock.Settings settings) {
		super(settings);
	}

	@Override
	public boolean fitInPot(ItemStack item) {
		return item.isIn(MeadowTags.BIG_FLOWER);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
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

	static {
		SHAPE = voxelShapeSupplier.get();
	}
}
