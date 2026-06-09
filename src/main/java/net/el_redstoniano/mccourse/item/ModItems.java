package net.el_redstoniano.mccourse.item;

import net.el_redstoniano.mccourse.MCCourse;
import net.el_redstoniano.mccourse.food.ModFoodProperties;
import net.el_redstoniano.mccourse.item.custom.ChiselItem;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ModItems {
    public static final Item BISMUTH = registerItem("bismuth", Item::new);
    public static final Item RAW_BISMUTH = registerItem("raw_bismuth", Item::new);
    public static final Item CHISEL = registerItem("chisel",
            properties -> new ChiselItem(properties.durability(32)));
    public static final Item CAULIFLOWER = registerItem("cauliflower",
            properties -> new Item(properties.food(ModFoodProperties.CAULIFLOWER,
                    ModFoodProperties.CAULIFLOWER_EFFECT)));
    public static final Item STARLIGHT_ASHES = registerItem("starlight_ashes", Item::new);

    public static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, MCCourse.id(name),
                function.apply(new Item.Properties().setId(
                        ResourceKey.create(Registries.ITEM, MCCourse.id(name))
                )));
    }

    public static void registerModItems(){
        MCCourse.LOGGER.info("Registering mod items for : " + MCCourse.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register(output -> {
                    output.accept(BISMUTH);
                    output.accept(RAW_BISMUTH);
                });
    }
}
