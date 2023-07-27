package net.satisfyu.meadow.registry;

import com.mojang.datafixers.util.Pair;
import de.cristelknight.doapi.client.render.feature.FullCustomArmor;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.satisfyu.meadow.client.model.FurArmorHat;
import net.satisfyu.meadow.client.model.FurArmorInner;
import net.satisfyu.meadow.client.model.FurArmorOuter;
import net.satisfyu.meadow.util.MeadowIdentifier;


import java.util.Map;

public class ArmorRegistry {

    public static void registerArmorModelLayers() {
        EntityModelLayerRegistry.register(FurArmorHat.LAYER_LOCATION, FurArmorHat::createBodyLayer);
        EntityModelLayerRegistry.register(FurArmorOuter.LAYER_LOCATION, FurArmorOuter::createBodyLayer);
        EntityModelLayerRegistry.register(FurArmorInner.LAYER_LOCATION, FurArmorInner::createBodyLayer);

    }

    public static  <T extends LivingEntity> void registerHatModels(Map<Item, EntityModel<T>> models, EntityModelSet modelLoader) {
        models.put(ObjectRegistry.FUR_HELMET.get(), new FurArmorHat<>(modelLoader.bakeLayer(FurArmorHat.LAYER_LOCATION)));
    }

    public static  <T extends LivingEntity> void registerArmorModels(Map<FullCustomArmor, Pair<HumanoidModel<T>, HumanoidModel<T>>> models, EntityModelSet modelLoader) {
        models.put(new FullCustomArmor(ObjectRegistry.FUR_BOOTS.get(), ObjectRegistry.FUR_CHESTPLATE.get(), ObjectRegistry.FUR_LEGGINGS.get(), new MeadowIdentifier("textures/models/armor/fur.png")), new Pair<>(new FurArmorOuter<>(modelLoader.bakeLayer(FurArmorOuter.LAYER_LOCATION)), new FurArmorInner<>(modelLoader.bakeLayer(FurArmorInner.LAYER_LOCATION))));
    }
}