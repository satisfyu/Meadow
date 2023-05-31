package net.satisfyu.meadow.block.cheeseForm;

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
import net.satisfyu.meadow.block.ModBlockEntities;
import net.satisfyu.meadow.block.ImplementedInventory;
import net.satisfyu.meadow.block.cookingCauldron.CookingCauldronBlockEntity;
import net.satisfyu.meadow.recipes.cheese.CheeseFormRecipe;
import net.satisfyu.meadow.registry.ObjectRegistry;
import net.satisfyu.meadow.util.MeadowTags;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static net.satisfyu.meadow.block.cookingCauldron.CookingCauldronBlock.DONE;
import static net.satisfyu.meadow.block.cookingCauldron.CookingCauldronBlock.VAR;

public class CheeseFormBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    public static final Text TITLE = Text.translatable("container.cheese_form");

    private int syncedInt;

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            if (index == 0) {
                return syncedInt;
            }
            return 0;
        }

        @Override
        public void set(int index, int value) {
            if (index == 0) {
                syncedInt = value;
            }
        }
        @Override
        public int size() {
            return 1;
        }

    };

    public CheeseFormBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CHEESE_FORM_BLOCK_ENTITY.get(), pos, state);
    }


    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putInt("syncedInt", syncedInt);
        Inventories.writeNbt(nbt, items);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, items);
        syncedInt = nbt.getInt("syncedInt");
    }

    public void tick(World world, BlockPos pos, BlockState state, CheeseFormBlockEntity be) {
        if(!world.isClient){
            Optional<CheeseFormRecipe> recipe = world.getRecipeManager().getFirstMatch(CheeseFormRecipe.Type.INSTANCE, this, world);
            int var = 0;
            boolean done = false;
            if(recipe.isPresent() && items.get(1).isEmpty()){
                var = getVar(items.get(0).getItem());
                if(!state.get(CheeseFormBlock.DONE) && state.get(VAR) > 0){
                    syncedInt++;
                }
                if(syncedInt >= CookingCauldronBlockEntity.MAX_COOKING_TIME){
                    done = true;
                    Item bucket = items.get(0).isIn(MeadowTags.WOODEN_BUCKETS) ? ObjectRegistry.WOODEN_BUCKET.get() : Items.BUCKET;
                    items.set(0, new ItemStack(bucket));
                    items.set(1, recipe.get().getOutput());
                    syncedInt = 0;
                }
            }
            else{
                syncedInt = 0;
                if(!items.get(1).isEmpty()){
                    done = true;
                    var = getVar(items.get(1).getItem());
                }
            }
            world.setBlockState(pos, ObjectRegistry.CHEESE_FORM.get().getDefaultState().with(VAR, var).with(DONE, done));
        }
    }

    public static int getVar(Item item){
        if (ObjectRegistry.CHEESE_MASS.equals(item) || ObjectRegistry.WOODEN_CHEESE_MASS.equals(item) || ObjectRegistry.CHEESE_BLOCK.get().asItem().equals(item)) {
            return 2;
        } else if (ObjectRegistry.BUFFALO_CHEESE_MASS.equals(item) || ObjectRegistry.WOODEN_BUFFALO_CHEESE_MASS.equals(item) || ObjectRegistry.BOWL_MOZRELLA.get().asItem().equals(item)) {
            return 1;
        } else if (ObjectRegistry.GOAT_CHEESE_MASS.equals(item) || ObjectRegistry.WOODEN_GOAT_CHEESE_MASS.equals(item) || ObjectRegistry.GOAT_CHEESE_BLOCK.get().asItem().equals(item)) {
            return 3;
        } else if (ObjectRegistry.OAT_CHEESE_MASS.equals(item) || ObjectRegistry.WOODEN_OAT_CHEESE_MASS.equals(item) || ObjectRegistry.OAT_CHEESE_BLOCK.get().asItem().equals(item)) {
            return 5;
        } else if (ObjectRegistry.SHEEP_CHEESE_MASS.equals(item) || ObjectRegistry.WOODEN_SHEEP_CHEESE_MASS.equals(item) || ObjectRegistry.SHEEP_CHEESE_BLOCK.get().asItem().equals(item)) {
            return 6;
        } else if (ObjectRegistry.LAVENDER_CHEESE_MASS.equals(item) || ObjectRegistry.WOODEN_LAVENDER_CHEESE_MASS.equals(item) || ObjectRegistry.LAVENDER_CHEESE_BLOCK.get().asItem().equals(item)) {
            return 4;
        } else if (ObjectRegistry.HERBS_CHEESE_MASS.equals(item) || ObjectRegistry.WOODEN_HERBS_CHEESE_MASS.equals(item) || ObjectRegistry.HERB_CHEESE_BLOCK.get().asItem().equals(item)) {
            return 7;
        }

        else{
            return 2;
        }
    }

    @Override
    public Text getDisplayName() {
        return TITLE;
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CheeseFormScreenHandler(syncId, inv, this, propertyDelegate);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }
}
