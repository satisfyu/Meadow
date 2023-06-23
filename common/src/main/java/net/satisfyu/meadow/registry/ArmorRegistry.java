package net.satisfyu.meadow.registry;

import com.mojang.datafixers.util.Pair;
import de.cristelknight.doapi.client.render.feature.FullCustomArmor;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
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

    public static  <T extends LivingEntity> void registerHatModels(Map<Item, EntityModel<T>> models, EntityModelLoader modelLoader) {
        models.put(ObjectRegistry.FUR_HELMET.get(), new FurArmorHat<>(modelLoader.getModelPart(FurArmorHat.LAYER_LOCATION)));
    }

    public static  <T extends LivingEntity> void registerArmorModels(Map<FullCustomArmor, Pair<BipedEntityModel<T>, BipedEntityModel<T>>> models, EntityModelLoader modelLoader) {
        models.put(new FullCustomArmor(ObjectRegistry.FUR_BOOTS.get(), ObjectRegistry.FUR_CHESTPLATE.get(), ObjectRegistry.FUR_LEGGINGS.get(), new MeadowIdentifier("textures/models/armor/fur.png")), new Pair<>(new FurArmorOuter<>(modelLoader.getModelPart(FurArmorOuter.LAYER_LOCATION)), new FurArmorInner<>(modelLoader.getModelPart(FurArmorInner.LAYER_LOCATION))));
    }
}