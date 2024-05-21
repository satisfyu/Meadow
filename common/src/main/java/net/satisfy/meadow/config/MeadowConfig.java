package net.satisfy.meadow.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.cristelknight.doapi.config.jankson.config.CommentedConfig;

import java.util.HashMap;


public record MeadowConfig(boolean customEntityTextures)
        implements CommentedConfig<MeadowConfig> {

    private static MeadowConfig INSTANCE = null;

    public static final MeadowConfig DEFAULT = new MeadowConfig(true);

    public static final Codec<MeadowConfig> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    Codec.BOOL.fieldOf("custom_entity_textures").orElse(DEFAULT.customEntityTextures).forGetter(c -> c.customEntityTextures)
            ).apply(builder, MeadowConfig::new)
    );

    @Override
    public HashMap<String, String> getComments() {
        HashMap<String, String> map = new HashMap<>();
        map.put("custom_entity_textures", "Enable or disable custom entity textures.");
        return map;
    }

    @Override
    public String getHeader() {
        return """
               Meadow Config
               
               ===========
               Discord: https://discord.gg/Vqu6wYZwdZ
               Modrinth: https://modrinth.com/mod/lets-do-meadow
               CurseForge: https://www.curseforge.com/minecraft/mc-mods/lets-do-meadow""";
    }

    @Override
    public String getSubPath() {
        return "meadow/config";
    }

    @Override
    public MeadowConfig getInstance() {
        return INSTANCE;
    }

    @Override
    public MeadowConfig getDefault() {
        return DEFAULT;
    }

    public static MeadowConfig get(){
        return MeadowConfig.DEFAULT.getConfig();
    }

    @Override
    public Codec<MeadowConfig> getCodec() {
        return CODEC;
    }

    @Override
    public boolean isSorted() {
        return false;
    }

    @Override
    public void setInstance(MeadowConfig instance) {
        INSTANCE = instance;
    }
}
