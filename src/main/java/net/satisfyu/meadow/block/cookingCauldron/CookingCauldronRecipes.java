package net.satisfyu.meadow.block.cookingCauldron;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.MilkBucketItem;
import net.satisfyu.meadow.item.ModItems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public enum CookingCauldronRecipes {
    MASS(Items.MILK_BUCKET, ModItems.LAB, ModItems.ALPINE_SALT, ModItems.CHEESE_MASS, ModItems.WOODEN_CHEESE_MASS),

    W_MASS(ModItems.WOODEN_MILK_BUCKET, ModItems.LAB, ModItems.ALPINE_SALT, ModItems.CHEESE_MASS, ModItems.WOODEN_CHEESE_MASS),

    BUFFALO_MASS(ModItems.BUFFALO_MILK, ModItems.LAB, ModItems.ALPINE_SALT, ModItems.BUFFALO_CHEESE_MASS, ModItems.WOODEN_BUFFALO_CHEESE_MASS),
    GOAT_MASS(ModItems.GOAT_MILK, ModItems.LAB, ModItems.ALPINE_SALT, ModItems.GOAT_CHEESE_MASS, ModItems.WOODEN_GOAT_CHEESE_MASS),
    OAT_MASS(ModItems.OAT_MILK, ModItems.LAB, ModItems.ALPINE_SALT, ModItems.OAT_CHEESE_MASS, ModItems.WOODEN_OAT_CHEESE_MASS),
    SHEEP_MASS(ModItems.SHEEP_MILK, ModItems.LAB, ModItems.ALPINE_SALT, ModItems.SHEEP_CHEESE_MASS, ModItems.WOODEN_SHEEP_CHEESE_MASS),

    W_BUFFALO_MASS(ModItems.WOODEN_BUFFALO_MILK, ModItems.LAB, ModItems.ALPINE_SALT, ModItems.BUFFALO_CHEESE_MASS, ModItems.WOODEN_BUFFALO_CHEESE_MASS),
    W_GOAT_MASS(ModItems.WOODEN_GOAT_MILK, ModItems.LAB, ModItems.ALPINE_SALT, ModItems.GOAT_CHEESE_MASS, ModItems.WOODEN_GOAT_CHEESE_MASS),
    W_OAT_MASS(ModItems.WOODEN_OAT_MILK, ModItems.LAB, ModItems.ALPINE_SALT, ModItems.OAT_CHEESE_MASS, ModItems.WOODEN_OAT_CHEESE_MASS),
    W_SHEEP_MASS(ModItems.WOODEN_SHEEP_MILK, ModItems.LAB, ModItems.ALPINE_SALT, ModItems.SHEEP_CHEESE_MASS, ModItems.WOODEN_SHEEP_CHEESE_MASS),

    LAVENDER_MASS(Items.BEDROCK, ModItems.LAB, ModItems.BAG_OF_LAVENDER, ModItems.LAVENDER_CHEESE_MASS, ModItems.WOODEN_LAVENDER_CHEESE_MASS),
    HERBS_MASS_1(Items.BEDROCK, ModItems.LAB, ModItems.BAG_OF_JUNIPER, ModItems.HERBS_CHEESE_MASS, ModItems.WOODEN_HERBS_CHEESE_MASS),
    HERBS_MASS_2(Items.BEDROCK, ModItems.LAB, ModItems.BAG_OF_YARROW, ModItems.HERBS_CHEESE_MASS, ModItems.WOODEN_HERBS_CHEESE_MASS);

    private final Item input1;

    private final Item input2;

    private final Item input3;

    private final Item output;

    private final Item output_w;


    CookingCauldronRecipes(Item input1, Item input2, Item input3, Item output, Item output_w) {
        this.input1 = input1;
        this.input2 = input2;
        this.input3 = input3;
        this.output = output;
        this.output_w = output_w;
    }

    public Item getInput1() {
        return input1;
    }

    public Item getInput2() {
        return input2;
    }

    public Item getInput3() {
        return input3;
    }

    public Item getOutput() {
        return output;
    }

    public Item getOutputW() {
        return output_w;
    }

    public List<Item> getInputs(){
        return new ArrayList<>(List.of(getInput1(), getInput2(), getInput3()));
    }

    public static Optional<CookingCauldronRecipes> getMatchingRecipe(Item input1, Item input2, Item input3){
        for(CookingCauldronRecipes r : CookingCauldronRecipes.values()){
            List<Item> items = r.getInputs();
            //List<Item> items2 = new ArrayList<>(Stream.of(input1, input2, input3).sorted(Comparator.comparing(stack -> Registry.ITEM.getId(stack).getNamespace())).toList());
            List<Item> items2 = new ArrayList<>(List.of(input1, input2, input3));
            if(items.contains(Items.BEDROCK)){
                items.remove(Items.BEDROCK);
                for(Item i : items2){
                    if(i instanceof MilkBucketItem){
                        items2.remove(i);
                        break;
                    }
                }
            }
            if(listEqualsIgnoreOrder(items, items2)) return Optional.of(r);
        }
        return Optional.empty();
    }

    public static int getVar(CookingCauldronRecipes recipe){
        return switch(recipe) {
            case MASS, W_MASS -> 2;
            case BUFFALO_MASS, W_BUFFALO_MASS -> 1;
            case GOAT_MASS, W_GOAT_MASS -> 3;
            case OAT_MASS, W_OAT_MASS -> 5;
            case SHEEP_MASS, W_SHEEP_MASS -> 6;
            case LAVENDER_MASS  -> 4;
            case HERBS_MASS_1, HERBS_MASS_2 -> 7;
        };

    }

    public static <T> boolean listEqualsIgnoreOrder(List<T> list1, List<T> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }
}
