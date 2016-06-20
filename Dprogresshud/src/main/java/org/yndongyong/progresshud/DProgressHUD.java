package org.yndongyong.progresshud;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Dong on 2016/6/20.
 */
public class DProgressHUD extends Dialog {

    public DProgressHUD(Context context) {
        super(context);
    }

    public DProgressHUD(Context context, int themeResId) {
        super(context, themeResId);
    }

    public enum Style {
        SPIN_INDETERMINATE, PIE_DETERNIMATER
    }

    private View rootView;
    private TextView mMessageView; //提示文本
    private FrameLayout mContainer;
    static Style mStyle = Style.SPIN_INDETERMINATE;


    public static Dialog show(Context context, Style style) {
        return show(context, style, null);
    }

    public static Dialog show(Context context, Style style, CharSequence label) {
        return show(context, style, label, false);
    }

    public static Dialog show(Context context, Style style, CharSequence label, boolean
            cancelable) {
        return show(context, style, label, cancelable, null);
    }

    public static Dialog show(Context context, Style style, CharSequence label, boolean
            cancelable, OnCancelListener cancelListener) {
        DProgressHUD dialog = new DProgressHUD(context, R.style.loading_dialog);
        dialog.show();
        dialog.setLabel(label);
        mStyle = style;
        dialog.setCustomView(style);
        dialog.setCancelable(cancelable);
        dialog.setOnCancelListener(cancelListener);
        return dialog;
    }

    @Override
    public void show() {
        Log.d("TAG", "show() ");
        super.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAG", "onCreate() Bundle");
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dyprogresshud_layout, null);

        rootView = view.findViewById(R.id.dy_progresshud_view);
        mContainer = (FrameLayout) view.findViewById(R.id.container);
        mMessageView = (TextView) view.findViewById(R.id.tipTextView);


        setCustomView(mStyle);

        setContentView(view, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局

        super.onCreate(savedInstanceState);


    }

    protected void setLabel(CharSequence message) {
        if (mMessageView != null) {
            if (!TextUtils.isEmpty(message)) {
                mMessageView.setVisibility(View.VISIBLE);
                mMessageView.setText(message);
            }

        }

    }

    protected void setCustomView(Style style) {
        View view = null;
        switch (style) {
            case PIE_DETERNIMATER:
                view = new SpinView(getContext());
                break;
            case SPIN_INDETERMINATE:
            default:
                view = new TextView(getContext());
                ((TextView) view).setText("内容布局...");
                break;

        }
        if (view != null) {
            // TODO: 2016/6/20  
           /* if (view instanceof Determinate) {
                mDeterminateView = (Determinate) view;
            }
            if (view instanceof Indeterminate) {
                 = (Indeterminate) view;
            }*/
            if (isShowing()) {
                mContainer.removeAllViews();
            }
            addViewToFrame(view);

        }

    }

    protected void addViewToFrame(View view) {
        if (view == null) {
            throw new RuntimeException("view can not be null!");
        }
        mContainer.addView(view);


    }
}
