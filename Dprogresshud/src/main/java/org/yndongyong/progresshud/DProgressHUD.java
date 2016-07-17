package org.yndongyong.progresshud;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.yndongyong.progresshud.layout.ProgressLinearLayout;

/**
 * Created by Dong on 2016/6/12.
 */
public class DProgressHUD {
    
    public enum Style{
        SPIN_INDETERMINATE,NORMAL
    }

    private DProgressDialog dialog;
    private Builder mBuilder;

    private boolean mIsAutoDismiss;

    public DProgressHUD(Builder builder) {
        mBuilder = builder;
        dialog = new DProgressDialog(builder._context);
        

        setStyle(mBuilder._style);        
        
    }

    private void setStyle(Style style) {
        View view = null;
        switch (style) {
            case SPIN_INDETERMINATE:
//                view = new Dialog(mBuilder._context);
                view = new TextView(mBuilder._context);
                ((TextView)view).setText("loading...");
                break;
        }
        
        dialog.setView(view);
                
    }

    public void show() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public static class Builder {
        int _bgColor;
        int _cornerRadius;//圆角大小
        CharSequence _label;

        Style _style;


        Context _context;

        public Builder(Context context) {
            this._context = context;
            _bgColor = context.getResources().getColor(R.color.dyprogresshud_default_color);
            _cornerRadius = context.getResources().getDimensionPixelOffset(R.dimen.dyprogresshud_default_radius);
        }

        public Builder setBackgroundColor(int color) {
            _bgColor = color;
            return this;
        }

        public Builder setCornerRadius(int radius) {
            _cornerRadius = radius;
            return this;
        }

        public Builder setLabel(CharSequence sequence) {
            _label = sequence;
            return this;
        }

        public Builder setStyle(Style style) {
            _style = style;
            return this;
        }

        public DProgressHUD build() {
            return new DProgressHUD(this);
        }

    }

    private class DProgressDialog extends Dialog {
        
        private ProgressLinearLayout mBackground;
        private FrameLayout mContainer;
        private TextView mLabel;

        private View mView;
        
        public DProgressDialog(Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);

            setContentView(R.layout.dyprogresshud_layout);

            Window window = getWindow();
            window.setBackgroundDrawable(new ColorDrawable(0));
            
            WindowManager.LayoutParams LayoutParams = getWindow().getAttributes();
            LayoutParams.gravity = Gravity.CENTER;
            window.setAttributes(LayoutParams);

            intiViewAndEvent();
        }

        private void intiViewAndEvent() {
            mBackground= (ProgressLinearLayout) findViewById(R.id.background);
            mContainer = (FrameLayout) findViewById(R.id.container);
            mLabel = (TextView) findViewById(R.id.label);

            mBackground.setBackgroundColor(mBuilder._bgColor);
            mBackground.setCornerRadius(mBuilder._cornerRadius);

            updateBackgroundSize();

            if (!TextUtils.isEmpty(mBuilder._label)) {
                mLabel.setText(mBuilder._label);
                mLabel.setVisibility(View.VISIBLE);
            }

        }

        private void addView2Frame(View view) {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                    .WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (isShowing()) {
                mContainer.removeAllViews();
            }
            mContainer.addView(view,params);
        }

        private void setView(View view) {
            // TODO: 2016/6/12 判断view的类型 
            addView2Frame(view);
        }

        private void updateBackgroundSize() {
            int width;
            int height;

            int Cwidth = 0;
            int Cheight = 0;
            
            int Twidth = 0;
            int Theight =0;
            
            if (mContainer != null) {
                ViewGroup.LayoutParams layoutParams = mContainer.getLayoutParams();
                Cwidth = layoutParams.width;
                Cheight = layoutParams.height;
            }

            if (mLabel != null) {
                ViewGroup.LayoutParams layoutParams = mLabel.getLayoutParams();
                Twidth = layoutParams.width;
                Cheight = layoutParams.height;
            }
            
            //deal width;

            width = Math.max(Cwidth, Twidth);

            if (mContainer != null) {
                ViewGroup.LayoutParams layoutParams = mContainer.getLayoutParams();
                layoutParams.width = width;
                mContainer.setLayoutParams(layoutParams);
            }
                
                
        }
    }
    
    
    
}
