package net.satisfyu.meadow.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.satisfyu.meadow.entity.BigFlowerPotBlockEntity;
import net.satisfyu.meadow.entity.FlowerPotBlockEntity;
import net.satisfyu.meadow.registry.TagRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;


public class BigFlowerPotBlock extends FlowerPotBlock implements EntityBlock {
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

    public BigFlowerPotBlock(Properties settings) {
        super(settings);
    }

    @Override
    public boolean fitInPot(ItemStack item) {
        return item.is(TagRegistry.BIG_FLOWER);
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BigFlowerPotBlockEntity(pos, state);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, BlockGetter world, List<Component> tooltip, TooltipFlag tooltipContext) {
        tooltip.add(Component.translatable("block.meadow.canbeplaced.tooltip").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
    }

    static {
        SHAPE = voxelShapeSupplier.get();
    }
}
