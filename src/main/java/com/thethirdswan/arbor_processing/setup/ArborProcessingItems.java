package com.thethirdswan.arbor_processing.setup;

import com.therighthon.afc.common.blocks.AFCWood;
import com.thethirdswan.arbor_processing.ArborProcessing;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArborProcessingItems {
    public static final DeferredRegister.Items AP_REGISTRY = DeferredRegister.createItems(ArborProcessing.MODID);
    public static final Map<String, DeferredItem<Item>> AP_BARKS = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> AP_BARK_POWDERS = new HashMap<>();

    public static void listAFCWoods() {
        Arrays.stream(AFCWood.values()).forEach(item -> {
            AP_BARKS.put(item.getSerializedName(), AP_REGISTRY.registerSimpleItem(item.getSerializedName() + "_bark", new Item.Properties()));
            AP_BARK_POWDERS.put(item.getSerializedName(), AP_REGISTRY.registerSimpleItem(item.getSerializedName() + "_bark_powder", new Item.Properties()));
        });

        for (AFCWoodVariants wood : AFCWoodVariants.values()) {
            AP_BARKS.put(wood.name().toLowerCase(), AP_REGISTRY.registerSimpleItem(wood.name().toLowerCase() + "_bark", new Item.Properties()));
            AP_BARK_POWDERS.put(wood.name().toLowerCase(), AP_REGISTRY.registerSimpleItem(wood.name().toLowerCase() + "_bark_powder", new Item.Properties()));
        }
    }
}
