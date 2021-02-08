package com.imagine;

import android.annotation.SuppressLint;
import android.app.Activity;

class InputDialogUtils {
    @SuppressLint("StaticFieldLeak")
    private static EditDialog editDialog = null;

    static void showInputDialog(Activity activity, String str, String str2, int x, int y, int input_view_width, int input_view_height, int i5) {
        //关闭对话框
        if (editDialog != null) {
            editDialog.dismiss();
        }
        editDialog = new EditDialog(activity, str, str2);
        editDialog.setWindowSize(x, y, input_view_width, input_view_height);
        editDialog.show();
    }

    static void dismissInputDialog(boolean b) {
        if (editDialog != null) {
            editDialog.dismiss();
        }
    }

    static void placeSysTextInput(int x, int y, int input_view_width, int input_view_height) {
        if (editDialog != null) {
            editDialog.setWindowSize(x, y, input_view_width, input_view_height);
            editDialog.getInputText().requestLayout();
        }
    }

    static void destroy() {
        editDialog = null;
    }

}
