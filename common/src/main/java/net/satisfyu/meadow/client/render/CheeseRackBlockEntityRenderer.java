package net.satisfyu.meadow.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfyu.meadow.block.CheeseRackBlock;
import net.satisfyu.meadow.entity.CheeseRackBlockEntity;

import static de.cristelknight.doapi.client.ClientUtil.renderBlock;

public class CheeseRackBlockEntityRenderer implements BlockEntityRenderer<CheeseRackBlockEntity> {

    @SuppressWarnings("unused")
    public CheeseRackBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
    }

    @Override
    public void render(CheeseRackBlockEntity entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        if (!entity.hasLevel()) {
            return;
        }
        BlockState selfState = entity.getBlockState();
        if (selfState.getBlock() instanceof CheeseRackBlock) {
            matrices.pushPose();
            applyBlockAngle(matrices, selfState, 180);
            matrices.translate(-0.5f, 0.05f, -0.5f);
            if (entity.hasStack(0)) {
                BlockState state = ((BlockItem) entity.getStack(0).getItem()).getBlock().defaultBlockState();
                renderBlock(state, matrices, vertexConsumers, entity);
            }
            matrices.translate(0f, 0.4f, 0f);
            if (entity.hasStack(1)) {
                BlockState state = ((BlockItem) entity.getStack(1).getItem()).getBlock().defaultBlockState();
                renderBlock(state, matrices, vertexConsumers, entity);
            }
            matrices.popPose();
        }
    }

    public static void applyBlockAngle(PoseStack matrices, BlockState state, float angleOffset) {
        float angle = state.getValue(CheeseRackBlock.FACING).toYRot();
        matrices.translate(0.5, 0, 0.5);
        matrices.mulPose(Axis.YP.rotationDegrees(angleOffset - angle));
    }
}