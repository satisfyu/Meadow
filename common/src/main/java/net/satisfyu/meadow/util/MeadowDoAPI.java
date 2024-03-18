package net.satisfyu.meadow.util;

import de.cristelknight.doapi.api.DoApiAPI;
import de.cristelknight.doapi.api.DoApiPlugin;
import de.cristelknight.doapi.client.render.feature.CustomArmorManager;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.satisfyu.meadow.registry.ArmorRegistry;

import java.util.Map;
import java.util.Set;

@DoApiPlugin
public class MeadowDoAPI implements DoApiAPI {

    @Override
    public void registerBlocks(Set<Block> blocks) {

    }

    @Override
    @SuppressWarnings("deprecation")
    public <T extends LivingEntity> void registerHat(Map<Item, EntityModel<T>> map, EntityModelSet entityModelSet) {
        ArmorRegistry.registerHatModels(map, entityModelSet);
    }

    @Override
    public <T extends LivingEntity> void registerArmor(CustomArmorManager<T> customArmorManager, EntityModelSet entityModelSet) {
        ArmorRegistry.registerArmorModels(customArmorManager, entityModelSet);
    }
}