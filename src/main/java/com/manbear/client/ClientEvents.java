package com.manbear.client;

import com.manbear.ManbearMod;
import com.manbear.net.ManbearNet;
import com.manbear.net.StrugglePacket;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/** Reports space-bar presses so you can mash your way out of his grip. */
@Mod.EventBusSubscriber(modid = ManbearMod.MODID, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onKey(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.screen != null) return;
        if (event.getAction() == 1 /* GLFW_PRESS */ && mc.options.keyJump.matches(event.getKey(), event.getScanCode())) {
            ManbearNet.CHANNEL.sendToServer(new StrugglePacket());
        }
    }
}
