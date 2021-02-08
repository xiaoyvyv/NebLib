package com.imagine;

import android.view.Choreographer.FrameCallback;

/**
 * 帧率回调
 */
final class NesFrameCallback implements FrameCallback {
    private ChoreographerHelper choreographerHelper;

    NesFrameCallback(ChoreographerHelper choreographerHelper) {
        this.choreographerHelper = choreographerHelper;
    }

    @Override
    public void doFrame(long frame) {
        choreographerHelper.onFrame(frame);
    }
}
