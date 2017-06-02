package com.cjt2325.colorbarlibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.cjt2325.colorbarlibrary.bean.PathBean;
import com.cjt2325.colorbarlibrary.bean.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * =====================================
 * 作    者: 陈嘉桐
 * 版    本：
 * 创建日期：2017/5/31
 * 描    述：
 * =====================================
 */
public class GraffitiView extends View {
    private Paint mPaint;

    private List<PathBean> pathList;

    private PathBean bean;
    private PathBean tempBean;

    private int strokeWidth = 14;

    public GraffitiView(Context context) {
        this(context, null);
    }

    public GraffitiView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GraffitiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        pathList = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < pathList.size(); i++) {
            bean = pathList.get(i);
            mPaint.setColor(bean.getColor());
            if (pathList.get(i) != null) {
                if (bean.getStartPoint() != null) {
                    mPaint.setStyle(Paint.Style.FILL);
                    canvas.drawCircle(bean.getStartPoint().x, bean.getStartPoint().y, strokeWidth / 2, mPaint);
                }
                if (bean.getPath() != null) {
                    mPaint.setStyle(Paint.Style.STROKE);
                    canvas.drawPath(bean.getPath(), mPaint);
                }
                if (bean.getEndPoint() != null) {
                    mPaint.setStyle(Paint.Style.FILL);
                    canvas.drawCircle(bean.getEndPoint().x, bean.getEndPoint().y, strokeWidth / 2, mPaint);
                    Log.i("CJT", "end");
                }
            }
        }
    }

    float temt_x, temp_y;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                tempBean = new PathBean();
                tempBean.setStartPoint(new Point(event.getX(), event.getY()));
                tempBean.setColor(mPaint.getColor());
                Path path = new Path();
                path.moveTo(event.getX(), event.getY());
                temt_x = event.getX();
                temp_y = event.getY();
                tempBean.setPath(path);
                pathList.add(tempBean);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                tempBean.getPath().quadTo(temt_x, temp_y, (event.getX() + temt_x) / 2, (event.getY() + temp_y) / 2);
                temt_x = event.getX();
                temp_y = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                tempBean.getPath().quadTo(temt_x, temp_y, event.getX(), event.getY());
                tempBean.setEndPoint(new Point(event.getX(), event.getY()));
                invalidate();
                break;
        }
        return true;
    }

    public void setColor(int color) {
        mPaint.setColor(color);
    }
}
