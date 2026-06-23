package net.el_redstoniano.mccourse.tag;

import net.el_redstoniano.mccourse.MCCourse;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_BISMUTH_TOOL = createTag("needs_bismuth_tool");
        public static final TagKey<Block> INCORRECT_FOR_BISMUTH_TOOL = createTag("incorrect_for_bismuth_tool");

        public static final TagKey<Block> PAXEL_MINEABLE = createTag("mineable/paxel");

        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, MCCourse.id(name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
        public static final TagKey<Item> BISMUTH_REPAIRABLES = createTag("bismuth_repairables");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, MCCourse.id(name));
        }
    }
}
