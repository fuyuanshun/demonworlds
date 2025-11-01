package com.fys.demonworlds.item;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.item.custom.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

/**
 * @author fys
 * @since 2025-10-22
 */
public class ModItems {

    //延迟注册器
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ModConstants.MOD_ID);

    //恶魔果实
    public static final DeferredItem<Item> DEMON_FRUIT_SUN = ITEMS.register("demon_fruit_sun",
            () -> new DemonFruitSun(new Item.Properties()));

    public static final DeferredItem<Item> DEMON_FRUIT_MOON = ITEMS.register("demon_fruit_moon",
            () -> new DemonFruitMoon(new Item.Properties()));

    public static final DeferredItem<Item> DEMON_FRUIT_LIGHTNING = ITEMS.register("demon_fruit_lightning",
            () -> new DemonFruitLightning(new Item.Properties()));

    //自定义工具
    public static final DeferredItem<AxeItem> END_AXE = ITEMS.register("end_axe", () -> new AxeItem(ModTiers.END, new Item.Properties().attributes(AxeItem.createAttributes(ModTiers.END, 8, -2.5F))));
    public static final DeferredItem<Item> END_PICKAXE = ITEMS.register("end_pickaxe", () -> new Item(ModTiers.END, new Item.Properties().attributes(PickaxeItem.createAttributes(ModTiers.END, 1.1F, -2.5F))));
    public static final DeferredItem<HoeItem> END_HOE = ITEMS.register("end_hoe", () -> new HoeItem(ModTiers.END, new Item.Properties().attributes(HoeItem.createAttributes(ModTiers.END, -3F, -2.5F))));
    public static final DeferredItem<ShovelItem> END_SHOVEL = ITEMS.register("end_shovel", () -> new ShovelItem(ModTiers.END, new Item.Properties().attributes(ShovelItem.createAttributes(ModTiers.END, 1.8F, -2.8F))));


    //自定义武器
    public static final DeferredItem<Item> END_SWORD = ITEMS.register("end_sword", () -> new Item(new Item.Properties().sword(ModToolMaterial.END, 5, -1.0F)));
    public static final DeferredItem<AxeItem> BAT = ITEMS.register("bat", () -> new AxeItem(ModTiers.END, 3, -1.0F));
    public static final DeferredItem<BowItem> DIAMOND_BOW = ITEMS.register("diamond_bow", () -> new BowItem(new Item.Properties().durability(888)));

    //自定义盔甲
    public static final DeferredItem<Item> END_HELMET = ITEMS.register("end_helmet", () -> new Item(new Item.Properties().humanoidArmor(ModArmorMaterials.END, ArmorType.HELMET).rarity(Rarity.UNCOMMON).fireResistant()));
    public static final DeferredItem<Item> END_CHESTPLATE = ITEMS.register("end_chestplate", () -> new Item(new Item.Properties().humanoidArmor(ModArmorMaterials.END, ArmorType.CHESTPLATE).rarity(Rarity.UNCOMMON).fireResistant()));
    public static final DeferredItem<Item> END_LEGGINS = ITEMS.register("end_leggings", () -> new Item(new Item.Properties().humanoidArmor(ModArmorMaterials.END, ArmorType.LEGGINGS).rarity(Rarity.UNCOMMON).fireResistant()));
    public static final DeferredItem<Item> END_BOOTS = ITEMS.register("end_boots", () -> new Item(new Item.Properties().humanoidArmor(ModArmorMaterials.END, ArmorType.BOOTS).rarity(Rarity.UNCOMMON).fireResistant()));

    //物品
    public static final DeferredItem<Item> MF = ITEMS.register("mf",
            () -> new ModItemMF(new Item.Properties()){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                    tooltipComponents.add(Component.translatable("item.demonworlds.mf.desc"));
                }
            });

    //黑暗之尘
    public static final DeferredItem<Item> DARK_DUST = ITEMS.register("dark_dust", () -> new DarkDustItem(new Item.Properties().food(
            new FoodProperties.Builder()
                    .alwaysEdible()
                    .build()
    ), 200));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
