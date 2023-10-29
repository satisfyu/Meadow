package net.satisfyu.meadow.client.config;

import de.cristelknight.doapi.config.cloth.CCUtil;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.config.MeadowConfig;
public class ClothConfigScreen {

    public static Screen create(Screen parent) {
        MeadowConfig config = MeadowConfig.DEFAULT.getConfig();
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setDefaultBackgroundTexture(new ResourceLocation("textures/block/dirt.png"))
                .setTitle(Component.translatable(Meadow.MOD_ID + ".config.title").withStyle(ChatFormatting.BOLD));

        ConfigEntries entries = new ConfigEntries(builder.entryBuilder(), config, builder.getOrCreateCategory(CCUtil.categoryName("main", Meadow.MOD_ID)));
        builder.setSavingRunnable(() -> {
            MeadowConfig.DEFAULT.setInstance(entries.createConfig());
            MeadowConfig.DEFAULT.getConfig(true, true);
        });
        return builder.build();
    }


    private static class ConfigEntries {
        private final ConfigEntryBuilder builder;
        private final ConfigCategory category;
        private final BooleanListEntry renderCustomEntityTextures;

        public ConfigEntries(ConfigEntryBuilder builder, MeadowConfig config, ConfigCategory category) {
            this.builder = builder;
            this.category = category;

            renderCustomEntityTextures = createBooleanField("renderCustomEntityTextures", config.renderCustomEntityTextures(), MeadowConfig.DEFAULT.renderCustomEntityTextures(), true);
        }


        public MeadowConfig createConfig() {
            return new MeadowConfig(renderCustomEntityTextures.getValue());
        }


        public BooleanListEntry createBooleanField(String id, boolean value, boolean defaultValue, boolean rr){
            BooleanListEntry e = CCUtil.createBooleanField(Meadow.MOD_ID, id, value, defaultValue, builder);
            e.setRequiresRestart(rr);
            category.addEntry(e);
            return e;
        }
    }
}
