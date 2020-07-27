package com.xiaoqiang.appstateview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * @author Android-小强
 * @email: 15075818555@163.com
 * @data: on 2020/3/18 15:47
 */
public class LoadProgress extends View {
    private float scale = 1;
    private int alpha = 255;
    /**
     * 画笔
     */
    private Paint mPaint;
    private int mPaintWidth;
    private int colorPaint;
    private AnimatorSet mAnimatorSet;

    public LoadProgress(Context context) {
        this(context, null);
    }

    public LoadProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    /**
     * 初始化
     *
     * @param context
     * @param attrs
     */
    private void initView(Context context, AttributeSet attrs) {
        colorPaint = Color.parseColor("#ffffff");
        mPaintWidth = dpTopx(2);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);


        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LoadProgress);
            if (array != null) {
                mPaintWidth = (int) array.getDimension(R.styleable.LoadProgress_load_paint_width, mPaintWidth);
                colorPaint = array.getColor(R.styleable.LoadProgress_load_color, colorPaint);
                array.recycle();
            }
        }
        mPaint.setColor(colorPaint);
        mPaint.setStrokeWidth(mPaintWidth);
    }

    /**
     * 单位换算
     *
     * @param dp
     * @return
     */
    public int dpTopx(int dp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics()) + 0.5F);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LoadProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

    public void setScale(float scale) {
        this.scale = scale;
        postInvalidate();
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
        postInvalidate();
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == VISIBLE) {
            initAnimation();
        } else {
            closeAnimation();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float v = mPaintWidth * 0.5F;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.scale(scale, scale, getWidth() / 2, getHeight() / 2);
        mPaint.setAlpha(alpha);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, mPaint);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        closeAnimation();
        mAnimatorSet = null;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.UNSPECIFIED) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(dpTopx(25), MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    /**
     * 开始动画
     */
    private void initAnimation() {
        Log.d("123456", "initAnimation: ");
        if (mAnimatorSet == null) {
            mAnimatorSet = new AnimatorSet();
            ObjectAnimator startAngle = ObjectAnimator.ofFloat(this, "scale", 0F, 1F);
            startAngle.setRepeatCount(ObjectAnimator.INFINITE);
            ObjectAnimator sweepAngle = ObjectAnimator.ofInt(this, "alpha", 255, 0);
            sweepAngle.setRepeatCount(ObjectAnimator.INFINITE);

            mAnimatorSet.playTogether(startAngle, sweepAngle);
            mAnimatorSet.setDuration(1000);
            mAnimatorSet.setInterpolator(new LinearInterpolator());
        }
        if (!mAnimatorSet.isRunning()) {
            mAnimatorSet.start();
        }

    }

    /**
     * 结束动画
     */
    private void closeAnimation() {
        Log.d("123456", "closeAnimation: ");
        if (mAnimatorSet != null) {
            mAnimatorSet.cancel();
        }
    }
}
