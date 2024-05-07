package net.satisfyu.meadow.client.render.block.storage;

import com.mojang.blaze3d.vertex.PoseStack;
import de.cristelknight.doapi.client.ClientUtil;
import de.cristelknight.doapi.client.render.block.storage.api.StorageTypeRenderer;
import de.cristelknight.doapi.common.block.entity.StorageBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

public class CheeseRackRenderer implements StorageTypeRenderer {
    @Override
    public void render(StorageBlockEntity storageBlockEntity, PoseStack poseStack, MultiBufferSource multiBufferSource, NonNullList<ItemStack> nonNullList) {
        poseStack.translate(-0.5f, 0.05f, -0.5f);
        if (!nonNullList.isEmpty()) {
            ItemStack itemStack1 = nonNullList.get(0);
            if(itemStack1.getItem() instanceof BlockItem blockItem){
                ClientUtil.renderBlockFromItem(blockItem, poseStack, multiBufferSource, storageBlockEntity);
            }
        }
        if (nonNullList.size() > 1) {
            ItemStack itemStack2 = nonNullList.get(1);
            if(itemStack2.getItem() instanceof BlockItem blockItem){
                poseStack.translate(0f, 0.4f, 0f);
                ClientUtil.renderBlockFromItem(blockItem, poseStack, multiBufferSource, storageBlockEntity);
            }
        }
    }
}
