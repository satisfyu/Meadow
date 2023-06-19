package net.satisfyu.meadow.forge.villager;

import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.sound.SoundEvents;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.registry.ObjectRegistry;
import net.satisfyu.meadow.util.MeadowVillagerUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ForgeVillager {
    public static final DeferredRegister<PointOfInterestType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, Meadow.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, Meadow.MOD_ID);

    public static final RegistryObject<PointOfInterestType> CHEESEMAKER_POI = POI_TYPES.register("cheesemaker_poi", () ->
            new PointOfInterestType(ImmutableSet.copyOf(ObjectRegistry.CHEESE_RACK.get().getStateManager().getStates()), 1, 12));
    public static final RegistryObject<VillagerProfession> CHEESEMAKER = VILLAGER_PROFESSIONS.register("cheesemaker", () ->
            new VillagerProfession("cheesemaker", x -> x.get() == CHEESEMAKER_POI.get(), x -> x.get() == CHEESEMAKER_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_FARMER));

    public static void registerPOIs(){
        try {
            ObfuscationReflectionHelper.findMethod(PointOfInterestType.class, "setup", PointOfInterestType.class).invoke(CHEESEMAKER_POI.get());
        } catch (InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }

    @Mod.EventBusSubscriber(modid = Meadow.MOD_ID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void addCustomTrades(VillagerTradesEvent event){
            if(event.getType().equals(CHEESEMAKER.get())){
                Int2ObjectMap<List<TradeOffers.Factory>> trades = event.getTrades();

                List<TradeOffers.Factory> level1 = trades.get(1);
                level1.add(new MeadowVillagerUtil.SellItemFactory(ObjectRegistry.WOODEN_BUCKET.get(), 5, 1, 10));

                List<TradeOffers.Factory> level2 = trades.get(2);
                level2.add(new MeadowVillagerUtil.SellItemFactory(ObjectRegistry.COOKING_CAULDRON.get(), 7, 1, 10));
                level2.add(new MeadowVillagerUtil.SellItemFactory(ObjectRegistry.WOODEN_MILK_BUCKET.get(), 12, 1, 10));

                List<TradeOffers.Factory> level3 = trades.get(3);
                level3.add(new MeadowVillagerUtil.SellItemFactory(ObjectRegistry.CHEESE_FORM.get(), 4, 1, 10));
                level3.add(new MeadowVillagerUtil.SellItemFactory(ObjectRegistry.CHEESE_BLOCK.get(), 10, 1, 10));

                List<TradeOffers.Factory> level4 = trades.get(4);
                level4.add(new MeadowVillagerUtil.SellItemFactory(ObjectRegistry.RENNET.get(), 1, 2, 7));

                List<TradeOffers.Factory> level5 = trades.get(5);
                level5.add(new MeadowVillagerUtil.SellItemFactory(ObjectRegistry.AMETHYST_CHEESE_BLOCK.get(), 12, 1, 15));
                level5.add(new MeadowVillagerUtil.SellItemFactory(ObjectRegistry.GRAIN_CHEESE_BLOCK.get(), 12, 1, 15));
            }
        }
    }
}
