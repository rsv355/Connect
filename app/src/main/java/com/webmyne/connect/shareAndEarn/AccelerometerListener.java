package com.webmyne.connect.shareAndEarn;

/**
 * Created by priyasindkar on 18-02-2016.
 */
public interface AccelerometerListener {
    public void onAccelerationChanged(float x, float y, float z);
    public void onShake(float force);
}