package com.krakenrek.rigui.rotation.clock;

public class TimeClockReverse extends AbstractRotationClock{

    public TimeClockReverse(float rotationTime) {
        this.maxCounter = Math.max(Math.round(rotationTime*20*50), 1);
    }
    private int counter = 0;
    private int maxCounter;

    @Override
    public void tick() {
        counter++;
        if (counter > maxCounter) {
            counter = 0;
        }
        angles[1] = ((float) (maxCounter-counter))/maxCounter*360;
    }
}
