package net.satisfyu.meadow.registry;

import de.cristelknight.doapi.DoApiExpectPlatform;
import net.minecraft.block.Block;

public class FlammableBlockRegistry {

    public static void init(){
        add(5, 20, ObjectRegistry.PINE_BARN_DOOR.get());
        add(5, 20, ObjectRegistry.PINE_BEAM.get());
        add(5, 20, ObjectRegistry.PINE_BARN_TRAPDOOR.get());
        add(5, 20, ObjectRegistry.PINE_DOOR.get());
        add(5, 20, ObjectRegistry.PINE_FENCE_GATE.get());
        add(5, 20, ObjectRegistry.PINE_LEAVES.get());
        add(5, 20, ObjectRegistry.PINE_LEAVES_2.get());
        add(5, 20, ObjectRegistry.PINE_BUTTON.get());
        add(5, 20, ObjectRegistry.PINE_LOG.get());
        add(5, 20, ObjectRegistry.PINE_PLANKS.get());
        add(5, 20, ObjectRegistry.PINE_FENCE.get());
        add(5, 20, ObjectRegistry.PINE_RAILING.get());
        add(5, 20, ObjectRegistry.PINE_PRESSURE_PLATE.get());
        add(5, 20, ObjectRegistry.PINE_SLAB.get());
        add(5, 20, ObjectRegistry.PINE_STAIRS.get());
        add(5, 20, ObjectRegistry.PINE_WOOD.get());
        add(5, 20, ObjectRegistry.PINE_TRAPDOOR.get());
        add(5, 20, ObjectRegistry.STRIPPED_PINE_LOG.get());
        add(5, 20, ObjectRegistry.STRIPPED_PINE_WOOD.get());
        add(5, 20, ObjectRegistry.FRAME.get());
        add(5, 20, ObjectRegistry.WOODCUTTER.get());
        add(5, 20, ObjectRegistry.SHUTTER_BLOCK.get());
        add(5, 20, ObjectRegistry.SHUTTER_BLOCK_BERRY.get());
        add(5, 20, ObjectRegistry.SHUTTER_BLOCK_FIR.get());
        add(5, 20, ObjectRegistry.SHUTTER_BLOCK_POPPY.get());
        add(5, 20, ObjectRegistry.SHELF.get());
        add(5, 20, ObjectRegistry.CHEESE_RACK.get());
        add(5, 20, ObjectRegistry.ROCKY_CARPET.get());
        add(5, 20, ObjectRegistry.ROCKY_BED.get());
        add(5, 20, ObjectRegistry.ROCKY_SHEEP_WOOL.get());
        add(5, 20, ObjectRegistry.FRAME.get());
        add(5, 20, ObjectRegistry.CHEESE_FORM.get());
        add(5, 20, ObjectRegistry.FIRE_LOG.get());
        add(5, 20, ObjectRegistry.WATERING_CAN.get());
        add(5, 20, ObjectRegistry.WOODEN_CAULDRON.get());
        add(5, 20, ObjectRegistry.CAMERA.get());
        add(5, 20, ObjectRegistry.DOORMAT.get());
        add(5, 20, ObjectRegistry.WOODEN_FLOWER_BOX.get());
        add(5, 20, ObjectRegistry.FLOWER_POT_BIG.get());
        add(5, 20, ObjectRegistry.WOODEN_FLOWER_POT.get());
        add(5, 20, ObjectRegistry.WARPED_BED.get());
        add(5, 20, ObjectRegistry.WARPED_CARPET.get());
        add(5, 20, ObjectRegistry.WARPED_WOOL.get());
        add(5, 20, ObjectRegistry.STRAW_BED.get());
        add(5, 20, ObjectRegistry.WATERING_CAN.get());
        add(5, 20, ObjectRegistry.WOODEN_CAULDRON.get());
        add(5, 20, ObjectRegistry.CAMERA.get());
        add(5, 20, ObjectRegistry.DOORMAT.get());
        add(5, 20, ObjectRegistry.WOODEN_FLOWER_BOX.get());
        add(5, 20, ObjectRegistry.FLOWER_POT_BIG.get());
        add(5, 20, ObjectRegistry.WOODEN_FLOWER_POT.get());
        add(5, 20, ObjectRegistry.FLECKED_BED.get());
        add(5, 20, ObjectRegistry.FLECKED_CARPET.get());
        add(5, 20, ObjectRegistry.FLECKED_WOOL.get());
        add(5, 20, ObjectRegistry.HIGHLAND_BED.get());
        add(5, 20, ObjectRegistry.HIGHLAND_CARPET.get());
        add(5, 20, ObjectRegistry.HIGHLAND_WOOL.get());
        add(5, 20, ObjectRegistry.PATCHED_BED.get());
        add(5, 20, ObjectRegistry.PATCHED_CARPET.get());
        add(5, 20, ObjectRegistry.PATCHED_WOOL.get());
        add(5, 20, ObjectRegistry.UMBRA_BED.get());
        add(5, 20, ObjectRegistry.UMBRA_CARPET.get());
        add(5, 20, ObjectRegistry.UMBRA_WOOL.get());
        add(5, 20, ObjectRegistry.INKY_BED.get());
        add(5, 20, ObjectRegistry.INKY_CARPET.get());
        add(5, 20, ObjectRegistry.INKY_WOOL.get());
    }

    public static void add(int burnOdd, int igniteOdd, Block... blocks){
        DoApiExpectPlatform.addFlammable(burnOdd, igniteOdd, blocks);
    }
}
