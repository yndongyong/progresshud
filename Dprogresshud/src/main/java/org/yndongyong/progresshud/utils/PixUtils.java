package org.yndongyong.progresshud.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by Dong on 2016/6/12.
 */
public class PixUtils {
    /**
     * 描述：dip转换为px.
     *
     * @param context the context
     * @param dipValue the dip value
     * @return px值
     */
    public static float dip2px(Context context, float dipValue) {
        DisplayMetrics mDisplayMetrics = context.getResources().getDisplayMetrics();
        return applyDimension(TypedValue.COMPLEX_UNIT_DIP,dipValue,mDisplayMetrics);
    }

    /**
     * TypedValue官方源码中的算法，任意单位转换为PX单位
     * @param unit  TypedValue.COMPLEX_UNIT_DIP
     * @param value 对应单位的值
     * @param metrics 密度
     * @return px值
     */
    public static float applyDimension(int unit, float value,
                                       DisplayMetrics metrics){
        switch (unit) {
            case TypedValue.COMPLEX_UNIT_PX:
                return value;
            case TypedValue.COMPLEX_UNIT_DIP:
                return value * metrics.density;
            case TypedValue.COMPLEX_UNIT_SP:
                return value * metrics.scaledDensity;
            case TypedValue.COMPLEX_UNIT_PT:
                return value * metrics.xdpi * (1.0f/72);
            case TypedValue.COMPLEX_UNIT_IN:
                return value * metrics.xdpi;
            case TypedValue.COMPLEX_UNIT_MM:
                return value * metrics.xdpi * (1.0f/25.4f);
        }
        return 0;
    }

    /**
     * 描述：px转换为dip.
     *
     * @param context the context
     * @param pxValue the px value
     * @return dip值
     */
    public static float px2dip(Context context, float pxValue) {
        DisplayMetrics mDisplayMetrics = context.getResources().getDisplayMetrics();
        return pxValue / mDisplayMetrics.density;
    }
}
