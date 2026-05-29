package com.fys.demonworlds.entity.client;

import com.fys.demonworlds.attachment.ElytraSlotHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ElytraModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * 自定义鞘翅渲染层 —— 当玩家在独立鞘翅栏中装备了鞘翅时，
 * 在玩家模型上渲染鞘翅（与胸甲栏中的鞘翅渲染效果一致）。
 *
 * @author fys
 * @since 2026-05-28
 */
public class ElytraSlotLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    private static final ResourceLocation ELYTRA_TEXTURE =
            ResourceLocation.withDefaultNamespace("textures/entity/elytra.png");

    private final ElytraModel<AbstractClientPlayer> elytraModel;

    public ElytraSlotLayer(
            RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer,
            EntityModelSet modelSet) {
        super(renderer);
        this.elytraModel = new ElytraModel<>(modelSet.bakeLayer(ModelLayers.ELYTRA));
    }

    @Override
    public void render(
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight,
            AbstractClientPlayer player,
            float limbSwing,
            float limbSwingAmount,
            float partialTick,
            float ageInTicks,
            float netHeadYaw,
            float headPitch) {

        // 检查自定义鞘翅栏
        ItemStack elytraStack = ElytraSlotHelper.get(player);
        if (!elytraStack.is(Items.ELYTRA)) return;

        // 检查披风设置（Minecraft 用披风开关控制鞘翅渲染）
        if (!player.isModelPartShown(PlayerModelPart.CAPE)) return;

        poseStack.pushPose();
        poseStack.translate(0.0F, 0.0F, 0.125F);

        // 复制玩家身体部分的变换
        this.getParentModel().copyPropertiesTo(this.elytraModel);
        this.elytraModel.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(
                buffer,
                RenderType.armorCutoutNoCull(ELYTRA_TEXTURE),
                elytraStack.hasFoil()
        );

        this.elytraModel.renderToBuffer(
                poseStack, vertexConsumer, packedLight,
                OverlayTexture.NO_OVERLAY
        );

        poseStack.popPose();
    }
}
