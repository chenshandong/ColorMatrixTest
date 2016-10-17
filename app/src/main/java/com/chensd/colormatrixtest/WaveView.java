package com.chensd.colormatrixtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by chensd on 2016/10/12.
 */
public class WaveView extends View {

    private Paint mPaint;
    private Path mPath;
    private int vWidth, vHeight;

    private float waveY, ctrX, ctrY;
    private boolean isInc;

    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(0xFFA2D6AE);
        mPaint.setStyle(Paint.Style.FILL);

        mPath = new Path();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        vWidth = w;
        vHeight = h;

        waveY = 1 / 8f * vHeight; //两端的y坐标
        ctrY = -1 / 8f * vHeight; // 控制点的y坐标

        Log.e("ctrY",ctrY+"");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float startX = -1 / 4f * vWidth;
        float endX = vWidth - startX;
        mPath.moveTo(startX, waveY);
        mPath.quadTo(ctrX, ctrY, endX, waveY);

        mPath.lineTo(endX, vHeight);
        mPath.lineTo(startX, vHeight);
        mPath.close();

        canvas.drawPath(mPath, mPaint);

        if(ctrX >= endX){
            isInc = false;
        }

        if(ctrX <= startX){
            isInc = true;
        }

        ctrX = isInc ? ctrX + 20 : ctrX - 20;

        if(ctrY <= vHeight){
            ctrY += 2;
            waveY += 2;
        }

        mPath.reset();

        invalidate();

    }
}
