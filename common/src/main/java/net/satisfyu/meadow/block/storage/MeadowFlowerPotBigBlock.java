package net.satisfyu.meadow.block.storage;

import de.cristelknight.doapi.common.block.storage.FlowerPotBigBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class MeadowFlowerPotBigBlock extends FlowerPotBigBlock {
    private static final VoxelShape SHAPE;

    private static final Supplier<VoxelShape> voxelShapeSupplier = () -> {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.25, 0, 0.25, 0.75, 0.4375, 0.75), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.1875, 0, 0.1875, 0.25, 0.5625, 0.8125), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.75, 0, 0.1875, 0.8125, 0.5625, 0.8125), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.25, 0, 0.1875, 0.75, 0.5625, 0.25), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.25, 0, 0.75, 0.75, 0.5625, 0.8125), BooleanOp.OR);
        return shape;
    };

    public MeadowFlowerPotBigBlock(Properties settings) {
        super(settings);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    static {
        SHAPE = voxelShapeSupplier.get();
    }
}