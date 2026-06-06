package com.thethirdswan.arbor_processing.setup;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

import static com.thethirdswan.arbor_processing.setup.ArborProcessingItems.AP_BARKS;
import static com.thethirdswan.arbor_processing.setup.ArborProcessingItems.AP_BARK_POWDERS;

public class ArborProcessingItemTags extends ItemTagsProvider {
    public ArborProcessingItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags);
    }

    public static TagKey<Item> TFC_COMPOST_BROWN = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("tfc", "compost_browns"));
    public static TagKey<Item> COMPOST_BROWN_LOW = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("tfc", "compost_browns/low"));
    public static TagKey<Item> FIREPIT_FUEL = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("tfc", "firepit_fuel"));

    public static TagKey<Item> BARK = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "bark"));
    public static TagKey<Item> BARK_POWDER = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "bark_powder"));

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        AP_BARKS.forEach((k, v) -> {
            tag(TFC_COMPOST_BROWN).add(v.get());
            tag(COMPOST_BROWN_LOW).add(v.get());
            tag(FIREPIT_FUEL).add(v.get());
            tag(BARK).add(v.get());
        });

        AP_BARK_POWDERS.forEach((k, v) -> {
            tag(TFC_COMPOST_BROWN).add(v.get());
            tag(COMPOST_BROWN_LOW).add(v.get());
            tag(BARK_POWDER).add(v.get());
        });
    }
}
