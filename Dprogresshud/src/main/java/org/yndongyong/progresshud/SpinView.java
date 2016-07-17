package org.yndongyong.progresshud;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by Dong on 2016/6/20.
 */
public class SpinView extends ImageView implements Indeterminate {

    private float mRotateDegrees;
    private boolean isNeedUpdate = true;
    private Runnable viewUpdate;
    private long delayMillis;


    public SpinView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setImageResource(R.drawable.kprogresshud_spinner);
//        setImageResource(R.drawable.ic_refresh_white_48dp);
        delayMillis = 1000 / 7;
        viewUpdate = new Runnable() {
            @Override
            public void run() {
                if (isNeedUpdate) {
                    mRotateDegrees += 20;
                    mRotateDegrees = mRotateDegrees < 360 ? mRotateDegrees : mRotateDegrees - 360;
                    invalidate();
                    postDelayed(this, delayMillis);
                }
            }
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(mRotateDegrees, getWidth() / 2, getHeight() / 2);
        super.onDraw(canvas);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
       /* // 加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                getContext(), R.anim.loading_animation);
        // 使用ImageView显示动画
        startAnimation(hyperspaceJumpAnimation);*/
        isNeedUpdate = true;
        post(viewUpdate);

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isNeedUpdate = false;
    }

    @Override
    public void setAnimationSpeed(float speed) {
        delayMillis = (long) (1000 / 6 / speed);

    }
}
