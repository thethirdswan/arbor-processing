package com.thethirdswan.arbor_processing.setup;

import com.simibubi.create.api.data.recipe.CuttingRecipeGen;
import com.therighthon.afc.common.blocks.AFCWood;
import com.thethirdswan.arbor_processing.ArborProcessing;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import tfclumberjack.init.TfcLumberjackModItems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.thethirdswan.arbor_processing.setup.ArborProcessingItems.AP_BARKS;

public class WoodCuttingRecipes extends CuttingRecipeGen {
    public WoodCuttingRecipes(PackOutput output) {
        super(output, ArborProcessing.MODID);
    }

    public static Map<String, GeneratedRecipe> CUTTING_RECIPES = new HashMap<>();

    {
        Arrays.stream(AFCWood.values()).forEach(item -> {
            CUTTING_RECIPES.put(item.getSerializedName(), create("debark/wood/" + item.getSerializedName(), b -> b.whenModLoaded("create").require(item.getBlock(Wood.BlockType.WOOD).get()).duration(150).output(item.getBlock(Wood.BlockType.STRIPPED_WOOD).get()).output(AP_BARKS.get(item.getSerializedName()).get())));
            CUTTING_RECIPES.put(item.getSerializedName(), create("debark/log/" + item.getSerializedName(), b -> b.whenModLoaded("create").require(item.getBlock(Wood.BlockType.LOG).get()).duration(150).output(item.getBlock(Wood.BlockType.STRIPPED_LOG).get()).output(AP_BARKS.get(item.getSerializedName()).get())));
            CUTTING_RECIPES.put(item.getSerializedName(), create("supports/wood/" + item.getSerializedName(), b -> b.whenModLoaded("create").require(item.getBlock(Wood.BlockType.STRIPPED_WOOD).get()).duration(300).output(ForgeRegistries.BLOCKS.getValue(ResourceLocation.fromNamespaceAndPath("afc", "wood/vertical_support/" + item.getSerializedName())), 3).output(TfcLumberjackModItems.SAWDUST.get(), 2)));
            CUTTING_RECIPES.put(item.getSerializedName(), create("supports/log/" + item.getSerializedName(), b -> b.whenModLoaded("create").require(item.getBlock(Wood.BlockType.STRIPPED_LOG).get()).duration(300).output(ForgeRegistries.BLOCKS.getValue(ResourceLocation.fromNamespaceAndPath("afc", "wood/vertical_support/" + item.getSerializedName())), 3).output(TfcLumberjackModItems.SAWDUST.get(), 2)));
            CUTTING_RECIPES.put(item.getSerializedName(), create("lumber/" + item.getSerializedName(), b -> b.whenModLoaded("create").require(item.getBlock(Wood.BlockType.VERTICAL_SUPPORT).get()).duration(300).output(ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("afc", "wood/lumber/" + item.getSerializedName())), 4).output(TfcLumberjackModItems.SAWDUST.get(), 2)));
        });
        for (AFCWoodVariants wood : AFCWoodVariants.values()) {
            CUTTING_RECIPES.put(wood.name().toLowerCase(), create("debark/wood/" + wood.originalWood + "_from_" + wood.name().toLowerCase(), b -> b.whenModLoaded("create").require(ForgeRegistries.BLOCKS.getValue(ResourceLocation.fromNamespaceAndPath("afc", "wood/wood/" + wood.name().toLowerCase()))).duration(150).output(ForgeRegistries.BLOCKS.getValue(wood.getOriginalWood("stripped_wood"))).output(AP_BARKS.get(wood.name().toLowerCase()).get())));
            CUTTING_RECIPES.put(wood.name().toLowerCase(), create("debark/log/" + wood.originalWood + "_from_" + wood.name().toLowerCase(), b -> b.whenModLoaded("create").require(ForgeRegistries.BLOCKS.getValue(ResourceLocation.fromNamespaceAndPath("afc", "wood/log/" + wood.name().toLowerCase()))).duration(150).output(ForgeRegistries.BLOCKS.getValue(wood.getOriginalWood("stripped_log"))).output(AP_BARKS.get(wood.name().toLowerCase()).get())));
        }
    }
}
