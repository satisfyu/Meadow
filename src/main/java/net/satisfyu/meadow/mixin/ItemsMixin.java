package net.satisfyu.meadow.mixin;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.satisfyu.meadow.block.ModBlocks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.satisfyu.meadow.block.custom.BowlBlock.FACING;

@Mixin(ServerPlayerInteractionManager.class)
public abstract class ItemsMixin {


    @Shadow @Final protected ServerPlayerEntity player;

    @Shadow protected ServerWorld world;

    @Inject(
            method = "interactBlock",
            at = @At(value = "HEAD"))


    private void register(ServerPlayerEntity player, World world, ItemStack stack, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        if(stack.getItem().equals(Items.BOWL)){
            BlockPos pos = hitResult.getBlockPos();
            if(Block.sideCoversSmallSquare(world, pos, Direction.UP) && world.canSetBlock(pos.up()) && world.getBlockState(pos.up()).isAir()){
                world.setBlockState(pos.up(), ModBlocks.BOWL_EMPTY.getDefaultState().with(FACING, player.getHorizontalFacing().getOpposite()));
                world.playSound(null, pos, BlockSoundGroup.SCAFFOLDING.getPlaceSound(), SoundCategory.BLOCKS, 1.0f, 1.0f);
                stack.decrement(1);
            }
        }
    }
}
