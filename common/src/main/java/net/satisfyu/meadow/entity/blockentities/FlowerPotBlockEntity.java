package net.satisfyu.meadow.entity.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.satisfyu.meadow.util.GeneralUtil;

public class FlowerPotBlockEntity extends BlockEntity {
    private Item flower;
    public static final String FLOWER_KEY ="flower";

    public FlowerPotBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        flower = null;
    }

    public Item getFlower() {
        return flower;
    }

    public void setFlower(Item flower) {
        this.flower = flower;
        markDirty();
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        writeFlower(nbt, flower);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        flower = readFlower(nbt);
    }

    public void writeFlower(NbtCompound nbt, Item flower) {
        NbtCompound nbtCompound = new NbtCompound();
        if (flower != null) {
            flower.getDefaultStack().writeNbt(nbtCompound);
        }
        nbt.put(FLOWER_KEY, nbtCompound);
    }

    public Item readFlower(NbtCompound nbt) {
        super.readNbt(nbt);
        if(nbt.contains(FLOWER_KEY)) {
            NbtCompound nbtCompound = nbt.getCompound(FLOWER_KEY);
            if (!nbtCompound.isEmpty()) {
                return ItemStack.fromNbt(nbtCompound).getItem();
            }
        }
        return null;
    }

    @Override
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }

    @Override
    public void markDirty() {
        if(world != null && !world.isClient()) {
            Packet<ClientPlayPacketListener> updatePacket = toUpdatePacket();

            for (ServerPlayerEntity player : GeneralUtil.tracking((ServerWorld) world, getPos())) {
                player.networkHandler.sendPacket(updatePacket);
            }
        }
        super.markDirty();
    }
}
