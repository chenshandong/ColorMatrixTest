package com.chensd.colormatrixtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chensd on 2016/10/9.
 */
public class PathEffectView extends View {
    private Paint mPaint;
    private Path mPath;
    private PathEffect[] pathEffects;
    private float mPhase;

    public PathEffectView(Context context) {
        super(context);
    }

    public PathEffectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.DKGRAY);

        mPath = new Path();

        mPath.moveTo(0, 0);

        for (int i = 0; i <= 30 ; i++) {
            mPath.lineTo(i*35, 0);
        }

        pathEffects = new PathEffect[7];

    }

    @Override
    protected void onDraw(Canvas canvas) {
        pathEffects[0] = null;
        pathEffects[1] = new CornerPathEffect(10);
        pathEffects[2] = new DiscretePathEffect(3.0f, 5.0f);
        pathEffects[3] = new DashPathEffect(new float[]{20, 20,9,7}, mPhase);
        Path path = new Path();
        path.addRect(0, 0, 8, 8, Path.Direction.CCW);
        pathEffects[4] = new PathDashPathEffect(path, 12, mPhase, PathDashPathEffect.Style.ROTATE);
        pathEffects[5] = new ComposePathEffect(pathEffects[2], pathEffects[4]);
        pathEffects[6] = new SumPathEffect(pathEffects[4], pathEffects[3]);

        /*
		 * 绘制路径
		 */
        for (int i = 0; i < pathEffects.length; i++) {
            mPaint.setPathEffect(pathEffects[i]);
            canvas.drawPath(mPath, mPaint);

            // 每绘制一条将画布向下平移250个像素
            canvas.translate(0, 150);
        }

        // 刷新偏移值并重绘视图实现动画效果
        mPhase += 1;
        invalidate();


    }

}
