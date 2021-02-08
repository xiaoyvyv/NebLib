package com.imagine;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.blankj.utilcode.util.StringUtils;

final class BluetoothFoundReceiver extends BroadcastReceiver {

    @Override
    public final void onReceive(Context context, Intent intent) {
        //发现了新的蓝牙信号
        if ("android.bluetooth.device.action.FOUND".equals(intent.getAction())) {
            BluetoothDevice newDevice = intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (newDevice != null)
                if (!StringUtils.isEmpty(newDevice.getName())) {
                    //历遍已经存在的蓝牙设备,若存在就不加入
                    for (BluetoothDevice bluetoothDevice : BluetoothClass.bluetoothDevices) {
                        if (StringUtils.equals(bluetoothDevice.getAddress(), newDevice.getAddress())) {
                            return;
                        }
                    }
                    //添加新设备到集合
                    BluetoothClass.bluetoothDevices.add(newDevice);
                    if (NesGameActivity.onScanDeviceClass(newDevice.getBluetoothClass().getDeviceClass())) {
                        NesGameActivity.onScanDeviceName(newDevice.getName(), newDevice.getAddress());
                    }
                }
        }
    }
}
