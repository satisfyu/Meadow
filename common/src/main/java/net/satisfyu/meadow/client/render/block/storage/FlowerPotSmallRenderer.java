package net.satisfyu.meadow.client.render.block.storage;

import com.mojang.blaze3d.vertex.PoseStack;
import de.cristelknight.doapi.client.render.block.storage.StorageTypeRenderer;
import de.cristelknight.doapi.common.block.entity.StorageBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import static de.cristelknight.doapi.client.ClientUtil.renderBlock;

public class FlowerPotSmallRenderer implements StorageTypeRenderer {

    @Override
    public void render(StorageBlockEntity storageBlockEntity, PoseStack poseStack, MultiBufferSource multiBufferSource, NonNullList<ItemStack> nonNullList) {
        ItemStack itemStack = nonNullList.get(0);
        if (itemStack.getItem() instanceof BlockItem blockItem) {
            BlockState state = blockItem.getBlock().defaultBlockState();
            poseStack.translate(-0.5f, 0.3f, -0.5f);
            renderBlock(state, poseStack, multiBufferSource, storageBlockEntity);
        }
    }
}