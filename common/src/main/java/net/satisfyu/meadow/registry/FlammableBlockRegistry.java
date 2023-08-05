package net.satisfyu.meadow.registry;

import de.cristelknight.doapi.DoApiExpectPlatform;
import net.minecraft.world.level.block.Block;

public class FlammableBlockRegistry {

    public static void init(){
        add(5, 20,
                ObjectRegistry.PINE_BARN_DOOR.get(),
                ObjectRegistry.PINE_BEAM.get(),
                ObjectRegistry.PINE_BARN_TRAPDOOR.get(),
                ObjectRegistry.PINE_DOOR.get(),
                ObjectRegistry.PINE_FENCE_GATE.get(),
                ObjectRegistry.PINE_LEAVES.get(),
                ObjectRegistry.PINE_LEAVES_2.get(),
                ObjectRegistry.PINE_BUTTON.get(),
                ObjectRegistry.PINE_LOG.get(),
                ObjectRegistry.PINE_PLANKS.get(),
                ObjectRegistry.PINE_FENCE.get(),
                ObjectRegistry.PINE_RAILING.get(),
                ObjectRegistry.PINE_PRESSURE_PLATE.get(),
                ObjectRegistry.PINE_SLAB.get(),
                ObjectRegistry.PINE_STAIRS.get(),
                ObjectRegistry.PINE_WOOD.get(),
                ObjectRegistry.PINE_TRAPDOOR.get(),
                ObjectRegistry.STRIPPED_PINE_LOG.get(),
                ObjectRegistry.STRIPPED_PINE_WOOD.get(),
                ObjectRegistry.FRAME.get(),
                ObjectRegistry.WOODCUTTER.get(),
                ObjectRegistry.SHUTTER_BLOCK.get(),
                ObjectRegistry.SHUTTER_BLOCK_BERRY.get(),
                ObjectRegistry.SHUTTER_BLOCK_FIR.get(),
                ObjectRegistry.SHUTTER_BLOCK_POPPY.get(),
                ObjectRegistry.SHELF.get(),
                ObjectRegistry.CHEESE_RACK.get(),
                ObjectRegistry.ROCKY_CARPET.get(),
                ObjectRegistry.ROCKY_BED.get(),
                ObjectRegistry.ROCKY_WOOL.get(),
                ObjectRegistry.FRAME.get(),
                ObjectRegistry.CHEESE_FORM.get(),
                ObjectRegistry.FIRE_LOG.get(),
                ObjectRegistry.WATERING_CAN.get(),
                ObjectRegistry.WOODEN_CAULDRON.get(),
                ObjectRegistry.CAMERA.get(),
                ObjectRegistry.DOORMAT.get(),
                ObjectRegistry.WOODEN_FLOWER_BOX.get(),
                ObjectRegistry.FLOWER_POT_BIG.get(),
                ObjectRegistry.WOODEN_FLOWER_POT.get(),
                ObjectRegistry.WARPED_BED.get(),
                ObjectRegistry.WARPED_CARPET.get(),
                ObjectRegistry.WARPED_WOOL.get(),
                ObjectRegistry.STRAW_BED.get(),
                ObjectRegistry.WATERING_CAN.get(),
                ObjectRegistry.WOODEN_CAULDRON.get(),
                ObjectRegistry.CAMERA.get(),
                ObjectRegistry.DOORMAT.get(),
                ObjectRegistry.WOODEN_FLOWER_BOX.get(),
                ObjectRegistry.FLOWER_POT_BIG.get(),
                ObjectRegistry.WOODEN_FLOWER_POT.get(),
                ObjectRegistry.FLECKED_BED.get(),
                ObjectRegistry.FLECKED_CARPET.get(),
                ObjectRegistry.FLECKED_WOOL.get(),
                ObjectRegistry.HIGHLAND_BED.get(),
                ObjectRegistry.HIGHLAND_CARPET.get(),
                ObjectRegistry.HIGHLAND_WOOL.get(),
                ObjectRegistry.PATCHED_BED.get(),
                ObjectRegistry.PATCHED_CARPET.get(),
                ObjectRegistry.PATCHED_WOOL.get(),
                ObjectRegistry.UMBRA_BED.get(),
                ObjectRegistry.UMBRA_CARPET.get(),
                ObjectRegistry.UMBRA_WOOL.get(),
                ObjectRegistry.INKY_BED.get(),
                ObjectRegistry.INKY_CARPET.get(),
                ObjectRegistry.INKY_WOOL.get()
        );
    }

    public static void add(int burnOdd, int igniteOdd, Block... blocks){
        DoApiExpectPlatform.addFlammable(burnOdd, igniteOdd, blocks);
    }
}
