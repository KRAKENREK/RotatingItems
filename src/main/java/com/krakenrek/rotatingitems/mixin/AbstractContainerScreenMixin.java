package com.krakenrek.rotatingitems.mixin;

import com.krakenrek.rotatingitems.Config;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerScreen.class)
public class AbstractContainerScreenMixin extends Screen {

    protected AbstractContainerScreenMixin(Component p_96550_) {
        super(p_96550_);
    }
    @Inject(method = "renderSlotHighlight*", at = @At("HEAD"), cancellable = true)
    private static void disableHighlight(PoseStack p_169607_, int p_169608_, int p_169609_, int p_169610_, int slotColor, CallbackInfo ci) {
        if (Config.CLIENT.disableHighlightInHovered.get() && (Config.CLIENT.rotationType.get().equals(Config.RotationType.HOVERED))) {
            ci.cancel();
        }
    }
}
