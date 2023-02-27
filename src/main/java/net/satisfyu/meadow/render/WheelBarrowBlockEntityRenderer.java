package net.satisfyu.meadow.render;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.math.Vec3f;
import net.satisfyu.meadow.block.flowerPot.WheelBarrowBlock;
import net.satisfyu.meadow.block.flowerPot.WheelBarrowBlockEntity;

import static net.satisfyu.meadow.util.ClientUtil.renderBlock;

public class WheelBarrowBlockEntityRenderer implements BlockEntityRenderer<WheelBarrowBlockEntity> {

    public WheelBarrowBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {}
    
    @Override
    public void render(WheelBarrowBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (!entity.hasWorld()) {
            return;
        }
        BlockState selfState = entity.getCachedState();
        if (selfState.getBlock() instanceof WheelBarrowBlock) {
            Item item = entity.getFlower();
            matrices.push();
            //applyBlockAngle(matrices, selfState, 180);
            if (item instanceof BlockItem) {
                BlockState state = ((BlockItem) item).getBlock().getDefaultState();
                matrices.translate(0f, 0.625f, 0f);
                renderBlock(state, matrices, vertexConsumers, entity);
            }
        }
        matrices.pop();
    }

    //NoUse
    public static void applyBlockAngle(MatrixStack matrices, BlockState state, float angleOffset) {
        float angle = state.get(WheelBarrowBlock.FACING).asRotation();
        matrices.translate(1, 0, 1);
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(angleOffset - angle));
    }
}