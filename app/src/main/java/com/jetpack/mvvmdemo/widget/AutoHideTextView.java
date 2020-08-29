package com.jetpack.mvvmdemo.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author : GuoQiang
 * e-mail : 849199845@qq.com
 * time   : 2019/10/11  16:16
 * desc   : 根据有无文字智能显示的 TextView
 * version: 1.0
 */
public class AutoHideTextView extends AppCompatTextView implements TextWatcher {

    public AutoHideTextView(Context context) {
        super(context);
        initialize();
    }

    public AutoHideTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public AutoHideTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        addTextChangedListener(this);
        // 触发一次监听
        afterTextChanged(null);
    }

    /**
     * {@link TextWatcher}
     */

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        // 判断当前有没有设置文本达到自动隐藏和显示的效果
        if ("".equals(getText().toString())) {
            if (getVisibility() != GONE) {
                setVisibility(GONE);
            }
        } else {
            if (getVisibility() != VISIBLE) {
                setVisibility(VISIBLE);
            }
        }
    }
}
