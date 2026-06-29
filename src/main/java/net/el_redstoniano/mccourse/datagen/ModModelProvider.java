package net.el_redstoniano.mccourse.datagen;

import net.el_redstoniano.mccourse.block.ModBlocks;
import net.el_redstoniano.mccourse.block.custom.BismuthLampBlock;
import net.el_redstoniano.mccourse.data.ModDataComponents;
import net.el_redstoniano.mccourse.item.ModArmorMaterials;
import net.el_redstoniano.mccourse.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ConditionalItemModel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.conditional.HasComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider { // Blocks and Items
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        //blockModelGenerators.createTrivialCube(ModBlocks.BISMUTH_BLOCK);
        var bismuthFamily = blockModelGenerators.family(ModBlocks.BISMUTH_BLOCK);

        blockModelGenerators.createTrivialCube(ModBlocks.RAW_BISMUTH_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlocks.BISMUTH_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.BISMUTH_DEEPSLATE_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.BISMUTH_NETHER_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.BISMUTH_END_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.MAGIC_BLOCK);

        bismuthFamily.stairs(ModBlocks.BISMUTH_STAIRS);
        bismuthFamily.slab(ModBlocks.BISMUTH_SLAB);

        bismuthFamily.button(ModBlocks.BISMUTH_BUTTON);
        bismuthFamily.pressurePlate(ModBlocks.BISMUTH_PRESSURE_PLATE);

        bismuthFamily.fence(ModBlocks.BISMUTH_FENCE);
        bismuthFamily.fenceGate(ModBlocks.BISMUTH_FENCE_GATE);
        bismuthFamily.wall(ModBlocks.BISMUTH_WALL);

        blockModelGenerators.createDoor(ModBlocks.BISMUTH_DOOR);
        blockModelGenerators.createTrapdoor(ModBlocks.BISMUTH_TRAPDOOR);

        createBismuthLamp(blockModelGenerators);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.BISMUTH, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.RAW_BISMUTH, ModelTemplates.FLAT_ITEM);
        //itemModelGenerators.generateFlatItem(ModItems.CHISEL, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CAULIFLOWER, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.STARLIGHT_ASHES, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_PAXEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerators.generateTrimmableItem(ModItems.BISMUTH_HELMET, ModArmorMaterials.BISMUTH_KEY,
                ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerators.generateTrimmableItem(ModItems.BISMUTH_CHESTPLATE, ModArmorMaterials.BISMUTH_KEY,
                ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerators.generateTrimmableItem(ModItems.BISMUTH_LEGGINGS, ModArmorMaterials.BISMUTH_KEY,
                ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerators.generateTrimmableItem(ModItems.BISMUTH_BOOTS, ModArmorMaterials.BISMUTH_KEY,
                ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);

        generateChisel(itemModelGenerators);
    }

    private void createBismuthLamp(BlockModelGenerators blockModelGenerators) {
        MultiVariant off = BlockModelGenerators.plainVariant(TexturedModel.CUBE.create(ModBlocks.BISMUTH_LAMP, blockModelGenerators.modelOutput));
        MultiVariant on = BlockModelGenerators.plainVariant(blockModelGenerators.createSuffixedVariant(ModBlocks.BISMUTH_LAMP, "_on", ModelTemplates.CUBE_ALL, TextureMapping::cube));
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.BISMUTH_LAMP)
                .with(BlockModelGenerators.createBooleanModelDispatch(BismuthLampBlock.CLICKED, on, off)));
    }

    private void generateChisel(ItemModelGenerators itemModelGenerators) {
        ItemModel.Unbaked unbakedChisel = ItemModelUtils.plainModel(itemModelGenerators.createFlatItemModel(ModItems.CHISEL,
                ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked unbakedUsedChisel = ItemModelUtils.plainModel(itemModelGenerators.createFlatItemModel(ModItems.CHISEL,
                "_used", ModelTemplates.FLAT_HANDHELD_ITEM));

        // Getting the chisel model with texture changes based on coordinates component:
//        itemModelGenerators.itemModelOutput.accept(ModItems.CHISEL,
//                new ClientItem(new ConditionalItemModel.Unbaked(Optional.empty(),
//                        new HasComponent(ModDataComponents.COORDINATES, false),
//                        unbakedUsedChisel, unbakedChisel), new ClientItem.Properties(false, false,
//                        1f)).model());
        // Another way to do the same thing
        itemModelGenerators.itemModelOutput.accept(ModItems.CHISEL,
                /*new ClientItem(*/ItemModelUtils.conditional(Optional.empty(),
                        new HasComponent(ModDataComponents.COORDINATES, false),
                        unbakedUsedChisel, unbakedChisel),
                new ClientItem.Properties(false, false,
                        1f)/*).model()*/);
    }
}
