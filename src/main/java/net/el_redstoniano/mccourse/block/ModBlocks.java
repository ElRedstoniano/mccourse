package net.el_redstoniano.mccourse.block;

import net.el_redstoniano.mccourse.MCCourse;
import net.el_redstoniano.mccourse.block.custom.MagicBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlocks {
    public static final Block BISMUTH_BLOCK = registerBlock("bismuth_block",
            properties -> new Block(properties.strength(4f)
                    .requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final Block RAW_BISMUTH_BLOCK = registerBlock("raw_bismuth_block",
            properties -> new Block(properties.strength(4f)
                    .requiresCorrectToolForDrops()));

    public static final Block BISMUTH_ORE = registerBlock("bismuth_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2,4),
                    properties.strength(3f).requiresCorrectToolForDrops()));

    public static final Block BISMUTH_DEEPSLATE_ORE = registerBlock("bismuth_deepslate_ore",
            properties -> new DropExperienceBlock(UniformInt.of(3,5),
                    properties.strength(4f).requiresCorrectToolForDrops()));

    public static final Block BISMUTH_NETHER_ORE = registerBlock("bismuth_nether_ore",
            properties -> new DropExperienceBlock(UniformInt.of(3,5),
                    properties.strength(3f).requiresCorrectToolForDrops()));

    public static final Block BISMUTH_END_ORE = registerBlock("bismuth_end_ore",
            properties -> new DropExperienceBlock(UniformInt.of(3,5),
                    properties.strength(4f).requiresCorrectToolForDrops()));

    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            properties -> new MagicBlock(
                    properties.strength(2f)
                            .requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final Block BISMUTH_STAIRS = registerBlock("bismuth_stairs",
            properties -> new StairBlock(ModBlocks.BISMUTH_BLOCK.defaultBlockState(),
                    properties.strength(2f).requiresCorrectToolForDrops()));
    public static final Block BISMUTH_SLAB = registerBlock("bismuth_slab",
            properties -> new SlabBlock(properties.strength(2f).requiresCorrectToolForDrops()));

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block blockToRegister = function.apply(BlockBehaviour.Properties.of()
                .setId(ResourceKey.create(Registries.BLOCK, MCCourse.id(name))));
        registerBlockItem(name, blockToRegister);
        return Registry.register(BuiltInRegistries.BLOCK, MCCourse.id(name), blockToRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, MCCourse.id(name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, MCCourse.id(name)))));
    }

    public static void registerModBlocks() {
        MCCourse.LOGGER.info("Registering blocks for " + MCCourse.MOD_ID);
    }
}
