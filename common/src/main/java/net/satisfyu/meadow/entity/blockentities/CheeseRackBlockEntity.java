package net.satisfyu.meadow.entity.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.satisfyu.meadow.registry.BlockEntityRegistry;
import net.satisfyu.meadow.util.GeneralUtil;

import java.util.ArrayList;
import java.util.List;

public class CheeseRackBlockEntity extends BlockEntity {

    private DefaultedList<ItemStack> inventory;

    public CheeseRackBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.CHEESE_RACK_BLOCK_ENTITY.get(), pos, state);
        this.inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, this.inventory);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);
        Inventories.readNbt(nbt, this.inventory);
    }

    public ItemStack removeStack(int slot) {
        ItemStack stack = inventory.set(slot, ItemStack.EMPTY);
        markDirty();
        return stack;
    }

    public void setStack(int slot, ItemStack stack) {
        inventory.set(slot, stack);
        markDirty();
    }

    public boolean hasStack(int slot) {
        return !inventory.get(slot).isEmpty();
    }

    public ItemStack getStack(int slot) {
        return inventory.get(slot);
    }

    public Item[] getItems() {
        List<Item> items = new ArrayList<>();
        for (ItemStack stack : inventory) {
            if (!stack.isEmpty()) {
                items.add(stack.getItem());
            }
        }
        return items.toArray(new Item[0]);
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
        if (world != null && !world.isClient()) {
            Packet<ClientPlayPacketListener> updatePacket = toUpdatePacket();

            for (ServerPlayerEntity player : GeneralUtil.tracking((ServerWorld) world, getPos())) {
                player.networkHandler.sendPacket(updatePacket);
            }
        }
        super.markDirty();
    }

}