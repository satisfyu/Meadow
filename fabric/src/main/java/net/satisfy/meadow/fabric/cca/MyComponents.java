package net.satisfy.meadow.fabric.cca;

import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Sheep;
import net.satisfy.meadow.util.MeadowIdentifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistryV3;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

public class MyComponents implements EntityComponentInitializer {
    public static final ComponentKey<VarHolder> VAR =
            ComponentRegistryV3.INSTANCE.getOrCreate(MeadowIdentifier.of("var"), VarHolder.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(Cow.class, VAR, VarHolder::new);
        registry.registerFor(Chicken.class, VAR, VarHolder::new);
        registry.registerFor(Sheep.class, VAR, VarHolder::new);
    }
}
