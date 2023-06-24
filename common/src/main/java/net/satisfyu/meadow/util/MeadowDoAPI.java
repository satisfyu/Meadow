package net.satisfyu.meadow.util;

import com.mojang.datafixers.util.Pair;
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
import net.satisfyu.meadow.client.model.FurArmorHat;
import net.satisfyu.meadow.client.model.FurArmorInner;
import net.satisfyu.meadow.client.model.FurArmorOuter;
import net.satisfyu.meadow.registry.ArmorRegistry;
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
        ArmorRegistry.registerHatModels(models, modelLoader);
    }

    @Override
    public <T extends LivingEntity> void registerArmor(Map<FullCustomArmor, Pair<BipedEntityModel<T>, BipedEntityModel<T>>> models, EntityModelLoader modelLoader) {
        ArmorRegistry.registerArmorModels(models, modelLoader);
    }


}
