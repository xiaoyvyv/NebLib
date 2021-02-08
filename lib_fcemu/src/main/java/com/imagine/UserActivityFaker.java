package com.imagine;

import android.app.Instrumentation;

import com.blankj.utilcode.util.ScreenUtils;


public class UserActivityFaker {
    private final NesGameActivity activity;
    private FakerThread fakerThread;
    private Instrumentation instrumentation;

    UserActivityFaker(NesGameActivity baseActivity) {
        this.activity = baseActivity;
        instrumentation = new Instrumentation();
    }

    /**
     * 游戏运行调用此方法
     */
    public void start() {
        if (fakerThread == null) {
            fakerThread = new FakerThread(this);
            fakerThread.setPriority(Thread.MIN_PRIORITY);
            fakerThread.start();
        }
        //强制横屏
        ScreenUtils.setLandscape(activity);
    }

    /**
     * 游戏停止调用此方法
     */
    public void stop() {
        if (fakerThread != null) {
            fakerThread.interrupt();
            fakerThread = null;
        }
    }

    Instrumentation getInstrumentation() {
        return instrumentation;
    }
}
