package com.chensd.colormatrixtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by chensd on 2016/10/10.
 */
public class BrickShaderView extends View {

    private Paint mStrokePaint;
    private Paint mFillPaint;
    private float posX, posY;

    public BrickShaderView(Context context) {
        super(context);
    }

    public BrickShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint(context);
    }

    private void initPaint(Context context) {
        mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(5);
        mStrokePaint.setColor(0xff000000);

        mFillPaint = new Paint();

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.brick);
        mFillPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.DKGRAY);

        canvas.drawCircle(posX, posY, 200, mStrokePaint);
        canvas.drawCircle(posX, posY, 200, mFillPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                posX = event.getX();
                posY = event.getY();
                break;
            default:
                break;
        }

        invalidate();
        return true;
    }
}
