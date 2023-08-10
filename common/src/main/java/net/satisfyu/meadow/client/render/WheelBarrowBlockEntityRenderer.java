package net.satisfyu.meadow.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfyu.meadow.block.WheelBarrowBlock;
import net.satisfyu.meadow.entity.blockentities.WheelBarrowBlockEntity;

import static net.satisfyu.meadow.util.ClientUtil.renderBlock;

public class WheelBarrowBlockEntityRenderer implements BlockEntityRenderer<WheelBarrowBlockEntity> {

    public WheelBarrowBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
    }

    @Override
    public void render(WheelBarrowBlockEntity entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        if (!entity.hasLevel()) {
            return;
        }
        BlockState selfState = entity.getBlockState();
        if (selfState.getBlock() instanceof WheelBarrowBlock) {
            Item item = entity.getFlower();
            matrices.pushPose();
            if (item instanceof BlockItem) {
                BlockState state = ((BlockItem) item).getBlock().defaultBlockState();
                matrices.translate(0f, 0.625f, 0f);
                renderBlock(state, matrices, vertexConsumers, entity);
            }
        }
        matrices.popPose();
    }
}