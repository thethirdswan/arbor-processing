package com.thethirdswan.arbor_processing.setup;

import com.thethirdswan.arbor_processing.ArborProcessing;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.List;

import static com.thethirdswan.arbor_processing.setup.ArborProcessingItems.AP_BARKS;
import static com.thethirdswan.arbor_processing.setup.ArborProcessingItems.AP_BARK_POWDERS;

public class ArborProcessingLanguageProvider extends LanguageProvider {
    public ArborProcessingLanguageProvider(PackOutput output) {
        super(output, ArborProcessing.MODID, "en_us");
    }

    public static String textNormalizer(String text) {
        List<String> split = List.of(text.split("_"));
        final String[] finishedText = {""};
        split.forEach(string -> {
            finishedText[0] = finishedText[0] + " " + Character.toUpperCase(string.charAt(0)) + string.substring(1);
        });
        return finishedText[0].trim();
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.arbor_processing", "Processing of the Arbor");
        AP_BARKS.forEach((k, v) -> add(v.get(), textNormalizer(k) + " Bark"));
        AP_BARK_POWDERS.forEach((k, v) -> add(v.get(), textNormalizer(k) + " Bark Powder"));
    }
}
