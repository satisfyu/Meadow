package net.satisfyu.meadow.villager;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class ModVillagers {
    public static final PointOfInterestType CHEESE_POI = registerPOI("cheese_poi", ModBlocks.CHEESE_FORM);
    public static final VillagerProfession CHEESEMAKER = registerProfession("cheesemaker",
            RegistryKey.of(Registry.POINT_OF_INTEREST_TYPE_KEY, new Identifier(Meadow.MOD_ID, "cheese_poi")));



    public static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registry.VILLAGER_PROFESSION, new Identifier(Meadow.MOD_ID, name),
                VillagerProfessionBuilder.create().id(new Identifier(Meadow.MOD_ID, name)).workstation(type)
                        .workSound(SoundEvents.ENTITY_VILLAGER_WORK_BUTCHER).build());
    }

    public static PointOfInterestType registerPOI(String name, Block block) {
        return PointOfInterestHelper.register(new Identifier(Meadow.MOD_ID, name),
                1, 1, ImmutableSet.copyOf(block.getStateManager().getStates()));
    }

    public static void registerVillagers() {
        Meadow.LOGGER.debug("Registering Villagers for " + Meadow.MOD_ID);
    }

    public static void registerTrades() {
        TradeOfferHelper.registerVillagerOffers(CHEESEMAKER,1,
                factories -> {
                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(ModItems.OAT_SEEDS, 5),
                            6, 2, 0.02f
                    )));
                });
    }
}