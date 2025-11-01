package com.fys.demonworlds.provider;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.item.ModItems;
import com.fys.demonworlds.tags.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * @author fys
 * @date 2025/10/22
 * @description
 */
public class ModItemTagsProvider extends ItemTagsProvider {


    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ModConstants.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModItemTags.CLEAR_CURSE_MATERIAL)
//                .add(ModI)
        ;

        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.END_HELMET.get())
                .add(ModItems.END_CHESTPLATE.get())
                .add(ModItems.END_LEGGINS.get())
                .add(ModItems.END_BOOTS.get())
                ;

        tag(ItemTags.WEAPON_ENCHANTABLE)
                .add(ModItems.END_SWORD.get())
                ;
        tag(ItemTags.SWORD_ENCHANTABLE)
                .add(ModItems.END_SWORD.get());
    }
}
