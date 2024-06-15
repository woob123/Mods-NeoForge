package net.woob123.testmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.woob123.testmod.TestMod;
import net.woob123.testmod.block.ModBlocks;
import net.woob123.testmod.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> SAPPHIRE_SMELTABLES = List.of(ModBlocks.SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get());
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        //Blasting recipes -> can check the RecipeProvider class for more methods for different kinds of recipes
        oreBlasting(pRecipeOutput, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, (ItemLike) ModItems.SAPPHIRE, 0.25f, 100, "sapphire");
        oreSmelting(pRecipeOutput, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, (ItemLike) ModItems.SAPPHIRE, 0.25f, 100, "sapphire");

        //Shaped recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SAPPHIRE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SAPPHIRE.get())
                .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_SAPPHIRE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.RAW_SAPPHIRE.get())
                .unlockedBy(getHasName(ModItems.RAW_SAPPHIRE.get()), has(ModItems.RAW_SAPPHIRE.get()))
                .save(pRecipeOutput);

        //Shapeless recipes
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 9)
                .requires(ModBlocks.SAPPHIRE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SAPPHIRE_BLOCK.get()), has(ModBlocks.SAPPHIRE_BLOCK.get()))
                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_SAPPHIRE.get(), 9)
                .requires(ModBlocks.RAW_SAPPHIRE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RAW_SAPPHIRE_BLOCK.get()), has(ModBlocks.RAW_SAPPHIRE_BLOCK.get()))
                .save(pRecipeOutput);
    }

    protected static void oreSmelting(
            RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(
                pRecipeOutput,
                RecipeSerializer.SMELTING_RECIPE,
                SmeltingRecipe::new,
                pIngredients,
                pCategory,
                pResult,
                pExperience,
                pCookingTime,
                pGroup,
                "_from_smelting"
        );
    }

    protected static void oreBlasting(
            RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup
    ) {
        oreCooking(
                pRecipeOutput,
                RecipeSerializer.BLASTING_RECIPE,
                BlastingRecipe::new,
                pIngredients,
                pCategory,
                pResult,
                pExperience,
                pCookingTime,
                pGroup,
                "_from_blasting"
        );
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(
            RecipeOutput pRecipeOutput,
            RecipeSerializer<T> pSerializer,
            AbstractCookingRecipe.Factory<T> pRecipeFactory,
            List<ItemLike> pIngredients,
            RecipeCategory pCategory,
            ItemLike pResult,
            float pExperience,
            int pCookingTime,
            String pGroup,
            String pSuffix
    ) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pSerializer, pRecipeFactory)
                    .group(pGroup)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, TestMod.MOD_ID + ":" + getItemName(pResult) + pSuffix + "_" + getItemName(itemlike));
        }
    }

}
