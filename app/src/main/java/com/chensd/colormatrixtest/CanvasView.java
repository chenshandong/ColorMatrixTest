package com.chensd.colormatrixtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chensd on 2016/10/12.
 */
public class CanvasView extends View {
    private int mViewWidth, mViewHeight;
    private Paint mPaint;

    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {

//        canvas.rotate(5);

        mPaint.setColor(Color.BLACK);
        canvas.drawRect(mViewWidth / 2f - 200, mViewHeight / 2f - 200, mViewWidth / 2f + 200, mViewHeight / 2f + 200, mPaint);

        canvas.save();
        canvas.rotate(20);
        mPaint.setColor(Color.RED);
        canvas.drawRect(mViewWidth / 2f - 100, mViewHeight / 2f - 100, mViewWidth / 2f + 100, mViewHeight / 2f + 100, mPaint);
        canvas.restore();
    }
}
