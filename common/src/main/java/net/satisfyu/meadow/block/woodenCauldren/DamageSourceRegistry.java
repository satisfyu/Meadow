package net.satisfyu.meadow.block.woodenCauldren;

import net.minecraft.entity.damage.DamageSource;
import net.satisfyu.meadow.Meadow;

public class DamageSourceRegistry extends DamageSource {

public static final DamageSource COBBLESTONE_FURNACE_BLOCK = (new DamageSourceRegistry("cobblestone_furnace_block")).setFire();

protected DamageSourceRegistry(String name) {
        super(Meadow.MOD_ID + "." + name);
        }
}