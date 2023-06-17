package net.satisfyu.meadow.fabric;


import net.satisfyu.meadow.terrablender.MeadowRegion;
import terrablender.api.TerraBlenderApi;

public class MeadowTerrablenderInit implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        MeadowRegion.loadTerrablender();
    }
}