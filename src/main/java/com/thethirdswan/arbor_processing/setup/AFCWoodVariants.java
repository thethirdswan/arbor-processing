package com.thethirdswan.arbor_processing.setup;

import net.minecraft.resources.ResourceLocation;

public enum AFCWoodVariants {
    RAINBOW_EUCALYPTUS("afc", "eucalyptus"),
    BLACK_OAK("tfc", "oak"),
    POPLAR("tfc", "aspen"),
    GUM_ARABIC("tfc", "acacia"),
    REDCEDAR("afc", "cypress"),
    RUBBER_FIG("afc", "fig");

    final String mod_id;
    final String originalWood;

    AFCWoodVariants(String modid, String originalWood) {
        this.mod_id = modid;
        this.originalWood = originalWood;
    }

    public ResourceLocation getOriginalWood(String woodType) {
        return ResourceLocation.fromNamespaceAndPath(this.mod_id, "wood/" + woodType + "/" + this.originalWood);
    }
}
