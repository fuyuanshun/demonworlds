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

    public ModEnchantmentTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ModConstants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(EnchantmentTags.IN_ENCHANTING_TABLE)
                .addOptional(ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "lightning_enchantment"))
        ;
    }
}
