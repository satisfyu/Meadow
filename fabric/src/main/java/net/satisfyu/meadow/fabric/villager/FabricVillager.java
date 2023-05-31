package net.satisfyu.meadow.fabric.villager;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.village.VillagerType;
import net.minecraft.world.poi.PointOfInterestType;
import net.satisfyu.meadow.registry.ObjectRegistry;
import net.satisfyu.meadow.util.MeadowIdentifier;

public class FabricVillager {
    private static final MeadowIdentifier CHEESEMAKER_POI_IDENTIFIER = new MeadowIdentifier("cheesemaker_poi");
    public static final PointOfInterestType CHEESEMAKER_POI = PointOfInterestHelper.register(CHEESEMAKER_POI_IDENTIFIER, 1, 12, ObjectRegistry.CHEESE_RACK.get());
    public static final VillagerProfession CHEESEMAKER = Registry.register(Registry.VILLAGER_PROFESSION, new Identifier("meadow", "cheesemaker"), VillagerProfessionBuilder.create().id(new Identifier("meadow", "cheesemaker")).workstation(RegistryKey.of(Registry.POINT_OF_INTEREST_TYPE_KEY, CHEESEMAKER_POI_IDENTIFIER)).build());

    private static final MeadowIdentifier HERMIT_POI_IDENTIFIER = new MeadowIdentifier("hermit_poi");
    public static final PointOfInterestType HERMIT_POI = PointOfInterestHelper.register(HERMIT_POI_IDENTIFIER, 1, 12, ObjectRegistry.WOODCUTTER.get());
    public static final VillagerProfession HERMIT = Registry.register(Registry.VILLAGER_PROFESSION, new Identifier("meadow", "hermit"), VillagerProfessionBuilder.create().id(new Identifier("meadow", "hermit")).workstation(RegistryKey.of(Registry.POINT_OF_INTEREST_TYPE_KEY, HERMIT_POI_IDENTIFIER)).build());

    public static final VillagerType MEADOW = Registry.register(Registry.VILLAGER_TYPE, new Identifier("meadow", "meadow"), new VillagerType("meadow"));
    
