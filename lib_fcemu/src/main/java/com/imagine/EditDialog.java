package com.imagine;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.StringUtils;
import com.yangtzeu.official.nes.R;

class EditDialog extends Dialog {
    private String native_str;
    private String tips;
    private EditText inputText;

    EditDialog(Context context, String native_str, String tips) {
        super(context);
        this.native_str = native_str;
        this.tips = tips;
        init();
    }

    private void init() {
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(R.layout.view_nes_input_dialog);

        inputText = findViewById(R.id.input_text);
        TextView dialog_title =findViewById(R.id.dialog_title);
        TextView clear =findViewById(R.id.clear);
        TextView done = findViewById(R.id.done);

        dialog_title.setText(tips);
        inputText.setText(native_str);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputStr = String.valueOf(inputText.getText());
                if (StringUtils.isEmpty(inputStr)) {
                    inputText.setError("输入为空");
                    inputText.requestFocus();
                    return;
                }
                //关闭输入法
                KeyboardUtils.hideSoftInput(inputText);
                //关闭输入框
                EditDialog.this.dismiss();
                NesGameActivity.endSysTextInput(inputStr, true);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭输入法
                KeyboardUtils.hideSoftInput(inputText);
                //销毁输入框
                InputDialogUtils.destroy();
                //关闭输入框
                EditDialog.this.dismiss();
                NesGameActivity.endSysTextInput(null, false);
            }
        });
    }

    void setWindowSize(int x, int y, int input_view_width, int input_view_height) {
       /* Window window = getWindow();
        if (window == null) return;

        LayoutParams attributes = window.getAttributes();

        // lp.x与lp.y表示相对于原始位置的偏移.
        //attributes.x = x;// 新位置X坐标
        //attributes.y = y;// 新位置Y坐标
        window.setAttributes(attributes);

        ViewGroup.LayoutParams layoutParams =rootView.getLayoutParams();
        layoutParams.width =input_view_width;
        //layoutParams.height = input_view_height;*/
    }

    EditText getInputText() {
        return inputText;
    }
}
