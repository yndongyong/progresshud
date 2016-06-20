package org.yndongyong.progresshud.layout;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import org.yndongyong.progresshud.R;
import org.yndongyong.progresshud.utils.PixUtils;

/**
 * Created by Dong on 2016/6/12.LinearLayout
 */
public class ProgressLinearLayout extends LinearLayout {

    private int mBgColor;
    private int mCornerRadius = 4;//圆角大小


    public ProgressLinearLayout(Context context) {
        this(context, null);
    }

    public ProgressLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ProgressLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStyle();
    }

    private void initStyle() {
        mBgColor = getContext().getResources().getColor(R.color.dyprogresshud_default_color);
        mCornerRadius = getContext().getResources().getDimensionPixelOffset(R.dimen
                .dyprogresshud_default_radius);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initLayoutbackground(int color, int radius) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(PixUtils.dip2px(getContext(),radius));
        setBackground(gradientDrawable);
    }

    /**
     * 设置整个视图的背景色
     * @param color
     */
    public void setBackgroundColor(int color) {
        this.mBgColor = color;
        initLayoutbackground(mBgColor, mCornerRadius);
    }

    /**
     * 设置圆角大小
     * @param radius
     */
    public void setCornerRadius(int radius) {
        this.mCornerRadius = radius;
        initLayoutbackground(mBgColor, mCornerRadius);
        
    }
    
    
}
