package net.satisfyu.meadow.block;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.entity.blockentities.StandardBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MeadowStandardBlock extends AbstractStandardBlock {
    public static final ResourceLocation TEXTURE = Meadow.MOD_ID("textures/standard/meadow_standard.png");

    public MeadowStandardBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public ResourceLocation getRenderTexture() {
        return TEXTURE;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return StandardBlockEntity::tick;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, BlockGetter world, List<Component> tooltip, TooltipFlag tooltipContext) {
        tooltip.add(Component.translatable("tooltip.meadow.thankyou_1").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
        tooltip.add(Component.empty());
        tooltip.add(Component.translatable("tooltip.meadow.thankyou_2").withStyle(ChatFormatting.DARK_PURPLE));
        tooltip.add(Component.translatable("tooltip.meadow.thankyou_4").withStyle(ChatFormatting.BLUE));
        tooltip.add(Component.empty());
        tooltip.add(Component.translatable("tooltip.meadow.thankyou_3").withStyle(ChatFormatting.GOLD));

    }
}