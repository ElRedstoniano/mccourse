package net.el_redstoniano.mccourse.datagen;

import net.el_redstoniano.mccourse.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.BISMUTH_BLOCK)
                .add(ModBlocks.RAW_BISMUTH_BLOCK)
                .add(ModBlocks.BISMUTH_ORE)
                .add(ModBlocks.BISMUTH_DEEPSLATE_ORE)
                .add(ModBlocks.BISMUTH_NETHER_ORE)
                .add(ModBlocks.BISMUTH_END_ORE)
        ;

        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BISMUTH_DEEPSLATE_ORE);

        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.BISMUTH_END_ORE);
    }
}
