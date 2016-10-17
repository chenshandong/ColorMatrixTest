package com.chensd.colormatrixtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chensd on 2016/10/8.
 */
public class ColorMatrixView extends View {

    private Context mContext;
    private Paint mPaint;
    private int x, y;
    private Bitmap srcBitmap, disBitmap;
    private boolean isClick;
    private PorterDuffXfermode porterDuffXfermode;
    private int screenW, screenH;

    public ColorMatrixView(Context context) {
        super(context);
    }

    public ColorMatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);

        initPaint();

        initRes();

//        setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isClick){
//                    mPaint.setColorFilter(null);
//                    isClick = false;
//                }else{
//                    mPaint.setColorFilter(new LightingColorFilter(0xffffffff, 0x00FF0000));
//                    isClick= true;
//                }
//
//                invalidate();
//            }
//        });
    }

    private void initRes() {
        disBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.b);
        srcBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.b_mask);

        screenW = DeviceUtil.getScreenSize(mContext)[0];
        screenH = DeviceUtil.getScreenSize(mContext)[1];

        x = screenW / 2 - disBitmap.getWidth() / 2;
        y = screenH / 2 - disBitmap.getHeight() / 2;
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setColor(Color.argb(255,255,128,103));

//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//                0.33F, 0.59F, 0.11F, 0, 0,
//                0.33F, 0.59F, 0.11F, 0, 0,
//                0.33F, 0.59F, 0.11F, 0, 0,
//                0, 0, 0, 1, 0,
//
//        });
//        colorMatrix.setSaturation(30);
//        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawCircle(DeviceUtil.getScreenWidth(mContext)/2, DeviceUtil.getScreenHeight(mContext)/2, 200, mPaint);

        canvas.drawColor(Color.WHITE);
//
        int sc = canvas.saveLayer(0, 0, screenW, screenH, null, Canvas.ALL_SAVE_FLAG);
//
//        canvas.drawBitmap(disBitmap, x, y, mPaint);
//
        canvas.drawColor(0xcc1c093e);

        mPaint.setXfermode(porterDuffXfermode);

        canvas.drawBitmap(disBitmap, x, y, mPaint);

        mPaint.setXfermode(null);

        canvas.restoreToCount(sc);
    }
}
