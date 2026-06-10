package net.el_redstoniano.mccourse.datagen;

import net.el_redstoniano.mccourse.block.ModBlocks;
import net.el_redstoniano.mccourse.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                List<ItemLike> BISMUTH_SMELTABLES = List.of(ModItems.RAW_BISMUTH, ModBlocks.BISMUTH_ORE,
                        ModBlocks.BISMUTH_DEEPSLATE_ORE, ModBlocks.BISMUTH_NETHER_ORE, ModBlocks.BISMUTH_END_ORE);

                oreSmelting(BISMUTH_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS,
                        ModItems.BISMUTH, 0.25f, 200, "bismuth");
                oreBlasting(BISMUTH_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS,
                        ModItems.BISMUTH, 0.25f, 100, "bismuth");

                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.BISMUTH,
                        RecipeCategory.DECORATIONS, ModBlocks.BISMUTH_BLOCK);
                // Short version for raw bismuth block
                /*nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_BISMUTH,
                        RecipeCategory.DECORATIONS, ModBlocks.RAW_BISMUTH_BLOCK);*/

                // Long/manual version for raw bismuth block
                shaped(RecipeCategory.MISC, ModBlocks.RAW_BISMUTH_BLOCK, 1)
                        .pattern("RRR")
                        .pattern("RRR")
                        .pattern("RRR")
                        .define('R', ModItems.RAW_BISMUTH)
                        .unlockedBy(getHasName(ModItems.RAW_BISMUTH), has(ModItems.RAW_BISMUTH))
                        .save(output);

                shapeless(RecipeCategory.MISC, ModItems.RAW_BISMUTH, 9)
                        .requires(ModBlocks.RAW_BISMUTH_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.RAW_BISMUTH_BLOCK), has(ModBlocks.RAW_BISMUTH_BLOCK))
                        .save(output/*, new_"name_to_avoid_duplicate_recipe_names" */);

                // Magic block
                shapeless(RecipeCategory.MISC, ModBlocks.MAGIC_BLOCK, 1)
                        .requires(Blocks.GOLD_BLOCK)
                        .requires(ModItems.BISMUTH)
                        .requires(Blocks.LAPIS_BLOCK)
                        .unlockedBy(getHasName(Blocks.GOLD_BLOCK), has(Blocks.GOLD_BLOCK))
                        .unlockedBy(getHasName(ModItems.BISMUTH), has(ModItems.BISMUTH))
                        .unlockedBy(getHasName(Blocks.LAPIS_BLOCK), has(Blocks.LAPIS_BLOCK))
                        .save(output);

                // Stairs -> Builder == unlockedBy + save
                stairBuilder(ModBlocks.BISMUTH_STAIRS, Ingredient.of(ModBlocks.BISMUTH_BLOCK))
                        .unlockedBy(getHasName(ModBlocks.BISMUTH_BLOCK), has(ModBlocks.BISMUTH_BLOCK))
                        .save(output);
                // Slab -> Not a builder == no need of unlockedBy + save
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BISMUTH_SLAB, ModBlocks.BISMUTH_BLOCK);

                // Button
                buttonBuilder(ModBlocks.BISMUTH_BUTTON, Ingredient.of(ModBlocks.BISMUTH_BLOCK))
                        .unlockedBy(getHasName(ModBlocks.BISMUTH_BLOCK), has(ModBlocks.BISMUTH_BLOCK))
                        .save(output);

                // Pressure plate
                pressurePlate(ModBlocks.BISMUTH_PRESSURE_PLATE, ModBlocks.BISMUTH_BLOCK);
            }
        };
    }

    @Override
    public String getName() {
        return "MCCourse Recipes";
    }
}
