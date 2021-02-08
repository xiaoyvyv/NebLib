package com.imagine;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * 字体渲染类
 */
public class FontRenderer {
    private Canvas canvas = new Canvas();
    private Paint paint;
    private Bitmap bitmap;
    private char word;
    private int width;
    private int height;
    private int top;
    private int left;
    private int bottom;
    private int temp_width;

    final boolean activeChar(int i) {
        if (this.word != i) {
            char[] chars = new char[2];
            chars[0] = (char) i;
            Rect rect = new Rect();
            this.paint.getTextBounds(chars, 0, 1, rect);
            int width = rect.right - rect.left;
            int height = rect.bottom - rect.top;
            if (width == 0 || height == 0) {
                return false;
            }
            float[] floats = new float[2];
            this.paint.getTextWidths(chars, 0, 1, floats);
            this.temp_width = (int) floats[0];
            this.width = width;
            this.height = height;

            this.left = rect.left;
            this.top = -rect.top;
            this.bottom = rect.bottom;
            this.word = (char) i;
        }
        return true;
    }

    public void applySize(Paint paint) {
        if (this.paint != paint) {
            this.paint = paint;
        }
    }

    public Bitmap charBitmap() {
        this.bitmap = Bitmap.createBitmap(this.width, this.height, Config.ALPHA_8);
        this.bitmap.eraseColor(0);
        this.canvas.setBitmap(this.bitmap);
        char[] chars = new char[2];
        chars[0] = this.word;
        this.canvas.drawText(chars, 0, 1, (float) (-this.left), (float) (this.height - this.bottom), this.paint);
        return this.bitmap;
    }

    public int currentCharXAdvance() {
        return this.temp_width;
    }

    public int currentCharXOffset() {
        return this.left;
    }

    public int currentCharXSize() {
        return this.width;
    }

    public int currentCharYOffset() {
        return this.top;
    }

    public int currentCharYSize() {
        return this.height;
    }

    public void finalize() {
        if (this.bitmap != null) {
            this.bitmap.recycle();
        }
    }

    public void freeSize(Paint paint) {
        if (this.paint == paint) {
            this.paint = null;
        }
    }

    public Paint newSize(int i) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize((float) i);
        return paint;
    }

    public void unlockCharBitmap(Bitmap bitmap) {
        bitmap.recycle();
    }
}
