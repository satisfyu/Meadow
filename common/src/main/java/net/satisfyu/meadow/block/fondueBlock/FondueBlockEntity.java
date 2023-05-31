package net.satisfyu.meadow.block.fondueBlock;


import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.satisfyu.meadow.block.ImplementedInventory;
import net.satisfyu.meadow.block.ModBlockEntities;
import net.satisfyu.meadow.registry.ObjectRegistry;
import net.satisfyu.meadow.util.MeadowTags;
import org.jetbrains.annotations.Nullable;

public class FondueBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    public int MAX_PROGRESS = 72;

    private int fuelAmount = 0;

    public FondueBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FONDUE.get(), pos, state);
        this.propertyDelegate = new PropertyDelegate() {
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

            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(this.getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new FondueScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("fondue.progress", progress);
        nbt.putInt("fondue.fuelAmount", fuelAmount);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("fondue.progress");
        fuelAmount = nbt.getInt("fondue.fuelAmount");
        super.readNbt(nbt);

    }

    private void resetProgress() {
        this.progress = 0;
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, FondueBlockEntity entity) {
        if(world.isClient()) {
            return;
        }

        if(hasRecipe(entity) && hasFuel(entity)) {
            entity.progress++;
            markDirty(world, blockPos, state);
            if(entity.progress >= entity.MAX_PROGRESS) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
            markDirty(world, blockPos, state);
        }
    }

    private static boolean hasFuel(FondueBlockEntity entity){
        if(entity.fuelAmount > 0) return true;
        ItemStack stack = entity.inventory.get(2);
        if(stack.isIn(MeadowTags.CHEESE)){
            entity.fuelAmount = 10;
            stack.decrement(1);
            return true;
        }
        return false;
    }

    private static void craftItem(FondueBlockEntity entity) {
        entity.removeStack(0, 1);
        entity.setStack(1, new ItemStack(ObjectRegistry.CHEESE_STICK.get(), entity.getStack(1).getCount() + 1));
        entity.resetProgress();
        entity.fuelAmount--;
    }

    private static boolean hasRecipe(FondueBlockEntity entity) {
        boolean hasBreadInFirstSlot = entity.getStack(0).getItem() == Items.BREAD;
        return hasBreadInFirstSlot && canInsertAmountIntoOutputSlot(entity.inventory)
                && canInsertItemIntoOutputSlot(entity.inventory, ObjectRegistry.CHEESE_STICK.get());
    }

    private static boolean canInsertItemIntoOutputSlot(DefaultedList<ItemStack> inventory, Item output) {
        return inventory.get(1).getItem() == output || inventory.get(1).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(DefaultedList<ItemStack> inventory) {
        return inventory.get(1).getMaxCount() > inventory.get(1).getCount();
    }
}
