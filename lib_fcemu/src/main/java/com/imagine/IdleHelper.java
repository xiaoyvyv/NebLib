package com.imagine;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;

public class IdleHelper implements MessageQueue.IdleHandler {
    private MessageQueue messageQueue;
    private Handler handler;

    IdleHelper() {
        this.messageQueue = Looper.myQueue();
        this.handler = new Handler();
    }

    private native boolean onFrame();

    public void postFrame() {
        this.messageQueue.addIdleHandler(this);
        this.handler.sendMessageAtFrontOfQueue(Message.obtain());
    }

    public boolean queueIdle() {
        if (!onFrame()) {
            return false;
        }
        this.handler.sendMessageAtFrontOfQueue(Message.obtain());
        return true;
    }

    public void unpostFrame() {
        this.messageQueue.removeIdleHandler(this);
    }
}