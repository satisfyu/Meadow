package net.satisfyu.meadow.fabric.terrablender;

import net.satisfyu.meadow.terrablender.MeadowRegion;
import terrablender.api.TerraBlenderApi;

public class TerrablenderFabric implements TerraBlenderApi {

    @Override
    public void onTerraBlenderInitialized() {
        MeadowRegion.loadTerrablender();
    }
}

