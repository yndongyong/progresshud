package org.yndongyong.progresshud.layout;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import org.yndongyong.progresshud.R;
import org.yndongyong.progresshud.utils.PixUtils;

/**
 * Created by Dong on 2016/6/12.LinearLayout
 */
public class ProgressLinearLayout extends LinearLayout {

    private static final String TAG = ProgressLinearLayout.class.getSimpleName();


    private int mWidth;
    private int mHeight;

    public ProgressLinearLayout(Context context) {
        this(context, null);
    }

    public ProgressLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ProgressLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        /**
         * 设置宽度 
         */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);


        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate  
        {
            Log.e(TAG, "EXACTLY");
            mWidth = specSize;
        } else {

            // 由图片决定的宽  
            int desireByImg = getPaddingLeft() + getPaddingRight() + getChildAt(0)
                    .getWidth();
           /* FrameLayout frameLayout = (FrameLayout)getChildAt(0);
            if (frameLayout.getChildAt(0) != null) {
                desireByImg = getPaddingLeft() + getPaddingRight() + frameLayout.getChildAt(0).getWidth();
            }*/
            // 由字体决定的宽  
            int desireByTitle = getPaddingLeft() + getPaddingRight() + getChildAt(1).getWidth();

            if (specMode == MeasureSpec.AT_MOST)// wrap_content  
            {
                int desire = Math.max(desireByImg, desireByTitle);
                mWidth = Math.min(desire, specSize);
                Log.e(TAG, "AT_MOST");
            }
        }

        /*** 
         * 设置高度 
         */

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate  
        {
            mHeight = specSize;
        } else {
            int desire = getPaddingTop() + getPaddingBottom() + getChildAt(0).getHeight() +
                    getChildAt(1).getHeight();
            if (specMode == MeasureSpec.AT_MOST)// wrap_content  
            {
                mHeight = Math.min(desire, specSize);
            }
        }
        setMeasuredDimension(mWidth, mHeight);
    }

}
