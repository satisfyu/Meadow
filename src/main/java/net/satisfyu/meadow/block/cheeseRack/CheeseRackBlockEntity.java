package net.satisfyu.meadow.block.cheeseRack;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.BlockPos;
import net.satisfyu.meadow.entity.ModEntities;

import java.util.ArrayList;
import java.util.List;

public class CheeseRackBlockEntity extends BlockEntity{


    private List<Item> items = new ArrayList<>();


    public CheeseRackBlockEntity(BlockPos pos, BlockState state) {
        super(ModEntities.CHEESE_RACK_BLOCK_ENTITY, pos, state);
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
        for(Item stack : stacks) {
            ItemStack itemStack = new ItemStack(stack);
            if (itemStack.isEmpty()) continue;
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

    public List<Item> getItems(){
        return items;
    }

    public void setItems(List<Item> items){
        this.items = items;
    }

}
