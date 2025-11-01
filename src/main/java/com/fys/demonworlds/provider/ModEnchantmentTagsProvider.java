package com.fys.demonworlds.provider;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.enchantment.ModEnchantments;
import com.fys.demonworlds.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * @author fys
 * @date 2025/10/26
 * @description
 */
public class ModEnchantmentTagsProvider extends EnchantmentTagsProvider {

    public ModEnchantmentTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ModConstants.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(EnchantmentTags.IN_ENCHANTING_TABLE)
                //这种方式发生在bootstrap之前，无法进行注册，需要使用下面的方式，可以注册“可能存在但尚未注册”的附魔
//                .add(ModEnchantments.LIGHTNING_ENCHANTMENT)
                .addOptional(ModEnchantments.LIGHTNING_ENCHANTMENT)
        ;
    }
}
