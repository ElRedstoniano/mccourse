package net.el_redstoniano.mccourse.item;

import net.el_redstoniano.mccourse.MCCourse;
import net.el_redstoniano.mccourse.block.ModBlocks;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
    public static final CreativeModeTab BISMUTH_ITEMS_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            MCCourse.id("bismuth_items"),
            FabricCreativeModeTab.builder()
                    .icon( () -> new ItemStack(ModItems.BISMUTH))
                    .title(Component.translatable("creativetab.mccourse.bismuth_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.BISMUTH);
                        output.accept(ModItems.RAW_BISMUTH);
                    }).build());

    public static final CreativeModeTab BISMUTH_BLOCKS_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            MCCourse.id("bismuth_blocks"),
            FabricCreativeModeTab.builder()
                    .icon( () -> new ItemStack(ModBlocks.BISMUTH_BLOCK))
                    .title(Component.translatable("creativetab.mccourse.bismuth_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.BISMUTH_BLOCK);
                        output.accept(ModBlocks.RAW_BISMUTH_BLOCK);
                        output.accept(ModBlocks.BISMUTH_ORE);
                        output.accept(ModBlocks.BISMUTH_DEEPSLATE_ORE);
                    }).build());

    public static void registerCreativeModeTabs() {
        MCCourse.LOGGER.info("Registering creative mode tabs for: " + MCCourse.MOD_ID);
    }
}
