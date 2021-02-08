package com.imagine;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowInsets;

import com.blankj.utilcode.util.BarUtils;

public class NesView extends View {
    private Rect rectB;
    private Rect rectC;
    private Activity activity;
    private int loadWidth;
    private int loadHeight;
    public long index;

    public NesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public NesView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public NesView(Context context) {
        super(context);
        init(context);
    }

    public NesView(Context context, long j) {
        super(context);
        init(context);
        this.index = j;
    }

    private void init(Context context) {
        this.rectB = new Rect();
        this.rectC = new Rect();
        Activity activity = (Activity) context;
        this.activity = activity;
        activity.getWindow().getAttributes().systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN ;
    }


    @Override
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        // 当宽高不为0时
        if (!(getWidth() == 0 || getHeight() == 0)) {
            View rootView = getRootView();

            int width = rootView.getWidth();
            int height = rootView.getHeight();
            this.rectC.left = 0;

            // 判断状态栏是否可见
            boolean statusBarVisible = BarUtils.isStatusBarVisible(activity);
            if (statusBarVisible) {
                this.rectC.top = BarUtils.getStatusBarHeight();
            } else {
                this.rectC.top = 0;
            }

            boolean navBarVisible = BarUtils.isNavBarVisible(activity);
            if (navBarVisible) {
                // 横屏的宽度不绘制到导航栏
                this.rectC.right = isLand() ? getWidth() - BarUtils.getNavBarHeight() : getWidth();
                // 竖屏的高度不绘制到导航栏
                this.rectC.bottom = isLand() ? getHeight() : getHeight() - BarUtils.getNavBarHeight();
            } else {
                this.rectC.right = getWidth();
                this.rectC.bottom = getHeight();
            }

            // 窗口发生改变时
            if (!(this.rectB.equals(this.rectC) && width == this.loadWidth && height == this.loadHeight)) {
                NesGameActivity.onContentRectChanged(this.index, this.rectC.left, this.rectC.top, this.rectC.right, this.rectC.bottom, width, height);
                this.rectB.set(this.rectC);
                this.loadWidth = width;
                this.loadHeight = height;
            }
        }
        return super.onApplyWindowInsets(insets);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        // 窗口发生改变时，重新绘制
        requestApplyInsets();
    }

    private boolean isLand() {
        // 获取设置的配置信息
        Configuration mConfiguration = this.getResources().getConfiguration();
        int ori = mConfiguration.orientation;
        // 返回是否为横屏
        return ori == Configuration.ORIENTATION_LANDSCAPE;
    }
}
