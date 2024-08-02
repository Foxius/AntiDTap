package com.saikonohack.antidtap.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;

import java.util.Objects;

public class AntiDTapClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && client.player.hurtTime > 0) {
                if (ConfigData.enableDisconnection) {
                    ClientPlayerEntity player = client.player;
                    if (player.getRecentDamageSource() != null) {
                        String damageSourceName = player.getRecentDamageSource().getName();
                        if ("explosion.player".equals(damageSourceName) || "badRespawnPoint".equals(damageSourceName)) {
                            MinecraftClient.getInstance().execute(() -> {
                                Objects.requireNonNull(client.getNetworkHandler()).getConnection().disconnect(Text.literal("Вы успешно спасены от дитя кристала благодаря AntiDTap"));
                            });
                        }
                    }
                }
            }
        });
    }
}
