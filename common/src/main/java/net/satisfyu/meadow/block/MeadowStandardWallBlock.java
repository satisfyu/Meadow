package net.satisfyu.meadow.block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfyu.meadow.entity.blockentities.StandardBlockEntity;
import org.jetbrains.annotations.Nullable;


public class MeadowStandardWallBlock extends AbstractStandardWallBlock {
    public MeadowStandardWallBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public ResourceLocation getRenderTexture() {
        return MeadowStandardBlock.TEXTURE;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return StandardBlockEntity::tick;
    }
}