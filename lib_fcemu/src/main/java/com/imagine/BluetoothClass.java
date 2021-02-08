package com.imagine;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.ParcelUuid;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class BluetoothClass {
    static List<BluetoothDevice> bluetoothDevices = new ArrayList<>();
    private static final Constructor constructor =getMethod(BluetoothSocket.class, new Class[]{Integer.TYPE, Integer.TYPE, Boolean.TYPE, Boolean.TYPE, BluetoothDevice.class, Integer.TYPE, ParcelUuid.class});
    private static final Method method = getMethod(BluetoothDevice.class, "createInsecureRfcommSocket", new Class[]{Integer.TYPE});
    static BroadcastReceiver broadcastReceiver = new MyBroadcastReceiver();
    static BroadcastReceiver foundReceiver = new BluetoothFoundReceiver();

    static BluetoothAdapter getInstance() {
        return BluetoothAdapter.getDefaultAdapter();
    }

    static BluetoothSocket getInstance(BluetoothAdapter bluetoothAdapter, String str, int i, boolean z) {
        BluetoothDevice remoteDevice = bluetoothAdapter.getRemoteDevice(str);
        if (remoteDevice == null) {
            return null;
        }
        BluetoothSocket bluetoothSocket = null;
        if (z) {
            try {
                if (constructor == null) {
                    return null;
                }
                bluetoothSocket = (BluetoothSocket) constructor.newInstance(new Object[]{3, -1, Boolean.FALSE, Boolean.FALSE, remoteDevice, i, null});
            } catch (IllegalAccessException e) {
                bluetoothSocket = null;
            } catch (InvocationTargetException e2) {
                bluetoothSocket = null;
            } catch (InstantiationException e3) {
                return null;
            }
        } else if (method == null) {
            return null;
        } else {
            try {
                bluetoothSocket = (BluetoothSocket) method.invoke(remoteDevice, new Object[]{i});
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                e1.printStackTrace();
            }
        }
        try {
            if (bluetoothSocket != null) {
                bluetoothSocket.connect();
            }
        } catch (IOException e9) {
            try {
                bluetoothSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            bluetoothSocket = null;
        }
        return bluetoothSocket;
    }

    static boolean getInstance(Activity activity, BluetoothAdapter bluetoothAdapter) {
        if (bluetoothDevices == null) {
            bluetoothDevices = new ArrayList<>();
        }
        bluetoothDevices.clear();
        if (bluetoothAdapter.isDiscovering()) {
            bluetoothAdapter.cancelDiscovery();
        }
        activity.registerReceiver(broadcastReceiver, new IntentFilter("android.bluetooth.adapter.action.DISCOVERY_FINISHED"));
        activity.registerReceiver(foundReceiver, new IntentFilter("android.bluetooth.device.action.FOUND"));
        return bluetoothAdapter.startDiscovery();
    }

    static void b(Activity activity, BluetoothAdapter bluetoothAdapter) {
        activity.unregisterReceiver(broadcastReceiver);
        activity.unregisterReceiver(foundReceiver);
        bluetoothAdapter.cancelDiscovery();
        bluetoothDevices.clear();
    }

    private static Method getMethod(Class cls, String str, Class[] clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (Exception e) {
            return null;
        }
    }

    private static Constructor getMethod(Class cls, Class[] clsArr) {
        try {
            Constructor declaredConstructor = cls.getDeclaredConstructor(clsArr);
            if (declaredConstructor.isAccessible()) {
                return declaredConstructor;
            }
            declaredConstructor.setAccessible(true);
            return declaredConstructor;
        } catch (Exception e) {
            return null;
        }
    }
}
