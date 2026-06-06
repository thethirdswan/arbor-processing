package com.thethirdswan.arbor_processing.setup;

import com.thethirdswan.arbor_processing.ArborProcessing;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.textures.NamespacedDirectoryLister;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SpriteSourceProvider;

import java.util.concurrent.CompletableFuture;

public class ArborProcessingSpriteProvider extends SpriteSourceProvider {
    public ArborProcessingSpriteProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future, ExistingFileHelper fileHelper) {
        super(output, future, ArborProcessing.MODID, fileHelper);
    }

    @Override
    protected void gather() {
        atlas(BLOCKS_ATLAS).addSource(new NamespacedDirectoryLister(ArborProcessing.MODID, "textures/bark", ArborProcessing.MODID));
        atlas(BLOCKS_ATLAS).addSource(new NamespacedDirectoryLister(ArborProcessing.MODID, "textures/bark_powder", ArborProcessing.MODID));
    }
}
