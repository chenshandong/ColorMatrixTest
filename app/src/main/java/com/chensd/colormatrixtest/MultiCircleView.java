package com.chensd.colormatrixtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chensd on 2016/10/11.
 */
public class MultiCircleView extends View {

    public static final float STROKE_WIDTH = 1F / 256F,
            LINE_LENGTH = 3F / 32F,
            CRICLE_LARGER_RADIU = 3F / 32F,// 大圆半径
            CRICLE_SMALL_RADIU = 5F / 64F,// 小圆半径
            ARC_RADIU = 1F / 8F,// 弧半径
            ARC_TEXT_RADIU = 5F / 32F;// 弧围绕文字半径
    private Paint mStrokePaint;
    private int size;
    private float strokeWidth;
    private float largeCircleRadiu;
    private int ccX;
    private float ccY;
    private float lineLenght;
    private TextPaint textPaint;


    public MultiCircleView(Context context) {
        super(context);
    }

    public MultiCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeCap(Paint.Cap.ROUND);
        mStrokePaint.setColor(Color.WHITE);

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
//        textPaint.setColor();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //强制长宽一致
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        size = w;
        calculation();
    }

    private void calculation() {
        strokeWidth = STROKE_WIDTH * size;

        largeCircleRadiu = size * CRICLE_LARGER_RADIU;

        ccX = size / 2;

        ccY = size / 2 + largeCircleRadiu;

        lineLenght = size * LINE_LENGTH;

        mStrokePaint.setStrokeWidth(strokeWidth);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(0xFFF29B76);
        canvas.drawCircle(ccX, ccY, largeCircleRadiu, mStrokePaint);

        drawTopLeft(canvas);
    }

    private void drawTopLeft(Canvas canvas) {
        canvas.save();
        canvas.translate(ccX, ccY);
        canvas.rotate(-30);

        canvas.drawLine(0, -largeCircleRadiu, 0, -lineLenght * 2, mStrokePaint);
        canvas.drawCircle(0, -largeCircleRadiu * 3, largeCircleRadiu, mStrokePaint);
        canvas.drawLine(0, -largeCircleRadiu * 4, 0, -largeCircleRadiu * 5, mStrokePaint);
        canvas.drawCircle(0, -largeCircleRadiu * 6, largeCircleRadiu, mStrokePaint);
        canvas.restore();
    }
}
