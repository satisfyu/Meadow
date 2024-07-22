package net.satisfy.meadow.client.render.entity;

public enum SheepColor {
    FLECKED_WOOL(new float[]{1.0F, 1.0F, 1.0F, 0.0F}),
    PATCHED_WOOL(new float[]{1.0F, 1.0F, 1.0F, 0.0F}),
    ROCKY_WOOL(new float[]{1.0F, 1.0F, 1.0F, 0.0F}),
    INKY_WOOL(new float[]{1.0F, 1.0F, 1.0F, 0.0F});

    private final float[] textureDiffuseColors;

    SheepColor(float[] textureDiffuseColors) {
        this.textureDiffuseColors = textureDiffuseColors;
    }

    public float[] getTextureDiffuseColors() {
        return this.textureDiffuseColors;
    }
}
