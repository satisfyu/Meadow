package net.satisfyu.meadow.render;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.math.Vec3f;
import net.satisfyu.meadow.block.cheeseRack.CheeseRackBlock;
import net.satisfyu.meadow.block.cheeseRack.CheeseRackBlockEntity;

import java.util.List;

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
            List<Item> items = entity.getItems();
            matrices.push();
            applyBlockAngle(matrices, selfState, 180);
            if (!items.isEmpty()) {
                BlockState state = ((BlockItem) items.get(0)).getBlock().getDefaultState();
                matrices.translate(0f, 0f, 0f);
                renderBlock(state, matrices, vertexConsumers, entity);
                if (items.size() > 1) {
                    state = ((BlockItem) items.get(1)).getBlock().getDefaultState();
                    matrices.translate(0f, 0.5f, 0f);
                    renderBlock(state, matrices, vertexConsumers, entity);
                }
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