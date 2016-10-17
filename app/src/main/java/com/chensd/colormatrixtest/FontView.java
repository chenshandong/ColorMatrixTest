package com.chensd.colormatrixtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by chensd on 2016/10/9.
 */
public class FontView extends View {

    private static final String TEXT = "ap栋哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓";
    private Paint mPaint;
    private Paint.FontMetrics mFontMetrics;

    public FontView(Context context) {
        super(context);
    }

    public FontView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setTextSize(50);
        mPaint.setColor(Color.BLACK);

        mFontMetrics = mPaint.getFontMetrics();

        Log.d("Font", "ascent：" + mFontMetrics.ascent);
        Log.d("Font", "top：" + mFontMetrics.top);
        Log.d("Font", "leading：" + mFontMetrics.leading);
        Log.d("Font", "descent：" + mFontMetrics.descent);
        Log.d("Font", "bottom：" + mFontMetrics.bottom);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText(TEXT, 0, Math.abs(mFontMetrics.ascent), mPaint);
    }
}
