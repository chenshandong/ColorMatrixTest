package com.chensd.colormatrixtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by chensd on 2016/10/9.
 */
public class EraserView extends View {

    public static final int MIN_MOVE_DIS = 5;
    private Context mContext;
    private int screenW, screenH;
    private Path mPath;
    private Paint mPaint;
    private Canvas mCanvas;
    private Bitmap fgBitmap, bgBitmap;
    private float preX, preY;


    public EraserView(Context context) {
        super(context);
    }

    public EraserView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        cal();

        init();
    }

    private void init() {
        mPath = new Path();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

        mPaint.setARGB(0x00, 255, 0, 0);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setStrokeWidth(50);
        
        mPaint.setStrokeJoin(Paint.Join.ROUND);

        mPaint.setStrokeCap(Paint.Cap.ROUND);

        fgBitmap = Bitmap.createBitmap(screenW, screenH, Bitmap.Config.ARGB_8888);

        mCanvas = new Canvas(fgBitmap);

//        fgBitmap.eraseColor(0xFF808080);// 此种方法也可以
        mCanvas.drawColor(0xFF808080);

        bgBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.b);

        bgBitmap = Bitmap.createScaledBitmap(bgBitmap, screenW, screenH, true);

    }

    private void cal() {
        int[] screenSize = DeviceUtil.getScreenSize(mContext);
        screenW = screenSize[0];
        screenH = screenSize[1];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bgBitmap, 0, 0, null);

        canvas.drawBitmap(fgBitmap, 0, 0, null);

        mCanvas.drawPath(mPath, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(x, y);
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                // 绝对值 也就是偏移量
                float dx = Math.abs(x - preX);
                float dy = Math.abs(y - preY);

                if (dx >= MIN_MOVE_DIS || dy >= MIN_MOVE_DIS) {
                    mPath.quadTo(preX, preY, (x + preX) / 2, (y + preY) / 2);
//                    mPath.quadTo(preX, preY, x, y);
//                    mPath.lineTo(x, y);
                    preX = x;
                    preY = y;
                }
                break;

        }
        invalidate();
        return true;
    }
}
