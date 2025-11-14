package com.fys.demonworlds.datagen;

import com.fys.demonworlds.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.internal.NeoForgeDataMapsProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

/**
 * @author fys
 * @date 2025/10/27
 * @description
 */
public class ModNeoForgeProvider extends NeoForgeDataMapsProvider {


    public ModNeoForgeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
//        super.gather();
        builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(ModItems.DARK_DUST, new FurnaceFuel(200), false)
        ;
    }
}
