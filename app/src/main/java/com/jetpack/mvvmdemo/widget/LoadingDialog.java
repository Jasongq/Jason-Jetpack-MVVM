package com.jetpack.mvvmdemo.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.StringRes;

import com.jason.mvvm.base.dialog.BaseDialog;
import com.jason.mvvm.base.dialog.action.AnimAction;
import com.jetpack.mvvmdemo.R;


/**
 * @author : GuoQiang
 * e-mail : 849199845@qq.com
 * time   : 2020/04/23  10:04
 * desc   : 加载对话框
 * version: 1.0
 */
public class LoadingDialog {
    public static final class Builder
            extends BaseDialog.Builder<Builder> {

        private final TextView mMessageView;

        public Builder(Context context) {
            super(context);
            setContentView(R.layout.dialog_loading);
            setAnimStyle(AnimAction.TOAST);
            setBackgroundDimEnabled(false);
            setCancelable(false);

            mMessageView = findViewById(R.id.tv_wait_message);
        }

        public Builder setMessage(@StringRes int id) {
            return setMessage(getString(id));
        }
        public Builder setMessage(CharSequence text) {
            mMessageView.setText(text);
            mMessageView.setVisibility(TextUtils.isEmpty(text ) ? View.GONE : View.VISIBLE);
            return this;
        }
    }
}
