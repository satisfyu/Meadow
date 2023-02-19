package net.satisfyu.meadow.block.bigFlowerPot;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.satisfyu.meadow.entity.ModEntities;

import java.util.ArrayList;
import java.util.List;

public class BigFlowerPotBlockEntity extends BlockEntity {

	private List<Item> items = new ArrayList<>();

	public BigFlowerPotBlockEntity(BlockPos pos, BlockState state) {
		super(ModEntities.BIG_FLOWER_POT_BLOCK_ENTITY, pos, state);
	}
	
	@Override
	public void writeNbt(NbtCompound nbt) {
		writeItems(nbt, items);
		super.writeNbt(nbt);
	}
	
	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		items = readItems(nbt);
	}
	
	public static void writeItems(NbtCompound nbt, List<Item> stacks) {
		NbtList nbtList = new NbtList();
		for (Item stack : stacks) {
			ItemStack itemStack = new ItemStack(stack);
			if (itemStack.isEmpty())
				continue;
			NbtCompound nbtCompound = new NbtCompound();
			itemStack.writeNbt(nbtCompound);
			nbtList.add(nbtCompound);
		}
		nbt.put("Items", nbtList);
	}
	
	public static List<Item> readItems(NbtCompound nbt) {
		NbtList nbtList = nbt.getList("Items", NbtElement.COMPOUND_TYPE);
		List<Item> itemList = new ArrayList<>();
		for (int i = 0; i < nbtList.size(); ++i) {
			NbtCompound nbtCompound = nbtList.getCompound(i);
			itemList.add(ItemStack.fromNbt(nbtCompound).getItem());
		}
		return itemList;
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
		markDirty();
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

			for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())) {
				player.networkHandler.sendPacket(updatePacket);

				if(world != null);

			}
		}
		super.markDirty();
	}
	
}