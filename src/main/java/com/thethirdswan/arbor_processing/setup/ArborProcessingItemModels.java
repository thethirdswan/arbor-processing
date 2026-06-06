package com.thethirdswan.arbor_processing.setup;

import com.therighthon.afc.common.blocks.AFCWood;
import com.thethirdswan.arbor_processing.ArborProcessing;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ArborProcessingItemModels extends ItemModelProvider {
    public ArborProcessingItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ArborProcessing.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (AFCWood wood : AFCWood.values()) {
            withExistingParent(wood.getSerializedName() + "_bark", ResourceLocation.parse("item/generated")).texture("layer0", ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "item/bark/" + wood.getSerializedName()));
            withExistingParent(wood.getSerializedName() + "_bark_powder", ResourceLocation.parse("item/generated")).texture("layer0", ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "item/bark_powder/" + wood.getSerializedName()));
        }

        for (AFCWoodVariants woodVariant : AFCWoodVariants.values()) {
            withExistingParent(woodVariant.name().toLowerCase() + "_bark", ResourceLocation.parse("item/generated")).texture("layer0", ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "item/bark/" + woodVariant.name().toLowerCase()));
            withExistingParent(woodVariant.name().toLowerCase() + "_bark_powder", ResourceLocation.parse("item/generated")).texture("layer0", ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "item/bark_powder/" + woodVariant.name().toLowerCase()));
        }
    }
}
