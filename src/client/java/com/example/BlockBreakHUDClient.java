package com.example.blockbreakhud;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public class BlockBreakHUDClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        HudRenderCallback.EVENT.register((DrawContext drawContext, float tickDelta) -> {

            MinecraftClient client = MinecraftClient.getInstance();

            if (client.player == null || client.world == null) return;
            if (client.crosshairTarget == null) return;

            TextRenderer textRenderer = client.textRenderer;

            // Example placeholder progress
            float progress = client.interactionManager.getBlockBreakingProgress();

            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            int barWidth = 120;
            int barHeight = 10;

            int x = (width / 2) - (barWidth / 2);
            int y = height - 80;

            int progressWidth = (int)(barWidth * progress);

            // Background
            drawContext.fill(x, y, x + barWidth, y + barHeight, 0x90000000);

            // Progress
            drawContext.fill(x, y, x + progressWidth, y + barHeight, 0xFF00FF00);

            // Text
            drawContext.drawText(
                    textRenderer,
                    "Mining...",
                    x,
                    y - 12,
                    0xFFFFFF,
                    true
            );

        });
    }
}
