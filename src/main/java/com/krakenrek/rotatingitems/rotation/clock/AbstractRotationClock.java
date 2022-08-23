package com.krakenrek.rotatingitems.rotation.clock;

public abstract class AbstractRotationClock {
    protected float[] angles = new float[3];

    public abstract void tick();

    public void reset() {
        angles[0] = 0f;
        angles[1] = 0f;
        angles[2] = 0f;
    }

    public float[] getAngles() {
        return angles;
    }
}
