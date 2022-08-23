package com.krakenrek.rotatingitems.rotation.clock;

public class TickClock extends AbstractRotationClock{

    public TickClock(float anglePerTick) {
        this.anglePerTick = anglePerTick;
    }
    public float anglePerTick;

    @Override
    public void tick() {
        float result = angles[1] + anglePerTick;
        if (result > 360) {
            result = result - 360;
        } else if (result < 0) {
            result = result + 360;
        }
        angles[1] = result;
    }
}
