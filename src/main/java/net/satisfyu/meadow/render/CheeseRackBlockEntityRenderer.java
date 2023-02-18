package net.satisfyu.meadow.render;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.util.math.Vec3f;
import net.satisfyu.meadow.block.cheeseRack.CheeseRackBlock;
import net.satisfyu.meadow.block.cheeseRack.CheeseRackBlockEntity;

import static net.satisfyu.meadow.util.ClientUtil.renderBlock;

public class CheeseRackBlockEntityRenderer implements BlockEntityRenderer<CheeseRackBlockEntity> {
    
    public CheeseRackBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {}
    
    @Override
    public void render(CheeseRackBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (!entity.hasWorld()) {
            return;
        }
        BlockState selfState = entity.getCachedState();
        if (selfState.getBlock() instanceof CheeseRackBlock) {
            matrices.push();
            applyBlockAngle(matrices, selfState, 180);
            matrices.translate(-0.5f, 0.1f, -0.5f);
            if (entity.hasStack(0)) {
                BlockState state = ((BlockItem) entity.getStack(0).getItem()).getBlock().getDefaultState();
                renderBlock(state, matrices, vertexConsumers, entity);
            }
            matrices.translate(0f, 0.4f, 0f);
            if (entity.hasStack(1)) {
                BlockState state = ((BlockItem) entity.getStack(1).getItem()).getBlock().getDefaultState();
                renderBlock(state, matrices, vertexConsumers, entity);
            }
            matrices.pop();
        }
    }
    
    public static void applyBlockAngle(MatrixStack matrices, BlockState state, float angleOffset) {
        float angle = state.get(CheeseRackBlock.FACING).asRotation();
        matrices.translate(0.5, 0, 0.5);
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(angleOffset - angle));
    }
}