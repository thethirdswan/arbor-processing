package com.thethirdswan.arbor_processing.setup;

import blusunrize.immersiveengineering.api.crafting.builders.SawmillRecipeBuilder;
import com.simibubi.create.api.data.recipe.ProcessingRecipeGen;
import com.therighthon.afc.common.blocks.AFCWood;
import com.thethirdswan.arbor_processing.ArborProcessing;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static com.thethirdswan.arbor_processing.setup.ArborProcessingItems.AP_BARKS;
import static vectorwing.farmersdelight.data.recipe.CuttingRecipes.AXES;

public class ArborProcessingRecipes extends RecipeProvider {
    static final List<ProcessingRecipeGen> GENERATORS = new ArrayList<>();

    public ArborProcessingRecipes(PackOutput output) {
        super(output);
    }

    public static void registerCreateProcessing(DataGenerator gen, PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        GENERATORS.add(new BarkMillingRecipes(output));
        GENERATORS.add(new WoodCuttingRecipes(output));
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

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        //        Sawmill can't use items for saw secondary :skull:
        Arrays.stream(AFCWood.values()).forEach(item -> {
            CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(item.getBlock(Wood.BlockType.WOOD).get()), AXES, item.getBlock(Wood.BlockType.STRIPPED_WOOD).get()).addResult(AP_BARKS.get(item.getSerializedName()).get()).save(consumer, ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "debark/farmers_delight/wood/" + item.getSerializedName()));
            CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(item.getBlock(Wood.BlockType.LOG).get()), AXES, item.getBlock(Wood.BlockType.STRIPPED_LOG).get()).addResult(AP_BARKS.get(item.getSerializedName()).get()).save(consumer, ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "debark/farmers_delight/log/" + item.getSerializedName()));
            SawmillRecipeBuilder.builder(new ItemStack(item.getBlock(Wood.BlockType.VERTICAL_SUPPORT).get(), 4)).addInput(Ingredient.of(item.getBlock(Wood.BlockType.WOOD).get())).setEnergy(1600).addStripped(item.getBlock(Wood.BlockType.STRIPPED_WOOD).get()).addSecondary(new ItemStack(AP_BARKS.get(item.getSerializedName()).get(), 1), true).addSecondary(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("forge", "sawdust")), false).build(consumer, ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "debark_and_supports/wood/immersiveengineering/" + item.getSerializedName()));
            SawmillRecipeBuilder.builder(new ItemStack(item.getBlock(Wood.BlockType.VERTICAL_SUPPORT).get(), 4)).addInput(Ingredient.of(item.getBlock(Wood.BlockType.LOG).get())).setEnergy(1600).addStripped(item.getBlock(Wood.BlockType.STRIPPED_LOG).get()).addSecondary(new ItemStack(AP_BARKS.get(item.getSerializedName()).get(), 1), true).addSecondary(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("forge", "sawdust")), false).build(consumer, ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "debark_and_supports/log/immersiveengineering/" + item.getSerializedName()));
            SawmillRecipeBuilder.builder(new ItemStack(ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("afc", "wood/lumber/" + item.getSerializedName())), 6)).addInput(Ingredient.of(item.getBlock(Wood.BlockType.VERTICAL_SUPPORT).get())).setEnergy(1600).addSecondary(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("forge", "sawdust")), false).addSecondary(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("forge", "sawdust")), false).build(consumer, ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "lumber/immersiveengineering/" + item.getSerializedName()));
        });
        for (AFCWoodVariants wood : AFCWoodVariants.values()) {
            CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ForgeRegistries.BLOCKS.getValue(ResourceLocation.fromNamespaceAndPath("afc", "wood/wood/" + wood.name().toLowerCase()))), AXES, ForgeRegistries.BLOCKS.getValue(wood.getOriginalWood("stripped_wood"))).addResult(AP_BARKS.get(wood.name().toLowerCase()).get()).save(consumer, ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "debark/farmers_delight/wood/" + wood.originalWood + "_from_" + wood.name().toLowerCase()));
            CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ForgeRegistries.BLOCKS.getValue(ResourceLocation.fromNamespaceAndPath("afc", "wood/log/" + wood.name().toLowerCase()))), AXES, ForgeRegistries.BLOCKS.getValue(wood.getOriginalWood("stripped_log"))).addResult(AP_BARKS.get(wood.name().toLowerCase()).get()).save(consumer, ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "debark/farmers_delight/log/" + wood.originalWood + "_from_" + wood.name().toLowerCase()));
            SawmillRecipeBuilder.builder(new ItemStack(ForgeRegistries.BLOCKS.getValue(wood.getOriginalWood("vertical_support")).asItem(), 4)).addInput(Ingredient.of(ForgeRegistries.BLOCKS.getValue(ResourceLocation.fromNamespaceAndPath("afc", "wood/wood/" + wood.name().toLowerCase())))).setEnergy(1600).addStripped(ForgeRegistries.BLOCKS.getValue(wood.getOriginalWood("stripped_wood"))).addSecondary(AP_BARKS.get(wood.name().toLowerCase()).get(), true).addSecondary(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("forge", "sawdust")), false).build(consumer, ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "debark_and_supports/wood/immersiveengineering/" + wood.originalWood + "_from_" + wood.name().toLowerCase()));
            SawmillRecipeBuilder.builder(new ItemStack(ForgeRegistries.BLOCKS.getValue(wood.getOriginalWood("vertical_support")).asItem(), 4)).addInput(Ingredient.of(ForgeRegistries.BLOCKS.getValue(ResourceLocation.fromNamespaceAndPath("afc", "wood/log/" + wood.name().toLowerCase())))).setEnergy(1600).addStripped(ForgeRegistries.BLOCKS.getValue(wood.getOriginalWood("stripped_log"))).addSecondary(AP_BARKS.get(wood.name().toLowerCase()).get(), true).addSecondary(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("forge", "sawdust")), false).build(consumer, ResourceLocation.fromNamespaceAndPath(ArborProcessing.MODID, "debark_and_supports/log/immersiveengineering/" + wood.originalWood + "_from_" + wood.name().toLowerCase()));
        }
    }
}
