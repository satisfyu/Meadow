package net.satisfyu.meadow.mixin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.BedPart;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;
import net.satisfyu.meadow.registry.ObjectRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Set;
import java.util.stream.Collectors;

@Mixin(PointOfInterestTypes.class)
public abstract class PointOfInterests {
    @ModifyArgs(method = "registerAndGetDefault", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/poi/PointOfInterestTypes;register(Lnet/minecraft/util/registry/Registry;Lnet/minecraft/util/registry/RegistryKey;Ljava/util/Set;II)Lnet/minecraft/world/poi/PointOfInterestType;"))
    private static void setRegistry(Args args) {
        RegistryKey<PointOfInterestType> key = args.get(1);
        if(key.equals(PointOfInterestTypes.HOME)){
            Set<BlockState> states = args.get(2);

            Set<BlockState> statesNew = ImmutableList.of(
                    ObjectRegistry.FLECKED_BED.get(), ObjectRegistry.HIGHLAND_BED.get(), ObjectRegistry.PATCHED_BED.get(),
                            ObjectRegistry.ROCKY_BED.get(), ObjectRegistry.UMBRA_BED.get(), ObjectRegistry.INKY_BED.get(),
                            ObjectRegistry.INKY_BED.get())

                    .stream().flatMap(block -> block.getStateManager().getStates().stream()).filter(blockState -> blockState.get(BedBlock.PART) == BedPart.HEAD).collect(Collectors.toSet());

            statesNew.addAll(states);
            args.set(2, statesNew);

        }
    }
}