    public static void init() {
        TradeOfferHelper.registerVillagerOffers(CHEESEMAKER, 1, factories -> {
            factories.add(new BuyForOneEmeraldFactory(ObjectRegistry.OAT_SEEDS.get(), 15, 4, 5));
            factories.add(new BuyForOneEmeraldFactory(ObjectRegistry.MOUNTAIN_LAVENDER_SEEDS.get(), 15, 4, 5));
            factories.add(new SellItemFactory(ObjectRegistry.BAG_OF_LAVENDER.get(), 2, 1, 5));
            factories.add(new SellItemFactory(ObjectRegistry.BAG_OF_JUNIPER.get(), 2, 1, 5));
        });
        TradeOfferHelper.registerVillagerOffers(CHEESEMAKER, 2, factories -> {
            factories.add(new SellItemFactory(ObjectRegistry.LAB.get(), 1, 2, 7));
        });
        TradeOfferHelper.registerVillagerOffers(CHEESEMAKER, 3, factories -> {
            factories.add(new SellItemFactory(ObjectRegistry.COOKING_POT.get(), 3, 1, 10));
            factories.add(new SellItemFactory(ObjectRegistry.BOWL_MILK.get(), 3, 1, 10));
            factories.add(new SellItemFactory(ObjectRegistry.JUG.get(), 7, 1, 10));
            factories.add(new SellItemFactory(ObjectRegistry.COOKING_CAULDRON.get(), 7, 1, 10));
        });
        TradeOfferHelper.registerVillagerOffers(CHEESEMAKER, 4, factories -> {
            factories.add(new SellItemFactory(ObjectRegistry.RICOLA.get(), 4, 1, 10));
            factories.add(new SellItemFactory(ObjectRegistry.WOODEN_BUCKET.get(), 5, 1, 10));
            factories.add(new SellItemFactory(ObjectRegistry.SHEEP_MILK.get(), 12, 1, 10));
            factories.add(new SellItemFactory(ObjectRegistry.FURNACE_COBBLESTONE.get(), 6, 1, 10));
        });
        TradeOfferHelper.registerVillagerOffers(CHEESEMAKER, 5, factories -> {
            factories.add(new SellItemFactory(ObjectRegistry.CHEESE_BLOCK.get(), 10, 1, 10));
            factories.add(new SellItemFactory(ObjectRegistry.OAT_MILK.get(), 4, 1, 10));
            factories.add(new SellItemFactory(ObjectRegistry.LAVENDER_CHEESE_BLOCK.get(), 12, 1, 15));
            factories.add(new SellItemFactory(ObjectRegistry.OAT_CHEESE_BLOCK.get(), 12, 1, 15));
        });
        TradeOfferHelper.registerVillagerOffers(HERMIT, 1, factories -> {
            //factories.add(new BuyForOneEmeraldFactory(ObjectRegistry.BENCH, 15, 4, 5));
            factories.add(new BuyForOneEmeraldFactory(ObjectRegistry.JUG_JUNIPER_TEA.get(), 4, 4, 5));
            factories.add(new BuyForOneEmeraldFactory(ObjectRegistry.PINE_LOG.get(), 10, 4, 5));
            factories.add(new SellItemFactory(ObjectRegistry.PINE_SAPLING.get(), 10, 6, 5));
            factories.add(new SellItemFactory(ObjectRegistry.PINE_SLAB.get(), 7, 4, 5));
        });
        TradeOfferHelper.registerVillagerOffers(HERMIT, 2, factories -> {
            factories.add(new BuyForOneEmeraldFactory(ObjectRegistry.SHELF.get(), 15, 4, 5));
            factories.add(new BuyForOneEmeraldFactory(ObjectRegistry.FIR.get(), 10, 2, 5));
            //TODO
            //factories.add(new SellItemFactory(ObjectRegistry.WATERING_CAN_ITEM.get(), 25, 4, 5));
            factories.add(new SellItemFactory(ObjectRegistry.OAT_BREAD.get(), 6, 3, 5));
            factories.add(new SellItemFactory(ObjectRegistry.CHEESE_SANDWICH.get(), 4, 4, 5));
        });
        TradeOfferHelper.registerVillagerOffers(HERMIT, 3, factories -> {
            factories.add(new SellItemFactory(ObjectRegistry.PINE_PLANKS.get(), 3, 2, 10));
            factories.add(new SellItemFactory(ObjectRegistry.BOWL_MILK.get(), 3, 1, 10));
            factories.add(new SellItemFactory(ObjectRegistry.JUG.get(), 7, 1, 10));
            factories.add(new SellItemFactory(ObjectRegistry.FRAME.get(), 25, 1, 10));
        });
        TradeOfferHelper.registerVillagerOffers(HERMIT, 4, factories -> {
            factories.add(new SellItemFactory(ObjectRegistry.JUG_MILK.get(), 4, 1, 10));
            factories.add(new SellItemFactory(ObjectRegistry.WOODEN_BUCKET.get(), 5, 1, 10));
            factories.add(new SellItemFactory(ObjectRegistry.BEAR_FUR.get(), 12, 2, 10));
            factories.add(new SellItemFactory(ObjectRegistry.FIRE_LOG.get(), 6, 2, 10));
        });
        TradeOfferHelper.registerVillagerOffers(HERMIT, 5, factories -> {
            factories.add(new SellItemFactory(Items.IRON_AXE, 10, 1, 10));
            factories.add(new SellItemFactory(ObjectRegistry.WOODEN_CAULDRON.get(), 5, 1, 10));
        });

        VillagerType.BIOME_TO_TYPE.put(RegistryKey.of(Registry.BIOME_KEY, new Identifier("meadow")), MEADOW);
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