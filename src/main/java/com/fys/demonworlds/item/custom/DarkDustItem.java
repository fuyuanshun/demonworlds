package com.fys.demonworlds.item.custom;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.effect.ModMobEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Set;

/**
 * 可燃物 使用getBurnTime设置燃烧时间即可
 * 可用来清除恶魔果实的诅咒buff
 * @author fys
 * @date 2025/10/25
 * @description
 */
public class DarkDustItem extends Item {
//    private int fuelTime;
//
//
    public DarkDustItem(Properties properties, int fuelTime) {
        super(properties);
//        this.fuelTime = fuelTime;
    }
//
//    @Override
//    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
//        return this.fuelTime;
//    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if(livingEntity instanceof Player player) {
            CompoundTag compound = player.getPersistentData().getCompound(Player.PERSISTED_NBT_TAG);
            CompoundTag demonFruits = compound.getCompound(ModConstants.NBT_DEMON_FRUIT);
            int size = demonFruits.size();
            if(size > 0){
                AttributeInstance attribute = player.getAttribute(Attributes.MAX_HEALTH);
                if(attribute != null){
                    double baseValue = attribute.getBaseValue();
                    //吃过的恶魔果实越多，恢复的血量越多
                    attribute.setBaseValue(baseValue + (size * ModConstants.DEMON_FRUIT_SUB_HEALTH));
                    Set<String> allKeys = demonFruits.getAllKeys();
                    for(String key : allKeys){
                        demonFruits.remove(key);
//                        String name = key.split("\\.")[2];
//                        ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name);
//                        Item item = BuiltInRegistries.ITEM.get(resourceLocation);
//                        FoodProperties foodProperties = item.getDefaultInstance().get(DataComponents.FOOD);
//                        List<FoodProperties.PossibleEffect> effects = foodProperties.effects();
//                        for(FoodProperties.PossibleEffect effect : effects){
//                            if(!effect.effect().equals(ModMobEffects.CURSE)){
//                                player.removeEffect(effect.effect().getEffect());
//                            }
//                        }
//                        player.addItem(item.getDefaultInstance());
                        break;
                    }

                    if(demonFruits.isEmpty()){
                        player.removeEffect(ModMobEffects.CURSE);
                    }
                    compound.put(ModConstants.NBT_DEMON_FRUIT, demonFruits);
                    player.getPersistentData().put(Player.PERSISTED_NBT_TAG, compound);
                }
            } else {
                player.removeEffect(ModMobEffects.CURSE);
            }

        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
