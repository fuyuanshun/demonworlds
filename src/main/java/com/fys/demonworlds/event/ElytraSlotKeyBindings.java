package com.fys.demonworlds.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

/**
 * 鞘翅栏快捷键定义。
 *
 * @author fys
 * @since 2026-05-28
 */
public final class ElytraSlotKeyBindings {

    private ElytraSlotKeyBindings() {
        throw new UnsupportedOperationException("Utility class");
    }

    /** G 键 —— 快速装/卸鞘翅栏 */
    public static final KeyMapping ELYTRA_SLOT_KEY = new KeyMapping(
            "key.demonworlds.elytra_slot",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            "key.categories.demonworlds"
    );
}
