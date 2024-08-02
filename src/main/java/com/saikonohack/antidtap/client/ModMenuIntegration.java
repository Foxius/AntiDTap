package com.saikonohack.antidtap.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screen.Screen;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.translatable("title.antidtap.config"));

            ConfigCategory general = builder.getOrCreateCategory(Text.translatable("category.antidtap.general"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.antidtap.enableDisconnection"), ConfigData.enableDisconnection)
                    .setDefaultValue(true)
                    .setSaveConsumer(newValue -> ConfigData.enableDisconnection = newValue)
                    .build());

            return builder.build();
        };
    }
}
