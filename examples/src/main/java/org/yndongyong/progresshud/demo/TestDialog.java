package org.yndongyong.progresshud.demo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Dong on 2016/6/20.
 */
public class TestDialog extends Dialog {


    public TestDialog(Context context) {
        super(context);
    }

    public TestDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static TestDialog show(Context context, String message) {

        TestDialog dialog = new TestDialog(context);
        dialog.setTitle(message);

        return dialog;
    }

    @Override
    public void show() {
        Log.d("TAG", "show myself() ");
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
        Log.d("TAG", "hide() ");
    }

    @Override
    public void dismiss() {
        super.dismiss();
        Log.d("TAG", "dismiss() ");
    }

    private View rootView;
    private TextView mMessageView; //提示文本
    private FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "onCreate() Bundle");

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(org.yndongyong.progresshud.R.layout.dyprogresshud_layout, null);

        rootView = view.findViewById(org.yndongyong.progresshud.R.id.dy_progresshud_view);
        mContainer = (FrameLayout) view.findViewById(org.yndongyong.progresshud.R.id.container);
        mMessageView = (TextView) view.findViewById(org.yndongyong.progresshud.R.id.tipTextView);

        setContentView(view, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG", "onStart() ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG", "onStop() ");
    }

    @Override
    public Bundle onSaveInstanceState() {
        Log.d("TAG", "onSaveInstanceState() ");
        return super.onSaveInstanceState();
    }
}
