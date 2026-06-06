package com.thethirdswan.arbor_processing.setup;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

import static com.thethirdswan.arbor_processing.setup.ArborProcessingRecipes.registerCreateProcessing;

public class ArborProcessingDataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> future = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        ArborProcessingBlockTags blockTags = new ArborProcessingBlockTags(output, future, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new ArborProcessingItemTags(output, future, blockTags.contentsGetter()));
        generator.addProvider(event.includeServer(), new ArborProcessingRecipes(output, future));
        if (event.includeServer()) registerCreateProcessing(generator, output, future);

        generator.addProvider(event.includeClient(), new ArborProcessingLanguageProvider(output));
        generator.addProvider(event.includeClient(), new ArborProcessingItemModels(output, existingFileHelper));
        generator.addProvider(event.includeClient(), new ArborProcessingSpriteProvider(output, future, existingFileHelper));

    }
}
