package com.imagine;

import android.view.KeyEvent;


final class FakerThread extends Thread {
    private UserActivityFaker userActivityFaker;

    FakerThread(UserActivityFaker userActivityFaker) {
        this.userActivityFaker = userActivityFaker;
    }

    @Override
    public void run() {
        while (true) {
            try {
                userActivityFaker.getInstrumentation().sendKeyDownUpSync(KeyEvent.ACTION_DOWN);
                sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
