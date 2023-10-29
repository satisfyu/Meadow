package net.satisfyu.meadow.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.cristelknight.doapi.config.jankson.config.CommentedConfig;
import net.minecraft.Util;

import java.util.HashMap;

public record MeadowConfig(boolean renderCustomEntityTextures) implements CommentedConfig<MeadowConfig> {

    private static MeadowConfig INSTANCE = null;
    public static final MeadowConfig DEFAULT = new MeadowConfig(true);

    public static final Codec<MeadowConfig> CODEC = RecordCodecBuilder.create((builder) -> builder.group(
            Codec.BOOL.fieldOf("render_custom_entity_textures").orElse(DEFAULT.renderCustomEntityTextures).forGetter((c) -> c.renderCustomEntityTextures)
    ).apply(builder, MeadowConfig::new));

    @Override
    public HashMap<String, String> getComments() {
        return Util.make(new HashMap<>(), map -> {
            map.put("render_custom_entity_textures", """
                    Render custom textures for our mob variants""");
        });
    }

    @Override
    public String getHeader() {
        return """
               Meadow Config
               
               ===========
               Discord: https://discord.gg/Vqu6wYZwdZ
               Modrinth: https://modrinth.com/mod/lets-do-cheese
               CurseForge: https://www.curseforge.com/minecraft/mc-mods/welcome-to-meadow""";
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

    @Override
    public Codec<MeadowConfig> getCodec() {
        return CODEC;
    }

    @Override
    public boolean isSorted() {
        return false;
    }

    @Override
    public void setInstance(MeadowConfig meadowConfig) {
        INSTANCE = meadowConfig;
    }
}
