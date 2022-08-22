package com.krakenrek.rigui.rotation.controller;

import com.krakenrek.rigui.rotation.clock.AbstractRotationClock;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;

public abstract class AbstractRotationController {
    private Quaternion currentRotation;

    private AbstractRotationClock clock;
    public AbstractRotationController(AbstractRotationClock clock) {
        this.clock = clock;
    }

    protected Quaternion getCurrentRotation() {
        return this.currentRotation;
    }
    protected void updateRotation() {
        float[] angles = clock.getAngles();
        this.currentRotation = Quaternion.fromXYZ(angles[0], angles[1], angles[2]);
    }

    protected void tick() {
        clock.tick();
    }

    protected void reset() {
        clock.reset();
    }

    public abstract void onClientTick(TickEvent event);

    public abstract void rotate(ItemStack p_115144_, ItemTransforms.TransformType p_115145_, boolean p_115146_, PoseStack p_115147_, MultiBufferSource p_115148_, int p_115149_, int p_115150_, BakedModel p_115151_);
}
