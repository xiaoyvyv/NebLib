package com.imagine;

import android.hardware.input.InputManager;

public class InputDeviceListener implements InputManager.InputDeviceListener {
    private final static int InputDeviceAdded = 0;
    private final static int InputDeviceChanged = 1;
    private final static int InputDeviceRemoved = 2;
    private InputDeviceListenerHelper listenerHelper;

    InputDeviceListener(InputDeviceListenerHelper inputDeviceListenerHelper) {
        this.listenerHelper = inputDeviceListenerHelper;
    }

    @Override
    public void onInputDeviceAdded(int deviceId) {
        this.listenerHelper.deviceChange(deviceId, InputDeviceAdded);
    }

    @Override
    public void onInputDeviceChanged(int deviceId) {
        this.listenerHelper.deviceChange(deviceId, InputDeviceChanged);
    }

    @Override
    public void onInputDeviceRemoved(int deviceId) {
        this.listenerHelper.deviceChange(deviceId, InputDeviceRemoved);
    }
}
