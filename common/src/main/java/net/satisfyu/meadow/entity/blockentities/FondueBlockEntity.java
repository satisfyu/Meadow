package net.satisfyu.meadow.entity.blockentities;


import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfyu.meadow.client.screen.handler.FondueGuiHandler;
import net.satisfyu.meadow.registry.BlockEntityRegistry;
import net.satisfyu.meadow.registry.ObjectRegistry;
import net.satisfyu.meadow.util.ImplementedInventory;
import net.satisfyu.meadow.registry.TagRegistry;
import org.jetbrains.annotations.Nullable;

public class FondueBlockEntity extends BlockEntity implements MenuProvider, ImplementedInventory {
    private final NonNullList<ItemStack> inventory = NonNullList.withSize(3, ItemStack.EMPTY);

    protected final ContainerData propertyDelegate;
    private int progress = 0;
    public int MAX_PROGRESS = 72;

    private int fuelAmount = 0;

    public FondueBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.FONDUE.get(), pos, state);
        this.propertyDelegate = new ContainerData() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> FondueBlockEntity.this.progress;
                    case 1 -> FondueBlockEntity.this.MAX_PROGRESS;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> FondueBlockEntity.this.progress = value;
                    case 1 -> FondueBlockEntity.this.MAX_PROGRESS = value;
                }
            }

            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public NonNullList<ItemStack> getInventory() {
        return this.inventory;
    }

    @Override
    public Component getDisplayName() {
        return Component.nullToEmpty("");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory inv, Player player) {
        return new FondueGuiHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        ContainerHelper.saveAllItems(nbt, inventory);
        nbt.putInt("fondue.progress", progress);
        nbt.putInt("fondue.fuelAmount", fuelAmount);
    }

    @Override
    public void load(CompoundTag nbt) {
        ContainerHelper.loadAllItems(nbt, inventory);
        progress = nbt.getInt("fondue.progress");
        fuelAmount = nbt.getInt("fondue.fuelAmount");
        super.load(nbt);

    }

    private void resetProgress() {
        this.progress = 0;
    }

    public static void tick(Level world, BlockPos blockPos, BlockState state, FondueBlockEntity entity) {
        if (world.isClientSide()) {
            return;
        }

        if (hasRecipe(entity) && hasFuel(entity)) {
            entity.progress++;
            setChanged(world, blockPos, state);
            if (entity.progress >= entity.MAX_PROGRESS) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
            setChanged(world, blockPos, state);
        }
    }

    private static boolean hasFuel(FondueBlockEntity entity) {
        if (entity.fuelAmount > 0) return true;
        ItemStack stack = entity.inventory.get(2);
        if (stack.is(TagRegistry.CHEESE)) {
            entity.fuelAmount = 10;
            stack.shrink(1);
            return true;
        }
        return false;
    }

    private static void craftItem(FondueBlockEntity entity) {
        entity.removeItem(0, 1);
        entity.setItem(1, new ItemStack(ObjectRegistry.CHEESE_STICK.get(), entity.getItem(1).getCount() + 1));
        entity.resetProgress();
        entity.fuelAmount--;
    }

    private static boolean hasRecipe(FondueBlockEntity entity) {
        boolean hasBreadInFirstSlot = entity.getItem(0).getItem() == Items.BREAD;
        return hasBreadInFirstSlot && canInsertAmountIntoOutputSlot(entity.inventory)
                && canInsertItemIntoOutputSlot(entity.inventory, ObjectRegistry.CHEESE_STICK.get());
    }

    private static boolean canInsertItemIntoOutputSlot(NonNullList<ItemStack> inventory, Item output) {
        return inventory.get(1).getItem() == output || inventory.get(1).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(NonNullList<ItemStack> inventory) {
        return inventory.get(1).getMaxStackSize() > inventory.get(1).getCount();
    }
}
