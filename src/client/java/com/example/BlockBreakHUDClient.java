package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class BlockBreakHUDClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {

            MinecraftClient client = MinecraftClient.getInstance();

            if (client.player == null || client.world == null) return;
            if (client.crosshairTarget == null) return;

            float progress = client.interactionManager.getBlockBreakingProgress();

            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            int barWidth = 120;
            int barHeight = 10;

            int x = (width / 2) - (barWidth / 2);
            int y = height - 70;

            int progressWidth = (int)(barWidth * progress);

            // background
            drawContext.fill(x, y, x + barWidth, y + barHeight, 0x90000000);

            // progress
            drawContext.fill(x, y, x + progressWidth, y + barHeight, 0xFF00FF00);

        });

    }
}
