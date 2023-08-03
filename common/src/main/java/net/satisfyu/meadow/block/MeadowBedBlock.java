package net.satisfyu.meadow.block;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

public class MeadowBedBlock extends BedBlock {
    public MeadowBedBlock(Properties settings) {
        super(DyeColor.BLACK, settings);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
