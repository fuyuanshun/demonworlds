package com.fys.demonworlds.attachment;

import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

/**
 * Attachment 类型注册。
 *
 * @author fys
 * @since 2026-05-28
 */
public class ModAttachments {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, ModConstants.MOD_ID);

    public static final Supplier<AttachmentType<ItemStack>> ELYTRA_SLOT =
            ATTACHMENT_TYPES.register("elytra_slot",
                    () -> AttachmentType.builder(() -> ItemStack.EMPTY)
                            .serialize(ItemStack.CODEC)
                            .sync(ItemStack.OPTIONAL_STREAM_CODEC)
                            .copyOnDeath()
                            .build());
}
