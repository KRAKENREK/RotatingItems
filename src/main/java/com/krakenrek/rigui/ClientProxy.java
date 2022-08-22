package com.krakenrek.rigui;

import com.krakenrek.rigui.rotation.clock.AbstractRotationClock;
import com.krakenrek.rigui.rotation.clock.TickClock;
import com.krakenrek.rigui.rotation.clock.TimeClock;
import com.krakenrek.rigui.rotation.clock.TimeClockReverse;
import com.krakenrek.rigui.rotation.controller.AbstractRotationController;
import com.krakenrek.rigui.rotation.controller.GlobalRotation;
import com.krakenrek.rigui.rotation.controller.HoveredRotation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.DistExecutor;

public class ClientProxy {

    public static final ClientProxy INSTANCE = new ClientProxy();

    public AbstractRotationController controller ;

    public static DistExecutor.SafeRunnable init() {
        return () -> MinecraftForge.EVENT_BUS.addListener(INSTANCE::onTick);
    }

    public AbstractRotationController getOrCreateController() {
        if (controller == null) {
            AbstractRotationClock clock = null;
            switch (Config.CLIENT.clockType.get()) {
                case TIME -> clock = new TimeClock(Config.CLIENT.rotationTime.get().floatValue());
                case TIME_REVERSE -> clock = new TimeClockReverse(Config.CLIENT.rotationTime.get().floatValue());
                case TICK -> clock = new TickClock(Config.CLIENT.anglePerTick.get().floatValue());
            }
            switch (Config.CLIENT.rotationType.get()) {
                case GLOBAL -> controller = new GlobalRotation(clock);
                case HOVERED -> controller = new HoveredRotation(clock);
            }
        }
        return controller;
    }

    public void onTick(TickEvent event) {
        getOrCreateController().onClientTick(event);
    }
}
