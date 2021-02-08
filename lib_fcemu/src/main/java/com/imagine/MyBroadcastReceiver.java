package com.imagine;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class MyBroadcastReceiver extends BroadcastReceiver {
    MyBroadcastReceiver() {
    }

    @Override
    public final void onReceive(Context context, Intent intent) {
        context.unregisterReceiver(BluetoothClass.broadcastReceiver);
        context.unregisterReceiver(BluetoothClass.foundReceiver);
        BluetoothClass.bluetoothDevices.clear();
        NesGameActivity.onBTScanStatus(1);
    }
}
