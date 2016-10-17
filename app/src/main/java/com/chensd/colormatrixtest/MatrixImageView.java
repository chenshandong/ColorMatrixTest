package com.chensd.colormatrixtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by chensd on 2016/10/10.
 */
public class MatrixImageView extends ImageView {
    private Context mContext;

    private static final int MODE_NONE = 0X00123;
    private static final int MODE_DRAG = 0X00321;
    private static final int MODE_ZOOM = 0X00132;

    private int mode;

    private Matrix currentMatrix;
    private Matrix savedMatrix;
    private PointF start;
    private PointF mid;

    private float[] preEventCoor;
    private float preMove = 1f;
    private float saveRotate = 0F;// 保存了的角度值
    private float rotate = 0F;// 旋转的角度


    public MatrixImageView(Context context) {
        super(context);
    }

    public MatrixImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        init();

    }

    private void init() {
        currentMatrix = new Matrix();
        savedMatrix = new Matrix();
        start = new PointF();
        mid = new PointF();

        mode = MODE_NONE;

        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.mylove);
        bitmap = Bitmap.createScaledBitmap(bitmap, DeviceUtil.getScreenWidth(mContext), DeviceUtil.getScreenHeight(mContext), true);
        setImageBitmap(bitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(currentMatrix);
                start.set(event.getX(), event.getY());
                mode = MODE_DRAG;
                preEventCoor = null;

                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                preMove = calSpacing(event);
                if (preMove > 10f) {
                    savedMatrix.set(currentMatrix);
                    calMidPoint(mid, event);
                    mode = MODE_ZOOM;

                }

                preEventCoor = new float[4];
                preEventCoor[0] = event.getX(0);
                preEventCoor[1] = event.getX(1);
                preEventCoor[2] = event.getY(0);
                preEventCoor[3] = event.getY(1);

                saveRotate = calRotation(event);
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == MODE_DRAG) {
                    currentMatrix.set(savedMatrix);
                    float dx = event.getX() - start.x;
                    float dy = event.getY() - start.y;
                    currentMatrix.postTranslate(dx, dy);
                }
                else if (mode == MODE_ZOOM && event.getPointerCount() == 2) {
                    float currentMove = calSpacing(event);
                    currentMatrix.set(savedMatrix);

                    if (currentMove > 10f) {
                        float scale = currentMove / preMove;
                        currentMatrix.postScale(scale, scale, mid.x, mid.y);
                    }

                    if(preEventCoor != null){
                        rotate = calRotation(event);
                        float r = rotate - saveRotate;
                        currentMatrix.postRotate(r, getMeasuredWidth()/2, getMeasuredHeight()/2);

                    }
                }
                break;
            default:
                break;
        }

        setImageMatrix(currentMatrix);
        return true;
    }

    /**
     * 计算两个触摸点间的距离
     */
    private float calSpacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * 计算两个触摸点的中点坐标
     */
    private void calMidPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    /**
     * 计算旋转角度
     *
     * @return 角度值
     */
    private float calRotation(MotionEvent event) {
        double deltaX = (event.getX(0) - event.getX(1));
        double deltaY = (event.getY(0) - event.getY(1));
        double radius = Math.atan2(deltaY, deltaX);
        return (float) Math.toDegrees(radius);
    }

}
