package com.imagine;

import android.view.Choreographer;

public class ChoreographerHelper {
    private NesFrameCallback nesFrameCallback;
    private Choreographer choreographer;

    ChoreographerHelper() {
        nesFrameCallback = new NesFrameCallback(this);
        choreographer = Choreographer.getInstance();
    }

    public void postFrame() {
        this.choreographer.postFrameCallback(nesFrameCallback);
    }

    public void unpostFrame() {
        this.choreographer.removeFrameCallback(nesFrameCallback);
    }

    /**
     * native方法
     *
     * @param frame 帧率
     * @return boolean
     */
    public native boolean onFrame(long frame);

}
