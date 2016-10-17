package com.chensd.colormatrixtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chensd on 2016/10/10.
 */
public class ShaderView extends View {
    private Context mContext;
    private static final int RECT_SIZE = 400;

    private int left, top, right, bottom;
    private Paint mPaint;
    private Bitmap bitmap;
    private int screenX, screenY;
    private BitmapShader bitmapShader;

    public ShaderView(Context context) {
        super(context);
    }

    public ShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        int[] screenSize = DeviceUtil.getScreenSize(context);
        screenX = screenSize[0] / 2;
        screenY = screenSize[1] / 2;

        left = screenX - RECT_SIZE;
        top = screenY - RECT_SIZE;
        right = screenY + RECT_SIZE;
        bottom = screenY + RECT_SIZE;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.a);

        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Matrix matrix = new Matrix();
        matrix.setTranslate(left, top);
//        matrix.setRotate(5);
        matrix.preRotate(5);
        bitmapShader.setLocalMatrix(matrix);

        mPaint.setShader(bitmapShader);

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawBitmap(bitmap, screenX, screenY, mPaint);
        canvas.drawRect(0, 0, screenX * 2, screenY * 2, mPaint);
    }
}
