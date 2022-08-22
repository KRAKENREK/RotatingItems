package com.krakenrek.rigui.rotation.controller;

import com.krakenrek.rigui.rotation.clock.AbstractRotationClock;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;

public class GlobalRotation extends AbstractRotationController {
    public GlobalRotation(AbstractRotationClock clock) {
        super(clock);
    }

    @Override
    public void onClientTick(TickEvent event) {
        if (event.phase.equals(TickEvent.Phase.START) && event.type.equals(TickEvent.Type.CLIENT)) {
            tick();
            updateRotation();
        }
    }

    @Override
    public void rotate(ItemStack p_115144_, ItemTransforms.TransformType p_115145_, boolean p_115146_, PoseStack p_115147_, MultiBufferSource p_115148_, int p_115149_, int p_115150_, BakedModel p_115151_) {
        if (p_115145_.equals(ItemTransforms.TransformType.GUI)) {
            p_115147_.mulPose(getCurrentRotation());
        }
    }
}
