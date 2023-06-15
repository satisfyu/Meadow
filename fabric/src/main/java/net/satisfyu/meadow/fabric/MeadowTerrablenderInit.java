package net.satisfyu.meadow.fabric;


import net.satisfyu.meadow.world.ModRegion;
import terrablender.api.TerraBlenderApi;

public class MeadowTerrablenderInit implements TerraBlenderApi {

    @Override
    public void onTerraBlenderInitialized() {
        ModRegion.loadTerrablender();
    }
}