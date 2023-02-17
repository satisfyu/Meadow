package net.satisfyu.meadow.villager;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffers;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import net.satisfyu.meadow.util.MeadowIdentifier;

public class ModVillagers {
    private static final MeadowIdentifier CHEESEMAKER_POI_IDENTIFIER = new MeadowIdentifier("cheesemaker_poi");
    public static final PointOfInterestType CHEESEMAKER_POI = PointOfInterestHelper.register(CHEESEMAKER_POI_IDENTIFIER, 1, 12, ModBlocks.CHEESE_RACK);
    public static final VillagerProfession CHEESEMAKER = Registry.register(Registry.VILLAGER_PROFESSION, new Identifier("meadow", "cheesemaker"), VillagerProfessionBuilder.create().id(new Identifier("meadow", "cheesemaker")).workstation(RegistryKey.of(Registry.POINT_OF_INTEREST_TYPE_KEY, CHEESEMAKER_POI_IDENTIFIER)).build());

    private static final MeadowIdentifier HERMIT_POI_IDENTIFIER = new MeadowIdentifier("hermit_poi");
    public static final PointOfInterestType HERMIT_POI = PointOfInterestHelper.register(HERMIT_POI_IDENTIFIER, 1, 12, ModBlocks.WOODCUTTER);
    public static final VillagerProfession HERMIT = Registry.register(Registry.VILLAGER_PROFESSION, new Identifier("meadow", "hermit"), VillagerProfessionBuilder.create().id(new Identifier("meadow", "hermit")).workstation(RegistryKey.of(Registry.POINT_OF_INTEREST_TYPE_KEY, HERMIT_POI_IDENTIFIER)).build());

    public static void init() {
        TradeOfferHelper.registerVillagerOffers(CHEESEMAKER, 1, factories -> {
            factories.add(new BuyForOneEmeraldFactory(ModItems.OAT_SEEDS, 15, 4, 5));
            factories.add(new BuyForOneEmeraldFactory(ModItems.MOUNTAIN_LAVENDER_SEEDS, 15, 4, 5));
            factories.add(new SellItemFactory(ModItems.BAG_OF_LAVENDER, 2, 1, 5));
            factories.add(new SellItemFactory(ModItems.BAG_OF_JUNIPER, 2, 1, 5));
        });
        TradeOfferHelper.registerVillagerOffers(CHEESEMAKER, 2, factories -> {
            factories.add(new SellItemFactory(ModItems.LAB, 1, 2, 7));
        });
        TradeOfferHelper.registerVillagerOffers(CHEESEMAKER, 3, factories -> {
            factories.add(new SellItemFactory(ModBlocks.COOKING_POT, 3, 1, 10));
            factories.add(new SellItemFactory(ModBlocks.BOWL_MILK, 3, 1, 10));
            factories.add(new SellItemFactory(ModItems.JUG, 7, 1, 10));
            factories.add(new SellItemFactory(ModBlocks.COOKING_CAULDRON, 7, 1, 10));
        });
        TradeOfferHelper.registerVillagerOffers(CHEESEMAKER, 4, factories -> {
            factories.add(new SellItemFactory(ModItems.RICOLA, 4, 1, 10));
            factories.add(new SellItemFactory(ModItems.WOODEN_BUCKET, 5, 1, 10));
            factories.add(new SellItemFactory(ModItems.SHEEP_MILK, 12, 1, 10));
            factories.add(new SellItemFactory(ModBlocks.FURNACE_COBBLESTONE, 6, 1, 10));
        });
        TradeOfferHelper.registerVillagerOffers(CHEESEMAKER, 5, factories -> {
            factories.add(new SellItemFactory(ModBlocks.CHEESE_BLOCK, 10, 1, 10));
            factories.add(new SellItemFactory(ModItems.OAT_MILK, 4, 1, 10));
            factories.add(new SellItemFactory(ModBlocks.LAVENDER_CHEESE_BLOCK, 12, 1, 15));
            factories.add(new SellItemFactory(ModBlocks.OAT_CHEESE_BLOCK, 12, 1, 15));
        });
        TradeOfferHelper.registerVillagerOffers(HERMIT, 1, factories -> {
            factories.add(new BuyForOneEmeraldFactory(ModBlocks.BENCH, 15, 4, 5));
            factories.add(new BuyForOneEmeraldFactory(ModItems.JUG_JUNIPER_TEA, 4, 4, 5));
            factories.add(new BuyForOneEmeraldFactory(ModBlocks.PINE_LOG, 10, 4, 5));
            factories.add(new SellItemFactory(ModBlocks.PINE_SAPLING, 10, 6, 5));
            factories.add(new SellItemFactory(ModBlocks.PINE_SLAB, 7, 4, 5));
        });
        TradeOfferHelper.registerVillagerOffers(HERMIT, 2, factories -> {
            factories.add(new BuyForOneEmeraldFactory(ModBlocks.SHELF, 15, 4, 5));
            factories.add(new BuyForOneEmeraldFactory(ModBlocks.FIR, 10, 2, 5));
            factories.add(new SellItemFactory(ModItems.WATERING_CAN_ITEM, 25, 4, 5));
            factories.add(new SellItemFactory(ModItems.OAT_BREAD, 6, 3, 5));
            factories.add(new SellItemFactory(ModItems.CHEESE_SANDWICH, 4, 4, 5));
        });
        TradeOfferHelper.registerVillagerOffers(HERMIT, 3, factories -> {
            factories.add(new SellItemFactory(ModBlocks.PINE_PLANKS, 3, 2, 10));
            factories.add(new SellItemFactory(ModBlocks.BOWL_MILK, 3, 1, 10));
            factories.add(new SellItemFactory(ModItems.JUG, 7, 1, 10));
            factories.add(new SellItemFactory(ModBlocks.FRAME, 25, 1, 10));
        });
        TradeOfferHelper.registerVillagerOffers(HERMIT, 4, factories -> {
            factories.add(new SellItemFactory(ModItems.JUG_MILK, 4, 1, 10));
            factories.add(new SellItemFactory(ModItems.WOODEN_BUCKET, 5, 1, 10));
            factories.add(new SellItemFactory(ModItems.BEAR_FUR, 12, 2, 10));
            factories.add(new SellItemFactory(ModBlocks.FIRE_LOG, 6, 2, 10));
        });
        TradeOfferHelper.registerVillagerOffers(HERMIT, 5, factories -> {
            factories.add(new SellItemFactory(Items.IRON_AXE, 10, 1, 10));
            factories.add(new SellItemFactory(ModBlocks.WOODEN_CAULDRON, 5, 1, 10));

        });






    }

