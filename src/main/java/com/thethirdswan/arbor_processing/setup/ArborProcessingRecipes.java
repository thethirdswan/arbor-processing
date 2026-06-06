package com.thethirdswan.arbor_processing.setup;

import blusunrize.immersiveengineering.data.recipes.builder.SawmillRecipeBuilder;
import com.simibubi.create.api.data.recipe.ProcessingRecipeGen;
import com.therighthon.afc.common.blocks.AFCWood;
import com.thethirdswan.arbor_processing.ArborProcessing;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.thethirdswan.arbor_processing.setup.ArborProcessingItems.AP_BARKS;
import static vectorwing.farmersdelight.data.recipe.CuttingRecipes.AXES;

public class ArborProcessingRecipes extends RecipeProvider {
    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
//        Sawmill can't use items for saw secondary :skull:
        Arrays.stream(AFCWood.values()).forEach(item -> {
            CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(item.getBlock(Wood.BlockType.WOOD).get()), AXES, item.getBlock(Wood.BlockType.STRIPPED_WOOD).get()).addResult(AP_BARKS.get(item.getSerializedName())).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "debark/farmers_delight/wood/" + item.getSerializedName()));
            CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(item.getBlock(Wood.BlockType.LOG).get()), AXES, item.getBlock(Wood.BlockType.STRIPPED_LOG).get()).addResult(AP_BARKS.get(item.getSerializedName())).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "debark/farmers_delight/log/" + item.getSerializedName()));
            SawmillRecipeBuilder.builder().input(Ingredient.of(item.getBlock(Wood.BlockType.WOOD).get())).output(item.getBlock(Wood.BlockType.VERTICAL_SUPPORT).get(), 4).setEnergy(1600).addStripped(item.getBlock(Wood.BlockType.STRIPPED_WOOD).get()).addStripSecondary(AP_BARKS.get(item.getSerializedName()), 1).addSawSecondary(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "sawdust"))).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "debark_and_supports/wood/immersiveengineering/" + item.getSerializedName()));
            SawmillRecipeBuilder.builder().input(Ingredient.of(item.getBlock(Wood.BlockType.LOG).get())).output(item.getBlock(Wood.BlockType.VERTICAL_SUPPORT).get(), 4).setEnergy(1600).addStripped(item.getBlock(Wood.BlockType.STRIPPED_LOG).get()).addStripSecondary(AP_BARKS.get(item.getSerializedName()), 1).addSawSecondary(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "sawdust"))).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "debark_and_supports/log/immersiveengineering/" + item.getSerializedName()));
            SawmillRecipeBuilder.builder().input(Ingredient.of(item.getBlock(Wood.BlockType.VERTICAL_SUPPORT).get())).output(BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("afc", "wood/lumber/" + item.getSerializedName())), 6).setEnergy(1600).addSawSecondary(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "sawdust"))).addSawSecondary(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "sawdust"))).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "lumber/immersiveengineering/" + item.getSerializedName()));
        });
        for (AFCWoodVariants wood : AFCWoodVariants.values()) {
            CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("afc", "wood/wood/" + wood.name().toLowerCase()))), AXES, BuiltInRegistries.BLOCK.get(wood.getOriginalWood("stripped_wood"))).addResult(AP_BARKS.get(wood.name().toLowerCase())).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "debark/farmers_delight/wood/" + wood.originalWood + "_from_" + wood.name().toLowerCase()));
            CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("afc", "wood/log/" + wood.name().toLowerCase()))), AXES, BuiltInRegistries.BLOCK.get(wood.getOriginalWood("stripped_log"))).addResult(AP_BARKS.get(wood.name().toLowerCase())).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "debark/farmers_delight/log/" + wood.originalWood + "_from_" + wood.name().toLowerCase()));
            SawmillRecipeBuilder.builder().input(Ingredient.of(BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("afc", "wood/wood/" + wood.name().toLowerCase())))).output(BuiltInRegistries.BLOCK.get(wood.getOriginalWood("vertical_support")), 4).setEnergy(1600).addStripped(BuiltInRegistries.BLOCK.get(wood.getOriginalWood("stripped_wood"))).addStripSecondary(AP_BARKS.get(wood.name().toLowerCase()), 1).addSawSecondary(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "sawdust"))).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "debark_and_supports/wood/immersiveengineering/" + wood.originalWood + "_from_" + wood.name().toLowerCase()));
            SawmillRecipeBuilder.builder().input(Ingredient.of(BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("afc", "wood/log/" + wood.name().toLowerCase())))).output(BuiltInRegistries.BLOCK.get(wood.getOriginalWood("vertical_support")), 4).setEnergy(1600).addStripped(BuiltInRegistries.BLOCK.get(wood.getOriginalWood("stripped_log"))).addStripSecondary(AP_BARKS.get(wood.name().toLowerCase()), 1).addSawSecondary(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "sawdust"))).build(recipeOutput, ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "debark_and_supports/log/immersiveengineering/" + wood.originalWood + "_from_" + wood.name().toLowerCase()));
        }
    }

    static final List<ProcessingRecipeGen<?, ?, ?>> GENERATORS = new ArrayList<>();

    public ArborProcessingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    public static void registerCreateProcessing(DataGenerator gen, PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        GENERATORS.add(new BarkMillingRecipes(output, registries));
        GENERATORS.add(new WoodCuttingRecipes(output, registries));
        gen.addProvider(true, new DataProvider() {
            @Override
            public CompletableFuture<?> run(CachedOutput output) {
                return CompletableFuture.allOf(GENERATORS.stream().map(gen -> gen.run(output)).toArray(CompletableFuture[]::new));
            }

            @Override
            public String getName() {
                return "Processing of the Arbor's Create Recipes";
            }
        });
    }
}
