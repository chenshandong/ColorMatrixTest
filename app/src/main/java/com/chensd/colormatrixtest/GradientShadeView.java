package com.chensd.colormatrixtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chensd on 2016/10/10.
 */
public class GradientShadeView extends View {

    private Paint mPaint;
    private static final int RECT_SIZE = 300;
    private int screenX, screenY, left, top, right, bottom;

    public GradientShadeView(Context context) {
        super(context);
    }

    public GradientShadeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    private void initPaint(Context context) {

        int[] screenSize = DeviceUtil.getScreenSize(context);
        screenX = screenSize[0] / 2;
        screenY = screenSize[1] / 2;

        left = screenX - RECT_SIZE;
        top = screenY - RECT_SIZE;
        right = screenX + RECT_SIZE;
        bottom = screenY + RECT_SIZE;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);

//        mPaint.setShader(new LinearGradient(left, top, right, bottom, new int[]{Color.RED, Color.GREEN, Color.YELLOW}, null, Shader.TileMode.MIRROR));
        mPaint.setShader(new SweepGradient(screenX,screenY,Color.RED,Color.YELLOW));

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawRect(left, top, right, bottom, mPaint);
        canvas.drawCircle(screenX,screenY,300,mPaint);
    }

}
