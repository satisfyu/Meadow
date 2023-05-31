package net.satisfyu.meadow.block.custom;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.util.DyeColor;

public class MeadowBedBlock extends BedBlock {
    public MeadowBedBlock(Settings settings) {
        super(DyeColor.BLACK, settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;

    }
}
