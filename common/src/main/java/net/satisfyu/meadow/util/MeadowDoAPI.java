package net.satisfyu.meadow.util;

import com.mojang.datafixers.util.Pair;
import de.cristelknight.doapi.DoApi;
import de.cristelknight.doapi.DoApiRL;
import de.cristelknight.doapi.api.DoApiAPI;
import de.cristelknight.doapi.api.DoApiPlugin;
import de.cristelknight.doapi.client.render.feature.FullCustomArmor;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.satisfyu.meadow.client.render.armor.FurArmorHat;
import net.satisfyu.meadow.client.render.armor.FurArmorInner;
import net.satisfyu.meadow.client.render.armor.FurArmorOuter;
import net.satisfyu.meadow.registry.ObjectRegistry;

import java.util.Map;
import java.util.Set;

@DoApiPlugin
public class MeadowDoAPI implements DoApiAPI {

    @Override
    public void registerBlocks(Set<Block> blocks) {

    }

    @Override
    public <T extends LivingEntity> void registerHat(Map<Item, EntityModel<T>> models, EntityModelLoader modelLoader) {
        models.put(ObjectRegistry.FUR_HELMET.get(), new FurArmorHat<>(modelLoader.getModelPart(FurArmorHat.LAYER_LOCATION)));
    }

    @Override
    public <T extends LivingEntity> void registerArmor(Map<FullCustomArmor, Pair<BipedEntityModel<T>, BipedEntityModel<T>>> models, EntityModelLoader modelLoader) {
        models.put(new FullCustomArmor(ObjectRegistry.FUR_BOOTS.get(), ObjectRegistry.FUR_CHESTPLATE.get(), ObjectRegistry.FUR_LEGGINGS.get(), new DoApiRL("textures/armor.png")), new Pair<>(new FurArmorOuter<>(modelLoader.getModelPart(FurArmorOuter.LAYER_LOCATION)), new FurArmorInner<>(modelLoader.getModelPart(FurArmorInner.LAYER_LOCATION))));
    }
    public static void registerArmorModelLayers(){
        EntityModelLayerRegistry.register(FurArmorOuter.LAYER_LOCATION, FurArmorOuter::createBodyLayer);
        EntityModelLayerRegistry.register(FurArmorInner.LAYER_LOCATION, FurArmorInner::createBodyLayer);
        EntityModelLayerRegistry.register(FurArmorHat.LAYER_LOCATION, FurArmorHat::createBodyLayer);
    }


}
