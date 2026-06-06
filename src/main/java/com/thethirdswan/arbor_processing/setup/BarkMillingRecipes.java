package com.thethirdswan.arbor_processing.setup;

import com.simibubi.create.api.data.recipe.MillingRecipeGen;
import com.therighthon.afc.common.blocks.AFCWood;
import com.thethirdswan.arbor_processing.ArborProcessing;
import net.minecraft.data.PackOutput;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.thethirdswan.arbor_processing.setup.ArborProcessingItems.AP_BARKS;
import static com.thethirdswan.arbor_processing.setup.ArborProcessingItems.AP_BARK_POWDERS;

public class BarkMillingRecipes extends MillingRecipeGen {
    public BarkMillingRecipes(PackOutput output) {
        super(output, ArborProcessing.MODID);
    }

    public static Map<String, GeneratedRecipe> MILLING_RECIPES = new HashMap<>();

    {
        Arrays.stream(AFCWood.values()).forEach(item -> MILLING_RECIPES.put(item.getSerializedName(), create("bark_powder/" + item.getSerializedName(), b -> b.whenModLoaded("create").require(AP_BARKS.get(item.getSerializedName()).get()).duration(240).output(AP_BARK_POWDERS.get(item.getSerializedName()).get()))));
        for (AFCWoodVariants wood : AFCWoodVariants.values()) {
            MILLING_RECIPES.put(wood.name().toLowerCase(), MILLING_RECIPES.put(wood.name().toLowerCase(), create("bark_powder/" + wood.name().toLowerCase(), b -> b.whenModLoaded("create").require(AP_BARKS.get(wood.name().toLowerCase()).get()).duration(240).output(AP_BARK_POWDERS.get(wood.name().toLowerCase()).get()))));
        }
    }
}
