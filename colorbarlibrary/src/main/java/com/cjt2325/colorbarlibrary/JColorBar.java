package com.cjt2325.colorbarlibrary;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * =====================================
 * 作    者: 陈嘉桐 445263848
 * 版    本：1.1.4
 * 创建日期：2017/5/25
 * 描    述：颜色选择
 * =====================================
 */
public class JColorBar extends View {
    private float x = 10;
    private int postion_Y = 100;
    private int gradient = 50;
    private int strokeWidth = 20;
    private int offset_X = 40;
    private Path path;
    private int selectedColor;

    private Paint mPaint;
    private int[] colors = {
            0xFFFFFFFF,
            0XFFF03411,
            0XFFFEBB46,
            0XFFFFE900,
            0XFFC3FE30,
            0XFF00B109,
            0XFF25D1FF,
            0XFF157AFC,
            0XFF0037C6,
            0XFF925AF9,
            0XFFFE99FF,
            0XFFFC6A8F,
            0XFFF725BA
    };

    public JColorBar(Context context) {
        this(context, null);
    }

    public JColorBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JColorBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        path = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        offset_X = getWidth() / 6;
        gradient = (int) ((getWidth() - offset_X * 1.4) / colors.length);
        strokeWidth = getWidth() / 45;
        x = (int) (strokeWidth / 1.5) + offset_X;
        postion_Y = getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStrokeWidth(strokeWidth);
        for (int i = 0; i < colors.length; i++) {
            mPaint.setColor(colors[i]);
            if (i == 0) {
                canvas.drawCircle(i * gradient + strokeWidth / 2 + offset_X, postion_Y, strokeWidth / 2, mPaint);
                canvas.drawLine(i * gradient + strokeWidth / 2 + offset_X, postion_Y, (i + 1) * gradient + offset_X,
                        postion_Y,
                        mPaint);
            } else if (i == colors.length - 1) {
                canvas.drawLine(i * gradient + offset_X, postion_Y, (i + 1) * gradient - strokeWidth / 2 + offset_X,
                        postion_Y,
                        mPaint);
                canvas.drawCircle((i + 1) * gradient - strokeWidth / 2 + offset_X, postion_Y, strokeWidth / 2, mPaint);
            } else {
                canvas.drawLine(i * gradient + offset_X, postion_Y, (i + 1) * gradient + offset_X, postion_Y, mPaint);
            }
        }
        mPaint.reset();
        mPaint.setColor(0xffffffff);
        mPaint.setAntiAlias(true);

        path.reset();
        path.arcTo(new RectF(x - (int) (strokeWidth / 1.5), postion_Y - 2 * (int) (strokeWidth / 1.5), x + (int)
                (strokeWidth / 1.5), postion_Y), 0, -180);
        path.arcTo(new RectF(x - (int) (strokeWidth / 1.5), postion_Y, x + (int) (strokeWidth / 1.5), postion_Y + 2 *
                (int)
                        (strokeWidth / 1.5)), 180, -180);
        canvas.drawPath(path, mPaint);
        mPaint.reset();
        int index = (int) ((x - offset_X) / gradient);
        if (index <= 0) {
            index = 0;
        } else if (index >= colors.length) {
            index = colors.length - 1;
        }
        selectedColor = colors[index];
        if (listener != null) {
            listener.update(selectedColor);
        }
        mPaint.setColor(colors[index]);
        mPaint.setAntiAlias(true);
        path.reset();
        path.arcTo(new RectF(x - strokeWidth / 2, postion_Y - strokeWidth - strokeWidth / 6, x + strokeWidth / 2,
                postion_Y -
                        strokeWidth / 6), 0, -180);
        path.arcTo(new RectF(x - strokeWidth / 2, postion_Y + strokeWidth / 6, x + strokeWidth / 2, postion_Y +
                strokeWidth +
                strokeWidth / 6), 180, -180);
        canvas.drawPath(path, mPaint);

        mPaint.reset();
        mPaint.setColor(colors[index]);
        mPaint.setAntiAlias(true);

        canvas.drawCircle(offset_X / 2, getHeight() / 2, strokeWidth, mPaint);
    }

    private boolean readyMover = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() >= (strokeWidth / 1.5) + offset_X &&
                        event.getX() <= colors.length * gradient - (int) (strokeWidth / 1.5) + offset_X &&
                        event.getY() >= postion_Y - strokeWidth * 4 &&
                        event.getY() <= postion_Y + strokeWidth * 4) {
                    readyMover = true;
                }
            case MotionEvent.ACTION_MOVE:
                if (!readyMover) {
                    break;
                }
                if (event.getX() <= (int) (strokeWidth / 1.5) + offset_X) {
                    x = (int) (strokeWidth / 1.5) + offset_X;
                } else if (event.getX() > colors.length * gradient - (int) (strokeWidth / 1.5) + offset_X) {
                    x = colors.length * gradient - (int) (strokeWidth / 1.5) + offset_X;
                } else {
                    x = (int) event.getX();
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                readyMover = false;
                int index = (int) ((x - offset_X) / gradient);
                if (index <= 0) {
                    index = 0;
                } else if (index >= colors.length) {
                    index = colors.length - 1;
                }
                startAnimation(x, index * gradient + gradient / 2 + offset_X);
        }
        return true;
    }

    private void startAnimation(float start_X, float end_X) {
        ValueAnimator animator = new ValueAnimator().ofFloat(start_X, end_X);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                x = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(500);
        animator.start();
    }

    public void setColors(int[] colors) {
        this.colors = colors;
        gradient = (int) ((getWidth() - offset_X * 1.4) / colors.length);
        strokeWidth = getWidth() / 45;
        postion_Y = getHeight() / 2;
        invalidate();
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    private ColorsUdateListener listener;

    public void setColorsUdateListener(ColorsUdateListener listener) {
        this.listener = listener;
    }

    public interface ColorsUdateListener {
        void update(int color);
    }
}
