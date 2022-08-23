package com.krakenrek.rotatingitems.rotation.controller;

import com.krakenrek.rotatingitems.Config;
import com.krakenrek.rotatingitems.rotation.clock.AbstractRotationClock;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;

public class HoveredRotation extends AbstractRotationController {
    private static final int tickTime = 20*100;

    private Slot hovered = null;
    private ItemStack hand = null;

    public HoveredRotation(AbstractRotationClock clock) {
        super(clock);
    }

    @Override
    public void onClientTick(TickEvent event) {
        if (event.phase.equals(TickEvent.Phase.START) && event.type.equals(TickEvent.Type.CLIENT)) {
            LocalPlayer player = Minecraft.getInstance().player;
            Screen screen = Minecraft.getInstance().screen;
            if (player == null) { return; }
            if (screen instanceof AbstractContainerScreen) {
                if (hand !=null) {
                    reset();
                    hand = null;
                }
                Slot slot = ((AbstractContainerScreen<?>) screen).getSlotUnderMouse();
                if (slot == null) {
                    reset();
                    hovered = null;
                }
                if (hovered == slot) {
                    tick();
                } else {
                    reset();
                    hovered = slot;
                }
            } else {
                if (hovered != null) {
                    reset();hovered = null;
                }
                ItemStack mainHand = player.getMainHandItem();
                if (hand == mainHand) {
                    tick();
                } else {
                    reset();
                    hand = mainHand;
                }
            }
            updateRotation();
        }
    }

    @Override
    public void rotate(ItemStack p_115144_, ItemTransforms.TransformType p_115145_, boolean p_115146_, PoseStack p_115147_, MultiBufferSource p_115148_, int p_115149_, int p_115150_, BakedModel p_115151_) {
        if (p_115145_.equals(ItemTransforms.TransformType.GUI)) {
            if (hovered != null) {
                if (hovered.getItem().equals( p_115144_)){
                    float scale = Config.CLIENT.scaleInHovered.get().floatValue();
                    p_115147_.scale(scale, scale, scale);
                    p_115147_.mulPose(getCurrentRotation());
                }
            }
            if (hand != null) {
                if (hand.equals(p_115144_)) {
                    p_115147_.mulPose(getCurrentRotation());
                }
            }
        }
    }
}
