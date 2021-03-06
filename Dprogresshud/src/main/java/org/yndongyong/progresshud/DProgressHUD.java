package org.yndongyong.progresshud;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static org.yndongyong.progresshud.DProgressHUD.Style.ALERT_ACTION_DONE;

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
        SPIN_INDETERMINATE, PIE_DETERNIMATER, ALERT_ACTION_DONE,
        ALERT_ACTION_ERROR, ALERT_ACTION_INFO, ALERT_ACTION_WARN
    }

    private View rootView;
    private TextView mMessageView; //提示文本
    private FrameLayout mContainer;
    static Style mStyle = Style.SPIN_INDETERMINATE;

    private Determinate mDeterminateView;//进度固定的动画的view

    public static DProgressHUD show(Context context, Style style) {
        return show(context, style, null);
    }

    public static DProgressHUD show(Context context, Style style, CharSequence label) {
        return show(context, style, label, true);
    }

    public static DProgressHUD show(Context context, Style style, CharSequence label, boolean
            cancelable) {
        return show(context, style, label, cancelable, false);
    }

    /**
     *
     * @param context
     * @param style
     * @param label
     * @param cancelable
     * @param backgroundDimEnabled true背景变暗，false：背景不变暗
     * @return
     */
    public static DProgressHUD show(Context context, Style style, CharSequence label, boolean
            cancelable, boolean backgroundDimEnabled) {
        return show(context, style, label, cancelable, backgroundDimEnabled, null);
    }

    public static DProgressHUD show(Context context, Style style, CharSequence label, boolean
            cancelable, boolean backgroundDimEnabled, OnCancelListener cancelListener) {
        DProgressHUD dialog = new DProgressHUD(context, R.style.loading_dialog);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        if (backgroundDimEnabled) {
            params.dimAmount = 0.5f;
        } else {
            params.dimAmount = 0.0f;
        }
        window.setAttributes(params);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
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
        super.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dyprogresshud_layout, null);

        rootView = view.findViewById(R.id.dy_progresshud_view);
        mContainer = (FrameLayout) view.findViewById(R.id.container);
        mMessageView = (TextView) view.findViewById(R.id.tipTextView);

        setCustomView(mStyle);


//        int wrapParam = ViewGroup.LayoutParams.WRAP_CONTENT;
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(wrapParam, wrapParam);
//        setContentView(view, params);
//        setContentView(view);
        setContentView(view, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        super.onCreate(savedInstanceState);
    }

    public void setLabel(CharSequence message) {
        if (mMessageView != null && !TextUtils.isEmpty(message)) {
            mMessageView.setVisibility(View.VISIBLE);
            mMessageView.setText(message);
        }
    }

    public void setStyle(Style style) {
        setCustomView(style);
    }

    private void setCustomView(Style style) {
        View view = null;
        switch (style) {
            case SPIN_INDETERMINATE:
                view = new SpinView(getContext());
                break;

            case ALERT_ACTION_DONE:
                view = new ImageView(getContext());
//                ((ImageView) view).setImageResource(R.drawable.ic_done_white_48dp);
                ((ImageView) view).setImageResource(R.drawable.clean_success);
//                scheduleDissmiss();
                break;

            case ALERT_ACTION_ERROR:
                view = new ImageView(getContext());
//                ((ImageView) view).setImageResource(R.drawable.ic_clear_white_48dp);
                ((ImageView) view).setImageResource(R.drawable.ic_failed);
//                scheduleDissmiss();
                break;

            case ALERT_ACTION_INFO:
                view = new ImageView(getContext());
                ((ImageView) view).setImageResource(R.drawable.ic_info_outline_white_48dp);
//                scheduleDissmiss();
                break;
            case ALERT_ACTION_WARN:
                view = new ImageView(getContext());
                ((ImageView) view).setImageResource(R.drawable.ic_priority_high_white_48dp);
//                scheduleDissmiss();
                break;

            //固定的有确定结束状态的
            case PIE_DETERNIMATER:
                view = new CircleView(getContext());
                break;
            default:
                view = new TextView(getContext());
                ((TextView) view).setText("内容布局...");
                break;

        }
        if (view != null) {
            // TODO: 2016/6/20  
            if (view instanceof Determinate) {
                mDeterminateView = (Determinate) view;
            }
          /*  if (view instanceof Indeterminate) {
                mDeterminateView = (Indeterminate) view;
            }*/
            if (isShowing()) {
                mContainer.removeAllViews();
            }
            addViewToFrame(view);
        }

    }

    private void schedulechange() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DProgressHUD.this.setLabel("操作完成");
                DProgressHUD.this.setCustomView(ALERT_ACTION_DONE);
            }
        }, 2000);
    }

    private void scheduleDissmiss() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DProgressHUD.this.dismiss2();
            }
        }, 2000);
    }

    public void dismiss2() {
        if (isShowing()) {
            this.dismiss();
        }
    }

    /**
     * 设置 determinateview  的经度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        if (mDeterminateView != null) {
            mDeterminateView.setProgress(progress);
            if (progress >= 100) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (DProgressHUD.this.isShowing()) {
                            DProgressHUD.this.dismiss();
                        }
                    }
                }, 300);
            }
        }
    }

    protected void addViewToFrame(View view) {
        if (view == null) {
            throw new RuntimeException("view can not be null!");
        }
        int wrapParam = ViewGroup.LayoutParams.WRAP_CONTENT;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(wrapParam, wrapParam);
        mContainer.addView(view, params);
    }
}
