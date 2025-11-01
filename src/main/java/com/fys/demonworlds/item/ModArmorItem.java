//package com.fys.demonworlds.item;
//
//import net.minecraft.core.Holder;
//import net.minecraft.world.effect.MobEffectInstance;
//import net.minecraft.world.effect.MobEffects;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ArmorItem;
//import net.minecraft.world.item.ArmorMaterial;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.Level;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author fys
// * @date 2025/10/25
// * @description
// */
//public class ModArmorItem extends Item {
//
//    private Map<Holder<ArmorMaterial>, List<MobEffectInstance>> EFFECT_MAP = new HashMap<>(){{
//        put(ModArmorMaterials.END, List.of(
//                new MobEffectInstance(MobEffects.GLOWING, 300, 1, true, false, true)
//        ));
//        }
//    };
//
//    public ModArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
//        super(material, type, properties);
//    }
//
//    @Override
//    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
//        super.inventoryTick(stack, level, entity, slotId, isSelected);
//        //服务端，并且玩家穿戴了整套盔甲
//        if(entity instanceof Player player && !level.isClientSide && wearAllArmorItem(player)){
//            for(Holder<ArmorMaterial> key : EFFECT_MAP.keySet()){
//                if(isCorrectArmor(key, player)){
//                    EFFECT_MAP.get(key).forEach(e->{
//                        if(!player.hasEffect(e.getEffect())){
//                            //每次新加一个实例
//                            player.addEffect(new  MobEffectInstance(e.getEffect(), e.getDuration(), e.getAmplifier(), e.isAmbient(), e.isVisible(), e.showIcon()));
//                        }
//                    });
////                    player.addEffect(new MobEffectInstance(ke));
//                }
//            }
//        }
//    }
//
//    private boolean isCorrectArmor(Holder<ArmorMaterial> key, Player player) {
//        ArmorItem boots = (ArmorItem) player.getInventory().getArmor(0).getItem();
//        ArmorItem leggins = (ArmorItem) player.getInventory().getArmor(1).getItem();
//        ArmorItem chestplate = (ArmorItem) player.getInventory().getArmor(2).getItem();
//        ArmorItem helmet = (ArmorItem) player.getInventory().getArmor(3).getItem();
//        return boots.getMaterial().equals(key) && leggins.getMaterial().equals(key) && chestplate.getMaterial().equals(key) && helmet.getMaterial().equals(key);
//    }
//
//    /**
//     * 玩家是否穿戴了全部盔甲，并且都是ArmorItem的子类
//     * @param player
//     * @return
//     */
//    private boolean wearAllArmorItem(Player player) {
//        ItemStack boots = player.getInventory().getArmor(0);
//        ItemStack leggins = player.getInventory().getArmor(1);
//        ItemStack chestplate = player.getInventory().getArmor(2);
//        ItemStack helmet = player.getInventory().getArmor(3);
//        return !boots.isEmpty() && !leggins.isEmpty() && !chestplate.isEmpty() && !helmet.isEmpty()
//                && boots.getItem() instanceof ArmorItem
//                && leggins.getItem() instanceof ArmorItem
//                && chestplate.getItem() instanceof ArmorItem
//                && helmet.getItem() instanceof ArmorItem
//                ;
//    }
//
//
//}
