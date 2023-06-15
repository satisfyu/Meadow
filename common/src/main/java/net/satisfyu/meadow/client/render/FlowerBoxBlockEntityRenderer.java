package net.satisfyu.meadow.client.render;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.util.math.Vec3f;
import net.satisfyu.meadow.block.FlowerBoxBlock;
import net.satisfyu.meadow.entity.blockentities.FlowerBoxBlockEntity;

import static net.satisfyu.meadow.util.ClientUtil.renderBlock;

public class FlowerBoxBlockEntityRenderer implements BlockEntityRenderer<FlowerBoxBlockEntity> {

    public FlowerBoxBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {

    }

    @Override
    public void render(FlowerBoxBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (!entity.hasWorld()) {
            return;
        }
        BlockState selfState = entity.getCachedState();
        if (selfState.getBlock() instanceof FlowerBoxBlock) {
            matrices.push();
            applyBlockAngle(matrices, selfState);
            matrices.translate(-0.25f, 0.25f, 0.25f);
            if (!entity.isSlotEmpty(0)) {
                BlockState state = ((BlockItem) entity.getFlower(0).getItem()).getBlock().getDefaultState();
                renderBlock(state, matrices, vertexConsumers, entity);
            }
            matrices.translate(0.5f, 0f, 0f);
            if (!entity.isSlotEmpty(1)) {
                BlockState state = ((BlockItem) entity.getFlower(1).getItem()).getBlock().getDefaultState();
                renderBlock(state, matrices, vertexConsumers, entity);
            }
            matrices.pop();
        }
    }

    public static void applyBlockAngle(MatrixStack matrices, BlockState state) {
        switch (state.get(FlowerBoxBlock.FACING)) {
            case EAST -> {
                matrices.translate(-0.5f, 0f, 1f);
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90));
            }
            case SOUTH -> {
                matrices.translate(1f, 0f, 1f);
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180));
            }
            case WEST -> {
                matrices.translate(1.5f, 0f, 0f);
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(270));
            }
        }
    }


}