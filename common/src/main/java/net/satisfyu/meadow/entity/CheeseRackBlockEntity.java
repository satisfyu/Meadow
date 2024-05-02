package net.satisfyu.meadow.entity;

import de.cristelknight.doapi.common.util.GeneralUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfyu.meadow.registry.BlockEntityRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CheeseRackBlockEntity extends BlockEntity {

    private NonNullList<ItemStack> inventory;

    public CheeseRackBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.CHEESE_RACK_BLOCK_ENTITY.get(), pos, state);
        this.inventory = NonNullList.withSize(2, ItemStack.EMPTY);
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        ContainerHelper.saveAllItems(nbt, this.inventory);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.inventory = NonNullList.withSize(2, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt, this.inventory);
    }

    public ItemStack removeStack(int slot) {
        ItemStack stack = inventory.set(slot, ItemStack.EMPTY);
        setChanged();
        return stack;
    }

    public void setStack(int slot, ItemStack stack) {
        inventory.set(slot, stack);
        setChanged();
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
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

    @Override
    public void setChanged() {
        if (level != null && !level.isClientSide()) {
            Packet<ClientGamePacketListener> updatePacket = getUpdatePacket();

            for (ServerPlayer player : GeneralUtil.tracking((ServerLevel) level, getBlockPos())) {
                player.connection.send(updatePacket);
            }
        }
        super.setChanged();
    }

}