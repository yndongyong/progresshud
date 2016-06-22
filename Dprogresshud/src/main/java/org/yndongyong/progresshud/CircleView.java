package org.yndongyong.progresshud;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import org.yndongyong.progresshud.utils.PixUtils;

/**
 * Created by Dong on 2016/6/22.
 */
public class CircleView extends View implements Determinate {

    private int mMax = 100;
    private int mProgress = 0;
    private Paint mFillPaint;
    private Paint mStrokPaint;

    private float width = 48.0f;
    private int raduis = 40;
    private float raduisF;

    private int indicatorOutColor = 0x80bbbbbb;
    private int indicatorInnerColor = 0x80eeeeee;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mStrokPaint = new Paint();
        mStrokPaint.setDither(true);
        mStrokPaint.setAntiAlias(true);
        mStrokPaint.setStyle(Paint.Style.STROKE);
        mStrokPaint.setColor(indicatorOutColor);
        mStrokPaint.setStrokeWidth(4);


        mFillPaint = new Paint();
        mFillPaint.setDither(true);
        mFillPaint.setAntiAlias(true);
        mFillPaint.setStyle(Paint.Style.FILL);
        mFillPaint.setColor(indicatorInnerColor);

        raduisF = PixUtils.dip2px(getContext(), raduis);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        float w = PixUtils.dip2px(getContext(), width);
        setMeasuredDimension((int) w,(int) w);
}

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(getWidth()/2,getHeight()/2);
        canvas.drawCircle(0, 0, raduisF, mStrokPaint);
        RectF rectF = new RectF();
        // TODO: 2016/6/22  
//        canvas.drawArc();
    }

    @Override
    public void setMax(int max) {
        this.mMax = max;
    }

    @Override
    public void setProgress(int progress) {
        this.mProgress = progress;
        invalidate();

    }
}
