package net.woob123.testmod.item;

import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.woob123.testmod.TestMod;
import net.woob123.testmod.item.custom.FuelItem;
import net.woob123.testmod.item.custom.MetalDetectorItem;

public class ModItems {
    //"Holds" the items
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(TestMod.MOD_ID);

    //Adding items
    public static final DeferredHolder<Item, Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire",
            () -> new Item(new Item.Properties()));

    //Adding items with durability and custom item classes
    public static final DeferredHolder<Item, MetalDetectorItem> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(100)));

    //Adding food items
    public static final DeferredHolder<Item, Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));

    //Adding items with 3D models
    public static final DeferredHolder<Item, Item> SAPPHIRE_STAFF = ITEMS.register("sapphire_staff",
            () -> new Item(new Item.Properties().stacksTo(1)));

    //Adding fuel items
    public static final DeferredHolder<Item, FuelItem> PINE_CONE = ITEMS.register("pine_cone",
            () -> new FuelItem(new Item.Properties(), 400));

    //Adding custom tools

    //Custom sword
    public static final DeferredHolder<Item, SwordItem> SAPPHIRE_SWORD = ITEMS.register("sapphire_sword",
            () -> new SwordItem(ModToolTiers.SAPPHIRE, new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.SAPPHIRE, 3, -2.4F))));
    //Custom pickaxe
    public static final DeferredHolder<Item, PickaxeItem> SAPPHIRE_PICKAXE = ITEMS.register("sapphire_pickaxe",
            () -> new PickaxeItem(ModToolTiers.SAPPHIRE, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.SAPPHIRE, 1.0F, -2.8F))));
    //Custom shovel
    public static final DeferredHolder<Item, ShovelItem> SAPPHIRE_SHOVEL = ITEMS.register("sapphire_shovel",
            () -> new ShovelItem(ModToolTiers.SAPPHIRE, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.SAPPHIRE, 1.5F, -3.0F))));
    //Custom axe
    public static final DeferredHolder<Item, AxeItem> SAPPHIRE_AXE = ITEMS.register("sapphire_axe",
            () -> new AxeItem(ModToolTiers.SAPPHIRE, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.SAPPHIRE, 5.0F, -3.0F))));
    //Custom hoe
    public static final DeferredHolder<Item, HoeItem> SAPPHIRE_HOE = ITEMS.register("sapphire_hoe",
            () -> new HoeItem(ModToolTiers.SAPPHIRE, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.SAPPHIRE, -4.0F, 0.0F))));


    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