    static class BuyForOneEmeraldFactory implements TradeOffers.Factory {
        private final Item buy;
        private final int price;
        private final int maxUses;
        private final int experience;
        private final float multiplier;

        public BuyForOneEmeraldFactory(ItemConvertible item, int price, int maxUses, int experience) {
            this.buy = item.asItem();
            this.price = price;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = 0.05F;
        }

        @Override
        public TradeOffer create(Entity entity, Random random) {
            ItemStack itemStack = new ItemStack(this.buy, this.price);
            return new TradeOffer(itemStack, new ItemStack(Items.EMERALD), this.maxUses, this.experience, this.multiplier);
        }
    }

    static class SellItemFactory implements TradeOffers.Factory {
        private final ItemStack sell;
        private final int price;
        private final int count;
        private final int maxUses;
        private final int experience;
        private final float multiplier;

        public SellItemFactory(Block block, int price, int count, int maxUses, int experience) {
            this(new ItemStack(block), price, count, maxUses, experience);
        }

        public SellItemFactory(Block item, int price, int count, int experience) {
            this(new ItemStack(item), price, count, 12, experience);
        }

        public SellItemFactory(Item item, int price, int count, int experience) {
            this(new ItemStack(item), price, count, 12, experience);
        }

        public SellItemFactory(Item item, int price, int count, int maxUses, int experience) {
            this(new ItemStack(item), price, count, maxUses, experience);
        }

        public SellItemFactory(ItemStack stack, int price, int count, int maxUses, int experience) {
            this(stack, price, count, maxUses, experience, 0.05F);
        }

        public SellItemFactory(ItemStack stack, int price, int count, int maxUses, int experience, float multiplier) {
            this.sell = stack;
            this.price = price;
            this.count = count;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = multiplier;
        }

        @Override
        public TradeOffer create(Entity entity, Random random) {
            return new TradeOffer(
                    new ItemStack(Items.EMERALD, this.price), new ItemStack(this.sell.getItem(), this.count), this.maxUses, this.experience, this.multiplier
            );
        }
    }
}